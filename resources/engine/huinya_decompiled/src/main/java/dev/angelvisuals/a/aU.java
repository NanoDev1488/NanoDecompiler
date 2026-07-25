// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.au
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.cK;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_10142;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_4588;
import net.minecraft.class_638;
import org.joml.Matrix4f;

@bI(name = "Arrows", a = "RENDER", I = "Показывает стрелки в сторону игроков")
public class au extends cK {

    // ---- поля ----
  public static final au field368; // было: a
  private static final class_2960 field369; // было: e
  private final bA field370; // было: z
  private final bA field371; // было: A
  private final Map field372; // было: a
  private static final String gW = "// good luck with the next 9999 classes";
  private static final String gX = "// class hierarchy hashing: ENABLED";
  private static final String gY = "// === DO NOT TOUCH ===";
  private static final String gZ = "// every class watermarked, every string encrypted, every number xored";
  private static final String ha = "// nice try. closed source for a reason.";
  private static final int dY = -42737269;
  private static final int dZ = 721265387;
  private static final int ea = 1604436055;
  private static final byte[] az;

    static {
        az = "C>gT\"~,eo$p@%b/q{']Wt9z996c$/=#yUf/sU)`~6xUrwJt_Hi04BT#ih|OeWJcuo4=(tT.M,~7d*`j(gqyL$#.ag+^lr1|(prOG#WmW#\"ki3fg}%:b<k\"-[Qm|C9VyjW]G4Z>L5}+>rEYQW/4B9DG8PPC8LK-D*FLcq*sVm6s.];v4p}WlOmc+k}ZzJ~l!` ETC<b)zIg~L`_]rI V#|l_kTfTy}dQLh`dWu{@a)X;8T<AL=+kQjlOQKYjc:yjh".getBytes("ISO-8859-1");
        field368 = new au();
        field369 = class_2960.method_60655(Decryptor.method1945(XorDecoder.method1946("\u000bJ£e7z²\u001f\u001e}\u001c.'­=\u000eJ¡doiÊi", 269562158 ^ 1155936114)), Decryptor.method1945(XorDecoder.method1946("\u000f§¡>³»~áÅ\u007f»¬tã¦t°éÏ", -711185685 ^ 659094956)));
    }

  public au() { // было: <init>
        super();
        field370 = new bA(Decryptor.method1945(XorDecoder.method1946("*6@MQ*n^0vL^\u0011\u000ePB\u0003uGx\u0008582", 82116008 ^ 199344073)), 100.0f, 50.0f, 300.0f, 5.0f);
        field371 = new bA(Decryptor.method1945(XorDecoder.method1946("þë\u001f\u0008øl\u000föËr,ça+Üèu\u000fëÆ\tS", 354360414 ^ 2066460131)), 10.0f, 5.0f, 25.0f, 1.0f);
        field372 = new HashMap();
    }

    @EventTarget
  private void method751(bx arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //      3: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //      6: ifnull  18 (offset +12)
        //      9: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //     12: getfield  #99 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     15: ifnonnull  19 (offset +4)
        //     18: return
        //     19: aload_1
        //     20: invokevirtual  #128 // dev.angelvisuals.a.bx.b:()Ldev/angelvisuals/a/ap;
        //     23: invokevirtual  #115 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //     26: astore_2
        //     27: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //     30: invokevirtual  #152 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     33: invokevirtual  #140 // net.minecraft.class_1041.method_4486:()I
        //     36: i2f
        //     37: fconst_2
        //     38: fdiv
        //     39: fstore_3
        //     40: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //     43: invokevirtual  #152 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     46: invokevirtual  #141 // net.minecraft.class_1041.method_4502:()I
        //     49: i2f
        //     50: fconst_2
        //     51: fdiv
        //     52: fstore  4
        //     54: invokestatic  #113 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     57: invokevirtual  #114 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //     60: invokevirtual  #130 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //     63: invokevirtual  #121 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //     66: astore  5
        //     68: invokestatic  #113 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     71: invokevirtual  #114 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //     74: invokevirtual  #130 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //     77: invokevirtual  #122 // dev.angelvisuals.a.bl.j:()Ldev/angelvisuals/a/bp;
        //     80: astore  6
        //     82: aload  6
        //     84: ifnonnull  96 (offset +12)
        //     87: aload  5
        //     89: ldc  #19 // 0.30000001192092896f
        //     91: invokevirtual  #125 // dev.angelvisuals.a.bp.d:(F)Ldev/angelvisuals/a/bp;
        //     94: astore  6
        //     96: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //     99: getfield  #99 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //    102: invokevirtual  #161 // net.minecraft.class_638.method_18112:()Ljava/lang/Iterable;
        //    105: invokeinterface  #167 // java.lang.Iterable.iterator:()Ljava/util/Iterator;, count 1
        //    110: astore  7
        //    112: aload  7
        //    114: invokeinterface  #168 // java.util.Iterator.hasNext:()Z, count 1
        //    119: ifeq  444 (offset +325)
        //    122: aload  7
        //    124: invokeinterface  #169 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    129: checkcast  #65 // net.minecraft.class_1297
        //    132: astore  8
        //    134: aload  8
        //    136: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    139: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    142: if_acmpne  148 (offset +6)
        //    145: goto  112 (offset -33)
        //    148: aload  8
        //    150: instanceof  #66 // net.minecraft.class_1657
        //    153: ifeq  112 (offset -41)
        //    156: aload  8
        //    158: checkcast  #66 // net.minecraft.class_1657
        //    161: astore  9
        //    163: invokestatic  #113 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    166: invokevirtual  #112 // dev.angelvisuals.AngelVisuals.getFriendManager:()Ldev/angelvisuals/a/bs;
        //    169: aload  8
        //    171: invokevirtual  #144 // net.minecraft.class_1297.method_5477:()Lnet/minecraft/class_2561;
        //    174: invokeinterface  #174 // net.minecraft.class_2561.getString:()Ljava/lang/String;, count 1
        //    179: invokevirtual  #126 // dev.angelvisuals.a.bs.m:(Ljava/lang/String;)Z
        //    182: ifeq  188 (offset +6)
        //    185: goto  112 (offset -73)
        //    188: aload  8
        //    190: invokevirtual  #142 // net.minecraft.class_1297.method_23317:()D
        //    193: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    196: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    199: invokevirtual  #163 // net.minecraft.class_746.method_23317:()D
        //    202: dsub
        //    203: dstore  10
        //    205: aload  8
        //    207: invokevirtual  #143 // net.minecraft.class_1297.method_23321:()D
        //    210: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    213: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    216: invokevirtual  #164 // net.minecraft.class_746.method_23321:()D
        //    219: dsub
        //    220: dstore  12
        //    222: dload  12
        //    224: dload  10
        //    226: invokestatic  #135 // java.lang.Math.atan2:(DD)D
        //    229: invokestatic  #136 // java.lang.Math.toDegrees:(D)D
        //    232: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    235: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    238: invokevirtual  #165 // net.minecraft.class_746.method_36454:()F
        //    241: f2d
        //    242: dsub
        //    243: ldc2_w  #87 // 90.0d
        //    246: dsub
        //    247: d2f
        //    248: fstore  14
        //    250: aload  8
        //    252: invokevirtual  #145 // net.minecraft.class_1297.method_5628:()I
        //    255: istore  15
        //    257: aload_0
        //    258: getfield  #91 // dev.angelvisuals.a.au.a:Ljava/util/Map;
        //    261: iload  15
        //    263: invokestatic  #134 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    266: fload  14
        //    268: invokestatic  #132 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    271: invokeinterface  #170 // java.util.Map.getOrDefault:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    276: checkcast  #51 // java.lang.Float
        //    279: invokevirtual  #131 // java.lang.Float.floatValue:()F
        //    282: fstore  16
        //    284: fload  14
        //    286: fload  16
        //    288: fsub
        //    289: invokestatic  #154 // net.minecraft.class_3532.method_15393:(F)F
        //    292: fstore  17
        //    294: fload  16
        //    296: fload  17
        //    298: ldc  #18 // 0.15000000596046448f
        //    300: fmul
        //    301: aload_1
        //    302: invokevirtual  #127 // dev.angelvisuals.a.bx.an:()F
        //    305: fconst_1
        //    306: fadd
        //    307: fmul
        //    308: fadd
        //    309: fstore  16
        //    311: aload_0
        //    312: getfield  #91 // dev.angelvisuals.a.au.a:Ljava/util/Map;
        //    315: iload  15
        //    317: invokestatic  #134 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    320: fload  16
        //    322: invokestatic  #132 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    325: invokeinterface  #172 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    330: pop
        //    331: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    334: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    337: aload  8
        //    339: invokevirtual  #166 // net.minecraft.class_746.method_5739:(Lnet/minecraft/class_1297;)F
        //    342: f2d
        //    343: dstore  18
        //    345: dconst_1
        //    346: dload  18
        //    348: ldc2_w  #85 // 60.0d
        //    351: ddiv
        //    352: dsub
        //    353: ldc2_w  #83 // 0.3d
        //    356: dconst_1
        //    357: invokestatic  #153 // net.minecraft.class_3532.method_15350:(DDD)D
        //    360: d2f
        //    361: fstore  20
        //    363: aload_2
        //    364: invokevirtual  #155 // net.minecraft.class_4587.method_22903:()V
        //    367: aload_2
        //    368: fload_3
        //    369: fload  4
        //    371: fconst_0
        //    372: invokevirtual  #159 // net.minecraft.class_4587.method_46416:(FFF)V
        //    375: aload_2
        //    376: getstatic  #102 // net.minecraft.class_7833.field_40718:Lnet/minecraft/class_7833;
        //    379: fload  16
        //    381: invokeinterface  #177 // net.minecraft.class_7833.rotationDegrees:(F)Lorg/joml/Quaternionf;, count 2
        //    386: invokevirtual  #156 // net.minecraft.class_4587.method_22907:(Lorg/joml/Quaternionf;)V
        //    389: aload_2
        //    390: fconst_0
        //    391: aload_0
        //    392: getfield  #95 // dev.angelvisuals.a.au.z:Ldev/angelvisuals/a/bA;
        //    395: invokevirtual  #120 // dev.angelvisuals.a.bA.bp:()F
        //    398: fneg
        //    399: fconst_0
        //    400: invokevirtual  #159 // net.minecraft.class_4587.method_46416:(FFF)V
        //    403: aload_0
        //    404: aload_2
        //    405: aload  5
        //    407: fload  20
        //    409: ldc  #25 // 255.0f
        //    411: fmul
        //    412: f2i
        //    413: invokevirtual  #124 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    416: aload  6
        //    418: fload  20
        //    420: ldc  #25 // 255.0f
        //    422: fmul
        //    423: f2i
        //    424: invokevirtual  #124 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    427: aload_0
        //    428: getfield  #89 // dev.angelvisuals.a.au.A:Ldev/angelvisuals/a/bA;
        //    431: invokevirtual  #120 // dev.angelvisuals.a.bA.bp:()F
        //    434: invokevirtual  #118 // dev.angelvisuals.a.au.a:(Lnet/minecraft/class_4587;Ldev/angelvisuals/a/bp;Ldev/angelvisuals/a/bp;F)V
        //    437: aload_2
        //    438: invokevirtual  #157 // net.minecraft.class_4587.method_22909:()V
        //    441: goto  112 (offset -329)
        //    444: getstatic  #94 // dev.angelvisuals.a.au.mc:Lnet/minecraft/class_310;
        //    447: getfield  #100 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    450: getfield  #101 // net.minecraft.class_746.field_6012:I
        //    453: ldc  #16 // 1990323160
        //    455: ldc  #15 // 1990323132
        //    457: ixor
        //    458: irem
        //    459: ifne  482 (offset +23)
        //    462: aload_0
        //    463: getfield  #91 // dev.angelvisuals.a.au.a:Ljava/util/Map;
        //    466: invokeinterface  #171 // java.util.Map.keySet:()Ljava/util/Set;, count 1
        //    471: invokedynamic  #178 // invokedynamic test:()Ljava/util/function/Predicate;
        //    476: invokeinterface  #173 // java.util.Set.removeIf:(Ljava/util/function/Predicate;)Z, count 2
        //    481: pop
        //    482: return
    }

  private void method752(class_4587 arg0, bp arg1, bp arg2, float arg3) { // было: a
        Matrix4f var5 = arg0.method_23760().method_23761();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader(class_10142.field_53880);
        RenderSystem.setShaderTexture(771027497 ^ 771027497, field369);
        class_289 var6 = class_289.method_1348();
        class_287 var7 = var6.method_60827(class_5596.field_27382, class_290.field_1575);
        int var8 = arg1.method1680();
        var7.method_22918(var5, -arg3 / 2.0f, -arg3, 0.0f).method_22913(0.0f, 0.0f).method_39415(var8);
        var7.method_22918(var5, arg3 / 2.0f, -arg3, 0.0f).method_22913(1.0f, 0.0f).method_39415(var8);
        var7.method_22918(var5, arg3 / 2.0f, 0.0f, 0.0f).method_22913(1.0f, 1.0f).method_39415(var8);
        var7.method_22918(var5, -arg3 / 2.0f, 0.0f, 0.0f).method_22913(0.0f, 1.0f).method_39415(var8);
        class_286.method_43433(var7.method_60800());
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
    }

  private static boolean method753(Integer arg0) { // было: a
        return mc.field_1687.method_8469(arg0.intValue()) != null ? 590707072 ^ 590707072 : -829508607 ^ -829508608;
    }

  private static int dx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dy(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int dz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}