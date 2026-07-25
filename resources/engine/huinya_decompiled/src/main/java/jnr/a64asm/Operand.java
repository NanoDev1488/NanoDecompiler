// исходный (обфусцированный) внутренний класс: jnr.a64asm.Operand
package jnr.a64asm;

import jnr.a64asm.BaseReg;

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

  public boolean isExtend() {
        return op() == 5;
    }

  public boolean isCond() {
        return op() == 7;
    }

  public boolean isPrefOp() {
        return op() == 11;
    }

  public boolean isPreIndex() {
        return op() == 12;
    }

  public boolean isPostIndex() {
        return op() == 13;
    }

  public boolean isOffset() {
        return op() == 14;
    }

  public boolean isPrfop() {
        return op() == 15;
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