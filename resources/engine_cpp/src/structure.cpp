// structure.cpp - см. structure.hpp. 1:1 порт structure.py.
#include "structure.hpp"

#include <algorithm>
#include <functional>
#include <regex>

namespace nd {

// ==================== negate / sentinel ====================

ExprPtr negate(const ExprPtr& cond) {
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

bool contains_local_ref_list(const std::vector<StmtPtr>& stmts, const std::string& name) {
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
    check_full_coverage(entry_pc);
    return stmts;
}

void Structurer::check_full_coverage(int64_t entry_pc) {
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
        bool trampoline = b.instrs.size() == 1 && (b.instrs[0].mnemonic == "goto" || b.instrs[0].mnemonic == "goto_w") &&
                          (!results_.count(pc) || results_.at(pc).stmts.empty());
        if (!trampoline) real_missing.insert(pc);
    }
    if (!real_missing.empty()) {
        std::string msg = "после структуризации остались недостижимые из AST, но живые по CFG блоки: [";
        bool first = true;
        for (int64_t pc : real_missing) {
            if (!first) msg += ", ";
            first = false;
            msg += std::to_string(pc);
        }
        msg += "] - похоже на потерю кода, откат на байткод";
        throw DecompileAbort(msg);
    }
}

// ---------------- core linear region scanner ----------------

std::vector<StmtPtr> Structurer::region(std::optional<int64_t> pc_opt, const std::set<int64_t>& stop_addrs) {
    std::vector<StmtPtr> out;
    std::set<int64_t> seen_here;
    while (true) {
        guard_ += 1;
        if (guard_ > 200000) throw DecompileAbort("structuring guard limit exceeded");
        if (!pc_opt.has_value() || !cfg_.blocks.count(*pc_opt) || stop_addrs.count(*pc_opt)) break;
        int64_t pc = *pc_opt;
        if (seen_here.count(pc)) throw DecompileAbort("нередуцируемый переход внутри региона");
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
        if (rit == results_.end()) throw DecompileAbort("нет результата симуляции для блока " + std::to_string(pc));
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
    throw DecompileAbort("нередуцируемый goto -> " + std::to_string(target));
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
        throw DecompileAbort("if/else-цепочка длиннее 800 уровней подряд - похоже на сгенерированную таблицу диспетчеризации");
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
        auto merge2_it = ipdom_.find(handler_pc);
        std::optional<int64_t> merge2 = (merge2_it != ipdom_.end()) ? merge2_it->second : std::nullopt;
        std::set<int64_t> local_stop = stop_addrs;
        if (merge2.has_value()) local_stop.insert(*merge2);
        auto cbody = region(handler_pc, local_stop);
        std::string var_name = "e" + std::to_string(catch_var_ctr_);
        if (!cbody.empty() && cbody[0]->kind == StmtKind::LocalDecl && is_sentinel(static_cast<LocalDecl*>(cbody[0].get())->init)) {
            var_name = static_cast<LocalDecl*>(cbody[0].get())->name;
            cbody.erase(cbody.begin());
        } else {
            rename_sentinel(cbody, var_name);
        }
        catches.push_back({disp_type, var_name, cbody});
    }

    auto overall_it = ipdom_.find(start);
    std::optional<int64_t> overall_merge = (overall_it != ipdom_.end()) ? overall_it->second : std::nullopt;
    if (overall_merge.has_value() && stop_addrs.count(*overall_merge)) overall_merge = std::nullopt;

    if (cfg_.blocks.count(end) && !stop_addrs.count(end)) {
        const Block& end_block = cfg_.blocks.at(end);
        bool is_trampoline = end_block.instrs.size() == 1 &&
                              (end_block.instrs[0].mnemonic == "goto" || end_block.instrs[0].mnemonic == "goto_w");
        std::set<int64_t> handler_pcs;
        for (auto& [ct, h] : entries) handler_pcs.insert(h);
        if (!is_trampoline && !handler_pcs.count(end)) {
            if (!overall_merge.has_value() || end < *overall_merge) overall_merge = end;
        }
    }
    last_try_merge_pc_ = overall_merge;
    auto stmt = std::make_shared<TryStmt>(body, catches, std::nullopt);
    return {stmt, overall_merge};
}

// ==================== loop beautification / постобработка ====================

namespace {

bool is_synth_temp(const std::string& name) {
    static const std::regex re(R"(^__stk\d+$)");
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
            ExprPtr target_expr;
            if (target_stmt->kind == StmtKind::ExprStmt) target_expr = static_cast<ExprStmtNode*>(target_stmt.get())->expr;
            else if (target_stmt->kind == StmtKind::ReturnStmt) target_expr = static_cast<ReturnStmt*>(target_stmt.get())->expr;
            else if (target_stmt->kind == StmtKind::ThrowStmt) target_expr = static_cast<ThrowStmt*>(target_stmt.get())->expr;
            else continue;
            if (!target_expr) continue;
            if (target_expr->kind == ExprKind::Local && static_cast<Local*>(target_expr.get())->name == tgt_name) continue;
            if (substitute_temp(target_expr, tgt_name, val)) {
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
    if (s->kind == StmtKind::WhileStmt) {
        auto w = std::static_pointer_cast<WhileStmt>(s);
        w->body = simplify_stmts(w->body);
        if (w->cond->kind == ExprKind::Const && static_cast<Const*>(w->cond.get())->literal == "true") {
            return simplify_while_true(w);
        }
        return w;
    }
    if (s->kind == StmtKind::DoWhileStmt) {
        auto* w = static_cast<DoWhileStmt*>(s.get());
        w->body = simplify_stmts(w->body);
        return s;
    }
    if (s->kind == StmtKind::ForStmt) {
        auto* f = static_cast<ForStmt*>(s.get());
        f->body = simplify_stmts(f->body);
        return s;
    }
    if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
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
        for (auto& c : sw->cases) c.body = simplify_stmts(c.body);
        return s;
    }
    if (s->kind == StmtKind::SyncStmt) {
        auto* sy = static_cast<SyncStmt*>(s.get());
        sy->body = simplify_stmts(sy->body);
        return s;
    }
    return s;
}

}  // namespace

std::vector<StmtPtr> simplify_stmts(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto& s : stmts) out.push_back(simplify_stmt(s));
    for (int iter = 0; iter < 4; ++iter) {
        out = fold_boolean_materialization(out);
        out = collapse_temp_chains(out);
        out = hoist_common_branch_tail(out);
        out = inline_single_use_temps_anywhere(out);
    }
    return out;
}

}  // namespace nd
