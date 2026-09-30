# Article 058: Try-With-Resources (Java 7+) Desugaring and Throwable.addSuppressed

## 1. Executive Summary & Bytecode Complexity
Java 7's `try (Resource r = ...)` statement ensures that `r.close()` is invoked even if exceptions occur. The compiler generates up to 20 bytecodes per resource, including:
- Primary exception capture.
- Null check on resource before calling `.close()`.
- Secondary `try-catch` around `.close()`.
- Call to `primaryException.addSuppressed(closeException)` if both fail.

## 2. Reconstruction Algorithm
1. Detect synthetic `catch (Throwable t)` block that calls `Throwable.addSuppressed()`.
2. Trace the resource allocation immediately preceding the try block.
3. Verify that the finally block contains `if (res != null) res.close()`.
4. Collapse the entire pattern into `try (Resource res = initExpr) { ... }`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/catchclean.cpp`:
- Recognizes try-with-resources desugaring patterns and reconstructs clean resource declarations in the try header.

## 4. References
- JLS §14.20.3: `try`-with-resources.
- JEP 110: *try-with-resources*. OpenJDK, 2011.
