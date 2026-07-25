// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.P
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA88_ClassA89;
import dev.angelvisuals.a.ClassA90;
import dev.angelvisuals.a.ClassA97_ClassA98;
import dev.angelvisuals.a.ClassA99_ClassA100;
import dev.angelvisuals.a.aM;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.bs;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.cQ;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.dD;
import net.minecraft.class_10142;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_3532;
import net.minecraft.class_3882;
import net.minecraft.class_4050;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_5498;
import net.minecraft.class_746;
import net.minecraft.class_7833;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

@bI(name = "Cosmetics", a = "RENDER", I = "Визуальные украшения")
public final class ClassA101 extends cK implements cF {

    // ---- поля ----
  public static final ClassA101 field390; // было: a
  private static final float field391 = 0.03490658476948738f; // было: V
  private static final float field392 = 1.0f; // было: W
  private static final float field393 = 1.600000023841858f; // было: X
  private static final float field394 = 25.0f; // было: Y
  private static final int bF = 2;
  private static final int bG = 17;
  private static final float field395 = 0.44999998807907104f; // было: Z
  private static final float aa = 0.23000000417232513f;
  private static final double field396 = 0.11; // было: j
  private static final int bH = 255;
  private static final int bI = 9;
  private static final float ab = 170.0f;
  private static final ClassA97_ClassA98[] field397; // было: a
  private final ClassA90 field398; // было: a
  private final aM field399; // было: a
  private final bA field400; // было: j
  private final aM field401; // было: b
  private final bA field402; // было: k
  private float ac;
  private boolean field403; // было: h
  private static final String do = "Protected by t.me/JoinerClient";
  private static final String dp = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String dq = "// number obfuscation: ENABLED (XOR masking)";
  private static final String dr = "// class hierarchy hashing: ENABLED";
  private static final String ds = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final int bJ = -94690340;
  private static final int bK = -1833953803;
  private static final int bL = 621028904;
  private static final byte[] field404; // было: P

    static {
        field404 = "y,'y}\\lPLlwX\\W{;ox/-7q@L;[$)xxI^r81|Qh|r)pEp2u;18i%DSXL#e*B%DajP|Kc;?:G .G/%.^vvxPKD\"vO.tL_N_/<uoB@~3%nei06D0PqEo^Bt3%N-d*8?.%rhcp{WrK([bfJf%OWHHAAGd,+s/bjW,M-:I,<-jWY[;7(6?OH0\"[U#+N12Ryl$SKvfDM3mtiojDlC/d5H!DW-Y|WJFx}Z]ec?k6\\-9y^6_p6LPQ04PIWS.vR!zyxZbA&zF".getBytes("ISO-8859-1");
        field390 = new ClassA101();
        ClassA97_ClassA98[] __obj1 = new ClassA97_ClassA98[338883902 ^ 338883891];
        __obj1[1751560841 ^ 1751560841] = new ClassA97_ClassA98(0.07999999821186066f, 0.10000000149011612f, 0.8799999952316284f);
        __obj1[1446048718 ^ 1446048719] = new ClassA97_ClassA98(0.2800000011920929f, 0.3400000035762787f, 0.7799999713897705f);
        __obj1[-858518251 ^ -858518249] = new ClassA97_ClassA98(0.5600000023841858f, 0.8199999928474426f, 0.6200000047683716f);
        __obj1[-1511168564 ^ -1511168561] = new ClassA97_ClassA98(0.8600000143051147f, 0.30000001192092896f, 0.5199999809265137f);
        __obj1[-964059395 ^ -964059399] = new ClassA97_ClassA98(1.1399999856948853f, 0.46000000834465027f, 0.4000000059604645f);
        __obj1[-2114619812 ^ -2114619815] = new ClassA97_ClassA98(1.2400000095367432f, 0.03999999910593033f, 0.30000001192092896f);
        __obj1[-2126116899 ^ -2126116901] = new ClassA97_ClassA98(1.0199999809265137f, -0.18000000715255737f, 0.2800000011920929f);
        __obj1[1480070011 ^ 1480070012] = new ClassA97_ClassA98(1.1799999475479126f, -0.6399999856948853f, 0.2199999988079071f);
        __obj1[1314415346 ^ 1314415354] = new ClassA97_ClassA98(0.8600000143051147f, -0.46000000834465027f, 0.20000000298023224f);
        __obj1[37857285 ^ 37857292] = new ClassA97_ClassA98(0.800000011920929f, -0.9800000190734863f, 0.14000000059604645f);
        __obj1[230141588 ^ 230141598] = new ClassA97_ClassA98(0.5400000214576721f, -0.7400000095367432f, 0.1599999964237213f);
        __obj1[-2012614612 ^ -2012614617] = new ClassA97_ClassA98(0.30000001192092896f, -1.159999966621399f, 0.11999999731779099f);
        __obj1[-2067595981 ^ -2067595969] = new ClassA97_ClassA98(0.10000000149011612f, -0.5400000214576721f, 0.18000000715255737f);
        field397 = __obj1;
    }

  private ClassA101() { // было: <init>
        super();
        ClassA88_ClassA89[] __obj1 = new ClassA88_ClassA89[1163154356 ^ 1163154352];
        __obj1[1974680272 ^ 1974680272] = new ClassA88_ClassA89(Decryptor.method1945(XorDecoder.method1946("÷)Õ¬Á]Ã\rÊ\u0011àRX", 1163807486 ^ 553432603)), 293449955 ^ 293449954);
        __obj1[506529910 ^ 506529911] = new ClassA88_ClassA89(Decryptor.method1945(XorDecoder.method1946("¶èÇï×ÞÛûÒÐäÐ²«©ïß²ÔÞÏØ ", -1958544764 ^ 937386777)), 968941094 ^ 968941095);
        __obj1[-739582871 ^ -739582869] = new ClassA88_ClassA89(Decryptor.method1945(XorDecoder.method1946("v\u007fXfKyxw~hI\u0015lmXn[LQ^XO\u0007\u0010", 1836775737 ^ 1077995301)), -973381188 ^ -973381188);
        __obj1[-1562245372 ^ -1562245369] = new ClassA88_ClassA89(Decryptor.method1945(XorDecoder.method1946("\u001fRa~>j[Y\u0014m{D.qI_/kig`#l~\u0016roS.QEQ;frq��qaa>=e\u000f", 785608391 ^ 486237854)), 214692907 ^ 214692906);
        field398 = new ClassA90(Decryptor.method1945(XorDecoder.method1946("hëZw~SCYE\u0003\u0002åYp\u0005ôd`WøG\u0002dÁCc{Ô~b`gakz[cY\u000c", -1465171687 ^ -1717968085)), __obj1);
        field399 = new aM(Decryptor.method1945(XorDecoder.method1946("n@RÙPF(ÂqjbÑaxz¾oAzâjH*ý`C0ÆAqrÆ\\BIÑ}m4Âqd#µ", 410949398 ^ -1872388594)), 1377564248 ^ 1377564249, () -> method795());
        field400 = new bA(Decryptor.method1945(XorDecoder.method1946("\u001b·%g\u001dÙV`\u0013HC\u0002Ú[D9´O`\u000e3<", 2026267595 ^ 2043186835)), 1.0f, 0.6499999761581421f, 1.7999999523162842f, 0.05000000074505806f, () -> method794());
        field401 = new aM(Decryptor.method1945(XorDecoder.method1946("³\u0016Dl_¬µ&L¼§>#²>\u007f·+Gâ\u0014t£¦\u000el÷¦mS³\u0013:¬lpkYôæ\u0016fö\u001edôºpq0g", -1500497802 ^ -1278218573)), 936597043 ^ 936597042, () -> method793());
        field402 = new bA(Decryptor.method1945(XorDecoder.method1946("æ«¯MÆâÑeÒ£Û}ëý¾UÚ»tò®Ô:", 507414301 ^ 433540767)), 1.0f, 0.6499999761581421f, 1.7999999523162842f, 0.05000000074505806f, () -> method792());
    }

  public void method768() { // было: k
        field403 = -1922715121 ^ -1922715121;
        super.method611();
    }

    @EventTarget
  public void method769(dD arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //      3: getfield  #550 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //      6: ifnull  18 (offset +12)
        //      9: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //     12: getfield  #548 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     15: ifnonnull  19 (offset +4)
        //     18: return
        //     19: aload_0
        //     20: getfield  #501 // dev.angelvisuals.a.P.a:Ldev/angelvisuals/a/I;
        //     23: ldc_w  #408 // '\x06ü\x0bñPÍ=\x85\x16ø?Õ\x11«uÉ\x14ÿ\x1c\x8a\x0cúc\x80'
        //     26: ldc  #228 // 1177847658
        //     28: ldc  #125 // -76932598
        //     30: ixor
        //     31: invokestatic  #561 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     34: invokestatic  #560 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     37: invokevirtual  #579 // dev.angelvisuals.a.I.d:(Ljava/lang/String;)Z
        //     40: ifeq  48 (offset +8)
        //     43: aload_0
        //     44: aload_1
        //     45: invokevirtual  #603 // dev.angelvisuals.a.P.c:(Ldev/angelvisuals/a/dD;)V
        //     48: aload_0
        //     49: getfield  #501 // dev.angelvisuals.a.P.a:Ldev/angelvisuals/a/I;
        //     52: ldc_w  #421 // 'dð\x82s\x05Æ\x9eg��È¡L`³ìs\rª\x91B\x1dÀå\x1d'
        //     55: ldc  #77 // -1063012383
        //     57: ldc  #102 // -528795986
        //     59: ixor
        //     60: invokestatic  #561 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     63: invokestatic  #560 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     66: invokevirtual  #579 // dev.angelvisuals.a.I.d:(Ljava/lang/String;)Z
        //     69: istore_2
        //     70: aload_0
        //     71: getfield  #501 // dev.angelvisuals.a.P.a:Ldev/angelvisuals/a/I;
        //     74: ldc_w  #420 // ']\x7f7F`y\x17WUh&5Gm7NpL>~sOh0'
        //     77: ldc_w  #266 // 1740509042
        //     80: ldc_w  #273 // 1793790277
        //     83: ixor
        //     84: invokestatic  #561 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     87: invokestatic  #560 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     90: invokevirtual  #579 // dev.angelvisuals.a.I.d:(Ljava/lang/String;)Z
        //     93: istore_3
        //     94: iload_2
        //     95: ifne  103 (offset +8)
        //     98: iload_3
        //     99: ifne  103 (offset +4)
        //    102: return
        //    103: aload_1
        //    104: invokevirtual  #620 // dev.angelvisuals.a.dD.bt:()F
        //    107: fstore  4
        //    109: aload_1
        //    110: invokevirtual  #619 // dev.angelvisuals.a.dD.b:()Lnet/minecraft/class_4587;
        //    113: astore  5
        //    115: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //    118: invokevirtual  #650 // net.minecraft.class_310.method_1561:()Lnet/minecraft/class_898;
        //    121: getfield  #559 // net.minecraft.class_898.field_4686:Lnet/minecraft/class_4184;
        //    124: invokevirtual  #660 // net.minecraft.class_4184.method_19326:()Lnet/minecraft/class_243;
        //    127: astore  6
        //    129: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //    132: getfield  #548 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //    135: invokevirtual  #673 // net.minecraft.class_638.method_18456:()Ljava/util/List;
        //    138: invokeinterface  #682 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    143: astore  7
        //    145: aload  7
        //    147: invokeinterface  #680 // java.util.Iterator.hasNext:()Z, count 1
        //    152: ifeq  243 (offset +91)
        //    155: aload  7
        //    157: invokeinterface  #681 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    162: checkcast  #463 // net.minecraft.class_1657
        //    165: astore  8
        //    167: aload_0
        //    168: aload  8
        //    170: invokevirtual  #583 // dev.angelvisuals.a.P.a:(Lnet/minecraft/class_1657;)Z
        //    173: ifne  179 (offset +6)
        //    176: goto  145 (offset -31)
        //    179: aload  8
        //    181: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //    184: getfield  #550 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    187: if_acmpne  208 (offset +21)
        //    190: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //    193: getfield  #549 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //    196: invokevirtual  #651 // net.minecraft.class_315.method_31044:()Lnet/minecraft/class_5498;
        //    199: invokevirtual  #671 // net.minecraft.class_5498.method_31034:()Z
        //    202: ifeq  208 (offset +6)
        //    205: goto  145 (offset -60)
        //    208: iload_2
        //    209: ifeq  224 (offset +15)
        //    212: aload_0
        //    213: aload  8
        //    215: fload  4
        //    217: aload  5
        //    219: aload  6
        //    221: invokevirtual  #586 // dev.angelvisuals.a.P.a:(Lnet/minecraft/class_1657;FLnet/minecraft/class_4587;Lnet/minecraft/class_243;)V
        //    224: iload_3
        //    225: ifeq  240 (offset +15)
        //    228: aload_0
        //    229: aload  8
        //    231: fload  4
        //    233: aload  5
        //    235: aload  6
        //    237: invokevirtual  #599 // dev.angelvisuals.a.P.b:(Lnet/minecraft/class_1657;FLnet/minecraft/class_4587;Lnet/minecraft/class_243;)V
        //    240: goto  145 (offset -95)
        //    243: return
    }

  private void method770(class_1657 arg0, float arg1, class_4587 arg2, class_243 arg3) { // было: a
        float __stk1;
        float __stk2;
        float __stk3;
        float __stk4;
        float __stk5;
        int __stk6;
        if (arg0.method_6128()) {
            return;
        } else {
            if (arg0.method_18376() == class_4050.field_18079) {
                return;
            } else {
                if (!arg0.method_20232()) {
                    class_243 var5 = arg0.method_18798();
                    float var6 = class_3532.method_16439(arg1, arg0.field_6220, arg0.field_6283);
                    float var7 = var6 * 0.01745329238474369f;
                    class_243 var8 = new class_243(((double) -class_3532.method_15374(var7)), 0.0, ((double) class_3532.method_15362(var7)));
                    class_243 var9 = new class_243(var8.field_1350, 0.0, -var8.field_1352);
                    float var10 = ((float) (var5.field_1352 * var8.field_1352 + var5.field_1350 * var8.field_1350));
                    float var11 = ((float) (var5.field_1352 * var9.field_1352 + var5.field_1350 * var9.field_1350));
                    float var12 = ((float) var5.field_1351);
                    boolean var13 = field399.method650();
                    __stk1 = !var13 ? 0.0f : class_3532.method_15363(-var10 * 140.0f - var12 * 48.0f, -24.0f, 26.0f);
                    float var14 = __stk1;
                    __stk2 = !var13 ? 0.0f : class_3532.method_15363(var11 * 90.0f, -10.0f, 10.0f);
                    float var15 = __stk2;
                    __stk3 = !var13 ? 0.0f : class_3532.method_15363(Math.abs(var10) * 0.949999988079071f + Math.abs(var11) * 0.6499999761581421f + Math.abs(var12) * 0.75f, 0.0f, 1.7000000476837158f);
                    float var16 = __stk3;
                    float var17 = (((float) arg0.field_6012) + arg1) * 0.2199999988079071f * 1.600000023841858f + var16 * 0.4000000059604645f;
                    __stk4 = !var13 ? 0.0f : class_3532.method_15374(var17);
                    float var18 = __stk4;
                    __stk5 = !var13 ? 0.0f : class_3532.method_15362(var17);
                    float var19 = __stk5;
                    float var20 = 18.0f + var16 * 5.0f;
                    float var21 = 13.0f + var14 * 0.30000001192092896f + var19 * 4.0f;
                    float var22 = var18 * 25.0f + var15 * 0.75f;
                    class_4050 var23 = arg0.method_18376();
                    boolean var24 = arg0.method_6128();
                    __stk6 = var23 == class_4050.field_18079 ? -1094434501 ^ -1094434502 : !var24 ? -657868484 ^ -657868484 : -1094434501 ^ -1094434502;
                    int var25 = __stk6;
                    if (var25 != 0) {
                        var20 = var20 - 4.0f;
                        var21 = var21 - 6.0f;
                        var22 = var22 * 0.7200000286102295f;
                    }
                    if (arg0.method_5715()) {
                        var20 = var20 - 3.0f;
                        var21 = var21 + 8.0f;
                    }
                    double var26 = class_3532.method_16436(((double) arg1), arg0.field_6014, arg0.method_23317()) - arg3.field_1352;
                    double var28 = class_3532.method_16436(((double) arg1), arg0.field_6036, arg0.method_23318()) - arg3.field_1351;
                    double var30 = class_3532.method_16436(((double) arg1), arg0.field_5969, arg0.method_23321()) - arg3.field_1350;
                    arg2.method_22903();
                    arg2.method_22904(var26, var28, var30);
                    arg2.method_22907(class_7833.field_40716.rotationDegrees(-var6));
                    method776(arg2, arg0, arg1, var23, var24);
                    int var32 = AngelVisuals.getInstance().getThemeManager().method481().method449().method1680();
                    int var33 = cQ.method1718(var32, 0.5f);
                    int var34 = cQ.method1718(var32, 0.4000000059604645f);
                    int var35 = cQ.method1718(var32, 0.800000011920929f);
                    RenderSystem.enableBlend();
                    RenderSystem.disableCull();
                    RenderSystem.enableDepthTest();
                    RenderSystem.depthMask(1603884859 ^ 1603884859);
                    RenderSystem.blendFunc(912637923 ^ 912637153, -348734827 ^ -348735082);
                    RenderSystem.setShader(class_10142.field_53876);
                    float var36 = 1.0f * field400.bp();
                    class_287 var37 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1576);
                    method785(var37, arg2, 1.0f, var20, var21, var22, var36, var33, var34);
                    method785(var37, arg2, -1.0f, var20, var21, var22, var36, var33, var34);
                    class_286.method_43433(var37.method_60800());
                    RenderSystem.lineWidth(1.899999976158142f);
                    class_287 var38 = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
                    method786(var38, arg2, 1.0f, var20, var21, var22, var36, var35);
                    method786(var38, arg2, -1.0f, var20, var21, var22, var36, var35);
                    class_286.method_43433(var38.method_60800());
                    RenderSystem.enableCull();
                    RenderSystem.enableDepthTest();
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableBlend();
                    RenderSystem.depthMask(274473210 ^ 274473211);
                    arg2.method_22909();
                    return;
                } else {
                    return;
                }
            }
        }
    }

  private void method771(class_1657 arg0, float arg1, class_4587 arg2, class_243 arg3) { // было: b
        float __stk1;
        float __stk2;
        float __stk3;
        float __stk4;
        float __stk5;
        int __stk6;
        if (!arg0.method_5805()) {
            return;
        } else {
            if (!arg0.method_5767()) {
                if (arg0.method_6128()) {
                    return;
                } else {
                    if (arg0.method_18376() == class_4050.field_18079) {
                        return;
                    } else {
                        if (!arg0.method_20232()) {
                            double var5 = class_3532.method_16436(((double) arg1), arg0.field_6014, arg0.method_23317()) - arg3.field_1352;
                            double var7 = class_3532.method_16436(((double) arg1), arg0.field_6036, arg0.method_23318()) - arg3.field_1351;
                            double var9 = class_3532.method_16436(((double) arg1), arg0.field_5969, arg0.method_23321()) - arg3.field_1350;
                            float var11 = method777(arg0, arg1);
                            class_243 var12 = arg0.method_18798();
                            float var13 = var11 * 0.01745329238474369f;
                            class_243 var14 = new class_243(((double) -class_3532.method_15374(var13)), 0.0, ((double) class_3532.method_15362(var13)));
                            class_243 var15 = new class_243(var14.field_1350, 0.0, -var14.field_1352);
                            float var16 = ((float) (var12.field_1352 * var14.field_1352 + var12.field_1350 * var14.field_1350));
                            float var17 = ((float) (var12.field_1352 * var15.field_1352 + var12.field_1350 * var15.field_1350));
                            float var18 = ((float) var12.field_1351);
                            boolean var19 = field401.method650();
                            __stk1 = !var19 ? 0.0f : class_3532.method_15363(-var16 * 140.0f - var18 * 48.0f, -24.0f, 26.0f);
                            float var20 = __stk1;
                            __stk2 = !var19 ? 0.0f : class_3532.method_15363(var17 * 90.0f, -10.0f, 10.0f);
                            float var21 = __stk2;
                            __stk3 = !var19 ? 0.0f : class_3532.method_15363(Math.abs(var16) * 0.949999988079071f + Math.abs(var17) * 0.6499999761581421f + Math.abs(var18) * 0.75f, 0.0f, 1.7000000476837158f);
                            float var22 = __stk3;
                            float var23 = (((float) arg0.field_6012) + arg1) * 0.2199999988079071f * 1.600000023841858f + var22 * 0.4000000059604645f;
                            __stk4 = !var19 ? 0.0f : class_3532.method_15374(var23);
                            float var24 = __stk4;
                            __stk5 = !var19 ? 0.0f : class_3532.method_15362(var23);
                            float var25 = __stk5;
                            float var26 = 18.0f + var22 * 5.0f;
                            float var27 = 13.0f + var20 * 0.30000001192092896f + var25 * 4.0f;
                            float var28 = var24 * 25.0f + var21 * 0.75f;
                            class_4050 var29 = arg0.method_18376();
                            boolean var30 = arg0.method_6128();
                            __stk6 = var29 == class_4050.field_18079 ? 1014345949 ^ 1014345948 : !var30 ? 1574144956 ^ 1574144956 : 1014345949 ^ 1014345948;
                            int var31 = __stk6;
                            if (var31 != 0) {
                                var26 = var26 - 4.0f;
                                var27 = var27 - 6.0f;
                                var28 = var28 * 0.7200000286102295f;
                            }
                            if (arg0.method_5715()) {
                                var26 = var26 - 3.0f;
                                var27 = var27 + 8.0f;
                            }
                            ClassA99_ClassA100 var32 = method778(arg0, arg1, var29);
                            float var33 = var26 * var32.bL;
                            float var34 = var32.bM * field402.bp();
                            float var35 = var32.bT + var27 * 0.18000000715255737f;
                            float var36 = var32.bS + var28 * 0.20000000298023224f;
                            int var37 = AngelVisuals.getInstance().getThemeManager().method481().method449().method1680();
                            int var38 = cQ.method1718(var37, 0.8500000238418579f);
                            int var39 = cQ.method1718(var37, 0.2199999988079071f);
                            int var40 = cQ.method1718(var37, 0.25999999046325684f);
                            int var41 = cQ.method1718(var37, 0.6200000047683716f);
                            int var42 = cQ.method1718(var37, 0.20000000298023224f);
                            RenderSystem.enableBlend();
                            RenderSystem.disableCull();
                            RenderSystem.enableDepthTest();
                            RenderSystem.depthMask(1781783596 ^ 1781783596);
                            RenderSystem.blendFunc(1116569796 ^ 1116570566, -1089248718 ^ -1089248975);
                            RenderSystem.setShader(class_10142.field_53876);
                            arg2.method_22903();
                            arg2.method_22904(var5, var7, var9);
                            arg2.method_22907(class_7833.field_40716.rotationDegrees(180.0f - var11));
                            if (var32.bF != 0.0f) {
                                arg2.method_46416(0.0f, var32.bF, var32.bG);
                            } else {
                                if (var32.bG != 0.0f) {
                                    arg2.method_46416(0.0f, var32.bF, var32.bG);
                                }
                            }
                            if (var32.bJ != 0.0f) {
                                arg2.method_22907(class_7833.field_40714.rotationDegrees(var32.bJ));
                            }
                            if (var32.bK != 0.0f) {
                                arg2.method_22907(class_7833.field_40718.rotationDegrees(var32.bK));
                            }
                            arg2.method_46416(0.0f, var32.bH, var32.bI);
                            arg2.method_22905(var34, var34, var34);
                            method779(arg2, -1.0f, var33, var35, var36, var38, var39, var40, var41, var42, var32);
                            method779(arg2, 1.0f, var33, var35, var36, var38, var39, var40, var41, var42, var32);
                            arg2.method_22909();
                            RenderSystem.enableCull();
                            RenderSystem.enableDepthTest();
                            RenderSystem.defaultBlendFunc();
                            RenderSystem.disableBlend();
                            RenderSystem.depthMask(1405851281 ^ 1405851280);
                            return;
                        } else {
                            return;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

  private void method772(dD arg0) { // было: c
        if (mc.field_1724 == null) {
            return;
        }
        if (mc.field_1687 == null) {
            return;
        }
        int var16;
        if (!mc.field_1690.method_31044().method_31034()) {
            float var2 = arg0.bt();
            class_243 var3 = mc.method_1561().field_4686.method_19326();
            double var4 = class_3532.method_16436(((double) var2), mc.field_1724.field_6014, mc.field_1724.method_23317());
            double var6 = class_3532.method_16436(((double) var2), mc.field_1724.field_6036, mc.field_1724.method_23318()) + ((double) mc.field_1724.method_17682()) + 0.1;
            double var8 = class_3532.method_16436(((double) var2), mc.field_1724.field_5969, mc.field_1724.method_23321());
            int var10 = AngelVisuals.getInstance().getThemeManager().method481().method449().method1680();
            long var11 = System.currentTimeMillis();
            double var13 = 0.0029670597283903604;
            RenderSystem.enableBlend();
            RenderSystem.disableCull();
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(995791623 ^ 995791623);
            RenderSystem.blendFunc(579329860 ^ 579329094, -1841279219 ^ -1841279220);
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.setShaderTexture(988143507 ^ 988143507, AngelVisuals.id(Decryptor.method1945(XorDecoder.method1946("íg­0Ðµ\u001dþª0ßÖl³­Ýi", -1238703238 ^ -490063386))));
            class_4587 var15 = arg0.method324();
            var16 = -1223976453 ^ -1223976453;
        } else {
            return;
        }
        while (var16 < (1780614214 ^ 1780614212)) {
            double var17 = var13 * ((double) var11) + ((double) var16) * 3.141592653589793;
            int var19 = 47745460 ^ 47745460;
            while (var19 < (-118866371 ^ -118866388)) {
                double var20 = var17 - ((double) var19) * 0.11;
                double var22 = Math.cos(var20) * 0.44999998807907104;
                double var24 = Math.sin(var20) * 0.44999998807907104;
                float var26 = ((float) var19) / ((float) Math.max(-116132842 ^ -116132841, 1423298777 ^ 1423298761));
                float var27 = 0.23000000417232513f * (1.0f - var26 * 0.699999988079071f);
                int var28 = class_3532.method_15340((1251655900 ^ 1251655715) - var19 * (-1258862062 ^ -1258862053), -1833761111 ^ -1833761111, -712537593 ^ -712537352);
                int var29 = cQ.method1718(var10, ((float) var28) / 255.0f);
                method774(var15, mc.method_1561().field_4686.method_19330(), mc.method_1561().field_4686.method_19329(), var4 - var3.field_1352 + var22, var6 - var3.field_1351, var8 - var3.field_1350 + var24, var27, var29);
                ++var19;
                continue;
            }
            ++var16;
            continue;
        }
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(2135953942 ^ 2135953943);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

  public void method773(class_4587 arg0, class_4597 arg1, class_1657 arg2, class_3882 arg3) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #597 // dev.angelvisuals.a.P.ah:()Z
        //      4: ifeq  31 (offset +27)
        //      7: aload_0
        //      8: getfield  #501 // dev.angelvisuals.a.P.a:Ldev/angelvisuals/a/I;
        //     11: ldc_w  #418 // 'U;á¾t\x03Û\x99^\x04û\x84d\x18É\x9fe\x02é§*Jì¾\\\x1bï\x93d8Å\x91q\x0fò±J\x18á¡tTåÏ'
        //     14: ldc  #111 // -286060974
        //     16: ldc  #172 // 475754305
        //     18: ixor
        //     19: invokestatic  #561 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     22: invokestatic  #560 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     25: invokevirtual  #579 // dev.angelvisuals.a.I.d:(Ljava/lang/String;)Z
        //     28: ifne  32 (offset +4)
        //     31: return
        //     32: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //     35: getfield  #550 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     38: ifnull  50 (offset +12)
        //     41: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //     44: getfield  #548 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     47: ifnonnull  51 (offset +4)
        //     50: return
        //     51: aload_0
        //     52: aload_3
        //     53: invokevirtual  #583 // dev.angelvisuals.a.P.a:(Lnet/minecraft/class_1657;)Z
        //     56: ifne  60 (offset +4)
        //     59: return
        //     60: aload_3
        //     61: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //     64: getfield  #550 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     67: if_acmpne  86 (offset +19)
        //     70: getstatic  #510 // dev.angelvisuals.a.P.mc:Lnet/minecraft/class_310;
        //     73: getfield  #549 // net.minecraft.class_310.field_1690:Lnet/minecraft/class_315;
        //     76: invokevirtual  #651 // net.minecraft.class_315.method_31044:()Lnet/minecraft/class_5498;
        //     79: invokevirtual  #671 // net.minecraft.class_5498.method_31034:()Z
        //     82: ifeq  86 (offset +4)
        //     85: return
        //     86: aload_3
        //     87: invokevirtual  #640 // net.minecraft.class_1657.method_5829:()Lnet/minecraft/class_238;
        //     90: getfield  #536 // net.minecraft.class_238.field_1320:D
        //     93: aload_3
        //     94: invokevirtual  #640 // net.minecraft.class_1657.method_5829:()Lnet/minecraft/class_238;
        //     97: getfield  #537 // net.minecraft.class_238.field_1323:D
        //    100: dsub
        //    101: dstore  5
        //    103: aload_3
        //    104: getstatic  #529 // net.minecraft.class_1304.field_6169:Lnet/minecraft/class_1304;
        //    107: invokevirtual  #641 // net.minecraft.class_1657.method_6118:(Lnet/minecraft/class_1304;)Lnet/minecraft/class_1799;
        //    110: invokevirtual  #643 // net.minecraft.class_1799.method_7960:()Z
        //    113: ifeq  122 (offset +9)
        //    116: ldc_w  #344 // 0.41499999165534973f
        //    119: goto  125 (offset +6)
        //    122: ldc_w  #347 // 0.47999998927116394f
        //    125: fstore  7
        //    127: aload_1
        //    128: invokevirtual  #663 // net.minecraft.class_4587.method_22903:()V
        //    131: aload  4
        //    133: invokeinterface  #684 // net.minecraft.class_3882.method_2838:()Lnet/minecraft/class_630;, count 1
        //    138: aload_1
        //    139: invokevirtual  #672 // net.minecraft.class_630.method_22703:(Lnet/minecraft/class_4587;)V
        //    142: invokestatic  #567 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //    145: invokestatic  #569 // com.mojang.blaze3d.systems.RenderSystem.enableDepthTest:()V
        //    148: invokestatic  #566 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //    151: invokestatic  #563 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //    154: getstatic  #527 // net.minecraft.class_10142.field_53876:Lnet/minecraft/class_10156;
        //    157: invokestatic  #571 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //    160: pop
        //    161: fconst_1
        //    162: fconst_1
        //    163: fconst_1
        //    164: fconst_1
        //    165: invokestatic  #572 // com.mojang.blaze3d.systems.RenderSystem.setShaderColor:(FFFF)V
        //    168: fconst_2
        //    169: invokestatic  #570 // com.mojang.blaze3d.systems.RenderSystem.lineWidth:(F)V
        //    172: ldc  #118 // -122677538
        //    174: ldc  #119 // -122675714
        //    176: ixor
        //    177: invokestatic  #679 // org.lwjgl.opengl.GL11.glEnable:(I)V
        //    180: aload_1
        //    181: fconst_0
        //    182: fload  7
        //    184: fneg
        //    185: fconst_0
        //    186: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    189: aload_1
        //    190: getstatic  #557 // net.minecraft.class_7833.field_40717:Lnet/minecraft/class_7833;
        //    193: ldc_w  #406 // 180.0f
        //    196: invokeinterface  #688 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //    201: invokevirtual  #666 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //    204: aload_1
        //    205: getstatic  #556 // net.minecraft.class_7833.field_40716:Lnet/minecraft/class_7833;
        //    208: ldc_w  #403 // 90.0f
        //    211: invokeinterface  #688 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //    216: invokevirtual  #666 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //    219: aload_1
        //    220: invokevirtual  #668 // net.minecraft.class_4587.method_23760:()Lnet/minecraft/class_4587$class_4665;
        //    223: invokevirtual  #670 // net.minecraft.class_4587$class_4665.method_23761:()Lorg/joml/Matrix4f;
        //    226: astore  8
        //    228: invokestatic  #648 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //    231: astore  9
        //    233: aload  9
        //    235: getstatic  #545 // net.minecraft.class_293$class_5596.field_27380:Lnet/minecraft/class_293$class_5596;
        //    238: getstatic  #542 // net.minecraft.class_290.field_1576:Lnet/minecraft/class_293;
        //    241: invokevirtual  #649 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    244: astore  10
        //    246: fconst_0
        //    247: fstore  11
        //    249: invokestatic  #575 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    252: invokevirtual  #576 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //    255: invokevirtual  #618 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //    258: invokevirtual  #612 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //    261: invokevirtual  #613 // dev.angelvisuals.a.bp.E:()I
        //    264: istore  12
        //    266: iload  12
        //    268: ldc_w  #348 // 0.5f
        //    271: invokestatic  #617 // dev.angelvisuals.a.cQ.e:(IF)I
        //    274: istore  13
        //    276: ldc  #215 // 1019445553
        //    278: ldc  #215 // 1019445553
        //    280: ixor
        //    281: istore  14
        //    283: iload  14
        //    285: ldc  #184 // 581387903
        //    287: ldc  #185 // 581387979
        //    289: ixor
        //    290: if_icmpgt  373 (offset +83)
        //    293: iload  14
        //    295: i2f
        //    296: ldc_w  #322 // 0.03490658476948738f
        //    299: fmul
        //    300: fstore  15
        //    302: fload  15
        //    304: invokestatic  #655 // net.minecraft.class_3532.method_15374:(F)F
        //    307: f2d
        //    308: dload  5
        //    310: dmul
        //    311: d2f
        //    312: fstore  16
        //    314: fload  15
        //    316: invokestatic  #653 // net.minecraft.class_3532.method_15362:(F)F
        //    319: f2d
        //    320: dload  5
        //    322: dmul
        //    323: d2f
        //    324: fstore  17
        //    326: aload  10
        //    328: aload  8
        //    330: fload  16
        //    332: fload  11
        //    334: fload  17
        //    336: invokevirtual  #646 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    339: iload  13
        //    341: invokeinterface  #687 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    346: pop
        //    347: aload  10
        //    349: aload  8
        //    351: fconst_0
        //    352: ldc_w  #340 // 0.30000001192092896f
        //    355: fconst_0
        //    356: invokevirtual  #646 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    359: iload  12
        //    361: invokeinterface  #687 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    366: pop
        //    367: iinc  14, 1
        //    370: goto  283 (offset -87)
        //    373: aload  10
        //    375: invokevirtual  #647 // net.minecraft.class_287.method_60800:()Lnet/minecraft/class_9801;
        //    378: invokestatic  #645 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    381: ldc  #163 // 389678129
        //    383: ldc  #163 // 389678129
        //    385: ixor
        //    386: invokestatic  #564 // com.mojang.blaze3d.systems.RenderSystem.depthMask:(Z)V
        //    389: aload  9
        //    391: getstatic  #543 // net.minecraft.class_293$class_5596.field_27378:Lnet/minecraft/class_293$class_5596;
        //    394: getstatic  #542 // net.minecraft.class_290.field_1576:Lnet/minecraft/class_293;
        //    397: invokevirtual  #649 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    400: astore  10
        //    402: fconst_0
        //    403: fstore  14
        //    405: fconst_0
        //    406: fstore  15
        //    408: ldc  #233 // 1295307844
        //    410: ldc  #233 // 1295307844
        //    412: ixor
        //    413: istore  16
        //    415: iload  12
        //    417: ldc_w  #357 // 0.699999988079071f
        //    420: invokestatic  #617 // dev.angelvisuals.a.cQ.e:(IF)I
        //    423: istore  17
        //    425: ldc  #218 // 1073574053
        //    427: ldc  #218 // 1073574053
        //    429: ixor
        //    430: istore  18
        //    432: iload  18
        //    434: ldc  #114 // -192680580
        //    436: ldc  #115 // -192680504
        //    438: ixor
        //    439: if_icmpgt  524 (offset +85)
        //    442: iload  18
        //    444: i2f
        //    445: ldc_w  #322 // 0.03490658476948738f
        //    448: fmul
        //    449: fstore  19
        //    451: fload  19
        //    453: invokestatic  #655 // net.minecraft.class_3532.method_15374:(F)F
        //    456: f2d
        //    457: dload  5
        //    459: dmul
        //    460: d2f
        //    461: fstore  20
        //    463: fload  19
        //    465: invokestatic  #653 // net.minecraft.class_3532.method_15362:(F)F
        //    468: f2d
        //    469: dload  5
        //    471: dmul
        //    472: d2f
        //    473: fstore  21
        //    475: aload  10
        //    477: aload  8
        //    479: fload  20
        //    481: fload  11
        //    483: fload  21
        //    485: invokevirtual  #646 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    488: iload  17
        //    490: invokeinterface  #687 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    495: pop
        //    496: iload  16
        //    498: ifne  518 (offset +20)
        //    501: fload  20
        //    503: fstore  14
        //    505: fload  21
        //    507: fstore  15
        //    509: ldc_w  #282 // 2079271287
        //    512: ldc_w  #281 // 2079271286
        //    515: ixor
        //    516: istore  16
        //    518: iinc  18, 1
        //    521: goto  432 (offset -89)
        //    524: iload  16
        //    526: ifeq  550 (offset +24)
        //    529: aload  10
        //    531: aload  8
        //    533: fload  14
        //    535: fload  11
        //    537: fload  15
        //    539: invokevirtual  #646 // net.minecraft.class_287.method_22918:(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/class_4588;
        //    542: iload  17
        //    544: invokeinterface  #687 // net.minecraft.class_4588.method_39415:(I)Lnet/minecraft/class_4588;, count 2
        //    549: pop
        //    550: aload  10
        //    552: invokevirtual  #647 // net.minecraft.class_287.method_60800:()Lnet/minecraft/class_9801;
        //    555: invokestatic  #645 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    558: ldc  #130 // -8657554
        //    560: ldc  #131 // -8657553
        //    562: ixor
        //    563: invokestatic  #564 // com.mojang.blaze3d.systems.RenderSystem.depthMask:(Z)V
        //    566: invokestatic  #568 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    569: invokestatic  #563 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //    572: fconst_1
        //    573: fconst_1
        //    574: fconst_1
        //    575: fconst_1
        //    576: invokestatic  #572 // com.mojang.blaze3d.systems.RenderSystem.setShaderColor:(FFFF)V
        //    579: ldc  #165 // 395288148
        //    581: ldc  #164 // 395285876
        //    583: ixor
        //    584: invokestatic  #678 // org.lwjgl.opengl.GL11.glDisable:(I)V
        //    587: aload_1
        //    588: invokevirtual  #667 // net.minecraft.class_4587.method_22909:()V
        //    591: return
    }

  private void method774(class_4587 arg0, float arg1, float arg2, double arg3, double arg4, double arg5, float arg6, int arg7) { // было: a
        int var12 = arg7 >> (192556914 ^ 192556906) & (1791895867 ^ 1791896004);
        if (var12 > 0) {
            int var13 = arg7 >> (-1371611825 ^ -1371611809) & (856827657 ^ 856827894);
            int var14 = arg7 >> (-1709661804 ^ -1709661796) & (-1066849906 ^ -1066849935);
            int var15 = arg7 & (-1899100995 ^ -1899101118);
            float var16 = arg6 * 0.5f;
            arg0.method_22903();
            arg0.method_22904(arg3, arg4, arg5);
            arg0.method_22907(class_7833.field_40716.rotationDegrees(-arg1));
            arg0.method_22907(class_7833.field_40714.rotationDegrees(arg2));
            Matrix4f var17 = arg0.method_23760().method_23761();
            class_287 var18 = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            var18.method_22918(var17, -var16, -var16, 0.0f).method_22913(0.0f, 1.0f).method_1336(var13, var14, var15, var12);
            var18.method_22918(var17, -var16, var16, 0.0f).method_22913(0.0f, 0.0f).method_1336(var13, var14, var15, var12);
            var18.method_22918(var17, var16, var16, 0.0f).method_22913(1.0f, 0.0f).method_1336(var13, var14, var15, var12);
            var18.method_22918(var17, var16, -var16, 0.0f).method_22913(1.0f, 1.0f).method_1336(var13, var14, var15, var12);
            class_286.method_43433(var18.method_60800());
            arg0.method_22909();
            return;
        } else {
            return;
        }
    }

  private boolean method775(class_1657 arg0) { // было: a
        if (mc.field_1724 != null) {
            if (arg0 != mc.field_1724) {
                return AngelVisuals.getInstance().getFriendManager().method345(arg0.method_5477().getString());
            } else {
                return 477373668 ^ 477373669;
            }
        } else {
            return 1092410200 ^ 1092410200;
        }
    }

  private void method776(class_4587 arg0, class_1657 arg1, float arg2, class_4050 arg3, boolean arg4) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload  5
        //      2: ifeq  65 (offset +63)
        //      5: aload_2
        //      6: fload_3
        //      7: invokevirtual  #636 // net.minecraft.class_1657.method_5695:(F)F
        //     10: fstore  6
        //     12: fload  6
        //     14: ldc_w  #290 // -65.0f
        //     17: ldc_w  #402 // 65.0f
        //     20: invokestatic  #654 // net.minecraft.class_3532.method_15363:(FFF)F
        //     23: fstore  7
        //     25: aload_1
        //     26: fconst_0
        //     27: ldc_w  #340 // 0.30000001192092896f
        //     30: fconst_0
        //     31: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //     34: aload_1
        //     35: getstatic  #555 // net.minecraft.class_7833.field_40714:Lnet/minecraft/class_7833;
        //     38: ldc_w  #403 // 90.0f
        //     41: fload  7
        //     43: fadd
        //     44: fneg
        //     45: invokeinterface  #688 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //     50: invokevirtual  #666 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //     53: aload_1
        //     54: fconst_0
        //     55: ldc_w  #310 // -0.15000000596046448f
        //     58: ldc_w  #329 // 0.11999999731779099f
        //     61: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //     64: return
        //     65: aload  4
        //     67: getstatic  #551 // net.minecraft.class_4050.field_18079:Lnet/minecraft/class_4050;
        //     70: if_acmpne  133 (offset +63)
        //     73: aload_2
        //     74: fload_3
        //     75: invokevirtual  #636 // net.minecraft.class_1657.method_5695:(F)F
        //     78: fstore  6
        //     80: fload  6
        //     82: ldc_w  #290 // -65.0f
        //     85: ldc_w  #402 // 65.0f
        //     88: invokestatic  #654 // net.minecraft.class_3532.method_15363:(FFF)F
        //     91: fstore  7
        //     93: aload_1
        //     94: fconst_0
        //     95: ldc_w  #340 // 0.30000001192092896f
        //     98: fconst_0
        //     99: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    102: aload_1
        //    103: getstatic  #555 // net.minecraft.class_7833.field_40714:Lnet/minecraft/class_7833;
        //    106: ldc_w  #403 // 90.0f
        //    109: fload  7
        //    111: fadd
        //    112: fneg
        //    113: invokeinterface  #688 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //    118: invokevirtual  #666 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //    121: aload_1
        //    122: fconst_0
        //    123: ldc_w  #310 // -0.15000000596046448f
        //    126: ldc_w  #329 // 0.11999999731779099f
        //    129: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    132: return
        //    133: aload_2
        //    134: invokevirtual  #637 // net.minecraft.class_1657.method_5715:()Z
        //    137: ifeq  176 (offset +39)
        //    140: aload_1
        //    141: fconst_0
        //    142: ldc_w  #377 // 1.149999976158142f
        //    145: fconst_0
        //    146: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    149: aload_1
        //    150: getstatic  #555 // net.minecraft.class_7833.field_40714:Lnet/minecraft/class_7833;
        //    153: ldc_w  #398 // 24.0f
        //    156: invokeinterface  #688 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //    161: invokevirtual  #666 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //    164: aload_1
        //    165: fconst_0
        //    166: fconst_0
        //    167: ldc_w  #327 // 0.07999999821186066f
        //    170: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    173: goto  187 (offset +14)
        //    176: aload_1
        //    177: fconst_0
        //    178: ldc_w  #381 // 1.2999999523162842f
        //    181: ldc_w  #327 // 0.07999999821186066f
        //    184: invokevirtual  #669 // net.minecraft.class_4587.method_46416:(FFF)V
        //    187: return
    }

  private float method777(class_1657 arg0, float arg1) { // было: a
        float var3 = class_3532.method_17821(arg1, arg0.field_6220, arg0.field_6283);
        if (arg0 == mc.field_1724) {
            if (field403) {
                float var4 = class_3532.method_15393(var3 - ac);
                ac = ac + class_3532.method_15363(var4, -14.0f, 14.0f);
                return ac;
            } else {
                ac = var3;
                field403 = 1516230946 ^ 1516230947;
                return ac;
            }
        } else {
            return var3;
        }
    }

  private ClassA99_ClassA100 method778(class_1657 arg0, float arg1, class_4050 arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: fload_2
        //      2: invokevirtual  #636 // net.minecraft.class_1657.method_5695:(F)F
        //      5: fstore  4
        //      7: aload_1
        //      8: invokevirtual  #642 // net.minecraft.class_1657.method_6128:()Z
        //     11: ifeq  79 (offset +68)
        //     14: fload  4
        //     16: ldc_w  #290 // -65.0f
        //     19: ldc_w  #402 // 65.0f
        //     22: invokestatic  #654 // net.minecraft.class_3532.method_15363:(FFF)F
        //     25: fstore  5
        //     27: new  #440 // dev.angelvisuals.a.P$a
        //     30: dup
        //     31: ldc_w  #378 // 1.1799999475479126f
        //     34: ldc_w  #328 // 0.10000000149011612f
        //     37: fconst_0
        //     38: fconst_0
        //     39: ldc_w  #403 // 90.0f
        //     42: fload  5
        //     44: fadd
        //     45: fneg
        //     46: fconst_0
        //     47: ldc_w  #360 // 0.7599999904632568f
        //     50: ldc_w  #369 // 0.9200000166893005f
        //     53: ldc_w  #328 // 0.10000000149011612f
        //     56: ldc_w  #352 // 0.5799999833106995f
        //     59: ldc_w  #325 // 0.05000000074505806f
        //     62: fconst_0
        //     63: ldc_w  #326 // 0.05999999865889549f
        //     66: ldc_w  #296 // -5.0f
        //     69: ldc_w  #299 // -2.0f
        //     72: ldc_w  #330 // 0.12999999523162842f
        //     75: invokespecial  #606 // dev.angelvisuals.a.P$a.<init>:(FFFFFFFFFFFFFFFF)V
        //     78: areturn
        //     79: aload_3
        //     80: getstatic  #551 // net.minecraft.class_4050.field_18079:Lnet/minecraft/class_4050;
        //     83: if_acmpeq  93 (offset +10)
        //     86: aload_1
        //     87: invokevirtual  #631 // net.minecraft.class_1657.method_20232:()Z
        //     90: ifeq  196 (offset +106)
        //     93: fload  4
        //     95: ldc_w  #290 // -65.0f
        //     98: ldc_w  #402 // 65.0f
        //    101: invokestatic  #654 // net.minecraft.class_3532.method_15363:(FFF)F
        //    104: fstore  5
        //    106: aload_1
        //    107: invokevirtual  #631 // net.minecraft.class_1657.method_20232:()Z
        //    110: ifeq  119 (offset +9)
        //    113: ldc_w  #375 // 1.100000023841858f
        //    116: goto  122 (offset +6)
        //    119: ldc_w  #378 // 1.1799999475479126f
        //    122: fstore  6
        //    124: aload_1
        //    125: invokevirtual  #631 // net.minecraft.class_1657.method_20232:()Z
        //    128: ifeq  137 (offset +9)
        //    131: ldc_w  #334 // 0.18000000715255737f
        //    134: goto  140 (offset +6)
        //    137: ldc_w  #329 // 0.11999999731779099f
        //    140: fstore  7
        //    142: new  #440 // dev.angelvisuals.a.P$a
        //    145: dup
        //    146: fload  6
        //    148: fload  7
        //    150: ldc_w  #334 // 0.18000000715255737f
        //    153: ldc_w  #347 // 0.47999998927116394f
        //    156: ldc_w  #403 // 90.0f
        //    159: fload  5
        //    161: fadd
        //    162: fneg
        //    163: fconst_0
        //    164: ldc_w  #364 // 0.8399999737739563f
        //    167: ldc_w  #371 // 0.9599999785423279f
        //    170: ldc_w  #329 // 0.11999999731779099f
        //    173: ldc_w  #357 // 0.699999988079071f
        //    176: ldc_w  #321 // 0.029999999329447746f
        //    179: fconst_0
        //    180: ldc_w  #318 // 0.009999999776482582f
        //    183: ldc_w  #295 // -7.0f
        //    186: ldc_w  #298 // -3.0f
        //    189: ldc_w  #333 // 0.1599999964237213f
        //    192: invokespecial  #606 // dev.angelvisuals.a.P$a.<init>:(FFFFFFFFFFFFFFFF)V
        //    195: areturn
        //    196: aload_1
        //    197: invokevirtual  #637 // net.minecraft.class_1657.method_5715:()Z
        //    200: ifeq  247 (offset +47)
        //    203: new  #440 // dev.angelvisuals.a.P$a
        //    206: dup
        //    207: fconst_0
        //    208: fconst_0
        //    209: ldc_w  #371 // 0.9599999785423279f
        //    212: ldc_w  #328 // 0.10000000149011612f
        //    215: ldc_w  #397 // 18.0f
        //    218: fconst_0
        //    219: fconst_1
        //    220: fconst_1
        //    221: ldc_w  #334 // 0.18000000715255737f
        //    224: ldc_w  #390 // 4.5f
        //    227: ldc_w  #326 // 0.05999999865889549f
        //    230: fconst_0
        //    231: ldc_w  #320 // 0.019999999552965164f
        //    234: ldc_w  #293 // -11.0f
        //    237: ldc_w  #297 // -4.0f
        //    240: ldc_w  #329 // 0.11999999731779099f
        //    243: invokespecial  #606 // dev.angelvisuals.a.P$a.<init>:(FFFFFFFFFFFFFFFF)V
        //    246: areturn
        //    247: new  #440 // dev.angelvisuals.a.P$a
        //    250: dup
        //    251: fconst_0
        //    252: fconst_0
        //    253: ldc_w  #378 // 1.1799999475479126f
        //    256: ldc_w  #328 // 0.10000000149011612f
        //    259: fconst_0
        //    260: fconst_0
        //    261: fconst_1
        //    262: fconst_1
        //    263: ldc_w  #334 // 0.18000000715255737f
        //    266: ldc_w  #390 // 4.5f
        //    269: ldc_w  #326 // 0.05999999865889549f
        //    272: fconst_0
        //    273: ldc_w  #320 // 0.019999999552965164f
        //    276: ldc_w  #293 // -11.0f
        //    279: ldc_w  #297 // -4.0f
        //    282: ldc_w  #329 // 0.11999999731779099f
        //    285: invokespecial  #606 // dev.angelvisuals.a.P$a.<init>:(FFFFFFFFFFFFFFFF)V
        //    288: areturn
    }

  private void method779(class_4587 arg0, float arg1, float arg2, float arg3, float arg4, int arg5, int arg6, int arg7, int arg8, int arg9, ClassA99_ClassA100 arg10) { // было: a
        arg0.method_22903();
        arg0.method_46416(arg1 * arg10.bP, arg10.bQ, arg10.bR);
        arg0.method_22907(class_7833.field_40716.rotationDegrees(arg1 * arg2));
        arg0.method_22907(class_7833.field_40718.rotationDegrees(arg1 * arg4));
        arg0.method_22907(class_7833.field_40714.rotationDegrees(arg3));
        RenderSystem.blendFunc(1133522485 ^ 1133522231, 243872106 ^ 243872107);
        method780(arg0, arg1, 1.2200000286102295f, arg6, cQ.method1718(arg6, 0.0f));
        method780(arg0, arg1, 0.8399999737739563f, arg7, cQ.method1718(arg7, 0.0f));
        RenderSystem.blendFunc(505810602 ^ 505810344, -1067836214 ^ -1067835447);
        method780(arg0, arg1, 1.0f, arg5, cQ.method1718(arg5, 0.03921568766236305f));
        RenderSystem.blendFunc(1214138417 ^ 1214139187, -345655367 ^ -345655368);
        method781(arg0, arg1, 1.0f, arg8);
        method782(arg0, arg1, 0.9599999785423279f, arg9);
        arg0.method_22909();
    }

  private void method780(class_4587 arg0, float arg1, float arg2, int arg3, int arg4) { // было: a
        Matrix4f var6 = arg0.method_23760().method_23761();
        class_287 var7 = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
        int var8 = 1697455527 ^ 1697455527;
        while (var8 < field397.length) {
            Object var9 = field397[var8];
            Object var10 = field397[(var8 + (61796303 ^ 61796302)) % field397.length];
            method784(var7, var6, 0.0f, 0.0f, 0.0f, arg3);
            method784(var7, var6, arg1 * var9.cn * arg2, var9.co * arg2, 0.0f, method783(arg4, var9.cp));
            method784(var7, var6, arg1 * var10.cn * arg2, var10.co * arg2, 0.0f, method783(arg4, var10.cp));
            ++var8;
            continue;
        }
        class_286.method_43433(var7.method_60800());
    }

  private void method781(class_4587 arg0, float arg1, float arg2, int arg3) { // было: a
        Matrix4f var5 = arg0.method_23760().method_23761();
        class_287 var6 = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
        RenderSystem.lineWidth(1.350000023841858f);
        GL11.glEnable(-1274479864 ^ -1274482648);
        int var7 = 1849131479 ^ 1849131479;
        while (var7 < field397.length) {
            Object var8 = field397[var7];
            Object var9 = field397[(var7 + (-2044325431 ^ -2044325432)) % field397.length];
            method791(var6, var5, arg1 * var8.cn * arg2, var8.co * arg2, 0.0f, arg1 * var9.cn * arg2, var9.co * arg2, 0.0f, arg3);
            ++var7;
            continue;
        }
        class_286.method_43433(var6.method_60800());
        GL11.glDisable(1401335980 ^ 1401338764);
    }

  private void method782(class_4587 arg0, float arg1, float arg2, int arg3) { // было: b
        Matrix4f var5 = arg0.method_23760().method_23761();
        class_287 var6 = class_289.method_1348().method_60827(class_5596.field_29344, class_290.field_1576);
        int[] __obj1 = new int[848566886 ^ 848566883];
        __obj1[-903318272 ^ -903318272] = 844679198 ^ 844679196;
        __obj1[-1941160705 ^ -1941160706] = -1650985935 ^ -1650985931;
        __obj1[2140551392 ^ 2140551394] = -1402781157 ^ -1402781156;
        __obj1[-1758100295 ^ -1758100294] = 771451356 ^ 771451349;
        __obj1[291430839 ^ 291430835] = -48494786 ^ -48494795;
        int[] var7 = __obj1;
        RenderSystem.lineWidth(0.8999999761581421f);
        int[] var8 = var7;
        int var9 = var8.length;
        int var10 = -1663400931 ^ -1663400931;
        while (var10 < var9) {
            int var11 = var8[var10];
            Object var12 = field397[var11];
            method784(var6, var5, 0.0f, 0.0f, 0.0f, cQ.method1718(arg3, 0.75f));
            method784(var6, var5, arg1 * var12.cn * arg2, var12.co * arg2, 0.0f, method783(arg3, var12.cp));
            ++var10;
            continue;
        }
        class_286.method_43433(var6.method_60800());
    }

  private int method783(int arg0, float arg1) { // было: a
        int var3 = arg0 >> (-970475108 ^ -970475132) & (159754103 ^ 159754120);
        return cQ.method1718(arg0, ((float) var3) * arg1 / 255.0f);
    }

  private void method784(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, int arg5) { // было: a
        arg0.method_22918(arg1, arg2, arg3, arg4).method_39415(arg5);
    }

  private void method785(class_287 arg0, class_4587 arg1, float arg2, float arg3, float arg4, float arg5, float arg6, int arg7, int arg8) { // было: a
        float var10 = 0.11999999731779099f * arg6;
        float var11 = 1.5199999809265137f * arg6;
        float var12 = 0.6399999856948853f * arg6;
        float var13 = 1.1399999856948853f * arg6;
        float var14 = 0.38999998569488525f * arg6;
        arg1.method_22903();
        arg1.method_46416(0.15000000596046448f * arg2, 0.0f, -0.17000000178813934f);
        arg1.method_22907(class_7833.field_40716.rotationDegrees(arg2 * arg3));
        arg1.method_22907(class_7833.field_40714.rotationDegrees(arg4));
        arg1.method_22907(class_7833.field_40718.rotationDegrees(arg2 * arg5));
        Matrix4f var15 = arg1.method_23760().method_23761();
        method789(arg0, var15, arg2 * var10, 0.019999999552965164f, -0.009999999776482582f, arg2 * (var10 + var11 * 0.2199999988079071f), var12 * 0.9800000190734863f, -0.05999999865889549f, arg2 * (var10 + var11 * 0.8799999952316284f), var12 * 0.6000000238418579f, -0.12999999523162842f, arg7, arg8);
        method789(arg0, var15, arg2 * var10, 0.019999999552965164f, -0.009999999776482582f, arg2 * (var10 + var11 * 0.8799999952316284f), var12 * 0.6000000238418579f, -0.12999999523162842f, arg2 * (var10 + var11), var12 * 0.11999999731779099f, -0.17000000178813934f, arg7, arg8);
        method789(arg0, var15, arg2 * var10, -0.029999999329447746f, -0.029999999329447746f, arg2 * (var10 + var13 * 0.25999999046325684f), -var14 * 0.9599999785423279f, -0.10999999940395355f, arg2 * (var10 + var13 * 0.8399999737739563f), -var14 * 0.5400000214576721f, -0.18000000715255737f, arg8, arg7);
        method789(arg0, var15, arg2 * var10, -0.029999999329447746f, -0.029999999329447746f, arg2 * (var10 + var13 * 0.8399999737739563f), -var14 * 0.5400000214576721f, -0.18000000715255737f, arg2 * (var10 + var13), -var14 * 0.11999999731779099f, -0.20999999344348907f, arg8, arg7);
        arg1.method_22909();
    }

  private void method786(class_287 arg0, class_4587 arg1, float arg2, float arg3, float arg4, float arg5, float arg6, int arg7) { // было: a
        float var9 = 0.11999999731779099f * arg6;
        float var10 = 1.5199999809265137f * arg6;
        float var11 = 0.6399999856948853f * arg6;
        float var12 = 1.1399999856948853f * arg6;
        float var13 = 0.38999998569488525f * arg6;
        arg1.method_22903();
        arg1.method_46416(0.15000000596046448f * arg2, 0.0f, -0.17000000178813934f);
        arg1.method_22907(class_7833.field_40716.rotationDegrees(arg2 * arg3));
        arg1.method_22907(class_7833.field_40714.rotationDegrees(arg4));
        arg1.method_22907(class_7833.field_40718.rotationDegrees(arg2 * arg5));
        Matrix4f var14 = arg1.method_23760().method_23761();
        method791(arg0, var14, arg2 * var9, 0.019999999552965164f, -0.009999999776482582f, arg2 * (var9 + var10 * 0.2199999988079071f), var11 * 0.9800000190734863f, -0.05999999865889549f, arg7);
        method791(arg0, var14, arg2 * (var9 + var10 * 0.2199999988079071f), var11 * 0.9800000190734863f, -0.05999999865889549f, arg2 * (var9 + var10 * 0.8799999952316284f), var11 * 0.6000000238418579f, -0.12999999523162842f, arg7);
        method791(arg0, var14, arg2 * (var9 + var10 * 0.8799999952316284f), var11 * 0.6000000238418579f, -0.12999999523162842f, arg2 * (var9 + var10), var11 * 0.11999999731779099f, -0.17000000178813934f, arg7);
        method791(arg0, var14, arg2 * var9, -0.029999999329447746f, -0.029999999329447746f, arg2 * (var9 + var12 * 0.25999999046325684f), -var13 * 0.9599999785423279f, -0.10999999940395355f, arg7);
        method791(arg0, var14, arg2 * (var9 + var12 * 0.25999999046325684f), -var13 * 0.9599999785423279f, -0.10999999940395355f, arg2 * (var9 + var12 * 0.8399999737739563f), -var13 * 0.5400000214576721f, -0.18000000715255737f, arg7);
        method791(arg0, var14, arg2 * (var9 + var12 * 0.8399999737739563f), -var13 * 0.5400000214576721f, -0.18000000715255737f, arg2 * (var9 + var12), -var13 * 0.11999999731779099f, -0.20999999344348907f, arg7);
        method791(arg0, var14, arg2 * var9, -0.009999999776482582f, -0.019999999552965164f, arg2 * (var9 + var10 * 0.6000000238418579f), 0.07999999821186066f, -0.07999999821186066f, arg7);
        arg1.method_22909();
    }

  private void method787(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, int arg14, int arg15, int arg16, int arg17) { // было: a
        method790(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
        method790(arg0, arg1, arg11, arg12, arg13, arg8, arg9, arg10, arg5, arg6, arg7, arg2, arg3, arg4, arg14, arg15, arg16, arg17);
    }

  private void method788(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, int arg14, int arg15) { // было: a
        int var17 = arg14 >> (-1009144939 ^ -1009144955) & (728598072 ^ 728598215);
        int var18 = arg14 >> (-2078121478 ^ -2078121486) & (-157598182 ^ -157597979);
        int var19 = arg14 & (-605716968 ^ -605716761);
        int var20 = arg14 >> (-2122494127 ^ -2122494135) & (-1980952033 ^ -1980951840);
        int var21 = arg15 >> (-33905016 ^ -33905000) & (-658790190 ^ -658790355);
        int var22 = arg15 >> (678415125 ^ 678415133) & (2120320249 ^ 2120320006);
        int var23 = arg15 & (245661245 ^ 245661378);
        int var24 = arg15 >> (1545285811 ^ 1545285803) & (425693481 ^ 425693654);
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(var17, var18, var19, var20);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(var21, var22, var23, var24);
        arg0.method_22918(arg1, arg8, arg9, arg10).method_1336(var21, var22, var23, var24);
        arg0.method_22918(arg1, arg11, arg12, arg13).method_1336(var17, var18, var19, var20);
        arg0.method_22918(arg1, arg11, arg12, arg13).method_1336(var17, var18, var19, var20);
        arg0.method_22918(arg1, arg8, arg9, arg10).method_1336(var21, var22, var23, var24);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(var21, var22, var23, var24);
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(var17, var18, var19, var20);
    }

  private void method789(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, int arg11, int arg12) { // было: a
        int var14 = arg11 >> (-1682352602 ^ -1682352586) & (-359645750 ^ -359645899);
        int var15 = arg11 >> (403489780 ^ 403489788) & (1794483048 ^ 1794483095);
        int var16 = arg11 & (-2125438401 ^ -2125438272);
        int var17 = arg11 >> (-236203235 ^ -236203259) & (-2077284097 ^ -2077284352);
        int var18 = arg12 >> (1383865049 ^ 1383865033) & (1580342759 ^ 1580342552);
        int var19 = arg12 >> (-2110285999 ^ -2110285991) & (-989548068 ^ -989548253);
        int var20 = arg12 & (1560135159 ^ 1560134920);
        int var21 = arg12 >> (1072229038 ^ 1072229046) & (-1951036546 ^ -1951036543);
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(var14, var15, var16, var17);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(var18, var19, var20, var21);
        arg0.method_22918(arg1, arg8, arg9, arg10).method_1336(var18, var19, var20, var21);
        arg0.method_22918(arg1, arg8, arg9, arg10).method_1336(var18, var19, var20, var21);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(var18, var19, var20, var21);
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(var14, var15, var16, var17);
    }

  private void method790(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, int arg14, int arg15, int arg16, int arg17) { // было: b
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(arg14, arg15, arg16, arg17);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(arg14, arg15, arg16, arg17);
        arg0.method_22918(arg1, arg8, arg9, arg10).method_1336(arg14, arg15, arg16, arg17);
        arg0.method_22918(arg1, arg11, arg12, arg13).method_1336(arg14, arg15, arg16, arg17);
    }

  private void method791(class_287 arg0, Matrix4f arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, int arg8) { // было: a
        int var10 = arg8 >> (1575827189 ^ 1575827173) & (-1441671262 ^ -1441671331);
        int var11 = arg8 >> (1460704565 ^ 1460704573) & (-947893070 ^ -947893171);
        int var12 = arg8 & (657214570 ^ 657214613);
        int var13 = arg8 >> (-1875758678 ^ -1875758670) & (932919840 ^ 932920031);
        arg0.method_22918(arg1, arg2, arg3, arg4).method_1336(var10, var11, var12, var13);
        arg0.method_22918(arg1, arg5, arg6, arg7).method_1336(var10, var11, var12, var13);
    }

  private Boolean method792() { // было: b
        return Boolean.valueOf(field398.method715(Decryptor.method1945(XorDecoder.method1946("Xú/eü\u000fPí>òBè/uÉ&¹vÊp÷", -1824333906 ^ 1494172828))));
    }

  private Boolean method793() { // было: c
        return Boolean.valueOf(field398.method715(Decryptor.method1945(XorDecoder.method1946("ûì~Æê»oóû\ráþvÖßFÕÜÄ\u0008", -454406194 ^ -787234209))));
    }

  private Boolean method794() { // было: d
        return Boolean.valueOf(field398.method715(Decryptor.method1945(XorDecoder.method1946("¼W\u0005ÙÝa\u0019ÍØo&æ¸\u0014kÙÕ\r\u0016èÅgb·", -2026716395 ^ 225305986))));
    }

  private Boolean method795() { // было: e
        return Boolean.valueOf(field398.method715(Decryptor.method1945(XorDecoder.method1946("*±¦+Kº?N\u0014.òÈ+Cëµ\u001aSÁE", 121020670 ^ 2143969535))));
    }

  private static int bt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}