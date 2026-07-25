// исходный (обфусцированный) внутренний класс: jnr.a64asm.LABEL_STATE
package jnr.a64asm;

public enum LABEL_STATE {

    LABEL_STATE_UNUSED,
    LABEL_STATE_LINKED,
    LABEL_STATE_BOUND;

  private LABEL_STATE() { // было: <init>
        // (пустое тело)
    }

}