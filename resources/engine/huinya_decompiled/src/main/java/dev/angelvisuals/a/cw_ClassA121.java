// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cw.a
package dev.angelvisuals.a;

import net.minecraft.class_2338.class_2339;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_638;

class cw_ClassA121 {

    // ---- поля ----
   class_243 field530; // было: m
   class_243 field531; // было: n
   int mi;
   float cH;
   long field532; // было: C
   long field533; // было: D
   float cI;
   float cJ;
   long field534; // было: E
   double field535; // было: t
  private static final String uG = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String uH = "// you are reading machine-generated garbage";
  private static final String uI = "// this jar protected by JoinerObfuscator";
  private static final String uJ = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String uK = "Protected by t.me/JoinerClient";
  private static final int mj = -1624026209;
  private static final int mk = 1259977399;
  private static final int ml = -336444949;
  private static final byte[] cV;

    static {
        cV = "Y:J%sR%~~,R>[$)Fy9q\"C4xCd~g;R+Bz|{Y* H5'$B.!FRZb|+qbl*N?H+q>NQY\"jw8WWF0iWwV`Mcd&kgN`(3N\\/P0+#vP`Gk]!j<k=g#?4qtol`4@|6#h,FejU_Y*r3?8((3=jjI[CQ-mu{OW9GW;P9R6'36_.0pRB~GqETAb'+Y<9<{a%~%$Osn]%b-A*Je=VKoeqiY|B_/>L}jR6_T(O^'kV~NbOMIuB^mBoHu]7,<m+e6,MB.TD5p #%Mu6".getBytes("ISO-8859-1");
    }

   cw_ClassA121(class_243 arg0, class_243 arg1, int arg2, float arg3, long arg4, float arg5, double arg6) { // было: <init>
        super();
        cI = 1.0f;
        field530 = arg0;
        field531 = arg1;
        mi = arg2;
        cH = arg3;
        field532 = arg4;
        field533 = System.currentTimeMillis();
        field534 = System.nanoTime();
        cJ = arg5;
        field535 = arg6;
    }

   boolean aa() {
        return System.currentTimeMillis() - field533 < field532 ? -460554055 ^ -460554055 : 421176140 ^ 421176141;
    }

   void method909(class_310 arg0) { // было: a
        long var2 = System.nanoTime();
        double var4 = ((double) (var2 - field534)) / 1000000000.0;
        field534 = var2;
        float var6 = Math.min(1.0f, ((float) (System.currentTimeMillis() - field533)) / ((float) field532));
        double var7 = Math.pow(1.0 - ((double) var6), ((double) cJ));
        double var9 = field531.field_1352;
        double var11 = field531.field_1351;
        double var13 = field531.field_1350;
        double var15 = field530.field_1352;
        double var17 = field530.field_1351;
        double var19 = field530.field_1350;
        var15 = var15 + var9 * var7 * var4 * 60.0;
        if (!method910(var15, field530.field_1351, field530.field_1350, cH, arg0)) {
            var9 = -var9 * 0.8;
            var15 = field530.field_1352;
        }
        var17 = var17 + var11 * var7 * var4 * 60.0;
        if (!method910(var15, var17, field530.field_1350, cH, arg0)) {
            var11 = -var11 * 1.5;
            var17 = field530.field_1351;
        }
        var19 = var19 + var13 * var7 * var4 * 60.0;
        if (!method910(var15, var17, var19, cH, arg0)) {
            var13 = -var13 * 0.8;
            var19 = field530.field_1350;
        }
        field530 = new class_243(var15, var17, var19);
        field531 = new class_243(var9 * 0.9999, var11 * 0.9999 - field535, var13 * 0.9999);
        cI = 1.0f - var6;
    }

  static boolean method910(double arg0, double arg1, double arg2, float arg3, class_310 arg4) { // было: a
        if (arg4.field_1687 != null) {
            double var8 = ((double) arg3) * 0.5;
            int var10 = class_3532.method_15357(arg0 - var8);
            int var11 = class_3532.method_15357(arg0 + var8);
            int var12 = class_3532.method_15357(arg1 - var8);
            int var13 = class_3532.method_15357(arg1 + var8);
            int var14 = class_3532.method_15357(arg2 - var8);
            int var15 = class_3532.method_15357(arg2 + var8);
            class_2339 var16 = new class_2339();
            int var17 = var10;
        } else {
            return -1395462042 ^ -1395462042;
        }
        loop1: while (true) {
            if (var17 > var11) {
                return 1985549408 ^ 1985549409;
            } else {
                int var18 = var12;
            }
            loop2: while (true) {
                if (var18 > var13) {
                    ++var17;
                    continue loop1;
                } else {
                    int var19 = var14;
                    while (true) {
                        if (var19 > var15) {
                            ++var18;
                            continue loop2;
                        } else {
                            var16.method_10103(var17, var18, var19);
                            class_2680 var20 = arg4.field_1687.method_8320(var16);
                            if (var20.method_26215()) {
                                ++var19;
                                continue;
                            } else {
                                if (var20.method_26212(arg4.field_1687, var16)) {
                                    break;
                                }
                                ++var19;
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return -611722774 ^ -611722774;
    }

  private static int kI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int kK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}