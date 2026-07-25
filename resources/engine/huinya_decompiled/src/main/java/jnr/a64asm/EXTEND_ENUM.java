// исходный (обфусцированный) внутренний класс: jnr.a64asm.EXTEND_ENUM
package jnr.a64asm;

public enum EXTEND_ENUM {

    UXTB,
    UXTH,
    UXTW,
    LSL,
    UXTX,
    SXTB,
    SXTH,
    SXTW,
    SXTX;

  private EXTEND_ENUM() { // было: <init>
        // (пустое тело)
    }

  public final int intValue() {
        return ordinal();
    }

}