// engine.cpp - см. engine.hpp. 1:1 порт engine.py.
#include "engine.hpp"

#include <algorithm>
#include <functional>
#include <sstream>

#include "catchclean.hpp"
#include "disassembler.hpp"
#include "emit.hpp"
#include "javatypes.hpp"
#include "structure.hpp"

namespace nd {

namespace {

// ---------------- generic expr child walker (левый-правый-и т.д. атрибуты) ----------------
// Единый набор проверяемых атрибутов Expr, используемый НЕСКОЛЬКИМИ функциями
// этого файла (`_collect_referenced_names`/`_collect_shallow_referenced_names`/
// `_prune_unused_imports`/`_count_local_uses` и т.п.) - у всех в оригинале один
// и тот же кортеж `("left","right","expr","target","value","array","index",
// "cond","tval","fval")` (+ отдельно "args"), поэтому здесь ОДНА реализация.
void walk_expr_children(const ExprPtr& e, const std::function<void(const ExprPtr&)>& visit) {
    if (!e) return;
    switch (e->kind) {
        case ExprKind::FieldAccess:
            visit(static_cast<FieldAccess*>(e.get())->target);
            break;
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(e.get());
            visit(a->array);
            visit(a->index);
            break;
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(e.get());
            visit(m->target);
            for (auto& arg : m->args) visit(arg);
            break;
        }
        case ExprKind::NewObject:
            for (auto& arg : static_cast<NewObject*>(e.get())->args) visit(arg);
            break;
        case ExprKind::NewArray:
            for (auto& d : static_cast<NewArray*>(e.get())->dims) visit(d);
            break;
        case ExprKind::Cast:
            visit(static_cast<Cast*>(e.get())->expr);
            break;
        case ExprKind::InstanceOf:
            visit(static_cast<InstanceOf*>(e.get())->expr);
            break;
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            visit(b->left);
            visit(b->right);
            break;
        }
        case ExprKind::UnOp:
            visit(static_cast<UnOp*>(e.get())->expr);
            break;
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            visit(t->cond);
            visit(t->tval);
            visit(t->fval);
            break;
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            visit(a->target);
            visit(a->value);
            break;
        }
        default:
            break;
    }
}

// ---------------- _collect_declared_names ----------------

void collect_declared_names_walk(const std::vector<StmtPtr>& lst, std::set<std::string>& names) {
    for (auto& s : lst) {
        if (!s) continue;
        if (s->kind == StmtKind::LocalDecl) names.insert(static_cast<LocalDecl*>(s.get())->name);
        switch (s->kind) {
            case StmtKind::IfStmt: {
                auto* i = static_cast<IfStmt*>(s.get());
                collect_declared_names_walk(i->then_body, names);
                if (i->else_body.has_value()) collect_declared_names_walk(*i->else_body, names);
                break;
            }
            case StmtKind::WhileStmt:
                collect_declared_names_walk(static_cast<WhileStmt*>(s.get())->body, names);
                break;
            case StmtKind::DoWhileStmt:
                collect_declared_names_walk(static_cast<DoWhileStmt*>(s.get())->body, names);
                break;
            case StmtKind::ForStmt:
                collect_declared_names_walk(static_cast<ForStmt*>(s.get())->body, names);
                break;
            case StmtKind::SyncStmt:
                collect_declared_names_walk(static_cast<SyncStmt*>(s.get())->body, names);
                break;
            case StmtKind::BlockStmt:
                collect_declared_names_walk(static_cast<BlockStmt*>(s.get())->stmts, names);
                break;
            case StmtKind::SwitchStmt:
                for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) collect_declared_names_walk(c.body, names);
                break;
            case StmtKind::TryStmt: {
                auto* t = static_cast<TryStmt*>(s.get());
                collect_declared_names_walk(t->body, names);
                for (auto& c : t->catches) collect_declared_names_walk(c.body, names);
                if (t->finally_body.has_value()) collect_declared_names_walk(*t->finally_body, names);
                break;
            }
            default:
                break;
        }
    }
}

std::set<std::string> collect_declared_names(const std::vector<StmtPtr>& stmts) {
    std::set<std::string> names;
    collect_declared_names_walk(stmts, names);
    return names;
}

// ---------------- _collect_referenced_names ----------------

void collect_referenced_names_walk_expr(const ExprPtr& e, std::set<std::string>& names) {
    if (!e) return;
    if (e->kind == ExprKind::Local) {
        names.insert(static_cast<Local*>(e.get())->name);
        return;
    }
    walk_expr_children(e, [&](const ExprPtr& c) { collect_referenced_names_walk_expr(c, names); });
}

void collect_referenced_names_walk(const std::vector<StmtPtr>& lst, std::set<std::string>& names) {
    for (auto& s : lst) {
        if (!s) continue;
        switch (s->kind) {
            case StmtKind::LocalDecl:
                collect_referenced_names_walk_expr(static_cast<LocalDecl*>(s.get())->init, names);
                break;
            case StmtKind::ExprStmt:
                collect_referenced_names_walk_expr(static_cast<ExprStmtNode*>(s.get())->expr, names);
                break;
            case StmtKind::ReturnStmt:
                collect_referenced_names_walk_expr(static_cast<ReturnStmt*>(s.get())->expr, names);
                break;
            case StmtKind::ThrowStmt:
                collect_referenced_names_walk_expr(static_cast<ThrowStmt*>(s.get())->expr, names);
                break;
            case StmtKind::IfStmt: {
                auto* i = static_cast<IfStmt*>(s.get());
                collect_referenced_names_walk_expr(i->cond, names);
                collect_referenced_names_walk(i->then_body, names);
                if (i->else_body.has_value()) collect_referenced_names_walk(*i->else_body, names);
                break;
            }
            case StmtKind::WhileStmt: {
                auto* w = static_cast<WhileStmt*>(s.get());
                collect_referenced_names_walk_expr(w->cond, names);
                collect_referenced_names_walk(w->body, names);
                break;
            }
            case StmtKind::DoWhileStmt: {
                auto* w = static_cast<DoWhileStmt*>(s.get());
                collect_referenced_names_walk_expr(w->cond, names);
                collect_referenced_names_walk(w->body, names);
                break;
            }
            case StmtKind::ForStmt: {
                auto* f = static_cast<ForStmt*>(s.get());
                collect_referenced_names_walk_expr(f->cond, names);
                collect_referenced_names_walk(f->body, names);
                break;
            }
            case StmtKind::SyncStmt: {
                auto* sy = static_cast<SyncStmt*>(s.get());
                collect_referenced_names_walk_expr(sy->expr, names);
                collect_referenced_names_walk(sy->body, names);
                break;
            }
            case StmtKind::BlockStmt:
                collect_referenced_names_walk(static_cast<BlockStmt*>(s.get())->stmts, names);
                break;
            case StmtKind::SwitchStmt: {
                auto* sw = static_cast<SwitchStmt*>(s.get());
                collect_referenced_names_walk_expr(sw->selector, names);
                for (auto& c : sw->cases) collect_referenced_names_walk(c.body, names);
                break;
            }
            case StmtKind::TryStmt: {
                auto* t = static_cast<TryStmt*>(s.get());
                collect_referenced_names_walk(t->body, names);
                for (auto& c : t->catches) collect_referenced_names_walk(c.body, names);
                if (t->finally_body.has_value()) collect_referenced_names_walk(*t->finally_body, names);
                break;
            }
            default:
                break;
        }
    }
}

[[maybe_unused]] std::set<std::string> collect_referenced_names(const std::vector<StmtPtr>& stmts) {
    std::set<std::string> names;
    collect_referenced_names_walk(stmts, names);
    return names;
}

// ---------------- _collect_shallow_referenced_names ----------------

std::set<std::string> collect_shallow_referenced_names(const std::vector<StmtPtr>& stmts) {
    std::set<std::string> names;
    for (auto& s : stmts) {
        if (!s) continue;
        switch (s->kind) {
            case StmtKind::LocalDecl:
                collect_referenced_names_walk_expr(static_cast<LocalDecl*>(s.get())->init, names);
                break;
            case StmtKind::ExprStmt:
                collect_referenced_names_walk_expr(static_cast<ExprStmtNode*>(s.get())->expr, names);
                break;
            case StmtKind::ReturnStmt:
                collect_referenced_names_walk_expr(static_cast<ReturnStmt*>(s.get())->expr, names);
                break;
            case StmtKind::ThrowStmt:
                collect_referenced_names_walk_expr(static_cast<ThrowStmt*>(s.get())->expr, names);
                break;
            case StmtKind::IfStmt: {
                // БАГ-ФИКС (см. HANDOFF_49, "реальный баг"): раньше здесь
                // смотрелось ТОЛЬКО cond, тела then/else игнорировались -
                // если escaping-переменная упоминалась исключительно внутри
                // then/else более позднего if, hoist_escaping_locals() её не
                // видел и не поднимал объявление наружу -> компилятор потом
                // не находил символ (переменная объявлена в чужой области
                // видимости). Теперь заходим и в тела веток тоже.
                auto* i = static_cast<IfStmt*>(s.get());
                collect_referenced_names_walk_expr(i->cond, names);
                auto sub_then = collect_shallow_referenced_names(i->then_body);
                names.insert(sub_then.begin(), sub_then.end());
                if (i->else_body.has_value()) {
                    auto sub_else = collect_shallow_referenced_names(*i->else_body);
                    names.insert(sub_else.begin(), sub_else.end());
                }
                break;
            }
            case StmtKind::WhileStmt: {
                auto* w = static_cast<WhileStmt*>(s.get());
                collect_referenced_names_walk_expr(w->cond, names);
                auto sub = collect_shallow_referenced_names(w->body);
                names.insert(sub.begin(), sub.end());
                break;
            }
            case StmtKind::DoWhileStmt: {
                auto* w = static_cast<DoWhileStmt*>(s.get());
                collect_referenced_names_walk_expr(w->cond, names);
                auto sub = collect_shallow_referenced_names(w->body);
                names.insert(sub.begin(), sub.end());
                break;
            }
            case StmtKind::ForStmt: {
                auto* f = static_cast<ForStmt*>(s.get());
                collect_referenced_names_walk_expr(f->cond, names);
                auto sub = collect_shallow_referenced_names(f->body);
                names.insert(sub.begin(), sub.end());
                break;
            }
            case StmtKind::SyncStmt: {
                auto* sy = static_cast<SyncStmt*>(s.get());
                collect_referenced_names_walk_expr(sy->expr, names);
                auto sub = collect_shallow_referenced_names(sy->body);
                names.insert(sub.begin(), sub.end());
                break;
            }
            case StmtKind::SwitchStmt: {
                auto* sw = static_cast<SwitchStmt*>(s.get());
                collect_referenced_names_walk_expr(sw->selector, names);
                for (auto& c : sw->cases) {
                    auto sub = collect_shallow_referenced_names(c.body);
                    names.insert(sub.begin(), sub.end());
                }
                break;
            }
            case StmtKind::BlockStmt: {
                auto sub = collect_shallow_referenced_names(static_cast<BlockStmt*>(s.get())->stmts);
                names.insert(sub.begin(), sub.end());
                break;
            }
            case StmtKind::TryStmt: {
                // Раньше только body - catch/finally тоже игнорировались,
                // та же категория бага, что и с if/while/for/switch выше.
                auto* t = static_cast<TryStmt*>(s.get());
                auto sub = collect_shallow_referenced_names(t->body);
                names.insert(sub.begin(), sub.end());
                for (auto& c : t->catches) {
                    auto sub_c = collect_shallow_referenced_names(c.body);
                    names.insert(sub_c.begin(), sub_c.end());
                }
                if (t->finally_body.has_value()) {
                    auto sub_f = collect_shallow_referenced_names(*t->finally_body);
                    names.insert(sub_f.begin(), sub_f.end());
                }
                break;
            }
            default:
                break;
        }
    }
    return names;
}

// ---------------- _inner_body_of ----------------

std::optional<std::vector<StmtPtr>> inner_body_of(const StmtPtr& s) {
    if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
        std::vector<StmtPtr> out = i->then_body;
        if (i->else_body.has_value()) out.insert(out.end(), i->else_body->begin(), i->else_body->end());
        return out;
    }
    if (s->kind == StmtKind::WhileStmt) return static_cast<WhileStmt*>(s.get())->body;
    if (s->kind == StmtKind::DoWhileStmt) return static_cast<DoWhileStmt*>(s.get())->body;
    if (s->kind == StmtKind::ForStmt) return static_cast<ForStmt*>(s.get())->body;
    if (s->kind == StmtKind::SyncStmt) return static_cast<SyncStmt*>(s.get())->body;
    if (s->kind == StmtKind::BlockStmt) return static_cast<BlockStmt*>(s.get())->stmts;
    if (s->kind == StmtKind::SwitchStmt) {
        std::vector<StmtPtr> out;
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) out.insert(out.end(), c.body.begin(), c.body.end());
        return out;
    }
    if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        std::vector<StmtPtr> out = t->body;
        for (auto& c : t->catches) out.insert(out.end(), c.body.begin(), c.body.end());
        if (t->finally_body.has_value()) out.insert(out.end(), t->finally_body->begin(), t->finally_body->end());
        return out;
    }
    return std::nullopt;
}

// ---------------- _strip_decl_to_assign ----------------

std::vector<StmtPtr> strip_decl_to_assign(const std::vector<StmtPtr>& lst, const std::set<std::string>& names,
                                           std::map<std::string, std::string>& types) {
    std::vector<StmtPtr> out;
    for (auto st : lst) {
        if (st->kind == StmtKind::LocalDecl && names.count(static_cast<LocalDecl*>(st.get())->name)) {
            auto* ld = static_cast<LocalDecl*>(st.get());
            if (!types.count(ld->name)) types[ld->name] = ld->type;
            out.push_back(std::make_shared<ExprStmtNode>(
                std::make_shared<Assign>(std::make_shared<Local>(ld->name, ld->type), ld->init)));
            continue;
        }
        if (st->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(st.get());
            i->then_body = strip_decl_to_assign(i->then_body, names, types);
            if (i->else_body.has_value()) i->else_body = strip_decl_to_assign(*i->else_body, names, types);
        } else if (st->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(st.get());
            w->body = strip_decl_to_assign(w->body, names, types);
        } else if (st->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(st.get());
            w->body = strip_decl_to_assign(w->body, names, types);
        } else if (st->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(st.get());
            f->body = strip_decl_to_assign(f->body, names, types);
        } else if (st->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(st.get());
            sy->body = strip_decl_to_assign(sy->body, names, types);
        } else if (st->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(st.get());
            b->stmts = strip_decl_to_assign(b->stmts, names, types);
        } else if (st->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(st.get());
            for (auto& c : sw->cases) c.body = strip_decl_to_assign(c.body, names, types);
        } else if (st->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(st.get());
            t->body = strip_decl_to_assign(t->body, names, types);
            for (auto& c : t->catches) c.body = strip_decl_to_assign(c.body, names, types);
            if (t->finally_body.has_value()) t->finally_body = strip_decl_to_assign(*t->finally_body, names, types);
        }
        out.push_back(st);
    }
    return out;
}

// ---------------- _hoist_escaping_locals ----------------

std::vector<StmtPtr> hoist_escaping_locals(const std::vector<StmtPtr>& stmts, std::set<std::string>& declared_so_far) {
    std::vector<StmtPtr> fixed;
    for (auto s : stmts) {
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            i->then_body = hoist_escaping_locals(i->then_body, declared_so_far);
            if (i->else_body.has_value()) i->else_body = hoist_escaping_locals(*i->else_body, declared_so_far);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            w->body = hoist_escaping_locals(w->body, declared_so_far);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            w->body = hoist_escaping_locals(w->body, declared_so_far);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            f->body = hoist_escaping_locals(f->body, declared_so_far);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            sy->body = hoist_escaping_locals(sy->body, declared_so_far);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            b->stmts = hoist_escaping_locals(b->stmts, declared_so_far);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) c.body = hoist_escaping_locals(c.body, declared_so_far);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = hoist_escaping_locals(t->body, declared_so_far);
            for (auto& c : t->catches) c.body = hoist_escaping_locals(c.body, declared_so_far);
            if (t->finally_body.has_value()) t->finally_body = hoist_escaping_locals(*t->finally_body, declared_so_far);
        }
        fixed.push_back(s);
    }

    std::vector<StmtPtr> out;
    size_t n = fixed.size();
    for (size_t idx = 0; idx < n; ++idx) {
        StmtPtr s = fixed[idx];
        auto inner = inner_body_of(s);
        if (inner.has_value() && !inner->empty()) {
            auto declared = collect_declared_names(*inner);
            std::set<std::string> escaping;
            if (!declared.empty()) {
                std::vector<StmtPtr> rest(fixed.begin() + idx + 1, fixed.end());
                auto later_refs = collect_shallow_referenced_names(rest);
                std::set_intersection(declared.begin(), declared.end(), later_refs.begin(), later_refs.end(),
                                       std::inserter(escaping, escaping.begin()));
            }
            if (!escaping.empty()) {
                std::map<std::string, std::string> types;
                if (s->kind == StmtKind::IfStmt) {
                    auto* i = static_cast<IfStmt*>(s.get());
                    i->then_body = strip_decl_to_assign(i->then_body, escaping, types);
                    if (i->else_body.has_value()) i->else_body = strip_decl_to_assign(*i->else_body, escaping, types);
                } else if (s->kind == StmtKind::WhileStmt) {
                    auto* w = static_cast<WhileStmt*>(s.get());
                    w->body = strip_decl_to_assign(w->body, escaping, types);
                } else if (s->kind == StmtKind::DoWhileStmt) {
                    auto* w = static_cast<DoWhileStmt*>(s.get());
                    w->body = strip_decl_to_assign(w->body, escaping, types);
                } else if (s->kind == StmtKind::ForStmt) {
                    auto* f = static_cast<ForStmt*>(s.get());
                    f->body = strip_decl_to_assign(f->body, escaping, types);
                } else if (s->kind == StmtKind::SyncStmt) {
                    auto* sy = static_cast<SyncStmt*>(s.get());
                    sy->body = strip_decl_to_assign(sy->body, escaping, types);
                } else if (s->kind == StmtKind::BlockStmt) {
                    auto* b = static_cast<BlockStmt*>(s.get());
                    b->stmts = strip_decl_to_assign(b->stmts, escaping, types);
                } else if (s->kind == StmtKind::SwitchStmt) {
                    auto* sw = static_cast<SwitchStmt*>(s.get());
                    for (auto& c : sw->cases) c.body = strip_decl_to_assign(c.body, escaping, types);
                } else if (s->kind == StmtKind::TryStmt) {
                    auto* t = static_cast<TryStmt*>(s.get());
                    t->body = strip_decl_to_assign(t->body, escaping, types);
                    for (auto& c : t->catches) c.body = strip_decl_to_assign(c.body, escaping, types);
                    if (t->finally_body.has_value()) t->finally_body = strip_decl_to_assign(*t->finally_body, escaping, types);
                }
                std::set<std::string> new_names;
                std::set_difference(escaping.begin(), escaping.end(), declared_so_far.begin(), declared_so_far.end(),
                                     std::inserter(new_names, new_names.begin()));
                for (auto& name : new_names) {  // std::set - уже отсортировано, как sorted(new_names)
                    std::string typ = types.count(name) ? types[name] : "Object";
                    out.push_back(std::make_shared<LocalDecl>(typ, name, nullptr));
                }
                declared_so_far.insert(escaping.begin(), escaping.end());
            }
        }
        out.push_back(s);
    }
    return out;
}

// ---------------- _has_escaping_local_decl ----------------

bool has_escaping_local_decl_check(const std::vector<StmtPtr>& lst) {
    for (size_t i = 0; i < lst.size(); ++i) {
        StmtPtr s = lst[i];
        std::optional<std::vector<StmtPtr>> inner;
        if (s->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(s.get());
            std::vector<StmtPtr> comb = ifs->then_body;
            if (ifs->else_body.has_value()) comb.insert(comb.end(), ifs->else_body->begin(), ifs->else_body->end());
            inner = comb;
        } else if (s->kind == StmtKind::WhileStmt) {
            inner = static_cast<WhileStmt*>(s.get())->body;
        } else if (s->kind == StmtKind::DoWhileStmt) {
            inner = static_cast<DoWhileStmt*>(s.get())->body;
        } else if (s->kind == StmtKind::ForStmt) {
            inner = static_cast<ForStmt*>(s.get())->body;
        } else if (s->kind == StmtKind::SyncStmt) {
            inner = static_cast<SyncStmt*>(s.get())->body;
        } else if (s->kind == StmtKind::BlockStmt) {
            inner = static_cast<BlockStmt*>(s.get())->stmts;
        } else if (s->kind == StmtKind::SwitchStmt) {
            std::vector<StmtPtr> comb;
            for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) comb.insert(comb.end(), c.body.begin(), c.body.end());
            inner = comb;
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            std::vector<StmtPtr> comb = t->body;
            for (auto& c : t->catches) comb.insert(comb.end(), c.body.begin(), c.body.end());
            if (t->finally_body.has_value()) comb.insert(comb.end(), t->finally_body->begin(), t->finally_body->end());
            inner = comb;
        }
        if (inner.has_value() && !inner->empty()) {
            auto declared = collect_declared_names(*inner);
            std::vector<StmtPtr> rest(lst.begin() + i + 1, lst.end());
            auto later = collect_shallow_referenced_names(rest);
            bool intersects = false;
            for (auto& d : declared) {
                if (later.count(d)) { intersects = true; break; }
            }
            if (intersects) return true;
            if (has_escaping_local_decl_check(*inner)) return true;
        }
    }
    return false;
}

bool has_escaping_local_decl(const std::vector<StmtPtr>& stmts) { return has_escaping_local_decl_check(stmts); }

// ---------------- _expr_key / monitor-sync folding ----------------

std::string expr_key(const ExprPtr& e) {
    try {
        return emit_expr(e);
    } catch (...) {
        std::ostringstream oss;
        oss << "<unrepr:" << e.get() << ">";
        return oss.str();
    }
}

std::vector<StmtPtr> strip_monitor_exits(const std::vector<StmtPtr>& stmts, const std::string& key) {
    if (stmts.empty()) return stmts;
    std::vector<StmtPtr> out;
    for (auto s : stmts) {
        if (auto* mm = dynamic_cast<MonitorMarkerStmt*>(s.get())) {
            if (mm->kind == "exit" && expr_key(mm->expr) == key) continue;
        }
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            i->then_body = strip_monitor_exits(i->then_body, key);
            if (i->else_body.has_value()) i->else_body = strip_monitor_exits(*i->else_body, key);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            w->body = strip_monitor_exits(w->body, key);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            w->body = strip_monitor_exits(w->body, key);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            f->body = strip_monitor_exits(f->body, key);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            sy->body = strip_monitor_exits(sy->body, key);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            b->stmts = strip_monitor_exits(b->stmts, key);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) c.body = strip_monitor_exits(c.body, key);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = strip_monitor_exits(t->body, key);
            for (auto& c : t->catches) c.body = strip_monitor_exits(c.body, key);
            if (t->finally_body.has_value()) t->finally_body = strip_monitor_exits(*t->finally_body, key);
        }
        out.push_back(s);
    }
    return out;
}

bool is_monitor_rethrow_catch(const std::string& catch_var, const std::vector<StmtPtr>& catch_body, const std::string& key) {
    if (catch_body.empty()) return false;
    bool has_exit = false;
    for (auto& s : catch_body) {
        if (auto* mm = dynamic_cast<MonitorMarkerStmt*>(s.get())) {
            if (mm->kind == "exit" && expr_key(mm->expr) == key) { has_exit = true; break; }
        }
    }
    if (!has_exit) return false;
    std::vector<StmtPtr> rest;
    for (auto& s : catch_body) {
        if (!dynamic_cast<MonitorMarkerStmt*>(s.get())) rest.push_back(s);
    }
    if (rest.size() != 1) return false;
    StmtPtr only = rest[0];
    if (only->kind != StmtKind::ThrowStmt) return false;
    ExprPtr te = static_cast<ThrowStmt*>(only.get())->expr;
    return te && te->kind == ExprKind::Local && static_cast<Local*>(te.get())->name == catch_var;
}

std::optional<std::vector<StmtPtr>> unwrap_if_monitor_try(const StmtPtr& s, const std::string& key) {
    if (s->kind != StmtKind::TryStmt) return std::nullopt;
    auto* t = static_cast<TryStmt*>(s.get());
    if (t->catches.size() != 1 || t->finally_body.has_value()) return std::nullopt;
    if (is_monitor_rethrow_catch(t->catches[0].var_name, t->catches[0].body, key)) return t->body;
    return std::nullopt;
}

std::pair<std::optional<std::vector<StmtPtr>>, std::optional<size_t>> extract_sync_region(
    const std::vector<StmtPtr>& stmts, size_t start, const std::string& key) {
    std::vector<StmtPtr> body;
    size_t j = start;
    size_t n = stmts.size();
    while (j < n) {
        StmtPtr cand = stmts[j];
        if (auto* mm = dynamic_cast<MonitorMarkerStmt*>(cand.get())) {
            if (mm->kind == "exit" && expr_key(mm->expr) == key) return {body, j + 1};
            if (mm->kind == "enter") return {std::nullopt, std::nullopt};
        }
        auto unwrapped = unwrap_if_monitor_try(cand, key);
        if (unwrapped.has_value()) {
            body.insert(body.end(), unwrapped->begin(), unwrapped->end());
        } else {
            body.push_back(cand);
        }
        j += 1;
    }
    return {body, n};
}

std::vector<StmtPtr> fold_sync_blocks(const std::vector<StmtPtr>& stmts) {
    if (stmts.empty()) return stmts;
    std::vector<StmtPtr> out;
    size_t i = 0, n = stmts.size();
    while (i < n) {
        StmtPtr s = stmts[i];
        if (auto* mm = dynamic_cast<MonitorMarkerStmt*>(s.get())) {
            if (mm->kind == "enter") {
                std::string key = expr_key(mm->expr);
                auto [body_opt, next_index] = extract_sync_region(stmts, i + 1, key);
                if (body_opt.has_value()) {
                    auto body = fold_sync_blocks(*body_opt);
                    body = strip_monitor_exits(body, key);
                    out.push_back(std::make_shared<SyncStmt>(mm->expr, body));
                    i = *next_index;
                    continue;
                }
            }
        }
        if (s->kind == StmtKind::IfStmt) {
            auto* i2 = static_cast<IfStmt*>(s.get());
            i2->then_body = fold_sync_blocks(i2->then_body);
            if (i2->else_body.has_value()) i2->else_body = fold_sync_blocks(*i2->else_body);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            w->body = fold_sync_blocks(w->body);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            w->body = fold_sync_blocks(w->body);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            f->body = fold_sync_blocks(f->body);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            sy->body = fold_sync_blocks(sy->body);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            b->stmts = fold_sync_blocks(b->stmts);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) c.body = fold_sync_blocks(c.body);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = fold_sync_blocks(t->body);
            for (auto& c : t->catches) c.body = fold_sync_blocks(c.body);
            if (t->finally_body.has_value()) t->finally_body = fold_sync_blocks(*t->finally_body);
        }
        out.push_back(s);
        i += 1;
    }
    return out;
}

bool contains_unfolded_monitor_list(const std::vector<StmtPtr>& lst);

bool contains_unfolded_monitor_stmt(const StmtPtr& s) {
    if (dynamic_cast<MonitorMarkerStmt*>(s.get())) return true;
    if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
        if (contains_unfolded_monitor_list(i->then_body)) return true;
        if (i->else_body.has_value() && contains_unfolded_monitor_list(*i->else_body)) return true;
        return false;
    }
    if (s->kind == StmtKind::WhileStmt) return contains_unfolded_monitor_list(static_cast<WhileStmt*>(s.get())->body);
    if (s->kind == StmtKind::DoWhileStmt) return contains_unfolded_monitor_list(static_cast<DoWhileStmt*>(s.get())->body);
    if (s->kind == StmtKind::ForStmt) return contains_unfolded_monitor_list(static_cast<ForStmt*>(s.get())->body);
    if (s->kind == StmtKind::SyncStmt) return contains_unfolded_monitor_list(static_cast<SyncStmt*>(s.get())->body);
    if (s->kind == StmtKind::BlockStmt) return contains_unfolded_monitor_list(static_cast<BlockStmt*>(s.get())->stmts);
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
            if (contains_unfolded_monitor_list(c.body)) return true;
        }
        return false;
    }
    if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        if (contains_unfolded_monitor_list(t->body)) return true;
        for (auto& c : t->catches) {
            if (contains_unfolded_monitor_list(c.body)) return true;
        }
        if (t->finally_body.has_value()) return contains_unfolded_monitor_list(*t->finally_body);
        return false;
    }
    return false;
}

bool contains_unfolded_monitor_list(const std::vector<StmtPtr>& lst) {
    for (auto& s : lst) {
        if (contains_unfolded_monitor_stmt(s)) return true;
    }
    return false;
}

bool contains_unfolded_monitor(const std::vector<StmtPtr>& stmts) { return contains_unfolded_monitor_list(stmts); }

// ---------------- enum switch desugaring ----------------

void try_desugar_one(SwitchStmt* switch_stmt, const std::map<std::string, std::vector<std::string>>& enum_ordinals,
                      MethodCtx& ctx,
                      const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables) {
    ExprPtr sel = switch_stmt->selector;
    if (sel->kind != ExprKind::ArrayAccess) return;
    auto* aa = static_cast<ArrayAccess*>(sel.get());
    if (!aa->array || aa->array->kind != ExprKind::FieldAccess) return;
    auto* fa = static_cast<FieldAccess*>(aa->array.get());
    if (!(fa->is_static && !fa->target && fa->name.find("SwitchMap") != std::string::npos)) return;
    ExprPtr idx = aa->index;
    if (!idx || idx->kind != ExprKind::MethodCall) return;
    auto* mc = static_cast<MethodCall*>(idx.get());
    if (!(mc->name == "ordinal" && mc->args.empty() && mc->target)) return;
    ExprPtr enum_expr = mc->target;
    std::string enum_type = enum_expr->type;
    if (enum_type.empty()) return;

    std::optional<std::map<int64_t, std::string>> exact;
    if (fa->owner.has_value()) {
        auto key = std::make_pair(*fa->owner, fa->name);
        auto it = switchmap_tables.find(key);
        if (it != switchmap_tables.end()) exact = it->second;
    }

    if (exact.has_value()) {
        for (auto& c : switch_stmt->cases) {
            if (c.is_default) continue;
            std::vector<std::string> new_values;
            bool ok = true;
            for (auto& v : c.values) {
                try {
                    int64_t n = std::stoll(v);
                    auto it2 = exact->find(n);
                    if (it2 == exact->end()) { ok = false; break; }
                    new_values.push_back(it2->second);
                } catch (...) {
                    ok = false;
                    break;
                }
            }
            if (!ok) return;
            c.values = new_values;
        }
        switch_stmt->selector = enum_expr;
        return;
    }

    std::string enum_type_base = enum_type;
    while (enum_type_base.size() >= 2 && enum_type_base.substr(enum_type_base.size() - 2) == "[]") {
        enum_type_base = enum_type_base.substr(0, enum_type_base.size() - 2);
    }
    auto known_it = ctx.known.find(enum_type_base);
    if (known_it == ctx.known.end()) return;
    auto ord_it = enum_ordinals.find(known_it->second);
    if (ord_it == enum_ordinals.end() || ord_it->second.empty()) return;
    const std::vector<std::string>& names = ord_it->second;

    std::vector<SwitchCase> new_cases;
    for (auto& c : switch_stmt->cases) {
        if (c.is_default) {
            new_cases.push_back(c);
            continue;
        }
        std::vector<std::string> new_values;
        bool ok = true;
        for (auto& v : c.values) {
            int64_t ordinal;
            try {
                ordinal = std::stoll(v) - 1;
            } catch (...) {
                ok = false;
                break;
            }
            if (!(ordinal >= 0 && static_cast<size_t>(ordinal) < names.size())) { ok = false; break; }
            new_values.push_back(names[ordinal]);
        }
        if (!ok) return;
        c.values = new_values;
        new_cases.push_back(c);
    }
    switch_stmt->cases = new_cases;
    switch_stmt->selector = enum_expr;
}

void desugar_enum_switches_visit_list(const std::vector<StmtPtr>& lst, const std::map<std::string, std::vector<std::string>>& eo,
                                       MethodCtx& ctx,
                                       const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& smt);

void desugar_enum_switches_visit_stmt(const StmtPtr& s, const std::map<std::string, std::vector<std::string>>& eo, MethodCtx& ctx,
                                       const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& smt) {
    if (s->kind == StmtKind::SwitchStmt) {
        auto* sw = static_cast<SwitchStmt*>(s.get());
        try_desugar_one(sw, eo, ctx, smt);
        for (auto& c : sw->cases) desugar_enum_switches_visit_list(c.body, eo, ctx, smt);
    } else if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
        if (!i->then_body.empty()) desugar_enum_switches_visit_list(i->then_body, eo, ctx, smt);
        if (i->else_body.has_value() && !i->else_body->empty()) desugar_enum_switches_visit_list(*i->else_body, eo, ctx, smt);
    } else if (s->kind == StmtKind::WhileStmt) {
        desugar_enum_switches_visit_list(static_cast<WhileStmt*>(s.get())->body, eo, ctx, smt);
    } else if (s->kind == StmtKind::DoWhileStmt) {
        desugar_enum_switches_visit_list(static_cast<DoWhileStmt*>(s.get())->body, eo, ctx, smt);
    } else if (s->kind == StmtKind::ForStmt) {
        desugar_enum_switches_visit_list(static_cast<ForStmt*>(s.get())->body, eo, ctx, smt);
    } else if (s->kind == StmtKind::SyncStmt) {
        desugar_enum_switches_visit_list(static_cast<SyncStmt*>(s.get())->body, eo, ctx, smt);
    } else if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        desugar_enum_switches_visit_list(t->body, eo, ctx, smt);
        for (auto& c : t->catches) desugar_enum_switches_visit_list(c.body, eo, ctx, smt);
        if (t->finally_body.has_value()) desugar_enum_switches_visit_list(*t->finally_body, eo, ctx, smt);
    }
}

void desugar_enum_switches_visit_list(const std::vector<StmtPtr>& lst, const std::map<std::string, std::vector<std::string>>& eo,
                                       MethodCtx& ctx,
                                       const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& smt) {
    for (auto& s : lst) desugar_enum_switches_visit_stmt(s, eo, ctx, smt);
}

std::vector<StmtPtr> desugar_enum_switches(const std::vector<StmtPtr>& stmts,
                                            const std::map<std::string, std::vector<std::string>>& enum_ordinals, MethodCtx& ctx,
                                            const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables) {
    desugar_enum_switches_visit_list(stmts, enum_ordinals, ctx, switchmap_tables);
    return stmts;
}

// ---------------- _reorder_ctor_call_to_front ----------------

std::vector<StmtPtr> reorder_ctor_call_to_front(const std::vector<StmtPtr>& stmts) {
    std::optional<size_t> idx;
    for (size_t i = 0; i < stmts.size(); ++i) {
        auto& s = stmts[i];
        if (s->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(s.get());
            if (es->expr->kind == ExprKind::MethodCall && static_cast<MethodCall*>(es->expr.get())->is_ctor) {
                idx = i;
                break;
            }
        }
    }
    if (!idx.has_value() || *idx == 0) return stmts;
    for (size_t i = 0; i < *idx; ++i) {
        auto& s = stmts[i];
        bool okpat = false;
        if (s->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(s.get());
            if (es->expr->kind == ExprKind::Assign) {
                auto* a = static_cast<Assign*>(es->expr.get());
                if (a->target->kind == ExprKind::FieldAccess) {
                    auto* fld = static_cast<FieldAccess*>(a->target.get());
                    if (fld->target && fld->target->kind == ExprKind::This) {
                        if (a->value->kind == ExprKind::Local || a->value->kind == ExprKind::Const) okpat = true;
                    }
                }
            }
        }
        if (!okpat) return stmts;
    }
    std::vector<StmtPtr> out;
    out.push_back(stmts[*idx]);
    for (size_t i = 0; i < *idx; ++i) out.push_back(stmts[i]);
    for (size_t i = *idx + 1; i < stmts.size(); ++i) out.push_back(stmts[i]);
    return out;
}

// ---------------- _fold_array_literals ----------------

std::optional<std::pair<int64_t, ExprPtr>> array_store_target(const StmtPtr& stmt, const std::string& array_name) {
    if (stmt->kind != StmtKind::ExprStmt) return std::nullopt;
    auto* es = static_cast<ExprStmtNode*>(stmt.get());
    if (es->expr->kind != ExprKind::Assign) return std::nullopt;
    auto* a = static_cast<Assign*>(es->expr.get());
    if (a->target->kind != ExprKind::ArrayAccess) return std::nullopt;
    auto* aa = static_cast<ArrayAccess*>(a->target.get());
    if (!(aa->array && aa->array->kind == ExprKind::Local && aa->array->kind == ExprKind::Local)) {}
    if (!aa->array || aa->array->kind != ExprKind::Local || static_cast<Local*>(aa->array.get())->name != array_name) return std::nullopt;
    if (!aa->index || aa->index->kind != ExprKind::Const) return std::nullopt;
    auto* ci = static_cast<Const*>(aa->index.get());
    if (ci->type != "int") return std::nullopt;
    try {
        int64_t idx = std::stoll(ci->literal);
        return std::make_pair(idx, a->value);
    } catch (...) {
        return std::nullopt;
    }
}

int count_local_uses_expr(const ExprPtr& e, const std::string& name) {
    if (!e) return 0;
    if (e->kind == ExprKind::Local) return static_cast<Local*>(e.get())->name == name ? 1 : 0;
    int total = 0;
    walk_expr_children(e, [&](const ExprPtr& c) { total += count_local_uses_expr(c, name); });
    return total;
}

int count_local_uses_stmt(const StmtPtr& s, const std::string& name);

int count_local_uses_list(const std::vector<StmtPtr>& lst, const std::string& name) {
    int total = 0;
    for (auto& s : lst) total += count_local_uses_stmt(s, name);
    return total;
}

int count_local_uses_stmt(const StmtPtr& s, const std::string& name) {
    if (!s) return 0;
    int total = 0;
    switch (s->kind) {
        case StmtKind::LocalDecl:
            total += count_local_uses_expr(static_cast<LocalDecl*>(s.get())->init, name);
            break;
        case StmtKind::ExprStmt:
            total += count_local_uses_expr(static_cast<ExprStmtNode*>(s.get())->expr, name);
            break;
        case StmtKind::ReturnStmt:
            total += count_local_uses_expr(static_cast<ReturnStmt*>(s.get())->expr, name);
            break;
        case StmtKind::ThrowStmt:
            total += count_local_uses_expr(static_cast<ThrowStmt*>(s.get())->expr, name);
            break;
        case StmtKind::IfStmt: {
            auto* i = static_cast<IfStmt*>(s.get());
            total += count_local_uses_expr(i->cond, name);
            total += count_local_uses_list(i->then_body, name);
            if (i->else_body.has_value()) total += count_local_uses_list(*i->else_body, name);
            break;
        }
        case StmtKind::WhileStmt: {
            auto* w = static_cast<WhileStmt*>(s.get());
            total += count_local_uses_expr(w->cond, name);
            total += count_local_uses_list(w->body, name);
            break;
        }
        case StmtKind::DoWhileStmt: {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            total += count_local_uses_expr(w->cond, name);
            total += count_local_uses_list(w->body, name);
            break;
        }
        case StmtKind::ForStmt: {
            auto* f = static_cast<ForStmt*>(s.get());
            total += count_local_uses_expr(f->init, name);
            total += count_local_uses_expr(f->cond, name);
            if (f->update) total += count_local_uses_stmt(f->update, name);
            total += count_local_uses_list(f->body, name);
            break;
        }
        case StmtKind::SyncStmt: {
            auto* sy = static_cast<SyncStmt*>(s.get());
            total += count_local_uses_expr(sy->expr, name);
            total += count_local_uses_list(sy->body, name);
            break;
        }
        case StmtKind::BlockStmt:
            total += count_local_uses_list(static_cast<BlockStmt*>(s.get())->stmts, name);
            break;
        case StmtKind::SwitchStmt: {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            total += count_local_uses_expr(sw->selector, name);
            for (auto& c : sw->cases) total += count_local_uses_list(c.body, name);
            break;
        }
        case StmtKind::TryStmt: {
            auto* t = static_cast<TryStmt*>(s.get());
            total += count_local_uses_list(t->body, name);
            for (auto& c : t->catches) total += count_local_uses_list(c.body, name);
            // ВНИМАНИЕ: finally_body НЕ учитывается - в оригинале generic-список
            // атрибутов _count_local_uses не содержит "finally_body" (в отличие
            // от _contains_local_ref в structure.py, где он есть) - разные
            // функции, разные списки, см. HANDOFF_38/39.
            break;
        }
        default:
            break;
    }
    return total;
}

// substitute_local_once: заменяет ЕДИНСТВЕННОЕ вхождение Local(name) в
// СПИСКЕ statement'ов на replacement - ищет по тому же обходу, что и
// count_local_uses (которым вызывающий код УЖЕ убедился, что вхождение
// ровно одно), останавливается после первой замены.
bool substitute_local_once_expr(ExprPtr& e, const std::string& name, const ExprPtr& replacement) {
    if (!e) return false;
    if (e->kind == ExprKind::Local && static_cast<Local*>(e.get())->name == name) {
        e = replacement;
        return true;
    }
    switch (e->kind) {
        case ExprKind::FieldAccess:
            return substitute_local_once_expr(static_cast<FieldAccess*>(e.get())->target, name, replacement);
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(e.get());
            if (substitute_local_once_expr(a->array, name, replacement)) return true;
            return substitute_local_once_expr(a->index, name, replacement);
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(e.get());
            if (substitute_local_once_expr(m->target, name, replacement)) return true;
            for (auto& arg : m->args) if (substitute_local_once_expr(arg, name, replacement)) return true;
            return false;
        }
        case ExprKind::NewObject: {
            auto* n = static_cast<NewObject*>(e.get());
            for (auto& arg : n->args) if (substitute_local_once_expr(arg, name, replacement)) return true;
            return false;
        }
        case ExprKind::Cast:
            return substitute_local_once_expr(static_cast<Cast*>(e.get())->expr, name, replacement);
        case ExprKind::InstanceOf:
            return substitute_local_once_expr(static_cast<InstanceOf*>(e.get())->expr, name, replacement);
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            if (substitute_local_once_expr(b->left, name, replacement)) return true;
            return substitute_local_once_expr(b->right, name, replacement);
        }
        case ExprKind::UnOp:
            return substitute_local_once_expr(static_cast<UnOp*>(e.get())->expr, name, replacement);
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            if (substitute_local_once_expr(t->cond, name, replacement)) return true;
            if (substitute_local_once_expr(t->tval, name, replacement)) return true;
            return substitute_local_once_expr(t->fval, name, replacement);
        }
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            if (substitute_local_once_expr(a->target, name, replacement)) return true;
            return substitute_local_once_expr(a->value, name, replacement);
        }
        default:
            return false;
    }
}

bool substitute_local_once_stmt(StmtPtr& s, const std::string& name, const ExprPtr& replacement);

bool substitute_local_once_list(std::vector<StmtPtr>& lst, const std::string& name, const ExprPtr& replacement) {
    for (auto& s : lst) {
        if (substitute_local_once_stmt(s, name, replacement)) return true;
    }
    return false;
}

bool substitute_local_once_stmt(StmtPtr& s, const std::string& name, const ExprPtr& replacement) {
    if (!s) return false;
    // ВАЖНО: список проверяемых атрибутов У́ЖЕ, чем у count_local_uses_stmt -
    // в оригинале это ДВЕ РАЗНЫЕ функции с РАЗНЫМИ списками. substitute НЕ
    // заходит в ForStmt.update, SwitchStmt.selector, TryStmt.finally_body,
    // BlockStmt.stmts - там, где count_local_uses заходит. Раз
    // count_local_uses(...)==1 уже проверено вызывающим кодом ПЕРЕД вызовом
    // substitute, но с ДРУГИМ (более широким) охватом - теоретически
    // возможен редкий случай, когда единственное вхождение лежит именно там,
    // куда substitute не заходит, и тогда подстановка молча не произойдёт -
    // это ограничение САМОГО оригинала, не баг порта, сохранено как есть.
    switch (s->kind) {
        case StmtKind::ExprStmt:
            if (substitute_local_once_expr(static_cast<ExprStmtNode*>(s.get())->expr, name, replacement)) return true;
            break;
        case StmtKind::LocalDecl:
            if (substitute_local_once_expr(static_cast<LocalDecl*>(s.get())->init, name, replacement)) return true;
            break;
        case StmtKind::ReturnStmt:
            if (substitute_local_once_expr(static_cast<ReturnStmt*>(s.get())->expr, name, replacement)) return true;
            break;
        case StmtKind::ThrowStmt:
            if (substitute_local_once_expr(static_cast<ThrowStmt*>(s.get())->expr, name, replacement)) return true;
            break;
        case StmtKind::IfStmt:
            if (substitute_local_once_expr(static_cast<IfStmt*>(s.get())->cond, name, replacement)) return true;
            break;
        case StmtKind::WhileStmt:
            if (substitute_local_once_expr(static_cast<WhileStmt*>(s.get())->cond, name, replacement)) return true;
            break;
        case StmtKind::DoWhileStmt:
            if (substitute_local_once_expr(static_cast<DoWhileStmt*>(s.get())->cond, name, replacement)) return true;
            break;
        case StmtKind::ForStmt: {
            auto* f = static_cast<ForStmt*>(s.get());
            if (substitute_local_once_expr(f->init, name, replacement)) return true;
            if (substitute_local_once_expr(f->cond, name, replacement)) return true;
            // update - НЕ трогаем (см. пояснение выше)
            break;
        }
        case StmtKind::SyncStmt:
            if (substitute_local_once_expr(static_cast<SyncStmt*>(s.get())->expr, name, replacement)) return true;
            break;
        default:
            break;  // SwitchStmt(selector)/TryStmt/BlockStmt - на этом этапе не трогаем (см. выше)
    }
    switch (s->kind) {
        case StmtKind::IfStmt: {
            auto* i = static_cast<IfStmt*>(s.get());
            if (substitute_local_once_list(i->then_body, name, replacement)) return true;
            if (i->else_body.has_value() && substitute_local_once_list(*i->else_body, name, replacement)) return true;
            break;
        }
        case StmtKind::WhileStmt:
            if (substitute_local_once_list(static_cast<WhileStmt*>(s.get())->body, name, replacement)) return true;
            break;
        case StmtKind::DoWhileStmt:
            if (substitute_local_once_list(static_cast<DoWhileStmt*>(s.get())->body, name, replacement)) return true;
            break;
        case StmtKind::ForStmt:
            if (substitute_local_once_list(static_cast<ForStmt*>(s.get())->body, name, replacement)) return true;
            break;
        case StmtKind::SyncStmt:
            if (substitute_local_once_list(static_cast<SyncStmt*>(s.get())->body, name, replacement)) return true;
            break;
        case StmtKind::TryStmt:
            if (substitute_local_once_list(static_cast<TryStmt*>(s.get())->body, name, replacement)) return true;
            break;
        default:
            break;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
            if (substitute_local_once_list(c.body, name, replacement)) return true;
        }
    }
    if (s->kind == StmtKind::TryStmt) {
        for (auto& c : static_cast<TryStmt*>(s.get())->catches) {
            if (substitute_local_once_list(c.body, name, replacement)) return true;
        }
    }
    return false;
}

std::vector<StmtPtr> fold_array_literals_pass(const std::vector<StmtPtr>& lst) {
    std::vector<StmtPtr> out;
    size_t i = 0, n_total = lst.size();
    while (i < n_total) {
        StmtPtr cur = lst[i];
        bool folded = false;
        if (cur->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(cur.get());
            if (ld->init && ld->init->kind == ExprKind::NewArray) {
                auto* na = static_cast<NewArray*>(ld->init.get());
                if (!na->initializer.has_value() && na->dims.size() == 1 && na->dims[0] &&
                    na->dims[0]->kind == ExprKind::Const && static_cast<Const*>(na->dims[0].get())->type == "int") {
                    int64_t size = -1;
                    try {
                        size = std::stoll(static_cast<Const*>(na->dims[0].get())->literal);
                    } catch (...) {
                    }
                    if (size > 0 && size <= 800) {
                        std::vector<ExprPtr> values(static_cast<size_t>(size), nullptr);
                        int64_t filled = 0;
                        size_t j = i + 1;
                        while (j < n_total && filled < size) {
                            auto tgt = array_store_target(lst[j], ld->name);
                            if (!tgt.has_value()) break;
                            auto [idx, val] = *tgt;
                            if (!(idx >= 0 && idx < size) || values[static_cast<size_t>(idx)] != nullptr) break;
                            values[static_cast<size_t>(idx)] = val;
                            filled += 1;
                            j += 1;
                        }
                        if (filled == size) {
                            std::vector<StmtPtr> rest(lst.begin() + j, lst.end());
                            if (count_local_uses_list(rest, ld->name) == 1) {
                                na->initializer = values;
                                na->dims = {nullptr};
                                substitute_local_once_list(rest, ld->name, ld->init);
                                out.insert(out.end(), rest.begin(), rest.end());
                                i = n_total;
                                folded = true;
                            }
                        }
                    }
                }
            }
        }
        if (!folded) {
            out.push_back(cur);
            i += 1;
        }
    }
    return out;
}

std::vector<StmtPtr> walk_stmt_lists(std::vector<StmtPtr> stmts, const std::function<std::vector<StmtPtr>(const std::vector<StmtPtr>&)>& fn) {
    stmts = fn(stmts);
    for (auto& s : stmts) {
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (!i->then_body.empty()) i->then_body = walk_stmt_lists(i->then_body, fn);
            if (i->else_body.has_value() && !i->else_body->empty()) i->else_body = walk_stmt_lists(*i->else_body, fn);
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            w->body = walk_stmt_lists(w->body, fn);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            w->body = walk_stmt_lists(w->body, fn);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            f->body = walk_stmt_lists(f->body, fn);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            sy->body = walk_stmt_lists(sy->body, fn);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) c.body = walk_stmt_lists(c.body, fn);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = walk_stmt_lists(t->body, fn);
            for (auto& c : t->catches) c.body = walk_stmt_lists(c.body, fn);
            if (t->finally_body.has_value()) t->finally_body = walk_stmt_lists(*t->finally_body, fn);
        }
    }
    return stmts;
}

std::vector<StmtPtr> fold_array_literals(const std::vector<StmtPtr>& stmts) {
    return walk_stmt_lists(stmts, fold_array_literals_pass);
}

// ---------------- _ensure_local_declarations ----------------

std::vector<StmtPtr> ensure_local_declarations(const std::vector<StmtPtr>& stmts, std::map<std::string, std::string>& declared) {
    std::vector<StmtPtr> out;
    for (auto s : stmts) {
        if (s->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(s.get());
            declared[ld->name] = ld->type;
            out.push_back(s);
            continue;
        }
        if (s->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(s.get());
            if (es->expr->kind == ExprKind::Assign) {
                auto* a = static_cast<Assign*>(es->expr.get());
                if (a->target->kind == ExprKind::Local) {
                    std::string name = static_cast<Local*>(a->target.get())->name;
                    if (!declared.count(name)) {
                        std::string val_type = a->value->type.empty() ? a->target->type : a->value->type;
                        if (val_type.empty()) val_type = a->target->type;
                        if (PSEUDO_TYPES.count(val_type)) {
                            val_type = !PSEUDO_TYPES.count(a->target->type) ? a->target->type : "Object";
                        }
                        declared[name] = val_type;
                        out.push_back(std::make_shared<LocalDecl>(val_type, name, a->value));
                        continue;
                    }
                }
            }
        }
        out.push_back(s);
        // ВАЖНО: для if/while/for/sync/try - КОПИЯ declared (как Python
        // `dict(declared)`) - объявления внутри ветки НЕ видны снаружи и не
        // видны в "соседней" ветке. Для switch - ссылка на ТОТ ЖЕ словарь
        // (как Python, передающий `declared` без dict(...)) - case'ы делят
        // одну лексическую область (реальные bytecode-слоты переиспользуются
        // между case'ами одного switch), И объявления изнутри switch остаются
        // видны в АРИФМЕТИКЕ ПОСЛЕ switch в том же списке (тот же словарь -
        // те же дальнейшие итерации этого цикла).
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (!i->then_body.empty()) {
                auto branch_declared = declared;
                i->then_body = ensure_local_declarations(i->then_body, branch_declared);
            }
            if (i->else_body.has_value() && !i->else_body->empty()) {
                auto branch_declared = declared;
                i->else_body = ensure_local_declarations(*i->else_body, branch_declared);
            }
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            auto branch_declared = declared;
            w->body = ensure_local_declarations(w->body, branch_declared);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            auto branch_declared = declared;
            w->body = ensure_local_declarations(w->body, branch_declared);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            auto branch_declared = declared;
            f->body = ensure_local_declarations(f->body, branch_declared);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            auto branch_declared = declared;
            sy->body = ensure_local_declarations(sy->body, branch_declared);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) c.body = ensure_local_declarations(c.body, declared);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            {
                auto branch_declared = declared;
                t->body = ensure_local_declarations(t->body, branch_declared);
            }
            for (auto& c : t->catches) {
                auto branch_declared = declared;
                c.body = ensure_local_declarations(c.body, branch_declared);
            }
            if (t->finally_body.has_value()) {
                auto branch_declared = declared;
                t->finally_body = ensure_local_declarations(*t->finally_body, branch_declared);
            }
        }
    }
    return out;
}

// ---------------- _prune_unused_imports ----------------

std::string strip_brackets(const std::string& t) {
    size_t end = t.size();
    while (end > 0 && (t[end - 1] == '[' || t[end - 1] == ']')) end -= 1;
    return t.substr(0, end);
}

void prune_note(std::set<std::string>& used, const std::string& t) {
    if (!t.empty()) used.insert(strip_brackets(t));
}

void prune_walk_expr(const ExprPtr& e, std::set<std::string>& used) {
    if (!e) return;
    if (e->kind == ExprKind::FieldAccess && static_cast<FieldAccess*>(e.get())->is_static) {
        auto* f = static_cast<FieldAccess*>(e.get());
        if (f->owner.has_value()) prune_note(used, *f->owner);
    } else if (e->kind == ExprKind::MethodCall) {
        auto* m = static_cast<MethodCall*>(e.get());
        if ((m->is_static || m->owner.has_value()) && m->owner.has_value()) prune_note(used, *m->owner);
    } else if (e->kind == ExprKind::NewObject) {
        prune_note(used, static_cast<NewObject*>(e.get())->type);
    } else if (e->kind == ExprKind::NewArray) {
        prune_note(used, static_cast<NewArray*>(e.get())->elem_type);
    } else if (e->kind == ExprKind::Cast) {
        prune_note(used, e->type);
    } else if (e->kind == ExprKind::InstanceOf) {
        prune_note(used, static_cast<InstanceOf*>(e.get())->check_type);
    } else if (e->kind == ExprKind::ClassLiteral) {
        prune_note(used, static_cast<ClassLiteral*>(e.get())->type_name);
    } else if (e->kind == ExprKind::Local) {
        prune_note(used, e->type);
    }
    walk_expr_children(e, [&](const ExprPtr& c) { prune_walk_expr(c, used); });
    if (e->kind == ExprKind::MethodCall) {
        for (auto& a : static_cast<MethodCall*>(e.get())->args) prune_walk_expr(a, used);
    } else if (e->kind == ExprKind::NewObject) {
        for (auto& a : static_cast<NewObject*>(e.get())->args) prune_walk_expr(a, used);
    }
    if (e->kind == ExprKind::NewArray) {
        for (auto& d : static_cast<NewArray*>(e.get())->dims) prune_walk_expr(d, used);
    }
}

void prune_walk_list(const std::vector<StmtPtr>& lst, std::set<std::string>& used);

void prune_walk_stmt(const StmtPtr& s, std::set<std::string>& used) {
    switch (s->kind) {
        case StmtKind::ExprStmt: prune_walk_expr(static_cast<ExprStmtNode*>(s.get())->expr, used); break;
        case StmtKind::ReturnStmt: prune_walk_expr(static_cast<ReturnStmt*>(s.get())->expr, used); break;
        case StmtKind::ThrowStmt: prune_walk_expr(static_cast<ThrowStmt*>(s.get())->expr, used); break;
        case StmtKind::SyncStmt: prune_walk_expr(static_cast<SyncStmt*>(s.get())->expr, used); break;
        case StmtKind::IfStmt: prune_walk_expr(static_cast<IfStmt*>(s.get())->cond, used); break;
        case StmtKind::WhileStmt: prune_walk_expr(static_cast<WhileStmt*>(s.get())->cond, used); break;
        case StmtKind::DoWhileStmt: prune_walk_expr(static_cast<DoWhileStmt*>(s.get())->cond, used); break;
        case StmtKind::ForStmt:
            prune_walk_expr(static_cast<ForStmt*>(s.get())->init, used);
            prune_walk_expr(static_cast<ForStmt*>(s.get())->cond, used);
            if (static_cast<ForStmt*>(s.get())->update)
                prune_walk_expr(static_cast<ExprStmtNode*>(static_cast<ForStmt*>(s.get())->update.get())->expr, used);
            break;
        case StmtKind::SwitchStmt: prune_walk_expr(static_cast<SwitchStmt*>(s.get())->selector, used); break;
        case StmtKind::LocalDecl:
            prune_note(used, static_cast<LocalDecl*>(s.get())->type);
            prune_walk_expr(static_cast<LocalDecl*>(s.get())->init, used);
            break;
        default:
            break;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) prune_walk_list(c.body, used);
    } else if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<IfStmt*>(s.get());
        if (!i->then_body.empty()) prune_walk_list(i->then_body, used);
        if (i->else_body.has_value() && !i->else_body->empty()) prune_walk_list(*i->else_body, used);
    } else if (s->kind == StmtKind::WhileStmt) {
        prune_walk_list(static_cast<WhileStmt*>(s.get())->body, used);
    } else if (s->kind == StmtKind::DoWhileStmt) {
        prune_walk_list(static_cast<DoWhileStmt*>(s.get())->body, used);
    } else if (s->kind == StmtKind::ForStmt) {
        prune_walk_list(static_cast<ForStmt*>(s.get())->body, used);
    } else if (s->kind == StmtKind::SyncStmt) {
        prune_walk_list(static_cast<SyncStmt*>(s.get())->body, used);
    } else if (s->kind == StmtKind::TryStmt) {
        auto* t = static_cast<TryStmt*>(s.get());
        prune_walk_list(t->body, used);
        for (auto& c : t->catches) {
            prune_note(used, c.type);
            prune_walk_list(c.body, used);
        }
        if (t->finally_body.has_value()) prune_walk_list(*t->finally_body, used);
    }
}

void prune_walk_list(const std::vector<StmtPtr>& lst, std::set<std::string>& used) {
    for (auto& s : lst) prune_walk_stmt(s, used);
}

void prune_unused_imports(const std::vector<StmtPtr>& stmts, MethodCtx& ctx) {
    std::set<std::string> used;
    prune_walk_list(stmts, used);
    OrderedImports new_imports;
    for (auto& [d, simp] : ctx.imports.items()) {
        if (used.count(d)) new_imports.set(d, simp);
    }
    ctx.imports = new_imports;
}

// ---------------- _simple_type / _inline_single_use_crossing_temps / _refresh_crossing_temp_types ----------------

std::string simple_type(const std::string& t) {
    auto pos = t.find_last_of('.');
    return (pos == std::string::npos) ? t : t.substr(pos + 1);
}

std::vector<StmtPtr> inline_crossing_pass(const std::vector<StmtPtr>& lst, MethodCtx& ctx) {
    std::vector<StmtPtr> out;
    size_t i = 0, n = lst.size();
    while (i < n) {
        StmtPtr cur = lst[i];
        StmtPtr nxt = (i + 1 < n) ? lst[i + 1] : nullptr;
        if (cur->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(cur.get());
            if (es->expr->kind == ExprKind::Assign) {
                auto* a = static_cast<Assign*>(es->expr.get());
                if (a->target->kind == ExprKind::Local) {
                    std::string tname = static_cast<Local*>(a->target.get())->name;
                    if (ctx.crossing_temp_types.count(tname) && nxt && nxt->kind == StmtKind::ReturnStmt) {
                        auto* r = static_cast<ReturnStmt*>(nxt.get());
                        if (r->expr && r->expr->kind == ExprKind::Local && static_cast<Local*>(r->expr.get())->name == tname) {
                            out.push_back(std::make_shared<ReturnStmt>(coerce_arg(a->value, ctx.ret_type)));
                            i += 2;
                            continue;
                        }
                    }
                }
            }
        }
        out.push_back(cur);
        i += 1;
    }
    return out;
}

std::vector<StmtPtr> inline_single_use_crossing_temps(const std::vector<StmtPtr>& stmts, MethodCtx& ctx) {
    return walk_stmt_lists(stmts, [&](const std::vector<StmtPtr>& l) { return inline_crossing_pass(l, ctx); });
}

void collect_local_names_expr(const ExprPtr& e, std::set<std::string>& out) {
    if (!e) return;
    if (e->kind == ExprKind::Local) {
        out.insert(static_cast<Local*>(e.get())->name);
        return;
    }
    walk_expr_children(e, [&](const ExprPtr& c) { collect_local_names_expr(c, out); });
    if (e->kind == ExprKind::MethodCall) {
        for (auto& a : static_cast<MethodCall*>(e.get())->args) collect_local_names_expr(a, out);
    } else if (e->kind == ExprKind::NewObject) {
        for (auto& a : static_cast<NewObject*>(e.get())->args) collect_local_names_expr(a, out);
    } else if (e->kind == ExprKind::NewArray) {
        for (auto& d : static_cast<NewArray*>(e.get())->dims) collect_local_names_expr(d, out);
    }
}

void collect_local_names_list(const std::vector<StmtPtr>& lst, std::set<std::string>& out);

void collect_local_names_stmt(const StmtPtr& s, std::set<std::string>& out) {
    if (!s) return;
    switch (s->kind) {
        case StmtKind::ExprStmt: collect_local_names_expr(static_cast<ExprStmtNode*>(s.get())->expr, out); break;
        case StmtKind::LocalDecl: collect_local_names_expr(static_cast<LocalDecl*>(s.get())->init, out); break;
        case StmtKind::ReturnStmt: collect_local_names_expr(static_cast<ReturnStmt*>(s.get())->expr, out); break;
        case StmtKind::ThrowStmt: collect_local_names_expr(static_cast<ThrowStmt*>(s.get())->expr, out); break;
        case StmtKind::SyncStmt: collect_local_names_expr(static_cast<SyncStmt*>(s.get())->expr, out); break;
        case StmtKind::IfStmt: collect_local_names_expr(static_cast<IfStmt*>(s.get())->cond, out); break;
        case StmtKind::WhileStmt: collect_local_names_expr(static_cast<WhileStmt*>(s.get())->cond, out); break;
        case StmtKind::DoWhileStmt: collect_local_names_expr(static_cast<DoWhileStmt*>(s.get())->cond, out); break;
        case StmtKind::SwitchStmt: collect_local_names_expr(static_cast<SwitchStmt*>(s.get())->selector, out); break;
        case StmtKind::ForStmt: {
            auto* f = static_cast<ForStmt*>(s.get());
            collect_local_names_expr(f->init, out);
            collect_local_names_expr(f->cond, out);
            if (f->update) collect_local_names_stmt(f->update, out);
            break;
        }
        default:
            break;
    }
    switch (s->kind) {
        case StmtKind::IfStmt: {
            auto* i = static_cast<IfStmt*>(s.get());
            collect_local_names_list(i->then_body, out);
            if (i->else_body.has_value()) collect_local_names_list(*i->else_body, out);
            break;
        }
        case StmtKind::WhileStmt: collect_local_names_list(static_cast<WhileStmt*>(s.get())->body, out); break;
        case StmtKind::DoWhileStmt: collect_local_names_list(static_cast<DoWhileStmt*>(s.get())->body, out); break;
        case StmtKind::ForStmt: collect_local_names_list(static_cast<ForStmt*>(s.get())->body, out); break;
        case StmtKind::SyncStmt: collect_local_names_list(static_cast<SyncStmt*>(s.get())->body, out); break;
        case StmtKind::TryStmt: collect_local_names_list(static_cast<TryStmt*>(s.get())->body, out); break;
        default:
            break;
    }
    if (s->kind == StmtKind::SwitchStmt) {
        for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) collect_local_names_list(c.body, out);
    }
    if (s->kind == StmtKind::TryStmt) {
        for (auto& c : static_cast<TryStmt*>(s.get())->catches) collect_local_names_list(c.body, out);
        // finally_body сознательно НЕ обходится - как и в _collect_local_names
        // оригинала (тот же список атрибутов, что у _count_local_uses).
    }
}

void collect_local_names_list(const std::vector<StmtPtr>& lst, std::set<std::string>& out) {
    for (auto& s : lst) collect_local_names_stmt(s, out);
}

void refresh_crossing_temp_types(const std::vector<StmtPtr>& stmts, MethodCtx& ctx) {
    std::set<std::string> boolish_hint;
    std::map<std::string, std::string> seen;
    std::vector<std::string> seen_order;

    walk_stmt_lists(stmts, [&](const std::vector<StmtPtr>& lst) {
        for (auto& s : lst) {
            if (s->kind == StmtKind::ExprStmt) {
                auto* es = static_cast<ExprStmtNode*>(s.get());
                if (es->expr->kind == ExprKind::Assign) {
                    auto* a = static_cast<Assign*>(es->expr.get());
                    if (a->target->kind == ExprKind::Local) {
                        std::string name = static_cast<Local*>(a->target.get())->name;
                        if (ctx.crossing_temp_types.count(name)) {
                            std::string vtype = a->value->type;
                            if (vtype == "boolean") boolish_hint.insert(name);
                            if (!seen.count(name)) {
                                std::string t = !vtype.empty() ? vtype : ctx.crossing_temp_types[name];
                                if (PSEUDO_TYPES.count(t)) {
                                    t = ctx.crossing_temp_types[name];
                                    if (PSEUDO_TYPES.count(t)) t = "Object";
                                }
                                seen[name] = t;
                                seen_order.push_back(name);
                            }
                        }
                    }
                }
            } else if (s->kind == StmtKind::ReturnStmt) {
                auto* r = static_cast<ReturnStmt*>(s.get());
                if (r->expr && r->expr->kind == ExprKind::Local && ctx.crossing_temp_types.count(static_cast<Local*>(r->expr.get())->name) &&
                    ctx.ret_type == "boolean") {
                    boolish_hint.insert(static_cast<Local*>(r->expr.get())->name);
                }
            }
        }
        return lst;
    });
    for (auto& name : seen_order) {
        std::string typ = seen[name];
        if (typ == "int" && boolish_hint.count(name)) typ = "boolean";
        ctx.crossing_temp_types[name] = typ;
    }
    walk_stmt_lists(stmts, [&](const std::vector<StmtPtr>& lst) {
        for (auto& s : lst) {
            if (s->kind == StmtKind::ExprStmt) {
                auto* es = static_cast<ExprStmtNode*>(s.get());
                if (es->expr->kind == ExprKind::Assign) {
                    auto* a = static_cast<Assign*>(es->expr.get());
                    if (a->target->kind == ExprKind::Local) {
                        std::string name = static_cast<Local*>(a->target.get())->name;
                        if (ctx.crossing_temp_types.count(name) && ctx.crossing_temp_types[name] == "boolean") {
                            if (a->value->kind == ExprKind::Const) {
                                auto* c = static_cast<Const*>(a->value.get());
                                if (c->type == "int" && (c->literal == "0" || c->literal == "1")) {
                                    a->value = std::make_shared<Const>(c->literal == "0" ? "false" : "true", "boolean");
                                }
                            }
                        }
                    }
                }
            }
        }
        return lst;
    });
    std::set<std::string> still_used;
    collect_local_names_list(stmts, still_used);
    std::vector<std::string> to_remove;
    for (auto& [name, t] : ctx.crossing_temp_types) {
        if (!still_used.count(name)) to_remove.push_back(name);
    }
    for (auto& name : to_remove) ctx.crossing_temp_types.erase(name);
}

}  // namespace

// ==================== fallback_bytecode_listing / decompile_method_body ====================

std::vector<std::string> fallback_bytecode_listing(const ClassFile& cf, const Method& method, int indent) {
    std::string pad(4 * static_cast<size_t>(indent), ' ');
    std::vector<std::string> lines = {pad + "// -- не удалось безопасно декомпилировать тело метода, показан байткод --"};
    if (method.has_code) {
        auto disasm = disassemble(method.code, cf, &method);
        for (auto& dl : disasm) lines.push_back(pad + "// " + dl);
    }
    return lines;
}

MethodDecompileResult decompile_method_body(const ClassFile& cf, const Method& method, const IRenamer& renamer,
                                             const std::map<std::string, std::string>& known_internal_by_dotted,
                                             const std::string& class_internal, int indent,
                                             const std::map<std::string, std::vector<std::string>>& enum_ordinals,
                                             const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables) {
    MethodDecompileResult result;
    if (!method.has_code) {
        result.ok = true;
        return result;
    }

    try {
        DecodedMethod dm = decode_method(method.code);
        result.n_instructions = static_cast<int>(dm.order.size());
        auto [filtered_exceptions, junk_removed] = filter_junk_catches(method);
        CFG cfg(dm.instrs, dm.order, filtered_exceptions);
        result.n_blocks = static_cast<int>(cfg.blocks.size());
        result.junk_catches_removed = junk_removed;
        MethodCtx ctx(cf, method, renamer, known_internal_by_dotted, class_internal);

        std::map<int64_t, std::vector<ExprPtr>> seeds;
        for (auto& [start, blk] : cfg.blocks) {
            if (!blk.handler_types.empty()) seeds[start] = {std::make_shared<Local>(CAUGHT_SENTINEL, "Throwable")};
        }

        std::map<int64_t, BlockResult> results;
        std::map<int64_t, size_t> underflow_starts;
        for (auto& [start, blk] : cfg.blocks) {
            std::vector<ExprPtr> seed = seeds.count(start) ? seeds[start] : std::vector<ExprPtr>{};
            std::vector<ExprPtr> flag;
            BlockResult res = simulate_block(blk, seed, ctx, &flag);
            if (!flag.empty()) underflow_starts[start] = flag.size();
            results[start] = std::move(res);
        }

        std::map<int64_t, std::vector<ExprPtr>> producer_temps;

        std::function<std::vector<ExprPtr>(int64_t, size_t, std::vector<int64_t>)> ensure_depth;
        std::function<std::vector<ExprPtr>(int64_t, size_t, std::vector<int64_t>)> get_producer_temps;

        get_producer_temps = [&](int64_t pc, size_t needed, std::vector<int64_t> chain) -> std::vector<ExprPtr> {
            auto it = producer_temps.find(pc);
            if (it != producer_temps.end()) {
                if (it->second.size() != needed) throw DecompileAbort("несогласованная глубина пересечения стека между предшественниками");
                return it->second;
            }
            ensure_depth(pc, needed, chain);
            auto& cur_stack = results.at(pc).exit_stack;
            std::vector<ExprPtr> temps;
            for (size_t j = 0; j < needed; ++j) {
                const ExprPtr& sample = cur_stack.at(needed - 1 - j);
                std::string t = ctx.stack_temp_for(pc, static_cast<int64_t>(j), 'A');
                std::string sample_type = sample->type.empty() ? "Object" : sample->type;
                if (PSEUDO_TYPES.count(sample_type)) sample_type = "Object";
                ctx.crossing_temp_types[t] = sample_type;
                temps.push_back(std::make_shared<Local>(t, sample_type));
            }
            for (size_t j = 0; j < needed; ++j) {
                const ExprPtr& real = cur_stack.at(needed - 1 - j);
                results.at(pc).stmts.push_back(std::make_shared<ExprStmtNode>(std::make_shared<Assign>(temps[j], real)));
            }
            results.at(pc).exit_stack.clear();
            producer_temps[pc] = temps;
            return temps;
        };

        ensure_depth = [&](int64_t pc, size_t needed, std::vector<int64_t> chain) -> std::vector<ExprPtr> {
            if (std::find(chain.begin(), chain.end(), pc) != chain.end()) throw DecompileAbort("зацикленное пересечение стека между блоками");
            auto& cur_stack = results.at(pc).exit_stack;
            if (cur_stack.size() == needed) return {};
            if (cur_stack.size() > needed) throw DecompileAbort("несогласованная глубина пересечения стека между предшественниками");
            auto& preds = cfg.blocks.at(pc).preds;
            if (preds.empty()) {
                if (!cfg.blocks.at(pc).handler_types.empty()) {
                    throw DecompileAbort("пересечение стека упирается в обработчик исключений - не поддерживается");
                }
                throw DecompileAbort("унаследованное значение стека без предшественников");
            }
            size_t missing = needed - cur_stack.size();
            std::vector<int64_t> new_chain = chain;
            new_chain.push_back(pc);
            std::vector<ExprPtr> canonical = get_producer_temps(preds[0], missing, new_chain);
            for (size_t pi = 1; pi < preds.size(); ++pi) {
                int64_t p = preds[pi];
                std::vector<ExprPtr> own;
                if (producer_temps.count(p) || results.at(p).exit_stack.size() < missing) {
                    own = get_producer_temps(p, missing, new_chain);
                } else {
                    auto& pstack = results.at(p).exit_stack;
                    for (size_t j = 0; j < missing; ++j) own.push_back(pstack.at(missing - 1 - j));
                    results.at(p).exit_stack.clear();
                }
                for (size_t j = 0; j < canonical.size(); ++j) {
                    auto* tmp = static_cast<Local*>(canonical[j].get());
                    results.at(p).stmts.push_back(std::make_shared<ExprStmtNode>(
                        std::make_shared<Assign>(std::make_shared<Local>(tmp->name, tmp->type), own[j])));
                }
            }
            std::vector<ExprPtr> seed(canonical.rbegin(), canonical.rend());
            std::vector<ExprPtr> flag2;
            results[pc] = simulate_block(cfg.blocks.at(pc), seed, ctx, &flag2);
            if (!flag2.empty()) throw DecompileAbort("двойное пересечение стека не поддерживается");
            return {};
        };

        for (auto& [cpc, k] : underflow_starts) ensure_depth(cpc, k, {});

        for (auto& [start, res] : results) {
            if (!res.exit_stack.empty()) throw DecompileAbort("неразрешённый остаток на стеке в блоке " + std::to_string(start));
        }

        Structurer structurer(cfg, results, filtered_exceptions, ctx);
        auto stmts = structurer.build(*cfg.entry);
        stmts = fold_sync_blocks(stmts);
        stmts = simplify_stmts(stmts);
        if (method.name == "<init>") stmts = reorder_ctor_call_to_front(stmts);
        stmts = inline_single_use_crossing_temps(stmts, ctx);
        stmts = fold_array_literals(stmts);
        if (!enum_ordinals.empty() || !switchmap_tables.empty()) {
            stmts = desugar_enum_switches(stmts, enum_ordinals, ctx, switchmap_tables);
        }
        if (!stmts.empty() && stmts.back()->kind == StmtKind::ReturnStmt && !static_cast<ReturnStmt*>(stmts.back().get())->expr) {
            stmts.pop_back();
        }
        refresh_crossing_temp_types(stmts, ctx);

        std::map<std::string, std::string> declared_seed;
        for (auto& [idx, info] : ctx.locals) {
            if (info.is_param) declared_seed[info.name] = info.type;
        }
        for (auto& [name, t] : ctx.crossing_temp_types) declared_seed[name] = t;
        stmts = ensure_local_declarations(stmts, declared_seed);
        {
            std::set<std::string> declared_so_far;
            stmts = hoist_escaping_locals(stmts, declared_so_far);
        }
        prune_unused_imports(stmts, ctx);
        if (contains_unfolded_monitor(stmts)) throw DecompileAbort("synchronized-блок не свёрнут (monitorenter/monitorexit)");
        if (has_escaping_local_decl(stmts)) {
            throw DecompileAbort("переменная объявлена в блоке, но используется за его пределами "
                                  "(типично для switch(String) через hashCode) - структуризация ненадёжна");
        }

        std::string pad(4 * static_cast<size_t>(indent), ' ');
        std::vector<std::string> pre_lines;
        for (auto& [name, typ] : ctx.crossing_temp_types) pre_lines.push_back(pad + simple_type(typ) + " " + name + ";");

        std::vector<std::string> local_names;
        for (auto& [idx, info] : ctx.locals) local_names.push_back(info.name);
        set_shadow_context(local_names);
        auto body_lines = emit_stmts(stmts, indent);
        result.ok = true;
        result.stmts = stmts;
        result.pre_lines = pre_lines;
        result.java_lines = pre_lines;
        result.java_lines.insert(result.java_lines.end(), body_lines.begin(), body_lines.end());
        result.locals = ctx.locals;
        result.imports = ctx.imports;
        return result;
    } catch (const DecompileAbort& e) {
        result.ok = false;
        result.reason = std::string(e.what());
        return result;
    } catch (const std::exception& e) {
        result.ok = false;
        result.reason = std::string("внутренняя ошибка декомпилятора: ") + e.what();
        return result;
    }
}

}  // namespace nd
