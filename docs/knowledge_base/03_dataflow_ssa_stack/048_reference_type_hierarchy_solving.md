# Article 048: Reference Type Hierarchy Solving and Lowest Common Ancestor (LCA)

## 1. Executive Summary & Specification
When two object references merge at a control flow join point or in a ternary expression (`cond ? objA : objB`), the resulting type must be the **Lowest Common Ancestor (LCA)** in the class hierarchy:
$$	ext{Type}(R) = 	ext{LCA}(	ext{Type}(A), 	ext{Type}(B))$$

## 2. Multiple Interface Invariant
Java supports single class inheritance but multiple interface inheritance:
- If `ClassA` implements `Comparable` and `Serializable`, and `ClassB` implements `Comparable` and `Cloneable`, their common class ancestor is `Object`, but their common interface ancestor is `Comparable`.
- The decompiler uses classpath hierarchy metadata to determine whether an interface cast is required.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- Class hierarchy traversal for common ancestor resolution.

## 4. References
- JLS §15.25: Conditional Operator `? :` - Type Resolution Rules.
