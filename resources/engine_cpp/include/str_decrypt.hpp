// str_decrypt.hpp - порт resources/engine/str_decrypt.py (v2.0, HANDOFF_30,
// модуль 7b). Расшифровка строковых литералов ОДНОЙ конкретной, узнаваемой
// схемы обфускатора (см. подробное описание схемы в оригинале и в .cpp).
#pragma once

#include <array>
#include <cstdint>
#include <optional>
#include <string>
#include <vector>

#include "classfile.hpp"

namespace nd {

constexpr const char* STR_DECRYPT_MARKER_UTF8 = "AES/ECB/PKCS5Padding";

// Состояние "активного расшифровщика" для ТЕКУЩЕГО обрабатываемого jar -
// зеркалит module-level `str_decrypt.ACTIVE`/`str_decrypt.DECRYPTED_COUNT`
// в Python (глобальные переменные модуля, читаемые/пишемые из stackvm.py).
// В Python это буквально module-level globals; здесь - функции доступа,
// чтобы не тащить глобальные mutable-объекты по всему C++-коду напрямую.
struct ActiveDecryptor {
    std::string owner;   // internal-имя класса-расшифровщика
    std::string method;  // имя статического метода (String)->String
    std::array<uint8_t, 16> key{};
};
void str_decrypt_set_active(const std::optional<ActiveDecryptor>& active);
const std::optional<ActiveDecryptor>& str_decrypt_get_active();
void str_decrypt_reset_decrypted_count();
int str_decrypt_get_decrypted_count();
void str_decrypt_increment_decrypted_count();

// True, если в constant pool класса встречается точная строка-маркер схемы.
bool str_decrypt_has_marker(const ClassFile& cf);

// h, l - как в Java int (32-битные, со знаком). 16-байтовый AES-128 ключ.
std::array<uint8_t, 16> str_decrypt_build_key(int32_t h, int32_t l);

// encoded - Base64 (как встречается в LDC-константе байткода). nullopt,
// если что-то не сошлось (некорректный base64/длина/паддинг/UTF-8) - НЕ
// бросает исключение, вызывающий код просто оставляет оригинал как есть.
std::optional<std::string> str_decrypt_decrypt_string(const std::array<uint8_t, 16>& key16, const std::string& encoded);

// Если cf - класс-расшифровщик (есть маркер), извлекает (h, l) из первых
// двух int-полей с ConstantValue (в порядке объявления полей в классе) и
// возвращает 16-байтовый ключ. nullopt, если это не расшифровщик, либо
// подходящих полей не нашлось.
std::optional<std::array<uint8_t, 16>> str_decrypt_find_decryptor_in_class(const ClassFile& cf);

// Имя статического метода (String)->String в классе-расшифровщике -
// используется затем, чтобы найти МЕСТА ВЫЗОВА этого метода в остальном
// коде (invokestatic на этот метод).
std::optional<std::string> str_decrypt_method_name(const ClassFile& cf);

// Протокол из main.py (строки 737-753 оригинала): перебирает классы В
// ПОРЯДКЕ ИХ ПОЯВЛЕНИЯ В JAR (важно - порядок соответствует
// zip.namelist()), ищет ПЕРВЫЙ класс с маркером расшифровщика, возвращает
// его как активный (owner/method/key) - и ОСТАНАВЛИВАЕТСЯ (не ищет
// дальше, даже если в jar теоретически несколько маркеров). nullopt, если
// ни один класс не подошёл (обычный, неспиратенный/незашифрованный jar -
// абсолютное большинство реальных плагинов, включая все тестовые jar
// пользователя).
std::optional<ActiveDecryptor> find_active_decryptor_in_jar(
    const std::vector<std::pair<std::string, const ClassFile*>>& classes_in_order);

}  // namespace nd
