# -*- coding: utf-8 -*-
"""
Структурный разбор байткода метода в список объектов Instruction.
В отличие от disassembler.py (который сразу печатает текст), здесь каждая
инструкция - это объект с полями, пригодными для дальнейшего анализа
(построение CFG, символическая интерпретация стека).
"""
import struct
from opcodes import OPCODES, NEWARRAY_TYPES


def _u2(b, p):
    return struct.unpack_from(">H", b, p)[0]

def _s1(b, p):
    return struct.unpack_from(">b", b, p)[0]

def _s2(b, p):
    return struct.unpack_from(">h", b, p)[0]

def _s4(b, p):
    return struct.unpack_from(">i", b, p)[0]


class Instruction:
    __slots__ = ("pc", "next_pc", "opcode", "mnemonic", "kind",
                 "ival", "cp_index", "target", "targets", "iinc_idx",
                 "iinc_const", "atype", "count", "dims", "raw_len")

    def __init__(self, pc, opcode, mnemonic, kind):
        self.pc = pc
        self.opcode = opcode
        self.mnemonic = mnemonic
        self.kind = kind
        self.next_pc = None
        self.ival = None          # generic integer operand (byte/short/local index)
        self.cp_index = None      # constant-pool operand
        self.target = None        # absolute pc, for simple branches
        self.targets = None       # dict for switch: {value_or_None(default): abs_pc}
        self.iinc_idx = None
        self.iinc_const = None
        self.atype = None
        self.count = None         # invokeinterface arg count
        self.dims = None          # multianewarray dims

    def __repr__(self):
        return f"<{self.pc}:{self.mnemonic}>"

    @property
    def is_branch(self):
        return self.kind in ("branch2", "branch4")

    @property
    def is_switch(self):
        return self.kind in ("tableswitch", "lookupswitch")

    @property
    def is_return(self):
        return self.mnemonic in ("return", "ireturn", "lreturn", "freturn",
                                  "dreturn", "areturn")

    @property
    def is_unconditional(self):
        return self.mnemonic in ("goto", "goto_w") or self.is_return or \
               self.mnemonic in ("athrow",)

    @property
    def is_conditional(self):
        return self.is_branch and self.mnemonic not in ("goto", "goto_w")


def decode_method(code: bytes):
    """Возвращает dict pc -> Instruction, разбирая весь метод."""
    instrs = {}
    order = []
    n = len(code)
    pc = 0
    while pc < n:
        start = pc
        opcode = code[pc]
        entry = OPCODES.get(opcode)
        if entry is None:
            ins = Instruction(start, opcode, f"<unknown 0x{opcode:02x}>", "none")
            ins.next_pc = start + 1
            instrs[start] = ins
            order.append(start)
            pc += 1
            continue
        mnemonic, kind = entry
        pc += 1
        ins = Instruction(start, opcode, mnemonic, kind)

        if kind == "none":
            pass
        elif kind == "byte":
            ins.ival = _s1(code, pc); pc += 1
        elif kind == "short":
            ins.ival = _s2(code, pc); pc += 2
        elif kind == "ubyte_cp":
            ins.cp_index = code[pc]; pc += 1
        elif kind == "ushort_cp":
            ins.cp_index = _u2(code, pc); pc += 2
        elif kind == "local_ubyte":
            ins.ival = code[pc]; pc += 1
        elif kind == "iinc":
            ins.iinc_idx = code[pc]; pc += 1
            ins.iinc_const = _s1(code, pc); pc += 1
        elif kind == "branch2":
            off = _s2(code, pc); pc += 2
            ins.target = start + off
        elif kind == "branch4":
            off = _s4(code, pc); pc += 4
            ins.target = start + off
        elif kind == "atype":
            ins.atype = NEWARRAY_TYPES.get(code[pc], "int"); pc += 1
        elif kind == "invokeinterface":
            ins.cp_index = _u2(code, pc); pc += 2
            ins.count = code[pc]; pc += 1
            pc += 1
        elif kind == "invokedynamic":
            ins.cp_index = _u2(code, pc); pc += 2
            pc += 2
        elif kind == "multianewarray":
            ins.cp_index = _u2(code, pc); pc += 2
            ins.dims = code[pc]; pc += 1
        elif kind == "tableswitch":
            pad = (4 - (pc % 4)) % 4
            pc += pad
            default = _s4(code, pc); pc += 4
            low = _s4(code, pc); pc += 4
            high = _s4(code, pc); pc += 4
            targets = {}
            for val in range(low, high + 1):
                off = _s4(code, pc); pc += 4
                targets[val] = start + off
            targets[None] = start + default
            ins.targets = targets
        elif kind == "lookupswitch":
            pad = (4 - (pc % 4)) % 4
            pc += pad
            default = _s4(code, pc); pc += 4
            npairs = _s4(code, pc); pc += 4
            targets = {}
            for _ in range(npairs):
                match = _s4(code, pc); pc += 4
                off = _s4(code, pc); pc += 4
                targets[match] = start + off
            targets[None] = start + default
            ins.targets = targets
        elif kind == "wide":
            sub_op = code[pc]; pc += 1
            sub_entry = OPCODES.get(sub_op)
            sub_mn = sub_entry[0] if sub_entry else f"0x{sub_op:02x}"
            idx = _u2(code, pc); pc += 2
            if sub_op == 0x84:  # iinc
                const = _s2(code, pc); pc += 2
                ins.iinc_idx = idx
                ins.iinc_const = const
                ins.mnemonic = "iinc"
                ins.kind = "iinc"
            else:
                ins.ival = idx
                ins.mnemonic = sub_mn
                ins.kind = "local_ubyte"
        ins.next_pc = pc
        instrs[start] = ins
        order.append(start)
    return instrs, order
