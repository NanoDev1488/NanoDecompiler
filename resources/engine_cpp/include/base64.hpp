// base64.hpp - строгий base64-декодер, аналог Python
// `base64.b64decode(s, validate=True)`: отвергает любые символы вне
// алфавита A-Za-z0-9+/= и некорректную длину/паддинг - нужен для
// str_decrypt.py (см. HANDOFF_30).
#pragma once

#include <cstdint>
#include <optional>
#include <string>
#include <vector>

namespace nd {

// nullopt при любой ошибке валидации (зеркалит `except Exception: return None`
// в decrypt_string, где вызывающий код str_decrypt.py ловит ЛЮБОЕ исключение
// b64decode).
std::optional<std::vector<uint8_t>> base64_decode_strict(const std::string& s);

}  // namespace nd
