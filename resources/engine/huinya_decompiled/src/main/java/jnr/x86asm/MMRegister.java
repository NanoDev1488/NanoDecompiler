// исходный (обфусцированный) внутренний класс: jnr.x86asm.MMRegister
package jnr.x86asm;

import jnr.x86asm.BaseReg;

public final class MMRegister extends BaseReg {

    // ---- поля ----
  static final MMRegister[] cache;

    static {
        cache = new MMRegister[8];
        int var0 = 0;
        while (var0 < cache.length) {
            cache[var0] = new MMRegister(96 | var0, 8);
            ++var0;
            continue;
        }
    }

  private MMRegister(int arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public static final MMRegister mm(int arg0) {
        if (arg0 < 0) {
            throw new IllegalArgumentException("invalid mm register");
        } else {
            if (arg0 >= cache.length) {
                throw new IllegalArgumentException("invalid mm register");
            } else {
                return ((MMRegister) cache[arg0]);
            }
        }
    }

}