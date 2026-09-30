# Article 098: String and Constant Encryption Solvers: Static and Dynamic Emulation

## 1. Executive Summary & Encryption Schemes
Java bytecode stores strings in plain UTF-8 in the constant pool. Obfuscators (ProGuard, Allatori, Zelix KlassMaster) replace plain strings with:
- Encrypted byte arrays or ciphertext strings (`"\u001f\u002a..."`).
- Calls to decryption routines at runtime: `decrypt("cipher", key)`.

## 2. Decryption Engine Architecture
NanoDecompiler integrates a dedicated string decryption subsystem:
1. **Pattern Recognition**: Identify decryption method signatures (static methods accepting string/byte[] and returning String).
2. **Local VM Emulation**: Execute the decryption method in a lightweight bytecode interpreter sandbox using the constants embedded in the class.
3. **AST Inlining**: Replace the method call in the AST with the resulting plaintext string literal.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/str_decrypt.cpp`:
- Automated decryption for XOR, AES-128, and custom bitwise string decryptor routines.

## 4. References
- Ceccato, M., et al. *Deobfuscation of String Encryption in Android Applications*. IEEE SANER, 2018.
