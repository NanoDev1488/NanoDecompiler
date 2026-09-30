# Article 007: The invokedynamic Architecture and Bootstrap Method Resolution

## 1. Executive Summary & Specification
Introduced in Java 7 (JSR 292, Da Vinci Machine), `invokedynamic` (0xBA) decouples bytecode execution from static class/method bindings (JVMS §6.5.invokedynamic, §4.7.23):
- Instead of pointing to a constant pool Methodref, `invokedynamic` points to a `CONSTANT_InvokeDynamic` entry.
- The entry references a bootstrap method in the `BootstrapMethods` attribute of the `ClassFile`.
- On first execution, the JVM invokes the bootstrap method (BSM), which returns a `java.lang.invoke.CallSite` linked to a `MethodHandle`. Subsequent invocations bypass BSM and execute the linked target directly.

## 2. Bootstrap Methods Table Structure
```
BootstrapMethods_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 num_bootstrap_methods;
    {   u2 bootstrap_method_ref;        // MethodHandle
        u2 num_bootstrap_arguments;
        u2 bootstrap_arguments[num_bootstrap_arguments]; // CP indices
    } bootstrap_methods[num_bootstrap_methods];
}
```

## 3. High-Level Java Idioms Powered by Indy
1. **Lambda Expressions** (Java 8+): `LambdaMetafactory.metafactory`.
2. **String Concatenation** (Java 9+): `StringConcatFactory.makeConcatWithConstants`.
3. **Record Canonical Methods** (Java 16+): `ObjectMethods.bootstrap`.
4. **Pattern Matching Switches** (Java 21+): `SwitchBootstraps.typeSwitch`.

## 4. Decompiler Reconstruction Strategy
A decompiler must analyze the BSM target:
- If BSM is `LambdaMetafactory`: Reconstruct lambda expression or method reference syntax.
- If BSM is `StringConcatFactory`: Reconstruct string concatenation `+` operations.
- If BSM is unknown/custom (e.g. Groovy, Kotlin, Nashorn, or obfuscators): Render as explicit dynamic invocation or comment with BSM signature.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp` and `stackvm.cpp`:
- `BootstrapMethods` table parsing.
- Pattern matching on standard JDK bootstrap handlers to emit high-level expressions.

## 6. References
- Rose, J. *JSR 292: Supporting Dynamically Typed Languages on the Java Platform*. Oracle, 2011.
- JVMS §4.7.23: The BootstrapMethods Attribute.
