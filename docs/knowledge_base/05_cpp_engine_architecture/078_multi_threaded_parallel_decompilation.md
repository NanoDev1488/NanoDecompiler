# Article 078: Multi-Threaded Parallel Decompilation Architecture

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
                     /       |                            ▼        ▼         ▼
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
