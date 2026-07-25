// исходный (обфусцированный) внутренний класс: jnr.a64asm.OP
package jnr.a64asm;

public final class OP {

    // ---- поля ----
  public static final int OP_NONE = 0;
  public static final int OP_REG = 1;
  public static final int OP_MEM = 2;
  public static final int OP_IMM = 3;
  public static final int OP_LABEL = 4;
  public static final int OP_EXT = 5;
  public static final int OP_SHIFT = 6;
  public static final int OP_COND = 7;
  public static final int OP_PSTATEFIELD = 8;
  public static final int OP_SYSREG = 9;
  public static final int OP_VAR = 10;
  public static final int OP_PREFOP = 11;
  public static final int OP_PREINDEX = 12;
  public static final int OP_POSTINDEX = 13;
  public static final int OP_OFFSET = 14;
  public static final int OP_PRFOP = 15;

  private OP() { // было: <init>
        super();
    }

}