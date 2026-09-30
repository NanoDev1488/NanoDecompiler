# Article 020: Class Loading, Verification, Preparation, and Initialization Semantics

## 1. Executive Summary & Specification
The lifecycle of a Java class within the JVM comprises five distinct phases (JVMS §5):
1. **Loading**: Acquiring raw binary bytes and creating the `java.lang.Class` instance.
2. **Verification**: Ensuring structural correctness, stack map frame adherence, and type safety constraints.
3. **Preparation**: Allocating static storage for class fields and initializing them to default values (0, null).
4. **Resolution**: Resolving symbolic references in the constant pool to direct memory pointers.
5. **Initialization (`<clinit>`)**: Executing static field initializers and static initialization blocks.

## 2. Static Initialization Order & Decompilation
The compiler packs all top-level static field initializers (`public static int x = 42;`) and `static { ... }` blocks into a single `<clinit>` method in textual order of declaration.
- Decompiler must split `<clinit>`:
  - Simple constant assignments to static fields are moved back to field declarations (`public static int x = 42;`).
  - Complex logic, try-catch, loops, or interdependent operations remain inside a reconstructed `static { ... }` block.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Analyzes `<clinit>` and lifts field initializer expressions directly onto class field definitions.

## 4. References
- JVMS §5: Loading, Linking, and Initializing.
