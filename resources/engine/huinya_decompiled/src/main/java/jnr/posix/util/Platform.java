// исходный (обфусцированный) внутренний класс: jnr.posix.util.Platform
package jnr.posix.util;

import java.util.HashMap;
import java.util.Map;

public class Platform {

    // ---- поля ----
  public static final String OS_NAME;
  public static final String OS_NAME_LC;
  private static final String WINDOWS = "windows";
  private static final String WINDOWS_9X = "windows 9";
  private static final String WINDOWS_NT = "nt";
  private static final String WINDOWS_20X = "windows 2";
  private static final String WINDOWS_XP = "windows xp";
  private static final String WINDOWS_SERVER = "server";
  private static final String WINDOWS_VISTA = "vista";
  private static final String WINDOWS_7 = "windows 7";
  private static final String MAC_OS = "mac os";
  private static final String DARWIN = "darwin";
  private static final String FREEBSD = "freebsd";
  private static final String DRAGONFLY = "dragonfly";
  private static final String OPENBSD = "openbsd";
  private static final String LINUX = "linux";
  private static final String SOLARIS = "sunos";
  public static final boolean IS_WINDOWS;
  public static final boolean IS_WINDOWS_9X;
  public static final boolean IS_WINDOWS_NT;
  public static final boolean IS_WINDOWS_20X;
  public static final boolean IS_WINDOWS_XP;
  public static final boolean IS_WINDOWS_VISTA;
  public static final boolean IS_WINDOWS_SERVER;
  public static final boolean IS_WINDOWS_7;
  public static final boolean IS_MAC;
  public static final boolean IS_FREEBSD;
  public static final boolean IS_DRAGONFLY;
  public static final boolean IS_OPENBSD;
  public static final boolean IS_LINUX;
  public static final boolean IS_SOLARIS;
  public static final boolean IS_BSD;
  public static final boolean IS_32_BIT;
  public static final boolean IS_64_BIT;
  public static final String ARCH;
  public static final Map OS_NAMES;

    static {
        boolean __stk1;
        boolean __stk2;
        int __stk3;
        boolean __stk4;
        boolean __stk5;
        int __stk6;
        int __stk7;
        int __stk8;
        int __stk9;
        int __stk10;
        OS_NAME = System.getProperty("os.name");
        OS_NAME_LC = OS_NAME.toLowerCase();
        __stk1 = OS_NAME_LC.indexOf("windows") != -1;
        IS_WINDOWS = __stk1;
        __stk2 = OS_NAME_LC.indexOf("windows 9") > -1;
        IS_WINDOWS_9X = __stk2;
        __stk3 = !IS_WINDOWS ? 0 : OS_NAME_LC.indexOf("nt") > -1;
        IS_WINDOWS_NT = __stk3;
        __stk4 = OS_NAME_LC.indexOf("windows 2") > -1;
        IS_WINDOWS_20X = __stk4;
        __stk5 = OS_NAME_LC.indexOf("windows xp") > -1;
        IS_WINDOWS_XP = __stk5;
        __stk6 = !IS_WINDOWS ? 0 : OS_NAME_LC.indexOf("vista") > -1;
        IS_WINDOWS_VISTA = __stk6;
        __stk7 = !IS_WINDOWS ? 0 : OS_NAME_LC.indexOf("server") > -1;
        IS_WINDOWS_SERVER = __stk7;
        __stk8 = !IS_WINDOWS ? 0 : OS_NAME_LC.indexOf("windows 7") > -1;
        IS_WINDOWS_7 = __stk8;
        __stk9 = OS_NAME_LC.startsWith("mac os") ? 1 : OS_NAME_LC.startsWith("darwin");
        IS_MAC = __stk9;
        IS_FREEBSD = OS_NAME_LC.startsWith("freebsd");
        IS_DRAGONFLY = OS_NAME_LC.startsWith("dragonfly");
        IS_OPENBSD = OS_NAME_LC.startsWith("openbsd");
        IS_LINUX = OS_NAME_LC.startsWith("linux");
        IS_SOLARIS = OS_NAME_LC.startsWith("sunos");
        __stk10 = IS_MAC ? 1 : IS_FREEBSD ? 1 : IS_OPENBSD ? 1 : IS_DRAGONFLY;
        IS_BSD = __stk10;
        IS_32_BIT = "32".equals(getProperty("sun.arch.data.model", "32"));
        IS_64_BIT = "64".equals(getProperty("sun.arch.data.model", "64"));
        String var0 = System.getProperty("os.arch");
        if (var0.equals("amd64")) {
            var0 = "x86_64";
        }
        ARCH = var0;
        OS_NAMES = new HashMap();
        OS_NAMES.put("Mac OS X", "darwin");
        OS_NAMES.put("Darwin", "darwin");
        OS_NAMES.put("Linux", "linux");
    }

  public Platform() { // было: <init>
        super();
    }

  public static final String envCommand() {
        if (!IS_WINDOWS) {
            return "env";
        } else {
            if (!IS_WINDOWS_9X) {
                if (IS_WINDOWS_NT) {
                    return "cmd.exe /c set";
                } else {
                    if (IS_WINDOWS_20X) {
                        return "cmd.exe /c set";
                    } else {
                        if (IS_WINDOWS_XP) {
                            return "cmd.exe /c set";
                        } else {
                            if (IS_WINDOWS_SERVER) {
                                return "cmd.exe /c set";
                            } else {
                                if (IS_WINDOWS_VISTA) {
                                    return "cmd.exe /c set";
                                } else {
                                    if (!IS_WINDOWS_7) {
                                        return "env";
                                    } else {
                                        return "cmd.exe /c set";
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return "command.com /c set";
            }
        }
    }

  public static String getOSName() {
        String var0 = ((String) OS_NAMES.get(OS_NAME));
        return var0 != null ? var0 : OS_NAME;
    }

  public static String getProperty(String arg0, String arg1) {
        String __stk1;
        try {
            __stk1 = System.getProperty(arg0, arg1);
        } catch (SecurityException var2) {
            return arg1;
        }
    }

}