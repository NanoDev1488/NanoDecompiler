# Article 082: Cross-Platform Abstraction Layers: Windows, Linux, and macOS

## 1. Executive Summary & Platform Portability
A cross-platform C++ engine must interface seamlessly with diverse OS APIs:
- **Windows**: `wininet.h` for HTTP networking, `CreateProcessW` for process spawning, UTF-16 wchar path semantics.
- **Linux / macOS**: POSIX sockets / `libcurl`, `fork`/`exec`, UTF-8 filesystem semantics.

## 2. Clean Header Abstraction
Encapsulate OS differences behind clean C++ interfaces:
```cpp
class Platform {
public:
    static bool download_file(std::string_view url, const std::string& destination);
    static std::string get_executable_path();
    static size_t get_total_system_memory();
};
```

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/platform_detect.cpp` and `auto_update.cpp`:
- Conditional compilation (`#ifdef _WIN32`) bridges WinINet on Windows and POSIX on Linux/macOS.

## 4. References
- Stroustrup, B. *Design and Evolution of C++*. Addison-Wesley, 1994.
