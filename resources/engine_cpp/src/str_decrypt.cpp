// str_decrypt.cpp - см. str_decrypt.hpp. 1:1 порт str_decrypt.py.
//
// СХЕМА (как есть у этого конкретного обфускатора, НЕ общий случай):
//   - В классе-расшифровщике в constant pool встречается Utf8-строка
//     "AES/ECB/PKCS5Padding" (маркер).
//   - В классе есть два int-поля с ConstantValue (h, l) - зерно ключа.
//   - buildCipher(h, l): 8 байт (big-endian h, потом big-endian l) ->
//     SHA-256 -> первые 16 байт как AES-128 ключ.
//   - Расшифровка: строка на входе - Base64, AES-128-ECB, PKCS7-паддинг.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "str_decrypt.hpp"

#include "aes128.hpp"
#include "base64.hpp"
#include "sha256.hpp"

namespace nd {

namespace {

bool is_valid_utf8(const std::string& s) {
    size_t i = 0, n = s.size();
    while (i < n) {
        unsigned char c = static_cast<unsigned char>(s[i]);
        size_t extra;
        uint32_t cp;
        uint32_t min_cp;
        if (c < 0x80) { i += 1; continue; }
        else if ((c & 0xE0) == 0xC0) { extra = 1; cp = c & 0x1F; min_cp = 0x80; }
        else if ((c & 0xF0) == 0xE0) { extra = 2; cp = c & 0x0F; min_cp = 0x800; }
        else if ((c & 0xF8) == 0xF0) { extra = 3; cp = c & 0x07; min_cp = 0x10000; }
        else return false;
        if (i + extra >= n) return false;  // недостаточно байт продолжения
        for (size_t k = 1; k <= extra; ++k) {
            unsigned char cc = static_cast<unsigned char>(s[i + k]);
            if ((cc & 0xC0) != 0x80) return false;
            cp = (cp << 6) | (cc & 0x3F);
        }
        if (cp < min_cp) return false;                    // переусложнённое (overlong) кодирование
        if (cp > 0x10FFFF) return false;
        if (cp >= 0xD800 && cp <= 0xDFFF) return false;    // суррогатные пары запрещены в UTF-8
        i += extra + 1;
    }
    return true;
}

}  // namespace

bool str_decrypt_has_marker(const ClassFile& cf) {
    for (auto& [idx, e] : cf.pool) {
        if (e.tag == CpTag::Utf8 && e.utf8_value == STR_DECRYPT_MARKER_UTF8) return true;
    }
    return false;
}

namespace {
std::optional<ActiveDecryptor> g_active;
int g_decrypted_count = 0;
}  // namespace

void str_decrypt_set_active(const std::optional<ActiveDecryptor>& active) { g_active = active; }
const std::optional<ActiveDecryptor>& str_decrypt_get_active() { return g_active; }
void str_decrypt_reset_decrypted_count() { g_decrypted_count = 0; }
int str_decrypt_get_decrypted_count() { return g_decrypted_count; }
void str_decrypt_increment_decrypted_count() { g_decrypted_count += 1; }

std::array<uint8_t, 16> str_decrypt_build_key(int32_t h, int32_t l) {
    std::vector<uint8_t> seed(8);
    uint32_t uh = static_cast<uint32_t>(h);
    uint32_t ul = static_cast<uint32_t>(l);
    for (int i = 0; i < 4; ++i) seed[i] = static_cast<uint8_t>((uh >> (24 - i * 8)) & 0xff);
    for (int i = 0; i < 4; ++i) seed[4 + i] = static_cast<uint8_t>((ul >> (24 - i * 8)) & 0xff);
    auto digest = sha256(seed);
    std::array<uint8_t, 16> key{};
    for (int i = 0; i < 16; ++i) key[i] = digest[i];
    return key;
}

std::optional<std::string> str_decrypt_decrypt_string(const std::array<uint8_t, 16>& key16, const std::string& encoded) {
    auto raw_opt = base64_decode_strict(encoded);
    if (!raw_opt.has_value()) return std::nullopt;
    const std::vector<uint8_t>& raw = *raw_opt;
    if (raw.empty() || raw.size() % 16 != 0) return std::nullopt;
    try {
        std::vector<uint8_t> decrypted_padded = aes128_ecb_decrypt(raw, key16);
        std::vector<uint8_t> plain = unpad_pkcs7(decrypted_padded);
        std::string s(plain.begin(), plain.end());
        if (!is_valid_utf8(s)) return std::nullopt;
        return s;
    } catch (...) {
        return std::nullopt;
    }
}

std::optional<std::array<uint8_t, 16>> str_decrypt_find_decryptor_in_class(const ClassFile& cf) {
    if (!str_decrypt_has_marker(cf)) return std::nullopt;
    std::vector<int32_t> ints;
    for (auto& f : cf.fields) {
        if (f.constant_value.has_value()) {
            if (f.constant_value->tag == CpTag::Integer) {
                ints.push_back(static_cast<int32_t>(f.constant_value->int_value));
            } else if (f.constant_value->tag == CpTag::Long) {
                uint64_t lv = f.constant_value->long_value;
                ints.push_back(static_cast<int32_t>(lv >> 32));
                ints.push_back(static_cast<int32_t>(lv & 0xffffffffULL));
            }
        }
    }
    if (ints.size() < 2) {
        // Fallback: если ConstantValue атрибуты срезаны обфускатором,
        // извлекаем int/long-константы из статического инициализатора <clinit>.
        for (const auto& m : cf.methods) {
            if (m.name == "<clinit>" && m.has_code && !m.code.empty()) {
                const auto& code = m.code;
                size_t pc = 0, n = code.size();
                while (pc < n && ints.size() < 2) {
                    uint8_t op = code[pc];
                    if (op >= 0x02 && op <= 0x08) {  // iconst_m1 .. iconst_5
                        ints.push_back(static_cast<int32_t>(op - 0x03));
                        pc += 1;
                    } else if (op == 0x10 && pc + 1 < n) {  // bipush
                        ints.push_back(static_cast<int8_t>(code[pc + 1]));
                        pc += 2;
                    } else if (op == 0x11 && pc + 2 < n) {  // sipush
                        int16_t v = (static_cast<int16_t>(code[pc + 1]) << 8) | code[pc + 2];
                        ints.push_back(v);
                        pc += 3;
                    } else if (op == 0x12 && pc + 1 < n) {  // ldc
                        uint8_t cpidx = code[pc + 1];
                        auto it = cf.pool.find(cpidx);
                        if (it != cf.pool.end() && it->second.tag == CpTag::Integer) {
                            ints.push_back(static_cast<int32_t>(it->second.int_value));
                        }
                        pc += 2;
                    } else if (op == 0x13 && pc + 2 < n) {  // ldc_w
                        uint16_t cpidx = (static_cast<uint16_t>(code[pc + 1]) << 8) | code[pc + 2];
                        auto it = cf.pool.find(cpidx);
                        if (it != cf.pool.end() && it->second.tag == CpTag::Integer) {
                            ints.push_back(static_cast<int32_t>(it->second.int_value));
                        }
                        pc += 3;
                    } else if (op == 0x14 && pc + 2 < n) {  // ldc2_w (Long seed)
                        uint16_t cpidx = (static_cast<uint16_t>(code[pc + 1]) << 8) | code[pc + 2];
                        auto it = cf.pool.find(cpidx);
                        if (it != cf.pool.end() && it->second.tag == CpTag::Long) {
                            uint64_t lv = it->second.long_value;
                            ints.push_back(static_cast<int32_t>(lv >> 32));
                            ints.push_back(static_cast<int32_t>(lv & 0xffffffffULL));
                        }
                        pc += 3;
                    } else {
                        pc += 1;
                    }
                }
                break;
            }
        }
    }
    if (ints.size() < 2) return std::nullopt;
    // Первые два найденных int-поля/значения - соответствует порядку
    // инициализации ключа (h объявлено раньше l).
    return str_decrypt_build_key(ints[0], ints[1]);
}

std::optional<std::string> str_decrypt_method_name(const ClassFile& cf) {
    for (auto& m : cf.methods) {
        if ((m.access & 0x0008) && m.descriptor == "(Ljava/lang/String;)Ljava/lang/String;") {
            return m.name;
        }
    }
    return std::nullopt;
}

std::optional<ActiveDecryptor> find_active_decryptor_in_jar(
    const std::vector<std::pair<std::string, const ClassFile*>>& classes_in_order) {
    for (auto& [internal, cf] : classes_in_order) {
        auto key = str_decrypt_find_decryptor_in_class(*cf);
        if (key.has_value()) {
            auto method = str_decrypt_method_name(*cf);
            if (method.has_value()) {
                ActiveDecryptor a;
                a.owner = internal;
                a.method = *method;
                a.key = *key;
                return a;
            }
        }
    }
    return std::nullopt;
}

namespace {

std::vector<uint16_t> utf8_to_utf16(const std::string& s) {
    std::vector<uint16_t> out;
    size_t i = 0, n = s.size();
    while (i < n) {
        unsigned char c = static_cast<unsigned char>(s[i]);
        uint32_t cp = 0;
        size_t extra = 0;
        if (c < 0x80) {
            cp = c;
            extra = 0;
        } else if ((c & 0xE0) == 0xC0) {
            cp = c & 0x1F;
            extra = 1;
        } else if ((c & 0xF0) == 0xE0) {
            cp = c & 0x0F;
            extra = 2;
        } else if ((c & 0xF8) == 0xF0) {
            cp = c & 0x07;
            extra = 3;
        } else {
            out.push_back(c);
            i++;
            continue;
        }
        if (i + extra >= n) {
            out.push_back(c);
            i++;
            continue;
        }
        bool valid = true;
        for (size_t k = 1; k <= extra; ++k) {
            unsigned char cc = static_cast<unsigned char>(s[i + k]);
            if ((cc & 0xC0) != 0x80) { valid = false; break; }
            cp = (cp << 6) | (cc & 0x3F);
        }
        if (!valid) {
            out.push_back(c);
            i++;
            continue;
        }
        i += extra + 1;
        if (cp <= 0xFFFF) {
            out.push_back(static_cast<uint16_t>(cp));
        } else if (cp <= 0x10FFFF) {
            cp -= 0x10000;
            out.push_back(static_cast<uint16_t>(0xD800 + (cp >> 10)));
            out.push_back(static_cast<uint16_t>(0xDC00 + (cp & 0x3FF)));
        }
    }
    return out;
}

std::string utf16_to_utf8(const std::vector<uint16_t>& chars) {
    std::string out;
    size_t i = 0, n = chars.size();
    while (i < n) {
        uint32_t cp = chars[i++];
        if (cp >= 0xD800 && cp <= 0xDBFF && i < n) {
            uint32_t low = chars[i];
            if (low >= 0xDC00 && low <= 0xDFFF) {
                cp = 0x10000 + ((cp - 0xD800) << 10) + (low - 0xDC00);
                i++;
            }
        }
        if (cp < 0x80) {
            out.push_back(static_cast<char>(cp));
        } else if (cp < 0x800) {
            out.push_back(static_cast<char>(0xC0 | (cp >> 6)));
            out.push_back(static_cast<char>(0x80 | (cp & 0x3F)));
        } else if (cp < 0x10000) {
            out.push_back(static_cast<char>(0xE0 | (cp >> 12)));
            out.push_back(static_cast<char>(0x80 | ((cp >> 6) & 0x3F)));
            out.push_back(static_cast<char>(0x80 | (cp & 0x3F)));
        } else {
            out.push_back(static_cast<char>(0xF0 | (cp >> 18)));
            out.push_back(static_cast<char>(0x80 | ((cp >> 12) & 0x3F)));
            out.push_back(static_cast<char>(0x80 | ((cp >> 6) & 0x3F)));
            out.push_back(static_cast<char>(0x80 | (cp & 0x3F)));
        }
    }
    return out;
}

}  // namespace

std::optional<std::string> str_decrypt_xor(const std::string& input, int32_t key) {
    auto u16 = utf8_to_utf16(input);
    if (u16.empty()) return "";
    for (size_t i = 0; i < u16.size(); ++i) {
        u16[i] = static_cast<uint16_t>(u16[i] ^ static_cast<uint16_t>(key));
    }
    std::string res = utf16_to_utf8(u16);
    if (!is_valid_utf8(res)) return std::nullopt;
    size_t printable = 0;
    for (unsigned char c : res) {
        if ((c >= 32 && c <= 126) || c == '\t' || c == '\n' || c == '\r' || c >= 128) {
            printable++;
        }
    }
    if (printable * 10 < res.size() * 7) return std::nullopt;
    return res;
}

std::optional<std::string> str_decrypt_xor_multikey(const std::string& input, const std::string& key) {
    auto u16 = utf8_to_utf16(input);
    auto k16 = utf8_to_utf16(key);
    if (k16.empty() || u16.empty()) return std::nullopt;
    for (size_t i = 0; i < u16.size(); ++i) {
        u16[i] = static_cast<uint16_t>(u16[i] ^ k16[i % k16.size()]);
    }
    std::string res = utf16_to_utf8(u16);
    if (!is_valid_utf8(res)) return std::nullopt;
    size_t printable = 0;
    for (unsigned char c : res) {
        if ((c >= 32 && c <= 126) || c == '\t' || c == '\n' || c == '\r' || c >= 128) {
            printable++;
        }
    }
    if (printable * 10 < res.size() * 7) return std::nullopt;
    return res;
}

std::optional<std::string> str_decrypt_allatori(const std::string& input, int key1, int key2) {
    auto u16 = utf8_to_utf16(input);
    if (u16.empty()) return "";
    for (size_t i = 0; i < u16.size(); ++i) {
        int k = (i % 2 == 0) ? key1 : key2;
        u16[i] = static_cast<uint16_t>(u16[i] ^ static_cast<uint16_t>(k));
    }
    std::string res = utf16_to_utf8(u16);
    if (!is_valid_utf8(res)) return std::nullopt;
    size_t printable = 0;
    for (unsigned char c : res) {
        if ((c >= 32 && c <= 126) || c == '\t' || c == '\n' || c == '\r' || c >= 128) {
            printable++;
        }
    }
    if (printable * 10 < res.size() * 7) return std::nullopt;
    return res;
}

bool is_likely_xor_decryptor(const Method& m) {
    if (!(m.access & 0x0008)) return false;
    if (!m.has_code || m.code.empty()) return false;
    if (m.descriptor != "(Ljava/lang/String;I)Ljava/lang/String;" &&
        m.descriptor != "(Ljava/lang/String;C)Ljava/lang/String;" &&
        m.descriptor != "(Ljava/lang/String;)Ljava/lang/String;" &&
        m.descriptor != "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;" &&
        m.descriptor != "(Ljava/lang/String;II)Ljava/lang/String;") {
        return false;
    }
    bool has_xor = false;
    for (uint8_t op : m.code) {
        if (op == 0x82) { // ixor
            has_xor = true;
            break;
        }
    }
    return has_xor;
}

std::optional<int32_t> find_xor_fixed_key(const Method& m, const ClassFile& cf) {
    const auto& c = m.code;
    size_t pc = 0, n = c.size();
    while (pc < n) {
        uint8_t op = c[pc];
        if (op >= 0x02 && op <= 0x08) {
            if (pc + 1 < n && c[pc + 1] == 0x82) return static_cast<int32_t>(op - 0x03);
            pc += 1;
        } else if (op == 0x10 && pc + 1 < n) {
            if (pc + 2 < n && c[pc + 2] == 0x82) return static_cast<int32_t>(static_cast<int8_t>(c[pc + 1]));
            pc += 2;
        } else if (op == 0x11 && pc + 2 < n) {
            if (pc + 3 < n && c[pc + 3] == 0x82) {
                int16_t v = (static_cast<int16_t>(c[pc + 1]) << 8) | c[pc + 2];
                return static_cast<int32_t>(v);
            }
            pc += 3;
        } else if (op == 0x12 && pc + 1 < n) {
            if (pc + 2 < n && c[pc + 2] == 0x82) {
                auto it = cf.pool.find(c[pc + 1]);
                if (it != cf.pool.end() && it->second.tag == CpTag::Integer) {
                    return static_cast<int32_t>(it->second.int_value);
                }
            }
            pc += 2;
        } else {
            pc += 1;
        }
    }
    return std::nullopt;
}

}  // namespace nd
