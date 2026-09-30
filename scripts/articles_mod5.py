"""
articles_mod5.py - Module 5: Advanced C++ Engine Architecture & Memory Performance (071 - 085)
"""

MODULE_NAME = "05_cpp_engine_architecture"

ARTICLES = [
    {
        "file": "071_ast_design_patterns_in_modern_cpp.md",
        "title": "AST Design Patterns in Modern C++: Polymorphic vs Variant-Based Hierarchies",
        "num": "071",
        "content": """# Article 071: AST Design Patterns in Modern C++: Polymorphic vs Variant-Based Hierarchies

## 1. Executive Summary & Design Trade-offs
In modern C++ decompiler architectures, two primary AST designs compete:
1. **Classical Polymorphic Hierarchy**:
   - Base class `AstNode` with virtual methods (`accept(Visitor&)`, `clone()`, `dump()`).
   - Managed via smart pointers (`std::shared_ptr<AstNode>` or `std::unique_ptr<AstNode>`).
   - *Strengths*: Highly extensible; open to new node types without recompiling unrelated modules.
   - *Weaknesses*: Virtual table dispatch overhead, cache fragmentation due to pointer chasing.
2. **Value-Based Tagged Union (`std::variant`)**:
   - Closed set of types in a variant: `using Node = std::variant<BlockNode, IfNode, WhileNode, ...>;`.
   - Processed via `std::visit` and pattern matching lambdas.
   - *Strengths*: Zero heap allocation when stored in flat vectors, excellent data locality.
   - *Weaknesses*: Size of variant is bounded by the largest alternative; recursive structures require indirection (`std::unique_ptr`).

## 2. Hybrid Modern Architecture
NanoDecompiler adopts a high-performance hybrid model: polymorphic node hierarchies backed by a custom monotonic memory arena, eliminating individual `malloc`/`free` calls while preserving full polymorphic extensibility.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/include/ast_nodes.hpp`:
- Polymorphic statement and expression hierarchy with visitor double-dispatch.

## 4. References
- Stroustrup, B. *The C++ Programming Language (4th Edition)*. Addison-Wesley, 2013.
- Alexandrescu, A. *Modern C++ Design: Generic Programming and Design Patterns Applied*. Addison-Wesley, 2001.
"""
    },
    {
        "file": "072_memory_arena_and_monotonic_allocators.md",
        "title": "Memory Arena and Monotonic Allocators for Decompiler Passes",
        "num": "072",
        "content": """# Article 072: Memory Arena and Monotonic Allocators for Decompiler Passes

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
"""
    },
    {
        "file": "073_string_interning_and_symbol_tables.md",
        "title": "String Interning and Symbol Tables for Constant Pool Symbols",
        "num": "073",
        "content": """# Article 073: String Interning and Symbol Tables for Constant Pool Symbols

## 1. Executive Summary & Hash Consing
Large Java codebases contain hundreds of thousands of redundant identifier strings (`java/lang/String`, `java/lang/Object`, `toString`, `equals`, `getId`).
**String Interning** (Hash Consing) ensures that exactly one immutable instance of each unique string is stored in memory.

## 2. Implementation Mechanics
```cpp
class SymbolTable {
    std::unordered_set<std::string> pool;
public:
    std::string_view intern(std::string_view s) {
        auto it = pool.find(s);
        if (it != pool.end()) return *it;
        return *pool.emplace(s).first;
    }
};
```
- Strings are represented throughout the AST as lightweight `std::string_view` or integer symbol IDs.
- String equality comparisons reduce from $O(N)$ character scans to $O(1)$ pointer comparisons.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp`:
- Constant pool UTF-8 symbols are indexed and deduplicated across the entire decompiler lifecycle.

## 4. References
- Ershov, A. P. *On programming of arithmetic operations*. Communications of the ACM, 1958 (First mention of hash consing).
"""
    },
    {
        "file": "074_visitor_pattern_and_double_dispatch.md",
        "title": "Visitor Pattern and Double Dispatch vs Modern C++ Alternatives",
        "num": "074",
        "content": """# Article 074: Visitor Pattern and Double Dispatch vs Modern C++ Alternatives

## 1. Executive Summary & Comparative Mechanics
The **Visitor Pattern** decouples algorithms from the object structures on which they operate:
- `AstVisitor` defines `visit(IfStmtNode&)`, `visit(WhileStmtNode&)`, etc.
- Each AST node implements `accept(AstVisitor& v) { v.visit(*this); }`.

## 2. Advantages for Decompilation
1. **Pass Separation**: Each transformation pass (e.g. `DeadCodeEliminator`, `ExpressionInliner`, `JavaEmitter`) is an isolated, self-contained visitor class.
2. **Selective Overrides**: A default `RecursiveAstVisitor` walks children automatically; specific passes only override the node types they inspect.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/include/ast_nodes.hpp` and `emit.cpp`:
- `AstVisitor` powers code emission and tree transformations.

## 4. References
- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley, 1994.
"""
    },
    {
        "file": "075_graph_data_structures_for_cfg.md",
        "title": "Graph Data Structures for CFG Representation: Adjacency and Bitsets",
        "num": "075",
        "content": """# Article 075: Graph Data Structures for CFG Representation: Adjacency and Bitsets

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
"""
    },
    {
        "file": "076_zero_copy_bytecode_parsing.md",
        "title": "Zero-Copy Bytecode Parsing and Memory-Mapped Class Files",
        "num": "076",
        "content": """# Article 076: Zero-Copy Bytecode Parsing and Memory-Mapped Class Files

## 1. Executive Summary & Throughput Bottlenecks
Reading thousands of `.class` files via standard `fread` or `std::ifstream` incurs massive OS kernel context-switching and buffer copying overhead.

## 2. Zero-Copy Architecture
1. **Memory Mapping**: Map JAR archives and extracted class files directly into process virtual memory via `CreateFileMapping` / `MapViewOfFile` (Windows) or `mmap` (POSIX).
2. **Pointer Casting & Endianness**: Read headers and attributes using pointer offsets directly into the mapped buffer:
```cpp
inline uint16_t read_u2(const uint8_t*& ptr) {
    uint16_t val = (uint16_t(ptr[0]) << 8) | uint16_t(ptr[1]);
    ptr += 2;
    return val;
}
```
3. Avoid allocating heap strings or vectors for byte sequences; reference them via `std::string_view` and `std::span<const uint8_t>`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `zip_reader.cpp`:
- Zero-copy stream cursors decode class headers directly from memory.

## 4. References
- Kerrisk, M. *The Linux Programming Interface*. Chapter 49: Memory Mappings. No Starch Press, 2010.
"""
    },
    {
        "file": "077_exception_handling_strategies_in_cpp.md",
        "title": "Exception Handling Strategies in C++: Result Types vs Exceptions",
        "num": "077",
        "content": """# Article 077: Exception Handling Strategies in C++: Result Types vs Exceptions

## 1. Executive Summary & Cost of C++ Exceptions
Standard C++ exceptions (`throw`, `catch`) incur non-zero runtime overhead during stack unwinding (Itanium ABI table lookups, Windows SEH dispatch). In high-performance compilers and decompilers:
- Exceptions should be reserved strictly for fatal errors (e.g. corrupted binary format, out-of-memory).
- Expected failure paths (e.g. inability to structure a particular irreducible loop) should return explicit result types (`std::expected<T, DecompileError>` in C++23, or custom `StatusOr<T>`).

## 2. NanoDecompiler Recovery Strategy
Rather than throwing a fatal `DecompileAbort` when an edge case is detected:
- The engine marks the current method with a diagnostic notice.
- Synthesizes fallback AST nodes or raw bytecode annotations.
- Allows surrounding methods and classes to decompile completely without halting the entire pipeline.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp` and `structure.cpp`:
- Replaced fatal aborts with graceful recovery and safe AST synthesis.

## 4. References
- Sutter, H. *Zero-overhead deterministic exceptions: Throwing values*. ISO C++ Committee Paper P0709R4, 2019.
"""
    },
    {
        "file": "078_multi_threaded_parallel_decompilation.md",
        "title": "Multi-Threaded Parallel Decompilation Architecture",
        "num": "078",
        "content": """# Article 078: Multi-Threaded Parallel Decompilation Architecture

## 1. Executive Summary & Scalability
Large enterprise JAR files contain upwards of 50,000 classes. A single-threaded decompiler takes minutes; a parallel multi-threaded architecture finishes in seconds.

## 2. Work-Stealing Thread Pool Design
```
                       [ Input JAR Archive ]
                                │
                        (Extract Classes)
                                │
                                ▼
                   [ Lock-Free Task Queue ]
                     /       |        \
                    ▼        ▼         ▼
                Thread 1  Thread 2  Thread N
                 (AST)     (AST)     (AST)
                    \        |        /
                     ▼       ▼       ▼
                 [ Thread-Safe Aggregator ]
                             │
                             ▼
                   [ Output Source Tree ]
```
Key Invariant: Class decompilation is embarrassingly parallel. Each thread processes a class in total isolation, with independent arenas and symbol tables, eliminating lock contention.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/cli_main.cpp` and `process_jar.cpp`:
- Thread pool dispatches class decompilation across all available hardware cores.

## 4. References
- Lea, D. *A Java fork/join framework*. ACM Java Grande, 2000.
"""
    },
    {
        "file": "079_cache_friendly_bitset_algorithms.md",
        "title": "Cache-Friendly Bitset Algorithms for Liveness and Dominance",
        "num": "079",
        "content": """# Article 079: Cache-Friendly Bitset Algorithms for Liveness and Dominance

## 1. Executive Summary & Hardware Performance
Dataflow analysis requires performing millions of set union, intersection, and difference operations:
$$\text{LiveIn}(B) = \text{Use}(B) \cup (\text{LiveOut}(B) \setminus \text{Def}(B))$$
Representing sets as standard hash sets (`std::unordered_set<int>`) incurs pointer chasing and cache misses.

## 2. Word-Aligned Dense Bitsets
A flat array of 64-bit words (`uint64_t[]`):
- Union: `words[i] |= other.words[i]`
- Intersection: `words[i] &= other.words[i]`
- Difference: `words[i] &= ~other.words[i]`
Processes 64 variables in a single CPU cycle using SIMD vector instructions (AVX2/AVX-512).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Fast bitsets power liveness and dominance calculations.

## 4. References
- Lemire, D., et al. *Consistently faster and smaller compressed bitmaps with Roaring*. Software: Practice and Experience, 2016.
"""
    },
    {
        "file": "080_fast_regex_and_pattern_matchers_cpp.md",
        "title": "Fast Bytecode Pattern Matchers in C++ (DFA and Boyer-Moore)",
        "num": "080",
        "content": """# Article 080: Fast Bytecode Pattern Matchers in C++ (DFA and Boyer-Moore)

## 1. Executive Summary & Pattern Matching in Bytecode
Recognizing syntactic sugar (e.g. `StringBuilder` chains, string switches, try-with-resources) requires matching opcode sequences with wildcards:
```
[aload_0] -> [dup] -> [getfield X] -> [bipush Y] -> [iadd] -> [putfield X]
```

## 2. Deterministic Finite Automata (DFA) Matchers
Instead of multiple conditional checks across nested loops:
- Compile bytecode sequence patterns into a DFA state machine.
- Match opcode sequences in a single linear pass over the basic block instruction stream.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- State-machine driven pattern recognizer for array initializers and constructor invocations.

## 4. References
- Aho, A. V., & Corasick, M. J. *Efficient string matching: an aid to bibliographic search*. Communications of the ACM, 1975.
"""
    },
    {
        "file": "081_diagnostics_and_error_recovery_architecture.md",
        "title": "Diagnostics and Error Recovery Architecture: Graceful Fallback",
        "num": "081",
        "content": """# Article 081: Diagnostics and Error Recovery Architecture: Graceful Fallback

## 1. Executive Summary & Design Principles
A production decompiler must never crash on malformed, corrupted, or obfuscated bytecode:
1. **Containment**: An error in a single method must not prevent decompilation of other methods in the same class.
2. **Transparency**: The decompiler must emit high-fidelity comments explaining why a fallback occurred.
3. **Structured Fallback Hierarchy**:
   - Level 1: Fully structured Java AST.
   - Level 2: Partial AST with inline bytecode for unstructurable subgraphs.
   - Level 3: Clean disassembled bytecode annotated with stack types.

## 2. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp` and `README_RU.txt`:
- Generates detailed diagnostic reports (`README_RU.txt`) explaining any fallback reasons and transmitting error telemetry when enabled.

## 3. References
- Cifuentes, C., et al. *The Dava Decompiler: Structured flow analysis*. ACM CC, 2003.
"""
    },
    {
        "file": "082_cross_platform_abstraction_layers.md",
        "title": "Cross-Platform Abstraction Layers: Windows, Linux, and macOS",
        "num": "082",
        "content": """# Article 082: Cross-Platform Abstraction Layers: Windows, Linux, and macOS

## 1. Executive Summary & Platform Portability
A cross-platform C++ engine must interface seamlessly with diverse OS APIs:
- **Windows**: `wininet.h` for HTTP networking, `CreateProcessW` for process spawning, UTF-16 wchar path semantics.
- **Linux / macOS**: POSIX sockets / `libcurl`, `fork`/`exec`, UTF-8 filesystem semantics.

## 2. Clean Header Abstraction
Encapsulate OS differences behind clean C++ interfaces:
```cpp
class Platform {
public:
    static bool download_file(std::string_view url, const std::string& destination);
    static std::string get_executable_path();
    static size_t get_total_system_memory();
};
```

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/platform_detect.cpp` and `auto_update.cpp`:
- Conditional compilation (`#ifdef _WIN32`) bridges WinINet on Windows and POSIX on Linux/macOS.

## 4. References
- Stroustrup, B. *Design and Evolution of C++*. Addison-Wesley, 1994.
"""
    },
    {
        "file": "083_binary_size_and_compilation_speed_optimization.md",
        "title": "Binary Size and Compilation Speed Optimization: LTO and Header Minimization",
        "num": "083",
        "content": """# Article 083: Binary Size and Compilation Speed Optimization: LTO and Header Minimization

## 1. Executive Summary & Build Performance
C++ compiler speed and binary size are critical for CI/CD and distribution:
1. **Header Minimization**: Replace `#include` with forward declarations (`class AstNode;`) in header files to minimize compilation cascades.
2. **Link-Time Optimization (LTO / LTCG)**: Enable whole-program optimization in CMake (`set(CMAKE_INTERPROCEDURAL_OPTIMIZATION TRUE)`), allowing cross-file function inlining and dead code stripping.
3. **Symbol Stripping**: Strip unused debugging symbols from release binaries to minimize executable payload.

## 2. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/CMakeLists.txt`:
- Optimization flags (`-O3`, `/O2`, LTO) configured for lean, standalone executable binaries.

## 3. References
- Meyers, S. *Effective Modern C++*. O'Reilly Media, 2014.
"""
    },
    {
        "file": "084_sanitizers_fuzzing_and_valgrind_in_engine.md",
        "title": "Sanitizers, Fuzzing, and Valgrind in C++ Decompiler Engine",
        "num": "084",
        "content": """# Article 084: Sanitizers, Fuzzing, and Valgrind in C++ Decompiler Engine

## 1. Executive Summary & Memory Safety
C++ decompilers parse untrusted binary inputs from third-party JARs. Memory safety vulnerabilities (buffer overflows, use-after-free) can lead to remote code execution.

## 2. Verification Tooling
- **AddressSanitizer (ASan)**: Detects out-of-bounds accesses, use-after-free, and stack corruptions at runtime.
- **UndefinedBehaviorSanitizer (UBSan)**: Detects signed integer overflow, null pointer dereferencing, and misaligned pointer casts.
- **libFuzzer**: Feeds randomly mutated `.class` files into the parser to uncover edge-case crashes.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/verify.cpp`:
- Rigorous bounds checking on all byte buffer reads.

## 4. References
- Serebryany, K., et al. *AddressSanitizer: A fast address sanity checker*. USENIX ATC, 2012.
"""
    },
    {
        "file": "085_profiling_and_benchmarking_cpp_decompilers.md",
        "title": "Profiling and Benchmarking C++ Decompilers: VTune, Tracy, and Throughput Metrics",
        "num": "085",
        "content": """# Article 085: Profiling and Benchmarking C++ Decompilers: VTune, Tracy, and Throughput Metrics

## 1. Executive Summary & Profiling Methodology
Performance optimization requires measuring concrete hardware performance metrics:
- **Throughput**: Classes decompiled per second (target: > 1,000 classes/sec on modern x86_64).
- **Latency**: P99 decompilation time per method (target: < 5ms).
- **Memory Peak**: Maximum Resident Set Size (RSS) during multi-gigabyte JAR processing.

## 2. Profiling Tools
- **Intel VTune / Linux `perf`**: Identifies CPU branch mispredictions and L1/L2 cache misses in CFG traversals.
- **Tracy Profiler**: Real-time frame-by-frame visualization of thread pool task queues and pipeline phases.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stats_json.cpp`:
- Collects fine-grained execution metrics (bytecode size, method counts, elapsed milliseconds).

## 4. References
- Gregg, B. *Systems Performance: Enterprise and the Cloud (2nd Edition)*. Addison-Wesley, 2020.
"""
    }
]
