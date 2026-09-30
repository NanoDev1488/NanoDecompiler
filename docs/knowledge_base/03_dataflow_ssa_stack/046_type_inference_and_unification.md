# Article 046: Type Inference Lattice and Hindley-Milner Type Unification

## 1. Executive Summary & Type Lattice
Bytecode lacks explicit type declarations for local variables. The decompiler reconstructs precise Java types using a **Type Lattice** $(L, \sqsubseteq, \sqcup, \sqcap)$:
```
                       Top (Unknown / Unconstrained)
                       /      |      \
                 Object    Numeric    Array
                 /    \     /   \       |
            String   List  int  double Object[]
                 \    /     \   /       |
                      Bottom (Conflict / Error)
```

## 2. Constraint Propagation & Unification
1. Generate type constraints for every operation:
   - `iadd` $\implies$ operands $\sqsubseteq 	ext{int}$, result $\sqsubseteq 	ext{int}$.
   - `invokevirtual Foo.bar(String)` $\implies$ receiver $\sqsubseteq 	ext{Foo}$, arg $\sqsubseteq 	ext{String}$.
2. Propagate constraints iteratively until reaching a fixed point.
3. Solve for the **Least Upper Bound (LUB)** of all constraints for each local variable.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp` and `engine.cpp`:
- Solves type constraints to assign accurate Java types to reconstructed variables.

## 4. References
- Pierce, B. C. *Types and Programming Languages*. MIT Press, 2002.
- Hindley, J. R. *The Principal Type-Scheme of an Object in Combinatory Logic*. Transactions of the AMS, 1969.
