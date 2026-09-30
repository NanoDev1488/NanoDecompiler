# Article 050: Escaping Variables, Scope Analysis, and Variable Hoisting

## 1. Executive Summary & Scope Rules
In Java, a variable declared inside a block (`if`, `while`, `try`) is lexically scoped to that block. If a variable is assigned inside an `if` block and read outside that block:
```java
if (cond) {
    String name = fetch(); // Escaping declaration!
}
print(name); // Compile Error in Java: name cannot be resolved
```
The declaration **escapes** the inner scope.

## 2. Hoisting Resolution Algorithm
1. Compute the set of all basic blocks where variable $v$ is defined or used: $S_v$.
2. Find the **Nearest Common Dominator (NCD)** block $B_{ncd}$ of all blocks in $S_v$.
3. **Hoist** the declaration of $v$ (`Type v;`) into the AST scope enclosing $B_{ncd}$.
4. Within the inner block, replace declaration statements with plain assignments (`v = fetch();`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- `hoist_escaping_locals()` and `hoist_all_escaping_to_root()` ensure that all escaping declarations are lifted to parent scopes, eliminating "escaping variable" compilation errors and decompiler aborts.

## 4. References
- JLS §6.3: Scope of a Declaration.
