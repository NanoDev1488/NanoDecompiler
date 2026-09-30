# Article 012: Nest-Based Access Control (JEP 181, Java 11+) and Attribute Mechanics

## 1. Executive Summary & Specification
Prior to Java 11, the JVM had no native concept of classes within the same file sharing private access. JEP 181 (Java 11) introduced **Nest-Based Access Control**:
- **NestHost**: Attribute in member class pointing to the top-level enclosing class.
- **NestMembers**: Attribute in top-level class listing all member classes in the nest.
- The JVM verifier permits direct private access (`invokevirtual`, `invokespecial`, `getfield`, `putfield`) between any two classes belonging to the same nest, eliminating synthetic `access$000` bridge methods.

## 2. Impact on Decompilation
- Source code decompiled from Java 11+ bytecode matches author intent much more closely, as private calls are authentic single bytecodes without compiler-generated trampoline artifacts.
- When decompiling pre-11 bytecode, decompilers can optionally "upgrade" desugared bridges into clean direct private calls.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp`:
- Parses `NestHost` and `NestMembers` attributes.
- Links nested classes during whole-JAR project analysis.

## 4. References
- JEP 181: *Nest-Based Access Control*. OpenJDK, 2018.
- JVMS §4.7.28, §4.7.29.
