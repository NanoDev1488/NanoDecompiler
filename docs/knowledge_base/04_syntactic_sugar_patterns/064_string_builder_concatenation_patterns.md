# Article 064: StringBuilder Concatenation Patterns (Java 8 and earlier)

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
