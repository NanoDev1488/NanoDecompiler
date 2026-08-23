// stackvm.cpp - см. stackvm.hpp. 1:1 порт stackvm.py.
#include "stackvm.hpp"

#include <algorithm>
#include <charconv>
#include <cmath>
#include <cstdio>
#include <functional>
#include <sstream>

#include "javatypes.hpp"
#include "str_decrypt.hpp"

namespace nd {

const std::set<std::string> PSEUDO_TYPES = {"null", "this"};

// ---------------- базовые хелперы ----------------

int width_of(const std::string& t) { return (t == "long" || t == "double") ? 2 : 1; }

char cat_of(const std::string& t) {
    if (t == "int" || t == "short" || t == "byte" || t == "char" || t == "boolean") return 'I';
    if (t == "long") return 'L';
    if (t == "float") return 'F';
    if (t == "double") return 'D';
    return 'A';
}

std::string default_type_for_cat(char cat) {
    switch (cat) {
        case 'I': return "int";
        case 'L': return "long";
        case 'F': return "float";
        case 'D': return "double";
        default: return "Object";
    }
}

std::string java_string_literal(const std::string& s) {
    std::string out = "\"";
    // Итерируем по БАЙТАМ UTF-8 (не по кодпоинтам) - как и Python-оригинал
    // итерирует по СИМВОЛАМ (кодпоинтам); для ASCII-подмножества (кавычка,
    // бэкслеш, управляющие символы) поведение идентично побайтово, т.к. эти
    // символы всегда однобайтовые в UTF-8; многобайтовые кодпоинты просто
    // копируются как есть (не escape'up.  чены) - совпадает с Python, который
    // тоже не эскейпит печатные не-ASCII символы.
    for (unsigned char c : s) {
        if (c == '"') {
            out += "\\\"";
        } else if (c == '\\') {
            out += "\\\\";
        } else if (c == '\n') {
            out += "\\n";
        } else if (c == '\t') {
            out += "\\t";
        } else if (c == '\r') {
            out += "\\r";
        } else if (c < 0x20 || c == 0x7f) {
            char buf[8];
            std::snprintf(buf, sizeof(buf), "\\u%04x", c);
            out += buf;
        } else {
            out += static_cast<char>(c);
        }
    }
    out += "\"";
    return out;
}

namespace {
// Аналог Python repr(float): кратчайшая десятичная запись, однозначно
// round-trip'ящаяся обратно в то же double, с ТЕМ ЖЕ правилом выбора
// фиксированной/научной нотации, что использует CPython (`float_repr` /
// `PyOS_double_to_string` с кодом формата 'r', режим 0 - "shortest").
// std::to_chars(..., chars_format::scientific) без явной точности (C++17,
// доступно в libstdc++ GCC>=11) уже даёт КРАТЧАЙШУЮ round-trip мантиссу -
// остаётся только воспроизвести правило Python, КОГДА переключаться на
// экспоненциальную запись (порог откалиброван прямым сравнением с CPython
// на широком наборе значений - см. HANDOFF_36).
std::string python_repr_double(double v) {
    if (v == 0.0) {
        return std::signbit(v) ? "-0.0" : "0.0";
    }
    char buf[64];
    auto res = std::to_chars(buf, buf + sizeof(buf), v, std::chars_format::scientific);
    std::string sci(buf, res.ptr);  // напр. "1.23456e+02" или "-1e+02" или "1e-05"

    bool neg = false;
    size_t pos = 0;
    if (sci[0] == '-') { neg = true; pos = 1; }
    std::string digits;
    digits += sci[pos];
    pos += 1;
    if (pos < sci.size() && sci[pos] == '.') {
        pos += 1;
        while (pos < sci.size() && sci[pos] != 'e') { digits += sci[pos]; pos += 1; }
    }
    // пропускаем 'e'
    pos = sci.find('e', pos);
    int exp = std::stoi(sci.substr(pos + 1));  // sci[pos+1] включает знак (+/-)
    int decpt = exp + 1;  // value = 0.digits * 10^decpt (Python dtoa-стиль)

    std::string out;
    if (neg) out += "-";

    // Порог Python: fixed-нотация при -4 < decpt <= 16, иначе экспоненциальная.
    if (decpt > -4 && decpt <= 16) {
        if (decpt <= 0) {
            out += "0.";
            for (int i = 0; i < -decpt; ++i) out += "0";
            out += digits;
        } else if (static_cast<size_t>(decpt) >= digits.size()) {
            out += digits;
            for (size_t i = digits.size(); i < static_cast<size_t>(decpt); ++i) out += "0";
            out += ".0";
        } else {
            out += digits.substr(0, decpt);
            out += ".";
            out += digits.substr(decpt);
        }
    } else {
        out += digits.substr(0, 1);
        if (digits.size() > 1) {
            out += ".";
            out += digits.substr(1);
        }
        int e = decpt - 1;
        out += "e";
        out += (e >= 0 ? "+" : "-");
        int ae = std::abs(e);
        if (ae < 10) out += "0";
        out += std::to_string(ae);
    }
    return out;
}
}  // namespace

std::string java_float_literal(double v, const std::string& suffix) {
    if (std::isnan(v)) return (suffix == "f" ? std::string("Float") : std::string("Double")) + ".NaN";
    if (std::isinf(v)) {
        std::string cls = (suffix == "f") ? "Float" : "Double";
        return cls + "." + (v > 0 ? "POSITIVE_INFINITY" : "NEGATIVE_INFINITY");
    }
    std::string s = python_repr_double(v);
    if (s.find('e') != std::string::npos || s.find('E') != std::string::npos || s.find('.') != std::string::npos) {
        return s + suffix;
    }
    return s + ".0" + suffix;
}

std::string char_literal(int codepoint) {
    int cp = codepoint & 0xFFFF;
    if (cp == static_cast<int>('\'')) return "'\\''";
    if (cp == static_cast<int>('\\')) return "'\\\\'";
    if (cp == '\n') return "'\\n'";
    if (cp == '\t') return "'\\t'";
    if (cp == '\r') return "'\\r'";
    if (cp < 0x20 || cp == 0x7f) {
        char buf[16];
        std::snprintf(buf, sizeof(buf), "'\\u%04x'", cp);
        return buf;
    }
    // codepoint как символ - для BMP-символов (наш случай, cp маскирован 0xFFFF)
    // однобайтовые ASCII печатаются напрямую; не-ASCII BMP-символы кодируем в
    // UTF-8 (совпадает с тем, как Python печатает `f"'{ch}'"` для строки с
    // одним Unicode-символом - тот же символ, просто в другой кодировке байт
    // на выходе, что и ожидается для текстового Java-файла в UTF-8).
    std::string out = "'";
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
    out += "'";
    return out;
}

// ==================== хелперы уровня инструкций/выражений ====================

namespace {

// Дети выражения, которые Python-оригинал ПРОВЕРЯЕТ в _has_side_effect (через
// getattr по фиксированному списку имён атрибутов + отдельно "args"). ВАЖНО:
// это НЕ "все дочерние выражения узла" - у NewArray (dims/initializer) и
// Lambda (params/body_method_ref) в этом списке атрибутов НЕТ, значит
// side-effect'ы внутри них Python-оригиналом НЕ обнаруживаются (для Lambda
// это семантически верно - тело выполняется не при создании; для NewArray
// dims - это, похоже, недосмотр оригинала) - сохранено как есть, см. HANDOFF_36.
std::vector<ExprPtr> side_effect_children(const ExprPtr& e) {
    std::vector<ExprPtr> out;
    switch (e->kind) {
        case ExprKind::FieldAccess: {
            auto* f = static_cast<FieldAccess*>(e.get());
            if (f->target) out.push_back(f->target);
            break;
        }
        case ExprKind::ArrayAccess: {
            auto* a = static_cast<ArrayAccess*>(e.get());
            if (a->array) out.push_back(a->array);
            if (a->index) out.push_back(a->index);
            break;
        }
        case ExprKind::MethodCall: {
            auto* m = static_cast<MethodCall*>(e.get());
            if (m->target) out.push_back(m->target);
            for (auto& a : m->args) out.push_back(a);
            break;
        }
        case ExprKind::NewObject: {
            auto* n = static_cast<NewObject*>(e.get());
            for (auto& a : n->args) out.push_back(a);
            break;
        }
        case ExprKind::Cast: {
            auto* c = static_cast<Cast*>(e.get());
            if (c->expr) out.push_back(c->expr);
            break;
        }
        case ExprKind::InstanceOf: {
            auto* io = static_cast<InstanceOf*>(e.get());
            if (io->expr) out.push_back(io->expr);
            break;
        }
        case ExprKind::BinOp: {
            auto* b = static_cast<BinOp*>(e.get());
            if (b->left) out.push_back(b->left);
            if (b->right) out.push_back(b->right);
            break;
        }
        case ExprKind::UnOp: {
            auto* u = static_cast<UnOp*>(e.get());
            if (u->expr) out.push_back(u->expr);
            break;
        }
        case ExprKind::Ternary: {
            auto* t = static_cast<Ternary*>(e.get());
            if (t->cond) out.push_back(t->cond);
            if (t->tval) out.push_back(t->tval);
            if (t->fval) out.push_back(t->fval);
            break;
        }
        default:
            break;
    }
    return out;
}

bool has_side_effect(const ExprPtr& e) {
    if (!e) return false;
    if (e->kind == ExprKind::MethodCall || e->kind == ExprKind::NewObject || e->kind == ExprKind::Assign) return true;
    if (e->kind == ExprKind::UnOp) {
        auto* u = static_cast<UnOp*>(e.get());
        if (u->op == "++" || u->op == "--") return true;
    }
    for (auto& child : side_effect_children(e)) {
        if (has_side_effect(child)) return true;
    }
    return false;
}

ExprPtr materialize_if_shared(const ExprPtr& val, std::vector<ExprPtr>& stack, MethodCtx& ctx, std::vector<StmtPtr>& stmts) {
    if (val->kind == ExprKind::Local || val->kind == ExprKind::This) return val;
    bool referenced = false;
    for (auto& s : stack) {
        if (s.get() == val.get()) { referenced = true; break; }
    }
    if (!referenced) return val;
    std::string typ = val->type.empty() ? "Object" : val->type;
    if (typ == "null" || typ == "this") typ = "Object";
    char cat = (typ == "int" || typ == "long" || typ == "float" || typ == "double") ? cat_of(typ) : 'A';
    std::string name = ctx.new_temp(cat);
    stmts.push_back(std::make_shared<LocalDecl>(typ, name, val));
    ExprPtr target = std::make_shared<Local>(name, typ);
    for (auto& s : stack) {
        if (s.get() == val.get()) s = target;
    }
    return target;
}

bool is_load_of(const Instruction& ins, int slot);
int64_t load_slot_static(const Instruction& ins, const std::string& mn);

const std::set<std::string> ILOAD_ALL = {"iload", "iload_0", "iload_1", "iload_2", "iload_3"};
const std::set<std::string> LLOAD_ALL = {"lload", "lload_0", "lload_1", "lload_2", "lload_3"};
const std::set<std::string> FLOAD_ALL = {"fload", "fload_0", "fload_1", "fload_2", "fload_3"};
const std::set<std::string> DLOAD_ALL = {"dload", "dload_0", "dload_1", "dload_2", "dload_3"};
const std::set<std::string> ALOAD_ALL = {"aload", "aload_0", "aload_1", "aload_2", "aload_3"};
const std::set<std::string> ISTORE_ALL = {"istore", "istore_0", "istore_1", "istore_2", "istore_3"};
const std::set<std::string> LSTORE_ALL = {"lstore", "lstore_0", "lstore_1", "lstore_2", "lstore_3"};
const std::set<std::string> FSTORE_ALL = {"fstore", "fstore_0", "fstore_1", "fstore_2", "fstore_3"};
const std::set<std::string> DSTORE_ALL = {"dstore", "dstore_0", "dstore_1", "dstore_2", "dstore_3"};
const std::set<std::string> ASTORE_ALL = {"astore", "astore_0", "astore_1", "astore_2", "astore_3"};

int64_t load_slot_static(const Instruction& ins, const std::string& mn) {
    if (mn == "iload" || mn == "lload" || mn == "fload" || mn == "dload" || mn == "aload") {
        return *ins.ival;
    }
    auto pos = mn.find_last_of('_');
    return std::stoll(mn.substr(pos + 1));
}

bool is_load_of(const Instruction& ins, int slot) {
    return ILOAD_ALL.count(ins.mnemonic) && load_slot_static(ins, ins.mnemonic) == slot;
}

int64_t store_slot(const Instruction& ins, const std::string& mn) {
    if (mn == "istore" || mn == "lstore" || mn == "fstore" || mn == "dstore" || mn == "astore") {
        return *ins.ival;
    }
    auto pos = mn.find_last_of('_');
    return std::stoll(mn.substr(pos + 1));
}

const std::map<std::string, std::pair<std::string, std::string>> BINOPS = {
    {"iadd", {"+", "int"}}, {"ladd", {"+", "long"}}, {"fadd", {"+", "float"}}, {"dadd", {"+", "double"}},
    {"isub", {"-", "int"}}, {"lsub", {"-", "long"}}, {"fsub", {"-", "float"}}, {"dsub", {"-", "double"}},
    {"imul", {"*", "int"}}, {"lmul", {"*", "long"}}, {"fmul", {"*", "float"}}, {"dmul", {"*", "double"}},
    {"idiv", {"/", "int"}}, {"ldiv", {"/", "long"}}, {"fdiv", {"/", "float"}}, {"ddiv", {"/", "double"}},
    {"irem", {"%", "int"}}, {"lrem", {"%", "long"}}, {"frem", {"%", "float"}}, {"drem", {"%", "double"}},
    {"ishl", {"<<", "int"}}, {"lshl", {"<<", "long"}},
    {"ishr", {">>", "int"}}, {"lshr", {">>", "long"}},
    {"iushr", {">>>", "int"}}, {"lushr", {">>>", "long"}},
    {"iand", {"&", "int"}}, {"land", {"&", "long"}},
    {"ior", {"|", "int"}}, {"lor", {"|", "long"}},
    {"ixor", {"^", "int"}}, {"lxor", {"^", "long"}},
};
const std::map<std::string, std::string> NEGOPS = {
    {"ineg", "int"}, {"lneg", "long"}, {"fneg", "float"}, {"dneg", "double"},
};
const std::map<std::string, std::string> CASTS = {
    {"i2l", "long"}, {"i2f", "float"}, {"i2d", "double"},
    {"l2i", "int"}, {"l2f", "float"}, {"l2d", "double"},
    {"f2i", "int"}, {"f2l", "long"}, {"f2d", "double"},
    {"d2i", "int"}, {"d2l", "long"}, {"d2f", "float"},
    {"i2b", "byte"}, {"i2c", "char"}, {"i2s", "short"},
};

ExprPtr fold_compare(const ExprPtr& v, const std::string& cmpop) {
    if (v->kind == ExprKind::BinOp) {
        auto* b = static_cast<BinOp*>(v.get());
        if (b->op == "cmp") return std::make_shared<BinOp>(cmpop, b->left, b->right, "boolean");
    }
    if (v->type == "boolean") {
        if (cmpop == "==") return std::make_shared<UnOp>("!", v, "boolean");
        if (cmpop == "!=") return v;
    }
    ExprPtr zero = std::make_shared<Const>("0", "int");
    return std::make_shared<BinOp>(cmpop, v, zero, "boolean");
}

StmtPtr assign_stmt(const ExprPtr& target, const ExprPtr& value) {
    return std::make_shared<ExprStmtNode>(std::make_shared<Assign>(target, value));
}

std::string refine_type(const std::string& current, const ExprPtr& value_expr) {
    const std::string& t = value_expr->type;
    if (!t.empty() && !PSEUDO_TYPES.count(t) &&
        (current == "Object" || current == "int" || current == "long" || current == "float" || current == "double")) {
        return t;
    }
    return current;
}

const std::map<std::string, std::string> PRIMITIVE_WRAPPERS = {
    {"int", "Integer"}, {"long", "Long"}, {"float", "Float"}, {"double", "Double"},
    {"boolean", "Boolean"}, {"char", "Character"}, {"byte", "Byte"}, {"short", "Short"},
};

}  // namespace

ExprPtr coerce_arg(const ExprPtr& expr, const std::string& expected_type) {
    if (expr->kind == ExprKind::Const) {
        auto* c = static_cast<Const*>(expr.get());
        if (c->type == "int") {
            if (expected_type == "boolean" && (c->literal == "0" || c->literal == "1")) {
                return std::make_shared<Const>(c->literal == "0" ? "false" : "true", "boolean");
            }
            if (expected_type == "char") {
                try {
                    int v = std::stoi(c->literal);
                    return std::make_shared<Const>(char_literal(v), "char");
                } catch (...) {
                    return expr;
                }
            }
        }
    }
    const std::string& expr_type = expr->type;
    if ((expr_type == "Object" || expr_type == "java.lang.Object") &&
        expected_type != "" && expected_type != "Object" && expected_type != "java.lang.Object" && expected_type != "void") {
        auto it = PRIMITIVE_WRAPPERS.find(expected_type);
        std::string target = (it != PRIMITIVE_WRAPPERS.end()) ? it->second : expected_type;
        return std::make_shared<Cast>(target, expr);
    }
    return expr;
}

namespace {

void substitute(std::vector<ExprPtr>& stack, const ExprPtr& old, const ExprPtr& new_) {
    for (auto& s : stack) {
        if (s.get() == old.get()) s = new_;
    }
}

std::string array_type_str(const std::string& internal, MethodCtx& ctx) {
    int depth = 0;
    std::string s = internal;
    while (!s.empty() && s[0] == '[') {
        depth += 1;
        s = s.substr(1);
    }
    std::string base;
    if (!s.empty() && s[0] == 'L' && s.back() == ';') {
        base = ctx.owner_display(s.substr(1, s.size() - 2));
    } else {
        base = field_descriptor_to_java(s);
    }
    std::string suffix;
    for (int i = 0; i < depth; ++i) suffix += "[]";
    return base + suffix;
}

}  // namespace

// ==================== invokedynamic: string-concat / лямбды ====================

namespace {

ExprPtr cp_const_simple(const ClassFile& cf, uint16_t idx) {
    auto it = cf.pool.find(idx);
    if (it == cf.pool.end()) throw DecompileAbort("bad constant-arg cp index");
    const CpEntry& e = it->second;
    switch (e.tag) {
        case CpTag::Integer: return std::make_shared<Const>(std::to_string(e.int_value), "int");
        case CpTag::Float: return std::make_shared<Const>(java_float_literal(e.float_value, "f"), "float");
        case CpTag::Long: return std::make_shared<Const>(std::to_string(e.int_value) + "L", "long");
        case CpTag::Double: return std::make_shared<Const>(java_float_literal(e.float_value, ""), "double");
        case CpTag::String: {
            const std::string* s = cf.utf8(e.idx1);
            std::string sv = s ? *s : "";
            return std::make_shared<Const>(java_string_literal(sv), "String", sv);
        }
        default:
            throw DecompileAbort("unsupported const-arg tag " + cp_tag_name(e.tag));
    }
}

ExprPtr build_string_concat(const ClassFile& cf, const std::vector<uint16_t>& bsm_args, const std::vector<ExprPtr>& call_args, MethodCtx& ctx) {
    (void)ctx;
    std::optional<std::string> recipe;
    std::vector<ExprPtr> const_args;
    if (!bsm_args.empty()) {
        auto fit = cf.pool.find(bsm_args[0]);
        if (fit != cf.pool.end() && fit->second.tag == CpTag::String) {
            const std::string* r = cf.utf8(fit->second.idx1);
            if (r) recipe = *r;
            for (size_t k = 1; k < bsm_args.size(); ++k) const_args.push_back(cp_const_simple(cf, bsm_args[k]));
        }
    }
    std::vector<ExprPtr> parts;
    if (recipe.has_value()) {
        size_t dyn_i = 0, const_i = 0;
        std::string buf;
        for (char ch : *recipe) {
            if (ch == '\x01') {
                if (!buf.empty()) { parts.push_back(std::make_shared<Const>(java_string_literal(buf), "String")); buf.clear(); }
                parts.push_back(call_args.at(dyn_i)); dyn_i += 1;
            } else if (ch == '\x02') {
                if (!buf.empty()) { parts.push_back(std::make_shared<Const>(java_string_literal(buf), "String")); buf.clear(); }
                parts.push_back(const_args.at(const_i)); const_i += 1;
            } else {
                buf += ch;
            }
        }
        if (!buf.empty()) parts.push_back(std::make_shared<Const>(java_string_literal(buf), "String"));
    } else {
        parts = call_args;
    }
    if (parts.empty()) return std::make_shared<Const>("\"\"", "String");
    ExprPtr result = parts[0];
    for (size_t k = 1; k < parts.size(); ++k) result = std::make_shared<BinOp>("+", result, parts[k], "String");
    result->type = "String";  // мутирует ТОТ ЖЕ узел, что и parts[0], если parts.size()==1 - как в оригинале
    return result;
}

const std::set<int> MH_KIND_STATIC = {6, 8};
const std::set<int> MH_KIND_VIRTUAL = {5, 9};
const std::set<int> MH_KIND_SPECIAL = {7};
const std::set<int> MH_KIND_NEW = {8};

ExprPtr build_lambda(const ClassFile& cf, const std::vector<uint16_t>& bsm_args, const std::vector<ExprPtr>& captured,
                      const std::string& indy_name, const std::string& functional_type_desc, MethodCtx& ctx) {
    (void)indy_name;
    if (bsm_args.size() < 3) throw DecompileAbort("некорректные аргументы LambdaMetafactory");
    auto sam_it = cf.pool.find(bsm_args[0]);
    uint16_t impl_mh_idx = bsm_args[1];
    if (sam_it == cf.pool.end() || sam_it->second.tag != CpTag::MethodType) throw DecompileAbort("bad SAM method type");
    const std::string* sam_desc_p = cf.utf8(sam_it->second.idx1);
    if (!sam_desc_p) throw DecompileAbort("bad SAM descriptor");
    std::string sam_ret;
    std::vector<std::string> sam_params;
    try {
        auto [r, p] = method_descriptor_to_java(*sam_desc_p);
        sam_ret = r;
        sam_params = p;
    } catch (...) {
        throw DecompileAbort("bad SAM descriptor");
    }
    (void)sam_ret;
    auto mh = cf.method_handle_ref(impl_mh_idx);
    if (!mh.has_value()) throw DecompileAbort("bad lambda impl method handle");
    auto [kind, impl_owner, impl_name, impl_desc] = *mh;

    std::vector<ExprPtr> lam_params;
    for (size_t k = 0; k < sam_params.size(); ++k) {
        lam_params.push_back(std::make_shared<Local>("lp" + std::to_string(k), ctx.map_type(sam_params[k])));
    }

    std::string impl_owner_disp = ctx.owner_display(impl_owner);
    std::string impl_mname = ctx.method_name(impl_owner, impl_name, impl_desc);
    std::optional<std::vector<std::string>> impl_params;
    try {
        auto [ir, ip] = method_descriptor_to_java(impl_desc);
        (void)ir;
        impl_params = ip;
    } catch (...) {
        impl_params = std::nullopt;
    }

    auto coerce_seq = [&](const std::vector<ExprPtr>& seq, const std::optional<std::vector<std::string>>& param_types) -> std::vector<ExprPtr> {
        if (!param_types.has_value() || param_types->size() != seq.size()) return seq;
        std::vector<ExprPtr> out;
        for (size_t k = 0; k < seq.size(); ++k) out.push_back(coerce_arg(seq[k], ctx.map_type((*param_types)[k])));
        return out;
    };

    ExprPtr call;
    if (MH_KIND_NEW.count(kind)) {
        std::vector<ExprPtr> args = captured;
        args.insert(args.end(), lam_params.begin(), lam_params.end());
        call = std::make_shared<NewObject>(impl_owner_disp, coerce_seq(args, impl_params));
    } else if (MH_KIND_STATIC.count(kind)) {
        std::vector<ExprPtr> args = captured;
        args.insert(args.end(), lam_params.begin(), lam_params.end());
        call = std::make_shared<MethodCall>(nullptr, impl_mname, coerce_seq(args, impl_params), "Object", true,
                                             std::optional<std::string>(impl_owner_disp));
    } else if (MH_KIND_VIRTUAL.count(kind) || MH_KIND_SPECIAL.count(kind)) {
        ExprPtr recv;
        std::vector<ExprPtr> rest;
        if (!captured.empty()) {
            recv = captured[0];
            rest.assign(captured.begin() + 1, captured.end());
            rest.insert(rest.end(), lam_params.begin(), lam_params.end());
        } else if (!lam_params.empty()) {
            recv = lam_params[0];
            rest.assign(lam_params.begin() + 1, lam_params.end());
        } else {
            throw DecompileAbort("не удалось определить получателя для лямбды");
        }
        if ((recv->type == "Object" || recv->type == "java.lang.Object") && impl_owner_disp != "" &&
            impl_owner_disp != "Object" && impl_owner_disp != "java.lang.Object") {
            recv = std::make_shared<Cast>(impl_owner_disp, recv);
        }
        call = std::make_shared<MethodCall>(recv, impl_mname, coerce_seq(rest, impl_params), "Object", false,
                                             std::optional<std::string>(impl_owner_disp));
    } else {
        throw DecompileAbort("неизвестный kind method handle: " + std::to_string(kind));
    }

    return std::make_shared<Lambda>(lam_params, call, functional_type_desc.empty() ? "Object" : functional_type_desc);
}

// HANDOFF_49: ObjectMethods.bootstrap - стандартный (JEP 384/395, JDK 16+)
// механизм генерации equals/hashCode/toString для record-классов. В отличие
// от LambdaMetafactory/StringConcatFactory формат СТРОГО специфицирован:
// bsm_args[0] - Class сам record, bsm_args[1] - String "field1;field2;..."
// (имена компонентов через ';', пусто для record без компонентов),
// bsm_args[2..] - MethodHandle-геттеры компонентов (getfield), по одному на
// каждое имя, в ТОМ ЖЕ порядке. call_args - (this) для hashCode/toString,
// (this, other) для equals.
//
// СОЗНАТЕЛЬНОЕ РЕШЕНИЕ: генерируем ЧИТАЕМЫЙ идиоматичный Java-код
// (`Objects.hash(...)`/`Objects.equals(...)`/конкатенация в toString), а НЕ
// пытаемся побитово воспроизвести внутренний алгоритм комбинирования JDK -
// hashCode() у record'ов НЕ специфицирован точным значением ни в JLS, ни в
// JEP (implementation detail), так что byte-perfect копия не нужна и не
// возможна в общем случае - а вот итоговый equals()/hashCode() ОБЯЗАН
// соблюдать контракт (совпадающие объекты дают совпадающий hashCode) -
// наша версия этому соответствует. Известное упрощение: для полей-массивов
// используется Objects.equals (ссылочное сравнение), а НЕ Arrays.equals -
// настоящие record'ы для массивов используют глубокое сравнение - крайне
// редкий случай для record'ов на практике (антипаттерн из-за проблем с
// equals у массивов вообще), не стали усложнять.
ExprPtr build_object_methods(const ClassFile& cf, const std::vector<uint16_t>& bsm_args, const std::vector<ExprPtr>& call_args,
                              const std::string& indy_name, MethodCtx& ctx) {
    if (bsm_args.size() < 2) throw DecompileAbort("некорректные аргументы ObjectMethods.bootstrap");
    auto names_it = cf.pool.find(bsm_args[1]);
    if (names_it == cf.pool.end() || names_it->second.tag != CpTag::String) throw DecompileAbort("bad ObjectMethods names arg");
    const std::string* names_str = cf.utf8(names_it->second.idx1);
    if (!names_str) throw DecompileAbort("bad ObjectMethods names arg");

    std::vector<std::string> field_names_raw;
    if (!names_str->empty()) {
        std::string cur;
        for (char c : *names_str) {
            if (c == ';') {
                field_names_raw.push_back(cur);
                cur.clear();
            } else {
                cur += c;
            }
        }
        field_names_raw.push_back(cur);
    }

    size_t n_fields = field_names_raw.size();
    if (bsm_args.size() < 2 + n_fields) throw DecompileAbort("несоответствие числа геттеров и полей в ObjectMethods.bootstrap");

    struct FieldInfo {
        std::string display_name;
        std::string java_type;
        bool is_primitive;
    };
    std::vector<FieldInfo> fields;
    for (size_t i = 0; i < n_fields; ++i) {
        auto mh = cf.method_handle_ref(bsm_args[2 + i]);
        if (!mh.has_value()) throw DecompileAbort("bad ObjectMethods field accessor handle");
        auto [kind, owner, name, desc] = *mh;
        (void)kind;
        (void)owner;
        std::string jtype;
        bool is_prim = false;
        try {
            jtype = field_descriptor_to_java(desc);
            is_prim = (jtype == "int" || jtype == "long" || jtype == "short" || jtype == "byte" || jtype == "char" ||
                       jtype == "boolean" || jtype == "float" || jtype == "double");
        } catch (...) {
            jtype = "Object";
        }
        fields.push_back({ctx.field_name(cf.this_class_name, name, desc), jtype, is_prim});
    }

    std::string class_display = ctx.owner_display(cf.this_class_name);

    if (indy_name == "toString") {
        if (call_args.empty()) throw DecompileAbort("ObjectMethods.toString: нет receiver'а");
        ExprPtr recv = call_args[0];
        ExprPtr result = std::make_shared<Const>(java_string_literal(class_display + "["), "String");
        for (size_t i = 0; i < fields.size(); ++i) {
            std::string sep = (i == 0 ? "" : ", ") + fields[i].display_name + "=";
            result = std::make_shared<BinOp>("+", result, std::make_shared<Const>(java_string_literal(sep), "String"), "String");
            result = std::make_shared<BinOp>("+", result, std::make_shared<FieldAccess>(recv, fields[i].display_name, fields[i].java_type),
                                              "String");
        }
        return std::make_shared<BinOp>("+", result, std::make_shared<Const>(java_string_literal("]"), "String"), "String");
    }

    if (indy_name == "hashCode") {
        if (call_args.empty()) throw DecompileAbort("ObjectMethods.hashCode: нет receiver'а");
        if (fields.empty()) return std::make_shared<Const>("0", "int");
        ExprPtr recv = call_args[0];
        std::vector<ExprPtr> args;
        for (auto& f : fields) args.push_back(std::make_shared<FieldAccess>(recv, f.display_name, f.java_type));
        return std::make_shared<MethodCall>(nullptr, "hash", args, "int", true, std::optional<std::string>("java.util.Objects"));
    }

    if (indy_name == "equals") {
        if (call_args.size() < 2) throw DecompileAbort("ObjectMethods.equals: ожидались 2 аргумента (this, other)");
        ExprPtr this_expr = call_args[0];
        ExprPtr other_expr = call_args[1];
        ExprPtr cond = std::make_shared<InstanceOf>(other_expr, class_display);
        for (auto& f : fields) {
            ExprPtr left = std::make_shared<FieldAccess>(this_expr, f.display_name, f.java_type);
            ExprPtr right = std::make_shared<FieldAccess>(std::make_shared<Cast>(class_display, other_expr), f.display_name, f.java_type);
            ExprPtr eq;
            if (f.is_primitive) {
                eq = std::make_shared<BinOp>("==", left, right, "boolean");
            } else {
                std::vector<ExprPtr> eq_args = {left, right};
                eq = std::make_shared<MethodCall>(nullptr, "equals", eq_args, "boolean", true, std::optional<std::string>("java.util.Objects"));
            }
            cond = std::make_shared<BinOp>("&&", cond, eq, "boolean");
        }
        return cond;
    }

    throw DecompileAbort("неизвестное имя метода ObjectMethods.bootstrap: " + indy_name);
}

ExprPtr handle_invokedynamic(const ClassFile& cf, const Instruction& ins, MethodCtx& ctx,
                              const std::function<std::vector<ExprPtr>(size_t)>& pop_n) {
    auto it = cf.pool.find(static_cast<uint16_t>(*ins.cp_index));
    if (it == cf.pool.end() || it->second.tag != CpTag::InvokeDynamic) throw DecompileAbort("bad invokedynamic cp entry");
    uint16_t bsm_idx = it->second.idx1;
    uint16_t nt_idx = it->second.idx2;
    if (bsm_idx >= cf.bootstrap_methods.size()) throw DecompileAbort("bootstrap method index out of range");
    const BootstrapMethod& bm = cf.bootstrap_methods[bsm_idx];
    auto mh = cf.method_handle_ref(bm.method_handle_idx);
    if (!mh.has_value()) throw DecompileAbort("bad bootstrap method handle");
    auto [kind, bsm_owner, bsm_name, bsm_desc] = *mh;
    (void)kind; (void)bsm_desc;
    auto nt = cf.name_and_type(nt_idx);
    if (!nt.has_value()) throw DecompileAbort("bad invokedynamic NameAndType");
    auto [indy_name, indy_desc] = *nt;
    std::string indy_ret;
    std::vector<std::string> indy_params;
    try {
        auto [r, p] = method_descriptor_to_java(indy_desc);
        indy_ret = r;
        indy_params = p;
    } catch (...) {
        throw DecompileAbort("bad invokedynamic descriptor");
    }
    std::vector<ExprPtr> call_args = pop_n(indy_params.size());

    if (bsm_owner == "java/lang/invoke/StringConcatFactory") {
        return build_string_concat(cf, bm.args, call_args, ctx);
    }
    if (bsm_owner == "java/lang/invoke/LambdaMetafactory") {
        return build_lambda(cf, bm.args, call_args, indy_name, indy_ret, ctx);
    }
    if (bsm_owner == "java/lang/runtime/ObjectMethods" && bsm_name == "bootstrap") {
        return build_object_methods(cf, bm.args, call_args, indy_name, ctx);
    }
    throw DecompileAbort("неподдерживаемый invokedynamic bootstrap: " + bsm_owner + "." + bsm_name);
}

}  // namespace

// ---------------- MethodCtx ----------------

MethodCtx::MethodCtx(const ClassFile& cf_, const Method& method_, const IRenamer& renamer_,
                      const std::map<std::string, std::string>& known_internal_by_dotted, const std::string& class_internal_)
    : cf(cf_), method(method_), renamer(renamer_), known(known_internal_by_dotted), class_internal(class_internal_) {
    lvt_by_slot_ = build_lvt_names();
    used_local_names_.insert("this");
    init_params();
}

std::map<int, std::string> MethodCtx::build_lvt_names() const {
    std::vector<LocalVarEntry> sorted_lvt = method.local_var_table;
    std::stable_sort(sorted_lvt.begin(), sorted_lvt.end(),
                      [](const LocalVarEntry& a, const LocalVarEntry& b) { return a.start_pc < b.start_pc; });
    std::map<int, std::string> by_slot;
    for (auto& e : sorted_lvt) {
        if (!is_safe_local_name(e.name)) continue;
        if (looks_obfuscated(e.name, "field")) continue;
        if (!by_slot.count(e.slot)) by_slot[e.slot] = e.name;
    }
    return by_slot;
}

std::optional<std::string> MethodCtx::lvt_name_for(int slot) {
    auto it = lvt_by_slot_.find(slot);
    if (it == lvt_by_slot_.end()) return std::nullopt;
    if (used_local_names_.count(it->second)) return std::nullopt;
    used_local_names_.insert(it->second);
    return it->second;
}

void MethodCtx::init_params() {
    bool is_static = (method.access & 0x0008) != 0;
    int slot = 0;
    if (!is_static) {
        LocalInfo info;
        info.name = "this";
        std::string dotted = class_internal;
        std::replace(dotted.begin(), dotted.end(), '/', '.');
        info.type = map_type(dotted);
        info.category = 'A';
        locals[0] = info;
        slot = 1;
    }
    std::string ret;
    std::vector<std::string> params;
    try {
        auto [r, p] = method_descriptor_to_java(method.descriptor);
        ret = r;
        params = p;
    } catch (...) {
        ret = "void";
        params = {};
    }
    ret_type = ret;
    for (size_t i = 0; i < params.size(); ++i) {
        const std::string& p = params[i];
        bool is_array = p.size() >= 2 && p.substr(p.size() - 2) == "[]";
        char cat = is_array ? 'A' : cat_of(p);
        auto lvt = lvt_name_for(slot);
        std::string name = lvt.has_value() ? *lvt : ("arg" + std::to_string(i));
        LocalInfo info;
        info.name = name;
        info.type = map_type(p);
        info.category = cat;
        info.is_param = true;
        locals[slot] = info;
        slot += is_array ? 1 : width_of(p);
    }
}

std::string MethodCtx::map_type(const std::string& java_type) {
    std::string base = java_type;
    std::string arr;
    while (base.size() >= 2 && base.substr(base.size() - 2) == "[]") {
        arr += "[]";
        base = base.substr(0, base.size() - 2);
    }
    auto it = known.find(base);
    if (it != known.end()) {
        std::string internal = it->second;
        std::string new_internal = renamer.friendly_class(internal);
        std::string dotted = new_internal;
        std::replace(dotted.begin(), dotted.end(), '/', '.');
        auto pos = dotted.find_last_of('.');
        std::string simple_name = (pos == std::string::npos) ? dotted : dotted.substr(pos + 1);
        imports.set(dotted, simple_name);
        base = dotted;
    } else if (base.find('.') != std::string::npos && base.compare(0, 10, "java.lang.") != 0) {
        auto pos = base.find_last_of('.');
        std::string simple_name = (pos == std::string::npos) ? base : base.substr(pos + 1);
        imports.set_default(base, simple_name);
    }
    return base + arr;
}

std::string MethodCtx::simple(const std::string& dotted) const {
    bool has_dot = dotted.find('.') != std::string::npos;
    if (imports.contains(dotted) || has_dot) {
        auto pos = dotted.find_last_of('.');
        return (pos == std::string::npos) ? dotted : dotted.substr(pos + 1);
    }
    return dotted;
}

std::string MethodCtx::owner_display(const std::string& owner_internal) {
    if (!owner_internal.empty() && owner_internal[0] == '[') {
        int depth = 0;
        std::string s = owner_internal;
        while (!s.empty() && s[0] == '[') {
            depth += 1;
            s = s.substr(1);
        }
        std::string base;
        if (!s.empty() && s[0] == 'L' && s.back() == ';') {
            base = owner_display(s.substr(1, s.size() - 2));
        } else {
            base = field_descriptor_to_java(s);
        }
        std::string suffix;
        for (int i = 0; i < depth; ++i) suffix += "[]";
        return base + suffix;
    }
    std::string new_internal;
    bool known_has_value = false;
    for (auto& [d, i] : known) {
        if (i == owner_internal) { known_has_value = true; break; }
    }
    if (known_has_value || renamer.class_map_contains(owner_internal)) {
        new_internal = renamer.friendly_class(owner_internal);
    } else if (known.count(owner_internal)) {
        // ВНИМАНИЕ: воспроизводит особенность оригинала - `known` это dotted->internal,
        // а owner_internal обычно internal-имя (a/b/C), так что это условие на
        // практике почти никогда не совпадает с реальными данными - но именно
        // так написано в Python (`elif owner_internal in self.known:`), поэтому
        // сохранено как есть, не "исправлено".
        new_internal = renamer.friendly_class(known.at(owner_internal));
    } else {
        new_internal = owner_internal;
    }
    std::string dotted = dotted_from_internal(new_internal);
    if (dotted != "java.lang.Object") {
        auto pos = dotted.find_last_of('.');
        std::string simple_name = (pos == std::string::npos) ? dotted : dotted.substr(pos + 1);
        imports.set_default(dotted, simple_name);
    }
    return dotted;
}

std::string MethodCtx::field_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
    return renamer.field_name(owner_internal, name, desc);
}

std::string MethodCtx::method_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
    if (name == "<init>" || name == "<clinit>") return name;
    return renamer.method_name(owner_internal, name, desc);
}

std::string MethodCtx::new_temp(char category) {
    temp_ctr += 1;
    std::string prefix;
    switch (category) {
        case 'I': prefix = "n"; break;
        case 'L': prefix = "lv"; break;
        case 'F': prefix = "fv"; break;
        case 'D': prefix = "dv"; break;
        case 'A': prefix = "obj"; break;
        default: prefix = "v"; break;
    }
    return "__" + prefix + std::to_string(temp_ctr);
}

std::string MethodCtx::stack_temp_for(int64_t pc, int64_t j, char category) {
    auto key = std::make_pair(pc, j);
    auto it = stack_temp_names.find(key);
    if (it == stack_temp_names.end()) {
        temp_ctr += 1;
        stack_temp_names[key] = {"__stk" + std::to_string(temp_ctr), category};
        return stack_temp_names[key].first;
    }
    return it->second.first;
}

LocalInfo& MethodCtx::local(int idx, char category) {
    auto it = locals.find(idx);
    if (it == locals.end()) {
        LocalInfo info;
        auto lvt = lvt_name_for(idx);
        info.name = lvt.has_value() ? *lvt : ("var" + std::to_string(idx));
        info.type = default_type_for_cat(category);
        info.category = category;
        info.seen_categories = {category};
        auto res = locals.emplace(idx, std::move(info));
        return res.first->second;
    }
    if (it->second.seen_categories.empty()) it->second.seen_categories.insert(it->second.category);
    it->second.seen_categories.insert(category);
    return it->second;
}

// ==================== simulate_block ====================

BlockResult simulate_block(const Block& block, const std::vector<ExprPtr>& entry_stack, MethodCtx& ctx,
                            std::vector<ExprPtr>* underflow_missing) {
    BlockResult res;
    std::vector<ExprPtr> stack = entry_stack;
    const ClassFile& cf = ctx.cf;

    auto push = [&](ExprPtr v) { stack.push_back(std::move(v)); };
    auto pop = [&]() -> ExprPtr {
        if (stack.empty()) {
            if (underflow_missing && underflow_missing->size() < 8) {
                auto ph = std::make_shared<Local>("__entry" + std::to_string(underflow_missing->size()) + "__", "Object");
                underflow_missing->push_back(ph);
                return ph;
            }
            throw DecompileAbort("stack underflow");
        }
        ExprPtr v = stack.back();
        stack.pop_back();
        return v;
    };
    auto pop_n = [&](size_t n) -> std::vector<ExprPtr> {
        std::vector<ExprPtr> vals;
        for (size_t k = 0; k < n; ++k) vals.push_back(pop());
        std::reverse(vals.begin(), vals.end());
        return vals;
    };
    auto emit = [&](StmtPtr s) { res.stmts.push_back(std::move(s)); };
    auto flush_side_effect_if_any = [&](const ExprPtr& v) {
        if (has_side_effect(v)) emit(std::make_shared<ExprStmtNode>(v));
    };
    auto cp_const = [&](int32_t idx) -> ExprPtr {
        auto it = cf.pool.find(static_cast<uint16_t>(idx));
        if (it == cf.pool.end()) throw DecompileAbort("bad cp index " + std::to_string(idx));
        const CpEntry& e = it->second;
        switch (e.tag) {
            case CpTag::Integer:
                return std::make_shared<Const>(std::to_string(e.int_value), "int");
            case CpTag::Float:
                return std::make_shared<Const>(java_float_literal(e.float_value, "f"), "float");
            case CpTag::Long:
                return std::make_shared<Const>(std::to_string(e.int_value) + "L", "long");
            case CpTag::Double:
                return std::make_shared<Const>(java_float_literal(e.float_value, ""), "double");
            case CpTag::String: {
                const std::string* s = cf.utf8(e.idx1);
                std::string sv = s ? *s : "";
                return std::make_shared<Const>(java_string_literal(sv), "String", sv);
            }
            case CpTag::Class: {
                const std::string* internal = cf.utf8(e.idx1);
                std::string disp;
                if (internal && !internal->empty() && (*internal)[0] == '[') {
                    disp = array_type_str(*internal, ctx);
                } else {
                    disp = internal ? ctx.owner_display(*internal) : "Object";
                }
                return std::make_shared<ClassLiteral>(disp);
            }
            default:
                throw DecompileAbort("unsupported ldc tag " + cp_tag_name(e.tag));
        }
    };

    const std::vector<Instruction>& instrs = block.instrs;
    size_t i = 0, n = instrs.size();
    while (i < n) {
        const Instruction& ins = instrs[i];
        const std::string& mn = ins.mnemonic;

        // ---- iinc как префиксный ++/-- ----
        if (mn == "iinc") {
            int32_t const_ = *ins.iinc_const;
            LocalInfo& info = ctx.local(*ins.iinc_idx, 'I');
            ExprPtr target = std::make_shared<Local>(info.name, info.type);
            if (const_ == 1 || const_ == -1) {
                const Instruction* nxt = (i + 1 < n) ? &instrs[i + 1] : nullptr;
                if (nxt && is_load_of(*nxt, *ins.iinc_idx)) {
                    std::string op = (const_ == 1) ? "++" : "--";
                    // ПРЕФИКС: iinc бампает переменную ПЕРВЫМ, load читает уже
                    // НОВОЕ значение - это `++var`, postfix=false (в отличие от
                    // симметричной ветки "load, затем iinc" ниже, где load
                    // читает СТАРОЕ значение первым - там postfix=true).
                    push(std::make_shared<UnOp>(op, target, info.type, false));
                    i += 2;
                    continue;
                }
                std::string op = (const_ == 1) ? "++" : "--";
                emit(std::make_shared<ExprStmtNode>(std::make_shared<UnOp>(op, target, info.type, false)));
            } else {
                emit(assign_stmt(target, std::make_shared<BinOp>("+", target, std::make_shared<Const>(std::to_string(const_), "int"))));
            }
            i += 1;
            continue;
        }

        // ---- loads ----
        if (ILOAD_ALL.count(mn)) {
            int slot = static_cast<int>(load_slot_static(ins, mn));
            LocalInfo& info = ctx.local(slot, 'I');
            ExprPtr val = std::make_shared<Local>(info.name, info.type);
            const Instruction* nxt = (i + 1 < n) ? &instrs[i + 1] : nullptr;
            if (nxt && nxt->mnemonic == "iinc" && *nxt->iinc_idx == slot && (*nxt->iinc_const == 1 || *nxt->iinc_const == -1)) {
                std::string op = (*nxt->iinc_const == 1) ? "++" : "--";
                push(std::make_shared<UnOp>(op, val, info.type, true));
                i += 2;
                continue;
            }
            push(val);
            i += 1;
            continue;
        }
        if (LLOAD_ALL.count(mn)) {
            int slot = static_cast<int>(load_slot_static(ins, mn));
            LocalInfo& info = ctx.local(slot, 'L');
            push(std::make_shared<Local>(info.name, info.type));
            i += 1;
            continue;
        }
        if (FLOAD_ALL.count(mn)) {
            int slot = static_cast<int>(load_slot_static(ins, mn));
            LocalInfo& info = ctx.local(slot, 'F');
            push(std::make_shared<Local>(info.name, info.type));
            i += 1;
            continue;
        }
        if (DLOAD_ALL.count(mn)) {
            int slot = static_cast<int>(load_slot_static(ins, mn));
            LocalInfo& info = ctx.local(slot, 'D');
            push(std::make_shared<Local>(info.name, info.type));
            i += 1;
            continue;
        }
        if (ALOAD_ALL.count(mn)) {
            int slot = static_cast<int>(load_slot_static(ins, mn));
            LocalInfo& info = ctx.local(slot, 'A');
            if (slot == 0 && info.name == "this") {
                push(std::make_shared<This>());
            } else {
                push(std::make_shared<Local>(info.name, info.type));
            }
            i += 1;
            continue;
        }

        // ---- constants ----
        if (mn == "aconst_null") { push(std::make_shared<Const>("null", "null")); i += 1; continue; }
        if (mn.rfind("iconst_", 0) == 0) {
            std::string v = mn.substr(7);
            if (v == "m1") v = "-1";
            push(std::make_shared<Const>(v, "int"));
            i += 1;
            continue;
        }
        if (mn == "lconst_0") { push(std::make_shared<Const>("0L", "long")); i += 1; continue; }
        if (mn == "lconst_1") { push(std::make_shared<Const>("1L", "long")); i += 1; continue; }
        if (mn == "fconst_0") { push(std::make_shared<Const>("0.0f", "float")); i += 1; continue; }
        if (mn == "fconst_1") { push(std::make_shared<Const>("1.0f", "float")); i += 1; continue; }
        if (mn == "fconst_2") { push(std::make_shared<Const>("2.0f", "float")); i += 1; continue; }
        if (mn == "dconst_0") { push(std::make_shared<Const>("0.0", "double")); i += 1; continue; }
        if (mn == "dconst_1") { push(std::make_shared<Const>("1.0", "double")); i += 1; continue; }
        if (mn == "bipush" || mn == "sipush") { push(std::make_shared<Const>(std::to_string(*ins.ival), "int")); i += 1; continue; }
        if (mn == "ldc" || mn == "ldc_w" || mn == "ldc2_w") { push(cp_const(*ins.cp_index)); i += 1; continue; }

        // ---- stores ----
        if (ISTORE_ALL.count(mn) || LSTORE_ALL.count(mn) || FSTORE_ALL.count(mn) || DSTORE_ALL.count(mn) || ASTORE_ALL.count(mn)) {
            char cat = ISTORE_ALL.count(mn) ? 'I' : LSTORE_ALL.count(mn) ? 'L' : FSTORE_ALL.count(mn) ? 'F' : DSTORE_ALL.count(mn) ? 'D' : 'A';
            int slot = static_cast<int>(store_slot(ins, mn));
            ExprPtr val = pop();
            bool declare = !ctx.locals.count(slot);
            LocalInfo& info = ctx.local(slot, cat);
            if (declare) info.type = refine_type(info.type, val);
            ExprPtr target = std::make_shared<Local>(info.name, info.type);
            StmtPtr stmt;
            if (declare) {
                stmt = std::make_shared<LocalDecl>(info.type, info.name, val);
            } else {
                stmt = assign_stmt(target, val);
            }
            emit(stmt);
            substitute(stack, val, target);
            i += 1;
            continue;
        }

        // ---- array load/store ----
        if (mn == "iaload" || mn == "laload" || mn == "faload" || mn == "daload" || mn == "aaload" ||
            mn == "baload" || mn == "caload" || mn == "saload") {
            ExprPtr idx = pop();
            ExprPtr arr = pop();
            static const std::map<std::string, std::string> ET = {
                {"iaload", "int"}, {"laload", "long"}, {"faload", "float"}, {"daload", "double"},
                {"aaload", "Object"}, {"baload", "byte"}, {"caload", "char"}, {"saload", "short"},
            };
            push(std::make_shared<ArrayAccess>(arr, idx, ET.at(mn)));
            i += 1;
            continue;
        }
        if (mn == "iastore" || mn == "lastore" || mn == "fastore" || mn == "dastore" || mn == "aastore" ||
            mn == "bastore" || mn == "castore" || mn == "sastore") {
            ExprPtr val = pop();
            ExprPtr idx = pop();
            ExprPtr arr = pop();
            arr = materialize_if_shared(arr, stack, ctx, res.stmts);
            static const std::map<std::string, std::string> ET = {
                {"iastore", "int"}, {"lastore", "long"}, {"fastore", "float"}, {"dastore", "double"},
                {"aastore", "Object"}, {"bastore", "byte"}, {"castore", "char"}, {"sastore", "short"},
            };
            emit(assign_stmt(std::make_shared<ArrayAccess>(arr, idx, ET.at(mn)), val));
            i += 1;
            continue;
        }

        // ---- stack manipulation ----
        if (mn == "pop") { ExprPtr v = pop(); flush_side_effect_if_any(v); i += 1; continue; }
        if (mn == "pop2") {
            ExprPtr v = pop();
            if (v->width() == 1) { ExprPtr v2 = pop(); flush_side_effect_if_any(v2); }
            flush_side_effect_if_any(v);
            i += 1;
            continue;
        }
        if (mn == "dup") { ExprPtr v = pop(); push(v); push(v); i += 1; continue; }
        if (mn == "dup_x1") {
            ExprPtr v1 = pop(), v2 = pop();
            push(v1); push(v2); push(v1);
            i += 1; continue;
        }
        if (mn == "dup_x2") {
            ExprPtr v1 = pop(), v2 = pop();
            if (v2->width() == 2) {
                push(v1); push(v2); push(v1);
            } else {
                ExprPtr v3 = pop();
                push(v1); push(v3); push(v2); push(v1);
            }
            i += 1; continue;
        }
        if (mn == "dup2") {
            ExprPtr v1 = pop();
            if (v1->width() == 2) {
                push(v1); push(v1);
            } else {
                ExprPtr v2 = pop();
                push(v2); push(v1); push(v2); push(v1);
            }
            i += 1; continue;
        }
        if (mn == "dup2_x1") {
            ExprPtr v1 = pop();
            if (v1->width() == 2) {
                ExprPtr v2 = pop();
                push(v1); push(v2); push(v1);
            } else {
                ExprPtr v2 = pop(), v3 = pop();
                push(v2); push(v1); push(v3); push(v2); push(v1);
            }
            i += 1; continue;
        }
        if (mn == "dup2_x2") {
            ExprPtr v1 = pop();
            if (v1->width() == 2) {
                ExprPtr v2 = pop();
                if (v2->width() == 2) {
                    push(v1); push(v2); push(v1);
                } else {
                    ExprPtr v3 = pop();
                    push(v1); push(v3); push(v2); push(v1);
                }
            } else {
                ExprPtr v2 = pop(), v3 = pop();
                if (v3->width() == 2) {
                    push(v2); push(v1); push(v3); push(v2); push(v1);
                } else {
                    ExprPtr v4 = pop();
                    push(v2); push(v1); push(v4); push(v3); push(v2); push(v1);
                }
            }
            i += 1; continue;
        }
        if (mn == "swap") { ExprPtr v1 = pop(), v2 = pop(); push(v1); push(v2); i += 1; continue; }

        // ---- arithmetic ----
        if (BINOPS.count(mn)) {
            auto& [op, t] = BINOPS.at(mn);
            ExprPtr r = pop(), l = pop();
            push(std::make_shared<BinOp>(op, l, r, t));
            i += 1; continue;
        }
        if (NEGOPS.count(mn)) {
            const std::string& t = NEGOPS.at(mn);
            ExprPtr v = pop();
            push(std::make_shared<UnOp>("-", v, t));
            i += 1; continue;
        }
        if (CASTS.count(mn)) {
            const std::string& t = CASTS.at(mn);
            ExprPtr v = pop();
            push(std::make_shared<Cast>(t, v));
            i += 1; continue;
        }
        if (mn == "lcmp" || mn == "fcmpl" || mn == "fcmpg" || mn == "dcmpl" || mn == "dcmpg") {
            ExprPtr r = pop(), l = pop();
            push(std::make_shared<BinOp>("cmp", l, r, "int"));
            i += 1; continue;
        }

        // ---- fields ----
        if (mn == "getstatic") {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (!r) throw DecompileAbort("bad getstatic");
            auto& [owner, name, desc] = *r;
            std::string ftype = ctx.map_type(field_descriptor_to_java(desc));
            std::string fname = ctx.field_name(owner, name, desc);
            push(std::make_shared<FieldAccess>(nullptr, fname, ftype, true, std::optional<std::string>(ctx.owner_display(owner))));
            i += 1; continue;
        }
        if (mn == "putstatic") {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (!r) throw DecompileAbort("bad putstatic");
            auto& [owner, name, desc] = *r;
            std::string ftype = ctx.map_type(field_descriptor_to_java(desc));
            std::string fname = ctx.field_name(owner, name, desc);
            ExprPtr val = pop();
            val = coerce_arg(val, ftype);
            ExprPtr tgt = std::make_shared<FieldAccess>(nullptr, fname, ftype, true, std::optional<std::string>(ctx.owner_display(owner)));
            emit(assign_stmt(tgt, val));
            i += 1; continue;
        }
        if (mn == "getfield") {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (!r) throw DecompileAbort("bad getfield");
            auto& [owner, name, desc] = *r;
            std::string ftype = ctx.map_type(field_descriptor_to_java(desc));
            std::string fname = ctx.field_name(owner, name, desc);
            ExprPtr obj = pop();
            push(std::make_shared<FieldAccess>(obj, fname, ftype));
            i += 1; continue;
        }
        if (mn == "putfield") {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (!r) throw DecompileAbort("bad putfield");
            auto& [owner, name, desc] = *r;
            std::string ftype = ctx.map_type(field_descriptor_to_java(desc));
            std::string fname = ctx.field_name(owner, name, desc);
            ExprPtr val = pop(), obj = pop();
            val = coerce_arg(val, ftype);
            ExprPtr tgt = std::make_shared<FieldAccess>(obj, fname, ftype);
            emit(assign_stmt(tgt, val));
            i += 1; continue;
        }

        // ---- invocations ----
        if (mn == "invokevirtual" || mn == "invokespecial" || mn == "invokestatic" || mn == "invokeinterface") {
            auto r = cf.ref_string(static_cast<uint16_t>(*ins.cp_index));
            if (!r) throw DecompileAbort("bad invoke ref");
            auto [owner, name, desc] = *r;
            std::string ret;
            std::vector<std::string> params;
            try {
                auto [rr, pp] = method_descriptor_to_java(desc);
                ret = rr;
                params = pp;
            } catch (...) {
                throw DecompileAbort("bad method descriptor");
            }
            std::vector<ExprPtr> args = pop_n(params.size());
            for (size_t k = 0; k < args.size(); ++k) args[k] = coerce_arg(args[k], ctx.map_type(params[k]));

            if (mn == "invokestatic") {
                auto& active = str_decrypt_get_active();
                if (active.has_value() && owner == active->owner && name == active->method && args.size() == 1 &&
                    args[0]->kind == ExprKind::Const) {
                    auto* c = static_cast<Const*>(args[0].get());
                    if (c->type == "String" && c->raw.has_value()) {
                        auto decrypted = str_decrypt_decrypt_string(active->key, *c->raw);
                        if (decrypted.has_value()) {
                            str_decrypt_increment_decrypted_count();
                            push(std::make_shared<Const>(java_string_literal(*decrypted), "String", *decrypted));
                            i += 1;
                            continue;
                        }
                    }
                }
                std::string mname = ctx.method_name(owner, name, desc);
                // ВАЖНО: порядок вычисления обязателен как в Python (map_type
                // ПЕРЕД owner_display - оба мутируют ctx.imports, и порядок
                // вставки в этот order-preserving словарь наблюдаем) - поэтому
                // раздельные последовательные строки, а не аргументы одного
                // вызова (порядок вычисления аргументов в C++ не гарантирован).
                std::string mapped_ret = ctx.map_type(ret);
                std::string owner_disp = ctx.owner_display(owner);
                ExprPtr call = std::make_shared<MethodCall>(nullptr, mname, args, mapped_ret, true,
                                                             std::optional<std::string>(owner_disp));
                if (ret == "void") emit(std::make_shared<ExprStmtNode>(call));
                else push(call);
            } else {
                ExprPtr recv = pop();
                if (name == "<init>") {
                    auto* pn = dynamic_cast<PendingNew*>(recv.get());
                    if (pn && !pn->initialized) {
                        pn->args = args;
                        pn->initialized = true;
                        // ничего не пушим: <init> ничего не возвращает
                    } else if (recv->kind == ExprKind::This && mn == "invokespecial") {
                        bool is_super = owner != ctx.class_internal;
                        ExprPtr call = std::make_shared<MethodCall>(nullptr, is_super ? "super" : "this", args, "void",
                                                                     false, std::nullopt, true, is_super, false);
                        emit(std::make_shared<ExprStmtNode>(call));
                    } else {
                        throw DecompileAbort("unrecognized <init> pattern");
                    }
                } else {
                    std::string mname = ctx.method_name(owner, name, desc);
                    bool is_super_call = (mn == "invokespecial" && owner != ctx.class_internal);
                    std::string mapped_ret = ctx.map_type(ret);
                    std::string owner_disp = ctx.owner_display(owner);
                    ExprPtr tgt = std::make_shared<MethodCall>(
                        is_super_call ? std::make_shared<This>() : recv, mname, args, mapped_ret, false,
                        std::optional<std::string>(owner_disp), false, is_super_call, mn == "invokeinterface");
                    if (ret == "void") emit(std::make_shared<ExprStmtNode>(tgt));
                    else push(tgt);
                }
            }
            i += 1;
            continue;
        }

        if (mn == "invokedynamic") {
            ExprPtr expr = handle_invokedynamic(cf, ins, ctx, pop_n);
            push(expr);
            i += 1;
            continue;
        }

        // ---- object / array creation ----
        if (mn == "new") {
            auto cname = cf.class_name(static_cast<uint16_t>(*ins.cp_index));
            if (!cname.has_value()) throw DecompileAbort("bad new target");
            push(std::make_shared<PendingNew>(ctx.owner_display(*cname)));
            i += 1; continue;
        }
        if (mn == "newarray") {
            ExprPtr size = pop();
            push(std::make_shared<NewArray>(*ins.atype, std::vector<ExprPtr>{size}));
            i += 1; continue;
        }
        if (mn == "anewarray") {
            auto cname = cf.class_name(static_cast<uint16_t>(*ins.cp_index));
            if (!cname.has_value()) throw DecompileAbort("bad anewarray target");
            ExprPtr size = pop();
            std::string elem = (!cname->empty() && (*cname)[0] == '[') ? array_type_str(*cname, ctx) : ctx.owner_display(*cname);
            push(std::make_shared<NewArray>(elem, std::vector<ExprPtr>{size}));
            i += 1; continue;
        }
        if (mn == "multianewarray") {
            auto cname = cf.class_name(static_cast<uint16_t>(*ins.cp_index));
            if (!cname.has_value()) throw DecompileAbort("bad multianewarray target");
            std::vector<ExprPtr> dims = pop_n(static_cast<size_t>(*ins.dims));
            std::string base = *cname;
            size_t strip = 0;
            while (strip < base.size() && base[strip] == '[') strip += 1;
            base = base.substr(strip);
            std::string elem;
            if (!base.empty() && base[0] == 'L' && base.back() == ';') {
                elem = ctx.owner_display(base.substr(1, base.size() - 2));
            } else {
                elem = field_descriptor_to_java(base);
            }
            push(std::make_shared<NewArray>(elem, dims));
            i += 1; continue;
        }
        if (mn == "arraylength") {
            ExprPtr arr = pop();
            push(std::make_shared<FieldAccess>(arr, "length", "int"));
            i += 1; continue;
        }

        if (mn == "checkcast") {
            auto cname = cf.class_name(static_cast<uint16_t>(*ins.cp_index));
            if (!cname.has_value()) throw DecompileAbort("bad checkcast target");
            ExprPtr v = pop();
            std::string disp = (!cname->empty() && (*cname)[0] == '[') ? array_type_str(*cname, ctx) : ctx.owner_display(*cname);
            push(std::make_shared<Cast>(disp, v));
            i += 1; continue;
        }
        if (mn == "instanceof") {
            auto cname = cf.class_name(static_cast<uint16_t>(*ins.cp_index));
            if (!cname.has_value()) throw DecompileAbort("bad instanceof target");
            ExprPtr v = pop();
            std::string disp = (!cname->empty() && (*cname)[0] == '[') ? array_type_str(*cname, ctx) : ctx.owner_display(*cname);
            push(std::make_shared<InstanceOf>(v, disp));
            i += 1; continue;
        }

        if (mn == "athrow") {
            ExprPtr v = pop();
            emit(std::make_shared<ThrowStmt>(v));
            res.term_kind = "throw";
            i += 1; continue;
        }

        if (mn == "monitorenter") {
            ExprPtr v = pop();
            emit(std::make_shared<MonitorMarkerStmt>("enter", v));
            i += 1; continue;
        }
        if (mn == "monitorexit") {
            ExprPtr v = pop();
            emit(std::make_shared<MonitorMarkerStmt>("exit", v));
            i += 1; continue;
        }

        if (mn == "return" || mn == "ireturn" || mn == "lreturn" || mn == "freturn" || mn == "dreturn" || mn == "areturn") {
            if (mn == "return") {
                emit(std::make_shared<ReturnStmt>(nullptr));
            } else {
                ExprPtr v = pop();
                v = coerce_arg(v, ctx.ret_type);
                emit(std::make_shared<ReturnStmt>(v));
            }
            res.term_kind = "return";
            i += 1; continue;
        }

        if (mn == "nop") { i += 1; continue; }

        if (ins.is_conditional()) {
            ExprPtr cond;
            if (mn == "ifnull" || mn == "ifnonnull") {
                ExprPtr v = pop();
                cond = std::make_shared<BinOp>(mn == "ifnull" ? "==" : "!=", v, std::make_shared<Const>("null", "null"), "boolean");
            } else if (mn == "ifeq" || mn == "ifne" || mn == "iflt" || mn == "ifge" || mn == "ifgt" || mn == "ifle") {
                ExprPtr v = pop();
                static const std::map<std::string, std::string> CMP = {
                    {"ifeq", "=="}, {"ifne", "!="}, {"iflt", "<"}, {"ifge", ">="}, {"ifgt", ">"}, {"ifle", "<="},
                };
                cond = fold_compare(v, CMP.at(mn));
            } else {
                ExprPtr r = pop(), l = pop();
                static const std::map<std::string, std::string> CMP2 = {
                    {"if_icmpeq", "=="}, {"if_icmpne", "!="}, {"if_icmplt", "<"}, {"if_icmpge", ">="},
                    {"if_icmpgt", ">"}, {"if_icmple", "<="}, {"if_acmpeq", "=="}, {"if_acmpne", "!="},
                };
                cond = std::make_shared<BinOp>(CMP2.at(mn), l, r, "boolean");
            }
            res.term_kind = "if";
            res.cond = cond;
            i += 1;
            continue;
        }

        if (mn == "goto" || mn == "goto_w") { i += 1; continue; }

        if (ins.is_switch()) {
            ExprPtr v = pop();
            res.term_kind = "switch";
            res.cond = v;
            i += 1;
            continue;
        }

        if (mn == "jsr" || mn == "jsr_w" || mn == "ret") {
            throw DecompileAbort("jsr/ret (legacy finally) не поддерживается");
        }

        throw DecompileAbort("неизвестная/неподдержанная инструкция " + mn);
    }

    res.exit_stack = stack;
    return res;
}

}  // namespace nd

