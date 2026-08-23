// dump_opcodes.cpp - печатает таблицу OPCODES построчно, для diff против
// opcodes.py (HANDOFF_25).
#include <iostream>

#include "opcodes.hpp"

using namespace nd;

int main() {
    for (int op = 0; op < 256; ++op) {
        const auto& e = OPCODES[op];
        if (e.mnemonic == nullptr) continue;
        std::cout << op << "\t" << e.mnemonic << "\t" << operand_kind_name(e.operand_kind) << "\n";
    }
    std::cout << "---NEWARRAY---\n";
    for (auto& [code, name] : NEWARRAY_TYPES) {
        std::cout << code << "\t" << name << "\n";
    }
    return 0;
}
