# Article 009: String Concatenation via StringConcatFactory (JEP 280) and Desugaring

## 1. Executive Summary & Specification
Prior to Java 9, string concatenations like `"x = " + x` were compiled into explicit `StringBuilder` sequences:
```java
new StringBuilder().append("x = ").append(x).toString();
```
JEP 280 (Java 9) replaced this bytecode bloat with a single `invokedynamic` call to `java.lang.invoke.StringConcatFactory.makeConcatWithConstants`:
- The BSM takes a **recipe string** where character `\1` represents an ordinary dynamic argument and `\2` represents a constant from the bootstrap arguments.

## 2. Recipe String Parsing
Example:
- Recipe: `"Score: \1, Bonus: \1 (Total: \2)"`
- Dynamic Args: `[score, bonus]`
- Static Constants: `[100]`
- Reconstructed Source: `"Score: " + score + ", Bonus: " + bonus + " (Total: " + 100 + ")"`

## 3. Decompilation Algorithm
1. Identify `invokedynamic` instruction calling `StringConcatFactory`.
2. Retrieve the recipe string from bootstrap argument 0.
3. Iterate through characters of the recipe:
   - Plain characters -> accumulate in string literal buffer.
   - `\1` -> pop next expression from the dynamic argument list.
   - `\2` -> take next constant from static constants list.
4. Construct a binary `+` expression tree (`BinaryExpr(OP_ADD, left, right)`), eliminating empty literal prefixes/suffixes.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Recognizes `makeConcatWithConstants` and emits clean binary `+` AST nodes directly into the parent expression tree.

## 5. References
- JEP 280: *Indify String Concatenation*. OpenJDK, 2016.
