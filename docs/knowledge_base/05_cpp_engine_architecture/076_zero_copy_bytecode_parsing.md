# Article 076: Zero-Copy Bytecode Parsing and Memory-Mapped Class Files

## 1. Executive Summary & Throughput Bottlenecks
Reading thousands of `.class` files via standard `fread` or `std::ifstream` incurs massive OS kernel context-switching and buffer copying overhead.

## 2. Zero-Copy Architecture
1. **Memory Mapping**: Map JAR archives and extracted class files directly into process virtual memory via `CreateFileMapping` / `MapViewOfFile` (Windows) or `mmap` (POSIX).
2. **Pointer Casting & Endianness**: Read headers and attributes using pointer offsets directly into the mapped buffer:
```cpp
inline uint16_t read_u2(const uint8_t*& ptr) {
    uint16_t val = (uint16_t(ptr[0]) << 8) | uint16_t(ptr[1]);
    ptr += 2;
    return val;
}
```
3. Avoid allocating heap strings or vectors for byte sequences; reference them via `std::string_view` and `std::span<const uint8_t>`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `zip_reader.cpp`:
- Zero-copy stream cursors decode class headers directly from memory.

## 4. References
- Kerrisk, M. *The Linux Programming Interface*. Chapter 49: Memory Mappings. No Starch Press, 2010.
