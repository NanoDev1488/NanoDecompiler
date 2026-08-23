// verify.hpp - порт resources/engine/verify.py (v2.0, HANDOFF_35).
// Проверки сгенерированного Java-текста (баланс скобок, коллизии
// simple-имён импортов) + агрегированная статистика качества декомпиляции.
#pragma once

#include <map>
#include <optional>
#include <set>
#include <string>
#include <vector>

namespace nd {

std::vector<std::string> check_brackets(const std::string& text, const std::string& filename);
std::vector<std::string> verify_class_text(const std::string& text, const std::string& filename);

// imports: dotted -> simple, В ПОРЯДКЕ ВСТАВКИ (важно для порядка результата -
// как Python dict, который сохраняет порядок вставки с 3.7). Возврат: пары
// (simple, отсортированный список dotted) в порядке ПЕРВОГО обнаружения
// simple-имени - только те, за которыми стоит БОЛЕЕ ОДНОГО разных dotted.
using OrderedImportMap = std::vector<std::pair<std::string, std::string>>;
using ImportConflicts = std::vector<std::pair<std::string, std::vector<std::string>>>;
ImportConflicts check_import_collisions(const OrderedImportMap& imports);

struct ProjectStats {
    int total_methods = 0;
    int decompiled_methods = 0;
    int fallback_methods = 0;
    // Порядок вставки важен (как Python dict) - пары (reason, count);
    // reason == nullopt - причина не указана (Python None). record_method
    // ищет существующий reason по значению и увеличивает счётчик, либо
    // добавляет новую пару в конец (эмулирует `dict.get(reason,0)+1`
    // с сохранением порядка первого появления).
    std::vector<std::pair<std::optional<std::string>, int>> fallback_reasons;
    int classes_total = 0;
    int classes_parsed = 0;
    std::vector<std::pair<std::string, std::string>> parse_errors;  // (name, error)
    std::vector<std::string> bracket_issues;
    ImportConflicts import_conflicts;  // порядок вставки важен - см. check_import_collisions
    int synthetic_switchmap_classes_hidden = 0;
    int library_classes_skipped = 0;
    std::set<std::string> library_names_hit;
    // HANDOFF_42: main.cpp (render_class) реально это поле заполняет -
    // суммарное число "мусорных" catch-блоков (обфускация try/catch-джанком),
    // убранных decompile_method_body по всем методам проекта.
    int junk_catches_removed = 0;
    // malware_findings/decrypted_strings_owner/decrypted_strings_count/
    // legitimacy - НЕ читаются нигде внутри verify.py самого (summary_text
    // их не использует) - опущены здесь намеренно (не относятся к
    // "статистике декомпиляции" как таковой); main.cpp носит их отдельно
    // в своей обёртке JarProcessResult (process_jar.hpp), а не здесь -
    // избегаем тянуть malware_scan.hpp/legitimacy_check.hpp в verify.hpp.

    void record_method(bool ok, const std::optional<std::string>& reason = std::nullopt);
    double pct(int part, int whole) const;
    std::string summary_text() const;
};

std::string quality_rating(double pct);
std::string group_reason(const std::optional<std::string>& reason);

}  // namespace nd
