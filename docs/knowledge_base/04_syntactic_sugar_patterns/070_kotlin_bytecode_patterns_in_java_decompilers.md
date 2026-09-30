# Article 070: Kotlin Bytecode Patterns in Java Decompilers (@Metadata, Intrinsics)

## 1. Executive Summary & Kotlin Compilation Artifacts
When Kotlin code is compiled to JVM bytecode, distinct compiler patterns emerge:
1. **Null Safety Intrinsics**: `Intrinsics.checkNotNullParameter(param, "param")` at method entry points.
2. **Default Arguments**: Synthetic methods with bitmasks `foo$default(..., int mask, Object handler)`.
3. **Companion Objects**: Static inner class named `Companion` with static field `Companion Companion;`.
4. **Metadata Attribute**: Binary protobuf stored in `@kotlin.Metadata` containing original Kotlin function signatures, nullability flags, and property declarations.

## 2. Decompilation Handling
- A Java decompiler should clean up Kotlin intrinsic noise (e.g. simplify `checkNotNullParameter` calls or flag them as synthetic guards).
- Support reading `@kotlin.Metadata` to recover accurate non-null types and parameter names when present.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `naming_hints.cpp`:
- Detects Kotlin metadata and cleans up synthetic companion artifacts.

## 4. References
- Breslav, A. *Kotlin: Design and Implementation on the JVM*. ACM SPLASH, 2016.
