# Article 052: Compound Assignment Operator Recovery (+=, -=, ++, --)

## 1. Executive Summary & Bytecode Mechanics
Java compound assignments (`x += 5;`, `x++;`) are compiled into explicit loads and arithmetic operations:
- For local integer increments: `iinc <var> <const>`.
  - `iinc x 1` $\implies$ `x++` or `++x`.
  - `iinc x -1` $\implies$ `x--` or `--x`.
  - `iinc x 5` $\implies$ `x += 5`.
- For general types and fields:
  ```
  aload_0 (this)
  dup
  getfield f
  iconst_5
  iadd
  putfield f
  ```
  $\implies$ `this.f += 5;`.

## 2. Pre- vs Post-Increment Disambiguation
- **Post-Increment (`x++`)**: Value of `x` is pushed onto stack *before* `iinc` executes.
- **Pre-Increment (`++x`)**: `iinc` executes *before* value of `x` is pushed onto stack.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Reconstructs compound assignments and increment/decrement operators.

## 4. References
- JLS §15.26.2: Compound Assignment Operators.
- JLS §15.14.2, §15.15.1: Postfix and Prefix Increment Operators.
