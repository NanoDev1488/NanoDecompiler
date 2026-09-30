# Article 010: Exception Tables, Half-Open PC Ranges, and Nested Handlers

## 1. Executive Summary & Specification
In the JVM `Code` attribute, structured exception handling (`try-catch-finally`) is represented as a flat lookup table (JVMS §4.7.3):
```
struct ExceptionHandler {
    u2 start_pc;    // Inclusive start
    u2 end_pc;      // Exclusive end [start_pc, end_pc)
    u2 handler_pc;  // Target PC
    u2 catch_type;  // CP index to Class, or 0 for finally/catch-all
};
```

## 2. Invariant Rules
1. **Half-Open Range**: The protected range is active for instruction PCs satisfying `start_pc <= pc < end_pc`.
2. **Priority Ordering**: The exception table is evaluated sequentially from index 0 to `exception_table_length - 1`. The first matching handler is selected. Subclass exceptions must precede superclass exceptions in the table.
3. **Finally / Catch-All**: `catch_type == 0` intercepts any `Throwable` instance.

## 3. Decompiler Structuring Challenges
- **Nested Ranges**: Multiple overlapping ranges require hierarchical nesting:
  - If Range A is completely contained in Range B: `[start_A, end_A) ⊂ [start_B, end_B)`, Range A is a nested inner try-block.
  - If ranges cross boundaries without containment, this indicates irreducible exception flow or bytecode obfuscation requiring interval splitting.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `catchclean.cpp`:
- `build_try()` organizes raw table entries into hierarchical `TryCatchRegion` trees.
- `CatchClean::clean_handlers()` removes redundant synthetic finally handlers.

## 5. References
- JVMS §4.7.3: The Code Attribute - Exception Tables.
