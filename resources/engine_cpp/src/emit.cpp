// emit.cpp - см. emit.hpp. 1:1 порт emit.py.
#include "emit.hpp"

#include <set>
#include <sstream>

#include "javatypes.hpp"

namespace nd {

namespace {
const std::string IND = "    ";
std::set<std::string> g_shadowed_names;
std::optional<std::string> g_current_class_dotted;

std::string simple(const std::string& dotted) {
    // Не сводим к simple-имени прямо сейчас: mark_type оборачивает в
    // маркер \x01...\x02, решение simple vs FQN - финальным проходом по
    // всему файлу класса (см. javatypes.hpp resolve_type_markers,
    // render_class.cpp - портирован, HANDOFF_42).
    return mark_type(dotted);
}

std::string paren(const ExprPtr& sub, const Expr* parent, char side = 0) {
    std::string txt = emit_expr(sub);
    int sp = sub->prec();
    int pp = parent->prec();
    if (sp < pp) return "(" + txt + ")";
    if (sp == pp && side == 'r' && parent->kind == ExprKind::BinOp) {
        const auto* bo = static_cast<const BinOp*>(parent);
        if (bo->op == "-" || bo->op == "/" || bo->op == "%" || bo->op == "<<" || bo->op == ">>" || bo->op == ">>>") {
            return "(" + txt + ")";
        }
    }
    return txt;
}

// Как paren(), но сравнивает не с parent->prec() (у Cast это теперь 100 -
// см. HANDOFF_49/ast_nodes.hpp, чтобы САМ Cast не оборачивался в лишние
// скобки вызывающим кодом), а с явным порогом - нужен для внутренней
// проверки "нужны ли скобки ОПЕРАНДУ каста" (`(Type) expr`), где порог
// исторически другой (85 - как было у Cast::prec() до этой правки).
std::string paren_at(const ExprPtr& sub, int threshold) {
    std::string txt = emit_expr(sub);
    return sub->prec() < threshold ? ("(" + txt + ")") : txt;
}

std::string join(const std::vector<std::string>& parts, const std::string& sep) {
    std::string out;
    for (size_t i = 0; i < parts.size(); ++i) {
        if (i) out += sep;
        out += parts[i];
    }
    return out;
}

}  // namespace

void set_current_class(const std::optional<std::string>& dotted) { g_current_class_dotted = dotted; }

void set_shadow_context(const std::vector<std::string>& local_names) {
    g_shadowed_names = std::set<std::string>(local_names.begin(), local_names.end());
}
void clear_shadow_context() { g_shadowed_names.clear(); }

std::string emit_expr(const ExprPtr& e) {
    if (!e) return "/* ? NoneType */";
    switch (e->kind) {
        case ExprKind::Const:
            return static_cast<const Const*>(e.get())->literal;
        case ExprKind::Local:
            return static_cast<const Local*>(e.get())->name;
        case ExprKind::This:
            return "this";
        case ExprKind::FieldAccess: {
            const auto* f = static_cast<const FieldAccess*>(e.get());
            if (f->is_static) {
                if (f->owner.has_value() && f->owner == g_current_class_dotted) return f->name;
                return simple(f->owner.value_or("")) + "." + f->name;
            }
            if (f->target && f->target->kind == ExprKind::This && !g_shadowed_names.count(f->name)) {
                return f->name;
            }
            return paren(f->target, f) + "." + f->name;
        }
        case ExprKind::ArrayAccess: {
            const auto* a = static_cast<const ArrayAccess*>(e.get());
            return paren(a->array, a) + "[" + emit_expr(a->index) + "]";
        }
        case ExprKind::MethodCall: {
            const auto* m = static_cast<const MethodCall*>(e.get());
            std::vector<std::string> arg_strs;
            for (auto& a : m->args) arg_strs.push_back(emit_expr(a));
            std::string args = join(arg_strs, ", ");
            if (m->is_ctor) return m->name + "(" + args + ")";
            if (m->is_static) {
                if (m->owner.has_value() && m->owner == g_current_class_dotted) return m->name + "(" + args + ")";
                return simple(m->owner.value_or("")) + "." + m->name + "(" + args + ")";
            }
            if (m->is_super) return "super." + m->name + "(" + args + ")";
            if (m->target && m->target->kind == ExprKind::This) return m->name + "(" + args + ")";
            return paren(m->target, m) + "." + m->name + "(" + args + ")";
        }
        case ExprKind::NewObject: {
            const auto* n = static_cast<const NewObject*>(e.get());
            std::vector<std::string> arg_strs;
            for (auto& a : n->args) arg_strs.push_back(emit_expr(a));
            return "new " + simple(n->type) + "(" + join(arg_strs, ", ") + ")";
        }
        case ExprKind::NewArray: {
            const auto* n = static_cast<const NewArray*>(e.get());
            std::string base = n->elem_type;
            int extra = 0;
            while (base.size() >= 2 && base.substr(base.size() - 2) == "[]") {
                base = base.substr(0, base.size() - 2);
                extra += 1;
            }
            std::string extra_brackets;
            for (int i = 0; i < extra; ++i) extra_brackets += "[]";
            if (n->initializer.has_value()) {
                std::vector<std::string> item_strs;
                for (auto& v : *n->initializer) item_strs.push_back(emit_expr(v));
                return "new " + simple(base) + "[]" + extra_brackets + "{" + join(item_strs, ", ") + "}";
            }
            std::string dims_txt;
            for (auto& d : n->dims) {
                dims_txt += d ? ("[" + emit_expr(d) + "]") : "[]";
            }
            return "new " + simple(base) + dims_txt + extra_brackets;
        }
        case ExprKind::Cast: {
            const auto* c = static_cast<const Cast*>(e.get());
            return "((" + simple(c->type) + ") " + paren_at(c->expr, 85) + ")";
        }
        case ExprKind::InstanceOf: {
            const auto* io = static_cast<const InstanceOf*>(e.get());
            return paren(io->expr, io) + " instanceof " + simple(io->check_type);
        }
        case ExprKind::BinOp: {
            const auto* b = static_cast<const BinOp*>(e.get());
            return paren(b->left, b, 'l') + " " + b->op + " " + paren(b->right, b, 'r');
        }
        case ExprKind::UnOp: {
            const auto* u = static_cast<const UnOp*>(e.get());
            if (u->op == "++" || u->op == "--") {
                std::string inner = emit_expr(u->expr);
                return u->postfix ? (inner + u->op) : (u->op + inner);
            }
            return u->op + paren(u->expr, u);
        }
        case ExprKind::Ternary: {
            const auto* t = static_cast<const Ternary*>(e.get());
            return paren(t->cond, t) + " ? " + paren(t->tval, t) + " : " + paren(t->fval, t);
        }
        case ExprKind::Assign: {
            const auto* a = static_cast<const Assign*>(e.get());
            return emit_expr(a->target) + " " + a->op + " " + emit_expr(a->value);
        }
        case ExprKind::ClassLiteral:
            return simple(static_cast<const ClassLiteral*>(e.get())->type_name) + ".class";
        case ExprKind::Lambda: {
            const auto* l = static_cast<const Lambda*>(e.get());
            std::vector<std::string> pnames;
            for (auto& p : l->params) {
                if (p->kind == ExprKind::Local) pnames.push_back(static_cast<const Local*>(p.get())->name);
            }
            std::string params = join(pnames, ", ");
            std::string header = (l->params.size() == 1) ? params : ("(" + params + ")");
            return header + " -> " + emit_expr(l->body_method_ref);
        }
        case ExprKind::Raw:
            return static_cast<const Raw*>(e.get())->text;
    }
    return "/* ? unknown expr */";
}

std::vector<std::string> emit_stmts(const std::vector<StmtPtr>& stmts, int indent) {
    std::vector<std::string> lines;
    for (auto& s : stmts) {
        auto sub = emit_stmt(s, indent);
        lines.insert(lines.end(), sub.begin(), sub.end());
    }
    return lines;
}

std::vector<std::string> emit_stmt(const StmtPtr& s, int indent) {
    std::string pad;
    for (int i = 0; i < indent; ++i) pad += IND;

    // MonitorMarkerStmt - внутренний тип stackvm.py (см. emit.hpp) - в
    // Python-оригинале это ПОСЛЕДНЯЯ проверка isinstance перед fallback;
    // здесь - dynamic_cast перед общим switch по StmtKind (см. обоснование
    // в MonitorMarkerStmt).
    if (const auto* mm = dynamic_cast<const MonitorMarkerStmt*>(s.get())) {
        return {pad + "/* monitor" + mm->kind + " " + emit_expr(mm->expr) + " (synchronized-блок не свёрнут) */"};
    }

    if (!s) return {pad + "/* ? NoneType */"};
    switch (s->kind) {
        case StmtKind::ExprStmt:
            return {pad + emit_expr(static_cast<const ExprStmtNode*>(s.get())->expr) + ";"};
        case StmtKind::LocalDecl: {
            const auto* ld = static_cast<const LocalDecl*>(s.get());
            std::string init = ld->init ? (" = " + emit_expr(ld->init)) : "";
            std::string pre = ld->is_final ? "final " : "";
            return {pad + pre + simple(ld->type) + " " + ld->name + init + ";"};
        }
        case StmtKind::ReturnStmt: {
            const auto* r = static_cast<const ReturnStmt*>(s.get());
            if (!r->expr) return {pad + "return;"};
            return {pad + "return " + emit_expr(r->expr) + ";"};
        }
        case StmtKind::ThrowStmt:
            return {pad + "throw " + emit_expr(static_cast<const ThrowStmt*>(s.get())->expr) + ";"};
        case StmtKind::BreakStmt: {
            const auto* b = static_cast<const BreakStmt*>(s.get());
            return {pad + "break" + (b->label.has_value() ? (" " + *b->label) : "") + ";"};
        }
        case StmtKind::ContinueStmt: {
            const auto* c = static_cast<const ContinueStmt*>(s.get());
            return {pad + "continue" + (c->label.has_value() ? (" " + *c->label) : "") + ";"};
        }
        case StmtKind::IfStmt: {
            const auto* i = static_cast<const IfStmt*>(s.get());
            std::vector<std::string> out = {pad + "if (" + emit_expr(i->cond) + ") {"};
            auto then_lines = emit_stmts(i->then_body, indent + 1);
            out.insert(out.end(), then_lines.begin(), then_lines.end());
            if (i->else_body.has_value() && !i->else_body->empty()) {
                out.push_back(pad + "} else {");
                auto else_lines = emit_stmts(*i->else_body, indent + 1);
                out.insert(out.end(), else_lines.begin(), else_lines.end());
            }
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::WhileStmt: {
            const auto* w = static_cast<const WhileStmt*>(s.get());
            std::string label = w->label.has_value() ? (*w->label + ": ") : "";
            std::vector<std::string> out = {pad + label + "while (" + emit_expr(w->cond) + ") {"};
            auto body_lines = emit_stmts(w->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::DoWhileStmt: {
            const auto* w = static_cast<const DoWhileStmt*>(s.get());
            std::string label = w->label.has_value() ? (*w->label + ": ") : "";
            std::vector<std::string> out = {pad + label + "do {"};
            auto body_lines = emit_stmts(w->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "} while (" + emit_expr(w->cond) + ");");
            return out;
        }
        case StmtKind::ForStmt: {
            const auto* f = static_cast<const ForStmt*>(s.get());
            std::string label = f->label.has_value() ? (*f->label + ": ") : "";
            std::string init_txt = f->init ? emit_expr(f->init) : "";
            bool cond_is_true_const = f->cond && f->cond->kind == ExprKind::Const &&
                                       static_cast<const Const*>(f->cond.get())->literal == "true";
            std::string cond_txt = (!f->cond || cond_is_true_const) ? "" : emit_expr(f->cond);
            std::string upd_txt;
            if (f->update && f->update->kind == StmtKind::ExprStmt) {
                upd_txt = emit_expr(static_cast<const ExprStmtNode*>(f->update.get())->expr);
            }
            std::vector<std::string> out = {pad + label + "for (" + init_txt + "; " + cond_txt + "; " + upd_txt + ") {"};
            auto body_lines = emit_stmts(f->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::SwitchStmt: {
            const auto* sw = static_cast<const SwitchStmt*>(s.get());
            std::vector<std::string> out = {pad + "switch (" + emit_expr(sw->selector) + ") {"};
            for (auto& c : sw->cases) {
                if (c.is_default) out.push_back(pad + IND + "default:");
                for (auto& v : c.values) out.push_back(pad + IND + "case " + v + ":");
                auto case_lines = emit_stmts(c.body, indent + 2);
                out.insert(out.end(), case_lines.begin(), case_lines.end());
            }
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::TryStmt: {
            const auto* t = static_cast<const TryStmt*>(s.get());
            std::vector<std::string> out = {pad + "try {"};
            auto body_lines = emit_stmts(t->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            for (auto& c : t->catches) {
                out.push_back(pad + "} catch (" + simple(c.type) + " " + c.var_name + ") {");
                auto cb = emit_stmts(c.body, indent + 1);
                out.insert(out.end(), cb.begin(), cb.end());
            }
            if (t->finally_body.has_value()) {
                out.push_back(pad + "} finally {");
                auto fb = emit_stmts(*t->finally_body, indent + 1);
                out.insert(out.end(), fb.begin(), fb.end());
            }
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::SyncStmt: {
            const auto* sy = static_cast<const SyncStmt*>(s.get());
            std::vector<std::string> out = {pad + "synchronized (" + emit_expr(sy->expr) + ") {"};
            auto body_lines = emit_stmts(sy->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::BlockStmt: {
            const auto* b = static_cast<const BlockStmt*>(s.get());
            std::vector<std::string> out = {pad + "{"};
            auto body_lines = emit_stmts(b->stmts, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::GotoStmt:
            return {pad + "/* нередуцируемый переход -> " + static_cast<const GotoStmt*>(s.get())->label + " */"};
        case StmtKind::LabelStmt:
            return {pad + static_cast<const LabelStmt*>(s.get())->label + ":"};
        case StmtKind::RawStmt:
            return {pad + static_cast<const RawStmt*>(s.get())->text};
    }
    return {pad + "/* ? unknown stmt */"};
}

}  // namespace nd
