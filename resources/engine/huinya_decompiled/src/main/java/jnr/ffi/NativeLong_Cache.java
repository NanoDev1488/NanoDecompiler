// исходный (обфусцированный) внутренний класс: jnr.ffi.NativeLong.Cache
package jnr.ffi;

import jnr.ffi.NativeLong;

final class NativeLong_Cache {

    // ---- поля ----
  static final NativeLong[] cache;

    static {
        cache = new NativeLong[256];
        int var0 = 0;
        while (var0 < cache.length) {
            cache[var0] = new NativeLong(var0 - 128);
            ++var0;
            continue;
        }
        cache[128] = NativeLong.access$000();
        cache[129] = NativeLong.access$100();
        cache[127] = NativeLong.access$200();
    }

  private NativeLong_Cache() { // было: <init>
        super();
    }

}