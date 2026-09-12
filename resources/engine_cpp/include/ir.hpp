// ir.hpp - порт resources/engine/ir.py (v2.0, HANDOFF_27, модуль 4).
// В отличие от disassembler.hpp (который сразу печатает текст), здесь
// каждая инструкция - объект с полями для дальнейшего анализа (CFG,
// символическая интерпретация стека).
#pragma once

#include <cstdint>
#include <map>
#include <optional>
#include <string>
#include <vector>

#include "opcodes.hpp"

namespace nd {

// targets для switch: список (значение_или_default, абсолютный pc) в ТОМ ЖЕ
// порядке, что и вставка в Python dict (обычные значения по возрастанию,
// default - ПОСЛЕДНИМ) - это не просто "набор", порядок используется
// дальше в cfg.py при построении списка succs, поэтому обычная
// std::map (сортирующая по ключу, из-за чего default с ключом nullopt
// оказался бы ПЕРВЫМ) здесь семантически неверна - см. HANDOFF_28.
using SwitchTargets = std::vector<std::pair<std::optional<int64_t>, int64_t>>;

struct Instruction {
    size_t pc = 0;
    size_t next_pc = 0;
    uint8_t opcode = 0;
    std::string mnemonic;
    OperandKind kind = OperandKind::None;

    std::optional<int64_t> ival;         // общий целочисленный операнд (byte/short/local index)
    std::optional<int32_t> cp_index;     // операнд - индекс constant pool
    std::optional<int64_t> target;       // абсолютный pc для простых branch
    std::optional<SwitchTargets> targets;  // для switch
    std::optional<int32_t> iinc_idx;
    std::optional<int32_t> iinc_const;
    std::optional<std::string> atype;
    std::optional<int32_t> count;        // invokeinterface arg count
    std::optional<int32_t> dims;         // multianewarray dims

    bool is_branch() const { return kind == OperandKind::Branch2 || kind == OperandKind::Branch4; }
    bool is_switch() const { return kind == OperandKind::TableSwitch || kind == OperandKind::LookupSwitch; }
    bool is_return() const {
        return mnemonic == "return" || mnemonic == "ireturn" || mnemonic == "lreturn" ||
               mnemonic == "freturn" || mnemonic == "dreturn" || mnemonic == "areturn";
    }
    bool is_unconditional() const {
        return mnemonic == "goto" || mnemonic == "goto_w" || is_return() || mnemonic == "athrow";
    }
    bool is_conditional() const { return is_branch() && mnemonic != "goto" && mnemonic != "goto_w"; }
};

struct DecodedMethod {
    std::map<size_t, Instruction> instrs;  // pc -> Instruction
    std::vector<size_t> order;             // pc в порядке следования
};

DecodedMethod decode_method(const std::vector<uint8_t>& code);

}  // namespace nd
