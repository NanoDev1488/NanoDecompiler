# Article 093: NSA Ghidra JVM SLEIGH Model and P-Code Decompilation

## 1. Executive Summary & Generic Decompilation Engine
The NSA's **Ghidra** software reverse engineering suite decompiles machine code and bytecode through a universal intermediate language: **P-Code**.

## 2. JVM SLEIGH Specification
- Ghidra models the JVM instruction set using SLEIGH processor specifications.
- Every JVM opcode is translated into micro-operations (P-Code): `COPY`, `INT_ADD`, `BRANCH`, `CALL`.
- The generic Ghidra decompiler applies global SSA and dominator structuring to P-Code, proving that bytecode can be decompiled using the same mathematics as x86 and ARM.

## 3. References
- National Security Agency (NSA). *Ghidra Software Reverse Engineering Framework*. GitHub, 2019.
- Eagle, C., & Nance, K. *The Ghidra Book: The Definitive Guide*. No Starch Press, 2020.
