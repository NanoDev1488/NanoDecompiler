// исходный (обфусцированный) внутренний класс: jnr.a64asm.BaseReg
package jnr.a64asm;

import jnr.a64asm.Operand;

public abstract class BaseReg extends Operand {

    // ---- поля ----
  public final int code;

  public BaseReg(int arg0, int arg1) { // было: <init>
        super(1, arg1);
        code = arg0;
    }

  public final int type() {
        return code() & 240;
    }

  public final int code() {
        return code;
    }

  public final int index() {
        return code() & 15;
    }

}