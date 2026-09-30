# Article 072: Memory Arena and Monotonic Allocators for Decompiler Passes

## 1. Executive Summary & Memory Lifecycle
Decompilation exhibits a phase-oriented memory lifecycle:
- A method AST is constructed, analyzed, rewritten across 10+ passes, emitted as text, and then completely discarded.
- Allocating millions of tiny AST nodes individually via `new` causes severe heap fragmentation and cache misses.

## 2. Monotonic Arena Architecture
An **Arena Allocator** allocates large contiguous chunks (e.g. 64 KB - 1 MB) from the OS.
- Allocation inside the arena is a simple pointer bump:
  ```cpp
  void* allocate(size_t bytes, size_t alignment) {
      char* aligned_ptr = align_forward(current_ptr, alignment);
      if (aligned_ptr + bytes > end_ptr) allocate_new_chunk();
      current_ptr = aligned_ptr + bytes;
      return aligned_ptr;
  }
  ```
- Destruction is $O(1)$: upon method completion, reset `current_ptr` to the chunk beginning without invoking individual node destructors.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Fast per-method memory pools drastically accelerate JAR-wide batch decompilation throughput.

## 4. References
- Hanson, D. R. *A C Interface for Monotonic Memory Allocation*. Software: Practice and Experience, 1990.
