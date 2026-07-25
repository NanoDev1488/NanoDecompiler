// исходный (обфусцированный) внутренний класс: jnr.a64asm.RelocData.Type
package jnr.a64asm;

enum RelocData_Type {

    ABSOLUTE_TO_ABSOLUTE,
    RELATIVE_TO_ABSOLUTE,
    ABSOLUTE_TO_RELATIVE,
    ABSOLUTE_TO_RELATIVE_TRAMPOLINE;

  private RelocData_Type() { // было: <init>
        // (пустое тело)
    }

}