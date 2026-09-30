// switchmap.cpp - см. switchmap.hpp. 1:1 порт switchmap.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "switchmap.hpp"

#include <algorithm>
#include <optional>

#include "ir.hpp"

namespace nd {

namespace {

std::optional<int64_t> push_int_value(const Instruction& ins) {
    if (ins.mnemonic.rfind("iconst_", 0) == 0) {
        std::string v = ins.mnemonic.substr(7);
        if (v == "m1") return -1;
        return std::stoll(v);
    }
    if (ins.mnemonic == "bipush" || ins.mnemonic == "sipush") return ins.ival;
    return std::nullopt;
}

std::optional<SwitchmapFieldInfo> extract_table(const ClassFile& cf, const DecodedMethod& dm, const std::string& field_name) {
    std::map<int64_t, std::string> table;
    std::optional<std::string> enum_owner;

    std::vector<const Instruction*> seq;
    seq.reserve(dm.order.size());
    for (auto pc : dm.order) seq.push_back(&dm.instrs.at(pc));
    size_t n = seq.size();

    size_t i = 0;
    while (i < n) {
        const Instruction& ins = *seq[i];
        if (ins.mnemonic == "getstatic" && ins.cp_index.has_value()) {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (r.has_value() && std::get<1>(*r) == field_name && std::get<2>(*r) == "[I") {
                // Ищем шаблон: getstatic enum.CONST, invokevirtual ordinal, push N, iastore
                std::optional<std::string> cur_owner;
                std::optional<std::string> cur_const;
                std::optional<int64_t> cur_val;
                bool has_ordinal = false;
                bool has_iastore = false;

                size_t lookahead = std::min(n, i + 8);
                size_t j = i + 1;
                for (; j < lookahead; ++j) {
                    const Instruction& next_ins = *seq[j];
                    if (next_ins.mnemonic == "getstatic" && next_ins.cp_index.has_value()) {
                        auto r2 = cf.ref_string(static_cast<uint16_t>(*next_ins.cp_index));
                        if (r2.has_value()) {
                            cur_owner = std::get<0>(*r2);
                            cur_const = std::get<1>(*r2);
                        }
                    } else if (next_ins.mnemonic == "invokevirtual" && next_ins.cp_index.has_value()) {
                        auto rv = cf.ref_string(static_cast<uint16_t>(*next_ins.cp_index));
                        if (rv.has_value() && std::get<1>(*rv) == "ordinal") {
                            has_ordinal = true;
                        }
                    } else if (auto v = push_int_value(next_ins); v.has_value()) {
                        cur_val = *v;
                    } else if (next_ins.mnemonic == "iastore") {
                        has_iastore = true;
                        break;
                    }
                }

                if (has_iastore && has_ordinal && cur_owner.has_value() && cur_const.has_value() && cur_val.has_value()) {
                    table[*cur_val] = *cur_const;
                    enum_owner = *cur_owner;
                    i = j + 1;
                    continue;
                }
            }
        }
        i += 1;
    }

    if (table.empty() || !enum_owner.has_value()) return std::nullopt;
    return SwitchmapFieldInfo{*enum_owner, table};
}

}  // namespace

SwitchmapDetectionResult detect_switchmaps(const std::map<std::string, ClassFile>& class_files) {
    SwitchmapDetectionResult result;

    for (auto& [internal, cf] : class_files) {
        std::vector<const Field*> candidate_fields;
        for (auto& f : cf.fields) {
            if (f.descriptor == "[I" && (f.name.rfind("$SwitchMap$", 0) == 0 ||
                                         f.name.rfind("$SWITCH_TABLE$", 0) == 0 ||
                                         f.name.find("SwitchMap") != std::string::npos)) {
                candidate_fields.push_back(&f);
            }
        }
        if (candidate_fields.empty()) continue;

        for (auto& m : cf.methods) {
            if ((m.name == "<clinit>" || m.name.rfind("$SWITCH_TABLE$", 0) == 0) && m.has_code) {
                DecodedMethod dm;
                try {
                    dm = decode_method(m.code);
                } catch (const std::exception&) {
                    continue;
                }

                for (auto f : candidate_fields) {
                    auto table = extract_table(cf, dm, f->name);
                    if (table.has_value()) result.switchmap_fields[{internal, f->name}] = *table;
                }
            }
        }

        // Класс целиком - синтетический switch-map холдер (не часть
        // исходника), если ВСЕ его поля - это найденные $SwitchMap$ массивы,
        // и единственный метод - <clinit>.
        bool all_fields_are_switchmaps =
            cf.fields.size() == candidate_fields.size() &&
            std::all_of(candidate_fields.begin(), candidate_fields.end(),
                        [&](const Field* f) { return result.switchmap_fields.count({internal, f->name}) != 0; });
        size_t non_clinit_methods = 0;
        for (auto& m : cf.methods)
            if (m.name != "<clinit>" && m.name.rfind("$SWITCH_TABLE$", 0) != 0) non_clinit_methods++;
        if (all_fields_are_switchmaps && non_clinit_methods == 0) result.synthetic_classes.insert(internal);
    }

    return result;
}

}  // namespace nd
