# Article 079: Cache-Friendly Bitset Algorithms for Liveness and Dominance

## 1. Executive Summary & Hardware Performance
Dataflow analysis requires performing millions of set union, intersection, and difference operations:
$$	ext{LiveIn}(B) = 	ext{Use}(B) \cup (	ext{LiveOut}(B) \setminus 	ext{Def}(B))$$
Representing sets as standard hash sets (`std::unordered_set<int>`) incurs pointer chasing and cache misses.

## 2. Word-Aligned Dense Bitsets
A flat array of 64-bit words (`uint64_t[]`):
- Union: `words[i] |= other.words[i]`
- Intersection: `words[i] &= other.words[i]`
- Difference: `words[i] &= ~other.words[i]`
Processes 64 variables in a single CPU cycle using SIMD vector instructions (AVX2/AVX-512).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Fast bitsets power liveness and dominance calculations.

## 4. References
- Lemire, D., et al. *Consistently faster and smaller compressed bitmaps with Roaring*. Software: Practice and Experience, 2016.
