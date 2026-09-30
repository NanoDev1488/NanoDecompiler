# Article 095: Evolution of JVM Bytecode Over 30 Years (Java 1.0 to Java 23)

## 1. Executive Summary & Chronological Milestones
- **Java 1.0 - 1.4 (1995-2002)**: Simple bytecode, `jsr`/`ret` subroutines, runtime type inference verification.
- **Java 5 (2004)**: Generics, Enums, Annotations, Autoboxing, Varargs (compiled via synthetic bridge methods and attributes).
- **Java 6 (2006)**: Type checking verification via mandatory `StackMapTable`.
- **Java 7 (2011)**: `invokedynamic` opcode, Method Handles, String switch desugaring, Try-with-resources.
- **Java 8 (2014)**: Lambda expressions via `LambdaMetafactory`, Default interface methods, Type annotations.
- **Java 9 - 11 (2017-2018)**: JPMS modules (`module-info`), Nest-Based Access Control, Indy string concatenation.
- **Java 15 - 17 (2020-2021)**: Sealed classes, Records, Hidden classes.
- **Java 21 - 23 (2023-2024)**: Pattern matching switches, Virtual Threads (Project Loom), Unnamed classes.

## 2. Architectural Implications
A robust decompiler cannot treat bytecode as a static format. It must dynamically detect the class file version and apply version-appropriate structuring and desugaring passes.

## 3. References
- Goetz, B., et al. *Thirty Years of the Java Virtual Machine*. Oracle Technical Keynote, 2025.
