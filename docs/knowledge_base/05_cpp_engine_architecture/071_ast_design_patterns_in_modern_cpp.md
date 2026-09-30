# Article 071: AST Design Patterns in Modern C++: Polymorphic vs Variant-Based Hierarchies

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
