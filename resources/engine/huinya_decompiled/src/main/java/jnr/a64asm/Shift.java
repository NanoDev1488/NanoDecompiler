// исходный (обфусцированный) внутренний класс: jnr.a64asm.Shift
package jnr.a64asm;

import jnr.a64asm.Operand;

public final class Shift extends Operand {

    // ---- поля ----
  private final int value;
  private final int type;

  public Shift(int arg0, int arg1) { // было: <init>
        super(6, 0);
        value = arg1;
        type = arg0;
    }

  public long value() {
        return ((long) value);
    }

  public long type() {
        return ((long) type);
    }

}