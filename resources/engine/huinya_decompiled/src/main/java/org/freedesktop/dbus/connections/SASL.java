// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.SASL
package org.freedesktop.dbus.connections;

import com.sun.security.auth.module.UnixSystem;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.attribute.PosixFilePermission;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import org.freedesktop.dbus.connections.SASL_Command;
import org.freedesktop.dbus.connections.SASL_SaslCommand;
import org.freedesktop.dbus.connections.SASL_SaslResult;
import org.freedesktop.dbus.connections.config.SaslConfig;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.utils.Hexdump;
import org.freedesktop.dbus.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SASL {

    // ---- поля ----
  public static final int AUTH_NONE = 0;
  public static final int AUTH_EXTERNAL = 1;
  public static final int AUTH_SHA = 2;
  public static final int AUTH_ANON = 4;
  public static final int LOCK_TIMEOUT = 1000;
  public static final int NEW_KEY_TIMEOUT_SECONDS = 300;
  public static final int EXPIRE_KEYS_TIMEOUT_SECONDS = 420;
  public static final int MAX_TIME_TRAVEL_SECONDS = 300;
  public static final int COOKIE_TIMEOUT = 240;
  public static final String COOKIE_CONTEXT = "org_freedesktop_java";
  private static final String AUTH_TYPE_EXTERNAL = "EXTERNAL";
  private static final String AUTH_TYPE_DBUS_COOKIE_SHA1 = "DBUS_COOKIE_SHA1";
  private static final String AUTH_TYPE_ANONYMOUS = "ANONYMOUS";
  private static final String INVALID_CMD_ERR = "Got invalid command";
  private static final int MAX_READ_BYTES = 1048576;
  private static final Random RANDOM;
  private static final Collator COL;
  private static final String SYSPROP_USER_HOME;
  private static final String DBUS_TEST_HOME_DIR;
  private static final File DBUS_KEYRINGS_DIR;
  private static final Set BAD_FILE_PERMISSIONS;
  private String challenge;
  private String cookie;
  private final Logger logger;
  private boolean fileDescriptorSupported;
  private final SaslConfig saslConfig;

    static {
        RANDOM = new Random();
        COL = Collator.getInstance();
        COL.setDecomposition(2);
        COL.setStrength(0);
        SYSPROP_USER_HOME = System.getProperty("user.home");
        DBUS_TEST_HOME_DIR = System.getProperty("DBUS_TEST_HOMEDIR");
        DBUS_KEYRINGS_DIR = new File(SYSPROP_USER_HOME, ".dbus-keyrings");
        BAD_FILE_PERMISSIONS = Set.of(PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.OTHERS_EXECUTE, PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE);
    }

  public SASL(SaslConfig arg0) { // было: <init>
        super();
        challenge = "";
        cookie = "";
        logger = LoggerFactory.getLogger(getClass());
        saslConfig = ((SaslConfig) Objects.requireNonNull(arg0, "Sasl Configuration required"));
    }

  private String findCookie(String arg0, String arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #139 // org.freedesktop.dbus.connections.SASL.DBUS_KEYRINGS_DIR:Ljava/io/File;
        //      3: astore_3
        //      4: getstatic  #140 // org.freedesktop.dbus.connections.SASL.DBUS_TEST_HOME_DIR:Ljava/lang/String;
        //      7: invokestatic  #281 // org.freedesktop.dbus.utils.Util.isBlank:(Ljava/lang/String;)Z
        //     10: ifne  24 (offset +14)
        //     13: new  #58 // java.io.File
        //     16: dup
        //     17: getstatic  #140 // org.freedesktop.dbus.connections.SASL.DBUS_TEST_HOME_DIR:Ljava/lang/String;
        //     20: invokespecial  #176 // java.io.File.<init>:(Ljava/lang/String;)V
        //     23: astore_3
        //     24: new  #58 // java.io.File
        //     27: dup
        //     28: aload_3
        //     29: aload_1
        //     30: invokespecial  #175 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     33: astore  4
        //     35: invokestatic  #203 // java.lang.System.currentTimeMillis:()J
        //     38: ldc2_w  #122 // 1000L
        //     41: ldiv
        //     42: lstore  5
        //     44: new  #57 // java.io.BufferedReader
        //     47: dup
        //     48: new  #61 // java.io.InputStreamReader
        //     51: dup
        //     52: new  #59 // java.io.FileInputStream
        //     55: dup
        //     56: aload  4
        //     58: invokespecial  #184 // java.io.FileInputStream.<init>:(Ljava/io/File;)V
        //     61: invokespecial  #185 // java.io.InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //     64: invokespecial  #172 // java.io.BufferedReader.<init>:(Ljava/io/Reader;)V
        //     67: astore  7
        //     69: aconst_null
        //     70: astore  8
        //     72: aconst_null
        //     73: astore  9
        //     75: aconst_null
        //     76: aload  7
        //     78: invokevirtual  #174 // java.io.BufferedReader.readLine:()Ljava/lang/String;
        //     81: dup
        //     82: astore  8
        //     84: if_acmpeq  177 (offset +93)
        //     87: aload  8
        //     89: ldc  #17 // ' '
        //     91: invokevirtual  #197 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //     94: astore  10
        //     96: aload  10
        //     98: arraylength
        //     99: iconst_3
        //    100: if_icmpeq  106 (offset +6)
        //    103: goto  75 (offset -28)
        //    106: aload  10
        //    108: iconst_1
        //    109: aaload
        //    110: invokestatic  #187 // java.lang.Long.parseLong:(Ljava/lang/String;)J
        //    113: lstore  11
        //    115: goto  123 (offset +8)
        //    118: astore  13
        //    120: goto  75 (offset -45)
        //    123: aload  10
        //    125: iconst_0
        //    126: aaload
        //    127: aload_2
        //    128: invokevirtual  #192 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    131: ifeq  174 (offset +43)
        //    134: lload  11
        //    136: lconst_0
        //    137: lcmp
        //    138: iflt  174 (offset +36)
        //    141: lload  5
        //    143: lload  11
        //    145: ldc2_w  #118 // 300L
        //    148: lsub
        //    149: lcmp
        //    150: iflt  174 (offset +24)
        //    153: lload  5
        //    155: lload  11
        //    157: ldc2_w  #120 // 420L
        //    160: ladd
        //    161: lcmp
        //    162: ifge  174 (offset +12)
        //    165: aload  10
        //    167: iconst_2
        //    168: aaload
        //    169: astore  9
        //    171: goto  177 (offset +6)
        //    174: goto  75 (offset -99)
        //    177: aload  9
        //    179: astore  10
        //    181: aload  7
        //    183: invokevirtual  #173 // java.io.BufferedReader.close:()V
        //    186: aload  10
        //    188: areturn
        //    189: astore  8
        //    191: aload  7
        //    193: invokevirtual  #173 // java.io.BufferedReader.close:()V
        //    196: goto  208 (offset +12)
        //    199: astore  9
        //    201: aload  8
        //    203: aload  9
        //    205: invokevirtual  #207 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    208: aload  8
        //    210: athrow
        //       Exception table:
        //         from 106 to 115 target 118 type java.lang.NumberFormatException
        //         from 69 to 181 target 189 type java.lang.Throwable
        //         from 191 to 196 target 199 type java.lang.Throwable
    }

  private void addCookie(String arg0, String arg1, long arg2, String arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #139 // org.freedesktop.dbus.connections.SASL.DBUS_KEYRINGS_DIR:Ljava/io/File;
        //      3: astore  6
        //      5: getstatic  #140 // org.freedesktop.dbus.connections.SASL.DBUS_TEST_HOME_DIR:Ljava/lang/String;
        //      8: invokestatic  #281 // org.freedesktop.dbus.utils.Util.isBlank:(Ljava/lang/String;)Z
        //     11: ifne  26 (offset +15)
        //     14: new  #58 // java.io.File
        //     17: dup
        //     18: getstatic  #140 // org.freedesktop.dbus.connections.SASL.DBUS_TEST_HOME_DIR:Ljava/lang/String;
        //     21: invokespecial  #176 // java.io.File.<init>:(Ljava/lang/String;)V
        //     24: astore  6
        //     26: new  #58 // java.io.File
        //     29: dup
        //     30: aload  6
        //     32: aload_1
        //     33: invokespecial  #175 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     36: astore  7
        //     38: new  #58 // java.io.File
        //     41: dup
        //     42: aload  6
        //     44: aload_1
        //     45: invokedynamic  #301 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     50: invokespecial  #175 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     53: astore  8
        //     55: new  #58 // java.io.File
        //     58: dup
        //     59: aload  6
        //     61: aload_1
        //     62: invokedynamic  #302 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     67: invokespecial  #175 // java.io.File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //     70: astore  9
        //     72: aload  6
        //     74: invokevirtual  #180 // java.io.File.exists:()Z
        //     77: ifne  137 (offset +60)
        //     80: aload  6
        //     82: invokevirtual  #181 // java.io.File.mkdirs:()Z
        //     85: ifeq  119 (offset +34)
        //     88: invokestatic  #282 // org.freedesktop.dbus.utils.Util.isWindows:()Z
        //     91: ifne  215 (offset +124)
        //     94: aload  6
        //     96: invokevirtual  #183 // java.io.File.toPath:()Ljava/nio/file/Path;
        //     99: aconst_null
        //    100: aconst_null
        //    101: getstatic  #135 // java.nio.file.attribute.PosixFilePermission.OWNER_READ:Ljava/nio/file/attribute/PosixFilePermission;
        //    104: getstatic  #136 // java.nio.file.attribute.PosixFilePermission.OWNER_WRITE:Ljava/nio/file/attribute/PosixFilePermission;
        //    107: getstatic  #134 // java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE:Ljava/nio/file/attribute/PosixFilePermission;
        //    110: invokestatic  #287 // java.util.Set.of:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Set;
        //    113: invokestatic  #283 // org.freedesktop.dbus.utils.Util.setFilePermissions:(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V
        //    116: goto  215 (offset +99)
        //    119: new  #103 // org.freedesktop.dbus.exceptions.AuthenticationException
        //    122: dup
        //    123: aload  6
        //    125: invokestatic  #198 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    128: invokedynamic  #303 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    133: invokespecial  #271 // org.freedesktop.dbus.exceptions.AuthenticationException.<init>:(Ljava/lang/String;)V
        //    136: athrow
        //    137: invokestatic  #282 // org.freedesktop.dbus.utils.Util.isWindows:()Z
        //    140: ifne  215 (offset +75)
        //    143: aload  6
        //    145: invokevirtual  #183 // java.io.File.toPath:()Ljava/nio/file/Path;
        //    148: iconst_1
        //    149: anewarray  #80 // java.nio.file.LinkOption
        //    152: dup
        //    153: iconst_0
        //    154: getstatic  #124 // java.nio.file.LinkOption.NOFOLLOW_LINKS:Ljava/nio/file/LinkOption;
        //    157: aastore
        //    158: invokestatic  #219 // java.nio.file.Files.getPosixFilePermissions:(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;
        //    161: astore  10
        //    163: aload  10
        //    165: getstatic  #137 // org.freedesktop.dbus.connections.SASL.BAD_FILE_PERMISSIONS:Ljava/util/Set;
        //    168: invokestatic  #280 // org.freedesktop.dbus.utils.Util.collectionContainsAny:(Ljava/util/Collection;Ljava/util/Collection;)Z
        //    171: ifeq  215 (offset +44)
        //    174: aload_0
        //    175: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //    178: invokevirtual  #269 // org.freedesktop.dbus.connections.config.SaslConfig.isStrictCookiePermissions:()Z
        //    181: ifeq  202 (offset +21)
        //    184: new  #103 // org.freedesktop.dbus.exceptions.AuthenticationException
        //    187: dup
        //    188: aload  8
        //    190: invokestatic  #198 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    193: invokedynamic  #304 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    198: invokespecial  #271 // org.freedesktop.dbus.exceptions.AuthenticationException.<init>:(Ljava/lang/String;)V
        //    201: athrow
        //    202: aload_0
        //    203: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    206: ldc  #30 // 'DBus keyring directory {} should have permissions 0700'
        //    208: aload  8
        //    210: invokeinterface  #299 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    215: aload  8
        //    217: invokestatic  #198 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    220: invokedynamic  #305 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    225: aload  8
        //    227: dup
        //    228: invokestatic  #230 // java.util.Objects.requireNonNull:(Ljava/lang/Object;)Ljava/lang/Object;
        //    231: pop
        //    232: invokedynamic  #306 // invokedynamic get:(Ljava/io/File;)Lorg/freedesktop/dbus/utils/IThrowingSupplier;
        //    237: ldc2_w  #122 // 1000L
        //    240: ldc2_w  #114 // 50L
        //    243: invokestatic  #284 // org.freedesktop.dbus.utils.Util.waitFor:(Ljava/lang/String;Lorg/freedesktop/dbus/utils/IThrowingSupplier;JJ)V
        //    246: new  #87 // java.util.ArrayList
        //    249: dup
        //    250: invokespecial  #228 // java.util.ArrayList.<init>:()V
        //    253: astore  10
        //    255: aload  7
        //    257: invokevirtual  #180 // java.io.File.exists:()Z
        //    260: ifeq  375 (offset +115)
        //    263: new  #57 // java.io.BufferedReader
        //    266: dup
        //    267: new  #61 // java.io.InputStreamReader
        //    270: dup
        //    271: new  #59 // java.io.FileInputStream
        //    274: dup
        //    275: aload  7
        //    277: invokespecial  #184 // java.io.FileInputStream.<init>:(Ljava/io/File;)V
        //    280: invokespecial  #185 // java.io.InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    283: invokespecial  #172 // java.io.BufferedReader.<init>:(Ljava/io/Reader;)V
        //    286: astore  11
        //    288: aconst_null
        //    289: astore  12
        //    291: aconst_null
        //    292: aload  11
        //    294: invokevirtual  #174 // java.io.BufferedReader.readLine:()Ljava/lang/String;
        //    297: dup
        //    298: astore  12
        //    300: if_acmpeq  345 (offset +45)
        //    303: aload  12
        //    305: ldc  #17 // ' '
        //    307: invokevirtual  #197 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    310: astore  13
        //    312: aload  13
        //    314: iconst_1
        //    315: aaload
        //    316: invokestatic  #187 // java.lang.Long.parseLong:(Ljava/lang/String;)J
        //    319: lstore  14
        //    321: lload_3
        //    322: lload  14
        //    324: lsub
        //    325: ldc2_w  #116 // 240L
        //    328: lcmp
        //    329: ifge  342 (offset +13)
        //    332: aload  10
        //    334: aload  12
        //    336: invokeinterface  #286 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    341: pop
        //    342: goto  291 (offset -51)
        //    345: aload  11
        //    347: invokevirtual  #173 // java.io.BufferedReader.close:()V
        //    350: goto  375 (offset +25)
        //    353: astore  12
        //    355: aload  11
        //    357: invokevirtual  #173 // java.io.BufferedReader.close:()V
        //    360: goto  372 (offset +12)
        //    363: astore  13
        //    365: aload  12
        //    367: aload  13
        //    369: invokevirtual  #207 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    372: aload  12
        //    374: athrow
        //    375: aload  10
        //    377: aload_2
        //    378: lload_3
        //    379: aload  5
        //    381: invokedynamic  #307 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;JLjava/lang/String;)Ljava/lang/String;
        //    386: invokeinterface  #286 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    391: pop
        //    392: aload  9
        //    394: invokevirtual  #183 // java.io.File.toPath:()Ljava/nio/file/Path;
        //    397: invokestatic  #205 // java.lang.System.lineSeparator:()Ljava/lang/String;
        //    400: aload  10
        //    402: invokestatic  #195 // java.lang.String.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //    405: invokestatic  #218 // java.nio.charset.Charset.defaultCharset:()Ljava/nio/charset/Charset;
        //    408: iconst_3
        //    409: anewarray  #81 // java.nio.file.OpenOption
        //    412: dup
        //    413: iconst_0
        //    414: getstatic  #125 // java.nio.file.StandardOpenOption.CREATE:Ljava/nio/file/StandardOpenOption;
        //    417: aastore
        //    418: dup
        //    419: iconst_1
        //    420: getstatic  #127 // java.nio.file.StandardOpenOption.WRITE:Ljava/nio/file/StandardOpenOption;
        //    423: aastore
        //    424: dup
        //    425: iconst_2
        //    426: getstatic  #126 // java.nio.file.StandardOpenOption.TRUNCATE_EXISTING:Ljava/nio/file/StandardOpenOption;
        //    429: aastore
        //    430: invokestatic  #220 // java.nio.file.Files.writeString:(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;
        //    433: pop
        //    434: aload  9
        //    436: aload  7
        //    438: invokevirtual  #182 // java.io.File.renameTo:(Ljava/io/File;)Z
        //    441: ifne  493 (offset +52)
        //    444: aload  7
        //    446: invokevirtual  #179 // java.io.File.delete:()Z
        //    449: ifne  468 (offset +19)
        //    452: aload_0
        //    453: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    456: ldc  #50 // 'Unable to delete cookie file {}'
        //    458: aload  7
        //    460: invokeinterface  #299 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    465: goto  493 (offset +28)
        //    468: aload  9
        //    470: aload  7
        //    472: invokevirtual  #182 // java.io.File.renameTo:(Ljava/io/File;)Z
        //    475: ifne  493 (offset +18)
        //    478: aload_0
        //    479: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    482: ldc  #51 // 'Unable to rename cookie file {} to {}'
        //    484: aload  9
        //    486: aload  7
        //    488: invokeinterface  #300 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    493: aload  8
        //    495: invokevirtual  #179 // java.io.File.delete:()Z
        //    498: ifne  514 (offset +16)
        //    501: aload_0
        //    502: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    505: ldc  #26 // 'Cannot delete lock file {}'
        //    507: aload  8
        //    509: invokeinterface  #293 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    514: return
        //       Exception table:
        //         from 288 to 345 target 353 type java.lang.Throwable
        //         from 355 to 360 target 363 type java.lang.Throwable
    }

  private String stupidlyEncode(String arg0) {
        return Hexdump.toHex(arg0.getBytes(), false);
    }

  private String stupidlyEncode(byte[] arg0) {
        return Hexdump.toHex(arg0, false);
    }

  private byte getNibble(char arg0) {
        byte __stk1;
        switch (arg0) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                __stk1 = ((byte) (arg0 - 48));
                break;
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
                __stk1 = ((byte) (arg0 - 65 + 10));
                break;
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
                __stk1 = ((byte) (arg0 - 97 + 10));
                break;
            default:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
                __stk1 = 0;
        }
        return __stk1;
    }

  private String stupidlyDecode(String arg0) {
        char[] var2 = new char[arg0.length()];
        char[] var3 = new char[var2.length / 2];
        arg0.getChars(0, arg0.length(), var2, 0);
        int var4 = 0;
        int var5 = 0;
        while (var5 < var3.length) {
            int var6 = 0;
            var6 = var6 | getNibble(var2[var4]) << 4;
            var6 = var6 | getNibble(var2[var4 + 1]);
            var3[var5] = ((char) var6);
            var4 = var4 + 2;
            ++var5;
            continue;
        }
        return new String(var3);
    }

  public SASL_Command receive(SocketChannel arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #67 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #199 // java.lang.StringBuilder.<init>:()V
        //      7: astore_2
        //      8: iconst_1
        //      9: invokestatic  #210 // java.nio.ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //     12: astore_3
        //     13: iconst_1
        //     14: istore  4
        //     16: iconst_0
        //     17: istore  5
        //     19: iload  4
        //     21: ifeq  139 (offset +118)
        //     24: aload_1
        //     25: aload_3
        //     26: invokevirtual  #216 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //     29: istore  6
        //     31: iload  5
        //     33: iload  6
        //     35: iadd
        //     36: istore  5
        //     38: aload_3
        //     39: iconst_0
        //     40: invokevirtual  #214 // java.nio.ByteBuffer.position:(I)Ljava/nio/ByteBuffer;
        //     43: pop
        //     44: iload  6
        //     46: iconst_m1
        //     47: if_icmpne  60 (offset +13)
        //     50: new  #104 // org.freedesktop.dbus.exceptions.SocketClosedException
        //     53: dup
        //     54: ldc  #48 // 'Stream unexpectedly short (broken pipe)'
        //     56: invokespecial  #273 // org.freedesktop.dbus.exceptions.SocketClosedException.<init>:(Ljava/lang/String;)V
        //     59: athrow
        //     60: aload_3
        //     61: invokevirtual  #213 // java.nio.ByteBuffer.position:()I
        //     64: istore  7
        //     66: iload  7
        //     68: iload  6
        //     70: if_icmpge  121 (offset +51)
        //     73: aload_3
        //     74: invokevirtual  #212 // java.nio.ByteBuffer.get:()B
        //     77: istore  8
        //     79: iload  8
        //     81: ifeq  115 (offset +34)
        //     84: iload  8
        //     86: bipush  13
        //     88: if_icmpne  94 (offset +6)
        //     91: goto  115 (offset +24)
        //     94: iload  8
        //     96: bipush  10
        //     98: if_icmpne  107 (offset +9)
        //    101: iconst_0
        //    102: istore  4
        //    104: goto  121 (offset +17)
        //    107: aload_2
        //    108: iload  8
        //    110: i2c
        //    111: invokevirtual  #200 // java.lang.StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    114: pop
        //    115: iinc  7, 1
        //    118: goto  66 (offset -52)
        //    121: aload_3
        //    122: invokevirtual  #211 // java.nio.ByteBuffer.clear:()Ljava/nio/ByteBuffer;
        //    125: pop
        //    126: iload  5
        //    128: ldc  #9 // 1048576
        //    130: if_icmple  136 (offset +6)
        //    133: goto  139 (offset +6)
        //    136: goto  19 (offset -117)
        //    139: aload_0
        //    140: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    143: ldc  #53 // 'received: {}'
        //    145: aload_2
        //    146: invokeinterface  #297 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    151: new  #96 // org.freedesktop.dbus.connections.SASL$Command
        //    154: dup
        //    155: aload_2
        //    156: invokevirtual  #202 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    159: invokespecial  #253 // org.freedesktop.dbus.connections.SASL$Command.<init>:(Ljava/lang/String;)V
        //    162: areturn
        //    163: astore  6
        //    165: aload_0
        //    166: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    169: ldc  #25 // 'Cannot create command.'
        //    171: aload  6
        //    173: invokeinterface  #294 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    178: new  #103 // org.freedesktop.dbus.exceptions.AuthenticationException
        //    181: dup
        //    182: ldc  #34 // 'Failed to authenticate.'
        //    184: aload  6
        //    186: invokespecial  #272 // org.freedesktop.dbus.exceptions.AuthenticationException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    189: athrow
        //       Exception table:
        //         from 151 to 162 target 163 type java.lang.Exception
    }

  public void send(SocketChannel arg0, SASL_SaslCommand arg1, String[] arg2) {
        StringBuilder var4 = new StringBuilder();
        var4.append(arg1.name());
        String[] var5 = arg2;
        int var6 = var5.length;
        int var7 = 0;
        while (var7 < var6) {
            Object var8 = var5[var7];
            var4.append(' ');
            var4.append(((String) var8));
            ++var7;
            continue;
        }
        var4.append('\r');
        var4.append('\n');
        logger.trace("sending: {}", var4);
        arg0.write(ByteBuffer.wrap(var4.toString().getBytes()));
    }

   SASL_SaslResult doChallenge(int arg0, SASL_Command arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_1
        //      1: lookupswitch  default->365, 2->28, 4->341
        //     28: aload_0
        //     29: aload_2
        //     30: invokevirtual  #255 // org.freedesktop.dbus.connections.SASL$Command.getData:()Ljava/lang/String;
        //     33: invokevirtual  #250 // org.freedesktop.dbus.connections.SASL.stupidlyDecode:(Ljava/lang/String;)Ljava/lang/String;
        //     36: ldc  #17 // ' '
        //     38: invokevirtual  #197 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //     41: astore_3
        //     42: aload_0
        //     43: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //     46: invokeinterface  #295 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //     51: aload_0
        //     52: aload_3
        //     53: invokedynamic  #308 // invokedynamic run:(Lorg/freedesktop/dbus/connections/SASL;[Ljava/lang/String;)Ljava/lang/Runnable;
        //     58: invokestatic  #277 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //     61: iconst_3
        //     62: aload_3
        //     63: arraylength
        //     64: if_icmpeq  82 (offset +18)
        //     67: aload_0
        //     68: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //     71: ldc  #42 // 'Reply is not length 3'
        //     73: invokeinterface  #289 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //     78: getstatic  #167 // org.freedesktop.dbus.connections.SASL$SaslResult.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //     81: areturn
        //     82: aload_3
        //     83: iconst_0
        //     84: aaload
        //     85: astore  4
        //     87: aload_3
        //     88: iconst_1
        //     89: aaload
        //     90: astore  5
        //     92: aload_3
        //     93: iconst_2
        //     94: aaload
        //     95: astore  6
        //     97: aconst_null
        //     98: astore  7
        //    100: ldc  #44 // 'SHA'
        //    102: invokestatic  #222 // java.security.MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    105: astore  7
        //    107: goto  129 (offset +22)
        //    110: astore  8
        //    112: aload_0
        //    113: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    116: ldc  #27 // 'Could not find SHA algorithm'
        //    118: aload  8
        //    120: invokeinterface  #291 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    125: getstatic  #167 // org.freedesktop.dbus.connections.SASL$SaslResult.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    128: areturn
        //    129: bipush  8
        //    131: newarray  byte
        //    133: astore  8
        //    135: invokestatic  #206 // java.lang.System.nanoTime:()J
        //    138: invokestatic  #188 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    141: invokestatic  #234 // java.util.Optional.of:(Ljava/lang/Object;)Ljava/util/Optional;
        //    144: invokedynamic  #309 // invokedynamic apply:()Ljava/util/function/Function;
        //    149: invokevirtual  #233 // java.util.Optional.map:(Ljava/util/function/Function;)Ljava/util/Optional;
        //    152: invokevirtual  #232 // java.util.Optional.get:()Ljava/lang/Object;
        //    155: checkcast  #63 // java.lang.Long
        //    158: invokevirtual  #186 // java.lang.Long.longValue:()J
        //    161: lstore  9
        //    163: lload  9
        //    165: aload  8
        //    167: iconst_0
        //    168: bipush  8
        //    170: invokestatic  #274 // org.freedesktop.dbus.messages.Message.marshallintBig:(J[BII)V
        //    173: aload_0
        //    174: aload  7
        //    176: aload  8
        //    178: invokevirtual  #221 // java.security.MessageDigest.digest:([B)[B
        //    181: invokevirtual  #252 // org.freedesktop.dbus.connections.SASL.stupidlyEncode:([B)Ljava/lang/String;
        //    184: astore  11
        //    186: aload  7
        //    188: invokevirtual  #223 // java.security.MessageDigest.reset:()V
        //    191: new  #108 // org.freedesktop.dbus.utils.TimeMeasure
        //    194: dup
        //    195: invokespecial  #278 // org.freedesktop.dbus.utils.TimeMeasure.<init>:()V
        //    198: astore  12
        //    200: aconst_null
        //    201: astore  13
        //    203: aload  13
        //    205: ifnonnull  233 (offset +28)
        //    208: aload  12
        //    210: invokevirtual  #279 // org.freedesktop.dbus.utils.TimeMeasure.getElapsed:()J
        //    213: ldc2_w  #122 // 1000L
        //    216: lcmp
        //    217: ifge  233 (offset +16)
        //    220: aload_0
        //    221: aload  4
        //    223: aload  5
        //    225: invokevirtual  #242 // org.freedesktop.dbus.connections.SASL.findCookie:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    228: astore  13
        //    230: goto  203 (offset -27)
        //    233: aload  13
        //    235: ifnonnull  257 (offset +22)
        //    238: aload_0
        //    239: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    242: ldc  #31 // 'Did not find a cookie in context {}  with ID {}'
        //    244: aload  4
        //    246: aload  5
        //    248: invokeinterface  #290 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    253: getstatic  #167 // org.freedesktop.dbus.connections.SASL$SaslResult.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    256: areturn
        //    257: aload  6
        //    259: aload  11
        //    261: aload  13
        //    263: invokedynamic  #310 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    268: astore  14
        //    270: aload  7
        //    272: aload  14
        //    274: invokevirtual  #193 // java.lang.String.getBytes:()[B
        //    277: invokevirtual  #221 // java.security.MessageDigest.digest:([B)[B
        //    280: astore  8
        //    282: aload_0
        //    283: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    286: invokeinterface  #295 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    291: ifeq  312 (offset +21)
        //    294: aload_0
        //    295: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    298: ldc  #43 // 'Response: {} hash: {}'
        //    300: aload  14
        //    302: aload  8
        //    304: invokestatic  #275 // org.freedesktop.dbus.utils.Hexdump.format:([B)Ljava/lang/String;
        //    307: invokeinterface  #298 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    312: aload_0
        //    313: aload  8
        //    315: invokevirtual  #252 // org.freedesktop.dbus.connections.SASL.stupidlyEncode:([B)Ljava/lang/String;
        //    318: astore  14
        //    320: aload_2
        //    321: aload_0
        //    322: aload  11
        //    324: aload  14
        //    326: invokedynamic  #311 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    331: invokevirtual  #251 // org.freedesktop.dbus.connections.SASL.stupidlyEncode:(Ljava/lang/String;)Ljava/lang/String;
        //    334: invokevirtual  #258 // org.freedesktop.dbus.connections.SASL$Command.setResponse:(Ljava/lang/String;)V
        //    337: getstatic  #168 // org.freedesktop.dbus.connections.SASL$SaslResult.OK:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    340: areturn
        //    341: aload_2
        //    342: aload_2
        //    343: invokevirtual  #255 // org.freedesktop.dbus.connections.SASL$Command.getData:()Ljava/lang/String;
        //    346: ifnonnull  354 (offset +8)
        //    349: ldc  #10 // ''
        //    351: goto  358 (offset +7)
        //    354: aload_2
        //    355: invokevirtual  #255 // org.freedesktop.dbus.connections.SASL$Command.getData:()Ljava/lang/String;
        //    358: invokevirtual  #258 // org.freedesktop.dbus.connections.SASL$Command.setResponse:(Ljava/lang/String;)V
        //    361: getstatic  #168 // org.freedesktop.dbus.connections.SASL$SaslResult.OK:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    364: areturn
        //    365: aload_0
        //    366: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    369: ldc  #41 // 'Not DBUS_COOKIE_SHA1 authtype.'
        //    371: invokeinterface  #289 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //    376: getstatic  #167 // org.freedesktop.dbus.connections.SASL$SaslResult.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    379: areturn
        //       Exception table:
        //         from 100 to 107 target 110 type java.security.NoSuchAlgorithmException
    }

   SASL_SaslResult doResponse(int arg0, String arg1, String arg2, SASL_Command arg3) {
        Object var5 = null;
        try {
            var5 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException var6) {
            logger.error("SHA hash algorithm not available", var6);
            return SASL_SaslResult.ERROR;
        }
    }

  public String[] convertAuthTypes(int arg0) {
        String[] __stk8;
        switch (arg0) {
            case 1:
                __stk8 = new String[]{"EXTERNAL"};
                break;
            case 2:
                __stk8 = new String[]{"DBUS_COOKIE_SHA1"};
                break;
            case 4:
                __stk8 = new String[]{"ANONYMOUS"};
                break;
            case 3:
                __stk8 = new String[]{"EXTERNAL", "DBUS_COOKIE_SHA1"};
                break;
            case 6:
                __stk8 = new String[]{"ANONYMOUS", "DBUS_COOKIE_SHA1"};
                break;
            case 5:
                __stk8 = new String[]{"ANONYMOUS", "EXTERNAL"};
                break;
            case 7:
                __stk8 = new String[]{"ANONYMOUS", "EXTERNAL", "DBUS_COOKIE_SHA1"};
                break;
            default:
                __stk8 = new String[0];
        }
        return __stk8;
    }

  public boolean auth(SocketChannel arg0, AbstractTransport arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_3
        //      2: aconst_null
        //      3: astore  4
        //      5: aload_0
        //      6: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //      9: invokevirtual  #267 // org.freedesktop.dbus.connections.config.SaslConfig.getSaslUid:()Ljava/util/OptionalLong;
        //     12: aload_0
        //     13: invokevirtual  #244 // org.freedesktop.dbus.connections.SASL.getUserId:()J
        //     16: invokevirtual  #235 // java.util.OptionalLong.orElse:(J)J
        //     19: lstore  5
        //     21: aload_0
        //     22: lload  5
        //     24: invokedynamic  #313 // invokedynamic makeConcatWithConstants:(J)Ljava/lang/String;
        //     29: invokevirtual  #251 // org.freedesktop.dbus.connections.SASL.stupidlyEncode:(Ljava/lang/String;)Ljava/lang/String;
        //     32: astore_3
        //     33: iconst_0
        //     34: istore  8
        //     36: iconst_0
        //     37: istore  9
        //     39: getstatic  #150 // org.freedesktop.dbus.connections.SASL$SaslAuthState.INITIAL_STATE:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //     42: astore  10
        //     44: aload  10
        //     46: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //     49: if_acmpeq  1708 (offset +1659)
        //     52: aload  10
        //     54: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //     57: if_acmpeq  1708 (offset +1651)
        //     60: aload_0
        //     61: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //     64: ldc  #40 // 'Mode: {} AUTH state: {}'
        //     66: aload_0
        //     67: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //     70: invokevirtual  #266 // org.freedesktop.dbus.connections.config.SaslConfig.getMode:()Lorg/freedesktop/dbus/connections/SASL$SaslMode;
        //     73: aload  10
        //     75: invokeinterface  #298 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     80: aload_0
        //     81: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //     84: invokevirtual  #266 // org.freedesktop.dbus.connections.config.SaslConfig.getMode:()Lorg/freedesktop/dbus/connections/SASL$SaslMode;
        //     87: invokevirtual  #262 // org.freedesktop.dbus.connections.SASL$SaslMode.ordinal:()I
        //     90: lookupswitch  default->1706, 0->941, 1->116
        //    116: aload  10
        //    118: invokevirtual  #259 // org.freedesktop.dbus.connections.SASL$SaslAuthState.ordinal:()I
        //    121: tableswitch  default->933, 0->152, 1->187, 2->610, 3->857
        //    152: aload_1
        //    153: iconst_1
        //    154: newarray  byte
        //    156: dup
        //    157: iconst_0
        //    158: iconst_0
        //    159: bastore
        //    160: invokestatic  #215 // java.nio.ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //    163: invokevirtual  #217 // java.nio.channels.SocketChannel.write:(Ljava/nio/ByteBuffer;)I
        //    166: pop
        //    167: aload_0
        //    168: aload_1
        //    169: getstatic  #158 // org.freedesktop.dbus.connections.SASL$SaslCommand.AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    172: iconst_0
        //    173: anewarray  #66 // java.lang.String
        //    176: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    179: getstatic  #154 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_DATA:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    182: astore  10
        //    184: goto  44 (offset -140)
        //    187: aload_0
        //    188: aload_1
        //    189: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //    192: astore  7
        //    194: aload  7
        //    196: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    199: invokevirtual  #261 // org.freedesktop.dbus.connections.SASL$SaslCommand.ordinal:()I
        //    202: tableswitch  default->590, 1->248, 2->358, 3->472, 4->590, 5->590, 6->408, 7->590, 8->544
        //    248: aload_0
        //    249: iload  9
        //    251: aload  7
        //    253: invokevirtual  #240 // org.freedesktop.dbus.connections.SASL.doChallenge:(ILorg/freedesktop/dbus/connections/SASL$Command;)Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //    256: invokevirtual  #263 // org.freedesktop.dbus.connections.SASL$SaslResult.ordinal:()I
        //    259: tableswitch  default->335, 0->307, 1->284, 2->335
        //    284: aload_0
        //    285: aload_1
        //    286: getstatic  #161 // org.freedesktop.dbus.connections.SASL$SaslCommand.DATA:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    289: iconst_1
        //    290: anewarray  #66 // java.lang.String
        //    293: dup
        //    294: iconst_0
        //    295: aload  7
        //    297: invokevirtual  #257 // org.freedesktop.dbus.connections.SASL$Command.getResponse:()Ljava/lang/String;
        //    300: aastore
        //    301: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    304: goto  44 (offset -260)
        //    307: aload_0
        //    308: aload_1
        //    309: getstatic  #161 // org.freedesktop.dbus.connections.SASL$SaslCommand.DATA:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    312: iconst_1
        //    313: anewarray  #66 // java.lang.String
        //    316: dup
        //    317: iconst_0
        //    318: aload  7
        //    320: invokevirtual  #257 // org.freedesktop.dbus.connections.SASL$Command.getResponse:()Ljava/lang/String;
        //    323: aastore
        //    324: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    327: getstatic  #155 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_OK:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    330: astore  10
        //    332: goto  44 (offset -288)
        //    335: aload_0
        //    336: aload_1
        //    337: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    340: iconst_1
        //    341: anewarray  #66 // java.lang.String
        //    344: dup
        //    345: iconst_0
        //    346: aload  7
        //    348: invokevirtual  #257 // org.freedesktop.dbus.connections.SASL$Command.getResponse:()Ljava/lang/String;
        //    351: aastore
        //    352: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    355: goto  44 (offset -311)
        //    358: iload  8
        //    360: iload  9
        //    362: ior
        //    363: istore  8
        //    365: aload  7
        //    367: invokevirtual  #256 // org.freedesktop.dbus.connections.SASL$Command.getMechs:()I
        //    370: iload  8
        //    372: iconst_m1
        //    373: ixor
        //    374: iand
        //    375: istore  11
        //    377: aload_0
        //    378: iload  11
        //    380: aload_3
        //    381: aload_1
        //    382: invokevirtual  #245 // org.freedesktop.dbus.connections.SASL.handleReject:(ILjava/lang/String;Ljava/nio/channels/SocketChannel;)I
        //    385: istore  12
        //    387: iload  12
        //    389: iconst_m1
        //    390: if_icmpne  401 (offset +11)
        //    393: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    396: astore  10
        //    398: goto  44 (offset -354)
        //    401: iload  12
        //    403: istore  9
        //    405: goto  44 (offset -361)
        //    408: aload  10
        //    410: getstatic  #151 // org.freedesktop.dbus.connections.SASL$SaslAuthState.NEGOTIATE_UNIX_FD:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    413: if_acmpne  452 (offset +39)
        //    416: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    419: astore  10
        //    421: aload_0
        //    422: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    425: ldc  #36 // 'File descriptors NOT supported by server'
        //    427: invokeinterface  #296 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    432: aload_0
        //    433: iconst_0
        //    434: putfield  #145 // org.freedesktop.dbus.connections.SASL.fileDescriptorSupported:Z
        //    437: aload_0
        //    438: aload_1
        //    439: getstatic  #159 // org.freedesktop.dbus.connections.SASL$SaslCommand.BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    442: iconst_0
        //    443: anewarray  #66 // java.lang.String
        //    446: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    449: goto  44 (offset -405)
        //    452: aload_0
        //    453: aload_1
        //    454: getstatic  #160 // org.freedesktop.dbus.connections.SASL$SaslCommand.CANCEL:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    457: iconst_0
        //    458: anewarray  #66 // java.lang.String
        //    461: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    464: getstatic  #156 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_REJECT:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    467: astore  10
        //    469: goto  44 (offset -425)
        //    472: aload_0
        //    473: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    476: ldc  #22 // 'Authenticated'
        //    478: invokeinterface  #296 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    483: aload_0
        //    484: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //    487: invokevirtual  #268 // org.freedesktop.dbus.connections.config.SaslConfig.isFileDescriptorSupport:()Z
        //    490: ifeq  524 (offset +34)
        //    493: getstatic  #154 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_DATA:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    496: astore  10
        //    498: aload_0
        //    499: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    502: ldc  #20 // 'Asking for file descriptor support'
        //    504: invokeinterface  #296 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    509: aload_0
        //    510: aload_1
        //    511: getstatic  #163 // org.freedesktop.dbus.connections.SASL$SaslCommand.NEGOTIATE_UNIX_FD:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    514: iconst_0
        //    515: anewarray  #66 // java.lang.String
        //    518: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    521: goto  44 (offset -477)
        //    524: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    527: astore  10
        //    529: aload_0
        //    530: aload_1
        //    531: getstatic  #159 // org.freedesktop.dbus.connections.SASL$SaslCommand.BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    534: iconst_0
        //    535: anewarray  #66 // java.lang.String
        //    538: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    541: goto  44 (offset -497)
        //    544: aload_0
        //    545: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //    548: invokevirtual  #268 // org.freedesktop.dbus.connections.config.SaslConfig.isFileDescriptorSupport:()Z
        //    551: ifeq  44 (offset -507)
        //    554: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    557: astore  10
        //    559: aload_0
        //    560: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //    563: ldc  #37 // 'File descriptors supported by server'
        //    565: invokeinterface  #296 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    570: aload_0
        //    571: iconst_1
        //    572: putfield  #145 // org.freedesktop.dbus.connections.SASL.fileDescriptorSupported:Z
        //    575: aload_0
        //    576: aload_1
        //    577: getstatic  #159 // org.freedesktop.dbus.connections.SASL$SaslCommand.BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    580: iconst_0
        //    581: anewarray  #66 // java.lang.String
        //    584: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    587: goto  44 (offset -543)
        //    590: aload_0
        //    591: aload_1
        //    592: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    595: iconst_1
        //    596: anewarray  #66 // java.lang.String
        //    599: dup
        //    600: iconst_0
        //    601: ldc  #38 // 'Got invalid command'
        //    603: aastore
        //    604: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    607: goto  44 (offset -563)
        //    610: aload_0
        //    611: aload_1
        //    612: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //    615: astore  7
        //    617: aload  7
        //    619: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    622: invokevirtual  #261 // org.freedesktop.dbus.connections.SASL$SaslCommand.ordinal:()I
        //    625: tableswitch  default->837, 1->684, 2->704, 3->664, 4->837, 5->837, 6->684
        //    664: aload_0
        //    665: aload_1
        //    666: getstatic  #159 // org.freedesktop.dbus.connections.SASL$SaslCommand.BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    669: iconst_0
        //    670: anewarray  #66 // java.lang.String
        //    673: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    676: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    679: astore  10
        //    681: goto  44 (offset -637)
        //    684: aload_0
        //    685: aload_1
        //    686: getstatic  #160 // org.freedesktop.dbus.connections.SASL$SaslCommand.CANCEL:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    689: iconst_0
        //    690: anewarray  #66 // java.lang.String
        //    693: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    696: getstatic  #156 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_REJECT:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    699: astore  10
        //    701: goto  44 (offset -657)
        //    704: iload  8
        //    706: iload  9
        //    708: ior
        //    709: istore  8
        //    711: aload  7
        //    713: invokevirtual  #256 // org.freedesktop.dbus.connections.SASL$Command.getMechs:()I
        //    716: iload  8
        //    718: iconst_m1
        //    719: ixor
        //    720: iand
        //    721: istore  11
        //    723: getstatic  #154 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_DATA:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    726: astore  10
        //    728: iconst_0
        //    729: iload  11
        //    731: iconst_1
        //    732: iand
        //    733: if_icmpeq  763 (offset +30)
        //    736: aload_0
        //    737: aload_1
        //    738: getstatic  #158 // org.freedesktop.dbus.connections.SASL$SaslCommand.AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    741: iconst_2
        //    742: anewarray  #66 // java.lang.String
        //    745: dup
        //    746: iconst_0
        //    747: ldc  #32 // 'EXTERNAL'
        //    749: aastore
        //    750: dup
        //    751: iconst_1
        //    752: aload_3
        //    753: aastore
        //    754: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    757: iconst_1
        //    758: istore  9
        //    760: goto  44 (offset -716)
        //    763: iconst_0
        //    764: iload  11
        //    766: iconst_2
        //    767: iand
        //    768: if_icmpeq  798 (offset +30)
        //    771: aload_0
        //    772: aload_1
        //    773: getstatic  #158 // org.freedesktop.dbus.connections.SASL$SaslCommand.AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    776: iconst_2
        //    777: anewarray  #66 // java.lang.String
        //    780: dup
        //    781: iconst_0
        //    782: ldc  #28 // 'DBUS_COOKIE_SHA1'
        //    784: aastore
        //    785: dup
        //    786: iconst_1
        //    787: aload_3
        //    788: aastore
        //    789: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    792: iconst_2
        //    793: istore  9
        //    795: goto  44 (offset -751)
        //    798: iconst_0
        //    799: iload  11
        //    801: iconst_4
        //    802: iand
        //    803: if_icmpeq  829 (offset +26)
        //    806: aload_0
        //    807: aload_1
        //    808: getstatic  #158 // org.freedesktop.dbus.connections.SASL$SaslCommand.AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    811: iconst_1
        //    812: anewarray  #66 // java.lang.String
        //    815: dup
        //    816: iconst_0
        //    817: ldc  #19 // 'ANONYMOUS'
        //    819: aastore
        //    820: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    823: iconst_4
        //    824: istore  9
        //    826: goto  44 (offset -782)
        //    829: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    832: astore  10
        //    834: goto  44 (offset -790)
        //    837: aload_0
        //    838: aload_1
        //    839: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    842: iconst_1
        //    843: anewarray  #66 // java.lang.String
        //    846: dup
        //    847: iconst_0
        //    848: ldc  #38 // 'Got invalid command'
        //    850: aastore
        //    851: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //    854: goto  44 (offset -810)
        //    857: aload_0
        //    858: aload_1
        //    859: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //    862: astore  7
        //    864: aload  7
        //    866: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    869: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    872: if_acmpne  925 (offset +53)
        //    875: iload  8
        //    877: iload  9
        //    879: ior
        //    880: istore  8
        //    882: aload  7
        //    884: invokevirtual  #256 // org.freedesktop.dbus.connections.SASL$Command.getMechs:()I
        //    887: iload  8
        //    889: iconst_m1
        //    890: ixor
        //    891: iand
        //    892: istore  11
        //    894: aload_0
        //    895: iload  11
        //    897: aload_3
        //    898: aload_1
        //    899: invokevirtual  #245 // org.freedesktop.dbus.connections.SASL.handleReject:(ILjava/lang/String;Ljava/nio/channels/SocketChannel;)I
        //    902: istore  12
        //    904: iload  12
        //    906: iconst_m1
        //    907: if_icmpne  918 (offset +11)
        //    910: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    913: astore  10
        //    915: goto  922 (offset +7)
        //    918: iload  12
        //    920: istore  9
        //    922: goto  44 (offset -878)
        //    925: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    928: astore  10
        //    930: goto  44 (offset -886)
        //    933: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //    936: astore  10
        //    938: goto  44 (offset -894)
        //    941: aload  10
        //    943: invokevirtual  #259 // org.freedesktop.dbus.connections.SASL$SaslAuthState.ordinal:()I
        //    946: tableswitch  default->1698, 0->984, 1->1307, 2->1698, 3->1698, 4->1072, 5->1545
        //    984: iconst_1
        //    985: invokestatic  #210 // java.nio.ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //    988: astore  11
        //    990: aload_1
        //    991: instanceof  #76 // java.nio.channels.NetworkChannel
        //    994: ifeq  1012 (offset +18)
        //    997: aload_1
        //    998: aload  11
        //   1000: invokevirtual  #216 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //   1003: pop
        //   1004: getstatic  #152 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1007: astore  10
        //   1009: goto  44 (offset -965)
        //   1012: iconst_m1
        //   1013: istore  12
        //   1015: aload_2
        //   1016: instanceof  #102 // org.freedesktop.dbus.connections.transports.AbstractUnixTransport
        //   1019: ifeq  1036 (offset +17)
        //   1022: aload_2
        //   1023: checkcast  #102 // org.freedesktop.dbus.connections.transports.AbstractUnixTransport
        //   1026: astore  13
        //   1028: aload  13
        //   1030: aload_1
        //   1031: invokevirtual  #270 // org.freedesktop.dbus.connections.transports.AbstractUnixTransport.getUid:(Ljava/nio/channels/SocketChannel;)I
        //   1034: istore  12
        //   1036: iload  12
        //   1038: iflt  1054 (offset +16)
        //   1041: aload_0
        //   1042: iload  12
        //   1044: invokedynamic  #312 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //   1049: invokevirtual  #251 // org.freedesktop.dbus.connections.SASL.stupidlyEncode:(Ljava/lang/String;)Ljava/lang/String;
        //   1052: astore  4
        //   1054: getstatic  #152 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1057: astore  10
        //   1059: goto  44 (offset -1015)
        //   1062: astore  12
        //   1064: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1067: astore  10
        //   1069: goto  44 (offset -1025)
        //   1072: aload_0
        //   1073: aload_1
        //   1074: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //   1077: astore  7
        //   1079: aload  7
        //   1081: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1084: invokevirtual  #261 // org.freedesktop.dbus.connections.SASL$SaslCommand.ordinal:()I
        //   1087: lookupswitch  default->1287, 0->1120, 4->1279, 6->1257
        //   1120: aload_0
        //   1121: iload  9
        //   1123: aload_3
        //   1124: aload  4
        //   1126: aload  7
        //   1128: invokevirtual  #241 // org.freedesktop.dbus.connections.SASL.doResponse:(ILjava/lang/String;Ljava/lang/String;Lorg/freedesktop/dbus/connections/SASL$Command;)Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //   1131: invokevirtual  #263 // org.freedesktop.dbus.connections.SASL$SaslResult.ordinal:()I
        //   1134: tableswitch  default->1232, 0->1199, 1->1164, 2->1232, 3->1232
        //   1164: aload_0
        //   1165: aload_1
        //   1166: getstatic  #161 // org.freedesktop.dbus.connections.SASL$SaslCommand.DATA:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1169: iconst_1
        //   1170: anewarray  #66 // java.lang.String
        //   1173: dup
        //   1174: iconst_0
        //   1175: aload  7
        //   1177: invokevirtual  #257 // org.freedesktop.dbus.connections.SASL$Command.getResponse:()Ljava/lang/String;
        //   1180: aastore
        //   1181: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1184: aload  7
        //   1186: invokevirtual  #256 // org.freedesktop.dbus.connections.SASL$Command.getMechs:()I
        //   1189: istore  9
        //   1191: getstatic  #154 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_DATA:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1194: astore  10
        //   1196: goto  44 (offset -1152)
        //   1199: aload_0
        //   1200: aload_1
        //   1201: getstatic  #164 // org.freedesktop.dbus.connections.SASL$SaslCommand.OK:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1204: iconst_1
        //   1205: anewarray  #66 // java.lang.String
        //   1208: dup
        //   1209: iconst_0
        //   1210: aload_0
        //   1211: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1214: invokevirtual  #265 // org.freedesktop.dbus.connections.config.SaslConfig.getGuid:()Ljava/lang/String;
        //   1217: aastore
        //   1218: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1221: getstatic  #153 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1224: astore  10
        //   1226: iconst_0
        //   1227: istore  9
        //   1229: goto  44 (offset -1185)
        //   1232: aload_0
        //   1233: aload_1
        //   1234: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1237: aload_0
        //   1238: aload_0
        //   1239: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1242: invokevirtual  #264 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //   1245: invokevirtual  #239 // org.freedesktop.dbus.connections.SASL.convertAuthTypes:(I)[Ljava/lang/String;
        //   1248: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1251: iconst_0
        //   1252: istore  9
        //   1254: goto  44 (offset -1210)
        //   1257: aload_0
        //   1258: aload_1
        //   1259: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1262: aload_0
        //   1263: aload_0
        //   1264: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1267: invokevirtual  #264 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //   1270: invokevirtual  #239 // org.freedesktop.dbus.connections.SASL.convertAuthTypes:(I)[Ljava/lang/String;
        //   1273: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1276: goto  44 (offset -1232)
        //   1279: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1282: astore  10
        //   1284: goto  44 (offset -1240)
        //   1287: aload_0
        //   1288: aload_1
        //   1289: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1292: iconst_1
        //   1293: anewarray  #66 // java.lang.String
        //   1296: dup
        //   1297: iconst_0
        //   1298: ldc  #38 // 'Got invalid command'
        //   1300: aastore
        //   1301: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1304: goto  44 (offset -1260)
        //   1307: aload_0
        //   1308: aload_1
        //   1309: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //   1312: astore  7
        //   1314: aload  7
        //   1316: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1319: invokevirtual  #261 // org.freedesktop.dbus.connections.SASL$SaslCommand.ordinal:()I
        //   1322: tableswitch  default->1525, 1->1360, 2->1525, 3->1525, 4->1517, 5->1490, 6->1490
        //   1360: aload_0
        //   1361: iload  9
        //   1363: aload_3
        //   1364: aload  4
        //   1366: aload  7
        //   1368: invokevirtual  #241 // org.freedesktop.dbus.connections.SASL.doResponse:(ILjava/lang/String;Ljava/lang/String;Lorg/freedesktop/dbus/connections/SASL$Command;)Lorg/freedesktop/dbus/connections/SASL$SaslResult;
        //   1371: invokevirtual  #263 // org.freedesktop.dbus.connections.SASL$SaslResult.ordinal:()I
        //   1374: tableswitch  default->1465, 0->1432, 1->1404, 2->1465, 3->1465
        //   1404: aload_0
        //   1405: aload_1
        //   1406: getstatic  #161 // org.freedesktop.dbus.connections.SASL$SaslCommand.DATA:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1409: iconst_1
        //   1410: anewarray  #66 // java.lang.String
        //   1413: dup
        //   1414: iconst_0
        //   1415: aload  7
        //   1417: invokevirtual  #257 // org.freedesktop.dbus.connections.SASL$Command.getResponse:()Ljava/lang/String;
        //   1420: aastore
        //   1421: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1424: getstatic  #154 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_DATA:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1427: astore  10
        //   1429: goto  44 (offset -1385)
        //   1432: aload_0
        //   1433: aload_1
        //   1434: getstatic  #164 // org.freedesktop.dbus.connections.SASL$SaslCommand.OK:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1437: iconst_1
        //   1438: anewarray  #66 // java.lang.String
        //   1441: dup
        //   1442: iconst_0
        //   1443: aload_0
        //   1444: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1447: invokevirtual  #265 // org.freedesktop.dbus.connections.config.SaslConfig.getGuid:()Ljava/lang/String;
        //   1450: aastore
        //   1451: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1454: getstatic  #153 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1457: astore  10
        //   1459: iconst_0
        //   1460: istore  9
        //   1462: goto  44 (offset -1418)
        //   1465: aload_0
        //   1466: aload_1
        //   1467: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1470: aload_0
        //   1471: aload_0
        //   1472: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1475: invokevirtual  #264 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //   1478: invokevirtual  #239 // org.freedesktop.dbus.connections.SASL.convertAuthTypes:(I)[Ljava/lang/String;
        //   1481: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1484: iconst_0
        //   1485: istore  9
        //   1487: goto  44 (offset -1443)
        //   1490: aload_0
        //   1491: aload_1
        //   1492: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1495: aload_0
        //   1496: aload_0
        //   1497: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1500: invokevirtual  #264 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //   1503: invokevirtual  #239 // org.freedesktop.dbus.connections.SASL.convertAuthTypes:(I)[Ljava/lang/String;
        //   1506: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1509: getstatic  #152 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1512: astore  10
        //   1514: goto  44 (offset -1470)
        //   1517: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1520: astore  10
        //   1522: goto  44 (offset -1478)
        //   1525: aload_0
        //   1526: aload_1
        //   1527: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1530: iconst_1
        //   1531: anewarray  #66 // java.lang.String
        //   1534: dup
        //   1535: iconst_0
        //   1536: ldc  #38 // 'Got invalid command'
        //   1538: aastore
        //   1539: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1542: goto  44 (offset -1498)
        //   1545: aload_0
        //   1546: aload_1
        //   1547: invokevirtual  #248 // org.freedesktop.dbus.connections.SASL.receive:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/connections/SASL$Command;
        //   1550: astore  7
        //   1552: aload  7
        //   1554: invokevirtual  #254 // org.freedesktop.dbus.connections.SASL$Command.getCommand:()Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1557: invokevirtual  #261 // org.freedesktop.dbus.connections.SASL$SaslCommand.ordinal:()I
        //   1560: tableswitch  default->1678, 4->1619, 5->1592, 6->1592, 7->1627
        //   1592: aload_0
        //   1593: aload_1
        //   1594: getstatic  #165 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1597: aload_0
        //   1598: aload_0
        //   1599: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1602: invokevirtual  #264 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //   1605: invokevirtual  #239 // org.freedesktop.dbus.connections.SASL.convertAuthTypes:(I)[Ljava/lang/String;
        //   1608: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1611: getstatic  #152 // org.freedesktop.dbus.connections.SASL$SaslAuthState.WAIT_AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1614: astore  10
        //   1616: goto  44 (offset -1572)
        //   1619: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1622: astore  10
        //   1624: goto  44 (offset -1580)
        //   1627: aload_0
        //   1628: getfield  #146 // org.freedesktop.dbus.connections.SASL.logger:Lorg/slf4j/Logger;
        //   1631: ldc  #35 // 'File descriptor negotiation requested'
        //   1633: invokeinterface  #289 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //   1638: aload_0
        //   1639: getfield  #147 // org.freedesktop.dbus.connections.SASL.saslConfig:Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //   1642: invokevirtual  #268 // org.freedesktop.dbus.connections.config.SaslConfig.isFileDescriptorSupport:()Z
        //   1645: ifne  1663 (offset +18)
        //   1648: aload_0
        //   1649: aload_1
        //   1650: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1653: iconst_0
        //   1654: anewarray  #66 // java.lang.String
        //   1657: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1660: goto  44 (offset -1616)
        //   1663: aload_0
        //   1664: aload_1
        //   1665: getstatic  #157 // org.freedesktop.dbus.connections.SASL$SaslCommand.AGREE_UNIX_FD:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1668: iconst_0
        //   1669: anewarray  #66 // java.lang.String
        //   1672: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1675: goto  44 (offset -1631)
        //   1678: aload_0
        //   1679: aload_1
        //   1680: getstatic  #162 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //   1683: iconst_1
        //   1684: anewarray  #66 // java.lang.String
        //   1687: dup
        //   1688: iconst_0
        //   1689: ldc  #38 // 'Got invalid command'
        //   1691: aastore
        //   1692: invokevirtual  #249 // org.freedesktop.dbus.connections.SASL.send:(Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/connections/SASL$SaslCommand;[Ljava/lang/String;)V
        //   1695: goto  44 (offset -1651)
        //   1698: getstatic  #148 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FAILED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1701: astore  10
        //   1703: goto  44 (offset -1659)
        //   1706: iconst_0
        //   1707: ireturn
        //   1708: aload  10
        //   1710: getstatic  #149 // org.freedesktop.dbus.connections.SASL$SaslAuthState.FINISHED:Lorg/freedesktop/dbus/connections/SASL$SaslAuthState;
        //   1713: if_acmpne  1720 (offset +7)
        //   1716: iconst_1
        //   1717: goto  1721 (offset +4)
        //   1720: iconst_0
        //   1721: ireturn
        //       Exception table:
        //         from 1012 to 1059 target 1062 type java.net.SocketException
    }

  public boolean isFileDescriptorSupported() {
        return fileDescriptorSupported;
    }

  private int handleReject(int arg0, String arg1, SocketChannel arg2) {
        int var4 = -1;
        if (0 == (arg0 & 1)) {
            if (0 == (arg0 & 2)) {
                if (0 != (arg0 & 4)) {
                    send(arg2, SASL_SaslCommand.AUTH, new String[]{"ANONYMOUS"});
                    var4 = 4;
                }
            } else {
                send(arg2, SASL_SaslCommand.AUTH, new String[]{"DBUS_COOKIE_SHA1", arg1});
                var4 = 2;
            }
        } else {
            send(arg2, SASL_SaslCommand.AUTH, new String[]{"EXTERNAL", arg1});
            var4 = 1;
        }
        return var4;
    }

  private long getUserId() {
        if (Util.isWindows()) {
            return 0L;
        } else {
            return new UnixSystem().getUid();
        }
    }

  private static Long lambda$doChallenge$1(Long arg0) {
        long __stk1;
        __stk1 = arg0.longValue() >= 0L ? arg0.longValue() : arg0.longValue() * -1L;
        return Long.valueOf(__stk1);
    }

  private void lambda$doChallenge$0(String[] arg0) {
        logger.trace("Auth data: {}", Arrays.toString(arg0));
    }

}