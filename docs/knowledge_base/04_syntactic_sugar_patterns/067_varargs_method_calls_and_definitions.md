# Article 067: Varargs Method Calls and Method Signatures (ACC_VARARGS)

## 1. Executive Summary & Bytecode Representation
Variable arity methods (`public void log(String format, Object... args)`) are marked with `ACC_VARARGS` (0x0080) in `access_flags`.
At the call site:
- The compiler generates an explicit array instantiation: `new Object[] { arg1, arg2 }`.

## 2. Call-Site Decompilation
1. When calling a method marked `ACC_VARARGS`:
   - If the last argument is an explicitly initialized array matching the varargs component type, unpack the array elements directly into the argument list:
     $$	ext{log}("x=%d", 	ext{new Object[]}\{ 42 \}) \implies 	ext{log}("x=%d", 42);$$

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `stackvm.cpp`:
- Emits `T... args` in method headers and flattens array arguments at call sites.

## 4. References
- JLS §8.4.1: Formal Parameters - Variable Arity Parameters.
