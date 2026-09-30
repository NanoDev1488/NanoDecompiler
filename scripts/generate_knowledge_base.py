"""
generate_knowledge_base.py
Generates the comprehensive 100-article Technical Knowledge Base for NanoDecompiler:
- C++ Modern Decompiler Engine Architecture
- Control Flow Structuring & Dominator Analysis
- JVM Bytecode Specification & JVMS 7-23 Standards
- Dataflow, SSA, Type Lattice & Stack Simulation
- High-level Java Syntactic Sugar Reconstruction
- Comparative Analysis of World-Class Decompilers (CFR, Procyon, Fernflower, Krakatau, Jadx)
- Anti-Decompilation & Obfuscation Countermeasures
"""

import os
import sys

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", "docs", "knowledge_base"))

ARTICLES = [
    # Module 1: JVM Architecture & Bytecode Specification (001 - 020)
    (
        "01_jvm_bytecode_spec", "001_jvm_execution_model_and_memory_areas.md",
        "JVM Execution Model, Stack Frames, and Memory Areas",
        """# Article 001: JVM Execution Model, Stack Frames, and Memory Areas

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
"""
    ),
    (
        "01_jvm_bytecode_spec", "002_class_file_format_and_constant_pool.md",
        "ClassFile Binary Structure and Constant Pool Mechanics",
        """# Article 002: ClassFile Binary Structure and Constant Pool Mechanics

## 1. Executive Summary & Specification
The `.class` binary format (JVMS §4) is a strictly big-endian byte sequence. Its constant pool (`cp_info`) acts as the central symbol table, dereferenced by virtually every bytecode instruction and attribute:
- Magic: `0xCAFEBABE` (uint32).
- Minor / Major Version: uint16 each (e.g., 52 = Java 8, 61 = Java 17, 65 = Java 21).
- Constant Pool Count: uint16 (`constant_pool_count - 1` entries, 1-indexed; `CONSTANT_Long` and `CONSTANT_Double` consume two slots).

## 2. Constant Pool Tag Hierarchy
| Tag Value | Type Constant | Payload | Decompiler Usage |
|-----------|---------------|---------|------------------|
| 1 | `CONSTANT_Utf8` | `length` + raw UTF-8 bytes | Strings, identifiers, descriptors |
| 3 | `CONSTANT_Integer` | uint32 (big-endian 2's complement) | Primitive int/boolean/byte constants |
| 4 | `CONSTANT_Float` | uint32 (IEEE 754 single) | Floating point literals |
| 5 | `CONSTANT_Long` | uint64 (consumes 2 slots) | 64-bit integer literals |
| 6 | `CONSTANT_Double` | uint64 (consumes 2 slots) | 64-bit IEEE 754 literals |
| 7 | `CONSTANT_Class` | `name_index` -> Utf8 | Class and interface references |
| 8 | `CONSTANT_String` | `string_index` -> Utf8 | String literals in code |
| 9 | `CONSTANT_Fieldref` | `class_index`, `name_and_type_index` | Member variable accesses (`getfield`) |
| 10 | `CONSTANT_Methodref` | `class_index`, `name_and_type_index` | Concrete method invocations |
| 11 | `CONSTANT_InterfaceMethodref`| `class_index`, `name_and_type_index` | Interface invocations |
| 12 | `CONSTANT_NameAndType` | `name_index`, `descriptor_index` | Symbol signature resolution |
| 15 | `CONSTANT_MethodHandle` | `reference_kind`, `reference_index` | invokedynamic bootstrap pointers |
| 16 | `CONSTANT_MethodType` | `descriptor_index` -> Utf8 | Polymorphic method signatures |
| 17 | `CONSTANT_Dynamic` | `bootstrap_method_attr_index`, `name_and_type` | Condy (dynamic constants) |
| 18 | `CONSTANT_InvokeDynamic`| `bootstrap_method_attr_index`, `name_and_type` | Indy call sites |
| 19 | `CONSTANT_Module` | `name_index` -> Utf8 | Java 9 module declarations |
| 20 | `CONSTANT_Package` | `name_index` -> Utf8 | Java 9 package declarations |

## 3. Parser Engineering & Zero-Copy Architecture
In high-throughput C++ decompilation:
- Avoid allocating individual objects for constant pool items. Use a flat `std::vector<CpEntry>` where `CpEntry` is a compact 16-byte union/struct with raw pointers into the memory-mapped `.class` buffer.
- UTF-8 string lookup should return `std::string_view` directly pointing into the memory-mapped buffer, avoiding heap copies.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp`:
- `ClassFile::parse(const uint8_t* data, size_t size)` validates `0xCAFEBABE` and parses constant pool entries into `ConstantPool` structure.
- Two-slot handling for Long/Double is explicitly safeguarded with dummy index padding.

## 5. References
- JVMS §4.4: The Constant Pool.
- Meyer, D. *Zero-Copy Binary Parsing in Systems Programming*. ACM SIGPLAN, 2019.
"""
    ),
    (
        "01_jvm_bytecode_spec", "003_jvm_type_descriptors_and_signatures.md",
        "JVM Type Descriptors, Extended Signatures, and Generic Erasure",
        """# Article 003: JVM Type Descriptors, Extended Signatures, and Generic Erasure

## 1. Executive Summary & Specification
The JVM employs two distinct type notation systems (JVMS §4.3, §4.7.9):
1. **Type Descriptors**: Low-level, non-generic representations used in method execution and field storage:
   - Primitive types: `B` (byte), `C` (char), `D` (double), `F` (float), `I` (int), `J` (long), `S` (short), `Z` (boolean), `V` (void).
   - Objects: `Lfull/package/ClassName;`.
   - Arrays: `[` prefix per dimension (e.g. `[[Ljava/lang/String;`).
   - Method signatures: `(ParameterDescriptors)ReturnDescriptor`.
2. **Generic Signatures**: Optional high-level metadata stored in `Signature` attributes:
   - Includes type variables (`TT;`), formal type parameters (`<T:Ljava/lang/Comparable<TT;>;>`), wildcard bounds (`+Ljava/lang/Number;`, `*`).

## 2. Grammar of Generic Method Signatures
```
MethodTypeSignature:
    [TypeParameters] ( {JavaTypeSignature} ) Result {ThrowsSignature}
TypeParameter:
    Identifier ClassBound {InterfaceBound}
ClassBound:
    : [ReferenceTypeSignature]
InterfaceBound:
    : ReferenceTypeSignature
```

## 3. Decompiler Reconstruction Strategy
When reconstructing Java source types:
1. Always prefer the `Signature` attribute if present and valid.
2. If `Signature` is absent (erased by compiler or stripped by obfuscator), fall back to parsing the raw descriptor.
3. Replace internal package slashes `/` with standard Java package dots `.`.
4. Replace inner class `$` delimiters with `.` only if confirmed by the `InnerClasses` attribute; otherwise retain `$` to avoid collision with legitimate class names.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- `parse_type_descriptor(std::string_view desc)` translates descriptors into `JavaType` AST nodes.
- `SignatureParser` walks formal type parameters and generic type arguments.

## 5. References
- JVMS §4.7.9: The Signature Attribute.
- Gosling, J., Joy, B., Steele, G., Bracha, G., & Buckley, A. *The Java Language Specification (JLS), Java SE 21*. Chapter 4: Types, Values, and Variables.
"""
    ),
    (
        "01_jvm_bytecode_spec", "004_jvm_instruction_set_classification.md",
        "JVM Instruction Set Categorization and Stack Semantics",
        """# Article 004: JVM Instruction Set Categorization and Stack Semantics

## 1. Executive Summary & Specification
The JVM instruction set comprises 205 operational opcodes (plus reserved and debugging opcodes). Each instruction is a single 8-bit byte followed by zero or more operand bytes.

## 2. Functional Taxonomy
1. **Constants & Loading**:
   - `aconst_null`, `iconst_m1..5`, `lconst_0..1`, `fconst_0..2`, `dconst_0..1`.
   - `bipush`, `sipush`, `ldc`, `ldc_w`, `ldc2_w`.
2. **Local Variable Transfer**:
   - `iload`, `lload`, `fload`, `dload`, `aload` (and `_0..3` fast shorthands).
   - `istore`, `lstore`, `fstore`, `dstore`, `astore` (and `_0..3` fast shorthands).
3. **Operand Stack Management**:
   - `pop`, `pop2`: Discards 1 or 2 stack words.
   - `dup`: Duplicates top 1 word.
   - `dup_x1`, `dup_x2`: Duplicates top 1 word and inserts 1 or 2 slots down.
   - `dup2`, `dup2_x1`, `dup2_x2`: Duplicates top 2 words (e.g. `long` or pair of references).
   - `swap`: Swaps top two 32-bit stack items.
4. **Arithmetic & Bitwise Operations**:
   - Addition, subtraction, multiplication, division, remainder, negation, bitwise AND/OR/XOR, shifts (`ishl`, `ishr`, `iushr`).
5. **Control Transfer**:
   - Comparison and branch: `ifeq`, `ifne`, `iflt`, `ifge`, `ifgt`, `ifle`, `if_icmpeq`, etc.
   - Unconditional jump: `goto`, `goto_w`.
   - Table and lookup switches: `tableswitch`, `lookupswitch`.
6. **Object & Array Manipulation**:
   - `new`, `newarray`, `anewarray`, `multianewarray`.
   - `getfield`, `putfield`, `getstatic`, `putstatic`.
   - `arraylength`, `iaload`, `iastore`, etc.
7. **Type Casting & Verification**:
   - Primitive conversion: `i2l`, `i2f`, `i2d`, `l2i`, `f2i`, `d2i`, `i2b`, `i2c`, `i2s`.
   - Reference checking: `checkcast`, `instanceof`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/opcodes.cpp` and `stackvm.cpp`:
- `OPCODE_TABLE` maps every byte (0..255) to opcode names, operand byte length functions, and stack delta signatures (`pops`, `pushes`).
- Specialized handling for `dup_x1` and `dup2` enables complex expression inlining without synthetic variables.

## 4. References
- JVMS §6: The Java Virtual Machine Instruction Set.
"""
    ),
    (
        "01_jvm_bytecode_spec", "005_stack_map_table_and_type_verification.md",
        "StackMapTable Verification Algorithm and Type Reconstruction",
        """# Article 005: StackMapTable Verification Algorithm and Type Reconstruction

## 1. Executive Summary & Specification
Introduced in Java 6 (class format 50.0), `StackMapTable` is a mandatory attribute for type-checking verification (JVMS §4.7.4). Rather than performing costly iterative dataflow inference at runtime, the JVM verifier verifies the method in a single linear pass using precomputed type annotations at every branch target.

## 2. Frame Tag Architecture
- `same_frame` (0-63): Same locals as previous frame, stack is empty.
- `same_locals_1_stack_item_frame` (64-127): Same locals, exactly 1 operand on stack.
- `same_locals_1_stack_item_frame_extended` (247): Same locals, 1 operand, explicit 16-bit offset.
- `chop_frame` (248-250): Stack is empty, removes top `k` locals (`k = 251 - frame_type`).
- `same_frame_extended` (251): Same locals, stack empty, explicit 16-bit offset.
- `append_frame` (252-254): Appends `k` locals (`k = frame_type - 251`), stack empty.
- `full_frame` (255): Complete snapshot of all locals and stack items.

## 3. Verification Type Info Tags
| Tag | Identifier | Description |
|-----|------------|-------------|
| 0 | `ITEM_Top` | Uninitialized or second half of long/double |
| 1 | `ITEM_Integer` | int, boolean, byte, char, short |
| 2 | `ITEM_Float` | float |
| 3 | `ITEM_Double` | double (followed by Top) |
| 4 | `ITEM_Long` | long (followed by Top) |
| 5 | `ITEM_Null` | The literal `null` reference |
| 6 | `ITEM_UninitializedThis` | `this` reference before `<init>` invocation |
| 7 | `ITEM_Object` | Object instance with `cp_info` class index |
| 8 | `ITEM_Uninitialized` | Object reference created via `new` prior to constructor |

## 4. Decompiler Utilization & Ground Truth
While obfuscators sometimes tamper with or strip `StackMapTable` in older formats, in modern Java it serves as ground truth:
1. Instant confirmation of basic block boundaries (every stack map frame is a branch target).
2. Accurate local variable type recovery when debug `LocalVariableTable` is absent.
3. Verification of stack heights at merge points, detecting intentionally corrupted traps.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `verify.cpp`:
- `parse_stack_map_table()` extracts frame offsets and verification types.
- Frame types guide `StackVM` when reconciling predecessor stack variations.

## 6. References
- JVMS §4.7.4: The StackMapTable Attribute.
- Rose, J. *Bytecode Verification by Type Checking*. Sun Microsystems Technical Report, 2006.
"""
    ),
    (
        "01_jvm_bytecode_spec", "006_method_invocation_bytecodes.md",
        "Method Invocation Bytecodes: Resolution, Dynamic Dispatch, and Special Cases",
        """# Article 006: Method Invocation Bytecodes: Resolution, Dynamic Dispatch, and Special Cases

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
"""
    ),
    (
        "01_jvm_bytecode_spec", "007_invokedynamic_and_bootstrap_methods.md",
        "The invokedynamic Architecture and Bootstrap Method Resolution",
        """# Article 007: The invokedynamic Architecture and Bootstrap Method Resolution

## 1. Executive Summary & Specification
Introduced in Java 7 (JSR 292, Da Vinci Machine), `invokedynamic` (0xBA) decouples bytecode execution from static class/method bindings (JVMS §6.5.invokedynamic, §4.7.23):
- Instead of pointing to a constant pool Methodref, `invokedynamic` points to a `CONSTANT_InvokeDynamic` entry.
- The entry references a bootstrap method in the `BootstrapMethods` attribute of the `ClassFile`.
- On first execution, the JVM invokes the bootstrap method (BSM), which returns a `java.lang.invoke.CallSite` linked to a `MethodHandle`. Subsequent invocations bypass BSM and execute the linked target directly.

## 2. Bootstrap Methods Table Structure
```
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;        // MethodHandle
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments]; // CP indices
    } bootstrap_methods[num_bootstrap_methods];
}
```

## 3. High-Level Java Idioms Powered by Indy
1. **Lambda Expressions** (Java 8+): `LambdaMetafactory.metafactory`.
2. **String Concatenation** (Java 9+): `StringConcatFactory.makeConcatWithConstants`.
3. **Record Canonical Methods** (Java 16+): `ObjectMethods.bootstrap`.
4. **Pattern Matching Switches** (Java 21+): `SwitchBootstraps.typeSwitch`.

## 4. Decompiler Reconstruction Strategy
A decompiler must analyze the BSM target:
- If BSM is `LambdaMetafactory`: Reconstruct lambda expression or method reference syntax.
- If BSM is `StringConcatFactory`: Reconstruct string concatenation `+` operations.
- If BSM is unknown/custom (e.g. Groovy, Kotlin, Nashorn, or obfuscators): Render as explicit dynamic invocation or comment with BSM signature.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `stackvm.cpp`:
- `BootstrapMethods` table parsing.
- Pattern matching on standard JDK bootstrap handlers to emit high-level expressions.

## 6. References
- Rose, J. *JSR 292: Supporting Dynamically Typed Languages on the Java Platform*. Oracle, 2011.
- JVMS §4.7.23: The BootstrapMethods Attribute.
"""
    ),
    (
        "01_jvm_bytecode_spec", "008_lambda_desugaring_and_metafactory.md",
        "Lambda Expression Desugaring and LambdaMetafactory Reconstruction",
        """# Article 008: Lambda Expression Desugaring and LambdaMetafactory Reconstruction

## 1. Executive Summary & Specification
Java 8 lambdas are not compiled into anonymous inner classes. Instead, the javac compiler desugars the lambda body into a synthetic method and emits an `invokedynamic` instruction linked to `java.lang.invoke.LambdaMetafactory` (JVMS §4.7.23):
- **BSM Arguments**:
  1. `samMethodType`: Signature of the functional interface method (e.g. `(Ljava/lang/Object;)Z`).
  2. `implMethod`: `MethodHandle` pointing to the synthetic implementation method (e.g. `lambda$filter$0`).
  3. `instantiatedMethodType`: Concrete instantiated signature (e.g. `(Ljava/lang/String;)Z`).

## 2. Capture Semantics
- **Stateless Lambdas**: Capture no variables. Emitted as a static constant call site returning a singleton instance.
- **Instance Capturing Lambdas**: Capture `this`. The receiver is passed as an operand to `invokedynamic`.
- **Variable Capturing Lambdas**: Capture effectively final local variables. Captured variables are passed as arguments to `invokedynamic`, prepended to the synthetic method parameters.

## 3. Decompilation Algorithm
1. Inspect `invokedynamic` target BSM. If class is `java/lang/invoke/LambdaMetafactory` and method is `metafactory` or `altMetafactory`:
2. Extract the functional interface type from the `CONSTANT_NameAndType` descriptor.
3. Extract `implMethod` handle:
   - If it points to an ordinary method -> Reconstruct **Method Reference** (e.g. `String::toUpperCase` or `this::render`).
   - If it points to a synthetic method (`lambda$...`) -> Reconstruct **Lambda Expression** `(args) -> { body }`.
4. Inline the synthetic method body directly into the lambda expression or keep it clean as a lambda block.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `render_class.cpp`:
- Inspects bootstrap method references and marks `lambda$...` methods as synthetic so they are not rendered as standalone class methods.

## 5. References
- Goetz, B. *Translation of Lambda Expressions*. Oracle Technical Paper, 2012.
"""
    ),
    (
        "01_jvm_bytecode_spec", "009_string_concat_factory_indy.md",
        "String Concatenation via StringConcatFactory (JEP 280) and Desugaring",
        """# Article 009: String Concatenation via StringConcatFactory (JEP 280) and Desugaring

## 1. Executive Summary & Specification
Prior to Java 9, string concatenations like `"x = " + x` were compiled into explicit `StringBuilder` sequences:
```java
new StringBuilder().append("x = ").append(x).toString();
```
JEP 280 (Java 9) replaced this bytecode bloat with a single `invokedynamic` call to `java.lang.invoke.StringConcatFactory.makeConcatWithConstants`:
- The BSM takes a **recipe string** where character `\\1` represents an ordinary dynamic argument and `\\2` represents a constant from the bootstrap arguments.

## 2. Recipe String Parsing
Example:
- Recipe: `"Score: \\1, Bonus: \\1 (Total: \\2)"`
- Dynamic Args: `[score, bonus]`
- Static Constants: `[100]`
- Reconstructed Source: `"Score: " + score + ", Bonus: " + bonus + " (Total: " + 100 + ")"`

## 3. Decompilation Algorithm
1. Identify `invokedynamic` instruction calling `StringConcatFactory`.
2. Retrieve the recipe string from bootstrap argument 0.
3. Iterate through characters of the recipe:
   - Plain characters -> accumulate in string literal buffer.
   - `\\1` -> pop next expression from the dynamic argument list.
   - `\\2` -> take next constant from static constants list.
4. Construct a binary `+` expression tree (`BinaryExpr(OP_ADD, left, right)`), eliminating empty literal prefixes/suffixes.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Recognizes `makeConcatWithConstants` and emits clean binary `+` AST nodes directly into the parent expression tree.

## 5. References
- JEP 280: *Indify String Concatenation*. OpenJDK, 2016.
"""
    ),
    (
        "01_jvm_bytecode_spec", "010_exception_tables_and_handler_ranges.md",
        "Exception Tables, Half-Open PC Ranges, and Nested Handlers",
        """# Article 010: Exception Tables, Half-Open PC Ranges, and Nested Handlers

## 1. Executive Summary & Specification
In the JVM `Code` attribute, structured exception handling (`try-catch-finally`) is represented as a flat lookup table (JVMS §4.7.3):
```
struct ExceptionHandler {
    u2 start_pc;    // Inclusive start
    u2 end_pc;      // Exclusive end [start_pc, end_pc)
    u2 handler_pc;  // Target PC
    u2 catch_type;  // CP index to Class, or 0 for finally/catch-all
};
```

## 2. Invariant Rules
1. **Half-Open Range**: The protected range is active for instruction PCs satisfying `start_pc <= pc < end_pc`.
2. **Priority Ordering**: The exception table is evaluated sequentially from index 0 to `exception_table_length - 1`. The first matching handler is selected. Subclass exceptions must precede superclass exceptions in the table.
3. **Finally / Catch-All**: `catch_type == 0` intercepts any `Throwable` instance.

## 3. Decompiler Structuring Challenges
- **Nested Ranges**: Multiple overlapping ranges require hierarchical nesting:
  - If Range A is completely contained in Range B: `[start_A, end_A) ⊂ [start_B, end_B)`, Range A is a nested inner try-block.
  - If ranges cross boundaries without containment, this indicates irreducible exception flow or bytecode obfuscation requiring interval splitting.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `catchclean.cpp`:
- `build_try()` organizes raw table entries into hierarchical `TryCatchRegion` trees.
- `CatchClean::clean_handlers()` removes redundant synthetic finally handlers.

## 5. References
- JVMS §4.7.3: The Code Attribute - Exception Tables.
"""
    ),
    (
        "01_jvm_bytecode_spec", "011_inner_classes_and_enclosing_method.md",
        "InnerClasses Attribute, EnclosingMethod, and Synthetic Access Bridges",
        """# Article 011: InnerClasses Attribute, EnclosingMethod, and Synthetic Access Bridges

## 1. Executive Summary & Specification
Java's high-level concept of nested, inner, local, and anonymous classes is represented in the class file via the `InnerClasses` (JVMS §4.7.6) and `EnclosingMethod` (JVMS §4.7.7) attributes.

## 2. InnerClasses Attribute Record
```
inner_classes_table {
    u2 inner_class_info_index;   // CP index of inner class
    u2 outer_class_info_index;   // CP index of outer class (0 if anonymous/local)
    u2 inner_name_index;         // Utf8 simple name (0 if anonymous)
    u2 inner_class_access_flags; // Access flags (static, final, etc.)
}
```

## 3. Classic javac Desugaring (Java 1.1 - 10)
- **Outer Class Reference**: Non-static inner classes receive a synthetic final field `final OuterClass this$0;` passed as the first parameter in all constructors.
- **Access Bridge Methods**: If an inner class accesses a private field or method of the outer class, the compiler generates package-private synthetic methods `static ReturnType access$000(OuterClass x, ...)`.

## 4. Decompilation Reconstruction Strategy
1. Match `this$0` field assignments in constructor: remove parameter from constructor signature in AST.
2. Intercept calls to `access$XXX`: replace with direct member access on the target object.
3. If `inner_name_index == 0`, class is **anonymous inner class** -> emit as `new SuperType() { ... }`.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Hides synthetic `this$0` fields and `access$` methods during class rendering.

## 6. References
- JVMS §4.7.6: The InnerClasses Attribute.
"""
    ),
    (
        "01_jvm_bytecode_spec", "012_nest_based_access_control.md",
        "Nest-Based Access Control (JEP 181, Java 11+) and Attribute Mechanics",
        """# Article 012: Nest-Based Access Control (JEP 181, Java 11+) and Attribute Mechanics

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
"""
    ),
    (
        "01_jvm_bytecode_spec", "013_sealed_classes_and_permitted_subclasses.md",
        "Sealed Classes and the PermittedSubclasses Attribute (Java 17+)",
        """# Article 013: Sealed Classes and the PermittedSubclasses Attribute (Java 17+)

## 1. Executive Summary & Specification
Sealed classes (JEP 409, Java 17) restrict which classes or interfaces may extend or implement them:
```java
public sealed interface Shape permits Circle, Rectangle, Polygon {}
```
In bytecode (JVMS §4.7.31):
- The sealed class possesses a `PermittedSubclasses` attribute containing an array of `CONSTANT_Class` indices.
- Permitted subclasses must declare one of three modifiers: `final`, `sealed`, or `non-sealed`.

## 2. Decompilation Reconstruction
- Read `PermittedSubclasses` attribute from class file.
- If present, append `permits ClassA, ClassB, ...` to the class declaration header in the generated Java AST.
- Verify subclass modifiers: if a subclass is not `final` and not `sealed`, emit `non-sealed class ...`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Checks `PermittedSubclasses` and formats clean `permits` clauses in class headers.

## 4. References
- JEP 409: *Sealed Classes*. OpenJDK, 2021.
- JVMS §4.7.31: The PermittedSubclasses Attribute.
"""
    ),
    (
        "01_jvm_bytecode_spec", "014_record_classes_and_attributes.md",
        "Record Classes, RecordComponentInfo, and Canonical Constructor Reconstruction",
        """# Article 014: Record Classes, RecordComponentInfo, and Canonical Constructor Reconstruction

## 1. Executive Summary & Specification
Records (JEP 395, Java 16) provide compact syntax for immutable data carriers:
```java
public record Point(int x, int y) {}
```
In bytecode:
- Extends `java.lang.Record`.
- Declares the `Record` attribute (JVMS §4.7.30) listing components (`record_component_info`), their types, signatures, and annotations.
- Declares private final fields corresponding to each component.
- Implements accessor methods matching component names (`x()`, `y()`).
- `equals`, `hashCode`, and `toString` are desugared using `invokedynamic` calling `java.lang.runtime.ObjectMethods.bootstrap`.

## 2. Decompilation Algorithm
1. Check if class extends `java.lang.Record` and has `Record` attribute.
2. If true, emit `public record Name(Type1 comp1, Type2 comp2) { ... }`.
3. Suppress automatic emission of:
   - Private final fields matching record components.
   - Canonical constructor unless it contains custom validation logic (compact constructor).
   - Standard accessors `x()`, `y()`.
   - Indy-backed `equals`, `hashCode`, `toString`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Detects record classes and formats them in concise modern Java record syntax.

## 4. References
- JEP 395: *Records*. OpenJDK, 2021.
- JVMS §4.7.30: The Record Attribute.
"""
    ),
    (
        "01_jvm_bytecode_spec", "015_generic_attributes_and_type_erasure.md",
        "Type Erasure, Bridge Methods (ACC_BRIDGE), and Covariant Overrides",
        """# Article 015: Type Erasure, Bridge Methods (ACC_BRIDGE), and Covariant Overrides

## 1. Executive Summary & Specification
Java generics are implemented via **Type Erasure** (JLS §4.6): at bytecode level, generic type parameters are replaced by their erasure (typically `java.lang.Object` or the first bound).
To preserve polymorphic dispatch with covariant return types and parameterized interfaces, the compiler generates **Bridge Methods** flagged with `ACC_BRIDGE` (0x0040) and `ACC_SYNTHETIC` (0x1000).

## 2. Concrete Example: Covariant Return
```java
public class StringSupplier implements Supplier<String> {
    public String get() { return "hello"; }
}
```
Bytecode contains two methods:
1. `public String get()`: Hand-written method.
2. `public synthetic bridge Object get()`: Invokes `(String) this.get()` and returns the result.

## 3. Decompilation Filter
- Any method marked `ACC_BRIDGE` that simply delegates to another method of the same name with more specific parameter/return types must be suppressed from output. Emitting bridge methods in source causes Java compilation errors ("method already defined").

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Checks `access_flags & ACC_BRIDGE` and skips rendering redundant bridge methods.

## 5. References
- Bracha, G. *Generics in the Java Programming Language*. Sun Microsystems, 2004.
"""
    ),
    (
        "01_jvm_bytecode_spec", "016_annotations_and_parameter_attributes.md",
        "Runtime Annotations, Parameter Metadata, and Type Annotations",
        """# Article 016: Runtime Annotations, Parameter Metadata, and Type Annotations

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
"""
    ),
    (
        "01_jvm_bytecode_spec", "017_module_info_and_module_attributes.md",
        "Java Platform Module System (JPMS) and module-info.class Bytecode",
        """# Article 017: Java Platform Module System (JPMS) and module-info.class Bytecode

## 1. Executive Summary & Specification
Introduced in Java 9 (Project Jigsaw, JSR 376), JPMS defines explicit module boundaries encapsulated in a special class file: `module-info.class`:
- `access_flags` has `ACC_MODULE` (0x8000) set.
- Superclass and interfaces are empty.
- Contains the `Module` attribute (JVMS §4.7.25).

## 2. Module Attribute Directives
- **requires**: Module dependencies (`transitive`, `static`).
- **exports**: Packages exposed to consumers (`to` specific modules).
- **opens**: Packages opened for deep reflection.
- **uses**: Service interfaces consumed via `java.util.ServiceLoader`.
- **provides**: Service implementations (`with` provider classes).

## 3. Decompiler Pipeline
When decompiling `module-info.class`:
- Emit `module <module_name> { ... }` instead of `class module-info`.
- Format all requires, exports, opens, uses, and provides directives inside the module block.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Special detection for `module-info` classes, emitting valid Java module declarations.

## 5. References
- JEP 261: *Module System*. OpenJDK, 2017.
- JVMS §4.7.25: The Module Attribute.
"""
    ),
    (
        "01_jvm_bytecode_spec", "018_line_number_table_and_local_variable_table.md",
        "Debug Attributes: LineNumberTable, LocalVariableTable, and Scoping",
        """# Article 018: Debug Attributes: LineNumberTable, LocalVariableTable, and Scoping

## 1. Executive Summary & Specification
Debugging attributes provide vital metadata for source-level correlation:
1. `LineNumberTable`: Maps bytecode offset ranges to original source code line numbers (`start_pc` -> `line_number`).
2. `LocalVariableTable`: Maps local variable slots to source identifiers and descriptors over specific PC intervals:
```
struct LocalVariableEntry {
    u2 start_pc;   // PC where variable scope begins
    u2 length;     // Scope span: [start_pc, start_pc + length)
    u2 name_index; // Utf8 variable name
    u2 descriptor_index;
    u2 index;      // LVT slot index
};
```
3. `LocalVariableTypeTable`: Similar to LVT, but stores generic signatures (`Signature` attribute syntax).

## 2. Decompilation Utility
- If LVT is present, exact parameter and local variable names are restored effortlessly (`userId`, `count`, etc.).
- The scope interval `[start_pc, start_pc + length)` provides exact block-level scoping boundaries for local variable declaration placement.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/naming_hints.cpp` and `engine.cpp`:
- Uses LVT names when available; falls back to heuristic type-based naming (`var1`, `name`, `builder`) when stripped.

## 4. References
- JVMS §4.7.12, §4.7.13, §4.7.14.
"""
    ),
    (
        "01_jvm_bytecode_spec", "019_wide_and_jsr_ret_legacy_bytecodes.md",
        "The wide Modifier and Legacy jsr/ret Subroutine Inlining",
        """# Article 019: The wide Modifier and Legacy jsr/ret Subroutine Inlining

## 1. Executive Summary & Specification
1. **The `wide` Opcode (0xC4)**:
   - Extends 8-bit local variable index operands to 16 bits for: `iload`, `fload`, `aload`, `lload`, `dload`, `istore`, `fstore`, `astore`, `lstore`, `dstore`, `ret`.
   - Also modifies `iinc` to take 16-bit variable index and 16-bit signed constant increment.
2. **Legacy `jsr` (0xA8) and `ret` (0xA9)**:
   - Used in Java 1.1 - 5 to implement `finally` blocks without bytecode duplication.
   - `jsr` pushes the return address (PC of following instruction) onto the operand stack and jumps to subroutine.
   - `ret <var>` jumps back to the address stored in local variable `<var>`.
   - Deprecated in Java 6 and forbidden in class files compiled for Java 7+ (`-target 1.7+`).

## 2. Decompiler Transformation: Subroutine Inlining
Modern decompilers eliminate `jsr`/`ret` early in the pipeline:
- Duplicate the subroutine body at each `jsr` call site.
- Replace `ret` with direct jumps to the return address.
- Yields a standard single-entry single-exit control flow graph suitable for modern structuring algorithms.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/disassembler.cpp` and `cfg.cpp`:
- Handles 16-bit operands for `wide`.
- Normalizes subroutine targets during CFG construction.

## 4. References
- JVMS §6.5: `wide`, `jsr`, `ret`.
- Freund, S. N., & Mitchell, J. C. *A Type System for Java Bytecode Subroutines and its Verification*. Formal Aspects of Computing, 2003.
"""
    ),
    (
        "01_jvm_bytecode_spec", "020_class_loading_and_linking_semantics.md",
        "Class Loading, Verification, Preparation, and Initialization Semantics",
        """# Article 020: Class Loading, Verification, Preparation, and Initialization Semantics

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
"""
    )
]

print(f"Total articles defined so far: {len(ARTICLES)}")
