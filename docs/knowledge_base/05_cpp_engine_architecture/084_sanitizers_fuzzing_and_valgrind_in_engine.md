# Article 084: Sanitizers, Fuzzing, and Valgrind in C++ Decompiler Engine

## 1. Executive Summary & Memory Safety
C++ decompilers parse untrusted binary inputs from third-party JARs. Memory safety vulnerabilities (buffer overflows, use-after-free) can lead to remote code execution.

## 2. Verification Tooling
- **AddressSanitizer (ASan)**: Detects out-of-bounds accesses, use-after-free, and stack corruptions at runtime.
- **UndefinedBehaviorSanitizer (UBSan)**: Detects signed integer overflow, null pointer dereferencing, and misaligned pointer casts.
- **libFuzzer**: Feeds randomly mutated `.class` files into the parser to uncover edge-case crashes.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/verify.cpp`:
- Rigorous bounds checking on all byte buffer reads.

## 4. References
- Serebryany, K., et al. *AddressSanitizer: A fast address sanity checker*. USENIX ATC, 2012.
