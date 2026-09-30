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

// paren_at() проверяет приоритет операнда sub относительно порогового значения threshold.
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
        case ExprKind::Const: {
            const auto* c = static_cast<const Const*>(e.get());
            if (c->type == "float") {
                if (c->literal == "nanf" || c->literal == "nan" || c->literal == "NaNf") return "Float.NaN";
                if (c->literal == "inff" || c->literal == "inf" || c->literal == "Infinityf") return "Float.POSITIVE_INFINITY";
                if (c->literal == "-inff" || c->literal == "-inf" || c->literal == "-Infinityf") return "Float.NEGATIVE_INFINITY";
            } else if (c->type == "double") {
                if (c->literal == "nand" || c->literal == "nan" || c->literal == "NaNd") return "Double.NaN";
                if (c->literal == "infd" || c->literal == "inf" || c->literal == "Infinityd") return "Double.POSITIVE_INFINITY";
                if (c->literal == "-infd" || c->literal == "-inf" || c->literal == "-Infinityd") return "Double.NEGATIVE_INFINITY";
            }
            return c->literal;
        }
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
                std::string body = item_strs.empty() ? "{}" : "{ " + join(item_strs, ", ") + " }";
                return "new " + simple(base) + "[]" + extra_brackets + body;
            }
            std::string dims_txt;
            for (auto& d : n->dims) {
                dims_txt += d ? ("[" + emit_expr(d) + "]") : "[]";
            }
            if (dims_txt.empty() && extra_brackets.empty()) dims_txt = "[0]";
            return "new " + simple(base) + dims_txt + extra_brackets;
        }
        case ExprKind::Cast: {
            const auto* c = static_cast<const Cast*>(e.get());
            return "(" + simple(c->type) + ") " + paren_at(c->expr, 85);
        }
        case ExprKind::InstanceOf: {
            const auto* io = static_cast<const InstanceOf*>(e.get());
            return paren(io->expr, io) + " instanceof " + simple(io->check_type);
        }
        case ExprKind::BinOp: {
            const auto* b = static_cast<const BinOp*>(e.get());
            if ((b->op == "&" || b->op == "|" || b->op == "^") && b->right->kind == ExprKind::Const) {
                const auto* c = static_cast<const Const*>(b->right.get());
                if (c->type == "int" || c->type == "long") {
                    if (c->value == "255") {
                        return paren(b->left, b, 'l') + " " + b->op + " 0xFF" + (c->type == "long" ? "L" : "");
                    } else if (c->value == "65535") {
                        return paren(b->left, b, 'l') + " " + b->op + " 0xFFFF" + (c->type == "long" ? "L" : "");
                    } else if (c->value == "16777215") {
                        return paren(b->left, b, 'l') + " " + b->op + " 0xFFFFFF" + (c->type == "long" ? "L" : "");
                    }
                }
            }
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
            if (l->is_method_ref && l->body_method_ref) {
                if (l->body_method_ref->kind == ExprKind::MethodCall) {
                    const auto* mc = static_cast<const MethodCall*>(l->body_method_ref.get());
                    if (mc->target) {
                        return emit_expr(mc->target) + "::" + mc->name;
                    } else if (mc->owner.has_value()) {
                        return simple(*mc->owner) + "::" + mc->name;
                    }
                } else if (l->body_method_ref->kind == ExprKind::NewObject) {
                    const auto* no = static_cast<const NewObject*>(l->body_method_ref.get());
                    return simple(no->type_name) + "::new";
                }
            }
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

static bool is_label_used_in_stmt(const StmtPtr& s, const std::string& lbl) {
    if (!s) return false;
    if (s->kind == StmtKind::BreakStmt) {
        auto* b = static_cast<const BreakStmt*>(s.get());
        return b->label.has_value() && *b->label == lbl;
    }
    if (s->kind == StmtKind::ContinueStmt) {
        auto* c = static_cast<const ContinueStmt*>(s.get());
        return c->label.has_value() && *c->label == lbl;
    }
    if (s->kind == StmtKind::BlockStmt) {
        auto* bl = static_cast<const BlockStmt*>(s.get());
        for (const auto& child : bl->stmts) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
    }
    if (s->kind == StmtKind::IfStmt) {
        auto* i = static_cast<const IfStmt*>(s.get());
        for (const auto& child : i->then_body) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
        if (i->else_body.has_value()) {
            for (const auto& child : *i->else_body) {
                if (is_label_used_in_stmt(child, lbl)) return true;
            }
        }
    }
    if (s->kind == StmtKind::WhileStmt) {
        auto* w = static_cast<const WhileStmt*>(s.get());
        for (const auto& child : w->body) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
    }
    if (s->kind == StmtKind::DoWhileStmt) {
        auto* dw = static_cast<const DoWhileStmt*>(s.get());
        for (const auto& child : dw->body) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
    }
    if (s->kind == StmtKind::ForStmt) {
        auto* f = static_cast<const ForStmt*>(s.get());
        for (const auto& child : f->body) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
    }
    if (s->kind == StmtKind::SwitchStmt) {
        auto* sw = static_cast<const SwitchStmt*>(s.get());
        for (const auto& c : sw->cases) {
            for (const auto& child : c.body) {
                if (is_label_used_in_stmt(child, lbl)) return true;
            }
        }
    }
    if (s->kind == StmtKind::TryStmt) {
        auto* tc = static_cast<const TryStmt*>(s.get());
        for (const auto& child : tc->resources) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
        for (const auto& child : tc->body) {
            if (is_label_used_in_stmt(child, lbl)) return true;
        }
        for (const auto& c : tc->catches) {
            for (const auto& child : c.body) {
                if (is_label_used_in_stmt(child, lbl)) return true;
            }
        }
        if (tc->finally_body.has_value()) {
            for (const auto& child : *tc->finally_body) {
                if (is_label_used_in_stmt(child, lbl)) return true;
            }
        }
    }
    return false;
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
            std::string label = (w->label.has_value() && is_label_used_in_stmt(s, *w->label)) ? (*w->label + ": ") : "";
            std::vector<std::string> out = {pad + label + "while (" + emit_expr(w->cond) + ") {"};
            auto body_lines = emit_stmts(w->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::DoWhileStmt: {
            const auto* w = static_cast<const DoWhileStmt*>(s.get());
            std::string label = (w->label.has_value() && is_label_used_in_stmt(s, *w->label)) ? (*w->label + ": ") : "";
            std::vector<std::string> out = {pad + label + "do {"};
            auto body_lines = emit_stmts(w->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            out.push_back(pad + "} while (" + emit_expr(w->cond) + ");");
            return out;
        }
        case StmtKind::ForStmt: {
            const auto* f = static_cast<const ForStmt*>(s.get());
            std::string label = (f->label.has_value() && is_label_used_in_stmt(s, *f->label)) ? (*f->label + ": ") : "";
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
            std::string label = (sw->label.has_value() && is_label_used_in_stmt(s, *sw->label)) ? (*sw->label + ": ") : "";
            std::string selector_str = emit_expr(sw->selector);
            std::vector<std::string> out;
            if (selector_str.size() > 11 && selector_str.compare(selector_str.size() - 11, 11, ".hashCode()") == 0) {
                out.push_back(pad + "// ПРИМЕЧАНИЕ: это switch(String) в оригинале - javac компилирует его именно");
                out.push_back(pad + "// так (switch по hashCode() строки + проверка equals() внутри каждого case),");
                out.push_back(pad + "// числа ниже - настоящие String.hashCode() из байткода, не потеряны и не");
                out.push_back(pad + "// выдуманы декомпилятором.");
            }
            out.push_back(pad + label + "switch (" + selector_str + ") {");
            for (auto& c : sw->cases) {
                if (c.is_default) out.push_back(pad + IND + "default:");
                for (auto& v : c.values) out.push_back(pad + IND + "case " + v + ":");
                std::vector<StmtPtr> clean_body = c.body;
                if (clean_body.size() >= 2) {
                    auto* last_break = dynamic_cast<BreakStmt*>(clean_body.back().get());
                    if (last_break != nullptr) {
                        auto* prev_term = clean_body[clean_body.size() - 2].get();
                        if (prev_term->kind == StmtKind::ReturnStmt || prev_term->kind == StmtKind::ThrowStmt) {
                            clean_body.pop_back();
                        }
                    }
                }
                if (&c == &sw->cases.back() && !clean_body.empty()) {
                    auto* last_break = dynamic_cast<BreakStmt*>(clean_body.back().get());
                    if (last_break != nullptr) {
                        clean_body.pop_back();
                    }
                }
                auto case_lines = emit_stmts(clean_body, indent + 2);
                out.insert(out.end(), case_lines.begin(), case_lines.end());
            }
            out.push_back(pad + "}");
            return out;
        }
        case StmtKind::TryStmt: {
            const auto* t = static_cast<const TryStmt*>(s.get());
            std::string res_str;
            if (!t->resources.empty()) {
                std::vector<std::string> res_parts;
                for (const auto& r : t->resources) {
                    if (r->kind == StmtKind::LocalDecl) {
                        const auto* ld = static_cast<const LocalDecl*>(r.get());
                        std::string init = ld->init ? (" = " + emit_expr(ld->init)) : "";
                        res_parts.push_back(simple(ld->type) + " " + ld->name + init);
                    } else if (r->kind == StmtKind::ExprStmt) {
                        res_parts.push_back(emit_expr(static_cast<const ExprStmtNode*>(r.get())->expr));
                    }
                }
                res_str = " (" + join(res_parts, "; ") + ")";
            }
            std::vector<std::string> out = {pad + "try" + res_str + " {"};
            auto body_lines = emit_stmts(t->body, indent + 1);
            out.insert(out.end(), body_lines.begin(), body_lines.end());
            for (auto& c : t->catches) {
                std::string catch_type_disp;
                if (c.type.find('|') != std::string::npos) {
                    std::stringstream ss(c.type);
                    std::string item;
                    std::vector<std::string> parts;
                    while (std::getline(ss, item, '|')) {
                        if (!item.empty()) parts.push_back(simple(item));
                    }
                    catch_type_disp = join(parts, " | ");
                } else {
                    catch_type_disp = simple(c.type);
                }
                out.push_back(pad + "} catch (" + catch_type_disp + " " + c.var_name + ") {");
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
