# Article 038: AST Simplification, Canonicalization Passes, and Redundant Cast Elimination

## 1. Executive Summary & Purpose
Raw ASTs synthesized from bytecode often contain unnatural compiler artifacts:
- Redundant parenthesis and nested singleton blocks `{{ S; }}`.
- Unnecessary primitive casts (e.g. `(int) (x + y)` where operands are already ints).
- Identity arithmetic operations (`x + 0`, `x * 1`, `x & -1`).
- Double negations (`!(!x)` $	o x$).

## 2. Canonicalization Passes
1. **Block Flattening**: If a block contains an inner child block that declares no conflicting local variable scopes, splice child statements directly into the parent block.
2. **Redundant Cast Stripping**:
   - Inspect receiver and parameter types: if the static type of the operand already satisfies the method signature or target variable type, remove `checkcast` / primitive cast node.
3. **Boolean Condition Inversion**: If an `if-then-else` statement has an empty `then` block and a populated `else` block, invert the condition and swap the blocks.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp` and `emit.cpp`:
- Post-processing AST rewrite passes canonicalize expressions prior to Java code emission.

## 4. References
- Muchnick, S. S. *Advanced Compiler Design and Implementation*. Chapter 12: AST Transformations.
