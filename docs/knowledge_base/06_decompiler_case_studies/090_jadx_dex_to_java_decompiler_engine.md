# Article 090: Jadx Decompiler: Dalvik Register Allocation and Android Dex Structuring

## 1. Executive Summary & Register-Based Architecture
Developed by Skylot, **Jadx** decompiles Android `.dex` binaries into Java. Android uses Dalvik bytecode, which is register-based rather than stack-based:
- Instructions operate directly on virtual registers (`v0`, `v1`, etc.).
- Eliminates the need for operand stack simulation, but requires aggressive register allocation reversal.

## 2. Region-Based Control Flow Structuring
Jadx structures CFGs using a hierarchy of **Regions**:
- `LoopRegion`, `IfRegion`, `SwitchRegion`, `SynchronizedRegion`.
- Employs iterative simplification passes that hoist declarations and resolve phi-nodes directly from Dalvik register lifetimes.

## 3. References
- Skylot. *Jadx - Dex to Java decompiler*. GitHub Repository, 2014-2024.
