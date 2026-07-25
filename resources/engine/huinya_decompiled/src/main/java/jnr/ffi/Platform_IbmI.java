// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.IbmI
package jnr.ffi;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jnr.ffi.Platform_OS;
import jnr.ffi.Platform_Supported;

final class Platform_IbmI extends Platform_Supported {

  public Platform_IbmI() { // было: <init>
        super(Platform_OS.IBMI);
    }

  public String mapLibraryName(String arg0) {
        if (!libPattern.matcher(arg0).find()) {
            return new StringBuilder().append("lib").append(arg0).append(".a(shr_64.o)").toString();
        } else {
            return arg0;
        }
    }

  public String locateLibrary(String arg0, List arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #16 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #44 // java.lang.StringBuilder.<init>:()V
        //      7: ldc  #11 // 'lib'
        //      9: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     12: aload_1
        //     13: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     16: ldc  #10 // '\\.so((?:\\.[0-9]+)*)$'
        //     18: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     21: invokevirtual  #46 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     24: invokestatic  #52 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //     27: astore_3
        //     28: new  #16 // java.lang.StringBuilder
        //     31: dup
        //     32: invokespecial  #44 // java.lang.StringBuilder.<init>:()V
        //     35: ldc  #11 // 'lib'
        //     37: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     40: aload_1
        //     41: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     44: ldc  #9 // '\\.(a|so)$'
        //     46: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     49: invokevirtual  #46 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     52: invokestatic  #52 // java.util.regex.Pattern.compile:(Ljava/lang/String;)Ljava/util/regex/Pattern;
        //     55: astore  4
        //     57: new  #19 // java.util.LinkedList
        //     60: dup
        //     61: invokespecial  #48 // java.util.LinkedList.<init>:()V
        //     64: astore  5
        //     66: new  #19 // java.util.LinkedList
        //     69: dup
        //     70: invokespecial  #48 // java.util.LinkedList.<init>:()V
        //     73: astore  6
        //     75: aload  6
        //     77: aload_2
        //     78: invokeinterface  #62 // java.util.List.addAll:(Ljava/util/Collection;)Z, count 2
        //     83: pop
        //     84: aload  6
        //     86: ldc  #5 // '/QOpenSys/pkgs/lib'
        //     88: invokeinterface  #61 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //     93: pop
        //     94: aload  6
        //     96: ldc  #6 // '/QOpenSys/usr/lib'
        //     98: invokeinterface  #61 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    103: pop
        //    104: new  #28 // jnr.ffi.Platform$IbmI$1
        //    107: dup
        //    108: aload_0
        //    109: aload  4
        //    111: aload_3
        //    112: invokespecial  #56 // jnr.ffi.Platform$IbmI$1.<init>:(Ljnr/ffi/Platform$IbmI;Ljava/util/regex/Pattern;Ljava/util/regex/Pattern;)V
        //    115: astore  7
        //    117: new  #18 // java.util.LinkedHashMap
        //    120: dup
        //    121: invokespecial  #47 // java.util.LinkedHashMap.<init>:()V
        //    124: astore  8
        //    126: aload  6
        //    128: invokeinterface  #65 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    133: astore  9
        //    135: aload  9
        //    137: invokeinterface  #59 // java.util.Iterator.hasNext:()Z, count 1
        //    142: ifeq  386 (offset +244)
        //    145: aload  9
        //    147: invokeinterface  #60 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    152: checkcast  #15 // java.lang.String
        //    155: astore  10
        //    157: aload  10
        //    159: invokestatic  #54 // jnr.ffi.Platform.access$100:()Ljava/util/Locale;
        //    162: invokevirtual  #43 // java.lang.String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //    165: ldc  #7 // '/qsys'
        //    167: invokevirtual  #42 // java.lang.String.startsWith:(Ljava/lang/String;)Z
        //    170: ifeq  176 (offset +6)
        //    173: goto  135 (offset -38)
        //    176: new  #13 // java.io.File
        //    179: dup
        //    180: aload  10
        //    182: invokespecial  #34 // java.io.File.<init>:(Ljava/lang/String;)V
        //    185: astore  11
        //    187: aload  11
        //    189: aload  7
        //    191: invokevirtual  #37 // java.io.File.listFiles:(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //    194: astore  12
        //    196: aload  12
        //    198: ifnonnull  204 (offset +6)
        //    201: goto  135 (offset -66)
        //    204: aload  12
        //    206: astore  13
        //    208: aload  13
        //    210: arraylength
        //    211: istore  14
        //    213: iconst_0
        //    214: istore  15
        //    216: iload  15
        //    218: iload  14
        //    220: if_icmpge  383 (offset +163)
        //    223: aload  13
        //    225: iload  15
        //    227: aaload
        //    228: astore  16
        //    230: aload  4
        //    232: aload  16
        //    234: invokevirtual  #36 // java.io.File.getName:()Ljava/lang/String;
        //    237: invokevirtual  #53 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    240: invokevirtual  #51 // java.util.regex.Matcher.matches:()Z
        //    243: ifeq  259 (offset +16)
        //    246: aload  5
        //    248: aload  16
        //    250: invokeinterface  #61 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    255: pop
        //    256: goto  377 (offset +121)
        //    259: aload_3
        //    260: aload  16
        //    262: invokevirtual  #36 // java.io.File.getName:()Ljava/lang/String;
        //    265: invokevirtual  #53 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    268: astore  17
        //    270: aload  17
        //    272: invokevirtual  #51 // java.util.regex.Matcher.matches:()Z
        //    275: ifeq  287 (offset +12)
        //    278: aload  17
        //    280: iconst_1
        //    281: invokevirtual  #50 // java.util.regex.Matcher.group:(I)Ljava/lang/String;
        //    284: goto  289 (offset +5)
        //    287: ldc  #1 // ''
        //    289: astore  18
        //    291: aload  18
        //    293: ifnull  304 (offset +11)
        //    296: aload  18
        //    298: invokevirtual  #40 // java.lang.String.isEmpty:()Z
        //    301: ifeq  312 (offset +11)
        //    304: iconst_0
        //    305: newarray  int
        //    307: astore  19
        //    309: goto  362 (offset +53)
        //    312: aload  18
        //    314: ldc  #8 // '\\.'
        //    316: invokevirtual  #41 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    319: astore  20
        //    321: aload  20
        //    323: arraylength
        //    324: iconst_1
        //    325: isub
        //    326: newarray  int
        //    328: astore  19
        //    330: iconst_1
        //    331: istore  21
        //    333: iload  21
        //    335: aload  20
        //    337: arraylength
        //    338: if_icmpge  362 (offset +24)
        //    341: aload  19
        //    343: iload  21
        //    345: iconst_1
        //    346: isub
        //    347: aload  20
        //    349: iload  21
        //    351: aaload
        //    352: invokestatic  #38 // java.lang.Integer.parseInt:(Ljava/lang/String;)I
        //    355: iastore
        //    356: iinc  21, 1
        //    359: goto  333 (offset -26)
        //    362: aload  8
        //    364: aload  16
        //    366: invokevirtual  #35 // java.io.File.getAbsolutePath:()Ljava/lang/String;
        //    369: aload  19
        //    371: invokeinterface  #67 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    376: pop
        //    377: iinc  15, 1
        //    380: goto  216 (offset -164)
        //    383: goto  135 (offset -248)
        //    386: aconst_null
        //    387: astore  9
        //    389: aconst_null
        //    390: astore  10
        //    392: aload  8
        //    394: invokeinterface  #66 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //    399: invokeinterface  #70 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //    404: astore  11
        //    406: aload  11
        //    408: invokeinterface  #59 // java.util.Iterator.hasNext:()Z, count 1
        //    413: ifeq  473 (offset +60)
        //    416: aload  11
        //    418: invokeinterface  #60 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    423: checkcast  #22 // java.util.Map$Entry
        //    426: astore  12
        //    428: aload  12
        //    430: invokeinterface  #68 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //    435: checkcast  #15 // java.lang.String
        //    438: astore  13
        //    440: aload  12
        //    442: invokeinterface  #69 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //    447: checkcast  #12 // [I
        //    450: astore  14
        //    452: aload  14
        //    454: aload  9
        //    456: invokestatic  #57 // jnr.ffi.Platform$Linux.access$300:([I[I)I
        //    459: ifle  470 (offset +11)
        //    462: aload  13
        //    464: astore  10
        //    466: aload  14
        //    468: astore  9
        //    470: goto  406 (offset -64)
        //    473: aconst_null
        //    474: aload  10
        //    476: if_acmpeq  482 (offset +6)
        //    479: aload  10
        //    481: areturn
        //    482: aload  5
        //    484: invokeinterface  #64 // java.util.List.isEmpty:()Z, count 1
        //    489: ifne  543 (offset +54)
        //    492: aload  5
        //    494: iconst_0
        //    495: invokeinterface  #63 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    500: checkcast  #13 // java.io.File
        //    503: invokevirtual  #35 // java.io.File.getAbsolutePath:()Ljava/lang/String;
        //    506: astore  11
        //    508: aload  11
        //    510: ldc  #3 // '.a'
        //    512: invokevirtual  #39 // java.lang.String.endsWith:(Ljava/lang/String;)Z
        //    515: ifeq  540 (offset +25)
        //    518: new  #16 // java.lang.StringBuilder
        //    521: dup
        //    522: invokespecial  #44 // java.lang.StringBuilder.<init>:()V
        //    525: aload  11
        //    527: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    530: ldc  #2 // '(shr_64.o)'
        //    532: invokevirtual  #45 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    535: invokevirtual  #46 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    538: astore  11
        //    540: aload  11
        //    542: areturn
        //    543: aload_0
        //    544: aload_1
        //    545: invokevirtual  #55 // jnr.ffi.Platform$IbmI.mapLibraryName:(Ljava/lang/String;)Ljava/lang/String;
        //    548: areturn
    }

}