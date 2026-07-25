// исходный (обфусцированный) внутренний класс: jnr.a64asm.Register
package jnr.a64asm;

import jnr.a64asm.BaseReg;

public class Register extends BaseReg {

    // ---- поля ----
  private static final Register[] gpb;
  private static final Register[] gpw;

    static {
        gpb = new Register[32];
        gpw = new Register[32];
        int var0 = 0;
        while (var0 < 32) {
            gpb[var0] = new Register(0 | var0, 64);
            gpw[var0] = new Register(32 | var0, 32);
            ++var0;
            continue;
        }
    }

   Register(int arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public static final Register gpr(int arg0) {
        switch (arg0 & 240) {
            case 0:
                return ((Register) gpb[arg0 & 15]);
            case 32:
                return ((Register) gpw[arg0 & 15]);
            default:
                throw new IllegalArgumentException(new StringBuilder().append("invalid register 0x").append(Integer.toHexString(arg0)).toString());
        }
    }

  private static final Register gpr(Register[] arg0, int arg1) {
        if (arg1 < 0) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid register index ").append(arg1).toString());
        } else {
            if (arg1 >= 32) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid register index ").append(arg1).toString());
            } else {
                return ((Register) arg0[arg1]);
            }
        }
    }

  public static final Register gpb(int arg0) {
        return gpr(gpb, arg0);
    }

  public static final Register gpw(int arg0) {
        return gpr(gpw, arg0);
    }

}