// исходный (обфусцированный) внутренний класс: jnr.a64asm.Offset
package jnr.a64asm;

import jnr.a64asm.Immediate;
import jnr.a64asm.Operand;
import jnr.a64asm.Register;

public final class Offset extends Operand {

    // ---- поля ----
  private final Immediate offset;
  private final Register basereg;

  public Offset(Register arg0, Immediate arg1) { // было: <init>
        super(14, 0);
        offset = arg1;
        basereg = arg0;
    }

  public final Immediate getOffset() {
        return offset;
    }

  public final Register getRegister() {
        return basereg;
    }

}