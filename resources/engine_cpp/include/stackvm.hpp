// stackvm.hpp - порт resources/engine/stackvm.py (v2.0, HANDOFF_36).
// Символическая интерпретация одного базового блока байткода: превращает
// инструкции стек-машины в дерево выражений (Expr) и список операторов (Stmt).
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <array>
#include <map>
#include <memory>
#include <optional>
#include <set>
#include <stdexcept>
#include <string>
#include <vector>

#include "ast_nodes.hpp"
#include "cfg.hpp"
#include "classfile.hpp"
#include "emit.hpp"  // MonitorMarkerStmt - переиспользуем как _MonitorMarker
#include "ir.hpp"

namespace nd {

class DecompileAbort : public std::runtime_error {
public:
    explicit DecompileAbort(const std::string& msg) : std::runtime_error(msg) {}
};

// Имя-сентинел для "только что пойманного исключения" на входе в
// exception-handler блок - реальное значение сеет engine.py (ещё не
// перенесён) как Local(CAUGHT_SENTINEL,"Throwable") на entry_stack блока-
// обработчика ПЕРЕД вызовом simulate_block; structure.py ищет именно это
// имя, чтобы понять, что первый LocalDecl catch-блока - это переменная
// исключения, а не обычная локальная переменная.
constexpr const char* CAUGHT_SENTINEL = "__caught__";
extern const std::set<std::string> PSEUDO_TYPES;  // {"null", "this"} - см. stackvm.cpp

int width_of(const std::string& java_type);
char cat_of(const std::string& java_type);  // 'I'|'L'|'F'|'D'|'A'
std::string default_type_for_cat(char cat);

std::string java_string_literal(const std::string& s);
std::string java_float_literal(double v, const std::string& suffix = "f");
std::string char_literal(int codepoint);

// ---------------- Renamer: интерфейс + заглушка ----------------
// Реальный Renamer живёт в renamer.hpp/.cpp (портирован, HANDOFF_41,
// переименование обфусцированных имён по эвристике looks_obfuscated).
// IRenamer - минимальный
// интерфейс, который использует ЭТОТ модуль; IdentityRenamer - заглушка
// "ничего не переименовано" (валидный, тестируемый режим - см. HANDOFF_36:
// на реальных jar пользователя эвристика деобфускации и так ничего не находит,
// т.к. эти jar не обфусцированы - IdentityRenamer воспроизводит именно этот
// наблюдаемый на практике случай).
class IRenamer {
public:
    virtual ~IRenamer() = default;
    virtual std::string friendly_class(const std::string& internal) const = 0;
    virtual bool class_map_contains(const std::string& internal) const = 0;
    virtual std::string field_name(const std::string& owner, const std::string& name, const std::string& desc) const = 0;
    virtual std::string method_name(const std::string& owner, const std::string& name, const std::string& desc) const = 0;
};

class IdentityRenamer : public IRenamer {
public:
    std::string friendly_class(const std::string& internal) const override { return internal; }
    bool class_map_contains(const std::string&) const override { return false; }
    std::string field_name(const std::string&, const std::string& name, const std::string&) const override { return name; }
    std::string method_name(const std::string&, const std::string& name, const std::string&) const override { return name; }
};

struct LocalInfo {
    std::string name;
    std::string type;
    char category = 'A';
    bool is_param = false;
    std::set<char> seen_categories;
};

// dotted -> simple, порядок вставки важен (как Python dict + setdefault) -
// см. verify.hpp OrderedImportMap для того же паттерна.
class OrderedImports {
public:
    void set(const std::string& dotted, const std::string& simple) {
        for (auto& [d, s] : items_) {
            if (d == dotted) { s = simple; return; }
        }
        items_.emplace_back(dotted, simple);
    }
    void set_default(const std::string& dotted, const std::string& simple) {
        for (auto& [d, s] : items_) {
            if (d == dotted) return;
        }
        items_.emplace_back(dotted, simple);
    }
    bool contains(const std::string& dotted) const {
        for (auto& [d, s] : items_) if (d == dotted) return true;
        return false;
    }
    const std::vector<std::pair<std::string, std::string>>& items() const { return items_; }

private:
    std::vector<std::pair<std::string, std::string>> items_;
};

// Аналог Python dict - сохраняет порядок ВСТАВКИ (важно: порядок,
// в котором ctx.crossing_temp_types обходится при печати деклараций,
// наблюдаем в итоговом тексте) - std::map здесь не годится (сортирует по
// ключу, а не по времени добавления).
class OrderedStringMap {
public:
    bool count(const std::string& k) const { return index_.count(k) != 0; }
    std::string& operator[](const std::string& k) {
        auto it = index_.find(k);
        if (it != index_.end()) return items_[it->second].second;
        index_[k] = items_.size();
        items_.emplace_back(k, std::string());
        return items_.back().second;
    }
    void erase(const std::string& k) {
        auto it = index_.find(k);
        if (it == index_.end()) return;
        size_t pos = it->second;
        items_.erase(items_.begin() + static_cast<long>(pos));
        index_.erase(it);
        for (auto& [kk, vv] : index_) {
            if (vv > pos) vv -= 1;
        }
    }
    auto begin() { return items_.begin(); }
    auto end() { return items_.end(); }
    auto begin() const { return items_.begin(); }
    auto end() const { return items_.end(); }
    size_t size() const { return items_.size(); }

private:
    std::vector<std::pair<std::string, std::string>> items_;
    std::map<std::string, size_t> index_;
};

class MethodCtx {
public:
    MethodCtx(const ClassFile& cf, const Method& method, const IRenamer& renamer,
              const std::map<std::string, std::string>& known_internal_by_dotted, const std::string& class_internal);

    const ClassFile& cf;
    const Method& method;
    const IRenamer& renamer;
    const std::map<std::string, std::string>& known;  // dotted -> internal ("известные" внешние типы для импортов)
    std::string class_internal;
    std::map<int, LocalInfo> locals;
    int temp_ctr = 0;
    std::map<std::pair<int64_t, int64_t>, std::pair<std::string, char>> stack_temp_names;  // (pc,j) -> (name, category)
    OrderedStringMap crossing_temp_types;            // порядок вставки важен - см. OrderedStringMap выше
    std::vector<std::string> warnings;
    OrderedImports imports;
    std::string ret_type;

    std::string map_type(const std::string& java_type);
    std::string simple(const std::string& dotted) const;
    std::string owner_display(const std::string& owner_internal);
    std::string field_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const;
    std::string method_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const;
    std::string new_temp(char category);
    std::string stack_temp_for(int64_t pc, int64_t j, char category);
    LocalInfo& local(int idx, char category);

private:
    std::map<int, std::string> lvt_by_slot_;
    std::set<std::string> used_local_names_;

    std::map<int, std::string> build_lvt_names() const;
    std::optional<std::string> lvt_name_for(int slot);
    void init_params();
};

// Результат `new X` до вызова <init>. Мутируется на месте invokespecial'ом,
// чтобы все dup-копии (тот же C++ shared_ptr - тот же объект) синхронно
// "стали" готовым вызовом - см. подробное обоснование ссылочной семантики
// в ast_nodes.hpp. Наследуется от NewObject (kind остаётся ExprKind::NewObject) -
// после инициализации неотличим от обычного NewObject для emit.cpp; различие
// нужно только ВНУТРИ этого модуля (dynamic_cast) для распознавания <init>.
class PendingNew : public NewObject {
public:
    bool initialized = false;
    explicit PendingNew(std::string type_) : NewObject(std::move(type_), {}) {}
};

struct BlockResult {
    std::vector<StmtPtr> stmts;
    std::vector<ExprPtr> exit_stack;
    std::optional<std::string> term_kind;  // "if" | "switch" | "return" | "throw"
    ExprPtr cond;
};

// underflow_missing (опционально): если задан, при недостатке значений на
// входе подставляются placeholder'ы Local("__entryN__","Object") вместо
// падения - engine.cpp использует это для обнаружения пересечения стека
// между блоками.
BlockResult simulate_block(const Block& block, const std::vector<ExprPtr>& entry_stack, MethodCtx& ctx,
                            std::vector<ExprPtr>* underflow_missing = nullptr);

// Публично (используется и в stackvm.cpp, и в engine.cpp - зеркалит явный
// `from stackvm import ..., _coerce_arg` в оригинале).
ExprPtr coerce_arg(const ExprPtr& expr, const std::string& expected_type);

}  // namespace nd
