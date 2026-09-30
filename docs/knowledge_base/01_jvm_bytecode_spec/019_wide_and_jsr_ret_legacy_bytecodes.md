# Article 019: The wide Modifier and Legacy jsr/ret Subroutine Inlining

## 1. Executive Summary & Specification
1. **The `wide` Opcode (0xC4)**:
   - Extends 8-bit local variable index operands to 16 bits for: `iload`, `fload`, `aload`, `lload`, `dload`, `istore`, `fstore`, `astore`, `lstore`, `dstore`, `ret`.
   - Also modifies `iinc` to take 16-bit variable index and 16-bit signed constant increment.
2. **Legacy `jsr` (0xA8) and `ret` (0xA9)**:
   - Used in Java 1.1 - 5 to implement `finally` blocks without bytecode duplication.
   - `jsr` pushes the return address (PC of following instruction) onto the operand stack and jumps to subroutine.
   - `ret <var>` jumps back to the address stored in local variable `<var>`.
   - Deprecated in Java 6 and forbidden in class files compiled for Java 7+ (`-target 1.7+`).

## 2. Decompiler Transformation: Subroutine Inlining
Modern decompilers eliminate `jsr`/`ret` early in the pipeline:
- Duplicate the subroutine body at each `jsr` call site.
- Replace `ret` with direct jumps to the return address.
- Yields a standard single-entry single-exit control flow graph suitable for modern structuring algorithms.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/disassembler.cpp` and `cfg.cpp`:
- Handles 16-bit operands for `wide`.
- Normalizes subroutine targets during CFG construction.

## 4. References
- JVMS §6.5: `wide`, `jsr`, `ret`.
- Freund, S. N., & Mitchell, J. C. *A Type System for Java Bytecode Subroutines and its Verification*. Formal Aspects of Computing, 2003.
