# Article 029: Structuring Short-Circuit Boolean Expressions (&&, ||) and Condition Trees

## 1. Executive Summary & Specification
Java mandates short-circuit evaluation for logical AND (`&&`) and logical OR (`||`):
- `A && B`: If $A$ is false, $B$ is not evaluated.
- `A || B`: If $A$ is true, $B$ is not evaluated.
Compilers emit this as a cascade of individual conditional branch instructions (`ifeq`, `ifne`, etc.).

## 2. CFG Pattern Matching for Compound Conditions
- **Logical AND (`&&`) Pattern**:
  - Block $A$ jumps to `FalseTarget` if false; falls through to Block $B$.
  - Block $B$ jumps to `FalseTarget` if false; falls through to `TrueTarget`.
  - $\implies$ Collapse into: `if (A && B) goto TrueTarget; else goto FalseTarget;`.
- **Logical OR (`||`) Pattern**:
  - Block $A$ jumps to `TrueTarget` if true; falls through to Block $B$.
  - Block $B$ jumps to `TrueTarget` if true; falls through to `FalseTarget`.
  - $\implies$ Collapse into: `if (A || B) goto TrueTarget; else goto FalseTarget;`.

## 3. Normalization via De Morgan's Laws
$$
eg(A \land B) \equiv (
eg A \lor 
eg B), \quad 
eg(A \lor B) \equiv (
eg A \land 
eg B)$$
Decompilers apply De Morgan's laws to eliminate unnecessary negations, ensuring expressions like `if (!(a == 0 && b == 0))` are emitted cleanly as `if (a != 0 || b != 0)`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `stackvm.cpp`:
- Merges sequential single-condition blocks that share common exit targets into compound `BinaryExpr(OP_LOGICAL_AND)` and `BinaryExpr(OP_LOGICAL_OR)`.

## 5. References
- JLS §15.23, §15.24: Conditional-And Operator `&&`, Conditional-Or Operator `||`.
