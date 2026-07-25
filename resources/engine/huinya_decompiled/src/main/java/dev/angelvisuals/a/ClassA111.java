// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.G
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA110_Anon1;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import dev.angelvisuals.a.do;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_746;
import net.minecraft.class_7833;
import org.joml.Matrix4f;

@bI(name = "JumpCircle", a = "RENDER", I = "Рисует круг при прыжке")
public final class ClassA111 extends cK implements cF {

    // ---- поля ----
  public static final ClassA111 field481; // было: a
  private static final float field482 = 1850.0f; // было: n
  private static final float field483 = 120.0f; // было: o
  private static final float field484 = 7.0f; // было: p
  private static final float field485 = 0.05999999865889549f; // было: q
  private static final float field486 = 0.11999999731779099f; // было: r
  private static final int be = 8;
  private final bA field487; // было: g
  private final bA field488; // было: h
  private final bA field489; // было: i
  private final List field490; // было: d
  private final class_2960 field491; // было: b
  private boolean field492; // было: g
  private static final String cn = "// good luck with the next 9999 classes";
  private static final String co = "// Joiner sees you";
  private static final String cp = "// this jar protected by JoinerObfuscator";
  private static final String cq = "// flow obfuscation: ENABLED";
  private static final String cr = "// === DO NOT TOUCH ===";
  private static final int bf = -1700173811;
  private static final int bg = 463227503;
  private static final int bh = 366170694;
  private static final byte[] field493; // было: H

    static {
        field493 = "S2KdKa<ro=N=|Kn!E>R!w2nTHdwU{wQfpE=ex6QMGTnCQf\"GKyX2sBnGV^BH=Cjm|}@O]*:xKU'[H1m}\\lR>wj<2G&cdZ_F|*VU%j=pH#ri=Wew&,u=xC2.|H<gf 2Wr_'~aDg-6trHEe)p)vUBl*j()Z~9CF<j!}0uutoU1-1sj}=Ujy0^HIgu';zC_MVi7}V/BAIl\"?Pn]KF:uZzWW~)?~l7w-7R\"w)[gFi^6(=*:g4{hbpDz plES ^9t|1a4".getBytes("ISO-8859-1");
        field481 = new ClassA111();
    }

  private ClassA111() { // было: <init>
        super();
        field487 = new bA(Decryptor.method1945(XorDecoder.method1946("]\u0006&\u001a´GFf>tE¸\u007f\u0005âò", 523946705 ^ -790251321)), 1.850000023841858f, 0.5f, 4.0f, 0.10000000149011612f);
        field488 = new bA(Decryptor.method1945(XorDecoder.method1946("@àV´IÿJDÉbzÊD¼BÕ\u0018J]\u0005\u001b~ç\u001e¢{|Jìh@Ø\u0013", -1012488520 ^ -318142357)), 1.2000000476837158f, 1.0f, 5.0f, 0.10000000149011612f);
        field489 = new bA(Decryptor.method1945(XorDecoder.method1946(" \"ä¾+û¢©&Í¢\u0018Î¬ Ñð²\u0016ÿ¶(ÆÂ6¶��ÎÃ;è°ß\u001aÏ¼Ç:ú±|ßª¼\u001bÍ°º\u0015Ç-ó«", -1344233839 ^ 1766576737)), 1.5f, 1.0f, 5.0f, 0.5f);
        field490 = new ArrayList();
        field491 = AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("H\u0001Mö]\u0017kÖS-Uñ]\u0007,E.5Ï< +ÔI\u007f)\\\u0004R{\u0001-õ@0YáS:m", -1189094221 ^ 503679161)));
        field492 = -1434856892 ^ -1434856891;
    }

  public void method865() { // было: j
        if (mc.field_1724 != null) {
            field492 = mc.field_1724.method_24828();
        }
        super.method610();
    }

  public void method866() { // было: k
        field490.clear();
        super.method611();
    }

    @EventTarget
  private void method867(do arg0) { // было: c
        if (mc.field_1724 == null) {
            return;
        }
        boolean var2;
        if (mc.field_1687 != null) {
            var2 = mc.field_1724.method_24828();
            if (field492) {
                if (!var2) {
                    class_243 var3 = new class_243(mc.field_1724.method_23317(), Math.floor(mc.field_1724.method_23318()) + 0.01, mc.field_1724.method_23321());
                    field490.add(new ClassA110_Anon1(var3, System.currentTimeMillis()));
                    while (field490.size() > (-411609558 ^ -411609566)) {
                        field490.remove(-1291952381 ^ -1291952381);
                        continue;
                    }
                }
            }
        } else {
            return;
        }
        field492 = var2;
        long var3 = System.currentTimeMillis();
        float var5 = method869();
        Iterator var6 = field490.iterator();
        while (var6.hasNext()) {
            ClassA110_Anon1 var7 = ((ClassA110_Anon1) var6.next());
            if (var3 - var7.field480 > ((long) var5)) {
                var6.remove();
            }
            continue;
        }
    }

    @EventTarget
  private void method868(dD arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #103 // dev.angelvisuals.a.G.d:Ljava/util/List;
        //      4: invokeinterface  #188 // java.util.List.isEmpty:()Z, count 1
        //      9: ifeq  13 (offset +4)
        //     12: return
        //     13: invokestatic  #162 // java.lang.System.currentTimeMillis:()J
        //     16: lstore_2
        //     17: getstatic  #108 // dev.angelvisuals.a.G.mc:Lnet/minecraft/class_310;
        //     20: invokevirtual  #170 // net.minecraft.class_310.method_1561:()Lnet/minecraft/class_898;
        //     23: getfield  #121 // net.minecraft.class_898.field_4686:Lnet/minecraft/class_4184;
        //     26: invokevirtual  #172 // net.minecraft.class_4184.method_19326:()Lnet/minecraft/class_243;
        //     29: astore  4
        //     31: aload_1
        //     32: invokevirtual  #156 // dev.angelvisuals.a.dD.b:()Lnet/minecraft/class_4587;
        //     35: astore  5
        //     37: invokestatic  #129 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     40: invokestatic  #131 // com.mojang.blaze3d.systems.RenderSystem.enableDepthTest:()V
        //     43: ldc  #24 // 1291249354
        //     45: ldc  #24 // 1291249354
        //     47: ixor
        //     48: invokestatic  #126 // com.mojang.blaze3d.systems.RenderSystem.depthMask:(Z)V
        //     51: invokestatic  #128 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //     54: ldc  #8 // -1202975546
        //     56: ldc  #9 // -1202974780
        //     58: ixor
        //     59: ldc  #4 // -1430880156
        //     61: ldc  #5 // -1430880155
        //     63: ixor
        //     64: invokestatic  #124 // com.mojang.blaze3d.systems.RenderSystem.blendFunc:(II)V
        //     67: getstatic  #111 // net.minecraft.class_10142.field_53880:Lnet/minecraft/class_10156;
        //     70: invokestatic  #132 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     73: pop
        //     74: ldc  #23 // 1250996139
        //     76: ldc  #23 // 1250996139
        //     78: ixor
        //     79: aload_0
        //     80: getfield  #102 // dev.angelvisuals.a.G.b:Lnet/minecraft/class_2960;
        //     83: invokestatic  #134 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(ILnet/minecraft/class_2960;)V
        //     86: aload_0
        //     87: getfield  #103 // dev.angelvisuals.a.G.d:Ljava/util/List;
        //     90: invokeinterface  #189 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //     95: astore  6
        //     97: aload  6
        //     99: invokeinterface  #183 // java.util.Iterator.hasNext:()Z, count 1
        //    104: ifeq  175 (offset +71)
        //    107: aload  6
        //    109: invokeinterface  #184 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    114: checkcast  #63 // dev.angelvisuals.a.G$1
        //    117: astore  7
        //    119: aload_0
        //    120: lload_2
        //    121: aload  7
        //    123: invokevirtual  #139 // dev.angelvisuals.a.G.a:(JLdev/angelvisuals/a/G$1;)F
        //    126: fstore  8
        //    128: fload  8
        //    130: fconst_1
        //    131: fcmpl
        //    132: iflt  138 (offset +6)
        //    135: goto  97 (offset -38)
        //    138: aload_0
        //    139: fload  8
        //    141: invokevirtual  #142 // dev.angelvisuals.a.G.b:(F)F
        //    144: fstore  9
        //    146: fload  9
        //    148: ldc  #26 // 0.009999999776482582f
        //    150: fcmpg
        //    151: ifgt  157 (offset +6)
        //    154: goto  97 (offset -57)
        //    157: aload_0
        //    158: aload  5
        //    160: aload  4
        //    162: aload  7
        //    164: fload  8
        //    166: fload  9
        //    168: lload_2
        //    169: invokevirtual  #141 // dev.angelvisuals.a.G.a:(Lnet/minecraft/class_4587;Lnet/minecraft/class_243;Ldev/angelvisuals/a/G$1;FFJ)V
        //    172: goto  97 (offset -75)
        //    175: invokestatic  #130 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    178: ldc  #18 // 386942186
        //    180: ldc  #19 // 386942187
        //    182: ixor
        //    183: invokestatic  #126 // com.mojang.blaze3d.systems.RenderSystem.depthMask:(Z)V
        //    186: invokestatic  #131 // com.mojang.blaze3d.systems.RenderSystem.enableDepthTest:()V
        //    189: invokestatic  #125 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //    192: invokestatic  #127 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    195: fconst_1
        //    196: fconst_1
        //    197: fconst_1
        //    198: fconst_1
        //    199: invokestatic  #133 // com.mojang.blaze3d.systems.RenderSystem.setShaderColor:(FFFF)V
        //    202: return
    }

  private float method869() { // было: h
        return 1850.0f / Math.max(0.25f, field488.bp());
    }

  private float method870(long arg0, ClassA110_Anon1 arg1) { // было: a
        return ((float) (arg0 - arg1.field480)) / method869();
    }

  private float method871(float arg0) { // было: b
        float var2 = class_3532.method_15363(arg0 * field489.bp(), 0.0f, 1.0f);
        return 1.0f - var2;
    }

  private void method872(class_4587 arg0, class_243 arg1, ClassA110_Anon1 arg2, float arg3, float arg4, long arg5) { // было: a
        float var8 = ((float) (arg5 - arg2.field480)) / 1000.0f;
        float var9 = method874(arg3);
        float var10 = Math.min(var9 * field487.bp(), field487.bp());
        float var11 = var8 * 120.0f * field488.bp();
        var11 = var11 + ((float) Math.sin(((double) arg3) * 3.141592653589793 * 2.0)) * 30.0f;
        float var12 = ((float) Math.sin(((double) (var8 * 7.0f * field488.bp()))));
        float var13 = 1.0f + var12 * 0.05999999865889549f;
        float var14 = class_3532.method_15363(arg4 * (1.0f + var12 * 0.11999999731779099f), 0.0f, 1.0f);
        float var15 = class_3532.method_15363(var14 * 1.25f, 0.0f, 1.0f);
        float var16 = var10 * var13;
        bp var17 = AngelVisuals.getInstance().getThemeManager().method481().method449();
        int var18 = var17.method1687(var15).method1680();
        int var19 = var17.method1687(var15).method1680();
        int var20 = var17.method1689(0.6499999761581421f).method1687(class_3532.method_15363(var15 * 0.8999999761581421f, 0.0f, 1.0f)).method1680();
        int var21 = var17.method1689(0.6499999761581421f).method1687(class_3532.method_15363(var15 * 0.8999999761581421f, 0.0f, 1.0f)).method1680();
        arg0.method_22903();
        arg0.method_22904(arg2.field479.field_1352 - arg1.field_1352, arg2.field479.field_1351 - arg1.field_1351, arg2.field479.field_1350 - arg1.field_1350);
        arg0.method_22907(class_7833.field_40714.rotationDegrees(90.0f));
        arg0.method_22907(class_7833.field_40718.rotationDegrees(var11));
        Matrix4f var22 = arg0.method_23760().method_23761();
        float var23 = var16 * 0.5f;
        float var24 = var16 * 1.0800000429153442f;
        float var25 = var24 * 0.5f;
        class_287 var26 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
        method873(var26, var22, -var23, -var23, var23, var23, var18, var19);
        method873(var26, var22, -var25, -var25, var25, var25, var20, var21);
        class_286.method_43433(var26.method_60800());
        arg0.method_22909();
    }

  private void method873(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, int arg6, int arg7) { // было: a
        arg0.method_22918(arg1, arg2, arg3, 0.0f).method_22913(0.0f, 1.0f).method_39415(arg6);
        arg0.method_22918(arg1, arg2, arg5, 0.0f).method_22913(0.0f, 0.0f).method_39415(arg7);
        arg0.method_22918(arg1, arg4, arg5, 0.0f).method_22913(1.0f, 0.0f).method_39415(arg7);
        arg0.method_22918(arg1, arg4, arg3, 0.0f).method_22913(1.0f, 1.0f).method_39415(arg6);
    }

  private static float method874(float arg0) { // было: c
        float var1 = 1.0f - arg0;
        return 1.0f - var1 * var1 * var1;
    }

  private static int aV(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}