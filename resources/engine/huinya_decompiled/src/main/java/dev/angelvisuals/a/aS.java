// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.as
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.as_Anon1;
import dev.angelvisuals.a.as_ClassA166;
import dev.angelvisuals.a.as_ClassA167;
import dev.angelvisuals.a.as_ClassA168;
import dev.angelvisuals.a.as_ClassA169;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cQ;
import dev.angelvisuals.a.dI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Generated;
import net.minecraft.class_10142;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_9779;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4i;
import org.lwjgl.opengl.GL11;

public final class as implements cF {

    // ---- поля ----
  public static final List field957; // было: D
  public static final List field958; // было: E
  private static final List field959; // было: F
  private static final List field960; // было: G
  public static final List field961; // было: H
  public static final List field962; // было: I
  public static final List field963; // было: J
  public static final List field964; // было: K
  private static class_289 field965; // было: a
  private static Matrix4f field966; // было: b
  private static Matrix4f field967; // было: c
  private static Matrix4f field968; // было: d
  private static final class_2960 field969; // было: i
  private static final class_2960 field970; // было: j
  private static float cX;
  private static float cY;
  private static float cZ;
  private static float da;
  private static float db;
  private static boolean ak;
  private static final String yu = "// every class watermarked, every string encrypted, every number xored";
  private static final String yv = "// === DO NOT TOUCH ===";
  private static final String yw = "// stop. seriously. go play minecraft instead";
  private static final String yx = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String yy = "// this jar protected by JoinerObfuscator";
  private static final int ot = 437462863;
  private static final int ou = 1444157248;
  private static final int ov = 1950056378;
  private static final byte[] dE;

    static {
        dE = "'lA<wq?6#:~:D6k-@NW1&++$Knzyn!@x6|678vYp[h27dxT-osZhKLaP$%_;j*sP>B'.AlkK5*>K]Ctmz_xM2b$<*^0cm9}H\\3S!Pj/-VTChvoY+V%o+iq'eeN$+7MU0Jvut|l\"P?M\\B9e_ ?d#Ik7AJN\"._p6fcvWfDm;'4]~-*%xy-nh:G7ztl&xqjmPcn]ToB2kg%uY,.+O^#hZbl|?i<6=di5>za6*RLA|T[e_%A\"jvSKRn6w-RaWXc=c&_U".getBytes("ISO-8859-1");
        field957 = new ArrayList();
        field958 = new ArrayList();
        field959 = new ArrayList();
        field960 = new ArrayList();
        field961 = new ArrayList();
        field962 = new ArrayList();
        field963 = new ArrayList();
        field964 = new ArrayList();
        field965 = class_289.method_1348();
        field966 = new Matrix4f();
        field967 = new Matrix4f();
        field968 = new Matrix4f();
        field969 = class_2960.method_60654(Decryptor.method1945(XorDecoder.method1946("ù\u0016æ'¾á\u001céõ)óÌ\u0012ªÖ\u0003¢­í)¥ê\u0008³´\u0016üµó\u0013®¶)ÿá", 1874540381 ^ -1284479010)));
        field970 = class_2960.method_60654(Decryptor.method1945(XorDecoder.method1946("p}¿`èl0´\"ëd½_µt¸'¾0¨ZTDh BL%MFtyç", 1348058868 ^ -1974507529)));
        cX = 1.0f;
        cY = 1.0f;
    }

  public static void method1804(class_4587 arg0) { // было: c
        class_4665 var1 = arg0.method_23760();
        class_4665 var2 = var1;
        if (!field964.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader(class_10142.field_53876);
            class_287 var4 = field965.method_60827(class_5596.field_27382, class_290.field_1576);
            Iterator var5 = field964.iterator();
            while (var5.hasNext()) {
                as_ClassA166 var6 = ((as_ClassA166) var5.next());
                method1814(var2, var4, var6.field945, var6.field946, var6.field947, var6.field948, var6.fk);
                continue;
            }
            class_286.method_43433(var4.method_60800());
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field964.clear();
        }
        if (!field962.isEmpty()) {
            GL11.glEnable(-852536927 ^ -852534655);
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader(class_10142.field_53864);
            LinkedHashSet var4 = new LinkedHashSet();
            Iterator var5 = field962.iterator();
            while (var5.hasNext()) {
                as_Anon1 var6 = ((as_Anon1) var5.next());
                var4.add(Float.valueOf(var6.dJ));
                continue;
            }
            var5 = var4.iterator();
            while (var5.hasNext()) {
                float var6 = (((Float) var5.next())).floatValue();
                RenderSystem.lineWidth(var6);
                class_287 var7 = field965.method_60827(class_5596.field_27377, class_290.field_29337);
                Iterator var8 = field962.iterator();
                while (var8.hasNext()) {
                    as_Anon1 var9 = ((as_Anon1) var8.next());
                    if (var9.dJ == var6) {
                        method1812(arg0, var7, var9.field943, var9.field944, var9.sl, var9.sm);
                    }
                    continue;
                }
                class_286.method_43433(var7.method_60800());
                continue;
            }
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field962.clear();
            GL11.glDisable(-588194976 ^ -588197824);
        }
        if (!field961.isEmpty()) {
            GL11.glEnable(2067630737 ^ 2067628465);
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(-1808992125 ^ -1808992125);
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader(class_10142.field_53864);
            LinkedHashSet var4 = new LinkedHashSet();
            Iterator var5 = field961.iterator();
            while (var5.hasNext()) {
                as_Anon1 var6 = ((as_Anon1) var5.next());
                var4.add(Float.valueOf(var6.dJ));
                continue;
            }
            var5 = var4.iterator();
            while (var5.hasNext()) {
                float var6 = (((Float) var5.next())).floatValue();
                RenderSystem.lineWidth(var6);
                class_287 var7 = field965.method_60827(class_5596.field_27377, class_290.field_29337);
                Iterator var8 = field961.iterator();
                while (var8.hasNext()) {
                    as_Anon1 var9 = ((as_Anon1) var8.next());
                    if (var9.dJ == var6) {
                        method1812(arg0, var7, var9.field943, var9.field944, var9.sl, var9.sm);
                    }
                    continue;
                }
                class_286.method_43433(var7.method_60800());
                continue;
            }
            RenderSystem.depthMask(1037517094 ^ 1037517095);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field961.clear();
            GL11.glDisable(-2140019273 ^ -2140017001);
        }
        if (!field963.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_CONSTANT_ALPHA);
            RenderSystem.setShader(class_10142.field_53876);
            class_287 var4 = field965.method_60827(class_5596.field_27382, class_290.field_1576);
            Iterator var5 = field963.iterator();
            while (var5.hasNext()) {
                as_ClassA166 var6 = ((as_ClassA166) var5.next());
                method1814(var2, var4, var6.field945, var6.field946, var6.field947, var6.field948, var6.fk);
                continue;
            }
            class_286.method_43433(var4.method_60800());
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field963.clear();
        }
        if (!field958.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader(class_10142.field_53880);
            Iterator var4 = field958.iterator();
            while (var4.hasNext()) {
                as_ClassA169 var5 = ((as_ClassA169) var4.next());
                RenderSystem.setShaderTexture(-1826945437 ^ -1826945437, var5.field955);
                class_287 var6 = field965.method_60827(class_5596.field_27382, class_290.field_1575);
                method1822(var5, var6);
                class_286.method_43433(var6.method_60800());
                continue;
            }
            RenderSystem.enableDepthTest();
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field958.clear();
        }
        if (!field957.isEmpty()) {
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(-1576994694 ^ -1576994694);
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader(class_10142.field_53880);
            Iterator var4 = field957.iterator();
            while (var4.hasNext()) {
                as_ClassA169 var5 = ((as_ClassA169) var4.next());
                RenderSystem.setShaderTexture(1482227312 ^ 1482227312, var5.field955);
                class_287 var6 = field965.method_60827(class_5596.field_27382, class_290.field_1575);
                method1822(var5, var6);
                class_286.method_43433(var6.method_60800());
                continue;
            }
            RenderSystem.depthMask(-435800094 ^ -435800093);
            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            field957.clear();
        }
    }

  public static void method1805(class_4587 arg0, class_238 arg1, int arg2) { // было: a
        class_289 var3 = class_289.method_1348();
        class_287 var4 = var3.method_60827(class_5596.field_27382, class_290.field_1576);
        Matrix4f var5 = arg0.method_23760().method_23761();
        float var6 = ((float) arg1.field_1323);
        float var7 = ((float) arg1.field_1322);
        float var8 = ((float) arg1.field_1321);
        float var9 = ((float) arg1.field_1320);
        float var10 = ((float) arg1.field_1325);
        float var11 = ((float) arg1.field_1324);
        var4.method_22918(var5, var6, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var7, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var6, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var6, var7, var11).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var11).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var6, var7, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var6, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var8).method_39415(arg2);
        var4.method_22918(var5, var9, var10, var11).method_39415(arg2);
        var4.method_22918(var5, var9, var7, var11).method_39415(arg2);
        class_286.method_43433(var4.method_60800());
    }

  public static void method1806(class_2338 arg0, class_265 arg1, int arg2, float arg3) { // было: a
        method1807(arg0, arg1, arg2, arg3, 2137503551 ^ 2137503550, -2009822812 ^ -2009822812);
    }

  public static void method1807(class_2338 arg0, class_265 arg1, int arg2, float arg3, boolean arg4, boolean arg5) { // было: a
        if (dI.method1438(arg1.method_1107().method_996(arg0))) {
            field960.stream().filter(lp0 -> method1838(arg1, ((as_ClassA167) lp0))).findFirst().ifPresentOrElse(lp0 -> method1836(arg0, arg2, arg3, arg4, arg5, ((as_ClassA167) lp0)), () -> method1835(arg1));
        }
    }

  public static void method1808(class_2338 arg0, class_265 arg1, int arg2, float arg3, boolean arg4, boolean arg5) { // было: b
        class_243 var6 = class_243.method_24954(arg0);
        if (dI.method1438(arg1.method_1107().method_997(var6))) {
            List var7 = arg1.method_1090();
            field959.stream().filter(lp0 -> method1834(var7, ((as_ClassA168) lp0))).findFirst().ifPresentOrElse(lp0 -> method1831(var6, arg2, arg3, arg4, arg5, ((as_ClassA168) lp0)), () -> method1829(arg1));
        }
    }

  public static void method1809(class_238 arg0, int arg1, float arg2) { // было: a
        method1810(arg0, arg1, arg2, -1173097963 ^ -1173097964, 560605445 ^ 560605444, 1384278430 ^ 1384278430);
    }

  public static void method1810(class_238 arg0, int arg1, float arg2, boolean arg3, boolean arg4, boolean arg5) { // было: a
        arg0 = arg0.method_1014(0.001);
        if (dI.method1438(arg0)) {
            double var6 = arg0.field_1323;
            double var8 = arg0.field_1322;
            double var10 = arg0.field_1321;
            double var12 = arg0.field_1320;
            double var14 = arg0.field_1325;
            double var16 = arg0.field_1324;
            if (arg4) {
                int var18 = cQ.method1718(arg1, 0.10000000149011612f);
                method1820(new class_243(var6, var8, var10), new class_243(var12, var8, var10), new class_243(var12, var8, var16), new class_243(var6, var8, var16), var18, arg5);
                method1820(new class_243(var6, var8, var10), new class_243(var6, var14, var10), new class_243(var12, var14, var10), new class_243(var12, var8, var10), var18, arg5);
                method1820(new class_243(var12, var8, var10), new class_243(var12, var14, var10), new class_243(var12, var14, var16), new class_243(var12, var8, var16), var18, arg5);
                method1820(new class_243(var6, var8, var16), new class_243(var12, var8, var16), new class_243(var12, var14, var16), new class_243(var6, var14, var16), var18, arg5);
                method1820(new class_243(var6, var8, var10), new class_243(var6, var8, var16), new class_243(var6, var14, var16), new class_243(var6, var14, var10), var18, arg5);
                method1820(new class_243(var6, var14, var10), new class_243(var6, var14, var16), new class_243(var12, var14, var16), new class_243(var12, var14, var10), var18, arg5);
            }
            if (arg3) {
                method1817(var6, var8, var10, var12, var8, var10, arg1, arg2, arg5);
                method1817(var12, var8, var10, var12, var8, var16, arg1, arg2, arg5);
                method1817(var12, var8, var16, var6, var8, var16, arg1, arg2, arg5);
                method1817(var6, var8, var16, var6, var8, var10, arg1, arg2, arg5);
                method1817(var6, var8, var16, var6, var14, var16, arg1, arg2, arg5);
                method1817(var6, var8, var10, var6, var14, var10, arg1, arg2, arg5);
                method1817(var12, var8, var16, var12, var14, var16, arg1, arg2, arg5);
                method1817(var12, var8, var10, var12, var14, var10, arg1, arg2, arg5);
                method1817(var6, var14, var10, var12, var14, var10, arg1, arg2, arg5);
                method1817(var12, var14, var10, var12, var14, var16, arg1, arg2, arg5);
                method1817(var12, var14, var16, var6, var14, var16, arg1, arg2, arg5);
                method1817(var6, var14, var16, var6, var14, var10, arg1, arg2, arg5);
            }
        }
    }

  public static void method1811(@NotNull class_4587 arg0, @NotNull class_4588 arg1, class_243 arg2, class_243 arg3, int arg4) { // было: a
        method1813(arg0, arg1, arg2.method_46409(), arg3.method_46409(), arg4, arg4);
    }

  public static void method1812(@NotNull class_4587 arg0, @NotNull class_4588 arg1, class_243 arg2, class_243 arg3, int arg4, int arg5) { // было: a
        method1813(arg0, arg1, arg2.method_46409(), arg3.method_46409(), arg4, arg5);
    }

  public static void method1813(@NotNull class_4587 arg0, @NotNull class_4588 arg1, Vector3f arg2, Vector3f arg3, int arg4, int arg5) { // было: a
        arg0.method_22903();
        class_4665 var6 = arg0.method_23760();
        Vector3f var7 = method1816(arg2.x, arg2.y, arg2.z, arg3.x, arg3.y, arg3.z);
        arg1.method_61032(var6, arg2).method_39415(arg4).method_60831(var6, var7.x(), var7.y(), var7.z());
        arg1.method_61032(var6, arg3).method_39415(arg5).method_60831(var6, var7.x(), var7.y(), var7.z());
        arg0.method_22909();
    }

  public static void method1814(@NotNull class_4665 arg0, @NotNull class_4588 arg1, class_243 arg2, class_243 arg3, class_243 arg4, class_243 arg5, int arg6) { // было: a
        method1815(arg0, arg1, arg2.method_46409(), arg3.method_46409(), arg4.method_46409(), arg5.method_46409(), arg6);
    }

  public static void method1815(@NotNull class_4665 arg0, @NotNull class_4588 arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, int arg6) { // было: a
        arg1.method_61032(arg0, arg2).method_39415(arg6);
        arg1.method_61032(arg0, arg3).method_39415(arg6);
        arg1.method_61032(arg0, arg4).method_39415(arg6);
        arg1.method_61032(arg0, arg5).method_39415(arg6);
    }

    @NotNull
  public static Vector3f method1816(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5) { // было: a
        float var6 = arg3 - arg0;
        float var7 = arg4 - arg1;
        float var8 = arg5 - arg2;
        float var9 = class_3532.method_15355(var6 * var6 + var7 * var7 + var8 * var8);
        return new Vector3f(var6 / var9, var7 / var9, var8 / var9);
    }

  public static void aL() {
        float __stk1;
        cZ = cX;
        cX = cX + cY;
        if (cY > 25.0f) {
            ak = -185263378 ^ -185263377;
        }
        if (cY < -25.0f) {
            ak = -1523407515 ^ -1523407515;
        }
        __stk1 = !ak ? cY + 0.5f : cY - 0.5f;
        cY = __stk1;
        da = db;
        db = db + 0.15000000596046448f;
    }

  public static void method1817(double arg0, double arg1, double arg2, double arg3, double arg4, double arg5, int arg6, float arg7, boolean arg8) { // было: a
        method1818(new class_243(arg0, arg1, arg2), new class_243(arg3, arg4, arg5), arg6, arg7, arg8);
    }

  public static void method1818(class_243 arg0, class_243 arg1, int arg2, float arg3, boolean arg4) { // было: a
        method1819(arg0, arg1, arg2, arg2, arg3, arg4);
    }

  public static void method1819(class_243 arg0, class_243 arg1, int arg2, int arg3, float arg4, boolean arg5) { // было: a
        class_243 var6 = mc.method_1561().field_4686.method_19326();
        as_Anon1 var7 = new as_Anon1(arg0.method_1020(var6), arg1.method_1020(var6), arg2, arg3, arg4);
        if (!arg5) {
            field962.add(var7);
        } else {
            field961.add(var7);
        }
    }

  public static void method1820(class_243 arg0, class_243 arg1, class_243 arg2, class_243 arg3, int arg4, boolean arg5) { // было: a
        class_243 var6 = mc.method_1561().field_4686.method_19326();
        as_ClassA166 var7 = new as_ClassA166(arg0.method_1020(var6), arg1.method_1020(var6), arg2.method_1020(var6), arg3.method_1020(var6), arg4);
        if (!arg5) {
            field964.add(var7);
        } else {
            field963.add(var7);
        }
    }

  public static void method1821(class_4665 arg0, class_2960 arg1, float arg2, float arg3, float arg4, float arg5, Vector4i arg6, boolean arg7) { // было: a
        as_ClassA169 var8 = new as_ClassA169(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
        if (!arg7) {
            field958.add(var8);
        } else {
            field957.add(var8);
        }
    }

  private static void method1822(as_ClassA169 arg0, class_287 arg1) { // было: a
        class_4665 var2 = arg0.field954;
        float var3 = arg0.ar;
        float var4 = arg0.as;
        float var5 = arg0.at;
        float var6 = arg0.au;
        Vector4i var7 = arg0.field956;
        arg1.method_56824(var2, var3, var4, 0.0f).method_39415(var7.x).method_22913(0.0f, 0.0f);
        arg1.method_56824(var2, var3, var4 + var6, 0.0f).method_39415(var7.y).method_22913(0.0f, 1.0f);
        arg1.method_56824(var2, var3 + var5, var4 + var6, 0.0f).method_39415(var7.z).method_22913(1.0f, 1.0f);
        arg1.method_56824(var2, var3 + var5, var4, 0.0f).method_39415(var7.w).method_22913(1.0f, 0.0f);
    }

  public static float be() {
        return mc.method_61966().method_60637(-1332802776 ^ -1332802776);
    }

    @Generated
  private as() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("21¾([\u0001;Å4\u0006½A?¡@DA\n¶£\u001c(£¬\u000b\u0008â»DAÚ\u0017\tº\u0006C(6§!\u0004¸\u0015\"ßC\u0018²ß+\u001b²)8ï»@\u0014¶Á:��ÙC\u0004­­B1æ×", -793948708 ^ 980827565)));
    }

    @Generated
  public static void method1823(Matrix4f arg0) { // было: a
        field966 = arg0;
    }

    @Generated
  public static void method1824(Matrix4f arg0) { // было: b
        field967 = arg0;
    }

    @Generated
  public static void method1825(Matrix4f arg0) { // было: c
        field968 = arg0;
    }

    @Generated
  public static Matrix4f method1826() { // было: b
        return field966;
    }

    @Generated
  public static Matrix4f method1827() { // было: c
        return field967;
    }

    @Generated
  public static Matrix4f method1828() { // было: d
        return field968;
    }

  private static void method1829(class_265 arg0) { // было: a
        ArrayList var1 = new ArrayList();
        arg0.method_1104((lp0, lp1, lp2, lp3, lp4, lp5) -> method1830(var1, lp0, lp1, lp2, lp3, lp4, lp5));
        field959.add(new as_ClassA168(arg0, var1, arg0.method_1090()));
    }

  private static void method1830(List arg0, double arg1, double arg2, double arg3, double arg4, double arg5, double arg6) { // было: a
        arg0.add(new as_Anon1(new class_243(arg1, arg2, arg3), new class_243(arg4, arg5, arg6), -664078878 ^ -664078878, -1611760116 ^ -1611760116, 0.0f));
    }

  private static void method1831(class_243 arg0, int arg1, float arg2, boolean arg3, boolean arg4, as_ClassA168 arg5) { // было: a
        arg5.field953.forEach(lp0 -> method1833(arg0, arg1, arg2, arg3, arg4, ((class_238) lp0)));
        arg5.field952.forEach(lp0 -> method1832(arg0, arg1, arg2, arg4, ((as_Anon1) lp0)));
    }

  private static void method1832(class_243 arg0, int arg1, float arg2, boolean arg3, as_Anon1 arg4) { // было: a
        method1818(arg4.field943.method_1019(arg0), arg4.field944.method_1019(arg0), arg1, arg2, arg3);
    }

  private static void method1833(class_243 arg0, int arg1, float arg2, boolean arg3, boolean arg4, class_238 arg5) { // было: a
        method1810(arg5.method_997(arg0), arg1, arg2, -202653074 ^ -202653074, arg3, arg4);
    }

  private static boolean method1834(List arg0, as_ClassA168 arg1) { // было: a
        return arg1.field953.equals(arg0);
    }

  private static void method1835(class_265 arg0) { // было: b
        field960.add(new as_ClassA167(arg0, arg0.method_1090()));
    }

  private static void method1836(class_2338 arg0, int arg1, float arg2, boolean arg3, boolean arg4, as_ClassA167 arg5) { // было: a
        arg5.field950.forEach(lp0 -> method1837(arg0, arg1, arg2, arg3, arg4, ((class_238) lp0)));
    }

  private static void method1837(class_2338 arg0, int arg1, float arg2, boolean arg3, boolean arg4, class_238 arg5) { // было: a
        method1810(arg5.method_996(arg0), arg1, arg2, 205124156 ^ 205124157, arg3, arg4);
    }

  private static boolean method1838(class_265 arg0, as_ClassA167 arg1) { // было: a
        return arg1.field949.equals(arg0);
    }

  private static int mJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mL(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}