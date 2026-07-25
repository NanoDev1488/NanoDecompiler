// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.SingletonHolder
package jnr.ffi;

import jnr.ffi.Platform;

final class Platform_SingletonHolder {

    // ---- поля ----
  static final Platform PLATFORM;

    static {
        PLATFORM = Platform.access$000();
    }

  private Platform_SingletonHolder() { // было: <init>
        super();
    }

}