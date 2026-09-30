# Article 040: Decompiler Correctness, Semantic Preservation, and Fuzzing Strategies

## 1. Executive Summary & Verification Criteria
A decompiler is strictly **correct** if for every valid input class file $C$, decompiling $C$ into source $S$ followed by recompiling $S$ with standard `javac` produces a binary $C'$ that is **semantically equivalent** to $C$:
$$orall C \in 	ext{ValidClassFiles}, \quad 	ext{Semantics}(	ext{javac}(	ext{Decompile}(C))) \equiv 	ext{Semantics}(C)$$

## 2. Differential Testing & Fuzzing Architecture
To guarantee engine stability across millions of edge cases:
1. **Round-Trip Testing**: Decompile JAR -> Recompile with `javac` -> Execute unit tests on recompiled classes.
2. **AST Invariant Fuzzing**: Mutate bytecode with arbitrary branch interleavings and verify decompiler never crashes, leaks memory, or emits invalid Java syntax.
3. **Differential Comparison**: Run multiple decompilers (NanoDecompiler, CFR, Fernflower, Procyon) against the same benchmark and compare decompiled AST branch structures.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/verify.cpp`:
- Automated sanity checks on CFG coverage, stack balance, and local variable scoping prior to final code emission.

## 4. References
- Myreen, M. O. *Decompilation as Reasoning in Logic*. PhD Thesis, University of Cambridge, 2008.
- Holler, C., et al. *Fuzzing Compilers and Decompilers with Grammar-based Mutators*. USENIX Security, 2012.
