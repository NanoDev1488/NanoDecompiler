# Article 049: Copy Propagation, Expression Inlining, and Side-Effect Safety

## 1. Executive Summary & Goal
Bytecode creates numerous intermediate local variables. To produce readable Java source code, the decompiler must collapse these temporaries into complex expressions via **Expression Inlining**:
```java
// Raw Decompilation:
int t1 = a + b;
int t2 = c * d;
int result = t1 - t2;

// Inlined Expression:
int result = (a + b) - (c * d);
```

## 2. Inlining Invariants & Side-Effect Safety
An expression $E$ assigned to variable $v$ can be inlined at its use site $U$ if and only if:
1. $v$ is used exactly once.
2. Between the assignment to $v$ and the use site $U$:
   - No sub-expression in $E$ has its operands modified (e.g. no variables read by $E$ are overwritten).
   - If $E$ has side effects (method calls, volatile reads, field writes), no other side-effecting operations intervene that could alter evaluation order.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Single-use copy propagation and AST expression inlining pass.

## 4. References
- Aho, A. V., et al. *Compilers: Principles, Techniques, and Tools*. §8.5: Copy Propagation.
