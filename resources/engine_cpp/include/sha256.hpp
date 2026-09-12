// sha256.hpp - минимальная реализация SHA-256 (FIPS-180-4), без внешних
// зависимостей - нужна для str_decrypt.py::build_key (Python использует
// hashlib.sha256 из стандартной библиотеки; здесь эквивалент "с нуля",
// в том же духе, что и aes128.py - см. HANDOFF_30).
#pragma once

#include <array>
#include <cstdint>
#include <vector>

namespace nd {

std::array<uint8_t, 32> sha256(const std::vector<uint8_t>& data);

}  // namespace nd
