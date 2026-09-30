# Article 075: Graph Data Structures for CFG Representation: Adjacency and Bitsets

## 1. Executive Summary & Performance Invariants
Decompiler CFG algorithms require ultra-fast traversal of predecessor and successor edges:
- Standard pointer-based graph nodes (`struct Node { vector<Node*> succs; }`) suffer from cache misses and pointer overhead.
- **Dense Indexed CFG**:
  - Basic blocks are assigned contiguous integer IDs: $0, 1, 2, \dots, |V|-1$.
  - Edge adjacency is stored in flat arrays: `std::vector<std::vector<int32_t>> preds, succs;`.

## 2. Bitset Graph Matrices
For dominance, reachability, and liveness analysis:
- A flat 2D bit matrix (`BitMatrix[V][V]`) enables parallel 64-bit word operations (`uint64_t` bitwise AND/OR) for set operations across the entire graph.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/cfg.cpp`:
- Indexed basic block identifiers and compact adjacency vectors guarantee rapid graph traversal.

## 4. References
- Tarjan, R. E. *Data Structures and Network Algorithms*. SIAM, 1983.
