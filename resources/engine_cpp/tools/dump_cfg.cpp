// dump_cfg.cpp - для каждого метода с кодом строит CFG (cfg.hpp) и печатает
// канонический дамп (блоки/рёбра/доминаторы/циклы/постдоминаторы) - для
// diff против dump_cfg_ref.py (HANDOFF_28).
#include <fstream>
#include <iostream>
#include <sstream>

#include "cfg.hpp"
#include "classfile.hpp"
#include "ir.hpp"

using namespace nd;

static std::string opt_i64(const std::optional<int64_t>& v) { return v.has_value() ? std::to_string(*v) : "None"; }
static std::string opt_str(const std::optional<std::string>& v) { return v.has_value() ? *v : "None"; }

static std::string join_i64(const std::vector<int64_t>& v) {
    std::ostringstream o;
    for (size_t i = 0; i < v.size(); ++i) {
        if (i) o << ",";
        o << v[i];
    }
    return o.str();
}
static std::string join_i64_set(const std::set<int64_t>& v) {
    std::ostringstream o;
    bool first = true;
    for (auto x : v) {
        if (!first) o << ",";
        first = false;
        o << x;
    }
    return o.str();
}

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_cfg <path-to-.class>\n";
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
            CFG g(dm.instrs, [&] {
                std::vector<size_t> o;
                for (size_t pc : dm.order) o.push_back(pc);
                return o;
            }(), m.exceptions);

            out << "entry=" << opt_i64(g.entry) << "\n";
            out << "blocks(" << g.blocks.size() << "):\n";
            for (auto& [start, b] : g.blocks) {
                out << "  block " << start << ".." << b.end << " ninstrs=" << b.instrs.size()
                    << " succs=[" << join_i64(b.succs) << "] preds=[" << join_i64(b.preds) << "]"
                    << " idom=" << opt_i64(b.idom) << " handler_types=[";
                bool first = true;
                for (auto& [ct, ex] : b.handler_types) {
                    if (!first) out << ";";
                    first = false;
                    out << opt_str(ct) << ":" << ex.start_pc << "-" << ex.end_pc << "->" << ex.handler_pc;
                }
                out << "]\n";
            }

            auto loops = g.natural_loops();
            out << "natural_loops(" << loops.size() << "):\n";
            for (auto& [header, body, tails] : loops) {
                out << "  header=" << header << " body={" << join_i64_set(body) << "} tails={"
                    << join_i64_set(tails) << "}\n";
            }

            auto rpo = g.reverse_postorder_list();
            out << "rpo=[" << join_i64(rpo) << "]\n";

            auto ipdom = g.compute_postdominators();
            out << "ipdom:\n";
            for (auto& [b, ip] : ipdom) {
                out << "  " << b << " -> " << opt_i64(ip) << "\n";
            }
        }
        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
