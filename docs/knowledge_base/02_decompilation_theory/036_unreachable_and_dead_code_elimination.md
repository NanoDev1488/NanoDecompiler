# Article 036: Unreachable and Dead Code Elimination in Decompiler AST

## 1. Executive Summary & Specification
Bytecode often contains dead or unreachable code due to:
- Constant folding at compile time (e.g. `if (DEBUG) { ... }` where `static final boolean DEBUG = false;`).
- Obfuscators injecting unreachable instructions to confuse analyzers.
- Compiler optimizations leaving abandoned blocks.

## 2. Reachability Analysis
1. Perform Depth-First Search (DFS) starting from the entry basic block ($PC = 0$).
2. Follow all direct jump, branch, switch, and valid exception edges.
3. Any basic block not reached during the traversal is strictly **unreachable dead code**.
4. In AST generation, unreachable blocks must be pruned unless they contain synthetic debugging markers or decompilation fallback hints.

## 3. Safe Fallback Guarantee
In adversarial or obfuscated binaries, an apparently unreachable block may be reached via dynamic reflection or corrupted jump targets. Rather than crashing:
- NanoDecompiler appends unconsumed blocks safely to the AST sequence with diagnostic annotations, avoiding decompiler aborts.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- `check_full_coverage()` ensures all CFG blocks are accounted for.
- `recover_unconsumed_blocks()` appends detached blocks safely without aborting decompilation.

## 5. References
- Cooper, K. D., & Torczon, L. *Engineering a Compiler (2nd Edition)*. Morgan Kaufmann, 2011.
