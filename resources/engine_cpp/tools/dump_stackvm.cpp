// dump_stackvm.cpp - для каждого метода с кодом строит CFG и прогоняет
// simulate_block на каждом базовом блоке (с общим MethodCtx на весь метод,
// IdentityRenamer, пустой entry_stack + underflow-заглушки) - печатает
// сгенерированные операторы через emit.hpp - для diff против
// dump_stackvm_ref.py (HANDOFF_36).
#include <fstream>
#include <iostream>
#include <sstream>

#include "classfile.hpp"
#include "emit.hpp"
#include "ir.hpp"
#include "stackvm.hpp"

using namespace nd;

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_stackvm <path-to-.class>\n";
        return 2;
    }
    std::ifstream f(argv[1], std::ios::binary);
    if (!f) { std::cerr << "cannot open " << argv[1] << "\n"; return 2; }
    std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());

    IdentityRenamer renamer;
    std::map<std::string, std::string> known;  // пусто - как и в проверяемом наборе jar (нет внешних импортов)

    try {
        ClassFile cf(data);
        std::ostringstream out;
        for (auto& m : cf.methods) {
            if (!m.has_code) continue;
            out << "== " << m.name << m.descriptor << " ==\n";
            DecodedMethod dm = decode_method(m.code);
            CFG g(dm.instrs, dm.order, m.exceptions);

            MethodCtx ctx(cf, m, renamer, known, cf.this_class_name);
            for (auto& [start, block] : g.blocks) {
                out << " -- block " << start << " --\n";
                std::vector<ExprPtr> underflow_missing;
                try {
                    BlockResult res = simulate_block(block, {}, ctx, &underflow_missing);
                    for (auto& s : res.stmts) {
                        for (auto& line : emit_stmt(s, 2)) out << line << "\n";
                    }
                    out << "   exit_stack=[";
                    for (size_t k = 0; k < res.exit_stack.size(); ++k) {
                        if (k) out << ", ";
                        out << emit_expr(res.exit_stack[k]);
                    }
                    out << "]\n";
                    out << "   term_kind=" << res.term_kind.value_or("None");
                    if (res.cond) out << " cond=" << emit_expr(res.cond);
                    out << "\n";
                    if (!underflow_missing.empty()) out << "   underflow_missing=" << underflow_missing.size() << "\n";
                } catch (const DecompileAbort& ex) {
                    out << "   ABORT: " << ex.what() << "\n";
                }
            }
            out << "  [imports]: ";
            bool first_imp = true;
            for (auto& [d, s] : ctx.imports.items()) {
                if (!first_imp) out << " ";
                first_imp = false;
                out << d << "->" << s;
            }
            out << "\n";
        }
        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
