#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <iostream>
// process_jar.cpp - см. process_jar.hpp. Порт process_jar_with_stats() и
// вспомогательных функций отчётов из main.py (HANDOFF_43).
#include "process_jar.hpp"

#include <cstdio>
#include <filesystem>
#include <fstream>
#include <regex>
#include <sstream>

#include "classfile.hpp"
#include "engine.hpp"
#include "javatypes.hpp"
#include "lib_filter.hpp"
#include "naming_hints.hpp"
#include "platform_detect.hpp"
#include "pom_builder.hpp"
#include "render_class.hpp"
#include "str_decrypt.hpp"
#include "switchmap.hpp"
#include "zip_reader.hpp"

namespace fs = std::filesystem;

namespace nd {

namespace {

void write_text_file(const std::string& path, const std::string& text) {
    fs::create_directories(fs::path(path).parent_path());
    std::ofstream f(path, std::ios::binary);
    f << text;
}

void write_binary_file(const std::string& path, const std::vector<uint8_t>& data) {
    fs::create_directories(fs::path(path).parent_path());
    std::ofstream f(path, std::ios::binary);
    if (!data.empty()) f.write(reinterpret_cast<const char*>(data.data()), static_cast<std::streamsize>(data.size()));
}

bool starts_with(const std::string& s, const std::string& p) {
    return s.size() >= p.size() && s.compare(0, p.size(), p) == 0;
}

std::string dotted_to_path_prefix(const std::string& dotted) {
    std::string p = dotted;
    for (auto& c : p)
        if (c == '.') c = '/';
    return p + "/";
}

bool looks_obfuscated_wrapper(const std::string& name, const std::string& kind) { return looks_obfuscated(name, kind); }

}  // namespace

JarProcessResult process_jar_with_stats(const std::string& jar_path, const std::string& out_dir, bool skip_legitimacy) {
    JarProcessResult jr;
    jr.out_dir = out_dir;
    ProjectStats& stats = jr.stats;

    // --- 1. Сканирование на подозрительное содержимое (см. HANDOFF_31) ---
    jr.malware_findings = scan_jar(jar_path);

    std::string src_dir = (fs::path(out_dir) / "src" / "main" / "java").string();
    fs::create_directories(src_dir);

    ZipReader zr(jar_path);
    std::vector<std::string> all_names = zr.namelist();

    // --- 1.5. Определение платформы (см. platform_detect.hpp) - ДО
    // разбора классов: если это МОД (Fabric/Forge/NeoForge), а не
    // серверный плагин, декомпиляция сразу прерывается с понятным
    // сообщением, вместо траты времени на разбор сотен классов мода,
    // который всё равно не задуман под структуру серверного плагина.
    jr.platform = detect_platform(all_names, [&zr](const std::string& path) -> std::optional<std::string> {
        try {
            auto data = zr.read(path);
            return std::string(data.begin(), data.end());
        } catch (...) {
            return std::nullopt;
        }
    });
    if (jr.platform.is_mod()) {
        jr.mod_rejected = true;
        jr.mod_rejected_reason =
            "Обнаружен " + jr.platform.kind_label() + " (" + jr.platform.manifest_path + ") - это МОД, а не "
            "серверный плагин (Bukkit/Spigot/Paper/BungeeCord/Velocity). Временно моды не декомпилируются.";
        return jr;
    }

    std::vector<std::string> class_names;
    for (auto& n : all_names)
        if (n.size() >= 6 && n.substr(n.size() - 6) == ".class" && n.find("module-info") == std::string::npos) class_names.push_back(n);
    stats.classes_total = static_cast<int>(class_names.size());

    // --- 2. Разбор .class файлов ---
    std::map<std::string, ClassFile> class_files;
    std::vector<std::pair<std::string, std::string>> parse_errors;
    std::optional<std::string> plugin_yml_text;
    std::vector<std::string> class_order;  // порядок появления в jar - нужен find_active_decryptor_in_jar
    for (auto& n : class_names) {
        try {
            auto data = zr.read(n);
            ClassFile cf(data);
            std::string internal = cf.this_class_name;
            class_order.push_back(internal);
            class_files.emplace(internal, std::move(cf));
        } catch (const std::exception& e) {
            parse_errors.push_back({n, e.what()});
        }
    }
    stats.classes_parsed = static_cast<int>(class_files.size());
    stats.parse_errors = parse_errors;

    // --- 3. Расшифровщик строк (см. str_decrypt.hpp) - сброс состояния с
    // прошлого вызова В ЭТОМ ЖЕ процессе (важно для будущего API-сервера,
    // который может обработать несколько jar подряд без перезапуска). ---
    str_decrypt_set_active(std::nullopt);
    str_decrypt_reset_decrypted_count();
    {
        std::vector<std::pair<std::string, const ClassFile*>> classes_in_order;
        for (auto& internal : class_order) classes_in_order.emplace_back(internal, &class_files.at(internal));
        auto active = find_active_decryptor_in_jar(classes_in_order);
        if (active.has_value()) {
            jr.decrypted_strings_owner = active->owner;
            str_decrypt_set_active(active);
        }
    }
    // --- 4. pom.xml плагина (если есть) - нужен ДЛЯ relocated_library_prefixes
    // ДО решения, какие классы пропускать (см. lib_filter.hpp/HANDOFF_40). ---
    auto pom_early = find_pom_properties_and_xml(all_names, zr);
    std::string pom_xml_early = pom_early.xml_text.value_or("");
    auto relocated_prefixes = relocated_library_prefixes(pom_xml_early);
    auto sig_prefixes = signature_relocated_prefixes(all_names);
    relocated_prefixes.insert(relocated_prefixes.end(), sig_prefixes.begin(), sig_prefixes.end());
    // --- 5. Известные библиотеки - убираем из class_files (см. HANDOFF_40). ---
    std::vector<std::string> library_internal_names;
    std::set<std::string> library_hit_labels;
    for (auto it = class_files.begin(); it != class_files.end();) {
        auto hit = known_library_coords(it->first, relocated_prefixes);
        if (hit.has_value()) {
            library_internal_names.push_back(it->first);
            library_hit_labels.insert(hit->second.group + ":" + hit->second.artifact);
            it = class_files.erase(it);
        } else {
            ++it;
        }
    }
    if (!library_internal_names.empty()) {
        stats.library_classes_skipped = static_cast<int>(library_internal_names.size());
        stats.library_names_hit = library_hit_labels;
    }
    // --- 6. Копируем ресурсы (кроме .class и библиотечных путей). ---
    std::vector<std::string> skip_res_prefixes;
    for (auto& e : known_libs()) skip_res_prefixes.push_back(dotted_to_path_prefix(e.prefix));
    for (auto& [prefix, coords] : relocated_prefixes) {
        (void)coords;
        skip_res_prefixes.push_back(dotted_to_path_prefix(prefix));
    }
    std::string res_dir = (fs::path(out_dir) / "src" / "main" / "resources").string();
    static const std::regex maven_meta_re(R"(META-INF/maven/([^/]+)/([^/]+)/)");
    // БАГ-ФИКС: MANIFEST.MF и файлы подписи из META-INF копировались как
    // обычные ресурсы. MANIFEST.MF - это манифест ИСХОДНОГО jar (Main-Class,
    // Class-Path на relocated/shaded зависимости, которых после
    // декомпиляции уже нет) - maven-jar-plugin генерирует свой при сборке,
    // и старый просто мусорит в структуре, как и жаловался пользователь.
    // Сигнатурные файлы (*.SF/*.RSA/*.DSA/*.EC под META-INF) хуже: при
    // пересборке контент меняется, и с ними jar при подписанной проверке
    // упадёт в рантайме с SecurityException "invalid signature file digest".
    // META-INF/services/* (SPI) и прочие META-INF-ресурсы (не манифест/не
    // подпись) по-прежнему копируются - они могут быть нужны рантайму.
    // БАГ-ФИКС (продолжение MANIFEST.MF-фикса выше): META-INF/maven/*/pom.xml
    // и pom.properties - метаданные сборки ИСХОДНОГО jar. pom_builder.cpp
    // уже читает их напрямую из zip (maven_meta_re выше) и генерирует по ним
    // НАСТОЯЩИЙ pom.xml в корне проекта - сырые копии внутри
    // resources/META-INF/maven/ ничего не добавляют, а при mvn package
    // задублируются обратно внутрь пересобранного jar рядом со свежим
    // корневым pom.xml, создавая ту же путаницу, что и старый MANIFEST.MF.
    static const std::regex meta_signature_re(R"(^META-INF/[^/]+\.(SF|RSA|DSA|EC)$)", std::regex::icase);
    static const std::regex meta_maven_re(R"(^META-INF/maven/.*\.(xml|properties)$)");
    for (auto& n : all_names) {
        if ((n.size() >= 6 && n.substr(n.size() - 6) == ".class") || (!n.empty() && n.back() == '/')) continue;
        if (n == "META-INF/MANIFEST.MF") continue;
        if (std::regex_match(n, meta_signature_re)) continue;
        if (std::regex_match(n, meta_maven_re)) continue;
        bool skip = false;
        for (auto& p : skip_res_prefixes)
            if (starts_with(n, p)) {
                skip = true;
                break;
            }
        if (skip) continue;
        std::smatch m;
        if (std::regex_search(n, m, maven_meta_re)) {
            std::string ga = m[1].str() + ":" + m[2].str();
            if (library_hit_labels.count(ga)) continue;
        }
        std::vector<uint8_t> data;
        try {
            data = zr.read(n);
        } catch (...) {
            continue;
        }
        write_binary_file((fs::path(res_dir) / n).string(), data);
        if (n == "plugin.yml") {
            plugin_yml_text = std::string(data.begin(), data.end());
        }
    }

    std::map<std::string, std::string> known_internal_by_dotted;
    for (auto& [internal, cf] : class_files) known_internal_by_dotted[dotted_from_internal(internal)] = internal;

    // --- 7. external_dotted (ссылки на классы, которых нет среди своих) - для build_pom. ---
    std::vector<std::string> external_dotted;
    {
        std::set<std::string> seen;
        for (auto& [internal, cf] : class_files) {
            for (auto& [idx, entry] : cf.pool) {
                (void)idx;
                if (entry.tag == CpTag::Class) {
                    const std::string* cname = cf.utf8(entry.idx1);
                    if (cname != nullptr && !class_files.count(*cname) && !cname->empty() && (*cname)[0] != '[') {
                        std::string d = dotted_from_internal(*cname);
                        if (seen.insert(d).second) external_dotted.push_back(d);
                    }
                }
            }
        }
    }

    // --- 8. pom.xml (оригинал или сгенерированный). ---
    auto pom_result = build_pom(jar_path, plugin_yml_text.value_or(""), external_dotted, all_names, zr);
    write_text_file((fs::path(out_dir) / "pom.xml").string(), pom_result.pom_xml);

    // --- 9. Проверка легитимности (см. legitimacy_check.hpp) - ВСЕГДА все
    // источники, если явно не выключена. См. header legitimacy_check.hpp -
    // сетевая часть не тестировалась вживую (нет сети в песочнице). ---
    if (!skip_legitimacy) {
        // БАГ-ФИКС: раньше имя плагина для проверки легитимности бралось
        // ТОЛЬКО регэкспом по plugin_yml_text (Bukkit-only YAML) - для
        // Velocity (JSON) и Bungee (YAML, но другой набор полей) имя
        // никогда не находилось, даже когда jr.platform.name уже успешно
        // распарсен detect_platform() правильным парсером под каждый
        // формат манифеста. Используем его напрямую вместо повторного
        // (и для не-YAML форматов - изначально нерабочего) разбора здесь.
        jr.legitimacy = run_legitimacy_check(jr.platform.name.value_or(""), plugin_yml_text.value_or(""), jar_path);
    }

    // --- 10. Renamer (см. HANDOFF_41) + naming_hints (см. HANDOFF_46,
    // портировано с Rust-инструмента пользователя) - подсказки ДОЛЖНЫ
    // выполниться ДО цикла ниже (иначе подсказка опоздает для классов,
    // обработанных раньше в friendly_class). ---
    Renamer renamer;
    {
        auto hints1 = hints_by_annotation_name(class_files, looks_obfuscated_wrapper);
        auto hints2 = hints_by_brigadier_super_call(class_files, looks_obfuscated_wrapper);
        for (auto& [k, v] : hints1) renamer.class_name_hints[k] = v;
        for (auto& [k, v] : hints2) renamer.class_name_hints[k] = v;  // .update() - позже перезаписывает раньше при коллизии ключа
    }
    for (auto& [internal, cf] : class_files) {
        renamer.friendly_class(internal);
        for (auto& f : cf.fields) renamer.friendly_field(internal, f.name, f.descriptor);
        for (auto& m : cf.methods) renamer.friendly_method(internal, m.name, m.descriptor);
    }

    // --- 11. enum_ordinals. ---
    std::map<std::string, std::vector<std::string>> enum_ordinals;
    for (auto& [internal, cf] : class_files) {
        if (!(cf.access & 0x4000)) continue;
        std::string own_desc = "L" + internal + ";";
        std::vector<std::string> names;
        for (auto& f : cf.fields)
            if ((f.access & 0x4000) && f.descriptor == own_desc) names.push_back(renamer.friendly_field(internal, f.name, f.descriptor));
        if (!names.empty()) enum_ordinals[internal] = names;
    }

    // --- 12. switchmap.py портирован (HANDOFF_46) - synthetic switch-on-enum
    // классы находятся и сворачиваются в настоящий switch(enum). ---
    auto switchmap_detection = detect_switchmaps(class_files);
    std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>> switchmap_tables;
    for (auto& [key, info] : switchmap_detection.switchmap_fields) switchmap_tables[key] = info.table;
    std::set<std::string> synthetic_switchmap_classes = switchmap_detection.synthetic_classes;

    // --- 13. Рендеринг классов. ---
    OrderedImports all_imports;
    for (auto& [internal, cf] : class_files) {
        if (synthetic_switchmap_classes.count(internal)) continue;
        std::string text;
        OrderedImports cls_imports;
        try {
            auto pr = render_class(cf, renamer, known_internal_by_dotted, stats, enum_ordinals, switchmap_tables);
            text = pr.first;
            cls_imports = pr.second;
        } catch (const std::exception& e) {
            text = "// ОШИБКА рендеринга класса " + internal + ": " + e.what() + "\n";
        }
        for (auto& [d, s] : cls_imports.items()) all_imports.set(d, s);
        std::string new_internal = renamer.friendly_class(internal);
        std::string dest = (fs::path(src_dir) / (new_internal + ".java")).string();
        write_text_file(dest, text);
        auto issues = check_brackets(text, new_internal + ".java");
        stats.bracket_issues.insert(stats.bracket_issues.end(), issues.begin(), issues.end());
    }

    stats.import_conflicts = check_import_collisions(all_imports.items());
    stats.synthetic_switchmap_classes_hidden = static_cast<int>(synthetic_switchmap_classes.size());
    jr.decrypted_strings_count = str_decrypt_get_decrypted_count();

    // --- 14. Отчёты. ---
    write_mapping_report(out_dir, renamer);
    int total_methods = 0, total_fields = 0;
    for (auto& [internal, cf] : class_files) {
        (void)internal;
        total_methods += static_cast<int>(cf.methods.size());
        total_fields += static_cast<int>(cf.fields.size());
    }
    write_readme(out_dir, jar_path, static_cast<int>(class_files.size()), parse_errors, total_methods, total_fields, renamer, stats);

    return jr;
}

void write_mapping_report(const std::string& out_dir, const Renamer& renamer) {
    std::ostringstream f;
    f << "Отчёт деобфускации: что было переименовано\n";
    f << std::string(60, '=') << "\n\n";

    f << "--- Пакеты ---\n";
    int n = 0;
    for (auto& [old, nw] : renamer.package_map()) {
        if (old != nw) {
            f << "  " << (old.empty() ? "(default)" : old) << "  ->  " << (nw.empty() ? "(default)" : nw) << "\n";
            n++;
        }
    }
    if (n == 0) f << "  (ни одно имя пакета не было изменено)\n";

    f << "\n--- Классы ---\n";
    n = 0;
    for (auto& [old, nw] : renamer.class_map()) {
        if (old != nw) {
            f << "  " << dotted_from_internal(old) << "  ->  " << dotted_from_internal(nw) << "\n";
            n++;
        }
    }
    if (n == 0) f << "  (ни одно имя класса не было изменено)\n";

    f << "\n--- Методы ---\n";
    n = 0;
    for (auto& [key, new_name] : renamer.method_map()) {
        auto& [owner, name, desc] = key;
        if (new_name != name) {
            f << "  " << dotted_from_internal(owner) << "." << name << desc << "  ->  " << new_name << "\n";
            n++;
        }
    }
    if (n == 0) f << "  (ни одно имя метода не было изменено)\n";

    f << "\n--- Поля ---\n";
    n = 0;
    for (auto& [key, new_name] : renamer.field_map()) {
        auto& [owner, name, desc] = key;
        if (new_name != name) {
            f << "  " << dotted_from_internal(owner) << "." << name << ":" << desc << "  ->  " << new_name << "\n";
            n++;
        }
    }
    if (n == 0) f << "  (ни одно имя поля не было изменено)\n";

    write_text_file((fs::path(out_dir) / "MAPPING_RU.txt").string(), f.str());
}

void write_readme(const std::string& out_dir, const std::string& jar_path, int n_classes,
                   const std::vector<std::pair<std::string, std::string>>& parse_errors, int total_methods_in_kept_classes,
                   int total_fields_in_kept_classes, const Renamer& renamer, const ProjectStats& stats) {
    std::ostringstream f;
    std::string base = fs::path(jar_path).filename().string();
    f << "Результат разбора: " << base << "\n";
    f << "Классов успешно разобрано: " << n_classes << "\n";
    f << "Ошибок парсинга: " << parse_errors.size() << "\n";
    if (!parse_errors.empty()) {
        f << "\nКлассы, которые не удалось разобрать:\n";
        for (auto& [n, err] : parse_errors) f << "  " << n << ": " << err << "\n";
    }

    int renamed_classes = 0, renamed_methods = 0, renamed_fields = 0;
    for (auto& [old, nw] : renamer.class_map())
        if (old != nw) renamed_classes++;
    for (auto& [key, new_name] : renamer.method_map())
        if (new_name != std::get<1>(key)) renamed_methods++;
    for (auto& [key, new_name] : renamer.field_map())
        if (new_name != std::get<1>(key)) renamed_fields++;

    f << "\n" << std::string(60, '=') << "\n";
    f << "СТАТИСТИКА ДЕОБФУСКАЦИИ ИМЁН\n";
    f << "(эвристика, см. javatypes.hpp: looks_obfuscated - может как пропустить,\n";
    f << "так и переименовать нормальное имя по ошибке; проверяйте MAPPING_RU.txt)\n\n";
    char buf[256];
    std::snprintf(buf, sizeof(buf), "  Классов всего: %d, переименовано: %d (%.1f%%)\n", n_classes, renamed_classes,
                  renamed_classes / static_cast<double>(std::max(n_classes, 1)) * 100.0);
    f << buf;
    std::snprintf(buf, sizeof(buf), "  Методов всего: %d, переименовано: %d (%.1f%%)\n", total_methods_in_kept_classes, renamed_methods,
                  renamed_methods / static_cast<double>(std::max(total_methods_in_kept_classes, 1)) * 100.0);
    f << buf;
    std::snprintf(buf, sizeof(buf), "  Полей всего: %d, переименовано: %d (%.1f%%)\n", total_fields_in_kept_classes, renamed_fields,
                  renamed_fields / static_cast<double>(std::max(total_fields_in_kept_classes, 1)) * 100.0);
    f << buf;
    if (renamed_classes == 0 && renamed_methods == 0 && renamed_fields == 0) {
        f << "  ни одного имени не было изменено (эвристика деобфускации не нашла,\n";
        f << "  что переименовывать - см. пояснение выше про looks_obfuscated)\n";
    }

    f << "\n" << stats.summary_text() << "\n";

    f << "\n" << std::string(60, '=') << "\n";
    f << "ЧТО РЕАЛЬНО ДЕЛАЕТ ЭТОТ ИНСТРУМЕНТ:\n\n"
         "  - Парсит constant pool, поля, методы, атрибут Code, BootstrapMethods,\n"
         "    InnerClasses - вручную, по спецификации JVM (свой парсер).\n"
         "  - Для каждого метода: строит CFG, символически исполняет байткод как\n"
         "    стек-машину (арифметика, вызовы, new/anewarray, касты, инкременты,\n"
         "    конкатенация строк через StringBuilder/invokedynamic, лямбды через\n"
         "    LambdaMetafactory) и СТРУКТУРИРУЕТ управляющий поток через дерево\n"
         "    доминаторов/постдоминаторов в if/else, while/do-while/for, switch,\n"
         "    try/catch - настоящий Java-код, а не листинг байткода.\n"
         "  - Если конкретный метод не удаётся восстановить с полной уверенностью -\n"
         "    он НЕ гадает: откатывается на честный дизассемблированный листинг\n"
         "    именно для этого метода (см. статистику выше), остальные методы это\n"
         "    не затрагивает.\n"
         "  - Эвристически деобфусцирует имена классов/методов/полей/пакетов,\n"
         "    переименовывая согласованно по всему проекту.\n\n"
         "ЧЕСТНО О ГРАНИЦАХ:\n"
         "  - В окружении сборки нет javac, поэтому финальная компиляция не была\n"
         "    проверена настоящим компилятором - только баланс скобок и структурная\n"
         "    самосогласованность (см. статистику проверок выше). Перед боевым\n"
         "    использованием рекомендуется прогнать через javac/IDE и поправить то,\n"
         "    что покажет реальный компилятор (в первую очередь - конфликты коротких\n"
         "    имён импортов, если они указаны выше).\n"
         "  - synchronized-блоки не сворачиваются в `synchronized (x) { ... }` -\n"
         "    вместо этого метод честно откатывается на дизассемблированный листинг\n"
         "    байткода (см. `синхронизированный-блок не свёрнут` в причинах ниже, если есть) -\n"
         "    компилировать такой листинг всё равно нельзя, зато семантика не теряется\n"
         "    молча.\n"
         "  - try/finally, скомпилированный через дублирование кода finally-блока\n"
         "    (стандартно для javac 7+), восстанавливается как несколько отдельных\n"
         "    catch(Throwable)-блоков с повторяющимся кодом, а не как единый\n"
         "    красивый `finally {}` - семантика верна, но не свёрнута.\n";

    write_text_file((fs::path(out_dir) / "README_RU.txt").string(), f.str());
}

}  // namespace nd
