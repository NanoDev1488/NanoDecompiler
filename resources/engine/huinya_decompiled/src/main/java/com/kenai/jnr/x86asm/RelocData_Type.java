// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.RelocData.Type
package com.kenai.jnr.x86asm;

enum RelocData_Type {

    ABSOLUTE_TO_ABSOLUTE,
    RELATIVE_TO_ABSOLUTE,
    ABSOLUTE_TO_RELATIVE,
    ABSOLUTE_TO_RELATIVE_TRAMPOLINE;

  private RelocData_Type() { // было: <init>
        // (пустое тело)
    }

}