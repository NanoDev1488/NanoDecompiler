# -*- coding: utf-8 -*-
"""
Построение графа потока управления (CFG) из линейного списка инструкций:
базовые блоки, рёбра, дерево доминаторов, естественные циклы (back edges).
"""


class Block:
    __slots__ = ("start", "end", "instrs", "succs", "preds", "idom",
                 "domfrontier", "kind_hint", "handler_types")

    def __init__(self, start):
        self.start = start
        self.end = None            # pc после последней инструкции блока (exclusive)
        self.instrs = []           # list[Instruction]
        self.succs = []            # list[int] (start pc of successor blocks)
        self.preds = []            # list[int]
        self.idom = None
        self.domfrontier = set()
        self.kind_hint = None      # для аннотаций (напр. 'handler')
        self.handler_types = []    # если это начало exception-handler'а: [(catch_type, ExceptionEntry), ...]

    def __repr__(self):
        return f"<Block {self.start}..{self.end}>"


class CFG:
    def __init__(self, instrs, order, exceptions):
        self.instrs = instrs
        self.order = order
        self.exceptions = exceptions
        self.blocks = {}       # start_pc -> Block
        self.entry = order[0] if order else None
        self._build()
        if self.entry is not None:
            self._compute_dominators()

    def _leaders(self):
        leaders = set()
        if self.order:
            leaders.add(self.order[0])
        for pc in self.order:
            ins = self.instrs[pc]
            if ins.is_branch:
                leaders.add(ins.target)
                leaders.add(ins.next_pc)
            elif ins.is_switch:
                for v, t in ins.targets.items():
                    leaders.add(t)
                leaders.add(ins.next_pc)
            elif ins.is_unconditional:
                leaders.add(ins.next_pc)
        # exception handlers and try-range starts are also leaders
        for e in self.exceptions:
            leaders.add(e.start_pc)
            leaders.add(e.end_pc)
            leaders.add(e.handler_pc)
        leaders = {pc for pc in leaders if pc in self.instrs}
        return leaders

    def _build(self):
        leaders = sorted(self._leaders())
        if not leaders:
            return
        for i, start in enumerate(leaders):
            b = Block(start)
            end = leaders[i + 1] if i + 1 < len(leaders) else (self.order[-1] and self.instrs[self.order[-1]].next_pc)
            pc = start
            while pc is not None and pc < end and pc in self.instrs:
                ins = self.instrs[pc]
                b.instrs.append(ins)
                pc = ins.next_pc
            b.end = pc
            self.blocks[start] = b

        starts = sorted(self.blocks)
        for i, start in enumerate(starts):
            b = self.blocks[start]
            if not b.instrs:
                continue
            last = b.instrs[-1]
            if last.is_switch:
                for v, t in last.targets.items():
                    if t in self.blocks:
                        b.succs.append(t)
            elif last.mnemonic in ("goto", "goto_w"):
                if last.target in self.blocks:
                    b.succs.append(last.target)
            elif last.is_conditional:
                if last.target in self.blocks:
                    b.succs.append(last.target)
                if last.next_pc in self.blocks:
                    b.succs.append(last.next_pc)
            elif last.is_return or last.mnemonic == "athrow":
                pass  # no fallthrough successor
            else:
                if i + 1 < len(starts):
                    b.succs.append(starts[i + 1])
        # exception edges: block covering try range -> handler (approx: any block
        # inside [start_pc,end_pc) gets an edge to handler_pc)
        for e in self.exceptions:
            if e.handler_pc not in self.blocks:
                continue
            for start in starts:
                if e.start_pc <= start < e.end_pc:
                    self.blocks[start].succs.append(e.handler_pc)
                    hb = self.blocks[e.handler_pc]
                    hb.handler_types.append((e.catch_type, e))

        for start, b in self.blocks.items():
            for s in b.succs:
                if start not in self.blocks[s].preds:
                    self.blocks[s].preds.append(start)

    # ---------------- dominators (iterative, simple) ----------------

    def _compute_dominators(self):
        starts = sorted(self.blocks)
        idx = {s: i for i, s in enumerate(starts)}
        # reverse postorder
        rpo = self._reverse_postorder()
        rpo_index = {s: i for i, s in enumerate(rpo)}
        idom = {self.entry: self.entry}
        changed = True
        while changed:
            changed = False
            for b in rpo:
                if b == self.entry:
                    continue
                preds = [p for p in self.blocks[b].preds if p in idom]
                if not preds:
                    continue
                new_idom = preds[0]
                for p in preds[1:]:
                    new_idom = self._intersect(new_idom, p, idom, rpo_index)
                if idom.get(b) != new_idom:
                    idom[b] = new_idom
                    changed = True
        for b in self.blocks:
            self.blocks[b].idom = idom.get(b)
        self.idom = idom

    def _intersect(self, a, b, idom, rpo_index):
        while a != b:
            while rpo_index[a] > rpo_index[b]:
                a = idom[a]
            while rpo_index[b] > rpo_index[a]:
                b = idom[b]
        return a

    def _reverse_postorder(self):
        visited = set()
        order = []
        def dfs(n):
            visited.add(n)
            for s in self.blocks[n].succs:
                if s not in visited and s in self.blocks:
                    dfs(s)
            order.append(n)
        if self.entry in self.blocks:
            dfs(self.entry)
        order.reverse()
        # append unreachable blocks (dead code) at the end, in address order,
        # so nothing is silently dropped
        for s in sorted(self.blocks):
            if s not in visited:
                order.append(s)
        return order

    def dominates(self, a, b):
        """a доминирует над b?"""
        n = b
        seen = 0
        while True:
            if n == a:
                return True
            if n == self.idom.get(n) or n not in self.idom:
                return n == a
            if seen > len(self.blocks) + 2:
                return False
            n = self.idom[n]
            seen += 1

    def natural_loops(self):
        """Возвращает список (header, set(body_blocks), back_edge_sources)."""
        loops = {}
        for start, b in self.blocks.items():
            for s in b.succs:
                if s in self.blocks and self.dominates(s, start):
                    # back edge start -> s ; s is loop header
                    loops.setdefault(s, set()).add(start)
        result = []
        for header, tails in loops.items():
            body = {header}
            stack = list(tails)
            while stack:
                n = stack.pop()
                if n not in body:
                    body.add(n)
                    for p in self.blocks[n].preds:
                        if p not in body:
                            stack.append(p)
            result.append((header, body, tails))
        return result

    def reverse_postorder_list(self):
        return self._reverse_postorder()

    # ---------------- post-dominators (for if/else merge-point detection) ----------------

    def compute_postdominators(self):
        """ipdom[b] = ближайший постдоминатор b (адрес блока, либо EXIT-маркер None
        если ни один реальный блок не является общим постдоминатором - т.е. все пути
        из b завершаются return/throw без общей точки схождения)."""
        EXIT = "__EXIT__"
        preds_rev = {b: list(self.blocks[b].succs) for b in self.blocks}   # reversed succ = pred
        succs_rev = {b: list(self.blocks[b].preds) for b in self.blocks}   # reversed pred = succ
        exit_preds = []
        for b, blk in self.blocks.items():
            if not blk.succs:
                exit_preds.append(b)
        # rpo of reversed graph starting from EXIT
        visited = set()
        order = []
        def dfs(n):
            visited.add(n)
            nxt = exit_preds if n == EXIT else succs_rev.get(n, [])
            for s in nxt:
                if s not in visited:
                    dfs(s)
            order.append(n)
        dfs(EXIT)
        for b in sorted(self.blocks):
            if b not in visited:
                order.append(b)
                visited.add(b)
        order.reverse()
        rpo_index = {n: i for i, n in enumerate(order)}

        idom = {EXIT: EXIT}
        changed = True
        while changed:
            changed = False
            for b in order:
                if b == EXIT:
                    continue
                ps = preds_rev.get(b, [])
                if b in exit_preds:
                    ps = ps + [EXIT]
                ps = [p for p in ps if p in idom]
                if not ps:
                    continue
                new_idom = ps[0]
                for p in ps[1:]:
                    new_idom = self._intersect_generic(new_idom, p, idom, rpo_index)
                if idom.get(b) != new_idom:
                    idom[b] = new_idom
                    changed = True
        result = {}
        for b in self.blocks:
            ip = idom.get(b)
            result[b] = None if ip == EXIT or ip is None else ip
        self.ipdom = result
        return result

    def _intersect_generic(self, a, b, idom, rpo_index):
        while a != b:
            while rpo_index[a] > rpo_index[b]:
                a = idom[a]
            while rpo_index[b] > rpo_index[a]:
                b = idom[b]
        return a
