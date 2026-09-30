# Article 053: Ternary Operator (? :) CFG Diamond Reconstruction

## 1. Executive Summary & Diamond CFG Pattern
The conditional expression `cond ? exprTrue : exprFalse` produces a characteristic diamond-shaped control flow subgraph:
```
           [ Condition Block ]
             /             \
      (true)/               \(false)
           ▼                 ▼
   [ True Block ]     [ False Block ]
   push exprTrue       push exprFalse
           \                 /
            \               /
             ▼             ▼
             [ Join Block ]
             consume result
```

## 2. Reconstruction Algorithm
1. Detect conditional branch at block $C$ targeting $B_{false}$, falling through to $B_{true}$.
2. Both $B_{true}$ and $B_{false}$ must jump unconditionally to common successor $J$.
3. At the end of $B_{true}$ and $B_{false}$, exactly 1 stack value is pushed, and neither block has other side effects.
4. Replace the entire diamond with a single `TernaryExpr(cond, exprTrue, exprFalse)` placed on the stack at entry to $J$.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `stackvm.cpp`:
- Diamond detector collapses expressions into `TernaryExpr`.

## 4. References
- JLS §15.25: Conditional Operator `? :`.
