# Article 081: Diagnostics and Error Recovery Architecture: Graceful Fallback

## 1. Executive Summary & Design Principles
A production decompiler must never crash on malformed, corrupted, or obfuscated bytecode:
1. **Containment**: An error in a single method must not prevent decompilation of other methods in the same class.
2. **Transparency**: The decompiler must emit high-fidelity comments explaining why a fallback occurred.
3. **Structured Fallback Hierarchy**:
   - Level 1: Fully structured Java AST.
   - Level 2: Partial AST with inline bytecode for unstructurable subgraphs.
   - Level 3: Clean disassembled bytecode annotated with stack types.

## 2. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp` and `README_RU.txt`:
- Generates detailed diagnostic reports (`README_RU.txt`) explaining any fallback reasons and transmitting error telemetry when enabled.

## 3. References
- Cifuentes, C., et al. *The Dava Decompiler: Structured flow analysis*. ACM CC, 2003.
