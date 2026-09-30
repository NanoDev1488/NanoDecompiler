# Article 004: JVM Instruction Set Categorization and Stack Semantics

## 1. Executive Summary & Specification
The JVM instruction set comprises 205 operational opcodes (plus reserved and debugging opcodes). Each instruction is a single 8-bit byte followed by zero or more operand bytes.

## 2. Functional Taxonomy
1. **Constants & Loading**:
   - `aconst_null`, `iconst_m1..5`, `lconst_0..1`, `fconst_0..2`, `dconst_0..1`.
   - `bipush`, `sipush`, `ldc`, `ldc_w`, `ldc2_w`.
2. **Local Variable Transfer**:
   - `iload`, `lload`, `fload`, `dload`, `aload` (and `_0..3` fast shorthands).
   - `istore`, `lstore`, `fstore`, `dstore`, `astore` (and `_0..3` fast shorthands).
3. **Operand Stack Management**:
   - `pop`, `pop2`: Discards 1 or 2 stack words.
   - `dup`: Duplicates top 1 word.
   - `dup_x1`, `dup_x2`: Duplicates top 1 word and inserts 1 or 2 slots down.
   - `dup2`, `dup2_x1`, `dup2_x2`: Duplicates top 2 words (e.g. `long` or pair of references).
   - `swap`: Swaps top two 32-bit stack items.
4. **Arithmetic & Bitwise Operations**:
   - Addition, subtraction, multiplication, division, remainder, negation, bitwise AND/OR/XOR, shifts (`ishl`, `ishr`, `iushr`).
5. **Control Transfer**:
   - Comparison and branch: `ifeq`, `ifne`, `iflt`, `ifge`, `ifgt`, `ifle`, `if_icmpeq`, etc.
   - Unconditional jump: `goto`, `goto_w`.
   - Table and lookup switches: `tableswitch`, `lookupswitch`.
6. **Object & Array Manipulation**:
   - `new`, `newarray`, `anewarray`, `multianewarray`.
   - `getfield`, `putfield`, `getstatic`, `putstatic`.
   - `arraylength`, `iaload`, `iastore`, etc.
7. **Type Casting & Verification**:
   - Primitive conversion: `i2l`, `i2f`, `i2d`, `l2i`, `f2i`, `d2i`, `i2b`, `i2c`, `i2s`.
   - Reference checking: `checkcast`, `instanceof`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/opcodes.cpp` and `stackvm.cpp`:
- `OPCODE_TABLE` maps every byte (0..255) to opcode names, operand byte length functions, and stack delta signatures (`pops`, `pushes`).
- Specialized handling for `dup_x1` and `dup2` enables complex expression inlining without synthetic variables.

## 4. References
- JVMS §6: The Java Virtual Machine Instruction Set.
