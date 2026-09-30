# Article 069: Record Canonical vs Compact Constructors

## 1. Executive Summary & Specification
In Java records, two constructor styles exist:
1. **Canonical Constructor**: Declares all components explicitly and assigns them to record fields.
2. **Compact Constructor**: Omits parameter list and field assignments; used solely for input validation and normalization:
   ```java
   public record Range(int start, int end) {
       public Range {
           if (start > end) throw new IllegalArgumentException();
       }
   }
   ```

## 2. Bytecode Analysis & Decompilation
In bytecode, the compact constructor is compiled into a standard constructor with parameters and trailing field assignments (`putfield`).
- Decompiler rule: If constructor parameters match record components, and all parameters are assigned to their respective fields at the end of the constructor, strip the trailing assignments and render as a compact constructor.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Simplifies record constructor declarations to compact form.

## 4. References
- JEP 395: *Records*. OpenJDK, 2021.
