// batch_javatypes.cpp - прогоняет тот же корпус через C++ порт javatypes.hpp,
// печатает в ТОМ ЖЕ формате, что batch_javatypes_ref.py - для diff (HANDOFF_25).
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

#include "javatypes.hpp"

using namespace nd;

static std::vector<std::string> split_tabs(const std::string& line) {
    std::vector<std::string> out;
    size_t start = 0;
    for (size_t i = 0; i < line.size(); ++i) {
        if (line[i] == '\t') {
            out.push_back(line.substr(start, i - start));
            start = i + 1;
        }
    }
    out.push_back(line.substr(start));
    return out;
}

static std::string join(const std::vector<std::string>& v, const std::string& sep) {
    std::string out;
    for (size_t i = 0; i < v.size(); ++i) {
        if (i) out += sep;
        out += v[i];
    }
    return out;
}

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: batch_javatypes <corpus.txt>\n";
        return 2;
    }
    std::ifstream f(argv[1]);
    if (!f) {
        std::cerr << "cannot open " << argv[1] << "\n";
        return 2;
    }
    std::ostringstream out;
    std::string line;
    bool first = true;
    while (std::getline(f, line)) {
        if (line.empty()) continue;
        auto parts = split_tabs(line);
        const std::string& tag = parts[0];
        std::string result;
        try {
            if (tag == "DOTTED") {
                result = dotted_from_internal(parts[1]);
            } else if (tag == "FDESC") {
                result = field_descriptor_to_java(parts[1]);
            } else if (tag == "MDESC") {
                auto [ret, params] = method_descriptor_to_java(parts[1]);
                result = ret + "|" + join(params, ",");
            } else if (tag == "FSIG") {
                auto r = parse_field_signature(parts[1]);
                result = r.has_value() ? *r : "<None>";
            } else if (tag == "MSIG") {
                auto r = parse_method_signature(parts[1]);
                if (!r.has_value()) {
                    result = "<None>";
                } else {
                    result = r->type_params + "|" + join(r->param_types, ",") + "|" + r->return_type;
                }
            } else if (tag == "CSIG") {
                auto r = parse_class_signature(parts[1]);
                if (!r.has_value()) {
                    result = "<None>";
                } else {
                    result = r->type_params + "|" + r->superclass + "|" + join(r->interfaces, ",");
                }
            } else if (tag == "OBF") {
                const std::string& kind = parts[1];
                const std::string& name = parts[2];
                result = looks_obfuscated(name, kind) ? "True" : "False";
            } else if (tag == "SAFE") {
                result = is_safe_local_name(parts[1]) ? "True" : "False";
            } else {
                result = "<UNKNOWN_TAG:" + tag + ">";
            }
        } catch (const std::exception& ex) {
            result = std::string("<EXC:") + typeid(ex).name() + ">";
        }
        if (!first) out << "\n";
        first = false;
        out << result;
    }
    out << "\n";
    std::cout << out.str();
    return 0;
}
