// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXFactory.DefaultLibCProvider
package jnr.posix;

import jnr.posix.Crypt;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.POSIXFactory_DefaultLibCProvider_SingletonHolder;

final class POSIXFactory_DefaultLibCProvider implements LibCProvider {

    // ---- поля ----
  public static final LibCProvider INSTANCE;

    static {
        INSTANCE = new POSIXFactory_DefaultLibCProvider();
    }

  private POSIXFactory_DefaultLibCProvider() { // было: <init>
        super();
    }

  public final LibC getLibC() {
        return POSIXFactory_DefaultLibCProvider_SingletonHolder.libc;
    }

  public final Crypt getCrypt() {
        return POSIXFactory_DefaultLibCProvider_SingletonHolder.crypt;
    }

}