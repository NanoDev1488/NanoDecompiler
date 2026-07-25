// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.g
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.aT;
import java.util.Iterator;
import java.util.List;
import lombok.Generated;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_239.class_240;
import net.minecraft.class_243;
import net.minecraft.class_3959.class_3960;
import net.minecraft.class_3965;
import net.minecraft.class_5134;
import ru.nexusguard.protection.annotations.Native;

public final class ClassA133 implements ClassA146 {

    // ---- поля ----
  private static final float field689 = 6.0f; // было: c
  private static final String field690 = "// this jar protected by JoinerObfuscator"; // было: E
  private static final String field691 = "// signed: JoinerClient @ t.me/JoinerClient"; // было: F
  private static final String field692 = "// string encryption: ENABLED (AES-128/ECB + XOR)"; // было: G
  private static final String field693 = "// you are reading machine-generated garbage"; // было: H
  private static final String field694 = "// number obfuscation: ENABLED (XOR masking)"; // было: I
  private static final int field695 = -1541922556; // было: t
  private static final int field696 = -1923066866; // было: u
  private static final int field697 = 478208005; // было: A
  private static final byte[] field698; // было: g

    static {
        field698 = "EMHkH9%@FIu&N@WmGMZ!l;VPcS&^mCB9sAT&YZ 1l0Ai1Qhw_+#m 62O}s~9>@H|b<L!v!fjqTL&1HXn7?u;it*A\\En!WuiJ h0mcz&V`,}F;J.(oU)<g=js~4~)`8UZnV<![_8B,d0=X2'Vf5Ko\"/i)fo(oGbcuwB5(f7a**%cQ#=z(VU>WjXqt.\\rBD9lNBzFNRT o9%|Eo%b'<5DQJtXn*D)CC%\\VMl#)$vZn?K?Vhn+AH`Sop0k`P|mtY>y)".getBytes("ISO-8859-1");
    }

    @Native
  public static float method1134(class_1511 arg0, class_1657 arg1, boolean arg2) { // было: a
        if (arg0 == null) {
            return 0.0f;
        } else {
            if (arg0.method_31481()) {
                return 0.0f;
            } else {
                if (arg1 == null) {
                    return 0.0f;
                } else {
                    if (mc.field_1687 == null) {
                        return 0.0f;
                    } else {
                        class_243 var3 = arg0.method_19538();
                        class_243 var4 = arg1.method_33571();
                        if (!arg2) {
                            class_243 var5 = arg1.method_19538();
                            float var6 = method1137(var3, var5, 6.0f);
                            float var7 = method1136(arg1);
                            float var8 = var6 * (1.0f - var7);
                            return Math.max(0.0f, var8);
                        } else {
                            if (!method1138(var4, var3, mc.field_1687)) {
                                class_243 var5 = arg1.method_19538();
                                float var6 = method1137(var3, var5, 6.0f);
                                float var7 = method1136(arg1);
                                float var8 = var6 * (1.0f - var7);
                                return Math.max(0.0f, var8);
                            } else {
                                return 0.0f;
                            }
                        }
                    }
                }
            }
        }
    }

    @Native
  public static float method1135(List arg0, class_1657 arg1, boolean arg2) { // было: a
        if (arg0 == null) {
            return 0.0f;
        }
        if (arg0.isEmpty()) {
            return 0.0f;
        }
        float var3;
        Iterator var4;
        if (arg1 != null) {
            var3 = 0.0f;
            var4 = arg0.iterator();
        } else {
            return 0.0f;
        }
        float var3;
        while (var4.hasNext()) {
            class_1511 var5 = ((class_1511) var4.next());
            float var6 = method1134(var5, arg1, arg2);
            if (var6 > var3) {
                var3 = var6;
            }
            continue;
        }
        return var3;
    }

    @Native
  private static float method1136(class_1657 arg0) { // было: a
        if (arg0 != null) {
            float var1 = ((float) arg0.method_45325(class_5134.field_23724));
            float var2 = Math.min(0.5f, var1 * 0.019999999552965164f);
            float var3 = 0.0f;
            class_1293 var4 = arg0.method_6112(class_1294.field_5907);
            int var5;
            if (var4 != null) {
                var5 = var4.method_5578();
                var3 = ((float) (var5 + (-894690935 ^ -894690936))) * 0.20000000298023224f;
            }
            float var5 = var2 + var3;
            return Math.min(0.800000011920929f, var5);
        } else {
            return 0.0f;
        }
    }

    @Native
  private static float method1137(class_243 arg0, class_243 arg1, float arg2) { // было: a
        double var3 = arg0.method_1022(arg1);
        double var5 = 1.0 - var3 / (((double) arg2) * 2.0);
        if (var5 > 0.0) {
            double var7 = (var5 * var5 + var5) / 2.0 * 7.0 * ((double) arg2) * 2.0 + 1.0;
            return ((float) var7);
        } else {
            return 0.0f;
        }
    }

  private static boolean method1138(class_243 arg0, class_243 arg1, class_1937 arg2) { // было: a
        if (arg2 != null) {
            class_3965 var3 = aT.method1282(arg0, arg1, class_3960.field_17559);
            return var3 == null ? -933916154 ^ -933916154 : var3.method_17783() != class_240.field_1332 ? -933916154 ^ -933916154 : 601740068 ^ 601740069;
        } else {
            return 1238694501 ^ 1238694500;
        }
    }

    @Generated
  private ClassA133() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("*\u001f\u00050u9\u0019\u0015~,(\u0006Y\u0011£ Xj5Y$´\u0018\u0004\u0006¡\u0017\u0013&à��\\oa\u000f'¸$\u001em90\u0018\u001c9*\u0003\r\u000cd[6°d35°#1\u0016í��X:´z\".b[*¯\u0016Z\u001fäl", -173621445 ^ -1535123118)));
    }

  private static int method1139(int arg0, int arg1) { // было: s
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1140(int arg0, int arg1) { // было: t
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1141(int arg0, int arg1) { // было: u
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}