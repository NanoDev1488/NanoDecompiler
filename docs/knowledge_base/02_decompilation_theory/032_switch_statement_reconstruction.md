# Article 032: Switch Statement Reconstruction: tableswitch vs lookupswitch

## 1. Executive Summary & Bytecode Mechanics
The JVM provides two specialized multi-way branch bytecodes (JVMS §6.5):
1. **`tableswitch` (0xAA)**:
   - Used for dense case keys.
   - Binary format contains `default_offset`, `low_byte`, `high_byte`, and a direct jump table indexed by $(key - low)$.
   - Execution is $O(1)$ direct array index lookup.
2. **`lookupswitch` (0xAB)**:
   - Used for sparse case keys.
   - Binary format contains `default_offset`, `npairs`, followed by sorted pairs of `(int32_key, int32_offset)`.
   - Execution is $O(\log N)$ binary search.

## 2. High-Level Switch Recovery
Both opcodes are translated into a single unified `SwitchStmtNode`:
1. Group cases that share identical target block offsets into multi-case labels (`case 1: case 2: ...`).
2. Identify the `default:` target block.
3. Detect case **fall-through**: If case $i$ does not end with a jump to switch exit, return, or throw, retain fall-through semantics; otherwise append `break;`.

## 3. Switch Expressions (Java 14+)
Modern Java supports switch expressions with arrow syntax:
```java
int result = switch (status) {
    case 1 -> 100;
    case 2 -> 200;
    default -> throw new IllegalStateException();
};
```
Decompilers detect when every case block yields a value to the same stack destination and format the switch as a concise expression.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `switchmap.cpp`:
- Translates `tableswitch` and `lookupswitch` into structured `SwitchStmtNode`.
- Tracks default target and resolves jump exits cleanly.

## 5. References
- JVMS §6.5: `tableswitch`, `lookupswitch`.
- JEP 361: *Switch Expressions*. OpenJDK, 2020.
