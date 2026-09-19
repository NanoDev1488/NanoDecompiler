// process_jar.hpp - порт process_jar()/process_jar_with_stats()/
// write_mapping_report()/write_readme() из resources/engine/main.py (v2.0,
// HANDOFF_43). Главная точка входа движка: один .jar -> распакованный
// Maven-проект с декомпилированными .java, pom.xml, отчётами.
//
// ЧЕСТНО ЗАДЕКЛАРИРОВАННЫЕ ОТЛИЧИЯ ОТ ОРИГИНАЛА (см. HANDOFF_43):
//  - naming_hints.py портирован (HANDOFF_46) - осмысленные подсказки имён
//    (аннотации с "name", Brigadier super("команда")) применяются к
//    обфусцированным классам вместо generic ClassA{N}, где их можно
//    надёжно прочитать из уже существующих в байткоде данных.
//  - switchmap.py портирован (HANDOFF_46) - synthetic switch-on-enum классы
//    сворачиваются в switch(enum), а не оставляются как обычный код.
//  - Консольный вывод (cprint/progress/banner_text/section) НЕ перенесён -
//    это GUI/CLI-слой поверх движка, не часть самого движка. Вызывающий
//    код (будущий CLI main() или GUI) должен сам печатать прогресс, если
//    нужно - process_jar_with_stats() работает молча (тихо возвращает
//    результат), в отличие от оригинала, который печатает прямо внутри.
//  - check_java_maven()/install-tools - НЕ перенесены (см. HANDOFF_40,
//    архитектурный вопрос не решён).
#pragma once

#include <optional>
#include <string>
#include <vector>

#include "legitimacy_check.hpp"
#include "malware_scan.hpp"
#include "platform_detect.hpp"
#include "renamer.hpp"
#include "verify.hpp"

namespace nd {

struct JarProcessResult {
    std::string out_dir;
    ProjectStats stats;
    std::vector<MalwareFinding> malware_findings;
    std::optional<std::string> decrypted_strings_owner;
    int decrypted_strings_count = 0;
    // nullopt эквивалентно skip_legitimacy=true (проверка не выполнялась).
    std::optional<LegitimacyCheckResult> legitimacy;
    // Платформа jar'а (см. platform_detect.hpp) - Bukkit/Paper/Velocity/
    // Bungee/мод/неизвестно.
    PlatformInfo platform;
    // НОВОЕ v1.8.0 (HANDOFF_URGENT п.6, реальный пример
    // пользователя - NanoCore-bundle.jar бандлит velocity-patched.jar/
    // BungeeCord-patched.jar/2× paper-*-nanocore.jar под bundled/...):
    // пути вложенных .jar внутри этого jar'а (см. process_jar.cpp - ищем
    // ЛЮБую запись *.jar, КРОМЕ уже известного шума под
    // META-INF/libraries/ - тот уже отдельно объяснён как чужой
    // maven-кэш загрузчика, а не "настоящий" вложенный артефакт).
    // Рекурсивная декомпиляция НЕ реализована - это отдельная большая
    // фича (детект + UI confirm-диалог + рекурсивный process_jar) - пока
    // только честно СООБЩАЕМ о находке, чтобы пользователь не думал, что
    // движок просто не заметил вложенные jar'ы.
    std::vector<std::string> embedded_jars;
    // rejected=true - декомпиляция ПРЕРВАНА до основной обработки, до
    // определения платформы включительно - остальные поля этой структуры
    // в этом случае пустые/неактуальные, смотреть только rejected_reason.
    // Три возможные причины (см. process_jar_with_stats, разделы 0/1.2/1.5):
    // файл меньше правдоподобного минимума для плагина, ZIP без единого
    // .class файла (подмена содержимого), либо распознанный МОД (не
    // серверный плагин) - разные ситуации, одно общее поле отказа.
    bool rejected = false;
    std::optional<std::string> rejected_reason;
};

// Бросает std::runtime_error, если jar_path не открывается (битый zip и т.п.) -
// зеркалит необработанное исключение zipfile.ZipFile(...) в оригинале
// (main.py тоже ничего не ловит на этом уровне - падает выше, в main()/GUI).
// БАГ-ФИКС v1.7.3 (реальный краш CI - --json-output печатал текстовый
// прогресс-бар ПЕРЕД JSON-блобом, ломая ConvertFrom-Json/json.loads у
// потребителя): добавлен параметр print_progress (по умолчанию true -
// поведение обычного CLI-режима не меняется ни на символ). run_json_output()
// -> decompile_silent_json() теперь явно передаёт false - контракт "эта
// функция работает МОЛЧА" (см. комментарий в api.cpp/HANDOFF_44) был
// задокументирован ДО того, как в этой же сессии добавили прогресс-бар -
// печать в этой ветке была честной регрессией, не входила в замысел.
JarProcessResult process_jar_with_stats(const std::string& jar_path, const std::string& out_dir, bool skip_legitimacy = false,
                                         bool print_progress = true);

// MAPPING_RU.txt - что было переименовано (пакеты/классы/методы/поля).
void write_mapping_report(const std::string& out_dir, const Renamer& renamer);

// README_RU.txt - сводка по проекту + статистика + пояснение "что реально
// делает инструмент" / "честно о границах". n_classes/parse_errors -
// ДО удаления библиотечных классов (как в оригинале - n_classes считает
// stats.classes_parsed уже ПОСЛЕ удаления library_classes_skipped, см.
// process_jar.cpp по месту вызова).
void write_readme(const std::string& out_dir, const std::string& jar_path, int n_classes,
                   int total_methods_in_kept_classes,
                   int total_fields_in_kept_classes, const Renamer& renamer, const ProjectStats& stats);

}  // namespace nd
