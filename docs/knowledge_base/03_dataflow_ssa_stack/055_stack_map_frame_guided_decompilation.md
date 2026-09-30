# Article 055: StackMapTable-Guided Decompilation and Type Synchronization

## 1. Executive Summary & Synergy
While traditional decompilers treat `StackMapTable` merely as a verifier artifact, modern decompilers exploit it as an authoritative oracle:
1. **Unambiguous Block Targets**: Every frame offset is guaranteed to be a valid basic block start.
2. **Authoritative Type Annotations**: Resolves primitive vs reference ambiguity at merge points.
3. **Stack Invariant Validation**: Verifies that the decompiler's symbolic stack height matches the JVM verifier's ground truth.

## 2. Synchronization Strategy
At each basic block boundary matching a `StackMapFrame`:
- Assert `stackvm.depth() == frame.stack_size()`.
- Reconcile local variable types with `frame.locals()`.
- Flag obfuscator-generated fake frames that violate reachability.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/verify.cpp` and `classfile.cpp`:
- Correlates stack simulation states with classfile stack map frames.

## 4. References
- JVMS §4.7.4: The StackMapTable Attribute.
