// opcodes.hpp - порт resources/engine/opcodes.py (v2.0, HANDOFF_25, модуль 2a).
// Полная таблица опкодов JVM SE 21 (0x00-0xC9): opcode -> (mnemonic, operand_kind).
#pragma once

#include <array>
#include <map>
#include <optional>
#include <string>

namespace nd {

enum class OperandKind {
    None, Byte, Short, UByteCp, UShortCp, LocalUByte, IInc, Branch2, Branch4,
    AType, InvokeInterface, InvokeDynamic, MultiANewArray, TableSwitch,
    LookupSwitch, Wide,
};

struct OpcodeInfo {
    const char* mnemonic;
    OperandKind operand_kind;
};

// Индекс - опкод (0-255). Отсутствующие опкоды - {nullptr, None} (проверять
// mnemonic != nullptr перед использованием - как проверка `opcode in OPCODES`
// в Python).
extern const std::array<OpcodeInfo, 256> OPCODES;

// newarray type code (JVM spec) -> имя примитивного типа.
extern const std::map<int, std::string> NEWARRAY_TYPES;

std::string operand_kind_name(OperandKind k);

}  // namespace nd
