// pom_builder.cpp - см. pom_builder.hpp. 1:1 порт pom_builder.py.
#include "pom_builder.hpp"

#include <algorithm>
#include <cctype>
#include <regex>
#include <set>
#include <sstream>
#include <tuple>

namespace nd {

namespace {

std::string lstrip(const std::string& s, const std::string& chars = " \t\r\n") {
    size_t a = s.find_first_not_of(chars);
    return (a == std::string::npos) ? "" : s.substr(a);
}

std::string strip(const std::string& s, const std::string& chars = " \t\r\n") {
    size_t a = s.find_first_not_of(chars);
    if (a == std::string::npos) return "";
    size_t b = s.find_last_not_of(chars);
    return s.substr(a, b - a + 1);
}

std::vector<std::string> split_lines(const std::string& text) {
    // Аналог str.splitlines() - разбивает по \n, \r\n, \r; не создаёт хвостовую
    // пустую строку, если текст заканчивается переводом строки (как Python).
    std::vector<std::string> out;
    size_t i = 0, n = text.size();
    size_t start = 0;
    while (i < n) {
        if (text[i] == '\n') {
            out.push_back(text.substr(start, i - start));
            i += 1;
            start = i;
        } else if (text[i] == '\r') {
            out.push_back(text.substr(start, i - start));
            i += 1;
            if (i < n && text[i] == '\n') i += 1;
            start = i;
        } else {
            i += 1;
        }
    }
    if (start < n) out.push_back(text.substr(start));
    return out;
}

std::vector<std::string> split_char(const std::string& s, char sep) {
    std::vector<std::string> out;
    size_t start = 0;
    for (size_t i = 0; i < s.size(); ++i) {
        if (s[i] == sep) {
            out.push_back(s.substr(start, i - start));
            start = i + 1;
        }
    }
    out.push_back(s.substr(start));
    return out;
}

// KNOWN_LIBS определена ниже, вне анонимного namespace (см. known_libs() в
// nd:: - объявлена в pom_builder.hpp, нужна main.cpp для library-skip-filter,
// см. HANDOFF_39).
using LibEntry = KnownLibEntry;

}  // namespace (закрывается здесь раньше обычного - known_libs/detect_lib публичные)

const std::vector<KnownLibEntry>& known_libs() {
    static const std::vector<KnownLibEntry> v = {
        {"org.bukkit", "org.spigotmc", "spigot-api", ""},
        {"org.spigotmc", "org.spigotmc", "spigot-api", ""},
        {"com.destroystokyo.paper", "io.papermc.paper", "paper-api", ""},
        {"io.papermc", "io.papermc.paper", "paper-api", ""},
        {"net.md_5.bungee", "net.md-5", "bungeecord-chat", ""},
        {"net.kyori.adventure", "net.kyori", "adventure-api", ""},
        {"com.google.common", "com.google.guava", "guava", ""},
        {"com.google.thirdparty", "com.google.guava", "guava", "вспомогательные классы внутри guava (напр. publicsuffix)"},
        {"io.reactivex.rxjava3", "io.reactivex.rxjava3", "rxjava", ""},
        {"com.google.gson", "com.google.code.gson", "gson", ""},
        {"com.google.inject", "com.google.inject", "guice", "shaded DI framework"},
        {"com.fasterxml.jackson", "com.fasterxml.jackson.core", "jackson-databind", ""},
        {"org.yaml.snakeyaml", "org.yaml", "snakeyaml", ""},
        {"org.apache.commons.lang3", "org.apache.commons", "commons-lang3", ""},
        {"org.apache.commons.io", "commons-io", "commons-io", ""},
        {"org.slf4j", "org.slf4j", "slf4j-api", ""},
        {"com.zaxxer.hikari", "com.zaxxer", "HikariCP", ""},
        {"org.sqlite", "org.xerial", "sqlite-jdbc", ""},
        {"com.mysql", "com.mysql", "mysql-connector-j", ""},
        {"org.h2", "com.h2database", "h2",
         "добавлено HANDOFF_40 - отсутствовало в таблице, хотя явно названо в HANDOFF_39 как "
         "требующее фильтрации (1049 классов в Salaires-3_4_1.jar пользователя не отфильтровывались)"},
        {"io.netty", "io.netty", "netty-all", ""},
        {"org.jetbrains.annotations", "org.jetbrains", "annotations", ""},
        {"org.intellij.lang.annotations", "org.jetbrains", "annotations",
         "тот же артефакт org.jetbrains:annotations, что и org.jetbrains.annotations выше - JAR содержит ОБА "
         "пакета (org.jetbrains.annotations + org.intellij.lang.annotations), добавлено HANDOFF_48, найдено на "
         "BukkitOfUtils-1_19_0.jar пользователя, где второй пакет не отфильтровывался"},
        {"org.json", "org.json", "json",
         "JSON-java (Дуглас Крокфорд) - добавлено HANDOFF_48, найдено на BukkitOfUtils-1_19_0.jar (29 классов, "
         "JSONArray/JSONException/JSONML и т.д. - точное совпадение со структурой библиотеки)"},
        {"org.opentest4j", "org.opentest4j", "opentest4j",
         "библиотека JUnit5 (AssertionFailedError/MultipleFailuresError и т.п.) - тестовая зависимость, в рантайм-jar "
         "плагина попадает по ошибке сборки автора, не является кодом плагина - добавлено HANDOFF_48"},
        {"mc.obliviate", "mc.obliviate", "inventory-api",
         "Obliviate Inventory - опенсорсная GUI/инвентарь-библиотека для Bukkit-плагинов (github.com/Obliviate-Inventory) "
         "- добавлено HANDOFF_48, найдено на BukkitOfUtils-1_19_0.jar (Gui/GuiIcon/InventoryAPI и т.п., пакеты "
         "mc.obliviate.inventory И mc.obliviate.util - префикс без .inventory ловит оба)"},
        {"javax.annotation", "javax.annotation", "jsr305", ""},
        {"com.mojang.brigadier", "com.mojang", "brigadier", ""},
        {"com.mojang.authlib", "com.mojang", "authlib", ""},
        {"org.enginehub.piston", "org.enginehub.piston", "piston-core", "часть EngineHub Piston (WorldEdit)"},
        {"com.sk89q.worldedit", "com.sk89q.worldedit", "worldedit-core", ""},
        {"com.sk89q.worldguard", "com.sk89q.worldguard", "worldguard-core", ""},
        {"org.bstats", "org.bstats", "bstats-bukkit", "статистика/телеметрия (bStats)"},
        {"redis.clients.jedis", "redis.clients", "jedis", ""},
        {"com.zaxxer", "com.zaxxer", "HikariCP", ""},
        {"org.bouncycastle", "org.bouncycastle", "bcprov-jdk18on", ""},
        {"okhttp3", "com.squareup.okhttp3", "okhttp", ""},
        {"okio", "com.squareup.okio", "okio", ""},
        {"retrofit2", "com.squareup.retrofit2", "retrofit", ""},
        {"org.apache.commons.codec", "commons-codec", "commons-codec", ""},
        {"org.apache.commons.collections4", "org.apache.commons", "commons-collections4", ""},
        {"com.mongodb", "org.mongodb", "mongodb-driver-sync", ""},
        {"org.bson", "org.mongodb", "bson", ""},
        {"ch.qos.logback", "ch.qos.logback", "logback-classic", ""},
        {"org.apache.http", "org.apache.httpcomponents", "httpclient", ""},
        {"kotlin", "org.jetbrains.kotlin", "kotlin-stdlib", ""},
        {"kotlinx", "org.jetbrains.kotlinx", "kotlinx-coroutines-core", ""},
        {"com.typesafe.config", "com.typesafe", "config", ""},
        {"it.unimi.dsi.fastutil", "it.unimi.dsi", "fastutil", ""},
        {"org.mariadb", "org.mariadb.jdbc", "mariadb-java-client", ""},
        {"com.google.protobuf", "com.google.protobuf", "protobuf-java", ""},
        {"com.google.errorprone.annotations", "com.google.errorprone", "error_prone_annotations", ""},
        {"javassist", "org.javassist", "javassist", ""},
        {"com.github.benmanes.caffeine", "com.github.ben-manes.caffeine", "caffeine", ""},
        {"org.apache.commons.math3", "org.apache.commons", "commons-math3", ""},
        {"org.reflections", "org.reflections", "reflections", ""},
        {"com.cryptomorin.xseries", "com.github.cryptomorin", "XSeries",
         "обычно шейдится напрямую из исходников (JitPack), не из Maven Central - "
         "проверить актуальный groupId/repository перед использованием pom.xml"},
    };
    return v;
}

std::optional<LibCoords> detect_lib(const std::string& dotted_name) {
    for (auto& e : known_libs()) {
        const std::string& prefix = e.prefix;
        if (dotted_name == prefix || (dotted_name.size() > prefix.size() && dotted_name.compare(0, prefix.size(), prefix) == 0 && dotted_name[prefix.size()] == '.')) {
            return LibCoords{e.group, e.artifact, e.comment};
        }
    }
    return std::nullopt;
}

namespace {

const std::set<std::string>& known_lib_artifact_ids() {
    static const std::set<std::string> s = [] {
        std::set<std::string> out;
        for (auto& e : known_libs()) out.insert(e.artifact);
        return out;
    }();
    return s;
}

const std::vector<std::string>& ignored_prefixes() {
    static const std::vector<std::string> v = {"java.", "javax.", "jdk.", "sun.", "com.sun."};
    return v;
}

}  // namespace

std::vector<std::pair<std::string, std::string>> parse_shade_relocations(const std::string& pom_xml_text) {
    std::vector<std::pair<std::string, std::string>> result;
    if (pom_xml_text.empty()) return result;
    static const std::regex block_re(R"(<relocation>([\s\S]*?)</relocation>)");
    static const std::regex pat_re(R"(<pattern>([^<]+)</pattern>)");
    static const std::regex shaded_re(R"(<shadedPattern>([^<]+)</shadedPattern>)");
    auto begin = std::sregex_iterator(pom_xml_text.begin(), pom_xml_text.end(), block_re);
    auto end = std::sregex_iterator();
    for (auto it = begin; it != end; ++it) {
        std::string block = (*it)[1].str();
        std::smatch mp, ms;
        if (std::regex_search(block, mp, pat_re) && std::regex_search(block, ms, shaded_re)) {
            result.emplace_back(strip(mp[1].str()), strip(ms[1].str()));
        }
    }
    return result;
}

PomPropertiesAndXml find_pom_properties_and_xml(const std::vector<std::string>& uploads_zip_names, const ZipReader& zip_reader) {
    static const std::regex props_re(R"(^(META-INF/maven/.+)/pom\.properties$)");
    static const std::regex xml_re(R"(^(META-INF/maven/.+)/pom\.xml$)");

    std::vector<std::string> dir_order;
    std::map<std::string, std::pair<std::optional<std::string>, std::optional<std::string>>> by_dir;  // dir -> (properties_name, xml_name)
    for (auto& n : uploads_zip_names) {
        std::smatch m;
        if (std::regex_match(n, m, props_re)) {
            std::string d = m[1].str();
            if (!by_dir.count(d)) dir_order.push_back(d);
            by_dir[d].first = n;
            continue;
        }
        if (std::regex_match(n, m, xml_re)) {
            std::string d = m[1].str();
            if (!by_dir.count(d)) dir_order.push_back(d);
            by_dir[d].second = n;
        }
    }

    struct Candidate {
        std::optional<std::map<std::string, std::string>> props;
        std::optional<std::string> xml_text;
    };
    std::vector<Candidate> candidates;
    for (auto& d : dir_order) {
        auto& entry = by_dir[d];
        Candidate c;
        if (entry.first.has_value()) {
            try {
                auto raw = zip_reader.read(*entry.first);
                std::string text(raw.begin(), raw.end());
                std::map<std::string, std::string> props;
                for (auto& line0 : split_lines(text)) {
                    std::string line = strip(line0);
                    if (line.empty() || line[0] == '#') continue;
                    auto eq = line.find('=');
                    if (eq == std::string::npos) continue;
                    props[strip(line.substr(0, eq))] = strip(line.substr(eq + 1));
                }
                c.props = props;
            } catch (...) {
                c.props = std::nullopt;
            }
        }
        if (entry.second.has_value()) {
            try {
                auto raw = zip_reader.read(*entry.second);
                c.xml_text = std::string(raw.begin(), raw.end());
            } catch (...) {
                c.xml_text = std::nullopt;
            }
        }
        candidates.push_back(std::move(c));
    }

    if (candidates.empty()) return {std::nullopt, std::nullopt};

    auto artifact_id = [](const Candidate& c) -> std::optional<std::string> {
        if (c.props.has_value()) {
            auto it = c.props->find("artifactId");
            if (it != c.props->end()) return it->second;
        }
        if (c.xml_text.has_value()) {
            static const std::regex re(R"(<artifactId>([^<]+)</artifactId>)");
            std::smatch m;
            if (std::regex_search(*c.xml_text, m, re)) return strip(m[1].str());
        }
        return std::nullopt;
    };

    std::vector<Candidate> own;
    for (auto& c : candidates) {
        auto aid = artifact_id(c);
        if (!aid.has_value() || !known_lib_artifact_ids().count(*aid)) own.push_back(c);
    }
    if (!own.empty()) {
        std::stable_sort(own.begin(), own.end(), [](const Candidate& a, const Candidate& b) {
            int ka = (a.props.has_value() && a.xml_text.has_value()) ? 0 : 1;
            int kb = (b.props.has_value() && b.xml_text.has_value()) ? 0 : 1;
            return ka < kb;
        });
        return {own[0].props, own[0].xml_text};
    }
    return {std::nullopt, std::nullopt};
}

PluginYmlInfo pom_parse_plugin_yml(const std::string& text) {
    PluginYmlInfo info;
    if (text.empty()) return info;
    auto lines = split_lines(text);
    static const std::regex kv_re(R"(^(name|version|api-version|main|website)\s*:\s*(.+)$)");
    static const std::regex authors_re(R"(^authors\s*:\s*\[(.*)\]$)");
    static const std::regex libraries_hdr_re(R"(^libraries\s*:\s*$)");
    static const std::regex lib_item_re(R"(^\s*-\s*)");
    static const std::regex dep_re(R"(^(depend|softdepend)\s*:\s*\[(.*)\]$)");

    size_t i = 0;
    while (i < lines.size()) {
        std::string stripped = strip(lines[i]);
        std::smatch m;
        if (std::regex_match(stripped, m, kv_re)) {
            std::string key = m[1].str();
            std::string val = strip(m[2].str());
            // val.strip('"\'') - убираем ведущие/хвостовые кавычки любого вида с обоих концов
            size_t a = val.find_first_not_of("\"'");
            size_t b = val.find_last_not_of("\"'");
            val = (a == std::string::npos) ? "" : val.substr(a, b - a + 1);
            if (key == "api-version") info.api_version = val;
            else if (key == "name") info.name = val;
            else if (key == "version") info.version = val;
            else if (key == "main") info.main = val;
            else if (key == "website") info.website = val;
            i += 1;
            continue;
        }
        if (std::regex_match(stripped, m, authors_re)) {
            std::string inner = m[1].str();
            for (auto& a0 : split_char(inner, ',')) {
                std::string a = strip(a0, " \t\"'");
                if (!strip(a0).empty()) info.authors.push_back(a);
            }
            i += 1;
            continue;
        }
        if (std::regex_match(stripped, libraries_hdr_re)) {
            i += 1;
            while (i < lines.size()) {
                std::smatch lm;
                if (!std::regex_search(lines[i], lm, lib_item_re, std::regex_constants::match_continuous)) break;
                std::string item = strip(std::regex_replace(lines[i], lib_item_re, "", std::regex_constants::format_first_only), " \t\"'");
                info.libraries.push_back(item);
                i += 1;
            }
            continue;
        }
        if (std::regex_match(stripped, dep_re)) {
            std::smatch m2;
            std::regex_match(stripped, m2, dep_re);
            std::string key = m2[1].str();
            std::string inner = m2[2].str();
            std::vector<std::string> vals;
            for (auto& a0 : split_char(inner, ',')) {
                std::string a = strip(a0, " \t\"'");
                if (!strip(a0).empty()) vals.push_back(a);
            }
            if (key == "depend") info.depend = vals;
            else info.softdepend = vals;
            i += 1;
            continue;
        }
        i += 1;
    }
    return info;
}

GroupArtifactVersion guess_group_artifact(const std::string& jar_basename, const PluginYmlInfo& plugin_info,
                                           const std::optional<std::map<std::string, std::string>>& pom_props) {
    if (pom_props.has_value() && pom_props->count("groupId") && pom_props->count("artifactId")) {
        std::string ver = pom_props->count("version") ? pom_props->at("version") : "1.0";
        return {pom_props->at("groupId"), pom_props->at("artifactId"), ver};
    }
    std::string name;
    if (plugin_info.name.has_value()) {
        name = *plugin_info.name;
    } else {
        static const std::regex trail_re(R"([-_][\d.]+$)");
        name = std::regex_replace(jar_basename, trail_re, "");
    }
    std::string version = plugin_info.version.value_or("1.0");
    std::string group_suffix;
    for (char c : name) {
        if (std::isalnum(static_cast<unsigned char>(c))) group_suffix += static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
    }
    std::string group = "com.example." + group_suffix;
    return {group, name, version};
}

PomBuildResult build_pom(const std::string& jar_path, const std::string& plugin_yml_text,
                          const std::vector<std::string>& external_dotted_names,
                          const std::vector<std::string>& uploads_zip_names, const ZipReader& zip_reader) {
    auto slash = jar_path.find_last_of("/\\");
    std::string base = (slash == std::string::npos) ? jar_path : jar_path.substr(slash + 1);
    auto dot = base.find_last_of('.');
    std::string jar_basename = (dot == std::string::npos) ? base : base.substr(0, dot);

    PluginYmlInfo plugin_info = pom_parse_plugin_yml(plugin_yml_text);
    PomPropertiesAndXml found = find_pom_properties_and_xml(uploads_zip_names, zip_reader);

    if (found.xml_text.has_value()) {
        std::string comment =
            "<!-- Это ОРИГИНАЛЬНЫЙ pom.xml, найденный внутри jar по пути "
            "META-INF/maven/*/pom.xml - не восстановление, а точная копия. -->\n";
        std::string text = *found.xml_text;
        // Python: text.lstrip("\ufeff \t\r\n") - lstrip именно СЛЕВА (не с обеих
        // сторон!). BOM (U+FEFF) в UTF-8-байтах - последовательность EF BB BF;
        // сначала убираем её явно (как целую последовательность, а не как
        // "любой из этих трёх байт"), потом - обычные пробельные символы слева.
        {
            std::string tmp = text;
            if (tmp.size() >= 3 && static_cast<unsigned char>(tmp[0]) == 0xEF &&
                static_cast<unsigned char>(tmp[1]) == 0xBB && static_cast<unsigned char>(tmp[2]) == 0xBF) {
                tmp = tmp.substr(3);
            }
            text = tmp;
        }
        std::string stripped = lstrip(text, " \t\r\n");
        if (stripped.rfind("<?xml", 0) == 0) {
            auto end = stripped.find("?>");
            if (end != std::string::npos) {
                std::string decl = stripped.substr(0, end + 2);
                std::string rest = stripped.substr(end + 2);
                size_t r0 = rest.find_first_not_of("\r\n");
                rest = (r0 == std::string::npos) ? "" : rest.substr(r0);
                text = decl + "\n" + comment + rest;
            } else {
                text = comment + text;
            }
        } else {
            text = comment + text;
        }
        return {text, "original"};
    }

    auto gav = guess_group_artifact(jar_basename, plugin_info, found.props);

    std::vector<std::tuple<std::string, std::string, std::string, std::string>> explicit_deps;
    for (auto& lib : plugin_info.libraries) {
        auto parts = split_char(lib, ':');
        if (parts.size() == 3) {
            explicit_deps.emplace_back(parts[0], parts[1], parts[2], "явно указано в plugin.yml libraries:");
        }
    }

    std::vector<std::string> guessed_order;
    std::map<std::string, std::pair<std::string, std::string>> guessed;  // "g|a" -> (g,a) для порядка + comment ниже
    std::map<std::string, std::string> guessed_comment;
    for (auto& dotted : external_dotted_names) {
        bool ignored = false;
        for (auto& pre : ignored_prefixes()) {
            if (dotted.compare(0, pre.size(), pre) == 0) { ignored = true; break; }
        }
        if (ignored) continue;
        auto coords = detect_lib(dotted);
        if (coords.has_value()) {
            std::string key = coords->group + "|" + coords->artifact;
            if (!guessed.count(key)) guessed_order.push_back(key);
            guessed[key] = {coords->group, coords->artifact};
            guessed_comment[key] = coords->comment;
        }
    }

    std::set<std::string> explicit_keys;
    for (auto& [g, a, v, c] : explicit_deps) explicit_keys.insert(g + "|" + a);
    explicit_keys.insert("org.spigotmc|spigot-api");
    explicit_keys.insert("io.papermc.paper|paper-api");
    explicit_keys.insert("com.destroystokyo.paper|paper-api");

    std::vector<std::string> dep_xml_parts;
    for (auto& [g, a, v, comment] : explicit_deps) {
        std::ostringstream part;
        part << "        <!-- " << comment << " -->\n"
             << "        <dependency>\n"
             << "            <groupId>" << g << "</groupId>\n"
             << "            <artifactId>" << a << "</artifactId>\n"
             << "            <version>" << v << "</version>\n"
             << "        </dependency>";
        dep_xml_parts.push_back(part.str());
    }
    for (auto& key : guessed_order) {
        if (explicit_keys.count(key)) continue;
        auto& [g, a] = guessed[key];
        std::string comment = guessed_comment[key];
        std::string note = comment.empty() ? "" : (" (" + comment + ")");
        std::ostringstream part;
        part << "        <!-- ПРЕДПОЛОЖЕНИЕ по обнаруженным импортам" << note << " - версию нужно подобрать вручную -->\n"
             << "        <dependency>\n"
             << "            <groupId>" << g << "</groupId>\n"
             << "            <artifactId>" << a << "</artifactId>\n"
             << "            <version>REPLACE_ME</version>\n"
             << "        </dependency>";
        dep_xml_parts.push_back(part.str());
    }

    std::string deps_block;
    if (dep_xml_parts.empty()) {
        deps_block = "        <!-- зависимостей не обнаружено -->";
    } else {
        for (size_t i = 0; i < dep_xml_parts.size(); ++i) {
            if (i) deps_block += "\n";
            deps_block += dep_xml_parts[i];
        }
    }

    std::string build_block;
    if (!guessed.empty()) {
        build_block =
            "\n"
            "    <build>\n"
            "        <plugins>\n"
            "            <plugin>\n"
            "                <groupId>org.apache.maven.plugins</groupId>\n"
            "                <artifactId>maven-shade-plugin</artifactId>\n"
            "                <version>3.5.1</version>\n"
            "                <executions>\n"
            "                    <execution>\n"
            "                        <phase>package</phase>\n"
            "                        <goals><goal>shade</goal></goals>\n"
            "                    </execution>\n"
            "                </executions>\n"
            "            </plugin>\n"
            "        </plugins>\n"
            "    </build>\n";
    }

    std::string api_version = plugin_info.api_version.value_or("1.13");
    std::string source_comment = found.props.has_value() ? "pom.properties внутри jar" : "plugin.yml + эвристика по импортам";

    std::ostringstream pom;
    pom << "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        << "<!--\n"
        << "  ЭТО СГЕНЕРИРОВАННЫЙ (восстановленный по метаданным) pom.xml, а НЕ оригинал автора.\n"
        << "  Источники: " << source_comment << ".\n"
        << "  Версии зависимостей, отмеченных REPLACE_ME, нужно подобрать вручную (в байткоде\n"
        << "  версия библиотеки не хранится - только имена пакетов/классов).\n"
        << "-->\n"
        << "<project xmlns=\"http://maven.apache.org/POM/4.0.0\">\n"
        << "    <modelVersion>4.0.0</modelVersion>\n\n"
        << "    <groupId>" << gav.group << "</groupId>\n"
        << "    <artifactId>" << gav.artifact << "</artifactId>\n"
        << "    <version>" << gav.version << "</version>\n"
        << "    <packaging>jar</packaging>\n\n"
        << "    <properties>\n"
        << "        <maven.compiler.source>17</maven.compiler.source>\n"
        << "        <maven.compiler.target>17</maven.compiler.target>\n"
        << "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n"
        << "    </properties>\n\n"
        << "    <repositories>\n"
        << "        <repository>\n"
        << "            <id>spigotmc-repo</id>\n"
        << "            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>\n"
        << "        </repository>\n"
        << "        <repository>\n"
        << "            <id>papermc</id>\n"
        << "            <url>https://repo.papermc.io/repository/maven-public/</url>\n"
        << "        </repository>\n"
        << "    </repositories>\n\n"
        << "    <dependencies>\n"
        << "        <!-- api-version из plugin.yml: " << api_version << " -->\n"
        << "        <dependency>\n"
        << "            <groupId>org.spigotmc</groupId>\n"
        << "            <artifactId>spigot-api</artifactId>\n"
        << "            <version>" << api_version << "-R0.1-SNAPSHOT</version>\n"
        << "            <scope>provided</scope>\n"
        << "        </dependency>\n"
        << deps_block << "\n"
        << "    </dependencies>\n"
        << build_block << "</project>\n";

    return {pom.str(), "generated"};
}

}  // namespace nd
