// dump_engine.cpp - для каждого метода класса вызывает decompile_method_body
// (полный пайплайн) и печатает результат - для diff против
// dump_engine_ref.py (HANDOFF_39).
#include <fstream>
#include <iostream>
#include <sstream>

#include "classfile.hpp"
#include "engine.hpp"

using namespace nd;

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_engine <path-to-.class>\n";
        return 2;
    }
    std::ifstream f(argv[1], std::ios::binary);
    if (!f) { std::cerr << "cannot open " << argv[1] << "\n"; return 2; }
    std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());

    IdentityRenamer renamer;
    std::map<std::string, std::string> known;

    try {
        ClassFile cf(data);
        std::ostringstream out;
        for (auto& m : cf.methods) {
            if (!m.has_code) continue;
            out << "== " << m.name << m.descriptor << " ==\n";
            auto result = decompile_method_body(cf, m, renamer, known, cf.this_class_name);
            if (!result.ok) {
                out << "ABORT: " << result.reason.value_or("<no reason>") << "\n";
                continue;
            }
            for (auto& line : result.java_lines) out << line << "\n";
        }
        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
