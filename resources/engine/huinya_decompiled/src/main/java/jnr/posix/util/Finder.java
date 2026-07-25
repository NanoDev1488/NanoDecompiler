// исходный (обфусцированный) внутренний класс: jnr.posix.util.Finder
package jnr.posix.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import jnr.posix.POSIX;

public class Finder {

    // ---- поля ----
  private static final Collection EXECUTABLE_EXTENSIONS;

    static {
        EXECUTABLE_EXTENSIONS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{".exe", ".com", ".cmd", ".bat"})));
    }

  public Finder() { // было: <init>
        super();
    }

  public static String findFileInPath(POSIX arg0, String arg1, String arg2) {
        if (arg2 == null) {
            arg2 = System.getenv("PATH");
        } else {
            if (arg2.length() == 0) {
                arg2 = System.getenv("PATH");
            }
        }
        if (arg2 == null) {
            return arg1;
        } else {
            if (arg2.length() != 0) {
                return findFileCommon(arg0, arg1, arg2, true);
            } else {
                return arg1;
            }
        }
    }

  public static String findFileCommon(POSIX arg0, String arg1, String arg2, boolean arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnull  11 (offset +10)
        //      4: aload_1
        //      5: invokevirtual  #35 // java.lang.String.length:()I
        //      8: ifne  13 (offset +5)
        //     11: aload_1
        //     12: areturn
        //     13: aload_1
        //     14: invokevirtual  #35 // java.lang.String.length:()I
        //     17: istore  4
        //     19: iconst_0
        //     20: istore  5
        //     22: iconst_0
        //     23: istore  6
        //     25: iconst_0
        //     26: istore  7
        //     28: getstatic  #28 // jnr.posix.util.Platform.IS_WINDOWS:Z
        //     31: ifeq  555 (offset +524)
        //     34: iload  4
        //     36: iconst_1
        //     37: if_icmple  67 (offset +30)
        //     40: aload_1
        //     41: iconst_0
        //     42: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //     45: invokestatic  #31 // java.lang.Character.isLetter:(C)Z
        //     48: ifeq  67 (offset +19)
        //     51: aload_1
        //     52: iconst_1
        //     53: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //     56: bipush  58
        //     58: if_icmpne  67 (offset +9)
        //     61: iconst_2
        //     62: istore  7
        //     64: iconst_1
        //     65: istore  5
        //     67: iconst_m1
        //     68: istore  8
        //     70: aload_1
        //     71: iload  7
        //     73: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //     76: istore  9
        //     78: iload  7
        //     80: bipush  47
        //     82: if_icmpeq  92 (offset +10)
        //     85: iload  7
        //     87: bipush  92
        //     89: if_icmpne  106 (offset +17)
        //     92: iinc  7, 1
        //     95: aload_1
        //     96: iload  7
        //     98: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //    101: istore  9
        //    103: iconst_1
        //    104: istore  5
        //    106: iload  7
        //    108: iload  4
        //    110: if_icmpge  177 (offset +67)
        //    113: iload  9
        //    115: lookupswitch  default->163, 46->157, 47->148, 92->148
        //    148: iconst_1
        //    149: istore  6
        //    151: iconst_m1
        //    152: istore  8
        //    154: goto  163 (offset +9)
        //    157: iload  7
        //    159: iconst_1
        //    160: isub
        //    161: istore  8
        //    163: aload_1
        //    164: iload  7
        //    166: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //    169: istore  9
        //    171: iinc  7, 1
        //    174: goto  106 (offset -68)
        //    177: iload  8
        //    179: iflt  205 (offset +26)
        //    182: getstatic  #27 // jnr.posix.util.Finder.EXECUTABLE_EXTENSIONS:Ljava/util/Collection;
        //    185: aload_1
        //    186: iload  8
        //    188: invokevirtual  #38 // java.lang.String.substring:(I)Ljava/lang/String;
        //    191: invokevirtual  #39 // java.lang.String.toLowerCase:()Ljava/lang/String;
        //    194: invokeinterface  #49 // java.util.Collection.contains:(Ljava/lang/Object;)Z, count 2
        //    199: ifne  205 (offset +6)
        //    202: iconst_m1
        //    203: istore  8
        //    205: iload_3
        //    206: ifne  216 (offset +10)
        //    209: iload  5
        //    211: ifeq  255 (offset +44)
        //    214: aload_1
        //    215: areturn
        //    216: iload  6
        //    218: ifeq  255 (offset +37)
        //    221: iload  8
        //    223: iflt  228 (offset +5)
        //    226: aload_1
        //    227: areturn
        //    228: iload_3
        //    229: ifeq  237 (offset +8)
        //    232: aload_1
        //    233: invokestatic  #47 // jnr.posix.util.Finder.addExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    236: areturn
        //    237: new  #10 // java.io.File
        //    240: dup
        //    241: aload_1
        //    242: invokespecial  #29 // java.io.File.<init>:(Ljava/lang/String;)V
        //    245: invokevirtual  #30 // java.io.File.exists:()Z
        //    248: ifeq  253 (offset +5)
        //    251: aload_1
        //    252: areturn
        //    253: aconst_null
        //    254: areturn
        //    255: aload_2
        //    256: getstatic  #26 // java.io.File.pathSeparator:Ljava/lang/String;
        //    259: invokevirtual  #37 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    262: astore  10
        //    264: iconst_0
        //    265: istore  11
        //    267: iload  11
        //    269: aload  10
        //    271: arraylength
        //    272: if_icmpge  555 (offset +283)
        //    275: aload  10
        //    277: iload  11
        //    279: aaload
        //    280: astore  12
        //    282: aload  12
        //    284: invokevirtual  #35 // java.lang.String.length:()I
        //    287: istore  13
        //    289: aload  12
        //    291: ifnull  549 (offset +258)
        //    294: iload  13
        //    296: ifne  302 (offset +6)
        //    299: goto  549 (offset +250)
        //    302: aload  12
        //    304: iconst_0
        //    305: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //    308: bipush  126
        //    310: if_icmpne  396 (offset +86)
        //    313: iload  13
        //    315: iconst_1
        //    316: if_icmpeq  347 (offset +31)
        //    319: iload  13
        //    321: iconst_1
        //    322: if_icmple  396 (offset +74)
        //    325: aload  12
        //    327: iconst_1
        //    328: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //    331: bipush  47
        //    333: if_icmpeq  347 (offset +14)
        //    336: aload  12
        //    338: iconst_1
        //    339: invokevirtual  #33 // java.lang.String.charAt:(I)C
        //    342: bipush  92
        //    344: if_icmpne  396 (offset +52)
        //    347: ldc  #7 // 'HOME'
        //    349: invokestatic  #43 // java.lang.System.getenv:(Ljava/lang/String;)Ljava/lang/String;
        //    352: astore  14
        //    354: aload  14
        //    356: ifnull  396 (offset +40)
        //    359: new  #14 // java.lang.StringBuilder
        //    362: dup
        //    363: invokespecial  #40 // java.lang.StringBuilder.<init>:()V
        //    366: aload  14
        //    368: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    371: iload  13
        //    373: iconst_1
        //    374: if_icmpne  382 (offset +8)
        //    377: ldc  #1 // ''
        //    379: goto  388 (offset +9)
        //    382: aload  12
        //    384: iconst_1
        //    385: invokevirtual  #38 // java.lang.String.substring:(I)Ljava/lang/String;
        //    388: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    391: invokevirtual  #42 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    394: astore  12
        //    396: aload  12
        //    398: ldc  #6 // '/'
        //    400: invokevirtual  #34 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //    403: ifne  438 (offset +35)
        //    406: aload  12
        //    408: ldc  #9 // '\\'
        //    410: invokevirtual  #34 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //    413: ifne  438 (offset +25)
        //    416: new  #14 // java.lang.StringBuilder
        //    419: dup
        //    420: invokespecial  #40 // java.lang.StringBuilder.<init>:()V
        //    423: aload  12
        //    425: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    428: ldc  #9 // '\\'
        //    430: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    433: invokevirtual  #42 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    436: astore  12
        //    438: new  #14 // java.lang.StringBuilder
        //    441: dup
        //    442: invokespecial  #40 // java.lang.StringBuilder.<init>:()V
        //    445: aload  12
        //    447: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    450: aload_1
        //    451: invokevirtual  #41 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    454: invokevirtual  #42 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    457: astore  14
        //    459: getstatic  #28 // jnr.posix.util.Platform.IS_WINDOWS:Z
        //    462: ifeq  476 (offset +14)
        //    465: aload  14
        //    467: bipush  47
        //    469: bipush  92
        //    471: invokevirtual  #36 // java.lang.String.replace:(CC)Ljava/lang/String;
        //    474: astore  14
        //    476: getstatic  #28 // jnr.posix.util.Platform.IS_WINDOWS:Z
        //    479: ifeq  507 (offset +28)
        //    482: iload_3
        //    483: ifeq  507 (offset +24)
        //    486: iload  8
        //    488: iconst_m1
        //    489: if_icmpne  507 (offset +18)
        //    492: aload  14
        //    494: invokestatic  #47 // jnr.posix.util.Finder.addExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    497: astore  15
        //    499: aload  15
        //    501: ifnull  549 (offset +48)
        //    504: aload  15
        //    506: areturn
        //    507: aload_0
        //    508: aload  14
        //    510: invokeinterface  #55 // jnr.posix.POSIX.stat:(Ljava/lang/String;)Ljnr/posix/FileStat;, count 2
        //    515: astore  15
        //    517: iload_3
        //    518: ifeq  541 (offset +23)
        //    521: aload  15
        //    523: invokeinterface  #53 // jnr.posix.FileStat.isDirectory:()Z, count 1
        //    528: ifne  544 (offset +16)
        //    531: aload  15
        //    533: invokeinterface  #54 // jnr.posix.FileStat.isExecutable:()Z, count 1
        //    538: ifeq  544 (offset +6)
        //    541: aload  14
        //    543: areturn
        //    544: goto  549 (offset +5)
        //    547: astore  15
        //    549: iinc  11, 1
        //    552: goto  267 (offset -285)
        //    555: aconst_null
        //    556: areturn
        //       Exception table:
        //         from 507 to 543 target 547 type java.lang.Throwable
    }

  public static String addExtension(String arg0) {
        Iterator var1 = EXECUTABLE_EXTENSIONS.iterator();
        String var3;
        while (true) {
            if (!var1.hasNext()) {
                return null;
            }
            String var2 = ((String) var1.next());
            var3 = new StringBuilder().append(arg0).append(var2).toString();
            if (new File(var3).exists()) {
                break;
            }
            continue;
        }
        return var3;
    }

}