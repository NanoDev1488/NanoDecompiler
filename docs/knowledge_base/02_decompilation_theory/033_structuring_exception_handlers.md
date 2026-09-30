# Article 033: Structuring Exception Handlers, Protected Ranges, and Multi-Catch Blocks

## 1. Executive Summary & Specification
In Java, exception handling is structured hierarchically (`try { ... } catch (E e) { ... }`). In bytecode, it is represented as flat rows in the exception table with half-open intervals $[start, end)$ targeting `handler_pc`.

## 2. Structuring Algorithm
1. **Interval Clustering**: Group exception table rows that share identical $[start, end)$ ranges. These form the individual `catch` clauses of a single `try` statement.
2. **Range Containment Hierarchy**: Construct a containment tree of ranges:
   - Range $A$ contains Range $B$ if $start_A \le start_B$ and $end_B \le end_A$.
   - Process ranges from innermost to outermost.
3. **Multi-Catch Reconstruction (Java 7+)**:
   - If multiple rows with the same $[start, end)$ point to the exact same `handler_pc`, combine their exception types into a multi-catch clause:
   ```java
   catch (IOException | SQLException e) { ... }
   ```

## 3. Exception Variable Scope
At entry to `handler_pc`, the JVM pushes the thrown exception object onto the operand stack. The first instruction of the handler is almost universally `astore <var>`, binding the exception to a local variable.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `catchclean.cpp`:
- `build_try()` organizes ranges into clean `TryCatchRegion` hierarchies.
- Binds handler stack item to caught exception variable.

## 5. References
- JLS §14.20: The `try` statement.
- JVMS §4.7.3: Exception Tables.
