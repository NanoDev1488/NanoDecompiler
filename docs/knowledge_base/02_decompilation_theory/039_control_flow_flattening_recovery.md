# Article 039: Control Flow Flattening Recovery and Dispatcher Loop Unflattening

## 1. Executive Summary & Obfuscation Mechanism
**Control Flow Flattening** (introduced by Chenxi Wang, 2001) breaks apart normal structured control flow into disjoint basic blocks and encloses them all within a single dispatcher loop with a multi-way `switch` statement:
```
int state = 1;
while (state != 0) {
    switch (state) {
        case 1: [ Original Block A ]; state = 2; break;
        case 2: if (cond) state = 3; else state = 4; break;
        ...
    }
}
```
All hierarchical nesting (`if`, `while`, `try`) is destroyed, turning the CFG into a flat star-shaped graph.

## 2. Unflattening Recovery Algorithm
1. **Dispatcher Identification**: Locate `while` loop whose header directly dominates a single `switch` statement indexed by a state variable $S$.
2. **Symbolic State Evaluation**: For each switch case $C_i$:
   - Trace assignments to state variable $S$ along each exit path of $C_i$.
   - Compute transition tuples: $(C_i 	o C_j)$ for unconditional transitions, or $(C_i 	o C_j 	ext{ if } cond 	ext{ else } C_k)$ for conditional transitions.
3. **CFG Reconstruction**: Rebuild genuine CFG edges directly between the recovered basic blocks, completely removing the dispatcher loop and state variable.
4. Apply standard structurer to the recovered CFG.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `switchmap.cpp`:
- Recognizes dispatcher loops and resolves state transitions.

## 4. References
- Wang, C., et al. *Reverse Engineering of Flattened Control Flow*. IEEE Software Engineering, 2001.
- Udupa, S. K., Debray, S. K., & Matias, M. *Deobfuscation: Reverse Engineering Obfuscated Code*. USENIX Security, 2005.
