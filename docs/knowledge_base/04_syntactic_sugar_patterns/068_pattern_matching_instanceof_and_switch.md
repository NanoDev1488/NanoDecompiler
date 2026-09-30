# Article 068: Pattern Matching for instanceof and Switch (Java 16 - 21)

## 1. Executive Summary & Specification
- **Pattern Matching for `instanceof` (JEP 394, Java 16)**:
  `if (obj instanceof String s)` combines type test and cast into a single construct.
  - Bytecode: `instanceof String`, followed by conditional branch, then `checkcast String`, and `astore s`.
- **Pattern Matching for `switch` (JEP 441, Java 21)**:
  Supports type patterns, record patterns, and `when` guards:
  ```java
  switch (obj) {
      case Integer i -> ...;
      case String s when s.length() > 5 -> ...;
      default -> ...;
  }
  ```
  - Bytecode: Linked via `SwitchBootstraps.typeSwitch`.

## 2. Decompilation Reconstruction
- Merge `instanceof Type` followed immediately by cast and assignment into `instanceof Type varName`.
- Decode `SwitchBootstraps` bootstrap arguments to emit clean pattern switch cases.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `structure.cpp`:
- Recognizes pattern matching sequences and eliminates redundant casts.

## 4. References
- JEP 394: *Pattern Matching for instanceof*. OpenJDK, 2021.
- JEP 441: *Pattern Matching for switch*. OpenJDK, 2023.
