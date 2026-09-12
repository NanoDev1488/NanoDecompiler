// jar_summary.hpp - порт gui_common.py::jar_summary() (HANDOFF_45). Нужен
// ИСКЛЮЧИТЕЛЬНО для совместимости с Electron GUI: main.ts дёргает
// `NanoDecompilerCLI --jar-summary plugin.jar` как ЗАПАСНОЙ путь, когда
// быстрый нативный разбор ZIP central directory в Node (electron/
// jarSummary.ts) отказывается работать (ZIP64 и подобные edge-case) - см.
// комментарий в electron/main.ts::ipcMain.handle("jar:summary", ...).
// Схема JSON СОЗНАТЕЛЬНО зеркалит electron/jarSummary.ts::JarSummary 1:1
// (те же имена и типы полей) - GUI парсит вывод как обычный JSON.parse,
// без какой-либо адаптации между "путём через Node" и "путём через движок".
#pragma once

#include <optional>
#include <string>

namespace nd {

struct JarSummary {
    std::string name;
    std::string size = "?";
    int classes = 0;
    int packages = 0;
    std::string java = "?";
    std::optional<std::string> plugin_name;
};

// НИКОГДА не бросает исключений наружу - на любой ошибке чтения возвращает
// частично заполненную структуру с дефолтами (см. jar_summary.cpp, тройное
// try/catch оригинала: размер файла / открытие zip / чтение plugin.yml -
// каждый независимо, ошибка в одном не должна занулять остальные поля).
JarSummary jar_summary(const std::string& jar_path);

// {"name":"...","size":"...","classes":N,"packages":N,"java":"...","plugin_name":null|"..."}
std::string jar_summary_to_json(const JarSummary& s);

}  // namespace nd
