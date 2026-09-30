# Article 002: ClassFile Binary Structure and Constant Pool Mechanics

## 1. Executive Summary & Specification
The `.class` binary format (JVMS §4) is a strictly big-endian byte sequence. Its constant pool (`cp_info`) acts as the central symbol table, dereferenced by virtually every bytecode instruction and attribute:
- Magic: `0xCAFEBABE` (uint32).
- Minor / Major Version: uint16 each (e.g., 52 = Java 8, 61 = Java 17, 65 = Java 21).
- Constant Pool Count: uint16 (`constant_pool_count - 1` entries, 1-indexed; `CONSTANT_Long` and `CONSTANT_Double` consume two slots).

## 2. Constant Pool Tag Hierarchy
| Tag Value | Type Constant | Payload | Decompiler Usage |
|-----------|---------------|---------|------------------|
| 1 | `CONSTANT_Utf8` | `length` + raw UTF-8 bytes | Strings, identifiers, descriptors |
| 3 | `CONSTANT_Integer` | uint32 (big-endian 2's complement) | Primitive int/boolean/byte constants |
| 4 | `CONSTANT_Float` | uint32 (IEEE 754 single) | Floating point literals |
| 5 | `CONSTANT_Long` | uint64 (consumes 2 slots) | 64-bit integer literals |
| 6 | `CONSTANT_Double` | uint64 (consumes 2 slots) | 64-bit IEEE 754 literals |
| 7 | `CONSTANT_Class` | `name_index` -> Utf8 | Class and interface references |
| 8 | `CONSTANT_String` | `string_index` -> Utf8 | String literals in code |
| 9 | `CONSTANT_Fieldref` | `class_index`, `name_and_type_index` | Member variable accesses (`getfield`) |
| 10 | `CONSTANT_Methodref` | `class_index`, `name_and_type_index` | Concrete method invocations |
| 11 | `CONSTANT_InterfaceMethodref`| `class_index`, `name_and_type_index` | Interface invocations |
| 12 | `CONSTANT_NameAndType` | `name_index`, `descriptor_index` | Symbol signature resolution |
| 15 | `CONSTANT_MethodHandle` | `reference_kind`, `reference_index` | invokedynamic bootstrap pointers |
| 16 | `CONSTANT_MethodType` | `descriptor_index` -> Utf8 | Polymorphic method signatures |
| 17 | `CONSTANT_Dynamic` | `bootstrap_method_attr_index`, `name_and_type` | Condy (dynamic constants) |
| 18 | `CONSTANT_InvokeDynamic`| `bootstrap_method_attr_index`, `name_and_type` | Indy call sites |
| 19 | `CONSTANT_Module` | `name_index` -> Utf8 | Java 9 module declarations |
| 20 | `CONSTANT_Package` | `name_index` -> Utf8 | Java 9 package declarations |

## 3. Parser Engineering & Zero-Copy Architecture
In high-throughput C++ decompilation:
- Avoid allocating individual objects for constant pool items. Use a flat `std::vector<CpEntry>` where `CpEntry` is a compact 16-byte union/struct with raw pointers into the memory-mapped `.class` buffer.
- UTF-8 string lookup should return `std::string_view` directly pointing into the memory-mapped buffer, avoiding heap copies.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp`:
- `ClassFile::parse(const uint8_t* data, size_t size)` validates `0xCAFEBABE` and parses constant pool entries into `ConstantPool` structure.
- Two-slot handling for Long/Double is explicitly safeguarded with dummy index padding.

## 5. References
- JVMS §4.4: The Constant Pool.
- Meyer, D. *Zero-Copy Binary Parsing in Systems Programming*. ACM SIGPLAN, 2019.
