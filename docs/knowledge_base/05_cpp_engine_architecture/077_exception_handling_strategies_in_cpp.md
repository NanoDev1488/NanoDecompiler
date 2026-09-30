# Article 077: Exception Handling Strategies in C++: Result Types vs Exceptions

## 1. Executive Summary & Cost of C++ Exceptions
Standard C++ exceptions (`throw`, `catch`) incur non-zero runtime overhead during stack unwinding (Itanium ABI table lookups, Windows SEH dispatch). In high-performance compilers and decompilers:
- Exceptions should be reserved strictly for fatal errors (e.g. corrupted binary format, out-of-memory).
- Expected failure paths (e.g. inability to structure a particular irreducible loop) should return explicit result types (`std::expected<T, DecompileError>` in C++23, or custom `StatusOr<T>`).

## 2. NanoDecompiler Recovery Strategy
Rather than throwing a fatal `DecompileAbort` when an edge case is detected:
- The engine marks the current method with a diagnostic notice.
- Synthesizes fallback AST nodes or raw bytecode annotations.
- Allows surrounding methods and classes to decompile completely without halting the entire pipeline.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp` and `structure.cpp`:
- Replaced fatal aborts with graceful recovery and safe AST synthesis.

## 4. References
- Sutter, H. *Zero-overhead deterministic exceptions: Throwing values*. ISO C++ Committee Paper P0709R4, 2019.
