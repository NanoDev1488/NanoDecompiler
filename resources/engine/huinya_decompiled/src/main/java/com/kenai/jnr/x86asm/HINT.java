// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.HINT
package com.kenai.jnr.x86asm;

@Deprecated
public enum HINT {

    HINT_NONE(0),
    HINT_TAKEN(62),
    HINT_NOT_TAKEN(46);

    // ---- поля ----
  private final int value;

  private HINT(int arg2) { // было: <init>
        value = arg2;
    }

  public final int value() {
        return value;
    }

  public static final HINT valueOf(int arg0) {
        switch (arg0) {
            case 62:
                return HINT_TAKEN;
            case 46:
                return HINT_NOT_TAKEN;
            default:
                return HINT_NONE;
        }
    }

}