// исходный (обфусцированный) внутренний класс: jnr.a64asm.Ext
package jnr.a64asm;

import jnr.a64asm.Operand;

public final class Ext extends Operand {

    // ---- поля ----
  private final long value;
  private final long type;

  public Ext(long arg0, long arg1) { // было: <init>
        super(5, 0);
        value = arg1;
        type = arg0;
    }

  public long value() {
        return value;
    }

  public long type() {
        return type;
    }

}