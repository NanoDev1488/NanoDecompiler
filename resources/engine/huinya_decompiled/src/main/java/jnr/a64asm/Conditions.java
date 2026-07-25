// исходный (обфусцированный) внутренний класс: jnr.a64asm.Conditions
package jnr.a64asm;

import jnr.a64asm.Operand;

public final class Conditions extends Operand {

    // ---- поля ----
  private final int value;

  public Conditions(int arg0) { // было: <init>
        super(7, 0);
        value = arg0;
    }

  public long value() {
        return ((long) value);
    }

}