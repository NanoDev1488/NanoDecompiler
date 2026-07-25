// исходный (обфусцированный) внутренний класс: jnr.posix.util.WindowsHelpers
package jnr.posix.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.StringTokenizer;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.posix.POSIX;
import jnr.posix.util.Finder;
import jnr.posix.util.WindowsHelpers_Anon1;

public class WindowsHelpers {

    // ---- поля ----
  static final Runtime runtime;
  static final int WORDSIZE;
  private static final String COMMAND_DOT_COM = "command.com";
  private static final int CDC_LENGTH;
  private static Map INTERNAL_COMMANDS;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !WindowsHelpers.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
        runtime = Runtime.getSystemRuntime();
        WORDSIZE = Runtime.getSystemRuntime().addressSize();
        CDC_LENGTH = "command.com".length();
        INTERNAL_COMMANDS = new WindowsHelpers_Anon1();
    }

  public WindowsHelpers() { // было: <init>
        super();
    }

  public static byte[] toWPath(String arg0) {
        return toWString(arg0);
    }

  public static byte[] toWString(String arg0) {
        byte[] __stk1;
        if (arg0 != null) {
            arg0 = new StringBuilder().append(arg0).append('\u0000').toString();
        } else {
            return null;
        }
        try {
            __stk1 = arg0.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException var1) {
            return null;
        }
    }

  public static Pointer createWideEnv(String[] arg0) {
        byte[] var1;
        int var2;
        Pointer var3;
        int var4;
        if (arg0 != null) {
            var1 = new byte[]{0};
            var2 = arg0.length;
            var3 = Memory.allocateDirect(runtime, WORDSIZE * (var2 + 1));
            var4 = 0;
        } else {
            return null;
        }
        while (var4 < var2) {
            byte[] var5 = toWString(((String) arg0[var4]));
            Pointer var6 = Memory.allocateDirect(runtime, var5.length + 1);
            var6.put(0L, var5, 0, var5.length);
            var6.put(((long) var5.length), var1, 0, var1.length);
            var3.putPointer(((long) (var4 * WORDSIZE)), var6);
            ++var4;
            continue;
        }
        Pointer var4 = Memory.allocateDirect(runtime, var1.length);
        var4.put(0L, var1, 0, var1.length);
        var3.putPointer(((long) (WORDSIZE * var2)), var4);
        return var3;
    }

  private static void joinSingleArgv(StringBuilder arg0, String arg1, boolean arg2, boolean arg3) {
        int var4 = 0;
        int var5 = 0;
        if (arg2) {
            arg0.append('"');
        }
        int var6 = 0;
        while (var6 < arg1.length()) {
            char var7 = arg1.charAt(var6);
            switch (var7) {
                case 92:
                    ++var4;
                    break;
                case 34:
                    arg0.append(arg1.substring(var5, var6));
                    int var8 = 0;
                    while (var8 < var4 + 1) {
                        arg0.append('\\');
                        ++var8;
                        continue;
                    }
                    var4 = 0;
                    var5 = var6;
                case 60:
                case 62:
                case 94:
                case 124:
                    if (arg3) {
                        if (!arg2) {
                            arg0.append(arg1.substring(var5, var6));
                            arg0.append('^');
                            var5 = var6;
                            break;
                        }
                    }
                default:
                    var4 = 0;
            }
            ++var6;
            continue;
        }
        arg0.append(arg1.substring(var5));
        if (arg2) {
            arg0.append('"');
        }
    }

  public static String joinArgv(String arg0, String[] arg1, boolean arg2) {
        StringBuilder var3 = new StringBuilder();
        if (arg0 != null) {
            var3.append(arg0);
            var3.append(' ');
        }
        int var4 = arg1.length - 1;
        int var5 = 0;
        while (var5 <= var4) {
            joinSingleArgv(var3, ((String) arg1[var5]), quotable(((String) arg1[var5])), arg2);
            if (var5 != var4) {
                var3.append(' ');
            }
            ++var5;
            continue;
        }
        return var3.toString();
    }

  public static boolean quotable(String arg0) {
        if (arg0 != null) {
            StringTokenizer var1 = new StringTokenizer(arg0, " \t\"'");
            var1.nextToken();
            return var1.hasMoreTokens();
        } else {
            return false;
        }
    }

  public static boolean isBatch(String arg0) {
        if (arg0 != null) {
            int var1 = arg0.length();
            if (var1 >= 5) {
                String var2 = arg0.substring(var1 - 4);
                return var2.equalsIgnoreCase(".bat") ? 1 : var2.equalsIgnoreCase(".cmd");
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

  public static String[] processCommandLine(POSIX arg0, String arg1, String arg2, String arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore  4
        //      3: aload_2
        //      4: ifnull  38 (offset +34)
        //      7: aload_0
        //      8: aload_2
        //      9: aload_3
        //     10: invokestatic  #69 // jnr.posix.util.Finder.findFileInPath:(Ljnr/posix/POSIX;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //     13: astore  5
        //     15: aload  5
        //     17: ifnonnull  24 (offset +7)
        //     20: aload_2
        //     21: goto  33 (offset +12)
        //     24: aload  5
        //     26: bipush  47
        //     28: bipush  92
        //     30: invokevirtual  #52 // java.lang.String.replace:(CC)Ljava/lang/String;
        //     33: astore  4
        //     35: goto  326 (offset +291)
        //     38: aload_1
        //     39: aload_1
        //     40: invokestatic  #70 // jnr.posix.util.WindowsHelpers.firstNonWhitespaceIndex:(Ljava/lang/String;)I
        //     43: invokevirtual  #53 // java.lang.String.substring:(I)Ljava/lang/String;
        //     46: astore_1
        //     47: ldc  #9 // 'COMSPEC'
        //     49: invokestatic  #60 // java.lang.System.getenv:(Ljava/lang/String;)Ljava/lang/String;
        //     52: astore  4
        //     54: iconst_1
        //     55: istore  5
        //     57: aload  4
        //     59: ifnull  137 (offset +78)
        //     62: aload  4
        //     64: invokestatic  #73 // jnr.posix.util.WindowsHelpers.isCommandDotCom:(Ljava/lang/String;)Z
        //     67: istore  6
        //     69: aload_1
        //     70: invokestatic  #71 // jnr.posix.util.WindowsHelpers.hasBuiltinSpecialNeeds:(Ljava/lang/String;)Z
        //     73: ifne  85 (offset +12)
        //     76: aload_1
        //     77: iload  6
        //     79: invokestatic  #76 // jnr.posix.util.WindowsHelpers.isInternalCommand:(Ljava/lang/String;Z)Z
        //     82: ifeq  137 (offset +55)
        //     85: iload  6
        //     87: ifeq  95 (offset +8)
        //     90: ldc  #5 // '"'
        //     92: goto  97 (offset +5)
        //     95: ldc  #1 // ''
        //     97: astore  7
        //     99: new  #20 // java.lang.StringBuilder
        //    102: dup
        //    103: invokespecial  #55 // java.lang.StringBuilder.<init>:()V
        //    106: aload  4
        //    108: invokevirtual  #57 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    111: ldc  #4 // ' /c '
        //    113: invokevirtual  #57 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    116: aload  7
        //    118: invokevirtual  #57 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    121: aload_1
        //    122: invokevirtual  #57 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    125: aload  7
        //    127: invokevirtual  #57 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    130: invokevirtual  #58 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    133: astore_1
        //    134: iconst_0
        //    135: istore  5
        //    137: iload  5
        //    139: ifeq  326 (offset +187)
        //    142: aload_1
        //    143: iconst_0
        //    144: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //    147: istore  6
        //    149: iload  6
        //    151: bipush  34
        //    153: if_icmpne  161 (offset +8)
        //    156: iload  6
        //    158: goto  174 (offset +16)
        //    161: iload  6
        //    163: bipush  39
        //    165: if_icmpne  173 (offset +8)
        //    168: iload  6
        //    170: goto  174 (offset +4)
        //    173: iconst_0
        //    174: istore  7
        //    176: aload_1
        //    177: invokevirtual  #50 // java.lang.String.length:()I
        //    180: istore  8
        //    182: iload  7
        //    184: ifne  191 (offset +7)
        //    187: iconst_0
        //    188: goto  192 (offset +4)
        //    191: iconst_1
        //    192: istore  9
        //    194: iload  9
        //    196: iload  8
        //    198: if_icmpne  207 (offset +9)
        //    201: aload_1
        //    202: astore  4
        //    204: goto  276 (offset +72)
        //    207: aload_1
        //    208: iload  9
        //    210: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //    213: istore  10
        //    215: iload  10
        //    217: iload  7
        //    219: if_icmpne  234 (offset +15)
        //    222: aload_1
        //    223: iconst_1
        //    224: iload  9
        //    226: invokevirtual  #54 // java.lang.String.substring:(II)Ljava/lang/String;
        //    229: astore  4
        //    231: goto  276 (offset +45)
        //    234: iload  7
        //    236: ifeq  242 (offset +6)
        //    239: goto  270 (offset +31)
        //    242: iload  10
        //    244: invokestatic  #42 // java.lang.Character.isSpaceChar:(C)Z
        //    247: ifne  258 (offset +11)
        //    250: iload  10
        //    252: invokestatic  #75 // jnr.posix.util.WindowsHelpers.isFunnyChar:(C)Z
        //    255: ifeq  270 (offset +15)
        //    258: aload_1
        //    259: iconst_0
        //    260: iload  9
        //    262: invokevirtual  #54 // java.lang.String.substring:(II)Ljava/lang/String;
        //    265: astore  4
        //    267: goto  276 (offset +9)
        //    270: iinc  9, 1
        //    273: goto  194 (offset -79)
        //    276: aload_0
        //    277: aload  4
        //    279: aload_3
        //    280: invokestatic  #69 // jnr.posix.util.Finder.findFileInPath:(Ljnr/posix/POSIX;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    283: astore  4
        //    285: aload  4
        //    287: ifnonnull  302 (offset +15)
        //    290: aload_1
        //    291: iconst_0
        //    292: iload  9
        //    294: invokevirtual  #54 // java.lang.String.substring:(II)Ljava/lang/String;
        //    297: astore  4
        //    299: goto  326 (offset +27)
        //    302: aload  4
        //    304: ldc  #2 // ' '
        //    306: invokevirtual  #47 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //    309: ifne  315 (offset +6)
        //    312: iconst_0
        //    313: istore  7
        //    315: aload  4
        //    317: bipush  47
        //    319: bipush  92
        //    321: invokevirtual  #52 // java.lang.String.replace:(CC)Ljava/lang/String;
        //    324: astore  4
        //    326: iconst_2
        //    327: anewarray  #19 // java.lang.String
        //    330: dup
        //    331: iconst_0
        //    332: aload_1
        //    333: aastore
        //    334: dup
        //    335: iconst_1
        //    336: aload  4
        //    338: aastore
        //    339: areturn
    }

  public static String[] processCommandArgs(POSIX arg0, String arg1, String[] arg2, String arg3) {
        boolean __stk2;
        String __stk3;
        if (arg1 == null) {
            arg1 = arg2[0];
        } else {
            if (arg1.length() == 0) {
                arg1 = arg2[0];
            }
        }
        int var4 = 0;
        int var5 = 0;
        int var6 = 1;
        String var7 = System.getenv("COMSPEC");
        Object var8 = null;
        if (var7 != null) {
            boolean var9 = isCommandDotCom(var7);
            if (isInternalCommand(arg1, var9)) {
                __stk2 = !var9;
                var5 = __stk2;
                arg1 = var7;
                var4 = 1;
                var6 = 0;
            }
        }
        if (var6 != 0) {
            var8 = Finder.findFileInPath(arg0, arg1, arg3);
            if (var8 == null) {
                if (arg1.contains("/")) {
                    var8 = arg1.replace('/', '\\');
                    arg1 = var8;
                }
            } else {
                arg1 = var8.replace('/', '\\');
            }
        }
        if (var4 != 0) {
            if (var4 == 0) {
                String[] var9 = new String[arg2.length - 1];
                System.arraycopy(arg2, 1, var9, 0, arg2.length - 1);
                arg2 = var9;
            } else {
                var8 = new StringBuilder().append(arg1).append(" /c ").toString();
            }
            if (arg2.length > 0) {
                var8 = joinArgv(((String) var8), arg2, var5);
            }
            __stk3 = var4 == 0 ? null : var7;
            arg1 = __stk3;
        } else {
            if (!isBatch(arg1)) {
                var8 = joinArgv(null, arg2, false);
            } else {
                if (var4 == 0) {
                    String[] var9 = new String[arg2.length - 1];
                    System.arraycopy(arg2, 1, var9, 0, arg2.length - 1);
                    arg2 = var9;
                } else {
                    var8 = new StringBuilder().append(arg1).append(" /c ").toString();
                }
                if (arg2.length > 0) {
                    var8 = joinArgv(((String) var8), arg2, var5);
                }
                __stk3 = var4 == 0 ? null : var7;
                arg1 = __stk3;
            }
        }
        return new String[]{var8, arg1};
    }

  private static boolean isFunnyChar(char arg0) {
        return arg0 == 60 ? 1 : arg0 == 62 ? 1 : arg0 == 124 ? 1 : arg0 == 42 ? 1 : arg0 == 63 ? 1 : arg0 == 34;
    }

  private static boolean hasBuiltinSpecialNeeds(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #50 // java.lang.String.length:()I
        //      4: istore_1
        //      5: iconst_0
        //      6: istore_2
        //      7: iconst_0
        //      8: istore_3
        //      9: iload_3
        //     10: iload_1
        //     11: if_icmpge  206 (offset +195)
        //     14: aload_0
        //     15: iload_3
        //     16: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //     19: istore  4
        //     21: iload  4
        //     23: lookupswitch  default->200, 10->109, 34->88, 37->115, 39->88, 60->109, 62->109, 124->109
        //     88: iload_2
        //     89: ifne  98 (offset +9)
        //     92: iload  4
        //     94: istore_2
        //     95: goto  200 (offset +105)
        //     98: iload_2
        //     99: iload  4
        //    101: if_icmpne  200 (offset +99)
        //    104: iconst_0
        //    105: istore_2
        //    106: goto  200 (offset +94)
        //    109: iload_2
        //    110: ifeq  200 (offset +90)
        //    113: iconst_1
        //    114: ireturn
        //    115: iload_3
        //    116: iconst_1
        //    117: iadd
        //    118: iload_1
        //    119: if_icmpge  200 (offset +81)
        //    122: iinc  3, 1
        //    125: aload_0
        //    126: iload_3
        //    127: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //    130: istore  5
        //    132: iload  5
        //    134: bipush  32
        //    136: if_icmpeq  150 (offset +14)
        //    139: iload  5
        //    141: invokestatic  #40 // java.lang.Character.isLetter:(C)Z
        //    144: ifne  150 (offset +6)
        //    147: goto  200 (offset +53)
        //    150: iload_3
        //    151: istore  6
        //    153: iload  6
        //    155: iload_1
        //    156: if_icmpge  191 (offset +35)
        //    159: aload_0
        //    160: iload  6
        //    162: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //    165: istore  5
        //    167: iload  5
        //    169: bipush  32
        //    171: if_icmpeq  185 (offset +14)
        //    174: iload  5
        //    176: invokestatic  #41 // java.lang.Character.isLetterOrDigit:(C)Z
        //    179: ifne  185 (offset +6)
        //    182: goto  191 (offset +9)
        //    185: iinc  6, 1
        //    188: goto  153 (offset -35)
        //    191: iload  5
        //    193: bipush  37
        //    195: if_icmpne  200 (offset +5)
        //    198: iconst_1
        //    199: ireturn
        //    200: iinc  3, 1
        //    203: goto  9 (offset -194)
        //    206: iconst_0
        //    207: ireturn
    }

  private static int firstNonWhitespaceIndex(String arg0) {
        int var1 = arg0.length();
        int var2 = 0;
        while (var2 < var1) {
            if (!Character.isSpaceChar(arg0.charAt(var2))) {
                break;
            }
            ++var2;
            continue;
        }
        return var2;
    }

  public static String escapePath(String arg0) {
        StringBuilder var1 = new StringBuilder();
        int var2 = 0;
        while (var2 < arg0.length()) {
            char var3 = arg0.charAt(var2);
            var1.append(var3);
            if (var3 == 92) {
                var1.append(var3);
            }
            ++var2;
            continue;
        }
        return new StringBuilder().append(var1.toString()).append("\\\\").toString();
    }

  private static boolean isDirectorySeparator(char arg0) {
        return arg0 == 47 ? 1 : arg0 == 92;
    }

  private static boolean isCommandDotCom(String arg0) {
        int var1 = arg0.length();
        int var2 = var1 - CDC_LENGTH;
        return var2 == 0 ? 1 : var2 <= 0 ? 0 : !isDirectorySeparator(arg0.charAt(var2 - 1)) ? 0 : arg0.regionMatches(true, var2, "command.com", 0, CDC_LENGTH);
    }

  private static boolean isInternalCommand(String arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #31 // jnr.posix.util.WindowsHelpers.$assertionsDisabled:Z
        //      3: ifne  31 (offset +28)
        //      6: aload_0
        //      7: ifnull  21 (offset +14)
        //     10: aload_0
        //     11: iconst_0
        //     12: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //     15: invokestatic  #42 // java.lang.Character.isSpaceChar:(C)Z
        //     18: ifeq  31 (offset +13)
        //     21: new  #15 // java.lang.AssertionError
        //     24: dup
        //     25: ldc  #10 // 'Spaces should have been stripped off already'
        //     27: invokespecial  #39 // java.lang.AssertionError.<init>:(Ljava/lang/Object;)V
        //     30: athrow
        //     31: aload_0
        //     32: invokevirtual  #50 // java.lang.String.length:()I
        //     35: istore_2
        //     36: new  #20 // java.lang.StringBuilder
        //     39: dup
        //     40: invokespecial  #55 // java.lang.StringBuilder.<init>:()V
        //     43: astore_3
        //     44: iconst_0
        //     45: istore  4
        //     47: iconst_0
        //     48: istore  5
        //     50: iload  4
        //     52: iload_2
        //     53: if_icmpge  91 (offset +38)
        //     56: aload_0
        //     57: iload  4
        //     59: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //     62: istore  5
        //     64: iload  5
        //     66: invokestatic  #40 // java.lang.Character.isLetter:(C)Z
        //     69: ifne  75 (offset +6)
        //     72: goto  91 (offset +19)
        //     75: aload_3
        //     76: iload  5
        //     78: invokestatic  #43 // java.lang.Character.toLowerCase:(C)C
        //     81: invokevirtual  #56 // java.lang.StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //     84: pop
        //     85: iinc  4, 1
        //     88: goto  50 (offset -38)
        //     91: iload  4
        //     93: iload_2
        //     94: if_icmpge  195 (offset +101)
        //     97: iload  5
        //     99: bipush  46
        //    101: if_icmpne  115 (offset +14)
        //    104: iload  4
        //    106: iconst_1
        //    107: iadd
        //    108: iload_2
        //    109: if_icmpge  115 (offset +6)
        //    112: iinc  4, 1
        //    115: aload_0
        //    116: iload  4
        //    118: invokevirtual  #46 // java.lang.String.charAt:(I)C
        //    121: lookupswitch  default->193, 0->190, 9->190, 10->190, 32->190, 60->188, 62->188, 124->188
        //    188: iconst_1
        //    189: ireturn
        //    190: goto  195 (offset +5)
        //    193: iconst_0
        //    194: ireturn
        //    195: getstatic  #33 // jnr.posix.util.WindowsHelpers.INTERNAL_COMMANDS:Ljava/util/Map;
        //    198: aload_3
        //    199: invokevirtual  #58 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    202: invokeinterface  #82 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    207: checkcast  #30 // jnr.posix.util.WindowsHelpers$InternalType
        //    210: astore  6
        //    212: aload  6
        //    214: getstatic  #36 // jnr.posix.util.WindowsHelpers$InternalType.BOTH:Ljnr/posix/util/WindowsHelpers$InternalType;
        //    217: if_acmpeq  243 (offset +26)
        //    220: iload_1
        //    221: ifeq  235 (offset +14)
        //    224: aload  6
        //    226: getstatic  #37 // jnr.posix.util.WindowsHelpers$InternalType.COMMAND:Ljnr/posix/util/WindowsHelpers$InternalType;
        //    229: if_acmpne  247 (offset +18)
        //    232: goto  243 (offset +11)
        //    235: aload  6
        //    237: getstatic  #38 // jnr.posix.util.WindowsHelpers$InternalType.SHELL:Ljnr/posix/util/WindowsHelpers$InternalType;
        //    240: if_acmpne  247 (offset +7)
        //    243: iconst_1
        //    244: goto  248 (offset +4)
        //    247: iconst_0
        //    248: ireturn
    }

  public static boolean isDriveLetterPath(String arg0) {
        return arg0.length() < 2 ? 0 : !Character.isLetter(arg0.charAt(0)) ? 0 : arg0.charAt(1) == 58;
    }

}