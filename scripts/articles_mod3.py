"""
articles_mod3.py - Module 3: Dataflow Analysis, SSA, & Stack Simulation (041 - 055)
"""

MODULE_NAME = "03_dataflow_ssa_stack"

ARTICLES = [
    {
        "file": "041_symbolic_jvm_stack_simulation.md",
        "title": "Symbolic JVM Stack Simulation and Value Propagation",
        "num": "041",
        "content": """# Article 041: Symbolic JVM Stack Simulation and Value Propagation

## 1. Executive Summary & Specification
Unlike register-based machines (e.g. ARM, x86, Dalvik), the JVM operand stack holds anonymous intermediate calculation results. A decompiler models this via **Symbolic Stack Simulation**:
- Instead of tracking concrete 32/64-bit integer values, the stack elements are pointers to symbolic expression AST nodes (`AstExpr`).
- When an opcode pushes a value, an expression node is synthesized and pushed.
- When an opcode pops operands, it consumes expressions from the stack and incorporates them as child nodes of a new compound expression.

## 2. Invariant Verification
For every instruction $i$ in basic block $B$, the stack height $h_i$ must be identical across all paths reaching $i$:
$$h_i = h_{entry} + \sum_{k=0}^{i-1} \Delta(k)$$
where $\Delta(k) = \text{pushes}(k) - \text{pops}(k)$.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- `StackVM::simulate_block()` executes symbolic simulation block-by-block.
- Synthesizes expressions for arithmetic, invocations, field lookups, and array accesses.

## 4. References
- Lindholm, T., et al. *The Java Virtual Machine Specification*. §3.1: The Operand Stack.
"""
    },
    {
        "file": "042_static_single_assignment_form.md",
        "title": "Static Single Assignment (SSA) Form and Cytron's Phi Placement",
        "num": "042",
        "content": """# Article 042: Static Single Assignment (SSA) Form and Cytron's Phi Placement

## 1. Executive Summary & Formal Theory
A program is in **Static Single Assignment (SSA)** form if:
1. Every variable is assigned a value exactly once.
2. Every use of a variable is dominated by its single definition.
At control flow merge points where divergent definitions meet, synthetic selection functions called $\phi$ (phi) functions are inserted:
$$x_3 = \phi(x_1, x_2)$$

## 2. Optimal $\phi$-Placement via Dominance Frontiers
Cytron et al. (1991) proved that $\phi$-nodes for variable $v$ are placed exactly at the **Iterated Dominance Frontier** $IDF(S_v)$, where $S_v$ is the set of basic blocks containing definitions of $v$.
- Dominance Frontier: $DF(X) = \{ Y \mid X \text{ dominates a predecessor of } Y \text{ but does not strictly dominate } Y \}$.

## 3. Decompiler Utility
SSA form isolates variable lifetimes, disentangling unrelated variables that were assigned to the same local variable slot by compiler register reuse.

## 4. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/ir.cpp` and `engine.cpp`:
- Constructs SSA representations for local variable slots, enabling unambiguous data dependency graphs.

## 5. References
- Cytron, R., et al. *Efficiently computing static single assignment form and the control dependence graph*. ACM TOPLAS, 1991.
"""
    },
    {
        "file": "043_phi_node_elimination_and_out_of_ssa.md",
        "title": "Out-of-SSA Translation, Phi Elimination, and Register Coalescing",
        "num": "043",
        "content": """# Article 043: Out-of-SSA Translation, Phi Elimination, and Register Coalescing

## 1. Executive Summary & Challenge
Before emitting Java source code, the decompiler must exit SSA form by eliminating all abstract $\phi$-nodes and consolidating SSA versions ($x_1, x_2, x_3$) into valid Java local variable identifiers ($x$).
The classic challenge is the **Parallel Copy Problem** (e.g. swap cycles $a \leftarrow b, b \leftarrow a$) which requires temporary variable introduction if not scheduled correctly.

## 2. Elimination Algorithm
1. Replace each $\phi$-function $x_0 = \phi(x_1, x_2, \dots, x_k)$ by placing a copy assignment $x_0 = x_i$ at the end of the $i$-th predecessor block.
2. Build an **Interference Graph** of variables whose lifetimes overlap.
3. Coalesce variables whose lifetimes do not interfere and share copy assignments, minimizing redundant assignments.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/ir.cpp` and `engine.cpp`:
- Resolves $\phi$-nodes and assigns cohesive variable identities to merged dataflow streams.

## 4. References
- Sreedhar, V. C., et al. *Translating Out of Static Single Assignment Form*. Static Analysis Symposium, 1999.
"""
    },
    {
        "file": "044_stack_underflow_and_inter_block_crossing.md",
        "title": "Stack Underflow Recovery and Inter-Block Stack Item Crossing",
        "num": "044",
        "content": """# Article 044: Stack Underflow Recovery and Inter-Block Stack Item Crossing

## 1. Executive Summary & Problem Formulation
While typical basic blocks begin and end with an empty operand stack, certain compiler optimizations and ternary operations leave values on the stack across block boundaries:
- A basic block consumes more operands than it pushed $\implies$ **Stack Underflow**.
- These missing operands were left on the stack by predecessor blocks.

## 2. Multi-Depth Recovery Algorithm
When block $B$ underflows by $k$ stack slots:
1. Examine all immediate predecessors $P_1, P_2, \dots, P_m$ of $B$.
2. For each predecessor $P$, trace its exit stack state.
3. If predecessor stacks match, propagate the top $k$ symbolic expressions into $B$'s entry stack.
4. If predecessors exhibit divergent stack depths or if a predecessor has multiple successors:
   - Synthesize a typed temporary variable `__tempX`.
   - In each predecessor, emit an assignment `__tempX = pop()`.
   - In block $B$, seed the initial stack with `__tempX`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Iterative loop resolving cascaded underflows up to depth 16.
- Preserves exit stack remainders as typed variables, preventing decompiler aborts.

## 4. References
- Proebsting, T. A. *Decompiling Java Bytecode: Problems and Solutions*. ACM SIGPLAN, 1997.
"""
    },
    {
        "file": "045_variable_liveness_and_interference_graphs.md",
        "title": "Variable Liveness Analysis and Interference Graph Coloring",
        "num": "045",
        "content": """# Article 045: Variable Liveness Analysis and Interference Graph Coloring

## 1. Executive Summary & Formal Definitions
A variable $v$ is **live** at point $p$ if there exists a path from $p$ to a use of $v$ along which $v$ is not redefined:
- **`Def(B)`**: Variables defined in block $B$ before any use.
- **`Use(B)`**: Variables used in block $B$ before any definition.
- **Dataflow Equations**:
  $$\text{In}(B) = \text{Use}(B) \cup (\text{Out}(B) \setminus \text{Def}(B))$$
  $$\text{Out}(B) = \bigcup_{S \in \text{succ}(B)} \text{In}(S)$$

## 2. Interference Graph & Coloring
Two variables **interfere** if their live ranges overlap at any point in the CFG.
- Vertices: Variables.
- Edges: Interference between variables.
- Goal: Assign colors (Java variable names and scopes) such that adjacent nodes receive different colors, while variables with identical types and non-overlapping ranges share the same color.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Computes variable liveness to prevent variable reuse bugs and ensure accurate scope boundaries.

## 4. References
- Chaitin, G. J. *Register allocation & spilling via graph coloring*. ACM SIGPLAN, 1982.
"""
    },
    {
        "file": "046_type_inference_and_unification.md",
        "title": "Type Inference Lattice and Hindley-Milner Type Unification",
        "num": "046",
        "content": """# Article 046: Type Inference Lattice and Hindley-Milner Type Unification

## 1. Executive Summary & Type Lattice
Bytecode lacks explicit type declarations for local variables. The decompiler reconstructs precise Java types using a **Type Lattice** $(L, \sqsubseteq, \sqcup, \sqcap)$:
```
                       Top (Unknown / Unconstrained)
                       /      |      \\
                 Object    Numeric    Array
                 /    \\     /   \\       |
            String   List  int  double Object[]
                 \\    /     \\   /       |
                      Bottom (Conflict / Error)
```

## 2. Constraint Propagation & Unification
1. Generate type constraints for every operation:
   - `iadd` $\implies$ operands $\sqsubseteq \text{int}$, result $\sqsubseteq \text{int}$.
   - `invokevirtual Foo.bar(String)` $\implies$ receiver $\sqsubseteq \text{Foo}$, arg $\sqsubseteq \text{String}$.
2. Propagate constraints iteratively until reaching a fixed point.
3. Solve for the **Least Upper Bound (LUB)** of all constraints for each local variable.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp` and `engine.cpp`:
- Solves type constraints to assign accurate Java types to reconstructed variables.

## 4. References
- Pierce, B. C. *Types and Programming Languages*. MIT Press, 2002.
- Hindley, J. R. *The Principal Type-Scheme of an Object in Combinatory Logic*. Transactions of the AMS, 1969.
"""
    },
    {
        "file": "047_primitive_type_disambiguation.md",
        "title": "Primitive Type Disambiguation: boolean, byte, char, short, and int",
        "num": "047",
        "content": """# Article 047: Primitive Type Disambiguation: boolean, byte, char, short, and int

## 1. Executive Summary & JVM 32-Bit Unification
In JVM bytecode, boolean, byte, char, short, and int are all represented internally as 32-bit integers and manipulated via the same `i*` opcodes (`iload`, `istore`, `iadd`, `ireturn`).
The decompiler must disambiguate the true high-level primitive type.

## 2. Disambiguation Heuristics
1. **Contextual Constraints**:
   - Storing into a typed field (`putfield boolField Z`) $\implies$ variable is `boolean`.
   - Passing into a method parameter (`foo(boolean)`) $\implies$ variable is `boolean`.
   - Array operations: `baload`/`bastore` on `boolean[]` vs `byte[]`.
2. **Value Range Analysis**:
   - If a variable is only ever assigned `0` or `1`, and used in conditional jumps (`ifeq`, `ifne`), classify as `boolean`.
   - If assigned character constants (e.g. `'A'`, `'\n'`), classify as `char`.
3. **Type Conversions**:
   - `i2b` $\implies$ `byte`.
   - `i2c` $\implies$ `char`.
   - `i2s` $\implies$ `short`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- Contextual heuristic solver for primitive types.

## 4. References
- JVMS §2.3: Primitive Types and Values.
"""
    },
    {
        "file": "048_reference_type_hierarchy_solving.md",
        "title": "Reference Type Hierarchy Solving and Lowest Common Ancestor (LCA)",
        "num": "048",
        "content": """# Article 048: Reference Type Hierarchy Solving and Lowest Common Ancestor (LCA)

## 1. Executive Summary & Specification
When two object references merge at a control flow join point or in a ternary expression (`cond ? objA : objB`), the resulting type must be the **Lowest Common Ancestor (LCA)** in the class hierarchy:
$$\text{Type}(R) = \text{LCA}(\text{Type}(A), \text{Type}(B))$$

## 2. Multiple Interface Invariant
Java supports single class inheritance but multiple interface inheritance:
- If `ClassA` implements `Comparable` and `Serializable`, and `ClassB` implements `Comparable` and `Cloneable`, their common class ancestor is `Object`, but their common interface ancestor is `Comparable`.
- The decompiler uses classpath hierarchy metadata to determine whether an interface cast is required.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/javatypes.cpp`:
- Class hierarchy traversal for common ancestor resolution.

## 4. References
- JLS §15.25: Conditional Operator `? :` - Type Resolution Rules.
"""
    },
    {
        "file": "049_copy_propagation_and_expression_inlining.md",
        "title": "Copy Propagation, Expression Inlining, and Side-Effect Safety",
        "num": "049",
        "content": """# Article 049: Copy Propagation, Expression Inlining, and Side-Effect Safety

## 1. Executive Summary & Goal
Bytecode creates numerous intermediate local variables. To produce readable Java source code, the decompiler must collapse these temporaries into complex expressions via **Expression Inlining**:
```java
// Raw Decompilation:
int t1 = a + b;
int t2 = c * d;
int result = t1 - t2;

// Inlined Expression:
int result = (a + b) - (c * d);
```

## 2. Inlining Invariants & Side-Effect Safety
An expression $E$ assigned to variable $v$ can be inlined at its use site $U$ if and only if:
1. $v$ is used exactly once.
2. Between the assignment to $v$ and the use site $U$:
   - No sub-expression in $E$ has its operands modified (e.g. no variables read by $E$ are overwritten).
   - If $E$ has side effects (method calls, volatile reads, field writes), no other side-effecting operations intervene that could alter evaluation order.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Single-use copy propagation and AST expression inlining pass.

## 4. References
- Aho, A. V., et al. *Compilers: Principles, Techniques, and Tools*. §8.5: Copy Propagation.
"""
    },
    {
        "file": "050_escaping_variables_and_scope_analysis.md",
        "title": "Escaping Variables, Scope Analysis, and Variable Hoisting",
        "num": "050",
        "content": """# Article 050: Escaping Variables, Scope Analysis, and Variable Hoisting

## 1. Executive Summary & Scope Rules
In Java, a variable declared inside a block (`if`, `while`, `try`) is lexically scoped to that block. If a variable is assigned inside an `if` block and read outside that block:
```java
if (cond) {
    String name = fetch(); // Escaping declaration!
}
print(name); // Compile Error in Java: name cannot be resolved
```
The declaration **escapes** the inner scope.

## 2. Hoisting Resolution Algorithm
1. Compute the set of all basic blocks where variable $v$ is defined or used: $S_v$.
2. Find the **Nearest Common Dominator (NCD)** block $B_{ncd}$ of all blocks in $S_v$.
3. **Hoist** the declaration of $v$ (`Type v;`) into the AST scope enclosing $B_{ncd}$.
4. Within the inner block, replace declaration statements with plain assignments (`v = fetch();`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- `hoist_escaping_locals()` and `hoist_all_escaping_to_root()` ensure that all escaping declarations are lifted to parent scopes, eliminating "escaping variable" compilation errors and decompiler aborts.

## 4. References
- JLS §6.3: Scope of a Declaration.
"""
    },
    {
        "file": "051_array_initialization_pattern_matching.md",
        "title": "Array Initialization Pattern Matching and Literal Synthesis",
        "num": "051",
        "content": """# Article 051: Array Initialization Pattern Matching and Literal Synthesis

## 1. Executive Summary & Compiler Lowering
An array initializer `int[] arr = { 1, 2, 3 };` is lowered by javac into:
```
newarray int [3]
dup
iconst_0
iconst_1
iastore
dup
iconst_1
iconst_2
iastore
dup
iconst_2
iconst_3
iastore
astore_1
```

## 2. Pattern Matching Algorithm
1. Identify `newarray` or `anewarray` instruction with constant size $N$.
2. Track subsequent instructions: look for repetitive sequences of `dup`, constant index $k \in [0, N-1]$, element expression $E_k$, and `*astore`.
3. If all indices $0 \le k < N$ are initialized sequentially without intervening branches:
   - Collapse the entire sequence into a single `ArrayInitExpr({ E_0, E_1, ..., E_{N-1} })`.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stackvm.cpp`:
- Recognizes consecutive array store patterns and constructs clean array literal expressions.

## 4. References
- JLS §10.6: Array Initializers.
"""
    },
    {
        "file": "052_compound_assignment_operator_recovery.md",
        "title": "Compound Assignment Operator Recovery (+=, -=, ++, --)",
        "num": "052",
        "content": """# Article 052: Compound Assignment Operator Recovery (+=, -=, ++, --)

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
"""
    },
    {
        "file": "053_ternary_operator_reconstruction.md",
        "title": "Ternary Operator (? :) CFG Diamond Reconstruction",
        "num": "053",
        "content": """# Article 053: Ternary Operator (? :) CFG Diamond Reconstruction

## 1. Executive Summary & Diamond CFG Pattern
The conditional expression `cond ? exprTrue : exprFalse` produces a characteristic diamond-shaped control flow subgraph:
```
           [ Condition Block ]
             /             \\
      (true)/               \\(false)
           ▼                 ▼
   [ True Block ]     [ False Block ]
   push exprTrue       push exprFalse
           \\                 /
            \\               /
             ▼             ▼
             [ Join Block ]
             consume result
```

## 2. Reconstruction Algorithm
1. Detect conditional branch at block $C$ targeting $B_{false}$, falling through to $B_{true}$.
2. Both $B_{true}$ and $B_{false}$ must jump unconditionally to common successor $J$.
3. At the end of $B_{true}$ and $B_{false}$, exactly 1 stack value is pushed, and neither block has other side effects.
4. Replace the entire diamond with a single `TernaryExpr(cond, exprTrue, exprFalse)` placed on the stack at entry to $J$.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/structure.cpp` and `stackvm.cpp`:
- Diamond detector collapses expressions into `TernaryExpr`.

## 4. References
- JLS §15.25: Conditional Operator `? :`.
"""
    },
    {
        "file": "054_def_use_and_use_def_chains.md",
        "title": "Def-Use and Use-Def Chains for Dependency Analysis",
        "num": "054",
        "content": """# Article 054: Def-Use and Use-Def Chains for Dependency Analysis

## 1. Executive Summary & Definitions
- **Def-Use (DU) Chain**: For a given variable definition $D$, the list of all instructions $U_1, U_2, \dots$ that can read the value written by $D$.
- **Use-Def (UD) Chain**: For a given variable use $U$, the list of all definitions $D_1, D_2, \dots$ that can reach $U$.

## 2. Decompiler Utility
- DU chains enable exact dead store elimination: if a definition has an empty DU chain and has no side effects, it can be pruned safely.
- UD chains verify whether a variable is guaranteed to be initialized before use, informing declaration placement (`Type x = val;` vs `Type x;`).

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/engine.cpp`:
- Tracks variable definitions and uses to optimize variable scopes.

## 4. References
- Kennedy, K. *Use-definition chains with applications to code optimization*. ACM SIGPLAN, 1978.
"""
    },
    {
        "file": "055_stack_map_frame_guided_decompilation.md",
        "title": "StackMapTable-Guided Decompilation and Type Synchronization",
        "num": "055",
        "content": """# Article 055: StackMapTable-Guided Decompilation and Type Synchronization

## 1. Executive Summary & Synergy
While traditional decompilers treat `StackMapTable` merely as a verifier artifact, modern decompilers exploit it as an authoritative oracle:
1. **Unambiguous Block Targets**: Every frame offset is guaranteed to be a valid basic block start.
2. **Authoritative Type Annotations**: Resolves primitive vs reference ambiguity at merge points.
3. **Stack Invariant Validation**: Verifies that the decompiler's symbolic stack height matches the JVM verifier's ground truth.

## 2. Synchronization Strategy
At each basic block boundary matching a `StackMapFrame`:
- Assert `stackvm.depth() == frame.stack_size()`.
- Reconcile local variable types with `frame.locals()`.
- Flag obfuscator-generated fake frames that violate reachability.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/verify.cpp` and `classfile.cpp`:
- Correlates stack simulation states with classfile stack map frames.

## 4. References
- JVMS §4.7.4: The StackMapTable Attribute.
"""
    }
]
