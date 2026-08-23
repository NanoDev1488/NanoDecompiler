// lib_filter.hpp - порт фрагмента resources/engine/main.py (v2.0, HANDOFF_39,
// HANDOFF_40): _known_library_coords / _relocated_library_prefixes /
// _signature_relocated_prefixes. Это ПЕРВОЕ, что main.cpp обязан сделать
// правильно (прямая цитата из HANDOFF_39_MASTER) - библиотечные классы
// (h2, protobuf, guava, gson, mysql-connector и т.д. - см. KNOWN_LIBS в
// pom_builder.hpp) НЕ должны декомпилироваться вообще, только посчитаны
// в ProjectStats::library_classes_skipped/library_names_hit и подтянуты в
// pom.xml как <dependency> (это уже делает build_pom - не переделываем).
#pragma once

#include <optional>
#include <string>
#include <vector>

#include "pom_builder.hpp"
#include "zip_reader.hpp"

namespace nd {

// Доп. префиксы (dotted_prefix, LibCoords), обнаруженные в <relocations>
// pom.xml самого плагина (maven-shade-plugin) - проверяются ПЕРЕД основной
// таблицей known_libs(), т.к. точнее (взяты из официального конфига сборки
// именно этого jar'а). Порт _relocated_library_prefixes.
std::vector<std::pair<std::string, LibCoords>> relocated_library_prefixes(const std::string& original_pom_xml);

// Fallback-детект релокации по сигнатурным путям ресурсов (для случаев,
// когда pom.xml плагина не забандлен внутри jar'а вообще - см. main.py
// комментарий про MLSAC-1.0/sqlite-jdbc). Порт _signature_relocated_prefixes.
// all_names: все имена записей в jar (ZipReader::namelist()).
std::vector<std::pair<std::string, LibCoords>> signature_relocated_prefixes(const std::vector<std::string>& all_names);

// Если internal-имя класса (JVM internal, "a/b/C") подпадает под известную
// библиотеку - вернуть (dotted_prefix, coords), иначе nullopt. extra_prefixes -
// объединение relocated_library_prefixes + signature_relocated_prefixes,
// проверяется первым. Порт _known_library_coords.
std::optional<std::pair<std::string, LibCoords>> known_library_coords(
    const std::string& internal, const std::vector<std::pair<std::string, LibCoords>>& extra_prefixes = {});

}  // namespace nd
