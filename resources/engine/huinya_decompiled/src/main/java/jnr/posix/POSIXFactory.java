// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXFactory
package jnr.posix;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.LibraryOption;
import jnr.ffi.Platform;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.mapper.FunctionMapper;
import jnr.posix.AixLibC;
import jnr.posix.AixPOSIX;
import jnr.posix.DragonFlyPOSIX;
import jnr.posix.FreeBSDPOSIX;
import jnr.posix.JavaPOSIX;
import jnr.posix.LazyPOSIX;
import jnr.posix.LinuxLibC;
import jnr.posix.LinuxPOSIX;
import jnr.posix.MacOSPOSIX;
import jnr.posix.OpenBSDPOSIX;
import jnr.posix.POSIX;
import jnr.posix.POSIXFactory_DefaultLibCProvider;
import jnr.posix.POSIXHandler;
import jnr.posix.POSIXTypeMapper;
import jnr.posix.SimpleFunctionMapper_Builder;
import jnr.posix.SolarisLibC;
import jnr.posix.SolarisPOSIX;
import jnr.posix.UnixLibC;
import jnr.posix.WindowsLibC;
import jnr.posix.WindowsPOSIX;
import jnr.posix.util.DefaultPOSIXHandler;

public class POSIXFactory {

    // ---- поля ----
  private static final Class BOGUS_HACK;
  public static final Platform NATIVE_PLATFORM;
  public static final String STANDARD_C_LIBRARY_NAME;

    static {
        BOGUS_HACK = Struct.class;
        NATIVE_PLATFORM = Platform.getNativePlatform();
        STANDARD_C_LIBRARY_NAME = NATIVE_PLATFORM.getStandardCLibraryName();
    }

  public POSIXFactory() { // было: <init>
        super();
    }

  public static POSIX getPOSIX(POSIXHandler arg0, boolean arg1) {
        return new LazyPOSIX(arg0, arg1);
    }

  public static POSIX getPOSIX() {
        return getPOSIX(new DefaultPOSIXHandler(), true);
    }

  public static POSIX getJavaPOSIX(POSIXHandler arg0) {
        return new JavaPOSIX(arg0);
    }

  public static POSIX getJavaPOSIX() {
        return getJavaPOSIX(new DefaultPOSIXHandler());
    }

  public static POSIX getNativePOSIX(POSIXHandler arg0) {
        return loadNativePOSIX(arg0);
    }

  public static POSIX getNativePOSIX() {
        return getNativePOSIX(new DefaultPOSIXHandler());
    }

  static POSIX loadPOSIX(POSIXHandler arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_2
        //      2: iload_1
        //      3: ifeq  86 (offset +83)
        //      6: aload_0
        //      7: invokestatic  #133 // jnr.posix.POSIXFactory.loadNativePOSIX:(Ljnr/posix/POSIXHandler;)Ljnr/posix/POSIX;
        //     10: astore_2
        //     11: aload_2
        //     12: ifnull  27 (offset +15)
        //     15: new  #67 // jnr.posix.CheckedPOSIX
        //     18: dup
        //     19: aload_2
        //     20: aload_0
        //     21: invokespecial  #114 // jnr.posix.CheckedPOSIX.<init>:(Ljnr/posix/POSIX;Ljnr/posix/POSIXHandler;)V
        //     24: goto  28 (offset +4)
        //     27: aconst_null
        //     28: astore_2
        //     29: aload_0
        //     30: invokeinterface  #145 // jnr.posix.POSIXHandler.isVerbose:()Z, count 1
        //     35: ifeq  61 (offset +26)
        //     38: aload_2
        //     39: ifnull  53 (offset +14)
        //     42: getstatic  #91 // java.lang.System.err:Ljava/io/PrintStream;
        //     45: ldc  #3 // 'Successfully loaded native POSIX impl.'
        //     47: invokevirtual  #102 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //     50: goto  61 (offset +11)
        //     53: getstatic  #91 // java.lang.System.err:Ljava/io/PrintStream;
        //     56: ldc  #2 // 'Failed to load native POSIX impl; falling back on Java impl. Unsupported OS.'
        //     58: invokevirtual  #102 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //     61: goto  86 (offset +25)
        //     64: astore_3
        //     65: aload_0
        //     66: invokeinterface  #145 // jnr.posix.POSIXHandler.isVerbose:()Z, count 1
        //     71: ifeq  86 (offset +15)
        //     74: getstatic  #91 // java.lang.System.err:Ljava/io/PrintStream;
        //     77: ldc  #1 // 'Failed to load native POSIX impl; falling back on Java impl. Stacktrace follows.'
        //     79: invokevirtual  #102 // java.io.PrintStream.println:(Ljava/lang/String;)V
        //     82: aload_3
        //     83: invokevirtual  #104 // java.lang.Throwable.printStackTrace:()V
        //     86: aload_2
        //     87: ifnonnull  95 (offset +8)
        //     90: aload_0
        //     91: invokestatic  #123 // jnr.posix.POSIXFactory.getJavaPOSIX:(Ljnr/posix/POSIXHandler;)Ljnr/posix/POSIX;
        //     94: astore_2
        //     95: aload_2
        //     96: areturn
        //       Exception table:
        //         from 6 to 61 target 64 type java.lang.Throwable
    }

  private static POSIX loadNativePOSIX(POSIXHandler arg0) {
        switch (NATIVE_PLATFORM.getOS()) {
            case DARWIN:
                return loadMacOSPOSIX(arg0);
            case LINUX:
                return loadLinuxPOSIX(arg0);
            case FREEBSD:
                return loadFreeBSDPOSIX(arg0);
            case DRAGONFLY:
                return loadDragonFlyPOSIX(arg0);
            case OPENBSD:
                return loadOpenBSDPOSIX(arg0);
            case SOLARIS:
                return loadSolarisPOSIX(arg0);
            case AIX:
                return loadAixPOSIX(arg0);
            case WINDOWS:
                return loadWindowsPOSIX(arg0);
            default:
                return null;
        }
    }

  public static POSIX loadLinuxPOSIX(POSIXHandler arg0) {
        return new LinuxPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadMacOSPOSIX(POSIXHandler arg0) {
        return new MacOSPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadSolarisPOSIX(POSIXHandler arg0) {
        return new SolarisPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadFreeBSDPOSIX(POSIXHandler arg0) {
        return new FreeBSDPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadDragonFlyPOSIX(POSIXHandler arg0) {
        return new DragonFlyPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadOpenBSDPOSIX(POSIXHandler arg0) {
        return new OpenBSDPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadWindowsPOSIX(POSIXHandler arg0) {
        return new WindowsPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  public static POSIX loadAixPOSIX(POSIXHandler arg0) {
        return new AixPOSIX(POSIXFactory_DefaultLibCProvider.INSTANCE, arg0);
    }

  private static String[] libraries() {
        String[] __stk8;
        switch (NATIVE_PLATFORM.getOS()) {
            case LINUX:
                return new String[]{STANDARD_C_LIBRARY_NAME};
            case SOLARIS:
                return new String[]{"socket", "nsl", STANDARD_C_LIBRARY_NAME};
            case FREEBSD:
            case DRAGONFLY:
            case NETBSD:
                return new String[]{STANDARD_C_LIBRARY_NAME};
            case AIX:
                if (Runtime.getSystemRuntime().addressSize() != 4) {
                    __stk8 = new String[]{"libc.a(shr_64.o)"};
                } else {
                    __stk8 = new String[]{"libc.a(shr.o)"};
                }
                return __stk8;
            case WINDOWS:
                return new String[]{"msvcrt", "kernel32"};
            default:
            case 5:
                return new String[]{STANDARD_C_LIBRARY_NAME};
        }
    }

  private static Class libraryInterface() {
        switch (NATIVE_PLATFORM.getOS()) {
            case LINUX:
                return LinuxLibC.class;
            case AIX:
                return AixLibC.class;
            case SOLARIS:
                return SolarisLibC.class;
            case WINDOWS:
                return WindowsLibC.class;
            default:
            case 3:
            case 4:
            case 5:
                return UnixLibC.class;
        }
    }

  private static FunctionMapper functionMapper() {
        switch (NATIVE_PLATFORM.getOS()) {
            case AIX:
                return new SimpleFunctionMapper_Builder().map("stat", "stat64x").map("fstat", "fstat64x").map("lstat", "lstat64x").map("stat64", "stat64x").map("fstat64", "fstat64x").map("lstat64", "lstat64x").build();
            case WINDOWS:
                return new SimpleFunctionMapper_Builder().map("getpid", "_getpid").map("chmod", "_chmod").map("fstat", "_fstat64").map("stat", "_stat64").map("umask", "_umask").map("isatty", "_isatty").map("read", "_read").map("write", "_write").map("close", "_close").map("getcwd", "_getcwd").map("unlink", "_unlink").map("access", "_access").map("open", "_open").map("dup", "_dup").map("dup2", "_dup2").map("lseek", "_lseek").map("ftruncate", "_chsize").build();
            case SOLARIS:
                return !jnr.posix.util.Platform.IS_32_BIT ? null : new SimpleFunctionMapper_Builder().map("stat", "stat64").map("fstat", "fstat64").map("lstat", "lstat64").build();
            default:
                return null;
        }
    }

  private static Map options() {
        HashMap var0 = new HashMap();
        FunctionMapper var1 = functionMapper();
        if (var1 != null) {
            var0.put(LibraryOption.FunctionMapper, var1);
        }
        var0.put(LibraryOption.TypeMapper, POSIXTypeMapper.INSTANCE);
        var0.put(LibraryOption.LoadNow, Boolean.TRUE);
        return Collections.unmodifiableMap(var0);
    }

  static Class access$000() {
        return libraryInterface();
    }

  static String[] access$100() {
        return libraries();
    }

  static Map access$200() {
        return options();
    }

}