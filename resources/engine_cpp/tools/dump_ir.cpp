// dump_ir.cpp - для каждого метода с кодом печатает канонический дамп
// декодированных инструкций (ir.hpp) - для diff против dump_ir_ref.py
// (HANDOFF_27).
#include <algorithm>
#include <fstream>
#include <iostream>
#include <sstream>

#include "classfile.hpp"
#include "ir.hpp"

using namespace nd;

static std::string opt_i64(const std::optional<int64_t>& v) { return v.has_value() ? std::to_string(*v) : "None"; }
static std::string opt_i32(const std::optional<int32_t>& v) { return v.has_value() ? std::to_string(*v) : "None"; }
static std::string opt_str(const std::optional<std::string>& v) { return v.has_value() ? *v : "None"; }
static std::string pybool(bool b) { return b ? "True" : "False"; }

static std::string fmt_targets(const std::optional<SwitchTargets>& t) {
    if (!t.has_value()) return "None";
    // канонический порядок для вывода (сортированные значения, затем default) -
    // сам порядок ХРАНЕНИЯ (важный для cfg.cpp) при этом сохранён как есть в t;
    // здесь только для человекочитаемого сравнения дампов.
    std::vector<int64_t> keys;
    bool has_default = false;
    int64_t default_val = 0;
    std::map<int64_t, int64_t> lookup;
    for (auto& [k, v] : *t) {
        if (k.has_value()) {
            keys.push_back(*k);
            lookup[*k] = v;
        } else {
            has_default = true;
            default_val = v;
        }
    }
    std::sort(keys.begin(), keys.end());
    std::ostringstream out;
    out << "{";
    bool first = true;
    for (auto k : keys) {
        if (!first) out << ", ";
        first = false;
        out << k << ":" << lookup[k];
    }
    if (has_default) {
        if (!first) out << ", ";
        out << "default:" << default_val;
    }
    out << "}";
    return out.str();
}

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_ir <path-to-.class>\n";
        return 2;
    }
    std::ifstream f(argv[1], std::ios::binary);
    if (!f) {
        std::cerr << "cannot open " << argv[1] << "\n";
        return 2;
    }
    std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());
    try {
        ClassFile cf(data);
        std::ostringstream out;
        for (auto& m : cf.methods) {
            if (!m.has_code) continue;
            out << "== " << m.name << m.descriptor << " ==\n";
            DecodedMethod dm = decode_method(m.code);
            for (size_t pc : dm.order) {
                const Instruction& ins = dm.instrs.at(pc);
                out << "pc=" << ins.pc << " next_pc=" << ins.next_pc << " opcode=" << int(ins.opcode)
                    << " mnemonic=" << ins.mnemonic << " kind=" << operand_kind_name(ins.kind)
                    << " ival=" << opt_i64(ins.ival) << " cp_index=" << opt_i32(ins.cp_index)
                    << " target=" << opt_i64(ins.target) << " targets=" << fmt_targets(ins.targets)
                    << " iinc_idx=" << opt_i32(ins.iinc_idx) << " iinc_const=" << opt_i32(ins.iinc_const)
                    << " atype=" << opt_str(ins.atype) << " count=" << opt_i32(ins.count)
                    << " dims=" << opt_i32(ins.dims)
                    << " branch=" << pybool(ins.is_branch()) << " switch=" << pybool(ins.is_switch())
                    << " return=" << pybool(ins.is_return()) << " uncond=" << pybool(ins.is_unconditional())
                    << " cond=" << pybool(ins.is_conditional()) << "\n";
            }
        }
        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
