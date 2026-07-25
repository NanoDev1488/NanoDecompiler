// исходный (обфусцированный) внутренний класс: jnr.a64asm.PRFOP_ENUM
package jnr.a64asm;

import jnr.a64asm.Operand;

public final class PRFOP_ENUM extends Operand {

    // ---- поля ----
  private final long value;
  public static final int PLDL1KEEP = 0;
  public static final int PLDL1STRM = 1;
  public static final int PLDL2KEEP = 2;
  public static final int PLDL2STRM = 3;
  public static final int PLDL3KEEP = 4;
  public static final int PLDL3STRM = 5;
  public static final int PLIL1KEEP = 8;
  public static final int PLIL1STRM = 9;
  public static final int PLIL2KEEP = 10;
  public static final int PLIL2STRM = 11;
  public static final int PLIL3KEEP = 12;
  public static final int PLIL3STRM = 13;
  public static final int PSTL1KEEP = 16;
  public static final int PSTL1STRM = 17;
  public static final int PSTL2KEEP = 18;
  public static final int PSTL2STRM = 19;
  public static final int PSTL3KEEP = 20;
  public static final int PSTL3STRM = 21;

  public PRFOP_ENUM(int arg0) { // было: <init>
        super(15, 0);
        value = ((long) arg0);
    }

  public final long intValue() {
        return value;
    }

}