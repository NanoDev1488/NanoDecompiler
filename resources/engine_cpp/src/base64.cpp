// base64.cpp - см. base64.hpp.
#include "base64.hpp"

#include <algorithm>

namespace nd {

namespace {

int decode_char(char c) {
    if (c >= 'A' && c <= 'Z') return c - 'A';
    if (c >= 'a' && c <= 'z') return c - 'a' + 26;
    if (c >= '0' && c <= '9') return c - '0' + 52;
    if (c == '+') return 62;
    if (c == '/') return 63;
    return -1;
}

}  // namespace

std::optional<std::vector<uint8_t>> base64_decode_strict(const std::string& s) {
    // ВАЖНО: реальная CPython-реализация (`binascii.a2b_base64(s,
    // strict_mode=True)`, вызывается из base64.b64decode(..., validate=True))
    // - это C-код со своим стейт-машинным разбором, а НЕ простой regex +
    // "длина кратна 4", как можно было бы предположить по описанию в
    // документации. Эмпирически (сверено дифференциальным тестированием
    // против самого CPython на ~350 случайных и специально подобранных
    // строках - см. HANDOFF_30) поведение таково:
    //  - встретили НЕ-alphanumeric/+// символ ДО хвостовых '=' - ошибка;
    //  - '=' допустим ТОЛЬКО хвостом (не в середине строки данных);
    //  - data_len (кол-во символов алфавита, без учёта хвостовых '=') % 4
    //    == 1 - ВСЕГДА ошибка ("cannot be 1 more than a multiple of 4");
    //  - data_len == 0 - валидно, ТОЛЬКО если '=' в строке вообще нет
    //    (паддинг без данных перед ним - ошибка, даже though technically
    //    0 % 4 == 0);
    //  - data_len % 4 == 0 (и data_len > 0) - ЛЮБОЕ количество хвостовых
    //    '=' (в т.ч. заведомо избыточное, 3+) допустимо и просто
    //    игнорируется при декодировании;
    //  - data_len % 4 в {2,3} - количество '=' должно РОВНО совпадать с
    //    недостающим до кратности 4 (2 либо 1 соответственно) - меньше
    //    ("Incorrect padding") или больше ("Excess data after padding") -
    //    ошибка.
    size_t n = s.size();
    size_t eq_start = n;
    while (eq_start > 0 && s[eq_start - 1] == '=') eq_start -= 1;
    size_t eq_count = n - eq_start;
    for (size_t i = 0; i < eq_start; ++i) {
        if (decode_char(s[i]) < 0) return std::nullopt;  // включая случай '=' В СЕРЕДИНЕ строки
    }

    size_t data_len = eq_start;
    size_t rem = data_len % 4;
    if (rem == 1) return std::nullopt;
    if (data_len == 0) {
        if (eq_count != 0) return std::nullopt;
    } else if (rem != 0) {
        size_t required_eq = 4 - rem;
        if (eq_count != required_eq) return std::nullopt;
    }
    // rem == 0 && data_len > 0: eq_count любое (включая избыточное) - ок.

    std::vector<uint8_t> out;
    out.reserve((data_len / 4 + 1) * 3);
    size_t i = 0;
    while (i < data_len) {
        int c0 = decode_char(s[i]);
        int c1 = (i + 1 < data_len) ? decode_char(s[i + 1]) : 0;
        int c2 = (i + 2 < data_len) ? decode_char(s[i + 2]) : 0;
        int c3 = (i + 3 < data_len) ? decode_char(s[i + 3]) : 0;
        size_t group_len = std::min<size_t>(4, data_len - i);
        uint32_t triple = (uint32_t(c0) << 18) | (uint32_t(c1) << 12) | (uint32_t(c2) << 6) | uint32_t(c3);
        out.push_back(static_cast<uint8_t>((triple >> 16) & 0xff));
        if (group_len >= 3) out.push_back(static_cast<uint8_t>((triple >> 8) & 0xff));
        if (group_len >= 4) out.push_back(static_cast<uint8_t>(triple & 0xff));
        i += 4;
    }
    return out;
}

}  // namespace nd
