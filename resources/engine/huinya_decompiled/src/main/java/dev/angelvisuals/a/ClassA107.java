// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.f
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA103_ClassA104;
import dev.angelvisuals.a.ClassA105_ClassA106;
import dev.angelvisuals.a.ClassA25;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ci;
import dev.angelvisuals.a.dD;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1671;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_638;

@bI(name = "FireworkESP", a = "RENDER", I = "Показывает теги и трейлы фейерверков")
public final class ClassA107 extends cK implements cF {

    // ---- поля ----
  public static final ClassA107 field451; // было: a
  private final bA field452; // было: B
  private final bA field453; // было: C
  private final Map field454; // было: b
  private float bv;
  private static final String lo = "// flow obfuscation: ENABLED";
  private static final String lp = "// Joiner sees you";
  private static final String lq = "// this jar protected by JoinerObfuscator";
  private static final String lr = "// good luck with the next 9999 classes";
  private static final String ls = "// Joiner sees you";
  private static final int gA = -672532302;
  private static final int gB = -1078361260;
  private static final int gC = -719330131;
  private static final byte[] bo;

    static {
        bo = " +6.Nv^7VB%v)!=!oH%QGA4$o:S4y$&1&l0:7=sK.</!+]>< 5^=X?rZJ+bF`P|Z;/NS6G8JoA6_{^,^s,*!f47%]mF_smvLw7s7jmt7!c0+8.d^!Q:6y0hHmr\"7'Fvt`<aZCU3[Y^.=o`+z$[p\"$T<*,Ue8/y`1+5F0m?,t|kWi@Uq!=\\FAkZ\\Kl)__-.|(_u1q#%-s<<>/0Q@(:9Acy<4T`?!D!mv&\"6v#L#~G-d@Uv\\qCz$8Un>_}zj/?v$Qd".getBytes("ISO-8859-1");
        field451 = new ClassA107();
    }

  private ClassA107() { // было: <init>
        super();
        field452 = new bA(Decryptor.method1945(XorDecoder.method1946("ô4¿wÝràxÙ\u0010°qÃ\u0002¼D±0êcÝ\rgÒ2¡gë.*ë\u000fSÓq¶dÑ)/", 1983686709 ^ 1693380015)), 100.0f, 10.0f, 1000.0f, 10.0f);
        field453 = new bA(Decryptor.method1945(XorDecoder.method1946("væ½XËÝHôIØ±@ÿ¬\u007fâÑrÚ°¨{ê¬¶xÿÎ¶_ºÆ:àØ", 1453042910 ^ -1284693553)), 1000.0f, 100.0f, 5000.0f, 100.0f);
        field454 = new HashMap();
    }

  public void method827() { // было: k
        super.method611();
        field454.clear();
    }

    @EventTarget
  private void method828(dD arg0) { // было: f
        bv = arg0.bt();
        Iterator var4;
        if (mc.field_1687 != null) {
            long var2 = System.currentTimeMillis();
            field454.entrySet().removeIf(lp0 -> method832(var2, ((Entry) lp0)));
            var4 = mc.field_1687.method_18112().iterator();
        } else {
            return;
        }
        while (var4.hasNext()) {
            class_1297 var5 = ((class_1297) var4.next());
            if (var5 instanceof class_1671) {
                if (var5.method_5805()) {
                    ClassA103_ClassA104 var6 = ((ClassA103_ClassA104) field454.computeIfAbsent(Integer.valueOf(var5.method_5628()), lp0 -> method831(((Integer) lp0))));
                    if (((float) (var2 - var6.field438)) >= field452.bp()) {
                        class_243 var7 = new class_243(class_3532.method_16436(((double) bv), var5.field_6014, var5.method_23317()), class_3532.method_16436(((double) bv), var5.field_6036, var5.method_23318()) + 0.5, class_3532.method_16436(((double) bv), var5.field_5969, var5.method_23321()));
                        float var8 = ((float) var5.field_6012) / 20.0f;
                        var6.field439.add(new ClassA105_ClassA106(var7, var2, var8));
                        var6.field438 = var2;
                    }
                }
            }
            continue;
        }
    }

    @EventTarget
  private void method829(ClassA25 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #114 // dev.angelvisuals.a.f.mc:Lnet/minecraft/class_310;
        //      3: getfield  #129 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //      6: ifnull  18 (offset +12)
        //      9: getstatic  #114 // dev.angelvisuals.a.f.mc:Lnet/minecraft/class_310;
        //     12: getfield  #128 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //     15: ifnonnull  19 (offset +4)
        //     18: return
        //     19: aload_1
        //     20: invokevirtual  #138 // dev.angelvisuals.a.b.a:()Ldev/angelvisuals/a/ap;
        //     23: invokevirtual  #137 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //     26: astore_2
        //     27: new  #98 // net.minecraft.class_1799
        //     30: dup
        //     31: getstatic  #124 // net.minecraft.class_1802.field_8639:Lnet/minecraft/class_1792;
        //     34: invokespecial  #168 // net.minecraft.class_1799.<init>:(Lnet/minecraft/class_1935;)V
        //     37: astore_3
        //     38: invokestatic  #160 // java.lang.System.currentTimeMillis:()J
        //     41: lstore  4
        //     43: aload_0
        //     44: getfield  #111 // dev.angelvisuals.a.f.b:Ljava/util/Map;
        //     47: invokeinterface  #188 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //     52: invokeinterface  #191 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //     57: astore  6
        //     59: aload  6
        //     61: invokeinterface  #180 // java.util.Iterator.hasNext:()Z, count 1
        //     66: ifeq  441 (offset +375)
        //     69: aload  6
        //     71: invokeinterface  #181 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     76: checkcast  #94 // java.util.Map$Entry
        //     79: astore  7
        //     81: aload  7
        //     83: invokeinterface  #190 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //     88: checkcast  #79 // dev.angelvisuals.a.f$a
        //     91: astore  8
        //     93: aload  8
        //     95: getfield  #116 // dev.angelvisuals.a.f$a.b:Ljava/util/List;
        //     98: invokeinterface  #184 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    103: astore  9
        //    105: aload  9
        //    107: invokeinterface  #180 // java.util.Iterator.hasNext:()Z, count 1
        //    112: ifeq  247 (offset +135)
        //    115: aload  9
        //    117: invokeinterface  #181 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    122: checkcast  #80 // dev.angelvisuals.a.f$b
        //    125: astore  10
        //    127: aload  10
        //    129: getfield  #119 // dev.angelvisuals.a.f$b.s:Lnet/minecraft/class_243;
        //    132: invokestatic  #147 // dev.angelvisuals.a.dI.d:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //    135: astore  11
        //    137: aload  11
        //    139: getfield  #125 // net.minecraft.class_243.field_1350:D
        //    142: dconst_0
        //    143: dcmpg
        //    144: iflt  105 (offset -39)
        //    147: aload  11
        //    149: getfield  #125 // net.minecraft.class_243.field_1350:D
        //    152: dconst_1
        //    153: dcmpl
        //    154: ifle  160 (offset +6)
        //    157: goto  105 (offset -52)
        //    160: fconst_1
        //    161: lload  4
        //    163: aload  10
        //    165: getfield  #117 // dev.angelvisuals.a.f$b.M:J
        //    168: lsub
        //    169: l2f
        //    170: aload_0
        //    171: getfield  #109 // dev.angelvisuals.a.f.C:Ldev/angelvisuals/a/bA;
        //    174: invokevirtual  #140 // dev.angelvisuals.a.bA.bp:()F
        //    177: fdiv
        //    178: fsub
        //    179: fstore  12
        //    181: fload  12
        //    183: fconst_0
        //    184: fconst_1
        //    185: invokestatic  #170 // net.minecraft.class_3532.method_15363:(FFF)F
        //    188: fstore  12
        //    190: ldc  #62 // '\x87\x8f0£»\xa0\x0f´\x84\x8c\x08¸½¢\x12¯\x94®\x17à½\x87}ä'
        //    192: ldc  #28 // 914261885
        //    194: ldc  #24 // -281128559
        //    196: ixor
        //    197: invokestatic  #131 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    200: invokestatic  #130 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    203: ldc  #30 // 1004390439
        //    205: ldc  #29 // 1004390438
        //    207: ixor
        //    208: anewarray  #84 // java.lang.Object
        //    211: dup
        //    212: ldc  #4 // -1741823685
        //    214: ldc  #4 // -1741823685
        //    216: ixor
        //    217: aload  10
        //    219: getfield  #118 // dev.angelvisuals.a.f$b.dj:F
        //    222: invokestatic  #155 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    225: aastore
        //    226: invokestatic  #158 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    229: astore  13
        //    231: aload_0
        //    232: aload_1
        //    233: aload_2
        //    234: aload_3
        //    235: aload  11
        //    237: fload  12
        //    239: aload  13
        //    241: invokevirtual  #151 // dev.angelvisuals.a.f.a:(Ldev/angelvisuals/a/b;Lnet/minecraft/class_4587;Lnet/minecraft/class_1799;Lnet/minecraft/class_243;FLjava/lang/String;)V
        //    244: goto  105 (offset -139)
        //    247: getstatic  #114 // dev.angelvisuals.a.f.mc:Lnet/minecraft/class_310;
        //    250: getfield  #128 // net.minecraft.class_310.field_1687:Lnet/minecraft/class_638;
        //    253: aload  7
        //    255: invokeinterface  #189 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //    260: checkcast  #82 // java.lang.Integer
        //    263: invokevirtual  #156 // java.lang.Integer.intValue:()I
        //    266: invokevirtual  #178 // net.minecraft.class_638.method_8469:(I)Lnet/minecraft/class_1297;
        //    269: astore  9
        //    271: aload  9
        //    273: instanceof  #97 // net.minecraft.class_1671
        //    276: ifeq  438 (offset +162)
        //    279: aload  9
        //    281: invokevirtual  #167 // net.minecraft.class_1297.method_5805:()Z
        //    284: ifeq  438 (offset +154)
        //    287: new  #100 // net.minecraft.class_243
        //    290: dup
        //    291: aload_0
        //    292: getfield  #113 // dev.angelvisuals.a.f.bv:F
        //    295: f2d
        //    296: aload  9
        //    298: getfield  #122 // net.minecraft.class_1297.field_6014:D
        //    301: aload  9
        //    303: invokevirtual  #163 // net.minecraft.class_1297.method_23317:()D
        //    306: invokestatic  #171 // net.minecraft.class_3532.method_16436:(DDD)D
        //    309: aload_0
        //    310: getfield  #113 // dev.angelvisuals.a.f.bv:F
        //    313: f2d
        //    314: aload  9
        //    316: getfield  #123 // net.minecraft.class_1297.field_6036:D
        //    319: aload  9
        //    321: invokevirtual  #164 // net.minecraft.class_1297.method_23318:()D
        //    324: invokestatic  #171 // net.minecraft.class_3532.method_16436:(DDD)D
        //    327: ldc2_w  #105 // 0.5d
        //    330: dadd
        //    331: aload_0
        //    332: getfield  #113 // dev.angelvisuals.a.f.bv:F
        //    335: f2d
        //    336: aload  9
        //    338: getfield  #120 // net.minecraft.class_1297.field_5969:D
        //    341: aload  9
        //    343: invokevirtual  #165 // net.minecraft.class_1297.method_23321:()D
        //    346: invokestatic  #171 // net.minecraft.class_3532.method_16436:(DDD)D
        //    349: invokespecial  #169 // net.minecraft.class_243.<init>:(DDD)V
        //    352: astore  10
        //    354: aload  10
        //    356: invokestatic  #147 // dev.angelvisuals.a.dI.d:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //    359: astore  11
        //    361: aload  11
        //    363: getfield  #125 // net.minecraft.class_243.field_1350:D
        //    366: dconst_0
        //    367: dcmpl
        //    368: iflt  438 (offset +70)
        //    371: aload  11
        //    373: getfield  #125 // net.minecraft.class_243.field_1350:D
        //    376: dconst_1
        //    377: dcmpg
        //    378: ifgt  438 (offset +60)
        //    381: ldc  #60 // 't#\x845H\x0c»"w ¼.N\x0e¦9g\x02£vN+Ér'
        //    383: ldc  #21 // -453372170
        //    385: ldc  #11 // -1425117975
        //    387: ixor
        //    388: invokestatic  #131 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    391: invokestatic  #130 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    394: ldc  #15 // -1020957187
        //    396: ldc  #14 // -1020957188
        //    398: ixor
        //    399: anewarray  #84 // java.lang.Object
        //    402: dup
        //    403: ldc  #27 // 732534413
        //    405: ldc  #27 // 732534413
        //    407: ixor
        //    408: aload  9
        //    410: getfield  #121 // net.minecraft.class_1297.field_6012:I
        //    413: i2f
        //    414: ldc  #49 // 20.0f
        //    416: fdiv
        //    417: invokestatic  #155 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    420: aastore
        //    421: invokestatic  #158 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    424: astore  12
        //    426: aload_0
        //    427: aload_1
        //    428: aload_2
        //    429: aload_3
        //    430: aload  11
        //    432: fconst_1
        //    433: aload  12
        //    435: invokevirtual  #151 // dev.angelvisuals.a.f.a:(Ldev/angelvisuals/a/b;Lnet/minecraft/class_4587;Lnet/minecraft/class_1799;Lnet/minecraft/class_243;FLjava/lang/String;)V
        //    438: goto  59 (offset -379)
        //    441: return
    }

  private void method830(ClassA25 arg0, class_4587 arg1, class_1799 arg2, class_243 arg3, float arg4, String arg5) { // было: a
        float var7 = 0.6000000238418579f;
        float var8 = 12.0f;
        float var9 = 2.5f;
        float var10 = 2.0f;
        float var11 = 3.5f;
        float var12 = 0.3499999940395355f + 0.6499999761581421f * arg4;
        int var13 = ((int) (255.0f * arg4));
        if (var13 > (-2028417399 ^ -2028417396)) {
            int var14 = var13 << (-1597750534 ^ -1597750558) | 1464054506 ^ 1464449248;
            int var15 = var13 << (502482943 ^ 502482919) | 1238868420 ^ 1227382331;
            float var16 = bc.field171.method381(arg5, 6.0f);
            float var17 = 16.0f * var7;
            float var18 = var9 + var17 + var10 + var16 + var9;
            arg1.method_22903();
            arg1.method_22904(arg3.field_1352, arg3.field_1351, 0.0);
            arg1.method_22905(var12, var12, 1.0f);
            aE.method1742(arg1, -var18 / 2.0f, -var8 / 2.0f, var18, var8, aY.method1597(2.0f), new bp(var14));
            float var19 = -var18 / 2.0f + var9;
            arg1.method_22903();
            arg1.method_46416(var19, -(16.0f * var7) / 2.0f, 0.0f);
            arg1.method_22905(var7, var7, 1.0f);
            arg0.method319().method_51427(arg2, 1652858094 ^ 1652858094, -389551467 ^ -389551467);
            arg1.method_22909();
            var19 = var19 + var17 + var10;
            ap.method1637(arg0.method319()).method1638(bc.field171.method383(6.0f), arg5, var19, -var8 / 2.0f + var11 + 0.5f, new bp(var15));
            arg1.method_22909();
            return;
        } else {
            return;
        }
    }

  private static ClassA103_ClassA104 method831(Integer arg0) { // было: a
        return new ClassA103_ClassA104();
    }

  private boolean method832(long arg0, Entry arg1) { // было: a
        int __stk1;
        class_1297 var4 = mc.field_1687.method_8469((((Integer) arg1.getKey())).intValue());
        __stk1 = var4 == null ? -1553622520 ^ -1553622519 : var4.method_5805() ? -952299307 ^ -952299307 : -1553622520 ^ -1553622519;
        int var5 = __stk1;
        (((ClassA103_ClassA104) arg1.getValue())).field439.removeIf(lp0 -> method833(arg0, ((ClassA105_ClassA106) lp0)));
        return var5 == 0 ? -1914982534 ^ -1914982534 : !(((ClassA103_ClassA104) arg1.getValue())).field439.isEmpty() ? -1914982534 ^ -1914982534 : -596033779 ^ -596033780;
    }

  private boolean method833(long arg0, ClassA105_ClassA106 arg1) { // было: a
        return ((float) (arg0 - arg1.field450)) <= field453.bp() ? -431364711 ^ -431364711 : -1425184597 ^ -1425184598;
    }

  private static int fQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}