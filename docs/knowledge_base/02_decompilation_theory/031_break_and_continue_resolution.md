# Article 031: Labeled break and continue Target Resolution in Nested Constructs

## 1. Executive Summary & Specification
Java supports unlabeled and labeled control transfers:
- `break`: Exits the immediately enclosing `switch`, `while`, `do`, or `for`.
- `continue`: Skips remainder of the current iteration of the immediately enclosing loop.
- `break <label>` / `continue <label>`: Exits or continues the specific enclosing construct flagged with `<label>:`.

## 2. Target Resolution Algorithm
When a jump edge $B 	o T$ is encountered inside a structured loop or switch:
1. If $T$ is the loop header $h$ of the immediately enclosing loop $\implies$ emit **`continue;`**.
2. If $T$ is the loop exit block of the immediately enclosing loop $\implies$ emit **`break;`**.
3. If $T$ is the header of an outer loop enclosing the current loop $\implies$ synthesize label on outer loop and emit **`continue outerLabel;`**.
4. If $T$ is the exit block of an outer loop or block $\implies$ synthesize label and emit **`break outerLabel;`**.

## 3. Eliminating Unnecessary Labels
Labels add visual noise. The decompiler must maintain a stack of active lexical scopes and only emit a label if an explicit jump requires disambiguation.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Resolves jump targets against lexical loop stacks.
- Emits `BreakStmtNode` and `ContinueStmtNode` with optional label identifiers.

## 5. References
- JLS §14.15: The `break` Statement.
- JLS §14.16: The `continue` Statement.
