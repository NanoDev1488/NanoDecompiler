# Article 063: Enhanced For-Loop (foreach) over Iterables (Collection/List)

## 1. Executive Summary & Bytecode Pattern
The enhanced for-loop `for (T item : collection)` is lowered by javac into:
```java
Iterator<T> it$ = collection.iterator();
while (it$.hasNext()) {
    T item = it$.next();
    // Loop Body
}
```

## 2. Reconstruction Algorithm
1. Detect loop where initialization block calls `.iterator()` on an `Iterable`.
2. Loop condition calls `it.hasNext()`.
3. First statement of the loop body calls `it.next()`.
4. Iterator variable is not used elsewhere in the loop.
5. Collapse into `for (T item : collection) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Recognizes `Iterator` patterns and transforms `while` loops into concise `ForEachStmtNode`.

## 4. References
- JLS §14.14.2: The enhanced `for` statement.
