// disassembler.hpp - порт resources/engine/disassembler.py (v2.0,
// HANDOFF_26, модуль 3). Дизассемблирует байткод метода в список текстовых
// строк вида "     0: aload_0" / "     1: invokespecial #7 // ...".
#pragma once

#include <cstdint>
#include <string>
#include <vector>

#include "classfile.hpp"

namespace nd {

// method может быть nullptr (тогда таблица исключений не печатается) -
// зеркалит `method=None` в Python.
std::vector<std::string> disassemble(const std::vector<uint8_t>& code, const ClassFile& cf,
                                      const Method* method = nullptr);

}  // namespace nd
