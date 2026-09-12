// javatypes.cpp - см. javatypes.hpp. 1:1 порт javatypes.py.
#include "javatypes.hpp"

#include <algorithm>
#include <cctype>
#include <map>
#include <stdexcept>

namespace nd {

namespace {

const std::map<char, std::string> PRIMS = {
    {'V', "void"}, {'Z', "boolean"}, {'B', "byte"}, {'C', "char"},
    {'S', "short"}, {'I', "int"}, {'J', "long"}, {'F', "float"}, {'D', "double"},
};

[[maybe_unused]] bool is_prim(char c) { return PRIMS.count(c) != 0; }

// Аналог str.split(sep) в Python (без maxsplit) - разбивает по КАЖДОМУ
// вхождению разделителя, сохраняя пустые сегменты.
std::vector<std::string> split_all(const std::string& s, char sep) {
    std::vector<std::string> out;
    size_t start = 0;
    for (size_t i = 0; i < s.size(); ++i) {
        if (s[i] == sep) {
            out.push_back(s.substr(start, i - start));
            start = i + 1;
        }
    }
    out.push_back(s.substr(start));
    return out;
}

bool is_ascii_digit_str(const std::string& s) {
    if (s.empty()) return false;
    for (char c : s) {
        if (!std::isdigit(static_cast<unsigned char>(c))) return false;
    }
    return true;
}

// rsplit(sep, 1)[-1] - последний сегмент после ПОСЛЕДНЕГО вхождения sep,
// или вся строка, если sep не встречается.
std::string last_segment_after(const std::string& s, char sep) {
    auto pos = s.rfind(sep);
    if (pos == std::string::npos) return s;
    return s.substr(pos + 1);
}

std::string join(const std::vector<std::string>& parts, const std::string& sep) {
    std::string out;
    for (size_t i = 0; i < parts.size(); ++i) {
        if (i) out += sep;
        out += parts[i];
    }
    return out;
}

}  // namespace

// ---------------- dotted_from_internal ----------------

std::string dotted_from_internal(const std::string& internal) {
    std::string pkg, simple;
    auto slash = internal.rfind('/');
    if (slash == std::string::npos) {
        pkg = "";
        simple = internal;
    } else {
        pkg = internal.substr(0, slash);
        simple = internal.substr(slash + 1);
    }
    auto segs = split_all(simple, '$');
    std::vector<std::string> pieces;
    pieces.push_back(segs[0]);
    for (size_t k = 1; k < segs.size(); ++k) {
        const std::string& s = segs[k];
        if (is_ascii_digit_str(s)) {
            pieces.back() += "$" + s;
        } else {
            pieces.push_back(s);
        }
    }
    std::string result = join(pieces, ".");
    std::string dotted_pkg = pkg;
    std::replace(dotted_pkg.begin(), dotted_pkg.end(), '/', '.');
    if (!dotted_pkg.empty()) return dotted_pkg + "." + result;
    return result;
}

// ---------------- descriptor parsing ----------------

std::pair<std::string, size_t> parse_one_type(const std::string& desc, size_t i) {
    if (i >= desc.size()) throw std::invalid_argument("Плохой дескриптор типа: '" + desc + "' at " + std::to_string(i));
    char c = desc[i];
    auto it = PRIMS.find(c);
    if (it != PRIMS.end()) return {it->second, i + 1};
    if (c == 'L') {
        auto j = desc.find(';', i);
        if (j == std::string::npos) throw std::invalid_argument("Плохой дескриптор типа: '" + desc + "' at " + std::to_string(i));
        std::string internal = desc.substr(i + 1, j - (i + 1));
        return {dotted_from_internal(internal), j + 1};
    }
    if (c == '[') {
        auto [inner, ni] = parse_one_type(desc, i + 1);
        return {inner + "[]", ni};
    }
    throw std::invalid_argument("Плохой дескриптор типа: '" + desc + "' at " + std::to_string(i));
}

std::string field_descriptor_to_java(const std::string& desc) {
    return parse_one_type(desc, 0).first;
}

std::pair<std::string, std::vector<std::string>> method_descriptor_to_java(const std::string& desc) {
    if (desc.empty() || desc[0] != '(') throw std::invalid_argument("method_descriptor_to_java: expected '(' at start");
    size_t i = 1;
    std::vector<std::string> params;
    while (i < desc.size() && desc[i] != ')') {
        auto [t, ni] = parse_one_type(desc, i);
        params.push_back(t);
        i = ni;
    }
    auto [ret, _] = parse_one_type(desc, i + 1);
    return {ret, params};
}

// ---------------- mark_type / resolve_type_markers ----------------

std::string mark_type(const std::optional<std::string>& dotted) {
    if (!dotted.has_value()) return "Object";
    if (dotted->empty()) return "";
    std::string arr;
    std::string base = *dotted;
    while (base.size() >= 2 && base.substr(base.size() - 2) == "[]") {
        arr += "[]";
        base = base.substr(0, base.size() - 2);
    }
    std::string out;
    out += TYPE_MARK_OPEN;
    out += base;
    out += TYPE_MARK_CLOSE;
    out += arr;
    return out;
}

std::string resolve_type_markers(const std::string& text, const std::unordered_set<std::string>& losers) {
    std::string out;
    size_t i = 0, n = text.size();
    while (i < n) {
        char ch = text[i];
        if (ch == TYPE_MARK_OPEN) {
            auto j = text.find(TYPE_MARK_CLOSE, i + 1);
            // Не должно случиться на корректно сформированном тексте
            // (каждый mark_type() всегда парный) - но на всякий случай, как
            // и оригинал (который тоже упал бы с ValueError на text.index()
            // при отсутствии закрывающего маркера), не глотаем ошибку молча.
            if (j == std::string::npos) throw std::invalid_argument("resolve_type_markers: unmatched TYPE_MARK_OPEN");
            std::string dotted = text.substr(i + 1, j - (i + 1));
            if (losers.count(dotted)) {
                out += dotted;
            } else {
                out += last_segment_after(dotted, '.');
            }
            i = j + 1;
        } else {
            out += ch;
            i += 1;
        }
    }
    return out;
}

// ---------------- Signature parsing ----------------

namespace {

struct SignatureParseError : std::runtime_error {
    explicit SignatureParseError(const std::string& m) : std::runtime_error(m) {}
};

class FieldSigParser {
public:
    explicit FieldSigParser(const std::string& s) : s_(s), i_(0), n_(s.size()) {}

    char peek() const {
        if (i_ >= n_) throw SignatureParseError("неожиданный конец сигнатуры");
        return s_[i_];
    }

    std::string parse_type() {
        char c = peek();
        if (c == 'L') return parse_class_type();
        if (c == 'T') return parse_type_var();
        if (c == '[') {
            i_ += 1;
            return parse_type() + "[]";
        }
        auto it = PRIMS.find(c);
        if (it != PRIMS.end()) {
            i_ += 1;
            return it->second;
        }
        if (c == '*') {
            i_ += 1;
            return "?";
        }
        if (c == '+' || c == '-') {
            char wc = c;
            i_ += 1;
            std::string inner = parse_type();
            return (wc == '+' ? std::string("? extends ") : std::string("? super ")) + inner;
        }
        throw SignatureParseError(std::string("неожиданный символ '") + c + "' в позиции " + std::to_string(i_));
    }

    std::string s_;
    size_t i_;
    size_t n_;

private:
    std::string parse_type_var() {
        i_ += 1;  // 'T'
        size_t start = i_;
        while (peek() != ';') i_ += 1;
        std::string name = s_.substr(start, i_ - start);
        i_ += 1;  // ';'
        return name;
    }

    std::string parse_class_type() {
        i_ += 1;  // 'L'
        size_t start = i_;
        while (peek() != '<' && peek() != ';' && peek() != '.') i_ += 1;
        std::string internal = s_.substr(start, i_ - start);
        std::string dotted = dotted_from_internal(internal);
        std::string result = last_segment_after(dotted, '.');
        if (peek() == '<') result += parse_type_args();
        while (i_ < n_ && peek() == '.') {
            i_ += 1;
            size_t istart = i_;
            while (peek() != '<' && peek() != ';' && peek() != '.') i_ += 1;
            std::string iname = s_.substr(istart, i_ - istart);
            result += "." + iname;
            if (peek() == '<') result += parse_type_args();
        }
        if (peek() != ';') throw SignatureParseError("ожидался ';' в конце ClassTypeSignature");
        i_ += 1;
        return result;
    }

    std::string parse_type_args() {
        i_ += 1;  // '<'
        std::vector<std::string> args;
        while (peek() != '>') args.push_back(parse_type());
        i_ += 1;  // '>'
        return "<" + join(args, ", ") + ">";
    }
};

class FullSigParser : public FieldSigParser {
public:
    explicit FullSigParser(const std::string& s) : FieldSigParser(s) {}

    std::string read_ident() {
        size_t start = i_;
        while (i_ < n_ && s_[i_] != ':' && s_[i_] != '<' && s_[i_] != ';' && s_[i_] != '.' &&
               s_[i_] != '[' && s_[i_] != '/' && s_[i_] != '(' && s_[i_] != ')' && s_[i_] != '^') {
            i_ += 1;
        }
        return s_.substr(start, i_ - start);
    }

    std::vector<std::pair<std::string, std::vector<std::string>>> parse_type_params() {
        std::vector<std::pair<std::string, std::vector<std::string>>> params;
        if (i_ >= n_ || peek() != '<') return params;
        i_ += 1;
        while (peek() != '>') {
            std::string name = read_ident();
            std::vector<std::string> bounds;
            if (peek() != ':') throw SignatureParseError("ожидался ':' после имени параметра типа");
            i_ += 1;  // первое ':' (ClassBound, может быть пустым)
            if (peek() != ':' && peek() != '>') bounds.push_back(parse_type());
            while (peek() == ':') {  // InterfaceBound*
                i_ += 1;
                bounds.push_back(parse_type());
            }
            if (bounds.size() == 1 && bounds[0] == "Object") bounds.clear();
            params.emplace_back(name, std::move(bounds));
        }
        i_ += 1;  // '>'
        return params;
    }
};

std::string format_type_params(const std::vector<std::pair<std::string, std::vector<std::string>>>& type_params) {
    if (type_params.empty()) return "";
    std::vector<std::string> parts;
    for (auto& [name, bounds] : type_params) {
        if (!bounds.empty()) {
            parts.push_back(name + " extends " + join(bounds, " & "));
        } else {
            parts.push_back(name);
        }
    }
    return "<" + join(parts, ", ") + ">";
}

}  // namespace

std::optional<std::string> parse_field_signature(const std::string& sig) {
    if (sig.empty()) return std::nullopt;
    try {
        FieldSigParser p(sig);
        std::string result = p.parse_type();
        if (p.i_ != p.n_) return std::nullopt;  // остался "хвост" - не доверяем
        return result;
    } catch (...) {
        return std::nullopt;  // SignatureParseError или любая другая ошибка разбора
    }
}

std::optional<MethodSignature> parse_method_signature(const std::string& sig) {
    if (sig.empty()) return std::nullopt;
    try {
        FullSigParser p(sig);
        auto type_params = p.parse_type_params();
        if (p.peek() != '(') return std::nullopt;
        p.i_ += 1;
        std::vector<std::string> param_types;
        while (p.peek() != ')') param_types.push_back(p.parse_type());
        p.i_ += 1;  // ')'
        std::string ret;
        if (p.peek() == 'V') {
            p.i_ += 1;
            ret = "void";
        } else {
            ret = p.parse_type();
        }
        while (p.i_ < p.n_ && p.peek() == '^') {
            p.i_ += 1;
            p.parse_type();  // typed throws - разбирается, но не используется
        }
        if (p.i_ != p.n_) return std::nullopt;
        MethodSignature ms;
        ms.type_params = format_type_params(type_params);
        ms.param_types = std::move(param_types);
        ms.return_type = ret;
        return ms;
    } catch (...) {
        return std::nullopt;
    }
}

std::optional<ClassSignature> parse_class_signature(const std::string& sig) {
    if (sig.empty()) return std::nullopt;
    try {
        FullSigParser p(sig);
        auto type_params = p.parse_type_params();
        std::string superclass = p.parse_type();
        std::vector<std::string> interfaces;
        while (p.i_ < p.n_) interfaces.push_back(p.parse_type());
        ClassSignature cs;
        cs.type_params = format_type_params(type_params);
        cs.superclass = superclass;
        cs.interfaces = std::move(interfaces);
        return cs;
    } catch (...) {
        return std::nullopt;
    }
}

// ---------------- obfuscation heuristics ----------------

namespace {
const std::unordered_set<std::string> JAVA_KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "true", "false", "null",
    "var", "record", "yield", "sealed", "permits",
};

const std::unordered_set<char> VOWELS = {'a', 'e', 'i', 'o', 'u'};

bool is_ident_char_first(char c) {
    return std::isalpha(static_cast<unsigned char>(c)) || c == '_' || c == '$';
}
bool is_ident_char_rest(char c) {
    return std::isalnum(static_cast<unsigned char>(c)) || c == '_' || c == '$';
}

int consonant_run(const std::string& name_lower) {
    int run = 0, best = 0;
    for (char ch : name_lower) {
        if (std::isalpha(static_cast<unsigned char>(ch)) && !VOWELS.count(ch)) {
            run += 1;
            best = std::max(best, run);
        } else {
            run = 0;
        }
    }
    return best;
}

}  // namespace

bool is_safe_local_name(const std::string& name) {
    if (name.empty()) return false;
    if (!is_ident_char_first(name[0])) return false;
    for (size_t i = 1; i < name.size(); ++i) {
        if (!is_ident_char_rest(name[i])) return false;
    }
    return JAVA_KEYWORDS.count(name) == 0;
}

bool looks_obfuscated(const std::optional<std::string>& name_opt, const std::string& kind) {
    if (!name_opt.has_value()) return false;
    const std::string& name = *name_opt;
    if (name == "<init>" || name == "<clinit>") return false;

    std::string base;
    if (kind == "class") {
        base = last_segment_after(name, '$');
    } else {
        if (name.find('$') != std::string::npos) return false;
        base = name;
    }

    if ((kind == "class" || kind == "method" || kind == "field") && base.size() == 1) return true;

    std::string lower = base;
    std::transform(lower.begin(), lower.end(), lower.begin(),
                    [](unsigned char c) { return std::tolower(c); });
    std::string letters_only;
    for (char c : lower) {
        if (c >= 'a' && c <= 'z') letters_only += c;
    }
    if (letters_only.size() < 12) return false;

    bool has_upper = std::any_of(base.begin(), base.end(), [](unsigned char c) { return std::isupper(c); });
    bool has_digit = std::any_of(base.begin(), base.end(), [](unsigned char c) { return std::isdigit(c); });
    bool has_underscore = base.find('_') != std::string::npos;

    double vowel_ratio = 0.0;
    {
        size_t vc = 0;
        for (char c : letters_only) if (VOWELS.count(c)) vc += 1;
        vowel_ratio = static_cast<double>(vc) / static_cast<double>(letters_only.size());
    }
    int max_run = consonant_run(letters_only);

    bool flat_lower_long = (!has_upper) && (!has_digit) && (!has_underscore) && base.size() >= 12;
    bool very_low_vowels = vowel_ratio < 0.20;
    bool very_long_consonant_run = max_run >= 6;

    if (kind == "class" || kind == "method" || kind == "field") {
        if (flat_lower_long && (very_low_vowels || very_long_consonant_run)) return true;
        if (flat_lower_long && base.size() >= 18) return true;
    }
    if (kind == "package") {
        if (flat_lower_long && base.size() >= 16) return true;
    }
    return false;
}

}  // namespace nd
