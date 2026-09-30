# Article 073: String Interning and Symbol Tables for Constant Pool Symbols

## 1. Executive Summary & Hash Consing
Large Java codebases contain hundreds of thousands of redundant identifier strings (`java/lang/String`, `java/lang/Object`, `toString`, `equals`, `getId`).
**String Interning** (Hash Consing) ensures that exactly one immutable instance of each unique string is stored in memory.

## 2. Implementation Mechanics
```cpp
class SymbolTable {
    std::unordered_set<std::string> pool;
public:
    std::string_view intern(std::string_view s) {
        auto it = pool.find(s);
        if (it != pool.end()) return *it;
        return *pool.emplace(s).first;
    }
};
```
- Strings are represented throughout the AST as lightweight `std::string_view` or integer symbol IDs.
- String equality comparisons reduce from $O(N)$ character scans to $O(1)$ pointer comparisons.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/classfile.cpp`:
- Constant pool UTF-8 symbols are indexed and deduplicated across the entire decompiler lifecycle.

## 4. References
- Ershov, A. P. *On programming of arithmetic operations*. Communications of the ACM, 1958 (First mention of hash consing).
