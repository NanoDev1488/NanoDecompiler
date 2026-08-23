// ir.cpp - см. ir.hpp. 1:1 порт ir.py.
#include "ir.hpp"

#include <cstdio>

namespace nd {

namespace {
int8_t s1_at(const std::vector<uint8_t>& b, size_t p) { return static_cast<int8_t>(b[p]); }
uint16_t u2_at(const std::vector<uint8_t>& b, size_t p) {
    return (uint16_t(b[p]) << 8) | uint16_t(b[p + 1]);
}
int16_t s2_at(const std::vector<uint8_t>& b, size_t p) { return static_cast<int16_t>(u2_at(b, p)); }
int32_t s4_at(const std::vector<uint8_t>& b, size_t p) {
    uint32_t v = (uint32_t(b[p]) << 24) | (uint32_t(b[p + 1]) << 16) | (uint32_t(b[p + 2]) << 8) | uint32_t(b[p + 3]);
    return static_cast<int32_t>(v);
}
}  // namespace

DecodedMethod decode_method(const std::vector<uint8_t>& code) {
    DecodedMethod out;
    size_t n = code.size();
    size_t pc = 0;

    while (pc < n) {
        size_t start = pc;
        uint8_t opcode = code[pc];
        const OpcodeInfo& entry = OPCODES[opcode];
        if (entry.mnemonic == nullptr) {
            Instruction ins;
            ins.pc = start;
            ins.opcode = opcode;
            char buf[32];
            std::snprintf(buf, sizeof(buf), "<unknown 0x%02x>", opcode);
            ins.mnemonic = buf;
            ins.kind = OperandKind::None;
            ins.next_pc = start + 1;
            out.instrs[start] = ins;
            out.order.push_back(start);
            pc += 1;
            continue;
        }
        std::string mnemonic = entry.mnemonic;
        OperandKind kind = entry.operand_kind;
        pc += 1;

        Instruction ins;
        ins.pc = start;
        ins.opcode = opcode;
        ins.mnemonic = mnemonic;
        ins.kind = kind;

        switch (kind) {
            case OperandKind::None:
                break;
            case OperandKind::Byte:
                ins.ival = s1_at(code, pc); pc += 1;
                break;
            case OperandKind::Short:
                ins.ival = s2_at(code, pc); pc += 2;
                break;
            case OperandKind::UByteCp:
                ins.cp_index = code[pc]; pc += 1;
                break;
            case OperandKind::UShortCp:
                ins.cp_index = u2_at(code, pc); pc += 2;
                break;
            case OperandKind::LocalUByte:
                ins.ival = code[pc]; pc += 1;
                break;
            case OperandKind::IInc:
                ins.iinc_idx = code[pc]; pc += 1;
                ins.iinc_const = s1_at(code, pc); pc += 1;
                break;
            case OperandKind::Branch2: {
                int16_t off = s2_at(code, pc); pc += 2;
                ins.target = static_cast<int64_t>(start) + off;
                break;
            }
            case OperandKind::Branch4: {
                int32_t off = s4_at(code, pc); pc += 4;
                ins.target = static_cast<int64_t>(start) + off;
                break;
            }
            case OperandKind::AType: {
                uint8_t t = code[pc]; pc += 1;
                auto it = NEWARRAY_TYPES.find(t);
                ins.atype = (it != NEWARRAY_TYPES.end()) ? it->second : std::string("int");
                break;
            }
            case OperandKind::InvokeInterface:
                ins.cp_index = u2_at(code, pc); pc += 2;
                ins.count = code[pc]; pc += 1;
                pc += 1;
                break;
            case OperandKind::InvokeDynamic:
                ins.cp_index = u2_at(code, pc); pc += 2;
                pc += 2;
                break;
            case OperandKind::MultiANewArray:
                ins.cp_index = u2_at(code, pc); pc += 2;
                ins.dims = code[pc]; pc += 1;
                break;
            case OperandKind::TableSwitch: {
                size_t pad = (4 - (pc % 4)) % 4;
                pc += pad;
                int32_t deflt = s4_at(code, pc); pc += 4;
                int32_t low = s4_at(code, pc); pc += 4;
                int32_t high = s4_at(code, pc); pc += 4;
                SwitchTargets targets;
                for (int64_t val = low; val <= static_cast<int64_t>(high); ++val) {
                    int32_t off = s4_at(code, pc); pc += 4;
                    targets.emplace_back(val, static_cast<int64_t>(start) + off);
                }
                targets.emplace_back(std::nullopt, static_cast<int64_t>(start) + deflt);
                ins.targets = std::move(targets);
                break;
            }
            case OperandKind::LookupSwitch: {
                size_t pad = (4 - (pc % 4)) % 4;
                pc += pad;
                int32_t deflt = s4_at(code, pc); pc += 4;
                int32_t npairs = s4_at(code, pc); pc += 4;
                SwitchTargets targets;
                for (int32_t i = 0; i < npairs; ++i) {
                    int32_t match = s4_at(code, pc); pc += 4;
                    int32_t off = s4_at(code, pc); pc += 4;
                    targets.emplace_back(match, static_cast<int64_t>(start) + off);
                }
                targets.emplace_back(std::nullopt, static_cast<int64_t>(start) + deflt);
                ins.targets = std::move(targets);
                break;
            }
            case OperandKind::Wide: {
                uint8_t sub_op = code[pc]; pc += 1;
                const OpcodeInfo& sub_entry = OPCODES[sub_op];
                std::string sub_mn;
                if (sub_entry.mnemonic != nullptr) {
                    sub_mn = sub_entry.mnemonic;
                } else {
                    char buf[8];
                    std::snprintf(buf, sizeof(buf), "0x%02x", sub_op);
                    sub_mn = buf;
                }
                uint16_t idx = u2_at(code, pc); pc += 2;
                if (sub_op == 0x84) {  // iinc
                    int16_t cst = s2_at(code, pc); pc += 2;
                    ins.iinc_idx = idx;
                    ins.iinc_const = cst;
                    ins.mnemonic = "iinc";
                    ins.kind = OperandKind::IInc;
                } else {
                    ins.ival = idx;
                    ins.mnemonic = sub_mn;
                    ins.kind = OperandKind::LocalUByte;
                }
                break;
            }
        }
        ins.next_pc = pc;
        out.instrs[start] = ins;
        out.order.push_back(start);
    }
    return out;
}

}  // namespace nd
