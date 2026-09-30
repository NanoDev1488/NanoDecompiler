# Article 026: Interval Analysis, T1-T2 Transformations, and Limit Flow Graphs

## 1. Executive Summary & Formal Theory
Cocke and Allen introduced **Interval Analysis** to partition a control flow graph into maximal single-entry subgraphs called **Intervals**.
An interval $I(h)$ with header $h$ satisfies:
1. $h$ is the only entry point into $I(h)$ from nodes outside $I(h)$.
2. All cycles inside $I(h)$ must contain $h$.

## 2. Hecht and Ullman T1-T2 Graph Transformations
Any reducible CFG can be collapsed into a single vertex through iterative application of two transformations:
- **T1 Transformation (Self-Loop Removal)**: If edge $n 	o n$ exists, delete it and record a loop operation.
- **T2 Transformation (Predecessor Collapse)**: If node $n$ has a unique predecessor $m$, and $n 
eq m$, and $m$ is not the entry node of another interval, merge $n$ into $m$.

## 3. Limit Flow Graph & Reducibility Criteria
A graph is **reducible** if and only if repeated application of T1 and T2 reduces the graph to a single node (the Limit Flow Graph).
If reduction halts with multiple nodes and no further T1 or T2 is applicable, the remaining subgraph is **irreducible** and contains multi-entry cycles.

## 4. Decompiler Application
Intervals provide natural encapsulation boundaries: each interval $I(h)$ corresponds directly to a Java structured compound statement (loop or nested block).

## 5. References
- Allen, F. E., & Cocke, J. *A program data flow analysis procedure*. Communications of the ACM, 1976.
- Hecht, M. S., & Ullman, J. D. *Characterizations of reducible flow graphs*. Journal of the ACM, 1974.
