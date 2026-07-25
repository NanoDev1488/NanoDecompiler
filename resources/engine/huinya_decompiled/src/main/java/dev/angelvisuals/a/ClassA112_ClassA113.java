// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.z.c
package dev.angelvisuals.a;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.ClassA116_ClassA117;
import dev.angelvisuals.a.bp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

class ClassA112_ClassA113 extends ClassA116_ClassA117 {

    // ---- поля ----
  private final List field494; // было: w
  private final Random field495; // было: d
  private static final String tr = "// flow obfuscation: ENABLED";
  private static final String ts = "// this jar protected by JoinerObfuscator";
  private static final String tt = "// nice try. closed source for a reason.";
  private static final String tu = "// class hierarchy hashing: ENABLED";
  private static final String tv = "// === DO NOT TOUCH ===";
  private static final int lx = -158168846;
  private static final int ly = -938137768;
  private static final int lz = -1878652014;
  private static final byte[] cJ;

    static {
        cJ = "\",.ZmZbI1fSFxFTPkx9b&_|I_*~ub2jD5\\a@\"Ql/YA=1!}P0t9\"|Gj7LR1z|3>lX.CocSPatB4#=2R9=Wy _uX@?jcmX{G80|ZX&daaDrRlWoB~esPYq`Rro.AT !Z=(RB{w]I/riqnWY3Z+z;b}o4//Ll\"J$0L,H3'c\\ pla&u_lUjj;@EzLmVRW#`*'pd%Cw]b}Bb(I5JZbSAx5gVCGp/=bEb]'n6_O(,__y@9#.F%c1}N)56wAJ@<md'D/WeC".getBytes("ISO-8859-1");
    }

  public ClassA112_ClassA113(class_243 arg0) { // было: <init>
        super(7723006518994382119L ^ 7723006518994382343L);
        field494 = new ArrayList();
        field495 = new Random();
        class_243 var2 = arg0.method_1031(0.0, 200.0, 0.0);
        field494.add(var2);
        while (var2.field_1351 > arg0.field_1351) {
            double var3 = (field495.nextDouble() - 0.5) * 0.8;
            double var5 = -(6.0 + field495.nextDouble() * 10.0);
            double var7 = (field495.nextDouble() - 0.5) * 0.8;
            var2 = var2.method_1031(var3, var5, var7);
            if (var2.field_1351 < arg0.field_1351) {
                var2 = new class_243(arg0.field_1352, arg0.field_1351, arg0.field_1350);
            }
            field494.add(var2);
            continue;
        }
    }

  public void am() {
        // (пустое тело)
    }

  public void method875(class_4587 arg0, class_243 arg1, bp arg2, float arg3) { // было: a
        float __stk1;
        float var5 = ay();
        __stk1 = var5 >= 0.15000000596046448f ? 1.0f - (var5 - 0.15000000596046448f) / 0.8500000238418579f : var5 / 0.15000000596046448f;
        float var6 = __stk1;
        if (var6 > 0.0f) {
            Matrix4f var7 = arg0.method_23760().method_23761();
            class_289 var8 = class_289.method_1348();
            RenderSystem.blendFunc(-1821800958 ^ -1821801216, -1117531385 ^ -1117531386);
            method876(var8, var7, arg1, arg2.method1686(((int) (var6 * 40.0f))), 40.0f);
            method876(var8, var7, arg1, arg2.method1686(((int) (var6 * 80.0f))), 24.0f);
            method876(var8, var7, arg1, arg2.method1686(((int) (var6 * 130.0f))), 12.0f);
            method876(var8, var7, arg1, arg2.method1686(((int) (var6 * 190.0f))), 6.0f);
            RenderSystem.defaultBlendFunc();
            method876(var8, var7, arg1, arg2.method1686(((int) (var6 * 255.0f))), 4.400000095367432f);
            return;
        } else {
            return;
        }
    }

  private void method876(class_289 arg0, Matrix4f arg1, class_243 arg2, bp arg3, float arg4) { // было: a
        RenderSystem.lineWidth(arg4);
        class_287 var6 = arg0.method_60827(class_5596.field_27377, class_290.field_29337);
        int var7 = arg3.method1695();
        int var8 = arg3.method1696();
        int var9 = arg3.method1697();
        int var10 = arg3.method1698();
        int var11 = -619867105 ^ -619867105;
        while (var11 < field494.size() - (-92987119 ^ -92987120)) {
            class_243 var12 = ((class_243) field494.get(var11));
            class_243 var13 = ((class_243) field494.get(var11 + (-624777623 ^ -624777624)));
            var6.method_22918(arg1, ((float) (var12.field_1352 - arg2.field_1352)), ((float) (var12.field_1351 - arg2.field_1351)), ((float) (var12.field_1350 - arg2.field_1350))).method_1336(var7, var8, var9, var10).method_22914(0.0f, 1.0f, 0.0f);
            var6.method_22918(arg1, ((float) (var13.field_1352 - arg2.field_1352)), ((float) (var13.field_1351 - arg2.field_1351)), ((float) (var13.field_1350 - arg2.field_1350))).method_1336(var7, var8, var9, var10).method_22914(0.0f, 1.0f, 0.0f);
            ++var11;
            continue;
        }
        class_286.method_43433(var6.method_60800());
    }

  private static int jY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ka(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}