// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.XMMRegister
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.BaseReg;

@Deprecated
public final class XMMRegister extends BaseReg {

    // ---- поля ----
  static final XMMRegister[] cache;

    static {
        cache = new XMMRegister[16];
        int var0 = 0;
        while (var0 < cache.length) {
            cache[var0] = new XMMRegister(112 | var0, 16);
            ++var0;
            continue;
        }
    }

  private XMMRegister(int arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public static final XMMRegister xmm(int arg0) {
        if (arg0 < 0) {
            throw new IllegalArgumentException("invalid xmm register");
        } else {
            if (arg0 >= cache.length) {
                throw new IllegalArgumentException("invalid xmm register");
            } else {
                return ((XMMRegister) cache[arg0]);
            }
        }
    }

}