# NanoDecompiler Comprehensive Knowledge Base: 100 Monographs on C++, Decompilers, and JVM Internals

> **For Developers and Autonomous Agents**: This repository contains 100 authoritative, deeply technical monographs covering the full theoretical and practical spectrum of modern C++ decompilation, JVM bytecode specifications, graph structuring, dataflow analysis, and reverse engineering.

## Table of Contents

### Module 1: JVM Architecture & Bytecode Specification (001 - 020)

- **[001] [JVM Execution Model, Stack Frames, and Memory Areas](01_jvm_bytecode_spec/001_jvm_execution_model_and_memory_areas.md)**
- **[002] [ClassFile Binary Structure and Constant Pool Mechanics](01_jvm_bytecode_spec/002_class_file_format_and_constant_pool.md)**
- **[003] [JVM Type Descriptors, Extended Signatures, and Generic Erasure](01_jvm_bytecode_spec/003_jvm_type_descriptors_and_signatures.md)**
- **[004] [JVM Instruction Set Categorization and Stack Semantics](01_jvm_bytecode_spec/004_jvm_instruction_set_classification.md)**
- **[005] [StackMapTable Verification Algorithm and Type Reconstruction](01_jvm_bytecode_spec/005_stack_map_table_and_type_verification.md)**
- **[006] [Method Invocation Bytecodes: Resolution, Dynamic Dispatch, and Special Cases](01_jvm_bytecode_spec/006_method_invocation_bytecodes.md)**
- **[007] [The invokedynamic Architecture and Bootstrap Method Resolution](01_jvm_bytecode_spec/007_invokedynamic_and_bootstrap_methods.md)**
- **[008] [Lambda Expression Desugaring and LambdaMetafactory Reconstruction](01_jvm_bytecode_spec/008_lambda_desugaring_and_metafactory.md)**
- **[009] [String Concatenation via StringConcatFactory (JEP 280) and Desugaring](01_jvm_bytecode_spec/009_string_concat_factory_indy.md)**
- **[010] [Exception Tables, Half-Open PC Ranges, and Nested Handlers](01_jvm_bytecode_spec/010_exception_tables_and_handler_ranges.md)**
- **[011] [InnerClasses Attribute, EnclosingMethod, and Synthetic Access Bridges](01_jvm_bytecode_spec/011_inner_classes_and_enclosing_method.md)**
- **[012] [Nest-Based Access Control (JEP 181, Java 11+) and Attribute Mechanics](01_jvm_bytecode_spec/012_nest_based_access_control.md)**
- **[013] [Sealed Classes and the PermittedSubclasses Attribute (Java 17+)](01_jvm_bytecode_spec/013_sealed_classes_and_permitted_subclasses.md)**
- **[014] [Record Classes, RecordComponentInfo, and Canonical Constructor Reconstruction](01_jvm_bytecode_spec/014_record_classes_and_attributes.md)**
- **[015] [Type Erasure, Bridge Methods (ACC_BRIDGE), and Covariant Overrides](01_jvm_bytecode_spec/015_generic_attributes_and_type_erasure.md)**
- **[016] [Runtime Annotations, Parameter Metadata, and Type Annotations](01_jvm_bytecode_spec/016_annotations_and_parameter_attributes.md)**
- **[017] [Java Platform Module System (JPMS) and module-info.class Bytecode](01_jvm_bytecode_spec/017_module_info_and_module_attributes.md)**
- **[018] [Debug Attributes: LineNumberTable, LocalVariableTable, and Scoping](01_jvm_bytecode_spec/018_line_number_table_and_local_variable_table.md)**
- **[019] [The wide Modifier and Legacy jsr/ret Subroutine Inlining](01_jvm_bytecode_spec/019_wide_and_jsr_ret_legacy_bytecodes.md)**
- **[020] [Class Loading, Verification, Preparation, and Initialization Semantics](01_jvm_bytecode_spec/020_class_loading_and_linking_semantics.md)**

### Module 2: Decompilation Theory & Control Flow Structuring (021 - 040)

- **[021] [Foundations of Decompilation: Historical Perspectives and Modern Pipelines](02_decompilation_theory/021_foundations_of_decompilation.md)**
- **[022] [Control Flow Graph Construction and Basic Block Identification](02_decompilation_theory/022_control_flow_graph_construction.md)**
- **[023] [Dominator Trees, Post-Dominance, and the Lengauer-Tarjan Algorithm](02_decompilation_theory/023_dominator_trees_and_post_dominance.md)**
- **[024] [Loop Detection, Natural Loops, and Header Identification](02_decompilation_theory/024_loop_detection_and_natural_loops.md)**
- **[025] [The Relooper Algorithm: WebAssembly Origins and Java CFG Structuring](02_decompilation_theory/025_relooper_algorithm_and_extensions.md)**
- **[026] [Interval Analysis, T1-T2 Transformations, and Limit Flow Graphs](02_decompilation_theory/026_interval_analysis_and_t1_t2_transformations.md)**
- **[027] [Irreducible Control Flow Graphs: Node Splitting and State Variable Emulation](02_decompilation_theory/027_irreducible_control_flow_graphs.md)**
- **[028] [Structuring If-Then and If-Then-Else Conditional Branches](02_decompilation_theory/028_structuring_if_then_and_if_then_else.md)**
- **[029] [Structuring Short-Circuit Boolean Expressions (&&, ||) and Condition Trees](02_decompilation_theory/029_structuring_short_circuit_boolean_expressions.md)**
- **[030] [Loop Structuring: while, do-while, and for Loop Recovery](02_decompilation_theory/030_loop_structuring_while_do_while_for.md)**
- **[031] [Labeled break and continue Target Resolution in Nested Constructs](02_decompilation_theory/031_break_and_continue_resolution.md)**
- **[032] [Switch Statement Reconstruction: tableswitch vs lookupswitch](02_decompilation_theory/032_switch_statement_reconstruction.md)**
- **[033] [Structuring Exception Handlers, Protected Ranges, and Multi-Catch Blocks](02_decompilation_theory/033_structuring_exception_handlers.md)**
- **[034] [Finally Block Reconstruction and Duplicate Bytecode De-duplication](02_decompilation_theory/034_finally_block_reconstruction.md)**
- **[035] [Synchronized Blocks, Monitor Invariants, and Synthetic Handlers](02_decompilation_theory/035_synchronized_blocks_and_monitors.md)**
- **[036] [Unreachable and Dead Code Elimination in Decompiler AST](02_decompilation_theory/036_unreachable_and_dead_code_elimination.md)**
- **[037] [Abstract Syntax Tree (AST) Hierarchy for Decompilers](02_decompilation_theory/037_ast_representation_for_decompilers.md)**
- **[038] [AST Simplification, Canonicalization Passes, and Redundant Cast Elimination](02_decompilation_theory/038_ast_simplification_and_canonicalization.md)**
- **[039] [Control Flow Flattening Recovery and Dispatcher Loop Unflattening](02_decompilation_theory/039_control_flow_flattening_recovery.md)**
- **[040] [Decompiler Correctness, Semantic Preservation, and Fuzzing Strategies](02_decompilation_theory/040_decompiler_correctness_and_verification.md)**

### Module 3: Dataflow Analysis, SSA, & Stack Simulation (041 - 055)

- **[041] [Symbolic JVM Stack Simulation and Value Propagation](03_dataflow_ssa_stack/041_symbolic_jvm_stack_simulation.md)**
- **[042] [Static Single Assignment (SSA) Form and Cytron's Phi Placement](03_dataflow_ssa_stack/042_static_single_assignment_form.md)**
- **[043] [Out-of-SSA Translation, Phi Elimination, and Register Coalescing](03_dataflow_ssa_stack/043_phi_node_elimination_and_out_of_ssa.md)**
- **[044] [Stack Underflow Recovery and Inter-Block Stack Item Crossing](03_dataflow_ssa_stack/044_stack_underflow_and_inter_block_crossing.md)**
- **[045] [Variable Liveness Analysis and Interference Graph Coloring](03_dataflow_ssa_stack/045_variable_liveness_and_interference_graphs.md)**
- **[046] [Type Inference Lattice and Hindley-Milner Type Unification](03_dataflow_ssa_stack/046_type_inference_and_unification.md)**
- **[047] [Primitive Type Disambiguation: boolean, byte, char, short, and int](03_dataflow_ssa_stack/047_primitive_type_disambiguation.md)**
- **[048] [Reference Type Hierarchy Solving and Lowest Common Ancestor (LCA)](03_dataflow_ssa_stack/048_reference_type_hierarchy_solving.md)**
- **[049] [Copy Propagation, Expression Inlining, and Side-Effect Safety](03_dataflow_ssa_stack/049_copy_propagation_and_expression_inlining.md)**
- **[050] [Escaping Variables, Scope Analysis, and Variable Hoisting](03_dataflow_ssa_stack/050_escaping_variables_and_scope_analysis.md)**
- **[051] [Array Initialization Pattern Matching and Literal Synthesis](03_dataflow_ssa_stack/051_array_initialization_pattern_matching.md)**
- **[052] [Compound Assignment Operator Recovery (+=, -=, ++, --)](03_dataflow_ssa_stack/052_compound_assignment_operator_recovery.md)**
- **[053] [Ternary Operator (? :) CFG Diamond Reconstruction](03_dataflow_ssa_stack/053_ternary_operator_reconstruction.md)**
- **[054] [Def-Use and Use-Def Chains for Dependency Analysis](03_dataflow_ssa_stack/054_def_use_and_use_def_chains.md)**
- **[055] [StackMapTable-Guided Decompilation and Type Synchronization](03_dataflow_ssa_stack/055_stack_map_frame_guided_decompilation.md)**

### Module 4: High-Level Java Idioms & Syntactic Sugar (056 - 070)

- **[056] [String Switch Compilation and Two-Tier Hash Desugaring](04_syntactic_sugar_patterns/056_string_switch_compilation_and_recovery.md)**
- **[057] [Enum Switch and Synthetic Mapping Class ($SwitchMap$)](04_syntactic_sugar_patterns/057_enum_switch_and_synthetic_mapping.md)**
- **[058] [Try-With-Resources (Java 7+) Desugaring and Throwable.addSuppressed](04_syntactic_sugar_patterns/058_try_with_resources_desugaring.md)**
- **[059] [Assert Statement Reconstruction and $assertionsDisabled Flag](04_syntactic_sugar_patterns/059_assert_statement_reconstruction.md)**
- **[060] [Anonymous Inner Classes vs Lambdas Disambiguation](04_syntactic_sugar_patterns/060_anonymous_classes_vs_lambdas.md)**
- **[061] [Method Reference Reconstruction: Static, Bound, Unbound, and Constructor](04_syntactic_sugar_patterns/061_method_reference_reconstruction.md)**
- **[062] [Enhanced For-Loop (foreach) over Arrays Reconstruction](04_syntactic_sugar_patterns/062_enhanced_for_loop_arrays.md)**
- **[063] [Enhanced For-Loop (foreach) over Iterables (Collection/List)](04_syntactic_sugar_patterns/063_enhanced_for_loop_iterables.md)**
- **[064] [StringBuilder Concatenation Patterns (Java 8 and earlier)](04_syntactic_sugar_patterns/064_string_builder_concatenation_patterns.md)**
- **[065] [Auto-Boxing and Unboxing Primitive Conversions](04_syntactic_sugar_patterns/065_boxing_and_unboxing_primitives.md)**
- **[066] [Constructor Chaining: this() and super() Placement](04_syntactic_sugar_patterns/066_constructor_chaining_this_and_super.md)**
- **[067] [Varargs Method Calls and Method Signatures (ACC_VARARGS)](04_syntactic_sugar_patterns/067_varargs_method_calls_and_definitions.md)**
- **[068] [Pattern Matching for instanceof and Switch (Java 16 - 21)](04_syntactic_sugar_patterns/068_pattern_matching_instanceof_and_switch.md)**
- **[069] [Record Canonical vs Compact Constructors](04_syntactic_sugar_patterns/069_record_canonical_and_compact_constructors.md)**
- **[070] [Kotlin Bytecode Patterns in Java Decompilers (@Metadata, Intrinsics)](04_syntactic_sugar_patterns/070_kotlin_bytecode_patterns_in_java_decompilers.md)**

### Module 5: Advanced C++ Engine Architecture & Memory Performance (071 - 085)

- **[071] [AST Design Patterns in Modern C++: Polymorphic vs Variant-Based Hierarchies](05_cpp_engine_architecture/071_ast_design_patterns_in_modern_cpp.md)**
- **[072] [Memory Arena and Monotonic Allocators for Decompiler Passes](05_cpp_engine_architecture/072_memory_arena_and_monotonic_allocators.md)**
- **[073] [String Interning and Symbol Tables for Constant Pool Symbols](05_cpp_engine_architecture/073_string_interning_and_symbol_tables.md)**
- **[074] [Visitor Pattern and Double Dispatch vs Modern C++ Alternatives](05_cpp_engine_architecture/074_visitor_pattern_and_double_dispatch.md)**
- **[075] [Graph Data Structures for CFG Representation: Adjacency and Bitsets](05_cpp_engine_architecture/075_graph_data_structures_for_cfg.md)**
- **[076] [Zero-Copy Bytecode Parsing and Memory-Mapped Class Files](05_cpp_engine_architecture/076_zero_copy_bytecode_parsing.md)**
- **[077] [Exception Handling Strategies in C++: Result Types vs Exceptions](05_cpp_engine_architecture/077_exception_handling_strategies_in_cpp.md)**
- **[078] [Multi-Threaded Parallel Decompilation Architecture](05_cpp_engine_architecture/078_multi_threaded_parallel_decompilation.md)**
- **[079] [Cache-Friendly Bitset Algorithms for Liveness and Dominance](05_cpp_engine_architecture/079_cache_friendly_bitset_algorithms.md)**
- **[080] [Fast Bytecode Pattern Matchers in C++ (DFA and Boyer-Moore)](05_cpp_engine_architecture/080_fast_regex_and_pattern_matchers_cpp.md)**
- **[081] [Diagnostics and Error Recovery Architecture: Graceful Fallback](05_cpp_engine_architecture/081_diagnostics_and_error_recovery_architecture.md)**
- **[082] [Cross-Platform Abstraction Layers: Windows, Linux, and macOS](05_cpp_engine_architecture/082_cross_platform_abstraction_layers.md)**
- **[083] [Binary Size and Compilation Speed Optimization: LTO and Header Minimization](05_cpp_engine_architecture/083_binary_size_and_compilation_speed_optimization.md)**
- **[084] [Sanitizers, Fuzzing, and Valgrind in C++ Decompiler Engine](05_cpp_engine_architecture/084_sanitizers_fuzzing_and_valgrind_in_engine.md)**
- **[085] [Profiling and Benchmarking C++ Decompilers: VTune, Tracy, and Throughput Metrics](05_cpp_engine_architecture/085_profiling_and_benchmarking_cpp_decompilers.md)**

### Module 6: Real-World Java Decompiler Case Studies (086 - 095)

- **[086] [Procyon Decompiler Architecture: AST Pipeline and C# Inspiration](06_decompiler_case_studies/086_procyon_decompiler_architecture.md)**
- **[087] [CFR Decompiler: Pattern-Driven Structuring and Modern Java Evolution](06_decompiler_case_studies/087_cfr_decompiler_and_expression_structuring.md)**
- **[088] [Fernflower / IntelliJ IDEA Decompiler: Statement Graph Reduction](06_decompiler_case_studies/088_fernflower_intellij_decompiler_pipeline.md)**
- **[089] [Krakatau Decompiler: Strict Verification, Type Solvers, and Obfuscation Robustness](06_decompiler_case_studies/089_krakatau_rigorous_type_solver_and_ssa.md)**
- **[090] [Jadx Decompiler: Dalvik Register Allocation and Android Dex Structuring](06_decompiler_case_studies/090_jadx_dex_to_java_decompiler_engine.md)**
- **[091] [Dava (Soot Framework): Structured Encapsulation Trees (SET)](06_decompiler_case_studies/091_dava_soot_structured_flow_analysis.md)**
- **[092] [JD-Core and JD-GUI: Fast Linear-Scan Decompilation](06_decompiler_case_studies/092_jd_gui_and_jd_core_fast_structurer.md)**
- **[093] [NSA Ghidra JVM SLEIGH Model and P-Code Decompilation](06_decompiler_case_studies/093_ghidra_jvm_pcode_and_sleigh_model.md)**
- **[094] [Comparative Benchmark of World-Class Java Decompilers](06_decompiler_case_studies/094_comparative_benchmark_of_java_decompilers.md)**
- **[095] [Evolution of JVM Bytecode Over 30 Years (Java 1.0 to Java 23)](06_decompiler_case_studies/095_evolution_of_java_bytecode_over_30_years.md)**

### Module 7: Anti-Decompilation & Obfuscation Countermeasures (096 - 100)

- **[096] [Opaque Predicates and Dead Branch Pruning](07_obfuscation_countermeasures/096_opaque_predicates_and_dead_branches.md)**
- **[097] [Control Flow Flattening De-obfuscation Algorithms](07_obfuscation_countermeasures/097_control_flow_flattening_unflattening.md)**
- **[098] [String and Constant Encryption Solvers: Static and Dynamic Emulation](07_obfuscation_countermeasures/098_string_and_constant_encryption_solvers.md)**
- **[099] [Exceptional Control Flow Obfuscation: Fake Try-Catch Ranges](07_obfuscation_countermeasures/099_exceptional_control_flow_obfuscation.md)**
- **[100] [Identifier Restoration and Context-Sensitive Heuristic Renaming](07_obfuscation_countermeasures/100_identifier_restoration_and_heuristic_renaming.md)**

