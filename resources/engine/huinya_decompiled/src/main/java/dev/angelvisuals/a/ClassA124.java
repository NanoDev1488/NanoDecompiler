// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.J
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA122_ClassA123;
import dev.angelvisuals.a.ClassA25;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aT;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ar;
import dev.angelvisuals.a.as;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cE;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.ci;
import dev.angelvisuals.a.dD;
import dev.angelvisuals.a.dI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1665;
import net.minecraft.class_1685;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_239.class_240;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3857;
import net.minecraft.class_3959.class_3960;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_638;

@bI(name = "Predictions", a = "RENDER", I = "Показывает куда упадет предмет")
public final class ClassA124 extends cK {

    // ---- поля ----
  private final List field554; // было: x
  public static ClassA124 field555; // было: a
  private static final String vq = "// good luck with the next 9999 classes";
  private static final String vr = "// class hierarchy hashing: ENABLED";
  private static final String vs = "// good luck with the next 9999 classes";
  private static final String vt = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String vu = "// Joiner sees you";
  private static final int mF = 1741621477;
  private static final int mG = 335770618;
  private static final int mH = 1255375853;
  private static final byte[] dc;

    static {
        dc = "k+P{Aj^w>t$->RDjo2~)W+|29j= b&kM:!vm1#yMq|w{tdlHII'EXl(*&nF|4EPkT{L^ )yJHS\"~$c9#=+}9/4^FniQj{|#'j>~{~s<_H&OPC+_fFBgsJ3Sd2F=)e_dcMTk]@-rTJYJs@:~95^[d6)3)9M,a782,Q^7s1A_9'dq;xns(]9iz9yL$~S)W\",pct^\\JA%3~HuR) R|iwPmIkYO_i$&#983If//cf%6_(-(+ya-\\5[VI1f'|>IWbF\"ZS".getBytes("ISO-8859-1");
        field555 = new ClassA124();
    }

  private ClassA124() { // было: <init>
        super();
        field554 = new ArrayList();
    }

    @EventTarget
  public void method928(ClassA25 arg0) { // было: c
        Iterator var2 = field554.iterator();
        while (var2.hasNext()) {
            ClassA122_ClassA123 var3 = ((ClassA122_ClassA123) var2.next());
            class_243 var4 = dI.method1436(var3.field552);
            int var5 = var3.br;
            if (dI.method1437(var3.field552)) {
                ar var6 = bc.field171.method383(7.0f);
                double var7 = ((double) (var5 * (183968671 ^ 183968685))) / 1000.0;
                Object[] __obj1 = new Object[377011578 ^ 377011579];
                __obj1[1760796227 ^ 1760796227] = Double.valueOf(var7);
                Object[] var9 = __obj1;
                String var10 = String.format(Decryptor.method1945(XorDecoder.method1946("»\u000bîfÇ\u001bE°3ìMÍuí=§=<\u000f5", -738630491 ^ -614712751)), var9) + " сек";
                float var11 = var6.method349(var10);
                float var12 = 2.0f;
                float var13 = ((float) var4.method_10216());
                float var14 = ((float) var4.method_10214());
                float var15 = var6.method348();
                float var16 = var13 - var11 / 2.0f;
                float var17 = var14 - var15 / 2.0f;
                float var18 = var17 + 5.0f;
                aE.method1742(arg0.method319().method_51448(), var16 - 8.0f, var18 - 2.0f, var11 + 16.0f, var15 + 5.0f, aY.field897, new bp(923812271 ^ 923812271, -359308163 ^ -359308163, 1223756593 ^ 1223756593, -33715091 ^ -33715179));
                arg0.method319().method_51448().method_22903();
                arg0.method319().method_51448().method_46416(var16 - 6.0f, var18 - 1.0f, 0.0f);
                arg0.method319().method_51448().method_22905(0.5f, 0.5f, 1.0f);
                arg0.method319().method_51427(var3.method922(), -386676980 ^ -386676980, -1868224542 ^ -1868224542);
                arg0.method319().method_51448().method_22905(1.0f, 1.0f, 1.0f);
                arg0.method319().method_51448().method_46416(-(var16 - 7.0f), -(var18 - 1.0f), 0.0f);
                arg0.method319().method_51448().method_22909();
                arg0.method319().method1638(var6, var10.replace(Decryptor.method1945(XorDecoder.method1946("õ\u000e'Mõ\u0014<\u001f©`jLê\u007f;;Ìj? ítoG", -697439059 ^ -1405105867)), Decryptor.method1945(XorDecoder.method1946("²þîÌ± ÌÊ¡ñ´¡Ù¦Ò©¢½", -1716374631 ^ 422435696))), var16 + 5.0f, var18 + 0.5f, bp.field909);
            }
            continue;
        }
    }

    @EventTarget
  public void method929(dD arg0) { // было: i
        field554.clear();
        method930().forEach(lp0 -> method935(((class_1297) lp0)));
    }

  public List method930() { // было: r
        return cE.method1231().filter(lp0 -> method934(((class_1297) lp0))).toList();
    }

  public class_243 method931(class_1297 arg0, class_243 arg1, class_243 arg2) { // было: a
        float __stk1;
        float __stk2;
        boolean var4 = (((class_638) Objects.requireNonNull(mc.field_1687))).method_8320(class_2338.method_49638(arg1)).method_26227().method_15767(class_3486.field_15517);
        float var5;
        if (!(arg0 instanceof class_1685)) {
            if (!(arg0 instanceof class_1665)) {
                __stk2 = !var4 ? 0.9900000095367432f : 0.800000011920929f;
                var5 = __stk2;
            } else {
                __stk1 = !var4 ? 0.9900000095367432f : 0.6000000238418579f;
                var5 = __stk1;
            }
        } else {
            var5 = 0.9900000095367432f;
        }
        return arg2.method_1021(((double) var5)).method_1031(0.0, -arg0.method_56989(), 0.0);
    }

  private void method932(class_1297 arg0, class_243 arg1, int arg2) { // было: a
        if (!(arg0 instanceof class_1542)) {
            if (!(arg0 instanceof class_3857)) {
                if (arg0 instanceof class_1665) {
                    class_1665 var6 = ((class_1665) arg0);
                    field554.add(new ClassA122_ClassA123(var6.method_54759(), arg1, arg2));
                }
            } else {
                class_3857 var5 = ((class_3857) arg0);
                field554.add(new ClassA122_ClassA123(var5.method_7495(), arg1, arg2));
            }
        } else {
            class_1542 var4 = ((class_1542) arg0);
            field554.add(new ClassA122_ClassA123(var4.method_6983(), arg1, arg2));
        }
    }

  private boolean method933(class_1297 arg0) { // было: a
        int __stk1;
        int __stk2;
        __stk1 = arg0.method_23317() != arg0.field_6014 ? 624950541 ^ 624950541 : arg0.method_23318() != arg0.field_6036 ? 624950541 ^ 624950541 : arg0.method_23321() != arg0.field_5969 ? 624950541 ^ 624950541 : 342589232 ^ 342589233;
        int var2 = __stk1;
        __stk2 = !(arg0 instanceof class_1542) ? 858140517 ^ 858140517 : arg0.method_24828() ? 1148453136 ^ 1148453137 : !cE.method1235(arg0.method_5829().method_1014(2.0), class_2246.field_10382) ? 858140517 ^ 858140517 : 1148453136 ^ 1148453137;
        int var3 = __stk2;
        return var2 != 0 ? 330120617 ^ 330120616 : var3 == 0 ? 1739291588 ^ 1739291588 : 330120617 ^ 330120616;
    }

  private boolean method934(class_1297 arg0) { // было: b
        return arg0 instanceof class_1665 ? method933(arg0) ? 421983582 ^ 421983582 : 1310350960 ^ 1310350961 : arg0 instanceof class_3857 ? method933(arg0) ? 421983582 ^ 421983582 : 1310350960 ^ 1310350961 : !(arg0 instanceof class_1542) ? 421983582 ^ 421983582 : method933(arg0) ? 421983582 ^ 421983582 : 1310350960 ^ 1310350961;
    }

  private void method935(class_1297 arg0) { // было: a
        class_243 var2 = arg0.method_18798();
        class_243 var3 = arg0.method_19538();
        int var4 = 1511102910 ^ 1511102910;
        int var5 = 829299456 ^ 829299456;
        while (var5 < (-1367380217 ^ -1367380437)) {
            class_243 var6 = var3;
            var3 = var3.method_1019(var2);
            var2 = method931(arg0, var6, var2);
            class_3965 var7 = aT.method1283(var6, var3, class_3960.field_17558, arg0);
            if (!var7.method_17783().equals(class_240.field_1333)) {
                var3 = var7.method_17784();
            }
            as.method1818(var6, var3, AngelVisuals.getInstance().getThemeManager().method480(var5).method1687(class_3532.method_15363(((float) var5) / 25.0f, 0.0f, 1.0f)).method1680(), 2.0f, 180108314 ^ 180108314);
            int var8 = AngelVisuals.getInstance().getThemeManager().method480(var5).method1687(class_3532.method_15363(((float) var5) / 50.0f, 0.0f, 0.30000001192092896f)).method1680();
            as.method1818(var6, var3, var8, 4.5f, -1643626394 ^ -1643626394);
            as.method1818(var6, var3, var8, 7.0f, 186181380 ^ 186181380);
            class_243 var9 = var6;
            class_243 var10 = var3;
            boolean var11 = cE.method1231().filter(lp0 -> method937(((class_1297) lp0))).anyMatch(lp0 -> method936(var9, var10, ((class_1297) lp0)));
            if (var7.method_17783().equals(class_240.field_1332)) {
                method932(arg0, var3, var4);
                break;
            } else {
                if (var3.field_1351 < -128.0) {
                    method932(arg0, var3, var4);
                    break;
                } else {
                    if (var11) {
                        method932(arg0, var3, var4);
                        break;
                    } else {
                        if (!var7.method_17783().equals(class_240.field_1331)) {
                            ++var4;
                            ++var5;
                            continue;
                        } else {
                            method932(arg0, var3, var4);
                            break;
                        }
                    }
                }
            }
        }
    }

  private static boolean method936(class_243 arg0, class_243 arg1, class_1297 arg2) { // было: a
        return arg2.method_5829().method_1014(0.25).method_993(arg0, arg1);
    }

  private static boolean method937(class_1297 arg0) { // было: c
        if (!(arg0 instanceof class_1309)) {
            int var1 = -115987994 ^ -115987994;
            return var1;
        } else {
            class_1309 var2 = ((class_1309) arg0);
            if (var2 == mc.field_1724) {
                int var1 = -115987994 ^ -115987994;
                return var1;
            } else {
                if (!var2.method_5805()) {
                    int var1 = -115987994 ^ -115987994;
                    return var1;
                } else {
                    int var1 = -1181213495 ^ -1181213496;
                    return var1;
                }
            }
        }
    }

  private static int ld(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int le(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lf(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}