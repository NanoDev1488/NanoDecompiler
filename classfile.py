# -*- coding: utf-8 -*-
"""
Ручной парсер .class файлов JVM (без javap/ASM/CFR).
Разбирает constant pool, поля, методы, атрибуты Code, и дизассемблирует байткод
в читаемый листинг (в духе javap -c / -p).
"""
import struct

CONSTANT_TAGS = {
    1: "Utf8", 3: "Integer", 4: "Float", 5: "Long", 6: "Double",
    7: "Class", 8: "String", 9: "Fieldref", 10: "Methodref",
    11: "InterfaceMethodref", 12: "NameAndType", 15: "MethodHandle",
    16: "MethodType", 17: "Dynamic", 18: "InvokeDynamic",
    19: "Module", 20: "Package",
}

ACC_FLAGS = [
    (0x0001, "public"), (0x0002, "private"), (0x0004, "protected"),
    (0x0008, "static"), (0x0010, "final"), (0x0020, "synchronized"),
    (0x0040, "volatile/bridge"), (0x0080, "transient/varargs"),
    (0x0200, "interface"), (0x0400, "abstract"), (0x0800, "strict"),
    (0x1000, "synthetic"), (0x2000, "annotation"), (0x4000, "enum"),
]


class Reader:
    """Позиционный бинарный ридер по байтам class-файла."""
    def __init__(self, data: bytes):
        self.data = data
        self.pos = 0

    def u1(self):
        v = self.data[self.pos]
        self.pos += 1
        return v

    def u2(self):
        v = struct.unpack_from(">H", self.data, self.pos)[0]
        self.pos += 2
        return v

    def u4(self):
        v = struct.unpack_from(">I", self.data, self.pos)[0]
        self.pos += 4
        return v

    def s1(self):
        v = struct.unpack_from(">b", self.data, self.pos)[0]
        self.pos += 1
        return v

    def s2(self):
        v = struct.unpack_from(">h", self.data, self.pos)[0]
        self.pos += 2
        return v

    def s4(self):
        v = struct.unpack_from(">i", self.data, self.pos)[0]
        self.pos += 4
        return v

    def bytes(self, n):
        v = self.data[self.pos:self.pos + n]
        self.pos += n
        return v

    def skip(self, n):
        self.pos += n


class Field:
    def __init__(self):
        self.access = 0
        self.name = ""
        self.descriptor = ""
        self.constant_value = None


class ExceptionEntry:
    def __init__(self, start_pc, end_pc, handler_pc, catch_type):
        self.start_pc = start_pc
        self.end_pc = end_pc
        self.handler_pc = handler_pc
        self.catch_type = catch_type


class Method:
    def __init__(self):
        self.access = 0
        self.name = ""
        self.descriptor = ""
        self.code = None            # raw bytecode bytes
        self.max_stack = 0
        self.max_locals = 0
        self.exceptions = []        # list[ExceptionEntry]
        self.instructions = []      # filled in by disassembler
        self.local_var_table = []   # list of (start_pc, length, name, descriptor, slot) - из
                                     # LocalVariableTable, если jar скомпилирован с отладочной
                                     # информацией (javac -g / maven по умолчанию часто её включает).
                                     # Пусто, если атрибута нет - тогда используются argN/varN.


class ClassFile:
    def __init__(self, path=None, data=None):
        if data is None:
            with open(path, "rb") as f:
                data = f.read()
        self.raw = data
        self.pool = {}          # index -> parsed entry tuple
        self.this_class_name = ""
        self.super_class_name = ""
        self.interfaces = []
        self.fields = []
        self.methods = []
        self.access = 0
        self.source_file = None
        self.bootstrap_methods = []   # list[(method_handle_cp_index, [arg_cp_index,...])]
        self.inner_classes = []       # list of dict(inner, outer, inner_name, access)
        self._parse(data)

    def method_handle_ref(self, mh_cp_index):
        """MethodHandle cp entry -> (kind:int, owner_internal, name, desc)."""
        e = self.pool.get(mh_cp_index)
        if not e or e[0] != "MethodHandle":
            return None
        kind, ref_idx = e[1], e[2]
        r = self.ref_string(ref_idx)
        if r is None:
            return None
        owner, name, desc = r
        return kind, owner, name, desc

    # ---------- constant pool resolution helpers ----------

    def utf8(self, idx):
        e = self.pool.get(idx)
        if e and e[0] == "Utf8":
            return e[1]
        return None

    def class_name(self, idx):
        """Class-info entry -> internal name (a/b/C)."""
        e = self.pool.get(idx)
        if not e or e[0] != "Class":
            return None
        return self.utf8(e[1])

    def name_and_type(self, idx):
        e = self.pool.get(idx)
        if not e or e[0] != "NameAndType":
            return None
        return self.utf8(e[1]), self.utf8(e[2])

    def ref_string(self, idx):
        """Fieldref/Methodref/InterfaceMethodref -> (owner_internal, name, desc)."""
        e = self.pool.get(idx)
        if not e or e[0] not in ("Fieldref", "Methodref", "InterfaceMethodref"):
            return None
        owner = self.class_name(e[1])
        nt = self.name_and_type(e[2])
        if nt is None:
            return owner, "?", "?"
        return owner, nt[0], nt[1]

    def describe_cp(self, idx):
        """Человекочитаемое представление произвольной cp-записи (для операндов инструкций)."""
        e = self.pool.get(idx)
        if e is None:
            return f"#{idx}"
        tag = e[0]
        if tag == "Utf8":
            return repr(e[1])
        if tag == "Integer":
            return str(e[1])
        if tag == "Float":
            return f"{e[1]}f"
        if tag == "Long":
            return f"{e[1]}L"
        if tag == "Double":
            return f"{e[1]}d"
        if tag == "String":
            s = self.utf8(e[1])
            return repr(s) if s is not None else f"#{e[1]}"
        if tag == "Class":
            n = self.utf8(e[1])
            return (n or f"#{e[1]}").replace("/", ".")
        if tag in ("Fieldref", "Methodref", "InterfaceMethodref"):
            r = self.ref_string(idx)
            if r is None:
                return f"#{idx}"
            owner, name, desc = r
            owner_dot = (owner or "?").replace("/", ".")
            return f"{owner_dot}.{name}:{desc}"
        if tag == "NameAndType":
            nt = self.name_and_type(idx)
            return f"{nt[0]}:{nt[1]}" if nt else f"#{idx}"
        if tag == "InvokeDynamic":
            nt = self.name_and_type(e[2])
            return f"invokedynamic {nt[0]}:{nt[1]}" if nt else f"#{idx}"
        if tag == "MethodType":
            return self.utf8(e[1]) or f"#{idx}"
        return f"#{idx}({tag})"

    # ---------- parsing ----------

    def _parse(self, data):
        r = Reader(data)
        magic = r.u4()
        if magic != 0xCAFEBABE:
            raise ValueError("Не class-файл (bad magic)")
        self.minor = r.u2()
        self.major = r.u2()
        cp_count = r.u2()

        i = 1
        while i < cp_count:
            tag_id = r.u1()
            tag = CONSTANT_TAGS.get(tag_id)
            if tag == "Utf8":
                length = r.u2()
                raw = r.bytes(length)
                s = raw.decode("utf-8", errors="replace")
                self.pool[i] = ("Utf8", s)
            elif tag == "Class":
                name_idx = r.u2()
                self.pool[i] = ("Class", name_idx)
            elif tag in ("Fieldref", "Methodref", "InterfaceMethodref"):
                c_idx = r.u2()
                nt_idx = r.u2()
                self.pool[i] = (tag, c_idx, nt_idx)
            elif tag == "String":
                s_idx = r.u2()
                self.pool[i] = ("String", s_idx)
            elif tag == "Integer":
                v = r.s4()
                self.pool[i] = ("Integer", v)
            elif tag == "Float":
                v = struct.unpack(">f", struct.pack(">i", r.s4()))[0]
                self.pool[i] = ("Float", v)
            elif tag == "Long":
                hi = r.u4(); lo = r.u4()
                v = (hi << 32) | lo
                if v >= 2**63:
                    v -= 2**64
                self.pool[i] = ("Long", v)
                i += 1
            elif tag == "Double":
                hi = r.u4(); lo = r.u4()
                bits = (hi << 32) | lo
                v = struct.unpack(">d", struct.pack(">Q", bits))[0]
                self.pool[i] = ("Double", v)
                i += 1
            elif tag == "NameAndType":
                n_idx = r.u2(); t_idx = r.u2()
                self.pool[i] = ("NameAndType", n_idx, t_idx)
            elif tag == "MethodHandle":
                kind = r.u1(); ref_idx = r.u2()
                self.pool[i] = ("MethodHandle", kind, ref_idx)
            elif tag == "MethodType":
                d_idx = r.u2()
                self.pool[i] = ("MethodType", d_idx)
            elif tag == "Dynamic" or tag == "InvokeDynamic":
                bsm_idx = r.u2(); nt_idx = r.u2()
                self.pool[i] = (tag, bsm_idx, nt_idx)
            elif tag in ("Module", "Package"):
                n_idx = r.u2()
                self.pool[i] = (tag, n_idx)
            else:
                raise ValueError(f"Неизвестный constant pool tag {tag_id} на позиции {r.pos}")
            i += 1

        self.access = r.u2()
        this_idx = r.u2()
        super_idx = r.u2()
        self.this_class_name = self.class_name(this_idx) or "Unknown"
        self.super_class_name = self.class_name(super_idx) if super_idx else None

        iface_count = r.u2()
        for _ in range(iface_count):
            idx = r.u2()
            self.interfaces.append(self.class_name(idx))

        field_count = r.u2()
        for _ in range(field_count):
            f = Field()
            f.access = r.u2()
            f.name = self.utf8(r.u2())
            f.descriptor = self.utf8(r.u2())
            attr_count = r.u2()
            for _ in range(attr_count):
                a_name = self.utf8(r.u2())
                a_len = r.u4()
                a_data = r.bytes(a_len)
                if a_name == "ConstantValue" and len(a_data) >= 2:
                    cv_idx = struct.unpack_from(">H", a_data, 0)[0]
                    f.constant_value = self.pool.get(cv_idx)
            self.fields.append(f)

        method_count = r.u2()
        for _ in range(method_count):
            m = Method()
            m.access = r.u2()
            m.name = self.utf8(r.u2())
            m.descriptor = self.utf8(r.u2())
            attr_count = r.u2()
            for _ in range(attr_count):
                a_name = self.utf8(r.u2())
                a_len = r.u4()
                a_data = r.bytes(a_len)
                if a_name == "Code":
                    self._parse_code(m, a_data)
            self.methods.append(m)

        # class attributes (SourceFile и т.д.)
        class_attr_count = r.u2()
        for _ in range(class_attr_count):
            a_name = self.utf8(r.u2())
            a_len = r.u4()
            a_data = r.bytes(a_len)
            if a_name == "SourceFile" and len(a_data) >= 2:
                sf_idx = struct.unpack_from(">H", a_data, 0)[0]
                self.source_file = self.utf8(sf_idx)
            elif a_name == "BootstrapMethods":
                ar = Reader(a_data)
                n = ar.u2()
                for _ in range(n):
                    mh_idx = ar.u2()
                    argc = ar.u2()
                    args = [ar.u2() for _ in range(argc)]
                    self.bootstrap_methods.append((mh_idx, args))
            elif a_name == "InnerClasses":
                ar = Reader(a_data)
                n = ar.u2()
                for _ in range(n):
                    inner_idx = ar.u2()
                    outer_idx = ar.u2()
                    name_idx = ar.u2()
                    iacc = ar.u2()
                    self.inner_classes.append({
                        "inner": self.class_name(inner_idx),
                        "outer": self.class_name(outer_idx) if outer_idx else None,
                        "inner_name": self.utf8(name_idx) if name_idx else None,
                        "access": iacc,
                    })

    def _parse_code(self, method: Method, a_data: bytes):
        cr = Reader(a_data)
        method.max_stack = cr.u2()
        method.max_locals = cr.u2()
        code_len = cr.u4()
        method.code = cr.bytes(code_len)
        exc_len = cr.u2()
        for _ in range(exc_len):
            start_pc = cr.u2(); end_pc = cr.u2(); handler_pc = cr.u2(); catch_idx = cr.u2()
            catch_type = self.class_name(catch_idx) if catch_idx else None
            method.exceptions.append(ExceptionEntry(start_pc, end_pc, handler_pc, catch_type))
        code_attr_count = cr.u2()
        for _ in range(code_attr_count):
            a_name_idx = cr.u2()
            sub_len = cr.u4()
            a_name = self.utf8(a_name_idx)
            if a_name == "LocalVariableTable":
                sub_data = cr.bytes(sub_len)
                sr = Reader(sub_data)
                n = sr.u2()
                for _ in range(n):
                    start_pc = sr.u2()
                    length = sr.u2()
                    name_idx = sr.u2()
                    desc_idx = sr.u2()
                    slot = sr.u2()
                    name = self.utf8(name_idx)
                    desc = self.utf8(desc_idx)
                    if name and name != "this":
                        method.local_var_table.append((start_pc, length, name, desc, slot))
            else:
                # LineNumberTable/LocalVariableTypeTable/StackMapTable и т.п. - не нужны
                cr.skip(sub_len)


def access_str(flags, kind="class"):
    parts = []
    for bit, name in ACC_FLAGS:
        if not (flags & bit):
            continue
        # synthetic/bridge/varargs/strictfp - внутренние флаги JVM без
        # соответствующего валидного модификатора в исходном Java-тексте
        # (varargs выражается через "..." в параметре, а не словом; synthetic
        # и bridge вообще не имеют исходного текста - метод/поле генерируется
        # компилятором) - печатать их буквально значило бы вывести невалидный
        # Java-код, поэтому просто пропускаем.
        if bit in (0x0040, 0x0080, 0x1000, 0x0800):
            if kind == "field" and bit == 0x0040:
                parts.append("volatile")
            elif kind == "field" and bit == 0x0080:
                parts.append("transient")
            continue
        n = name.split("/")[0]
        parts.append(n)
    return " ".join(parts)
