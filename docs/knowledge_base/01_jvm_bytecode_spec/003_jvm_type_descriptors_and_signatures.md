# Article 003: JVM Type Descriptors, Extended Signatures, and Generic Erasure

## 1. Executive Summary & Specification
The JVM employs two distinct type notation systems (JVMS §4.3, §4.7.9):
1. **Type Descriptors**: Low-level, non-generic representations used in method execution and field storage:
   - Primitive types: `B` (byte), `C` (char), `D` (double), `F` (float), `I` (int), `J` (long), `S` (short), `Z` (boolean), `V` (void).
   - Objects: `Lfull/package/ClassName;`.
   - Arrays: `[` prefix per dimension (e.g. `[[Ljava/lang/String;`).
   - Method signatures: `(ParameterDescriptors)ReturnDescriptor`.
2. **Generic Signatures**: Optional high-level metadata stored in `Signature` attributes:
   - Includes type variables (`TT;`), formal type parameters (`<T:Ljava/lang/Comparable<TT;>;>`), wildcard bounds (`+Ljava/lang/Number;`, `*`).

## 2. Grammar of Generic Method Signatures
```
MethodTypeSignature:
    [TypeParameters] ( {JavaTypeSignature} ) Result {ThrowsSignature}
TypeParameter:
    Identifier ClassBound {InterfaceBound}
ClassBound:
    : [ReferenceTypeSignature]
InterfaceBound:
    : ReferenceTypeSignature
```

## 3. Decompiler Reconstruction Strategy
When reconstructing Java source types:
1. Always prefer the `Signature` attribute if present and valid.
2. If `Signature` is absent (erased by compiler or stripped by obfuscator), fall back to parsing the raw descriptor.
3. Replace internal package slashes `/` with standard Java package dots `.`.
4. Replace inner class `$` delimiters with `.` only if confirmed by the `InnerClasses` attribute; otherwise retain `$` to avoid collision with legitimate class names.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- `parse_type_descriptor(std::string_view desc)` translates descriptors into `JavaType` AST nodes.
- `SignatureParser` walks formal type parameters and generic type arguments.

## 5. References
- JVMS §4.7.9: The Signature Attribute.
- Gosling, J., et al. *The Java Language Specification (JLS), Java SE 21*. Chapter 4: Types, Values, and Variables.
