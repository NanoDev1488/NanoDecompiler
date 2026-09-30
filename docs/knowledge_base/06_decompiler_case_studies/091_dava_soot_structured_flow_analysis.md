# Article 091: Dava (Soot Framework): Structured Encapsulation Trees (SET)

## 1. Executive Summary & Academic Lineage
Developed at McGill University within the **Soot** compiler framework, **Dava** pioneered formal academic control flow structuring for Java bytecode.

## 2. Structured Encapsulation Trees (SET)
Dava introduced SET, a tree representation that models single-entry multiple-exit subgraphs:
- Identifies natural loop constructs and conditional branching clusters.
- Employs advanced graph transformations to resolve irreducible flow graphs without code duplication.

## 3. References
- Driesen, K., et al. *Dava: A decompiler for arbitrary Java bytecode*. ACM OOPSLA, 2003.
- Vallée-Rai, R., et al. *Soot: A Java bytecode analysis and transformation framework*. CASCON, 1999.
