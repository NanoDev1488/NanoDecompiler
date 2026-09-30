# Article 025: The Relooper Algorithm: WebAssembly Origins and Java CFG Structuring

## 1. Executive Summary & Context
Originally designed by Alon Zakai for Emscripten (LLVM to JavaScript/WebAssembly), the **Relooper Algorithm** reconstructs high-level structured control flow from arbitrary irreducible or reducible CFGs without relying on expensive backtracking.

## 2. The Three Core Structural Shapes
1. **Simple Shape**: A single basic block that transitions directly to at most one follow-up shape.
```
[ Block A ] ───► [ Follow Shape ]
```
2. **Loop Shape**: An entry block with a set of reachable recursive blocks and an exit shape:
```
┌──► [ Loop Body ] ───┐
│          │          │
└──────────┴──────────┘
           │
           ▼
     [ Exit Shape ]
```
3. **Multiple Shape**: A branching node that can jump to multiple independent shapes, structured as an `if/else` cascade or a switch block.

## 3. Extending Relooper for Java Decompilation
While Relooper in WebAssembly generates `loop` and `block` labels with break-to-label instructions, Java decompiler adaptations:
- Synthesize labeled `break <label>;` and `continue <label>;` when jumps cross multiple nesting levels.
- Re-order branching targets to prefer clean `if (cond) { ... } else { ... }` without artificial labels.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- `Structurer::region()` adopts Relooper-style recursive region decomposition, isolating subgraphs into `BlockStmtNode`, `IfStmtNode`, and `WhileStmtNode`.

## 5. References
- Zakai, A. *Emscripten: an LLVM-to-JavaScript compiler*. ACM SPLASH, 2011.
