# Article 042: Static Single Assignment (SSA) Form and Cytron's Phi Placement

## 1. Executive Summary & Formal Theory
A program is in **Static Single Assignment (SSA)** form if:
1. Every variable is assigned a value exactly once.
2. Every use of a variable is dominated by its single definition.
At control flow merge points where divergent definitions meet, synthetic selection functions called $\phi$ (phi) functions are inserted:
$$x_3 = \phi(x_1, x_2)$$

## 2. Optimal $\phi$-Placement via Dominance Frontiers
Cytron et al. (1991) proved that $\phi$-nodes for variable $v$ are placed exactly at the **Iterated Dominance Frontier** $IDF(S_v)$, where $S_v$ is the set of basic blocks containing definitions of $v$.
- Dominance Frontier: $DF(X) = \{ Y \mid X 	ext{ dominates a predecessor of } Y 	ext{ but does not strictly dominate } Y \}$.

## 3. Decompiler Utility
SSA form isolates variable lifetimes, disentangling unrelated variables that were assigned to the same local variable slot by compiler register reuse.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/ir.cpp` and `engine.cpp`:
- Constructs SSA representations for local variable slots, enabling unambiguous data dependency graphs.

## 5. References
- Cytron, R., et al. *Efficiently computing static single assignment form and the control dependence graph*. ACM TOPLAS, 1991.
