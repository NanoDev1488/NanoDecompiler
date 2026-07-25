// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.db
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.ClassA129;
import dev.angelvisuals.a.aE;
import dev.angelvisuals.a.aY;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.bN;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.ch;
import dev.angelvisuals.a.ci;
import java.util.List;
import net.minecraft.class_1041;
import net.minecraft.class_332;

public class db {

    // ---- поля ----
  private static final float dd = 154.0f;
  private static final float de = 14.0f;
  private static final float df = 170.0f;
  private static final float dg = 12.0f;
  private static final float dh = 22.0f;
  private static final float di = 4.0f;
  private static final String zJ = "// reverse-engineering this jar is a waste of time, friend";
  private static final String zK = "// you are reading machine-generated garbage";
  private static final String zL = "// reverse-engineering this jar is a waste of time, friend";
  private static final String zM = "// number obfuscation: ENABLED (XOR masking)";
  private static final String zN = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int ph = 578647339;
  private static final int pi = 549867423;
  private static final int pj = -1029264681;
  private static final byte[] dQ;

    static {
        dQ = "@T*KUjKa?KmGFCxiD=`ZzNd\\O\\;OA&Qz~2:rsq6m6$s<B`{Sz!v>{8\\].,MWaxO_!l8o^@36LNP?GdBOcy*'\"1G8Hfrwz!XL|E>BatF*&aND3.SA7$vUTo7uNJo/eiyYL&UK`y:7@eJQH>9d/LJh//zM2:hjh)O5:- G F{vk2+PaCNpx:G$qZ!KGV3yIzSnl6~FAThQ9[2~x1(0~I#ZYK/t~V3Ur6I{ZCe-a|s9&W~0EhyRK=lv;e-c_b0qXj~n".getBytes("ISO-8859-1");
    }

  public db() { // было: <init>
        super();
    }

  public void method1087(class_332 arg0, class_1041 arg1, bN arg2, int arg3, float arg4) { // было: a
        String __stk1;
        int __stk2;
        if (arg0 == null) {
            return;
        }
        if (arg1 == null) {
            return;
        }
        List var6 = AngelVisuals.getInstance().getThemeManager().method482();
        if (var6 == null) {
            return;
        }
        ap var10;
        bl var12;
        float var8;
        float var9;
        if (!var6.isEmpty()) {
            float var7 = arg2.ao() + ClassA129.method1010(arg3) / 2.0f;
            var8 = var7 - 77.0f;
            var9 = arg2.ap() + arg2.aq() - 22.0f;
            var10 = ap.method1637(arg0);
            bp var11 = new bp(182348880 ^ 182348890, -1690610145 ^ -1690610155, -594442286 ^ -594442275, ((int) (205.0f * arg4)));
            var12 = AngelVisuals.getInstance().getThemeManager().method481();
            aE.method1759(var10.method_51448(), var8, var9, 154.0f, 14.0f, 10.0f, aY.method1597(4.0f), bp.field909.method1686(((int) (255.0f * arg4))));
            aE.method1742(var10.method_51448(), var8, var9, 154.0f, 14.0f, aY.method1597(4.0f), var11);
            __stk1 = !arg2.method1074() ? Decryptor.method1945(XorDecoder.method1946("Ü÷ÎßìæÒª¦Ç ö¢Û³îí©", -378430791 ^ 2107712850)) : Decryptor.method1945(XorDecoder.method1946("ÿÐ³þ×Û¦¡ö©ª³¤ê³° Ò¿ô", -1936560578 ^ 1158728688));
        } else {
            return;
        }
        String var13 = __stk1;
        var10.method1638(bc.field171.method383(7.0f), Decryptor.method1945(XorDecoder.method1946("<±Ì\u0011\u0001Ë\r#ï_\u0003¹È_-¼èX0µ¾\u0007", 1842923198 ^ 1465586923)), var8 + 7.0f, var9 + 4.0f, bp.field909.method1686(((int) (230.0f * arg4))));
        var10.method1638(bc.field171.method383(7.0f), var12.method463(), var8 + 42.0f, var9 + 4.0f, bp.field909.method1686(((int) (205.0f * arg4))));
        var10.method1638(bc.field171.method383(7.0f), ((String) var13), var8 + 154.0f - 10.0f, var9 + 4.0f, bp.field909.method1686(((int) (210.0f * arg4))));
        float var14 = arg2.ar();
        int var19;
        if (var14 > 0.009999999776482582f) {
            float var15 = ((float) var6.size()) * 12.0f + 6.0f;
            float var16 = var15 * var14;
            float var17 = var7 - 85.0f;
            float var18 = var9 - var16 - 4.0f;
            aE.method1759(var10.method_51448(), var17, var18, 170.0f, var16, 14.0f, aY.method1597(5.0f), bp.field909.method1686(((int) (255.0f * arg4 * var14))));
            aE.method1742(var10.method_51448(), var17, var18, 170.0f, var16, aY.method1597(5.0f), new bp(1561063392 ^ 1561063404, -673782804 ^ -673782816, -670732494 ^ -670732512, ((int) (220.0f * arg4 * var14))));
            arg0.method_44379(((int) var17), ((int) var18), ((int) (var17 + 170.0f)), ((int) (var18 + var16)));
            var19 = 2138930791 ^ 2138930791;
        } else {
            return;
        }
        while (var19 < var6.size()) {
            bl var20 = ((bl) var6.get(var19));
            float var21 = var18 + 3.0f + ((float) var19) * 12.0f + (1.0f - var14) * 6.0f;
            __stk2 = var20 != var12 ? -1463726260 ^ -1463726260 : 1310021244 ^ 1310021245;
            int var22 = __stk2;
            int var23 = ((int) (arg4 * var14 * 255.0f));
            if (var22 != 0) {
                aE.method1742(var10.method_51448(), var17 + 2.0f, var21, 166.0f, 11.0f, aY.method1597(3.0f), new bp(1119496674 ^ 1119496477, 1348109917 ^ 1348109986, 17584235 ^ 17584276, ((int) (22.0f * arg4 * var14))));
            }
            aE.method1742(var10.method_51448(), var17 + 6.0f, var21 + 2.0f, 7.0f, 7.0f, aY.method1597(2.0f), var20.method449().method1686(var23));
            var10.method1638(bc.field171.method383(6.800000190734863f), var20.method463(), var17 + 17.0f, var21 + 3.0f, bp.field909.method1686(((int) (225.0f * arg4 * var14))));
            ++var19;
            continue;
        }
        arg0.method_44380();
    }

  public boolean method1088(class_1041 arg0, double arg1, double arg2, int arg3, bN arg4, int arg5) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnull  9 (offset +8)
        //      4: iload  6
        //      6: ifeq  15 (offset +9)
        //      9: ldc  #4 // -1542350106
        //     11: ldc  #4 // -1542350106
        //     13: ixor
        //     14: ireturn
        //     15: invokestatic  #116 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     18: invokevirtual  #117 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //     21: invokevirtual  #137 // dev.angelvisuals.a.ch.p:()Ljava/util/List;
        //     24: astore  9
        //     26: aload  9
        //     28: ifnull  41 (offset +13)
        //     31: aload  9
        //     33: invokeinterface  #145 // java.util.List.isEmpty:()Z, count 1
        //     38: ifeq  47 (offset +9)
        //     41: ldc  #25 // 250878654
        //     43: ldc  #25 // 250878654
        //     45: ixor
        //     46: ireturn
        //     47: aload  7
        //     49: invokevirtual  #126 // dev.angelvisuals.a.bN.ao:()F
        //     52: iload  8
        //     54: invokestatic  #118 // dev.angelvisuals.a.L.a:(I)F
        //     57: fconst_2
        //     58: fdiv
        //     59: fadd
        //     60: fstore  10
        //     62: fload  10
        //     64: ldc  #62 // 77.0f
        //     66: fsub
        //     67: fstore  11
        //     69: aload  7
        //     71: invokevirtual  #127 // dev.angelvisuals.a.bN.ap:()F
        //     74: aload  7
        //     76: invokevirtual  #128 // dev.angelvisuals.a.bN.aq:()F
        //     79: fadd
        //     80: ldc  #60 // 22.0f
        //     82: fsub
        //     83: fstore  12
        //     85: dload_2
        //     86: dload  4
        //     88: fload  11
        //     90: f2d
        //     91: fload  12
        //     93: f2d
        //     94: ldc2_w  #105 // 154.0d
        //     97: ldc2_w  #103 // 14.0d
        //    100: invokestatic  #139 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    103: ifeq  138 (offset +35)
        //    106: aload  7
        //    108: aload  7
        //    110: invokevirtual  #125 // dev.angelvisuals.a.bN.L:()Z
        //    113: ifne  124 (offset +11)
        //    116: ldc  #45 // 1893036237
        //    118: ldc  #44 // 1893036236
        //    120: ixor
        //    121: goto  129 (offset +8)
        //    124: ldc  #13 // -729499933
        //    126: ldc  #13 // -729499933
        //    128: ixor
        //    129: invokevirtual  #130 // dev.angelvisuals.a.bN.j:(Z)V
        //    132: ldc  #27 // 297520041
        //    134: ldc  #26 // 297520040
        //    136: ixor
        //    137: ireturn
        //    138: aload  7
        //    140: invokevirtual  #125 // dev.angelvisuals.a.bN.L:()Z
        //    143: ifne  152 (offset +9)
        //    146: ldc  #32 // 1063096838
        //    148: ldc  #32 // 1063096838
        //    150: ixor
        //    151: ireturn
        //    152: aload  9
        //    154: invokeinterface  #146 // java.util.List.size:()I, count 1
        //    159: i2f
        //    160: ldc  #57 // 12.0f
        //    162: fmul
        //    163: ldc  #52 // 6.0f
        //    165: fadd
        //    166: fstore  13
        //    168: fload  10
        //    170: ldc  #63 // 85.0f
        //    172: fsub
        //    173: fstore  14
        //    175: fload  12
        //    177: fload  13
        //    179: fsub
        //    180: ldc  #50 // 4.0f
        //    182: fsub
        //    183: fstore  15
        //    185: dload_2
        //    186: dload  4
        //    188: fload  14
        //    190: f2d
        //    191: fload  15
        //    193: f2d
        //    194: ldc2_w  #109 // 170.0d
        //    197: fload  13
        //    199: f2d
        //    200: invokestatic  #139 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    203: ifeq  311 (offset +108)
        //    206: ldc  #30 // 860847767
        //    208: ldc  #30 // 860847767
        //    210: ixor
        //    211: istore  16
        //    213: iload  16
        //    215: aload  9
        //    217: invokeinterface  #146 // java.util.List.size:()I, count 1
        //    222: if_icmpge  305 (offset +83)
        //    225: fload  15
        //    227: ldc  #49 // 3.0f
        //    229: fadd
        //    230: iload  16
        //    232: i2f
        //    233: ldc  #57 // 12.0f
        //    235: fmul
        //    236: fadd
        //    237: fstore  17
        //    239: dload_2
        //    240: dload  4
        //    242: fload  14
        //    244: fconst_2
        //    245: fadd
        //    246: f2d
        //    247: fload  17
        //    249: f2d
        //    250: ldc2_w  #107 // 166.0d
        //    253: ldc2_w  #101 // 11.0d
        //    256: invokestatic  #139 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    259: ifeq  299 (offset +40)
        //    262: invokestatic  #116 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    265: invokevirtual  #117 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //    268: aload  9
        //    270: iload  16
        //    272: invokeinterface  #144 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    277: checkcast  #91 // dev.angelvisuals.a.bl
        //    280: invokevirtual  #136 // dev.angelvisuals.a.ch.a:(Ldev/angelvisuals/a/bl;)V
        //    283: aload  7
        //    285: ldc  #10 // -1237308581
        //    287: ldc  #10 // -1237308581
        //    289: ixor
        //    290: invokevirtual  #130 // dev.angelvisuals.a.bN.j:(Z)V
        //    293: ldc  #6 // -1331022126
        //    295: ldc  #7 // -1331022125
        //    297: ixor
        //    298: ireturn
        //    299: iinc  16, 1
        //    302: goto  213 (offset -89)
        //    305: ldc  #8 // -1279462086
        //    307: ldc  #9 // -1279462085
        //    309: ixor
        //    310: ireturn
        //    311: aload  7
        //    313: ldc  #31 // 1013690837
        //    315: ldc  #31 // 1013690837
        //    317: ixor
        //    318: invokevirtual  #130 // dev.angelvisuals.a.bN.j:(Z)V
        //    321: ldc  #11 // -1078926330
        //    323: ldc  #11 // -1078926330
        //    325: ixor
        //    326: ireturn
    }

  private static int nt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nu(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int nv(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}