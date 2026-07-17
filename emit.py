# -*- coding: utf-8 -*-
"""
Печать AST (ast_nodes.Expr / Stmt) в отформатированный Java-текст.
"""
from ast_nodes import (
    Const, Local, This, FieldAccess, ArrayAccess, MethodCall, NewObject,
    NewArray, Cast, InstanceOf, BinOp, UnOp, Ternary, Assign, Raw,
    ClassLiteral, Lambda, ExprStmt, LocalDecl, ReturnStmt, ThrowStmt,
    IfStmt, WhileStmt, DoWhileStmt, ForStmt, BreakStmt, ContinueStmt,
    BlockStmt, SwitchStmt, SwitchCase, TryStmt, SyncStmt, GotoStmt,
    LabelStmt, RawStmt,
)
from stackvm import _MonitorMarker
from javatypes import mark_type

IND = "    "


def emit_expr(e):
    if isinstance(e, Const):
        return e.literal
    if isinstance(e, Local):
        return e.name
    if isinstance(e, This):
        return "this"
    if isinstance(e, FieldAccess):
        if e.static:
            return f"{_simple(e.owner)}.{e.name}"
        return f"{_paren(e.target, e)}.{e.name}"
    if isinstance(e, ArrayAccess):
        return f"{_paren(e.array, e)}[{emit_expr(e.index)}]"
    if isinstance(e, MethodCall):
        args = ", ".join(emit_expr(a) for a in e.args)
        if e.is_ctor:
            return f"{e.name}({args})"
        if e.static:
            return f"{_simple(e.owner)}.{e.name}({args})"
        if e.is_super:
            return f"super.{e.name}({args})"
        return f"{_paren(e.target, e)}.{e.name}({args})"
    if isinstance(e, NewObject):
        args = ", ".join(emit_expr(a) for a in e.args)
        return f"new {_simple(e.type)}({args})"
    if isinstance(e, NewArray):
        base = e.elem_type
        extra = 0
        while base.endswith("[]"):
            base = base[:-2]
            extra += 1
        if e.initializer is not None:
            items = ", ".join(emit_expr(v) for v in e.initializer)
            return f"new {_simple(base)}[]{'[]' * extra}{{{items}}}"
        dims_txt = "".join(f"[{emit_expr(d)}]" if d is not None else "[]" for d in e.dims)
        return f"new {_simple(base)}{dims_txt}{'[]' * extra}"
    if isinstance(e, Cast):
        return f"(({_simple(e.type)}) {_paren(e.expr, e)})"
    if isinstance(e, InstanceOf):
        return f"{_paren(e.expr, e)} instanceof {_simple(e.check_type)}"
    if isinstance(e, BinOp):
        return f"{_paren(e.left, e, side='l')} {e.op} {_paren(e.right, e, side='r')}"
    if isinstance(e, UnOp):
        if e.op in ("++", "--"):
            inner = emit_expr(e.expr)
            return f"{inner}{e.op}" if e.postfix else f"{e.op}{inner}"
        return f"{e.op}{_paren(e.expr, e)}"
    if isinstance(e, Ternary):
        return f"{_paren(e.cond, e)} ? {_paren(e.tval, e)} : {_paren(e.fval, e)}"
    if isinstance(e, Assign):
        return f"{emit_expr(e.target)} {e.op} {emit_expr(e.value)}"
    if isinstance(e, ClassLiteral):
        return f"{_simple(e.type_name)}.class"
    if isinstance(e, Lambda):
        params = ", ".join(p.name for p in e.params)
        header = params if len(e.params) == 1 else f"({params})"
        return f"{header} -> {emit_expr(e.body_method_ref)}"
    if isinstance(e, Raw):
        return e.text
    return f"/* ? {type(e).__name__} */"


def _simple(dotted):
    # Не сводим к simple-имени прямо сейчас: оборачиваем в маркер и решаем
    # simple-имя vs FQN одним финальным проходом по всему файлу класса,
    # когда известны ВСЕ типы, использованные в файле (нужно для
    # обнаружения коллизий simple-имён между разными пакетами -
    # см. javatypes.mark_type/resolve_type_markers, main.py::render_class).
    return mark_type(dotted)


def _paren(sub, parent, side=None):
    txt = emit_expr(sub)
    sp = sub.prec()
    pp = parent.prec()
    if sp < pp:
        return f"({txt})"
    if sp == pp and side == "r" and isinstance(parent, BinOp) and parent.op in ("-", "/", "%", "<<", ">>", ">>>"):
        return f"({txt})"
    return txt


def emit_stmts(stmts, indent):
    lines = []
    for s in stmts:
        lines.extend(emit_stmt(s, indent))
    return lines


def emit_stmt(s, indent):
    pad = IND * indent
    if isinstance(s, ExprStmt):
        return [f"{pad}{emit_expr(s.expr)};"]
    if isinstance(s, LocalDecl):
        init = f" = {emit_expr(s.init)}" if s.init is not None else ""
        pre = "final " if s.is_final else ""
        return [f"{pad}{pre}{_simple(s.type)} {s.name}{init};"]
    if isinstance(s, ReturnStmt):
        if s.expr is None:
            return [f"{pad}return;"]
        return [f"{pad}return {emit_expr(s.expr)};"]
    if isinstance(s, ThrowStmt):
        return [f"{pad}throw {emit_expr(s.expr)};"]
    if isinstance(s, BreakStmt):
        return [f"{pad}break{' ' + s.label if s.label else ''};"]
    if isinstance(s, ContinueStmt):
        return [f"{pad}continue{' ' + s.label if s.label else ''};"]
    if isinstance(s, IfStmt):
        out = [f"{pad}if ({emit_expr(s.cond)}) {{"]
        out.extend(emit_stmts(s.then_body or [], indent + 1))
        if s.else_body:
            out.append(f"{pad}}} else {{")
            out.extend(emit_stmts(s.else_body, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, WhileStmt):
        label = f"{s.label}: " if getattr(s, "label", None) else ""
        out = [f"{pad}{label}while ({emit_expr(s.cond)}) {{"]
        out.extend(emit_stmts(s.body, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, DoWhileStmt):
        label = f"{s.label}: " if getattr(s, "label", None) else ""
        out = [f"{pad}{label}do {{"]
        out.extend(emit_stmts(s.body, indent + 1))
        out.append(f"{pad}}} while ({emit_expr(s.cond)});")
        return out
    if isinstance(s, ForStmt):
        label = f"{s.label}: " if getattr(s, "label", None) else ""
        init_txt = emit_expr(s.init) if s.init is not None else ""
        cond_txt = "" if (s.cond is None or (isinstance(s.cond, Const) and s.cond.literal == "true")) else emit_expr(s.cond)
        upd_txt = emit_expr(s.update.expr) if s.update is not None else ""
        out = [f"{pad}{label}for ({init_txt}; {cond_txt}; {upd_txt}) {{"]
        out.extend(emit_stmts(s.body, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, SwitchStmt):
        out = [f"{pad}switch ({emit_expr(s.selector)}) {{"]
        for c in s.cases:
            if c.is_default:
                out.append(f"{pad}{IND}default:")
            for v in c.values:
                out.append(f"{pad}{IND}case {v}:")
            out.extend(emit_stmts(c.body, indent + 2))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, TryStmt):
        out = [f"{pad}try {{"]
        out.extend(emit_stmts(s.body, indent + 1))
        for typ, var, cbody in s.catches:
            out.append(f"{pad}}} catch ({_simple(typ)} {var}) {{")
            out.extend(emit_stmts(cbody, indent + 1))
        if s.finally_body is not None:
            out.append(f"{pad}}} finally {{")
            out.extend(emit_stmts(s.finally_body, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, SyncStmt):
        out = [f"{pad}synchronized ({emit_expr(s.expr)}) {{"]
        out.extend(emit_stmts(s.body, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, BlockStmt):
        out = [f"{pad}{{"]
        out.extend(emit_stmts(s.stmts, indent + 1))
        out.append(f"{pad}}}")
        return out
    if isinstance(s, GotoStmt):
        return [f"{pad}/* нередуцируемый переход -> {s.label} */"]
    if isinstance(s, LabelStmt):
        return [f"{pad}{s.label}:"]
    if isinstance(s, RawStmt):
        return [f"{pad}{s.text}"]
    if isinstance(s, _MonitorMarker):
        return [f"{pad}/* monitor{s.kind} {emit_expr(s.expr)} (synchronized-блок не свёрнут) */"]
    return [f"{pad}/* ? {type(s).__name__} */"]
