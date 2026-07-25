// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXFactory.DefaultLibCProvider.SingletonHolder
package jnr.posix;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import jnr.ffi.LibraryLoader;
import jnr.ffi.LibraryOption;
import jnr.posix.Crypt;
import jnr.posix.LibC;
import jnr.posix.POSIXFactory;

final class POSIXFactory_DefaultLibCProvider_SingletonHolder {

    // ---- поля ----
  public static LibC libc;
  public static Crypt crypt;

    static {
        LibraryLoader var0 = LibraryLoader.create(POSIXFactory.access$000());
        var0.searchDefault();
        String[] var1 = POSIXFactory.access$100();
        int var2 = var1.length;
        int var3 = 0;
        while (var3 < var2) {
            Object var4 = var1[var3];
            var0.library(((String) var4));
            ++var3;
            continue;
        }
        var1 = POSIXFactory.access$200().entrySet().iterator();
        while (var1.hasNext()) {
            var2 = ((Entry) var1.next());
            var0.option(((LibraryOption) var2.getKey()), var2.getValue());
            continue;
        }
        var0.failImmediately();
        libc = ((LibC) var0.load());
        var1 = null;
        try {
            var2 = LibraryLoader.create(Crypt.class).failImmediately();
            var1 = ((Crypt) var2.load("libcrypt.so.1"));
        } catch (UnsatisfiedLinkError e1) {
            var2 = e1;
        }
        crypt = var1;
    }

  private POSIXFactory_DefaultLibCProvider_SingletonHolder() { // было: <init>
        super();
    }

}