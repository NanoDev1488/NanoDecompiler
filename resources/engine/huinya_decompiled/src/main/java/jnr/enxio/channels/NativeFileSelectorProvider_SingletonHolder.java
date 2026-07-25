// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeFileSelectorProvider.SingletonHolder
package jnr.enxio.channels;

import jnr.enxio.channels.NativeFileSelectorProvider;

final class NativeFileSelectorProvider_SingletonHolder {

    // ---- поля ----
  static NativeFileSelectorProvider INSTANCE;

    static {
        INSTANCE = new NativeFileSelectorProvider();
    }

  private NativeFileSelectorProvider_SingletonHolder() { // было: <init>
        super();
    }

}