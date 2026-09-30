# Article 089: Krakatau Decompiler: Strict Verification, Type Solvers, and Obfuscation Robustness

## 1. Executive Summary & Robustness Invariants
Created by Robert Grosse, **Krakatau** is designed specifically to decompile arbitrary, non-standard, and heavily obfuscated bytecode:
- Rejects heuristics; relies on formal mathematical type solvers.
- Models the JVM verifier with absolute mathematical precision.

## 2. Strict Type Lattice Solver
Krakatau formulates type recovery as a global constraint satisfaction problem over the complete Java reference type hierarchy. It can successfully decompile code with stripped `StackMapTable` attributes or deliberately corrupted debug tables.

## 3. Handling Malicious Bytecode
Where other decompilers crash or throw unhandled exceptions on invalid bytecode, Krakatau guarantees either valid decompiled output or precise diagnostic disassembler listings.

## 4. References
- Grosse, R. *Krakatau: Java decompiler and disassembler*. GitHub Repository, 2012-2023.
