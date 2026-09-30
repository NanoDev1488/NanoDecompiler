# Article 044: Stack Underflow Recovery and Inter-Block Stack Item Crossing

## 1. Executive Summary & Problem Formulation
While typical basic blocks begin and end with an empty operand stack, certain compiler optimizations and ternary operations leave values on the stack across block boundaries:
- A basic block consumes more operands than it pushed $\implies$ **Stack Underflow**.
- These missing operands were left on the stack by predecessor blocks.

## 2. Multi-Depth Recovery Algorithm
When block $B$ underflows by $k$ stack slots:
1. Examine all immediate predecessors $P_1, P_2, \dots, P_m$ of $B$.
2. For each predecessor $P$, trace its exit stack state.
3. If predecessor stacks match, propagate the top $k$ symbolic expressions into $B$'s entry stack.
4. If predecessors exhibit divergent stack depths or if a predecessor has multiple successors:
   - Synthesize a typed temporary variable `__tempX`.
   - In each predecessor, emit an assignment `__tempX = pop()`.
   - In block $B$, seed the initial stack with `__tempX`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Iterative loop resolving cascaded underflows up to depth 16.
- Preserves exit stack remainders as typed variables, preventing decompiler aborts.

## 4. References
- Proebsting, T. A. *Decompiling Java Bytecode: Problems and Solutions*. ACM SIGPLAN, 1997.
