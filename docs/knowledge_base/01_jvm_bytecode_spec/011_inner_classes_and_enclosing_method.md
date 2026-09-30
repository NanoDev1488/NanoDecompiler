# Article 011: InnerClasses Attribute, EnclosingMethod, and Synthetic Access Bridges

## 1. Executive Summary & Specification
Java's high-level concept of nested, inner, local, and anonymous classes is represented in the class file via the `InnerClasses` (JVMS §4.7.6) and `EnclosingMethod` (JVMS §4.7.7) attributes.

## 2. InnerClasses Attribute Record
```
inner_classes_table {
    u2 inner_class_info_index;   // CP index of inner class
    u2 outer_class_info_index;   // CP index of outer class (0 if anonymous/local)
    u2 inner_name_index;         // Utf8 simple name (0 if anonymous)
    u2 inner_class_access_flags; // Access flags (static, final, etc.)
}
```

## 3. Classic javac Desugaring (Java 1.1 - 10)
- **Outer Class Reference**: Non-static inner classes receive a synthetic final field `final OuterClass this$0;` passed as the first parameter in all constructors.
- **Access Bridge Methods**: If an inner class accesses a private field or method of the outer class, the compiler generates package-private synthetic methods `static ReturnType access$000(OuterClass x, ...)`.

## 4. Decompilation Reconstruction Strategy
1. Match `this$0` field assignments in constructor: remove parameter from constructor signature in AST.
2. Intercept calls to `access$XXX`: replace with direct member access on the target object.
3. If `inner_name_index == 0`, class is **anonymous inner class** -> emit as `new SuperType() { ... }`.

## 5. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/render_class.cpp`:
- Hides synthetic `this$0` fields and `access$` methods during class rendering.

## 6. References
- JVMS §4.7.6: The InnerClasses Attribute.
