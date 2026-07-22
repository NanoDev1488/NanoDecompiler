# -*- coding: utf-8 -*-
"""
Оркестрация декомпиляции одного метода: CFG -> символическое исполнение ->
структуризация -> печать Java. При любой неуверенности (DecompileAbort)
откатывается на честный дизассемблированный листинг байткода - НИКОГДА не
выводит код, в котором не уверен.
"""
from ir import decode_method
from cfg import CFG
from stackvm import simulate_block, MethodCtx, DecompileAbort, CAUGHT_SENTINEL, _PSEUDO_TYPES, _MonitorMarker
from ast_nodes import (
    Local, Assign, ExprStmt, LocalDecl, ReturnStmt, ThrowStmt, IfStmt, WhileStmt,
    DoWhileStmt, ForStmt, SyncStmt, SwitchStmt, TryStmt, ArrayAccess, Const,
    FieldAccess, This, MethodCall, BlockStmt,
)
from structure import Structurer, simplify_stmts
from emit import emit_stmts, set_shadow_context
import disassembler


class MethodDecompileResult:
    def __init__(self):
        self.ok = False
        self.java_lines = []
        self.reason = None
        self.ctx = None
        self.n_instructions = 0
        self.n_blocks = 0
        self.stmts = None
        self.pre_lines = []


def _collect_declared_names(stmts):
    """Имена всех LocalDecl где-либо внутри (рекурсивно во вложенные тела)."""
    names = set()

    def walk(lst):
        for s in (lst or []):
            if isinstance(s, LocalDecl):
                names.add(s.name)
            if isinstance(s, IfStmt):
                walk(s.then_body); walk(s.else_body)
            elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
                walk(s.body)
            elif isinstance(s, BlockStmt):
                walk(s.stmts)
            elif isinstance(s, SwitchStmt):
                for c in s.cases:
                    walk(c.body)
            elif isinstance(s, TryStmt):
                walk(s.body)
                for _, _, cb in s.catches:
                    walk(cb)
                walk(s.finally_body)
    walk(stmts)
    return names


def _collect_referenced_names(stmts):
    """Все имена локальных переменных, на которые где-либо внутри есть
    ссылка (Local) - и в выражениях, и как цель присваивания."""
    names = set()

    def walk_expr(e):
        if e is None:
            return
        if isinstance(e, Local):
            names.add(e.name)
            return
        for attr in ("left", "right", "expr", "target", "value", "array", "index",
                     "cond", "tval", "fval"):
            walk_expr(getattr(e, attr, None))
        for a in getattr(e, "args", None) or []:
            walk_expr(a)

    def walk(lst):
        for s in (lst or []):
            if isinstance(s, LocalDecl):
                walk_expr(s.init)
            elif isinstance(s, (ExprStmt, ReturnStmt, ThrowStmt)):
                walk_expr(getattr(s, "expr", None))
            elif isinstance(s, IfStmt):
                walk_expr(s.cond); walk(s.then_body); walk(s.else_body)
            elif isinstance(s, (WhileStmt, DoWhileStmt)):
                walk_expr(s.cond); walk(s.body)
            elif isinstance(s, ForStmt):
                walk_expr(getattr(s, "cond", None)); walk(s.body)
            elif isinstance(s, SyncStmt):
                walk_expr(s.expr); walk(s.body)
            elif isinstance(s, BlockStmt):
                walk(s.stmts)
            elif isinstance(s, SwitchStmt):
                walk_expr(s.expr)
                for c in s.cases:
                    walk(c.body)
            elif isinstance(s, TryStmt):
                walk(s.body)
                for _, _, cb in s.catches:
                    walk(cb)
                walk(s.finally_body)
    walk(stmts)
    return names


def _collect_shallow_referenced_names(stmts):
    """Как _collect_referenced_names, но НЕ спускается во вложенные
    АЛЬТЕРНАТИВНЫЕ ветки исполнения (then/else, тела циклов, тела case'ов,
    catch/finally) - только "условия входа" (if/while/switch-дискриминант)
    и одноходовые конструкции (обычный {} блок, тело try - там нет
    альтернативных путей, они всегда выполняются). Это и есть ключевое
    отличие настоящей утечки области видимости (типично - switch(String):
    int-селектор объявлен в if, использован в switch() СРАЗУ после, без
    альтернативных веток между ними) от безобидного повторного использования
    того же имени/JVM-слота в НЕЗАВИСИМОЙ более поздней ветке if/else -
    именно на этой путанице раньше ловилось много ложных срабатываний
    (см. комментарий у _has_escaping_local_decl)."""
    names = set()

    def walk_expr(e):
        if e is None:
            return
        if isinstance(e, Local):
            names.add(e.name)
            return
        for attr in ("left", "right", "expr", "target", "value", "array", "index",
                     "cond", "tval", "fval"):
            walk_expr(getattr(e, attr, None))
        for a in getattr(e, "args", None) or []:
            walk_expr(a)

    for s in (stmts or []):
        if isinstance(s, LocalDecl):
            walk_expr(s.init)
        elif isinstance(s, (ExprStmt, ReturnStmt, ThrowStmt)):
            walk_expr(getattr(s, "expr", None))
        elif isinstance(s, IfStmt):
            walk_expr(s.cond)
        elif isinstance(s, (WhileStmt, DoWhileStmt)):
            walk_expr(s.cond)
        elif isinstance(s, ForStmt):
            walk_expr(getattr(s, "cond", None))
        elif isinstance(s, SyncStmt):
            walk_expr(s.expr)
        elif isinstance(s, SwitchStmt):
            walk_expr(s.expr)
        elif isinstance(s, BlockStmt):
            names |= _collect_shallow_referenced_names(s.stmts)
        elif isinstance(s, TryStmt):
            names |= _collect_shallow_referenced_names(s.body)
    return names


def _inner_body_of(s):
    """Список statement'ов ВНУТРИ блокового оператора (все ветки разом -
    для целей поиска "что там объявлено", не для порядка выполнения)."""
    if isinstance(s, IfStmt):
        return (s.then_body or []) + (s.else_body or [])
    if isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
        return s.body or []
    if isinstance(s, BlockStmt):
        return s.stmts or []
    if isinstance(s, SwitchStmt):
        return [st for c in s.cases for st in (c.body or [])]
    if isinstance(s, TryStmt):
        return ((s.body or []) + [st for _, _, cb in s.catches for st in (cb or [])] +
                (s.finally_body or []))
    return None


def _strip_decl_to_assign(lst, names, types):
    """Рекурсивно заменяет LocalDecl(name in names) на обычное присваивание
    (объявление теперь будет НАД блоком - см. _hoist_escaping_locals) - во
    всех вложенных телах, попутно запоминая исходный тип для внешнего
    объявления."""
    new = []
    for st in (lst or []):
        if isinstance(st, LocalDecl) and st.name in names:
            types.setdefault(st.name, st.type)
            new.append(ExprStmt(Assign(Local(st.name, st.type), st.init)))
            continue
        if isinstance(st, IfStmt):
            st.then_body = _strip_decl_to_assign(st.then_body, names, types)
            st.else_body = _strip_decl_to_assign(st.else_body, names, types) if st.else_body else st.else_body
        elif isinstance(st, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            st.body = _strip_decl_to_assign(st.body, names, types)
        elif isinstance(st, BlockStmt):
            st.stmts = _strip_decl_to_assign(st.stmts, names, types)
        elif isinstance(st, SwitchStmt):
            for c in st.cases:
                c.body = _strip_decl_to_assign(c.body, names, types)
        elif isinstance(st, TryStmt):
            st.body = _strip_decl_to_assign(st.body, names, types)
            st.catches = [(t, v, _strip_decl_to_assign(cb, names, types)) for t, v, cb in st.catches]
            if st.finally_body:
                st.finally_body = _strip_decl_to_assign(st.finally_body, names, types)
        new.append(st)
    return new


def _hoist_escaping_locals(stmts):
    """Настоящее исправление (не просто детект-и-откат): если внутри
    if/while/for/try(body)/switch объявлена (LocalDecl) переменная, а
    используется она и ПОСЛЕ этого блока (см. _collect_shallow_referenced_names -
    только "тот же уровень", не заглядывая в альтернативные ветки) -
    поднимаем голое объявление `Type name;` НАД блоком, а исходный LocalDecl
    внутри превращаем в обычное присваивание. Схлопывает и javac-идиому
    switch(String) через hashCode-селектор (int объявлен в if, используется в
    switch() после), и паттерн guard-clause вида `if (cond) {setup...} else
    {return;} <используем setup...>` (одна из веток всегда завершается -
    поток исполнения корректен, но лексическая область видимости - нет).
    Рекурсивно чинит все вложенные тела, снизу вверх."""
    fixed = []
    for s in (stmts or []):
        if isinstance(s, IfStmt):
            s.then_body = _hoist_escaping_locals(s.then_body)
            s.else_body = _hoist_escaping_locals(s.else_body) if s.else_body else s.else_body
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            s.body = _hoist_escaping_locals(s.body)
        elif isinstance(s, BlockStmt):
            s.stmts = _hoist_escaping_locals(s.stmts)
        elif isinstance(s, SwitchStmt):
            for c in s.cases:
                c.body = _hoist_escaping_locals(c.body)
        elif isinstance(s, TryStmt):
            s.body = _hoist_escaping_locals(s.body)
            s.catches = [(t, v, _hoist_escaping_locals(cb)) for t, v, cb in s.catches]
            if s.finally_body:
                s.finally_body = _hoist_escaping_locals(s.finally_body)
        fixed.append(s)

    out = []
    n = len(fixed)
    for i, s in enumerate(fixed):
        inner = _inner_body_of(s)
        if inner:
            declared = _collect_declared_names(inner)
            escaping = (declared & _collect_shallow_referenced_names(fixed[i + 1:])) if declared else set()
            if escaping:
                types = {}
                if isinstance(s, IfStmt):
                    s.then_body = _strip_decl_to_assign(s.then_body, escaping, types)
                    s.else_body = _strip_decl_to_assign(s.else_body, escaping, types) if s.else_body else s.else_body
                elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
                    s.body = _strip_decl_to_assign(s.body, escaping, types)
                elif isinstance(s, BlockStmt):
                    s.stmts = _strip_decl_to_assign(s.stmts, escaping, types)
                elif isinstance(s, SwitchStmt):
                    for c in s.cases:
                        c.body = _strip_decl_to_assign(c.body, escaping, types)
                elif isinstance(s, TryStmt):
                    s.body = _strip_decl_to_assign(s.body, escaping, types)
                    s.catches = [(t, v, _strip_decl_to_assign(cb, escaping, types)) for t, v, cb in s.catches]
                    if s.finally_body:
                        s.finally_body = _strip_decl_to_assign(s.finally_body, escaping, types)
                for name in sorted(escaping):
                    out.append(LocalDecl(types.get(name, "Object"), name, None))
        out.append(s)
    return out


def _has_escaping_local_decl(stmts):
    """True, если где-то объявленная (LocalDecl) переменная используется
    ПОСЛЕ блока, в котором она объявлена (в одном из следующих sibling-
    операторов того же списка) - типичный случай: javac компилирует
    switch(String) через вспомогательный int-селектор, объявленный внутри
    if, но используемый в switch() уже после него. Структуризация тут
    ненадёжна (переменная физически не будет видна в Java-коде за пределами
    блока) - честно откатываемся на байткод вместо гарантированной ошибки
    компиляции "cannot find symbol".

    ВАЖНО: "используется позже" проверяется через _collect_shallow_referenced_names
    (не полный _collect_referenced_names) - иначе ловится масса ложных
    срабатываний на безобидном переиспользовании одного имени/слота в
    независимой более поздней ветке if/else (см. её докстринг - на этом уже
    один раз наступили: -8..-14 п.п. успешности на реальных jar)."""
    def check(lst):
        for i, s in enumerate(lst):
            inner = None
            if isinstance(s, IfStmt):
                inner = (s.then_body or []) + (s.else_body or [])
            elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
                inner = s.body or []
            elif isinstance(s, BlockStmt):
                inner = s.stmts or []
            elif isinstance(s, SwitchStmt):
                inner = [st for c in s.cases for st in (c.body or [])]
            elif isinstance(s, TryStmt):
                inner = ((s.body or []) + [st for _, _, cb in s.catches for st in (cb or [])] +
                         (s.finally_body or []))
            if inner:
                declared = _collect_declared_names(inner)
                if declared and (declared & _collect_shallow_referenced_names(lst[i + 1:])):
                    return True
                if check(inner):
                    return True
        return False
    return check(stmts)


def _contains_unfolded_monitor(stmts):
    """True, если где-то во вложенных statement'ах остался НЕ свёрнутый в
    SyncStmt monitorenter/monitorexit (_MonitorMarker). Свёртка
    monitorenter/monitorexit -> `synchronized(x){...}` (SyncStmt) в этой
    версии движка НЕ реализована (SyncStmt нигде не конструируется) -
    поэтому _MonitorMarker всегда означает несвёрнутый synchronized-блок.
    Печатать его как есть (просто комментарий на месте monitorenter/exit)
    ПОЛНОСТЬЮ теряет семантику блокировки и часто соседствует с изломанной
    структуризацией try/catch вокруг него (см. HANDOFF - найдено на
    реальном плагине: `this var7 = this;` + бесконечный `while(true)` без
    выхода) - именно тот случай, когда по ключевому принципу архитектуры
    нужно откатываться на честный bytecode-листинг, а не печатать код, в
    котором нет уверенности."""
    def visit_list(lst):
        return any(visit_stmt(s) for s in (lst or []))

    def visit_stmt(s):
        if isinstance(s, _MonitorMarker):
            return True
        if isinstance(s, IfStmt):
            return visit_list(s.then_body) or visit_list(s.else_body)
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            return visit_list(s.body)
        elif isinstance(s, BlockStmt):
            return visit_list(s.stmts)
        elif isinstance(s, SwitchStmt):
            return any(visit_list(c.body) for c in s.cases)
        elif isinstance(s, TryStmt):
            if visit_list(s.body):
                return True
            for _, _, cb in s.catches:
                if visit_list(cb):
                    return True
            return visit_list(s.finally_body)
        return False

    return visit_list(stmts)


def decompile_method_body(cf, method, renamer, known_internal_by_dotted, class_internal, indent=2, enum_ordinals=None, switchmap_tables=None):
    """Возвращает MethodDecompileResult. Никогда не кидает исключений наружу -
    любая проблема превращается в ok=False + честный дизассемблированный листинг."""
    result = MethodDecompileResult()
    if method.code is None:
        result.ok = True
        result.java_lines = []
        return result

    try:
        instrs, order = decode_method(method.code)
        result.n_instructions = len(order)
        cfg = CFG(instrs, order, method.exceptions)
        result.n_blocks = len(cfg.blocks)
        ctx = MethodCtx(cf, method, renamer, known_internal_by_dotted, class_internal)
        result.ctx = ctx

        seeds = {}
        for start, blk in cfg.blocks.items():
            if blk.handler_types:
                seeds[start] = [Local(CAUGHT_SENTINEL, "Throwable")]

        results = {}
        underflow_starts = {}
        for start, blk in cfg.blocks.items():
            seed = list(seeds.get(start, []))
            flag = {}
            res = simulate_block(blk, seed, ctx, underflow_flag=flag)
            results[start] = res
            if flag.get("missing"):
                underflow_starts[start] = len(flag["missing"])

        # разрешаем пересечения стека между блоками (тернарные/логические
        # выражения, arr[i] = cond ? a : b, и т.п.) - возможно НЕСКОЛЬКО
        # значений сразу (K), пересекающих границу блока одновременно.
        for cpc, k in underflow_starts.items():
            preds = cfg.blocks[cpc].preds
            if not preds:
                raise DecompileAbort("унаследованное значение стека без предшественников")
            producers = [p for p in preds if len(results[p].exit_stack) >= 1]
            if len(producers) != len(preds):
                raise DecompileAbort("не все предшественники поставляют значение через границу блока")
            for p in producers:
                if len(results[p].exit_stack) != k:
                    raise DecompileAbort("несогласованная глубина пересечения стека между предшественниками")

            temp_names = []
            for j in range(k):
                sample = results[producers[0]].exit_stack[k - 1 - j]
                t = ctx.stack_temp_for((cpc, j), "A")
                sample_type = getattr(sample, "type", "Object") or "Object"
                if sample_type in _PSEUDO_TYPES:
                    # "null"/"this" - внутренние маркеры-псевдотипы (см.
                    # stackvm.py: Const("null","null"), This.type=="this"), а не
                    # настоящие Java-типы - печатать их как тип переменной нельзя
                    # ("null __stk1;" / "this __stk1;" невалидны). И null, и this
                    # всегда можно безопасно объявить как Object.
                    sample_type = "Object"
                ctx.crossing_temp_types[t] = sample_type
                temp_names.append(t)

            for p in producers:
                pres = results[p]
                for j in range(k):
                    real = pres.exit_stack[k - 1 - j]
                    typ = ctx.crossing_temp_types[temp_names[j]]
                    pres.stmts.append(ExprStmt(Assign(Local(temp_names[j], typ), real)))
                pres.exit_stack = []

            seed2 = [Local(temp_names[j], ctx.crossing_temp_types[temp_names[j]]) for j in reversed(range(k))]
            flag2 = {}
            results[cpc] = simulate_block(cfg.blocks[cpc], seed2, ctx, underflow_flag=flag2)
            if flag2.get("missing"):
                raise DecompileAbort("двойное пересечение стека не поддерживается")

        for start, res in results.items():
            if res.exit_stack:
                raise DecompileAbort(f"неразрешённый остаток на стеке в блоке {start}")

        structurer = Structurer(cfg, results, method.exceptions, ctx)
        stmts = structurer.build(cfg.entry)
        stmts = simplify_stmts(stmts)
        if method.name == "<init>":
            stmts = _reorder_ctor_call_to_front(stmts)
        stmts = _inline_single_use_crossing_temps(stmts, ctx)
        stmts = _fold_array_literals(stmts, ctx)
        if enum_ordinals or switchmap_tables:
            stmts = _desugar_enum_switches(stmts, enum_ordinals or {}, ctx, switchmap_tables)
        if stmts and isinstance(stmts[-1], ReturnStmt) and stmts[-1].expr is None:
            stmts = stmts[:-1]
        _refresh_crossing_temp_types(stmts, ctx)
        _declared_seed = {info["name"]: info["type"] for info in ctx.locals.values() if info.get("is_param")}
        _declared_seed.update(ctx.crossing_temp_types)
        stmts = _ensure_local_declarations(stmts, _declared_seed)
        stmts = _hoist_escaping_locals(stmts)
        _prune_unused_imports(stmts, ctx)
        if _contains_unfolded_monitor(stmts):
            raise DecompileAbort("synchronized-блок не свёрнут (monitorenter/monitorexit)")
        if _has_escaping_local_decl(stmts):
            raise DecompileAbort("переменная объявлена в блоке, но используется за его пределами "
                                  "(типично для switch(String) через hashCode) - структуризация ненадёжна")

        pre_lines = []
        for name, typ in ctx.crossing_temp_types.items():
            pre_lines.append(f"{'    ' * indent}{_simple_type(typ)} {name};")

        set_shadow_context(ctx)
        body_lines = emit_stmts(stmts, indent)
        result.ok = True
        result.stmts = stmts
        result.pre_lines = pre_lines
        result.java_lines = pre_lines + body_lines
        return result
    except DecompileAbort as e:
        result.ok = False
        result.reason = str(e)
        return result
    except Exception as e:  # защитный пояс: любая непредвиденная ошибка тоже безопасно откатывается
        result.ok = False
        result.reason = f"внутренняя ошибка декомпилятора: {type(e).__name__}: {e}"
        return result


def _desugar_enum_switches(stmts, enum_ordinals, ctx, switchmap_tables=None):
    def visit_list(lst):
        for s in lst:
            visit_stmt(s)
        return lst

    def visit_stmt(s):
        if isinstance(s, SwitchStmt):
            _try_desugar_one(s, enum_ordinals, ctx, switchmap_tables)
            for c in s.cases:
                visit_list(c.body)
        elif isinstance(s, IfStmt):
            if s.then_body:
                visit_list(s.then_body)
            if s.else_body:
                visit_list(s.else_body)
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            visit_list(s.body)
        elif isinstance(s, TryStmt):
            visit_list(s.body)
            for _, _, cb in s.catches:
                visit_list(cb)
            if s.finally_body:
                visit_list(s.finally_body)

    visit_list(stmts)
    return stmts


def _try_desugar_one(switch_stmt, enum_ordinals, ctx, switchmap_tables=None):
    from ast_nodes import ArrayAccess, FieldAccess, MethodCall
    sel = switch_stmt.selector
    if not (isinstance(sel, ArrayAccess) and isinstance(sel.array, FieldAccess) and
            sel.array.static and sel.array.target is None and "SwitchMap" in sel.array.name):
        return
    idx = sel.index
    if not (isinstance(idx, MethodCall) and idx.name == "ordinal" and not idx.args and idx.target is not None):
        return
    enum_expr = idx.target
    enum_type = getattr(enum_expr, "type", None)
    if not enum_type:
        return

    # приоритет 1: точная таблица N -> имя константы, извлечённая напрямую из
    # байткода <clinit> синтетического switch-map класса (switchmap.py) -
    # всегда верна, даже если case-метки идут не в порядке объявления констант
    exact = None
    if switchmap_tables is not None:
        exact = switchmap_tables.get((sel.array.owner, sel.array.name))

    new_cases = []
    if exact is not None:
        for c in switch_stmt.cases:
            if c.is_default:
                new_cases.append(c)
                continue
            new_values = []
            ok = True
            for v in c.values:
                try:
                    name = exact.get(int(v))
                except ValueError:
                    name = None
                if name is None:
                    ok = False
                    break
                new_values.append(name)
            if not ok:
                return
            c.values = new_values
            new_cases.append(c)
        switch_stmt.cases = new_cases
        switch_stmt.selector = enum_expr
        return

    # приоритет 2 (fallback): эвристика "N-1 == ordinal объявления" - верна в
    # подавляющем большинстве случаев, но может ошибиться, если case-метки в
    # исходном switch шли не в порядке объявления констант enum
    internal = ctx.known.get(enum_type.rstrip("[]"))
    if internal is None:
        return
    names = enum_ordinals.get(internal)
    if not names:
        return
    for c in switch_stmt.cases:
        if c.is_default:
            new_cases.append(c)
            continue
        new_values = []
        ok = True
        for v in c.values:
            try:
                ordinal = int(v) - 1
            except ValueError:
                ok = False
                break
            if not (0 <= ordinal < len(names)):
                ok = False
                break
            new_values.append(names[ordinal])
        if not ok:
            return  # что-то не сошлось - не трогаем switch вовсе, безопасный откат
        c.values = new_values
        new_cases.append(c)
    switch_stmt.cases = new_cases
    switch_stmt.selector = enum_expr


def _reorder_ctor_call_to_front(stmts):
    """В байткоде синтетических конструкторов anonymous/inner-классов поля
    захваченных переменных (val$xxx) пишутся ДО invokespecial super() - это
    валидный байткод (JVM разрешает запись полей ДАННОГО класса до вызова
    super(), запрещая лишь чтение/вызовы через ещё не инициализированный
    this у СУПЕРКЛАССА). Но в исходном Java super()/this() ОБЯЗАН быть
    первым оператором - поэтому переставляем его в начало, если перед ним
    стоят только простые присваивания `this.field = argN;` (без побочных
    эффектов, безопасно переставляемые)."""
    idx = None
    for i, s in enumerate(stmts):
        if isinstance(s, ExprStmt) and isinstance(s.expr, MethodCall) and s.expr.is_ctor:
            idx = i
            break
    if idx is None or idx == 0:
        return stmts
    for s in stmts[:idx]:
        if not (isinstance(s, ExprStmt) and isinstance(s.expr, Assign) and
                isinstance(s.expr.target, FieldAccess) and isinstance(s.expr.target.target, This) and
                isinstance(s.expr.value, (Local, Const))):
            return stmts  # непредвиденный паттерн - не рискуем, оставляем как есть
    return [stmts[idx]] + stmts[:idx] + stmts[idx + 1:]


def _fold_array_literals(stmts, ctx):
    from ast_nodes import NewArray

    def pass_(lst):
        out = []
        i = 0
        n_total = len(lst)
        while i < n_total:
            cur = lst[i]
            folded = False
            if isinstance(cur, LocalDecl) and isinstance(cur.init, NewArray) and \
                    cur.init.initializer is None and len(cur.init.dims) == 1 and \
                    isinstance(cur.init.dims[0], Const) and cur.init.dims[0].type == "int":
                try:
                    size = int(cur.init.dims[0].literal)
                except ValueError:
                    size = -1
                if 0 < size <= 800:
                    values = [None] * size
                    filled = 0
                    j = i + 1
                    while j < n_total and filled < size:
                        s2 = lst[j]
                        tgt = _array_store_target(s2, cur.name)
                        if tgt is None:
                            break
                        idx, val = tgt
                        if not (0 <= idx < size) or values[idx] is not None:
                            break
                        values[idx] = val
                        filled += 1
                        j += 1
                    if filled == size:
                        rest = lst[j:]
                        if _count_local_uses(rest, cur.name) == 1:
                            cur.init.initializer = values
                            cur.init.dims = [None]
                            rest = _substitute_local_once(rest, cur.name, cur.init)
                            out.extend(rest)
                            i = n_total
                            folded = True
            if not folded:
                out.append(cur)
                i += 1
        return out
    return _walk_stmt_lists(stmts, pass_)


def _array_store_target(stmt, array_name):
    if isinstance(stmt, ExprStmt) and isinstance(stmt.expr, Assign):
        tgt = stmt.expr.target
        if isinstance(tgt, ArrayAccess) and isinstance(tgt.array, Local) and \
                tgt.array.name == array_name and isinstance(tgt.index, Const) and tgt.index.type == "int":
            try:
                return int(tgt.index.literal), stmt.expr.value
            except ValueError:
                return None
    return None


def _count_local_uses(node, name):
    names = set()
    if isinstance(node, list):
        total = 0
        for x in node:
            total += _count_local_uses(x, name)
        return total
    cnt = [0]

    def walk(n):
        if isinstance(n, Local):
            if n.name == name:
                cnt[0] += 1
            return
        if isinstance(n, list):
            for x in n:
                walk(x)
            return
        for attr in ("expr", "cond", "target", "value", "left", "right", "array", "index",
                     "tval", "fval", "init", "then_body", "else_body", "body", "selector", "update"):
            v = getattr(n, attr, None)
            if v is not None:
                walk(v)
        for attr in ("args", "dims"):
            v = getattr(n, attr, None)
            if v:
                for x in v:
                    if x is not None:
                        walk(x)
        if hasattr(n, "cases"):
            for c in n.cases:
                walk(c.body)
        if hasattr(n, "catches"):
            for _, _, cb in n.catches:
                walk(cb)
    walk(node)
    return cnt[0]


def _substitute_local_once(node, name, replacement):
    """Заменяет ЕДИНСТВЕННОЕ вхождение Local(name) на replacement (по месту,
    рекурсивно). Вызывается только когда _count_local_uses(...) == 1."""
    done = [False]

    def sub(n):
        if done[0] or n is None:
            return n
        if isinstance(n, list):
            for idx in range(len(n)):
                n[idx] = sub(n[idx])
                if done[0]:
                    break
            return n
        if isinstance(n, Local):
            if n.name == name:
                done[0] = True
                return replacement
            return n
        for attr in ("expr", "cond", "target", "value", "left", "right", "array", "index",
                     "tval", "fval", "init"):
            v = getattr(n, attr, None)
            if v is not None:
                setattr(n, attr, sub(v))
                if done[0]:
                    return n
        for attr in ("then_body", "else_body", "body"):
            v = getattr(n, attr, None)
            if v is not None:
                setattr(n, attr, sub(v))
                if done[0]:
                    return n
        for attr in ("args", "dims"):
            v = getattr(n, attr, None)
            if v:
                setattr(n, attr, [sub(x) if x is not None else None for x in v])
                if done[0]:
                    return n
        if hasattr(n, "cases"):
            for c in n.cases:
                c.body = sub(c.body)
                if done[0]:
                    return n
        if hasattr(n, "catches"):
            new_catches = []
            for t, vn, cb in n.catches:
                new_catches.append((t, vn, sub(cb)))
                if done[0]:
                    new_catches.extend(n.catches[len(new_catches):])
                    break
            n.catches = new_catches
        return n

    if isinstance(node, list):
        for idx in range(len(node)):
            node[idx] = sub(node[idx])
            if done[0]:
                break
        return node
    return sub(node)


def _ensure_local_declarations(stmts, declared):
    """Гарантирует, что каждая локальная переменная объявлена (Type name = ...)
    именно в той лексической области, где она реально впервые используется на
    данном пути выполнения. Нужно из-за того, что javac переиспользует один и
    тот же bytecode-слот под РАЗНЫЕ (по типу и смыслу) переменные в
    непересекающихся ветках if/else/try - без этого второе использование
    печаталось бы без объявления типа и не компилировалось."""
    out = []
    for s in stmts:
        if isinstance(s, LocalDecl):
            declared[s.name] = s.type
            out.append(s)
            continue
        if isinstance(s, ExprStmt) and isinstance(s.expr, Assign) and isinstance(s.expr.target, Local):
            name = s.expr.target.name
            if name not in declared:
                val_type = getattr(s.expr.value, "type", None) or s.expr.target.type
                if val_type in _PSEUDO_TYPES:
                    val_type = s.expr.target.type if s.expr.target.type not in _PSEUDO_TYPES else "Object"
                declared[name] = val_type
                out.append(LocalDecl(val_type, name, s.expr.value))
                continue
        out.append(s)
        if isinstance(s, IfStmt):
            if s.then_body:
                s.then_body = _ensure_local_declarations(s.then_body, dict(declared))
            if s.else_body:
                s.else_body = _ensure_local_declarations(s.else_body, dict(declared))
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            s.body = _ensure_local_declarations(s.body, dict(declared))
        elif isinstance(s, SwitchStmt):
            # тело switch - единая лексическая область (case'ы физически один
            # блок), поэтому словарь объявленных имён НЕ копируется, а
            # накапливается последовательно по порядку case'ов
            for c in s.cases:
                c.body = _ensure_local_declarations(c.body, declared)
        elif isinstance(s, TryStmt):
            s.body = _ensure_local_declarations(s.body, dict(declared))
            new_catches = []
            for typ, var, cb in s.catches:
                new_catches.append((typ, var, _ensure_local_declarations(cb, dict(declared))))
            s.catches = new_catches
            if s.finally_body:
                s.finally_body = _ensure_local_declarations(s.finally_body, dict(declared))
    return out


def _prune_unused_imports(stmts, ctx):
    used = set()

    def note(t):
        if t:
            used.add(t.rstrip("[]"))

    def walk_expr(e):
        if e is None or not hasattr(e, "prec"):
            return
        from ast_nodes import (
            FieldAccess, MethodCall, NewObject, NewArray, Cast, InstanceOf,
            ClassLiteral, Local,
        )
        if isinstance(e, FieldAccess) and e.static:
            note(e.owner)
        elif isinstance(e, MethodCall) and (e.static or e.owner):
            note(e.owner)
        elif isinstance(e, NewObject):
            note(e.type)
        elif isinstance(e, NewArray):
            note(e.elem_type)
        elif isinstance(e, Cast):
            note(e.type)
        elif isinstance(e, InstanceOf):
            note(e.check_type)
        elif isinstance(e, ClassLiteral):
            note(e.type_name)
        elif isinstance(e, Local):
            note(e.type)
        for attr in ("left", "right", "expr", "target", "value", "array", "index",
                     "cond", "tval", "fval"):
            walk_expr(getattr(e, attr, None))
        for a in getattr(e, "args", None) or []:
            walk_expr(a)
        for d in getattr(e, "dims", None) or []:
            walk_expr(d)

    def walk_list(lst):
        for s in lst:
            walk_stmt(s)
        return lst

    def walk_stmt(s):
        for attr in ("expr", "cond", "init", "update", "selector"):
            v = getattr(s, attr, None)
            if v is not None and hasattr(v, "prec"):
                walk_expr(v)
            elif attr == "update" and v is not None:
                walk_expr(getattr(v, "expr", None))
        if isinstance(s, LocalDecl):
            note(s.type)
            walk_expr(s.init)
        if isinstance(s, SwitchStmt):
            for c in s.cases:
                walk_list(c.body)
        elif isinstance(s, IfStmt):
            if s.then_body:
                walk_list(s.then_body)
            if s.else_body:
                walk_list(s.else_body)
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            walk_list(s.body)
        elif isinstance(s, TryStmt):
            walk_list(s.body)
            for typ, _, cb in s.catches:
                note(typ)
                walk_list(cb)
            if s.finally_body:
                walk_list(s.finally_body)

    walk_list(stmts)
    ctx.imports = {k: v for k, v in ctx.imports.items() if k in used}


def _simple_type(t):
    return t.rsplit(".", 1)[-1] if "." in t else t


def _walk_stmt_lists(stmts, fn):
    """Применяет fn(list_of_stmts) к stmts и рекурсивно ко всем вложенным
    спискам операторов (тела if/while/for/switch/try/catch)."""
    stmts = fn(stmts)
    for s in stmts:
        if isinstance(s, IfStmt):
            if s.then_body:
                s.then_body = _walk_stmt_lists(s.then_body, fn)
            if s.else_body:
                s.else_body = _walk_stmt_lists(s.else_body, fn)
        elif isinstance(s, (WhileStmt, DoWhileStmt, ForStmt, SyncStmt)):
            s.body = _walk_stmt_lists(s.body, fn)
        elif isinstance(s, SwitchStmt):
            for c in s.cases:
                c.body = _walk_stmt_lists(c.body, fn)
        elif isinstance(s, TryStmt):
            s.body = _walk_stmt_lists(s.body, fn)
            s.catches = [(t, n, _walk_stmt_lists(b, fn)) for t, n, b in s.catches]
            if s.finally_body:
                s.finally_body = _walk_stmt_lists(s.finally_body, fn)
    return stmts


def _inline_single_use_crossing_temps(stmts, ctx):
    def pass_(lst):
        out = []
        i = 0
        while i < len(lst):
            cur = lst[i]
            nxt = lst[i + 1] if i + 1 < len(lst) else None
            if isinstance(cur, ExprStmt) and isinstance(cur.expr, Assign) and \
                    isinstance(cur.expr.target, Local) and cur.expr.target.name in ctx.crossing_temp_types and \
                    isinstance(nxt, ReturnStmt) and isinstance(nxt.expr, Local) and \
                    nxt.expr.name == cur.expr.target.name:
                out.append(ReturnStmt(cur.expr.value))
                i += 2
                continue
            out.append(cur)
            i += 1
        return out
    return _walk_stmt_lists(stmts, pass_)


def _refresh_crossing_temp_types(stmts, ctx):
    seen = {}

    def scan(lst):
        for s in lst:
            if isinstance(s, ExprStmt) and isinstance(s.expr, Assign) and isinstance(s.expr.target, Local):
                name = s.expr.target.name
                if name in ctx.crossing_temp_types and name not in seen:
                    t = getattr(s.expr.value, "type", ctx.crossing_temp_types[name])
                    if t in _PSEUDO_TYPES:
                        t = ctx.crossing_temp_types[name]
                        if t in _PSEUDO_TYPES:
                            t = "Object"
                    seen[name] = t
        return lst
    _walk_stmt_lists(stmts, scan)
    for name, typ in seen.items():
        ctx.crossing_temp_types[name] = typ
    still_used = set()

    def scan_uses(lst):
        for s in lst:
            _collect_local_names(s, still_used)
        return lst
    _walk_stmt_lists(stmts, scan_uses)
    for name in list(ctx.crossing_temp_types):
        if name not in still_used:
            del ctx.crossing_temp_types[name]


def _collect_local_names(node, out):
    if isinstance(node, Local):
        out.add(node.name)
        return
    if isinstance(node, list):
        for x in node:
            _collect_local_names(x, out)
        return
    for attr in ("expr", "cond", "target", "value", "left", "right", "array", "index",
                 "tval", "fval", "init", "then_body", "else_body", "body", "selector",
                 "update"):
        v = getattr(node, attr, None)
        if v is not None:
            _collect_local_names(v, out)
    for attr in ("args", "dims"):
        v = getattr(node, attr, None)
        if v:
            for x in v:
                if x is not None:
                    _collect_local_names(x, out)
    if hasattr(node, "cases"):
        for c in node.cases:
            _collect_local_names(c.body, out)
    if hasattr(node, "catches"):
        for _, _, cb in node.catches:
            _collect_local_names(cb, out)


def fallback_bytecode_listing(cf, method, indent=2):
    """Честный дизассемблированный листинг (старое поведение) - используется,
    когда декомпиляция метода не удалась."""
    pad = "    " * indent
    lines = [f"{pad}// -- не удалось безопасно декомпилировать тело метода, показан байткод --"]
    if method.code is not None:
        disasm = disassembler.disassemble(method.code, cf, method)
        for dl in disasm:
            lines.append(f"{pad}// {dl}")
    return lines
