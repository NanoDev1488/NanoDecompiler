// version.hpp - единственное место с версией движка (HANDOFF_46) - раньше
// строка NANO_DECOMPILER_VERSION дублировалась бы в cli_main.cpp/api.cpp
// по отдельности, теперь один источник для обоих.
#pragma once

namespace nd {
constexpr const char* NANO_DECOMPILER_VERSION = "NanoDecompiler v1.6.4-fix";
}
