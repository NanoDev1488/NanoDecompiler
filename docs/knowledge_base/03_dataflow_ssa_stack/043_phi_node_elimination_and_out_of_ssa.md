# Article 043: Out-of-SSA Translation, Phi Elimination, and Register Coalescing

## 1. Executive Summary & Challenge
Before emitting Java source code, the decompiler must exit SSA form by eliminating all abstract $\phi$-nodes and consolidating SSA versions ($x_1, x_2, x_3$) into valid Java local variable identifiers ($x$).
The classic challenge is the **Parallel Copy Problem** (e.g. swap cycles $a \leftarrow b, b \leftarrow a$) which requires temporary variable introduction if not scheduled correctly.

## 2. Elimination Algorithm
1. Replace each $\phi$-function $x_0 = \phi(x_1, x_2, \dots, x_k)$ by placing a copy assignment $x_0 = x_i$ at the end of the $i$-th predecessor block.
2. Build an **Interference Graph** of variables whose lifetimes overlap.
3. Coalesce variables whose lifetimes do not interfere and share copy assignments, minimizing redundant assignments.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/ir.cpp` and `engine.cpp`:
- Resolves $\phi$-nodes and assigns cohesive variable identities to merged dataflow streams.

## 4. References
- Sreedhar, V. C., et al. *Translating Out of Static Single Assignment Form*. Static Analysis Symposium, 1999.
