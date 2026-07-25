// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeRuntime.SingletonHolder
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.NativeRuntime;

final class NativeRuntime_SingletonHolder {

    // ---- поля ----
  public static final NativeRuntime INSTANCE;

    static {
        INSTANCE = new NativeRuntime(null);
    }

  private NativeRuntime_SingletonHolder() { // было: <init>
        super();
    }

}