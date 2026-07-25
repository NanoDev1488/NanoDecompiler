// исходный (обфусцированный) внутренний класс: jnr.x86asm.Register
package jnr.x86asm;

import jnr.x86asm.BaseReg;

public final class Register extends BaseReg {

    // ---- поля ----
  private static final Register[] gpb;
  private static final Register[] gpw;
  private static final Register[] gpd;
  private static final Register[] gpq;

    static {
        gpb = new Register[16];
        gpw = new Register[16];
        gpd = new Register[16];
        gpq = new Register[16];
        int var0 = 0;
        while (var0 < 16) {
            gpb[var0] = new Register(0 | var0, 1);
            gpw[var0] = new Register(16 | var0, 2);
            gpd[var0] = new Register(32 | var0, 4);
            gpq[var0] = new Register(48 | var0, 8);
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
            case 16:
                return ((Register) gpw[arg0 & 15]);
            case 32:
                return ((Register) gpd[arg0 & 15]);
            case 48:
                return ((Register) gpq[arg0 & 15]);
            default:
                throw new IllegalArgumentException(new StringBuilder().append("invalid register 0x").append(Integer.toHexString(arg0)).toString());
        }
    }

  private static final Register gpr(Register[] arg0, int arg1) {
        if (arg1 < 0) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid register index ").append(arg1).toString());
        } else {
            if (arg1 >= 16) {
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

  public static final Register gpd(int arg0) {
        return gpr(gpd, arg0);
    }

  public static final Register gpq(int arg0) {
        return gpr(gpq, arg0);
    }

}