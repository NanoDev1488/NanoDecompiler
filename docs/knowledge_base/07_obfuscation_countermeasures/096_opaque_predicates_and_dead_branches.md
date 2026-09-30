# Article 096: Opaque Predicates and Dead Branch Pruning

## 1. Executive Summary & Mathematical Foundations
An **Opaque Predicate** is a conditional expression whose evaluation is known a priori to the obfuscator at compile time, but is difficult or impossible for static analyzers to deduce:
- $P_{	ext{true}}$: Always evaluates to true (e.g. $x^2 \ge 0$ for integers, or $(x \cdot (x + 1)) \equiv 0 \pmod 2$).
- $P_{	ext{false}}$: Always evaluates to false.

## 2. Obfuscation Threat Model
Obfuscators inject opaque predicates to:
1. Introduce spurious edges in the CFG, transforming reducible graphs into irreducible graphs.
2. Route dead branches to illegal bytecodes or traps designed to crash decompilers.

## 3. Solver Algorithm
1. **Constant Propagation**: Trace local variable values through basic blocks.
2. **Algebraic Simplification**: Recognize known mathematical identities and bitwise invariants ($x \land 0 = 0$, $x \oplus x = 0$).
3. **Dead Edge Elimination**: When a conditional branch condition is proven constant, prune the non-taken edge from the CFG and convert the branch into an unconditional jump.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Evaluates constant expressions to prune bogus control flow branches before structuring.

## 5. References
- Collberg, C., Thomborson, C., & Low, D. *A taxonomy of obfuscating transformations*. Technical Report, University of Auckland, 1997.
