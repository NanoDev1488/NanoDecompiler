// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.R
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.ClassA131_Anon1;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.cH;
import dev.angelvisuals.a.cI;
import dev.angelvisuals.a.cr;
import dev.angelvisuals.a.dh;
import dev.angelvisuals.a.do;
import lombok.Generated;
import net.minecraft.class_241;
import net.minecraft.class_315;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_7172;
import net.minecraft.class_757;
import ru.nexusguard.protection.annotations.Native;

public class ClassA132 implements ClassA146 {

    // ---- поля ----
  public static ClassA132 field685; // было: a
  private ClassA131_Anon1 field686; // было: a
  private float ad;
  private float ae;
  private float af;
  private float ag;
  private int bU;
  private int bV;
  private int bW;
  private cH field687; // было: a
  private static final String dE = "// flow obfuscation: ENABLED";
  private static final String dF = "// every class watermarked, every string encrypted, every number xored";
  private static final String dG = "// Joiner sees you";
  private static final String dH = "// reverse-engineering this jar is a waste of time, friend";
  private static final String dI = "// === DO NOT TOUCH ===";
  private static final int bX = 1876339206;
  private static final int bY = 1389770838;
  private static final int bZ = 1723150675;
  private static final byte[] field688; // было: S

    static {
        field688 = "w!khT,TOtfhY#i_57:Id$:|KA]szE?H=6avsRyKz7m2ZM=Fc%GpV3V)_H*K33^vlV3q[`3FS-n~e*w7@%Am[v:H{w'G2&1~l\"+rZjfW: 0GQ%mRl3-kv!a) d)=4?zy$Ph7G:;nscmGyjx;TBj;s7BYe]d&@,BG7Yx4L%r@<m KQ3=Ahhy6PG`-fw\\yjH`z,n%C(Q%`eum&!m'W/gQd,z.&#-<Md!Ys2R* 2\"lG#;TE+12X]bo$M]/_])RrD[axa".getBytes("ISO-8859-1");
        field685 = new ClassA132();
    }

  public ClassA132() { // было: <init>
        super();
        field686 = ClassA131_Anon1.field683;
        field687 = new cH(0.0f, 0.0f);
        EventManager.register(this);
        new cI();
    }

  public static double method1103(float arg0, float arg1, float arg2) { // было: a
        if (arg1 < 0.0f) {
            arg0 = arg0 + 180.0f;
        }
        float var3 = 1.0f;
        if (arg1 < 0.0f) {
            var3 = -0.5f;
        }
        if (arg1 > 0.0f) {
            var3 = 0.5f;
        }
        if (arg2 > 0.0f) {
            arg0 = arg0 - 90.0f * var3;
        }
        if (arg2 < 0.0f) {
            arg0 = arg0 + 90.0f * var3;
        }
        return Math.toRadians(((double) arg0));
    }

    @EventTarget
  public void method1104(cr arg0) { // было: a
        if (method1113()) {
            dh.method1219(arg0, class_3532.method_15393(mc.field_1773.method_19418().method_19330()));
        }
    }

  private void method1105() { // было: l
        cH var1 = new cH(cI.ba(), cI.bb());
        if (method1111(var1, method1117(), method1118())) {
            method1112();
        }
    }

    @EventTarget
    @Native
  public void method1106(do arg0) { // было: d
        if (method1114().equals(ClassA131_Anon1.field681)) {
            if (method1121() > method1120()) {
                method1123(ClassA131_Anon1.field682);
            }
        }
        if (method1114().equals(ClassA131_Anon1.field682)) {
            method1105();
        }
        bW = bW + (-1755210434 ^ -1755210433);
    }

    @Native
  public static class_241 method1107(class_241 arg0, class_241 arg1) { // было: a
        double var2 = (((Double) mc.field_1690.method_42495().method_41753())).doubleValue();
        double var4 = Math.pow(var2 * 0.6000000238418579 + 0.20000000298023224, 3.0) * 8.0;
        double var6 = ((double) arg1.field_1343);
        double var8 = ((double) arg1.field_1342);
        double var10 = ((double) arg0.field_1343);
        double var12 = ((double) arg0.field_1342);
        double var14 = ((double) Math.round((var10 - var6) / (var4 * 0.15000000596046448))) * var4 * 0.15000000596046448;
        double var16 = ((double) Math.round((var12 - var8) / (var4 * 0.15000000596046448))) * var4 * 0.15000000596046448;
        return new class_241(((float) (var6 + var14)), ((float) (var8 + var16)));
    }

  public static void method1108(cH arg0, float arg1, float arg2, float arg3, float arg4, int arg5, int arg6, boolean arg7) { // было: a
        ClassA132 var8 = field685;
        if (var8.method1119() <= arg6) {
            if (var8.method1114().equals(ClassA131_Anon1.field683)) {
                if (!arg7) {
                    cI.method1096(442641128 ^ 442641129);
                }
            }
            var8.method1124(arg1);
            var8.method1125(arg2);
            var8.method1126(arg3);
            var8.method1127(arg4);
            var8.method1129(arg5);
            var8.method1128(arg6);
            var8.method1123(ClassA131_Anon1.field681);
            var8.method1131(arg0);
            var8.method1111(arg0, arg1, arg2);
        }
    }

  public static void method1109(cH arg0, float arg1, float arg2, int arg3, int arg4) { // было: a
        method1108(arg0, arg1, arg1, arg2, arg2, arg3, arg4, 928394423 ^ 928394423);
    }

  public static void method1110(cH arg0, float arg1, float arg2, float arg3, int arg4, int arg5) { // было: a
        method1108(arg0, arg1, arg2, arg3, arg3, arg4, arg5, -1125009799 ^ -1125009799);
    }

    @Native
  private boolean method1111(cH arg0, float arg1, float arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //      3: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //      6: ifnonnull  15 (offset +9)
        //      9: ldc  #2 // -1758292935
        //     11: ldc  #2 // -1758292935
        //     13: ixor
        //     14: ireturn
        //     15: new  #36 // dev.angelvisuals.a.cH
        //     18: dup
        //     19: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //     22: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     25: invokevirtual  #128 // net.minecraft.class_746.method_36454:()F
        //     28: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //     31: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     34: invokevirtual  #129 // net.minecraft.class_746.method_36455:()F
        //     37: invokespecial  #105 // dev.angelvisuals.a.cH.<init>:(FF)V
        //     40: astore  4
        //     42: aload_1
        //     43: invokevirtual  #106 // dev.angelvisuals.a.cH.aX:()F
        //     46: aload  4
        //     48: invokevirtual  #106 // dev.angelvisuals.a.cH.aX:()F
        //     51: fsub
        //     52: invokestatic  #125 // net.minecraft.class_3532.method_15393:(F)F
        //     55: fstore  5
        //     57: aload_1
        //     58: invokevirtual  #107 // dev.angelvisuals.a.cH.aY:()F
        //     61: aload  4
        //     63: invokevirtual  #107 // dev.angelvisuals.a.cH.aY:()F
        //     66: fsub
        //     67: fstore  6
        //     69: fload  5
        //     71: invokestatic  #115 // java.lang.Math.abs:(F)F
        //     74: fload  6
        //     76: invokestatic  #115 // java.lang.Math.abs:(F)F
        //     79: fadd
        //     80: fstore  7
        //     82: fload  7
        //     84: fconst_0
        //     85: fcmpl
        //     86: ifne  93 (offset +7)
        //     89: fconst_0
        //     90: goto  103 (offset +13)
        //     93: fload  5
        //     95: fload  7
        //     97: fdiv
        //     98: invokestatic  #115 // java.lang.Math.abs:(F)F
        //    101: fload_2
        //    102: fmul
        //    103: fstore  8
        //    105: fload  7
        //    107: fconst_0
        //    108: fcmpl
        //    109: ifne  116 (offset +7)
        //    112: fconst_0
        //    113: goto  126 (offset +13)
        //    116: fload  6
        //    118: fload  7
        //    120: fdiv
        //    121: invokestatic  #115 // java.lang.Math.abs:(F)F
        //    124: fload_3
        //    125: fmul
        //    126: fstore  9
        //    128: new  #43 // net.minecraft.class_241
        //    131: dup
        //    132: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    135: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    138: invokevirtual  #128 // net.minecraft.class_746.method_36454:()F
        //    141: fload  5
        //    143: fload  8
        //    145: fneg
        //    146: fload  8
        //    148: invokestatic  #124 // net.minecraft.class_3532.method_15363:(FFF)F
        //    151: fadd
        //    152: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    155: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    158: invokevirtual  #129 // net.minecraft.class_746.method_36455:()F
        //    161: fload  6
        //    163: fload  9
        //    165: fneg
        //    166: fload  9
        //    168: invokestatic  #124 // net.minecraft.class_3532.method_15363:(FFF)F
        //    171: fadd
        //    172: ldc  #20 // -90.0f
        //    174: ldc  #23 // 90.0f
        //    176: invokestatic  #124 // net.minecraft.class_3532.method_15363:(FFF)F
        //    179: invokespecial  #122 // net.minecraft.class_241.<init>:(FF)V
        //    182: new  #43 // net.minecraft.class_241
        //    185: dup
        //    186: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    189: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    192: invokevirtual  #128 // net.minecraft.class_746.method_36454:()F
        //    195: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    198: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    201: invokevirtual  #129 // net.minecraft.class_746.method_36455:()F
        //    204: invokespecial  #122 // net.minecraft.class_241.<init>:(FF)V
        //    207: invokestatic  #90 // dev.angelvisuals.a.R.a:(Lnet/minecraft/class_241;Lnet/minecraft/class_241;)Lnet/minecraft/class_241;
        //    210: astore  10
        //    212: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    215: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    218: aload  10
        //    220: getfield  #77 // net.minecraft.class_241.field_1343:F
        //    223: invokevirtual  #130 // net.minecraft.class_746.method_36456:(F)V
        //    226: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    229: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    232: aload  10
        //    234: getfield  #76 // net.minecraft.class_241.field_1342:F
        //    237: invokevirtual  #131 // net.minecraft.class_746.method_36457:(F)V
        //    240: new  #36 // dev.angelvisuals.a.cH
        //    243: dup
        //    244: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    247: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    250: invokevirtual  #128 // net.minecraft.class_746.method_36454:()F
        //    253: getstatic  #72 // dev.angelvisuals.a.R.mc:Lnet/minecraft/class_310;
        //    256: getfield  #79 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    259: invokevirtual  #129 // net.minecraft.class_746.method_36455:()F
        //    262: invokespecial  #105 // dev.angelvisuals.a.cH.<init>:(FF)V
        //    265: astore  11
        //    267: aload_0
        //    268: ldc  #12 // 407833320
        //    270: ldc  #12 // 407833320
        //    272: ixor
        //    273: invokevirtual  #94 // dev.angelvisuals.a.R.c:(I)Ldev/angelvisuals/a/R;
        //    276: pop
        //    277: aload  11
        //    279: aload_1
        //    280: invokevirtual  #108 // dev.angelvisuals.a.cH.b:(Ldev/angelvisuals/a/cH;)F
        //    283: f2d
        //    284: aload_0
        //    285: getfield  #62 // dev.angelvisuals.a.R.a:Ldev/angelvisuals/a/R$1;
        //    288: getstatic  #74 // dev.angelvisuals.a.R$1.c:Ldev/angelvisuals/a/R$1;
        //    291: invokevirtual  #104 // dev.angelvisuals.a.R$1.equals:(Ljava/lang/Object;)Z
        //    294: ifeq  313 (offset +19)
        //    297: aload_0
        //    298: getfield  #67 // dev.angelvisuals.a.R.af:F
        //    301: f2d
        //    302: aload_0
        //    303: getfield  #68 // dev.angelvisuals.a.R.ag:F
        //    306: f2d
        //    307: invokestatic  #116 // java.lang.Math.hypot:(DD)D
        //    310: goto  326 (offset +16)
        //    313: aload_0
        //    314: getfield  #65 // dev.angelvisuals.a.R.ad:F
        //    317: f2d
        //    318: aload_0
        //    319: getfield  #66 // dev.angelvisuals.a.R.ae:F
        //    322: f2d
        //    323: invokestatic  #116 // java.lang.Math.hypot:(DD)D
        //    326: dcmpg
        //    327: ifge  338 (offset +11)
        //    330: ldc  #7 // -215415510
        //    332: ldc  #8 // -215415509
        //    334: ixor
        //    335: goto  343 (offset +8)
        //    338: ldc  #5 // -1401091561
        //    340: ldc  #5 // -1401091561
        //    342: ixor
        //    343: ireturn
    }

    @Native
  public void method1112() { // было: m
        method1123(ClassA131_Anon1.field683);
        method1128(2035035648 ^ 2035035648);
        cI.method1096(130208472 ^ 130208472);
    }

  public boolean method1113() { // было: p
        return field686.equals(ClassA131_Anon1.field683) ? -1846188586 ^ -1846188586 : -203289863 ^ -203289864;
    }

    @Generated
  public ClassA131_Anon1 method1114() { // было: a
        return field686;
    }

    @Generated
  public float method1115() { // было: n
        return ad;
    }

    @Generated
  public float method1116() { // было: o
        return ae;
    }

    @Generated
  public float method1117() { // было: p
        return af;
    }

    @Generated
  public float method1118() { // было: q
        return ag;
    }

    @Generated
  public int method1119() { // было: f
        return bU;
    }

    @Generated
  public int method1120() { // было: g
        return bV;
    }

    @Generated
  public int method1121() { // было: h
        return bW;
    }

    @Generated
  public cH method1122() { // было: b
        return field687;
    }

    @Generated
  public ClassA132 method1123(ClassA131_Anon1 arg0) { // было: a
        field686 = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1124(float arg0) { // было: a
        ad = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1125(float arg0) { // было: b
        ae = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1126(float arg0) { // было: c
        af = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1127(float arg0) { // было: d
        ag = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1128(int arg0) { // было: a
        bU = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1129(int arg0) { // было: b
        bV = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1130(int arg0) { // было: c
        bW = arg0;
        return this;
    }

    @Generated
  public ClassA132 method1131(cH arg0) { // было: a
        field687 = arg0;
        return this;
    }

  private static int bC(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bD(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bE(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}