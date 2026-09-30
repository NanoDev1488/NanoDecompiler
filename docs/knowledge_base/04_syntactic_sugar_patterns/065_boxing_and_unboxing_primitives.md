# Article 065: Auto-Boxing and Unboxing Primitive Conversions

## 1. Executive Summary & Specification
Java 5 introduced autoboxing (automatic conversion between primitives and their wrapper classes):
- **Boxing**: `Integer.valueOf(intVal)`, `Boolean.valueOf(boolVal)`, etc.
- **Unboxing**: `intObj.intValue()`, `boolObj.booleanValue()`, etc.

## 2. Decompiler Simplification Pass
- If a boxed wrapper is passed into a context expecting an object (e.g. `list.add(Integer.valueOf(x))`), simplify to `list.add(x)`.
- If an unboxed call occurs in an arithmetic context (e.g. `x.intValue() + 5`), simplify to `x + 5`.
- Preserve explicit `valueOf()` or `.intValue()` only if necessary to disambiguate overloaded method calls (e.g. `remove(int index)` vs `remove(Object obj)`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Context-sensitive autoboxing simplification pass.

## 4. References
- JLS §5.1.7: Boxing Conversion.
- JLS §5.1.8: Unboxing Conversion.
