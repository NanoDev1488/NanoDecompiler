# Article 024: Loop Detection, Natural Loops, and Header Identification

## 1. Executive Summary & Formal Definitions
A **Loop** in a CFG is a subgraph that is strongly connected (every node in the loop can reach every other node in the loop) with a single entry point called the **Header**.
- **Back-Edge**: A directed edge $n 	o h$ where $h 	ext{ dom } n$.
- **Natural Loop**: Given a back-edge $n 	o h$, the natural loop of $n 	o h$ is defined as:
$$	ext{Loop}(n 	o h) = \{ d \in V \mid d 	ext{ can reach } n 	ext{ without passing through } h \} \cup \{ h \}$$

## 2. Loop Nesting & Disjointness Properties
1. Any two natural loops are either:
   - Disjoint (share no nodes except perhaps an exit target).
   - Nested (one loop is entirely contained within the other).
   - Sharing the same header (loops with multiple latching back-edges).
2. The decompiler processes loops from innermost to outermost, collapsing recognized inner loops into abstract loop compound statements before structuring outer loops.

## 3. Loop Classification for Java
- **Pre-test Loop (`while`)**: Condition is checked at header $h$. If false, exits loop.
- **Post-test Loop (`do-while`)**: Body executes unconditionally first; condition is evaluated at latch block $n$.
- **Endless Loop (`while (true)`)**: Loop with no natural conditional exit edge; exited solely via `break`, `return`, or `throw`.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp`:
- Detects back-edges via dominance check `dom.is_dom(h, n)`.
- Reconstructs loop scopes and distinguishes `while` from `do-while` based on condition block PC.

## 5. References
- Tarjan, R. E. *Testing flow graph reducibility*. Journal of Computer and System Sciences, 1974.
