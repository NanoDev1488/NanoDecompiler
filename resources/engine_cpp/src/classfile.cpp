// classfile.cpp - см. classfile.hpp. Порт classfile.py, 1:1 по семантике.
#include "classfile.hpp"

#include <charconv>
#include <cmath>
#include <cstdio>
#include <cstring>

namespace nd {

namespace {

// ---- UTF-8 декодирование с заменой (аналог bytes.decode("utf-8",
// errors="replace") в Python) ----
//
// classfile.py делает ИМЕННО ЭТО при чтении Utf8-записей constant pool -
// т.е. хранит уже ДЕКОДИРОВАННУЮ (с заменой невалидных последовательностей
// на U+FFFD) строку, а не сырые байты. Изначально порт хранил сырые байты
// как есть - на валидном UTF-8 (все имена классов/методов/полей в 4075
// тестовых классах модуля 1) расхождений не было, но на РЕАЛЬНЫХ строковых
// КОНСТАНТАХ (не именах) внутри кода это дало разницу: (1) бинарные blob'ы,
// хранящиеся как "строки" (напр. сериализованные protobuf-дескрипторы в
// MySQL JDBC коннекторе - Salaires.jar) содержат невалидный UTF-8, и без
// правильной de-факто замены на U+FFFD печатаются как "мусорные" сырые
// байты вместо одного replacement-символа на невалидную подпоследовательность;
// (2) валидные, но НЕПЕЧАТАЕМЫЕ юникод-символы (напр. правильно
// декодированный U+E100 из Private Use Area, U+2003 EM SPACE) должны при
// repr() эскейпиться как \uXXXX, а не выводиться как есть "сырыми" байтами.
// Алгоритм замены - "maximal subpart" по Unicode Standard Table 3-7 (то же
// правило использует сам CPython).
std::u32string decode_utf8_replace(const std::string& bytes) {
    std::u32string out;
    size_t i = 0, n = bytes.size();
    auto b = [&](size_t k) -> unsigned char { return static_cast<unsigned char>(bytes[k]); };
    while (i < n) {
        unsigned char b0 = b(i);
        if (b0 < 0x80) {
            out.push_back(b0);
            i += 1;
            continue;
        }
        if (b0 < 0xC2) {  // continuation-байт "не на месте" или overlong C0/C1
            out.push_back(0xFFFD);
            i += 1;
            continue;
        }
        if (b0 < 0xE0) {  // 2-байтовая последовательность
            if (i + 1 < n && (b(i + 1) & 0xC0) == 0x80) {
                char32_t cp = (char32_t(b0 & 0x1F) << 6) | char32_t(b(i + 1) & 0x3F);
                out.push_back(cp);
                i += 2;
            } else {
                out.push_back(0xFFFD);
                i += 1;
            }
            continue;
        }
        if (b0 < 0xF0) {  // 3-байтовая
            unsigned char lo, hi;
            if (b0 == 0xE0) { lo = 0xA0; hi = 0xBF; }
            else if (b0 == 0xED) { lo = 0x80; hi = 0x9F; }  // избегаем суррогатов D800-DFFF
            else { lo = 0x80; hi = 0xBF; }
            if (i + 1 < n && b(i + 1) >= lo && b(i + 1) <= hi) {
                if (i + 2 < n && (b(i + 2) & 0xC0) == 0x80) {
                    char32_t cp = (char32_t(b0 & 0x0F) << 12) | (char32_t(b(i + 1) & 0x3F) << 6) |
                                  char32_t(b(i + 2) & 0x3F);
                    out.push_back(cp);
                    i += 3;
                } else {
                    out.push_back(0xFFFD);
                    i += 2;  // maximal subpart - первые 2 байта были валидны
                }
            } else {
                out.push_back(0xFFFD);
                i += 1;
            }
            continue;
        }
        if (b0 < 0xF5) {  // 4-байтовая
            unsigned char lo, hi;
            if (b0 == 0xF0) { lo = 0x90; hi = 0xBF; }
            else if (b0 == 0xF4) { lo = 0x80; hi = 0x8F; }  // избегаем > 10FFFF
            else { lo = 0x80; hi = 0xBF; }
            if (i + 1 < n && b(i + 1) >= lo && b(i + 1) <= hi) {
                if (i + 2 < n && (b(i + 2) & 0xC0) == 0x80) {
                    if (i + 3 < n && (b(i + 3) & 0xC0) == 0x80) {
                        char32_t cp = (char32_t(b0 & 0x07) << 18) | (char32_t(b(i + 1) & 0x3F) << 12) |
                                      (char32_t(b(i + 2) & 0x3F) << 6) | char32_t(b(i + 3) & 0x3F);
                        out.push_back(cp);
                        i += 4;
                    } else {
                        out.push_back(0xFFFD);
                        i += 3;
                    }
                } else {
                    out.push_back(0xFFFD);
                    i += 2;
                }
            } else {
                out.push_back(0xFFFD);
                i += 1;
            }
            continue;
        }
        out.push_back(0xFFFD);  // b0 >= 0xF5 - невалидный ведущий байт
        i += 1;
    }
    return out;
}

void encode_utf8_append(char32_t cp, std::string& out) {
    if (cp <= 0x7F) {
        out += static_cast<char>(cp);
    } else if (cp <= 0x7FF) {
        out += static_cast<char>(0xC0 | (cp >> 6));
        out += static_cast<char>(0x80 | (cp & 0x3F));
    } else if (cp <= 0xFFFF) {
        out += static_cast<char>(0xE0 | (cp >> 12));
        out += static_cast<char>(0x80 | ((cp >> 6) & 0x3F));
        out += static_cast<char>(0x80 | (cp & 0x3F));
    } else {
        out += static_cast<char>(0xF0 | (cp >> 18));
        out += static_cast<char>(0x80 | ((cp >> 12) & 0x3F));
        out += static_cast<char>(0x80 | ((cp >> 6) & 0x3F));
        out += static_cast<char>(0x80 | (cp & 0x3F));
    }
}

// Приводит сырые байты к тому виду, в каком их держал бы Python-объект str
// после `raw.decode("utf-8", errors="replace")` (записанному обратно в
// UTF-8) - т.е. валидные последовательности остаются как есть, невалидные
// заменяются на U+FFFD (3 байта EF BF BD каждая). Вызывается ОДИН раз при
// разборе Utf8-записи constant pool - все дальнейшие потребители
// (describe_cp, имена классов/методов/полей, javatypes) работают уже с
// этой нормализованной строкой, как и в оригинале.
std::string normalize_utf8_replace(const std::string& raw) {
    auto cps = decode_utf8_replace(raw);
    std::string out;
    out.reserve(raw.size());
    for (char32_t cp : cps) encode_utf8_append(cp, out);
    return out;
}

// Аналог Python str.isprintable() на уровне одного кодпоинта - используется
// в repr() для решения, эскейпить символ (\xXX/\uXXXX/\UXXXXXXXX) или
// выводить как есть. НЕ претендует на 100% соответствие полной таблице
// категорий Unicode (в частности неполно покрыт Cn - "не назначено" - и
// длинный хвост редких Cf-символов) - покрыты все категории, реально
// встречающиеся в строковых константах из тестовых jar (управляющие
// символы, суррогаты, приватная область, разделители-пробелы) - см.
// HANDOFF_26 "осознанные упрощения".
bool is_printable_codepoint(char32_t cp) {
    if (cp == 0x20) return true;
    if (cp < 0x20 || cp == 0x7F) return false;             // Cc (C0)
    if (cp >= 0x80 && cp <= 0x9F) return false;             // Cc (C1)
    if (cp >= 0xD800 && cp <= 0xDFFF) return false;         // Cs суррогаты
    if ((cp >= 0xE000 && cp <= 0xF8FF) ||                   // Co Private Use
        (cp >= 0xF0000 && cp <= 0xFFFFD) ||
        (cp >= 0x100000 && cp <= 0x10FFFD)) return false;
    static const std::vector<char32_t> zs = {
        0xA0, 0x1680, 0x2000, 0x2001, 0x2002, 0x2003, 0x2004, 0x2005, 0x2006,
        0x2007, 0x2008, 0x2009, 0x200A, 0x202F, 0x205F, 0x3000,
    };
    for (char32_t z : zs) if (cp == z) return false;         // Zs (кроме ASCII-пробела)
    if (cp == 0x2028 || cp == 0x2029) return false;          // Zl/Zp
    static const std::vector<char32_t> cf = {
        0xAD, 0x200B, 0x200C, 0x200D, 0x200E, 0x200F, 0x202A, 0x202B, 0x202C,
        0x202D, 0x202E, 0x2060, 0x2061, 0x2062, 0x2063, 0x2064, 0x2066, 0x2067,
        0x2068, 0x2069, 0x206A, 0x206B, 0x206C, 0x206D, 0x206E, 0x206F, 0xFEFF,
        0xFFF9, 0xFFFA, 0xFFFB,
    };
    for (char32_t c : cf) if (cp == c) return false;          // Cf (частичный список)
    if (cp > 0x10FFFF) return false;
    return true;
}

// Аналог Python repr(float)/repr(double): кратчайшее десятичное
// представление, круглящееся обратно в то же число, ВСЕГДА с точкой
// (Python: repr(1.0) == '1.0', repr(60000.0) == '60000.0') - в отличие от
// std::to_chars по умолчанию, который для целых значений точку не ставит
// (даёт "1", "60000").
// Точное воспроизведение CPython float_repr / format_float_short (тип 'r',
// mode 0 = кратчайшее round-trip представление). Ключевая деталь, из-за
// которой первая версия этой функции (через std::to_chars() без указания
// формата) расходилась с оригиналом на реальных double-константах из
// Salaires.jar (org/h2/**, MySQL JDBC): порог переключения на
// экспоненциальную запись у Python - НЕ то же самое, что "общий" формат
// std::to_chars по умолчанию выбирает сам. У Python порог завязан на
// decpt (позицию десятичной точки относительно первой значащей цифры
// кратчайшего представления): экспонента используется только при
// `decpt <= -4 || decpt > 16` - иначе всегда фиксированная запись, даже
// для больших чисел вроде 1e12 (-> "1000000000000.0", НЕ "1e+12").
std::string python_float_repr(double v) {
    if (std::isnan(v)) return "nan";
    if (std::isinf(v)) return v > 0 ? "inf" : "-inf";
    bool neg = std::signbit(v);
    double av = std::fabs(v);
    if (av == 0.0) return neg ? "-0.0" : "0.0";

    char buf[64];
    // scientific формат гарантирует ровно одну цифру до точки - удобно для
    // извлечения "чистых" кратчайших цифр + десятичной экспоненты.
    auto res = std::to_chars(buf, buf + sizeof(buf), av, std::chars_format::scientific);
    std::string s(buf, res.ptr);
    auto epos = s.find('e');
    std::string mantissa = s.substr(0, epos);
    int exp = std::stoi(s.substr(epos + 1));
    std::string digits;
    for (char c : mantissa) if (c != '.') digits += c;
    int decpt = exp + 1;  // позиция десятичной точки относительно digits[0]

    std::string result;
    if (decpt <= -4 || decpt > 16) {
        result += digits[0];
        if (digits.size() > 1) {
            result += '.';
            result += digits.substr(1);
        }
        result += 'e';
        int e2 = decpt - 1;
        result += (e2 >= 0 ? '+' : '-');
        std::string edigits = std::to_string(std::abs(e2));
        while (edigits.size() < 2) edigits = "0" + edigits;  // минимум 2 цифры экспоненты
        result += edigits;
    } else if (decpt <= 0) {
        result = "0." + std::string(-decpt, '0') + digits;
    } else if (decpt >= static_cast<int>(digits.size())) {
        result = digits + std::string(decpt - digits.size(), '0') + ".0";
    } else {
        result = digits.substr(0, decpt) + "." + digits.substr(decpt);
    }
    return neg ? ("-" + result) : result;
}

// Аналог Python repr(str) для константных строк constant pool (Utf8/String).
// Выбор кавычек, экранирование - как в CPython: одинарные кавычки по
// умолчанию, двойные - если строка содержит ' и не содержит ".
// s ожидается УЖЕ нормализованной через normalize_utf8_replace (валидный
// UTF-8) - как и хранится в CpEntry.utf8_value после фикса парсинга.
std::string python_repr_str(const std::string& s) {
    std::u32string cps = decode_utf8_replace(s);  // на чистом UTF-8 - без изменений, просто декодирование
    bool has_single = false, has_double = false;
    for (char32_t cp : cps) {
        if (cp == U'\'') has_single = true;
        if (cp == U'"') has_double = true;
    }
    char32_t quote = (has_single && !has_double) ? U'"' : U'\'';
    std::string out;
    out += static_cast<char>(quote);
    for (char32_t cp : cps) {
        if (cp == quote) {
            out += '\\';
            out += static_cast<char>(quote);
        } else if (cp == U'\\') {
            out += "\\\\";
        } else if (cp == U'\n') {
            out += "\\n";
        } else if (cp == U'\r') {
            out += "\\r";
        } else if (cp == U'\t') {
            out += "\\t";
        } else if (is_printable_codepoint(cp)) {
            encode_utf8_append(cp, out);
        } else {
            char buf[16];
            if (cp < 0x100) std::snprintf(buf, sizeof(buf), "\\x%02x", static_cast<unsigned>(cp));
            else if (cp < 0x10000) std::snprintf(buf, sizeof(buf), "\\u%04x", static_cast<unsigned>(cp));
            else std::snprintf(buf, sizeof(buf), "\\U%08x", static_cast<unsigned>(cp));
            out += buf;
        }
    }
    out += static_cast<char>(quote);
    return out;
}

}  // namespace

// ---------- Reader ----------

uint8_t Reader::u1() {
    if (pos_ >= data_.size()) throw ClassFormatError("u1: unexpected EOF");
    return data_[pos_++];
}

uint16_t Reader::u2() {
    if (pos_ + 2 > data_.size()) throw ClassFormatError("u2: unexpected EOF");
    uint16_t v = (uint16_t(data_[pos_]) << 8) | uint16_t(data_[pos_ + 1]);
    pos_ += 2;
    return v;
}

uint32_t Reader::u4() {
    if (pos_ + 4 > data_.size()) throw ClassFormatError("u4: unexpected EOF");
    uint32_t v = (uint32_t(data_[pos_]) << 24) | (uint32_t(data_[pos_ + 1]) << 16) |
                 (uint32_t(data_[pos_ + 2]) << 8) | uint32_t(data_[pos_ + 3]);
    pos_ += 4;
    return v;
}

int8_t Reader::s1() { return static_cast<int8_t>(u1()); }
int16_t Reader::s2() { return static_cast<int16_t>(u2()); }
int32_t Reader::s4() { return static_cast<int32_t>(u4()); }

std::vector<uint8_t> Reader::bytes(size_t n) {
    if (pos_ + n > data_.size()) throw ClassFormatError("bytes: unexpected EOF");
    std::vector<uint8_t> v(data_.begin() + pos_, data_.begin() + pos_ + n);
    pos_ += n;
    return v;
}

void Reader::skip(size_t n) {
    if (pos_ + n > data_.size()) throw ClassFormatError("skip: unexpected EOF");
    pos_ += n;
}

// ---------- helpers ----------

std::string cp_tag_name(CpTag t) {
    switch (t) {
        case CpTag::Utf8: return "Utf8";
        case CpTag::Integer: return "Integer";
        case CpTag::Float: return "Float";
        case CpTag::Long: return "Long";
        case CpTag::Double: return "Double";
        case CpTag::Class: return "Class";
        case CpTag::String: return "String";
        case CpTag::Fieldref: return "Fieldref";
        case CpTag::Methodref: return "Methodref";
        case CpTag::InterfaceMethodref: return "InterfaceMethodref";
        case CpTag::NameAndType: return "NameAndType";
        case CpTag::MethodHandle: return "MethodHandle";
        case CpTag::MethodType: return "MethodType";
        case CpTag::Dynamic: return "Dynamic";
        case CpTag::InvokeDynamic: return "InvokeDynamic";
        case CpTag::Module: return "Module";
        case CpTag::Package: return "Package";
        default: return "Unknown";
    }
}

static CpTag tag_from_id(uint8_t id) {
    switch (id) {
        case 1: return CpTag::Utf8;
        case 3: return CpTag::Integer;
        case 4: return CpTag::Float;
        case 5: return CpTag::Long;
        case 6: return CpTag::Double;
        case 7: return CpTag::Class;
        case 8: return CpTag::String;
        case 9: return CpTag::Fieldref;
        case 10: return CpTag::Methodref;
        case 11: return CpTag::InterfaceMethodref;
        case 12: return CpTag::NameAndType;
        case 15: return CpTag::MethodHandle;
        case 16: return CpTag::MethodType;
        case 17: return CpTag::Dynamic;
        case 18: return CpTag::InvokeDynamic;
        case 19: return CpTag::Module;
        case 20: return CpTag::Package;
        default: return CpTag::Unknown;
    }
}

static std::string replace_slashes(std::string s) {
    for (auto& c : s) if (c == '/') c = '.';
    return s;
}

// ---------- access_str (аналог access_str в classfile.py) ----------

namespace {
struct AccFlag { uint16_t bit; const char* name; };
// порядок и значения ДОЛЖНЫ совпадать с ACC_FLAGS в Python
const AccFlag kAccFlags[] = {
    {0x0001, "public"}, {0x0002, "private"}, {0x0004, "protected"},
    {0x0008, "static"}, {0x0010, "final"}, {0x0020, "synchronized"},
    {0x0040, "volatile/bridge"}, {0x0080, "transient/varargs"},
    {0x0200, "interface"}, {0x0400, "abstract"}, {0x0800, "strict"},
    {0x1000, "synthetic"}, {0x2000, "annotation"}, {0x4000, "enum"},
};
}  // namespace

std::string access_str(uint16_t flags, const std::string& kind) {
    std::string out;
    bool first = true;
    for (const auto& af : kAccFlags) {
        if (!(flags & af.bit)) continue;
        if (af.bit == 0x0040 || af.bit == 0x0080 || af.bit == 0x1000 || af.bit == 0x0800) {
            if (kind == "field" && af.bit == 0x0040) {
                if (!first) out += ' ';
                out += "volatile";
                first = false;
            } else if (kind == "field" && af.bit == 0x0080) {
                if (!first) out += ' ';
                out += "transient";
                first = false;
            }
            continue;
        }
        // name.split("/")[0]
        std::string n = af.name;
        auto slash = n.find('/');
        if (slash != std::string::npos) n = n.substr(0, slash);
        if (!first) out += ' ';
        out += n;
        first = false;
    }
    return out;
}

// ---------- ClassFile: constant-pool resolution ----------

const std::string* ClassFile::utf8(uint16_t idx) const {
    auto it = pool.find(idx);
    if (it == pool.end() || it->second.tag != CpTag::Utf8) return nullptr;
    return &it->second.utf8_value;
}

std::optional<std::string> ClassFile::class_name(uint16_t idx) const {
    auto it = pool.find(idx);
    if (it == pool.end() || it->second.tag != CpTag::Class) return std::nullopt;
    const std::string* s = utf8(it->second.idx1);
    if (!s) return std::nullopt;
    return *s;
}

std::optional<std::pair<std::string, std::string>> ClassFile::name_and_type(uint16_t idx) const {
    auto it = pool.find(idx);
    if (it == pool.end() || it->second.tag != CpTag::NameAndType) return std::nullopt;
    const std::string* n = utf8(it->second.idx1);
    const std::string* t = utf8(it->second.idx2);
    if (!n || !t) return std::nullopt;
    return std::make_pair(*n, *t);
}

std::optional<std::tuple<std::string, std::string, std::string>> ClassFile::ref_string(uint16_t idx) const {
    auto it = pool.find(idx);
    if (it == pool.end()) return std::nullopt;
    CpTag t = it->second.tag;
    if (t != CpTag::Fieldref && t != CpTag::Methodref && t != CpTag::InterfaceMethodref) return std::nullopt;
    auto owner = class_name(it->second.idx1);
    auto nt = name_and_type(it->second.idx2);
    std::string owner_s = owner.value_or("?");
    if (!nt) return std::make_tuple(owner_s, std::string("?"), std::string("?"));
    return std::make_tuple(owner_s, nt->first, nt->second);
}

std::string ClassFile::describe_cp(uint16_t idx) const {
    auto it = pool.find(idx);
    if (it == pool.end()) return "#" + std::to_string(idx);
    const CpEntry& e = it->second;
    switch (e.tag) {
        case CpTag::Utf8:
            return python_repr_str(e.utf8_value);
        case CpTag::Integer:
            return std::to_string(e.int_value);
        case CpTag::Float:
            return python_float_repr(e.float_value) + "f";
        case CpTag::Long:
            return std::to_string(e.int_value) + "L";
        case CpTag::Double:
            return python_float_repr(e.float_value) + "d";
        case CpTag::String: {
            const std::string* s = utf8(e.idx1);
            if (s) return python_repr_str(*s);
            return "#" + std::to_string(e.idx1);
        }
        case CpTag::Class: {
            const std::string* n = utf8(e.idx1);
            return n ? replace_slashes(*n) : ("#" + std::to_string(e.idx1));
        }
        case CpTag::Fieldref:
        case CpTag::Methodref:
        case CpTag::InterfaceMethodref: {
            auto r = ref_string(idx);
            if (!r) return "#" + std::to_string(idx);
            std::string owner_dot = replace_slashes(std::get<0>(*r).empty() ? "?" : std::get<0>(*r));
            return owner_dot + "." + std::get<1>(*r) + ":" + std::get<2>(*r);
        }
        case CpTag::NameAndType: {
            auto nt = name_and_type(idx);
            if (!nt) return "#" + std::to_string(idx);
            return nt->first + ":" + nt->second;
        }
        case CpTag::InvokeDynamic: {
            auto nt = name_and_type(e.idx2);
            if (!nt) return "#" + std::to_string(idx);
            return "invokedynamic " + nt->first + ":" + nt->second;
        }
        case CpTag::MethodType: {
            const std::string* s = utf8(e.idx1);
            return s ? *s : ("#" + std::to_string(idx));
        }
        default:
            return "#" + std::to_string(idx) + "(" + cp_tag_name(e.tag) + ")";
    }
}

// ---------- parsing ----------

ClassFile::ClassFile(const std::vector<uint8_t>& data) { parse(data); }

std::optional<std::tuple<int, std::string, std::string, std::string>> ClassFile::method_handle_ref(uint16_t mh_cp_index) const {
    auto it = pool.find(mh_cp_index);
    if (it == pool.end() || it->second.tag != CpTag::MethodHandle) return std::nullopt;
    int kind = it->second.idx1;
    uint16_t ref_idx = it->second.idx2;
    auto r = ref_string(ref_idx);
    if (!r) return std::nullopt;
    return std::make_tuple(kind, std::get<0>(*r), std::get<1>(*r), std::get<2>(*r));
}

void ClassFile::parse(const std::vector<uint8_t>& data) {
    Reader r(data);
    uint32_t magic = r.u4();
    if (magic != 0xCAFEBABEu) throw ClassFormatError("Не class-файл (bad magic)");
    minor = r.u2();
    major = r.u2();
    uint16_t cp_count = r.u2();

    uint16_t i = 1;
    while (i < cp_count) {
        uint8_t tag_id = r.u1();
        CpTag tag = tag_from_id(tag_id);
        CpEntry e;
        e.tag = tag;
        switch (tag) {
            case CpTag::Utf8: {
                uint16_t length = r.u2();
                auto raw = r.bytes(length);
                std::string raw_str(raw.begin(), raw.end());
                e.utf8_value = normalize_utf8_replace(raw_str);  // как Python: decode("utf-8", errors="replace")
                break;
            }
            case CpTag::Class: {
                e.idx1 = r.u2();
                break;
            }
            case CpTag::Fieldref:
            case CpTag::Methodref:
            case CpTag::InterfaceMethodref: {
                e.idx1 = r.u2();
                e.idx2 = r.u2();
                break;
            }
            case CpTag::String: {
                e.idx1 = r.u2();
                break;
            }
            case CpTag::Integer: {
                e.int_value = r.s4();
                break;
            }
            case CpTag::Float: {
                int32_t bits = r.s4();
                float f;
                std::memcpy(&f, &bits, sizeof(f));
                e.float_value = f;
                break;
            }
            case CpTag::Long: {
                uint32_t hi = r.u4(), lo = r.u4();
                uint64_t v = (uint64_t(hi) << 32) | uint64_t(lo);
                e.int_value = static_cast<int64_t>(v);
                pool[i] = e;
                i += 1;  // Long занимает 2 слота constant pool (JVM spec)
                i += 1;
                continue;
            }
            case CpTag::Double: {
                uint32_t hi = r.u4(), lo = r.u4();
                uint64_t bits = (uint64_t(hi) << 32) | uint64_t(lo);
                double d;
                std::memcpy(&d, &bits, sizeof(d));
                e.float_value = d;
                pool[i] = e;
                i += 1;  // Double тоже занимает 2 слота
                i += 1;
                continue;
            }
            case CpTag::NameAndType: {
                e.idx1 = r.u2();
                e.idx2 = r.u2();
                break;
            }
            case CpTag::MethodHandle: {
                uint8_t kind = r.u1();
                uint16_t ref_idx = r.u2();
                e.idx1 = kind;
                e.idx2 = ref_idx;
                break;
            }
            case CpTag::MethodType: {
                e.idx1 = r.u2();
                break;
            }
            case CpTag::Dynamic:
            case CpTag::InvokeDynamic: {
                e.idx1 = r.u2();  // bsm_idx
                e.idx2 = r.u2();  // nt_idx
                break;
            }
            case CpTag::Module:
            case CpTag::Package: {
                e.idx1 = r.u2();
                break;
            }
            default:
                throw ClassFormatError("Неизвестный constant pool tag " + std::to_string(tag_id) +
                                        " на позиции " + std::to_string(r.pos()));
        }
        pool[i] = e;
        i += 1;
    }

    access = r.u2();
    uint16_t this_idx = r.u2();
    uint16_t super_idx = r.u2();
    this_class_name = class_name(this_idx).value_or("Unknown");
    super_class_name = super_idx ? class_name(super_idx) : std::nullopt;

    uint16_t iface_count = r.u2();
    for (uint16_t k = 0; k < iface_count; ++k) {
        uint16_t idx = r.u2();
        interfaces.push_back(class_name(idx));
    }

    // Примечание для ревьюеров порта: имя/дескриптор читаются в отдельные
    // локальные переменные ДО обращения к ним - в C++, в отличие от Python,
    // порядок вычисления операндов в одном выражении не гарантирован, так
    // что r.u2() нельзя звать дважды "инлайново" в одной строке.
    uint16_t field_count = r.u2();
    for (uint16_t k = 0; k < field_count; ++k) {
        Field f;
        f.access = r.u2();
        uint16_t name_idx = r.u2();
        uint16_t desc_idx = r.u2();
        {
            const std::string* n = utf8(name_idx);
            f.name = n ? *n : "";
        }
        {
            const std::string* d = utf8(desc_idx);
            f.descriptor = d ? *d : "";
        }
        uint16_t attr_count = r.u2();
        for (uint16_t a = 0; a < attr_count; ++a) {
            uint16_t a_name_idx = r.u2();
            const std::string* a_name_p = utf8(a_name_idx);
            std::string a_name = a_name_p ? *a_name_p : "";
            uint32_t a_len = r.u4();
            auto a_data = r.bytes(a_len);
            if (a_name == "ConstantValue" && a_data.size() >= 2) {
                uint16_t cv_idx = (uint16_t(a_data[0]) << 8) | uint16_t(a_data[1]);
                auto pit = pool.find(cv_idx);
                if (pit != pool.end()) f.constant_value = pit->second;
            } else if (a_name == "RuntimeVisibleAnnotations" || a_name == "RuntimeInvisibleAnnotations") {
                auto parsed = parse_annotations_attr(a_data);
                f.annotations.insert(f.annotations.end(), parsed.begin(), parsed.end());
            } else if (a_name == "Signature" && a_data.size() >= 2) {
                uint16_t sig_idx = (uint16_t(a_data[0]) << 8) | uint16_t(a_data[1]);
                const std::string* s = utf8(sig_idx);
                if (s) f.signature = *s;
            }
        }
        fields.push_back(std::move(f));
    }

    uint16_t method_count = r.u2();
    for (uint16_t k = 0; k < method_count; ++k) {
        Method m;
        m.access = r.u2();
        uint16_t name_idx = r.u2();
        uint16_t desc_idx = r.u2();
        {
            const std::string* n = utf8(name_idx);
            m.name = n ? *n : "";
        }
        {
            const std::string* d = utf8(desc_idx);
            m.descriptor = d ? *d : "";
        }
        uint16_t attr_count = r.u2();
        for (uint16_t a = 0; a < attr_count; ++a) {
            uint16_t a_name_idx = r.u2();
            const std::string* a_name_p = utf8(a_name_idx);
            std::string a_name = a_name_p ? *a_name_p : "";
            uint32_t a_len = r.u4();
            auto a_data = r.bytes(a_len);
            if (a_name == "Code") {
                parse_code(m, a_data);
            } else if (a_name == "RuntimeVisibleAnnotations" || a_name == "RuntimeInvisibleAnnotations") {
                auto parsed = parse_annotations_attr(a_data);
                m.annotations.insert(m.annotations.end(), parsed.begin(), parsed.end());
            } else if (a_name == "RuntimeVisibleParameterAnnotations" || a_name == "RuntimeInvisibleParameterAnnotations") {
                auto parsed = parse_param_annotations_attr(a_data);
                if (m.param_annotations.empty()) {
                    m.param_annotations = std::move(parsed);
                } else {
                    for (size_t pidx = 0; pidx < parsed.size(); ++pidx) {
                        if (pidx < m.param_annotations.size()) {
                            auto& dst = m.param_annotations[pidx];
                            auto& src = parsed[pidx];
                            dst.insert(dst.end(), src.begin(), src.end());
                        }
                    }
                }
            } else if (a_name == "Signature" && a_data.size() >= 2) {
                uint16_t sig_idx = (uint16_t(a_data[0]) << 8) | uint16_t(a_data[1]);
                const std::string* s = utf8(sig_idx);
                if (s) m.signature = *s;
            }
        }
        methods.push_back(std::move(m));
    }

    uint16_t class_attr_count = r.u2();
    for (uint16_t a = 0; a < class_attr_count; ++a) {
        uint16_t a_name_idx = r.u2();
        const std::string* a_name_p = utf8(a_name_idx);
        std::string a_name = a_name_p ? *a_name_p : "";
        uint32_t a_len = r.u4();
        auto a_data = r.bytes(a_len);
        if (a_name == "SourceFile" && a_data.size() >= 2) {
            uint16_t sf_idx = (uint16_t(a_data[0]) << 8) | uint16_t(a_data[1]);
            const std::string* s = utf8(sf_idx);
            if (s) source_file = *s;
        } else if (a_name == "RuntimeVisibleAnnotations" || a_name == "RuntimeInvisibleAnnotations") {
            auto parsed = parse_annotations_attr(a_data);
            annotations.insert(annotations.end(), parsed.begin(), parsed.end());
        } else if (a_name == "Signature" && a_data.size() >= 2) {
            uint16_t sig_idx = (uint16_t(a_data[0]) << 8) | uint16_t(a_data[1]);
            const std::string* s = utf8(sig_idx);
            if (s) signature = *s;
        } else if (a_name == "BootstrapMethods") {
            Reader ar(a_data);
            uint16_t n = ar.u2();
            for (uint16_t b = 0; b < n; ++b) {
                BootstrapMethod bm;
                bm.method_handle_idx = ar.u2();
                uint16_t argc = ar.u2();
                for (uint16_t c = 0; c < argc; ++c) bm.args.push_back(ar.u2());
                bootstrap_methods.push_back(std::move(bm));
            }
        } else if (a_name == "InnerClasses") {
            Reader ar(a_data);
            uint16_t n = ar.u2();
            for (uint16_t b = 0; b < n; ++b) {
                uint16_t inner_idx = ar.u2();
                uint16_t outer_idx = ar.u2();
                uint16_t name_idx = ar.u2();
                uint16_t iacc = ar.u2();
                InnerClassEntry ic;
                ic.inner = class_name(inner_idx);
                ic.outer = outer_idx ? class_name(outer_idx) : std::nullopt;
                ic.inner_name = name_idx ? (utf8(name_idx) ? std::make_optional(*utf8(name_idx)) : std::nullopt) : std::nullopt;
                ic.access = iacc;
                inner_classes.push_back(std::move(ic));
            }
        } else if (a_name == "Record") {
            // HANDOFF_50: JVM spec 4.7.30 (JDK 16+) - список компонентов
            // record'а (имя + дескриптор), в объявленном порядке. Каждый
            // компонент может нести свои вложенные атрибуты (Signature,
            // аннотации) - осознанно НЕ разбираем их здесь (генерик-тип
            // компонента и так уже виден через обычный accessor-метод
            // того же имени, у которого Signature разбирается как для
            // любого метода) - для рендеринга `record Name(...) {}`
            // достаточно имени+дескриптора в исходном порядке объявления.
            Reader ar(a_data);
            uint16_t n = ar.u2();
            for (uint16_t b = 0; b < n; ++b) {
                uint16_t name_idx = ar.u2();
                uint16_t desc_idx = ar.u2();
                uint16_t attrs_n = ar.u2();
                for (uint16_t k = 0; k < attrs_n; ++k) {
                    ar.u2();  // attribute_name_index (пропускаем)
                    uint32_t alen = ar.u4();
                    ar.bytes(alen);  // пропускаем содержимое вложенного атрибута целиком
                }
                RecordComponent rc;
                const std::string* nm = utf8(name_idx);
                const std::string* ds = utf8(desc_idx);
                rc.name = nm ? *nm : "";
                rc.descriptor = ds ? *ds : "";
                record_components.push_back(std::move(rc));
            }
        }
    }
}

AnnotationValuePtr ClassFile::parse_element_value(Reader& r) {
    char tag = static_cast<char>(r.u1());
    auto v = std::make_shared<AnnotationValue>();
    if (tag == 'B' || tag == 'C' || tag == 'D' || tag == 'F' || tag == 'I' || tag == 'J' ||
        tag == 'S' || tag == 'Z') {
        uint16_t idx = r.u2();
        auto it = pool.find(idx);
        if (tag == 'Z') {
            v->kind = AnnotationValue::Kind::Bool;
            v->bool_v = (it != pool.end()) && (it->second.int_value != 0);
        } else if (tag == 'C') {
            v->kind = AnnotationValue::Kind::Char;
            v->int_v = (it != pool.end()) ? it->second.int_value : 0;
        } else if (tag == 'D' || tag == 'F') {
            v->kind = (tag == 'D') ? AnnotationValue::Kind::Double : AnnotationValue::Kind::Float;
            v->dbl_v = (it != pool.end()) ? it->second.float_value : 0.0;
        } else if (tag == 'J') {
            v->kind = AnnotationValue::Kind::Long;
            v->int_v = (it != pool.end()) ? it->second.int_value : 0;
        } else {
            v->kind = AnnotationValue::Kind::Int;
            v->int_v = (it != pool.end()) ? it->second.int_value : 0;
        }
        return v;
    }
    if (tag == 's') {
        uint16_t idx = r.u2();
        const std::string* s = utf8(idx);
        v->kind = AnnotationValue::Kind::Str;
        v->str_v = s ? *s : "";
        return v;
    }
    if (tag == 'e') {
        r.u2();  // type_name_index - не нужен (см. classfile.py)
        uint16_t const_name_idx = r.u2();
        const std::string* s = utf8(const_name_idx);
        v->kind = AnnotationValue::Kind::Str;
        v->str_v = s ? *s : "";
        return v;
    }
    if (tag == 'c') {
        uint16_t idx = r.u2();
        const std::string* s = utf8(idx);
        v->kind = AnnotationValue::Kind::Str;
        v->str_v = s ? *s : "";
        return v;
    }
    if (tag == '@') {
        v->kind = AnnotationValue::Kind::Annotation;
        v->ann_v = std::make_shared<Annotation>(parse_annotation(r));
        return v;
    }
    if (tag == '[') {
        uint16_t n = r.u2();
        v->kind = AnnotationValue::Kind::Array;
        for (uint16_t i = 0; i < n; ++i) v->arr_v.push_back(parse_element_value(r));
        return v;
    }
    v->kind = AnnotationValue::Kind::None;
    return v;
}

Annotation ClassFile::parse_annotation(Reader& r) {
    Annotation ann;
    uint16_t type_idx = r.u2();
    const std::string* t = utf8(type_idx);
    ann.type = t ? *t : "";
    uint16_t num_pairs = r.u2();
    for (uint16_t i = 0; i < num_pairs; ++i) {
        uint16_t name_idx = r.u2();
        const std::string* n = utf8(name_idx);
        ann.args.emplace_back(n ? *n : "", parse_element_value(r));
    }
    return ann;
}

std::vector<Annotation> ClassFile::parse_annotations_attr(const std::vector<uint8_t>& a_data) {
    Reader ar(a_data);
    uint16_t n = ar.u2();
    std::vector<Annotation> out;
    out.reserve(n);
    for (uint16_t i = 0; i < n; ++i) out.push_back(parse_annotation(ar));
    return out;
}

std::vector<std::vector<Annotation>> ClassFile::parse_param_annotations_attr(const std::vector<uint8_t>& a_data) {
    Reader ar(a_data);
    uint8_t num_params = ar.u1();
    std::vector<std::vector<Annotation>> out;
    out.reserve(num_params);
    for (uint8_t i = 0; i < num_params; ++i) {
        uint16_t n = ar.u2();
        std::vector<Annotation> lst;
        lst.reserve(n);
        for (uint16_t j = 0; j < n; ++j) lst.push_back(parse_annotation(ar));
        out.push_back(std::move(lst));
    }
    return out;
}

void ClassFile::parse_code(Method& method, const std::vector<uint8_t>& a_data) {
    Reader cr(a_data);
    method.max_stack = cr.u2();
    method.max_locals = cr.u2();
    uint32_t code_len = cr.u4();
    method.code = cr.bytes(code_len);
    method.has_code = true;
    uint16_t exc_len = cr.u2();
    for (uint16_t i = 0; i < exc_len; ++i) {
        ExceptionEntry ex;
        ex.start_pc = cr.u2();
        ex.end_pc = cr.u2();
        ex.handler_pc = cr.u2();
        uint16_t catch_idx = cr.u2();
        ex.catch_type = catch_idx ? class_name(catch_idx) : std::nullopt;
        method.exceptions.push_back(ex);
    }
    uint16_t code_attr_count = cr.u2();
    for (uint16_t i = 0; i < code_attr_count; ++i) {
        uint16_t a_name_idx = cr.u2();
        uint32_t sub_len = cr.u4();
        const std::string* a_name_p = utf8(a_name_idx);
        std::string a_name = a_name_p ? *a_name_p : "";
        if (a_name == "LocalVariableTable") {
            auto sub_data = cr.bytes(sub_len);
            Reader sr(sub_data);
            uint16_t n = sr.u2();
            for (uint16_t k = 0; k < n; ++k) {
                uint16_t start_pc = sr.u2();
                uint16_t length = sr.u2();
                uint16_t name_idx = sr.u2();
                uint16_t desc_idx = sr.u2();
                uint16_t slot = sr.u2();
                const std::string* name = utf8(name_idx);
                const std::string* desc = utf8(desc_idx);
                if (name && *name != "this") {
                    LocalVarEntry lv;
                    lv.start_pc = start_pc;
                    lv.length = length;
                    lv.name = *name;
                    lv.descriptor = desc ? *desc : "";
                    lv.slot = slot;
                    method.local_var_table.push_back(lv);
                }
            }
        } else {
            cr.skip(sub_len);  // LineNumberTable/LocalVariableTypeTable/StackMapTable - не нужны
        }
    }
}

}  // namespace nd
