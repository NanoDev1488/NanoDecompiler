// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform
package jnr.ffi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.LibraryLoader_DefaultLibPaths;
import jnr.ffi.Platform_Anon1;
import jnr.ffi.Platform_CPU;
import jnr.ffi.Platform_Darwin;
import jnr.ffi.Platform_Default;
import jnr.ffi.Platform_IbmI;
import jnr.ffi.Platform_Linux;
import jnr.ffi.Platform_OS;
import jnr.ffi.Platform_SingletonHolder;
import jnr.ffi.Platform_Unsupported;
import jnr.ffi.Platform_Windows;

public abstract class Platform {

    // ---- поля ----
  private static final Locale LOCALE;
  private final Platform_OS os;
  private final Platform_CPU cpu;
  private final int addressSize;
  private final int longSize;
  protected final Pattern libPattern;

    static {
        LOCALE = Locale.ENGLISH;
    }

  private static Platform_OS determineOS() {
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
                                if (startsWithIgnoreCase(((String) var0), "os400")) {
                                    return Platform_OS.IBMI;
                                } else {
                                    if (!startsWithIgnoreCase(((String) var0), "os/400")) {
                                        if (!startsWithIgnoreCase(((String) var0), "openbsd")) {
                                            if (!startsWithIgnoreCase(((String) var0), "freebsd")) {
                                                if (!startsWithIgnoreCase(((String) var0), "dragonfly")) {
                                                    if (!startsWithIgnoreCase(((String) var0), "windows")) {
                                                        if (!startsWithIgnoreCase(((String) var0), "midnightbsd")) {
                                                            return Platform_OS.UNKNOWN;
                                                        } else {
                                                            return Platform_OS.MIDNIGHTBSD;
                                                        }
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

  private static Platform determinePlatform(Platform_OS arg0) {
        switch (arg0) {
            case DARWIN:
                return new Platform_Darwin();
            case LINUX:
                return new Platform_Linux();
            case WINDOWS:
                return new Platform_Windows();
            case IBMI:
                return new Platform_IbmI();
            case UNKNOWN:
                return new Platform_Unsupported(arg0);
            default:
                return new Platform_Default(arg0);
        }
    }

  private static Platform determinePlatform() {
        Platform __stk1;
        String var0 = System.getProperty("jnr.ffi.provider");
        try {
            Class var1 = Class.forName(new StringBuilder().append(var0).append("$Platform").toString());
            __stk1 = ((Platform) var1.newInstance());
        } catch (ClassNotFoundException e1) {
            Throwable var1 = e1;
            return determinePlatform(determineOS());
        } catch (IllegalAccessException e2) {
            Throwable var1 = e2;
            throw new ExceptionInInitializerError(var1);
        } catch (InstantiationException e3) {
            Throwable var1 = e3;
            throw new ExceptionInInitializerError(var1);
        }
    }

  private static Platform_CPU determineCPU() {
        String var0 = System.getProperty("os.arch");
        if (equalsIgnoreCase("x86", var0)) {
            return Platform_CPU.I386;
        }
        if (equalsIgnoreCase("i386", var0)) {
            return Platform_CPU.I386;
        }
        if (equalsIgnoreCase("i86pc", var0)) {
            return Platform_CPU.I386;
        }
        if (equalsIgnoreCase("i686", var0)) {
            return Platform_CPU.I386;
        }
        if (equalsIgnoreCase("x86_64", var0)) {
            return Platform_CPU.X86_64;
        }
        if (equalsIgnoreCase("amd64", var0)) {
            return Platform_CPU.X86_64;
        }
        if (equalsIgnoreCase("ppc", var0)) {
            if (!Platform_OS.IBMI.equals(determineOS())) {
                return Platform_CPU.PPC;
            } else {
                return Platform_CPU.PPC64;
            }
        }
        if (equalsIgnoreCase("powerpc", var0)) {
            if (!Platform_OS.IBMI.equals(determineOS())) {
                return Platform_CPU.PPC;
            } else {
                return Platform_CPU.PPC64;
            }
        }
        if (equalsIgnoreCase("ppc64", var0)) {
            if (!"little".equals(System.getProperty("sun.cpu.endian"))) {
                return Platform_CPU.PPC64;
            } else {
                return Platform_CPU.PPC64LE;
            }
        }
        if (equalsIgnoreCase("powerpc64", var0)) {
            if (!"little".equals(System.getProperty("sun.cpu.endian"))) {
                return Platform_CPU.PPC64;
            } else {
                return Platform_CPU.PPC64LE;
            }
        }
        if (equalsIgnoreCase("ppc64le", var0)) {
            return Platform_CPU.PPC64LE;
        }
        if (equalsIgnoreCase("powerpc64le", var0)) {
            return Platform_CPU.PPC64LE;
        }
        if (equalsIgnoreCase("s390", var0)) {
            return Platform_CPU.S390X;
        }
        if (equalsIgnoreCase("s390x", var0)) {
            return Platform_CPU.S390X;
        }
        if (equalsIgnoreCase("aarch64", var0)) {
            return Platform_CPU.AARCH64;
        }
        if (equalsIgnoreCase("arm", var0)) {
            return Platform_CPU.ARM;
        }
        if (equalsIgnoreCase("armv7l", var0)) {
            return Platform_CPU.ARM;
        }
        if (equalsIgnoreCase("mips64", var0)) {
            return Platform_CPU.MIPS64EL;
        }
        if (equalsIgnoreCase("mips64el", var0)) {
            return Platform_CPU.MIPS64EL;
        }
        if (equalsIgnoreCase("loongarch64", var0)) {
            return Platform_CPU.LOONGARCH64;
        }
        if (!equalsIgnoreCase("riscv64", var0)) {
            Platform_CPU[] var1 = Platform_CPU.values();
            int var2 = var1.length;
            int var3 = 0;
        } else {
            return Platform_CPU.RISCV64;
        }
        Object var4;
        while (true) {
            if (var3 >= var2) {
                return Platform_CPU.UNKNOWN;
            }
            var4 = var1[var3];
            if (equalsIgnoreCase(var4.name(), var0)) {
                break;
            }
            ++var3;
            continue;
        }
        return ((CPU) var4);
    }

  public Platform(Platform_OS arg0, Platform_CPU arg1, int arg2, int arg3, String arg4) { // было: <init>
        super();
        os = arg0;
        cpu = arg1;
        addressSize = arg2;
        longSize = arg3;
        libPattern = Pattern.compile(arg4);
    }

  private Platform(Platform_OS arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #137 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: aload_1
        //      6: putfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //      9: aload_0
        //     10: invokestatic  #162 // jnr.ffi.Platform.determineCPU:()Ljnr/ffi/Platform$CPU;
        //     13: putfield  #93 // jnr.ffi.Platform.cpu:Ljnr/ffi/Platform$CPU;
        //     16: getstatic  #98 // jnr.ffi.Platform$1.$SwitchMap$jnr$ffi$Platform$OS:[I
        //     19: aload_1
        //     20: invokevirtual  #180 // jnr.ffi.Platform$OS.ordinal:()I
        //     23: iaload
        //     24: tableswitch  default->74, 1->62, 2->74, 3->56, 4->68
        //     56: ldc  #4 // '.*\\.dll$'
        //     58: astore_2
        //     59: goto  77 (offset +18)
        //     62: ldc  #21 // 'lib.*\\.(dylib|jnilib)$'
        //     64: astore_2
        //     65: goto  77 (offset +12)
        //     68: ldc  #22 // 'lib.*\\.(so|a\\(shr.o\\)|a\\(shr_64.o\\)|a|so.[\\.0-9]+)$'
        //     70: astore_2
        //     71: goto  77 (offset +6)
        //     74: ldc  #23 // 'lib.*\\.so.*$'
        //     76: astore_2
        //     77: aload_0
        //     78: aload_2
        //     79: invokestatic  #158 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //     82: putfield  #94 // jnr.ffi.Platform.libPattern:Ljava/util/regex/Pattern;
        //     85: aload_0
        //     86: aload_0
        //     87: getfield  #93 // jnr.ffi.Platform.cpu:Ljnr/ffi/Platform$CPU;
        //     90: invokestatic  #161 // jnr.ffi.Platform.calculateAddressSize:(Ljnr/ffi/Platform$CPU;)I
        //     93: putfield  #92 // jnr.ffi.Platform.addressSize:I
        //     96: aload_0
        //     97: aload_1
        //     98: getstatic  #122 // jnr.ffi.Platform$OS.WINDOWS:Ljnr/ffi/Platform$OS;
        //    101: if_acmpne  109 (offset +8)
        //    104: bipush  32
        //    106: goto  113 (offset +7)
        //    109: aload_0
        //    110: getfield  #92 // jnr.ffi.Platform.addressSize:I
        //    113: putfield  #95 // jnr.ffi.Platform.longSize:I
        //    116: return
    }

  private static int calculateAddressSize(Platform_CPU arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #51 // 'sun.arch.data.model'
        //      2: invokestatic  #133 // java.lang.Integer.getInteger:(Ljava/lang/String;)Ljava/lang/Integer;
        //      5: astore_1
        //      6: aload_1
        //      7: ifnull  28 (offset +21)
        //     10: aload_1
        //     11: invokevirtual  #134 // java.lang.Integer.intValue:()I
        //     14: bipush  32
        //     16: if_icmpeq  128 (offset +112)
        //     19: aload_1
        //     20: invokevirtual  #134 // java.lang.Integer.intValue:()I
        //     23: bipush  64
        //     25: if_icmpeq  128 (offset +103)
        //     28: getstatic  #97 // jnr.ffi.Platform$1.$SwitchMap$jnr$ffi$Platform$CPU:[I
        //     31: aload_0
        //     32: invokevirtual  #173 // jnr.ffi.Platform$CPU.ordinal:()I
        //     35: iaload
        //     36: tableswitch  default->118, 1->100, 2->100, 3->100, 4->109, 5->109, 6->109, 7->109, 8->109, 9->109, 10->109, 11->109, 12->109
        //    100: bipush  32
        //    102: invokestatic  #136 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    105: astore_1
        //    106: goto  128 (offset +22)
        //    109: bipush  64
        //    111: invokestatic  #136 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    114: astore_1
        //    115: goto  128 (offset +13)
        //    118: new  #60 // java.lang.ExceptionInInitializerError
        //    121: dup
        //    122: ldc  #5 // 'Cannot determine cpu address size'
        //    124: invokespecial  #131 // java.lang.ExceptionInInitializerError.<init>:(Ljava/lang/String;)V
        //    127: athrow
        //    128: aload_1
        //    129: invokevirtual  #134 // java.lang.Integer.intValue:()I
        //    132: ireturn
    }

  public static Platform getNativePlatform() {
        return Platform_SingletonHolder.PLATFORM;
    }

    @Deprecated
  public static Platform getPlatform() {
        return Platform_SingletonHolder.PLATFORM;
    }

  public final Platform_OS getOS() {
        return os;
    }

  public final Platform_CPU getCPU() {
        return cpu;
    }

  public final boolean isBSD() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //      4: getstatic  #114 // jnr.ffi.Platform$OS.FREEBSD:Ljnr/ffi/Platform$OS;
        //      7: if_acmpeq  74 (offset +67)
        //     10: aload_0
        //     11: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //     14: getstatic  #119 // jnr.ffi.Platform$OS.OPENBSD:Ljnr/ffi/Platform$OS;
        //     17: if_acmpeq  74 (offset +57)
        //     20: aload_0
        //     21: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //     24: getstatic  #118 // jnr.ffi.Platform$OS.NETBSD:Ljnr/ffi/Platform$OS;
        //     27: if_acmpeq  74 (offset +47)
        //     30: aload_0
        //     31: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //     34: getstatic  #112 // jnr.ffi.Platform$OS.DARWIN:Ljnr/ffi/Platform$OS;
        //     37: if_acmpeq  74 (offset +37)
        //     40: aload_0
        //     41: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //     44: getstatic  #113 // jnr.ffi.Platform$OS.DRAGONFLY:Ljnr/ffi/Platform$OS;
        //     47: if_acmpne  54 (offset +7)
        //     50: iconst_1
        //     51: goto  55 (offset +4)
        //     54: iconst_0
        //     55: aload_0
        //     56: getfield  #96 // jnr.ffi.Platform.os:Ljnr/ffi/Platform$OS;
        //     59: getstatic  #117 // jnr.ffi.Platform$OS.MIDNIGHTBSD:Ljnr/ffi/Platform$OS;
        //     62: if_acmpne  69 (offset +7)
        //     65: iconst_1
        //     66: goto  70 (offset +4)
        //     69: iconst_0
        //     70: ior
        //     71: ifeq  78 (offset +7)
        //     74: iconst_1
        //     75: goto  79 (offset +4)
        //     78: iconst_0
        //     79: ireturn
    }

  public final boolean isUnix() {
        return os != Platform_OS.WINDOWS;
    }

  public final int longSize() {
        return longSize;
    }

  public final int addressSize() {
        return addressSize;
    }

  public final boolean is32Bit() {
        return addressSize == 32;
    }

  public final boolean is64Bit() {
        return addressSize == 64;
    }

  public final boolean isLittleEndian() {
        return "little".equals(System.getProperty("sun.cpu.endian"));
    }

  public final boolean isBigEndian() {
        return "big".equals(System.getProperty("sun.cpu.endian"));
    }

  public final String getOSName() {
        return System.getProperty("os.name", null);
    }

  public String getName() {
        return new StringBuilder().append(cpu).append("-").append(os).toString();
    }

  public String getVersion() {
        return System.getProperty("os.version", null);
    }

  private List getVersionNumbers() {
        String var1 = getVersion();
        Matcher var2;
        ArrayList var3;
        if (var1 != null) {
            var2 = Pattern.compile("[\\d]+").matcher(var1);
            var3 = new ArrayList();
        } else {
            return Collections.emptyList();
        }
        while (var2.find()) {
            var3.add(var2.group());
            continue;
        }
        return var3;
    }

  public int getVersionMajor() {
        List var1 = getVersionNumbers();
        return var1.size() >= 1 ? Integer.parseInt(((String) var1.get(0))) : -1;
    }

  public int getVersionMinor() {
        List var1 = getVersionNumbers();
        return var1.size() >= 2 ? Integer.parseInt(((String) var1.get(1))) : -1;
    }

  public String getStandardCLibraryName() {
        switch (os) {
            case LINUX:
                return "libc.so.6";
            case SOLARIS:
                return "c";
            case DRAGONFLY:
            case FREEBSD:
            case MIDNIGHTBSD:
            case NETBSD:
                return "c";
            case IBMI:
            case AIX:
                return addressSize != 32 ? "libc.a(shr_64.o)" : "libc.a(shr.o)";
            case WINDOWS:
                return "msvcrt";
            default:
            case 5:
                return "c";
        }
    }

  public String mapLibraryName(String arg0) {
        if (!libPattern.matcher(arg0).find()) {
            return System.mapLibraryName(arg0);
        } else {
            return arg0;
        }
    }

  public String locateLibrary(String arg0, List arg1) {
        String var3 = mapLibraryName(arg0);
        Iterator var4 = arg1.iterator();
        File var6;
        while (true) {
            if (!var4.hasNext()) {
                return var3;
            }
            String var5 = ((String) var4.next());
            var6 = new File(var5, var3);
            if (var6.exists()) {
                break;
            }
            continue;
        }
        return var6.getAbsolutePath();
    }

  public String locateLibrary(String arg0, List arg1, Map arg2) {
        return locateLibrary(arg0, arg1);
    }

  public List libraryLocations(String arg0, List arg1) {
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        if (arg1 != null) {
            var4.addAll(arg1);
        }
        var4.addAll(LibraryLoader_DefaultLibPaths.PATHS);
        String var5 = new File(locateLibrary(arg0, var4)).getName();
        Iterator var6 = var4.iterator();
        while (var6.hasNext()) {
            String var7 = ((String) var6.next());
            File var8 = new File(var7, var5);
            if (var8.exists()) {
                var3.add(var8.getAbsolutePath());
            }
            continue;
        }
        return var3;
    }

  private static boolean startsWithIgnoreCase(String arg0, String arg1) {
        return arg0.startsWith(arg1) ? 1 : arg0.toUpperCase(LOCALE).startsWith(arg1.toUpperCase(LOCALE)) ? 1 : arg0.toLowerCase(LOCALE).startsWith(arg1.toLowerCase(LOCALE));
    }

  private static boolean equalsIgnoreCase(String arg0, String arg1) {
        return arg0.equalsIgnoreCase(arg1) ? 1 : arg0.toUpperCase(LOCALE).equals(arg1.toUpperCase(LOCALE)) ? 1 : arg0.toLowerCase(LOCALE).equals(arg1.toLowerCase(LOCALE));
    }

  static Platform access$000() {
        return determinePlatform();
    }

  static Locale access$100() {
        return LOCALE;
    }

   Platform(Platform_OS arg0, Platform_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}