// исходный (обфусцированный) внутренний класс: jnr.a64asm.Pre_index
package jnr.a64asm;

import jnr.a64asm.Immediate;
import jnr.a64asm.Operand;
import jnr.a64asm.Register;

public final class Pre_index extends Operand {

    // ---- поля ----
  private final Immediate preIndex;
  private final Register basereg;

  public Pre_index(Register arg0, Immediate arg1) { // было: <init>
        super(12, 0);
        basereg = arg0;
        preIndex = arg1;
    }

  public final Immediate getPreIndex() {
        return preIndex;
    }

  public final Register getRegister() {
        return basereg;
    }

}