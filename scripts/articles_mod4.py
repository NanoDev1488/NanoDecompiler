"""
articles_mod4.py - Module 4: High-Level Java Idioms & Syntactic Sugar Reconstruction (056 - 070)
"""

MODULE_NAME = "04_syntactic_sugar_patterns"

ARTICLES = [
    {
        "file": "056_string_switch_compilation_and_recovery.md",
        "title": "String Switch Compilation and Two-Tier Hash Desugaring",
        "num": "056",
        "content": """# Article 056: String Switch Compilation and Two-Tier Hash Desugaring

## 1. Executive Summary & Specification
Java 7 introduced `switch` on `String` values. Because JVM `tableswitch` and `lookupswitch` only accept 32-bit integers, javac desugars a string switch into a **two-tier switch**:
1. **Tier 1 (Hash Switch)**: Invokes `str.hashCode()` and executes a switch on the integer hash code.
2. In each hash case, javac emits `str.equals("literal")` comparisons to handle hash collisions. If equal, an internal integer ordinal (0, 1, 2, ...) is stored in a temporary variable.
3. **Tier 2 (Ordinal Switch)**: Executes a `tableswitch` on the internal ordinal, jumping to the actual user case blocks.

## 2. Decompilation Reconstruction Algorithm
1. Identify Tier 1 switch on `str.hashCode()`.
2. Trace the target blocks: match `str.equals("literal")` calls and record the mapping:
   $$\text{ordinal} \mapsto \text{"literal"}$$
3. Trace into the Tier 2 switch on the ordinal variable: replace each `case ordinal:` with `case "literal":`.
4. Delete the temporary ordinal variable, the `hashCode()` call, and the equals comparisons.
5. Emit a single clean `switch (str) { case "a": ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/switchmap.cpp`:
- Two-tier string switch detector eliminates compiler hash trampolines.

## 4. References
- JLS §14.11: The `switch` Statement.
- Goetz, B. *Strings in switch*. Project Coin, 2011.
"""
    },
    {
        "file": "057_enum_switch_and_synthetic_mapping.md",
        "title": "Enum Switch and Synthetic Mapping Class ($SwitchMap$)",
        "num": "057",
        "content": """# Article 057: Enum Switch and Synthetic Mapping Class ($SwitchMap$)

## 1. Executive Summary & Binary Pattern
When switching over an `enum`, javac generates an anonymous synthetic nested class (e.g. `EnclosingClass$1`) containing a static integer array:
```java
static final int[] $SwitchMap$com$example$Color = new int[Color.values().length];
static {
    try { $SwitchMap$...[Color.RED.ordinal()] = 1; } catch (NoSuchFieldError e) {}
    try { $SwitchMap$...[Color.BLUE.ordinal()] = 2; } catch (NoSuchFieldError e) {}
}
```
The switch statement in the user method then accesses `$SwitchMap$...[color.ordinal()]` to produce dense integers for `tableswitch`.

## 2. Decompilation Reconstruction Algorithm
1. Detect array access to static field whose name matches `$SwitchMap$*`.
2. Parse the synthetic class's `<clinit>` to extract the mapping from ordinal index to enum constant name.
3. Replace integer case labels in the switch statement with their corresponding enum constant identifiers (`case RED:`, `case BLUE:`).
4. Mark the synthetic `$SwitchMap$` class as hidden so it is not rendered in the decompiled source.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/switchmap.cpp` and `render_class.cpp`:
- Resolves enum switch synthetic arrays to symbolic enum names.

## 4. References
- JLS §14.11: The `switch` Statement - Enum Switches.
"""
    },
    {
        "file": "058_try_with_resources_desugaring.md",
        "title": "Try-With-Resources (Java 7+) Desugaring and Throwable.addSuppressed",
        "num": "058",
        "content": """# Article 058: Try-With-Resources (Java 7+) Desugaring and Throwable.addSuppressed

## 1. Executive Summary & Bytecode Complexity
Java 7's `try (Resource r = ...)` statement ensures that `r.close()` is invoked even if exceptions occur. The compiler generates up to 20 bytecodes per resource, including:
- Primary exception capture.
- Null check on resource before calling `.close()`.
- Secondary `try-catch` around `.close()`.
- Call to `primaryException.addSuppressed(closeException)` if both fail.

## 2. Reconstruction Algorithm
1. Detect synthetic `catch (Throwable t)` block that calls `Throwable.addSuppressed()`.
2. Trace the resource allocation immediately preceding the try block.
3. Verify that the finally block contains `if (res != null) res.close()`.
4. Collapse the entire pattern into `try (Resource res = initExpr) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp`:
- Recognizes try-with-resources desugaring patterns and reconstructs clean resource declarations in the try header.

## 4. References
- JLS §14.20.3: `try`-with-resources.
- JEP 110: *try-with-resources*. OpenJDK, 2011.
"""
    },
    {
        "file": "059_assert_statement_reconstruction.md",
        "title": "Assert Statement Reconstruction and $assertionsDisabled Flag",
        "num": "059",
        "content": """# Article 059: Assert Statement Reconstruction and $assertionsDisabled Flag

## 1. Executive Summary & Desugaring Pattern
Java `assert condition : message;` statements are compiled using a synthetic class-level static boolean field:
```java
static final boolean $assertionsDisabled = !EnclosingClass.class.desiredAssertionStatus();
```
At the assertion site:
```
getstatic $assertionsDisabled
ifne SkipLabel
[ Evaluate Condition ]
ifne SkipLabel
new java/lang/AssertionError
dup
[ Evaluate Message ]
invokespecial AssertionError.<init>(message)
athrow
SkipLabel:
```

## 2. Decompilation Reconstruction
1. Detect conditional throw of `AssertionError` gated by `$assertionsDisabled`.
2. Reconstruct `assert <cond> : <message>;`.
3. Suppress the synthetic `$assertionsDisabled` static field from class output.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `render_class.cpp`:
- Identifies assertion patterns and renders clean `assert` statements.

## 4. References
- JLS §14.10: The `assert` Statement.
"""
    },
    {
        "file": "060_anonymous_classes_vs_lambdas.md",
        "title": "Anonymous Inner Classes vs Lambdas Disambiguation",
        "num": "060",
        "content": """# Article 060: Anonymous Inner Classes vs Lambdas Disambiguation

## 1. Executive Summary & Differences
| Feature | Anonymous Inner Class | Lambda Expression |
|---------|-----------------------|-------------------|
| Class File | Generates separate `Outer$1.class` | Desugared into synthetic method in same class |
| Bytecode Opcode | `new Outer$1`, `invokespecial <init>` | `invokedynamic LambdaMetafactory` |
| Scope of `this` | Refers to anonymous class instance | Refers to enclosing class instance |
| Performance | Creates separate object per instance | Can be cached as static constant singleton |

## 2. Decompilation Guidance
- Anonymous classes should only be converted to lambdas if the target is a Single Abstract Method (SAM) interface and `this` is not shadowed.
- Otherwise, retain the full `new Interface() { public void method() { ... } }` syntax.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp` and `render_class.cpp`:
- Accurate classification of SAM interfaces and anonymous class declarations.

## 4. References
- Goetz, B. *Translation of Lambda Expressions*. Oracle, 2012.
"""
    },
    {
        "file": "061_method_reference_reconstruction.md",
        "title": "Method Reference Reconstruction: Static, Bound, Unbound, and Constructor",
        "num": "061",
        "content": """# Article 061: Method Reference Reconstruction: Static, Bound, Unbound, and Constructor

## 1. Executive Summary & Taxonomy
Method references (Java 8) provide compact syntax for lambdas that merely delegate to existing methods:
1. **Static Method Reference**: `ContainingClass::staticMethod`
   - BSM: `LambdaMetafactory.metafactory`, target handle kind = `H_INVOKESTATIC`.
2. **Bound Instance Method Reference**: `expr::instanceMethod`
   - Captures the receiver expression as an argument to `invokedynamic`.
3. **Unbound Instance Method Reference**: `ContainingType::instanceMethod`
   - First parameter of the functional interface serves as the receiver.
4. **Constructor Reference**: `ClassName::new` or `TypeName[]::new`
   - Target handle kind = `H_NEWINVOKESPECIAL`.

## 2. Decompiler Reconstruction
- When an `invokedynamic` with `LambdaMetafactory` references a non-synthetic method directly in its `implMethod` handle:
  - Format as `Target::name` instead of `(args) -> Target.name(args)`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Generates clean `MethodRefExpr` nodes when target method matches delegation signatures.

## 4. References
- JLS §15.13: Method Reference Expressions.
"""
    },
    {
        "file": "062_enhanced_for_loop_arrays.md",
        "title": "Enhanced For-Loop (foreach) over Arrays Reconstruction",
        "num": "062",
        "content": """# Article 062: Enhanced For-Loop (foreach) over Arrays Reconstruction

## 1. Executive Summary & Bytecode Pattern
The enhanced for-loop `for (T item : array)` is lowered by javac into:
```java
T[] arr$ = array;
int len$ = arr$.length;
for (int i$ = 0; i$ < len$; ++i$) {
    T item = arr$[i$];
    // Loop Body
}
```

## 2. Reconstruction Algorithm
1. Detect `ForStmtNode` where:
   - Loop bound is `arr.length`.
   - Index variable `i` is initialized to 0 and incremented by 1.
   - First statement of the loop body is an array read `T item = arr[i]`.
2. Eliminate `arr$`, `len$`, and `i$` temporaries from AST.
3. Emit `for (T item : array) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Pattern matches array iteration loops and converts them to `ForEachStmtNode`.

## 4. References
- JLS §14.14.2: The enhanced `for` statement.
"""
    },
    {
        "file": "063_enhanced_for_loop_iterables.md",
        "title": "Enhanced For-Loop (foreach) over Iterables (Collection/List)",
        "num": "063",
        "content": """# Article 063: Enhanced For-Loop (foreach) over Iterables (Collection/List)

## 1. Executive Summary & Bytecode Pattern
The enhanced for-loop `for (T item : collection)` is lowered by javac into:
```java
Iterator<T> it$ = collection.iterator();
while (it$.hasNext()) {
    T item = it$.next();
    // Loop Body
}
```

## 2. Reconstruction Algorithm
1. Detect loop where initialization block calls `.iterator()` on an `Iterable`.
2. Loop condition calls `it.hasNext()`.
3. First statement of the loop body calls `it.next()`.
4. Iterator variable is not used elsewhere in the loop.
5. Collapse into `for (T item : collection) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Recognizes `Iterator` patterns and transforms `while` loops into concise `ForEachStmtNode`.

## 4. References
- JLS §14.14.2: The enhanced `for` statement.
"""
    },
    {
        "file": "064_string_builder_concatenation_patterns.md",
        "title": "StringBuilder Concatenation Patterns (Java 8 and earlier)",
        "num": "064",
        "content": """# Article 064: StringBuilder Concatenation Patterns (Java 8 and earlier)

## 1. Executive Summary & Lowering Pattern
In Java 8 and earlier, `"Prefix: " + x + " suffix"` compiles into:
```
new java/lang/StringBuilder
dup
invokespecial StringBuilder.<init>()
ldc "Prefix: "
invokevirtual StringBuilder.append(String)
iload x
invokevirtual StringBuilder.append(int)
ldc " suffix"
invokevirtual StringBuilder.append(String)
invokevirtual StringBuilder.toString()
```

## 2. Reconstruction Algorithm
1. Identify `new StringBuilder()` allocation chained to `.append()` calls terminating in `.toString()`.
2. Extract the arguments of each `.append()` invocation.
3. Reconstruct a left-associative binary addition expression tree:
   $$(((E_1 + E_2) + E_3) + \dots)$$

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Collapses `StringBuilder.append()` chains into clean `+` expressions.

## 4. References
- JLS §15.18.1: String Concatenation Operator `+`.
"""
    },
    {
        "file": "065_boxing_and_unboxing_primitives.md",
        "title": "Auto-Boxing and Unboxing Primitive Conversions",
        "num": "065",
        "content": """# Article 065: Auto-Boxing and Unboxing Primitive Conversions

## 1. Executive Summary & Specification
Java 5 introduced autoboxing (automatic conversion between primitives and their wrapper classes):
- **Boxing**: `Integer.valueOf(intVal)`, `Boolean.valueOf(boolVal)`, etc.
- **Unboxing**: `intObj.intValue()`, `boolObj.booleanValue()`, etc.

## 2. Decompiler Simplification Pass
- If a boxed wrapper is passed into a context expecting an object (e.g. `list.add(Integer.valueOf(x))`), simplify to `list.add(x)`.
- If an unboxed call occurs in an arithmetic context (e.g. `x.intValue() + 5`), simplify to `x + 5`.
- Preserve explicit `valueOf()` or `.intValue()` only if necessary to disambiguate overloaded method calls (e.g. `remove(int index)` vs `remove(Object obj)`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Context-sensitive autoboxing simplification pass.

## 4. References
- JLS §5.1.7: Boxing Conversion.
- JLS §5.1.8: Unboxing Conversion.
"""
    },
    {
        "file": "066_constructor_chaining_this_and_super.md",
        "title": "Constructor Chaining: this() and super() Placement",
        "num": "066",
        "content": """# Article 066: Constructor Chaining: this() and super() Placement

## 1. Executive Summary & Invariant Rules
In Java, every constructor must invoke either another constructor of the same class via `this(...)` or a constructor of the direct superclass via `super(...)` as its very first statement (JLS §8.8.7).
In bytecode:
- `aload_0` (`this`) followed by `invokespecial <init>`.

## 2. Decompilation Rules
1. If the first statement is `super()` with no arguments, and the superclass is `java.lang.Object`, the call can be omitted (it is implicit in Java).
2. If the first statement is `this(...)` or `super(...)` with arguments, it must be rendered explicitly as the first line of the constructor body.
3. Field initializers in the class declaration must not be duplicated inside constructors that delegate via `this(...)`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `stackvm.cpp`:
- Separates `super(...)`/`this(...)` constructor calls from subsequent method statements.

## 4. References
- JLS §8.8.7: Constructor Body.
"""
    },
    {
        "file": "067_varargs_method_calls_and_definitions.md",
        "title": "Varargs Method Calls and Method Signatures (ACC_VARARGS)",
        "num": "067",
        "content": """# Article 067: Varargs Method Calls and Method Signatures (ACC_VARARGS)

## 1. Executive Summary & Bytecode Representation
Variable arity methods (`public void log(String format, Object... args)`) are marked with `ACC_VARARGS` (0x0080) in `access_flags`.
At the call site:
- The compiler generates an explicit array instantiation: `new Object[] { arg1, arg2 }`.

## 2. Call-Site Decompilation
1. When calling a method marked `ACC_VARARGS`:
   - If the last argument is an explicitly initialized array matching the varargs component type, unpack the array elements directly into the argument list:
     $$\text{log}("x=%d", \text{new Object[]}\{ 42 \}) \implies \text{log}("x=%d", 42);$$

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `stackvm.cpp`:
- Emits `T... args` in method headers and flattens array arguments at call sites.

## 4. References
- JLS §8.4.1: Formal Parameters - Variable Arity Parameters.
"""
    },
    {
        "file": "068_pattern_matching_instanceof_and_switch.md",
        "title": "Pattern Matching for instanceof and Switch (Java 16 - 21)",
        "num": "068",
        "content": """# Article 068: Pattern Matching for instanceof and Switch (Java 16 - 21)

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
"""
    },
    {
        "file": "069_record_canonical_and_compact_constructors.md",
        "title": "Record Canonical vs Compact Constructors",
        "num": "069",
        "content": """# Article 069: Record Canonical vs Compact Constructors

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
"""
    },
    {
        "file": "070_kotlin_bytecode_patterns_in_java_decompilers.md",
        "title": "Kotlin Bytecode Patterns in Java Decompilers (@Metadata, Intrinsics)",
        "num": "070",
        "content": """# Article 070: Kotlin Bytecode Patterns in Java Decompilers (@Metadata, Intrinsics)

## 1. Executive Summary & Kotlin Compilation Artifacts
When Kotlin code is compiled to JVM bytecode, distinct compiler patterns emerge:
1. **Null Safety Intrinsics**: `Intrinsics.checkNotNullParameter(param, "param")` at method entry points.
2. **Default Arguments**: Synthetic methods with bitmasks `foo$default(..., int mask, Object handler)`.
3. **Companion Objects**: Static inner class named `Companion` with static field `Companion Companion;`.
4. **Metadata Attribute**: Binary protobuf stored in `@kotlin.Metadata` containing original Kotlin function signatures, nullability flags, and property declarations.

## 2. Decompilation Handling
- A Java decompiler should clean up Kotlin intrinsic noise (e.g. simplify `checkNotNullParameter` calls or flag them as synthetic guards).
- Support reading `@kotlin.Metadata` to recover accurate non-null types and parameter names when present.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp` and `naming_hints.cpp`:
- Detects Kotlin metadata and cleans up synthetic companion artifacts.

## 4. References
- Breslav, A. *Kotlin: Design and Implementation on the JVM*. ACM SPLASH, 2016.
"""
    }
]
