// pom_builder.hpp - порт resources/engine/pom_builder.py (v2.0, HANDOFF_33).
// Восстановление/генерация pom.xml для jar-плагина.
#pragma once

#include <map>
#include <optional>
#include <string>
#include <vector>

#include "zip_reader.hpp"

namespace nd {

struct PluginYmlInfo {
    std::optional<std::string> name;
    std::optional<std::string> version;
    std::optional<std::string> api_version;
    std::optional<std::string> main;
    std::vector<std::string> libraries;
    std::vector<std::string> depend;
    std::vector<std::string> softdepend;
    std::optional<std::string> website;
    std::vector<std::string> authors;
};

PluginYmlInfo pom_parse_plugin_yml(const std::string& text);

// Известная сторонняя библиотека по dotted-префиксу пакета: (groupId,
// artifactId, comment_or_empty), или nullopt, если не совпало ни с чем.
// Вынесено из анонимного namespace pom_builder.cpp (было приватным для
// самого себя) - см. HANDOFF_39: main.cpp должен переиспользовать ЭТУ
// функцию для library-skip-filter'а, а не дублировать таблицу.
struct LibCoords {
    std::string group;
    std::string artifact;
    std::string comment;
};
std::optional<LibCoords> detect_lib(const std::string& dotted_name);

// Таблица известных библиотек (dotted prefix, groupId, artifactId, comment) -
// нужна main.cpp для _relocated_library_prefixes-эквивалента (сверка
// pattern из <relocations> с известным префиксом).
struct KnownLibEntry {
    std::string prefix;
    std::string group;
    std::string artifact;
    std::string comment;
};
const std::vector<KnownLibEntry>& known_libs();

// (pattern, shadedPattern) пары из <relocations> maven-shade-plugin.
std::vector<std::pair<std::string, std::string>> parse_shade_relocations(const std::string& pom_xml_text);

// props: пары ключ=значение из pom.properties (пусто, если не нашли/не распарсили).
// xml_text: содержимое pom.xml (nullopt, если не нашли).
struct PomPropertiesAndXml {
    std::optional<std::map<std::string, std::string>> props;
    std::optional<std::string> xml_text;
};
PomPropertiesAndXml find_pom_properties_and_xml(const std::vector<std::string>& uploads_zip_names, const ZipReader& zip_reader);

struct GroupArtifactVersion {
    std::string group;
    std::string artifact;
    std::string version;
};
GroupArtifactVersion guess_group_artifact(const std::string& jar_basename, const PluginYmlInfo& plugin_info,
                                           const std::optional<std::map<std::string, std::string>>& pom_props);

struct PomBuildResult {
    std::string pom_xml;
    std::string source;  // "original" | "generated"
};
PomBuildResult build_pom(const std::string& jar_path, const std::string& plugin_yml_text,
                          const std::vector<std::string>& external_dotted_names,
                          const std::vector<std::string>& uploads_zip_names, const ZipReader& zip_reader);

}  // namespace nd
