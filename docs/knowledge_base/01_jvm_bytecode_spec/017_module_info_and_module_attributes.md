# Article 017: Java Platform Module System (JPMS) and module-info.class Bytecode

## 1. Executive Summary & Specification
Introduced in Java 9 (Project Jigsaw, JSR 376), JPMS defines explicit module boundaries encapsulated in a special class file: `module-info.class`:
- `access_flags` has `ACC_MODULE` (0x8000) set.
- Superclass and interfaces are empty.
- Contains the `Module` attribute (JVMS §4.7.25).

## 2. Module Attribute Directives
- **requires**: Module dependencies (`transitive`, `static`).
- **exports**: Packages exposed to consumers (`to` specific modules).
- **opens**: Packages opened for deep reflection.
- **uses**: Service interfaces consumed via `java.util.ServiceLoader`.
- **provides**: Service implementations (`with` provider classes).

## 3. Decompiler Pipeline
When decompiling `module-info.class`:
- Emit `module <module_name> { ... }` instead of `class module-info`.
- Format all requires, exports, opens, uses, and provides directives inside the module block.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Special detection for `module-info` classes, emitting valid Java module declarations.

## 5. References
- JEP 261: *Module System*. OpenJDK, 2017.
- JVMS §4.7.25: The Module Attribute.
