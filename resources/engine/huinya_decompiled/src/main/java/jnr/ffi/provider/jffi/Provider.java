// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.Provider
package jnr.ffi.provider.jffi;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.provider.FFIProvider;
import jnr.ffi.provider.jffi.NativeLibraryLoader;
import jnr.ffi.provider.jffi.NativeRuntime;

public final class Provider extends FFIProvider {

    // ---- поля ----
  private final NativeRuntime runtime;

  public Provider() { // было: <init>
        super();
        runtime = NativeRuntime.getInstance();
    }

  public final Runtime getRuntime() {
        return runtime;
    }

  public LibraryLoader createLibraryLoader(Class arg0) {
        return new NativeLibraryLoader(arg0);
    }

}