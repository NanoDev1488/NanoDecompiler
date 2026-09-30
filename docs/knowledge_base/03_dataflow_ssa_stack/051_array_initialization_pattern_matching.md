# Article 051: Array Initialization Pattern Matching and Literal Synthesis

## 1. Executive Summary & Compiler Lowering
An array initializer `int[] arr = { 1, 2, 3 };` is lowered by javac into:
```
newarray int [3]
dup
iconst_0
iconst_1
iastore
dup
iconst_1
iconst_2
iastore
dup
iconst_2
iconst_3
iastore
astore_1
```

## 2. Pattern Matching Algorithm
1. Identify `newarray` or `anewarray` instruction with constant size $N$.
2. Track subsequent instructions: look for repetitive sequences of `dup`, constant index $k \in [0, N-1]$, element expression $E_k$, and `*astore`.
3. If all indices $0 \le k < N$ are initialized sequentially without intervening branches:
   - Collapse the entire sequence into a single `ArrayInitExpr({ E_0, E_1, ..., E_{N-1} })`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Recognizes consecutive array store patterns and constructs clean array literal expressions.

## 4. References
- JLS §10.6: Array Initializers.
