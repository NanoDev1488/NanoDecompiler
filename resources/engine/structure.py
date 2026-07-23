# -*- coding: utf-8 -*-
"""
Структуризация: убирает goto/переходы по адресам и строит вложенный AST
(if/else, while/do-while/for, switch, try/catch, break/continue) поверх CFG.

Стратегия: для каждого цикла сначала строится общая корректная форма
`while (true) { ...; if (X) break; ...; if (Y) continue; ... }`, которая
ВСЕГДА корректна для приводимого (reducible) графа потока управления, затем
`simplify_loops` в отдельном проходе сворачивает частые случаи в красивые
`while (cond)`, `do {...} while (cond)`, `for (...; cond; update)`.

Если встречается goto/переход, который не удаётся сопоставить ни одной из
известных структур (нередуцируемый控-flow, jsr/ret и т.п.) - поднимается
DecompileAbort, и метод целиком откатывается на честный байткод-листинг
(engine.py).
"""
from ast_nodes import (
    IfStmt, WhileStmt, DoWhileStmt, ForStmt, BreakStmt, ContinueStmt,
    SwitchStmt, SwitchCase, TryStmt, SyncStmt, BlockStmt, ExprStmt, Const,
    BinOp, UnOp, Local, LocalDecl, ReturnStmt, ThrowStmt, FieldAccess, Assign,
    Ternary,
)
from stackvm import DecompileAbort, _MonitorMarker, CAUGHT_SENTINEL, _PSEUDO_TYPES


class Structurer:
    def __init__(self, cfg, block_results, exceptions, ctx):
        self.cfg = cfg
        self.results = block_results
        self.exceptions = exceptions
        self.ctx = ctx
        self.ipdom = cfg.compute_postdominators()
        self._prepare_loops()
        self._prepare_try()
        self.loop_stack = []
        self.breakable_stack = []
        self.label_ctr = 0
        self._guard = 0
        self._terminates_cache = {}
        # Счётчик catch-переменных (e1, e2...) - ОБЩИЙ на весь метод, а не
        # обнуляемый на каждый try/catch (см. _build_try) - иначе два
        # независимых (или, того хуже, вложенных) try/catch в одном методе
        # оба называли бы свою первую catch-переменную "e1", что для
        # вложенного случая - реальная ошибка компиляции ("variable e1 is
        # already defined"), а для соседних - просто путаница при чтении.
        self._catch_var_ctr = 0

    # ---------------- loop discovery ----------------

    def _prepare_loops(self):
        self.loop_headers = {}
        for header, body, tails in self.cfg.natural_loops():
            exits = set()
            for b in body:
                for s in self.cfg.blocks[b].succs:
                    if s not in body:
                        exits.add(s)
            if not exits:
                exit_pc = None
            elif len(exits) == 1:
                exit_pc = next(iter(exits))
            else:
                ip = self.ipdom.get(header)
                exit_pc = ip if ip in exits else min(exits)
            self.loop_headers[header] = (body, exit_pc)
        self._consumed_loop = set()

    def _prepare_try(self):
        by_key = {}
        order = []
        for e in self.exceptions:
            key = (e.start_pc, e.end_pc)
            if key not in by_key:
                by_key[key] = []
                order.append(key)
            by_key[key].append((e.catch_type, e.handler_pc))
        self.try_by_key = by_key
        by_start = {}
        for key in order:
            by_start.setdefault(key[0], []).append(key)
        self.try_by_start = by_start
        self._consumed_try = set()

    # ---------------- entry point ----------------

    def build(self, entry_pc):
        return self.region(entry_pc, frozenset())

    # ---------------- core linear region scanner ----------------

    def region(self, pc, stop_addrs):
        out = []
        seen_here = set()
        while True:
            self._guard += 1
            if self._guard > 200000:
                raise DecompileAbort("structuring guard limit exceeded")
            if pc is None or pc not in self.cfg.blocks or pc in stop_addrs:
                break
            if pc in seen_here:
                raise DecompileAbort("нередуцируемый переход внутри региона")
            seen_here.add(pc)

            if pc in self.try_by_start and pc not in self._consumed_try:
                stmt, next_pc = self._build_try(pc, stop_addrs)
                out.append(stmt)
                if next_pc is None:
                    break
                pc = next_pc
                continue

            if pc in self.loop_headers and pc not in self._consumed_loop:
                stmt, next_pc = self._build_loop(pc, stop_addrs)
                out.append(stmt)
                if next_pc is None:
                    break
                pc = next_pc
                continue

            block = self.cfg.blocks[pc]
            res = self.results[pc]
            out.extend(res.stmts)

            if res.term_kind in ("return", "throw"):
                break

            if res.term_kind == "if":
                true_t, false_t = block.succs[0], block.succs[1]
                stmt, next_pc = self._build_if(pc, res.cond, true_t, false_t, stop_addrs)
                if stmt is not None:
                    out.append(stmt)
                if next_pc is None:
                    break
                pc = next_pc
                continue

            if res.term_kind == "switch":
                stmt, next_pc = self._build_switch(pc, res.cond, block, stop_addrs)
                out.append(stmt)
                if next_pc is None:
                    break
                pc = next_pc
                continue

            last_ins = block.instrs[-1] if block.instrs else None
            if last_ins is not None and last_ins.mnemonic in ("goto", "goto_w"):
                target = last_ins.target
                special = self._resolve_jump_stmt(target, stop_addrs)
                if special is _NO_STMT:
                    break
                elif special is _CONTINUE_LINEARLY:
                    pc = target
                    continue
                else:
                    out.append(special)
                    break
            else:
                if block.succs:
                    pc = block.succs[0]
                    continue
                break
        return out

    # ---------------- jump resolution ----------------

    def _resolve_jump_stmt(self, target, stop_addrs):
        for entry in reversed(self.loop_stack):
            if target == entry["header"]:
                if entry is self.loop_stack[-1]:
                    return ContinueStmt(None)
                entry["label"] = entry["label"] or self._new_label()
                return ContinueStmt(entry["label"])
        for entry in reversed(self.breakable_stack):
            if target == entry["exit"]:
                if entry is self.breakable_stack[-1]:
                    return BreakStmt(None)
                entry["label"] = entry["label"] or self._new_label()
                return BreakStmt(entry["label"])
        if target in stop_addrs:
            return _NO_STMT
        # безопасный частный случай: чистый "трамплин" - блок, состоящий ровно
        # из одного безусловного goto и не содержащий вычисленных statement'ов
        # (например несколько continue-точек цикла, слитых компилятором в один
        # общий "goto Lheader"). Проваливаться в него не опасно: он сам по себе
        # не производит никакого текста, поэтому даже повторный проход через
        # него (с разных путей) не может задвоить видимый код.
        tblock = self.cfg.blocks.get(target)
        if tblock is not None and len(tblock.instrs) == 1 and \
                tblock.instrs[0].mnemonic in ("goto", "goto_w") and not self.results[target].stmts:
            return _CONTINUE_LINEARLY
        raise DecompileAbort(f"нередуцируемый goto -> {target}")

    def _try_resolve_special_target(self, target):
        """Для прямых веток условного перехода (if(x) break/continue), без
        обращения к stop_addrs - только к активным циклам/switch."""
        for entry in reversed(self.loop_stack):
            if target == entry["header"]:
                if entry is self.loop_stack[-1]:
                    return ContinueStmt(None)
                entry["label"] = entry["label"] or self._new_label()
                return ContinueStmt(entry["label"])
        for entry in reversed(self.breakable_stack):
            if target == entry["exit"]:
                if entry is self.breakable_stack[-1]:
                    return BreakStmt(None)
                entry["label"] = entry["label"] or self._new_label()
                return BreakStmt(entry["label"])
        return None

    def _new_label(self):
        self.label_ctr += 1
        return f"loop{self.label_ctr}"

    # ---------------- if/else ----------------

    def _is_terminating(self, pc, depth=0, seen=None):
        """Гарантированно ли КАЖДЫЙ путь из pc заканчивается return/throw, не
        доходя до реального 'слияния' с чем-то ещё? Используется, когда общий
        постдоминатор двух веток if не найден (self.ipdom[pc] is None) именно
        потому, что одна из веток гарантированно завершается раньше слияния -
        в этом случае 'merge' для if - это просто естественное продолжение
        ДРУГОЙ (не завершающейся) ветки, а не виртуальный EXIT."""
        if pc in self._terminates_cache:
            return self._terminates_cache[pc]
        if seen is None:
            seen = set()
        if depth > 300 or pc in seen or pc not in self.cfg.blocks:
            return pc not in self.cfg.blocks
        seen = seen | {pc}
        res = self.results.get(pc)
        if res is None:
            return False
        if res.term_kind in ("return", "throw"):
            result = True
        elif res.term_kind == "if":
            succs = self.cfg.blocks[pc].succs
            if len(succs) != 2:
                result = False
            else:
                result = self._is_terminating(succs[0], depth + 1, seen) and \
                          self._is_terminating(succs[1], depth + 1, seen)
        elif res.term_kind == "switch" or pc in self.loop_headers or pc in self.try_by_start:
            result = False  # консервативно: не пытаемся анализировать сложные конструкции здесь
        else:
            block = self.cfg.blocks[pc]
            last = block.instrs[-1] if block.instrs else None
            if last is not None and last.mnemonic in ("goto", "goto_w"):
                result = self._is_terminating(last.target, depth + 1, seen)
            elif block.succs:
                result = self._is_terminating(block.succs[0], depth + 1, seen)
            else:
                result = True
        if depth == 0:
            self._terminates_cache[pc] = result
        return result

    def _build_if(self, pc, cond, true_t, false_t, stop_addrs):
        sp_true = self._try_resolve_special_target(true_t)
        sp_false = self._try_resolve_special_target(false_t)

        if sp_true is not None and sp_false is not None:
            merge = self.ipdom.get(pc)
            return IfStmt(cond, [sp_true], [sp_false]), (merge if merge not in stop_addrs else None)

        if sp_true is not None:
            return IfStmt(cond, [sp_true], None), false_t

        if sp_false is not None:
            return IfStmt(_negate(cond), [sp_false], None), true_t

        merge = self.ipdom.get(pc)
        if merge is None:
            # общий постдоминатор не найден - типично, когда одна из веток
            # гарантированно завершается return/throw раньше точки слияния;
            # тогда merge - это естественное продолжение ДРУГОЙ ветки (её
            # собственный постдоминатор), а не адрес её начала
            t_term = self._is_terminating(true_t)
            f_term = self._is_terminating(false_t)
            if t_term and not f_term:
                raw = self.ipdom.get(false_t)
                merge = raw if raw is not None else false_t
            elif f_term and not t_term:
                raw = self.ipdom.get(true_t)
                merge = raw if raw is not None else true_t
        local_stop = stop_addrs | ({merge} if merge is not None else set())
        then_body = self.region(true_t, local_stop)
        else_body = None if false_t == merge or false_t in stop_addrs else self.region(false_t, local_stop)
        return IfStmt(cond, then_body, else_body), merge

    # ---------------- loops ----------------

    def _build_loop(self, header_pc, stop_addrs):
        body_set, exit_pc = self.loop_headers[header_pc]
        self._consumed_loop.add(header_pc)
        entry = {"header": header_pc, "exit": exit_pc, "label": None}
        self.loop_stack.append(entry)
        self.breakable_stack.append(entry)
        local_stop = stop_addrs | ({exit_pc} if exit_pc is not None else set())
        body = self.region(header_pc, local_stop)
        self.loop_stack.pop()
        self.breakable_stack.pop()
        stmt = WhileStmt(Const("true", "boolean"), body, label=entry["label"])
        return stmt, exit_pc

    # ---------------- switch ----------------

    def _build_switch(self, pc, selector, block, stop_addrs):
        last_ins = block.instrs[-1]
        targets = last_ins.targets
        merge = self.ipdom.get(pc)
        entry = {"exit": merge, "label": None}
        self.breakable_stack.append(entry)

        label_map = {}
        for v, t in sorted(((v, t) for v, t in targets.items() if v is not None), key=lambda vt: vt[0]):
            label_map.setdefault(t, []).append(str(v))
        default_t = targets.get(None)
        if default_t is not None:
            label_map.setdefault(default_t, []).append("default")

        if not label_map:
            self.breakable_stack.pop()
            return SwitchStmt(selector, [], label=entry["label"]), merge

        # каждый case физически расположен по своему адресу в байткоде;
        # сканируем их НЕЗАВИСИМО, каждый со своей верхней границей (начало
        # следующего case ИЛИ общая точка схождения switch) - это гарантирует,
        # что explicit break в конце одного case не оборвёт сканирование
        # остальных, и что естественный fallthrough (без break) тоже
        # передаётся верно.
        case_addrs = sorted(label_map.keys())
        local_stop_base = stop_addrs | ({merge} if merge is not None else set())

        cases = []
        for idx, addr in enumerate(case_addrs):
            next_addr = case_addrs[idx + 1] if idx + 1 < len(case_addrs) else None
            case_stop = local_stop_base | ({next_addr} if next_addr is not None else set())
            body = self.region(addr, case_stop)
            values = [v for v in label_map[addr] if v != "default"]
            is_default = "default" in label_map[addr]
            cases.append(SwitchCase(values, body, is_default=is_default))

        self.breakable_stack.pop()
        return SwitchStmt(selector, cases, label=entry["label"]), merge

    # ---------------- try/catch ----------------

    def _build_try(self, pc, stop_addrs):
        key = self.try_by_start[pc][0]
        start, end = key
        entries = self.try_by_key[key]
        self._consumed_try.add(pc)

        body = self.region(start, stop_addrs | {end})
        catches = []
        seen_handlers = set()
        for catch_type, handler_pc in entries:
            if handler_pc in seen_handlers:
                continue
            seen_handlers.add(handler_pc)
            self._catch_var_ctr += 1
            disp_type = self.ctx.owner_display(catch_type) if catch_type else "Throwable"
            merge2 = self.ipdom.get(handler_pc)
            local_stop = stop_addrs | ({merge2} if merge2 is not None else set())
            cbody = self.region(handler_pc, local_stop)
            var_name = f"e{self._catch_var_ctr}"
            if cbody and isinstance(cbody[0], LocalDecl) and _is_sentinel(cbody[0].init):
                var_name = cbody[0].name
                cbody = cbody[1:]
            else:
                _rename_sentinel(cbody, var_name)
            catches.append((disp_type, var_name, cbody))
        overall_merge = self.ipdom.get(start)
        if overall_merge in stop_addrs:
            overall_merge = None
        return TryStmt(body, catches, None), overall_merge


_NO_STMT = object()
_CONTINUE_LINEARLY = object()


def _negate(cond):
    if isinstance(cond, UnOp) and cond.op == "!":
        return cond.expr
    if isinstance(cond, BinOp):
        flip = {"==": "!=", "!=": "==", "<": ">=", ">=": "<", ">": "<=", "<=": ">"}
        if cond.op in flip:
            return BinOp(flip[cond.op], cond.left, cond.right, "boolean")
    return UnOp("!", cond, "boolean")


def _is_sentinel(e):
    return isinstance(e, Local) and e.name == CAUGHT_SENTINEL


def _rename_sentinel(stmts, new_name):
    def walk_expr(e):
        if e is None:
            return e
        if isinstance(e, Local) and e.name == CAUGHT_SENTINEL:
            e.name = new_name
            return e
        for attr in ("left", "right", "expr", "target", "value", "array", "index",
                      "cond", "tval", "fval", "init"):
            v = getattr(e, attr, None)
            if v is not None and hasattr(v, "prec"):
                walk_expr(v)
        args = getattr(e, "args", None)
        if args:
            for a in args:
                walk_expr(a)
        dims = getattr(e, "dims", None)
        if dims:
            for d in dims:
                if d is not None:
                    walk_expr(d)
        return e

    def walk_stmt(s):
        for attr in ("expr", "cond", "init", "update", "selector"):
            v = getattr(s, attr, None)
            if v is not None and hasattr(v, "prec"):
                walk_expr(v)
        for attr in ("then_body", "else_body", "body"):
            v = getattr(s, attr, None)
            if isinstance(v, list):
                for sub in v:
                    walk_stmt(sub)
        if isinstance(s, type(s)) and hasattr(s, "cases"):
            for c in s.cases:
                for sub in c.body:
                    walk_stmt(sub)
        if hasattr(s, "catches"):
            for _, _, cb in s.catches:
                for sub in cb:
                    walk_stmt(sub)

    for s in stmts:
        walk_stmt(s)


# ---------------- loop beautification (while(true)+break -> while/do-while/for) ----------------

def simplify_stmts(stmts):
    out = []
    for s in stmts:
        out.append(simplify_stmt(s))
    return _fold_boolean_materialization(out)


def _fold_boolean_materialization(stmts):
    """Паттерн javac для `t = (a < b);` / `t = cond ? x : y;`:
    if (cond) t = X; else t = Y; - сворачиваем обратно в t = cond ? X : Y;
    (с частным случаем X/Y = 1/0 -> просто t = cond;/t = !cond;)."""
    out = []
    for s in stmts:
        if isinstance(s, IfStmt) and s.then_body and len(s.then_body) == 1 and \
                s.else_body and len(s.else_body) == 1:
            t1, t2 = s.then_body[0], s.else_body[0]
            pair = _as_assign(t1), _as_assign(t2)
            if pair[0] is not None and pair[1] is not None:
                (tgt1, v1), (tgt2, v2) = pair
                if _same_target(tgt1, tgt2):
                    bconst = _as_bool_const(v1), _as_bool_const(v2)
                    if bconst[0] is not None and bconst[1] is not None and {bconst[0], bconst[1]} == {0, 1}:
                        cond = s.cond if bconst[0] == 1 else _negate(s.cond)
                        out.append(ExprStmt(Assign(tgt1, cond)))
                        continue
                    t1_type = getattr(v1, "type", None)
                    t2_type = getattr(v2, "type", None)
                    if t1_type and t1_type not in _PSEUDO_TYPES:
                        result_type = t1_type
                    elif t2_type and t2_type not in _PSEUDO_TYPES:
                        result_type = t2_type
                    else:
                        tgt_type = getattr(tgt1, "type", None)
                        result_type = tgt_type if tgt_type and tgt_type not in _PSEUDO_TYPES else "Object"
                    out.append(ExprStmt(Assign(tgt1, Ternary(s.cond, v1, v2, result_type))))
                    continue
        out.append(s)
    return out


def _as_assign(stmt):
    if isinstance(stmt, ExprStmt) and isinstance(stmt.expr, Assign):
        return stmt.expr.target, stmt.expr.value
    return None


def _as_bool_const(v):
    if isinstance(v, Const) and v.type in ("int", "boolean") and v.literal in ("0", "1", "true", "false"):
        return {"0": 0, "1": 1, "false": 0, "true": 1}[v.literal]
    return None


def _same_target(a, b):
    if isinstance(a, Local) and isinstance(b, Local):
        return a.name == b.name
    if isinstance(a, FieldAccess) and isinstance(b, FieldAccess):
        return a.name == b.name and a.static == b.static and \
               (a.target is None) == (b.target is None)
    return False


def simplify_stmt(s):
    if isinstance(s, WhileStmt):
        s.body = simplify_stmts(s.body)
        if isinstance(s.cond, Const) and s.cond.literal == "true":
            _simplify_while_true(s)
        return s
    if isinstance(s, DoWhileStmt):
        s.body = simplify_stmts(s.body)
        return s
    if isinstance(s, ForStmt):
        s.body = simplify_stmts(s.body)
        return s
    if isinstance(s, IfStmt):
        s.then_body = simplify_stmts(s.then_body) if s.then_body else s.then_body
        s.else_body = simplify_stmts(s.else_body) if s.else_body else s.else_body
        if not s.then_body and s.else_body:
            s.cond = _negate(s.cond)
            s.then_body, s.else_body = s.else_body, None
        return s
    if isinstance(s, TryStmt):
        s.body = simplify_stmts(s.body)
        s.catches = [(t, n, simplify_stmts(b)) for t, n, b in s.catches]
        if s.finally_body:
            s.finally_body = simplify_stmts(s.finally_body)
        return s
    if isinstance(s, SwitchStmt):
        for c in s.cases:
            c.body = simplify_stmts(c.body)
        return s
    if isinstance(s, SyncStmt):
        s.body = simplify_stmts(s.body)
        return s
    return s


def _is_plain_break(x):
    return isinstance(x, BreakStmt) and x.label is None


def _is_plain_continue(x):
    return isinstance(x, ContinueStmt) and x.label is None


def _simplify_while_true(s):
    body = s.body
    # leading `if (cond) break;` -> while(!cond)
    if body and isinstance(body[0], IfStmt):
        first = body[0]
        if first.then_body and len(first.then_body) == 1 and _is_plain_break(first.then_body[0]) and not first.else_body:
            s.cond = _negate(first.cond)
            body = body[1:]
        elif first.else_body and len(first.else_body) == 1 and _is_plain_break(first.else_body[0]) and \
                (not first.then_body or len(first.then_body) == 0):
            s.cond = first.cond
            body = body[1:]
    s.body = body
    # trailing back-edge test -> do-while, only if cond wasn't already simplified above
    if s.cond is not None and isinstance(s.cond, Const) and s.cond.literal == "true" and body:
        last = body[-1]
        if isinstance(last, IfStmt):
            tb, eb = last.then_body, last.else_body
            if tb and len(tb) == 1 and _is_plain_continue(tb[0]) and eb and len(eb) == 1 and _is_plain_break(eb[0]):
                do = DoWhileStmt(last.cond, body[:-1], label=s.label)
                _mutate_into(s, do)
                return
            if eb and len(eb) == 1 and _is_plain_continue(eb[0]) and tb and len(tb) == 1 and _is_plain_break(tb[0]):
                do = DoWhileStmt(_negate(last.cond), body[:-1], label=s.label)
                _mutate_into(s, do)
                return
    # for-loop beautification: trailing update statement + while-style cond already set
    if not (isinstance(s.cond, Const) and s.cond.literal == "true") and body:
        last = body[-1]
        if _looks_like_update(last):
            s2 = ForStmt(None, s.cond, last, body[:-1], label=s.label)
            _mutate_into(s, s2)
            return


def _looks_like_update(stmt):
    if not isinstance(stmt, ExprStmt):
        return False
    e = stmt.expr
    if isinstance(e, UnOp) and e.op in ("++", "--"):
        return True
    if isinstance(e, __import__("ast_nodes").Assign):
        return True
    return False


def _mutate_into(while_stmt, replacement):
    while_stmt.__class__ = replacement.__class__
    while_stmt.__dict__ = replacement.__dict__
