// dump_disasm.cpp - для каждого метода в .class файле печатает дизассемблер
// (через C++ порт disassembler.hpp) - для diff против dump_disasm_ref.py
// (HANDOFF_26).
#include <fstream>
#include <iostream>

#include "classfile.hpp"
#include "disassembler.hpp"

using namespace nd;

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_disasm <path-to-.class>\n";
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
        for (auto& m : cf.methods) {
            if (!m.has_code) continue;
            std::cout << "== " << m.name << m.descriptor << " ==\n";
            auto lines = disassemble(m.code, cf, &m);
            for (auto& l : lines) std::cout << l << "\n";
        }
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
