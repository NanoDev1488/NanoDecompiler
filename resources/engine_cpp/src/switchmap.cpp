// switchmap.cpp - см. switchmap.hpp. 1:1 порт switchmap.py.
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
                if (i + 4 < n) {
                    const Instruction& g2 = *seq[i + 1];
                    const Instruction& ov = *seq[i + 2];
                    const Instruction& push_ins = *seq[i + 3];
                    const Instruction& store = *seq[i + 4];
                    if (g2.mnemonic == "getstatic" && ov.mnemonic == "invokevirtual" && store.mnemonic == "iastore") {
                        auto r2 = g2.cp_index.has_value() ? cf.ref_string(static_cast<uint16_t>(*g2.cp_index)) : std::nullopt;
                        auto rv = ov.cp_index.has_value() ? cf.ref_string(static_cast<uint16_t>(*ov.cp_index)) : std::nullopt;
                        if (r2.has_value() && rv.has_value() && std::get<1>(*rv) == "ordinal") {
                            std::string owner = std::get<0>(*r2);
                            std::string const_name = std::get<1>(*r2);
                            auto val = push_int_value(push_ins);
                            if (val.has_value()) {
                                table[*val] = const_name;
                                enum_owner = owner;
                                i += 5;
                                continue;
                            }
                        }
                    }
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
        for (auto& f : cf.fields)
            if (f.descriptor == "[I" && f.name.rfind("$SwitchMap$", 0) == 0) candidate_fields.push_back(&f);
        if (candidate_fields.empty()) continue;

        const Method* clinit = nullptr;
        for (auto& m : cf.methods)
            if (m.name == "<clinit>") {
                clinit = &m;
                break;
            }
        if (clinit == nullptr || !clinit->has_code) continue;

        DecodedMethod dm;
        try {
            dm = decode_method(clinit->code);
        } catch (const std::exception&) {
            continue;
        }

        for (auto f : candidate_fields) {
            auto table = extract_table(cf, dm, f->name);
            if (table.has_value()) result.switchmap_fields[{internal, f->name}] = *table;
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
            if (m.name != "<clinit>") non_clinit_methods++;
        if (all_fields_are_switchmaps && non_clinit_methods == 0) result.synthetic_classes.insert(internal);
    }

    return result;
}

}  // namespace nd
