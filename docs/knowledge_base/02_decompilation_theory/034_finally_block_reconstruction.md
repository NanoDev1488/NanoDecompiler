# Article 034: Finally Block Reconstruction and Duplicate Bytecode De-duplication

## 1. Executive Summary & Compiler Generation Patterns
Since the deprecation of `jsr`/`ret`, Java compilers implement `finally` blocks through **code duplication**:
1. A copy of the finally block body is inserted before every normal exit point (every `return`, `break`, or `continue`) inside the `try` and `catch` blocks.
2. An all-encompassing exception handler (`catch_type == 0`, covering both the try block and all catch blocks) is generated. This handler executes another copy of the finally block and then re-throws the exception via `athrow`.

## 2. Decompilation Reconstruction Algorithm
If a decompiler outputs raw bytecode without recognizing this pattern, the finally code will appear duplicated 3 to 10 times in every method exit!
- **Algorithm**:
  1. Identify any `catch_type == 0` handler that terminates in `athrow`.
  2. Extract the statement sequence immediately preceding `athrow` as the candidate `finally` body $F$.
  3. Scan preceding normal exit paths: if a sequence isomorphic to $F$ precedes returns/exits from the try/catch blocks, prune that sequence from the AST.
  4. Emit $F$ exactly once inside the `finally { ... }` block.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp`:
- `CatchClean::clean_handlers()` detects duplicated finally blocks, strips repetitive exit code, and consolidates them into a single `finally` clause.

## 4. References
- Gosling, J., et al. *The Java Language Specification*. §14.20.2: Execution of `try-finally` and `try-catch-finally`.
