# Article 086: Procyon Decompiler Architecture: AST Pipeline and C# Inspiration

## 1. Executive Summary & Architectural Overview
Developed by Mike Strobel, **Procyon** is widely recognized for superior handling of Java 5+ language features (enums, generics, annotations) and Java 8 lambdas. Procyon's pipeline is heavily influenced by the C# ILSpy decompiler architecture:
- Directly models an expression-based intermediate AST.
- Applies successive optimization passes on the high-level AST rather than low-level CFG.

## 2. Key Innovations
1. **Generic Type Reconstruction**: Rigorously analyzes generic signatures and solves type parameter constraints across method call chains.
2. **Declarative Pattern Matching**: Uses structural tree-matching patterns to collapse synthetic enum and switch artifacts into clean source syntax.

## 3. Comparative Strengths & Weaknesses
- *Strengths*: Exceptionally clean, idiomatic Java source output on well-formed code.
- *Weaknesses*: High memory footprint, slower on obfuscated binaries with deliberately mangled CFGs.

## 4. References
- Strobel, M. *Procyon: Java Decompiler and Metaprogramming Framework*. GitHub, 2015.
