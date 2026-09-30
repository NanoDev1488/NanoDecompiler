# Article 045: Variable Liveness Analysis and Interference Graph Coloring

## 1. Executive Summary & Formal Definitions
A variable $v$ is **live** at point $p$ if there exists a path from $p$ to a use of $v$ along which $v$ is not redefined:
- **`Def(B)`**: Variables defined in block $B$ before any use.
- **`Use(B)`**: Variables used in block $B$ before any definition.
- **Dataflow Equations**:
  $$	ext{In}(B) = 	ext{Use}(B) \cup (	ext{Out}(B) \setminus 	ext{Def}(B))$$
  $$	ext{Out}(B) = igcup_{S \in 	ext{succ}(B)} 	ext{In}(S)$$

## 2. Interference Graph & Coloring
Two variables **interfere** if their live ranges overlap at any point in the CFG.
- Vertices: Variables.
- Edges: Interference between variables.
- Goal: Assign colors (Java variable names and scopes) such that adjacent nodes receive different colors, while variables with identical types and non-overlapping ranges share the same color.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Computes variable liveness to prevent variable reuse bugs and ensure accurate scope boundaries.

## 4. References
- Chaitin, G. J. *Register allocation & spilling via graph coloring*. ACM SIGPLAN, 1982.
