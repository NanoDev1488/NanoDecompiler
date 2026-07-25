// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cQ
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.ch;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.minecraft.class_3532;

public final class cQ {

    // ---- поля ----
  public static final int oi;
  private static final Pattern field919; // было: a
  private static final String xZ = "// === DO NOT TOUCH ===";
  private static final String ya = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String yb = "// this jar protected by JoinerObfuscator";
  private static final String yc = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String yd = "// === DO NOT TOUCH ===";
  private static final int oj = 1173965071;
  private static final int ok = 1927819111;
  private static final int ol = 30073910;
  private static final byte[] dB;

    static {
        dB = "nE;#W_{\"N1k}..8)_h.jUASEom4`\\Db;1( 9_&_8g-=`{3;GKvO7rWFw5n~CYG+mu+-wt+VV_I_E~)yc'.glck@%<KeVc/[,hw2uZqTgemkGZ#1U\\6{5a4H/x3_O#Ty*X3wAs5-qks*m64[;r\"hV<51jq]3f\\Jg]AsK^:lFq GxQYm~y&2LO)ryDJa_Vr142,#[*rD>9T7-ZNzXM/ro_krh:9K\\<@FMtqYN^K%N2X~7_9M_dkmh9/o=uOR'`k<IR".getBytes("ISO-8859-1");
        oi = method1719(-1832028632 ^ -1832028457, -1025142021 ^ -1025142098, -1170520168 ^ -1170520115, -1764549591 ^ -1764549418);
        field919 = Pattern.compile(Decryptor.method1945(XorDecoder.method1946("}²\u000cD\u001cND2Ï.Vz¤-WqÆ\u000fT��\u0008M\u000e¦03x§H%\u007f��-\u001fJH>\u000f!", -47313988 ^ -514392844)));
    }

  public static int method1699(int arg0) { // было: d
        return arg0 >> (-1924181188 ^ -1924181204) & (-1769514609 ^ -1769514640);
    }

  public static int method1700(int arg0) { // было: e
        return arg0 >> (-1091625404 ^ -1091625396) & (-1130150574 ^ -1130150483);
    }

  public static int method1701(int arg0) { // было: f
        return arg0 & (41439239 ^ 41439480);
    }

  public static int method1702(int arg0) { // было: g
        return arg0 >> (-213129095 ^ -213129119) & (2138286376 ^ 2138286551);
    }

  public static float method1703(int arg0) { // было: b
        return ((float) method1699(arg0)) / 255.0f;
    }

  public static float method1704(int arg0) { // было: c
        return ((float) method1700(arg0)) / 255.0f;
    }

  public static float method1705(int arg0) { // было: d
        return ((float) method1701(arg0)) / 255.0f;
    }

  public static float method1706(int arg0) { // было: e
        return ((float) method1702(arg0)) / 255.0f;
    }

  public static int[] method1707(int arg0) { // было: a
        int[] __obj1 = new int[-264894841 ^ -264894845];
        __obj1[-904669762 ^ -904669762] = method1699(arg0);
        __obj1[-1590476035 ^ -1590476036] = method1700(arg0);
        __obj1[-1678414771 ^ -1678414769] = method1701(arg0);
        __obj1[1048785427 ^ 1048785424] = method1702(arg0);
        return __obj1;
    }

  public static int[] method1708(int arg0) { // было: b
        int[] __obj1 = new int[-459803816 ^ -459803813];
        __obj1[-1820164209 ^ -1820164209] = method1699(arg0);
        __obj1[-1470243299 ^ -1470243300] = method1700(arg0);
        __obj1[-829720105 ^ -829720107] = method1701(arg0);
        return __obj1;
    }

  public static float[] method1709(int arg0) { // было: a
        float[] __obj1 = new float[-2095012424 ^ -2095012420];
        __obj1[-1561790763 ^ -1561790763] = method1703(arg0);
        __obj1[-622847280 ^ -622847279] = method1704(arg0);
        __obj1[1471479250 ^ 1471479248] = method1705(arg0);
        __obj1[741702566 ^ 741702565] = method1706(arg0);
        return __obj1;
    }

  public static float[] method1710(int arg0) { // было: b
        float[] __obj1 = new float[1475950439 ^ 1475950436];
        __obj1[-215826033 ^ -215826033] = method1703(arg0);
        __obj1[-1033261296 ^ -1033261295] = method1704(arg0);
        __obj1[-1539668049 ^ -1539668051] = method1705(arg0);
        return __obj1;
    }

  public static boolean method1711(String arg0) { // было: k
        return arg0 == null ? 1453377420 ^ 1453377420 : !arg0.matches(Decryptor.method1945(XorDecoder.method1946("ÊúuÐ°çB×ê \u0014µµðV¸×\u0011±»¢YÒìô\u0015çÑC¤Èe´ïýt­½ý\u001d", 602661771 ^ 58276970))) ? 1453377420 ^ 1453377420 : 729610154 ^ 729610155;
    }

  public static bp method1712(String arg0, bp arg1) { // было: a
        if (method1711(arg0)) {
            int var2 = Integer.parseInt(arg0, -1894720100 ^ -1894720116);
            int var3 = var2 >> (854776712 ^ 854776728) & (-1842450063 ^ -1842450034);
            int var4 = var2 >> (-85497031 ^ -85497039) & (-571793562 ^ -571793511);
            int var5 = var2 & (1493737122 ^ 1493737053);
            return new bp(new Color(var3, var4, var5));
        } else {
            return arg1;
        }
    }

  public static String method1713(bp arg0) { // было: a
        int var1 = arg0.method1680();
        Object[] __obj1 = new Object[-1825978824 ^ -1825978823];
        __obj1[-1449205619 ^ -1449205619] = Integer.valueOf(var1 & (-128088664 ^ -123569577));
        return String.format(Decryptor.method1945(XorDecoder.method1946("»'\u0008²ü,&ÈÇ\u0005'Â<C¶¼|\u0019¶ÜpQ", 685320226 ^ 1150592477)), __obj1);
    }

  public static bp method1714(int arg0, int arg1, bp arg2, bp arg3) { // было: a
        int __stk1;
        int var4 = ((int) ((System.currentTimeMillis() / ((long) arg0) + ((long) arg1)) % (-6783108400273211989L ^ -6783108400273212221L)));
        __stk1 = var4 < (-821830344 ^ -821830260) ? var4 : (-1152836498 ^ -1152836346) - var4;
        var4 = __stk1 * (-1210409467 ^ -1210409465);
        return method1716(arg2, arg3, ((float) var4) / 360.0f);
    }

  public static bp method1715(int arg0, int arg1, bp[] arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #191 // java.lang.System.currentTimeMillis:()J
        //      3: iload_0
        //      4: i2l
        //      5: ldiv
        //      6: iload_1
        //      7: i2l
        //      8: ladd
        //      9: ldc2_w  #158 // -4444631038567754482L
        //     12: ldc2_w  #156 // -4444631038567754650L
        //     15: lxor
        //     16: lrem
        //     17: l2i
        //     18: istore_3
        //     19: iload_3
        //     20: ldc  #118 // 2101198454
        //     22: ldc  #119 // 2101198530
        //     24: ixor
        //     25: if_icmple  38 (offset +13)
        //     28: ldc  #61 // -509819014
        //     30: ldc  #60 // -509819374
        //     32: ixor
        //     33: iload_3
        //     34: isub
        //     35: goto  39 (offset +4)
        //     38: iload_3
        //     39: ldc  #28 // -1554015524
        //     41: ldc  #27 // -1554015640
        //     43: ixor
        //     44: iadd
        //     45: istore_3
        //     46: iload_3
        //     47: i2f
        //     48: ldc  #124 // 360.0f
        //     50: fdiv
        //     51: aload_2
        //     52: arraylength
        //     53: i2f
        //     54: fmul
        //     55: f2i
        //     56: istore  4
        //     58: iload  4
        //     60: aload_2
        //     61: arraylength
        //     62: if_icmpne  68 (offset +6)
        //     65: iinc  4, -1
        //     68: aload_2
        //     69: iload  4
        //     71: aaload
        //     72: astore  5
        //     74: aload_2
        //     75: iload  4
        //     77: aload_2
        //     78: arraylength
        //     79: ldc  #105 // 1575090712
        //     81: ldc  #106 // 1575090713
        //     83: ixor
        //     84: isub
        //     85: if_icmpne  96 (offset +11)
        //     88: ldc  #23 // -1605914386
        //     90: ldc  #23 // -1605914386
        //     92: ixor
        //     93: goto  104 (offset +11)
        //     96: iload  4
        //     98: ldc  #65 // -424886543
        //    100: ldc  #64 // -424886544
        //    102: ixor
        //    103: iadd
        //    104: aaload
        //    105: astore  6
        //    107: aload  5
        //    109: aload  6
        //    111: iload_3
        //    112: i2f
        //    113: ldc  #124 // 360.0f
        //    115: fdiv
        //    116: aload_2
        //    117: arraylength
        //    118: i2f
        //    119: fmul
        //    120: iload  4
        //    122: i2f
        //    123: fsub
        //    124: invokestatic  #172 // dev.angelvisuals.a.cQ.b:(Ldev/angelvisuals/a/bp;Ldev/angelvisuals/a/bp;F)Ldev/angelvisuals/a/bp;
        //    127: areturn
    }

  public static bp method1716(bp arg0, bp arg1, float arg2) { // было: b
        return arg0.method1688(arg1, arg2);
    }

  public static String method1717(String arg0) { // было: e
        return arg0 == null ? null : arg0.isEmpty() ? null : field919.matcher(arg0).replaceAll(Decryptor.method1945(XorDecoder.method1946("®£\u0013ºª\u0008£¥7É!­½2ûµYË", -1946174902 ^ 2107330263)));
    }

  public static int method1718(int arg0, float arg1) { // было: e
        return method1719(method1699(arg0), method1700(arg0), method1701(arg0), Math.round(((float) method1702(arg0)) * arg1));
    }

  private static int method1719(int arg0, int arg1, int arg2, int arg3) { // было: a
        return class_3532.method_15340(arg3, 2058311066 ^ 2058311066, 1880103825 ^ 1880103790) << (1631643740 ^ 1631643716) | class_3532.method_15340(arg0, -1552920234 ^ -1552920234, 1921352235 ^ 1921352404) << (1400347973 ^ 1400347989) | class_3532.method_15340(arg1, -335254013 ^ -335254013, 2011839020 ^ 2011839187) << (415146488 ^ 415146480) | class_3532.method_15340(arg2, 618185955 ^ 618185955, -1692822618 ^ -1692822695);
    }

  public static int method1720(int arg0) { // было: h
        return AngelVisuals.getInstance().getThemeManager().method480(arg0).method1680();
    }

    @Generated
  private cQ() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("Æãú¬ôÆ²ÌíñüùòÈÜßó³àÊòýËç¯ßÞè¸ÿÿ÷¶þ¤þÇÛµ´çÆÁèãóéü¦ÕíðïÏìÏÜÏÿóãË÷îðóÐéñÆ", 1753334805 ^ -970505257)));
    }

  private static int mA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mB(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}