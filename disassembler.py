# -*- coding: utf-8 -*-
import struct
from opcodes import OPCODES, NEWARRAY_TYPES


def disassemble(code: bytes, cf, method=None):
    """
    Возвращает список строк вида:
        "     0: aload_0"
        "     1: invokespecial #7 // java.lang.Object.<init>:()V"
    cf - ClassFile (для резолва constant pool операндов)
    """
    lines = []
    n = len(code)
    pc = 0
    while pc < n:
        start = pc
        opcode = code[pc]
        entry = OPCODES.get(opcode)
        if entry is None:
            lines.append(f"{start:6d}: <unknown opcode 0x{opcode:02x}>")
            pc += 1
            continue
        mnemonic, kind = entry
        pc += 1
        operand_txt = ""

        if kind == "none":
            pass
        elif kind == "byte":
            v = _s1(code, pc); pc += 1
            operand_txt = str(v)
        elif kind == "short":
            v = _s2(code, pc); pc += 2
            operand_txt = str(v)
        elif kind == "ubyte_cp":
            idx = code[pc]; pc += 1
            operand_txt = f"#{idx} // {cf.describe_cp(idx)}"
        elif kind == "ushort_cp":
            idx = _u2(code, pc); pc += 2
            operand_txt = f"#{idx} // {cf.describe_cp(idx)}"
        elif kind == "local_ubyte":
            idx = code[pc]; pc += 1
            operand_txt = str(idx)
        elif kind == "iinc":
            idx = code[pc]; pc += 1
            const = _s1(code, pc); pc += 1
            operand_txt = f"{idx}, {const}"
        elif kind == "branch2":
            off = _s2(code, pc); pc += 2
            operand_txt = f"{start + off} (offset {off:+d})"
        elif kind == "branch4":
            off = _s4(code, pc); pc += 4
            operand_txt = f"{start + off} (offset {off:+d})"
        elif kind == "atype":
            t = code[pc]; pc += 1
            operand_txt = NEWARRAY_TYPES.get(t, f"type{t}")
        elif kind == "invokeinterface":
            idx = _u2(code, pc); pc += 2
            count = code[pc]; pc += 1
            pc += 1  # zero byte
            operand_txt = f"#{idx} // {cf.describe_cp(idx)}, count {count}"
        elif kind == "invokedynamic":
            idx = _u2(code, pc); pc += 2
            pc += 2  # two zero bytes
            operand_txt = f"#{idx} // {cf.describe_cp(idx)}"
        elif kind == "multianewarray":
            idx = _u2(code, pc); pc += 2
            dims = code[pc]; pc += 1
            operand_txt = f"#{idx} // {cf.describe_cp(idx)}, dims {dims}"
        elif kind == "tableswitch":
            # padding to next 4-byte boundary (relative to start of instruction sequence)
            pad = (4 - (pc % 4)) % 4
            pc += pad
            default = _s4(code, pc); pc += 4
            low = _s4(code, pc); pc += 4
            high = _s4(code, pc); pc += 4
            targets = []
            for val in range(low, high + 1):
                off = _s4(code, pc); pc += 4
                targets.append(f"{val}->{start + off}")
            operand_txt = f"default->{start + default}, " + ", ".join(targets)
        elif kind == "lookupswitch":
            pad = (4 - (pc % 4)) % 4
            pc += pad
            default = _s4(code, pc); pc += 4
            npairs = _s4(code, pc); pc += 4
            pairs = []
            for _ in range(npairs):
                match = _s4(code, pc); pc += 4
                off = _s4(code, pc); pc += 4
                pairs.append(f"{match}->{start + off}")
            operand_txt = f"default->{start + default}, " + ", ".join(pairs)
        elif kind == "wide":
            sub_op = code[pc]; pc += 1
            sub_entry = OPCODES.get(sub_op)
            sub_mn = sub_entry[0] if sub_entry else f"0x{sub_op:02x}"
            idx = _u2(code, pc); pc += 2
            if sub_op == 0x84:  # iinc
                const = _s2(code, pc); pc += 2
                operand_txt = f"{sub_mn} {idx}, {const}"
            else:
                operand_txt = f"{sub_mn} {idx}"
            mnemonic = "wide"
        else:
            operand_txt = "<?>"

        line = f"{start:6d}: {mnemonic}"
        if operand_txt:
            line += f"  {operand_txt}"
        lines.append(line)

    # аннотируем exception table, если есть
    if method is not None and getattr(method, "exceptions", None):
        lines.append("      Exception table:")
        for e in method.exceptions:
            catch = e.catch_type.replace("/", ".") if e.catch_type else "any"
            lines.append(
                f"        from {e.start_pc} to {e.end_pc} target {e.handler_pc} type {catch}"
            )
    return lines


def _u2(b, p):
    return struct.unpack_from(">H", b, p)[0]

def _s1(b, p):
    return struct.unpack_from(">b", b, p)[0]

def _s2(b, p):
    return struct.unpack_from(">h", b, p)[0]

def _s4(b, p):
    return struct.unpack_from(">i", b, p)[0]
