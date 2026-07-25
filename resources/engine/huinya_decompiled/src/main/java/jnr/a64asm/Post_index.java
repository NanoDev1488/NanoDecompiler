// исходный (обфусцированный) внутренний класс: jnr.a64asm.Post_index
package jnr.a64asm;

import jnr.a64asm.Immediate;
import jnr.a64asm.Operand;
import jnr.a64asm.Register;

public final class Post_index extends Operand {

    // ---- поля ----
  private final Immediate postIndex;
  private final Register basereg;

  public Post_index(Register arg0, Immediate arg1) { // было: <init>
        super(13, 0);
        basereg = arg0;
        postIndex = arg1;
    }

  public final Immediate getPostIndex() {
        return postIndex;
    }

  public final Register getRegister() {
        return basereg;
    }

}