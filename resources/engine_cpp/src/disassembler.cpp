// disassembler.cpp - см. disassembler.hpp. 1:1 порт disassembler.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "disassembler.hpp"

#include <cstdio>
#include <sstream>

#include "opcodes.hpp"

namespace nd {

namespace {

int8_t s1_at(const std::vector<uint8_t>& b, size_t p) {
    return (p < b.size()) ? static_cast<int8_t>(b[p]) : 0;
}
uint16_t u2_at(const std::vector<uint8_t>& b, size_t p) {
    if (p + 1 >= b.size()) return 0;
    return (uint16_t(b[p]) << 8) | uint16_t(b[p + 1]);
}
int16_t s2_at(const std::vector<uint8_t>& b, size_t p) { return static_cast<int16_t>(u2_at(b, p)); }
int32_t s4_at(const std::vector<uint8_t>& b, size_t p) {
    if (p + 3 >= b.size()) return 0;
    uint32_t v = (uint32_t(b[p]) << 24) | (uint32_t(b[p + 1]) << 16) | (uint32_t(b[p + 2]) << 8) | uint32_t(b[p + 3]);
    return static_cast<int32_t>(v);
}

std::string fmt_signed(int64_t v) {
    char buf[32];
    std::snprintf(buf, sizeof(buf), "%+lld", static_cast<long long>(v));
    return buf;
}

std::string replace_slashes(std::string s) {
    for (auto& c : s) if (c == '/') c = '.';
    return s;
}

}  // namespace

std::vector<std::string> disassemble(const std::vector<uint8_t>& code, const ClassFile& cf, const Method* method) {
    std::vector<std::string> lines;
    size_t n = code.size();
    size_t pc = 0;

    while (pc < n) {
        size_t start = pc;
        uint8_t opcode = code[pc];
        const OpcodeInfo& entry = OPCODES[opcode];
        if (entry.mnemonic == nullptr) {
            char buf[64];
            std::snprintf(buf, sizeof(buf), "%6zu: <unknown opcode 0x%02x>", start, opcode);
            lines.emplace_back(buf);
            pc += 1;
            continue;
        }
        std::string mnemonic = entry.mnemonic;
        OperandKind kind = entry.operand_kind;
        pc += 1;
        std::string operand_txt;

        switch (kind) {
            case OperandKind::None:
                break;
            case OperandKind::Byte: {
                if (pc + 1 > n) { pc = n; operand_txt = "<truncated>"; break; }
                int v = s1_at(code, pc); pc += 1;
                operand_txt = std::to_string(v);
                break;
            }
            case OperandKind::Short: {
                if (pc + 2 > n) { pc = n; operand_txt = "<truncated>"; break; }
                int v = s2_at(code, pc); pc += 2;
                operand_txt = std::to_string(v);
                break;
            }
            case OperandKind::UByteCp: {
                if (pc + 1 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint8_t idx = code[pc]; pc += 1;
                operand_txt = "#" + std::to_string(idx) + " // " + cf.describe_cp(idx);
                break;
            }
            case OperandKind::UShortCp: {
                if (pc + 2 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint16_t idx = u2_at(code, pc); pc += 2;
                operand_txt = "#" + std::to_string(idx) + " // " + cf.describe_cp(idx);
                break;
            }
            case OperandKind::LocalUByte: {
                if (pc + 1 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint8_t idx = code[pc]; pc += 1;
                std::string var_name;
                if (method != nullptr) {
                    for (const auto& lv : method->local_var_table) {
                        if (lv.slot == idx && start >= lv.start_pc && start < lv.start_pc + lv.length) {
                            var_name = lv.name;
                            break;
                        }
                    }
                }
                operand_txt = std::to_string(idx) + (var_name.empty() ? "" : (" // " + var_name));
                break;
            }
            case OperandKind::IInc: {
                if (pc + 2 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint8_t idx = code[pc]; pc += 1;
                int8_t cst = s1_at(code, pc); pc += 1;
                operand_txt = std::to_string(idx) + ", " + std::to_string(cst);
                break;
            }
            case OperandKind::Branch2: {
                if (pc + 2 > n) { pc = n; operand_txt = "<truncated>"; break; }
                int16_t off = s2_at(code, pc); pc += 2;
                operand_txt = std::to_string(static_cast<int64_t>(start) + off) + " (offset " + fmt_signed(off) + ")";
                break;
            }
            case OperandKind::Branch4: {
                if (pc + 4 > n) { pc = n; operand_txt = "<truncated>"; break; }
                int32_t off = s4_at(code, pc); pc += 4;
                operand_txt = std::to_string(static_cast<int64_t>(start) + off) + " (offset " + fmt_signed(off) + ")";
                break;
            }
            case OperandKind::AType: {
                if (pc + 1 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint8_t t = code[pc]; pc += 1;
                auto it = NEWARRAY_TYPES.find(t);
                operand_txt = (it != NEWARRAY_TYPES.end()) ? it->second : ("type" + std::to_string(t));
                break;
            }
            case OperandKind::InvokeInterface: {
                if (pc + 4 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint16_t idx = u2_at(code, pc); pc += 2;
                uint8_t count = code[pc]; pc += 1;
                pc += 1;  // нулевой байт
                operand_txt = "#" + std::to_string(idx) + " // " + cf.describe_cp(idx) + ", count " + std::to_string(count);
                break;
            }
            case OperandKind::InvokeDynamic: {
                if (pc + 4 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint16_t idx = u2_at(code, pc); pc += 2;
                pc += 2;  // два нулевых байта
                operand_txt = "#" + std::to_string(idx) + " // " + cf.describe_cp(idx);
                break;
            }
            case OperandKind::MultiANewArray: {
                if (pc + 3 > n) { pc = n; operand_txt = "<truncated>"; break; }
                uint16_t idx = u2_at(code, pc); pc += 2;
                uint8_t dims = code[pc]; pc += 1;
                operand_txt = "#" + std::to_string(idx) + " // " + cf.describe_cp(idx) + ", dims " + std::to_string(dims);
                break;
            }
            case OperandKind::TableSwitch: {
                size_t pad = (4 - (pc % 4)) % 4;
                if (pc + pad + 12 > n) {
                    pc = n;
                    operand_txt = "<truncated tableswitch>";
                    break;
                }
                pc += pad;
                int32_t deflt = s4_at(code, pc); pc += 4;
                int32_t low = s4_at(code, pc); pc += 4;
                int32_t high = s4_at(code, pc); pc += 4;
                if (high < low || static_cast<int64_t>(high) - low > 16384 || pc + static_cast<size_t>(static_cast<int64_t>(high) - low + 1) * 4 > n) {
                    operand_txt = "default->" + std::to_string(static_cast<int64_t>(start) + deflt) + ", <invalid/truncated range: " + std::to_string(low) + ".." + std::to_string(high) + ">";
                    pc = n;
                    break;
                }
                std::vector<std::string> targets;
                for (int64_t val = low; val <= static_cast<int64_t>(high); ++val) {
                    int32_t off = s4_at(code, pc); pc += 4;
                    targets.push_back(std::to_string(val) + "->" + std::to_string(static_cast<int64_t>(start) + off));
                }
                std::string joined;
                for (size_t i = 0; i < targets.size(); ++i) {
                    if (i) joined += ", ";
                    joined += targets[i];
                }
                operand_txt = "default->" + std::to_string(static_cast<int64_t>(start) + deflt) + ", " + joined;
                break;
            }
            case OperandKind::LookupSwitch: {
                size_t pad = (4 - (pc % 4)) % 4;
                if (pc + pad + 8 > n) {
                    pc = n;
                    operand_txt = "<truncated lookupswitch>";
                    break;
                }
                pc += pad;
                int32_t deflt = s4_at(code, pc); pc += 4;
                int32_t npairs = s4_at(code, pc); pc += 4;
                if (npairs < 0 || npairs > 16384 || pc + static_cast<size_t>(npairs) * 8 > n) {
                    operand_txt = "default->" + std::to_string(static_cast<int64_t>(start) + deflt) + ", <invalid/truncated pairs: " + std::to_string(npairs) + ">";
                    pc = n;
                    break;
                }
                std::vector<std::string> pairs;
                for (int32_t i = 0; i < npairs; ++i) {
                    int32_t match = s4_at(code, pc); pc += 4;
                    int32_t off = s4_at(code, pc); pc += 4;
                    pairs.push_back(std::to_string(match) + "->" + std::to_string(static_cast<int64_t>(start) + off));
                }
                std::string joined;
                for (size_t i = 0; i < pairs.size(); ++i) {
                    if (i) joined += ", ";
                    joined += pairs[i];
                }
                operand_txt = "default->" + std::to_string(static_cast<int64_t>(start) + deflt) + ", " + joined;
                break;
            }
            case OperandKind::Wide: {
                if (pc + 3 > n) { pc = n; operand_txt = "<truncated wide>"; break; }
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
                    if (pc + 2 > n) { pc = n; operand_txt = sub_mn + " " + std::to_string(idx) + " <truncated>"; break; }
                    int16_t cst = s2_at(code, pc); pc += 2;
                    operand_txt = sub_mn + " " + std::to_string(idx) + ", " + std::to_string(cst);
                } else {
                    operand_txt = sub_mn + " " + std::to_string(idx);
                }
                mnemonic = "wide";
                break;
            }
        }

        if (operand_txt.empty()) {
            if (mnemonic == "dup_x1") operand_txt = "// (v2, v1 -> v1, v2, v1)";
            else if (mnemonic == "dup_x2") operand_txt = "// (v3, v2, v1 -> v1, v3, v2, v1)";
            else if (mnemonic == "dup2_x1") operand_txt = "// (v3, v2, v1 -> v2, v1, v3, v2, v1)";
            else if (mnemonic == "dup2_x2") operand_txt = "// (v4, v3, v2, v1 -> v2, v1, v4, v3, v2, v1)";
            else if (method != nullptr && mnemonic.size() >= 7 && mnemonic[mnemonic.size() - 2] == '_') {
                char d = mnemonic.back();
                if (d >= '0' && d <= '3') {
                    uint16_t slot = d - '0';
                    for (const auto& lv : method->local_var_table) {
                        if (lv.slot == slot && start >= lv.start_pc && start < lv.start_pc + lv.length) {
                            operand_txt = "// " + lv.name;
                            break;
                        }
                    }
                }
            }
        }

        char head[32];
        std::snprintf(head, sizeof(head), "%6zu: ", start);
        std::string line = std::string(head) + mnemonic;
        if (!operand_txt.empty()) line += "  " + operand_txt;
        lines.push_back(std::move(line));
    }

    if (method != nullptr && !method->exceptions.empty()) {
        lines.emplace_back("      Exception table:");
        for (const auto& e : method->exceptions) {
            std::string catch_name = e.catch_type.has_value() ? replace_slashes(*e.catch_type) : "any";
            lines.push_back("        from " + std::to_string(e.start_pc) + " to " + std::to_string(e.end_pc) +
                             " target " + std::to_string(e.handler_pc) + " type " + catch_name);
        }
    }
    return lines;
}

}  // namespace nd
