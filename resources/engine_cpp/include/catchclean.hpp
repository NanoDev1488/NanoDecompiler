// catchclean.hpp - порт resources/engine/catchclean.py (v2.0, HANDOFF_39).
// Удаление catch-блоков вида `catch (T t) { throw t; }` (семантически no-op).
#pragma once

#include <cstdint>
#include <utility>
#include <vector>

#include "classfile.hpp"

namespace nd {

bool is_pure_rethrow_handler(const std::vector<uint8_t>& code, size_t handler_pc);

// Возвращает (отфильтрованный список exceptions, сколько убрано). Ничего не мутирует.
std::pair<std::vector<ExceptionEntry>, int> filter_junk_catches(const Method& method);

}  // namespace nd
