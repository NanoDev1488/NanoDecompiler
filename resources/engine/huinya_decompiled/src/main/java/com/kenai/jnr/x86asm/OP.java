// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.OP
package com.kenai.jnr.x86asm;

@Deprecated
public final class OP {

    // ---- поля ----
  public static final int OP_NONE = 0;
  public static final int OP_REG = 1;
  public static final int OP_MEM = 2;
  public static final int OP_IMM = 3;
  public static final int OP_LABEL = 4;
  public static final int OP_VAR = 5;

  private OP() { // было: <init>
        super();
    }

}