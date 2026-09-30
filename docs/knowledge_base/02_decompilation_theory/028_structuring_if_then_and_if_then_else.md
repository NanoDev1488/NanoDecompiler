# Article 028: Structuring If-Then and If-Then-Else Conditional Branches

## 1. Executive Summary & Specification
In bytecode, a high-level `if (cond) { thenBlock } else { elseBlock }` is lowered into:
```
    [ Condition Eval ] ───► false branch jumps to ElseLabel
    [ Then Block ]
    goto JoinLabel
ElseLabel:
    [ Else Block ]
JoinLabel:
    [ Successor Block ]
```

## 2. Structuring Algorithm
1. Let $B$ be a block terminating with a conditional jump to $B_{target}$ with fallthrough to $B_{fall}$.
2. Compute $J = 	ext{ipdom}(B)$ (the immediate post-dominator).
3. If $B_{target} == J$:
   - This is an **`if-then` without else**: The then-clause is the subgraph rooted at $B_{fall}$ terminating at $J$.
4. If $B_{fall} == J$:
   - This is an **`if-then` with inverted condition**: Invert branch condition, then-clause is rooted at $B_{target}$.
5. If neither target equals $J$:
   - This is an **`if-then-else`**:
     - `then` body: Nodes reachable from $B_{fall}$ stopping at $J$.
     - `else` body: Nodes reachable from $B_{target}$ stopping at $J$.

## 3. Empty Else Optimization
If the `else` branch contains only a `goto` or returns immediately, restructure to avoid nested `else` indentation.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- `Structurer::region()` recognizes conditional branch patterns and synthesizes `IfStmtNode` with attached `then_block` and optional `else_block`.

## 5. References
- Baker, B. S. *An algorithm for structuring flowgraphs*. Journal of the ACM, 1977.
