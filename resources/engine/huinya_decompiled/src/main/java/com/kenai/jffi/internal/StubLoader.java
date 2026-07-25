// исходный (обфусцированный) внутренний класс: com.kenai.jffi.internal.StubLoader
package com.kenai.jffi.internal;

import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;
import com.kenai.jffi.Util;
import com.kenai.jffi.internal.StubLoader_CPU;
import com.kenai.jffi.internal.StubLoader_OS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Properties;

public class StubLoader {

    // ---- поля ----
  public static final int VERSION_MAJOR;
  public static final int VERSION_MINOR;
  private static final String versionClassName = "com.kenai.jffi.Version";
  private static final Locale LOCALE;
  private static final String bootPropertyFilename = "boot.properties";
  private static final String bootLibraryPropertyName = "jffi.boot.library.path";
  private static final String stubLibraryName;
  private static final String TMPDIR_ENV;
  private static final String TMPDIR;
  private static final String TMPDIR_RECOMMENDATION;
  public static final String TMPDIR_WRITE_ERROR;
  public static final String TMPDIR_EXEC_ERROR;
  private static volatile StubLoader_OS os;
  private static volatile StubLoader_CPU cpu;
  private static volatile Throwable failureCause;
  private static volatile boolean loaded;
  private static final File jffiExtractDir;
  private static final String jffiExtractName;
  private static final String JFFI_EXTRACT_DIR = "jffi.extract.dir";
  private static final String JFFI_EXTRACT_NAME = "jffi.extract.name";

    static {
        String __stk2;
        VERSION_MAJOR = getVersionField("MAJOR");
        VERSION_MINOR = getVersionField("MINOR");
        LOCALE = Locale.ENGLISH;
        stubLibraryName = String.format("jffi-%d.%d", new Object[]{Integer.valueOf(VERSION_MAJOR), Integer.valueOf(VERSION_MINOR)});
        __stk2 = Platform.getPlatform().getOS() != Platform_OS.WINDOWS ? "TMPDIR" : "TEMP";
        TMPDIR_ENV = __stk2;
        TMPDIR = System.getProperty("java.io.tmpdir");
        TMPDIR_RECOMMENDATION = new StringBuilder().append("Set `").append(TMPDIR_ENV).append("` or Java property `java.io.tmpdir` to a read/write path that is not mounted \"noexec\".").toString();
        TMPDIR_WRITE_ERROR = new StringBuilder().append("Unable to write jffi binary stub to `").append(TMPDIR).append("`.").toString();
        TMPDIR_EXEC_ERROR = new StringBuilder().append("Unable to execute or load jffi binary stub from `").append(TMPDIR).append("`.").toString();
        os = null;
        cpu = null;
        failureCause = null;
        loaded = false;
        String var0 = System.getProperty("jffi.extract.dir");
        jffiExtractDir = var0 == null ? null : new File(var0);
        String var1 = System.getProperty("jffi.extract.name");
        jffiExtractName = var1 == null ? null : var1;
        try {
            load();
            loaded = true;
        } catch (Throwable e1) {
            var0 = e1;
            failureCause = var0;
        }
    }

  public static final boolean isLoaded() {
        return loaded;
    }

  public static final Throwable getFailureCause() {
        return failureCause;
    }

  private static StubLoader_OS determineOS() {
        Object var0 = System.getProperty("os.name").split(" ")[0];
        if (Util.startsWithIgnoreCase(((String) var0), "mac", LOCALE)) {
            return StubLoader_OS.DARWIN;
        } else {
            if (!Util.startsWithIgnoreCase(((String) var0), "darwin", LOCALE)) {
                if (!Util.startsWithIgnoreCase(((String) var0), "linux", LOCALE)) {
                    if (Util.startsWithIgnoreCase(((String) var0), "sunos", LOCALE)) {
                        return StubLoader_OS.SOLARIS;
                    } else {
                        if (!Util.startsWithIgnoreCase(((String) var0), "solaris", LOCALE)) {
                            if (!Util.startsWithIgnoreCase(((String) var0), "aix", LOCALE)) {
                                if (Util.startsWithIgnoreCase(((String) var0), "os400", LOCALE)) {
                                    return StubLoader_OS.IBMI;
                                } else {
                                    if (!Util.startsWithIgnoreCase(((String) var0), "os/400", LOCALE)) {
                                        if (!Util.startsWithIgnoreCase(((String) var0), "openbsd", LOCALE)) {
                                            if (!Util.startsWithIgnoreCase(((String) var0), "freebsd", LOCALE)) {
                                                if (!Util.startsWithIgnoreCase(((String) var0), "dragonfly", LOCALE)) {
                                                    if (!Util.startsWithIgnoreCase(((String) var0), "windows", LOCALE)) {
                                                        throw new RuntimeException("cannot determine operating system");
                                                    } else {
                                                        return StubLoader_OS.WINDOWS;
                                                    }
                                                } else {
                                                    return StubLoader_OS.DRAGONFLY;
                                                }
                                            } else {
                                                return StubLoader_OS.FREEBSD;
                                            }
                                        } else {
                                            return StubLoader_OS.OPENBSD;
                                        }
                                    } else {
                                        return StubLoader_OS.IBMI;
                                    }
                                }
                            } else {
                                return StubLoader_OS.AIX;
                            }
                        } else {
                            return StubLoader_OS.SOLARIS;
                        }
                    }
                } else {
                    return StubLoader_OS.LINUX;
                }
            } else {
                return StubLoader_OS.DARWIN;
            }
        }
    }

  private static StubLoader_CPU determineCPU() {
        String var0 = System.getProperty("os.arch", "unknown");
        if (Util.equalsIgnoreCase("x86", var0, LOCALE)) {
            return StubLoader_CPU.I386;
        }
        if (Util.equalsIgnoreCase("i386", var0, LOCALE)) {
            return StubLoader_CPU.I386;
        }
        if (Util.equalsIgnoreCase("i86pc", var0, LOCALE)) {
            return StubLoader_CPU.I386;
        }
        if (Util.equalsIgnoreCase("x86_64", var0, LOCALE)) {
            return StubLoader_CPU.X86_64;
        }
        if (Util.equalsIgnoreCase("amd64", var0, LOCALE)) {
            return StubLoader_CPU.X86_64;
        }
        if (Util.equalsIgnoreCase("ppc", var0, LOCALE)) {
            return StubLoader_CPU.PPC;
        }
        if (Util.equalsIgnoreCase("powerpc", var0, LOCALE)) {
            return StubLoader_CPU.PPC;
        }
        if (Util.equalsIgnoreCase("ppc64", var0, LOCALE)) {
            if (!"little".equals(System.getProperty("sun.cpu.endian"))) {
                return StubLoader_CPU.PPC64;
            } else {
                return StubLoader_CPU.PPC64LE;
            }
        }
        if (Util.equalsIgnoreCase("powerpc64", var0, LOCALE)) {
            if (!"little".equals(System.getProperty("sun.cpu.endian"))) {
                return StubLoader_CPU.PPC64;
            } else {
                return StubLoader_CPU.PPC64LE;
            }
        }
        if (Util.equalsIgnoreCase("ppc64le", var0, LOCALE)) {
            return StubLoader_CPU.PPC64LE;
        }
        if (Util.equalsIgnoreCase("powerpc64le", var0, LOCALE)) {
            return StubLoader_CPU.PPC64LE;
        }
        if (Util.equalsIgnoreCase("s390", var0, LOCALE)) {
            return StubLoader_CPU.S390X;
        }
        if (Util.equalsIgnoreCase("s390x", var0, LOCALE)) {
            return StubLoader_CPU.S390X;
        }
        if (Util.equalsIgnoreCase("arm", var0, LOCALE)) {
            return StubLoader_CPU.ARM;
        }
        if (Util.equalsIgnoreCase("armv7l", var0, LOCALE)) {
            return StubLoader_CPU.ARM;
        }
        if (Util.equalsIgnoreCase("aarch64", var0, LOCALE)) {
            return StubLoader_CPU.AARCH64;
        }
        if (Util.equalsIgnoreCase("loongarch64", var0, LOCALE)) {
            return StubLoader_CPU.LOONGARCH64;
        }
        if (Util.equalsIgnoreCase("mipsel", var0, LOCALE)) {
            return StubLoader_CPU.MIPSEL;
        }
        if (Util.equalsIgnoreCase("mips64", var0, LOCALE)) {
            return StubLoader_CPU.MIPS64EL;
        }
        if (Util.equalsIgnoreCase("mips64el", var0, LOCALE)) {
            return StubLoader_CPU.MIPS64EL;
        }
        if (!Util.equalsIgnoreCase("riscv64", var0, LOCALE)) {
            StubLoader_CPU[] var1 = StubLoader_CPU.values();
            int var2 = var1.length;
            int var3 = 0;
        } else {
            return StubLoader_CPU.RISCV64;
        }
        Object var4;
        while (true) {
            if (var3 >= var2) {
                throw new RuntimeException("cannot determine CPU");
            }
            var4 = var1[var3];
            if (Util.equalsIgnoreCase(var4.name(), var0, LOCALE)) {
                break;
            }
            ++var3;
            continue;
        }
        return ((CPU) var4);
    }

  public static StubLoader_CPU getCPU() {
        StubLoader_CPU __stk1;
        if (cpu == null) {
            cpu = determineCPU();
            __stk1 = determineCPU();
        } else {
            __stk1 = cpu;
        }
        return __stk1;
    }

  public static StubLoader_OS getOS() {
        StubLoader_OS __stk1;
        if (os == null) {
            os = determineOS();
            __stk1 = determineOS();
        } else {
            __stk1 = os;
        }
        return __stk1;
    }

  private static String getStubLibraryName() {
        return stubLibraryName;
    }

  public static String getPlatformName() {
        if (!getOS().equals(StubLoader_OS.DARWIN)) {
            Object var0 = System.getProperty("os.name").split(" ")[0];
            return new StringBuilder().append(getCPU().name().toLowerCase(LOCALE)).append("-").append(((String) var0)).toString();
        } else {
            return "Darwin";
        }
    }

  private static String getStubLibraryPath() {
        String __stk1;
        __stk1 = !StubLoader_OS.IBMI.equals(getOS()) ? System.mapLibraryName(stubLibraryName) : new StringBuilder().append("lib").append(stubLibraryName).append(".so").toString();
        String var0 = __stk1;
        return new StringBuilder().append("jni/").append(getPlatformName()).append("/").append(((String) var0)).toString();
    }

  public StubLoader() { // было: <init>
        super();
    }

  static void load() {
        String var0 = getStubLibraryName();
        ArrayList var1 = new ArrayList();
        String var2 = getBootPath();
        if (var2 == null) {
            String var3 = System.getProperty("java.library.path");
            if (var3 == null) {
                if (jffiExtractDir == null) {
                    try {
                        loadFromJar(null);
                    } catch (SecurityException e1) {
                        Throwable var4 = e1;
                        throw var4;
                    } catch (Throwable e2) {
                        Throwable var4 = e2;
                    }
                } else {
                    try {
                        loadFromJar(jffiExtractDir);
                    } catch (SecurityException var4) {
                        throw var4;
                    } catch (Throwable e4) {
                        Throwable var4 = e4;
                        UnsatisfiedLinkError var5 = new UnsatisfiedLinkError(new StringBuilder().append("could not load jffi library from ").append(jffiExtractDir).toString());
                        var5.initCause(var4);
                        throw var5;
                    }
                }
            } else {
                if (loadFromBootPath(var0, var3, var1)) {
                    return;
                }
                if (jffiExtractDir == null) {
                    loadFromJar(null);
                    return;
                } else {
                    loadFromJar(jffiExtractDir);
                    return;
                }
            }
        } else {
            if (loadFromBootPath(var0, var2, var1)) {
                return;
            }
            String var3 = System.getProperty("java.library.path");
            if (var3 == null) {
                if (jffiExtractDir == null) {
                    loadFromJar(null);
                    return;
                } else {
                    loadFromJar(jffiExtractDir);
                    return;
                }
            } else {
                if (loadFromBootPath(var0, var3, var1)) {
                    return;
                }
                if (jffiExtractDir == null) {
                    loadFromJar(null);
                    return;
                } else {
                    loadFromJar(jffiExtractDir);
                    return;
                }
            }
        }
    }

  private static String getBootPath() {
        String var0 = System.getProperty("jffi.boot.library.path");
        if (var0 != null) {
            return var0;
        }
        InputStream var1 = getResourceAsStream("boot.properties");
        Properties var2;
        if (var1 == null) {
            return null;
        } else {
            var2 = new Properties();
        }
        try {
            var2.load(var1);
            String var3 = var2.getProperty("jffi.boot.library.path");
        } catch (IOException e3) {
            try {
                Throwable var3 = e3;
                Throwable var4 = null;
            } catch (Throwable e3) {
                try {
                    while (true) {
                        Throwable var6 = e3;
                    }
                } catch (Throwable var6) {
                }
            }
        } catch (Throwable e4) {
            Throwable var6 = e3;
        }
    }

  private static String getAlternateLibraryPath(String arg0) {
        if (!arg0.endsWith("dylib")) {
            return new StringBuilder().append(arg0.substring(0, arg0.lastIndexOf("jnilib"))).append("dylib").toString();
        } else {
            return new StringBuilder().append(arg0.substring(0, arg0.lastIndexOf("dylib"))).append("jnilib").toString();
        }
    }

  private static boolean loadFromBootPath(String arg0, String arg1, Collection arg2) {
        int __stk1;
        int __stk2;
        String[] var3 = arg1.split(File.pathSeparator);
        int var4 = 0;
        while (true) {
            File var6;
            if (var4 >= var3.length) {
                return false;
            } else {
                String var5 = System.mapLibraryName(arg0);
                var6 = new File(new File(((String) var3[var4]), getPlatformName()), var5);
                if (!var6.isFile()) {
                    var6 = new File(new File(((String) var3[var4])), var5);
                }
            }
            String var7 = var6.getAbsolutePath();
            if (!var6.isFile()) {
                if (getOS() != StubLoader_OS.DARWIN) {
                    ++var4;
                    continue;
                } else {
                    var7 = getAlternateLibraryPath(var7);
                    if (!new File(var7).isFile()) {
                        ++var4;
                        continue;
                    } else {
                        try {
                            System.load(var7);
                            __stk2 = 1;
                        } catch (UnsatisfiedLinkError e1) {
                            Throwable var8 = e1;
                            arg2.add(var8);
                        }
                    }
                }
            } else {
                try {
                    System.load(var7);
                    __stk1 = 1;
                } catch (UnsatisfiedLinkError var8) {
                    arg2.add(var8);
                }
            }
        }
        return __stk1;
    }

  static String dlExtension() {
        switch (getOS()) {
            case WINDOWS:
                return "dll";
            case DARWIN:
                return "dylib";
            default:
                return "so";
        }
    }

  private static void loadFromJar(File arg0) {
        String var2 = jffiExtractName;
        try {
            InputStream var3 = getStubLibraryStream();
            try {
                File var1 = calculateExtractPath(arg0, var2);
                if (var2 == null) {
                    unpackLibrary(var1, var3);
                } else {
                    if (!var1.exists()) {
                        unpackLibrary(var1, var3);
                    } else {
                        verifyExistingLibrary(var1, var3);
                    }
                }
            } catch (Throwable var4) {
                if (var3 == null) {
                    throw var4;
                } else {
                    try {
                        var3.close();
                    } catch (Throwable var5) {
                        var4.addSuppressed(var5);
                        throw var4;
                    }
                }
            }
        } catch (IOException e3) {
            Throwable var3 = e3;
            throw tempReadonlyError(var3);
        }
    }

  private static void unpackLibrary(File arg0, InputStream arg1) {
        FileOutputStream var2 = new FileOutputStream(arg0);
        try {
            ReadableByteChannel var3 = Channels.newChannel(arg1);
            long var4 = 0L;
            while (arg1.available() > 0) {
                var4 = var4 + var2.getChannel().transferFrom(var3, var4, ((long) Math.max(4096, arg1.available())));
                continue;
            }
        } catch (Throwable e1) {
            Throwable var3 = e1;
        }
    }

  private static void verifyExistingLibrary(File arg0, InputStream arg1) {
        int var2 = arg1.available();
        try {
            FileInputStream var3 = new FileInputStream(arg0);
            try {
                int var4 = var3.available();
                if (var4 != var2) {
                    throw sizeMismatchError(arg0, var2, var4);
                }
                MessageDigest var5 = MessageDigest.getInstance("SHA-256");
                MessageDigest var6 = MessageDigest.getInstance("SHA-256");
                DigestInputStream var7 = new DigestInputStream(arg1, var5);
                DigestInputStream var8 = new DigestInputStream(var3, var6);
                byte[] var9 = new byte[8192];
                while (arg1.available() > 0) {
                    var7.read(var9);
                    var8.read(var9);
                    continue;
                }
                byte[] var10 = var5.digest();
                byte[] var11 = var6.digest();
                if (!Arrays.equals(var10, var11)) {
                    throw digestMismatchError(arg0);
                }
            } catch (Throwable e2) {
                Throwable var4 = e2;
                try {
                    var3.close();
                } catch (Throwable e2) {
                    Throwable var5 = e2;
                    var4.addSuppressed(var5);
                    throw var4;
                }
            }
        } catch (NoSuchAlgorithmException e3) {
            Throwable var3 = e3;
            throw new IOException(var3);
        }
    }

  private static SecurityException sizeMismatchError(File arg0, int arg1, int arg2) {
        return new SecurityException(new StringBuilder().append("file size mismatch: ").append(arg0).append(" (").append(arg2).append(") does not match packaged library (").append(arg1).append(")").toString());
    }

  private static SecurityException digestMismatchError(File arg0) {
        return new SecurityException(new StringBuilder().append("digest mismatch: ").append(arg0).append(" does not match packaged library").toString());
    }

  static File calculateExtractPath(File arg0, String arg1) {
        if (arg1 != null) {
            if (null == arg1) {
                arg1 = new StringBuilder().append("jffi-").append(VERSION_MAJOR).append(".").append(VERSION_MINOR).toString();
            } else {
                if (arg1.isEmpty()) {
                    arg1 = new StringBuilder().append("jffi-").append(VERSION_MAJOR).append(".").append(VERSION_MINOR).toString();
                }
            }
            if (!arg1.endsWith(dlExtension())) {
                arg1 = new StringBuilder().append(arg1).append(".").append(dlExtension()).toString();
            }
            File var2;
            if (null != arg0) {
                var2 = new File(arg0, arg1);
            } else {
                var2 = new File(TMPDIR, arg1);
            }
            return var2;
        } else {
            return calculateExtractPath(arg0);
        }
    }

  static File calculateExtractPath(File arg0) {
        File var1;
        if (null != arg0) {
            var1 = File.createTempFile("jffi", new StringBuilder().append(".").append(dlExtension()).toString(), arg0);
        } else {
            var1 = File.createTempFile("jffi", new StringBuilder().append(".").append(dlExtension()).toString());
        }
        var1.deleteOnExit();
        return var1;
    }

  private static IOException tempReadonlyError(IOException arg0) {
        return new IOException(new StringBuilder().append(TMPDIR_WRITE_ERROR).append(" ").append(TMPDIR_RECOMMENDATION).toString(), arg0);
    }

  private static UnsatisfiedLinkError tempLoadError(UnsatisfiedLinkError arg0) {
        return new UnsatisfiedLinkError(new StringBuilder().append(TMPDIR_EXEC_ERROR).append(" ").append(TMPDIR_RECOMMENDATION).append("\n").append(arg0.getLocalizedMessage()).toString());
    }

  private static InputStream getStubLibraryStream() {
        String var0 = getStubLibraryPath();
        String[] var1 = new String[]{var0, new StringBuilder().append("/").append(var0).toString()};
        String[] var2 = var1;
        int var3 = var2.length;
        int var4 = 0;
        InputStream var6;
        while (true) {
            var6 = /* ? NoneType */;
            if (var4 >= var3) {
                throw new UnsatisfiedLinkError(new StringBuilder().append("could not locate stub library in jar file.  Tried ").append(Arrays.deepToString(var1)).toString());
            } else {
                Object var5 = var2[var4];
                var6 = getResourceAsStream(((String) var5));
                if (var6 == null) {
                    if (getOS() == StubLoader_OS.DARWIN) {
                        var6 = getResourceAsStream(getAlternateLibraryPath(((String) var5)));
                    }
                }
            }
            if (var6 != null) {
                break;
            }
            ++var4;
            continue;
        }
        return var6;
    }

  private static InputStream getResourceAsStream(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_3
        //      1: anewarray  #98 // java.lang.ClassLoader
        //      4: dup
        //      5: iconst_0
        //      6: invokestatic  #223 // java.lang.ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //      9: aastore
        //     10: dup
        //     11: iconst_1
        //     12: ldc  #86 // com.kenai.jffi.internal.StubLoader
        //     14: invokevirtual  #220 // java.lang.Class.getClassLoader:()Ljava/lang/ClassLoader;
        //     17: aastore
        //     18: dup
        //     19: iconst_2
        //     20: invokestatic  #249 // java.lang.Thread.currentThread:()Ljava/lang/Thread;
        //     23: invokevirtual  #250 // java.lang.Thread.getContextClassLoader:()Ljava/lang/ClassLoader;
        //     26: aastore
        //     27: astore_1
        //     28: aload_1
        //     29: astore_2
        //     30: aload_2
        //     31: arraylength
        //     32: istore_3
        //     33: iconst_0
        //     34: istore  4
        //     36: iload  4
        //     38: iload_3
        //     39: if_icmpge  77 (offset +38)
        //     42: aload_2
        //     43: iload  4
        //     45: aaload
        //     46: astore  5
        //     48: aload  5
        //     50: ifnonnull  56 (offset +6)
        //     53: goto  71 (offset +18)
        //     56: aload  5
        //     58: aload_0
        //     59: invokevirtual  #222 // java.lang.ClassLoader.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;
        //     62: dup
        //     63: astore  6
        //     65: ifnull  71 (offset +6)
        //     68: aload  6
        //     70: areturn
        //     71: iinc  4, 1
        //     74: goto  36 (offset -38)
        //     77: aconst_null
        //     78: areturn
    }

  private static int getVersionField(String arg0) {
        int __stk1;
        try {
            Class var1 = Class.forName("com.kenai.jffi.Version");
            __stk1 = (((Integer) var1.getField(arg0).get(var1))).intValue();
        } catch (Throwable e1) {
            Throwable var1 = e1;
            throw new RuntimeException(var1);
        }
    }

  static Locale access$000() {
        return LOCALE;
    }

}