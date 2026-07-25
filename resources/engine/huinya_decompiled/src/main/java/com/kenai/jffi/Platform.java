// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Platform_Anon1;
import com.kenai.jffi.Platform_ArchHolder;
import com.kenai.jffi.Platform_CPU;
import com.kenai.jffi.Platform_Darwin;
import com.kenai.jffi.Platform_Default;
import com.kenai.jffi.Platform_OS;
import com.kenai.jffi.Platform_SingletonHolder;
import com.kenai.jffi.Platform_Windows;
import java.util.Locale;

public abstract class Platform {

    // ---- поля ----
  private static final Locale LOCALE;
  private final Platform_OS os;
  private final int javaVersionMajor;

    static {
        LOCALE = Locale.ENGLISH;
    }

  private static final Platform_OS determineOS() {
        Object var0 = System.getProperty("os.name").split(" ")[0];
        if (startsWithIgnoreCase(((String) var0), "mac")) {
            return Platform_OS.DARWIN;
        } else {
            if (!startsWithIgnoreCase(((String) var0), "darwin")) {
                if (!startsWithIgnoreCase(((String) var0), "linux")) {
                    if (startsWithIgnoreCase(((String) var0), "sunos")) {
                        return Platform_OS.SOLARIS;
                    } else {
                        if (!startsWithIgnoreCase(((String) var0), "solaris")) {
                            if (!startsWithIgnoreCase(((String) var0), "aix")) {
                                if (startsWithIgnoreCase(((String) var0), "os/400")) {
                                    return Platform_OS.IBMI;
                                } else {
                                    if (!startsWithIgnoreCase(((String) var0), "os400")) {
                                        if (!startsWithIgnoreCase(((String) var0), "openbsd")) {
                                            if (!startsWithIgnoreCase(((String) var0), "freebsd")) {
                                                if (!startsWithIgnoreCase(((String) var0), "dragonfly")) {
                                                    if (!startsWithIgnoreCase(((String) var0), "windows")) {
                                                        return Platform_OS.UNKNOWN;
                                                    } else {
                                                        return Platform_OS.WINDOWS;
                                                    }
                                                } else {
                                                    return Platform_OS.DRAGONFLY;
                                                }
                                            } else {
                                                return Platform_OS.FREEBSD;
                                            }
                                        } else {
                                            return Platform_OS.OPENBSD;
                                        }
                                    } else {
                                        return Platform_OS.IBMI;
                                    }
                                }
                            } else {
                                return Platform_OS.AIX;
                            }
                        } else {
                            return Platform_OS.SOLARIS;
                        }
                    }
                } else {
                    return Platform_OS.LINUX;
                }
            } else {
                return Platform_OS.DARWIN;
            }
        }
    }

  private static final Platform determinePlatform(Platform_OS arg0) {
        switch (arg0) {
            case DARWIN:
                return newDarwinPlatform();
            case WINDOWS:
                return newWindowsPlatform();
            default:
                return newDefaultPlatform(arg0);
        }
    }

  private static Platform newDarwinPlatform() {
        return new Platform_Darwin();
    }

  private static Platform newWindowsPlatform() {
        return new Platform_Windows();
    }

  private static Platform newDefaultPlatform(Platform_OS arg0) {
        return new Platform_Default(arg0);
    }

  private Platform(Platform_OS arg0) { // было: <init>
        super();
        os = arg0;
        int var2 = 8;
        try {
            String var3 = System.getProperty("java.version");
            if (var3 != null) {
                Object var4 = var3.split("[^0-9.]")[0];
                int var5 = var4.indexOf(46);
                if (var5 == -1) {
                    var2 = Integer.valueOf(((String) var4)).intValue();
                } else {
                    var4 = var4.substring(var5 + 1);
                    var2 = Integer.valueOf(((String) var4)).intValue();
                }
            }
            javaVersionMajor = var2;
            return;
        } catch (Exception e1) {
            Throwable var3 = e1;
            var2 = 8;
        }
        javaVersionMajor = var2;
    }

  public static final Platform getPlatform() {
        return Platform_SingletonHolder.PLATFORM;
    }

  public final Platform_OS getOS() {
        return os;
    }

  public final Platform_CPU getCPU() {
        return Platform_ArchHolder.cpu;
    }

  public final int getJavaMajorVersion() {
        return javaVersionMajor;
    }

  public abstract int longSize();

  public final int addressSize() {
        return getCPU().dataModel;
    }

  public final long addressMask() {
        return getCPU().addressMask;
    }

  public String getName() {
        Object var1 = System.getProperty("os.name").split(" ")[0];
        return new StringBuilder().append(getCPU().name().toLowerCase(LOCALE)).append("-").append(((String) var1)).toString();
    }

  public String mapLibraryName(String arg0) {
        if (!arg0.matches(getLibraryNamePattern())) {
            if (!Platform_OS.IBMI.equals(getOS())) {
                return System.mapLibraryName(arg0);
            } else {
                return new StringBuilder().append("lib").append(arg0).append(".so").toString();
            }
        } else {
            return arg0;
        }
    }

  public String getLibraryNamePattern() {
        return "lib.*\\.so.*$";
    }

  public boolean isSupported() {
        int var1 = Foreign.getInstance().getVersion();
        if ((var1 & 16776960) != (Foreign.VERSION_MAJOR << 16 | Foreign.VERSION_MINOR << 8)) {
            throw new UnsatisfiedLinkError("Incorrect native library version");
        } else {
            return true;
        }
    }

  private static boolean startsWithIgnoreCase(String arg0, String arg1) {
        return arg0.startsWith(arg1) ? 1 : arg0.toUpperCase(LOCALE).startsWith(arg1.toUpperCase(LOCALE)) ? 1 : arg0.toLowerCase(LOCALE).startsWith(arg1.toLowerCase(LOCALE));
    }

  static Locale access$000() {
        return LOCALE;
    }

  static Platform_OS access$100() {
        return determineOS();
    }

  static Platform access$200(Platform_OS arg0) {
        return determinePlatform(arg0);
    }

   Platform(Platform_OS arg0, Platform_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}