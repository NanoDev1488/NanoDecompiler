// structure.cpp - см. structure.hpp. 1:1 порт structure.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "structure.hpp"
#include "emit.hpp"

#include <algorithm>
#include <functional>
#include <regex>

namespace nd {

// ==================== negate / sentinel ====================

ExprPtr negate(const ExprPtr& cond) {
    if (!cond) return cond;
    if (cond->kind == ExprKind::UnOp) {
        auto* u = static_cast<UnOp*>(cond.get());
        if (u->op == "!") return u->expr;
    }
    if (cond->kind == ExprKind::BinOp) {
        auto* b = static_cast<BinOp*>(cond.get());
        static const std::map<std::string, std::string> flip = {
            {"==", "!="}, {"!=", "=="}, {"<", ">="}, {">=", "<"}, {">", "<="}, {"<=", ">"},
        };
        auto it = flip.find(b->op);
        if (it != flip.end()) return std::make_shared<BinOp>(it->second, b->left, b->right, "boolean");
        if (b->op == "&&") {
            return std::make_shared<BinOp>("||", negate(b->left), negate(b->right), "boolean");
        }
        if (b->op == "||") {
            return std::make_shared<BinOp>("&&", negate(b->left), negate(b->right), "boolean");
        }
    }
    return std::make_shared<UnOp>("!", cond, "boolean");
}

namespace {

bool is_sentinel(const ExprPtr& e) {
    return e && e->kind == ExprKind::Local && static_cast<Local*>(e.get())->name == CAUGHT_SENTINEL;
}

// ---------------- rename_local (см. _rename_local в оригинале) ----------------
// ВАЖНО: воспроизводит РОВНО те ограничения, что есть в оригинале (не
// заходит внутрь ForStmt.update - он Stmt, а не Expr, и в Python-версии
// стоит явный гейт `hasattr(v, "prec")`, отсеивающий не-Expr объекты; не
// заходит в BlockStmt.stmts - в generic-цикле оригинала проверяется имя
// атрибута "body", а у BlockStmt атрибут называется "stmts"; не заходит в
// TryStmt.finally_body - тоже не входит в список атрибутов оригинала для
// ИМЕННО этой функции). См. HANDOFF_38.

void rename_walk_expr(const ExprPtr& e, const std::string& old_name, const std::string& new_name) {
    if (!e) return;
    if (e->kind == ExprKind::Local) {
        auto* l = static_cast<Local*>(e.get());
        if (l->name == old_name) l->name = new_name;
        return;
    }
    switch (e->kind) {
        case ExprKind::FieldAccess:
            rename_walk_expr(static_cast<FieldAccess*>(e.get())->target, old_name, new_name);
            break;
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(e.get());
            rename_walk_expr(a->array, old_name, new_name);
            rename_walk_expr(a->index, old_name, new_name);
            break;
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(e.get());
            rename_walk_expr(m->target, old_name, new_name);
            for (auto& a : m->args) rename_walk_expr(a, old_name, new_name);
            break;
        }
        case ExprKind::NewObject:
            for (auto& a : static_cast<NewObject*>(e.get())->args) rename_walk_expr(a, old_name, new_name);
            break;
        case ExprKind::NewArray:
            for (auto& d : static_cast<NewArray*>(e.get())->dims) rename_walk_expr(d, old_name, new_name);
            break;
        case ExprKind::Cast:
            rename_walk_expr(static_cast<Cast*>(e.get())->expr, old_name, new_name);
            break;
        case ExprKind::InstanceOf:
            rename_walk_expr(static_cast<InstanceOf*>(e.get())->expr, old_name, new_name);
            break;
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            rename_walk_expr(b->left, old_name, new_name);
            rename_walk_expr(b->right, old_name, new_name);
            break;
        }
        case ExprKind::UnOp:
            rename_walk_expr(static_cast<UnOp*>(e.get())->expr, old_name, new_name);
            break;
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            rename_walk_expr(t->cond, old_name, new_name);
            rename_walk_expr(t->tval, old_name, new_name);
            rename_walk_expr(t->fval, old_name, new_name);
            break;
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            rename_walk_expr(a->target, old_name, new_name);
            rename_walk_expr(a->value, old_name, new_name);
            break;
        }
        default:
            break;  // Const/This/Raw/ClassLiteral/Lambda - нет обходимых детей (см. пояснение выше)
    }
}

void rename_walk_stmt(const StmtPtr& s, const std::string& old_name, const std::string& new_name) {
    if (!s) return;
    switch (s->kind) {
        case StmtKind::ExprStmt:
            rename_walk_expr(static_cast<ExprStmtNode*>(s.get())->expr, old_name, new_name);
            break;
        case StmtKind::ThrowStmt:
            rename_walk_expr(static_cast<ThrowStmt*>(s.get())->expr, old_name, new_name);
            break;
        case StmtKind::SyncStmt:
            rename_walk_expr(static_cast<SyncStmt*>(s.get())->expr, old_name, new_name);
            break;
        case StmtKind::ReturnStmt:
            rename_walk_expr(static_cast<ReturnStmt*>(s.get())->expr, old_name, new_name);
            break;
        case StmtKind::IfStmt:
            rename_walk_expr(static_cast<IfStmt*>(s.get())->cond, old_name, new_name);
            break;
        case StmtKind::WhileStmt:
            rename_walk_expr(static_cast<WhileStmt*>(s.get())->cond, old_name, new_name);
            break;
        case StmtKind::DoWhileStmt:
            rename_walk_expr(static_cast<DoWhileStmt*>(s.get())->cond, old_name, new_name);
            break;
        case StmtKind::ForStmt:
            rename_walk_expr(static_cast<ForStmt*>(s.get())->init, old_name, new_name);
            rename_walk_expr(static_cast<ForStmt*>(s.get())->cond, old_name, new_name);
            // update - НЕ трогаем (см. комментарий выше)
            break;
        case StmtKind::LocalDecl:
            rename_walk_expr(static_cast<LocalDecl*>(s.get())->init, old_name, new_name);
            break;
        case StmtKind::SwitchStmt:
            rename_walk_expr(static_cast<SwitchStmt*>(s.get())->selector, old_name, new_name);
            break;
        default:
            break;
    }
    switch (s->kind) {
        case StmtKind::IfStmt: {
            auto* i = static_cast<IfStmt*>(s.get());
            for (auto& sub : i->then_body) rename_walk_stmt(sub, old_name, new_name);
            if (i->else_body.has_value()) {
                for (auto& sub : *i->else_body) rename_walk_stmt(sub, old_name, new_name);
            }
            break;
        }
        case StmtKind::WhileStmt:
            for (auto& sub : static_cast<WhileStmt*>(s.get())->body) rename_walk_stmt(sub, old_name, new_name);
            break;
        case StmtKind::DoWhileStmt:
            for (auto& sub : static_cast<DoWhileStmt*>(s.get())->body) rename_walk_stmt(sub, old_name, new_name);
            break;
        case StmtKind::ForStmt:
            for (auto& sub : static_cast<ForStmt*>(s.get())->body) rename_walk_stmt(sub, old_name, new_name);
            break;
        case StmtKind::TryStmt:
            for (auto& sub : static_cast<TryStmt*>(s.get())->body) rename_walk_stmt(sub, old_name, new_name);
            break;
        case StmtKind::SyncStmt:
            for (auto& sub : static_cast<SyncStmt*>(s.get())->body) rename_walk_stmt(sub, old_name, new_name);
            break;
        default:
            break;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
            for (auto& sub : c.body) rename_walk_stmt(sub, old_name, new_name);
        }
    }
    if (s->kind == StmtKind::TryStmt) {
        for (auto& c : static_cast<TryStmt*>(s.get())->catches) {
            for (auto& sub : c.body) rename_walk_stmt(sub, old_name, new_name);
        }
    }
}

void rename_local(std::vector<StmtPtr>& stmts, const std::string& old_name, const std::string& new_name) {
    for (auto& s : stmts) rename_walk_stmt(s, old_name, new_name);
}

void rename_sentinel(std::vector<StmtPtr>& stmts, const std::string& new_name) {
    rename_local(stmts, CAUGHT_SENTINEL, new_name);
}

// ---------------- contains_local_ref (полностью общий обход Expr+Stmt) ----------------
// Собственный, БОЛЕЕ ШИРОКИЙ список атрибутов (включает "finally_body",
// заходит в ForStmt.update ЧЕРЕЗ вложенный ExprStmtNode.expr, т.к. не имеет
// гейта "только Expr") - сознательно ОТДЕЛЬНАЯ функция от rename_walk_*,
// не общий код - см. пояснение в HANDOFF_38.

bool contains_local_ref_expr(const ExprPtr& e, const std::string& name);
bool contains_local_ref_stmt(const StmtPtr& s, const std::string& name);

bool contains_local_ref_expr(const ExprPtr& e, const std::string& name) {
    if (!e) return false;
    if (e->kind == ExprKind::Local) return static_cast<Local*>(e.get())->name == name;
    switch (e->kind) {
        case ExprKind::FieldAccess:
            return contains_local_ref_expr(static_cast<FieldAccess*>(e.get())->target, name);
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(e.get());
            return contains_local_ref_expr(a->array, name) || contains_local_ref_expr(a->index, name);
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(e.get());
            if (contains_local_ref_expr(m->target, name)) return true;
            for (auto& a : m->args) if (contains_local_ref_expr(a, name)) return true;
            return false;
        }
        case ExprKind::NewObject:
            for (auto& a : static_cast<NewObject*>(e.get())->args) if (contains_local_ref_expr(a, name)) return true;
            return false;
        case ExprKind::NewArray: {
            auto* n = static_cast<NewArray*>(e.get());
            for (auto& d : n->dims) if (contains_local_ref_expr(d, name)) return true;
            return false;
        }
        case ExprKind::Cast:
            return contains_local_ref_expr(static_cast<Cast*>(e.get())->expr, name);
        case ExprKind::InstanceOf:
            return contains_local_ref_expr(static_cast<InstanceOf*>(e.get())->expr, name);
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            return contains_local_ref_expr(b->left, name) || contains_local_ref_expr(b->right, name);
        }
        case ExprKind::UnOp:
            return contains_local_ref_expr(static_cast<UnOp*>(e.get())->expr, name);
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            return contains_local_ref_expr(t->cond, name) || contains_local_ref_expr(t->tval, name) ||
                   contains_local_ref_expr(t->fval, name);
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            return contains_local_ref_expr(a->target, name) || contains_local_ref_expr(a->value, name);
        }
        default:
            return false;
    }
}

bool contains_local_ref_stmt(const StmtPtr& s, const std::string& name) {
    if (!s) return false;
    switch (s->kind) {
        case StmtKind::ExprStmt:
            if (contains_local_ref_expr(static_cast<ExprStmtNode*>(s.get())->expr, name)) return true;
            break;
        case StmtKind::ThrowStmt:
            if (contains_local_ref_expr(static_cast<ThrowStmt*>(s.get())->expr, name)) return true;
            break;
        case StmtKind::SyncStmt:
            if (contains_local_ref_expr(static_cast<SyncStmt*>(s.get())->expr, name)) return true;
            break;
        case StmtKind::ReturnStmt:
            if (contains_local_ref_expr(static_cast<ReturnStmt*>(s.get())->expr, name)) return true;
            break;
        case StmtKind::IfStmt:
            if (contains_local_ref_expr(static_cast<IfStmt*>(s.get())->cond, name)) return true;
            break;
        case StmtKind::WhileStmt:
            if (contains_local_ref_expr(static_cast<WhileStmt*>(s.get())->cond, name)) return true;
            break;
        case StmtKind::DoWhileStmt:
            if (contains_local_ref_expr(static_cast<DoWhileStmt*>(s.get())->cond, name)) return true;
            break;
        case StmtKind::ForStmt: {
            auto* f = static_cast<ForStmt*>(s.get());
            if (contains_local_ref_expr(f->init, name)) return true;
            if (contains_local_ref_expr(f->cond, name)) return true;
            if (f->update && contains_local_ref_stmt(f->update, name)) return true;  // через ExprStmtNode.expr
            break;
        }
        case StmtKind::LocalDecl:
            if (contains_local_ref_expr(static_cast<LocalDecl*>(s.get())->init, name)) return true;
            break;
        case StmtKind::SwitchStmt:
            if (contains_local_ref_expr(static_cast<SwitchStmt*>(s.get())->selector, name)) return true;
            break;
        default:
            break;
    }
    switch (s->kind) {
        case StmtKind::IfStmt: {
            auto* i = static_cast<IfStmt*>(s.get());
            for (auto& sub : i->then_body) if (contains_local_ref_stmt(sub, name)) return true;
            if (i->else_body.has_value()) {
                for (auto& sub : *i->else_body) if (contains_local_ref_stmt(sub, name)) return true;
            }
            break;
        }
        case StmtKind::WhileStmt:
            for (auto& sub : static_cast<WhileStmt*>(s.get())->body) if (contains_local_ref_stmt(sub, name)) return true;
            break;
        case StmtKind::DoWhileStmt:
            for (auto& sub : static_cast<DoWhileStmt*>(s.get())->body) if (contains_local_ref_stmt(sub, name)) return true;
            break;
        case StmtKind::ForStmt:
            for (auto& sub : static_cast<ForStmt*>(s.get())->body) if (contains_local_ref_stmt(sub, name)) return true;
            break;
        case StmtKind::TryStmt:
            for (auto& sub : static_cast<TryStmt*>(s.get())->body) if (contains_local_ref_stmt(sub, name)) return true;
            break;
        case StmtKind::SyncStmt:
            for (auto& sub : static_cast<SyncStmt*>(s.get())->body) if (contains_local_ref_stmt(sub, name)) return true;
            break;
        case StmtKind::BlockStmt:
            // BlockStmt.stmts - НЕ в списке атрибутов _contains_local_ref
            // оригинала (там тоже "body", а не "stmts") - не заходим,
            // соответствует оригиналу.
            break;
        default:
            break;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
            for (auto& sub : c.body) if (contains_local_ref_stmt(sub, name)) return true;
        }
    }
    if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        for (auto& c : t->catches) {
            for (auto& sub : c.body) if (contains_local_ref_stmt(sub, name)) return true;
        }
        if (t->finally_body.has_value()) {
            for (auto& sub : *t->finally_body) if (contains_local_ref_stmt(sub, name)) return true;
        }
    }
    return false;
}

[[maybe_unused]] bool contains_local_ref_list(const std::vector<StmtPtr>& stmts, const std::string& name) {
    for (auto& s : stmts) if (contains_local_ref_stmt(s, name)) return true;
    return false;
}

// ---------------- substitute_temp (только внутри Expr-дерева) ----------------

bool substitute_temp(const ExprPtr& node, const std::string& name, const ExprPtr& replacement) {
    if (!node) return false;
    auto try_field = [&](ExprPtr& field) -> bool {
        if (!field) return false;
        if (field->kind == ExprKind::Local && static_cast<Local*>(field.get())->name == name) {
            field = replacement;
            return true;
        }
        return substitute_temp(field, name, replacement);
    };
    switch (node->kind) {
        case ExprKind::FieldAccess:
            return try_field(static_cast<FieldAccess*>(node.get())->target);
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(node.get());
            if (try_field(a->array)) return true;
            return try_field(a->index);
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(node.get());
            if (try_field(m->target)) return true;
            for (auto& arg : m->args) if (try_field(arg)) return true;
            return false;
        }
        case ExprKind::NewObject: {
            auto* n = static_cast<NewObject*>(node.get());
            for (auto& arg : n->args) if (try_field(arg)) return true;
            return false;
        }
        case ExprKind::NewArray: {
            auto* n = static_cast<NewArray*>(node.get());
            for (auto& d : n->dims) if (try_field(d)) return true;
            return false;
        }
        case ExprKind::Cast:
            return try_field(static_cast<Cast*>(node.get())->expr);
        case ExprKind::InstanceOf:
            return try_field(static_cast<InstanceOf*>(node.get())->expr);
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(node.get());
            if (try_field(b->left)) return true;
            return try_field(b->right);
        }
        case ExprKind::UnOp:
            return try_field(static_cast<UnOp*>(node.get())->expr);
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(node.get());
            if (try_field(t->cond)) return true;
            if (try_field(t->tval)) return true;
            return try_field(t->fval);
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(node.get());
            if (try_field(a->target)) return true;
            return try_field(a->value);
        }
        default:
            return false;
    }
}

}  // namespace

// ==================== Structurer ====================

Structurer::Structurer(CFG& cfg, const std::map<int64_t, BlockResult>& block_results,
                        const std::vector<ExceptionEntry>& exceptions, MethodCtx& ctx)
    : cfg_(cfg), results_(block_results), exceptions_(exceptions), ctx_(ctx) {
    ipdom_ = cfg_.compute_postdominators();
    prepare_loops();
    prepare_try();
}

void Structurer::prepare_loops() {
    for (auto& [header, body, tails] : cfg_.natural_loops()) {
        (void)tails;
        std::set<int64_t> exits;
        for (int64_t b : body) {
            for (int64_t s : cfg_.blocks.at(b).succs) {
                if (!body.count(s)) exits.insert(s);
            }
        }
        std::optional<int64_t> exit_pc;
        if (exits.empty()) {
            exit_pc = std::nullopt;
        } else if (exits.size() == 1) {
            exit_pc = *exits.begin();
        } else {
            auto ip_it = ipdom_.find(header);
            std::optional<int64_t> ip = (ip_it != ipdom_.end()) ? ip_it->second : std::nullopt;
            if (ip.has_value() && exits.count(*ip)) {
                exit_pc = ip;
            } else {
                exit_pc = *exits.begin();  // std::set - уже отсортирован по возрастанию, begin() == min()
            }
        }
        loop_headers_[header] = {body, exit_pc};
    }
}

std::vector<Structurer::MergedExc> Structurer::merge_split_exception_ranges(const std::vector<ExceptionEntry>& exceptions) const {
    std::vector<std::pair<std::optional<std::string>, int64_t>> order;
    std::map<std::pair<std::optional<std::string>, int64_t>, std::vector<ExceptionEntry>> by_group;
    for (auto& e : exceptions) {
        auto key = std::make_pair(e.catch_type, static_cast<int64_t>(e.handler_pc));
        auto [it, inserted] = by_group.try_emplace(key);
        if (inserted) order.push_back(key);
        it->second.push_back(e);
    }
    std::vector<MergedExc> merged;
    for (auto& k : order) {
        auto es = by_group[k];
        std::stable_sort(es.begin(), es.end(), [](const ExceptionEntry& a, const ExceptionEntry& b) { return a.start_pc < b.start_pc; });
        int64_t min_start = es.front().start_pc;
        int64_t max_end = es.front().end_pc;
        for (auto& e : es) {
            min_start = std::min<int64_t>(min_start, e.start_pc);
            max_end = std::max<int64_t>(max_end, e.end_pc);
        }
        merged.push_back({min_start, max_end, k.first, k.second});
    }
    return merged;
}

void Structurer::prepare_try() {
    auto merged = merge_split_exception_ranges(exceptions_);
    std::vector<std::pair<int64_t, int64_t>> order;
    for (auto& e : merged) {
        auto key = std::make_pair(e.start_pc, e.end_pc);
        if (!try_by_key_.count(key)) order.push_back(key);
        try_by_key_[key].emplace_back(e.catch_type, e.handler_pc);
    }
    for (auto& key : order) {
        try_by_start_[key.first].push_back(key);
    }
}

// ---------------- entry point ----------------

std::vector<StmtPtr> Structurer::build(int64_t entry_pc) {
    auto stmts = region(entry_pc, {});
    recover_unconsumed_blocks(stmts, entry_pc);
    check_full_coverage(entry_pc, stmts);
    return stmts;
}

void Structurer::recover_unconsumed_blocks(std::vector<StmtPtr>& stmts, int64_t entry_pc) {
    std::set<int64_t> reachable;
    std::vector<int64_t> stack = {entry_pc};
    while (!stack.empty()) {
        int64_t b = stack.back();
        stack.pop_back();
        if (reachable.count(b) || !cfg_.blocks.count(b)) continue;
        reachable.insert(b);
        for (int64_t s : cfg_.blocks.at(b).succs) stack.push_back(s);
    }

    while (true) {
        std::set<int64_t> missing;
        for (int64_t pc : reachable) {
            if (!all_consumed_.count(pc)) missing.insert(pc);
        }
        if (missing.empty()) break;

        int64_t next_recover = -1;
        for (int64_t pc : missing) {
            const Block& b = cfg_.blocks.at(pc);
            bool has_real_stmts = false;
            if (results_.count(pc)) {
                for (auto& st : results_.at(pc).stmts) {
                    if (!st) continue;
                    if (st->kind == StmtKind::ReturnStmt && !static_cast<ReturnStmt*>(st.get())->expr) continue;
                    has_real_stmts = true;
                    break;
                }
            }
            if (has_real_stmts || (!b.instrs.empty() && b.instrs[0].mnemonic != "return" && b.instrs[0].mnemonic != "nop" && b.instrs[0].mnemonic != "goto" && b.instrs[0].mnemonic != "goto_w")) {
                next_recover = pc;
                break;
            }
        }

        if (next_recover == -1) {
            for (int64_t pc : missing) all_consumed_.insert(pc);
            break;
        }

        auto extra = region(next_recover, {});
        if (!extra.empty()) {
            stmts.insert(stmts.end(), extra.begin(), extra.end());
        }
        all_consumed_.insert(next_recover);
    }
}

void Structurer::check_full_coverage(int64_t entry_pc, std::vector<StmtPtr>& stmts) {
    std::set<int64_t> reachable;
    std::vector<int64_t> stack = {entry_pc};
    while (!stack.empty()) {
        int64_t b = stack.back();
        stack.pop_back();
        if (reachable.count(b) || !cfg_.blocks.count(b)) continue;
        reachable.insert(b);
        for (int64_t s : cfg_.blocks.at(b).succs) stack.push_back(s);
    }
    std::set<int64_t> missing;
    for (int64_t pc : reachable) {
        if (!all_consumed_.count(pc)) missing.insert(pc);
    }
    std::set<int64_t> real_missing;
    for (int64_t pc : missing) {
        const Block& b = cfg_.blocks.at(pc);
        bool empty_or_pure_jump = false;
        if (b.instrs.empty()) {
            empty_or_pure_jump = true;
        } else if (b.instrs.size() == 1) {
            const std::string& mn = b.instrs[0].mnemonic;
            if (mn == "goto" || mn == "goto_w" || mn == "nop" || mn == "return") {
                empty_or_pure_jump = true;
            }
        } else {
            bool all_nops_or_goto = true;
            for (const auto& ins : b.instrs) {
                if (ins.mnemonic != "nop" && ins.mnemonic != "goto" && ins.mnemonic != "goto_w") {
                    all_nops_or_goto = false;
                    break;
                }
            }
            if (all_nops_or_goto) empty_or_pure_jump = true;
        }
        bool has_real_stmts = false;
        if (results_.count(pc)) {
            for (auto& st : results_.at(pc).stmts) {
                if (!st) continue;
                if (st->kind == StmtKind::ReturnStmt && !static_cast<ReturnStmt*>(st.get())->expr) continue;
                has_real_stmts = true;
                break;
            }
        }
        if (!empty_or_pure_jump || has_real_stmts) {
            real_missing.insert(pc);
        }
    }
    if (!real_missing.empty()) {
        for (int64_t pc : real_missing) {
            if (results_.count(pc)) {
                for (auto& st : results_.at(pc).stmts) {
                    if (st) stmts.push_back(st);
                }
            }
            all_consumed_.insert(pc);
        }
    }
}

// ---------------- core linear region scanner ----------------

std::vector<StmtPtr> Structurer::region(std::optional<int64_t> pc_opt, const std::set<int64_t>& stop_addrs) {
    std::vector<StmtPtr> out;
    std::set<int64_t> seen_here;
    while (true) {
        guard_ += 1;
        if (guard_ > 200000) break;
        if (!pc_opt.has_value() || !cfg_.blocks.count(*pc_opt) || stop_addrs.count(*pc_opt)) break;
        int64_t pc = *pc_opt;
        if (seen_here.count(pc)) {
            out.push_back(std::make_shared<GotoStmt>("block_" + std::to_string(pc)));
            break;
        }
        seen_here.insert(pc);
        all_consumed_.insert(pc);

        if (try_by_start_.count(pc) && !consumed_try_.count(pc)) {
            auto [stmt, next_pc] = build_try(pc, stop_addrs);
            out.push_back(stmt);
            if (!next_pc.has_value()) break;
            pc_opt = next_pc;
            continue;
        }

        if (loop_headers_.count(pc) && !consumed_loop_.count(pc)) {
            auto [stmt, next_pc] = build_loop(pc, stop_addrs);
            out.push_back(stmt);
            if (!next_pc.has_value()) break;
            pc_opt = next_pc;
            continue;
        }

        const Block& block = cfg_.blocks.at(pc);
        auto rit = results_.find(pc);
        if (rit == results_.end()) {
            if (block.succs.size() == 1) {
                pc_opt = block.succs[0];
                continue;
            }
            break;
        }
        const BlockResult& res = rit->second;
        for (auto& s : res.stmts) out.push_back(s);

        if (res.term_kind == "return" || res.term_kind == "throw") break;

        if (res.term_kind == "if") {
            int64_t true_t = block.succs[0], false_t = block.succs[1];
            auto [stmt, next_pc] = build_if(pc, res.cond, true_t, false_t, stop_addrs);
            if (stmt) out.push_back(stmt);
            if (!next_pc.has_value()) break;
            pc_opt = next_pc;
            continue;
        }

        if (res.term_kind == "switch") {
            auto [stmt, next_pc] = build_switch(pc, res.cond, block, stop_addrs);
            out.push_back(stmt);
            if (!next_pc.has_value()) break;
            pc_opt = next_pc;
            continue;
        }

        const Instruction* last_ins = block.instrs.empty() ? nullptr : &block.instrs.back();
        if (last_ins && (last_ins->mnemonic == "goto" || last_ins->mnemonic == "goto_w")) {
            int64_t target = *last_ins->target;
            StmtPtr special_stmt;
            JumpKind jk = resolve_jump_stmt(target, stop_addrs, special_stmt);
            if (jk == JumpKind::NoStmt) {
                break;
            } else if (jk == JumpKind::ContinueLinearly) {
                pc_opt = target;
                continue;
            } else {
                out.push_back(special_stmt);
                break;
            }
        } else {
            if (!block.succs.empty()) {
                pc_opt = block.succs[0];
                continue;
            }
            break;
        }
    }
    return out;
}

// ---------------- jump resolution ----------------

Structurer::JumpKind Structurer::resolve_jump_stmt(int64_t target, const std::set<int64_t>& stop_addrs, StmtPtr& out_stmt) {
    for (auto it = loop_stack_.rbegin(); it != loop_stack_.rend(); ++it) {
        StackEntryPtr entry = *it;
        if (entry->header.has_value() && *entry->header == target) {
            if (entry == loop_stack_.back()) {
                out_stmt = std::make_shared<ContinueStmt>();
            } else {
                if (!entry->label.has_value()) entry->label = new_label();
                out_stmt = std::make_shared<ContinueStmt>(entry->label);
            }
            return JumpKind::Stmt;
        }
    }
    for (auto it = breakable_stack_.rbegin(); it != breakable_stack_.rend(); ++it) {
        StackEntryPtr entry = *it;
        if (entry->exit.has_value() && *entry->exit == target) {
            if (entry == breakable_stack_.back()) {
                out_stmt = std::make_shared<BreakStmt>();
            } else {
                if (!entry->label.has_value()) entry->label = new_label();
                out_stmt = std::make_shared<BreakStmt>(entry->label);
            }
            return JumpKind::Stmt;
        }
    }
    if (stop_addrs.count(target)) return JumpKind::NoStmt;
    auto tit = cfg_.blocks.find(target);
    if (tit != cfg_.blocks.end() && tit->second.instrs.size() == 1 &&
        (tit->second.instrs[0].mnemonic == "goto" || tit->second.instrs[0].mnemonic == "goto_w") &&
        (!results_.count(target) || results_.at(target).stmts.empty())) {
        return JumpKind::ContinueLinearly;
    }
    // HANDOFF: было throw DecompileAbort(...) здесь - ЛЮБОЙ нередуцируемый
    // goto откатывал ВЕСЬ метод целиком к сырому байткоду, даже если он
    // встречался в одном редком побочном ответвлении (напр. один case
    // switch'а или редкая ветка catch), а остальной метод прекрасно
    // структурировался. Полноценный node-splitting - отдельная большая
    // задача (см. HANDOFF_49), НЕ делаем это здесь наспех. Вместо этого -
    // локальная деградация: GotoStmt уже существовал в AST/emit.cpp
    // (печатается как некомпилируемый комментарий-маркер), но никогда не
    // конструировался - throw происходил раньше, чем до него доходило
    // дело. Теперь именно ЭТА ветка перехода превращается в маркер, а не
    // весь метод - при малом числе таких переходов итоговый метод почти
    // весь остаётся читаемым структурированным Java (маркер явно виден
    // и не выдаётся за компилируемый код), вместо полного отката.
    // Осторожность: не трогаем ContinueLinearly-путь и loop/break-резолвы
    // выше - только этот последний, ранее фатальный, случай.
    out_stmt = std::make_shared<GotoStmt>(std::to_string(target));
    return JumpKind::Stmt;
}

StmtPtr Structurer::try_resolve_special_target(int64_t target) {
    for (auto it = loop_stack_.rbegin(); it != loop_stack_.rend(); ++it) {
        StackEntryPtr entry = *it;
        if (entry->header.has_value() && *entry->header == target) {
            if (entry == loop_stack_.back()) return std::make_shared<ContinueStmt>();
            if (!entry->label.has_value()) entry->label = new_label();
            return std::make_shared<ContinueStmt>(entry->label);
        }
    }
    for (auto it = breakable_stack_.rbegin(); it != breakable_stack_.rend(); ++it) {
        StackEntryPtr entry = *it;
        if (entry->exit.has_value() && *entry->exit == target) {
            if (entry == breakable_stack_.back()) return std::make_shared<BreakStmt>();
            if (!entry->label.has_value()) entry->label = new_label();
            return std::make_shared<BreakStmt>(entry->label);
        }
    }
    return nullptr;
}

std::string Structurer::new_label() {
    label_ctr_ += 1;
    return "loop" + std::to_string(label_ctr_);
}

// ---------------- is_terminating / find_forward_merge ----------------

bool Structurer::is_terminating(int64_t pc, int depth, std::set<int64_t> seen) {
    auto cache_it = terminates_cache_.find(pc);
    if (cache_it != terminates_cache_.end()) return cache_it->second;
    if (depth > 300 || seen.count(pc) || !cfg_.blocks.count(pc)) {
        return !cfg_.blocks.count(pc);
    }
    seen.insert(pc);
    auto res_it = results_.find(pc);
    if (res_it == results_.end()) return false;
    const BlockResult& res = res_it->second;
    bool result;
    if (res.term_kind == "return" || res.term_kind == "throw") {
        result = true;
    } else if (res.term_kind == "if") {
        auto& succs = cfg_.blocks.at(pc).succs;
        if (succs.size() != 2) {
            result = false;
        } else {
            result = is_terminating(succs[0], depth + 1, seen) && is_terminating(succs[1], depth + 1, seen);
        }
    } else if (res.term_kind == "switch" || loop_headers_.count(pc) || try_by_start_.count(pc)) {
        result = false;
    } else {
        const Block& block = cfg_.blocks.at(pc);
        const Instruction* last = block.instrs.empty() ? nullptr : &block.instrs.back();
        if (last && (last->mnemonic == "goto" || last->mnemonic == "goto_w")) {
            result = is_terminating(*last->target, depth + 1, seen);
        } else if (!block.succs.empty()) {
            result = is_terminating(block.succs[0], depth + 1, seen);
        } else {
            result = true;
        }
    }
    if (depth == 0) terminates_cache_[pc] = result;
    return result;
}

std::optional<int64_t> Structurer::find_forward_merge(int64_t true_t, int64_t false_t, const std::set<int64_t>& stop_addrs,
                                                        bool exclude_starts) {
    auto reachable = [&](int64_t start) -> std::set<int64_t> {
        std::set<int64_t> seen;
        std::vector<int64_t> frontier = {start};
        size_t limit = 4000;
        while (!frontier.empty() && seen.size() < limit) {
            int64_t pc = frontier.back();
            frontier.pop_back();
            if (seen.count(pc) || !cfg_.blocks.count(pc)) continue;
            const Block& block = cfg_.blocks.at(pc);
            if (!block.handler_types.empty()) continue;
            seen.insert(pc);
            if (stop_addrs.count(pc)) continue;
            for (int64_t s : block.succs) {
                if (s > pc) frontier.push_back(s);
            }
        }
        return seen;
    };
    std::set<int64_t> r1 = reachable(true_t);
    std::set<int64_t> r2 = reachable(false_t);
    std::set<int64_t> common;
    std::set_intersection(r1.begin(), r1.end(), r2.begin(), r2.end(), std::inserter(common, common.begin()));
    if (exclude_starts) {
        common.erase(true_t);
        common.erase(false_t);
    }
    if (common.empty()) return std::nullopt;
    return *common.begin();
}

// ---------------- if/else ----------------

std::pair<StmtPtr, std::optional<int64_t>> Structurer::build_if(int64_t pc, ExprPtr cond, int64_t true_t, int64_t false_t,
                                                                   const std::set<int64_t>& stop_addrs) {
    if_chain_depth_ += 1;
    if (if_chain_depth_ > 800) {
        if_chain_depth_ -= 1;
        auto then_stmt = std::make_shared<GotoStmt>("block_" + std::to_string(true_t));
        auto else_stmt = std::make_shared<GotoStmt>("block_" + std::to_string(false_t));
        return {std::make_shared<IfStmt>(cond, std::vector<StmtPtr>{then_stmt}, std::vector<StmtPtr>{else_stmt}), std::nullopt};
    }
    try {
        auto result = build_if_inner(pc, cond, true_t, false_t, stop_addrs);
        if_chain_depth_ -= 1;
        return result;
    } catch (...) {
        if_chain_depth_ -= 1;
        throw;
    }
}

std::pair<StmtPtr, std::optional<int64_t>> Structurer::build_if_inner(int64_t pc, ExprPtr cond, int64_t true_t, int64_t false_t,
                                                                         const std::set<int64_t>& stop_addrs) {
    bool was_right_after_try = last_try_merge_pc_.has_value() && *last_try_merge_pc_ == pc;
    last_try_merge_pc_ = std::nullopt;

    StmtPtr sp_true = try_resolve_special_target(true_t);
    StmtPtr sp_false = try_resolve_special_target(false_t);

    if (sp_true && sp_false) {
        auto merge_it = ipdom_.find(pc);
        std::optional<int64_t> merge = (merge_it != ipdom_.end()) ? merge_it->second : std::nullopt;
        auto stmt = std::make_shared<IfStmt>(cond, std::vector<StmtPtr>{sp_true}, std::optional<std::vector<StmtPtr>>(std::vector<StmtPtr>{sp_false}));
        return {stmt, (merge.has_value() && !stop_addrs.count(*merge)) ? merge : std::nullopt};
    }
    if (sp_true) {
        auto stmt = std::make_shared<IfStmt>(cond, std::vector<StmtPtr>{sp_true}, std::nullopt);
        return {stmt, false_t};
    }
    if (sp_false) {
        auto stmt = std::make_shared<IfStmt>(negate(cond), std::vector<StmtPtr>{sp_false}, std::nullopt);
        return {stmt, true_t};
    }

    auto merge_it = ipdom_.find(pc);
    std::optional<int64_t> merge = (merge_it != ipdom_.end()) ? merge_it->second : std::nullopt;
    if (!merge.has_value()) {
        bool t_term = is_terminating(true_t, 0, {});
        bool f_term = is_terminating(false_t, 0, {});
        if (t_term && !f_term) {
            auto raw_it = ipdom_.find(false_t);
            std::optional<int64_t> raw = (raw_it != ipdom_.end()) ? raw_it->second : std::nullopt;
            if (!raw.has_value()) raw = find_forward_merge(true_t, false_t, stop_addrs, true);
            merge = raw.has_value() ? raw : std::optional<int64_t>(false_t);
        } else if (f_term && !t_term) {
            auto raw_it = ipdom_.find(true_t);
            std::optional<int64_t> raw = (raw_it != ipdom_.end()) ? raw_it->second : std::nullopt;
            if (!raw.has_value()) raw = find_forward_merge(true_t, false_t, stop_addrs, true);
            merge = raw.has_value() ? raw : std::optional<int64_t>(true_t);
        }
        if (!merge.has_value()) {
            merge = find_forward_merge(true_t, false_t, stop_addrs, false);
        }
    }
    if (was_right_after_try && merge.has_value()) {
        auto forward = find_forward_merge(true_t, false_t, stop_addrs, false);
        if (forward.has_value() && *forward < *merge) merge = forward;
    }
    if (merge.has_value()) {
        for (auto& entry : loop_stack_) {
            if (entry->header.has_value() && *entry->header == *merge) {
                merge = std::nullopt;
                break;
            }
        }
    }
    std::set<int64_t> local_stop = stop_addrs;
    if (merge.has_value()) local_stop.insert(*merge);
    auto then_body = region(true_t, local_stop);
    std::vector<StmtPtr> else_body_vec;
    bool has_else = !((merge.has_value() && false_t == *merge) || stop_addrs.count(false_t));
    std::optional<std::vector<StmtPtr>> else_body;
    if (has_else) {
        else_body = region(false_t, local_stop);
    } else {
        else_body = std::nullopt;
    }
    if (then_body.size() == 1 && else_body.has_value() && else_body->size() == 1) {
        auto* ret_then = dynamic_cast<ReturnStmt*>(then_body[0].get());
        auto* ret_else = dynamic_cast<ReturnStmt*>((*else_body)[0].get());
        if (ret_then != nullptr && ret_then->expr != nullptr &&
            ret_else != nullptr && ret_else->expr != nullptr) {
            auto* const_then = dynamic_cast<Const*>(ret_then->expr.get());
            auto* const_else = dynamic_cast<Const*>(ret_else->expr.get());
            if (const_then != nullptr && const_else != nullptr &&
                const_then->type == "boolean" && const_else->type == "boolean") {
                if (const_then->value == "true" && const_else->value == "false") {
                    return {std::make_shared<ReturnStmt>(cond), merge};
                } else if (const_then->value == "false" && const_else->value == "true") {
                    return {std::make_shared<ReturnStmt>(negate(cond)), merge};
                }
            }
        }
    }
    auto stmt = std::make_shared<IfStmt>(cond, then_body, else_body);
    return {stmt, merge};
}

// ---------------- loops ----------------

std::pair<StmtPtr, std::optional<int64_t>> Structurer::build_loop(int64_t header_pc, const std::set<int64_t>& stop_addrs) {
    auto& [body_set, exit_pc] = loop_headers_.at(header_pc);
    (void)body_set;
    consumed_loop_.insert(header_pc);
    auto entry = std::make_shared<StackEntry>();
    entry->header = header_pc;
    entry->exit = exit_pc;
    entry->label = std::nullopt;
    loop_stack_.push_back(entry);
    breakable_stack_.push_back(entry);
    std::set<int64_t> local_stop = stop_addrs;
    if (exit_pc.has_value()) local_stop.insert(*exit_pc);
    auto body = region(header_pc, local_stop);
    loop_stack_.pop_back();
    breakable_stack_.pop_back();
    auto stmt = std::make_shared<WhileStmt>(std::make_shared<Const>("true", "boolean"), body, entry->label);
    return {stmt, exit_pc};
}

// ---------------- switch ----------------

std::pair<StmtPtr, std::optional<int64_t>> Structurer::build_switch(int64_t pc, ExprPtr selector, const Block& block,
                                                                       const std::set<int64_t>& stop_addrs) {
    const Instruction& last_ins = block.instrs.back();
    const SwitchTargets& targets = *last_ins.targets;
    auto merge_it = ipdom_.find(pc);
    std::optional<int64_t> merge = (merge_it != ipdom_.end()) ? merge_it->second : std::nullopt;
    auto entry = std::make_shared<StackEntry>();
    entry->exit = merge;
    entry->label = std::nullopt;
    breakable_stack_.push_back(entry);

    std::vector<int64_t> label_order;
    std::map<int64_t, std::vector<std::string>> label_map;
    std::vector<std::pair<int64_t, int64_t>> sorted_vt;  // (value, target) отсортировано по value
    for (auto& [v, t] : targets) {
        if (v.has_value()) sorted_vt.emplace_back(*v, t);
    }
    std::sort(sorted_vt.begin(), sorted_vt.end(), [](auto& a, auto& b) { return a.first < b.first; });
    for (auto& [v, t] : sorted_vt) {
        if (!label_map.count(t)) label_order.push_back(t);
        label_map[t].push_back(std::to_string(v));
    }
    std::optional<int64_t> default_t;
    for (auto& [v, t] : targets) {
        if (!v.has_value()) { default_t = t; break; }
    }
    if (default_t.has_value()) {
        if (!label_map.count(*default_t)) label_order.push_back(*default_t);
        label_map[*default_t].push_back("default");
    }

    if (label_map.empty()) {
        breakable_stack_.pop_back();
        auto stmt = std::make_shared<SwitchStmt>(selector, std::vector<SwitchCase>{}, entry->label);
        return {stmt, merge};
    }

    std::vector<int64_t> case_addrs(label_order.begin(), label_order.end());
    std::sort(case_addrs.begin(), case_addrs.end());
    std::set<int64_t> local_stop_base = stop_addrs;
    if (merge.has_value()) local_stop_base.insert(*merge);

    std::vector<SwitchCase> cases;
    for (size_t idx = 0; idx < case_addrs.size(); ++idx) {
        int64_t addr = case_addrs[idx];
        std::optional<int64_t> next_addr = (idx + 1 < case_addrs.size()) ? std::optional<int64_t>(case_addrs[idx + 1]) : std::nullopt;
        std::set<int64_t> case_stop = local_stop_base;
        if (next_addr.has_value()) case_stop.insert(*next_addr);
        auto body = region(addr, case_stop);
        std::vector<std::string> values;
        bool is_default = false;
        for (auto& v : label_map.at(addr)) {
            if (v == "default") is_default = true;
            else values.push_back(v);
        }
        cases.push_back({values, body, is_default});
    }

    breakable_stack_.pop_back();
    auto stmt = std::make_shared<SwitchStmt>(selector, cases, entry->label);
    return {stmt, merge};
}

// ---------------- try/catch ----------------

std::pair<StmtPtr, std::optional<int64_t>> Structurer::build_try(int64_t pc, const std::set<int64_t>& stop_addrs) {
    auto key = try_by_start_.at(pc)[0];
    int64_t start = key.first, end = key.second;
    auto& entries = try_by_key_.at(key);
    consumed_try_.insert(pc);

    std::set<int64_t> body_stop = stop_addrs;
    body_stop.insert(end);
    auto body = region(start, body_stop);

    std::vector<CatchClause> catches;
    std::set<int64_t> seen_handlers;
    for (auto& [catch_type, handler_pc] : entries) {
        if (seen_handlers.count(handler_pc)) continue;
        seen_handlers.insert(handler_pc);
        catch_var_ctr_ += 1;
        std::string disp_type = catch_type.has_value() ? ctx_.owner_display(*catch_type) : "Throwable";
        // Переписано на явный if/else вместо тернарника - функционально
        // идентично, но убирает шумовой -Wmaybe-uninitialized от GCC
        // (ложное срабатывание: значение всегда либо валидное, либо
        // nullopt, но GCC не может доказать это через тернарник).
        auto merge2_it = ipdom_.find(handler_pc);
        std::optional<int64_t> merge2;
        if (merge2_it != ipdom_.end()) {
            merge2 = merge2_it->second;
        } else {
            merge2 = std::nullopt;
        }
        std::set<int64_t> local_stop = stop_addrs;
        if (merge2.has_value()) local_stop.insert(*merge2);
        auto cbody = region(handler_pc, local_stop);
        std::string var_name = "e" + std::to_string(catch_var_ctr_);
        if (!cbody.empty() && cbody[0]->kind == StmtKind::LocalDecl && is_sentinel(static_cast<LocalDecl*>(cbody[0].get())->init)) {
            std::string old_name = static_cast<LocalDecl*>(cbody[0].get())->name;
            cbody.erase(cbody.begin());
            if (old_name.rfind("var", 0) == 0 || old_name.rfind("__", 0) == 0 || old_name == "obj" || old_name == "arg") {
                rename_local(cbody, old_name, var_name);
            } else {
                var_name = old_name;
                rename_sentinel(cbody, var_name);
            }
        } else if (!cbody.empty() && cbody[0]->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(cbody[0].get());
            if (es->expr && es->expr->kind == ExprKind::Assign) {
                auto* a = static_cast<Assign*>(es->expr.get());
                if (is_sentinel(a->value) && a->target && a->target->kind == ExprKind::Local) {
                    std::string old_name = static_cast<Local*>(a->target.get())->name;
                    cbody.erase(cbody.begin());
                    if (old_name.rfind("var", 0) == 0 || old_name.rfind("__", 0) == 0 || old_name == "obj" || old_name == "arg") {
                        rename_local(cbody, old_name, var_name);
                    } else {
                        var_name = old_name;
                        rename_sentinel(cbody, var_name);
                    }
                } else {
                    rename_sentinel(cbody, var_name);
                }
            } else {
                rename_sentinel(cbody, var_name);
            }
        } else {
            rename_sentinel(cbody, var_name);
        }
        catches.push_back({disp_type, var_name, cbody});
    }

    auto overall_it = ipdom_.find(start);
    std::optional<int64_t> overall_merge = (overall_it != ipdom_.end()) ? overall_it->second : std::nullopt;
    if (overall_merge.has_value() && stop_addrs.count(*overall_merge)) overall_merge = std::nullopt;

    std::set<int64_t> handler_pcs;
    for (auto& [ct, h] : entries) handler_pcs.insert(h);

    if (cfg_.blocks.count(end) && !stop_addrs.count(end)) {
        const Block& end_block = cfg_.blocks.at(end);
        bool is_trampoline = end_block.instrs.size() == 1 &&
                              (end_block.instrs[0].mnemonic == "goto" || end_block.instrs[0].mnemonic == "goto_w");
        if (!is_trampoline && !handler_pcs.count(end)) {
            if (!overall_merge.has_value() || end < *overall_merge) overall_merge = end;
        } else if (is_trampoline && !end_block.succs.empty()) {
            int64_t target = end_block.succs[0];
            if (!stop_addrs.count(target) && !handler_pcs.count(target)) {
                if (!overall_merge.has_value() || target < *overall_merge) overall_merge = target;
            }
        }
    }

    if (!overall_merge.has_value()) {
        std::set<int64_t> candidate_merges;
        for (auto& [bpc, blk] : cfg_.blocks) {
            if (bpc >= start && bpc < end && all_consumed_.count(bpc)) {
                for (int64_t succ : blk.succs) {
                    if (!all_consumed_.count(succ) && !stop_addrs.count(succ) && !handler_pcs.count(succ) && cfg_.blocks.count(succ)) {
                        candidate_merges.insert(succ);
                    }
                }
            }
        }
        if (!candidate_merges.empty()) {
            overall_merge = *candidate_merges.begin();
        }
    }
    last_try_merge_pc_ = overall_merge;
    auto stmt = std::make_shared<TryStmt>(body, catches, std::nullopt);
    return {stmt, overall_merge};
}

// ==================== loop beautification / постобработка ====================

namespace {

bool is_synth_temp(const std::string& name) {
    static const std::regex re(R"(^__(stk|temp|cross|sb|[a-zA-Z]+)\d+$)");
    return std::regex_match(name, re);
}

std::optional<std::pair<ExprPtr, ExprPtr>> as_assign(const StmtPtr& stmt) {
    if (stmt && stmt->kind == StmtKind::ExprStmt) {
        auto* es = static_cast<ExprStmtNode*>(stmt.get());
        if (es->expr && es->expr->kind == ExprKind::Assign) {
            auto* a = static_cast<Assign*>(es->expr.get());
            return std::make_pair(a->target, a->value);
        }
    }
    return std::nullopt;
}

std::optional<int> as_bool_const(const ExprPtr& v) {
    if (v && v->kind == ExprKind::Const) {
        auto* c = static_cast<Const*>(v.get());
        if ((c->type == "int" || c->type == "boolean")) {
            if (c->literal == "0" || c->literal == "false") return 0;
            if (c->literal == "1" || c->literal == "true") return 1;
        }
    }
    return std::nullopt;
}

bool same_target(const ExprPtr& a, const ExprPtr& b) {
    if (a->kind == ExprKind::Local && b->kind == ExprKind::Local) {
        return static_cast<Local*>(a.get())->name == static_cast<Local*>(b.get())->name;
    }
    if (a->kind == ExprKind::FieldAccess && b->kind == ExprKind::FieldAccess) {
        auto* fa = static_cast<FieldAccess*>(a.get());
        auto* fb = static_cast<FieldAccess*>(b.get());
        return fa->name == fb->name && fa->is_static == fb->is_static && (fa->target == nullptr) == (fb->target == nullptr);
    }
    return false;
}

bool is_plain_break(const StmtPtr& x) {
    return x->kind == StmtKind::BreakStmt && !static_cast<BreakStmt*>(x.get())->label.has_value();
}
bool is_plain_continue(const StmtPtr& x) {
    return x->kind == StmtKind::ContinueStmt && !static_cast<ContinueStmt*>(x.get())->label.has_value();
}

bool looks_like_update(const StmtPtr& stmt) {
    if (stmt->kind != StmtKind::ExprStmt) return false;
    ExprPtr e = static_cast<ExprStmtNode*>(stmt.get())->expr;
    if (e->kind == ExprKind::UnOp) {
        auto* u = static_cast<UnOp*>(e.get());
        if (u->op == "++" || u->op == "--") return true;
    }
    return e->kind == ExprKind::Assign;
}

static bool is_same_expr(const ExprPtr& a, const ExprPtr& b) {
    if (!a && !b) return true;
    if (!a || !b) return false;
    if (a->kind != b->kind) return false;
    if (a->kind == ExprKind::Local) {
        return static_cast<Local*>(a.get())->name == static_cast<Local*>(b.get())->name;
    }
    if (a->kind == ExprKind::Const) {
        return static_cast<Const*>(a.get())->literal == static_cast<Const*>(b.get())->literal;
    }
    if (a->kind == ExprKind::FieldAccess) {
        auto* fa1 = static_cast<FieldAccess*>(a.get());
        auto* fa2 = static_cast<FieldAccess*>(b.get());
        return fa1->name == fa2->name && fa1->owner == fa2->owner && is_same_expr(fa1->target, fa2->target);
    }
    if (a->kind == ExprKind::ArrayAccess) {
        auto* aa1 = static_cast<ArrayAccess*>(a.get());
        auto* aa2 = static_cast<ArrayAccess*>(b.get());
        return is_same_expr(aa1->array, aa2->array) && is_same_expr(aa1->index, aa2->index);
    }
    if (a->kind == ExprKind::Cast) {
        auto* c1 = static_cast<Cast*>(a.get());
        auto* c2 = static_cast<Cast*>(b.get());
        return c1->type == c2->type && is_same_expr(c1->expr, c2->expr);
    }
    return false;
}

ExprPtr simplify_expr(ExprPtr e) {
    if (!e) return nullptr;
    switch (e->kind) {
        case ExprKind::FieldAccess: {
            auto* fa = static_cast<FieldAccess*>(e.get());
            if (fa->target) fa->target = simplify_expr(fa->target);
            break;
        }
        case ExprKind::ArrayAccess: {
            auto* aa = static_cast<ArrayAccess*>(e.get());
            if (aa->array) aa->array = simplify_expr(aa->array);
            if (aa->index) aa->index = simplify_expr(aa->index);
            ExprPtr arr = aa->array;
            if (arr && arr->kind == ExprKind::Cast) {
                arr = static_cast<Cast*>(arr.get())->expr;
            }
            if (arr && arr->kind == ExprKind::NewArray && aa->index && aa->index->kind == ExprKind::Const) {
                auto* na = static_cast<NewArray*>(arr.get());
                auto* idx_c = static_cast<Const*>(aa->index.get());
                if (na->initializer.has_value()) {
                    try {
                        long long idx = std::stoll(idx_c->literal);
                        if (idx >= 0 && static_cast<size_t>(idx) < na->initializer->size()) {
                            return (*na->initializer)[static_cast<size_t>(idx)];
                        }
                    } catch (...) {}
                }
            }
            break;
        }
        case ExprKind::MethodCall: {
            auto* mc = static_cast<MethodCall*>(e.get());
            if (mc->target) mc->target = simplify_expr(mc->target);
            for (auto& arg : mc->args) arg = simplify_expr(arg);

            // Inlining constant string methods used by obfuscators
            if (mc->target && mc->target->kind == ExprKind::Const) {
                auto* ct = static_cast<Const*>(mc->target.get());
                if (ct->type == "String" && ct->literal.size() >= 2 && ct->literal.front() == '"' && ct->literal.back() == '"') {
                    std::string raw_str = ct->literal.substr(1, ct->literal.size() - 2);
                    if (mc->name == "intern" && mc->args.empty()) {
                        return mc->target;
                    }
                    if (mc->name == "length" && mc->args.empty()) {
                        return std::make_shared<Const>(std::to_string(raw_str.size()), "int");
                    }
                    if (mc->name == "isEmpty" && mc->args.empty()) {
                        return std::make_shared<Const>(raw_str.empty() ? "true" : "false", "boolean");
                    }
                    if (mc->name == "charAt" && mc->args.size() == 1 && mc->args[0]->kind == ExprKind::Const) {
                        try {
                            int idx = std::stoi(static_cast<Const*>(mc->args[0].get())->literal);
                            if (idx >= 0 && (size_t)idx < raw_str.size()) {
                                std::string ch_lit = "'" + std::string(1, raw_str[idx]) + "'";
                                return std::make_shared<Const>(ch_lit, "char");
                            }
                        } catch (...) {}
                    }
                    if (mc->name == "substring" && mc->args.size() == 1 && mc->args[0]->kind == ExprKind::Const) {
                        try {
                            int idx = std::stoi(static_cast<Const*>(mc->args[0].get())->literal);
                            if (idx >= 0 && (size_t)idx <= raw_str.size()) {
                                return std::make_shared<Const>("\"" + raw_str.substr(idx) + "\"", "String");
                            }
                        } catch (...) {}
                    }
                    if (mc->name == "substring" && mc->args.size() == 2 && mc->args[0]->kind == ExprKind::Const && mc->args[1]->kind == ExprKind::Const) {
                        try {
                            int start = std::stoi(static_cast<Const*>(mc->args[0].get())->literal);
                            int end = std::stoi(static_cast<Const*>(mc->args[1].get())->literal);
                            if (start >= 0 && end >= start && (size_t)end <= raw_str.size()) {
                                return std::make_shared<Const>("\"" + raw_str.substr(start, end - start) + "\"", "String");
                            }
                        } catch (...) {}
                    }
                }
            }
            break;
        }
        case ExprKind::NewObject: {
            auto* no = static_cast<NewObject*>(e.get());
            for (auto& arg : no->args) arg = simplify_expr(arg);
            break;
        }
        case ExprKind::NewArray: {
            auto* na = static_cast<NewArray*>(e.get());
            for (auto& d : na->dims) d = simplify_expr(d);
            if (na->initializer.has_value()) {
                for (auto& v : *na->initializer) v = simplify_expr(v);
            }
            break;
        }
        case ExprKind::Cast: {
            auto* c = static_cast<Cast*>(e.get());
            if (c->expr) c->expr = simplify_expr(c->expr);
            if (c->expr && c->expr->type == c->type) return c->expr;
            if (c->expr && c->expr->kind == ExprKind::Cast) {
                auto* inner_c = static_cast<Cast*>(c->expr.get());
                if (inner_c->type == c->type) return c->expr;
            }
            break;
        }
        case ExprKind::InstanceOf: {
            auto* io = static_cast<InstanceOf*>(e.get());
            if (io->expr) io->expr = simplify_expr(io->expr);
            break;
        }
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            if (b->left) b->left = simplify_expr(b->left);
            if (b->right) b->right = simplify_expr(b->right);
            if (b->left && b->left->kind == ExprKind::Const && b->right && b->right->kind == ExprKind::Const) {
                auto* c1 = static_cast<Const*>(b->left.get());
                auto* c2 = static_cast<Const*>(b->right.get());
                bool eq = (c1->literal == c2->literal);
                if (b->op == "==") return std::make_shared<Const>(eq ? "true" : "false", "boolean");
                if (b->op == "!=") return std::make_shared<Const>(eq ? "false" : "true", "boolean");

                // Constant string folding: "hello " + "world" -> "hello world"
                if (b->op == "+" && c1->literal.size() >= 2 && c1->literal.front() == '"' && c1->literal.back() == '"' &&
                    c2->literal.size() >= 2 && c2->literal.front() == '"' && c2->literal.back() == '"') {
                    std::string s1 = c1->literal.substr(1, c1->literal.size() - 2);
                    std::string s2 = c2->literal.substr(1, c2->literal.size() - 2);
                    return std::make_shared<Const>("\"" + s1 + s2 + "\"", "String");
                }

                // Numeric constant comparisons and operations
                if (c1->literal != "true" && c1->literal != "false" && c1->literal != "null" &&
                    c2->literal != "true" && c2->literal != "false" && c2->literal != "null" &&
                    c1->literal.front() != '"' && c2->literal.front() != '"') {
                    try {
                        std::string s1 = c1->literal;
                        std::string s2 = c2->literal;
                        bool is_long = (!s1.empty() && (s1.back() == 'L' || s1.back() == 'l')) ||
                                       (!s2.empty() && (s2.back() == 'L' || s2.back() == 'l')) ||
                                       b->type == "long" || c1->type == "long" || c2->type == "long";
                        if (!s1.empty() && (s1.back() == 'L' || s1.back() == 'l')) s1.pop_back();
                        if (!s2.empty() && (s2.back() == 'L' || s2.back() == 'l')) s2.pop_back();
                        long long v1 = (s1.rfind("0x", 0) == 0 || s1.rfind("0X", 0) == 0) ? std::stoll(s1, nullptr, 16) : std::stoll(s1, nullptr, 10);
                        long long v2 = (s2.rfind("0x", 0) == 0 || s2.rfind("0X", 0) == 0) ? std::stoll(s2, nullptr, 16) : std::stoll(s2, nullptr, 10);

                        if (b->op == "<") return std::make_shared<Const>(v1 < v2 ? "true" : "false", "boolean");
                        if (b->op == "<=") return std::make_shared<Const>(v1 <= v2 ? "true" : "false", "boolean");
                        if (b->op == ">") return std::make_shared<Const>(v1 > v2 ? "true" : "false", "boolean");
                        if (b->op == ">=") return std::make_shared<Const>(v1 >= v2 ? "true" : "false", "boolean");

                        if (b->op == "+") {
                            long long res = is_long ? (v1 + v2) : (int32_t)((int32_t)v1 + (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "-") {
                            long long res = is_long ? (v1 - v2) : (int32_t)((int32_t)v1 - (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "*") {
                            long long res = is_long ? (v1 * v2) : (int32_t)((int32_t)v1 * (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "/" && v2 != 0) {
                            long long res = is_long ? (v1 / v2) : (int32_t)((int32_t)v1 / (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "%" && v2 != 0) {
                            long long res = is_long ? (v1 % v2) : (int32_t)((int32_t)v1 % (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "&") {
                            long long res = is_long ? (v1 & v2) : (int32_t)((int32_t)v1 & (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "|") {
                            long long res = is_long ? (v1 | v2) : (int32_t)((int32_t)v1 | (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "^") {
                            long long res = is_long ? (v1 ^ v2) : (int32_t)((int32_t)v1 ^ (int32_t)v2);
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == "<<") {
                            long long res = is_long ? (v1 << (v2 & 63)) : (int32_t)((int32_t)v1 << (v2 & 31));
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == ">>") {
                            long long res = is_long ? (v1 >> (v2 & 63)) : (int32_t)((int32_t)v1 >> (v2 & 31));
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                        if (b->op == ">>>") {
                            long long res = is_long ? (long long)((uint64_t)v1 >> (v2 & 63)) : (int32_t)((uint32_t)v1 >> (v2 & 31));
                            return std::make_shared<Const>(std::to_string(res) + (is_long ? "L" : ""), is_long ? "long" : "int");
                        }
                    } catch (...) {}
                }
            }
            if (is_same_expr(b->left, b->right)) {
                if (b->op == "==") return std::make_shared<Const>("true", "boolean");
                if (b->op == "!=") return std::make_shared<Const>("false", "boolean");
                if (b->op == "<=" || b->op == ">=") return std::make_shared<Const>("true", "boolean");
                if (b->op == "<" || b->op == ">") return std::make_shared<Const>("false", "boolean");
                if (b->op == "^" || b->op == "-") return std::make_shared<Const>("0", b->type);
                if (b->op == "&" || b->op == "|") return b->left;
            }
            if (b->op == "^") {
                if (b->left && b->left->kind == ExprKind::BinOp) {
                    auto* bl = static_cast<BinOp*>(b->left.get());
                    if (bl->op == "^") {
                        if (is_same_expr(bl->right, b->right)) return bl->left;
                        if (is_same_expr(bl->left, b->right)) return bl->right;
                    }
                }
                if (b->right && b->right->kind == ExprKind::BinOp) {
                    auto* br = static_cast<BinOp*>(b->right.get());
                    if (br->op == "^") {
                        if (is_same_expr(br->right, b->left)) return br->left;
                        if (is_same_expr(br->left, b->left)) return br->right;
                    }
                }
            }
            if (b->op == "==") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "true") return b->left;
                    if (c->literal == "false") return negate(b->left);
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "true") return b->right;
                    if (c->literal == "false") return negate(b->right);
                }
            }
            if (b->op == "!=") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "true") return negate(b->left);
                    if (c->literal == "false") return b->left;
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "true") return negate(b->right);
                    if (c->literal == "false") return b->right;
                }
            }
            if (b->op == "&") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "0") return std::make_shared<Const>("0", b->type);
                    if (c->literal == "-1") return b->left;
                    if (c->literal == "255") { c->literal = "0xFF"; c->value = "0xFF"; c->val = "0xFF"; }
                    else if (c->literal == "65535") { c->literal = "0xFFFF"; c->value = "0xFFFF"; c->val = "0xFFFF"; }
                    else if (c->literal == "16777215") { c->literal = "0xFFFFFF"; c->value = "0xFFFFFF"; c->val = "0xFFFFFF"; }
                    else if (c->literal == "65280") { c->literal = "0xFF00"; c->value = "0xFF00"; c->val = "0xFF00"; }
                    else if (c->literal == "4294967295L") { c->literal = "0xFFFFFFFFL"; c->value = "0xFFFFFFFFL"; c->val = "0xFFFFFFFFL"; }
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "0") return std::make_shared<Const>("0", b->type);
                    if (c->literal == "-1") return b->right;
                    if (c->literal == "255") { c->literal = "0xFF"; c->value = "0xFF"; c->val = "0xFF"; }
                    else if (c->literal == "65535") { c->literal = "0xFFFF"; c->value = "0xFFFF"; c->val = "0xFFFF"; }
                }
            }
            if (b->op == "|" || b->op == "^") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "0") return b->left;
                    if (c->literal == "255") { c->literal = "0xFF"; c->value = "0xFF"; c->val = "0xFF"; }
                    else if (c->literal == "65535") { c->literal = "0xFFFF"; c->value = "0xFFFF"; c->val = "0xFFFF"; }
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "0") return b->right;
                    if (c->literal == "255") { c->literal = "0xFF"; c->value = "0xFF"; c->val = "0xFF"; }
                    else if (c->literal == "65535") { c->literal = "0xFFFF"; c->value = "0xFFFF"; c->val = "0xFFFF"; }
                }
            }
            if (b->op == "<<" || b->op == ">>" || b->op == ">>>") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "0") return b->left;
                }
            }
            if (b->op == "+") {
                if (b->right && b->right->kind == ExprKind::Const && static_cast<Const*>(b->right.get())->literal == "0" && b->type != "String") {
                    return b->left;
                }
                if (b->left && b->left->kind == ExprKind::Const && static_cast<Const*>(b->left.get())->literal == "0" && b->type != "String") {
                    return b->right;
                }
            }
            if (b->op == "-") {
                if (b->right && b->right->kind == ExprKind::Const && static_cast<Const*>(b->right.get())->literal == "0") {
                    return b->left;
                }
            }
            if (b->op == "*") {
                if (b->right && b->right->kind == ExprKind::Const && static_cast<Const*>(b->right.get())->literal == "1") return b->left;
                if (b->left && b->left->kind == ExprKind::Const && static_cast<Const*>(b->left.get())->literal == "1") return b->right;
            }
            if (b->op == "/") {
                if (b->right && b->right->kind == ExprKind::Const && static_cast<Const*>(b->right.get())->literal == "1") return b->left;
            }
            if (b->op == "&&") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "true") return b->left;
                    if (c->literal == "false") return std::make_shared<Const>("false", "boolean");
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "true") return b->right;
                    if (c->literal == "false") return std::make_shared<Const>("false", "boolean");
                }
            }
            if (b->op == "||") {
                if (b->right && b->right->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->right.get());
                    if (c->literal == "false") return b->left;
                    if (c->literal == "true") return std::make_shared<Const>("true", "boolean");
                }
                if (b->left && b->left->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(b->left.get());
                    if (c->literal == "false") return b->right;
                    if (c->literal == "true") return std::make_shared<Const>("true", "boolean");
                }
            }
            break;
        }
        case ExprKind::UnOp: {
            auto* u = static_cast<UnOp*>(e.get());
            if (u->expr) u->expr = simplify_expr(u->expr);
            if (u->op == "!" && u->expr && u->expr->kind == ExprKind::UnOp) {
                auto* inner_u = static_cast<UnOp*>(u->expr.get());
                if (inner_u->op == "!") return inner_u->expr;
            }
            if (u->op == "~" && u->expr && u->expr->kind == ExprKind::UnOp) {
                auto* inner_u = static_cast<UnOp*>(u->expr.get());
                if (inner_u->op == "~") return inner_u->expr;
            }
            if (u->op == "-" && u->expr && u->expr->kind == ExprKind::UnOp) {
                auto* inner_u = static_cast<UnOp*>(u->expr.get());
                if (inner_u->op == "-") return inner_u->expr;
            }
            if (u->expr && u->expr->kind == ExprKind::Const) {
                auto* c = static_cast<Const*>(u->expr.get());
                if (u->op == "!") {
                    if (c->literal == "true") return std::make_shared<Const>("false", "boolean");
                    if (c->literal == "false") return std::make_shared<Const>("true", "boolean");
                } else if ((u->op == "~" || u->op == "-") && c->literal != "true" && c->literal != "false" &&
                           c->literal != "null" && (c->literal.empty() || c->literal.front() != '"')) {
                    try {
                        std::string lit = c->literal;
                        bool is_long = (!lit.empty() && (lit.back() == 'L' || lit.back() == 'l')) || u->type == "long" || c->type == "long";
                        if (!lit.empty() && (lit.back() == 'L' || lit.back() == 'l')) lit.pop_back();
                        long long val = (lit.rfind("0x", 0) == 0 || lit.rfind("0X", 0) == 0)
                                            ? std::stoll(lit, nullptr, 16)
                                            : std::stoll(lit, nullptr, 10);
                        if (u->op == "~") val = ~val;
                        else if (u->op == "-") val = -val;
                        if (is_long) {
                            return std::make_shared<Const>(std::to_string(val) + "L", "long");
                        } else {
                            return std::make_shared<Const>(std::to_string((int32_t)val), "int");
                        }
                    } catch (...) {}
                }
            }
            break;
        }
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            if (t->cond) t->cond = simplify_expr(t->cond);
            if (t->tval) t->tval = simplify_expr(t->tval);
            if (t->fval) t->fval = simplify_expr(t->fval);
            if (t->tval && t->tval->kind == ExprKind::Const && t->fval && t->fval->kind == ExprKind::Const) {
                auto* c1 = static_cast<Const*>(t->tval.get());
                auto* c2 = static_cast<Const*>(t->fval.get());
                if (c1->literal == "true" && c2->literal == "false") return t->cond;
                if (c1->literal == "false" && c2->literal == "true") return negate(t->cond);
            }
            break;
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            if (a->target) a->target = simplify_expr(a->target);
            if (a->value) a->value = simplify_expr(a->value);
            break;
        }
        default:
            break;
    }
    return e;
}

std::vector<StmtPtr> fold_boolean_materialization(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) {
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (!i->then_body.empty() && i->then_body.size() == 1 && i->else_body.has_value() && i->else_body->size() == 1) {
                ExprPtr v1, v2, tgt1, tgt2;
                auto a1 = as_assign(i->then_body[0]);
                auto a2 = as_assign((*i->else_body)[0]);
                if (a1.has_value() && a2.has_value()) {
                    tgt1 = a1->first; v1 = a1->second;
                    tgt2 = a2->first; v2 = a2->second;
                    if (same_target(tgt1, tgt2)) {
                        auto b1 = as_bool_const(v1), b2 = as_bool_const(v2);
                        if (b1.has_value() && b2.has_value() && ((*b1 == 0 && *b2 == 1) || (*b1 == 1 && *b2 == 0))) {
                            ExprPtr cond = (*b1 == 1) ? i->cond : negate(i->cond);
                            out.push_back(std::make_shared<ExprStmtNode>(std::make_shared<Assign>(tgt1, cond)));
                            continue;
                        }
                        std::string t1_type = v1->type;
                        std::string t2_type = v2->type;
                        if (t1_type == "int" && as_bool_const(v1).has_value() && t2_type == "boolean") {
                            auto* c1 = static_cast<Const*>(v1.get());
                            v1 = std::make_shared<Const>(c1->literal == "0" ? "false" : "true", "boolean");
                            t1_type = "boolean";
                        } else if (t2_type == "int" && as_bool_const(v2).has_value() && t1_type == "boolean") {
                            auto* c2 = static_cast<Const*>(v2.get());
                            v2 = std::make_shared<Const>(c2->literal == "0" ? "false" : "true", "boolean");
                            t2_type = "boolean";
                        }
                        std::string result_type;
                        if (!t1_type.empty() && !PSEUDO_TYPES.count(t1_type)) {
                            result_type = t1_type;
                        } else if (!t2_type.empty() && !PSEUDO_TYPES.count(t2_type)) {
                            result_type = t2_type;
                        } else {
                            std::string tgt_type = tgt1->type;
                            result_type = (!tgt_type.empty() && !PSEUDO_TYPES.count(tgt_type)) ? tgt_type : "Object";
                        }
                        out.push_back(std::make_shared<ExprStmtNode>(
                            std::make_shared<Assign>(tgt1, std::make_shared<Ternary>(i->cond, v1, v2, result_type))));
                        continue;
                    }
                }
            }
        }
        out.push_back(s);
    }
    return out;
}

std::vector<StmtPtr> collapse_temp_chains(std::vector<StmtPtr> stmts) {
    bool changed = true;
    while (changed) {
        changed = false;
        size_t n = stmts.size();
        for (size_t i = 0; i < n; ++i) {
            auto a = as_assign(stmts[i]);
            if (!a.has_value()) continue;
            ExprPtr tgt = a->first, val = a->second;
            if (!(tgt->kind == ExprKind::Local && is_synth_temp(static_cast<Local*>(tgt.get())->name))) continue;
            std::string tgt_name = static_cast<Local*>(tgt.get())->name;
            std::vector<size_t> uses;
            for (size_t j = i + 1; j < n; ++j) {
                if (contains_local_ref_stmt(stmts[j], tgt_name)) uses.push_back(j);
            }
            if (uses.size() != 1) continue;
            size_t j = uses[0];
            auto b = as_assign(stmts[j]);
            if (!b.has_value()) continue;
            ExprPtr tgt2 = b->first, val2 = b->second;
            if (!(val2->kind == ExprKind::Local && static_cast<Local*>(val2.get())->name == tgt_name)) continue;
            std::vector<StmtPtr> new_stmts;
            new_stmts.insert(new_stmts.end(), stmts.begin(), stmts.begin() + i);
            new_stmts.insert(new_stmts.end(), stmts.begin() + i + 1, stmts.begin() + j);
            new_stmts.push_back(std::make_shared<ExprStmtNode>(std::make_shared<Assign>(tgt2, val)));
            new_stmts.insert(new_stmts.end(), stmts.begin() + j + 1, stmts.end());
            stmts = std::move(new_stmts);
            changed = true;
            break;
        }
    }
    return stmts;
}

std::vector<StmtPtr> hoist_common_branch_tail(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) {
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (!i->then_body.empty() && i->else_body.has_value() && !i->else_body->empty()) {
                std::vector<StmtPtr> tb = i->then_body, eb = *i->else_body;
                std::vector<StmtPtr> tail;
                while (!tb.empty() && !eb.empty()) {
                    auto a1 = as_assign(tb.back());
                    auto a2 = as_assign(eb.back());
                    if (!a1.has_value() || !a2.has_value()) break;
                    ExprPtr t1 = a1->first, v1 = a1->second;
                    ExprPtr t2 = a2->first, v2 = a2->second;
                    if (t1->kind == ExprKind::Local && t2->kind == ExprKind::Local &&
                        static_cast<Local*>(t1.get())->name == static_cast<Local*>(t2.get())->name &&
                        v1->kind == ExprKind::Local && v2->kind == ExprKind::Local &&
                        static_cast<Local*>(v1.get())->name == static_cast<Local*>(v2.get())->name) {
                        tail.push_back(tb.back());
                        tb.pop_back();
                        eb.pop_back();
                        continue;
                    }
                    break;
                }
                if (!tail.empty()) {
                    i->then_body = tb;
                    i->else_body = eb;
                    out.push_back(s);
                    for (auto it = tail.rbegin(); it != tail.rend(); ++it) out.push_back(*it);
                    continue;
                }
            }
        }
        out.push_back(s);
    }
    return out;
}

bool is_unconditional_exit(const StmtPtr& s) {
    if (!s) return false;
    return s->kind == StmtKind::ReturnStmt || s->kind == StmtKind::ThrowStmt;
}

bool block_always_exits(const std::vector<StmtPtr>& block) {
    if (block.empty()) return false;
    return is_unconditional_exit(block.back());
}

std::vector<StmtPtr> eliminate_redundant_else_after_return(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) {
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (i->else_body.has_value() && !i->else_body->empty() && block_always_exits(i->then_body)) {
                std::vector<StmtPtr> eb = *i->else_body;
                i->else_body = std::nullopt;
                out.push_back(s);
                for (auto& es : eb) out.push_back(es);
                continue;
            }
        }
        out.push_back(s);
    }
    return out;
}

bool substitute_expr_or_root(ExprPtr& expr, const std::string& name, const ExprPtr& replacement) {
    if (!expr) return false;
    if (expr->kind == ExprKind::Local && static_cast<Local*>(expr.get())->name == name) {
        expr = replacement;
        return true;
    }
    return substitute_temp(expr, name, replacement);
}

bool substitute_temp_in_stmt(const StmtPtr& stmt, const std::string& name, const ExprPtr& replacement) {
    if (!stmt) return false;
    if (stmt->kind == StmtKind::ExprStmt) {
        auto* es = static_cast<ExprStmtNode*>(stmt.get());
        return substitute_expr_or_root(es->expr, name, replacement);
    }
    if (stmt->kind == StmtKind::ReturnStmt) {
        auto* r = static_cast<ReturnStmt*>(stmt.get());
        return substitute_expr_or_root(r->expr, name, replacement);
    }
    if (stmt->kind == StmtKind::ThrowStmt) {
        auto* t = static_cast<ThrowStmt*>(stmt.get());
        return substitute_expr_or_root(t->expr, name, replacement);
    }
    if (stmt->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(stmt.get());
        return substitute_expr_or_root(i->cond, name, replacement);
    }
    if (stmt->kind == StmtKind::WhileStmt) {
        auto* w = static_cast<WhileStmt*>(stmt.get());
        return substitute_expr_or_root(w->cond, name, replacement);
    }
    if (stmt->kind == StmtKind::LocalDecl) {
        auto* ld = static_cast<LocalDecl*>(stmt.get());
        return substitute_expr_or_root(ld->init, name, replacement);
    }
    if (stmt->kind == StmtKind::SwitchStmt) {
        auto* sw = static_cast<SwitchStmt*>(stmt.get());
        return substitute_expr_or_root(sw->selector, name, replacement);
    }
    if (stmt->kind == StmtKind::SyncStmt) {
        auto* sy = static_cast<SyncStmt*>(stmt.get());
        return substitute_expr_or_root(sy->expr, name, replacement);
    }
    return false;
}

std::vector<StmtPtr> inline_single_use_temps_anywhere(std::vector<StmtPtr> stmts) {
    bool changed = true;
    while (changed) {
        changed = false;
        size_t n = stmts.size();
        for (size_t i = 0; i < n; ++i) {
            auto a = as_assign(stmts[i]);
            if (!a.has_value()) continue;
            ExprPtr tgt = a->first, val = a->second;
            if (!(tgt->kind == ExprKind::Local && is_synth_temp(static_cast<Local*>(tgt.get())->name))) continue;
            std::string tgt_name = static_cast<Local*>(tgt.get())->name;
            std::vector<size_t> uses;
            for (size_t j = i + 1; j < n; ++j) {
                if (contains_local_ref_stmt(stmts[j], tgt_name)) uses.push_back(j);
            }
            if (uses.size() != 1) continue;
            size_t j = uses[0];
            StmtPtr target_stmt = stmts[j];
            if (substitute_temp_in_stmt(target_stmt, tgt_name, val)) {
                std::vector<StmtPtr> new_stmts;
                new_stmts.insert(new_stmts.end(), stmts.begin(), stmts.begin() + i);
                new_stmts.insert(new_stmts.end(), stmts.begin() + i + 1, stmts.end());
                stmts = std::move(new_stmts);
                changed = true;
                break;
            }
        }
    }
    return stmts;
}

StmtPtr simplify_while_true(const std::shared_ptr<WhileStmt>& s) {
    std::vector<StmtPtr> body = s->body;
    if (!body.empty() && body[0]->kind == StmtKind::IfStmt) {
        auto* first = static_cast<IfStmt*>(body[0].get());
        if (!first->then_body.empty() && first->then_body.size() == 1 && is_plain_break(first->then_body[0]) &&
            (!first->else_body.has_value() || first->else_body->empty())) {
            s->cond = negate(first->cond);
            body.erase(body.begin());
        } else if (first->else_body.has_value() && first->else_body->size() == 1 && is_plain_break((*first->else_body)[0]) &&
                   first->then_body.empty()) {
            s->cond = first->cond;
            body.erase(body.begin());
        }
    }
    s->body = body;
    if (s->cond) s->cond = simplify_expr(s->cond);
    if (s->cond->kind == ExprKind::Const && static_cast<Const*>(s->cond.get())->literal == "true" && !body.empty()) {
        StmtPtr last = body.back();
        if (last->kind == StmtKind::IfStmt) {
            auto* li = static_cast<IfStmt*>(last.get());
            auto& tb = li->then_body;
            auto& eb = li->else_body;
            if (!tb.empty() && tb.size() == 1 && is_plain_continue(tb[0]) && eb.has_value() && eb->size() == 1 &&
                is_plain_break((*eb)[0])) {
                return std::make_shared<DoWhileStmt>(li->cond, std::vector<StmtPtr>(body.begin(), body.end() - 1), s->label);
            }
            if (eb.has_value() && eb->size() == 1 && is_plain_continue((*eb)[0]) && !tb.empty() && tb.size() == 1 &&
                is_plain_break(tb[0])) {
                return std::make_shared<DoWhileStmt>(negate(li->cond), std::vector<StmtPtr>(body.begin(), body.end() - 1), s->label);
            }
            // Паттерн 3: if (cond) break; в конце тела while(true) без else
            if (!tb.empty() && tb.size() == 1 && is_plain_break(tb[0]) && (!eb.has_value() || eb->empty())) {
                return std::make_shared<DoWhileStmt>(negate(li->cond), std::vector<StmtPtr>(body.begin(), body.end() - 1), s->label);
            }
            // Паттерн 4: if (cond) break; в конце тела while(true) в ветке else (при пустом then)
            if (tb.empty() && eb.has_value() && eb->size() == 1 && is_plain_break((*eb)[0])) {
                return std::make_shared<DoWhileStmt>(li->cond, std::vector<StmtPtr>(body.begin(), body.end() - 1), s->label);
            }
        }
    }
    bool cond_is_true = s->cond->kind == ExprKind::Const && static_cast<Const*>(s->cond.get())->literal == "true";
    if (!cond_is_true && !body.empty()) {
        StmtPtr last = body.back();
        if (looks_like_update(last)) {
            return std::make_shared<ForStmt>(nullptr, s->cond, last, std::vector<StmtPtr>(body.begin(), body.end() - 1), s->label);
        }
    }
    return s;
}

StmtPtr simplify_stmt(StmtPtr s) {
    if (!s) return s;
    if (s->kind == StmtKind::ExprStmt) {
        auto* es = static_cast<ExprStmtNode*>(s.get());
        if (es->expr) es->expr = simplify_expr(es->expr);
        return s;
    }
    if (s->kind == StmtKind::ReturnStmt) {
        auto* r = static_cast<ReturnStmt*>(s.get());
        if (r->expr) r->expr = simplify_expr(r->expr);
        return s;
    }
    if (s->kind == StmtKind::ThrowStmt) {
        auto* t = static_cast<ThrowStmt*>(s.get());
        if (t->expr) t->expr = simplify_expr(t->expr);
        return s;
    }
    if (s->kind == StmtKind::LocalDecl) {
        auto* ld = static_cast<LocalDecl*>(s.get());
        if (ld->init) ld->init = simplify_expr(ld->init);
        return s;
    }
    if (s->kind == StmtKind::WhileStmt) {
        auto w = std::static_pointer_cast<WhileStmt>(s);
        if (w->cond) w->cond = simplify_expr(w->cond);
        w->body = simplify_stmts(w->body);
        while (!w->body.empty() && is_plain_continue(w->body.back())) w->body.pop_back();
        if (w->cond->kind == ExprKind::Const && static_cast<Const*>(w->cond.get())->literal == "true") {
            return simplify_while_true(w);
        }
        if (!w->body.empty() && looks_like_update(w->body.back())) {
            StmtPtr last = w->body.back();
            return std::make_shared<ForStmt>(nullptr, w->cond, last, std::vector<StmtPtr>(w->body.begin(), w->body.end() - 1), w->label);
        }
        return w;
    }
    if (s->kind == StmtKind::DoWhileStmt) {
        auto* w = static_cast<DoWhileStmt*>(s.get());
        if (w->cond) w->cond = simplify_expr(w->cond);
        w->body = simplify_stmts(w->body);
        while (!w->body.empty() && is_plain_continue(w->body.back())) w->body.pop_back();
        return s;
    }
    if (s->kind == StmtKind::ForStmt) {
        auto* f = static_cast<ForStmt*>(s.get());
        if (f->init) f->init = simplify_expr(f->init);
        if (f->cond) f->cond = simplify_expr(f->cond);
        if (f->update && f->update->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(f->update.get());
            if (es->expr) es->expr = simplify_expr(es->expr);
        }
        f->body = simplify_stmts(f->body);
        while (!f->body.empty() && is_plain_continue(f->body.back())) f->body.pop_back();
        return s;
    }
    if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
        if (i->cond) i->cond = simplify_expr(i->cond);
        if (!i->then_body.empty()) i->then_body = simplify_stmts(i->then_body);
        if (i->else_body.has_value() && !i->else_body->empty()) i->else_body = simplify_stmts(*i->else_body);
        if (i->then_body.empty() && i->else_body.has_value() && !i->else_body->empty()) {
            i->cond = negate(i->cond);
            i->then_body = *i->else_body;
            i->else_body = std::nullopt;
        }
        return s;
    }
    if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        t->body = simplify_stmts(t->body);
        for (auto& c : t->catches) c.body = simplify_stmts(c.body);
        if (t->finally_body.has_value()) t->finally_body = simplify_stmts(*t->finally_body);
        return s;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        auto* sw = static_cast<SwitchStmt*>(s.get());
        if (sw->selector) sw->selector = simplify_expr(sw->selector);
        for (auto& c : sw->cases) c.body = simplify_stmts(c.body);
        return s;
    }
    if (s->kind == StmtKind::SyncStmt) {
        auto* sy = static_cast<SyncStmt*>(s.get());
        if (sy->expr) sy->expr = simplify_expr(sy->expr);
        sy->body = simplify_stmts(sy->body);
        return s;
    }
    return s;
}


static bool is_same_stmt(const StmtPtr& a, const StmtPtr& b) {
    if (!a && !b) return true;
    if (!a || !b) return false;
    if (a->kind != b->kind) return false;
    if (a->kind == StmtKind::ReturnStmt) {
        return is_same_expr(static_cast<ReturnStmt*>(a.get())->expr, static_cast<ReturnStmt*>(b.get())->expr);
    }
    return false;
}

std::vector<StmtPtr> collapse_nested_if_conditions(std::vector<StmtPtr> stmts) {
    bool changed = true;
    while (changed) {
        changed = false;
        for (auto& s : stmts) {
            if (s->kind == StmtKind::IfStmt) {
                auto* i = static_cast<IfStmt*>(s.get());
                if (!i->else_body.has_value() && i->then_body.size() == 1 && i->then_body[0]->kind == StmtKind::IfStmt) {
                    auto* inner = static_cast<IfStmt*>(i->then_body[0].get());
                    if (!inner->else_body.has_value()) {
                        i->cond = std::make_shared<BinOp>("&&", i->cond, inner->cond, "boolean");
                        i->then_body = inner->then_body;
                        changed = true;
                        break;
                    }
                }
            }
        }
    }
    return stmts;
}

std::vector<StmtPtr> merge_sequential_short_circuit_ifs(std::vector<StmtPtr> stmts) {
    if (stmts.size() < 2) return stmts;
    std::vector<StmtPtr> out;
    size_t idx = 0;
    while (idx < stmts.size()) {
        if (idx + 1 < stmts.size() && stmts[idx]->kind == StmtKind::IfStmt && stmts[idx + 1]->kind == StmtKind::IfStmt) {
            auto* i1 = static_cast<IfStmt*>(stmts[idx].get());
            auto* i2 = static_cast<IfStmt*>(stmts[idx + 1].get());
            if (!i1->else_body.has_value() && !i2->else_body.has_value() &&
                i1->then_body.size() == 1 && i2->then_body.size() == 1 &&
                is_same_stmt(i1->then_body[0], i2->then_body[0])) {
                auto merged_cond = std::make_shared<BinOp>("||", i1->cond, i2->cond, "boolean");
                auto merged_if = std::make_shared<IfStmt>(merged_cond, i1->then_body, std::nullopt);
                out.push_back(merged_if);
                idx += 2;
                continue;
            }
        }
        out.push_back(stmts[idx]);
        idx += 1;
    }
    return out;
}

std::vector<StmtPtr> fold_try_with_resources(std::vector<StmtPtr> stmts) {
    if (stmts.size() < 2) return stmts;
    std::vector<StmtPtr> out;
    size_t i = 0;
    while (i < stmts.size()) {
        if (i + 1 < stmts.size() && stmts[i]->kind == StmtKind::LocalDecl && stmts[i + 1]->kind == StmtKind::TryStmt) {
            auto* ld = static_cast<LocalDecl*>(stmts[i].get());
            auto* t = static_cast<TryStmt*>(stmts[i + 1].get());
            std::string var_name = ld->name;
            bool is_resource = false;
            if (t->finally_body.has_value() && !t->finally_body->empty()) {
                std::vector<StmtPtr> filtered_fb;
                for (const auto& fs : *t->finally_body) {
                    bool is_close = false;
                    if (fs->kind == StmtKind::ExprStmt) {
                        auto* es = static_cast<ExprStmtNode*>(fs.get());
                        if (es->expr && es->expr->kind == ExprKind::MethodCall) {
                            auto* mc = static_cast<MethodCall*>(es->expr.get());
                            if (mc->name == "close" && mc->target && mc->target->kind == ExprKind::Local &&
                                static_cast<Local*>(mc->target.get())->name == var_name) {
                                is_close = true;
                                is_resource = true;
                            }
                        }
                    } else if (fs->kind == StmtKind::IfStmt) {
                        auto* ifs = static_cast<IfStmt*>(fs.get());
                        if (!ifs->then_body.empty() && ifs->then_body[0]->kind == StmtKind::ExprStmt) {
                            auto* es = static_cast<ExprStmtNode*>(ifs->then_body[0].get());
                            if (es->expr && es->expr->kind == ExprKind::MethodCall) {
                                auto* mc = static_cast<MethodCall*>(es->expr.get());
                                if (mc->name == "close" && mc->target && mc->target->kind == ExprKind::Local &&
                                    static_cast<Local*>(mc->target.get())->name == var_name) {
                                    is_close = true;
                                    is_resource = true;
                                }
                            }
                        }
                    }
                    if (!is_close) {
                        filtered_fb.push_back(fs);
                    }
                }
                if (is_resource) {
                    t->resources.push_back(stmts[i]);
                    if (filtered_fb.empty()) {
                        t->finally_body = std::nullopt;
                    } else {
                        t->finally_body = filtered_fb;
                    }
                    out.push_back(stmts[i + 1]);
                    i += 2;
                    continue;
                }
            }
        }
        out.push_back(stmts[i]);
        i += 1;
    }
    return out;
}

std::vector<StmtPtr> fuse_for_initializers(std::vector<StmtPtr> stmts) {
    bool changed = true;
    while (changed) {
        changed = false;
        size_t n = stmts.size();
        for (size_t i = 0; i + 1 < n; ++i) {
            if (stmts[i + 1]->kind != StmtKind::ForStmt) continue;
            auto* f = static_cast<ForStmt*>(stmts[i + 1].get());
            if (f->init != nullptr) continue;

            std::string var_name;
            std::string var_type = "int";
            ExprPtr init_val = nullptr;
            bool is_decl = false;

            if (stmts[i]->kind == StmtKind::LocalDecl) {
                auto* ld = static_cast<LocalDecl*>(stmts[i].get());
                var_name = ld->name;
                var_type = ld->type;
                init_val = ld->init;
                is_decl = true;
            } else if (stmts[i]->kind == StmtKind::ExprStmt) {
                auto* es = static_cast<ExprStmtNode*>(stmts[i].get());
                if (es->expr && es->expr->kind == ExprKind::Assign) {
                    auto* as = static_cast<Assign*>(es->expr.get());
                    if (as->target && as->target->kind == ExprKind::Local) {
                        var_name = static_cast<Local*>(as->target.get())->name;
                        var_type = as->target->type;
                        init_val = as->value;
                        is_decl = false;
                    }
                }
            }

            if (var_name.empty()) continue;

            bool in_cond = f->cond && contains_local_ref_expr(f->cond, var_name);
            bool in_upd = f->update && contains_local_ref_stmt(f->update, var_name);
            if (!in_cond && !in_upd) continue;

            bool used_after = false;
            for (size_t j = i + 2; j < n; ++j) {
                if (contains_local_ref_stmt(stmts[j], var_name)) {
                    used_after = true;
                    break;
                }
            }

            if (is_decl && !used_after) {
                std::string init_val_str = init_val ? emit_expr(init_val) : "0";
                f->init = std::make_shared<Raw>(var_type + " " + var_name + " = " + init_val_str);
                stmts.erase(stmts.begin() + i);
                changed = true;
                break;
            } else if (!is_decl) {
                f->init = std::make_shared<Assign>(
                    std::make_shared<Local>(var_name, var_type),
                    init_val ? init_val : std::make_shared<Const>("0", "int")
                );
                stmts.erase(stmts.begin() + i);
                changed = true;
                break;
            }
        }
    }
    return stmts;
}

static bool expr_modifies_array(const ExprPtr& e, const std::string& name) {
    if (!e) return false;
    if (e->kind == ExprKind::Assign) {
        auto* as = static_cast<Assign*>(e.get());
        if (as->target && as->target->kind == ExprKind::Local && static_cast<Local*>(as->target.get())->name == name) return true;
        if (as->target && as->target->kind == ExprKind::ArrayAccess) {
            auto* aa = static_cast<ArrayAccess*>(as->target.get());
            if (aa->array && aa->array->kind == ExprKind::Local && static_cast<Local*>(aa->array.get())->name == name) return true;
        }
    }
    return false;
}

static ExprPtr fold_const_array_lookups_in_expr(ExprPtr e, const std::map<std::string, std::vector<ExprPtr>>& arrays) {
    if (!e) return e;
    if (e->kind == ExprKind::ArrayAccess) {
        auto* aa = static_cast<ArrayAccess*>(e.get());
        if (aa->array) aa->array = fold_const_array_lookups_in_expr(aa->array, arrays);
        if (aa->index) aa->index = fold_const_array_lookups_in_expr(aa->index, arrays);
        if (aa->array && aa->array->kind == ExprKind::Local && aa->index && aa->index->kind == ExprKind::Const) {
            auto* loc = static_cast<Local*>(aa->array.get());
            auto it = arrays.find(loc->name);
            if (it != arrays.end()) {
                auto* idx_c = static_cast<Const*>(aa->index.get());
                try {
                    long long idx = std::stoll(idx_c->literal);
                    if (idx >= 0 && static_cast<size_t>(idx) < it->second.size()) {
                        return it->second[static_cast<size_t>(idx)];
                    }
                } catch (...) {}
            }
        }
        return e;
    }
    if (e->kind == ExprKind::BinOp) {
        auto* b = static_cast<BinOp*>(e.get());
        if (b->left) b->left = fold_const_array_lookups_in_expr(b->left, arrays);
        if (b->right) b->right = fold_const_array_lookups_in_expr(b->right, arrays);
        return e;
    }
    if (e->kind == ExprKind::UnOp) {
        auto* u = static_cast<UnOp*>(e.get());
        if (u->expr) u->expr = fold_const_array_lookups_in_expr(u->expr, arrays);
        return e;
    }
    if (e->kind == ExprKind::Ternary) {
        auto* t = static_cast<Ternary*>(e.get());
        if (t->cond) t->cond = fold_const_array_lookups_in_expr(t->cond, arrays);
        if (t->tval) t->tval = fold_const_array_lookups_in_expr(t->tval, arrays);
        if (t->fval) t->fval = fold_const_array_lookups_in_expr(t->fval, arrays);
        return e;
    }
    if (e->kind == ExprKind::MethodCall) {
        auto* mc = static_cast<MethodCall*>(e.get());
        if (mc->target) mc->target = fold_const_array_lookups_in_expr(mc->target, arrays);
        for (auto& a : mc->args) if (a) a = fold_const_array_lookups_in_expr(a, arrays);
        return e;
    }
    if (e->kind == ExprKind::Cast) {
        auto* c = static_cast<Cast*>(e.get());
        if (c->expr) c->expr = fold_const_array_lookups_in_expr(c->expr, arrays);
        return e;
    }
    if (e->kind == ExprKind::Assign) {
        auto* as = static_cast<Assign*>(e.get());
        if (as->target) as->target = fold_const_array_lookups_in_expr(as->target, arrays);
        if (as->value) as->value = fold_const_array_lookups_in_expr(as->value, arrays);
        return e;
    }
    return e;
}

std::vector<StmtPtr> propagate_local_constant_arrays(std::vector<StmtPtr> stmts) {
    std::map<std::string, std::vector<ExprPtr>> candidate_arrays;
    for (auto& s : stmts) {
        if (s->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(s.get());
            if (ld->init && ld->init->kind == ExprKind::NewArray) {
                auto* na = static_cast<NewArray*>(ld->init.get());
                if (na->initializer.has_value() && !na->initializer->empty()) {
                    candidate_arrays[ld->name] = *na->initializer;
                }
            }
        }
    }
    if (candidate_arrays.empty()) return stmts;

    for (auto& s : stmts) {
        for (auto it = candidate_arrays.begin(); it != candidate_arrays.end(); ) {
            bool modified = false;
            if (s->kind == StmtKind::ExprStmt) {
                auto* es = static_cast<ExprStmtNode*>(s.get());
                if (expr_modifies_array(es->expr, it->first)) modified = true;
            }
            if (modified) {
                it = candidate_arrays.erase(it);
            } else {
                ++it;
            }
        }
    }
    if (candidate_arrays.empty()) return stmts;

    for (auto& s : stmts) {
        if (s->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(s.get());
            if (es->expr) es->expr = fold_const_array_lookups_in_expr(es->expr, candidate_arrays);
        } else if (s->kind == StmtKind::ReturnStmt) {
            auto* r = static_cast<ReturnStmt*>(s.get());
            if (r->expr) r->expr = fold_const_array_lookups_in_expr(r->expr, candidate_arrays);
        } else if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (i->cond) i->cond = fold_const_array_lookups_in_expr(i->cond, candidate_arrays);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            if (w->cond) w->cond = fold_const_array_lookups_in_expr(w->cond, candidate_arrays);
        } else if (s->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(s.get());
            if (ld->init) ld->init = fold_const_array_lookups_in_expr(ld->init, candidate_arrays);
        }
    }
    return stmts;
}

static ExprPtr rename_local_in_expr(ExprPtr e, const std::string& from, const std::string& to) {
    if (!e) return e;
    if (e->kind == ExprKind::Local) {
        auto* l = static_cast<Local*>(e.get());
        if (l->name == from) {
            return std::make_shared<Local>(to, l->type);
        }
        return e;
    }
    if (e->kind == ExprKind::MethodCall) {
        auto* mc = static_cast<MethodCall*>(e.get());
        ExprPtr tgt = rename_local_in_expr(mc->target, from, to);
        std::vector<ExprPtr> args;
        for (auto& a : mc->args) args.push_back(rename_local_in_expr(a, from, to));
        return std::make_shared<MethodCall>(tgt, mc->name, args, mc->type, mc->is_static, mc->owner, mc->is_ctor, mc->is_super, mc->interface);
    }
    if (e->kind == ExprKind::BinOp) {
        auto* b = static_cast<BinOp*>(e.get());
        return std::make_shared<BinOp>(b->op, rename_local_in_expr(b->left, from, to), rename_local_in_expr(b->right, from, to), b->type);
    }
    if (e->kind == ExprKind::UnOp) {
        auto* u = static_cast<UnOp*>(e.get());
        return std::make_shared<UnOp>(u->op, rename_local_in_expr(u->expr, from, to), u->type, u->postfix);
    }
    if (e->kind == ExprKind::Ternary) {
        auto* t = static_cast<Ternary*>(e.get());
        return std::make_shared<Ternary>(rename_local_in_expr(t->cond, from, to), rename_local_in_expr(t->tval, from, to), rename_local_in_expr(t->fval, from, to), t->type);
    }
    if (e->kind == ExprKind::Assign) {
        auto* a = static_cast<Assign*>(e.get());
        return std::make_shared<Assign>(rename_local_in_expr(a->target, from, to), rename_local_in_expr(a->value, from, to), a->op);
    }
    if (e->kind == ExprKind::Cast) {
        auto* c = static_cast<Cast*>(e.get());
        return std::make_shared<Cast>(c->type, rename_local_in_expr(c->expr, from, to));
    }
    return e;
}

static StmtPtr rename_local_in_stmt(StmtPtr s, const std::string& from, const std::string& to) {
    if (!s) return s;
    if (s->kind == StmtKind::ExprStmt) {
        auto* es = static_cast<ExprStmtNode*>(s.get());
        return std::make_shared<ExprStmtNode>(rename_local_in_expr(es->expr, from, to));
    }
    if (s->kind == StmtKind::ReturnStmt) {
        auto* r = static_cast<ReturnStmt*>(s.get());
        return std::make_shared<ReturnStmt>(rename_local_in_expr(r->expr, from, to));
    }
    if (s->kind == StmtKind::ThrowStmt) {
        auto* t = static_cast<ThrowStmt*>(s.get());
        return std::make_shared<ThrowStmt>(rename_local_in_expr(t->expr, from, to));
    }
    return s;
}

static std::vector<StmtPtr> clone_stmts_renaming_local(const std::vector<StmtPtr>& stmts, const std::string& from, const std::string& to) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) out.push_back(rename_local_in_stmt(s, from, to));
    return out;
}

std::vector<StmtPtr> fold_try_catches(std::vector<StmtPtr> stmts) {
    for (auto& s : stmts) {
        if (!s) continue;
        if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = fold_try_catches(t->body);
            for (auto& c : t->catches) c.body = fold_try_catches(c.body);
            if (t->finally_body.has_value()) t->finally_body = fold_try_catches(*t->finally_body);

            // 0. Neutralize fake exception trampolines: try { throw new E(); } catch (E e) { body; }
            if (t->body.size() == 1 && t->body[0]->kind == StmtKind::ThrowStmt &&
                !t->finally_body.has_value() && t->resources.empty() && t->catches.size() == 1) {
                auto* ts = static_cast<ThrowStmt*>(t->body[0].get());
                if (ts->expr && ts->expr->kind == ExprKind::NewObject) {
                    auto* no = static_cast<NewObject*>(ts->expr.get());
                    const std::string& cat_t = t->catches[0].type;
                    if (no->type == cat_t || cat_t == "Throwable" || cat_t == "Exception" || cat_t == "RuntimeException" ||
                        no->type.find("Exception") != std::string::npos || no->type.find("Error") != std::string::npos) {
                        const std::string& vname = t->catches[0].var_name;
                        std::vector<StmtPtr> unwrapped = t->catches[0].body;
                        if (!vname.empty() && contains_local_ref_list(unwrapped, vname)) {
                            unwrapped.insert(unwrapped.begin(), std::make_shared<LocalDecl>(vname, cat_t, ts->expr));
                        }
                        s = std::make_shared<BlockStmt>(unwrapped);
                        continue;
                    }
                }
            }

            // 1. Flatten single nested try without finally/resources into outer try
            if (t->body.size() == 1 && t->body[0]->kind == StmtKind::TryStmt) {
                auto* inner = static_cast<TryStmt*>(t->body[0].get());
                if (!inner->finally_body.has_value() && inner->resources.empty() && !t->finally_body.has_value()) {
                    std::vector<CatchClause> merged = inner->catches;
                    merged.insert(merged.end(), t->catches.begin(), t->catches.end());
                    t->body = inner->body;
                    t->catches = merged;
                }
            }

            // 2. Multi-catch merging: combine adjacent catches with identical body
            for (size_t i = 0; i + 1 < t->catches.size(); ) {
                auto& c1 = t->catches[i];
                auto& c2 = t->catches[i + 1];
                bool same = false;
                if (c1.body.empty() && c2.body.empty()) {
                    same = true;
                } else {
                    auto b1_lines = emit_stmts(c1.body, 0);
                    auto c2_renamed = clone_stmts_renaming_local(c2.body, c2.var_name, c1.var_name);
                    auto b2_lines = emit_stmts(c2_renamed, 0);
                    if (b1_lines == b2_lines) same = true;
                }
                if (same) {
                    c1.type = c1.type + "|" + c2.type;
                    t->catches.erase(t->catches.begin() + i + 1);
                } else {
                    ++i;
                }
            }
        } else if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            i->then_body = fold_try_catches(i->then_body);
            if (i->else_body.has_value()) i->else_body = fold_try_catches(*i->else_body);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            w->body = fold_try_catches(w->body);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* d = static_cast<DoWhileStmt*>(s.get());
            d->body = fold_try_catches(d->body);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            f->body = fold_try_catches(f->body);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            b->stmts = fold_try_catches(b->stmts);
        }
    }
    return stmts;
}

std::vector<StmtPtr> prune_opaque_branches(std::vector<StmtPtr> stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) {
        if (!s) continue;
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (i->cond) i->cond = simplify_expr(i->cond);
            i->then_body = prune_opaque_branches(i->then_body);
            if (i->else_body.has_value()) i->else_body = prune_opaque_branches(*i->else_body);
            if (i->cond && i->cond->kind == ExprKind::Const) {
                auto* c = static_cast<Const*>(i->cond.get());
                if (c->literal == "true") {
                    for (auto& bs : i->then_body) out.push_back(bs);
                    continue;
                }
                if (c->literal == "false") {
                    if (i->else_body.has_value()) {
                        for (auto& bs : *i->else_body) out.push_back(bs);
                    }
                    continue;
                }
            }
            out.push_back(s);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            if (w->cond) w->cond = simplify_expr(w->cond);
            if (w->cond && w->cond->kind == ExprKind::Const && static_cast<Const*>(w->cond.get())->literal == "false") {
                continue;
            }
            w->body = prune_opaque_branches(w->body);
            out.push_back(s);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            b->stmts = prune_opaque_branches(b->stmts);
            out.push_back(s);
        } else {
            out.push_back(s);
        }
    }
    return out;
}

std::vector<StmtPtr> unflatten_switch_dispatchers(std::vector<StmtPtr> stmts) {
    if (stmts.size() < 2) return stmts;
    std::vector<StmtPtr> out;
    size_t i = 0;
    while (i < stmts.size()) {
        if (i + 1 < stmts.size() && stmts[i]->kind == StmtKind::LocalDecl && stmts[i + 1]->kind == StmtKind::WhileStmt) {
            auto* ld = static_cast<LocalDecl*>(stmts[i].get());
            auto* w = static_cast<WhileStmt*>(stmts[i + 1].get());
            if (ld->init && ld->init->kind == ExprKind::Const && w->body.size() == 1 && w->body[0]->kind == StmtKind::SwitchStmt) {
                std::string var_name = ld->name;
                std::string curr_state = static_cast<Const*>(ld->init.get())->literal;
                auto* sw = static_cast<SwitchStmt*>(w->body[0].get());
                if (sw->selector && sw->selector->kind == ExprKind::Local && static_cast<Local*>(sw->selector.get())->name == var_name) {
                    std::map<std::string, const SwitchCase*> case_map;
                    for (const auto& c : sw->cases) {
                        for (const auto& v : c.values) {
                            case_map[v] = &c;
                        }
                    }
                    std::vector<StmtPtr> unflattened;
                    std::set<std::string> visited;
                    bool success = false;
                    while (!curr_state.empty()) {
                        if (visited.count(curr_state)) break;
                        visited.insert(curr_state);
                        auto it = case_map.find(curr_state);
                        if (it == case_map.end()) {
                            success = true;
                            break;
                        }
                        const SwitchCase* sc = it->second;
                        std::vector<StmtPtr> c_body = sc->body;
                        while (!c_body.empty() && c_body.back()->kind == StmtKind::BreakStmt) {
                            c_body.pop_back();
                        }
                        if (c_body.empty()) break;
                        if (c_body.back()->kind == StmtKind::ReturnStmt) {
                            for (auto& s_in_c : c_body) unflattened.push_back(s_in_c);
                            success = true;
                            break;
                        }
                        std::string next_state = "";
                        if (c_body.back()->kind == StmtKind::ExprStmt) {
                            auto* es = static_cast<ExprStmtNode*>(c_body.back().get());
                            if (es->expr && es->expr->kind == ExprKind::Assign) {
                                auto* as = static_cast<Assign*>(es->expr.get());
                                if (as->target && as->target->kind == ExprKind::Local &&
                                    static_cast<Local*>(as->target.get())->name == var_name &&
                                    as->value && as->value->kind == ExprKind::Const) {
                                    next_state = static_cast<Const*>(as->value.get())->literal;
                                    c_body.pop_back();
                                }
                            }
                        }
                        if (next_state.empty()) break;
                        for (auto& s_in_c : c_body) unflattened.push_back(s_in_c);
                        curr_state = next_state;
                    }
                    if (success && !unflattened.empty()) {
                        for (auto& us : unflattened) out.push_back(us);
                        i += 2;
                        continue;
                    }
                }
            }
        }
        out.push_back(stmts[i]);
        i += 1;
    }
    return out;
}

}  // namespace

std::vector<StmtPtr> simplify_stmts(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) out.push_back(simplify_stmt(s));
    for (int iter = 0; iter < 4; ++iter) {
        out = fold_boolean_materialization(out);
        out = collapse_temp_chains(out);
        out = hoist_common_branch_tail(out);
        out = eliminate_redundant_else_after_return(out);
        out = inline_single_use_temps_anywhere(out);
        out = collapse_nested_if_conditions(out);
        out = merge_sequential_short_circuit_ifs(out);
        out = fold_try_with_resources(out);
        out = fuse_for_initializers(out);
        out = propagate_local_constant_arrays(out);
        out = fold_try_catches(out);
        out = prune_opaque_branches(out);
        out = unflatten_switch_dispatchers(out);
    }
    return out;
}

}  // namespace nd

