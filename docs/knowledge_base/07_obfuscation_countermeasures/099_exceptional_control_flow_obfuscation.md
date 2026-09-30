# Article 099: Exceptional Control Flow Obfuscation: Fake Try-Catch Ranges

## 1. Executive Summary & Trap Injection
Adversarial bytecode creates overlapping, zero-length, or out-of-order exception table entries:
- Handlers covering non-throwing instructions (e.g. `iconst_0`, `nop`).
- Handler ranges that partially overlap without containment (e.g. $[10, 30)$ and $[20, 40)$).
- Target PCs that jump into the middle of instructions.

## 2. Sanitization Pipeline
1. **Instruction Boundary Check**: Ensure `start_pc`, `end_pc`, and `handler_pc` align with valid instruction boundaries.
2. **Range Normalization**: Split partially overlapping ranges into disjoint sub-ranges.
3. **Dead Handler Pruning**: If a handler protects instructions that cannot throw exceptions of the specified `catch_type`, prune the handler entry.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp`:
- Normalizes exception tables and filters synthetic trap handlers before structuring.

## 4. References
- Linn, C., & Debray, S. *Obfuscation of Executable Code to Foil Reverse Engineering*. ACM CCS, 2003.
