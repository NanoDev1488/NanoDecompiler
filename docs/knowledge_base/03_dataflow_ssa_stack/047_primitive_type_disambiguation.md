# Article 047: Primitive Type Disambiguation: boolean, byte, char, short, and int

## 1. Executive Summary & JVM 32-Bit Unification
In JVM bytecode, boolean, byte, char, short, and int are all represented internally as 32-bit integers and manipulated via the same `i*` opcodes (`iload`, `istore`, `iadd`, `ireturn`).
The decompiler must disambiguate the true high-level primitive type.

## 2. Disambiguation Heuristics
1. **Contextual Constraints**:
   - Storing into a typed field (`putfield boolField Z`) $\implies$ variable is `boolean`.
   - Passing into a method parameter (`foo(boolean)`) $\implies$ variable is `boolean`.
   - Array operations: `baload`/`bastore` on `boolean[]` vs `byte[]`.
2. **Value Range Analysis**:
   - If a variable is only ever assigned `0` or `1`, and used in conditional jumps (`ifeq`, `ifne`), classify as `boolean`.
   - If assigned character constants (e.g. `'A'`, `'
'`), classify as `char`.
3. **Type Conversions**:
   - `i2b` $\implies$ `byte`.
   - `i2c` $\implies$ `char`.
   - `i2s` $\implies$ `short`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- Contextual heuristic solver for primitive types.

## 4. References
- JVMS §2.3: Primitive Types and Values.
