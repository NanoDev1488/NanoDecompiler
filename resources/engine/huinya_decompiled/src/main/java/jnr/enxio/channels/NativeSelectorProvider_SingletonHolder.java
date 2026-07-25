// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeSelectorProvider.SingletonHolder
package jnr.enxio.channels;

import jnr.enxio.channels.NativeSelectorProvider;

final class NativeSelectorProvider_SingletonHolder {

    // ---- поля ----
  static NativeSelectorProvider INSTANCE;

    static {
        INSTANCE = new NativeSelectorProvider();
    }

  private NativeSelectorProvider_SingletonHolder() { // было: <init>
        super();
    }

}