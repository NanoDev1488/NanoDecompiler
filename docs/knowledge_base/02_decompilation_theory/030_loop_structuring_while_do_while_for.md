# Article 030: Loop Structuring: while, do-while, and for Loop Recovery

## 1. Executive Summary & Loop Classification
Bytecode uses identical conditional jumps (`if_icmp*`) for `while`, `do-while`, and `for` loops. The decompiler determines the intended loop flavor by analyzing the location of the loop test and induction variables.

## 2. Loop Form Identification
1. **`while (cond)` Loop**:
   - Condition check is at the loop header $h$.
   - False edge exits the loop ($h 	o 	ext{exit}$).
   - True edge leads to loop body, which ends with an unconditional `goto h`.
2. **`do { ... } while (cond)` Loop**:
   - Loop header $h$ is an unconditional statement block.
   - Loop latch block evaluates condition: true edge jumps back to $h$; false edge falls through to exit.
3. **`for (init; cond; step)` Loop**:
   - Predecessor block of $h$ contains an assignment to variable $i$ (`init`).
   - Latch block contains an increment/update of $i$ (`step`), e.g., `iinc` or `i = i + 1`.
   - Variable $i$ is not modified elsewhere in the loop body.
   - $\implies$ Emitted cleanly as standard `for (int i = 0; i < N; i++)`.

## 3. Infinite Loops (`while (true)`)
If the loop subgraph has no natural back-edge condition (all exits occur via internal `break`, `return`, or `throw`), emit as `while (true) { ... }`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Distinguishes header-test loops (`WhileStmtNode`) from latch-test loops (`DoWhileStmtNode`).
- Inspects induction variables to reconstruct concise `for` loops.

## 5. References
- Muchnick, S. S. *Advanced Compiler Design and Implementation*. Chapter 18: Control-Flow Optimizations.
