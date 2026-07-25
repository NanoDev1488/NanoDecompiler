// исходный (обфусцированный) внутренний класс: jnr.ffi.Runtime.SingletonHolder
package jnr.ffi;

import jnr.ffi.Runtime;
import jnr.ffi.provider.FFIProvider;

final class Runtime_SingletonHolder {

    // ---- поля ----
  public static final Runtime SYSTEM_RUNTIME;

    static {
        SYSTEM_RUNTIME = FFIProvider.getSystemProvider().getRuntime();
    }

  private Runtime_SingletonHolder() { // было: <init>
        super();
    }

}