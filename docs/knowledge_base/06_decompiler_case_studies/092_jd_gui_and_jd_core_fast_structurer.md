# Article 092: JD-Core and JD-GUI: Fast Linear-Scan Decompilation

## 1. Executive Summary & Legacy Impact
Created by Emmanuel Dupuy, **JD-Core** was for many years the most popular Java decompiler, integrated into Eclipse and standalone JD-GUI.

## 2. Linear-Scan Structuring
Unlike modern graph-reduction engines, JD-Core uses a fast linear scan of the bytecode stream with local pattern matching:
- *Pros*: Extremely fast; near-instantaneous decompilation of entire libraries.
- *Cons*: Fails on complex nested loops, throws internal exceptions on unusual compiler patterns, and lacks support for modern Java 9+ features.

## 3. References
- Dupuy, E. *Java Decompiler (JD-GUI / JD-Core)*. java-decompiler.github.io, 2008-2020.
