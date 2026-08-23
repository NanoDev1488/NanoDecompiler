// stats_json.hpp - порт api.py::stats_to_dict() (HANDOFF_46) - сериализация
// результата декомпиляции в JSON, общая для --json-output и --api-server
// (HTTP). В оригинале это был dict на самом объекте `stats` (Python
// динамически развешивал malware_findings/legitimacy/decrypted_strings_*
// прямо на ProjectStats) - у нас эти поля живут раздельно в JarProcessResult
// (см. process_jar.hpp - сознательное решение ещё в HANDOFF_42/44, чтобы не
// тянуть malware_scan.hpp/legitimacy_check.hpp в verify.hpp) - здесь просто
// собираются вместе при сериализации, JSON-схема на выходе НЕ меняется.
//
// ОГОВОРКА про поле "legitimacy": python-исходник legitimacy_check.py НЕ
// был доступен ни в одной из сессий порта (только main.py его импортирует
// и кладёт результат как есть) - точная форма python-словаря, который
// возвращал run_legitimacy_check(), НЕ была видна напрямую. Схема ниже
// зеркалит C++-структуру LegitimacyCheckResult (legitimacy_check.hpp),
// которая сама была перенесена и сверена с оригиналом в HANDOFF_32 - высокая
// уверенность, что это соответствует и python-словарю, но 100% байт-в-байт
// сверки JSON именно этого поля не было ни разу.
#pragma once

#include <string>

#include "process_jar.hpp"

namespace nd {

// {"status":"ok","out_dir":...,"elapsed_sec":N,"stats":{...}} или
// {"status":"error","error":"..."} - зеркалит decompile_silent()/
// run_json_output() из api.py 1:1 по форме (elapsed_sec считает сам
// вызывающий код в cli_main.cpp/api-сервере, не эта функция).
std::string jar_process_result_to_json(const JarProcessResult& jr, const std::string& out_dir, double elapsed_sec);

std::string json_error_response(const std::string& error_message);

}  // namespace nd
