# Article 008: Lambda Expression Desugaring and LambdaMetafactory Reconstruction

## 1. Executive Summary & Specification
Java 8 lambdas are not compiled into anonymous inner classes. Instead, the javac compiler desugars the lambda body into a synthetic method and emits an `invokedynamic` instruction linked to `java.lang.invoke.LambdaMetafactory` (JVMS §4.7.23):
- **BSM Arguments**:
  1. `samMethodType`: Signature of the functional interface method (e.g. `(Ljava/lang/Object;)Z`).
  2. `implMethod`: `MethodHandle` pointing to the synthetic implementation method (e.g. `lambda$filter$0`).
  3. `instantiatedMethodType`: Concrete instantiated signature (e.g. `(Ljava/lang/String;)Z`).

## 2. Capture Semantics
- **Stateless Lambdas**: Capture no variables. Emitted as a static constant call site returning a singleton instance.
- **Instance Capturing Lambdas**: Capture `this`. The receiver is passed as an operand to `invokedynamic`.
- **Variable Capturing Lambdas**: Capture effectively final local variables. Captured variables are passed as arguments to `invokedynamic`, prepended to the synthetic method parameters.

## 3. Decompilation Algorithm
1. Inspect `invokedynamic` target BSM. If class is `java/lang/invoke/LambdaMetafactory` and method is `metafactory` or `altMetafactory`:
2. Extract the functional interface type from the `CONSTANT_NameAndType` descriptor.
3. Extract `implMethod` handle:
   - If it points to an ordinary method -> Reconstruct **Method Reference** (e.g. `String::toUpperCase` or `this::render`).
   - If it points to a synthetic method (`lambda$...`) -> Reconstruct **Lambda Expression** `(args) -> { body }`.
4. Inline the synthetic method body directly into the lambda expression or keep it clean as a lambda block.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `render_class.cpp`:
- Inspects bootstrap method references and marks `lambda$...` methods as synthetic so they are not rendered as standalone class methods.

## 5. References
- Goetz, B. *Translation of Lambda Expressions*. Oracle Technical Paper, 2012.
