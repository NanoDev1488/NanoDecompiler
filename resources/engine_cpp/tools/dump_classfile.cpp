// dump_classfile.cpp - служебный инструмент для HANDOFF_24: парсит .class
// файл через C++ порт и печатает канонический текстовый дамп ключевых полей
// (не сырой constant pool - высокоуровневые данные, которые реально
// используются дальше в движке) - для построчного diff против дампа той же
// структуры, снятого с оригинального classfile.py (см. dump_classfile_ref.py).
#include <cstdio>
#include <fstream>
#include <iostream>
#include <sstream>

#include "classfile.hpp"

using namespace nd;

static std::string opt_str(const std::optional<std::string>& s) {
    return s.has_value() ? *s : "<null>";
}

static void print_annotations(const std::vector<Annotation>& anns, const std::string& indent, std::ostream& out) {
    for (const auto& a : anns) {
        out << indent << "@" << a.type << "(";
        bool first = true;
        for (const auto& [k, v] : a.args) {
            if (!first) out << ", ";
            first = false;
            out << k << "=";
            switch (v->kind) {
                case AnnotationValue::Kind::Bool: out << (v->bool_v ? "true" : "false"); break;
                case AnnotationValue::Kind::Int: out << v->int_v; break;
                case AnnotationValue::Kind::Char: out << "'" << char(v->int_v) << "'"; break;
                case AnnotationValue::Kind::Long: out << v->int_v << "L"; break;
                case AnnotationValue::Kind::Float: out << v->dbl_v << "f"; break;
                case AnnotationValue::Kind::Double: out << v->dbl_v << "d"; break;
                case AnnotationValue::Kind::Str: out << "\"" << v->str_v << "\""; break;
                case AnnotationValue::Kind::Annotation: out << "<nested-ann:" << v->ann_v->type << ">"; break;
                case AnnotationValue::Kind::Array: out << "[array len=" << v->arr_v.size() << "]"; break;
                default: out << "<none>";
            }
        }
        out << ")\n";
    }
}

int main(int argc, char** argv) {
    if (argc < 2) {
        std::cerr << "usage: dump_classfile <path-to-.class>\n";
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
        out << "class " << cf.this_class_name << " extends " << opt_str(cf.super_class_name) << "\n";
        out << "access: " << access_str(cf.access, "class") << " (0x" << std::hex << cf.access << std::dec << ")\n";
        out << "major.minor: " << cf.major << "." << cf.minor << "\n";
        out << "source_file: " << opt_str(cf.source_file) << "\n";
        out << "signature: " << opt_str(cf.signature) << "\n";
        {
            std::string joined;
            for (size_t ii = 0; ii < cf.interfaces.size(); ++ii) {
                if (ii) joined += " ";
                joined += opt_str(cf.interfaces[ii]);
            }
            out << "interfaces: " << joined << " \n";
        }
        print_annotations(cf.annotations, "  ", out);

        out << "fields (" << cf.fields.size() << "):\n";
        for (auto& fld : cf.fields) {
            out << "  " << access_str(fld.access, "field") << " " << fld.descriptor << " " << fld.name;
            if (fld.signature) out << " sig=" << *fld.signature;
            out << "\n";
            print_annotations(fld.annotations, "    ", out);
        }

        out << "methods (" << cf.methods.size() << "):\n";
        for (auto& m : cf.methods) {
            out << "  " << access_str(m.access, "method") << " " << m.name << m.descriptor;
            if (m.signature) out << " sig=" << *m.signature;
            out << "\n";
            if (m.has_code) {
                out << "    code_len=" << m.code.size() << " max_stack=" << m.max_stack
                    << " max_locals=" << m.max_locals << " exceptions=" << m.exceptions.size()
                    << " locals=" << m.local_var_table.size() << "\n";
                for (auto& lv : m.local_var_table) {
                    out << "      local slot=" << lv.slot << " name=" << lv.name << " desc=" << lv.descriptor
                        << " start=" << lv.start_pc << " len=" << lv.length << "\n";
                }
            }
            print_annotations(m.annotations, "    ", out);
        }

        out << "inner_classes (" << cf.inner_classes.size() << "):\n";
        for (auto& ic : cf.inner_classes) {
            out << "  inner=" << opt_str(ic.inner) << " outer=" << opt_str(ic.outer)
                << " name=" << opt_str(ic.inner_name) << "\n";
        }
        out << "bootstrap_methods (" << cf.bootstrap_methods.size() << "):\n";

        std::cout << out.str();
    } catch (const std::exception& ex) {
        std::cerr << "ERROR: " << ex.what() << "\n";
        return 1;
    }
    return 0;
}
