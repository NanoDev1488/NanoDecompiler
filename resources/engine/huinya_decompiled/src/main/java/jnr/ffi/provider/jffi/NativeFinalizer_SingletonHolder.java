// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeFinalizer.SingletonHolder
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.NativeFinalizer;

final class NativeFinalizer_SingletonHolder {

    // ---- поля ----
  private static final NativeFinalizer INSTANCE;

    static {
        INSTANCE = new NativeFinalizer();
    }

  private NativeFinalizer_SingletonHolder() { // было: <init>
        super();
    }

  static NativeFinalizer access$000() {
        return INSTANCE;
    }

}