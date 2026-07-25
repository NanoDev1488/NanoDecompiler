// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aA
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA144;
import dev.angelvisuals.a.aT;
import dev.angelvisuals.a.ba;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cH;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;
import lombok.Generated;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_239.class_240;
import net.minecraft.class_243;
import net.minecraft.class_3545;
import net.minecraft.class_3959.class_3960;
import net.minecraft.class_3965;
import net.minecraft.class_746;

public class aA implements cF {

    // ---- поля ----
  private static final double field731 = 0.15; // было: n
  private static final int ew = 14;
  private static final int ex = 10;
  private final Random field732; // было: b
  private class_243 field733; // было: g
  private static final String hK = "Protected by t.me/JoinerClient";
  private static final String hL = "// Joiner sees you";
  private static final String hM = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String hN = "// number obfuscation: ENABLED (XOR masking)";
  private static final String hO = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int ey = -97684239;
  private static final int ez = 1365042784;
  private static final int eA = -1136148543;
  private static final byte[] aH;

    static {
        aH = ":_3f#!:~c@OFE6AE^-cfAc+Anc0L!]%/G<C|6M>ft54h+8L;a|21D=w.#GeI.P|'7}GT~9?)xI*AHyp^(s'1pxOU#7=VqKVW'F%>!.Ok70/lL7s2`CS6Q\"iv.5BCJ?3|,lAHa*}$$6n~~ZqzPnBnhDkYq43-{h\\|I0%5M4&d, JX1v___j/oI(]zOKQfQ8O$dU=3']yus_!a Ig;z4n+^`a;Qv.)YOxxDyj$Qf2I0%1?P%MxOVzc_w/SyKDN?hK#".getBytes("ISO-8859-1");
    }

  public aA() { // было: <init>
        super();
        field732 = new SecureRandom();
        field733 = class_243.field_1353;
    }

  public class_3545 method1264(class_1309 arg0, float arg1, cH arg2, class_243 arg3, boolean arg4) { // было: a
        class_243 __stk1;
        class_3545 var6 = method1265(arg0, arg1, arg4);
        List var7 = method1269(((List) var6.method_15442()), arg1);
        class_243 var8 = method1268(var7, arg1, arg4);
        if (var8 == null) {
            var8 = method1268(((List) var6.method_15442()), arg1, arg4);
        }
        if (var8 == null) {
            var8 = method1271(((List) var6.method_15442()));
        }
        method1272(arg3);
        __stk1 = var8 != null ? var8 : arg0.method_33571();
        class_243 var9 = __stk1.method_1019(field733);
        return new class_3545(var9, ((class_238) var6.method_15441()));
    }

  public class_3545 method1265(class_1309 arg0, float arg1, boolean arg2) { // было: a
        double __stk1;
        double __stk2;
        class_238 var4 = arg0.method_5829();
        class_243 var5 = mc.field_1724.method_33571();
        double var6 = var4.method_17939();
        double var8 = var4.method_17940();
        double var10 = var4.method_17941();
        int var12 = method1274(var6);
        int var13 = method1274(var10);
        int var14 = Math.max(759349961 ^ 759349963, -107015079 ^ -107015085);
        double var15 = var8 / ((double) (var14 - (-1196067287 ^ -1196067288)));
        double var17 = var4.field_1323;
        double var19 = var4.field_1322;
        double var21 = var4.field_1321;
        __stk1 = var12 > (-1462248233 ^ -1462248234) ? var6 / ((double) (var12 - (1901111566 ^ 1901111567))) : 0.0;
        double var23 = __stk1;
        __stk2 = var13 > (-811997005 ^ -811997006) ? var10 / ((double) (var13 - (-368619635 ^ -368619636))) : 0.0;
        double var25 = __stk2;
        ArrayList var27 = new ArrayList(var14 * var12 * var13);
        int var28 = -1085710316 ^ -1085710316;
        while (var28 < var14) {
            double var29 = var19 + ((double) var28) * var15;
            int var31 = -1991479293 ^ -1991479293;
            while (var31 < var12) {
                double var32 = var17 + ((double) var31) * var23;
                int var34 = 72361937 ^ 72361937;
                while (var34 < var13) {
                    double var35 = var21 + ((double) var34) * var25;
                    class_243 var37 = new class_243(var32, var29, var35);
                    if (method1267(var5, var37, arg1, arg2)) {
                        var27.add(var37);
                    }
                    ++var34;
                    continue;
                }
                ++var31;
                continue;
            }
            ++var28;
            continue;
        }
        return new class_3545(var27, var4);
    }

  public boolean method1266(class_1309 arg0, float arg1, boolean arg2) { // было: a
        double __stk1;
        double __stk2;
        class_238 var4 = arg0.method_5829();
        class_243 var5 = mc.field_1724.method_33571();
        double var6 = var4.method_17939();
        double var8 = var4.method_17940();
        double var10 = var4.method_17941();
        int var12 = method1274(var6);
        int var13 = method1274(var10);
        int var14 = Math.max(1979679536 ^ 1979679538, -1909416369 ^ -1909416379);
        double var15 = var8 / ((double) (var14 - (-644092025 ^ -644092026)));
        double var17 = var4.field_1323;
        double var19 = var4.field_1322;
        double var21 = var4.field_1321;
        __stk1 = var12 > (-597573716 ^ -597573715) ? var6 / ((double) (var12 - (-329260377 ^ -329260378))) : 0.0;
        double var23 = __stk1;
        __stk2 = var13 > (-1296982326 ^ -1296982325) ? var10 / ((double) (var13 - (-352908704 ^ -352908703))) : 0.0;
        double var25 = __stk2;
        int var27 = -1058332723 ^ -1058332723;
        loop1: while (true) {
            if (var27 >= var14) {
                return -1950990186 ^ -1950990186;
            } else {
                double var28 = var19 + ((double) var27) * var15;
                int var30 = -229263772 ^ -229263772;
            }
            loop2: while (true) {
                if (var30 >= var12) {
                    ++var27;
                    continue loop1;
                } else {
                    double var31 = var17 + ((double) var30) * var23;
                    int var33 = -1299650858 ^ -1299650858;
                    while (true) {
                        if (var33 >= var13) {
                            ++var30;
                            continue loop2;
                        } else {
                            double var34 = var21 + ((double) var33) * var25;
                            class_243 var36 = new class_243(var31, var28, var34);
                            if (method1267(var5, var36, arg1, arg2)) {
                                break;
                            }
                            ++var33;
                            continue;
                        }
                    }
                }
            }
        }
        return -924397979 ^ -924397980;
    }

  private boolean method1267(class_243 arg0, class_243 arg1, float arg2, boolean arg3) { // было: a
        if (arg0.method_1025(arg1) <= ((double) arg2) * ((double) arg2)) {
            if (!arg3) {
                class_3965 var5 = aT.method1282(arg0, arg1, class_3960.field_17558);
                return var5.method_17783() == class_240.field_1332 ? 379866078 ^ 379866078 : -1219235121 ^ -1219235122;
            } else {
                return -168770860 ^ -168770859;
            }
        } else {
            return 1499419803 ^ 1499419803;
        }
    }

  private class_243 method1268(List arg0, float arg1, boolean arg2) { // было: a
        if (arg0 == null) {
            return null;
        } else {
            if (arg0.isEmpty()) {
                return null;
            } else {
                class_243 var4 = mc.field_1724.method_33571();
                class_243 var5 = method1270(arg0);
                return !method1267(var4, var5, arg1, arg2) ? ((class_243) arg0.stream().filter(lp0 -> method1279(var4, arg1, arg2, ((class_243) lp0))).min(Comparator.comparingDouble(lp0 -> method1278(var5, ((class_243) lp0)))).orElse(null)) : var5;
            }
        }
    }

  private List method1269(List arg0, float arg1) { // было: a
        if (arg0 == null) {
            return List.of();
        }
        ArrayList var8;
        Iterator var9;
        if (arg0.isEmpty()) {
            return List.of();
        } else {
            class_243 var3 = mc.field_1724.method_33571();
            double var4 = Math.max(0.0, ((double) arg1) - 0.3);
            double var6 = var4 * var4;
            var8 = new ArrayList();
            var9 = arg0.iterator();
        }
        while (var9.hasNext()) {
            class_243 var10 = ((class_243) var9.next());
            if (var3.method_1025(var10) < var6) {
                var8.add(var10);
            }
            continue;
        }
        return var8;
    }

  private class_243 method1270(List arg0) { // было: a
        double var2 = 0.0;
        double var4 = 0.0;
        double var6 = 0.0;
        int var8 = arg0.size();
        Iterator var10 = arg0.iterator();
        while (var10.hasNext()) {
            class_243 var9 = ((class_243) var10.next());
            var2 = var2 + var9.field_1352;
            var4 = var4 + var9.field_1351;
            var6 = var6 + var9.field_1350;
            continue;
        }
        return new class_243(var2 / ((double) var8), var4 / ((double) var8), var6 / ((double) var8));
    }

  private class_243 method1271(List arg0) { // было: b
        if (arg0 == null) {
            return null;
        } else {
            if (arg0.isEmpty()) {
                return null;
            } else {
                class_243 var2 = mc.field_1724.method_33571();
                return ((class_243) arg0.stream().min(Comparator.comparingDouble(lp0 -> method1277(var2, ((class_243) lp0)))).orElse(null));
            }
        }
    }

  private void method1272(class_243 arg0) { // было: d
        field733 = field733.method_1031(field732.nextGaussian(), field732.nextGaussian(), field732.nextGaussian()).method_18806(arg0);
    }

  private double method1273(class_243 arg0, class_243 arg1, cH arg2) { // было: a
        if (arg2 != null) {
            cH var4 = ClassA144.method1363(arg1.method_1020(arg0));
            ba var5 = arg2.method1350(var4);
            return Math.hypot(((double) var5.ad()), ((double) var5.ae()));
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

  private int method1274(double arg0) { // было: a
        if (arg0 > 0.0) {
            int var3 = ((int) Math.ceil(arg0 / 0.15)) + (34419496 ^ 34419497);
            int var4 = Math.min(var3, 1460951295 ^ 1460951281);
            return Math.max(-1129268548 ^ -1129268546, var4);
        } else {
            return -584196046 ^ -584196045;
        }
    }

    @Generated
  public Random method1275() { // было: a
        return field732;
    }

    @Generated
  public class_243 method1276() { // было: d
        return field733;
    }

  private static double method1277(class_243 arg0, class_243 arg1) { // было: a
        return arg1.method_1025(arg0);
    }

  private static double method1278(class_243 arg0, class_243 arg1) { // было: b
        return arg1.method_1025(arg0);
    }

  private boolean method1279(class_243 arg0, float arg1, boolean arg2, class_243 arg3) { // было: a
        return method1267(arg0, arg3, arg1, arg2);
    }

  private static int dV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}