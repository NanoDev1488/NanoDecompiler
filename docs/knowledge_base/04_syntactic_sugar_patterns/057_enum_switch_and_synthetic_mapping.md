# Article 057: Enum Switch and Synthetic Mapping Class ($SwitchMap$)

## 1. Executive Summary & Binary Pattern
When switching over an `enum`, javac generates an anonymous synthetic nested class (e.g. `EnclosingClass$1`) containing a static integer array:
```java
static final int[] $SwitchMap$com$example$Color = new int[Color.values().length];
static {
    try { $SwitchMap$...[Color.RED.ordinal()] = 1; } catch (NoSuchFieldError e) {}
    try { $SwitchMap$...[Color.BLUE.ordinal()] = 2; } catch (NoSuchFieldError e) {}
}
```
The switch statement in the user method then accesses `$SwitchMap$...[color.ordinal()]` to produce dense integers for `tableswitch`.

## 2. Decompilation Reconstruction Algorithm
1. Detect array access to static field whose name matches `$SwitchMap$*`.
2. Parse the synthetic class's `<clinit>` to extract the mapping from ordinal index to enum constant name.
3. Replace integer case labels in the switch statement with their corresponding enum constant identifiers (`case RED:`, `case BLUE:`).
4. Mark the synthetic `$SwitchMap$` class as hidden so it is not rendered in the decompiled source.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/switchmap.cpp` and `render_class.cpp`:
- Resolves enum switch synthetic arrays to symbolic enum names.

## 4. References
- JLS §14.11: The `switch` Statement - Enum Switches.
