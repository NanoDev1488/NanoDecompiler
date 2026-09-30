"""
articles_mod7.py - Module 7: Anti-Decompilation & Obfuscation Countermeasures (096 - 100)
"""

MODULE_NAME = "07_obfuscation_countermeasures"

ARTICLES = [
    {
        "file": "096_opaque_predicates_and_dead_branches.md",
        "title": "Opaque Predicates and Dead Branch Pruning",
        "num": "096",
        "content": """# Article 096: Opaque Predicates and Dead Branch Pruning

## 1. Executive Summary & Mathematical Foundations
An **Opaque Predicate** is a conditional expression whose evaluation is known a priori to the obfuscator at compile time, but is difficult or impossible for static analyzers to deduce:
- $P_{\text{true}}$: Always evaluates to true (e.g. $x^2 \ge 0$ for integers, or $(x \cdot (x + 1)) \equiv 0 \pmod 2$).
- $P_{\text{false}}$: Always evaluates to false.

## 2. Obfuscation Threat Model
Obfuscators inject opaque predicates to:
1. Introduce spurious edges in the CFG, transforming reducible graphs into irreducible graphs.
2. Route dead branches to illegal bytecodes or traps designed to crash decompilers.

## 3. Solver Algorithm
1. **Constant Propagation**: Trace local variable values through basic blocks.
2. **Algebraic Simplification**: Recognize known mathematical identities and bitwise invariants ($x \land 0 = 0$, $x \oplus x = 0$).
3. **Dead Edge Elimination**: When a conditional branch condition is proven constant, prune the non-taken edge from the CFG and convert the branch into an unconditional jump.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Evaluates constant expressions to prune bogus control flow branches before structuring.

## 5. References
- Collberg, C., Thomborson, C., & Low, D. *A taxonomy of obfuscating transformations*. Technical Report, University of Auckland, 1997.
"""
    },
    {
        "file": "097_control_flow_flattening_unflattening.md",
        "title": "Control Flow Flattening De-obfuscation Algorithms",
        "num": "097",
        "content": """# Article 097: Control Flow Flattening De-obfuscation Algorithms

## 1. Executive Summary & De-obfuscation Pipeline
Control Flow Flattening encapsulates all basic blocks inside a central switch dispatcher loop.
De-obfuscating flattened code requires **Symbolic Execution**:
1. Identify the dispatcher state variable `int state`.
2. Trace each case block symbolically to find the next state value:
   - Fixed constant $\implies$ unconditional transition.
   - Conditional branch depending on original program variable $\implies$ true/false branch transitions.
3. Re-link basic blocks directly using recovered transitions and eliminate the dispatcher loop.

## 2. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `switchmap.cpp`:
- State transition analyzer reconstructs structured if/loop hierarchies from switch dispatchers.

## 3. References
- Wang, C. *A Security Architecture for Survivability Mechanisms*. PhD Thesis, University of Virginia, 2000.
"""
    },
    {
        "file": "098_string_and_constant_encryption_solvers.md",
        "title": "String and Constant Encryption Solvers: Static and Dynamic Emulation",
        "num": "098",
        "content": """# Article 098: String and Constant Encryption Solvers: Static and Dynamic Emulation

## 1. Executive Summary & Encryption Schemes
Java bytecode stores strings in plain UTF-8 in the constant pool. Obfuscators (ProGuard, Allatori, Zelix KlassMaster) replace plain strings with:
- Encrypted byte arrays or ciphertext strings (`"\\u001f\\u002a..."`).
- Calls to decryption routines at runtime: `decrypt("cipher", key)`.

## 2. Decryption Engine Architecture
NanoDecompiler integrates a dedicated string decryption subsystem:
1. **Pattern Recognition**: Identify decryption method signatures (static methods accepting string/byte[] and returning String).
2. **Local VM Emulation**: Execute the decryption method in a lightweight bytecode interpreter sandbox using the constants embedded in the class.
3. **AST Inlining**: Replace the method call in the AST with the resulting plaintext string literal.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/str_decrypt.cpp`:
- Automated decryption for XOR, AES-128, and custom bitwise string decryptor routines.

## 4. References
- Ceccato, M., et al. *Deobfuscation of String Encryption in Android Applications*. IEEE SANER, 2018.
"""
    },
    {
        "file": "099_exceptional_control_flow_obfuscation.md",
        "title": "Exceptional Control Flow Obfuscation: Fake Try-Catch Ranges",
        "num": "099",
        "content": """# Article 099: Exceptional Control Flow Obfuscation: Fake Try-Catch Ranges

## 1. Executive Summary & Trap Injection
Adversarial bytecode creates overlapping, zero-length, or out-of-order exception table entries:
- Handlers covering non-throwing instructions (e.g. `iconst_0`, `nop`).
- Handler ranges that partially overlap without containment (e.g. $[10, 30)$ and $[20, 40)$).
- Target PCs that jump into the middle of instructions.

## 2. Sanitization Pipeline
1. **Instruction Boundary Check**: Ensure `start_pc`, `end_pc`, and `handler_pc` align with valid instruction boundaries.
2. **Range Normalization**: Split partially overlapping ranges into disjoint sub-ranges.
3. **Dead Handler Pruning**: If a handler protects instructions that cannot throw exceptions of the specified `catch_type`, prune the handler entry.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp`:
- Normalizes exception tables and filters synthetic trap handlers before structuring.

## 4. References
- Linn, C., & Debray, S. *Obfuscation of Executable Code to Foil Reverse Engineering*. ACM CCS, 2003.
"""
    },
    {
        "file": "100_identifier_restoration_and_heuristic_renaming.md",
        "title": "Identifier Restoration and Context-Sensitive Heuristic Renaming",
        "num": "100",
        "content": """# Article 100: Identifier Restoration and Context-Sensitive Heuristic Renaming

## 1. Executive Summary & The Problem of Obfuscated Names
Obfuscators strip `LocalVariableTable` attributes and rename classes, fields, and methods to meaningless, unreadable names:
- Single characters: `a`, `b`, `c`.
- Invisible/unprintable Unicode characters: `\\u200B`, `\\u00A0`.
- Overloaded names identical except for type signatures: `int a; String a; boolean a;`.

## 2. Context-Sensitive Renaming Heuristics
NanoDecompiler applies intelligent naming heuristics to restore readability:
1. **Type-Based Naming**:
   - `StringBuilder` $\implies$ `sb`, `builder`.
   - `List<String>` $\implies$ `stringList`, `list`.
   - `HttpServletRequest` $\implies$ `request`.
2. **Method Context Propagation**:
   - Getter/Setter pairing: `getX()` / `setX(int val)` $\implies$ field is named `x`.
   - Method parameter names inferred from library interfaces (e.g. Bukkit, Spigot, Spring, JDK).
3. **Unicode Normalization**: Replace illegal/unprintable characters with deterministic alphanumeric IDs (`var_1`, `class_2`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/renamer.cpp` and `naming_hints.cpp`:
- Identifier normalization engine produces clean, standard-compliant Java identifiers.

## 4. References
- Raychev, V., et al. *Predicting program properties from big code*. ACM POPL, 2015.
- Vasilescu, B., et al. *Recovering variable names from minified code with statistical language models*. ACM ESEC/FSE, 2017.
"""
    }
]
