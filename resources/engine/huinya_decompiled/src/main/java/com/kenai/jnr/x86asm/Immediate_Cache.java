// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.Immediate.Cache
package com.kenai.jnr.x86asm;

import com.kenai.jnr.x86asm.Immediate;

final class Immediate_Cache {

    // ---- поля ----
  static final Immediate[] cache;

    static {
        cache = new Immediate[256];
        int var0 = 0;
        while (var0 < cache.length) {
            cache[var0] = new Immediate(((long) (var0 - 128)), false);
            ++var0;
            continue;
        }
    }

  private Immediate_Cache() { // было: <init>
        super();
    }

}