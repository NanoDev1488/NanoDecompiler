# Article 056: String Switch Compilation and Two-Tier Hash Desugaring

## 1. Executive Summary & Specification
Java 7 introduced `switch` on `String` values. Because JVM `tableswitch` and `lookupswitch` only accept 32-bit integers, javac desugars a string switch into a **two-tier switch**:
1. **Tier 1 (Hash Switch)**: Invokes `str.hashCode()` and executes a switch on the integer hash code.
2. In each hash case, javac emits `str.equals("literal")` comparisons to handle hash collisions. If equal, an internal integer ordinal (0, 1, 2, ...) is stored in a temporary variable.
3. **Tier 2 (Ordinal Switch)**: Executes a `tableswitch` on the internal ordinal, jumping to the actual user case blocks.

## 2. Decompilation Reconstruction Algorithm
1. Identify Tier 1 switch on `str.hashCode()`.
2. Trace the target blocks: match `str.equals("literal")` calls and record the mapping:
   $$	ext{ordinal} \mapsto 	ext{"literal"}$$
3. Trace into the Tier 2 switch on the ordinal variable: replace each `case ordinal:` with `case "literal":`.
4. Delete the temporary ordinal variable, the `hashCode()` call, and the equals comparisons.
5. Emit a single clean `switch (str) { case "a": ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/switchmap.cpp`:
- Two-tier string switch detector eliminates compiler hash trampolines.

## 4. References
- JLS §14.11: The `switch` Statement.
- Goetz, B. *Strings in switch*. Project Coin, 2011.
