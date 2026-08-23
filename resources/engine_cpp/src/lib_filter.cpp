// lib_filter.cpp - см. lib_filter.hpp. 1:1 порт фрагмента main.py.
#include "lib_filter.hpp"

#include <regex>
#include <set>

#include "javatypes.hpp"

namespace nd {

std::vector<std::pair<std::string, LibCoords>> relocated_library_prefixes(const std::string& original_pom_xml) {
    std::vector<std::pair<std::string, LibCoords>> out;
    for (auto& [pattern, shaded] : parse_shade_relocations(original_pom_xml)) {
        for (auto& e : known_libs()) {
            bool matches = (pattern == e.prefix) ||
                           (pattern.size() > e.prefix.size() && pattern.compare(0, e.prefix.size(), e.prefix) == 0 &&
                            pattern[e.prefix.size()] == '.') ||
                           (e.prefix.size() > pattern.size() && e.prefix.compare(0, pattern.size(), pattern) == 0 &&
                            e.prefix[pattern.size()] == '.');
            if (matches) {
                out.emplace_back(shaded, LibCoords{e.group, e.artifact, e.comment});
                break;
            }
        }
    }
    return out;
}

namespace {
// sqlite-jdbc: org/sqlite/native/{OS}/{arch}/(lib)?sqlitejdbc.{so,dll,dylib}
// Порт _SIGNATURE_PATTERNS - единственная запись в оригинале. Ниже - ДВЕ
// ДОПОЛНИТЕЛЬНЫЕ сигнатуры, добавленные в этой сессии (HANDOFF_46, НЕ
// присутствуют в python-оригинале - реальные пропуски, найденные на
// пользовательских jar): Gson и bStats часто релоцируются БЕЗ бандла
// плагинского pom.xml с <relocations> внутрь jar (maven-shade по
// умолчанию бандлит pom.xml КАЖДОЙ shaded-зависимости отдельно -
// META-INF/maven/<их-groupId>/<их-artifactId>/pom.xml, но НЕ pom.xml
// самого плагина с секцией relocations) - см. MSG.jar пользователя,
// где `com.google.gson` релоцирован в `Shadow.libs.gson` (226 классов,
// НИ ОДНОГО признака в KNOWN_LIBS-совместимом виде) и `org.bstats` в
// `Shadow.libs.bstats` - оба ПРОПУСКАЛИСЬ фильтром до этого фикса.
// В отличие от sqlite-jdbc (один уникальный файл), эти сигнатуры требуют
// ДВУХ независимых маркеров одновременно (иначе риск ложного
// срабатывания на плагинский код, который сам называется "Gson.java"
// не будучи библиотекой) - см. detect_gson_signature/detect_bstats_signature.
const std::regex& sqlite_signature_pattern() {
    static const std::regex re(R"(^(.*)/native/[^/]+/[^/]+/(?:lib)?sqlitejdbc\.(?:so|dll|dylib)$)");
    return re;
}

// Возвращает dotted-префикс (напр. "Shadow.libs.gson"), если ВСЕ
// перечисленные в extra_markers относительные пути (относительно
// кандидата-префикса candidate_class_path, минус ".class") присутствуют
// среди all_names - иначе nullopt. candidate_regex вычленяет сам префикс
// из имени класса, который РЕАЛЬНО встретился в jar.
std::optional<std::string> detect_prefixed_signature(const std::vector<std::string>& all_names, const std::regex& candidate_regex,
                                                       const std::vector<std::string>& corroborating_suffixes) {
    std::set<std::string> name_set(all_names.begin(), all_names.end());
    for (auto& n : all_names) {
        std::smatch m;
        if (!std::regex_match(n, m, candidate_regex)) continue;
        std::string prefix_path = m[1].str();
        bool all_present = true;
        for (auto& suf : corroborating_suffixes) {
            if (!name_set.count(prefix_path + suf)) {
                all_present = false;
                break;
            }
        }
        if (all_present) {
            std::string dotted_prefix = prefix_path;
            for (auto& c : dotted_prefix)
                if (c == '/') c = '.';
            return dotted_prefix;
        }
    }
    return std::nullopt;
}

const std::regex& gson_candidate_pattern() { static const std::regex re(R"(^(.*)/Gson\.class$)"); return re; }
const std::regex& bstats_candidate_pattern() { static const std::regex re(R"(^(.*)/bukkit/Metrics\.class$)"); return re; }
// Старый однофайловый bStats (до разделения на bukkit/+charts/ пакеты) -
// один класс Metrics с ЧАРТАМИ КАК ВЛОЖЕННЫМИ классами (Metrics$AdvancedPie
// и т.п.) - см. EryBuyer-v1.jar пользователя (HANDOFF_46), где именно этот
// вариант не ловился ни первой bStats-сигнатурой (нет bukkit/charts
// подпапок), ни pom.xml-релокацией (pom.xml вообще не забандлен).
const std::regex& bstats_legacy_candidate_pattern() { static const std::regex re(R"(^(.*)/Metrics\.class$)"); return re; }
}  // namespace

std::vector<std::pair<std::string, LibCoords>> signature_relocated_prefixes(const std::vector<std::string>& all_names) {
    std::vector<std::pair<std::string, LibCoords>> out;
    std::set<std::pair<std::string, std::string>> seen_coords;  // (group, artifact)
    const LibCoords sqlite_coords{"org.xerial", "sqlite-jdbc", ""};
    for (auto& name : all_names) {
        if (seen_coords.count({sqlite_coords.group, sqlite_coords.artifact})) continue;
        std::smatch m;
        if (std::regex_match(name, m, sqlite_signature_pattern())) {
            std::string dotted_prefix = m[1].str();
            for (auto& c : dotted_prefix)
                if (c == '/') c = '.';
            out.emplace_back(dotted_prefix, sqlite_coords);
            seen_coords.insert({sqlite_coords.group, sqlite_coords.artifact});
        }
    }

    // Gson - подтверждаем наличие JsonDeserializer.class и JsonSerializer.class
    // рядом с Gson.class (сама по себе Gson.class слишком общее имя файла).
    const LibCoords gson_coords{"com.google.code.gson", "gson", "релоцирован без бандла relocations в pom.xml, см. HANDOFF_46"};
    if (!seen_coords.count({gson_coords.group, gson_coords.artifact})) {
        auto prefix = detect_prefixed_signature(all_names, gson_candidate_pattern(), {"/JsonDeserializer.class", "/JsonSerializer.class"});
        if (prefix.has_value()) {
            out.emplace_back(*prefix, gson_coords);
            seen_coords.insert({gson_coords.group, gson_coords.artifact});
        }
    }

    // bStats - подтверждаем структуру charts/AdvancedPie.class рядом с
    // bukkit/Metrics.class (сама структура bStats всегда содержит charts/).
    const LibCoords bstats_coords{"org.bstats", "bstats-bukkit", "релоцирован без бандла relocations в pom.xml, см. HANDOFF_46"};
    if (!seen_coords.count({bstats_coords.group, bstats_coords.artifact})) {
        auto prefix = detect_prefixed_signature(all_names, bstats_candidate_pattern(), {"/charts/AdvancedPie.class"});
        if (prefix.has_value()) {
            out.emplace_back(*prefix, bstats_coords);
            seen_coords.insert({bstats_coords.group, bstats_coords.artifact});
        }
    }
    // Старый однофайловый bStats (см. bstats_legacy_candidate_pattern) -
    // отдельная проверка, т.к. у него другой корневой префикс (сам файл
    // Metrics.class лежит прямо в <prefix>/, без bukkit/ подпапки).
    if (!seen_coords.count({bstats_coords.group, bstats_coords.artifact})) {
        auto prefix = detect_prefixed_signature(all_names, bstats_legacy_candidate_pattern(), {"/Metrics$AdvancedPie.class"});
        if (prefix.has_value()) {
            out.emplace_back(*prefix, bstats_coords);
            seen_coords.insert({bstats_coords.group, bstats_coords.artifact});
        }
    }

    return out;
}

std::optional<std::pair<std::string, LibCoords>> known_library_coords(
    const std::string& internal, const std::vector<std::pair<std::string, LibCoords>>& extra_prefixes) {
    std::string dotted = dotted_from_internal(internal);
    for (auto& [prefix, coords] : extra_prefixes) {
        if (dotted == prefix || (dotted.size() > prefix.size() && dotted.compare(0, prefix.size(), prefix) == 0 &&
                                  dotted[prefix.size()] == '.')) {
            return std::make_pair(prefix, coords);
        }
    }
    for (auto& e : known_libs()) {
        if (dotted == e.prefix || (dotted.size() > e.prefix.size() && dotted.compare(0, e.prefix.size(), e.prefix) == 0 &&
                                    dotted[e.prefix.size()] == '.')) {
            return std::make_pair(e.prefix, LibCoords{e.group, e.artifact, e.comment});
        }
    }
    return std::nullopt;
}

}  // namespace nd
