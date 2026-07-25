// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.X87Register
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.BaseReg;

@Deprecated
public final class X87Register extends BaseReg {

    // ---- поля ----
  static final X87Register[] cache;

    static {
        cache = new X87Register[16];
        int var0 = 0;
        while (var0 < cache.length) {
            cache[var0] = new X87Register(80 | var0, 10);
            ++var0;
            continue;
        }
    }

  private X87Register(int arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public static final X87Register st(int arg0) {
        return x87(arg0);
    }

  public static final X87Register x87(int arg0) {
        if (arg0 < 0) {
            throw new IllegalArgumentException("invalid x87 register");
        } else {
            if (arg0 >= cache.length) {
                throw new IllegalArgumentException("invalid x87 register");
            } else {
                return ((X87Register) cache[arg0]);
            }
        }
    }

}