// dump_structure.cpp - для каждого метода с кодом строит CFG, симулирует
// все блоки (с посевом CAUGHT_SENTINEL для exception-handler блоков, как
// это в реальности делает ещё не перенесённый engine.py), запускает
// Structurer::build() + simplify_stmts(), печатает итоговый Java-текст
// через emit.hpp - для diff против dump_structure_ref.py (HANDOFF_38).
#include <fstream>
#include <iostream>
#include <sstream>

#include "classfile.hpp"
#include "emit.hpp"
#include "ir.hpp"
#include "stackvm.hpp"
#include "structure.hpp"

using namespace nd;

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_structure <path-to-.class>\n";
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
            DecodedMethod dm = decode_method(m.code);
            CFG g(dm.instrs, dm.order, m.exceptions);
            MethodCtx ctx(cf, m, renamer, known, cf.this_class_name);

            std::map<int64_t, std::vector<ExprPtr>> seeds;
            for (auto& [start, block] : g.blocks) {
                if (!block.handler_types.empty()) {
                    seeds[start] = {std::make_shared<Local>(CAUGHT_SENTINEL, "Throwable")};
                }
            }
            std::map<int64_t, BlockResult> results;
            for (auto& [start, block] : g.blocks) {
                std::vector<ExprPtr> seed = seeds.count(start) ? seeds[start] : std::vector<ExprPtr>{};
                std::vector<ExprPtr> underflow;
                try {
                    results[start] = simulate_block(block, seed, ctx, &underflow);
                } catch (const DecompileAbort& ex) {
                    out << "  ABORT(simulate " << start << "): " << ex.what() << "\n";
                    goto next_method;
                }
            }
            try {
                Structurer st(g, results, m.exceptions, ctx);
                auto stmts = st.build(*g.entry);
                stmts = simplify_stmts(stmts);
                for (auto& s : stmts) {
                    for (auto& line : emit_stmt(s, 1)) out << line << "\n";
                }
            } catch (const DecompileAbort& ex) {
                out << "  ABORT(structure): " << ex.what() << "\n";
            }
            next_method:;
        }
        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
