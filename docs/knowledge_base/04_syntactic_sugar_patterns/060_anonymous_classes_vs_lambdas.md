# Article 060: Anonymous Inner Classes vs Lambdas Disambiguation

## 1. Executive Summary & Differences
| Feature | Anonymous Inner Class | Lambda Expression |
|---------|-----------------------|-------------------|
| Class File | Generates separate `Outer$1.class` | Desugared into synthetic method in same class |
| Bytecode Opcode | `new Outer$1`, `invokespecial <init>` | `invokedynamic LambdaMetafactory` |
| Scope of `this` | Refers to anonymous class instance | Refers to enclosing class instance |
| Performance | Creates separate object per instance | Can be cached as static constant singleton |

## 2. Decompilation Guidance
- Anonymous classes should only be converted to lambdas if the target is a Single Abstract Method (SAM) interface and `this` is not shadowed.
- Otherwise, retain the full `new Interface() { public void method() { ... } }` syntax.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `render_class.cpp`:
- Accurate classification of SAM interfaces and anonymous class declarations.

## 4. References
- Goetz, B. *Translation of Lambda Expressions*. Oracle, 2012.
