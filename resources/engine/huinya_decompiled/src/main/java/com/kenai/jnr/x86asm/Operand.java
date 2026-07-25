// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.Operand
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.BaseReg;

@Deprecated
public class Operand {

    // ---- поля ----
  private final int op;
  private final int size;

  public Operand(int arg0, int arg1) { // было: <init>
        super();
        op = arg0;
        size = arg1;
    }

  public int op() {
        return op;
    }

  public int size() {
        return size;
    }

  public boolean isNone() {
        return op() == 0;
    }

  public boolean isReg() {
        return op() == 1;
    }

  public boolean isMem() {
        return op() == 2;
    }

  public boolean isImm() {
        return op() == 3;
    }

  public boolean isLabel() {
        return op() == 4;
    }

  public final boolean isRegMem() {
        return isMem() ? 1 : isReg();
    }

  public final boolean isRegCode(int arg0) {
        return !(this instanceof BaseReg) ? 0 : (((BaseReg) this)).code() == arg0;
    }

  public final boolean isRegType(int arg0) {
        return !(this instanceof BaseReg) ? 0 : (((BaseReg) this)).type() == arg0;
    }

  public final boolean isRegIndex(int arg0) {
        return !(this instanceof BaseReg) ? 0 : (((BaseReg) this)).index() == arg0;
    }

  public final boolean isRegMem(int arg0) {
        return isMem() ? 1 : isRegType(arg0);
    }

}