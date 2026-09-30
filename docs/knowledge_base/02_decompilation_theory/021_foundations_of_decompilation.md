# Article 021: Foundations of Decompilation: Historical Perspectives and Modern Pipelines

## 1. Executive Summary & Theoretical Context
Decompilation is the process of reversing the compilation transformation: translating low-level machine code or bytecode back into high-level source code. Formally established by Cristina Cifuentes (1994), decompilation transforms an unstructured instruction stream with jump instructions into a structured Abstract Syntax Tree (AST).

## 2. The Classic 5-Stage Decompiler Pipeline
```
[Bytecode / ClassFile]
         │
         ▼ (1. Front-End Parsing)
[Instruction Stream & Exception Tables]
         │
         ▼ (2. Control Flow Analysis)
[Control Flow Graph (CFG) & Dominator Trees]
         │
         ▼ (3. Dataflow Analysis & SSA)
[High-Level Expressions & Type Recovery]
         │
         ▼ (4. AST Structuring)
[Hierarchical AST (If, Loop, Switch, Try)]
         │
         ▼ (5. Back-End Code Generation)
[High-Quality Java Source Code]
```

## 3. The Central Challenge: Information Loss
Compilation is lossy:
- High-level control structures (`while`, `for`, `switch`) are flattened into conditional jumps (`if_icmpne`, `goto`).
- Local variable scopes and names are discarded (unless debug tables are preserved).
- Expression boundaries are dissolved into temporary operand stack pushes/pops.
Decompilation algorithms must invert these lossy mappings using deterministic structural patterns and lattice-based dataflow analysis.

## 4. NanoDecompiler C++ Engine Integration
NanoDecompiler executes this exact pipeline across its core C++ modules:
- Front-end: `classfile.cpp`, `disassembler.cpp`.
- CFG & Structuring: `cfg.cpp`, `structure.cpp`.
- Dataflow & Stack: `stackvm.cpp`, `engine.cpp`.
- AST & Emitting: `ast_nodes.hpp`, `render_class.cpp`, `emit.cpp`.

## 5. References
- Cifuentes, C. *Reverse Compilation Techniques*. PhD Thesis, Queensland University of Technology, 1994.
- Muchnick, S. S. *Advanced Compiler Design and Implementation*. Morgan Kaufmann, 1997.
