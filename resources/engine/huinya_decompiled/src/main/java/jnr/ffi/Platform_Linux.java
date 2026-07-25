// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.Linux
package jnr.ffi;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jnr.ffi.LibraryOption;
import jnr.ffi.Platform_Linux_Match;
import jnr.ffi.Platform_OS;
import jnr.ffi.Platform_Supported;

final class Platform_Linux extends Platform_Supported {

  public Platform_Linux() { // было: <init>
        super(Platform_OS.LINUX);
    }

  public String locateLibrary(String arg0, List arg1) {
        return locateLibrary(arg0, arg1, null);
    }

  public String locateLibrary(String arg0, List arg1, Map arg2) {
        int __stk1;
        List var4 = getMatches(arg0, arg1);
        if (!var4.isEmpty()) {
            __stk1 = arg2 == null ? 0 : arg2.containsKey(LibraryOption.PreferCustomPaths);
        } else {
            return mapLibraryName(arg0);
        }
        int var5 = __stk1;
        Collections.sort(var4);
        Object var6 = null;
        if (var5 != 0) {
            Iterator var7 = var4.iterator();
            while (var7.hasNext()) {
                Platform_Linux_Match var8 = ((Platform_Linux_Match) var7.next());
                if (!var8.isCustom) {
                    continue;
                } else {
                    var6 = var8;
                    break;
                }
            }
        }
        return var6 == null ? (((Platform_Linux_Match) var4.get(0))).path : var6.path;
    }

  private List getMatches(String arg0, List arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #15 // java.util.ArrayList
        //      3: dup
        //      4: invokespecial  #53 // java.util.ArrayList.<init>:()V
        //      7: astore_3
        //      8: getstatic  #33 // jnr.ffi.LibraryLoader$DefaultLibPaths.PATHS:Ljava/util/List;
        //     11: invokeinterface  #77 // java.util.List.size:()I, count 1
        //     16: ifle  97 (offset +81)
        //     19: aload_2
        //     20: invokeinterface  #77 // java.util.List.size:()I, count 1
        //     25: getstatic  #33 // jnr.ffi.LibraryLoader$DefaultLibPaths.PATHS:Ljava/util/List;
        //     28: invokeinterface  #77 // java.util.List.size:()I, count 1
        //     33: if_icmplt  97 (offset +64)
        //     36: getstatic  #33 // jnr.ffi.LibraryLoader$DefaultLibPaths.PATHS:Ljava/util/List;
        //     39: iconst_0
        //     40: invokeinterface  #73 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     45: checkcast  #13 // java.lang.String
        //     48: astore  4
        //     50: aload_2
        //     51: aload  4
        //     53: invokeinterface  #76 // java.util.List.lastIndexOf:(Ljava/lang/Object;)I, count 2
        //     58: istore  5
        //     60: iconst_0
        //     61: istore  6
        //     63: iload  6
        //     65: iload  5
        //     67: if_icmpge  94 (offset +27)
        //     70: aload_3
        //     71: aload_2
        //     72: iload  6
        //     74: invokeinterface  #73 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     79: checkcast  #13 // java.lang.String
        //     82: invokeinterface  #70 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //     87: pop
        //     88: iinc  6, 1
        //     91: goto  63 (offset -28)
        //     94: goto  105 (offset +11)
        //     97: aload_3
        //     98: aload_2
        //     99: invokeinterface  #71 // java.util.List.addAll:(Ljava/util/Collection;)Z, count 2
        //    104: pop
        //    105: aload_0
        //    106: invokevirtual  #60 // jnr.ffi.Platform$Linux.getCPU:()Ljnr/ffi/Platform$CPU;
        //    109: getstatic  #35 // jnr.ffi.Platform$CPU.X86_64:Ljnr/ffi/Platform$CPU;
        //    112: if_acmpne  125 (offset +13)
        //    115: ldc  #2 // '.*(lib[a-z]*32|i[0-9]86).*'
        //    117: invokestatic  #57 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //    120: astore  4
        //    122: goto  132 (offset +10)
        //    125: ldc  #3 // '.*(lib[a-z]*64|amd64|x86_64).*'
        //    127: invokestatic  #57 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //    130: astore  4
        //    132: new  #14 // java.lang.StringBuilder
        //    135: dup
        //    136: invokespecial  #50 // java.lang.StringBuilder.<init>:()V
        //    139: ldc  #7 // 'lib'
        //    141: invokevirtual  #51 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    144: aload_1
        //    145: invokevirtual  #51 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    148: ldc  #5 // '\\.so((?:\\.[0-9]+)*)$'
        //    150: invokevirtual  #51 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    153: invokevirtual  #52 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    156: invokestatic  #57 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //    159: astore  5
        //    161: new  #29 // jnr.ffi.Platform$Linux$1
        //    164: dup
        //    165: aload_0
        //    166: aload  5
        //    168: invokespecial  #64 // jnr.ffi.Platform$Linux$1.<init>:(Ljnr/ffi/Platform$Linux;Ljava/util/regex/Pattern;)V
        //    171: astore  6
        //    173: new  #15 // java.util.ArrayList
        //    176: dup
        //    177: invokespecial  #53 // java.util.ArrayList.<init>:()V
        //    180: astore  7
        //    182: aload_2
        //    183: invokeinterface  #75 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    188: astore  8
        //    190: aload  8
        //    192: invokeinterface  #68 // java.util.Iterator.hasNext:()Z, count 1
        //    197: ifeq  445 (offset +248)
        //    200: aload  8
        //    202: invokeinterface  #69 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    207: checkcast  #13 // java.lang.String
        //    210: astore  9
        //    212: aload  4
        //    214: aload  9
        //    216: invokevirtual  #58 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    219: invokevirtual  #56 // java.util.regex.Matcher.matches:()Z
        //    222: ifeq  228 (offset +6)
        //    225: goto  190 (offset -35)
        //    228: new  #10 // java.io.File
        //    231: dup
        //    232: aload  9
        //    234: invokespecial  #40 // java.io.File.<init>:(Ljava/lang/String;)V
        //    237: astore  10
        //    239: aload  10
        //    241: aload  6
        //    243: invokevirtual  #43 // java.io.File.listFiles:(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //    246: astore  11
        //    248: aload  11
        //    250: ifnonnull  256 (offset +6)
        //    253: goto  190 (offset -63)
        //    256: aload  11
        //    258: astore  12
        //    260: aload  12
        //    262: arraylength
        //    263: istore  13
        //    265: iconst_0
        //    266: istore  14
        //    268: iload  14
        //    270: iload  13
        //    272: if_icmpge  442 (offset +170)
        //    275: aload  12
        //    277: iload  14
        //    279: aaload
        //    280: astore  15
        //    282: aload  5
        //    284: aload  15
        //    286: invokevirtual  #42 // java.io.File.getName:()Ljava/lang/String;
        //    289: invokevirtual  #58 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    292: astore  16
        //    294: aload  16
        //    296: invokevirtual  #56 // java.util.regex.Matcher.matches:()Z
        //    299: ifeq  311 (offset +12)
        //    302: aload  16
        //    304: iconst_1
        //    305: invokevirtual  #55 // java.util.regex.Matcher.group:(I)Ljava/lang/String;
        //    308: goto  313 (offset +5)
        //    311: ldc  #1 // ''
        //    313: astore  17
        //    315: aload  17
        //    317: ifnull  328 (offset +11)
        //    320: aload  17
        //    322: invokevirtual  #48 // java.lang.String.isEmpty:()Z
        //    325: ifeq  336 (offset +11)
        //    328: iconst_0
        //    329: newarray  int
        //    331: astore  18
        //    333: goto  386 (offset +53)
        //    336: aload  17
        //    338: ldc  #4 // '\\.'
        //    340: invokevirtual  #49 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    343: astore  19
        //    345: aload  19
        //    347: arraylength
        //    348: iconst_1
        //    349: isub
        //    350: newarray  int
        //    352: astore  18
        //    354: iconst_1
        //    355: istore  20
        //    357: iload  20
        //    359: aload  19
        //    361: arraylength
        //    362: if_icmpge  386 (offset +24)
        //    365: aload  18
        //    367: iload  20
        //    369: iconst_1
        //    370: isub
        //    371: aload  19
        //    373: iload  20
        //    375: aaload
        //    376: invokestatic  #45 // java.lang.Integer.parseInt:(Ljava/lang/String;)I
        //    379: iastore
        //    380: iinc  20, 1
        //    383: goto  357 (offset -26)
        //    386: new  #30 // jnr.ffi.Platform$Linux$Match
        //    389: dup
        //    390: aconst_null
        //    391: invokespecial  #65 // jnr.ffi.Platform$Linux$Match.<init>:(Ljnr/ffi/Platform$1;)V
        //    394: astore  19
        //    396: aload  19
        //    398: aload  15
        //    400: invokevirtual  #41 // java.io.File.getAbsolutePath:()Ljava/lang/String;
        //    403: putfield  #37 // jnr.ffi.Platform$Linux$Match.path:Ljava/lang/String;
        //    406: aload  19
        //    408: aload  18
        //    410: putfield  #38 // jnr.ffi.Platform$Linux$Match.version:[I
        //    413: aload  19
        //    415: aload_3
        //    416: aload  9
        //    418: invokeinterface  #72 // java.util.List.contains:(Ljava/lang/Object;)Z, count 2
        //    423: putfield  #36 // jnr.ffi.Platform$Linux$Match.isCustom:Z
        //    426: aload  7
        //    428: aload  19
        //    430: invokeinterface  #70 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    435: pop
        //    436: iinc  14, 1
        //    439: goto  268 (offset -171)
        //    442: goto  190 (offset -252)
        //    445: aload  7
        //    447: areturn
    }

  private static int compareVersions(int[] arg0, int[] arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ifnonnull  14 (offset +13)
        //      4: aload_1
        //      5: ifnonnull  12 (offset +7)
        //      8: iconst_0
        //      9: goto  13 (offset +4)
        //     12: iconst_m1
        //     13: ireturn
        //     14: aload_1
        //     15: ifnonnull  20 (offset +5)
        //     18: iconst_1
        //     19: ireturn
        //     20: aload_0
        //     21: arraylength
        //     22: aload_1
        //     23: arraylength
        //     24: invokestatic  #46 // java.lang.Math.min:(II)I
        //     27: istore_2
        //     28: iconst_0
        //     29: istore_3
        //     30: iload_3
        //     31: iload_2
        //     32: if_icmpge  63 (offset +31)
        //     35: aload_0
        //     36: iload_3
        //     37: iaload
        //     38: aload_1
        //     39: iload_3
        //     40: iaload
        //     41: if_icmpge  46 (offset +5)
        //     44: iconst_m1
        //     45: ireturn
        //     46: aload_0
        //     47: iload_3
        //     48: iaload
        //     49: aload_1
        //     50: iload_3
        //     51: iaload
        //     52: if_icmple  57 (offset +5)
        //     55: iconst_1
        //     56: ireturn
        //     57: iinc  3, 1
        //     60: goto  30 (offset -30)
        //     63: aload_0
        //     64: arraylength
        //     65: aload_1
        //     66: arraylength
        //     67: invokestatic  #44 // java.lang.Integer.compare:(II)I
        //     70: ireturn
    }

  public String mapLibraryName(String arg0) {
        return "c".equals(arg0) ? "libc.so.6" : !"libc.so".equals(arg0) ? super.mapLibraryName(arg0) : "libc.so.6";
    }

  static int access$300(int[] arg0, int[] arg1) {
        return compareVersions(arg0, arg1);
    }

}