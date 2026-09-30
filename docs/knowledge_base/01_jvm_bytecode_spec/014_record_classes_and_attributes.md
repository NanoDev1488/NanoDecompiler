# Article 014: Record Classes, RecordComponentInfo, and Canonical Constructor Reconstruction

## 1. Executive Summary & Specification
Records (JEP 395, Java 16) provide compact syntax for immutable data carriers:
```java
public record Point(int x, int y) {}
```
In bytecode:
- Extends `java.lang.Record`.
- Declares the `Record` attribute (JVMS §4.7.30) listing components (`record_component_info`), their types, signatures, and annotations.
- Declares private final fields corresponding to each component.
- Implements accessor methods matching component names (`x()`, `y()`).
- `equals`, `hashCode`, and `toString` are desugared using `invokedynamic` calling `java.lang.runtime.ObjectMethods.bootstrap`.

## 2. Decompilation Algorithm
1. Check if class extends `java.lang.Record` and has `Record` attribute.
2. If true, emit `public record Name(Type1 comp1, Type2 comp2) { ... }`.
3. Suppress automatic emission of:
   - Private final fields matching record components.
   - Canonical constructor unless it contains custom validation logic (compact constructor).
   - Standard accessors `x()`, `y()`.
   - Indy-backed `equals`, `hashCode`, `toString`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Detects record classes and formats them in concise modern Java record syntax.

## 4. References
- JEP 395: *Records*. OpenJDK, 2021.
- JVMS §4.7.30: The Record Attribute.
