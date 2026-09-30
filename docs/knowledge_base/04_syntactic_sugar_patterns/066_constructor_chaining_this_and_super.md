# Article 066: Constructor Chaining: this() and super() Placement

## 1. Executive Summary & Invariant Rules
In Java, every constructor must invoke either another constructor of the same class via `this(...)` or a constructor of the direct superclass via `super(...)` as its very first statement (JLS §8.8.7).
In bytecode:
- `aload_0` (`this`) followed by `invokespecial <init>`.

## 2. Decompilation Rules
1. If the first statement is `super()` with no arguments, and the superclass is `java.lang.Object`, the call can be omitted (it is implicit in Java).
2. If the first statement is `this(...)` or `super(...)` with arguments, it must be rendered explicitly as the first line of the constructor body.
3. Field initializers in the class declaration must not be duplicated inside constructors that delegate via `this(...)`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `stackvm.cpp`:
- Separates `super(...)`/`this(...)` constructor calls from subsequent method statements.

## 4. References
- JLS §8.8.7: Constructor Body.
