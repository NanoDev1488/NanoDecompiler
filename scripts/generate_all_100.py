"""
generate_all_100.py
Main driver to generate all 100 technical knowledge base articles, INDEX, and README.
"""

import os
import sys

from articles_mod1 import ARTICLES as MOD1, MODULE_NAME as MOD1_DIR
from articles_mod2 import ARTICLES as MOD2, MODULE_NAME as MOD2_DIR
from articles_mod3 import ARTICLES as MOD3, MODULE_NAME as MOD3_DIR
from articles_mod4 import ARTICLES as MOD4, MODULE_NAME as MOD4_DIR
from articles_mod5 import ARTICLES as MOD5, MODULE_NAME as MOD5_DIR
from articles_mod6 import ARTICLES as MOD6, MODULE_NAME as MOD6_DIR
from articles_mod7 import ARTICLES as MOD7, MODULE_NAME as MOD7_DIR

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", "docs", "knowledge_base"))

MODULES = [
    (MOD1_DIR, "Module 1: JVM Architecture & Bytecode Specification (001 - 020)", MOD1),
    (MOD2_DIR, "Module 2: Decompilation Theory & Control Flow Structuring (021 - 040)", MOD2),
    (MOD3_DIR, "Module 3: Dataflow Analysis, SSA, & Stack Simulation (041 - 055)", MOD3),
    (MOD4_DIR, "Module 4: High-Level Java Idioms & Syntactic Sugar (056 - 070)", MOD4),
    (MOD5_DIR, "Module 5: Advanced C++ Engine Architecture & Memory Performance (071 - 085)", MOD5),
    (MOD6_DIR, "Module 6: Real-World Java Decompiler Case Studies (086 - 095)", MOD6),
    (MOD7_DIR, "Module 7: Anti-Decompilation & Obfuscation Countermeasures (096 - 100)", MOD7),
]

def main():
    os.makedirs(BASE_DIR, exist_ok=True)
    total_written = 0
    
    index_lines = [
        "# NanoDecompiler Comprehensive Knowledge Base: 100 Monographs on C++, Decompilers, and JVM Internals",
        "",
        "> **For Developers and Autonomous Agents**: This repository contains 100 authoritative, deeply technical monographs covering the full theoretical and practical spectrum of modern C++ decompilation, JVM bytecode specifications, graph structuring, dataflow analysis, and reverse engineering.",
        "",
        "## Table of Contents",
        ""
    ]
    
    for mod_dir, mod_title, articles in MODULES:
        target_dir = os.path.join(BASE_DIR, mod_dir)
        os.makedirs(target_dir, exist_ok=True)
        
        index_lines.append(f"### {mod_title}")
        index_lines.append("")
        
        for art in articles:
            filepath = os.path.join(target_dir, art["file"])
            with open(filepath, "w", encoding="utf-8") as f:
                f.write(art["content"].strip() + "\n")
            total_written += 1
            
            rel_link = f"{mod_dir}/{art['file']}"
            index_lines.append(f"- **[{art['num']}] [{art['title']}]({rel_link})**")
            
        index_lines.append("")
        
    index_content = "\n".join(index_lines) + "\n"
    index_file = os.path.join(BASE_DIR, "INDEX_100_ARTICLES.md")
    with open(index_file, "w", encoding="utf-8") as f:
        f.write(index_content)
        
    readme_content = f"""# NanoDecompiler Engineering Knowledge Base (100 Technical Monographs)

Welcome to the **NanoDecompiler Knowledge Base**, an exhaustive corpus of **100 deep technical monographs** covering compiler engineering, JVM bytecode internals, control flow structuring, dataflow lattice solving, high-performance modern C++ architecture, and deobfuscation algorithms.

## Quick Links
- **[Full Index of 100 Articles](INDEX_100_ARTICLES.md)**
- **Total Articles Generated**: {total_written}
- **Language & Standards Covered**: C++20/C++23, Java SE 7 through 23, JVM Spec (JVMS 23), Dalvik DEX.

## Overview of Modules
1. **[01_jvm_bytecode_spec/](01_jvm_bytecode_spec/)** (Articles 001 - 020): Execution model, ClassFile format, Constant Pool tags, StackMapTable, InvokeDynamic, Lambdas, StringConcatFactory, Sealed classes, Records, JPMS modules.
2. **[02_decompilation_theory/](02_decompilation_theory/)** (Articles 021 - 040): CFG construction, Dominator trees, Natural loops, Relooper algorithm, T1/T2 reductions, Irreducible flow graphs, If/Else structuring, Short-circuit logic, Switch reconstruction, Exception tables, Synchronized blocks.
3. **[03_dataflow_ssa_stack/](03_dataflow_ssa_stack/)** (Articles 041 - 055): Symbolic stack simulation, SSA form, Out-of-SSA, Stack underflow recovery, Variable liveness & interference graphs, Type inference lattice, Primitive disambiguation, LCA reference resolution, Copy propagation, Escaping variable hoisting.
4. **[04_syntactic_sugar_patterns/](04_syntactic_sugar_patterns/)** (Articles 056 - 070): String switch two-tier hash desugaring, Enum switch synthetic inner classes, Try-with-resources, Assert statements, Method references, Enhanced for-loops (arrays and iterables), Autoboxing, Pattern matching, Kotlin bytecode patterns.
5. **[05_cpp_engine_architecture/](05_cpp_engine_architecture/)** (Articles 071 - 085): Modern C++ AST node design, Memory arenas, String interning, Visitor double-dispatch, Dense indexed CFG bitsets, Zero-copy memory mapping, Safe error recovery, Parallel thread pools, ASan/UBSan fuzzing, Profiling.
6. **[06_decompiler_case_studies/](06_decompiler_case_studies/)** (Articles 086 - 095): Deep architectural analysis of Procyon, CFR, Fernflower, Krakatau, Jadx, Dava/Soot, JD-Core, and NSA Ghidra; Comparative benchmarks.
7. **[07_obfuscation_countermeasures/](07_obfuscation_countermeasures/)** (Articles 096 - 100): Opaque predicates, Control flow flattening unflattening, String encryption solvers, Exceptional control flow traps, Heuristic identifier restoration.
"""
    readme_file = os.path.join(BASE_DIR, "README.md")
    with open(readme_file, "w", encoding="utf-8") as f:
        f.write(readme_content)
        
    print(f"Successfully generated {total_written} technical articles in {BASE_DIR}")
    print(f"Generated {index_file} and {readme_file}")

if __name__ == "__main__":
    main()
