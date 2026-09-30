# Article 027: Irreducible Control Flow Graphs: Node Splitting and State Variable Emulation

## 1. Executive Summary & Problem Formulation
An **Irreducible CFG** contains a cycle with multiple distinct entry nodes from outside the cycle. Java has no `goto` statement (it is a reserved keyword without syntax), meaning irreducible bytecode cannot be represented in standard structured Java without semantic transformation.

## 2. Resolution Technique 1: Node Splitting (Duplication)
Duplicate one of the entry nodes so each cycle entrance has its own private copy:
```
Before Splitting (Irreducible):
  Entry1 ──► [ LoopNode A ] ◄── Entry2
                  │
                  ▼
             [ LoopNode B ] ───► LoopNode A

After Node Splitting (Reducible):
  Entry1 ──► [ LoopNode A1 ] ──► [ LoopNode B1 ] ──► LoopNode A1
  Entry2 ──► [ LoopNode A2 ] ──► [ LoopNode B2 ] ──► LoopNode A2
```
*Advantage*: Generates clean Java without synthetic state variables.
*Disadvantage*: Exponential code size explosion if nested irreducible cycles exist.

## 3. Resolution Technique 2: State Variable Dispatcher (Flattening)
Introduce a synthetic integer variable `int __state = initial;` wrapped in a `while (true) switch (__state)` loop. Each basic block ends with an assignment to `__state` and `break;`.
*Advantage*: Guaranteed $O(|V|)$ transformation of any arbitrary graph.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Detects irreducible cross-links in `Structurer::region`.
- Emits annotated loop-with-comment or structured state jumps instead of aborting decompilation.

## 5. References
- Cifuentes, C., & Gough, K. J. *Decompilation of binary programs*. Software: Practice and Experience, 1995.
