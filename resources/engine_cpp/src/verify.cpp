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
    return check_brackets(text, filename);
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
        bool found = false;
        for (auto& [r, cnt] : fallback_reasons) {
            if (r == reason) {
                cnt += 1;
                found = true;
                break;
            }
        }
        if (!found) fallback_reasons.emplace_back(reason, 1);
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
bool starts_with(const std::string& s, const std::string& prefix) {
    return s.size() >= prefix.size() && s.compare(0, prefix.size(), prefix) == 0;
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

namespace {
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
        lines.push_back("ВНИМАНИЕ: найдены проблемы с балансом скобок в " + std::to_string(bracket_issues.size()) +
                         " местах (это указывало бы на баг в генераторе кода):");
        size_t lim = std::min<size_t>(40, bracket_issues.size());
        for (size_t i = 0; i < lim; ++i) lines.push_back("  " + bracket_issues[i]);
    } else {
        lines.push_back("Баланс скобок {} () [] проверен по всем сгенерированным .java файлам - "
                         "проблем не найдено.");
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
