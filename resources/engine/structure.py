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
import re
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
        self._if_chain_depth = 0
        self._terminates_cache = {}
        # Счётчик catch-переменных (e1, e2...) - ОБЩИЙ на весь метод, а не
        # обнуляемый на каждый try/catch (см. _build_try) - иначе два
        # независимых (или, того хуже, вложенных) try/catch в одном методе
        # оба называли бы свою первую catch-переменную "e1", что для
        # вложенного случая - реальная ошибка компиляции ("variable e1 is
        # already defined"), а для соседних - просто путаница при чтении.
        self._catch_var_ctr = 0
        # HANDOFF_18/20: адрес, который ПОСЛЕДНИЙ раз вернул _build_try
        # ИЛИ _build_loop как свою собственную "точку продолжения после
        # try/catch или цикла" (или None). Используется ТОЛЬКО чтобы
        # узнать в _build_if_inner "этот if - ПЕРВЫЙ statement сразу
        # после только что построенного try/catch/цикла?" - см. докстринг
        # у места использования, почему это важно и почему это ДОЛЖНО
        # быть узко (широкое правило регрессировало - см. HANDOFF_17).
        # "Потребляется" (сбрасывается в None) при первом же чтении,
        # чтобы не давать ложных срабатываний на несвязанных if дальше по
        # методу.
        self._last_try_merge_pc = None
        # См. HANDOFF_7/9 - глобальный (на весь метод, не per-region()-call)
        # набор адресов блоков, которые реально попали КУДА-ТО в итоговый
        # AST (через любой путь - тело try, catch, if-ветку, тело цикла,
        # case switch'а...). Нужен для check_full_coverage() ниже - ловит
        # случаи вроде найденного бага с try-with-resources, где точка
        # схождения после try (`ipdom`) в редких случаях "перепрыгивает"
        # реальный код между защищённым диапазоном и обработчиком (см.
        # HANDOFF_7 - там это тихо теряло вызов close() на успешном пути).
        self._all_consumed = set()

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

    class _MergedExc:
        """Лёгкая замена ExceptionEntry - нужны только 4 поля, которые
        читает _prepare_try/остальной код (start_pc/end_pc/catch_type/
        handler_pc), без зависимости от classfile.py."""
        __slots__ = ("start_pc", "end_pc", "catch_type", "handler_pc")
        def __init__(self, start_pc, end_pc, catch_type, handler_pc):
            self.start_pc = start_pc
            self.end_pc = end_pc
            self.catch_type = catch_type
            self.handler_pc = handler_pc

    def _merge_split_exception_ranges(self, exceptions):
        """javac иногда режет ОДИН логический try-блок на НЕСКОЛЬКО смежных
        диапазонов в exception-table для ОДНОГО И ТОГО ЖЕ (catch_type,
        handler_pc) - реальный найденный случай (см. HANDOFF_11):
        `SkullUtils.applyPlayerProfileSkullReflect` - 3 типа исключений,
        КАЖДЫЙ дважды, с диапазонами (0,32) и (33,120) - ИДЕНТИЧНЫЙ
        handler_pc для обеих половин каждого типа. Раньше `_prepare_try`
        группировал СТРОГО по (start_pc,end_pc), из-за чего эти две
        половины строились как ДВА вложенных try/catch с задвоенными
        catch-блоками (и невалидными именами catch-переменных вне области
        видимости - `e1` из внутреннего catch использовался во внешнем).
        Сшиваем смежные (end предыдущего == start следующего) диапазоны
        с ОДИНАКОВЫМ (catch_type, handler_pc) обратно в один ПЕРЕД
        построением try/catch."""
        by_group = {}
        order = []
        for e in exceptions:
            k = (e.catch_type, e.handler_pc)
            if k not in by_group:
                by_group[k] = []
                order.append(k)
            by_group[k].append(e)
        merged = []
        for k in order:
            es = sorted(by_group[k], key=lambda e: e.start_pc)
            # Сшиваем ВСЕ диапазоны одного (catch_type, handler_pc) в один
            # span (min start .. max end), а не только строго смежные -
            # разрыв между ними бывает даже в 1 байткод-инструкцию (см.
            # найденный случай: `ireturn` сам по себе бросить исключение
            # не может, поэтому компилятор законно исключил именно эту
            # ОДНУ инструкцию из защищённого диапазона, хотя семантически
            # `return false;` находится ВНУТРИ того же try). Если один и
            # тот же (catch_type, handler_pc) встречается в methode
            # несколько раз - это практически всегда один логический
            # try, а не совпадение (см. HANDOFF_11).
            catch_type, handler_pc = k
            merged.append(self._MergedExc(es[0].start_pc, es[-1].end_pc, catch_type, handler_pc))
        return merged

    def _prepare_try(self):
        by_key = {}
        order = []
        for e in self._merge_split_exception_ranges(self.exceptions):
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
        stmts = self.region(entry_pc, frozenset())
        self._check_full_coverage(entry_pc)
        return stmts

    def _check_full_coverage(self, entry_pc):
        """См. HANDOFF_7/9. После полной структуризации метода - проверяем,
        что КАЖДЫЙ блок, реально достижимый из entry по рёбрам CFG (включая
        рёбра исключений - блок обработчика тоже "достижим"), попал хоть
        куда-то в итоговый AST (`self._all_consumed`, см. region()). Если
        нет - значит где-то в процессе структуризации часть живого кода
        тихо потерялась (найденный реальный случай - try-with-resources,
        нормальный путь `if (x != null) x.close();` между защищённым
        диапазоном try и его обработчиком, см. HANDOFF_7 подробный разбор).

        Откатываемся на честный байткод для ВСЕГО метода вместо того, чтобы
        печатать код с тихо пропавшим statement'ом - это прямое продолжение
        ключевого принципа архитектуры (см. HANDOFF_1). Не пытаемся сами
        угадать/пофиксить точку схождения - это, как показала практика (см.
        HANDOFF_7 - первая попытка чинить `_build_try` эвристикой "бери end,
        если он раньше ipdom" уронила процент декомпиляции с 93 до 85 на
        других, ранее корректных методах), слишком легко сломать другие,
        уже рабочие случаи вслепую, без большого корпуса реальных .jar под
        рукой для регрессии."""
        reachable = set()
        stack = [entry_pc]
        while stack:
            b = stack.pop()
            if b in reachable or b not in self.cfg.blocks:
                continue
            reachable.add(b)
            stack.extend(self.cfg.blocks[b].succs)
        missing = reachable - self._all_consumed
        # Безобидное исключение: блок, состоящий РОВНО из одного безусловного
        # goto и не производящий ни одного видимого statement'а
        # (self.results[pc].stmts пуст) - тот самый "трамплин", который
        # region()/_resolve_jump_stmt уже сознательно умеют прозрачно
        # пропускать в других местах (см. их докстринг) - у него физически
        # нечего терять, поэтому это НЕ тот случай потери кода, который эта
        # проверка ищет.
        missing = {
            pc for pc in missing
            if not (
                len(self.cfg.blocks[pc].instrs) == 1
                and self.cfg.blocks[pc].instrs[0].mnemonic in ("goto", "goto_w")
                and not self.results[pc].stmts
            )
        }
        if missing:
            raise DecompileAbort(
                f"после структуризации остались недостижимые из AST, но живые "
                f"по CFG блоки: {sorted(missing)} - похоже на потерю кода, "
                f"откат на байткод"
            )

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
            self._all_consumed.add(pc)

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

    def _find_forward_merge(self, true_t, false_t, stop_addrs, exclude_starts=False):
        """Пытается найти общую точку схождения двух веток if, когда
        ipdom(pc) её не увидел (обычно из-за консервативных рёбер
        исключений - см. HANDOFF_7 Аддендум 13). Идёт вперёд по CFG
        (ограниченный BFS) от каждой ветки и ищет ближайший блок, до
        которого доходят ОБЕ, НЕ проходя через обработчики исключений
        (`cfg.py` подмешивает рёбра try->handler прямо в block.succs -
        без фильтрации BFS проваливается внутрь catch-машинерии и находит
        ложное "слияние" там, а не в реальном коде - так уже ловилось
        один раз при первой попытке этого фикса, отсюда фильтр). Чисто
        эвристика-фоллбэк: если ошибётся - `_check_full_coverage()` (см.
        Аддендум 3) поймает недостигнутый код и откатит МЕТОД на честный
        байткод, а не тихо испортит вывод.

        exclude_starts: не считать true_t/false_t сами по себе валидным
        слиянием. Нужно ТОЛЬКО когда true_t и false_t раньше уже были
        определены как явные ветки одного if (см. вызов из XOR-фоллбэка
        ниже) - там true_t может быть ЧУЖОЙ shared jump-target (см.
        SQLite.isConnected(), где false-ветка сама содержит ещё один if,
        одна из веток которого ведёт на тот же адрес, что true_t внешнего
        if) - без исключения BFS находит этот shared-адрес как "общую"
        точку, что даёт ПУСТОЕ then_body (see HANDOFF_8/9).
        Для общего вызова (оба branch термально расходятся, не связаны с
        конкретным shared-target паттерном) исключать НЕЛЬЗЯ - здесь
        true_t/false_t сами по себе иногда И ЕСТЬ правильный merge
        (сломало InventoryListener.onInventoryClick при первой попытке
        исключать всегда - см. HANDOFF_9)."""
        def reachable(start):
            seen = set()
            frontier = [start]
            limit = 4000  # разумный потолок для одного метода
            while frontier and len(seen) < limit:
                pc = frontier.pop()
                if pc in seen or pc not in self.cfg.blocks:
                    continue
                block = self.cfg.blocks[pc]
                if block.handler_types:
                    continue  # вход в обработчик исключений - не идём дальше
                seen.add(pc)
                if pc in stop_addrs:
                    # это уже известная внешняя граница ("конец метода"/
                    # продолжение снаружи) - она САМА по себе валидный
                    # кандидат на точку схождения (обе ветки реально в неё
                    # упираются), просто дальше неё идти незачем - это чужая
                    # территория (см. HANDOFF_8: раньше stop_addrs исключались
                    # целиком, из-за чего вложенный if внутри isConnected() не
                    # находил свою реальную точку схождения - она и была этой
                    # внешней границей).
                    continue
                # ТОЛЬКО рёбра "вперёд" (target > pc) - back-edge цикла
                # (continue/повтор итерации) уводит обратно к началу цикла,
                # где "min(общих блоков)" перестаёт значить что-либо
                # осмысленное (внутри цикла почти всё технически достижимо
                # из всего) - конкретно на этом сломался реальный метод со
                # while-циклом (SkullUtils.invokeSetSkin) при первой
                # проверке фикса на EryBuyer-v1.jar.
                frontier.extend(s for s in block.succs if s > pc)
            return seen
        common = reachable(true_t) & reachable(false_t)
        if exclude_starts:
            common = common - {true_t, false_t}
        if not common:
            return None
        return min(common)

    def _build_if(self, pc, cond, true_t, false_t, stop_addrs):
        # Явный лимит глубины if/else-цепочки (region <-> _build_if
        # взаимно рекурсивны - каждое звено "else if" добавляет уровень).
        # Встречается в реальности на огромных сгенерированных
        # диспетчер-методах (напр. javassist, найдено на реальном тесте -
        # см. HANDOFF_4) - вместо невнятного RecursionError (который к тому
        # же зависит от ОБЩЕГО лимита рекурсии Python, разделяемого со всеми
        # остальными вызовами в этом же стеке - emit_expr/_is_terminating и
        # т.п.) - явный, предсказуемый и безопасный откат с понятной причиной.
        self._if_chain_depth += 1
        if self._if_chain_depth > 800:
            self._if_chain_depth -= 1
            raise DecompileAbort(
                f"if/else-цепочка длиннее {800} уровней подряд - похоже на "
                f"сгенерированную таблицу диспетчеризации, рекурсивный "
                f"построитель для такого не годится")
        try:
            return self._build_if_inner(pc, cond, true_t, false_t, stop_addrs)
        finally:
            self._if_chain_depth -= 1

    def _build_if_inner(self, pc, cond, true_t, false_t, stop_addrs):
        # HANDOFF_18/20: "потребляем" флаг СРАЗУ, до любых вложенных
        # вызовов (которые сами могут пройти через _build_try/_build_loop
        # и перезаписать его) - см. докстринг у места использования ниже.
        was_right_after_try = (pc == self._last_try_merge_pc)
        self._last_try_merge_pc = None
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
                if raw is None:
                    raw = self._find_forward_merge(true_t, false_t, stop_addrs, exclude_starts=True)
                merge = raw if raw is not None else false_t
            elif f_term and not t_term:
                raw = self.ipdom.get(true_t)
                if raw is None:
                    raw = self._find_forward_merge(true_t, false_t, stop_addrs, exclude_starts=True)
                merge = raw if raw is not None else true_t
            if merge is None:
                merge = self._find_forward_merge(true_t, false_t, stop_addrs)
        if was_right_after_try and merge is not None:
            # HANDOFF_18: этот if - ПЕРВЫЙ statement сразу после только
            # что построенного try/catch (см. `_last_try_merge_pc`,
            # выставляется в _build_try). Именно в ЭТОЙ конкретной
            # позиции ipdom() иногда "перепрыгивает" ДАЛЬШЕ, чем нужно -
            # через рёбра исключений (которые только что построенный
            # try/catch добавил в граф) он может честно увидеть ОБЩИЙ
            # постдоминатор где-то далеко (напр. `return` в самом конце
            # метода), хотя обе ветки на самом деле сходятся намного
            # РАНЬШЕ - и между "рано" и "поздно" есть настоящий код,
            # который при таком merge молча теряется (см. HANDOFF_17 -
            # FunnyClans.DatabaseManager.setupDatabase(): `if (resource
            # != null) resource.close();` сразу после try/catch - ipdom
            # сказал "160" (конец метода) вместо настоящих 89).
            #
            # В HANDOFF_17 то же самое исправление уже пробовалось, но
            # БЕЗ этого узкого условия (применялось ко ВСЕМ if без
            # разбора) - дало чистую РЕГРЕССИЮ на широком корпусе
            # (ChatFilterPlus -18, MSG -22, TowerClans -5 и т.д.),
            # откачено. Здесь условие сужено ИМЕННО до того случая,
            # который был проверен и подтверждён - если регрессий не
            # будет и на этот раз, можно постепенно расширять условие
            # дальше, но НЕ раньше повторной широкой проверки.
            forward = self._find_forward_merge(true_t, false_t, stop_addrs)
            if forward is not None and forward < merge:
                merge = forward
        if merge is not None and any(merge == entry["header"] for entry in self.loop_stack):
            # ipdom() честно вычисляет заголовок ОБЪЕМЛЮЩЕГО цикла как
            # общий постдоминатор (все пути от pc рано или поздно
            # возвращаются в начало цикла - это математически верно), но
            # использовать его как "точку продолжения ПОСЛЕ if" нельзя:
            # код тела цикла уже строится ОТДЕЛЬНЫМ вызовом region() (см.
            # _build_loop) - попадание туда изнутри if это continue, а
            # НЕ обычное линейное продолжение сканирования. Из-за этого
            # `region()` пытался повторно построить уже строящийся
            # заголовок цикла и падал в "нередуцируемый переход внутри
            # региона" (см. HANDOFF_15 - найдено на bStats
            # `getChartData()`: `for (Entry e : map.entrySet()) { if
            # (e.getValue() <= 0) continue; ... }` - постдоминатор
            # внутреннего if честно указывал на заголовок for-цикла).
            merge = None
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
        # См. HANDOFF_7/16 - вторая, более узкая попытка (первая уронила
        # 93.08% -> 85.53%, откачена - см. подробный разбор в HANDOFF).
        # ipdom(start) иногда "перепрыгивает" через РЕАЛЬНЫЙ код между
        # концом защищённого диапазона (`end`) и найденной точкой схождения
        # - конкретно, когда исключение и обычный путь ведут к РАЗНЫМ
        # исходам (см. try-with-resources баг: catch делает athrow, обычный
        # путь просто идёт себе дальше) - из-за консервативных рёбер
        # исключений от ВЛОЖЕННЫХ/пересекающихся try (см. разбор). Отличаем
        # от безобидного случая "end - это просто goto-трамплин" (там
        # ipdom(start) обычно и так прав, см. getConnection() в HANDOFF) -
        # тем же критерием, что и в _check_full_coverage() ниже: блок из
        # ОДНОГО безусловного goto без собственных statement'ов - пропускаем,
        # его физически нечего терять. Применяем подмену ТОЛЬКО когда в
        # блоке `end` есть реальное содержимое (значит - его пропустить
        # нельзя) И он раньше уже найденной ipdom(start) (никогда не двигаем
        # точку схождения ПОЗЖЕ - это как раз ломало getConnection() в первой
        # попытке).
        #
        # Подстраховка на случай, если эта эвристика всё равно окажется не
        # универсальной: _check_full_coverage() (см. ниже) в любом случае
        # поймает недостающий код и откатит метод на честный байткод, а не
        # тихо потеряет его - так что риск этой правки теперь СИЛЬНО ниже,
        # чем был у первой попытки (тогда такой подстраховки ещё не было).
        if end in self.cfg.blocks and end not in stop_addrs:
            end_block = self.cfg.blocks[end]
            is_trampoline = (
                len(end_block.instrs) == 1
                and end_block.instrs[0].mnemonic in ("goto", "goto_w")
            )
            handler_pcs = {h for _, h in entries}
            if not is_trampoline and end not in handler_pcs:
                if overall_merge is None or end < overall_merge:
                    overall_merge = end
        self._last_try_merge_pc = overall_merge
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


def _rename_local(stmts, old_name, new_name):
    """Переименовывает ВСЕ обращения к Local(old_name) в new_name внутри
    stmts. Используется как для переименования сентинела перехваченного
    исключения (см. _rename_sentinel), так и для случая, когда JVM
    переиспользовал JVM-слот под перехваченное исключение, а имя уже
    занято переменной ДРУГОГО типа снаружи catch-а (см. HANDOFF_12 -
    SQLite.getPlayerData()/var7)."""
    def walk_expr(e):
        if e is None:
            return e
        if isinstance(e, Local) and e.name == old_name:
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


def _rename_sentinel(stmts, new_name):
    _rename_local(stmts, CAUGHT_SENTINEL, new_name)


# ---------------- loop beautification (while(true)+break -> while/do-while/for) ----------------

def simplify_stmts(stmts):
    out = []
    for s in stmts:
        out.append(simplify_stmt(s))
    # Косметическая чистка вслед за рекурсивным фиксом пересечения стека
    # (HANDOFF_13) - тот фикс даёт КОРРЕКТНЫЙ, но многословный код (по
    # несколько __stkN на каждый уровень цепочки). См. HANDOFF_14: цикл
    # до неподвижной точки, потому что каждый проход может открыть новую
    # возможность для другого - например схлопывание цепочки temp'ов
    # может привести if/else к виду, который тернарная свёртка узнаёт
    # только СЕЙЧАС, а результат свёртки, в свою очередь, может стать
    # кандидатом на инлайн в место вызова. Ограничение по числу итераций -
    # чисто защитный пояс (в реальности сходится за 2-3 прохода).
    for _ in range(4):
        out = _fold_boolean_materialization(out)
        out = _collapse_temp_chains(out)
        out = _hoist_common_branch_tail(out)
        out = _inline_single_use_temps_anywhere(out)
    return out


_SYNTH_TEMP_RE = re.compile(r"^__stk\d+$")


def _is_synth_temp(name):
    return bool(_SYNTH_TEMP_RE.match(name))


def _contains_local_ref(node, name):
    """Есть ли ГДЕ-ЛИБО в node (statement, expression или список того и
    другого) чтение Local(name)? Используется чистящими проходами ниже,
    чтобы убедиться в безопасности схлопывания/подстановки temp-
    переменной - см. _collapse_temp_chains/_inline_single_use_temps_anywhere."""
    if node is None:
        return False
    if isinstance(node, list):
        return any(_contains_local_ref(x, name) for x in node)
    if isinstance(node, Local):
        return node.name == name
    for attr in ("left", "right", "expr", "target", "value", "array", "index",
                 "cond", "tval", "fval", "init", "then_body", "else_body",
                 "body", "update", "selector", "finally_body"):
        v = getattr(node, attr, None)
        if v is not None and _contains_local_ref(v, name):
            return True
    args = getattr(node, "args", None)
    if args and any(_contains_local_ref(a, name) for a in args):
        return True
    dims = getattr(node, "dims", None)
    if dims and any(_contains_local_ref(d, name) for d in dims if d is not None):
        return True
    if hasattr(node, "cases"):
        for c in node.cases:
            if _contains_local_ref(c.body, name):
                return True
    if hasattr(node, "catches"):
        for _, _, cb in node.catches:
            if _contains_local_ref(cb, name):
                return True
    return False


def _collapse_temp_chains(stmts):
    """`__stk4 = A; ... __stk2 = __stk4;` (единственное дальнейшее
    использование __stk4 в этом же списке - простое копирование) ->
    `__stk2 = A;`, __stk4 убирается целиком. Порождается рекурсивным
    фиксом пересечения стека (HANDOFF_13), где значение "проезжает"
    через несколько уровней временных переменных подряд. Затрагивает
    ТОЛЬКО синтетические __stkN - настоящие именованные локальные не
    трогаются. Работает до неподвижной точки (может понадобиться
    несколько проходов, если цепочка длиннее двух звеньев)."""
    stmts = list(stmts)
    changed = True
    while changed:
        changed = False
        n = len(stmts)
        for i in range(n):
            a = _as_assign(stmts[i])
            if a is None:
                continue
            tgt, val = a
            if not (isinstance(tgt, Local) and _is_synth_temp(tgt.name)):
                continue
            uses = [j for j in range(i + 1, n) if _contains_local_ref(stmts[j], tgt.name)]
            if len(uses) != 1:
                continue
            j = uses[0]
            b = _as_assign(stmts[j])
            if b is None:
                continue
            tgt2, val2 = b
            if not (isinstance(val2, Local) and val2.name == tgt.name):
                continue  # единственное использование - не простое копирование
            stmts = stmts[:i] + stmts[i + 1:j] + [ExprStmt(Assign(tgt2, val))] + stmts[j + 1:]
            changed = True
            break
    return stmts


def _hoist_common_branch_tail(stmts):
    """Если ПОСЛЕДНИЕ statements then_body и else_body - идентичные
    простые копирования `t = u;` (одно и то же t и одно и то же u в
    обеих ветках - типично для значения, которое просто "проезжает"
    через if, не меняясь, см. HANDOFF_13/14), выносим их из ОБЕИХ веток
    наружу, после if (порядок не важен - от cond они не зависят)."""
    out = []
    for s in stmts:
        if isinstance(s, IfStmt) and s.then_body and s.else_body:
            tb, eb = list(s.then_body), list(s.else_body)
            tail = []
            while tb and eb:
                a1, a2 = _as_assign(tb[-1]), _as_assign(eb[-1])
                if a1 is None or a2 is None:
                    break
                t1, v1 = a1
                t2, v2 = a2
                if isinstance(t1, Local) and isinstance(t2, Local) and t1.name == t2.name and \
                        isinstance(v1, Local) and isinstance(v2, Local) and v1.name == v2.name:
                    tail.append(tb.pop())
                    eb.pop()
                    continue
                break
            if tail:
                s.then_body = tb
                s.else_body = eb
                out.append(s)
                out.extend(reversed(tail))
                continue
        out.append(s)
    return out


def _substitute_temp(node, name, replacement):
    """Заменяет ПЕРВОЕ вхождение Local(name) на replacement прямо в
    дереве (мутирует на месте). Возвращает True при успешной замене."""
    for attr in ("left", "right", "expr", "target", "value", "array", "index",
                 "cond", "tval", "fval", "init"):
        v = getattr(node, attr, None)
        if v is None:
            continue
        if isinstance(v, Local) and v.name == name:
            setattr(node, attr, replacement)
            return True
        if hasattr(v, "prec") and _substitute_temp(v, name, replacement):
            return True
    args = getattr(node, "args", None)
    if args:
        for idx, a in enumerate(args):
            if isinstance(a, Local) and a.name == name:
                args[idx] = replacement
                return True
            if hasattr(a, "prec") and _substitute_temp(a, name, replacement):
                return True
    dims = getattr(node, "dims", None)
    if dims:
        for idx, d in enumerate(dims):
            if d is None:
                continue
            if isinstance(d, Local) and d.name == name:
                dims[idx] = replacement
                return True
            if hasattr(d, "prec") and _substitute_temp(d, name, replacement):
                return True
    return False


def _inline_single_use_temps_anywhere(stmts):
    """`__stkN = X; <единственное следующее использование __stkN где-то
    внутри одного выражения>` -> подставляем X прямо в место
    использования, убираем присваивание. Обобщение существующего
    `_inline_single_use_crossing_temps` (engine.py), который делал то же
    самое, но ТОЛЬКО для паттерна `t=X; return t;` - здесь годится ЛЮБОЕ
    следующее выражение-statement (вызов метода, putfield и т.п.), не
    только return. Затрагивает ТОЛЬКО __stkN - см. HANDOFF_14."""
    stmts = list(stmts)
    changed = True
    while changed:
        changed = False
        n = len(stmts)
        for i in range(n):
            a = _as_assign(stmts[i])
            if a is None:
                continue
            tgt, val = a
            if not (isinstance(tgt, Local) and _is_synth_temp(tgt.name)):
                continue
            uses = [j for j in range(i + 1, n) if _contains_local_ref(stmts[j], tgt.name)]
            if len(uses) != 1:
                continue
            j = uses[0]
            target_stmt = stmts[j]
            if isinstance(target_stmt, (ExprStmt, ReturnStmt, ThrowStmt)):
                target_expr = target_stmt.expr
            else:
                continue
            if target_expr is None:
                continue
            if isinstance(target_expr, Local) and target_expr.name == tgt.name:
                continue  # это тот самый case, который уже покрывает _collapse_temp_chains
            if _substitute_temp(target_expr, tgt.name, val):
                stmts = stmts[:i] + stmts[i + 1:]
                changed = True
                break
    return stmts


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
                    # JVM хранит boolean как int (iconst_0/iconst_1) - если
                    # одна сторона тернарника - такая "0/1"-константа
                    # типа "int", а другая - НАСТОЯЩЕЕ boolean-выражение
                    # (сравнение, !x, boolean-метод), тип константы (int)
                    # вводит в заблуждение: результат на самом деле boolean,
                    # а не int. Раньше это давало `int __stk1; ... return
                    # __stk1;` при объявленном `boolean isConnected()` -
                    # несовпадение типов, не компилируется (см. HANDOFF_9 -
                    # предсуществующий баг, докопался при валидации
                    # if/else-фикса, тут наконец чиню).
                    if t1_type == "int" and _as_bool_const(v1) is not None and t2_type == "boolean":
                        v1 = Const("false" if v1.literal == "0" else "true", "boolean")
                        t1_type = "boolean"
                    elif t2_type == "int" and _as_bool_const(v2) is not None and t1_type == "boolean":
                        v2 = Const("false" if v2.literal == "0" else "true", "boolean")
                        t2_type = "boolean"
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
