// исходный (обфусцированный) внутренний класс: jnr.ffi.LibraryLoader.DefaultLibPaths
package jnr.ffi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;

final class LibraryLoader_DefaultLibPaths {

    // ---- поля ----
  static final List PATHS;

    static {
        LinkedHashSet var0 = new LinkedHashSet();
        try {
            var0.addAll(LibraryLoader.access$000("jnr.ffi.library.path"));
            var0.addAll(LibraryLoader.access$000("jaffl.library.path"));
            var0.addAll(LibraryLoader.access$000("jna.library.path"));
            var0.addAll(LibraryLoader.access$000("java.library.path"));
        } catch (Exception var1) {
        }
        if (Platform.getNativePlatform().isUnix()) {
            var0.add("/usr/local/lib");
            var0.add("/usr/lib");
            var0.add("/lib");
        }
        switch (Platform.getNativePlatform().getOS()) {
            case FREEBSD:
            case OPENBSD:
            case NETBSD:
            case LINUX:
            case ZLINUX:
            case MIDNIGHTBSD:
                File var1 = new File("/etc/ld.so.conf");
                File var2 = new File("/etc/ld.so.conf.d");
                if (var1.exists()) {
                    addPathsFromFile(var0, var1);
                }
                if (!var2.isDirectory()) {
                    break;
                }
                File[] var3 = var2.listFiles();
                int var4 = var3.length;
                int var5 = 0;
                while (var5 < var4) {
                    Object var6 = var3[var5];
                    addPathsFromFile(var0, ((File) var6));
                    ++var5;
                    continue;
                }
            default:
        }
        PATHS = Collections.unmodifiableList(new ArrayList(var0));
    }

   LibraryLoader_DefaultLibPaths() { // было: <init>
        super();
    }

  private static void addPathsFromFile(Collection arg0, File arg1) {
        if (!arg1.isFile()) {
            return;
        }
        Object var2;
        if (arg1.exists()) {
            var2 = null;
        } else {
            return;
        }
        try {
            BufferedReader var2 = new BufferedReader(new FileReader(arg1));
            String var3 = var2.readLine();
            while (var3 != null) {
                if (var3.trim().isEmpty()) {
                    var3 = var2.readLine();
                    continue;
                } else {
                    if (var3.startsWith("#")) {
                        var3 = var2.readLine();
                        continue;
                    } else {
                        if (var3.startsWith("include ")) {
                            var3 = var2.readLine();
                            continue;
                        } else {
                            arg0.add(var3);
                            var3 = var2.readLine();
                            continue;
                        }
                    }
                }
            }
        } catch (IOException e2) {
            Throwable var3 = e2;
            if (var2 != null) {
                try {
                    var2.close();
                } catch (IOException e2) {
                    var3 = e2;
                }
            }
        } catch (Throwable e4) {
            try {
                while (true) {
                    Throwable var4 = e4;
                }
            } catch (Throwable var4) {
            }
        }
    }

}