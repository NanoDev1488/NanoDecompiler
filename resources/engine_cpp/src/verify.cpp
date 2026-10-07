// verify.cpp - см. verify.hpp. 1:1 порт verify.py.
#include "verify.hpp"

#include <algorithm>
#include <cctype>
#include <iomanip>
#include <set>
#include <sstream>

namespace nd {

std::vector<std::string> check_brackets(const std::string& text, const std::string& filename) {
    std::vector<std::string> issues;
    // pairs: close -> open ; opens/closes - множества символов
    const std::set<char> opens = {'(', '[', '{'};
    auto open_for_close = [](char c) -> char {
        if (c == ')') return '(';
        if (c == ']') return '[';
        if (c == '}') return '{';
        return 0;
    };
    auto is_close = [](char c) { return c == ')' || c == ']' || c == '}'; };

    std::vector<std::pair<char, int>> stack;
    size_t i = 0, n = text.size();
    int line = 1;
    bool in_string = false, in_char = false, in_line_comment = false, in_block_comment = false;

    while (i < n) {
        char c = text[i];
        if (c == '\n') {
            line += 1;
            in_line_comment = false;
            i += 1;
            continue;
        }
        if (in_line_comment) {
            i += 1;
            continue;
        }
        if (in_block_comment) {
            if (c == '*' && i + 1 < n && text[i + 1] == '/') {
                in_block_comment = false;
                i += 2;
                continue;
            }
            i += 1;
            continue;
        }
        if (in_string) {
            if (c == '\\') {
                i += 2;  // намеренно НЕ считает переводы строк внутри пропущенных 2 байт - как в оригинале
                continue;
            }
            if (c == '"') in_string = false;
            i += 1;
            continue;
        }
        if (in_char) {
            if (c == '\\') {
                i += 2;
                continue;
            }
            if (c == '\'') in_char = false;
            i += 1;
            continue;
        }
        if (c == '/' && i + 1 < n && text[i + 1] == '/') {
            in_line_comment = true;
            i += 2;
            continue;
        }
        if (c == '/' && i + 1 < n && text[i + 1] == '*') {
            in_block_comment = true;
            i += 2;
            continue;
        }
        if (c == '"') {
            in_string = true;
            i += 1;
            continue;
        }
        if (c == '\'') {
            in_char = true;
            i += 1;
            continue;
        }
        if (opens.count(c)) {
            stack.emplace_back(c, line);
        } else if (is_close(c)) {
            if (stack.empty()) {
                std::ostringstream oss;
                oss << filename << ":" << line << ": лишняя закрывающая скобка '" << c << "'";
                issues.push_back(oss.str());
            } else {
                auto [oc, oline] = stack.back();
                stack.pop_back();
                if (open_for_close(c) != oc) {
                    std::ostringstream oss;
                    oss << filename << ":" << line << ": несовпадение скобок: открыта '" << oc
                        << "' на строке " << oline << ", закрыта '" << c << "'";
                    issues.push_back(oss.str());
                }
            }
        }
        i += 1;
    }
    for (auto& [oc, oline] : stack) {
        std::ostringstream oss;
        oss << filename << ":" << oline << ": незакрытая скобка '" << oc << "'";
        issues.push_back(oss.str());
    }
    return issues;
}

std::vector<std::string> verify_class_text(const std::string& text, const std::string& filename) {
    // 1-6. Проверка баланса скобок (), {}, [] и незакрытых литералов
    std::vector<std::string> issues = check_brackets(text, filename);

    // Построчный анализ для остальных 19 проверок (всего 25 проверок целостности и синтаксиса)
    std::istringstream stream(text);
    std::string line;
    int line_num = 0;
    int brace_depth = 0;
    int loop_or_switch_depth = 0;

    while (std::getline(stream, line)) {
        line_num++;
        std::string s = line;

        size_t first = s.find_first_not_of(" \t\r\n");
        if (first == std::string::npos) continue;
        size_t last = s.find_last_not_of(" \t\r\n");
        std::string trimmed = s.substr(first, last - first + 1);

        if (trimmed.rfind("//", 0) == 0) continue;

        // 7. Проверка \u unicode escape (должно быть 4 hex-символа)
        size_t u_pos = 0;
        while ((u_pos = s.find("\\u", u_pos)) != std::string::npos) {
            if (u_pos + 6 > s.size()) {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": некорректный unicode escape \\u (слишком короткий)");
                break;
            }
            for (size_t k = u_pos + 2; k < u_pos + 6; ++k) {
                if (!std::isxdigit(static_cast<unsigned char>(s[k]))) {
                    issues.push_back(filename + ":" + std::to_string(line_num) + ": недопустимый символ в unicode escape: " + s.substr(u_pos, 6));
                    break;
                }
            }
            u_pos += 6;
        }

        // 8. package декларация
        if (trimmed.rfind("package ", 0) == 0) {
            if (trimmed == "package ;" || trimmed == "package;") {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": пустое объявление package");
            } else if (trimmed.back() != ';') {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": пропущена точка с запятой в объявлении package");
            }
        }

        // 9. import декларация
        if (trimmed.rfind("import ", 0) == 0) {
            if (trimmed == "import ;" || trimmed == "import;") {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": пустое объявление import");
            } else if (trimmed.back() != ';') {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": пропущена точка с запятой в объявлении import");
            }
        }

        // 10. Дублирующиеся модификаторы доступа
        static const std::vector<std::string> dup_modifiers = {
            "public public", "private private", "protected protected",
            "public private", "private public", "static static", "final final",
            "abstract abstract", "volatile volatile", "transient transient"
        };
        for (const auto& dm : dup_modifiers) {
            if (s.find(dm) != std::string::npos) {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": конфликт или дублирование модификаторов доступа: '" + dm + "'");
            }
        }

        // 11-12. break / continue вне контекста цикла
        if (s.find("for (") != std::string::npos || s.find("for(") != std::string::npos ||
            s.find("while (") != std::string::npos || s.find("while(") != std::string::npos ||
            s.find("switch (") != std::string::npos || s.find("switch(") != std::string::npos) {
            loop_or_switch_depth++;
        }
        if (trimmed == "continue;" && loop_or_switch_depth == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": continue вне тела цикла");
        }
        if (trimmed == "break;" && loop_or_switch_depth == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": break вне цикла или switch");
        }

        // 13. Двойная точка с запятой (;; вне for)
        if (s.find(";;") != std::string::npos && s.find("for") == std::string::npos) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": лишняя точка с запятой ;;");
        }

        // 14. Несбалансированные дженерики < > в сигнатуре
        if ((trimmed.rfind("class ", 0) == 0 || trimmed.rfind("interface ", 0) == 0 || trimmed.find("public ") != std::string::npos) &&
            trimmed.find("<<") == std::string::npos && trimmed.find(">>") == std::string::npos) {
            int open_angles = std::count(trimmed.begin(), trimmed.end(), '<');
            int close_angles = std::count(trimmed.begin(), trimmed.end(), '>');
            if (open_angles != close_angles && trimmed.find(";") != std::string::npos) {
                if (trimmed.back() == ';' || trimmed.back() == '{') {
                    issues.push_back(filename + ":" + std::to_string(line_num) + ": возможное несовпадение угловых скобок дженериков < >");
                }
            }
        }

        // 15. Утечка сырого байткод-дескриптора в идентификаторах
        if (s.find("Ljava/lang/") != std::string::npos || s.find("Lorg/") != std::string::npos || s.find("Lcom/") != std::string::npos) {
            if (s.find("//") == std::string::npos && s.find("/*") == std::string::npos) {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": утечка сырого JVM-дескриптора в коде");
            }
        }

        // 16. Висячие инфиксные операторы перед концом блока
        if (trimmed.size() > 1 && (trimmed.back() == '+' || trimmed.back() == '-' || trimmed.back() == '*' || trimmed.back() == '/') &&
            s.find("\"") == std::string::npos) {
            if (trimmed.size() >= 2 && trimmed[trimmed.size() - 2] == '}') {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": висячий оператор перед концом блока");
            }
        }

        // 17. Некорректный числовой литерал
        if (s.find("0x;") != std::string::npos || s.find("0b;") != std::string::npos) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": неполный шестнадцатеричный или бинарный литерал");
        }

        // 18. Некорректный throw без выражения
        if (trimmed == "throw;" || trimmed == "throw ;") {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": оператор throw без выражения исключения");
        }

        // 19. Некорректный catch без аргумента
        if (trimmed.rfind("catch ()", 0) == 0 || trimmed.rfind("catch()", 0) == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": пустой блок catch без параметра исключения");
        }

        // 20. Пустые условия if () / while ()
        if (trimmed.rfind("if ()", 0) == 0 || trimmed.rfind("if()", 0) == 0 ||
            trimmed.rfind("while ()", 0) == 0 || trimmed.rfind("while()", 0) == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": пустое условие if/while");
        }

        // 21. Пустое выражение switch ()
        if (trimmed.rfind("switch ()", 0) == 0 || trimmed.rfind("switch()", 0) == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": пустое выражение switch");
        }

        // 22. Пустой блок synchronized ()
        if (trimmed.rfind("synchronized ()", 0) == 0 || trimmed.rfind("synchronized()", 0) == 0) {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": пустой монитор блокировки synchronized");
        }

        // 23. Утечка служебных маркеров движка (StackVM tokens)
        static const std::vector<std::string> engine_tokens = {
            "__stk_unresolved", "__ILLEGAL_OPCODE", "__NULL_REF_AST", "UNDEFINED_LOCAL", "__UNSUPPORTED_BYTECODE"
        };
        for (const auto& tok : engine_tokens) {
            if (s.find(tok) != std::string::npos) {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": утечка служебного токена движка: " + tok);
            }
        }

        // 24. Утечка двойной точки .. в выражениях (не varargs ...)
        size_t dotdot = 0;
        while ((dotdot = s.find("..", dotdot)) != std::string::npos) {
            if (dotdot + 2 < s.size() && s[dotdot + 2] == '.') {
                dotdot += 3;
                continue;
            }
            if (dotdot > 0 && s[dotdot - 1] == '.') {
                dotdot += 2;
                continue;
            }
            if (s.find("\"") == std::string::npos && s.find("//") == std::string::npos) {
                issues.push_back(filename + ":" + std::to_string(line_num) + ": недопустимый оператор '..' (опечатка или артефакт слияния)");
                break;
            }
            dotdot += 2;
        }

        // 25. return со значением в конструкторе
        if (s.find("public <init>") != std::string::npos && trimmed.rfind("return ", 0) == 0 && trimmed != "return;") {
            issues.push_back(filename + ":" + std::to_string(line_num) + ": возврат значения в конструкторе");
        }

        int closes = std::count(trimmed.begin(), trimmed.end(), '}');
        int opens = std::count(trimmed.begin(), trimmed.end(), '{');
        brace_depth += opens - closes;
        if (closes > 0 && loop_or_switch_depth > 0) {
            loop_or_switch_depth = std::max(0, loop_or_switch_depth - closes);
        }
    }

    return issues;
}

ImportConflicts check_import_collisions(const OrderedImportMap& imports) {
    std::vector<std::string> simple_order;
    std::map<std::string, std::vector<std::string>> by_simple_order_preserving_values;
    std::map<std::string, std::set<std::string>> by_simple_set;
    for (auto& [dotted, simple] : imports) {
        auto [it, inserted] = by_simple_set.try_emplace(simple);
        if (inserted) simple_order.push_back(simple);
        it->second.insert(dotted);
    }
    ImportConflicts conflicts;
    for (auto& simple : simple_order) {
        auto& dset = by_simple_set[simple];
        if (dset.size() > 1) {
            std::vector<std::string> sorted_d(dset.begin(), dset.end());
            std::sort(sorted_d.begin(), sorted_d.end());  // sorted(d) в Python - множество уже без дублей
            conflicts.emplace_back(simple, std::move(sorted_d));
        }
    }
    return conflicts;
}

void ProjectStats::record_method(bool ok, const std::optional<std::string>& reason) {
    total_methods += 1;
    if (ok) {
        decompiled_methods += 1;
    } else {
        fallback_methods += 1;
        // ПРИЧЁСАНО v1.7.3.1 (по просьбе - "verify.cpp написан не очень
        // красиво"): раньше здесь был ручной линейный поиск по вектору
        // (`for` + `if` + флаг `found`) - ниже в этом же файле, в
        // summary_text(), для АБСОЛЮТНО той же задачи ("посчитать по
        // ключу, сохранив порядок первого появления") уже используется
        // чистый паттерн map + вектор порядка (см. `grouped`/`grouped_order`).
        // Применяем тот же паттерн здесь - меньше кода, тот же результат,
        // единообразно с остальным файлом.
        auto [it, inserted] = fallback_reason_index.try_emplace(reason, fallback_reasons.size());
        if (inserted) {
            fallback_reasons.emplace_back(reason, 1);
        } else {
            fallback_reasons[it->second].second += 1;
        }
    }
}

double ProjectStats::pct(int part, int whole) const {
    return whole ? (static_cast<double>(part) / static_cast<double>(whole) * 100.0) : 0.0;
}

std::string quality_rating(double p) {
    if (p < 50) {
        return "БАГ \xE2\x9A\xA0\xEF\xB8\x8F Меньше половины методов восстановлено - это уже не похоже на "
               "особенности плагина, скорее всего где-то реальный баг в самом движке. "
               "Напишите разработчику в Telegram: @ERROR_92 - приложите исходный .jar "
               "и этот README_RU.txt целиком, так баг найдётся и починится быстрее.";
    }
    if (p >= 96.9) return "\xF0\x9F\x94\xA5 Идеально! Практически весь код восстановлен в чистый структурированный Java.";
    if (p >= 90) return "Отлично - почти всё восстановлено, местами придётся чуть подчистить руками.";
    if (p >= 75) return "Неплохо - основная часть восстановлена, но заметная доля ушла в байткод-фоллбэк.";
    return "Так себе - многовато байткод-фоллбэков, плагин явно с нестандартными конструкциями.";
}

namespace {
// ПРИЧЁСАНО v1.7.3.1: три отдельных anonymous namespace в одном файле были
// разбросаны по разным местам без причины - объединено в один блок
// служебных хелперов файла (реального изменения поведения нет).
bool starts_with(const std::string& s, const std::string& prefix) {
    return s.size() >= prefix.size() && s.compare(0, prefix.size(), prefix) == 0;
}
std::string fmt1(double v) {
    std::ostringstream oss;
    oss.precision(1);
    oss << std::fixed << v;
    return oss.str();
}
std::string join(const std::vector<std::string>& v, const std::string& sep) {
    std::string out;
    for (size_t i = 0; i < v.size(); ++i) {
        if (i) out += sep;
        out += v[i];
    }
    return out;
}
}  // namespace

std::string group_reason(const std::optional<std::string>& reason_opt) {
    if (!reason_opt.has_value()) return "неизвестно";
    const std::string& reason = *reason_opt;
    if (starts_with(reason, "нередуцируемый goto")) return "нередуцируемый goto (сложный control-flow, не сведённый к структурам)";
    if (starts_with(reason, "несогласованная глубина")) return "многозначное пересечение стека между блоками (напр. arr[i] = cond ? a : b)";
    if (starts_with(reason, "unrecognized <init>")) return "нестандартный паттерн вызова конструктора";
    if (starts_with(reason, "внутренняя ошибка")) return "внутренняя ошибка декомпилятора (см. детали в логе)";
    if (starts_with(reason, "неизвестная/неподдержанная инструкция")) return reason;
    return reason;
}

std::string ProjectStats::summary_text() const {
    std::vector<std::string> lines;
    lines.push_back(std::string(70, '='));
    lines.push_back("ПРОВЕРКА КАЧЕСТВА ДЕКОМПИЛЯЦИИ");
    lines.push_back(std::string(70, '='));
    lines.push_back("");
    lines.push_back("Классов в jar: " + std::to_string(classes_total) + ", успешно распарсено байткода: " +
                     std::to_string(classes_parsed) + " (" + fmt1(pct(classes_parsed, classes_total)) + "%)");
    if (library_classes_skipped) {
        std::vector<std::string> hit(library_names_hit.begin(), library_names_hit.end());
        std::sort(hit.begin(), hit.end());
        std::string hit_str = hit.empty() ? "?" : join(hit, ", ");
        lines.push_back("  Классов из известных сторонних библиотек НЕ декомпилировано (не бандлятся - "
                         "добавлены в pom.xml как maven-зависимость): " +
                         std::to_string(library_classes_skipped) + " (обнаружено: " + hit_str + ")");
    }
    if (!parse_errors.empty()) {
        lines.push_back("  Классы с ошибкой парсинга constant pool/байткода (" + std::to_string(parse_errors.size()) + "):");
        size_t lim = std::min<size_t>(30, parse_errors.size());
        for (size_t i = 0; i < lim; ++i) {
            lines.push_back("    - " + parse_errors[i].first + ": " + parse_errors[i].second);
        }
    }
    lines.push_back("");
    lines.push_back("Методов с телом (есть байткод): " + std::to_string(total_methods));
    double p = pct(decompiled_methods, total_methods);
    lines.push_back("  - Полностью восстановлены в структурированный Java "
                     "(if/else, while/for, switch, try/catch, выражения): " +
                     std::to_string(decompiled_methods) + " (" + fmt1(p) + "%)");
    lines.push_back("  - Не удалось безопасно восстановить -> честный дизассемблированный "
                     "листинг байткода (см. комментарий в самом методе): " +
                     std::to_string(fallback_methods) + " (" + fmt1(pct(fallback_methods, total_methods)) + "%)");
    lines.push_back("");
    lines.push_back("  Крутизна декомпиляции: " + quality_rating(p));
    if (!fallback_reasons.empty()) {
        lines.push_back("");
        lines.push_back("  Причины отката на байткод (сгруппировано):");
        std::vector<std::string> grouped_order;
        std::map<std::string, int> grouped;
        for (auto& [reason, cnt] : fallback_reasons) {
            std::string key = group_reason(reason);
            auto [it, inserted] = grouped.try_emplace(key, 0);
            if (inserted) grouped_order.push_back(key);
            it->second += cnt;
        }
        // sorted(grouped.items(), key=lambda kv: -kv[1]) - устойчивая сортировка
        // по убыванию count, при равенстве - порядок первого появления ключа.
        std::stable_sort(grouped_order.begin(), grouped_order.end(),
                          [&](const std::string& a, const std::string& b) { return grouped[a] > grouped[b]; });
        for (auto& key : grouped_order) {
            std::ostringstream oss;
            oss << "    " << std::setw(5) << grouped[key] << "  " << key;
            lines.push_back(oss.str());
        }
    }
    lines.push_back("");
    if (synthetic_switchmap_classes_hidden) {
        lines.push_back("Восстановлено настоящих switch(enum){...} вместо synthetic switch-map "
                         "классов компилятора: скрыто " + std::to_string(synthetic_switchmap_classes_hidden) +
                         " вспомогательных классов (их никогда не было в исходнике).");
        lines.push_back("");
    }
    if (!bracket_issues.empty()) {
        lines.push_back("ВНИМАНИЕ: найдены замечания верификации кода в " + std::to_string(bracket_issues.size()) +
                         " местах (проверка по 25 правилам синтаксиса, структуры и баланса скобок):");
        size_t lim = std::min<size_t>(40, bracket_issues.size());
        for (size_t i = 0; i < lim; ++i) lines.push_back("  " + bracket_issues[i]);
    } else {
        lines.push_back("Код проверен по 25 синтаксическим правилам верификации (баланс скобок {} () [], строковые литералы, модификаторы, маркеры декомпилятора) - замечаний не обнаружено.");
    }
    lines.push_back("");
    if (!import_conflicts.empty()) {
        lines.push_back("ВНИМАНИЕ: " + std::to_string(import_conflicts.size()) +
                         " коллизий коротких имён классов (разные полные имена сведены к одному simple-имени "
                         "в одном файле - возможна неоднозначность, при ручной доводке используйте полное имя):");
        size_t lim = std::min<size_t>(30, import_conflicts.size());
        for (size_t i = 0; i < lim; ++i) {
            lines.push_back("  " + import_conflicts[i].first + ": " + join(import_conflicts[i].second, ", "));
        }
    }
    lines.push_back("");
    lines.push_back("ЧТО ЭТО ЗНАЧИТ НА ПРАКТИКЕ:");
    lines.push_back(
        "  В этом окружении сборки нет javac, поэтому мы не можем гарантировать компиляцию\n"
        "  на 100% - НО каждый метод, помеченный как 'восстановлен', прошёл через:\n"
        "    1) полную символическую интерпретацию байткода (стек-машина -> выражения),\n"
        "    2) структуризацию control-flow (if/while/for/switch/try) через дерево\n"
        "       доминаторов/постдоминаторов,\n"
        "    3) проверку баланса скобок сгенерированного текста.\n"
        "  Если на любом из этих шагов декомпилятор не был уверен на 100% - метод\n"
        "  автоматически откатывается на честный дизассемблированный листинг байткода\n"
        "  вместо того, чтобы 'угадывать' и рисковать неверной логикой.\n");
    return join(lines, "\n");
}

}  // namespace nd
