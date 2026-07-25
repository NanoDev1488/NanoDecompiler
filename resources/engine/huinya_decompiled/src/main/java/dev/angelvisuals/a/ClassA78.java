// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.Q
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA76_ClassA77;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ay;
import dev.angelvisuals.a.ay_ClassA82;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.ci;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_1074;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_408;
import net.minecraft.class_5250;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import org.joml.Vector4f;

public class ClassA78 extends ay {

    // ---- поля ----
  private final ClassA2 field272; // было: C
  private final ClassA2 field273; // было: D
  private final ClassA2 field274; // было: E
  private final List field275; // было: A
  private final boolean ad;
  private static final String wq = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String wr = "// === DO NOT TOUCH ===";
  private static final String ws = "Protected by t.me/JoinerClient";
  private static final String wt = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String wu = "// Joiner sees you";
  private static final int ng = 1454458914;
  private static final int nh = 1849364943;
  private static final int ni = 730242965;
  private static final byte[] dl;

    static {
        dl = "ryo\"elG@c<~d#vkywY7}WDMZ<#nTlW$90_}58fYsPGfng1w9oi|N&u\"y{kiPj^m,P:VI`52\"&*s,7)i#pm{}D2S[TY^uZtH1+vy A:eEPEOmw2'_C!B|gtS]_6)>.;[O8]%50fybKczXa_zH:rj&9:^ZZyA%y\"s8>LwilsLd[cNV&={~\"i xyk-Y7ig7wk=]S}[@yF5Y `~7COf1PmUr3Gggr]JA1dUt<n>}Xx?zDL#i^Q%Lt9Z7Y'xtU@IB{h:J".getBytes("ISO-8859-1");
    }

  public ClassA78(String arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, ay_ClassA82 arg7, boolean arg8) { // было: <init>
        super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        field272 = new ClassA2(2395389160777445392L ^ 2395389160777445592L, aH.field21);
        field273 = new ClassA2(-3493519808775267144L ^ -3493519808775267310L, aH.field30);
        field274 = new ClassA2(6709185207637615602L ^ 6709185207637615418L, aH.field21);
        field275 = new CopyOnWriteArrayList();
        ad = arg8;
    }

  public void method533(ap arg0) { // было: a
        if (mc.field_1724 != null) {
            aB();
            float var2 = M();
            float var3 = N();
            bl var4 = AngelVisuals.getInstance().getThemeManager().method481();
            bp var5 = var4.method449();
            if (!ad) {
                method535(arg0, var2, var3, var5);
            } else {
                method534(arg0, var2, var3, var5);
            }
        }
    }

  private void method534(ap arg0, float arg1, float arg2, bp arg3) { // было: c
        int var5 = -974834591 ^ -974834591;
        float var6 = 0.0f;
        float var7 = 0.0f;
        ArrayList var8 = new ArrayList();
        Iterator var9 = field275.iterator();
        float var11;
        String var12;
        while (var9.hasNext()) {
            ClassA76_ClassA77 var10 = ((ClassA76_ClassA77) var9.next());
            var10.field270.method5(var10.field268);
            var11 = var10.field270.method13();
            if (var11 > 0.009999999776482582f) {
                var12 = class_1074.method_4662(var10.dy, new Object[-1750841090 ^ -1750841090]);
                if (var10.bP > 0) {
                    var12 = var12 + " " + var10.bP + (1782961902 ^ 1782961903);
                }
                String var13 = method537(var10.bQ);
                float var14 = bc.field171.method381(var12 + " " + var13, 7.199999809265137f);
                var6 = Math.max(var6, var14);
                var7 = var7 + 11.0f * var11;
                var8.add(var10);
                var5 = 1584539951 ^ 1584539950;
            }
            continue;
        }
        if (var5 != 0) {
            field274.method6(1.0f);
        } else {
            if (mc.field_1755 instanceof class_408) {
                field274.method6(1.0f);
            } else {
                field274.method6(0.0f);
            }
        }
        float var11;
        float var12;
        Iterator var23;
        if (field274.method13() >= 0.009999999776482582f) {
            var9 = 15.0f;
            float var10 = Math.max(var6 + 25.0f, 85.0f);
            field272.method6(var10);
            var11 = field272.method13();
            var12 = var9 + var7 + 4.0f;
            Vector4f var13 = new Vector4f(6.0f, 6.0f, 6.0f, 6.0f);
            aE.method1759(arg0.method_51448(), arg1, arg2, var11, var12, 18.0f, aY.method1597(var13.x), bp.field909.method1686(((int) (255.0f * field274.method13()))));
            aE.method1742(arg0.method_51448(), arg1, arg2, var11, var9, new aY(var13.x, var13.y, 0.0f, 0.0f), bp.field910.method1686(((int) (255.0f * field274.method13()))));
            float var14 = arg2 + var9;
            float var15 = var12 - var9;
            aE.method1742(arg0.method_51448(), arg1, var14, var11, var15, new aY(0.0f, 0.0f, var13.z, var13.w), new bp(-152961056 ^ -152961056, -1391789004 ^ -1391789004, 1871279152 ^ 1871279152, ((int) (135.0f * field274.method13()))));
            float var16 = bc.field171.method381(Decryptor.method1945(XorDecoder.method1946("qÂ8ÒQ¼ywþsSÝ#â\u000f÷\"ÔOúv", 731149556 ^ -1864369715)), 8.0f);
            float var17 = 8.0f;
            float var18 = bc.field174.method381(Decryptor.method1945(XorDecoder.method1946("T\u0008ÈÃ-D÷²\u0016\u001fï¹?\u001dä³\u0006M×ò+-¶", -50005828 ^ 1991869648)), var17);
            float var19 = 3.5f;
            float var20 = var18 + var19 + var16;
            float var21 = arg1 + (var11 - var20) / 2.0f;
            arg0.method1638(bc.field174.method383(var17), Decryptor.method1945(XorDecoder.method1946("\u000e:«K\nÈà\u000f%·ç\u0018\u0019´ÕM;±ß\u001a\u0001À®", -1620244717 ^ 210786158)), var21 + 0.5f, arg2 + 4.699999809265137f, arg3.method1686(((int) (255.0f * field274.method13()))));
            arg0.method1638(bc.field171.method383(8.0f), Decryptor.method1945(XorDecoder.method1946("ãxÏÃ\u0006ÄåDÛÁgÔ¶MÕÝ@Ò", 1137233353 ^ -1401619870)), var21 + var18 + var19, arg2 + 4.900000095367432f, bp.field909.method1686(((int) (255.0f * field274.method13()))));
            float var22 = var14 + 1.0f;
            var23 = var8.iterator();
        } else {
            return;
        }
        while (var23.hasNext()) {
            ClassA76_ClassA77 var24 = ((ClassA76_ClassA77) var23.next());
            float var25 = var24.field270.method13();
            String var26 = class_1074.method_4662(var24.dy, new Object[1252718836 ^ 1252718836]);
            if (var24.bP > 0) {
                var26 = var26 + " " + var24.bP + (433402744 ^ 433402745);
            }
            String var27 = method537(var24.bQ);
            class_2960 var28 = method538(((class_1291) var24.field269.method_5579().comp_349()));
            int var29 = ((int) (255.0f * field274.method13() * var25));
            arg0.method1650(var28, arg1 + 4.0f, var22 + 1.2000000476837158f, 8.0f, 8.0f, bp.field909.method1686(var29));
            arg0.method1638(bc.field171.method383(7.199999809265137f), var26, arg1 + 14.0f, var22 + 3.0f, bp.field909.method1686(var29));
            float var30 = bc.field171.method381(var27, 7.199999809265137f);
            arg0.method1638(bc.field171.method383(7.199999809265137f), var27, arg1 + var11 - var30 - 6.0f, var22 + 3.0f, arg3.method1686(var29));
            float var22 = var22 + 11.0f * var25;
            continue;
        }
        aM = var11;
        aN = var12;
    }

  private void method535(ap arg0, float arg1, float arg2, bp arg3) { // было: d
        int var5 = -1880953197 ^ -1880953197;
        float var6 = 0.0f;
        float var7 = 0.0f;
        float var8 = 0.0f;
        Iterator var9 = field275.iterator();
        ClassA76_ClassA77 var10;
        float var11;
        String var12;
        String var13;
        float var14;
        while (var9.hasNext()) {
            var10 = ((ClassA76_ClassA77) var9.next());
            var10.field270.method5(var10.field268);
            var11 = var10.field270.method13();
            if (var11 > 0.009999999776482582f) {
                var12 = class_1074.method_4662(var10.dy, new Object[-479602156 ^ -479602156]);
                if (var10.bP > 0) {
                    var12 = var12 + " " + var10.bP + (951998756 ^ 951998757);
                }
                var13 = method537(var10.bQ);
                var14 = bc.field171.method381(var12, 7.199999809265137f);
                float var15 = bc.field171.method381(var13, 7.199999809265137f);
                var6 = Math.max(var6, var14 * var11 + 10.0f);
                var7 = Math.max(var7, var15 * var11);
                var8 = var8 + 11.0f * var11;
                var5 = 1444763246 ^ 1444763247;
            }
            continue;
        }
        if (var5 != 0) {
            field274.method6(1.0f);
        } else {
            if (mc.field_1755 instanceof class_408) {
                field274.method6(1.0f);
            } else {
                field274.method6(0.0f);
            }
        }
        if (mc.field_1755 instanceof class_408) {
            field274.method6(1.0f);
        }
        var9 = 15.0f;
        float var10 = 4.0f;
        float var11 = var8 + var10;
        float var12 = var9 + var11;
        float var13 = Math.max(var6 + var7 + 25.0f, 80.0f);
        field272.method6(var13);
        float var14 = field272.method13();
        if (field274.method13() > 0.009999999776482582f) {
            float var15 = 4.0f;
            bp var16 = new bp(-858188228 ^ -858188228, 1691347042 ^ 1691347042, -252561490 ^ -252561490, ((int) (255.0f * field274.method13())));
            bp var17 = new bp(-1086396957 ^ -1086396957, 1685673588 ^ 1685673588, 293229671 ^ 293229671, ((int) (125.0f * field274.method13())));
            aE.method1759(arg0.method_51448(), arg1, arg2, var14, var12, 15.0f, aY.method1597(var15), bp.field909.method1686(((int) (255.0f * field274.method13()))));
            aE.method1742(arg0.method_51448(), arg1, arg2, var14, var12, aY.method1597(var15), var17);
            aE.method1742(arg0.method_51448(), arg1, arg2, var14, var9, new aY(var15, var15, 0.0f, 0.0f), var16);
            arg0.method1638(bc.field171.method383(8.0f), Decryptor.method1945(XorDecoder.method1946("<ÕC\u001c«\u0002À:é\u0008ß\u001eÊX²BàY\u0002í\rÖ", 1632554367 ^ -1971431669)), arg1 + 7.0f, arg2 + 4.5f, bp.field909.method1686(((int) (255.0f * field274.method13()))));
            arg0.method1638(bc.field177.method383(9.5f), Decryptor.method1945(XorDecoder.method1946("÷v¤R÷\u001aX<\u0018ýh\u000cèj÷3Í\u0008ûW", -437459565 ^ -1893024714)), arg1 - 1.0f + var14 - 14.0f, arg2 + 5.5f, arg3.method1686(((int) (255.0f * field274.method13()))));
            float var18 = arg2 + var9 + 2.0f;
            float var19 = arg1 + var14 - 8.0f;
            Iterator var20 = field275.iterator();
            while (var20.hasNext()) {
                ClassA76_ClassA77 var21 = ((ClassA76_ClassA77) var20.next());
                float var22 = var21.field270.method13();
                if (var22 > 0.009999999776482582f) {
                    String var23 = class_1074.method_4662(var21.dy, new Object[986760639 ^ 986760639]);
                    if (var21.bP > 0) {
                        var23 = var23 + " " + var21.bP + (375521262 ^ 375521263);
                    }
                    String var24 = method537(var21.bQ);
                    class_2960 var25 = method538(((class_1291) var21.field269.method_5579().comp_349()));
                    bp var26 = arg3.method1686(((int) (255.0f * field274.method13() * var22)));
                    bp var27 = bp.field909.method1686(((int) (255.0f * field274.method13() * var22)));
                    arg0.method1650(var25, arg1 + 6.0f, var18 + 1.0f, 8.0f, 8.0f, bp.field909.method1686(((int) (255.0f * field274.method13() * var22))));
                    arg0.method1638(bc.field171.method383(7.199999809265137f), var23, arg1 + 16.5f, var18 + 2.5f, var27);
                    arg0.method1638(bc.field171.method383(7.199999809265137f), var24, var19 - bc.field171.method381(var24, 7.199999809265137f) + 1.0f, var18 + 2.5f, var26);
                    var18 = var18 + 11.0f * var22;
                }
                continue;
            }
        }
        aM = var14;
        aN = var12;
    }

  private String method536(int arg0) { // было: b
        return String.valueOf(arg0 + (-705455223 ^ -705455224));
    }

  private String method537(int arg0) { // было: c
        int var2 = arg0 / (-1065680934 ^ -1065680946);
        int var3 = var2 / (950374497 ^ 950374493);
        int var4 = var2 % (1977626445 ^ 1977626481);
        Object[] __obj1 = new Object[769971759 ^ 769971757];
        __obj1[1144270799 ^ 1144270799] = Integer.valueOf(var3);
        __obj1[-1781545670 ^ -1781545669] = Integer.valueOf(var4);
        return String.format(Decryptor.method1945(XorDecoder.method1946("Â<¡xä+±\"ÃvqÎ,¸0ó/7à\u0018Ô}", 316110753 ^ 1379806762)), __obj1);
    }

  private class_2960 method538(class_1291 arg0) { // было: a
        String var2 = arg0.method_5567().replace(Decryptor.method1945(XorDecoder.method1946("!\u0006´Yf½#\u0002°\u00136°°66£«#\"û¶\u0002g¦´\u0006\u0001Ñ\u001b��úY\u0010ð'3Ü", -702202947 ^ 938240463)), Decryptor.method1945(XorDecoder.method1946("ç\u001eã±\u0005ì±:²£,®ä?«²Tÿ", 801137643 ^ -307645377))).replace(Decryptor.method1945(XorDecoder.method1946("ì��ÑÙ\u000eÖÙÆ;ÉÌ\u0010¾ÖÕ-ÔÄ;Ó", 1172597783 ^ -469100902)), Decryptor.method1945(XorDecoder.method1946("ºà}¾«ûd±«ÄAÝ¹Ò]¹ÁXïª\u000c", 1815983718 ^ 1571453935)));
        return class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("h­Ü£mùÿoÔáX¨ï¤H¾ÃÄ^ª§ª", -1634849911 ^ 152368788)), "textures/mob_effect/" + var2 + ".png");
    }

  public void aB() {
        if (mc.field_1724 != null) {
            Map var1 = ((Map) mc.field_1724.method_6026().stream().collect(Collectors.toMap(lp0 -> method544(((class_1293) lp0)), lp0 -> method543(((class_1293) lp0)), (lp0, lp1) -> method542(((class_1293) lp0), ((class_1293) lp1)))));
            field275.forEach(lp0 -> method541(var1, ((ClassA76_ClassA77) lp0)));
            var1.forEach((lp0, lp1) -> method540(((String) lp0), ((class_1293) lp1)));
            field275.removeIf(lp0 -> method539(((ClassA76_ClassA77) lp0)));
        }
    }

  private static boolean method539(ClassA76_ClassA77 arg0) { // было: a
        return arg0.field268 ? -688335798 ^ -688335798 : arg0.field270.method13() != 0.0f ? -688335798 ^ -688335798 : 1264377843 ^ 1264377842;
    }

  private void method540(String arg0, class_1293 arg1) { // было: a
        field275.add(new ClassA76_ClassA77(class_2561.method_43471(arg1.method_5586()).getString(), arg1.method_5578(), arg1.method_5584(), arg1));
    }

  private static void method541(Map arg0, ClassA76_ClassA77 arg1) { // было: a
        String var2 = arg1.dy + ":" + arg1.bP;
        class_1293 var3 = ((class_1293) arg0.get(var2));
        if (var3 == null) {
            arg1.field268 = -1258768522 ^ -1258768522;
        } else {
            arg1.bQ = var3.method_5584();
            if (!arg1.field268) {
                arg1.field270.method7(1.0f);
            }
            arg1.field268 = 119198764 ^ 119198765;
            arg0.remove(var2);
        }
    }

  private static class_1293 method542(class_1293 arg0, class_1293 arg1) { // было: a
        return arg0;
    }

  private static class_1293 method543(class_1293 arg0) { // было: a
        return arg0;
    }

  private static String method544(class_1293 arg0) { // было: a
        String var1 = class_2561.method_43471(arg0.method_5586()).getString();
        return var1 + ":" + arg0.method_5578();
    }

  private static int lE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lF(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lG(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}