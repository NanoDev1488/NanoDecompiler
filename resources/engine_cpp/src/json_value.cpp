// json_value.cpp - см. json_value.hpp. Компактный рекурсивный разбор JSON
// (RFC 8259) - только то, что нужно для чтения ответов простых REST API
// (объекты/массивы/строки/числа/bool/null, стандартные escape-последовательности).
#include "json_value.hpp"

#include <cctype>
#include <cmath>
#include <stdexcept>

namespace nd {

namespace {

class Parser {
public:
    explicit Parser(const std::string& s) : s_(s), i_(0), n_(s.size()) {}

    JsonValue parse_value() {
        skip_ws();
        if (i_ >= n_) throw std::runtime_error("unexpected end");
        char c = s_[i_];
        if (c == '{') return parse_object();
        if (c == '[') return parse_array();
        if (c == '"') return parse_string_value();
        if (c == 't' || c == 'f') return parse_bool();
        if (c == 'n') return parse_null();
        if (c == '-' || (c >= '0' && c <= '9')) return parse_number();
        throw std::runtime_error("unexpected character");
    }

    void skip_ws() {
        while (i_ < n_ && (s_[i_] == ' ' || s_[i_] == '\t' || s_[i_] == '\n' || s_[i_] == '\r')) i_ += 1;
    }

    void expect(char c) {
        if (i_ >= n_ || s_[i_] != c) throw std::runtime_error("expected char");
        i_ += 1;
    }

    JsonValue parse_object() {
        JsonValue v;
        v.kind = JsonValue::Kind::Object;
        v.obj_v = std::make_shared<JsonObject>();
        expect('{');
        skip_ws();
        if (i_ < n_ && s_[i_] == '}') { i_ += 1; return v; }
        while (true) {
            skip_ws();
            std::string key = parse_string_raw();
            skip_ws();
            expect(':');
            JsonValue val = parse_value();
            v.obj_v->emplace_back(std::move(key), std::move(val));
            skip_ws();
            if (i_ < n_ && s_[i_] == ',') { i_ += 1; continue; }
            break;
        }
        skip_ws();
        expect('}');
        return v;
    }

    JsonValue parse_array() {
        JsonValue v;
        v.kind = JsonValue::Kind::Array;
        v.arr_v = std::make_shared<JsonArray>();
        expect('[');
        skip_ws();
        if (i_ < n_ && s_[i_] == ']') { i_ += 1; return v; }
        while (true) {
            JsonValue val = parse_value();
            v.arr_v->push_back(std::move(val));
            skip_ws();
            if (i_ < n_ && s_[i_] == ',') { i_ += 1; continue; }
            break;
        }
        skip_ws();
        expect(']');
        return v;
    }

    std::string parse_string_raw() {
        expect('"');
        std::string out;
        while (i_ < n_ && s_[i_] != '"') {
            char c = s_[i_];
            if (c == '\\') {
                i_ += 1;
                if (i_ >= n_) throw std::runtime_error("bad escape");
                char e = s_[i_];
                switch (e) {
                    case '"': out += '"'; break;
                    case '\\': out += '\\'; break;
                    case '/': out += '/'; break;
                    case 'b': out += '\b'; break;
                    case 'f': out += '\f'; break;
                    case 'n': out += '\n'; break;
                    case 'r': out += '\r'; break;
                    case 't': out += '\t'; break;
                    case 'u': {
                        if (i_ + 4 >= n_) throw std::runtime_error("bad unicode escape");
                        unsigned code = 0;
                        for (int k = 1; k <= 4; ++k) {
                            char hc = s_[i_ + k];
                            code <<= 4;
                            if (hc >= '0' && hc <= '9') code |= (hc - '0');
                            else if (hc >= 'a' && hc <= 'f') code |= (hc - 'a' + 10);
                            else if (hc >= 'A' && hc <= 'F') code |= (hc - 'A' + 10);
                            else throw std::runtime_error("bad hex digit");
                        }
                        i_ += 4;
                        // Кодируем как UTF-8 (упрощённо - без обработки суррогатных пар,
                        // для полей типа названий репозиториев/проектов этого достаточно
                        // в подавляющем большинстве случаев; полные surrogate-пары вне
                        // Basic Multilingual Plane - редкость в этих API-ответах).
                        if (code < 0x80) {
                            out += static_cast<char>(code);
                        } else if (code < 0x800) {
                            out += static_cast<char>(0xC0 | (code >> 6));
                            out += static_cast<char>(0x80 | (code & 0x3F));
                        } else {
                            out += static_cast<char>(0xE0 | (code >> 12));
                            out += static_cast<char>(0x80 | ((code >> 6) & 0x3F));
                            out += static_cast<char>(0x80 | (code & 0x3F));
                        }
                        break;
                    }
                    default: throw std::runtime_error("bad escape char");
                }
                i_ += 1;
            } else {
                out += c;
                i_ += 1;
            }
        }
        expect('"');
        return out;
    }

    JsonValue parse_string_value() {
        JsonValue v;
        v.kind = JsonValue::Kind::String;
        v.str_v = parse_string_raw();
        return v;
    }

    JsonValue parse_bool() {
        JsonValue v;
        v.kind = JsonValue::Kind::Bool;
        if (s_.compare(i_, 4, "true") == 0) { v.bool_v = true; i_ += 4; }
        else if (s_.compare(i_, 5, "false") == 0) { v.bool_v = false; i_ += 5; }
        else throw std::runtime_error("bad literal");
        return v;
    }

    JsonValue parse_null() {
        if (s_.compare(i_, 4, "null") != 0) throw std::runtime_error("bad literal");
        i_ += 4;
        JsonValue v;
        v.kind = JsonValue::Kind::Null;
        return v;
    }

    JsonValue parse_number() {
        size_t start = i_;
        if (i_ < n_ && s_[i_] == '-') i_ += 1;
        while (i_ < n_ && std::isdigit(static_cast<unsigned char>(s_[i_]))) i_ += 1;
        if (i_ < n_ && s_[i_] == '.') {
            i_ += 1;
            while (i_ < n_ && std::isdigit(static_cast<unsigned char>(s_[i_]))) i_ += 1;
        }
        if (i_ < n_ && (s_[i_] == 'e' || s_[i_] == 'E')) {
            i_ += 1;
            if (i_ < n_ && (s_[i_] == '+' || s_[i_] == '-')) i_ += 1;
            while (i_ < n_ && std::isdigit(static_cast<unsigned char>(s_[i_]))) i_ += 1;
        }
        std::string num_str = s_.substr(start, i_ - start);
        JsonValue v;
        v.kind = JsonValue::Kind::Number;
        v.num_v = std::stod(num_str);
        return v;
    }

    const std::string& s_;
    size_t i_;
    size_t n_;
};

}  // namespace

std::optional<JsonValue> json_parse(const std::string& text) {
    try {
        Parser p(text);
        JsonValue v = p.parse_value();
        p.skip_ws();
        if (p.i_ != p.n_) return std::nullopt;  // мусор после значения - как json.loads, отклоняем
        return v;
    } catch (...) {
        return std::nullopt;
    }
}

}  // namespace nd
