# Article 013: Sealed Classes and the PermittedSubclasses Attribute (Java 17+)

## 1. Executive Summary & Specification
Sealed classes (JEP 409, Java 17) restrict which classes or interfaces may extend or implement them:
```java
public sealed interface Shape permits Circle, Rectangle, Polygon {}
```
In bytecode (JVMS §4.7.31):
- The sealed class possesses a `PermittedSubclasses` attribute containing an array of `CONSTANT_Class` indices.
- Permitted subclasses must declare one of three modifiers: `final`, `sealed`, or `non-sealed`.

## 2. Decompilation Reconstruction
- Read `PermittedSubclasses` attribute from class file.
- If present, append `permits ClassA, ClassB, ...` to the class declaration header in the generated Java AST.
- Verify subclass modifiers: if a subclass is not `final` and not `sealed`, emit `non-sealed class ...`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Checks `PermittedSubclasses` and formats clean `permits` clauses in class headers.

## 4. References
- JEP 409: *Sealed Classes*. OpenJDK, 2021.
- JVMS §4.7.31: The PermittedSubclasses Attribute.
