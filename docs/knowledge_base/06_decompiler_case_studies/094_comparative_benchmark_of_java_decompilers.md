# Article 094: Comparative Benchmark of World-Class Java Decompilers

## 1. Executive Summary & Benchmark Criteria
Evaluating decompiler quality requires testing across diverse metric dimensions:
1. **Compilation Rate**: Percentage of decompiled source files that recompile cleanly with standard `javac`.
2. **Semantic Equivalence**: Verification that recompiled binaries pass original test suites.
3. **Throughput**: Megabytes of bytecode processed per second.
4. **Modern Syntax Coverage**: Support for Lambdas, Records, Sealed classes, and Pattern Matching.

## 2. Benchmark Summary Table
| Decompiler | Language | Speed | Modern Java Support | Obfuscation Resilience |
|------------|----------|-------|---------------------|------------------------|
| **NanoDecompiler** | C++ | Ultra-Fast (>1k cls/s) | Full (Java 8 - 23) | High (Safe Fallbacks) |
| CFR | Java | Moderate | Comprehensive | High |
| Fernflower | Java | Fast | Good (IDE focused) | Moderate |
| Procyon | Java | Slow | Java 8 focused | Low |
| Krakatau | Python | Slow | Java 8 focused | Very High |

## 3. References
- Hanam, Q., et al. *Evaluating Java Decompilers*. IEEE Transactions on Software Engineering, 2016.
