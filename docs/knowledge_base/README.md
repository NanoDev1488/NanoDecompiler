# NanoDecompiler Engineering Knowledge Base (100 Technical Monographs)

Welcome to the **NanoDecompiler Knowledge Base**, an exhaustive corpus of **100 deep technical monographs** covering compiler engineering, JVM bytecode internals, control flow structuring, dataflow lattice solving, high-performance modern C++ architecture, and deobfuscation algorithms.

## Quick Links
- **[Full Index of 100 Articles](INDEX_100_ARTICLES.md)**
- **Total Articles Generated**: 100
- **Language & Standards Covered**: C++20/C++23, Java SE 7 through 23, JVM Spec (JVMS 23), Dalvik DEX.

## Overview of Modules
1. **[01_jvm_bytecode_spec/](01_jvm_bytecode_spec/)** (Articles 001 - 020): Execution model, ClassFile format, Constant Pool tags, StackMapTable, InvokeDynamic, Lambdas, StringConcatFactory, Sealed classes, Records, JPMS modules.
2. **[02_decompilation_theory/](02_decompilation_theory/)** (Articles 021 - 040): CFG construction, Dominator trees, Natural loops, Relooper algorithm, T1/T2 reductions, Irreducible flow graphs, If/Else structuring, Short-circuit logic, Switch reconstruction, Exception tables, Synchronized blocks.
3. **[03_dataflow_ssa_stack/](03_dataflow_ssa_stack/)** (Articles 041 - 055): Symbolic stack simulation, SSA form, Out-of-SSA, Stack underflow recovery, Variable liveness & interference graphs, Type inference lattice, Primitive disambiguation, LCA reference resolution, Copy propagation, Escaping variable hoisting.
4. **[04_syntactic_sugar_patterns/](04_syntactic_sugar_patterns/)** (Articles 056 - 070): String switch two-tier hash desugaring, Enum switch synthetic inner classes, Try-with-resources, Assert statements, Method references, Enhanced for-loops (arrays and iterables), Autoboxing, Pattern matching, Kotlin bytecode patterns.
5. **[05_cpp_engine_architecture/](05_cpp_engine_architecture/)** (Articles 071 - 085): Modern C++ AST node design, Memory arenas, String interning, Visitor double-dispatch, Dense indexed CFG bitsets, Zero-copy memory mapping, Safe error recovery, Parallel thread pools, ASan/UBSan fuzzing, Profiling.
6. **[06_decompiler_case_studies/](06_decompiler_case_studies/)** (Articles 086 - 095): Deep architectural analysis of Procyon, CFR, Fernflower, Krakatau, Jadx, Dava/Soot, JD-Core, and NSA Ghidra; Comparative benchmarks.
7. **[07_obfuscation_countermeasures/](07_obfuscation_countermeasures/)** (Articles 096 - 100): Opaque predicates, Control flow flattening unflattening, String encryption solvers, Exceptional control flow traps, Heuristic identifier restoration.
