// aes128.hpp - порт resources/engine/aes128.py (v2.0, HANDOFF_30,
// модуль 7a). AES-128, ТОЛЬКО расшифровка, режим ECB - без внешних
// зависимостей (тот же принцип, что у Python-оригинала).
#pragma once

#include <array>
#include <cstdint>
#include <stdexcept>
#include <vector>

namespace nd {

using AesBlock = std::array<uint8_t, 16>;
using AesRoundKeys = std::array<AesBlock, 11>;  // 11 раундовых ключей (AES-128 = 10 раундов + начальный)

AesRoundKeys aes_key_expansion_128(const AesBlock& key16);
AesBlock aes_decrypt_block(const AesBlock& block16, const AesRoundKeys& round_keys);

// Бросает std::invalid_argument, если длина не кратна 16 или ciphertext пуст
// (зеркалит ValueError в Python).
std::vector<uint8_t> aes128_ecb_decrypt(const std::vector<uint8_t>& ciphertext, const AesBlock& key16);

// Бросает std::invalid_argument на некорректном паддинге (зеркалит ValueError).
std::vector<uint8_t> unpad_pkcs7(const std::vector<uint8_t>& data);

}  // namespace nd
