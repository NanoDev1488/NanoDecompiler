# Article 018: Debug Attributes: LineNumberTable, LocalVariableTable, and Scoping

## 1. Executive Summary & Specification
Debugging attributes provide vital metadata for source-level correlation:
1. `LineNumberTable`: Maps bytecode offset ranges to original source code line numbers (`start_pc` -> `line_number`).
2. `LocalVariableTable`: Maps local variable slots to source identifiers and descriptors over specific PC intervals:
```
struct LocalVariableEntry {
    u2 start_pc;   // PC where variable scope begins
    u2 length;     // Scope span: [start_pc, start_pc + length)
    u2 name_index; // Utf8 variable name
    u2 descriptor_index;
    u2 index;      // LVT slot index
};
```
3. `LocalVariableTypeTable`: Similar to LVT, but stores generic signatures (`Signature` attribute syntax).

## 2. Decompilation Utility
- If LVT is present, exact parameter and local variable names are restored effortlessly (`userId`, `count`, etc.).
- The scope interval `[start_pc, start_pc + length)` provides exact block-level scoping boundaries for local variable declaration placement.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/naming_hints.cpp` and `engine.cpp`:
- Uses LVT names when available; falls back to heuristic type-based naming (`var1`, `name`, `builder`) when stripped.

## 4. References
- JVMS §4.7.12, §4.7.13, §4.7.14.
