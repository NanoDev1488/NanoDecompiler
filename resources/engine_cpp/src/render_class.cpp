// render_class.cpp - см. render_class.hpp. Порт render_class()/
// format_type_dotted()/_format_annotation()/_format_annotation_value()/
// format_field_constant() из main.py (HANDOFF_42). Комментарии по месту
// отмечают КАЖДОЕ сознательное отличие от оригинала.
#include "render_class.hpp"

#include <algorithm>
#include <cstdio>
#include <sstream>

#include <unordered_set>

#include "ast_nodes.hpp"
#include "emit.hpp"
#include "engine.hpp"
#include "javatypes.hpp"

namespace nd {

namespace {

std::string collapse_double_spaces(const std::string& s) {
    // Ровно то же самое, что Python str.replace("  ", " ") - ОДИН
    // нерекурсивный левый-направо проход (не сворачивает "   " в один
    // пробел полностью за один вызов - и оригинал этого не делает).
    std::string out;
    out.reserve(s.size());
    for (size_t i = 0; i < s.size();) {
        if (i + 1 < s.size() && s[i] == ' ' && s[i + 1] == ' ') {
            out += ' ';
            i += 2;
        } else {
            out += s[i];
            i += 1;
        }
    }
    return out;
}

std::pair<std::string, std::string> rpartition_slash(const std::string& s) {
    auto pos = s.find_last_of('/');
    if (pos == std::string::npos) return {"", s};
    return {s.substr(0, pos), s.substr(pos + 1)};
}

std::string simple_name_of_dotted(const std::string& dotted) {
    auto pos = dotted.find_last_of('.');
    return pos == std::string::npos ? dotted : dotted.substr(pos + 1);
}

std::string strip_array_suffix(const std::string& s) {
    std::string base = s;
    while (base.size() >= 2 && base.substr(base.size() - 2) == "[]") base = base.substr(0, base.size() - 2);
    return base;
}

// codepoint (<=0xFFFF, значение JVM char) -> UTF-8 байты.
std::string utf8_encode(unsigned cp) {
    std::string out;
    if (cp < 0x80) {
        out += static_cast<char>(cp);
    } else if (cp < 0x800) {
        out += static_cast<char>(0xC0 | (cp >> 6));
        out += static_cast<char>(0x80 | (cp & 0x3F));
    } else {
        out += static_cast<char>(0xE0 | (cp >> 12));
        out += static_cast<char>(0x80 | ((cp >> 6) & 0x3F));
        out += static_cast<char>(0x80 | (cp & 0x3F));
    }
    return out;
}

// Python str(float)/str(int) для "простых" значений аннотаций - НЕ то же
// самое, что java_float_literal() (которая добавляет суффикс f/d для
// литералов внутри тела метода) - здесь печатается "голое" число, как в
// оригинале (см. render_class.hpp - известное ограничение самого main.py,
// не чиним здесь: не заявленная в HANDOFF задача).
std::string python_str_double(double v) {
    if (v == static_cast<int64_t>(v) && std::abs(v) < 1e15) {
        char buf[64];
        std::snprintf(buf, sizeof(buf), "%.1f", v);
        return buf;
    }
    std::ostringstream oss;
    oss.precision(17);
    oss << v;
    return oss.str();
}

// HANDOFF_50: определение record'ов - см. render_class.hpp за общим
// описанием политики "печатать record Name(...) {} вместо class Name
// extends Record". Оба хелпера ниже работают по СЫРОМУ байткоду метода
// (не по уже декомпилированному AST) - сознательно: при ложноотрицательном
// срабатывании (не распознали тривиальный паттерн) последствие -
// напечатать явный, но полностью валидный код (accessor/equals остаётся
// как обычный метод) - НЕ баг корректности, просто чуть менее "чистый"
// результат. Ложноположительного срабатывания в общем-то не может быть
// логически (проверяются точные байты опкодов), но даже если бы могло -
// цена ошибки та же (валидный, просто более многословный код).

// Аксессор компонента record'а генерируется javac как ТОЧНО:
// aload_0; getfield <это же поле>; <возврат>. Ничего больше. Сверяем и
// форму байткода, и что getfield ссылается ИМЕННО на ожидаемое поле (по
// имени через constant pool - индекс Fieldref-записи может быть разным
// при повторном использовании пула, поэтому сверяем не индекс, а имя).
bool code_is_getfield_return(const ClassFile& cf, const std::vector<uint8_t>& code, const std::string& field_name) {
    if (code.size() != 5) return false;  // aload_0(1) + getfield+idx(3) + <return>(1)
    if (code[0] != 0x2a || code[1] != 0xb4) return false;  // aload_0; getfield
    uint16_t idx = static_cast<uint16_t>((code[2] << 8) | code[3]);
    auto ref = cf.ref_string(idx);
    if (!ref.has_value()) return false;
    return std::get<1>(*ref) == field_name;
}

// equals/hashCode/toString сгенерированы через ObjectMethods.bootstrap
// (см. stackvm.cpp::build_object_methods, HANDOFF_49), если тело метода -
// ТОЛЬКО invokedynamic (0xba) на этот bootstrap + возврат, без какой-либо
// собственной логики (пользователь ничего не переопределял).
bool is_object_methods_delegate(const ClassFile& cf, const Method& m) {
    const auto& code = m.code;
    // aload_0 [, aload_1] ; invokedynamic idx1 idx2 0 0 ; <return>
    size_t i = 0;
    if (i >= code.size() || code[i] != 0x2a) return false;  // aload_0 (this)
    i += 1;
    if (i < code.size() && code[i] == 0x2b) i += 1;  // aload_1 (equals: второй аргумент)
    if (i + 5 > code.size() || code[i] != 0xba) return false;  // invokedynamic
    uint16_t cp_idx = static_cast<uint16_t>((code[i + 1] << 8) | code[i + 2]);
    i += 5;  // invokedynamic всегда 5 байт (opcode + idx1 + idx2 + 0 + 0)
    if (i != code.size() - 1) return false;  // после invokedynamic - РОВНО один опкод возврата
    auto it = cf.pool.find(cp_idx);
    if (it == cf.pool.end() || it->second.tag != CpTag::InvokeDynamic) return false;
    uint16_t bsm_idx = it->second.idx1;
    if (bsm_idx >= cf.bootstrap_methods.size()) return false;
    auto mh = cf.method_handle_ref(cf.bootstrap_methods[bsm_idx].method_handle_idx);
    if (!mh.has_value()) return false;
    return std::get<1>(*mh) == "java/lang/runtime/ObjectMethods";
}

}  // namespace

std::string format_type_dotted(const std::string& java_type, const Renamer& renamer,
                                const std::map<std::string, std::string>& known_internal_by_dotted,
                                OrderedImports* all_imports) {
    std::string arr;
    std::string base = java_type;
    while (base.size() >= 2 && base.substr(base.size() - 2) == "[]") {
        arr += "[]";
        base = base.substr(0, base.size() - 2);
    }
    auto it = known_internal_by_dotted.find(base);
    if (it != known_internal_by_dotted.end()) {
        std::string internal = it->second;
        base = dotted_from_internal(renamer.friendly_class(internal));
        if (all_imports) all_imports->set_default(base, simple_name_of_dotted(base));
    } else if (all_imports && base.find('.') != std::string::npos) {
        bool bare_java_lang = base.rfind("java.lang.", 0) == 0 && base.substr(10).find('.') == std::string::npos;
        if (!bare_java_lang) all_imports->set_default(base, simple_name_of_dotted(base));
    }
    return base + arr;
}

std::optional<std::string> format_annotation_value(const AnnotationValue& v) {
    switch (v.kind) {
        case AnnotationValue::Kind::Bool:
            return v.bool_v ? "true" : "false";
        case AnnotationValue::Kind::Int:
        case AnnotationValue::Kind::Char:
        case AnnotationValue::Kind::Long:
            return std::to_string(v.int_v);
        case AnnotationValue::Kind::Float:
        case AnnotationValue::Kind::Double:
            return python_str_double(v.dbl_v);
        case AnnotationValue::Kind::Str:
            return java_string_literal(v.str_v);
        default:
            return std::nullopt;  // enum-константа/аннотация/массив - сложно, не гадаем
    }
}

std::string format_annotation(const Annotation& ann, const Renamer& renamer,
                               const std::map<std::string, std::string>& known_internal_by_dotted,
                               OrderedImports& all_imports) {
    std::string java_type;
    try {
        java_type = field_descriptor_to_java(ann.type);
    } catch (...) {
        std::string t = ann.type;
        if (!t.empty() && t.front() == 'L') t.erase(t.begin());
        if (!t.empty() && t.back() == ';') t.pop_back();
        for (auto& c : t)
            if (c == '/') c = '.';
        java_type = t;
    }
    std::string dotted = format_type_dotted(java_type, renamer, known_internal_by_dotted, &all_imports);
    std::string name_marker = mark_type(dotted);
    if (ann.args.empty()) return "@" + name_marker;
    bool single_value = ann.args.size() == 1 && ann.args[0].first == "value";
    std::vector<std::string> parts;
    for (auto& [k, v] : ann.args) {
        std::optional<std::string> rendered = v ? format_annotation_value(*v) : std::nullopt;
        if (!rendered.has_value()) return "@" + name_marker;  // сложное значение - без аргументов
        parts.push_back(single_value ? *rendered : (k + " = " + *rendered));
    }
    std::string joined;
    for (size_t i = 0; i < parts.size(); ++i) {
        if (i) joined += ", ";
        joined += parts[i];
    }
    return "@" + name_marker + "(" + joined + ")";
}

std::optional<std::string> format_field_constant(const ClassFile& cf, const std::optional<CpEntry>& entry,
                                                   const std::string& descriptor) {
    if (!entry.has_value()) return std::nullopt;
    const CpEntry& e = *entry;
    switch (e.tag) {
        case CpTag::String: {
            const std::string* s = cf.utf8(e.idx1);
            return java_string_literal(s ? *s : "");
        }
        case CpTag::Integer: {
            int32_t v = static_cast<int32_t>(e.int_value);
            if (descriptor == "Z") return std::string(v ? "true" : "false");
            if (descriptor == "C") {
                unsigned ch = static_cast<unsigned>(v) & 0xFFFF;
                if (ch == '\'') return std::string("'\\''");
                if (ch == '\\') return std::string("'\\\\'");
                if (ch == '\n') return std::string("'\\n'");
                if (ch == '\t') return std::string("'\\t'");
                if (ch == '\r') return std::string("'\\r'");
                if (ch < 0x20 || ch == 0x7f) {
                    char buf[16];
                    std::snprintf(buf, sizeof(buf), "'\\u%04x'", ch);
                    return std::string(buf);
                }
                return "'" + utf8_encode(ch) + "'";
            }
            return std::to_string(v);
        }
        case CpTag::Float:
            return java_float_literal(e.float_value, "f");
        case CpTag::Long:
            return std::to_string(e.int_value) + "L";
        case CpTag::Double:
            return java_float_literal(e.float_value, "");
        default:
            return std::nullopt;
    }
}

std::pair<std::string, OrderedImports> render_class(
    const ClassFile& cf, const Renamer& renamer, const std::map<std::string, std::string>& known_internal_by_dotted,
    ProjectStats& stats, const std::map<std::string, std::vector<std::string>>& enum_ordinals,
    const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables) {
    const std::string& internal = cf.this_class_name;
    std::string new_internal = renamer.friendly_class(internal);
    auto [pkg, simple] = rpartition_slash(new_internal);

    {
        auto [_, last_seg] = rpartition_slash(internal);
        if (last_seg == "package-info") {
            OrderedImports all_imports;
            std::vector<std::string> lines;
            lines.push_back("// исходный (обфусцированный) внутренний класс: " + dotted_from_internal(internal));
            for (auto& a : cf.annotations) lines.push_back(format_annotation(a, renamer, known_internal_by_dotted, all_imports));
            if (!pkg.empty()) {
                std::string dotted_pkg = pkg;
                for (auto& c : dotted_pkg)
                    if (c == '/') c = '.';
                lines.push_back("package " + dotted_pkg + ";");
            }
            std::string joined;
            for (size_t i = 0; i < lines.size(); ++i) {
                if (i) joined += "\n";
                joined += lines[i];
            }
            std::string text = resolve_type_markers(joined, {});
            return {text + "\n", all_imports};
        }
    }

    std::vector<std::string> lines;
    lines.push_back("// исходный (обфусцированный) внутренний класс: " + dotted_from_internal(internal));
    if (!pkg.empty()) {
        std::string dotted_pkg = pkg;
        for (auto& c : dotted_pkg)
            if (c == '/') c = '.';
        lines.push_back("package " + dotted_pkg + ";");
    }

    set_current_class(dotted_from_internal(new_internal));

    bool is_interface = (cf.access & 0x0200) != 0;
    bool is_enum = (cf.access & 0x4000) != 0;
    bool is_annotation = (cf.access & 0x2000) != 0;
    // HANDOFF_50: record'ы (JDK 16+) - см. RecordComponent (classfile.hpp).
    // is_record проверяется И по наличию record_components (надёжный
    // сигнал - атрибут Record в принципе не пишется ни для чего другого),
    // И по факту, что супер-класс - java/lang/Record (двойная проверка не
    // повредит, но именно record_components - единственный источник
    // истины по факту; extends-условие ниже и так исключает Record из
    // печати "extends" для is_enum-подобных случаев).
    bool is_record = !cf.record_components.empty();
    std::string kind = is_annotation ? "@interface" : (is_interface ? "interface" : (is_enum ? "enum" : (is_record ? "record" : "class")));

    std::vector<std::string> mod_bits;
    if (cf.access & 0x0001) mod_bits.push_back("public");
    if ((cf.access & 0x0010) && !is_enum) mod_bits.push_back("final");
    if ((cf.access & 0x0400) && !is_interface && !is_enum) mod_bits.push_back("abstract");
    std::string mods;
    for (size_t i = 0; i < mod_bits.size(); ++i) {
        if (i) mods += " ";
        mods += mod_bits[i];
    }

    OrderedImports all_imports;
    std::vector<std::string> class_annotation_lines;
    for (auto& a : cf.annotations) class_annotation_lines.push_back(format_annotation(a, renamer, known_internal_by_dotted, all_imports));

    std::string header = collapse_double_spaces(mods + " " + kind + " " + simple);
    // trim
    {
        size_t b = header.find_first_not_of(' ');
        size_t e = header.find_last_not_of(' ');
        header = (b == std::string::npos) ? "" : header.substr(b, e - b + 1);
    }

    std::optional<ClassSignature> cgsig;
    if (cf.signature.has_value()) {
        try {
            cgsig = parse_class_signature(*cf.signature);
        } catch (...) {
            cgsig = std::nullopt;
        }
    }
    if (cgsig.has_value() && !cgsig->type_params.empty()) header += cgsig->type_params;

    // HANDOFF_50: заголовок компонентов record'а - `record Name(Type comp1, ...)`.
    // Типы компонентов берём тем же путём, что и обычные поля (format_type_dotted +
    // mark_type) - НЕ пытаемся достать generic-сигнатуру компонента отдельно
    // (она есть и на самом accessor-методе того же имени - тип там уже
    // корректно generic, если компонент рендерился бы как обычное поле;
    // здесь, в заголовке record'а, ограничиваемся raw-типом ради простоты -
    // известное упрощение, не влияет на корректность, только на дженерик-
    // читаемость заголовка для generic record'ов, которые на практике редки
    // в Bukkit-плагинах).
    if (is_record) {
        std::vector<std::string> comp_strs;
        for (auto& rc : cf.record_components) {
            std::string jtype;
            try {
                jtype = format_type_dotted(field_descriptor_to_java(rc.descriptor), renamer, known_internal_by_dotted, &all_imports);
            } catch (...) {
                jtype = rc.descriptor;
            }
            comp_strs.push_back(mark_type(jtype) + " " + renamer.friendly_field(internal, rc.name, rc.descriptor));
        }
        std::string joined_comps;
        for (size_t i = 0; i < comp_strs.size(); ++i) {
            if (i) joined_comps += ", ";
            joined_comps += comp_strs[i];
        }
        header += "(" + joined_comps + ")";
    }

    if (!is_record && cf.super_class_name.has_value() && *cf.super_class_name != "java/lang/Object" && !is_interface && !is_enum &&
        *cf.super_class_name != "java/lang/Enum") {
        std::string super_disp = format_type_dotted(dotted_from_internal(*cf.super_class_name), renamer, known_internal_by_dotted, &all_imports);
        if (cgsig.has_value() && !cgsig->superclass.empty()) {
            header += " extends " + cgsig->superclass;
        } else {
            header += " extends " + mark_type(super_disp);
        }
    }
    if (!cf.interfaces.empty()) {
        std::vector<std::string> iface_strs;
        const std::vector<std::string>* giface = nullptr;
        if (cgsig.has_value() && cgsig->interfaces.size() == cf.interfaces.size()) giface = &cgsig->interfaces;
        for (size_t idx = 0; idx < cf.interfaces.size(); ++idx) {
            if (giface) {
                iface_strs.push_back((*giface)[idx]);
            } else {
                std::string d = cf.interfaces[idx].has_value()
                                     ? format_type_dotted(dotted_from_internal(*cf.interfaces[idx]), renamer, known_internal_by_dotted, &all_imports)
                                     : "Object";
                iface_strs.push_back(mark_type(d));
            }
        }
        std::string kw = is_interface ? "extends" : "implements";
        std::string joined;
        for (size_t i = 0; i < iface_strs.size(); ++i) {
            if (i) joined += ", ";
            joined += iface_strs[i];
        }
        header += " " + kw + " " + joined;
    }
    header += " {";

    std::vector<std::string> body_lines;

    std::string own_field_type_desc = "L" + internal + ";";
    std::vector<const Field*> enum_const_fields;
    std::vector<const Field*> other_fields;
    for (auto& f : cf.fields) {
        bool is_enum_const = is_enum && (f.access & 0x4000) && f.descriptor == own_field_type_desc;
        // HANDOFF_50: поля-компоненты record'а (private final поля,
        // backing'ующие каждый компонент) НЕ печатаем отдельно - они уже
        // объявлены в заголовке record'а. Печать их ЕЩЁ РАЗ как обычных
        // полей была бы ошибкой компиляции (дублирующее объявление поля с
        // тем же именем) - это единственная часть record-поддержки, где
        // ошибка обнаружения была бы некорректным кодом, а не просто
        // менее чистым - но обнаружение здесь тривиально надёжно (сверка
        // имя+дескриптор с record_components, у которых нет причин
        // расходиться с реальными полями того же класса).
        bool is_record_component_field =
            is_record && std::any_of(cf.record_components.begin(), cf.record_components.end(),
                                      [&](const RecordComponent& rc) { return rc.name == f.name && rc.descriptor == f.descriptor; });
        if (is_enum_const) {
            enum_const_fields.push_back(&f);
        } else if (is_record_component_field) {
            // пропускаем - см. комментарий выше
        } else if (!(is_enum && f.name == "$VALUES" && (f.access & 0x1000))) {
            other_fields.push_back(&f);
        }
    }

    if (is_enum && !enum_const_fields.empty()) {
        // HANDOFF_42: УПРОЩЕНО относительно оригинала - имена enum-констант
        // печатаются БЕЗ реконструкции аргументов конструктора из <clinit>
        // (см. render_class.hpp). Если у enum'а есть конструктор с
        // параметрами (помимо неявных name/ordinal), результат не
        // скомпилируется без ручной доводки - честно предупреждаем.
        bool ctor_needs_args = false;
        for (auto& m : cf.methods) {
            if (m.name == "<init>") {
                auto [ret, params] = ([&]() -> std::pair<std::string, std::vector<std::string>> {
                    try {
                        return method_descriptor_to_java(m.descriptor);
                    } catch (...) {
                        return {"", {}};
                    }
                })();
                (void)ret;
                if (params.size() > 2) ctor_needs_args = true;
            }
        }
        if (ctor_needs_args) {
            body_lines.push_back(
                "    // ВНИМАНИЕ: конструктор этого enum принимает аргументы - реконструкция аргументов");
            body_lines.push_back("    // вызовов конструктора для каждой константы НЕ перенесена в этой версии порта");
            body_lines.push_back("    // (см. HANDOFF_42) - список ниже без аргументов НЕ СКОМПИЛИРУЕТСЯ как есть.");
        }
        std::vector<std::string> names;
        for (auto f : enum_const_fields) names.push_back(renamer.friendly_field(internal, f->name, f->descriptor));
        std::string joined;
        for (size_t i = 0; i < names.size(); ++i) {
            if (i) joined += ",\n    ";
            joined += names[i];
        }
        body_lines.push_back("    " + joined + ";");
        body_lines.push_back("");
    }

    // HANDOFF_42: interface_field_inits ВСЕГДА пуст (см. render_class.hpp) -
    // <clinit> интерфейса всегда считается "сложным", если он вообще есть.
    bool interface_clinit_complex = false;
    if (is_interface && !is_enum) {
        for (auto& m : cf.methods) {
            if (m.name == "<clinit>" && m.has_code) {
                interface_clinit_complex = true;
                break;
            }
        }
    }

    if (!other_fields.empty()) body_lines.push_back("    // ---- поля ----");
    for (auto f : other_fields) {
        std::string fmods = access_str(f->access, "field");
        std::string jtype;
        try {
            jtype = field_descriptor_to_java(f->descriptor);
            jtype = format_type_dotted(jtype, renamer, known_internal_by_dotted, &all_imports);
        } catch (...) {
            jtype = f->descriptor;
        }
        std::optional<std::string> generic_jtype;
        if (f->signature.has_value()) {
            try {
                generic_jtype = parse_field_signature(*f->signature);
            } catch (...) {
                generic_jtype = std::nullopt;
            }
        }
        std::string fname = renamer.friendly_field(internal, f->name, f->descriptor);
        std::string renamed_note = (fname == f->name) ? "" : ("  // было: " + f->name);
        // interface_field_inits всегда пуст (см. выше) - cv только из ConstantValue.
        std::string cv;
        auto literal = format_field_constant(cf, f->constant_value, f->descriptor);
        if (literal.has_value()) cv = " = " + *literal;
        for (auto& a : f->annotations) body_lines.push_back("    " + format_annotation(a, renamer, known_internal_by_dotted, all_imports));
        std::string display_type = generic_jtype.has_value() ? *generic_jtype : mark_type(jtype);
        body_lines.push_back(collapse_double_spaces("    " + fmods + " " + display_type + " " + fname + cv + ";" + renamed_note));
        std::string base_jtype = strip_array_suffix(jtype);
        if (base_jtype.find('.') != std::string::npos) all_imports.set_default(base_jtype, simple_name_of_dotted(base_jtype));
    }
    if (!other_fields.empty()) body_lines.push_back("");

    std::set<const Method*> skip_methods;
    const Method* clinit_m = nullptr;
    for (auto& m : cf.methods)
        if (m.name == "<clinit>") {
            clinit_m = &m;
            break;
        }
    if (clinit_m != nullptr) {
        skip_methods.insert(clinit_m);
        if (clinit_m->has_code && is_interface && !is_enum) {
            stats.total_methods += 1;
            if (!interface_clinit_complex) {
                // interface_field_inits всегда пуст в этом порте (см. выше) ->
                // этот путь практически недостижим, оставлен для честности
                // относительно структуры оригинала.
                stats.decompiled_methods += 1;
            } else {
                stats.fallback_methods += 1;
                std::string reason = "<clinit> интерфейса требует ручной правки (static{} невозможен в interface)";
                bool found = false;
                for (auto& [r, cnt] : stats.fallback_reasons)
                    if (r.has_value() && *r == reason) {
                        cnt += 1;
                        found = true;
                        break;
                    }
                if (!found) stats.fallback_reasons.emplace_back(reason, 1);
                body_lines.push_back("    // ВНИМАНИЕ: static-инициализатор этого интерфейса не удалось безопасно");
                body_lines.push_back("    // разложить по полям (interface не может иметь блок static{} - JLS).");
                body_lines.push_back("    // Нужна РУЧНАЯ доводка полей выше по дизассемблированному листингу метода <clinit>.");
                body_lines.push_back("");
            }
        } else if (clinit_m->has_code) {
            if (is_enum && !enum_const_fields.empty()) {
                // HANDOFF_42: см. выше - реконструкция <clinit> для enum-констант
                // не перенесена, поэтому static-инициализация (если в <clinit>
                // enum'а было что-то ЕЩЁ, кроме создания констант) теряется.
                body_lines.push_back(
                    "    // ПРИМЕЧАНИЕ: static-инициализация этого enum (<clinit>) не разобрана в этой версии порта.");
                body_lines.push_back("    // См. HANDOFF_42 - при необходимости сверьтесь с дизассемблированным листингом.");
                body_lines.push_back("");
            } else {
                MethodDecompileResult cres2 =
                    decompile_method_body(cf, *clinit_m, renamer, known_internal_by_dotted, internal, 2, enum_ordinals, switchmap_tables);
                std::optional<std::vector<StmtPtr>> static_stmts;
                if (cres2.ok) static_stmts = cres2.stmts;
                if (static_stmts.has_value()) {
                    if (!static_stmts->empty()) {
                        auto* ret = dynamic_cast<ReturnStmt*>(static_stmts->back().get());
                        if (ret != nullptr && ret->expr == nullptr) static_stmts->pop_back();
                    }
                    for (auto& [d, s] : cres2.imports.items()) all_imports.set(d, s);
                    if (!static_stmts->empty()) {
                        std::vector<std::string> local_names;
                        for (auto& [slot, info] : cres2.locals) local_names.push_back(info.name);
                        set_shadow_context(local_names);
                        body_lines.push_back("    static {");
                        for (auto& l : cres2.pre_lines) body_lines.push_back(l);
                        for (auto& l : emit_stmts(*static_stmts, 2)) body_lines.push_back(l);
                        body_lines.push_back("    }");
                        body_lines.push_back("");
                    }
                    stats.total_methods += 1;
                    stats.decompiled_methods += 1;
                } else {
                    stats.total_methods += 1;
                    stats.fallback_methods += 1;
                    std::string reason = "<clinit> (static-инициализатор)";
                    bool found = false;
                    for (auto& [r, cnt] : stats.fallback_reasons)
                        if (r.has_value() && *r == reason) {
                            cnt += 1;
                            found = true;
                            break;
                        }
                    if (!found) stats.fallback_reasons.emplace_back(reason, 1);
                    body_lines.push_back("    static {");
                    for (auto& l : fallback_bytecode_listing(cf, *clinit_m, 2)) body_lines.push_back(l);
                    body_lines.push_back("    }");
                    body_lines.push_back("");
                }
            }
        }
    }

    if (is_enum) {
        std::string values_desc = "()[" + own_field_type_desc;
        std::string value_of_desc = "(Ljava/lang/String;)" + own_field_type_desc;
        for (auto& m : cf.methods) {
            if (m.name == "values" && m.descriptor == values_desc) skip_methods.insert(&m);
            else if (m.name == "valueOf" && m.descriptor == value_of_desc) skip_methods.insert(&m);
            else if (m.name == "$values" && (m.access & 0x1000)) skip_methods.insert(&m);
        }
    }

    for (auto& m : cf.methods) {
        if (skip_methods.count(&m)) continue;
        // HANDOFF_50: для record'ов - пропустить компилятором сгенерированные
        // accessor'ы (`Type comp() { return this.comp; }`, точное совпадение
        // байткода) и equals/hashCode/toString, если они делегируют В
        // ObjectMethods.bootstrap НЕИЗМЕНЁННЫМИ (пользователь их не
        // переопределял) - `record Name(...) {}` сам их сгенерирует, повторно
        // печатать не нужно. См. code_is_getfield_return/is_object_methods_delegate
        // выше - при ложноотрицательном срабатывании метод просто печатается
        // как обычно (валидный явный override, не ошибка).
        if (is_record && m.has_code) {
            bool is_accessor_for_component =
                std::any_of(cf.record_components.begin(), cf.record_components.end(), [&](const RecordComponent& rc) {
                    return m.name == rc.name && m.descriptor == "()" + rc.descriptor && code_is_getfield_return(cf, m.code, rc.name);
                });
            if (is_accessor_for_component) continue;
            bool is_generated_object_method = ((m.name == "equals" && m.descriptor == "(Ljava/lang/Object;)Z") ||
                                                (m.name == "hashCode" && m.descriptor == "()I") ||
                                                (m.name == "toString" && m.descriptor == "()Ljava/lang/String;")) &&
                                               is_object_methods_delegate(cf, m);
            if (is_generated_object_method) continue;
        }
        std::string mmods = access_str(m.access, "method");
        std::string ret_disp;
        std::vector<std::string> params_disp;
        try {
            auto [ret, params] = method_descriptor_to_java(m.descriptor);
            ret_disp = format_type_dotted(ret, renamer, known_internal_by_dotted, &all_imports);
            for (auto& p : params) params_disp.push_back(format_type_dotted(p, renamer, known_internal_by_dotted, &all_imports));
        } catch (...) {
            ret_disp = m.descriptor;
            params_disp.clear();
        }
        std::string mname = renamer.friendly_method(internal, m.name, m.descriptor);
        bool is_enum_ctor = is_enum && m.name == "<init>";
        int arg_offset = 0;
        if (m.name == "<init>") {
            mname = simple;
            ret_disp = "";
            if (is_enum_ctor && params_disp.size() >= 2) {
                params_disp.erase(params_disp.begin(), params_disp.begin() + 2);
                arg_offset = 2;
            }
        }
        std::string renamed_note = (mname == m.name) ? "" : ("  // было: " + m.name);

        std::optional<MethodDecompileResult> result;
        if (m.has_code) {
            stats.total_methods += 1;
            result = decompile_method_body(cf, m, renamer, known_internal_by_dotted, internal, 2, enum_ordinals, switchmap_tables);
            stats.junk_catches_removed += result->junk_catches_removed;
        }

        // Имена параметров ДОЛЖНЫ совпадать с теми, что реально использует
        // тело метода (см. HANDOFF_42/engine.hpp) - сигнатура строится ИЗ
        // result.locals, а не заново.
        std::vector<std::string> param_names;
        if (result.has_value() && result->ok) {
            std::vector<std::pair<int, std::string>> param_entries;
            for (auto& [slot, info] : result->locals)
                if (info.is_param) param_entries.emplace_back(slot, info.name);
            std::sort(param_entries.begin(), param_entries.end(), [](auto& a, auto& b) { return a.first < b.first; });
            std::vector<std::string> names;
            for (auto& [slot, name] : param_entries) names.push_back(name);
            if (is_enum_ctor && names.size() >= 2) names.erase(names.begin(), names.begin() + 2);
            if (names.size() == params_disp.size()) param_names = names;
        }
        if (param_names.empty() && !params_disp.empty()) {
            for (size_t i = 0; i < params_disp.size(); ++i) param_names.push_back("arg" + std::to_string(i + static_cast<size_t>(arg_offset)));
        }

        std::string method_type_params;
        bool used_generic_sig = false;
        if (m.signature.has_value()) {
            std::optional<MethodSignature> gsig;
            try {
                gsig = parse_method_signature(*m.signature);
            } catch (...) {
                gsig = std::nullopt;
            }
            if (gsig.has_value() && gsig->param_types.size() == params_disp.size()) {
                params_disp = gsig->param_types;
                if (m.name != "<init>") ret_disp = gsig->return_type;
                method_type_params = gsig->type_params;
                used_generic_sig = true;
            }
        }

        std::vector<std::vector<Annotation>> param_anns = m.param_annotations;
        if (is_enum_ctor && param_anns.size() >= 2) param_anns.erase(param_anns.begin(), param_anns.begin() + 2);
        std::vector<std::string> param_parts;
        for (size_t i = 0; i < params_disp.size() && i < param_names.size(); ++i) {
            std::string prefix;
            if (i < param_anns.size() && !param_anns[i].empty()) {
                for (auto& a : param_anns[i]) prefix += format_annotation(a, renamer, known_internal_by_dotted, all_imports) + " ";
            }
            std::string ptype = used_generic_sig ? params_disp[i] : mark_type(params_disp[i]);
            param_parts.push_back(prefix + ptype + " " + param_names[i]);
        }
        std::string param_str;
        for (size_t i = 0; i < param_parts.size(); ++i) {
            if (i) param_str += ", ";
            param_str += param_parts[i];
        }
        bool has_body = result.has_value();
        std::string sig_end = has_body ? " {" : ";";
        for (auto& a : m.annotations) body_lines.push_back("    " + format_annotation(a, renamer, known_internal_by_dotted, all_imports));
        std::string ret_display = (used_generic_sig && m.name != "<init>") ? ret_disp : mark_type(ret_disp);
        std::string tparams_prefix = method_type_params.empty() ? "" : (method_type_params + " ");
        std::string sig =
            collapse_double_spaces("    " + mmods + " " + tparams_prefix + ret_display + " " + mname + "(" + param_str + ")" + sig_end + renamed_note);
        body_lines.push_back(sig);

        if (result.has_value()) {
            if (result->ok) {
                stats.decompiled_methods += 1;
                std::vector<StmtPtr> out_stmts = result->stmts;
                bool implicit_super_stripped = false;
                if (is_enum_ctor && !out_stmts.empty()) {
                    auto* es = dynamic_cast<ExprStmtNode*>(out_stmts[0].get());
                    if (es != nullptr) {
                        auto* mc = dynamic_cast<MethodCall*>(es->expr.get());
                        if (mc != nullptr && mc->is_ctor) implicit_super_stripped = true;
                    }
                }
                if (implicit_super_stripped) {
                    std::vector<StmtPtr> rest(out_stmts.begin() + 1, out_stmts.end());
                    std::vector<std::string> rendered = result->pre_lines;
                    auto emitted = emit_stmts(rest, 2);
                    rendered.insert(rendered.end(), emitted.begin(), emitted.end());
                    if (rendered.empty()) rendered.push_back("        // (пустое тело)");
                    for (auto& l : rendered) body_lines.push_back(l);
                } else {
                    if (result->java_lines.empty()) {
                        body_lines.push_back("        // (пустое тело)");
                    } else {
                        for (auto& l : result->java_lines) body_lines.push_back(l);
                    }
                }
                for (auto& [d, s] : result->imports.items()) all_imports.set(d, s);
            } else {
                stats.fallback_methods += 1;
                std::string reason = result->reason.has_value() ? *result->reason : std::string();
                bool found = false;
                for (auto& [r, cnt] : stats.fallback_reasons) {
                    bool same = (!r.has_value() && !result->reason.has_value()) || (r.has_value() && result->reason.has_value() && *r == *result->reason);
                    if (same) {
                        cnt += 1;
                        found = true;
                        break;
                    }
                }
                if (!found) stats.fallback_reasons.emplace_back(result->reason, 1);
                for (auto& l : fallback_bytecode_listing(cf, m, 2)) body_lines.push_back(l);
            }
            body_lines.push_back("    }");
        }
        body_lines.push_back("");
    }

    std::string own_dotted = dotted_from_internal(new_internal);

    std::map<std::string, std::vector<std::string>> by_simple;  // simple -> [dotted...] в порядке вставки
    std::vector<std::string> by_simple_order;
    for (auto& [dotted, simple_name] : all_imports.items()) {
        if (!by_simple.count(simple_name)) by_simple_order.push_back(simple_name);
        by_simple[simple_name].push_back(dotted);
    }
    std::unordered_set<std::string> losers;
    for (auto& simple_name : by_simple_order) {
        auto& dotted_list = by_simple[simple_name];
        if (dotted_list.size() > 1) {
            for (size_t i = 1; i < dotted_list.size(); ++i) losers.insert(dotted_list[i]);
        }
    }

    std::vector<std::pair<std::string, std::string>> sorted_imports(all_imports.items().begin(), all_imports.items().end());
    std::sort(sorted_imports.begin(), sorted_imports.end());
    std::vector<std::string> import_lines;
    for (auto& [dotted, simple_name] : sorted_imports) {
        (void)simple_name;
        if (dotted.rfind("java.lang.", 0) == 0 && dotted.substr(10).find('.') == std::string::npos) continue;
        if (dotted == own_dotted) continue;
        if (dotted.find('.') == std::string::npos) continue;
        if (losers.count(dotted)) continue;
        import_lines.push_back("import " + dotted + ";");
    }

    lines.push_back("");
    if (!import_lines.empty()) {
        for (auto& l : import_lines) lines.push_back(l);
        lines.push_back("");
    }
    for (auto& l : class_annotation_lines) lines.push_back(l);
    lines.push_back(header);
    lines.push_back("");
    for (auto& l : body_lines) lines.push_back(l);
    lines.push_back("}");

    std::string joined;
    for (size_t i = 0; i < lines.size(); ++i) {
        if (i) joined += "\n";
        joined += lines[i];
    }
    std::string text = resolve_type_markers(joined, losers);
    return {text, all_imports};
}

}  // namespace nd
