// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InvalidProvider
package jnr.ffi.provider;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.provider.FFIProvider;
import jnr.ffi.provider.InvalidProvider_Anon1;
import jnr.ffi.provider.InvalidRuntime;

final class InvalidProvider extends FFIProvider {

    // ---- поля ----
  private final String message;
  private final Throwable cause;
  private final Runtime runtime;

   InvalidProvider(String arg0, Throwable arg1) { // было: <init>
        super();
        message = arg0;
        cause = arg1;
        runtime = new InvalidRuntime(arg0, arg1);
    }

  public Runtime getRuntime() {
        return runtime;
    }

  public LibraryLoader createLibraryLoader(Class arg0) {
        return new InvalidProvider_Anon1(this, arg0);
    }

  static String access$000(InvalidProvider arg0) {
        return arg0.message;
    }

  static Throwable access$100(InvalidProvider arg0) {
        return arg0.cause;
    }

}