// engine.cpp - см. engine.hpp. 1:1 порт engine.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
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
        if (!st) continue;
        if (st->kind == StmtKind::LocalDecl && names.count(static_cast<LocalDecl*>(st.get())->name)) {
            auto* ld = static_cast<LocalDecl*>(st.get());
            if (!types.count(ld->name) || types[ld->name].empty() || types[ld->name] == "Object") {
                if (!ld->type.empty()) types[ld->name] = ld->type;
            }
            if (ld->init) {
                out.push_back(std::make_shared<ExprStmtNode>(
                    std::make_shared<Assign>(std::make_shared<Local>(ld->name, ld->type), ld->init)));
            }
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

// ---------------- _collapse_string_switch ----------------
// НОВАЯ ФУНКЦИЯ (найдено сравнением декомпилированного вывода с настоящим
// исходником пользователя NanoForge - switch(String) в исходнике всегда
// компилируется javac в МЕХАНИЧЕСКИЙ паттерн: switch(x.hashCode()) с
// .equals()-проверкой на коллизии хэша внутри каждого case, назначающий
// индекс во временную int-переменную, и ВТОРОЙ switch по этому индексу с
// реальными телами case. Это не эвристика - строго один и тот же паттерн
// у ЛЮБОГО javac для ЛЮБОГО switch(String), безопасно распознавать и
// схлопывать обратно. Один из самых крупных источников нечитаемости (см.
// упомянутую ниже по коду ошибку "типично для switch(String) через
// hashCode" - эта же функция, если успешно схлопнёт паттерн ДО той
// проверки, заодно чинит часть случаев, раньше проваливавшихся в
// fallback из-за "утекающей" между двумя switch'ами индекс-переменной).

namespace {

// Достаёт X из `X.hashCode()`, если expr - ровно такой вызов, иначе nullptr.
Local* as_hashcode_target(const ExprPtr& e) {
    if (!e || e->kind != ExprKind::MethodCall) return nullptr;
    auto* mc = static_cast<MethodCall*>(e.get());
    if (mc->name != "hashCode" || mc->is_static || !mc->target || mc->target->kind != ExprKind::Local) return nullptr;
    return static_cast<Local*>(mc->target.get());
}

// Разбирает тело одного case первого (hashCode) switch'а:
//   if (!X.equals("лит")) break;
//   idxVar = N;
//   [break;]                       <- необязателен у последнего case перед пустым default
// Возвращает {строковый литерал, индекс}, либо nullopt, если тело не совпадает ТОЧНО.
std::optional<std::pair<std::string, std::string>> match_hash_case(const std::vector<StmtPtr>& body,
                                                                     const std::string& x_name,
                                                                     const std::string& idx_name) {
    if (body.empty()) return std::nullopt;

    // Форма A:
    //   if (!X.equals("лит")) break;
    //   idxVar = N;
    //   [break;]
    if (body.size() == 2 || body.size() == 3) {
        if (body[0]->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(body[0].get());
            if (!ifs->else_body.has_value() && ifs->then_body.size() == 1 && ifs->then_body[0]->kind == StmtKind::BreakStmt) {
                if (ifs->cond && ifs->cond->kind == ExprKind::UnOp) {
                    auto* neg = static_cast<UnOp*>(ifs->cond.get());
                    if (neg->op == "!" && neg->expr && neg->expr->kind == ExprKind::MethodCall) {
                        auto* eq = static_cast<MethodCall*>(neg->expr.get());
                        if (eq->name == "equals" && eq->args.size() == 1 && eq->target && eq->target->kind == ExprKind::Local) {
                            if (static_cast<Local*>(eq->target.get())->name == x_name && eq->args[0]->kind == ExprKind::Const) {
                                auto* lit = static_cast<Const*>(eq->args[0].get());
                                if (lit->type == "String" && body[1]->kind == StmtKind::ExprStmt) {
                                    auto* es = static_cast<ExprStmtNode*>(body[1].get());
                                    if (es->expr && es->expr->kind == ExprKind::Assign) {
                                        auto* asn = static_cast<Assign*>(es->expr.get());
                                        if (asn->op == "=" && asn->target && asn->target->kind == ExprKind::Local) {
                                            if (static_cast<Local*>(asn->target.get())->name == idx_name && asn->value && asn->value->kind == ExprKind::Const) {
                                                if (body.size() == 2 || (body.size() == 3 && body[2]->kind == StmtKind::BreakStmt)) {
                                                    return std::make_pair(lit->literal, static_cast<Const*>(asn->value.get())->literal);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Форма B:
    //   if (X.equals("лит")) { idxVar = N; [break;] }
    //   [break;]
    if (body.size() == 1 || body.size() == 2) {
        if (body[0]->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(body[0].get());
            if (!ifs->else_body.has_value() && !ifs->then_body.empty() && ifs->then_body[0]->kind == StmtKind::ExprStmt) {
                if (ifs->cond && ifs->cond->kind == ExprKind::MethodCall) {
                    auto* eq = static_cast<MethodCall*>(ifs->cond.get());
                    if (eq->name == "equals" && eq->args.size() == 1 && eq->target && eq->target->kind == ExprKind::Local) {
                        if (static_cast<Local*>(eq->target.get())->name == x_name && eq->args[0]->kind == ExprKind::Const) {
                            auto* lit = static_cast<Const*>(eq->args[0].get());
                            if (lit->type == "String") {
                                auto* es = static_cast<ExprStmtNode*>(ifs->then_body[0].get());
                                if (es->expr && es->expr->kind == ExprKind::Assign) {
                                    auto* asn = static_cast<Assign*>(es->expr.get());
                                    if (asn->op == "=" && asn->target && asn->target->kind == ExprKind::Local) {
                                        if (static_cast<Local*>(asn->target.get())->name == idx_name && asn->value && asn->value->kind == ExprKind::Const) {
                                            if (body.size() == 1 || (body.size() == 2 && body[1]->kind == StmtKind::BreakStmt)) {
                                                return std::make_pair(lit->literal, static_cast<Const*>(asn->value.get())->literal);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    return std::nullopt;
}

}  // namespace

std::vector<StmtPtr> collapse_string_switch(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    size_t i = 0;
    while (i < stmts.size()) {
        // рекурсия вглубь вложенных тел - тот же обход, что и hoist_escaping_locals
        auto& s = stmts[i];
        if (s->kind == StmtKind::IfStmt) {
            auto* n = static_cast<IfStmt*>(s.get());
            n->then_body = collapse_string_switch(n->then_body);
            if (n->else_body.has_value()) n->else_body = collapse_string_switch(*n->else_body);
        } else if (s->kind == StmtKind::WhileStmt) {
            static_cast<WhileStmt*>(s.get())->body = collapse_string_switch(static_cast<WhileStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            static_cast<DoWhileStmt*>(s.get())->body = collapse_string_switch(static_cast<DoWhileStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::ForStmt) {
            static_cast<ForStmt*>(s.get())->body = collapse_string_switch(static_cast<ForStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::SyncStmt) {
            static_cast<SyncStmt*>(s.get())->body = collapse_string_switch(static_cast<SyncStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::BlockStmt) {
            static_cast<BlockStmt*>(s.get())->stmts = collapse_string_switch(static_cast<BlockStmt*>(s.get())->stmts);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = collapse_string_switch(t->body);
            for (auto& c : t->catches) c.body = collapse_string_switch(c.body);
            if (t->finally_body.has_value()) t->finally_body = collapse_string_switch(*t->finally_body);
        } else if (s->kind == StmtKind::SwitchStmt) {
            for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) c.body = collapse_string_switch(c.body);
        }

        // ---- попытка распознать паттерн, начиная с позиции i ----
        // Форма A: [LocalDecl X=Y] [LocalDecl idx=-1] [SwitchStmt(X.hashCode())] [SwitchStmt(idx)]
        // Форма B (без алиаса X):  [LocalDecl idx=-1] [SwitchStmt(X.hashCode())] [SwitchStmt(idx)]
        for (int has_alias = 1; has_alias >= 0; --has_alias) {
            size_t base = i + static_cast<size_t>(has_alias);
            if (base + 2 >= stmts.size()) continue;
            std::string x_name;
            ExprPtr subject;
            if (has_alias) {
                if (stmts[i]->kind != StmtKind::LocalDecl) continue;
                auto* ad = static_cast<LocalDecl*>(stmts[i].get());
                if (ad->type != "String" || !ad->init) continue;
                x_name = ad->name;
                subject = ad->init;
            }
            if (stmts[base]->kind != StmtKind::LocalDecl) continue;
            auto* idxd = static_cast<LocalDecl*>(stmts[base].get());
            if (idxd->type != "int" || !idxd->init || idxd->init->kind != ExprKind::Const) continue;
            std::string idx_name = idxd->name;

            if (stmts[base + 1]->kind != StmtKind::SwitchStmt) continue;
            auto* hsw = static_cast<SwitchStmt*>(stmts[base + 1].get());
            Local* ht = as_hashcode_target(hsw->selector);
            if (!ht) continue;
            if (has_alias) {
                if (ht->name != x_name) continue;
            } else {
                x_name = ht->name;
                subject = std::make_shared<Local>(ht->name, ht->type);
            }

            // Каждый case первого switch'а обязан совпасть ТОЧНО - без
            // исключений, при первом же несовпадении вся форма отбрасывается
            // (никаких частичных трансформаций).
            std::map<std::string, std::string> idx_to_literal;  // "0" -> "\"message\""
            bool ok = true;
            for (auto& c : hsw->cases) {
                if (c.is_default) {
                    // default case может содержать только break; или быть пустым
                    bool default_ok = c.body.empty();
                    if (!default_ok) {
                        default_ok = true;
                        for (auto& ds : c.body) {
                            if (ds->kind != StmtKind::BreakStmt) { default_ok = false; break; }
                        }
                    }
                    if (!default_ok) { ok = false; break; }
                    continue;
                }
                auto m = match_hash_case(c.body, x_name, idx_name);
                if (!m.has_value()) { ok = false; break; }
                idx_to_literal[m->second] = m->first;
            }
            if (!ok || idx_to_literal.empty()) continue;

            if (base + 2 >= stmts.size() || stmts[base + 2]->kind != StmtKind::SwitchStmt) continue;
            auto* isw = static_cast<SwitchStmt*>(stmts[base + 2].get());
            if (!isw->selector || isw->selector->kind != ExprKind::Local) continue;
            if (static_cast<Local*>(isw->selector.get())->name != idx_name) continue;

            // Строим новые case: каждая числовая метка -> строковый литерал
            // из карты выше. Если хоть одна метка не найдена в карте (не
            // "наш" индекс-свитч, совпадение случайное) - отбрасываем форму.
            std::vector<SwitchCase> new_cases;
            for (auto& c : isw->cases) {
                SwitchCase nc;
                nc.is_default = c.is_default;
                nc.body = c.body;
                if (c.is_default) {
                    new_cases.push_back(std::move(nc));
                    continue;
                }
                for (auto& v : c.values) {
                    auto it = idx_to_literal.find(v);
                    if (it == idx_to_literal.end()) { ok = false; break; }
                    nc.values.push_back(it->second);
                }
                if (!ok) break;
                new_cases.push_back(std::move(nc));
            }
            if (!ok) continue;

            out.push_back(std::make_shared<SwitchStmt>(subject, std::move(new_cases)));
            i = base + 3;
            goto matched;
        }

        out.push_back(s);
        ++i;
    matched:;
    }
    return out;
}

// ---------------- _fold_if_else_ternary ----------------
// Преобразует паттерн:
//   if (cond) { x = A; } else { x = B; }
// в:
//   x = cond ? A : B;
// Безопасно: обе ветки состоят ровно из одного присваивания в ОДНУ и ту
// же переменную. Это один из основных источников "escaping local" -
// переменная x объявлена внутри if/else, используется после.

namespace {

struct SingleValueStmt {
    enum class Kind { Assign, Return } kind;
    ExprPtr target;  // nullptr for Return
    ExprPtr value;
};

bool exprs_match(const ExprPtr& a, const ExprPtr& b) {
    if (!a || !b) return false;
    if (a->kind != b->kind) return false;
    if (a->kind == ExprKind::Local) {
        return static_cast<Local*>(a.get())->name == static_cast<Local*>(b.get())->name;
    }
    if (a->kind == ExprKind::FieldAccess) {
        auto* fa1 = static_cast<FieldAccess*>(a.get());
        auto* fa2 = static_cast<FieldAccess*>(b.get());
        if (fa1->name != fa2->name || fa1->is_static != fa2->is_static) return false;
        if (fa1->is_static) return true;
        if (fa1->target && fa2->target) return exprs_match(fa1->target, fa2->target);
        return !fa1->target && !fa2->target;
    }
    if (a->kind == ExprKind::ArrayAccess) {
        auto* aa1 = static_cast<ArrayAccess*>(a.get());
        auto* aa2 = static_cast<ArrayAccess*>(b.get());
        if (!exprs_match(aa1->array, aa2->array)) return false;
        if (aa1->index->kind == ExprKind::Const && aa2->index->kind == ExprKind::Const) {
            return static_cast<Const*>(aa1->index.get())->literal == static_cast<Const*>(aa2->index.get())->literal;
        }
        if (aa1->index->kind == ExprKind::Local && aa2->index->kind == ExprKind::Local) {
            return static_cast<Local*>(aa1->index.get())->name == static_cast<Local*>(aa2->index.get())->name;
        }
    }
    return false;
}

std::optional<SingleValueStmt> extract_single_value(const std::vector<StmtPtr>& body) {
    if (body.size() != 1) return std::nullopt;
    if (body[0]->kind == StmtKind::ExprStmt) {
        auto* es = static_cast<ExprStmtNode*>(body[0].get());
        if (es->expr && es->expr->kind == ExprKind::Assign) {
            auto* a = static_cast<Assign*>(es->expr.get());
            if (a->op == "=" && a->target && a->value) {
                return SingleValueStmt{SingleValueStmt::Kind::Assign, a->target, a->value};
            }
        }
    } else if (body[0]->kind == StmtKind::LocalDecl) {
        auto* ld = static_cast<LocalDecl*>(body[0].get());
        if (ld->init) {
            return SingleValueStmt{SingleValueStmt::Kind::Assign, std::make_shared<Local>(ld->name, ld->type), ld->init};
        }
    } else if (body[0]->kind == StmtKind::ReturnStmt) {
        auto* rs = static_cast<ReturnStmt*>(body[0].get());
        if (rs->value) {
            return SingleValueStmt{SingleValueStmt::Kind::Return, nullptr, rs->value};
        }
    }
    return std::nullopt;
}

}  // namespace

std::vector<StmtPtr> fold_if_else_ternary(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (auto s : stmts) {
        // Рекурсия вглубь
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            i->then_body = fold_if_else_ternary(i->then_body);
            if (i->else_body.has_value()) i->else_body = fold_if_else_ternary(*i->else_body);
        } else if (s->kind == StmtKind::WhileStmt) {
            static_cast<WhileStmt*>(s.get())->body = fold_if_else_ternary(static_cast<WhileStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            static_cast<DoWhileStmt*>(s.get())->body = fold_if_else_ternary(static_cast<DoWhileStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::ForStmt) {
            static_cast<ForStmt*>(s.get())->body = fold_if_else_ternary(static_cast<ForStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::SyncStmt) {
            static_cast<SyncStmt*>(s.get())->body = fold_if_else_ternary(static_cast<SyncStmt*>(s.get())->body);
        } else if (s->kind == StmtKind::BlockStmt) {
            static_cast<BlockStmt*>(s.get())->stmts = fold_if_else_ternary(static_cast<BlockStmt*>(s.get())->stmts);
        } else if (s->kind == StmtKind::SwitchStmt) {
            for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) c.body = fold_if_else_ternary(c.body);
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            t->body = fold_if_else_ternary(t->body);
            for (auto& c : t->catches) c.body = fold_if_else_ternary(c.body);
            if (t->finally_body.has_value()) t->finally_body = fold_if_else_ternary(*t->finally_body);
        }

        // Проверяем: if (cond) { target = A; } else { target = B; } → target = cond ? A : B
        //           if (cond) { return A; } else { return B; } → return cond ? A : B;
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (i->else_body.has_value()) {
                auto thn = extract_single_value(i->then_body);
                auto els = extract_single_value(*i->else_body);
                if (thn.has_value() && els.has_value() && thn->kind == els->kind) {
                    if (thn->kind == SingleValueStmt::Kind::Assign && exprs_match(thn->target, els->target)) {
                        std::string rtype = thn->value->type;
                        if (rtype.empty()) rtype = els->value->type;
                        if (rtype.empty()) rtype = "Object";
                        auto ternary = std::make_shared<Ternary>(i->cond, thn->value, els->value, rtype);
                        out.push_back(std::make_shared<ExprStmtNode>(std::make_shared<Assign>(thn->target, ternary)));
                        continue;
                    }
                    if (thn->kind == SingleValueStmt::Kind::Return) {
                        std::string rtype = thn->value->type;
                        if (rtype.empty()) rtype = els->value->type;
                        if (rtype.empty()) rtype = "Object";
                        auto ternary = std::make_shared<Ternary>(i->cond, thn->value, els->value, rtype);
                        out.push_back(std::make_shared<ReturnStmt>(ternary));
                        continue;
                    }
                }
            }
        }
        out.push_back(s);
    }
    return out;
}

// ---------------- _eliminate_dead_locals ----------------
// Удаляет LocalDecl x = ..., если x больше НИГДЕ не используется ниже
// по списку (и не внутри вложенных блоков). Типичный случай: после
// collapse_string_switch осталось `int var8 = -1;` - больше нигде не
// используется (сам switch на var8 уже удалён/слит).

bool uses_name_in_expr(const ExprPtr& e, const std::string& name);

bool uses_name_in_stmts(const std::vector<StmtPtr>& stmts, const std::string& name) {
    for (auto& s : stmts) {
        if (s->kind == StmtKind::ExprStmt) {
            if (uses_name_in_expr(static_cast<ExprStmtNode*>(s.get())->expr, name)) return true;
        } else if (s->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(s.get());
            if (ld->name == name) return true;
            if (ld->init && uses_name_in_expr(ld->init, name)) return true;
        } else if (s->kind == StmtKind::ReturnStmt) {
            auto* r = static_cast<ReturnStmt*>(s.get());
            if (r->expr && uses_name_in_expr(r->expr, name)) return true;
        } else if (s->kind == StmtKind::ThrowStmt) {
            if (uses_name_in_expr(static_cast<ThrowStmt*>(s.get())->expr, name)) return true;
        } else if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            if (uses_name_in_expr(i->cond, name)) return true;
            if (uses_name_in_stmts(i->then_body, name)) return true;
            if (i->else_body.has_value() && uses_name_in_stmts(*i->else_body, name)) return true;
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            if (uses_name_in_expr(w->cond, name)) return true;
            if (uses_name_in_stmts(w->body, name)) return true;
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            if (uses_name_in_expr(w->cond, name)) return true;
            if (uses_name_in_stmts(w->body, name)) return true;
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            if (f->init && uses_name_in_expr(f->init, name)) return true;
            if (f->cond && uses_name_in_expr(f->cond, name)) return true;
            if (uses_name_in_stmts(f->body, name)) return true;
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            if (uses_name_in_expr(sw->selector, name)) return true;
            for (auto& c : sw->cases) {
                if (uses_name_in_stmts(c.body, name)) return true;
            }
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            if (uses_name_in_expr(sy->expr, name)) return true;
            if (uses_name_in_stmts(sy->body, name)) return true;
        } else if (s->kind == StmtKind::BlockStmt) {
            if (uses_name_in_stmts(static_cast<BlockStmt*>(s.get())->stmts, name)) return true;
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            if (uses_name_in_stmts(t->body, name)) return true;
            for (auto& c : t->catches) {
                if (uses_name_in_stmts(c.body, name)) return true;
            }
            if (t->finally_body.has_value() && uses_name_in_stmts(*t->finally_body, name)) return true;
        }
    }
    return false;
}

bool uses_name_in_expr(const ExprPtr& e, const std::string& name) {
    if (!e) return false;
    if (e->kind == ExprKind::Local) return static_cast<Local*>(e.get())->name == name;
    if (e->kind == ExprKind::Assign) {
        auto* a = static_cast<Assign*>(e.get());
        return uses_name_in_expr(a->target, name) || uses_name_in_expr(a->value, name);
    }
    if (e->kind == ExprKind::MethodCall) {
        auto* mc = static_cast<MethodCall*>(e.get());
        if (uses_name_in_expr(mc->target, name)) return true;
        for (auto& arg : mc->args) {
            if (uses_name_in_expr(arg, name)) return true;
        }
        return false;
    }
    if (e->kind == ExprKind::BinOp) {
        auto* b = static_cast<BinOp*>(e.get());
        return uses_name_in_expr(b->left, name) || uses_name_in_expr(b->right, name);
    }
    if (e->kind == ExprKind::UnOp) {
        return uses_name_in_expr(static_cast<UnOp*>(e.get())->expr, name);
    }
    if (e->kind == ExprKind::Ternary) {
        auto* t = static_cast<Ternary*>(e.get());
        return uses_name_in_expr(t->cond, name) || uses_name_in_expr(t->tval, name) || uses_name_in_expr(t->fval, name);
    }
    if (e->kind == ExprKind::Cast) {
        return uses_name_in_expr(static_cast<Cast*>(e.get())->expr, name);
    }
    if (e->kind == ExprKind::InstanceOf) {
        return uses_name_in_expr(static_cast<InstanceOf*>(e.get())->expr, name);
    }
    if (e->kind == ExprKind::FieldAccess) {
        return uses_name_in_expr(static_cast<FieldAccess*>(e.get())->target, name);
    }
    if (e->kind == ExprKind::ArrayAccess) {
        auto* aa = static_cast<ArrayAccess*>(e.get());
        return uses_name_in_expr(aa->array, name) || uses_name_in_expr(aa->index, name);
    }
    if (e->kind == ExprKind::NewArray) {
        auto* na = static_cast<NewArray*>(e.get());
        for (auto& d : na->dims) {
            if (uses_name_in_expr(d, name)) return true;
        }
        if (na->initializer.has_value()) {
            for (auto& v : *na->initializer) {
                if (uses_name_in_expr(v, name)) return true;
            }
        }
        return false;
    }
    if (e->kind == ExprKind::NewObject) {
        auto* no = static_cast<NewObject*>(e.get());
        for (auto& arg : no->args) {
            if (uses_name_in_expr(arg, name)) return true;
        }
        return false;
    }
    return false;
}

std::vector<StmtPtr> eliminate_dead_locals(const std::vector<StmtPtr>& stmts) {
    std::vector<StmtPtr> out;
    for (size_t i = 0; i < stmts.size(); ++i) {
        if (stmts[i]->kind == StmtKind::LocalDecl) {
            auto* ld = static_cast<LocalDecl*>(stmts[i].get());
            // Если init - чистое выражение (const/literal) без побочных эффектов,
            // и имя НЕ используется нигде дальше - пропускаем объявление
            bool init_pure = !ld->init || ld->init->kind == ExprKind::Const ||
                             (ld->init->kind == ExprKind::UnOp && static_cast<UnOp*>(ld->init.get())->expr->kind == ExprKind::Const);
            if (init_pure) {
                std::vector<StmtPtr> rest(stmts.begin() + static_cast<long>(i) + 1, stmts.end());
                if (!uses_name_in_stmts(rest, ld->name)) {
                    continue;  // мёртвый код - пропускаем
                }
            }
        }
        out.push_back(stmts[i]);
    }
    return out;
}

// ---------------- _collapse_stringbuilder ----------------
// НОВАЯ ФУНКЦИЯ (найдено сравнением декомпилированного вывода с настоящим
// исходником пользователя NanoForge - каждая строковая конкатенация "a + b"
// в исходнике компилируется javac в new StringBuilder().append(a).append(b)
// .toString() - тоже строго механический паттерн одного и того же вида у
// ЛЮБОГО javac, безопасно распознавать и схлопывать обратно в "a + b".
namespace {

bool is_stringbuilder_type(const std::string& t) {
    return t == "StringBuilder" || t == "java.lang.StringBuilder" || t == "StringBuffer" ||
           t == "java.lang.StringBuffer";
}

// Пытается распознать expr как ЗАВЕРШЁННУЮ цепочку
// new StringBuilder([initial]).append(a).append(b)....toString() -
// возвращает куски СЛЕВА НАПРАВО, либо nullopt при любом несовпадении.
std::optional<std::vector<ExprPtr>> try_match_sb_chain(const ExprPtr& e) {
    if (!e || e->kind != ExprKind::MethodCall) return std::nullopt;
    auto* top = static_cast<MethodCall*>(e.get());
    if (top->name != "toString" || !top->args.empty() || top->is_static || !top->target) return std::nullopt;

    std::vector<ExprPtr> pieces_rev;
    ExprPtr cur = top->target;
    while (true) {
        if (!cur) return std::nullopt;
        if (cur->kind == ExprKind::MethodCall) {
            auto* mc = static_cast<MethodCall*>(cur.get());
            if (mc->name != "append" || mc->args.size() != 1 || mc->is_static || !mc->target) return std::nullopt;
            pieces_rev.push_back(mc->args[0]);
            cur = mc->target;
            continue;
        }
        if (cur->kind == ExprKind::NewObject) {
            auto* no = static_cast<NewObject*>(cur.get());
            if (!is_stringbuilder_type(no->type)) return std::nullopt;
            if (no->args.size() == 1) {
                // Если аргумент int (initial capacity, напр. new StringBuilder(16)) - игнорируем как часть строки
                if (no->args[0]->type != "int") {
                    pieces_rev.push_back(no->args[0]);
                }
            } else if (!no->args.empty()) return std::nullopt;
            break;
        }
        return std::nullopt;
    }
    // Одна деталь (голое "new StringBuilder().append(a).toString()") -
    // такое эквивалентно просто "a", если a уже String, но безопаснее не
    // трогать редкий вырожденный случай, чем ошибиться с типом.
    if (pieces_rev.size() < 2) return std::nullopt;
    std::reverse(pieces_rev.begin(), pieces_rev.end());
    return pieces_rev;
}

}  // namespace

ExprPtr collapse_sb_chain(const ExprPtr& e) {
    if (!e) return e;
    auto pieces = try_match_sb_chain(e);
    if (pieces.has_value()) {
        for (auto& p : *pieces) p = collapse_sb_chain(p);  // вложенные цепочки внутри кусков
        ExprPtr result = (*pieces)[0];
        for (size_t k = 1; k < pieces->size(); ++k) result = std::make_shared<BinOp>("+", result, (*pieces)[k], "String");
        return result;
    }
    switch (e->kind) {
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            b->left = collapse_sb_chain(b->left);
            b->right = collapse_sb_chain(b->right);
            break;
        }
        case ExprKind::UnOp: static_cast<UnOp*>(e.get())->expr = collapse_sb_chain(static_cast<UnOp*>(e.get())->expr); break;
        case ExprKind::Assign: {
            auto* a = static_cast<Assign*>(e.get());
            a->target = collapse_sb_chain(a->target);
            a->value = collapse_sb_chain(a->value);
            break;
        }
        case ExprKind::Cast: static_cast<Cast*>(e.get())->expr = collapse_sb_chain(static_cast<Cast*>(e.get())->expr); break;
        case ExprKind::InstanceOf:
            static_cast<InstanceOf*>(e.get())->expr = collapse_sb_chain(static_cast<InstanceOf*>(e.get())->expr);
            break;
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            t->cond = collapse_sb_chain(t->cond);
            t->tval = collapse_sb_chain(t->tval);
            t->fval = collapse_sb_chain(t->fval);
            break;
        }
        case ExprKind::ArrayAccess: {
            auto* aa = static_cast<ArrayAccess*>(e.get());
            aa->array = collapse_sb_chain(aa->array);
            aa->index = collapse_sb_chain(aa->index);
            break;
        }
        case ExprKind::FieldAccess: {
            auto* fa = static_cast<FieldAccess*>(e.get());
            if (fa->target) fa->target = collapse_sb_chain(fa->target);
            break;
        }
        case ExprKind::MethodCall: {
            auto* mc = static_cast<MethodCall*>(e.get());
            if (mc->target) mc->target = collapse_sb_chain(mc->target);
            for (auto& a : mc->args) a = collapse_sb_chain(a);
            break;
        }
        case ExprKind::NewObject:
            for (auto& a : static_cast<NewObject*>(e.get())->args) a = collapse_sb_chain(a);
            break;
        case ExprKind::NewArray: {
            auto* na = static_cast<NewArray*>(e.get());
            for (auto& d : na->dims)
                if (d) d = collapse_sb_chain(d);
            if (na->initializer.has_value())
                for (auto& v : *na->initializer) v = collapse_sb_chain(v);
            break;
        }
        default:
            break;  // Const/Local/This/Raw/ClassLiteral/Lambda - листья, не трогаем
    }
    return e;
}

void collapse_sb_in_stmts(std::vector<StmtPtr>& stmts) {
    for (auto& s : stmts) {
        switch (s->kind) {
            case StmtKind::ExprStmt:
                static_cast<ExprStmtNode*>(s.get())->expr = collapse_sb_chain(static_cast<ExprStmtNode*>(s.get())->expr);
                break;
            case StmtKind::LocalDecl: {
                auto* ld = static_cast<LocalDecl*>(s.get());
                if (ld->init) ld->init = collapse_sb_chain(ld->init);
                break;
            }
            case StmtKind::ReturnStmt: {
                auto* r = static_cast<ReturnStmt*>(s.get());
                if (r->expr) r->expr = collapse_sb_chain(r->expr);
                break;
            }
            case StmtKind::ThrowStmt:
                static_cast<ThrowStmt*>(s.get())->expr = collapse_sb_chain(static_cast<ThrowStmt*>(s.get())->expr);
                break;
            case StmtKind::IfStmt: {
                auto* i = static_cast<IfStmt*>(s.get());
                i->cond = collapse_sb_chain(i->cond);
                collapse_sb_in_stmts(i->then_body);
                if (i->else_body.has_value()) collapse_sb_in_stmts(*i->else_body);
                break;
            }
            case StmtKind::WhileStmt: {
                auto* w = static_cast<WhileStmt*>(s.get());
                w->cond = collapse_sb_chain(w->cond);
                collapse_sb_in_stmts(w->body);
                break;
            }
            case StmtKind::DoWhileStmt: {
                auto* w = static_cast<DoWhileStmt*>(s.get());
                w->cond = collapse_sb_chain(w->cond);
                collapse_sb_in_stmts(w->body);
                break;
            }
            case StmtKind::ForStmt: {
                auto* f = static_cast<ForStmt*>(s.get());
                if (f->init) f->init = collapse_sb_chain(f->init);
                if (f->cond) f->cond = collapse_sb_chain(f->cond);
                if (f->update) {
                    std::vector<StmtPtr> one{f->update};
                    collapse_sb_in_stmts(one);
                    f->update = one[0];
                }
                collapse_sb_in_stmts(f->body);
                break;
            }
            case StmtKind::SwitchStmt: {
                auto* sw = static_cast<SwitchStmt*>(s.get());
                sw->selector = collapse_sb_chain(sw->selector);
                for (auto& c : sw->cases) collapse_sb_in_stmts(c.body);
                break;
            }
            case StmtKind::SyncStmt: {
                auto* sy = static_cast<SyncStmt*>(s.get());
                sy->expr = collapse_sb_chain(sy->expr);
                collapse_sb_in_stmts(sy->body);
                break;
            }
            case StmtKind::BlockStmt:
                collapse_sb_in_stmts(static_cast<BlockStmt*>(s.get())->stmts);
                break;
            case StmtKind::TryStmt: {
                auto* t = static_cast<TryStmt*>(s.get());
                collapse_sb_in_stmts(t->body);
                for (auto& c : t->catches) collapse_sb_in_stmts(c.body);
                if (t->finally_body.has_value()) collapse_sb_in_stmts(*t->finally_body);
                break;
            }
            default:
                break;
        }
    }
}

// ---------------- _hoist_escaping_locals ----------------

std::vector<StmtPtr> hoist_escaping_locals(const std::vector<StmtPtr>& stmts, std::set<std::string>& declared_so_far) {
    std::vector<StmtPtr> fixed;
    for (auto s : stmts) {
        if (!s) continue;
        if (s->kind == StmtKind::IfStmt) {
            auto* i = static_cast<IfStmt*>(s.get());
            auto branch_declared = declared_so_far;
            i->then_body = hoist_escaping_locals(i->then_body, branch_declared);
            if (i->else_body.has_value()) {
                auto else_declared = declared_so_far;
                i->else_body = hoist_escaping_locals(*i->else_body, else_declared);
            }
        } else if (s->kind == StmtKind::WhileStmt) {
            auto* w = static_cast<WhileStmt*>(s.get());
            auto branch_declared = declared_so_far;
            w->body = hoist_escaping_locals(w->body, branch_declared);
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto* w = static_cast<DoWhileStmt*>(s.get());
            auto branch_declared = declared_so_far;
            w->body = hoist_escaping_locals(w->body, branch_declared);
        } else if (s->kind == StmtKind::ForStmt) {
            auto* f = static_cast<ForStmt*>(s.get());
            auto branch_declared = declared_so_far;
            f->body = hoist_escaping_locals(f->body, branch_declared);
        } else if (s->kind == StmtKind::SyncStmt) {
            auto* sy = static_cast<SyncStmt*>(s.get());
            auto branch_declared = declared_so_far;
            sy->body = hoist_escaping_locals(sy->body, branch_declared);
        } else if (s->kind == StmtKind::BlockStmt) {
            auto* b = static_cast<BlockStmt*>(s.get());
            auto branch_declared = declared_so_far;
            b->stmts = hoist_escaping_locals(b->stmts, branch_declared);
        } else if (s->kind == StmtKind::SwitchStmt) {
            auto* sw = static_cast<SwitchStmt*>(s.get());
            for (auto& c : sw->cases) {
                auto branch_declared = declared_so_far;
                c.body = hoist_escaping_locals(c.body, branch_declared);
            }
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            auto try_declared = declared_so_far;
            t->body = hoist_escaping_locals(t->body, try_declared);
            for (auto& c : t->catches) {
                auto catch_declared = declared_so_far;
                c.body = hoist_escaping_locals(c.body, catch_declared);
            }
            if (t->finally_body.has_value()) {
                auto fin_declared = declared_so_far;
                t->finally_body = hoist_escaping_locals(*t->finally_body, fin_declared);
            }
        }
        fixed.push_back(s);
    }

    std::vector<StmtPtr> out;
    size_t n = fixed.size();
    for (size_t idx = 0; idx < n; ++idx) {
        StmtPtr s = fixed[idx];
        if (!s) continue;
        if (s->kind == StmtKind::LocalDecl) {
            declared_so_far.insert(static_cast<LocalDecl*>(s.get())->name);
        }
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
                for (auto& name : new_names) {
                    std::string typ = (types.count(name) && !types[name].empty()) ? types[name] : "Object";
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
        if (!s) continue;
        auto inner = inner_body_of(s);
        if (inner.has_value() && !inner->empty()) {
            auto declared = collect_declared_names(*inner);
            std::vector<StmtPtr> rest(lst.begin() + i + 1, lst.end());
            auto later = collect_shallow_referenced_names(rest);
            for (auto& d : declared) {
                if (later.count(d)) return true;
            }
        }
        if (s->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(s.get());
            if (has_escaping_local_decl_check(ifs->then_body)) return true;
            if (ifs->else_body.has_value() && has_escaping_local_decl_check(*ifs->else_body)) return true;
        } else if (s->kind == StmtKind::WhileStmt) {
            if (has_escaping_local_decl_check(static_cast<WhileStmt*>(s.get())->body)) return true;
        } else if (s->kind == StmtKind::DoWhileStmt) {
            if (has_escaping_local_decl_check(static_cast<DoWhileStmt*>(s.get())->body)) return true;
        } else if (s->kind == StmtKind::ForStmt) {
            if (has_escaping_local_decl_check(static_cast<ForStmt*>(s.get())->body)) return true;
        } else if (s->kind == StmtKind::SyncStmt) {
            if (has_escaping_local_decl_check(static_cast<SyncStmt*>(s.get())->body)) return true;
        } else if (s->kind == StmtKind::BlockStmt) {
            if (has_escaping_local_decl_check(static_cast<BlockStmt*>(s.get())->stmts)) return true;
        } else if (s->kind == StmtKind::SwitchStmt) {
            for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
                if (has_escaping_local_decl_check(c.body)) return true;
            }
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            if (has_escaping_local_decl_check(t->body)) return true;
            for (auto& c : t->catches) {
                if (has_escaping_local_decl_check(c.body)) return true;
            }
            if (t->finally_body.has_value() && has_escaping_local_decl_check(*t->finally_body)) return true;
        }
    }
    return false;
}

bool has_escaping_local_decl(const std::vector<StmtPtr>& stmts) { return has_escaping_local_decl_check(stmts); }

// ---------------- _collect_all_escaping_names & _hoist_all_escaping_to_root ----------------

std::set<std::string> collect_all_escaping_names(const std::vector<StmtPtr>& lst) {
    std::set<std::string> escaping;
    for (size_t i = 0; i < lst.size(); ++i) {
        StmtPtr s = lst[i];
        if (!s) continue;
        auto inner = inner_body_of(s);
        if (inner.has_value() && !inner->empty()) {
            auto declared = collect_declared_names(*inner);
            std::vector<StmtPtr> rest(lst.begin() + i + 1, lst.end());
            auto later = collect_shallow_referenced_names(rest);
            for (auto& d : declared) {
                if (later.count(d)) escaping.insert(d);
            }
        }
        if (s->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(s.get());
            auto sub = collect_all_escaping_names(ifs->then_body);
            escaping.insert(sub.begin(), sub.end());
            if (ifs->else_body.has_value()) {
                sub = collect_all_escaping_names(*ifs->else_body);
                escaping.insert(sub.begin(), sub.end());
            }
        } else if (s->kind == StmtKind::WhileStmt) {
            auto sub = collect_all_escaping_names(static_cast<WhileStmt*>(s.get())->body);
            escaping.insert(sub.begin(), sub.end());
        } else if (s->kind == StmtKind::DoWhileStmt) {
            auto sub = collect_all_escaping_names(static_cast<DoWhileStmt*>(s.get())->body);
            escaping.insert(sub.begin(), sub.end());
        } else if (s->kind == StmtKind::ForStmt) {
            auto sub = collect_all_escaping_names(static_cast<ForStmt*>(s.get())->body);
            escaping.insert(sub.begin(), sub.end());
        } else if (s->kind == StmtKind::SyncStmt) {
            auto sub = collect_all_escaping_names(static_cast<SyncStmt*>(s.get())->body);
            escaping.insert(sub.begin(), sub.end());
        } else if (s->kind == StmtKind::BlockStmt) {
            auto sub = collect_all_escaping_names(static_cast<BlockStmt*>(s.get())->stmts);
            escaping.insert(sub.begin(), sub.end());
        } else if (s->kind == StmtKind::SwitchStmt) {
            for (auto& c : static_cast<SwitchStmt*>(s.get())->cases) {
                auto sub = collect_all_escaping_names(c.body);
                escaping.insert(sub.begin(), sub.end());
            }
        } else if (s->kind == StmtKind::TryStmt) {
            auto* t = static_cast<TryStmt*>(s.get());
            auto sub = collect_all_escaping_names(t->body);
            escaping.insert(sub.begin(), sub.end());
            for (auto& c : t->catches) {
                sub = collect_all_escaping_names(c.body);
                escaping.insert(sub.begin(), sub.end());
            }
            if (t->finally_body.has_value()) {
                sub = collect_all_escaping_names(*t->finally_body);
                escaping.insert(sub.begin(), sub.end());
            }
        }
    }
    return escaping;
}

std::vector<StmtPtr> hoist_all_escaping_to_root(std::vector<StmtPtr> stmts, MethodCtx& ctx) {
    auto escaping = collect_all_escaping_names(stmts);
    if (escaping.empty()) return stmts;
    std::map<std::string, std::string> types;
    for (auto& [slot, info] : ctx.locals) {
        if (!info.name.empty() && !info.type.empty()) types[info.name] = info.type;
    }
    for (auto& [name, typ] : ctx.crossing_temp_types) {
        if (!name.empty() && !typ.empty()) types[name] = typ;
    }
    stmts = strip_decl_to_assign(stmts, escaping, types);
    std::set<std::string> root_declared;
    for (auto& s : stmts) {
        if (s && s->kind == StmtKind::LocalDecl) {
            root_declared.insert(static_cast<LocalDecl*>(s.get())->name);
        }
    }
    for (auto& [slot, info] : ctx.locals) {
        if (info.is_param) root_declared.insert(info.name);
    }
    std::vector<StmtPtr> prefix;
    for (auto& name : escaping) {
        if (root_declared.count(name)) continue;
        std::string typ = (types.count(name) && !types[name].empty()) ? types[name] : "Object";
        prefix.push_back(std::make_shared<LocalDecl>(typ, name, nullptr));
        root_declared.insert(name);
    }
    if (!prefix.empty()) {
        size_t insert_pos = 0;
        if (!stmts.empty() && stmts[0] && stmts[0]->kind == StmtKind::ExprStmt) {
            auto* es = static_cast<ExprStmtNode*>(stmts[0].get());
            if (es->expr && es->expr->kind == ExprKind::MethodCall && static_cast<MethodCall*>(es->expr.get())->is_ctor) {
                insert_pos = 1;
            }
        }
        stmts.insert(stmts.begin() + insert_pos, prefix.begin(), prefix.end());
    }
    return stmts;
}

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
    if (t->catches.size() == 1 && !t->finally_body.has_value()) {
        if (is_monitor_rethrow_catch(t->catches[0].var_name, t->catches[0].body, key)) return t->body;
    }
    // Также поддерживаем форму try { ... } finally { monitorexit(key); }
    if (t->finally_body.has_value() && t->catches.empty()) {
        bool has_exit = false;
        for (auto& fs : *t->finally_body) {
            if (auto* mm = dynamic_cast<MonitorMarkerStmt*>(fs.get())) {
                if (mm->kind == "exit" && expr_key(mm->expr) == key) { has_exit = true; break; }
            }
        }
        if (has_exit) return t->body;
    }
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

std::vector<StmtPtr> collapse_adjacent_monitors(std::vector<StmtPtr> stmts) {
    std::vector<StmtPtr> out;
    for (size_t i = 0; i < stmts.size(); ++i) {
        if (i + 1 < stmts.size()) {
            auto* m1 = dynamic_cast<MonitorMarkerStmt*>(stmts[i].get());
            auto* m2 = dynamic_cast<MonitorMarkerStmt*>(stmts[i + 1].get());
            if (m1 && m2 && m1->kind == "enter" && m2->kind == "exit") {
                out.push_back(std::make_shared<SyncStmt>(m1->expr, std::vector<StmtPtr>{}));
                i += 1;
                continue;
            }
        }
        out.push_back(stmts[i]);
    }
    return out;
}

bool contains_unfolded_monitor(const std::vector<StmtPtr>& stmts) { return contains_unfolded_monitor_list(stmts); }

// ---------------- enum switch desugaring ----------------

void try_desugar_one(SwitchStmt* switch_stmt, const std::map<std::string, std::vector<std::string>>& enum_ordinals,
                      MethodCtx& ctx,
                      const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables) {
    ExprPtr sel = switch_stmt->selector;
    if (sel->kind != ExprKind::ArrayAccess) return;
    auto* aa = static_cast<ArrayAccess*>(sel.get());
    if (!aa->array) return;

    std::string field_name;
    std::optional<std::string> owner_name;
    if (aa->array->kind == ExprKind::FieldAccess) {
        auto* fa = static_cast<FieldAccess*>(aa->array.get());
        field_name = fa->name;
        owner_name = fa->owner;
    } else if (aa->array->kind == ExprKind::MethodCall) {
        auto* mca = static_cast<MethodCall*>(aa->array.get());
        field_name = mca->name;
        owner_name = mca->owner;
    } else {
        return;
    }

    ExprPtr idx = aa->index;
    if (!idx || idx->kind != ExprKind::MethodCall) return;
    auto* mc = static_cast<MethodCall*>(idx.get());
    if (!(mc->name == "ordinal" && mc->args.empty() && mc->target)) return;
    ExprPtr enum_expr = mc->target;
    std::string enum_type = enum_expr->type;

    std::optional<std::map<int64_t, std::string>> exact;
    auto find_table = [&](const std::string& owner_str, const std::string& fname) -> std::optional<std::map<int64_t, std::string>> {
        auto it = switchmap_tables.find({owner_str, fname});
        if (it != switchmap_tables.end()) return it->second;
        std::string slashed = owner_str;
        for (char& c : slashed) if (c == '.') c = '/';
        it = switchmap_tables.find({slashed, fname});
        if (it != switchmap_tables.end()) return it->second;
        for (auto& [pair, tbl] : switchmap_tables) {
            if (pair.second == fname) {
                if (pair.first == owner_str || pair.first == slashed) return tbl;
                if (!owner_str.empty() && pair.first.size() > owner_str.size() &&
                    pair.first.rfind("/" + owner_str) == pair.first.size() - owner_str.size() - 1) return tbl;
                if (!owner_str.empty() && pair.first.size() > owner_str.size() &&
                    pair.first.rfind("." + owner_str) == pair.first.size() - owner_str.size() - 1) return tbl;
            }
        }
        int matches = 0;
        const std::map<int64_t, std::string>* candidate = nullptr;
        for (auto& [pair, tbl] : switchmap_tables) {
            if (pair.second == fname) {
                matches++;
                candidate = &tbl;
            }
        }
        if (matches == 1 && candidate) return *candidate;
        return std::nullopt;
    };

    if (!field_name.empty()) {
        exact = find_table(owner_name.value_or(""), field_name);
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

    if (enum_type.empty()) return;
    std::string enum_type_base = enum_type;
    while (enum_type_base.size() >= 2 && enum_type_base.substr(enum_type_base.size() - 2) == "[]") {
        enum_type_base = enum_type_base.substr(0, enum_type_base.size() - 2);
    }

    const std::vector<std::string>* names_ptr = nullptr;
    auto ord_it = enum_ordinals.find(enum_type_base);
    if (ord_it != enum_ordinals.end()) {
        names_ptr = &ord_it->second;
    } else {
        std::string slashed = enum_type_base;
        for (char& c : slashed) if (c == '.') c = '/';
        ord_it = enum_ordinals.find(slashed);
        if (ord_it != enum_ordinals.end()) {
            names_ptr = &ord_it->second;
        } else {
            auto known_it = ctx.known.find(enum_type_base);
            if (known_it != ctx.known.end()) {
                ord_it = enum_ordinals.find(known_it->second);
                if (ord_it != enum_ordinals.end()) names_ptr = &ord_it->second;
            }
            if (!names_ptr) {
                for (auto& [k, v] : enum_ordinals) {
                    if (k.size() > enum_type_base.size() &&
                        k.rfind("/" + enum_type_base) == k.size() - enum_type_base.size() - 1) {
                        names_ptr = &v;
                        break;
                    }
                }
            }
        }
    }
    if (!names_ptr || names_ptr->empty()) return;
    const std::vector<std::string>& names = *names_ptr;

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
    std::vector<StmtPtr> work = lst;
    bool changed = true;
    while (changed) {
        changed = false;
        std::vector<StmtPtr> out;
        size_t i = 0, n = work.size();
        while (i < n) {
            StmtPtr cur = work[i];
            StmtPtr nxt = (i + 1 < n) ? work[i + 1] : nullptr;
            if (cur->kind == StmtKind::ExprStmt && nxt) {
                auto* es = static_cast<ExprStmtNode*>(cur.get());
                if (es->expr && es->expr->kind == ExprKind::Assign) {
                    auto* a = static_cast<Assign*>(es->expr.get());
                    if (a->target && a->target->kind == ExprKind::Local) {
                        std::string tname = static_cast<Local*>(a->target.get())->name;
                        if (ctx.crossing_temp_types.count(tname) || (tname.rfind("__stk", 0) == 0) || (tname.rfind("__temp", 0) == 0)) {
                            std::vector<StmtPtr> rest(work.begin() + i + 1, work.end());
                            if (count_local_uses_list(rest, tname) == 1) {
                                if (nxt->kind == StmtKind::ReturnStmt) {
                                    auto* r = static_cast<ReturnStmt*>(nxt.get());
                                    if (r->expr && r->expr->kind == ExprKind::Local && static_cast<Local*>(r->expr.get())->name == tname) {
                                        out.push_back(std::make_shared<ReturnStmt>(coerce_arg(a->value, ctx.ret_type)));
                                        i += 2;
                                        changed = true;
                                        continue;
                                    }
                                }
                                StmtPtr nxt_mod = nxt;
                                if (substitute_local_once_stmt(nxt_mod, tname, a->value)) {
                                    out.push_back(nxt_mod);
                                    i += 2;
                                    changed = true;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            out.push_back(cur);
            i += 1;
        }
        work = std::move(out);
    }
    return work;
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

std::vector<std::string> fallback_bytecode_listing(const ClassFile& cf, const Method& method, int indent,
                                                   const std::optional<std::string>& reason) {
    std::string pad(4 * static_cast<size_t>(indent), ' ');
    std::vector<std::string> lines = {pad + "// -- не удалось безопасно декомпилировать тело метода, показан байткод --"};
    if (reason.has_value() && !reason->empty()) {
        lines.push_back(pad + "// Причина: " + *reason);
    }
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
                if (it->second.size() == needed) return it->second;
                if (it->second.size() > needed) {
                    return std::vector<ExprPtr>(it->second.begin(), it->second.begin() + needed);
                }
                std::vector<ExprPtr> res = it->second;
                size_t missing_more = needed - res.size();
                for (size_t k = 0; k < missing_more; ++k) {
                    std::string t = ctx.new_temp('A');
                    ctx.crossing_temp_types[t] = "Object";
                    res.push_back(std::make_shared<Local>(t, "Object"));
                }
                producer_temps[pc] = res;
                return res;
            }
            ensure_depth(pc, needed, chain);
            auto& cur_stack = results.at(pc).exit_stack;
            std::vector<ExprPtr> temps;
            for (size_t j = 0; j < needed; ++j) {
                const ExprPtr& sample = (j < cur_stack.size()) ? cur_stack.at(cur_stack.size() - 1 - j) : nullptr;
                std::string t = ctx.stack_temp_for(pc, static_cast<int64_t>(j), 'A');
                std::string sample_type = (sample && !sample->type.empty()) ? sample->type : "Object";
                if (PSEUDO_TYPES.count(sample_type)) sample_type = "Object";
                ctx.crossing_temp_types[t] = sample_type;
                temps.push_back(std::make_shared<Local>(t, sample_type));
            }
            for (size_t j = 0; j < needed; ++j) {
                if (j < cur_stack.size()) {
                    const ExprPtr& real = cur_stack.at(cur_stack.size() - 1 - j);
                    results.at(pc).stmts.push_back(std::make_shared<ExprStmtNode>(std::make_shared<Assign>(temps[j], real)));
                }
            }
            results.at(pc).exit_stack.clear();
            producer_temps[pc] = temps;
            return temps;
        };

        ensure_depth = [&](int64_t pc, size_t needed, std::vector<int64_t> chain) -> std::vector<ExprPtr> {
            if (std::find(chain.begin(), chain.end(), pc) != chain.end()) {
                std::vector<ExprPtr> synthetic;
                for (size_t k = 0; k < needed; ++k) {
                    std::string t = ctx.new_temp('A');
                    ctx.crossing_temp_types[t] = "Object";
                    synthetic.push_back(std::make_shared<Local>(t, "Object"));
                }
                return synthetic;
            }
            auto& cur_stack = results.at(pc).exit_stack;
            if (cur_stack.size() >= needed) return {};
            auto& preds = cfg.blocks.at(pc).preds;
            if (preds.empty()) {
                size_t missing = needed - cur_stack.size();
                std::vector<ExprPtr> synthetic;
                for (size_t k = 0; k < missing; ++k) {
                    std::string t = ctx.new_temp('A');
                    ctx.crossing_temp_types[t] = "Object";
                    synthetic.push_back(std::make_shared<Local>(t, "Object"));
                }
                std::vector<ExprPtr> seed(synthetic.rbegin(), synthetic.rend());
                std::vector<ExprPtr> flag2;
                results[pc] = simulate_block(cfg.blocks.at(pc), seed, ctx, &flag2);
                return synthetic;
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
            while (!flag2.empty() && canonical.size() < 16) {
                size_t more_needed = flag2.size();
                flag2.clear();
                std::vector<ExprPtr> more_canonical = get_producer_temps(preds[0], more_needed, new_chain);
                for (size_t pi = 1; pi < preds.size(); ++pi) {
                    int64_t p = preds[pi];
                    std::vector<ExprPtr> own;
                    if (producer_temps.count(p) || results.at(p).exit_stack.size() < more_needed) {
                        own = get_producer_temps(p, more_needed, new_chain);
                    } else {
                        auto& pstack = results.at(p).exit_stack;
                        for (size_t j = 0; j < more_needed; ++j) own.push_back(pstack.at(more_needed - 1 - j));
                        results.at(p).exit_stack.clear();
                    }
                    for (size_t j = 0; j < more_canonical.size(); ++j) {
                        auto* tmp = static_cast<Local*>(more_canonical[j].get());
                        results.at(p).stmts.push_back(std::make_shared<ExprStmtNode>(
                            std::make_shared<Assign>(std::make_shared<Local>(tmp->name, tmp->type), own[j])));
                    }
                }
                canonical.insert(canonical.begin(), more_canonical.begin(), more_canonical.end());
                std::vector<ExprPtr> new_seed(canonical.rbegin(), canonical.rend());
                results[pc] = simulate_block(cfg.blocks.at(pc), new_seed, ctx, &flag2);
            }
            if (!flag2.empty()) {
                for (size_t k = 0; k < flag2.size(); ++k) {
                    std::string t = ctx.new_temp('A');
                    ctx.crossing_temp_types[t] = "Object";
                    canonical.insert(canonical.begin(), std::make_shared<Local>(t, "Object"));
                }
                std::vector<ExprPtr> final_seed(canonical.rbegin(), canonical.rend());
                results[pc] = simulate_block(cfg.blocks.at(pc), final_seed, ctx, nullptr);
            }
            return {};
        };

        for (auto& [cpc, k] : underflow_starts) ensure_depth(cpc, k, {});

        for (auto& [start, res] : results) {
            if (!res.exit_stack.empty()) {
                for (auto& left : res.exit_stack) {
                    if (left) {
                        std::string t = ctx.new_temp('A');
                        std::string styp = left->type.empty() || PSEUDO_TYPES.count(left->type) ? "Object" : left->type;
                        ctx.crossing_temp_types[t] = styp;
                        res.stmts.push_back(std::make_shared<ExprStmtNode>(
                            std::make_shared<Assign>(std::make_shared<Local>(t, styp), left)));
                    }
                }
                res.exit_stack.clear();
            }
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
        stmts = fold_if_else_ternary(stmts);
        {
            std::set<std::string> declared_so_far;
            for (int pass = 0; pass < 3; ++pass) {
                stmts = hoist_escaping_locals(stmts, declared_so_far);
            }
        }
        // НОВОЕ: схлопываем javac-паттерн switch(x.hashCode())+.equals() обратно
        // в нормальный switch(String) - см. collapse_string_switch выше. ДО
        // проверки has_escaping_local_decl ниже - если паттерн распознан и
        // схлопнут, индекс-переменная (var8 и т.п.) вообще исчезает из AST,
        // и часть методов, раньше падавших в fallback именно из-за неё,
        // теперь пройдут структуризацию успешно.
        stmts = collapse_string_switch(stmts);
        stmts = eliminate_dead_locals(stmts);
        collapse_sb_in_stmts(stmts);
        prune_unused_imports(stmts, ctx);
        stmts = collapse_adjacent_monitors(stmts);
        // Не выбрасываем исключение, если единичный монитор не удалось схлопнуть:
        // emit.cpp безопасно выведет комментарий /* monitorenter/exit */, сохранив чистый Java AST!
        for (int hoist_pass = 0; hoist_pass < 4 && has_escaping_local_decl(stmts); ++hoist_pass) {
            stmts = hoist_all_escaping_to_root(stmts, ctx);
        }
        if (has_escaping_local_decl(stmts)) {
            auto all_escaping = collect_all_escaping_names(stmts);
            if (!all_escaping.empty()) {
                std::map<std::string, std::string> types;
                for (auto& [slot, info] : ctx.locals) {
                    if (!info.name.empty() && !info.type.empty()) types[info.name] = info.type;
                }
                for (auto& [name, typ] : ctx.crossing_temp_types) {
                    if (!name.empty() && !typ.empty()) types[name] = typ;
                }
                stmts = strip_decl_to_assign(stmts, all_escaping, types);
                std::set<std::string> root_declared;
                for (auto& s : stmts) {
                    if (s && s->kind == StmtKind::LocalDecl) root_declared.insert(static_cast<LocalDecl*>(s.get())->name);
                }
                for (auto& [slot, info] : ctx.locals) {
                    if (info.is_param) root_declared.insert(info.name);
                }
                std::vector<StmtPtr> prefix;
                for (auto& name : all_escaping) {
                    if (root_declared.count(name)) continue;
                    std::string typ = (types.count(name) && !types[name].empty()) ? types[name] : "Object";
                    prefix.push_back(std::make_shared<LocalDecl>(typ, name, nullptr));
                    root_declared.insert(name);
                }
                if (!prefix.empty()) {
                    size_t insert_pos = 0;
                    if (!stmts.empty() && stmts[0] && stmts[0]->kind == StmtKind::ExprStmt) {
                        auto* es = static_cast<ExprStmtNode*>(stmts[0].get());
                        if (es->expr && es->expr->kind == ExprKind::MethodCall && static_cast<MethodCall*>(es->expr.get())->is_ctor) {
                            insert_pos = 1;
                        }
                    }
                    stmts.insert(stmts.begin() + insert_pos, prefix.begin(), prefix.end());
                }
            }
        }

        refresh_crossing_temp_types(stmts, ctx);

        std::string pad(4 * static_cast<size_t>(indent), ' ');
        std::vector<std::string> pre_lines;
        for (auto& [name, typ] : ctx.crossing_temp_types) pre_lines.push_back(pad + simple_type(typ) + " " + name + ";");

        std::vector<std::string> local_names;
        for (auto& [idx, info] : ctx.locals) local_names.push_back(info.name);
        set_shadow_context(local_names);
        // НОВОЕ v1.7.6: пересборка switch(String) - ДО emit_stmts, чтобы
        // и текстовый вывод, и result.stmts (используется, например, для
        // реконструкции enum-констант в render_class.cpp) видели уже
        // слитый switch, а не исходную hashCode-форму javac.
        collapse_string_switches(stmts);
        // НОВОЕ v1.8.0 (HANDOFF_URGENT п.14): та же логика "ДО
        // emit_stmts" - сворачиваем `new T[N]; x[0]=..; x[1]=..;` в
        // `new T[]{..}` перед выводом.
        collapse_array_literals(stmts);
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
