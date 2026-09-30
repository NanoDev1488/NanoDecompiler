# Article 001: JVM Execution Model, Stack Frames, and Memory Areas

## 1. Executive Summary & Specification
The Java Virtual Machine (JVM) is an abstract stack-based computing machine defined by the Java Virtual Machine Specification (JVMS §2, §3). Understanding its concrete memory architecture is foundational for decompilation:
- **Operand Stack**: Last-In-First-Out (LIFO) stack of 32-bit words (64-bit `long` and `double` occupy two consecutive slots). All arithmetic, logical, and invocation operations consume and push stack values.
- **Local Variable Table (LVT)**: Zero-indexed array of 32-bit slots storing parameters and local variables. `this` resides at index 0 for instance methods.
- **Frame Data**: Constant pool resolution references, normal method completion return handling, and exception dispatch dispatchers.
- **Heap and Metaspace**: Dynamic object allocations and class metadata (formerly PermGen).

## 2. Low-Level Mechanics & Bytecode Semantics
During execution of a method frame:
```
+-------------------------------------------------------+
| Method Frame (Thread Stack)                           |
|  +--------------------+  +-------------------------+  |
|  | Local Variables    |  | Operand Stack           |  |
|  | [0] this (ref)     |  | [2] expr2 (int)         |  |
|  | [1] param1 (int)   |  | [1] expr1 (ref)         |  |
|  | [2] localA (long0) |  | [0] base (ref)          |  |
|  | [3] localA (long1) |  +-------------------------+  |
|  +--------------------+                               |
+-------------------------------------------------------+
```
Key invariant: The JVM verification algorithm requires that at any given bytecode instruction, the operand stack depth and the types of all slots are fixed and determinable statically, without dynamic execution.

## 3. Decompiler Recovery Algorithm
A decompiler cannot simply output push/pop instructions. It transforms the transient operand stack into an Abstract Syntax Tree (AST):
1. **Symbolic Stack Execution**: Walk basic blocks, pushing symbolic expressions (`std::shared_ptr<AstExpr>`) onto a simulated stack instead of primitive values.
2. **Variable Association**: When an instruction writes to LVT (`istore`, `astore`), pop the top symbolic expression and emit an assignment `VarNode = Expr`.
3. **Expression Inlining**: If a pushed expression is immediately consumed by a subsequent instruction within the same basic block without local side-effects, inline the sub-expression tree directly into the consumer AST node.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `engine.cpp`:
- `StackVM::simulate_block()` models the JVM frame state.
- `stack_depth` tracking matches JVMS stack invariants.
- Multi-depth underflow handling reconciles stack items carried across CFG edges.

## 5. References
- Lindholm, T., Yellin, F., Bracha, G., & Buckley, A. *The Java Virtual Machine Specification, Java SE 21 Edition*. Chapter 2: The Structure of the Java Virtual Machine.
- Cifuentes, C. *Reverse Compilation Techniques*. Queensland University of Technology, 1994.
