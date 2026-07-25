// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.Native.SingletonHolder
package jnr.enxio.channels;

import jnr.enxio.channels.Native_LibC;
import jnr.enxio.channels.WinLibCAdapter;
import jnr.enxio.channels.WinLibCAdapter_LibMSVCRT;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;
import jnr.ffi.Platform_OS;
import jnr.ffi.Runtime;

final class Native_SingletonHolder {

    // ---- поля ----
  static final Native_LibC libc;
  static final Runtime runtime;

    static {
        Platform var0 = Platform.getNativePlatform();
        LibraryLoader var1 = LibraryLoader.create(Native_LibC.class);
        var1.library(var0.getStandardCLibraryName());
        if (var0.getOS() == Platform_OS.SOLARIS) {
            var1.library("socket");
        }
        Native_LibC var2 = ((Native_LibC) var1.load());
        if (var0.getOS() != Platform_OS.WINDOWS) {
            libc = var2;
        } else {
            WinLibCAdapter_LibMSVCRT var3 = ((WinLibCAdapter_LibMSVCRT) LibraryLoader.create(WinLibCAdapter_LibMSVCRT.class).load(var0.getStandardCLibraryName()));
            libc = new WinLibCAdapter(var3);
        }
        runtime = Runtime.getRuntime(libc);
    }

  private Native_SingletonHolder() { // было: <init>
        super();
    }

}