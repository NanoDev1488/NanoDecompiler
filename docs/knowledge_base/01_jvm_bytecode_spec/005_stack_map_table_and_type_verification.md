# Article 005: StackMapTable Verification Algorithm and Type Reconstruction

## 1. Executive Summary & Specification
Introduced in Java 6 (class format 50.0), `StackMapTable` is a mandatory attribute for type-checking verification (JVMS §4.7.4). Rather than performing costly iterative dataflow inference at runtime, the JVM verifier verifies the method in a single linear pass using precomputed type annotations at every branch target.

## 2. Frame Tag Architecture
- `same_frame` (0-63): Same locals as previous frame, stack is empty.
- `same_locals_1_stack_item_frame` (64-127): Same locals, exactly 1 operand on stack.
- `same_locals_1_stack_item_frame_extended` (247): Same locals, 1 operand, explicit 16-bit offset.
- `chop_frame` (248-250): Stack is empty, removes top `k` locals (`k = 251 - frame_type`).
- `same_frame_extended` (251): Same locals, stack empty, explicit 16-bit offset.
- `append_frame` (252-254): Appends `k` locals (`k = frame_type - 251`), stack empty.
- `full_frame` (255): Complete snapshot of all locals and stack items.

## 3. Verification Type Info Tags
| Tag | Identifier | Description |
|-----|------------|-------------|
| 0 | `ITEM_Top` | Uninitialized or second half of long/double |
| 1 | `ITEM_Integer` | int, boolean, byte, char, short |
| 2 | `ITEM_Float` | float |
| 3 | `ITEM_Double` | double (followed by Top) |
| 4 | `ITEM_Long` | long (followed by Top) |
| 5 | `ITEM_Null` | The literal `null` reference |
| 6 | `ITEM_UninitializedThis` | `this` reference before `<init>` invocation |
| 7 | `ITEM_Object` | Object instance with `cp_info` class index |
| 8 | `ITEM_Uninitialized` | Object reference created via `new` prior to constructor |

## 4. Decompiler Utilization & Ground Truth
While obfuscators sometimes tamper with or strip `StackMapTable` in older formats, in modern Java it serves as ground truth:
1. Instant confirmation of basic block boundaries (every stack map frame is a branch target).
2. Accurate local variable type recovery when debug `LocalVariableTable` is absent.
3. Verification of stack heights at merge points, detecting intentionally corrupted traps.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `verify.cpp`:
- `parse_stack_map_table()` extracts frame offsets and verification types.
- Frame types guide `StackVM` when reconciling predecessor stack variations.

## 6. References
- JVMS §4.7.4: The StackMapTable Attribute.
- Rose, J. *Bytecode Verification by Type Checking*. Sun Microsystems Technical Report, 2006.
