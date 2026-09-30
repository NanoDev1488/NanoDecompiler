# Article 015: Type Erasure, Bridge Methods (ACC_BRIDGE), and Covariant Overrides

## 1. Executive Summary & Specification
Java generics are implemented via **Type Erasure** (JLS §4.6): at bytecode level, generic type parameters are replaced by their erasure (typically `java.lang.Object` or the first bound).
To preserve polymorphic dispatch with covariant return types and parameterized interfaces, the compiler generates **Bridge Methods** flagged with `ACC_BRIDGE` (0x0040) and `ACC_SYNTHETIC` (0x1000).

## 2. Concrete Example: Covariant Return
```java
public class StringSupplier implements Supplier<String> {
    public String get() { return "hello"; }
}
```
Bytecode contains two methods:
1. `public String get()`: Hand-written method.
2. `public synthetic bridge Object get()`: Invokes `(String) this.get()` and returns the result.

## 3. Decompilation Filter
- Any method marked `ACC_BRIDGE` that simply delegates to another method of the same name with more specific parameter/return types must be suppressed from output. Emitting bridge methods in source causes Java compilation errors ("method already defined").

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Checks `access_flags & ACC_BRIDGE` and skips rendering redundant bridge methods.

## 5. References
- Bracha, G. *Generics in the Java Programming Language*. Sun Microsystems, 2004.
