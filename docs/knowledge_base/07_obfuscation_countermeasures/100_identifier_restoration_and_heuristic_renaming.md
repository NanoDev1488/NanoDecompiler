# Article 100: Identifier Restoration and Context-Sensitive Heuristic Renaming

## 1. Executive Summary & The Problem of Obfuscated Names
Obfuscators strip `LocalVariableTable` attributes and rename classes, fields, and methods to meaningless, unreadable names:
- Single characters: `a`, `b`, `c`.
- Invisible/unprintable Unicode characters: `\u200B`, `\u00A0`.
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
