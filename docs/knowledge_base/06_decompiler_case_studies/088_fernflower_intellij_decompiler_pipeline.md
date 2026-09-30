# Article 088: Fernflower / IntelliJ IDEA Decompiler: Statement Graph Reduction

## 1. Executive Summary & JetBrains Integration
Originally written by Egor Ushakov, **Fernflower** was acquired by JetBrains and serves as the default decompiler in IntelliJ IDEA and Android Studio.

## 2. Statement Graph Reduction Engine
Fernflower structures code through hierarchical **Statement Graph Reduction**:
- Every basic block is initially a `BasicBlockStatement`.
- Iteratively applies reduction rules to encapsulate connected subgraphs into composite statements: `IfStatement`, `DoStatement`, `CatchStatement`, etc.
- If graph reduction stalls due to irregular jumps, it inserts synthetic "dummy" exit nodes and breaks cycles.

## 3. High-Throughput Heuristics
Because Fernflower runs interactively inside an IDE, it prioritizes speed and syntax validity, sometimes outputting labeled breaks rather than performing expensive global graph refactoring.

## 4. References
- JetBrains. *Fernflower: Analytical Decompiler for Java*. GitHub Repository, 2014-2024.
