# Article 041: Symbolic JVM Stack Simulation and Value Propagation

## 1. Executive Summary & Specification
Unlike register-based machines (e.g. ARM, x86, Dalvik), the JVM operand stack holds anonymous intermediate calculation results. A decompiler models this via **Symbolic Stack Simulation**:
- Instead of tracking concrete 32/64-bit integer values, the stack elements are pointers to symbolic expression AST nodes (`AstExpr`).
- When an opcode pushes a value, an expression node is synthesized and pushed.
- When an opcode pops operands, it consumes expressions from the stack and incorporates them as child nodes of a new compound expression.

## 2. Invariant Verification
For every instruction $i$ in basic block $B$, the stack height $h_i$ must be identical across all paths reaching $i$:
$$h_i = h_{entry} + \sum_{k=0}^{i-1} \Delta(k)$$
where $\Delta(k) = 	ext{pushes}(k) - 	ext{pops}(k)$.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- `StackVM::simulate_block()` executes symbolic simulation block-by-block.
- Synthesizes expressions for arithmetic, invocations, field lookups, and array accesses.

## 4. References
- Lindholm, T., et al. *The Java Virtual Machine Specification*. §3.1: The Operand Stack.
