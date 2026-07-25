// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.dv
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.cF;
import java.util.Locale;
import lombok.Generated;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import ru.nexusguard.protection.annotations.Native;

public final class dv implements cF {

    // ---- поля ----
  private static final String DA = "// every class watermarked, every string encrypted, every number xored";
  private static final String DB = "// number obfuscation: ENABLED (XOR masking)";
  private static final String DC = "// Joiner sees you";
  private static final String DD = "// good luck with the next 9999 classes";
  private static final String DE = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int rz = -194212101;
  private static final int rA = 1298621529;
  private static final int rB = 64913670;
  private static final byte[] eC;

    static {
        eC = "ob D.j&1?Hz~*r08[$tUi<$E6?GB.t]N<z:QVr{yXV{@~O->AzGn+TXOTSDehq#k^'VDb9SzQ$:0ntC>i|\"suA:-G^[WoK0X7{OOjAsS^<b/<0qOaTRSO2sW5@3Z0Fj^bNd?1[4CgPmL1U?VX1J|0=WoW(F!GzpYD\"a3Xg2q4\\bQ;risi*2VWMJc*5|hAu&~jBp,6r/ [9FxNsvE_yIb,WNe|=eSo+KUpXG]iC5Jc!^o%bK(52yEklX7jV# ;Qup".getBytes("ISO-8859-1");
    }

  public static String method1194(double arg0) { // было: a
        Object[] __obj1 = new Object[1284204638 ^ 1284204639];
        __obj1[-284180979 ^ -284180979] = Double.valueOf(arg0);
        return String.format(Locale.US, Decryptor.method1945(XorDecoder.method1946("yíE\u0006\u0005ý!%rÕG-\u000fF]eÛ;\\Xé3U", 1371264640 ^ 968191670)), __obj1);
    }

    @Native
  public static class_2561 method1195(class_2561 arg0, boolean arg1) { // было: a
        class_2583 __stk4;
        class_5481 var2 = arg0.method_30937();
        class_5250 var3 = class_2561.method_43473();
        StringBuilder var4 = new StringBuilder();
        class_2583[] __obj1 = new class_2583[1619729347 ^ 1619729346];
        __obj1[-864745460 ^ -864745460] = class_2583.field_24360;
        class_2583[] var5 = __obj1;
        int[] __obj2 = new int[-142822588 ^ -142822587];
        __obj2[-1275776681 ^ -1275776681] = 206448178 ^ 206448178;
        int[] var6 = __obj2;
        boolean[] __obj3 = new boolean[-997335216 ^ -997335215];
        __obj3[1825866018 ^ 1825866018] = -588926271 ^ -588926271;
        boolean[] var7 = __obj3;
        var2.accept((lp0, lp1, lp2) -> method1202(var6, var7, var5, var4, var3, lp0, lp1, lp2));
        if (var4.length() > 0) {
            var3.method_10852(class_2561.method_43470(var4.toString()).method_10862(((class_2583) var5[1251021485 ^ 1251021485])));
        }
        if (arg1) {
            if (var7[-1319249813 ^ -1319249813] != 0) {
                __stk4 = var5[1352381882 ^ 1352381882] == null ? arg0.method_10866() : var5[1581794177 ^ 1581794177];
                Object var8 = __stk4;
                var3.method_10852(class_2561.method_43470(Decryptor.method1945(XorDecoder.method1946("ºo ¼Ù\u000e°×~ÄDN¥Ì\u0001É", -1275952178 ^ 1204708145))).method_10862(((class_2583) var8)));
            }
        }
        return var3;
    }

  public static class_5250 method1196(class_2561 arg0, String arg1) { // было: a
        class_5481 var2 = arg0.method_30937();
        int[] __obj1 = new int[-1725297648 ^ -1725297647];
        __obj1[1784741408 ^ 1784741408] = -876569628 ^ -876569628;
        int[] var3 = __obj1;
        var2.accept((lp0, lp1, lp2) -> method1201(var3, lp0, lp1, lp2));
        if (var3[-1682614131 ^ -1682614131] != 0) {
            int var4 = var3[-413295000 ^ -413295000] - (-1576956788 ^ -1576956787);
            class_5250 var5 = class_2561.method_43473();
            StringBuilder var6 = new StringBuilder();
            class_2583[] __obj2 = new class_2583[1373084820 ^ 1373084821];
            __obj2[1314210904 ^ 1314210904] = null;
            class_2583[] var7 = __obj2;
            int[] __obj3 = new int[-1332945935 ^ -1332945936];
            __obj3[455163251 ^ 455163251] = -538767329 ^ -538767329;
            int[] var8 = __obj3;
            var2.accept((lp0, lp1, lp2) -> method1200(var8, var4, var7, var5, var6, lp0, lp1, lp2));
            method1197(var5, var6, ((class_2583) var7[-984119772 ^ -984119772]));
            return var5;
        } else {
            return arg0.method_27661();
        }
    }

    @Native
  private static void method1197(class_5250 arg0, StringBuilder arg1, class_2583 arg2) { // было: a
        if (arg1.length() != 0) {
            class_5250 var3 = class_2561.method_43470(arg1.toString());
            if (arg2 != null) {
                var3.method_10862(arg2);
            }
            arg0.method_10852(var3);
            arg1.setLength(1368572065 ^ 1368572065);
        }
    }

    @Native
  public static class_2561 method1198(class_2561 arg0, String arg1, boolean arg2) { // было: a
        class_2583 __stk3;
        if (arg1 == null) {
            return arg0;
        } else {
            if (arg1.isEmpty()) {
                return arg0;
            } else {
                class_5481 var3 = arg0.method_30937();
                class_5250 var4 = class_2561.method_43473();
                StringBuilder var5 = new StringBuilder();
                class_2583[] __obj1 = new class_2583[1230469584 ^ 1230469585];
                __obj1[-1880522577 ^ -1880522577] = class_2583.field_24360;
                class_2583[] var6 = __obj1;
                StringBuilder var7 = new StringBuilder();
                boolean[] __obj2 = new boolean[-1161844747 ^ -1161844748];
                __obj2[-337818287 ^ -337818287] = -510170424 ^ -510170424;
                boolean[] var8 = __obj2;
                var3.accept((lp0, lp1, lp2) -> method1199(var7, arg1, var8, var6, var5, var4, lp0, lp1, lp2));
                if (var5.length() > 0) {
                    var4.method_10852(class_2561.method_43470(var5.toString()).method_10862(((class_2583) var6[-221829602 ^ -221829602])));
                }
                if (arg2) {
                    if (var8[-1171501590 ^ -1171501590] != 0) {
                        __stk3 = var6[593448792 ^ 593448792] == null ? arg0.method_10866() : var6[917459566 ^ 917459566];
                        Object var9 = __stk3;
                        var4.method_10852(class_2561.method_43470(Decryptor.method1945(XorDecoder.method1946("\u000b³%áÒ°)ï¢ù\u000eª¤\u0008¿U¿Ýô", 1581639753 ^ -1750781905))).method_10862(((class_2583) var9)));
                    }
                }
                return var4;
            }
        }
    }

    @Generated
  private dv() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("ßÝ`pÅ·wLì×n\u000bÙê\u007fs¬Ó_U­¨c@¬æHmñÄ]bæä\u001cu©­}\u0014úåDQë¯dLÅÚkiÌèjvøÎn\u0011®ôL\u0011Æ÷LVÄÔ\u0011u­øH\u000f×ìm\u0017®èSc¯Ý\u0018\u0019", 1311732184 ^ 1779100996)));
    }

  private static boolean method1199(StringBuilder arg0, String arg1, boolean[] arg2, class_2583[] arg3, StringBuilder arg4, class_5250 arg5, int arg6, class_2583 arg7, int arg8) { // было: a
        String var9 = new String(Character.toChars(arg8));
        if (!arg0.toString().contains(arg1)) {
            arg0.append(var9);
            if (!arg7.equals(arg3[1994096265 ^ 1994096265])) {
                if (arg4.length() > 0) {
                    arg5.method_10852(class_2561.method_43470(arg4.toString()).method_10862(((class_2583) arg3[1341068531 ^ 1341068531])));
                    arg4.setLength(292258976 ^ 292258976);
                }
                arg3[-1867207625 ^ -1867207625] = arg7;
            }
            arg4.append(var9);
            return 515940094 ^ 515940095;
        } else {
            arg2[14974581 ^ 14974581] = 1881415139 ^ 1881415138;
            return -1356533484 ^ -1356533484;
        }
    }

  private static boolean method1200(int[] arg0, int arg1, class_2583[] arg2, class_5250 arg3, StringBuilder arg4, int arg5, class_2583 arg6, int arg7) { // было: a
        int __stk1;
        __stk1 = arg0[-132889081 ^ -132889081] != arg1 ? -1281618634 ^ -1281618634 : -187152105 ^ -187152106;
        int var8 = __stk1;
        if (arg2[-1637100193 ^ -1637100193] == null) {
            method1197(arg3, arg4, ((class_2583) arg2[-2035192201 ^ -2035192201]));
            arg2[699042606 ^ 699042606] = arg6;
        } else {
            if (!arg2[289355388 ^ 289355388].equals(arg6)) {
                method1197(arg3, arg4, ((class_2583) arg2[-2035192201 ^ -2035192201]));
                arg2[699042606 ^ 699042606] = arg6;
            }
        }
        if (var8 == 0) {
        }
        arg4.appendCodePoint(arg7);
        arg0[-1791169077 ^ -1791169077] = arg0[-1791169077 ^ -1791169077] + (244865936 ^ 244865937);
        int var9 = arg0[-1791169077 ^ -1791169077];
        return 455505871 ^ 455505870;
    }

  private static boolean method1201(int[] arg0, int arg1, class_2583 arg2, int arg3) { // было: a
        arg0[659700795 ^ 659700795] = arg0[659700795 ^ 659700795] + (-2006540034 ^ -2006540033);
        int var4 = arg0[659700795 ^ 659700795];
        return -1085709280 ^ -1085709279;
    }

  private static boolean method1202(int[] arg0, boolean[] arg1, class_2583[] arg2, StringBuilder arg3, class_5250 arg4, int arg5, class_2583 arg6, int arg7) { // было: a
        if (arg0[1784677461 ^ 1784677461] <= (-1197613763 ^ -1197613761)) {
            if (!arg6.equals(arg2[-2078731805 ^ -2078731805])) {
                if (!arg3.isEmpty()) {
                    arg4.method_10852(class_2561.method_43470(arg3.toString()).method_10862(((class_2583) arg2[836580272 ^ 836580272])));
                    arg3.setLength(-499869259 ^ -499869259);
                }
                arg2[-1195063437 ^ -1195063437] = arg6;
            }
            arg3.appendCodePoint(arg7);
            if (Character.isWhitespace(arg7)) {
                arg0[-928112421 ^ -928112421] = arg0[-928112421 ^ -928112421] + (-985009661 ^ -985009662);
                int var8 = arg0[-928112421 ^ -928112421];
            }
            return 988714549 ^ 988714548;
        } else {
            arg1[458881735 ^ 458881735] = -583790587 ^ -583790588;
            return -502440697 ^ -502440697;
        }
    }

  private static int pE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int pG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}