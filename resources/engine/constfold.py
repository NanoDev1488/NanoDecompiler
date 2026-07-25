# -*- coding: utf-8 -*-
"""
Свёртка констант и удаление гарантированно мёртвых веток (opaque predicates).

ЗАЧЕМ: обфускаторы часто вставляют условия, значение которых ИЗВЕСТНО уже
на этапе компиляции - `if (1 < 2) { ... } else { мёртвый код }`,
`if (false) { мусор }`, `if ((5 & 3) == 1) { ... }` и т.п. (Zelix
KlassMaster, Allatori и подобные - "opaque predicates" в терминологии
литературы по обфускации). Такой код синтаксически валиден и компилируется,
но не несёт смысла - только запутывает чтение декомпилированного вывода.

КАК ЭТО БЕЗОПАСНО: мы вычисляем значение условия в Python ТОЛЬКО если оно
целиком построено из литеральных констант (AST-узел Const) и чистых
арифметических/логических/битовых операций (BinOp/UnOp) без единой
локальной переменной, поля, вызова метода или элемента массива. Раз в
выражении нет ничего, что зависит от рантайм-состояния - JVM в любом
исполнении вычислит РОВНО то же самое значение, что и мы здесь. Это не
эвристика и не догадка - формальное вычисление константного выражения,
идентичное тому, что делает сам javac при компиляции (constant folding -
JLS §15.28). Свёртка int/long делается с эмуляцией переполнения по
разрядности (маскирование), а не "как есть" в Python - иначе большие
константы после `<<`/`*` дали бы неверный результат.

ЕСЛИ НЕ УВЕРЕНЫ - НЕ ТРОГАЕМ: `try_eval_const` возвращает None при первом
же узле, который не может быть строго вычислен (локальная переменная, поле,
вызов, NaN/Infinity, деление на 0, char/String-литералы - см. ниже). Тот же
принцип, что и `DecompileAbort` в engine.py (см. HANDOFF_1_ARCHITECTURE.md):
лучше не свернуть реальный opaque predicate, чем свернуть что-то неверно.

НЕ ПОДДЕРЖАНО НАМЕРЕННО (не входит в эту версию, не риск - просто пока не
сделано): char и String константы (сравнение/конкатенация текстовых
литералов) - самих чисел в реальных opaque predicates подавляющее
большинство, а строковые сравнения ("==" на String) в живом байткоде почти
всегда объектные (не константные) сравнения, а не то, что стоило бы
сворачивать.
"""
import math

from ast_nodes import Const, BinOp, UnOp

_INT_MASK = (1 << 32) - 1
_LONG_MASK = (1 << 64) - 1

# Счётчик убранных мёртвых веток - для прозрачной статистики в README_RU.txt
# (см. main.py::write_readme). Сбрасывается per-jar через reset_stats().
_dead_branches_removed = 0


def reset_stats():
    global _dead_branches_removed
    _dead_branches_removed = 0


def get_dead_branches_removed():
    return _dead_branches_removed


def _note_removed():
    global _dead_branches_removed
    _dead_branches_removed += 1


def _to_signed(v, bits):
    mask = (1 << bits) - 1
    v &= mask
    if v >= (1 << (bits - 1)):
        v -= (1 << bits)
    return v


def _parse_const(node):
    """Const AST-узел -> Python bool/int/float, либо None (тип/значение не
    поддержаны для безопасной свёртки - char/String/null/Object, либо
    NaN/Infinity, с которыми лучше не связываться)."""
    t = node.type
    lit = node.literal
    try:
        if t == "boolean":
            if lit == "true":
                return True
            if lit == "false":
                return False
            return None
        if t in ("int", "short", "byte"):
            return int(lit)
        if t == "long":
            return int(lit[:-1]) if lit and lit[-1] in "Ll" else int(lit)
        if t in ("float", "double"):
            if "NaN" in lit or "Infinity" in lit:
                return None
            s = lit
            if s and s[-1] in "fFdD":
                s = s[:-1]
            return float(s)
    except (ValueError, TypeError):
        return None
    return None


_ARITH = {"+", "-", "*", "/", "%", "&", "^", "|", "<<", ">>", ">>>"}
_CMP = {"<", ">", "<=", ">=", "==", "!="}
_LOGIC = {"&&", "||"}


def try_eval_const(expr):
    """Пытается вычислить Expr как compile-time-константу. Возвращает
    Python bool/int/float при успехе, иначе None. НИКОГДА не вычисляет
    ничего с потенциальными побочными эффектами или зависящего от
    рантайм-состояния (переменные/поля/вызовы/массивы) - см. докстринг
    модуля."""
    if isinstance(expr, Const):
        return _parse_const(expr)

    if isinstance(expr, UnOp) and not expr.postfix:
        v = try_eval_const(expr.expr)
        if v is None:
            return None
        if expr.op == "!":
            return (not v) if isinstance(v, bool) else None
        if expr.op == "-":
            if isinstance(v, bool):
                return None
            if isinstance(v, float):
                return -v
            bits = 64 if expr.type == "long" else 32
            return _to_signed(-v, bits)
        if expr.op == "~":
            if isinstance(v, bool) or isinstance(v, float):
                return None
            bits = 64 if expr.type == "long" else 32
            return _to_signed(~v, bits)
        return None

    if isinstance(expr, BinOp):
        lv = try_eval_const(expr.left)
        op = expr.op
        # short-circuit && / || - можно решить по левой части одной, не
        # трогая правую (безопасно ТОЛЬКО потому что try_eval_const в
        # принципе не проходит через выражения с побочными эффектами -
        # правая часть заведомо чистая, если она вообще успела бы
        # вычислиться в try_eval_const(expr.right))
        if lv is not None and isinstance(lv, bool):
            if op == "&&" and lv is False:
                return False
            if op == "||" and lv is True:
                return True
        if lv is None:
            return None

        rv = try_eval_const(expr.right)
        if rv is None:
            return None

        try:
            if op in _LOGIC:
                if not isinstance(lv, bool) or not isinstance(rv, bool):
                    return None
                return (lv and rv) if op == "&&" else (lv or rv)

            if op in _CMP:
                if op == "<":
                    return lv < rv
                if op == ">":
                    return lv > rv
                if op == "<=":
                    return lv <= rv
                if op == ">=":
                    return lv >= rv
                if op == "==":
                    return lv == rv
                if op == "!=":
                    return lv != rv

            if op in _ARITH:
                if isinstance(lv, bool) or isinstance(rv, bool):
                    return None
                is_float = isinstance(lv, float) or isinstance(rv, float)
                bits = 64 if expr.type == "long" else 32

                if op in ("&", "^", "|", "<<", ">>", ">>>"):
                    if is_float:
                        return None
                    li, ri = int(lv), int(rv)
                    shift_mask = 63 if bits == 64 else 31
                    if op == "&":
                        res = li & ri
                    elif op == "^":
                        res = li ^ ri
                    elif op == "|":
                        res = li | ri
                    elif op == "<<":
                        res = li << (ri & shift_mask)
                    elif op == ">>":
                        res = li >> (ri & shift_mask)
                    else:  # >>>
                        full_mask = _LONG_MASK if bits == 64 else _INT_MASK
                        res = (li & full_mask) >> (ri & shift_mask)
                    return _to_signed(res, bits)

                if op == "/":
                    if rv == 0:
                        return None  # деление на 0 - реальное рантайм-поведение (ArithmeticException/NaN), не гадаем
                    if is_float:
                        return lv / rv
                    # Java int/long-деление усекает К НУЛЮ, Python // - к -inf
                    q = abs(lv) // abs(rv)
                    if (lv < 0) != (rv < 0):
                        q = -q
                    return _to_signed(q, bits)

                if op == "%":
                    if rv == 0:
                        return None
                    if is_float:
                        return math.fmod(lv, rv)
                    # Java % сохраняет знак делимого, как fmod, не Python %
                    r = math.fmod(lv, rv)
                    return _to_signed(int(r), bits)

                if op == "+":
                    res = lv + rv
                elif op == "-":
                    res = lv - rv
                elif op == "*":
                    res = lv * rv
                else:
                    return None
                return res if is_float else _to_signed(int(res), bits)
        except (ZeroDivisionError, OverflowError, ValueError):
            return None
    return None


def fold_dead_branches(stmts):
    """Однопроходная свёртка ТОЛЬКО верхнего уровня переданного списка
    операторов (не рекурсивно - вызывающий код, structure.py::simplify_stmt,
    уже рекурсивно спускается во вложенные тела ДО вызова этой функции на
    каждом уровне, так что рекурсия здесь не нужна и не должна дублироваться)."""
    from ast_nodes import IfStmt

    out = []
    changed = False
    for s in stmts:
        if isinstance(s, IfStmt):
            v = try_eval_const(s.cond)
            if v is True:
                out.extend(s.then_body or [])
                _note_removed()
                changed = True
                continue
            if v is False:
                out.extend(s.else_body or [])
                _note_removed()
                changed = True
                continue
        out.append(s)
    return out, changed
