# Article 023: Dominator Trees, Post-Dominance, and the Lengauer-Tarjan Algorithm

## 1. Executive Summary & Formal Definitions
Dominance analysis is the cornerstone of control flow structuring:
- **Dominance**: A node $d$ dominates node $n$ ($d 	ext{ dom } n$) if every path from entry node $r$ to $n$ must pass through $d$.
- **Strict Dominance**: $d 	ext{ sdom } n \iff d 	ext{ dom } n \land d 
eq n$.
- **Immediate Dominator ($	ext{idom}(n)$)**: The unique strict dominator of $n$ that does not dominate any other strict dominator of $n$. The set of all immediate dominator edges forms the **Dominator Tree**.
- **Post-Dominance**: Node $p$ post-dominates $n$ ($p 	ext{ pdom } n$) if every path from $n$ to the exit node must pass through $p$. $	ext{ipdom}(n)$ represents the convergence or join point of branches originating at $n$.

## 2. Lengauer-Tarjan Algorithm
Computes dominator trees in near-linear time $O(|E| \cdot lpha(|E|, |V|))$ using depth-first search numbering and semi-dominators:
1. DFS traversal assigns discovery numbers `dfnum(v)`.
2. Compute semi-dominators using path-compression with disjoint sets (Union-Find).
3. Compute immediate dominators $	ext{idom}(v)$ from semi-dominator links.

## 3. Role in AST Structuring
- If a conditional branch occurs at block $B$, the join point of the two branches is precisely $	ext{ipdom}(B)$.
- All blocks post-dominated by $B$'s branch targets that precede $	ext{ipdom}(B)$ form the `then` and `else` statement bodies.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- `compute_dominators()` and `compute_post_dominators()` calculate immediate dominator relations used to delineate branch and loop nesting scopes.

## 5. References
- Lengauer, T., & Tarjan, R. E. *A fast algorithm for finding dominators in a flowgraph*. ACM TOPLAS, 1979.
