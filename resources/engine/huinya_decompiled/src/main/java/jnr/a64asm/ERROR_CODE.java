// исходный (обфусцированный) внутренний класс: jnr.a64asm.ERROR_CODE
package jnr.a64asm;

public enum ERROR_CODE {

    ERROR_NONE,
    ERROR_NO_HEAP_MEMORY,
    ERROR_NO_VIRTUAL_MEMORY,
    ERROR_UNKNOWN_INSTRUCTION,
    ERROR_ILLEGAL_INSTRUCTION,
    ERROR_ILLEGAL_ADDRESING,
    ERROR_ILLEGAL_SHORT_JUMP,
    _ERROR_COUNT;

  private ERROR_CODE() { // было: <init>
        // (пустое тело)
    }

  public final int intValue() {
        return ordinal();
    }

}