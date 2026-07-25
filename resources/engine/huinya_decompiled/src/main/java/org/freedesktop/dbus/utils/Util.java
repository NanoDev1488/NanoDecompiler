// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.Util
package org.freedesktop.dbus.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.freedesktop.dbus.TypeRef;
import org.freedesktop.dbus.utils.Hexdump;
import org.freedesktop.dbus.utils.IThrowingSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Util {

    // ---- поля ----
  private static final Random RANDOM;
  private static final Logger LOGGER;
  private static final char[] SYMBOLS;

    static {
        RANDOM = new Random();
        LOGGER = LoggerFactory.getLogger(Util.class);
        StringBuilder var0 = new StringBuilder();
        int var1 = 48;
        while (var1 <= 57) {
            var0.append(var1);
            var1 = ((char) (var1 + 1));
            continue;
        }
        var1 = 97;
        while (var1 <= 122) {
            var0.append(var1);
            var1 = ((char) (var1 + 1));
            continue;
        }
        var1 = 65;
        while (var1 <= 90) {
            var0.append(var1);
            var1 = ((char) (var1 + 1));
            continue;
        }
        SYMBOLS = var0.toString().toCharArray();
    }

  private Util() { // было: <init>
        super();
    }

  public static Properties readProperties(File arg0) {
        Properties __stk1;
        if (!arg0.exists()) {
            return null;
        }
        try {
            __stk1 = readProperties(new FileInputStream(arg0));
        } catch (FileNotFoundException var1) {
            LOGGER.info("Could not load properties file: " + String.valueOf(arg0), var1);
        }
    }

  public static Properties readProperties(InputStream arg0) {
        Properties __stk1;
        Properties var1 = new Properties();
        if (arg0 == null) {
            return null;
        }
        try {
            var1.load(arg0);
            __stk1 = var1;
        } catch (IOException var2) {
            LOGGER.warn("Could not properties: ", var2);
            return null;
        }
    }

  public static boolean isBlank(String arg0) {
        if (arg0 != null) {
            return arg0.isBlank();
        } else {
            return true;
        }
    }

  public static boolean strEquals(String arg0, String arg1) {
        if (arg0 != arg1) {
            if (arg0 == null) {
                return false;
            } else {
                if (arg1 != null) {
                    if (arg0.length() == arg1.length()) {
                        return arg0.equals(arg1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public static boolean isEmpty(String arg0) {
        if (arg0 != null) {
            return arg0.isEmpty();
        } else {
            return true;
        }
    }

  public static String randomString(int arg0) {
        char[] var1;
        int var2;
        if (arg0 > 0) {
            var1 = new char[arg0];
            var2 = 0;
        } else {
            return "";
        }
        while (var2 < var1.length) {
            var1[var2] = SYMBOLS[RANDOM.nextInt(SYMBOLS.length)];
            ++var2;
            continue;
        }
        return new String(var1);
    }

  public static String upperCaseFirstChar(String arg0) {
        if (arg0 != null) {
            if (!arg0.isEmpty()) {
                return arg0.substring(0, 1).toUpperCase() + arg0.substring(1);
            } else {
                return arg0;
            }
        } else {
            return null;
        }
    }

  public static String snakeToCamelCase(String arg0) {
        Matcher var2;
        String var3;
        if (!isBlank(arg0)) {
            Pattern var1 = Pattern.compile("_[a-zA-Z]");
            var2 = var1.matcher(arg0);
            var3 = arg0;
        } else {
            return arg0;
        }
        String var3;
        while (var2.find()) {
            String var4 = var2.group();
            String var5 = var4.replace("_", "");
            var5 = var5.toUpperCase();
            var3 = var3.replaceFirst(var4, var5);
            continue;
        }
        return var3;
    }

  public static String abbreviate(String arg0, int arg1) {
        if (arg0 != null) {
            if (arg0.length() > arg1) {
                return arg0.substring(0, arg1 - 3) + "...";
            } else {
                return arg0;
            }
        } else {
            return null;
        }
    }

  public static boolean isValidNetworkPort(int arg0, boolean arg1) {
        if (!arg1) {
            return arg0 <= 1024 ? 0 : arg0 < 65536;
        } else {
            return arg0 <= 0 ? 0 : arg0 < 65536;
        }
    }

  public static boolean isValidNetworkPort(String arg0, boolean arg1) {
        if (!isInteger(arg0, false)) {
            return false;
        } else {
            return isValidNetworkPort(Integer.parseInt(arg0), arg1);
        }
    }

  public static boolean isInteger(String arg0, boolean arg1) {
        if (arg0 != null) {
            String var2 = "[0-9]+$";
            var2 = !arg1 ? "^" + var2 : "^-?" + var2;
            return arg0.matches(var2);
        } else {
            return false;
        }
    }

  public static List readFileToList(String arg0) {
        return getTextfileFromUrl(arg0, Charset.defaultCharset(), false);
    }

  public static String readFileToString(File arg0) {
        return String.join(System.lineSeparator(), readFileToList(arg0.getAbsolutePath()));
    }

  public static List getTextfileFromUrl(String arg0, Charset arg1, boolean arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ifnonnull  6 (offset +5)
        //      4: aconst_null
        //      5: areturn
        //      6: aload_0
        //      7: astore_3
        //      8: aload_3
        //      9: ldc  #13 // '://'
        //     11: invokevirtual  #141 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //     14: ifne  24 (offset +10)
        //     17: aload_3
        //     18: invokedynamic  #245 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     23: astore_3
        //     24: aload_3
        //     25: ldc  #37 // 'file:/'
        //     27: invokevirtual  #152 // java.lang.String.startsWith:(Ljava/lang/String;)Z
        //     30: ifeq  57 (offset +27)
        //     33: new  #80 // java.net.URL
        //     36: dup
        //     37: ldc  #36 // 'file'
        //     39: ldc  #3 // ''
        //     41: aload_3
        //     42: ldc  #39 // 'file:\\/{1,2}'
        //     44: ldc  #3 // ''
        //     46: invokevirtual  #151 // java.lang.String.replaceFirst:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //     49: invokespecial  #176 // java.net.URL.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     52: astore  4
        //     54: goto  67 (offset +13)
        //     57: new  #80 // java.net.URL
        //     60: dup
        //     61: aload_3
        //     62: invokespecial  #175 // java.net.URL.<init>:(Ljava/lang/String;)V
        //     65: astore  4
        //     67: aload  4
        //     69: invokevirtual  #177 // java.net.URL.openConnection:()Ljava/net/URLConnection;
        //     72: astore  5
        //     74: aload  5
        //     76: iconst_1
        //     77: invokevirtual  #179 // java.net.URLConnection.setDoInput:(Z)V
        //     80: aload  5
        //     82: iconst_0
        //     83: invokevirtual  #180 // java.net.URLConnection.setUseCaches:(Z)V
        //     86: aload  5
        //     88: invokevirtual  #178 // java.net.URLConnection.getInputStream:()Ljava/io/InputStream;
        //     91: aload_1
        //     92: iload_2
        //     93: invokestatic  #215 // org.freedesktop.dbus.utils.Util.readTextFileFromStream:(Ljava/io/InputStream;Ljava/nio/charset/Charset;Z)Ljava/util/List;
        //     96: areturn
        //     97: astore  4
        //     99: iload_2
        //    100: ifne  115 (offset +15)
        //    103: getstatic  #111 // org.freedesktop.dbus.utils.Util.LOGGER:Lorg/slf4j/Logger;
        //    106: ldc  #21 // 'Error while reading file:'
        //    108: aload  4
        //    110: invokeinterface  #239 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    115: aconst_null
        //    116: areturn
        //       Exception table:
        //         from 24 to 96 target 97 type java.io.IOException
    }

  public static List readTextFileFromStream(InputStream arg0, Charset arg1, boolean arg2) {
        if (arg0 == null) {
            return null;
        }
        try {
            BufferedReader var4 = new BufferedReader(new InputStreamReader(arg0, arg1));
            try {
                ArrayList var3 = new ArrayList();
                while (true) {
                    String var5 = var4.readLine();
                    if (var5 == null) {
                        break;
                    }
                    var3.add(var5);
                    continue;
                }
            } catch (Throwable e2) {
                Throwable var5 = e2;
                try {
                    var4.close();
                } catch (Throwable var6) {
                    var5.addSuppressed(var6);
                    throw var5;
                }
            }
        } catch (IOException e3) {
            Throwable var3 = e3;
            if (!arg2) {
                LOGGER.warn("Error while reading file:", var3);
            }
        }
    }

  public static boolean writeTextFile(String arg0, String arg1, Charset arg2, boolean arg3) {
        String var4;
        File var5;
        if (!isBlank(arg0)) {
            var4 = "";
            if (arg3) {
                var5 = new File(arg0);
                if (var5.exists()) {
                    var4 = readFileToString(var5);
                }
            }
        } else {
            return false;
        }
        String var4 = var4 + arg1;
        try {
            OutputStreamWriter var5 = new OutputStreamWriter(new FileOutputStream(arg0), arg2);
            try {
                var5.write(var4);
            } catch (Throwable var6) {
                try {
                    var5.close();
                } catch (Throwable var7) {
                    var6.addSuppressed(var7);
                    throw var6;
                }
            }
        } catch (IOException e3) {
            Throwable var5 = e3;
            LOGGER.error("Could not write file to '" + arg0 + "'", var5);
            return false;
        }
    }

  public static String getHostName() {
        String __stk1;
        try {
            __stk1 = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var0) {
            return null;
        }
    }

  public static boolean collectionContainsAny(Collection arg0, Collection arg1) {
        if (arg0 == null) {
            return false;
        }
        if (arg1 != null) {
            Iterator var2 = arg1.iterator();
        } else {
            return false;
        }
        while (true) {
            if (!var2.hasNext()) {
                return false;
            }
            Object var3 = var2.next();
            if (arg0.contains(var3)) {
                break;
            }
            continue;
        }
        return true;
    }

  public static String getCurrentUser() {
        String[] var0 = new String[]{"user.name", "USER", "USERNAME"};
        int var1 = 0;
        String var2;
        while (true) {
            if (var1 >= var0.length) {
                return null;
            }
            var2 = System.getProperty(((String) var0[var1]));
            if (!isEmpty(var2)) {
                break;
            }
            ++var1;
            continue;
        }
        return var2;
    }

  public static boolean isMacOs() {
        String var0 = System.getProperty("os.name");
        return var0 == null ? 0 : var0.toLowerCase(Locale.US).startsWith("mac");
    }

  public static boolean isFreeBsd() {
        String var0 = System.getProperty("os.name");
        return var0 == null ? 0 : var0.toLowerCase(Locale.US).startsWith("freebsd");
    }

  public static boolean isWindows() {
        String var0 = System.getProperty("os.name");
        return var0 == null ? 0 : var0.toLowerCase(Locale.US).startsWith("windows");
    }

  public static int getJavaVersion() {
        String var0 = System.getProperty("java.version");
        if (!var0.startsWith("1.")) {
            int var1 = var0.indexOf(46);
            if (var1 != -1) {
                var0 = var0.substring(0, var1);
            }
        } else {
            var0 = var0.substring(2, 3);
        }
        return Integer.parseInt(var0);
    }

  public static String genGUID() {
        byte[] var0 = new byte[16];
        RANDOM.nextBytes(var0);
        return Hexdump.toHex(var0, false);
    }

  public static String createDynamicSessionAddress(boolean arg0, boolean arg1) {
        String var2 = "unix:";
        String var3 = new File(System.getProperty("java.io.tmpdir"), "dbus-XXXXXXXXXX").getAbsolutePath();
        do {
            StringBuilder var4 = new StringBuilder();
            int var5 = 0;
            while (var5 < 10) {
                var4.append(((char) (Math.abs(RANDOM.nextInt(0, 2147483647)) % 26)) + 65);
                ++var5;
                continue;
            }
            var3 = var3.replaceAll("..........$", var4.toString());
            LoggerFactory.getLogger(Util.class).trace("Trying path {}", var3);
        } while (new File(var3).exists());
        var2 = !arg1 ? var2 + "path=" + var3 : var2 + "abstract=" + var3;
        if (arg0) {
            var2 = var2 + ",listen=true";
        }
        var2 = var2 + ",guid=" + genGUID();
        LoggerFactory.getLogger(Util.class).debug("Created Session address: {}", var2);
        return var2;
    }

  public static int checkIntInRange(int arg0, int arg1, int arg2) {
        if (arg0 < arg1) {
            throw new IllegalArgumentException("Value " + arg0 + " is out ouf range (< " + arg1 + " && > " + arg2 + ")");
        } else {
            if (arg0 > arg2) {
                throw new IllegalArgumentException("Value " + arg0 + " is out ouf range (< " + arg1 + " && > " + arg2 + ")");
            } else {
                return arg0;
            }
        }
    }

  public static void setFilePermissions(Path arg0, String arg1, String arg2, Set arg3) {
        Objects.requireNonNull(arg0, "Path required");
        UserPrincipalLookupService var4 = arg0.getFileSystem().getUserPrincipalLookupService();
        if (var4 != null) {
            if (!isBlank(arg1)) {
                try {
                    UserPrincipal var5 = var4.lookupPrincipalByName(arg1);
                    if (var5 != null) {
                        (((PosixFileAttributeView) Files.getFileAttributeView(arg0, PosixFileAttributeView.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}))).setOwner(var5);
                    }
                } catch (IOException e1) {
                    Throwable var5 = e1;
                    LOGGER.error("Could not change owner of {} to {}", new Object[]{arg0, arg1, var5});
                }
            }
        } else {
            LOGGER.error("Unable to set user/group permissions on {}", arg0);
            return;
        }
        if (!isBlank(arg2)) {
            try {
                GroupPrincipal var5 = var4.lookupPrincipalByGroupName(arg2);
                if (var5 != null) {
                    (((PosixFileAttributeView) Files.getFileAttributeView(arg0, PosixFileAttributeView.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}))).setGroup(var5);
                }
            } catch (IOException e2) {
                Throwable var5 = e2;
                LOGGER.error("Could not change group of {} to {}", new Object[]{arg0, arg2, var5});
            }
        }
        if (!isWindows()) {
            if (arg3 != null) {
                try {
                    Files.setPosixFilePermissions(arg0, arg3);
                } catch (Exception e3) {
                    Throwable var5 = e3;
                    LOGGER.error("Could not set file permissions of {} to {}", new Object[]{arg0, arg3, var5});
                }
            }
        }
    }

  public static void waitFor(String arg0, IThrowingSupplier arg1, long arg2, long arg3) {
        long var6 = 0L;
        int var8 = 0;
        Object var9 = null;
        try {
            while (true) {
                var9 = null;
                var8 = (((Boolean) arg1.get())).booleanValue();
            }
            if (var9 == null) {
                throw new IllegalStateException(arg0 + " not available in the specified time of " + arg2 + " ms");
            } else {
                throw var9;
            }
        } catch (Throwable var10) {
            var9 = var10;
            var8 = 0;
        }
        if (var6 >= arg2) {
            if (var9 == null) {
                throw new IllegalStateException(arg0 + " not available in the specified time of " + arg2 + " ms");
            } else {
                throw var9;
            }
        }
        try {
            Thread.sleep(arg3);
        } catch (InterruptedException e2) {
            Throwable var10 = e2;
            LOGGER.debug("Interrupted while waiting for {}", arg0);
            Thread.currentThread().interrupt();
        }
    }

  public static Type unwrapTypeRef(Class arg0) {
        Objects.requireNonNull(ParameterizedType.class);
        Objects.requireNonNull(ParameterizedType.class);
        return ((Type) Arrays.stream(arg0.getGenericInterfaces()).filter(lp0 -> ParameterizedType.class.isInstance(lp0)).map(lp0 -> ParameterizedType.class.cast(lp0)).filter(lp0 -> lambda$unwrapTypeRef$0(((ParameterizedType) lp0))).map(lp0 -> lambda$unwrapTypeRef$1(((ParameterizedType) lp0))).findFirst().orElse(null));
    }

  public static Object[] toObjectArray(Object arg0) {
        if (arg0 == null) {
            return new Object[0];
        }
        int var1;
        Object[] var2;
        int var3;
        if (arg0.getClass().isArray()) {
            var1 = Array.getLength(arg0);
            var2 = new Object[var1];
            var3 = 0;
        } else {
            return new Object[0];
        }
        while (var3 < var1) {
            var2[var3] = Array.get(arg0, var3);
            ++var3;
            continue;
        }
        return var2;
    }

  private static Type lambda$unwrapTypeRef$1(ParameterizedType arg0) {
        return ((Type) arg0.getActualTypeArguments()[0]);
    }

  private static boolean lambda$unwrapTypeRef$0(ParameterizedType arg0) {
        return TypeRef.class.equals(arg0.getRawType());
    }

}