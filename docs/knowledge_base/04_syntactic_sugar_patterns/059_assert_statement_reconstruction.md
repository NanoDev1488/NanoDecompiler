# Article 059: Assert Statement Reconstruction and $assertionsDisabled Flag

## 1. Executive Summary & Desugaring Pattern
Java `assert condition : message;` statements are compiled using a synthetic class-level static boolean field:
```java
static final boolean $assertionsDisabled = !EnclosingClass.class.desiredAssertionStatus();
```
At the assertion site:
```
getstatic $assertionsDisabled
ifne SkipLabel
[ Evaluate Condition ]
ifne SkipLabel
new java/lang/AssertionError
dup
[ Evaluate Message ]
invokespecial AssertionError.<init>(message)
athrow
SkipLabel:
```

## 2. Decompilation Reconstruction
1. Detect conditional throw of `AssertionError` gated by `$assertionsDisabled`.
2. Reconstruct `assert <cond> : <message>;`.
3. Suppress the synthetic `$assertionsDisabled` static field from class output.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `render_class.cpp`:
- Identifies assertion patterns and renders clean `assert` statements.

## 4. References
- JLS §14.10: The `assert` Statement.
