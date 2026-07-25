// исходный (обфусцированный) внутренний класс: jnr.a64asm.PrefOp
package jnr.a64asm;

import jnr.a64asm.Operand;
import jnr.a64asm.PREF_ENUM;

public class PrefOp extends Operand {

    // ---- поля ----
   PREF_ENUM type;

   PrefOp(long arg0, PREF_ENUM arg1) { // было: <init>
        super(11, 0);
        type = arg1;
    }

  public PREF_ENUM type() {
        return type;
    }

}