// str_decrypt.cpp - см. str_decrypt.hpp. 1:1 порт str_decrypt.py.
//
// СХЕМА (как есть у этого конкретного обфускатора, НЕ общий случай):
//   - В классе-расшифровщике в constant pool встречается Utf8-строка
//     "AES/ECB/PKCS5Padding" (маркер).
//   - В классе есть два int-поля с ConstantValue (h, l) - зерно ключа.
//   - buildCipher(h, l): 8 байт (big-endian h, потом big-endian l) ->
//     SHA-256 -> первые 16 байт как AES-128 ключ.
//   - Расшифровка: строка на входе - Base64, AES-128-ECB, PKCS7-паддинг.
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
        if (f.constant_value.has_value() && f.constant_value->tag == CpTag::Integer) {
            ints.push_back(static_cast<int32_t>(f.constant_value->int_value));
        }
    }
    if (ints.size() < 2) return std::nullopt;
    // Первые два найденных int-поля с ConstantValue - соответствует порядку
    // полей в оригинальном классе (h объявлено раньше l).
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
            // ВАЖНО: в оригинале `break` срабатывает здесь БЕЗУСЛОВНО (даже
            // если ниже decrypt_method_name не найдёт подходящий метод) -
            // предполагается, что класс с маркером - и есть искомый
            // расшифровщик, дальше по jar не ищем, даже если ACTIVE в итоге
            // останется не установлен.
            auto method = str_decrypt_method_name(*cf);
            if (method.has_value()) {
                ActiveDecryptor a;
                a.owner = internal;
                a.method = *method;
                a.key = *key;
                return a;
            }
            return std::nullopt;
        }
    }
    return std::nullopt;
}

}  // namespace nd
