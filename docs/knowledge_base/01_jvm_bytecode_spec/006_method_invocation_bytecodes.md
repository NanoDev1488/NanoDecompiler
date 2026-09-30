# Article 006: Method Invocation Bytecodes: Resolution, Dynamic Dispatch, and Special Cases

## 1. Executive Summary & Specification
The JVM provides four classic invocation bytecodes, each embodying specific dispatch semantics (JVMS §6.5):
1. `invokevirtual` (0xB6): Dynamic dispatch based on the runtime class of the target object (`vtable` lookup). Consumes receiver + parameters.
2. `invokespecial` (0xB7): Non-virtual dispatch. Specifically used for:
   - Instance initialization methods (`<init>`).
   - Private methods of current class.
   - Superclass methods via `super.method()`.
3. `invokestatic` (0xB8): Static dispatch to class methods. Consumes parameters without receiver object. Triggers class initialization if not already initialized.
4. `invokeinterface` (0xB9): Interface method dispatch (`itable` lookup). Consumes receiver + parameters, requires operand count and reserved zero byte.

## 2. Dispatch Resolution Matrix
```
+-------------------+--------------------+------------------------+------------------+
| Opcode            | Target Resolved At | Dynamic Dispatch?      | Receiver Null?   |
+-------------------+--------------------+------------------------+------------------+
| invokevirtual     | Link / Runtime     | Yes (vtable)           | NullPointerEx    |
| invokespecial     | Compile / Link     | No (static binding)    | NullPointerEx    |
| invokestatic      | Compile / Link     | No (class method)      | N/A              |
| invokeinterface   | Link / Runtime     | Yes (itable)           | NullPointerEx    |
+-------------------+--------------------+------------------------+------------------+
```

## 3. Decompilation Challenges
- **Super Invocations**: An `invokespecial` referencing a method of `super_class` on `this` must be rendered as `super.method(...)` rather than `this.method(...)`.
- **Private Calls**: Modern compilers (Java 11+) emit `invokevirtual` or `invokespecial` for private calls within nestmates; older compilers synthesized `access$000` bridge methods.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Resolves method descriptors to determine exact parameter pop count.
- Distinguishes `<init>` constructor calls from ordinary instance invocations, linking them with preceding `new` allocations.

## 5. References
- JVMS §6.5: `invokevirtual`, `invokespecial`, `invokestatic`, `invokeinterface`.
