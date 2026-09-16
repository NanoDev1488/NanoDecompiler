// cli_main.cpp - CLI-обвязка поверх process_jar.hpp/api.hpp/toolinstaller.hpp
// (HANDOFF_44/45/46). Полная замена main.py - других способов запустить
// движок больше нет (main.py и весь Python-код удалены из проекта, см.
// HANDOFF_46).
//
// Режимы:
//   NanoDecompilerCLI plugin.jar [out_dir] [--no-legitimacy-check]
//   NanoDecompilerCLI plugin.jar [out_dir] --json-output
//   NanoDecompilerCLI --api-server [--host H] [--port P]
//   NanoDecompilerCLI --jar-summary plugin.jar
//   NanoDecompilerCLI --install-tools[=jdk|maven]
//   NanoDecompilerCLI --install-tools-json[=jdk|maven]
//
// ЧТО ОСТАЁТСЯ СОЗНАТЕЛЬНО УПРОЩЕНО (см. подробности в заголовках
// соответствующих модулей):
//  - Живой построчный прогресс-бар декомпиляции (progress()/section()/
//    classify_line() с ANSI) - process_jar_with_stats() молча работает
//    (см. process_jar.hpp), CLI печатает только финальные вехи.
//  - Прогресс скачивания JDK/Maven - 2 события на файл (0%/100%), не
//    потоковый процент - см. toolinstaller.hpp (HTTPS через системный curl).
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <cstdlib>
#include <filesystem>
#include <iostream>

#include "api.hpp"
#include "jar_summary.hpp"
#include "legitimacy_check.hpp"
#include "malware_scan.hpp"
#include "process_jar.hpp"
#include "stats_json.hpp"
#include "toolinstaller.hpp"
#include "version.hpp"

namespace fs = std::filesystem;

namespace {

using nd::NANO_DECOMPILER_VERSION;

std::string json_escape(const std::string& s) {
    std::string out;
    for (unsigned char c : s) {
        if (c == '"' || c == '\\') {
            out += '\\';
            out += static_cast<char>(c);
        } else if (c == '\n') {
            out += "\\n";
        } else if (c < 0x20) {
            char buf[8];
            std::snprintf(buf, sizeof(buf), "\\u%04x", c);
            out += buf;
        } else {
            out += static_cast<char>(c);
        }
    }
    return out;
}

// Порт banner_text() - БЕЗ ANSI-раскраски (см. HANDOFF_44 - classify_line()/
// _supports_color()/isatty()-детект не переносились, печатаем всегда простым
// текстом; смысл рамки и текста сохранён 1:1).
// НОВОЕ v1.7.3: подсчёт "ширины" строки в БАЙТАХ (std::string::size()) ломает
// рамку баннера на кириллице - каждый кириллический символ занимает 2 байта
// в UTF-8, но 1 колонку на экране. Из-за этого `width` вычислялась сильно
// больше настоящей видимой ширины (по самой длинной строке в БАЙТАХ, а не в
// символах), и рамка получалась либо непропорционально широкой, либо
// текст прижимался к правой границе неравномерно между строками с разным
// соотношением кириллицы/латиницы. Смотри правильно: считаем ТОЛЬКО стартовые
// байты UTF-8-последовательностей (не продолжения - 10xxxxxx), что даёт
// число КОДОВЫХ ТОЧЕК = видимых колонок для нашего алфавита (ASCII +
// кириллица, оба однодиапазонные - без учёта экзотики вроде emoji/CJK
// широких символов, которые тут не встречаются).
size_t utf8_display_width(const std::string& s) {
    size_t n = 0;
    for (unsigned char c : s)
        if ((c & 0xC0) != 0x80) ++n;
    return n;
}

std::string banner_text() {
    std::string line1 = std::string("\u273B ") + NANO_DECOMPILER_VERSION;
    std::string line2 = "   Java-декомпилятор/деобфускатор для Bukkit-плагинов";
    size_t w1 = utf8_display_width(line1);
    size_t w2 = utf8_display_width(line2);
    size_t width = w2 > w1 ? w2 : w1;
    std::string top = "\u256D";
    for (size_t i = 0; i < width + 2; ++i) top += "\u2500";
    top += "\u256E";
    std::string bot = "\u2570";
    for (size_t i = 0; i < width + 2; ++i) bot += "\u2500";
    bot += "\u256F";
    auto pad = [&](const std::string& s, size_t visible_width) {
        std::string padded = s;
        if (visible_width < width) padded += std::string(width - visible_width, ' ');
        return "\u2502 " + padded + " \u2502";
    };
    return top + "\n" + pad(line1, w1) + "\n" + pad(line2, w2) + "\n" + bot;
}

void print_usage() {
    std::cout << "Использование: NanoDecompilerCLI plugin.jar [output_dir]\n";
    std::cout << "       NanoDecompilerCLI plugin.jar [out_dir] --no-legitimacy-check\n";
    std::cout << "       NanoDecompilerCLI plugin.jar [out_dir] --json-output   (разовый вызов, JSON в stdout)\n";
    std::cout << "       NanoDecompilerCLI --api-server [--host H] [--port 8791]   (HTTP-сервер)\n";
    std::cout << "       NanoDecompilerCLI --jar-summary plugin.jar   (JSON-сводка для GUI)\n";
    std::cout << "       NanoDecompilerCLI --version   (JSON с версией движка, для GUI - см. settings:checkEngine)\n";
    std::cout << "       NanoDecompilerCLI --install-tools[=jdk|maven]   (portable JDK/Maven по требованию)\n";
}

int run_decompile_console(const std::string& jar_path, const std::string& out_dir, bool skip_legitimacy) {
    std::cout << "\n" << banner_text() << "\n\n";

    nd::JarProcessResult jr;
    try {
        jr = nd::process_jar_with_stats(jar_path, out_dir, skip_legitimacy);
    } catch (const std::exception& e) {
        std::cerr << "[!] ОШИБКА: " << e.what() << "\n";
        return 1;
    }

    if (jr.rejected) {
        std::cout << "[!] " << jr.rejected_reason.value_or("Декомпиляция не выполнена.") << "\n";
        return 1;
    }

    auto warning_text = nd::format_findings_for_console(jr.malware_findings);
    if (warning_text.has_value()) {
        std::cout << *warning_text << "\n";
    } else {
        std::cout << "[*] Признаков вредоносного кода не обнаружено (эвристика, не гарантия - см. README_RU.txt).\n";
    }

    if (jr.legitimacy.has_value()) {
        auto leg_text = nd::format_for_console(*jr.legitimacy);
        if (leg_text.has_value()) std::cout << *leg_text << "\n";
    }

    double pct = jr.stats.pct(jr.stats.decompiled_methods, jr.stats.total_methods);
    std::printf("[*] Методов декомпилировано: %d/%d (%.1f%%)\n", jr.stats.decompiled_methods, jr.stats.total_methods, pct);
    std::cout << "[+] Готово. Результат в: " << out_dir << "\n";
    // НОВОЕ v1.8.0 (реальный запрос - "детальнейшая информация о плагине"
    // в мини-карточке в GUI): переиспользуем ТОТ ЖЕ сериализатор, что и
    // --json-output, ОДНОЙ строкой с машиночитаемым маркером в конце
    // обычного вывода - GUI ловит её и молча прячет из лога (см.
    // parseEngineResult в engine.tsx), человеку в терминале эта строка не
    // нужна и не показывается никак кроме уже напечатанного текста выше.
    // Явно НЕ добавляем эту строку в json_output-режим (там UI и так
    // получает всё этим же JSON-объектом целиком - дублировать незачем).
    std::cout << "##ND_RESULT:" << nd::jar_process_result_to_json(jr, out_dir, 0.0) << "##\n";
    return 0;
}

// --install-tools[=jdk|maven] - текстовый режим для терминала (человек
// читает глазами) - см. toolinstaller.hpp для --install-tools-json (тот же
// функционал, но NDJSON для GUI).
int run_install_tools_console(const std::optional<std::string>& only) {
    auto java_path = nd::resolve_tool_path({"java", "java.exe"}, "java");
    if (!java_path.has_value()) java_path = nd::find_local_java();
    auto mvn_path = nd::resolve_tool_path({"mvn", "mvn.cmd"}, "maven");
    if (!mvn_path.has_value()) mvn_path = nd::find_local_maven();

    bool need_java = !java_path.has_value();
    bool need_maven = !mvn_path.has_value();
    if (only.has_value()) {
        if (*only == "jdk" || *only == "java") {
            need_maven = false;
        } else if (*only == "maven") {
            need_java = false;
        }
    }

    if (!need_java && !need_maven) {
        std::cout << "[*] Всё уже установлено:\n";
        std::cout << "    java: " << java_path.value_or("(не найдена)") << "\n";
        std::cout << "    mvn:  " << mvn_path.value_or("(не найден)") << "\n";
        return 0;
    }

    std::cout << "[*] Скачивание и распаковка (см. " << nd::get_tools_dir() << ")...\n";
    auto result = nd::install_missing(need_java, need_maven, [](const std::string& label, uint64_t downloaded, std::optional<uint64_t> total) {
        if (total.has_value()) {
            std::printf("    %s: %.1f МБ / %.1f МБ\n", label.c_str(), downloaded / 1024.0 / 1024.0, *total / 1024.0 / 1024.0);
        } else {
            std::printf("    %s: %.1f МБ\n", label.c_str(), downloaded / 1024.0 / 1024.0);
        }
    });

    for (auto& err : result.errors) std::cerr << "[!] " << err << "\n";
    if (result.java.has_value()) std::cout << "[+] java: " << *result.java << "\n";
    if (result.maven.has_value()) std::cout << "[+] mvn:  " << *result.maven << "\n";
    return result.errors.empty() ? 0 : 1;
}

// --install-tools-json[=jdk|maven] - см. toolinstaller.hpp/HANDOFF_46.
// Формат событий 1:1 с main.py::_try_handle_install_tools_json (см. старый
// HANDOFF_39/40 для истории) - Electron-клиент это ожидает именно так:
//   {"type":"progress","label":"JDK","pct":42|null,"downloaded_mb":N,"total_mb":N|null}
//   {"type":"done","java":"путь"|null,"maven":"путь"|null,"errors":[...]}
//   {"type":"error","message":"..."}
int run_install_tools_json(const std::optional<std::string>& only) {
    try {
        auto java_path = nd::resolve_tool_path({"java", "java.exe"}, "java");
        if (!java_path.has_value()) java_path = nd::find_local_java();
        auto mvn_path = nd::resolve_tool_path({"mvn", "mvn.cmd"}, "maven");
        if (!mvn_path.has_value()) mvn_path = nd::find_local_maven();

        bool need_java = !java_path.has_value();
        bool need_maven = !mvn_path.has_value();
        if (only.has_value()) {
            if (*only == "jdk" || *only == "java") {
                need_maven = false;
            } else if (*only == "maven") {
                need_java = false;
            }
        }

        auto print_done = [](const std::optional<std::string>& java, const std::optional<std::string>& maven,
                              const std::vector<std::string>& errors) {
            std::string errs;
            for (size_t i = 0; i < errors.size(); ++i) {
                if (i) errs += ",";
                errs += "\"" + json_escape(errors[i]) + "\"";
            }
            std::cout << "{\"type\":\"done\",\"java\":" << (java.has_value() ? ("\"" + json_escape(*java) + "\"") : "null")
                       << ",\"maven\":" << (maven.has_value() ? ("\"" + json_escape(*maven) + "\"") : "null") << ",\"errors\":[" << errs
                       << "]}\n";
        };

        if (!need_java && !need_maven) {
            print_done(java_path, mvn_path, {});
            return 0;
        }

        auto progress_cb = [](const std::string& label, uint64_t downloaded, std::optional<uint64_t> total) {
            std::cout << "{\"type\":\"progress\",\"label\":\"" << json_escape(label) << "\",\"pct\":";
            if (total.has_value() && *total > 0) {
                std::cout << (downloaded * 100 / *total);
            } else {
                std::cout << "null";
            }
            std::cout << ",\"downloaded_mb\":" << (downloaded / 1024 / 1024) << ",\"total_mb\":";
            if (total.has_value())
                std::cout << (*total / 1024 / 1024);
            else
                std::cout << "null";
            std::cout << "}\n";
            std::cout.flush();
        };

        auto result = nd::install_missing(need_java, need_maven, progress_cb);
        print_done(result.java, result.maven, result.errors);
        return 0;
    } catch (const std::exception& e) {
        std::cout << "{\"type\":\"error\",\"message\":\"" << json_escape(e.what()) << "\"}\n";
        return 1;
    }
}

}  // namespace

int run_cli(int argc, char** argv) {
    std::vector<std::string> args(argv + 1, argv + argc);
    if (args.empty()) {
        print_usage();
        return 1;
    }

    if (args[0] == "--version") {
        // БАГ-ФИКС: раньше версии движка/GUI в SettingsModal.tsx/AppHeader.tsx/
        // Titlebar.tsx/StatusBar.tsx были захардкожены заглушками из демо-
        // прототипа ("2.4.1", "GUI v2.1.0", "build a3f9c2") и никогда не
        // совпадали с реальным version.hpp ("NanoDecompiler v1.6.1 BETA") -
        // мгновенный флаг без запуска jar/сервера, чтобы GUI мог спросить
        // движок напрямую вместо хардкода.
        std::cout << "{\"version\":\"" << NANO_DECOMPILER_VERSION << "\"}\n";
        return 0;
    }

    if (args[0] == "--jar-summary") {
        if (args.size() < 2) {
            std::cout << "{\"error\":\"использование: NanoDecompilerCLI --jar-summary plugin.jar\"}\n";
            return 0;
        }
        {
            // БАГ-ФИКС v1.7.6: см. подробный комментарий у аналогичной
            // проверки ниже (обычный/json режим декомпиляции) - та же
            // путаница "нет прав" -> "файл не найден" была и здесь.
            std::error_code ec;
            bool is_reg = fs::is_regular_file(fs::u8path(args[1]), ec);
            if (!is_reg) {
                if (ec && ec == std::errc::permission_denied) {
                    std::cout << "{\"error\":\"нет прав на чтение файла: " << args[1] << "\"}\n";
                } else {
                    std::cout << "{\"error\":\"файл не найден: " << args[1] << "\"}\n";
                }
                return 0;
            }
        }
        std::cout << nd::jar_summary_to_json(nd::jar_summary(args[1])) << "\n";
        return 0;
    }

    if (args[0] == "--api-server") {
        std::string host = "127.0.0.1";
        int port = 8791;
        for (size_t i = 1; i < args.size(); ++i) {
            if (args[i] == "--host" && i + 1 < args.size()) host = args[++i];
            else if (args[i] == "--port" && i + 1 < args.size()) port = std::atoi(args[++i].c_str());
        }
        return nd::run_api_server(host, port) ? 0 : 1;
    }

    if (args[0].rfind("--install-tools-json", 0) == 0) {
        std::optional<std::string> only;
        auto eq = args[0].find('=');
        if (eq != std::string::npos) {
            std::string v = args[0].substr(eq + 1);
            for (auto& c : v) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
            only = v;
        }
        return run_install_tools_json(only);
    }

    if (args[0].rfind("--install-tools", 0) == 0) {
        std::optional<std::string> only;
        auto eq = args[0].find('=');
        if (eq != std::string::npos) {
            std::string v = args[0].substr(eq + 1);
            for (auto& c : v) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
            only = v;
        }
        return run_install_tools_console(only);
    }

    std::string jar_path = args[0];
    bool skip_legitimacy = false;
    bool json_output = false;
    std::vector<std::string> positional_rest;
    for (size_t i = 1; i < args.size(); ++i) {
        if (args[i] == "--no-legitimacy-check") {
            skip_legitimacy = true;
        } else if (args[i] == "--json-output" || args[i] == "--api") {
            json_output = true;
        } else {
            positional_rest.push_back(args[i]);
        }
    }
    std::string out_dir = !positional_rest.empty() ? positional_rest[0] : (fs::u8path(jar_path).stem().u8string() + "_decompiled");

    // БАГ-ФИКС v1.7.6 (реальная жалоба пользователя - "не может декомпить
    // плагин на диске С, будто нет прав, но через Загрузки всё ок"):
    // fs::is_regular_file() без std::error_code при ошибке ДОСТУПА (не
    // "файла нет", а "нет прав его прочитать/статнуть" - например, если
    // jar лежит в защищённой системной папке типа Program Files/Windows)
    // просто возвращает false - неотличимо от "файла реально нет". Отсюда
    // вводящее в заблуждение "файл не найден", хотя файл РЕАЛЬНО есть,
    // просто движку не хватает прав его открыть. Различаем причины через
    // std::error_code явно и даём разные, куда более полезные сообщения.
    std::error_code jar_check_ec;
    bool jar_is_regular = fs::is_regular_file(fs::u8path(jar_path), jar_check_ec);
    if (!jar_is_regular) {
        std::string msg;
        if (jar_check_ec && jar_check_ec == std::errc::permission_denied) {
            msg = "нет прав на чтение файла: " + jar_path +
                  " - попробуйте переместить .jar в другую папку (например, в \"Загрузки\") "
                  "или запустить программу от имени администратора";
        } else if (jar_check_ec) {
            msg = "не удалось проверить файл: " + jar_path + " (" + jar_check_ec.message() + ")";
        } else {
            msg = "файл не найден: " + jar_path;
        }
        if (json_output) {
            std::cout << nd::json_error_response(msg) << "\n";
            return 1;
        }
        std::cerr << "[!] ОШИБКА: " << msg << "\n";
        return 1;
    }

    if (json_output) return nd::run_json_output(jar_path, out_dir, skip_legitimacy);

    return run_decompile_console(jar_path, out_dir, skip_legitimacy);
}

// БАГ-ФИКС v1.7.4 (реальный краш на Windows - необработанное исключение
// std::filesystem::filesystem_error улетало прямо в std::terminate(),
// пользователь видел сырой C++ "terminate called after throwing..." вместо
// понятного сообщения). Первопричина (кодировка путей на Windows) исправлена
// отдельно (см. fs::u8path() выше), но это - защита "на всякий случай":
// ЛЮБОЕ другое непредвиденное исключение (в этой ли функции, в будущих ли
// изменениях кода) теперь даёт читаемую ошибку и код возврата 1 вместо
// падения самого процесса без единого пояснения.
#ifdef _WIN32
#include <windows.h>

// БАГ-ФИКС v1.7.5 (реальный краш ВСЁ ЕЩЁ повторялся у пользователя ПОСЛЕ
// u8path()/u8string() фиксов - копали не в ту сторону): main(argc, char**
// argv) на Windows/MinGW получает аргументы командной строки УЖЕ
// сконвертированными CRT-стартапом в АНСИ-кодовую страницу (НЕ UTF-8!) -
// если реальный путь (например, содержащий кириллическое имя профиля
// пользователя Windows) не укладывается в текущую ANSI-кодовую страницу,
// argv[] УЖЕ содержит испорченные/потерянные байты ДО того, как наш код
// вообще успевает что-то сделать - fs::u8path() на УЖЕ испорченной строке
// всё равно бросает то же исключение, потому что байты не валидны в UTF-8
// (это не "наши" байты, это то, что получилось после ANSI-конвертации).
// Единственный надёжный способ получить РЕАЛЬНЫЕ (не потерянные) аргументы
// командной строки на Windows - взять их в ШИРОКОМ (UTF-16) виде через
// GetCommandLineW()+CommandLineToArgvW() (не проходят через ANSI вообще) и
// перекодировать САМИМ в UTF-8 явно (WideCharToMultiByte с CP_UTF8) - то
// есть, по сути, свой собственный "wmain", т.к. использовать wmain()
// напрямую с MinGW менее переносимо между вариантами тулчейна.
std::vector<std::string> get_utf8_argv() {
    int wargc = 0;
    LPWSTR* wargv = CommandLineToArgvW(GetCommandLineW(), &wargc);
    std::vector<std::string> out;
    if (!wargv) return out;
    for (int i = 0; i < wargc; ++i) {
        int len = WideCharToMultiByte(CP_UTF8, 0, wargv[i], -1, nullptr, 0, nullptr, nullptr);
        std::string s(len > 0 ? len - 1 : 0, '\0');
        if (len > 0) WideCharToMultiByte(CP_UTF8, 0, wargv[i], -1, s.data(), len, nullptr, nullptr);
        out.push_back(std::move(s));
    }
    LocalFree(wargv);
    return out;
}
#endif

int main(int argc, char** argv) {
    try {
#ifdef _WIN32
        // Игнорируем ANSI-испорченный argv целиком - берём собственный,
        // честно перекодированный из UTF-16 в UTF-8 (см. get_utf8_argv()).
        std::vector<std::string> wide_args = get_utf8_argv();
        std::vector<char*> fake_argv;
        for (auto& s : wide_args) fake_argv.push_back(s.data());
        return run_cli(static_cast<int>(fake_argv.size()), fake_argv.data());
#else
        return run_cli(argc, argv);
#endif
    } catch (const std::exception& e) {
        std::cerr << "[!] ВНУТРЕННЯЯ ОШИБКА движка: " << e.what() << "\n"
                   << "    Пожалуйста, сообщите об этом разработчику вместе с именем .jar файла,\n"
                   << "    который вызвал эту ошибку (см. README_RU.txt).\n";
        return 1;
    } catch (...) {
        std::cerr << "[!] ВНУТРЕННЯЯ ОШИБКА движка: неизвестное исключение (не std::exception).\n";
        return 1;
    }
}
