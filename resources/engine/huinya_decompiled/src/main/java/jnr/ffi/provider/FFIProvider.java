// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.FFIProvider
package jnr.ffi.provider;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.provider.FFIProvider_SystemProviderSingletonHolder;
import jnr.ffi.provider.InvalidProvider;

public abstract class FFIProvider {

  public static FFIProvider getSystemProvider() {
        return FFIProvider_SystemProviderSingletonHolder.access$000();
    }

  protected FFIProvider() { // было: <init>
        super();
    }

  public abstract Runtime getRuntime();

  public abstract LibraryLoader createLibraryLoader(Class arg0);

  private static FFIProvider newInvalidProvider(String arg0, Throwable arg1) {
        return new InvalidProvider(arg0, arg1);
    }

  static FFIProvider access$100(String arg0, Throwable arg1) {
        return newInvalidProvider(arg0, arg1);
    }

}