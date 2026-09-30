# Article 054: Def-Use and Use-Def Chains for Dependency Analysis

## 1. Executive Summary & Definitions
- **Def-Use (DU) Chain**: For a given variable definition $D$, the list of all instructions $U_1, U_2, \dots$ that can read the value written by $D$.
- **Use-Def (UD) Chain**: For a given variable use $U$, the list of all definitions $D_1, D_2, \dots$ that can reach $U$.

## 2. Decompiler Utility
- DU chains enable exact dead store elimination: if a definition has an empty DU chain and has no side effects, it can be pruned safely.
- UD chains verify whether a variable is guaranteed to be initialized before use, informing declaration placement (`Type x = val;` vs `Type x;`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Tracks variable definitions and uses to optimize variable scopes.

## 4. References
- Kennedy, K. *Use-definition chains with applications to code optimization*. ACM SIGPLAN, 1978.
