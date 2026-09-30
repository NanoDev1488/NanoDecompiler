# Article 037: Abstract Syntax Tree (AST) Hierarchy for Decompilers

## 1. Executive Summary & Node Taxonomy
The decompiler AST represents the structured semantic hierarchy of the program. It bifurcates strictly into **Statements** and **Expressions**.

```
                         [ AstNode ]
                         /         \
                [ AstStmt ]       [ AstExpr ]
                 /   |   \          /    |    \
            Block   If   Loop    Binary Unary Call
```

## 2. Core Statement Node Hierarchy
- `BlockStmtNode`: Ordered list of child statements `{ S1; S2; ... }`.
- `IfStmtNode`: Condition expression, `then` block, optional `else` block.
- `WhileStmtNode`, `DoWhileStmtNode`, `ForStmtNode`: Loop constructs with conditions and step statements.
- `SwitchStmtNode`: Selector expression, case entries with associated blocks.
- `TryCatchStmtNode`: Protected block, catch clauses with caught exception types, optional finally block.
- `ReturnStmtNode`: Optional return expression.
- `ThrowStmtNode`: Thrown exception expression.
- `ExprStmtNode`: Wrapper for expressions with side effects (method invocations, assignments).

## 3. Core Expression Node Hierarchy
- `VarExpr`: Local variable or parameter reference.
- `LiteralExpr`: Integer, float, string, class, null literals.
- `BinaryExpr`: Arithmetic, bitwise, comparison, logical operators.
- `UnaryExpr`: Negation, bitwise NOT, logical NOT, cast expressions.
- `CallExpr`: Method call with receiver, method name, argument list.
- `NewExpr`: Object instantiation (`new Foo(...)`).
- `ArrayAccessExpr`, `FieldAccessExpr`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/include/ast_nodes.hpp`:
- Comprehensive C++ polymorphic class hierarchy with visitor support and clean memory management.

## 5. References
- Jones, N. D. *Abstract Syntax Trees in Compiler Construction*. ACM Computing Surveys, 2003.
