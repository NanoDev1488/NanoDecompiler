# Article 062: Enhanced For-Loop (foreach) over Arrays Reconstruction

## 1. Executive Summary & Bytecode Pattern
The enhanced for-loop `for (T item : array)` is lowered by javac into:
```java
T[] arr$ = array;
int len$ = arr$.length;
for (int i$ = 0; i$ < len$; ++i$) {
    T item = arr$[i$];
    // Loop Body
}
```

## 2. Reconstruction Algorithm
1. Detect `ForStmtNode` where:
   - Loop bound is `arr.length`.
   - Index variable `i` is initialized to 0 and incremented by 1.
   - First statement of the loop body is an array read `T item = arr[i]`.
2. Eliminate `arr$`, `len$`, and `i$` temporaries from AST.
3. Emit `for (T item : array) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Pattern matches array iteration loops and converts them to `ForEachStmtNode`.

## 4. References
- JLS §14.14.2: The enhanced `for` statement.
