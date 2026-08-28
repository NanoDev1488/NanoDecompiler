// catchclean.cpp - см. catchclean.hpp. 1:1 порт catchclean.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "catchclean.hpp"

#include <map>
#include <optional>

namespace nd {

namespace {

constexpr uint8_t ASTORE = 0x3a;
constexpr uint8_t ALOAD = 0x19;
constexpr uint8_t ATHROW = 0xbf;
const std::map<uint8_t, int> ASTORE_N = {{0x4b, 0}, {0x4c, 1}, {0x4d, 2}, {0x4e, 3}};
const std::map<uint8_t, int> ALOAD_N = {{0x2a, 0}, {0x2b, 1}, {0x2c, 2}, {0x2d, 3}};

std::optional<std::pair<int, size_t>> parse_store_or_load(const std::vector<uint8_t>& code, size_t pos, bool is_store) {
    if (pos >= code.size()) return std::nullopt;
    uint8_t op = code[pos];
    if (is_store) {
        if (op == ASTORE) {
            if (pos + 1 >= code.size()) return std::nullopt;
            return std::make_pair(static_cast<int>(code[pos + 1]), pos + 2);
        }
        auto it = ASTORE_N.find(op);
        if (it != ASTORE_N.end()) return std::make_pair(it->second, pos + 1);
    } else {
        if (op == ALOAD) {
            if (pos + 1 >= code.size()) return std::nullopt;
            return std::make_pair(static_cast<int>(code[pos + 1]), pos + 2);
        }
        auto it = ALOAD_N.find(op);
        if (it != ALOAD_N.end()) return std::make_pair(it->second, pos + 1);
    }
    return std::nullopt;
}

}  // namespace

bool is_pure_rethrow_handler(const std::vector<uint8_t>& code, size_t handler_pc) {
    if (handler_pc >= code.size()) return false;
    if (code[handler_pc] == ATHROW) return true;
    auto st = parse_store_or_load(code, handler_pc, true);
    if (!st.has_value()) return false;
    auto [slot, pos] = *st;
    auto ld = parse_store_or_load(code, pos, false);
    if (!ld.has_value()) return false;
    auto [ld_slot, pos2] = *ld;
    if (ld_slot != slot) return false;
    return pos2 < code.size() && code[pos2] == ATHROW;
}

std::pair<std::vector<ExceptionEntry>, int> filter_junk_catches(const Method& method) {
    if (!method.has_code || method.exceptions.empty()) return {method.exceptions, 0};
    std::vector<ExceptionEntry> kept;
    int removed = 0;
    for (auto& entry : method.exceptions) {
        if (is_pure_rethrow_handler(method.code, entry.handler_pc)) {
            removed += 1;
            continue;
        }
        kept.push_back(entry);
    }
    return {kept, removed};
}

}  // namespace nd
