# Article 061: Method Reference Reconstruction: Static, Bound, Unbound, and Constructor

## 1. Executive Summary & Taxonomy
Method references (Java 8) provide compact syntax for lambdas that merely delegate to existing methods:
1. **Static Method Reference**: `ContainingClass::staticMethod`
   - BSM: `LambdaMetafactory.metafactory`, target handle kind = `H_INVOKESTATIC`.
2. **Bound Instance Method Reference**: `expr::instanceMethod`
   - Captures the receiver expression as an argument to `invokedynamic`.
3. **Unbound Instance Method Reference**: `ContainingType::instanceMethod`
   - First parameter of the functional interface serves as the receiver.
4. **Constructor Reference**: `ClassName::new` or `TypeName[]::new`
   - Target handle kind = `H_NEWINVOKESPECIAL`.

## 2. Decompiler Reconstruction
- When an `invokedynamic` with `LambdaMetafactory` references a non-synthetic method directly in its `implMethod` handle:
  - Format as `Target::name` instead of `(args) -> Target.name(args)`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Generates clean `MethodRefExpr` nodes when target method matches delegation signatures.

## 4. References
- JLS §15.13: Method Reference Expressions.
