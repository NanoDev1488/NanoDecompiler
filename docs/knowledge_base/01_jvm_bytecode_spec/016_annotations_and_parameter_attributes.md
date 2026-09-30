# Article 016: Runtime Annotations, Parameter Metadata, and Type Annotations

## 1. Executive Summary & Specification
Java supports extensive declarative metadata stored across specialized attributes:
- `RuntimeVisibleAnnotations` / `RuntimeInvisibleAnnotations` (Classes, Fields, Methods).
- `RuntimeVisibleParameterAnnotations` / `RuntimeInvisibleParameterAnnotations` (Method parameters).
- `MethodParameters` (Java 8 parameter names when compiled with `-parameters`).
- `RuntimeVisibleTypeAnnotations` (JSR 308, Java 8 type-use annotations like `@NotNull List<@Valid String>`).

## 2. Annotation Value Format (`element_value`)
An annotation value is a tagged union:
- Primitives (`B`, `C`, `I`, etc.) -> Constant pool constant.
- Strings (`s`) -> Utf8 constant.
- Enums (`e`) -> Enum class name + enum constant name.
- Class literals (`c`) -> Class descriptor.
- Nested annotations (`@`) -> `annotation` structure.
- Arrays (`[`) -> Array of `element_value` items.

## 3. Decompiler Rendering
- Render annotations directly preceding the target entity (class, field, method, parameter).
- Preserve named value pairs `@MyAnno(value = "x", priority = 1)` and simplify single default values `@MyAnno("x")`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Reconstructs annotation syntax and decorates declarations cleanly.

## 5. References
- JVMS §4.7.16 - §4.7.22.
