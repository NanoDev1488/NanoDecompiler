// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cW
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA144;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.aT;
import dev.angelvisuals.a.cH;
import dev.angelvisuals.a.cs;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_239.class_240;
import net.minecraft.class_243;
import net.minecraft.class_3959;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import net.minecraft.class_3965;
import net.minecraft.class_638;
import net.minecraft.class_746;

public class cW implements ClassA146 {

    // ---- поля ----
  private static class_243 field794; // было: p
  private static class_243 field795; // было: q
  private static final String za = "// this jar protected by JoinerObfuscator";
  private static final String zb = "// good luck with the next 9999 classes";
  private static final String zc = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String zd = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String ze = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final int oI = -1863530093;
  private static final int oJ = -395270060;
  private static final int oK = 1743005223;
  private static final byte[] dJ;

    static {
        dJ = "EOL:}-'(A9g&=|G3]_D:GyGKN<fNS0'F:afe-dtb~Rc0j{ d-ma8wCeyu'HFB:,o=$a6Ug]]'|9#zX,VHMU8>R!f'xm;j3%Dnt-B1#c$Z<mz8_N4}oIlT+LnaTHP@7t[UMvpk|Kp}_2\\83@O>^Y]!y)FyePt/-h%n\\fe^o;-QDiPq,)bCnaNwd<@7E~\\-FKgfOdB!&n/wJ}=i5yL,uCvpy[cJ]>^P[g!HTROxj<NGKLlQeZQ:p\\egKaW:/hrE?p7".getBytes("ISO-8859-1");
        field794 = class_243.field_1353;
        field795 = class_243.field_1353;
    }

  public cW() { // было: <init>
        super();
    }

  public static class_243 method1434(class_1297 arg0, double arg1) { // было: a
        int __stk1;
        class_243 var3 = mc.field_1724.method_5836(1.0f);
        double var4 = arg1 * arg1;
        class_238 var6 = arg0.method_5829();
        class_243 var7 = var6.method_1005();
        double var8 = 0.1;
        double var10 = 0.1;
        Object var12 = null;
        double var13 = Double.POSITIVE_INFINITY;
        double var15 = var6.field_1323;
        while (var15 <= var6.field_1320) {
            double var17 = var6.field_1322;
            while (var17 <= var6.field_1325) {
                double var19 = var6.field_1321;
                while (var19 <= var6.field_1324) {
                    class_243 var21 = new class_243(var15, var17, var19);
                    if (var3.method_1025(var21) <= var4) {
                        class_3959 var22 = new class_3959(var3, var21, class_3960.field_17558, class_242.field_1348, mc.field_1724);
                        class_3965 var23 = mc.field_1687.method_17742(var22);
                        int var24;
                        if (var23.method_17783() != class_240.field_1333) {
                            __stk1 = var23.method_17783() == class_240.field_1332 ? -1985486484 ^ -1985486484 : -2136996282 ^ -2136996281;
                            var24 = __stk1;
                        } else {
                            var24 = -1102025932 ^ -1102025931;
                        }
                        if (var24 != 0) {
                            double var25 = var7.method_1025(var21);
                            if (var25 < var13) {
                                var13 = var25;
                                var12 = var21;
                            }
                        }
                    }
                    var19 = var19 + var8;
                    continue;
                }
                var17 = var17 + var10;
                continue;
            }
            var15 = var15 + var8;
            continue;
        }
        return var12 != null ? var12 : arg0.method_5829().method_1005();
    }

  public static class_243 method1435(class_1297 arg0, double arg1) { // было: b
        float var3 = 0.009999999776482582f;
        float var4 = 0.029999999329447746f;
        float var5 = 0.009999999776482582f;
        float var6 = 0.029999999329447746f;
        double var7 = arg0.method_5829().method_17939();
        double var9 = arg0.method_5829().method_17940();
        double var11 = arg0.method_5829().method_17941();
        if (field795.equals(class_243.field_1353)) {
            field795 = new class_243(((double) cs.method1413(-0.05000000074505806, 0.05000000074505806)), ((double) cs.method1413(-0.05000000074505806, 0.05000000074505806)), ((double) cs.method1413(-0.05000000074505806, 0.05000000074505806)));
        }
        field794 = field794.method_1019(field795);
        if (field794.field_1352 >= (var7 - 0.05) / 2.0) {
            field795 = new class_243(((double) -cs.method1413(((double) var3), ((double) var4))), field795.method_10214(), field795.method_10215());
        }
        if (field794.field_1351 >= var9 / 2.0) {
            field795 = new class_243(field795.method_10216(), ((double) -cs.method1413(((double) var5), ((double) var6))), field795.method_10215());
        }
        if (field794.field_1350 >= (var11 - 0.05) / 2.0) {
            field795 = new class_243(field795.method_10216(), field795.method_10214(), ((double) -cs.method1413(((double) var3), ((double) var4))));
        }
        if (field794.field_1352 <= -(var7 - 0.05) / 2.0) {
            field795 = new class_243(((double) cs.method1413(((double) var3), 0.029999999329447746)), field795.method_10214(), field795.method_10215());
        }
        if (field794.field_1351 <= 0.1) {
            field795 = new class_243(field795.method_10216(), ((double) cs.method1413(((double) var5), ((double) var6))), field795.method_10215());
        }
        if (field794.field_1350 <= -(var11 - 0.05) / 2.0) {
            field795 = new class_243(field795.method_10216(), field795.method_10214(), ((double) cs.method1413(((double) var3), ((double) var4))));
        }
        field794.method_1031(((double) cs.method1413(-0.029999999329447746, 0.029999999329447746)), 0.0, ((double) cs.method1413(-0.029999999329447746, 0.029999999329447746)));
        if (!aT.method1285(mc.field_1724.method_5720(), arg1, arg0.method_5829())) {
            float var13 = ((float) (var7 / 2.0));
            float var14 = -var13;
            while (var14 <= var13) {
                float var15 = -var13;
                while (var15 <= var13) {
                    float var16 = 0.05000000074505806f;
                    while (((double) var16) <= arg0.method_5829().method_17940()) {
                        class_243 var17 = new class_243(arg0.method_23317() + ((double) var14), arg0.method_23318() + ((double) var16), arg0.method_23321() + ((double) var15));
                        cH var18 = ClassA144.method1363(var17);
                        if (!aT.method1285(var18.method1355(), arg1, arg0.method_5829())) {
                            var16 = var16 + 0.15000000596046448f;
                            continue;
                        } else {
                            field794 = new class_243(((double) var14), ((double) var16), ((double) var15));
                            break;
                        }
                    }
                    var15 = var15 + 0.05000000074505806f;
                    continue;
                }
                var14 = var14 + 0.05000000074505806f;
                continue;
            }
        }
        return arg0.method_19538().method_1019(field794);
    }

  private static int mY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int na(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}