# Article 074: Visitor Pattern and Double Dispatch vs Modern C++ Alternatives

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
