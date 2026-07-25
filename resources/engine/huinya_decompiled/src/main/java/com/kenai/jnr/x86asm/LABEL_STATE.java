// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.LABEL_STATE
package com.kenai.jnr.x86asm;

@Deprecated
public enum LABEL_STATE {

    LABEL_STATE_UNUSED,
    LABEL_STATE_LINKED,
    LABEL_STATE_BOUND;

  private LABEL_STATE() { // было: <init>
        // (пустое тело)
    }

}