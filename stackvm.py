# -*- coding: utf-8 -*-
"""
Символическая интерпретация одного базового блока байткода: превращает
инструкции стек-машины в дерево выражений (Expr) и список операторов (Stmt).

Ключевой принцип: если встречается паттерн, который нельзя восстановить с
полной уверенностью (jsr/ret, экзотичный dup, неизвестный invokedynamic
bootstrap, рассинхронизация стека на границах блоков) - поднимается
DecompileAbort, и вызывающий код (engine.py) обязан откатить ВЕСЬ метод на
честный дизассемблированный листинг, а не выводить код, который может быть
неверным.
"""
import math
from ast_nodes import (
    Const, Local, This, FieldAccess, ArrayAccess, MethodCall, NewObject,
    NewArray, Cast, InstanceOf, BinOp, UnOp, Ternary, Assign, Raw,
    ClassLiteral, Lambda, ExprStmt, LocalDecl, ReturnStmt, ThrowStmt, Expr,
)
from javatypes import (
    field_descriptor_to_java, method_descriptor_to_java, dotted_from_internal,
    is_safe_local_name, looks_obfuscated,
)


class DecompileAbort(Exception):
    pass


CAUGHT_SENTINEL = "__caught__"


_WIDE2 = {"long", "double"}


def width_of(java_type):
    return 2 if java_type in _WIDE2 else 1


def cat_of(java_type):
    if java_type in ("int", "short", "byte", "char", "boolean"):
        return "I"
    if java_type == "long":
        return "L"
    if java_type == "float":
        return "F"
    if java_type == "double":
        return "D"
    return "A"


def default_type_for_cat(cat):
    return {"I": "int", "L": "long", "F": "float", "D": "double"}.get(cat, "Object")


def java_string_literal(s):
    out = ['"']
    for ch in s:
        o = ord(ch)
        if ch == '"':
            out.append('\\"')
        elif ch == '\\':
            out.append('\\\\')
        elif ch == '\n':
            out.append('\\n')
        elif ch == '\t':
            out.append('\\t')
        elif ch == '\r':
            out.append('\\r')
        elif o < 0x20 or o == 0x7f:
            out.append(f'\\u{o:04x}')
        else:
            out.append(ch)
    out.append('"')
    return "".join(out)


def java_float_literal(v, suffix="f"):
    if math.isnan(v):
        return ("Float" if suffix == "f" else "Double") + ".NaN"
    if math.isinf(v):
        cls = "Float" if suffix == "f" else "Double"
        return f"{cls}.{'POSITIVE' if v > 0 else 'NEGATIVE'}_INFINITY"
    s = repr(v)
    if "e" in s or "E" in s or "." in s:
        return s + suffix
    return s + ".0" + suffix


class _PendingNew(NewObject):
    """Результат `new X` до вызова <init>. Мутируется на месте invokespecial'ом,
    чтобы все dup-копии (тот же python-объект) синхронно "стали" готовым вызовом."""
    def __init__(self, type_):
        super().__init__(type_, [])
        self.initialized = False


def _materialize_if_shared(val, stack, ctx, emit):
    """Если val (снятый со стека получатель, напр. массив под aastore) - не
    простая ссылка на переменную, и на стеке остались другие ссылки на тот
    же объект (dup, напр. литерал массива new T[]{a,b,c}), материализуем его
    в новую temp-переменную - иначе конструктор был бы напечатан повторно на
    каждый следующий оператор, что в корне поменяло бы семантику."""
    if isinstance(val, (Local, This)):
        return val
    if not any(s is val for s in stack):
        return val
    typ = getattr(val, "type", "Object") or "Object"
    if typ in ("null", "this"):
        typ = "Object"
    name = ctx.new_temp(cat_of(typ) if typ in ("int", "long", "float", "double") else "A")
    emit(LocalDecl(typ, name, val))
    target = Local(name, typ)
    for idx2 in range(len(stack)):
        if stack[idx2] is val:
            stack[idx2] = target
    return target


def _has_side_effect(e):
    if isinstance(e, (MethodCall, NewObject, Assign)):
        return True
    if isinstance(e, UnOp) and e.op in ("++", "--"):
        return True
    for attr in ("left", "right", "expr", "target", "value", "array", "index", "cond", "tval", "fval"):
        v = getattr(e, attr, None)
        if isinstance(v, list):
            if any(_has_side_effect(x) for x in v):
                return True
        elif isinstance(v, object) and hasattr(v, "prec") and _is_expr(v):
            if _has_side_effect(v):
                return True
    args = getattr(e, "args", None)
    if args:
        if any(_has_side_effect(a) for a in args):
            return True
    return False


def _is_expr(v):
    from ast_nodes import Expr
    return isinstance(v, Expr)


class MethodCtx:
    """Состояние декомпиляции одного метода: локальные переменные, temp-имена."""
    def __init__(self, cf, method, renamer, known_internal_by_dotted, class_internal):
        self.cf = cf
        self.method = method
        self.renamer = renamer
        self.known = known_internal_by_dotted
        self.class_internal = class_internal
        self.locals = {}
        self.temp_ctr = 0
        self.stack_temp_names = {}
        self.crossing_temp_types = {}
        self.warnings = []
        self.imports = {}       # dotted -> simple  (для сбора import-ов вызывающим кодом)
        self._lvt_by_slot = self._build_lvt_names()
        self._used_local_names = {"this"}
        self._init_params()

    def _build_lvt_names(self):
        """Разбирает LocalVariableTable (если jar скомпилирован с отладочной
        информацией - см. classfile.py) в словарь slot -> имя. Один слот JVM
        может переиспользоваться под разные (непересекающиеся по области
        видимости) переменные - берём запись с наименьшим start_pc (как
        правило, самая "внешняя"/основная), остальные записи того же слота
        игнорируем: движок и так печатает одно имя на слот на весь метод (см.
        engine.py::_redeclare_first_assign - переиспользование слота под
        разные типы уже обрабатывается на уровне ТИПА, а не имени)."""
        by_slot = {}
        for start_pc, length, name, desc, slot in sorted(self.method.local_var_table, key=lambda e: e[0]):
            if not is_safe_local_name(name):
                continue
            if looks_obfuscated(name, "field"):
                continue
            if slot not in by_slot:
                by_slot[slot] = name
        return by_slot

    def _lvt_name_for(self, slot):
        """LVT-имя для слота, если оно есть, валидно и ещё не занято другим
        слотом в этом же методе (иначе - коллизия имён, откатываемся на
        родовое имя argN/varN)."""
        name = self._lvt_by_slot.get(slot)
        if name and name not in self._used_local_names:
            self._used_local_names.add(name)
            return name
        return None

    def _init_params(self):
        is_static = bool(self.method.access & 0x0008)
        slot = 0
        if not is_static:
            self.locals[0] = {"name": "this", "type": self.map_type(self.class_internal.replace("/", ".")), "category": "A"}
            slot = 1
        try:
            ret, params = method_descriptor_to_java(self.method.descriptor)
        except Exception:
            ret, params = "void", []
        self.ret_type = ret
        for i, p in enumerate(params):
            is_array = p.endswith("[]")
            cat = "A" if is_array else cat_of(p)
            name = self._lvt_name_for(slot) or f"arg{i}"
            self.locals[slot] = {"name": name, "type": self.map_type(p), "category": cat, "is_param": True}
            slot += (1 if is_array else width_of(p))

    def map_type(self, java_type):
        base = java_type
        arr = ""
        while base.endswith("[]"):
            arr += "[]"
            base = base[:-2]
        if base in self.known:
            internal = self.known[base]
            new_internal = self.renamer.friendly_class(internal)
            dotted = new_internal.replace("/", ".")
            self.imports[dotted] = dotted.rsplit(".", 1)[-1]
            base = dotted
        elif "." in base and not base.startswith("java.lang."):
            self.imports.setdefault(base, base.rsplit(".", 1)[-1])
        return base + arr

    def simple(self, dotted):
        """Короткое имя типа для печати (импорт уже зарегистрирован через map_type)."""
        return dotted.rsplit(".", 1)[-1] if dotted in self.imports or dotted.count(".") else dotted

    def owner_display(self, owner_internal):
        """Дружелюбное dotted-имя класса-владельца (с учётом переименования, если он в этом jar)."""
        if owner_internal.startswith("["):
            # напр. владелец synthetic-метода clone() у массива: "[Lpkg/Foo;" или "[I"
            depth = 0
            s = owner_internal
            while s.startswith("["):
                depth += 1
                s = s[1:]
            if s.startswith("L") and s.endswith(";"):
                base = self.owner_display(s[1:-1])
            else:
                base = field_descriptor_to_java(s)
            return base + "[]" * depth
        if owner_internal in self.known.values() or owner_internal in self.renamer.class_map:
            new_internal = self.renamer.friendly_class(owner_internal)
        elif owner_internal in self.known:
            new_internal = self.renamer.friendly_class(self.known[owner_internal])
        else:
            new_internal = owner_internal
        dotted = dotted_from_internal(new_internal)
        if dotted != "java.lang.Object":
            self.imports.setdefault(dotted, dotted.rsplit(".", 1)[-1])
        return dotted

    def field_name(self, owner_internal, name, desc):
        return self.renamer.field_map.get((owner_internal, name, desc), name)

    def method_name(self, owner_internal, name, desc):
        if name in ("<init>", "<clinit>"):
            return name
        return self.renamer.method_map.get((owner_internal, name, desc), name)

    def new_temp(self, category):
        self.temp_ctr += 1
        prefix = {"I": "n", "L": "lv", "F": "fv", "D": "dv", "A": "obj"}.get(category, "v")
        return f"__{prefix}{self.temp_ctr}"

    def stack_temp_for(self, target_block_start, category):
        if target_block_start not in self.stack_temp_names:
            self.temp_ctr += 1
            self.stack_temp_names[target_block_start] = (f"__stk{self.temp_ctr}", category)
        return self.stack_temp_names[target_block_start][0]

    def local(self, idx, category, is_store=False):
        info = self.locals.get(idx)
        if info is None:
            name = self._lvt_name_for(idx) or f"var{idx}"
            info = {"name": name, "type": default_type_for_cat(category), "category": category, "seen_categories": {category}}
            self.locals[idx] = info
        else:
            info.setdefault("seen_categories", {info["category"]}).add(category)
        return info


class BlockResult:
    __slots__ = ("stmts", "exit_stack", "term_kind", "cond")

    def __init__(self):
        self.stmts = []
        self.exit_stack = []
        self.term_kind = None   # None | 'if' | 'switch' | 'return' | 'throw'
        self.cond = None


def simulate_block(block, entry_stack, ctx, underflow_flag=None):
    """Исполняет один базовый блок символически. Может кинуть DecompileAbort.
    Если underflow_flag - это dict с ключом 'missing' (list), при недостатке
    значений на входе (блок ожидает 1+ унаследованных значений с границы
    блока) вместо падения подставляются временные placeholder'ы, добавляемые
    в missing (в порядке извлечения) - engine.py использует это для
    обнаружения пересечения стека между блоками (тернарные операторы,
    `boolean b = x && y;`, `arr[i] = cond ? a : b;` и т.п.)."""
    res = BlockResult()
    stack = list(entry_stack)
    cf = ctx.cf

    def push(v):
        stack.append(v)

    def pop():
        if not stack:
            if underflow_flag is not None and len(underflow_flag.setdefault("missing", [])) < 8:
                ph = Local(f"__entry{len(underflow_flag['missing'])}__", "Object")
                underflow_flag["missing"].append(ph)
                return ph
            raise DecompileAbort("stack underflow")
        return stack.pop()

    def pop_n(n):
        vals = [pop() for _ in range(n)]
        vals.reverse()
        return vals

    def emit(stmt):
        res.stmts.append(stmt)

    def flush_side_effect_if_any(v):
        if _has_side_effect(v):
            emit(ExprStmt(v))

    def cp_const(idx):
        e = cf.pool.get(idx)
        if e is None:
            raise DecompileAbort(f"bad cp index {idx}")
        tag = e[0]
        if tag == "Integer":
            return Const(str(e[1]), "int")
        if tag == "Float":
            return Const(java_float_literal(float(e[1]), "f"), "float")
        if tag == "Long":
            return Const(f"{e[1]}L", "long")
        if tag == "Double":
            return Const(java_float_literal(float(e[1]), ""), "double")
        if tag == "String":
            s = cf.utf8(e[1]) or ""
            return Const(java_string_literal(s), "String")
        if tag == "Class":
            internal = cf.utf8(e[1])
            if internal and internal.startswith("["):
                # массив: JVM хранит дескриптор вида "[Ljava/lang/reflect/Type;"
                # или "[I" - его нельзя печатать как есть в `X.class`, нужно
                # привести к валидному Java-синтаксису литерала класса массива
                # (напр. "Type[].class", "int[].class"). Раньше здесь передавался
                # сырой дескриптор напрямую - невалидный Java (найдено на
                # регрессии commons-lang3, TypeUtils$ParameterizedTypeImpl).
                disp = _array_type_str(internal, ctx)
            else:
                disp = ctx.owner_display(internal) if internal else "Object"
            return ClassLiteral(disp)
        raise DecompileAbort(f"unsupported ldc tag {tag}")

    instrs = block.instrs
    i = 0
    n = len(instrs)
    while i < n:
        ins = instrs[i]
        mn = ins.mnemonic

        # ---- iinc as prefix ++/--, when not already claimed by postfix logic ----
        if mn == "iinc":
            const = ins.iinc_const
            info = ctx.local(ins.iinc_idx, "I")
            target = Local(info["name"], info["type"])
            if const in (1, -1):
                # ищем следующую инструкцию: iload того же слота сразу после -> префиксная форма
                nxt = instrs[i + 1] if i + 1 < n else None
                if nxt is not None and _is_load_of(nxt, ins.iinc_idx):
                    op = "++" if const == 1 else "--"
                    push(UnOp(op, target, info["type"], postfix=False))
                    i += 2
                    continue
                op = "++" if const == 1 else "--"
                emit(ExprStmt(UnOp(op, target, info["type"], postfix=False)))
            else:
                emit(ExprStmt(Assign(target, BinOp("+", target, Const(str(const), "int")))))
            i += 1
            continue

        # ---- loads ----
        if mn in _ILOAD_ALL:
            slot = _load_slot(ins, mn, "I")
            info = ctx.local(slot, "I")
            val = Local(info["name"], info["type"])
            nxt = instrs[i + 1] if i + 1 < n else None
            if nxt is not None and nxt.mnemonic == "iinc" and nxt.iinc_idx == slot and nxt.iinc_const in (1, -1):
                op = "++" if nxt.iinc_const == 1 else "--"
                push(UnOp(op, val, info["type"], postfix=True))
                i += 2
                continue
            push(val)
            i += 1
            continue
        if mn in _LLOAD_ALL:
            slot = _load_slot(ins, mn, "L")
            info = ctx.local(slot, "L")
            push(Local(info["name"], info["type"]))
            i += 1
            continue
        if mn in _FLOAD_ALL:
            slot = _load_slot(ins, mn, "F")
            info = ctx.local(slot, "F")
            push(Local(info["name"], info["type"]))
            i += 1
            continue
        if mn in _DLOAD_ALL:
            slot = _load_slot(ins, mn, "D")
            info = ctx.local(slot, "D")
            push(Local(info["name"], info["type"]))
            i += 1
            continue
        if mn in _ALOAD_ALL:
            slot = _load_slot(ins, mn, "A")
            info = ctx.local(slot, "A")
            if slot == 0 and info.get("name") == "this":
                push(This())
            else:
                push(Local(info["name"], info["type"]))
            i += 1
            continue

        # ---- constants ----
        if mn == "aconst_null":
            push(Const("null", "null")); i += 1; continue
        if mn.startswith("iconst_"):
            v = mn.split("_")[1]
            v = "-1" if v == "m1" else v
            push(Const(v, "int")); i += 1; continue
        if mn == "lconst_0": push(Const("0L", "long")); i += 1; continue
        if mn == "lconst_1": push(Const("1L", "long")); i += 1; continue
        if mn == "fconst_0": push(Const("0.0f", "float")); i += 1; continue
        if mn == "fconst_1": push(Const("1.0f", "float")); i += 1; continue
        if mn == "fconst_2": push(Const("2.0f", "float")); i += 1; continue
        if mn == "dconst_0": push(Const("0.0", "double")); i += 1; continue
        if mn == "dconst_1": push(Const("1.0", "double")); i += 1; continue
        if mn == "bipush" or mn == "sipush":
            push(Const(str(ins.ival), "int")); i += 1; continue
        if mn in ("ldc", "ldc_w", "ldc2_w"):
            push(cp_const(ins.cp_index)); i += 1; continue

        # ---- stores ----
        if mn in _ISTORE_ALL or mn in _LSTORE_ALL or mn in _FSTORE_ALL or mn in _DSTORE_ALL or mn in _ASTORE_ALL:
            cat = ("I" if mn in _ISTORE_ALL else "L" if mn in _LSTORE_ALL else
                   "F" if mn in _FSTORE_ALL else "D" if mn in _DSTORE_ALL else "A")
            slot = _store_slot(ins, mn)
            val = pop()
            declare = slot not in ctx.locals
            info = ctx.local(slot, cat)
            if declare:
                info["type"] = _refine_type(info["type"], val)
            target = Local(info["name"], info["type"])
            stmt = LocalDecl(info["type"], info["name"], val) if declare else _assign_stmt(target, val)
            emit(stmt)
            _substitute(stack, val, target)
            i += 1
            continue

        # ---- array load/store ----
        if mn in ("iaload", "laload", "faload", "daload", "aaload", "baload", "caload", "saload"):
            idx = pop(); arr = pop()
            et = {"iaload": "int", "laload": "long", "faload": "float", "daload": "double",
                  "aaload": "Object", "baload": "byte", "caload": "char", "saload": "short"}[mn]
            push(ArrayAccess(arr, idx, et))
            i += 1; continue
        if mn in ("iastore", "lastore", "fastore", "dastore", "aastore", "bastore", "castore", "sastore"):
            val = pop(); idx = pop(); arr = pop()
            arr = _materialize_if_shared(arr, stack, ctx, emit)
            et = {"iastore": "int", "lastore": "long", "fastore": "float", "dastore": "double",
                  "aastore": "Object", "bastore": "byte", "castore": "char", "sastore": "short"}[mn]
            emit(_assign_stmt(ArrayAccess(arr, idx, et), val))
            i += 1; continue

        # ---- stack manipulation ----
        if mn == "pop":
            v = pop(); flush_side_effect_if_any(v); i += 1; continue
        if mn == "pop2":
            v = pop()
            if getattr(v, "width", 1) == 1:
                v2 = pop(); flush_side_effect_if_any(v2)
            flush_side_effect_if_any(v)
            i += 1; continue
        if mn == "dup":
            v = pop(); push(v); push(v); i += 1; continue
        if mn == "dup_x1":
            v1 = pop(); v2 = pop(); push(v1); push(v2); push(v1); i += 1; continue
        if mn == "dup_x2":
            v1 = pop(); v2 = pop()
            if getattr(v2, "width", 1) == 2:
                push(v1); push(v2); push(v1)
            else:
                v3 = pop()
                push(v1); push(v3); push(v2); push(v1)
            i += 1; continue
        if mn == "dup2":
            v1 = pop()
            if getattr(v1, "width", 1) == 2:
                push(v1); push(v1)
            else:
                v2 = pop()
                push(v2); push(v1); push(v2); push(v1)
            i += 1; continue
        if mn == "dup2_x1":
            v1 = pop()
            if getattr(v1, "width", 1) == 2:
                v2 = pop(); push(v1); push(v2); push(v1)
            else:
                v2 = pop(); v3 = pop()
                push(v2); push(v1); push(v3); push(v2); push(v1)
            i += 1; continue
        if mn == "dup2_x2":
            v1 = pop()
            if getattr(v1, "width", 1) == 2:
                v2 = pop()
                if getattr(v2, "width", 1) == 2:
                    push(v1); push(v2); push(v1)
                else:
                    v3 = pop(); push(v1); push(v3); push(v2); push(v1)
            else:
                v2 = pop(); v3 = pop()
                if getattr(v3, "width", 1) == 2:
                    push(v2); push(v1); push(v3); push(v2); push(v1)
                else:
                    v4 = pop(); push(v2); push(v1); push(v4); push(v3); push(v2); push(v1)
            i += 1; continue
        if mn == "swap":
            v1 = pop(); v2 = pop(); push(v1); push(v2); i += 1; continue

        # ---- arithmetic ----
        if mn in _BINOPS:
            op, t = _BINOPS[mn]
            r = pop(); l = pop()
            push(BinOp(op, l, r, t))
            i += 1; continue
        if mn in _NEGOPS:
            t = _NEGOPS[mn]
            v = pop()
            push(UnOp("-", v, t))
            i += 1; continue

        if mn in _CASTS:
            t = _CASTS[mn]
            v = pop()
            push(Cast(t, v))
            i += 1; continue

        if mn in ("lcmp", "fcmpl", "fcmpg", "dcmpl", "dcmpg"):
            r = pop(); l = pop()
            push(Raw(f"__cmp__", "int"))  # placeholder, real comparisons folded at branch site below
            # заменим последним настоящим деревом: используем спец. узел через BinOp с op 'cmp'
            stack[-1] = BinOp("cmp", l, r, "int")
            i += 1; continue

        # ---- fields ----
        if mn == "getstatic":
            r = cf.ref_string(ins.cp_index)
            if r is None: raise DecompileAbort("bad getstatic")
            owner, name, desc = r
            ftype = ctx.map_type(field_descriptor_to_java(desc))
            fname = ctx.field_name(owner, name, desc)
            push(FieldAccess(None, fname, ftype, static=True, owner=ctx.owner_display(owner)))
            i += 1; continue
        if mn == "putstatic":
            r = cf.ref_string(ins.cp_index)
            if r is None: raise DecompileAbort("bad putstatic")
            owner, name, desc = r
            ftype = ctx.map_type(field_descriptor_to_java(desc))
            fname = ctx.field_name(owner, name, desc)
            val = pop()
            val = _coerce_arg(val, ftype)
            tgt = FieldAccess(None, fname, ftype, static=True, owner=ctx.owner_display(owner))
            emit(_assign_stmt(tgt, val))
            i += 1; continue
        if mn == "getfield":
            r = cf.ref_string(ins.cp_index)
            if r is None: raise DecompileAbort("bad getfield")
            owner, name, desc = r
            ftype = ctx.map_type(field_descriptor_to_java(desc))
            fname = ctx.field_name(owner, name, desc)
            obj = pop()
            push(FieldAccess(obj, fname, ftype))
            i += 1; continue
        if mn == "putfield":
            r = cf.ref_string(ins.cp_index)
            if r is None: raise DecompileAbort("bad putfield")
            owner, name, desc = r
            ftype = ctx.map_type(field_descriptor_to_java(desc))
            fname = ctx.field_name(owner, name, desc)
            val = pop(); obj = pop()
            val = _coerce_arg(val, ftype)
            tgt = FieldAccess(obj, fname, ftype)
            emit(_assign_stmt(tgt, val))
            i += 1; continue

        # ---- invocations ----
        if mn in ("invokevirtual", "invokespecial", "invokestatic", "invokeinterface"):
            r = cf.ref_string(ins.cp_index)
            if r is None: raise DecompileAbort("bad invoke ref")
            owner, name, desc = r
            try:
                ret, params = method_descriptor_to_java(desc)
            except Exception:
                raise DecompileAbort("bad method descriptor")
            args = pop_n(len(params))
            args = [_coerce_arg(a, ctx.map_type(p)) for a, p in zip(args, params)]
            if mn == "invokestatic":
                mname = ctx.method_name(owner, name, desc)
                call = MethodCall(None, mname, args, ctx.map_type(ret), static=True, owner=ctx.owner_display(owner))
                if ret == "void":
                    emit(ExprStmt(call))
                else:
                    push(call)
            else:
                recv = pop()
                if name == "<init>":
                    if isinstance(recv, _PendingNew) and not recv.initialized:
                        recv.args = args
                        recv.initialized = True
                        # ничего не пушим: <init> ничего не возвращает - уже
                        # существующая dup-копия того же объекта (если была)
                        # автоматически "видит" инициализацию (тот же python-объект)
                    elif isinstance(recv, This) and mn == "invokespecial":
                        # this(...) / super(...) конструктор-делегат
                        is_super = owner != ctx.class_internal
                        call = MethodCall(None, "super" if is_super else "this", args, "void", is_ctor=True, is_super=is_super)
                        emit(ExprStmt(call))
                    else:
                        raise DecompileAbort("unrecognized <init> pattern")
                else:
                    mname = ctx.method_name(owner, name, desc)
                    is_super_call = (mn == "invokespecial" and owner != ctx.class_internal)
                    tgt = MethodCall(This() if is_super_call else recv, mname, args, ctx.map_type(ret),
                                      owner=ctx.owner_display(owner), is_super=is_super_call,
                                      interface=(mn == "invokeinterface"))
                    if ret == "void":
                        emit(ExprStmt(tgt))
                    else:
                        push(tgt)
            i += 1; continue

        if mn == "invokedynamic":
            expr = _handle_invokedynamic(cf, ins, ctx, pop_n)
            push(expr)
            i += 1; continue

        # ---- object / array creation ----
        if mn == "new":
            cname = cf.class_name(ins.cp_index)
            if cname is None: raise DecompileAbort("bad new target")
            push(_PendingNew(ctx.owner_display(cname)))
            i += 1; continue
        if mn == "newarray":
            size = pop()
            push(NewArray(ins.atype, [size]))
            i += 1; continue
        if mn == "anewarray":
            cname = cf.class_name(ins.cp_index)
            if cname is None: raise DecompileAbort("bad anewarray target")
            size = pop()
            elem = _array_type_str(cname, ctx) if cname.startswith("[") else ctx.owner_display(cname)
            push(NewArray(elem, [size]))
            i += 1; continue
        if mn == "multianewarray":
            cname = cf.class_name(ins.cp_index)
            if cname is None: raise DecompileAbort("bad multianewarray target")
            dims = pop_n(ins.dims)
            base = cname.lstrip("[")
            if base.startswith("L") and base.endswith(";"):
                elem = ctx.owner_display(base[1:-1])
            else:
                from javatypes import field_descriptor_to_java as fd
                elem = fd(base)
            push(NewArray(elem, dims))
            i += 1; continue
        if mn == "arraylength":
            arr = pop()
            push(FieldAccess(arr, "length", "int"))
            i += 1; continue

        if mn == "checkcast":
            cname = cf.class_name(ins.cp_index)
            if cname is None: raise DecompileAbort("bad checkcast target")
            v = pop()
            disp = ctx.owner_display(cname) if not cname.startswith("[") else _array_type_str(cname, ctx)
            push(Cast(disp, v))
            i += 1; continue
        if mn == "instanceof":
            cname = cf.class_name(ins.cp_index)
            if cname is None: raise DecompileAbort("bad instanceof target")
            v = pop()
            disp = ctx.owner_display(cname) if not cname.startswith("[") else _array_type_str(cname, ctx)
            push(InstanceOf(v, disp))
            i += 1; continue

        if mn == "athrow":
            v = pop()
            emit(ThrowStmt(v))
            res.term_kind = "throw"
            i += 1; continue

        if mn == "monitorenter":
            v = pop()
            emit(_MonitorMarker("enter", v))
            i += 1; continue
        if mn == "monitorexit":
            v = pop()
            emit(_MonitorMarker("exit", v))
            i += 1; continue

        if mn in ("return", "ireturn", "lreturn", "freturn", "dreturn", "areturn"):
            if mn == "return":
                emit(ReturnStmt(None))
            else:
                v = pop()
                v = _coerce_arg(v, ctx.ret_type)
                emit(ReturnStmt(v))
            res.term_kind = "return"
            i += 1; continue

        if mn == "nop":
            i += 1; continue

        if ins.is_conditional:
            if mn in ("ifnull", "ifnonnull"):
                v = pop()
                cond = BinOp("==" if mn == "ifnull" else "!=", v, Const("null", "null"), "boolean")
            elif mn in ("ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle"):
                v = pop()
                cmpop = {"ifeq": "==", "ifne": "!=", "iflt": "<", "ifge": ">=", "ifgt": ">", "ifle": "<="}[mn]
                cond = _fold_compare(v, cmpop)
            else:
                r = pop(); l = pop()
                cmpop = {"if_icmpeq": "==", "if_icmpne": "!=", "if_icmplt": "<", "if_icmpge": ">=",
                         "if_icmpgt": ">", "if_icmple": "<=", "if_acmpeq": "==", "if_acmpne": "!="}[mn]
                cond = BinOp(cmpop, l, r, "boolean")
            res.term_kind = "if"
            res.cond = cond
            i += 1
            continue

        if mn in ("goto", "goto_w"):
            i += 1
            continue

        if ins.is_switch:
            v = pop()
            res.term_kind = "switch"
            res.cond = v
            i += 1
            continue

        if mn in ("jsr", "jsr_w", "ret"):
            raise DecompileAbort("jsr/ret (legacy finally) не поддерживается")

        raise DecompileAbort(f"неизвестная/неподдержанная инструкция {mn}")

    res.exit_stack = stack
    return res


class _MonitorMarker:
    """Промежуточный маркер statement-уровня для synchronized(); связывается в structure.py."""
    def __init__(self, kind, expr):
        self.kind = kind
        self.expr = expr


def _fold_compare(v, cmpop):
    """lcmp/fcmpl/dcmpl создают BinOp('cmp', l, r) на стеке; if сравнивает его с 0 -
    здесь сворачиваем это обратно в нормальное l < r / l >= r и т.п."""
    if isinstance(v, BinOp) and v.op == "cmp":
        return BinOp(cmpop, v.left, v.right, "boolean")
    if v.type == "boolean":
        if cmpop == "==":   # ifeq: value == 0 => значение false
            return UnOp("!", v, "boolean")
        if cmpop == "!=":   # ifne: value != 0 => значение true
            return v
    zero = Const("0", "int")
    return BinOp(cmpop, v, zero, "boolean")


def _assign_stmt(target, value):
    return ExprStmt(Assign(target, value))


_PSEUDO_TYPES = ("null", "this")


def _refine_type(current, value_expr):
    t = getattr(value_expr, "type", None)
    if t and t not in _PSEUDO_TYPES and current in ("Object", "int", "long", "float", "double"):
        return t
    return current


_PRIMITIVE_WRAPPERS = {
    "int": "Integer", "long": "Long", "float": "Float", "double": "Double",
    "boolean": "Boolean", "char": "Character", "byte": "Byte", "short": "Short",
}


def _coerce_arg(expr, expected_type):
    if isinstance(expr, Const) and expr.type == "int":
        if expected_type == "boolean" and expr.literal in ("0", "1"):
            return Const("false" if expr.literal == "0" else "true", "boolean")
        if expected_type == "char":
            try:
                v = int(expr.literal)
            except ValueError:
                return expr
            return Const(_char_literal(v), "char")
    # Мы не парсим generic Signature-атрибут, поэтому значения из "сырых"
    # (erasure) generic-контекстов (Map.get, лямбда-параметры BiFunction/
    # Predicate без известных type-параметров и т.п.) размечены нашим общим
    # типом-заглушкой "Object". Если КОНКРЕТНЫЙ (из дескриптора реального
    # вызываемого метода) ожидаемый тип параметра - что-то более узкое,
    # безопасно вставить явный каст: раз исходный байткод скомпилировался,
    # рантайм-тип там гарантированно совпадает - каст просто восстанавливает
    # то, что стёрла эрозия дженериков (в точности как делает javac сам
    # через checkcast при работе с generic-кодом). Без этого - каскад
    # "incompatible types: Object cannot be converted to X" / "cannot find
    # symbol" на вызовах через стёртые generic-параметры (см. HANDOFF/чат:
    # реальный пример - Map.merge()/removeIf()/entrySet() на сырых коллекциях).
    expr_type = getattr(expr, "type", None)
    if expr_type in ("Object", "java.lang.Object") and expected_type not in (None, "Object", "java.lang.Object", "void"):
        target = _PRIMITIVE_WRAPPERS.get(expected_type, expected_type)
        return Cast(target, expr)
    return expr


def _char_literal(codepoint):
    ch = chr(codepoint & 0xFFFF)
    if ch == "'":
        return "'\\''"
    if ch == "\\":
        return "'\\\\'"
    if ch == "\n":
        return "'\\n'"
    if ch == "\t":
        return "'\\t'"
    if ch == "\r":
        return "'\\r'"
    if ord(ch) < 0x20 or ord(ch) == 0x7f:
        return f"'\\u{ord(ch):04x}'"
    return f"'{ch}'"


def _count_refs(stack, val):
    return sum(1 for s in stack if s is val)


def _substitute(stack, old, new):
    for idx in range(len(stack)):
        if stack[idx] is old:
            stack[idx] = new


def _array_type_str(internal, ctx):
    depth = 0
    s = internal
    while s.startswith("["):
        depth += 1
        s = s[1:]
    if s.startswith("L") and s.endswith(";"):
        base = ctx.owner_display(s[1:-1])
    else:
        base = field_descriptor_to_java(s)
    return base + "[]" * depth


def _is_load_of(ins, slot):
    return ins.mnemonic in _ILOAD_ALL and _load_slot_static(ins, ins.mnemonic) == slot


def _load_slot_static(ins, mn):
    if mn in ("iload", "lload", "fload", "dload", "aload"):
        return ins.ival
    return int(mn.rsplit("_", 1)[1])


def _load_slot(ins, mn, cat):
    return _load_slot_static(ins, mn)


def _store_slot(ins, mn):
    if mn in ("istore", "lstore", "fstore", "dstore", "astore"):
        return ins.ival
    return int(mn.rsplit("_", 1)[1])


_ILOAD_ALL = {"iload", "iload_0", "iload_1", "iload_2", "iload_3"}
_LLOAD_ALL = {"lload", "lload_0", "lload_1", "lload_2", "lload_3"}
_FLOAD_ALL = {"fload", "fload_0", "fload_1", "fload_2", "fload_3"}
_DLOAD_ALL = {"dload", "dload_0", "dload_1", "dload_2", "dload_3"}
_ALOAD_ALL = {"aload", "aload_0", "aload_1", "aload_2", "aload_3"}
_ISTORE_ALL = {"istore", "istore_0", "istore_1", "istore_2", "istore_3"}
_LSTORE_ALL = {"lstore", "lstore_0", "lstore_1", "lstore_2", "lstore_3"}
_FSTORE_ALL = {"fstore", "fstore_0", "fstore_1", "fstore_2", "fstore_3"}
_DSTORE_ALL = {"dstore", "dstore_0", "dstore_1", "dstore_2", "dstore_3"}
_ASTORE_ALL = {"astore", "astore_0", "astore_1", "astore_2", "astore_3"}

_BINOPS = {
    "iadd": ("+", "int"), "ladd": ("+", "long"), "fadd": ("+", "float"), "dadd": ("+", "double"),
    "isub": ("-", "int"), "lsub": ("-", "long"), "fsub": ("-", "float"), "dsub": ("-", "double"),
    "imul": ("*", "int"), "lmul": ("*", "long"), "fmul": ("*", "float"), "dmul": ("*", "double"),
    "idiv": ("/", "int"), "ldiv": ("/", "long"), "fdiv": ("/", "float"), "ddiv": ("/", "double"),
    "irem": ("%", "int"), "lrem": ("%", "long"), "frem": ("%", "float"), "drem": ("%", "double"),
    "ishl": ("<<", "int"), "lshl": ("<<", "long"),
    "ishr": (">>", "int"), "lshr": (">>", "long"),
    "iushr": (">>>", "int"), "lushr": (">>>", "long"),
    "iand": ("&", "int"), "land": ("&", "long"),
    "ior": ("|", "int"), "lor": ("|", "long"),
    "ixor": ("^", "int"), "lxor": ("^", "long"),
}
_NEGOPS = {"ineg": "int", "lneg": "long", "fneg": "float", "dneg": "double"}
_CASTS = {
    "i2l": "long", "i2f": "float", "i2d": "double",
    "l2i": "int", "l2f": "float", "l2d": "double",
    "f2i": "int", "f2l": "long", "f2d": "double",
    "d2i": "int", "d2l": "long", "d2f": "float",
    "i2b": "byte", "i2c": "char", "i2s": "short",
}


def _handle_invokedynamic(cf, ins, ctx, pop_n):
    e = cf.pool.get(ins.cp_index)
    if e is None or e[0] != "InvokeDynamic":
        raise DecompileAbort("bad invokedynamic cp entry")
    bsm_idx, nt_idx = e[1], e[2]
    if bsm_idx >= len(cf.bootstrap_methods):
        raise DecompileAbort("bootstrap method index out of range")
    mh_idx, bsm_args = cf.bootstrap_methods[bsm_idx]
    mh = cf.method_handle_ref(mh_idx)
    if mh is None:
        raise DecompileAbort("bad bootstrap method handle")
    kind, bsm_owner, bsm_name, bsm_desc = mh
    nt = cf.name_and_type(nt_idx)
    if nt is None:
        raise DecompileAbort("bad invokedynamic NameAndType")
    indy_name, indy_desc = nt
    try:
        indy_ret, indy_params = method_descriptor_to_java(indy_desc)
    except Exception:
        raise DecompileAbort("bad invokedynamic descriptor")
    call_args = pop_n(len(indy_params))

    if bsm_owner == "java/lang/invoke/StringConcatFactory":
        return _build_string_concat(cf, bsm_args, call_args, ctx)

    if bsm_owner == "java/lang/invoke/LambdaMetafactory":
        return _build_lambda(cf, bsm_args, call_args, indy_name, indy_ret, ctx)

    raise DecompileAbort(f"неподдерживаемый invokedynamic bootstrap: {bsm_owner}.{bsm_name}")


def _build_string_concat(cf, bsm_args, call_args, ctx):
    recipe = None
    const_args = []
    if bsm_args:
        first = cf.pool.get(bsm_args[0])
        if first and first[0] == "String":
            recipe = cf.utf8(first[1])
            for a_idx in bsm_args[1:]:
                const_args.append(cp_const_simple(cf, a_idx, ctx))
    parts = []
    if recipe is not None:
        dyn_i = 0
        const_i = 0
        buf = ""
        j = 0
        while j < len(recipe):
            ch = recipe[j]
            if ch == "\u0001":
                if buf:
                    parts.append(Const(java_string_literal(buf), "String")); buf = ""
                parts.append(call_args[dyn_i]); dyn_i += 1
            elif ch == "\u0002":
                if buf:
                    parts.append(Const(java_string_literal(buf), "String")); buf = ""
                parts.append(const_args[const_i]); const_i += 1
            else:
                buf += ch
            j += 1
        if buf:
            parts.append(Const(java_string_literal(buf), "String"))
    else:
        parts = list(call_args)
    if not parts:
        return Const('""', "String")
    result = parts[0]
    for p in parts[1:]:
        result = BinOp("+", result, p, "String")
    result.type = "String"
    return result


def cp_const_simple(cf, idx, ctx):
    e = cf.pool.get(idx)
    if e is None:
        raise DecompileAbort("bad constant-arg cp index")
    tag = e[0]
    if tag == "Integer": return Const(str(e[1]), "int")
    if tag == "Float": return Const(java_float_literal(float(e[1]), "f"), "float")
    if tag == "Long": return Const(f"{e[1]}L", "long")
    if tag == "Double": return Const(java_float_literal(float(e[1]), ""), "double")
    if tag == "String": return Const(java_string_literal(cf.utf8(e[1]) or ""), "String")
    raise DecompileAbort(f"unsupported const-arg tag {tag}")


_MH_KIND_STATIC = {6, 8}
_MH_KIND_VIRTUAL = {5, 9}
_MH_KIND_SPECIAL = {7}
_MH_KIND_NEW = {8}


def _build_lambda(cf, bsm_args, captured, indy_name, functional_type_desc, ctx):
    if len(bsm_args) < 3:
        raise DecompileAbort("некорректные аргументы LambdaMetafactory")
    sam_desc_e = cf.pool.get(bsm_args[0])
    impl_mh_idx = bsm_args[1]
    if sam_desc_e is None or sam_desc_e[0] != "MethodType":
        raise DecompileAbort("bad SAM method type")
    sam_desc = cf.utf8(sam_desc_e[1])
    try:
        sam_ret, sam_params = method_descriptor_to_java(sam_desc)
    except Exception:
        raise DecompileAbort("bad SAM descriptor")
    mh = cf.method_handle_ref(impl_mh_idx)
    if mh is None:
        raise DecompileAbort("bad lambda impl method handle")
    kind, impl_owner, impl_name, impl_desc = mh
    lam_params = [Local(f"lp{k}", ctx.map_type(p)) for k, p in enumerate(sam_params)]

    impl_owner_disp = ctx.owner_display(impl_owner)
    impl_mname = ctx.method_name(impl_owner, impl_name, impl_desc)
    try:
        _impl_ret, impl_params = method_descriptor_to_java(impl_desc)
    except Exception:
        impl_params = None

    def _coerce_seq(seq, param_types):
        # См. _coerce_arg выше: SAM-erasure типизирует lam_params как Object,
        # а РЕАЛЬНЫЙ синтетический метод лямбды (impl_desc) знает точные типы -
        # используем их, чтобы не потерять точность на границе вызова (иначе
        # каскад "incompatible types"/"cannot find symbol" на месте вызова).
        if param_types is None or len(param_types) != len(seq):
            return seq
        return [_coerce_arg(a, ctx.map_type(p)) for a, p in zip(seq, param_types)]

    if kind in _MH_KIND_NEW:
        call = NewObject(impl_owner_disp, _coerce_seq(list(captured) + lam_params, impl_params))
    elif kind in _MH_KIND_STATIC:
        call = MethodCall(None, impl_mname, _coerce_seq(list(captured) + lam_params, impl_params),
                           static=True, owner=impl_owner_disp)
    elif kind in _MH_KIND_VIRTUAL or kind in _MH_KIND_SPECIAL:
        if captured:
            recv = captured[0]
            rest = list(captured[1:]) + lam_params
        elif lam_params:
            recv = lam_params[0]
            rest = lam_params[1:]
        else:
            raise DecompileAbort("не удалось определить получателя для лямбды")
        if getattr(recv, "type", None) in ("Object", "java.lang.Object") and impl_owner_disp not in (None, "Object", "java.lang.Object"):
            recv = Cast(impl_owner_disp, recv)
        call = MethodCall(recv, impl_mname, _coerce_seq(rest, impl_params), owner=impl_owner_disp)
    else:
        raise DecompileAbort(f"неизвестный kind method handle: {kind}")

    return Lambda(lam_params, call, functional_type_desc or "Object")
