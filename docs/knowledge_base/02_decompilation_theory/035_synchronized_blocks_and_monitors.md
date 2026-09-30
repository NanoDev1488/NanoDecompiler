# Article 035: Synchronized Blocks, Monitor Invariants, and Synthetic Handlers

## 1. Executive Summary & Specification
Java supports mutual exclusion at method level (`ACC_SYNCHRONIZED`) and block level (`synchronized (expr) { ... }`).
Block-level synchronization is compiled using `monitorenter` (0xC2) and `monitorexit` (0xC3) bytecodes (JVMS §6.5).

## 2. Invariant Rules & Compiler Pattern
The JVM requires that for every execution path exiting the synchronized block (normal completion, return, or exception), `monitorexit` must be executed exactly once on the lock object.
Compiler sequence:
```
    aload_lock
    dup
    astore_lock_temp
    monitorenter
TryStart:
    [ Synchronized Body ]
    aload_lock_temp
    monitorexit
    goto EndLabel
ExceptionHandler: (covers TryStart to EndLabel, catch_type = 0)
    aload_lock_temp
    monitorexit
    athrow
EndLabel:
```

## 3. Decompiler Recovery Strategy
1. Identify `monitorenter` instruction and extract the lock expression.
2. Locate the synthetic exception handler covering the block that executes `monitorexit` and `athrow`.
3. Eliminate the synthetic temporary lock variable, the duplicate `monitorexit` calls, and the synthetic exception handler.
4. Wrap the protected body in `synchronized (lockExpr) { ... }`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp` and `structure.cpp`:
- Identifies monitor enter/exit pairs and converts them into structured `SynchronizedStmtNode`.

## 5. References
- JVMS §3.14: Synchronization.
- JLS §14.19: The `synchronized` Statement.
