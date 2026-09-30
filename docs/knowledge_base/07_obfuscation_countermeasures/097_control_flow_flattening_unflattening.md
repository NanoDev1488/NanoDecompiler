# Article 097: Control Flow Flattening De-obfuscation Algorithms

## 1. Executive Summary & De-obfuscation Pipeline
Control Flow Flattening encapsulates all basic blocks inside a central switch dispatcher loop.
De-obfuscating flattened code requires **Symbolic Execution**:
1. Identify the dispatcher state variable `int state`.
2. Trace each case block symbolically to find the next state value:
   - Fixed constant $\implies$ unconditional transition.
   - Conditional branch depending on original program variable $\implies$ true/false branch transitions.
3. Re-link basic blocks directly using recovered transitions and eliminate the dispatcher loop.

## 2. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `switchmap.cpp`:
- State transition analyzer reconstructs structured if/loop hierarchies from switch dispatchers.

## 3. References
- Wang, C. *A Security Architecture for Survivability Mechanisms*. PhD Thesis, University of Virginia, 2000.
