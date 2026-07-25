// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bz.a
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_243;
import net.minecraft.class_3532;

class bz_ClassA119 {

    // ---- поля ----
  private final List field506; // было: B
  private final ClassA2 field507; // было: F
  private final int nv;
  private boolean af;
  private class_243 field508; // было: o
  private float cQ;
  private float cR;
  private List field509; // было: C
  private float cS;
  private static final String wM = "// nice try. closed source for a reason.";
  private static final String wN = "// every class watermarked, every string encrypted, every number xored";
  private static final String wO = "// reverse-engineering this jar is a waste of time, friend";
  private static final String wP = "// good luck with the next 9999 classes";
  private static final String wQ = "// you are reading machine-generated garbage";
  private static final int nw = 171805354;
  private static final int nx = 652793730;
  private static final int ny = -1540271894;
  private static final byte[] dp;

    static {
        dp = "EmkBV!uq+]=\"~#_E3v,OBr(\"x\"!i?[%3Z=$v>\"f`,BMD8*,!7A0>6X1qwDaX^%y*B+y<:DVhY!dF^?7}d \\DDwD8SXj}})}6\\r<YCg(8)\\_<w~-8E\\lfS%;xZ#y;w Ne7{E|.6J~/#yjXytXX(mVv6P*wA*^,W,\\w8jae[w+At0Ka-'/e3nsXT+fOdUQMB'vIOc};<HOzQ_xs7j4j#Pb_%iXO|!&^[hArbKztwcPagie \"Pc=&H:xR41!/Gvk-f(".getBytes("ISO-8859-1");
    }

  public bz_ClassA119(class_243 arg0) { // было: <init>
        super();
        field506 = new ArrayList();
        field507 = new ClassA2(-8581991700416880773L ^ -8581991700416881717L, aH.field19);
        af = 1909560623 ^ 1909560623;
        field508 = class_243.field_1353;
        cQ = 0.0f;
        cR = 0.0f;
        field509 = new ArrayList();
        cS = -1.0f;
        field506.add(arg0);
        field507.method7(0.0f);
        nv = (-796584073 ^ -796584069) + new Random().nextInt(2043036631 ^ 2043036632);
    }

  public void method884(float arg0) { // было: W
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #64 // dev.angelvisuals.a.bz$a.F:Ldev/angelvisuals/a/k;
        //      4: aload_0
        //      5: getfield  #65 // dev.angelvisuals.a.bz$a.af:Z
        //      8: ifne  19 (offset +11)
        //     11: ldc  #14 // 7149389
        //     13: ldc  #13 // 7149388
        //     15: ixor
        //     16: goto  24 (offset +8)
        //     19: ldc  #28 // 1633670674
        //     21: ldc  #28 // 1633670674
        //     23: ixor
        //     24: invokevirtual  #79 // dev.angelvisuals.a.k.a:(Z)V
        //     27: aload_0
        //     28: getfield  #65 // dev.angelvisuals.a.bz$a.af:Z
        //     31: ifne  103 (offset +72)
        //     34: aload_0
        //     35: aload_0
        //     36: getfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //     39: putfield  #67 // dev.angelvisuals.a.bz$a.cR:F
        //     42: aload_0
        //     43: dup
        //     44: getfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //     47: ldc  #34 // 0.02500000037252903f
        //     49: fload_1
        //     50: fmul
        //     51: fadd
        //     52: putfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //     55: aload_0
        //     56: getfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //     59: fconst_1
        //     60: fcmpl
        //     61: iflt  78 (offset +17)
        //     64: aload_0
        //     65: fconst_0
        //     66: putfield  #67 // dev.angelvisuals.a.bz$a.cR:F
        //     69: aload_0
        //     70: fconst_0
        //     71: putfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //     74: aload_0
        //     75: invokevirtual  #76 // dev.angelvisuals.a.bz$a.aC:()V
        //     78: aload_0
        //     79: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //     82: invokeinterface  #98 // java.util.List.size:()I, count 1
        //     87: aload_0
        //     88: getfield  #70 // dev.angelvisuals.a.bz$a.nv:I
        //     91: if_icmplt  103 (offset +12)
        //     94: aload_0
        //     95: ldc  #7 // -1424094523
        //     97: ldc  #6 // -1424094524
        //     99: ixor
        //    100: putfield  #65 // dev.angelvisuals.a.bz$a.af:Z
        //    103: return
    }

  private void aC() {
        class_243 var1 = ((class_243) field506.get(field506.size() - (1099403666 ^ 1099403667)));
        class_243 var2 = method885();
        while (var2.method_1026(field508) < -0.5) {
            var2 = method885();
            continue;
        }
        field508 = var2;
        field506.add(var1.method_1019(var2.method_1021(2.5)));
    }

  private class_243 method885() { // было: l
        int __stk1;
        class_243 __stk2;
        int var1 = new Random().nextInt(415801201 ^ 415801202);
        __stk1 = !new Random().nextBoolean() ? 1280160230 ^ -1280160231 : 1440194845 ^ 1440194844;
        int var2 = __stk1;
        switch (var1) {
            case 0:
                __stk2 = new class_243(((double) var2), 0.0, 0.0);
                break;
            case 1:
                __stk2 = new class_243(0.0, ((double) var2), 0.0);
                break;
            default:
                __stk2 = new class_243(0.0, 0.0, ((double) var2));
        }
        return __stk2;
    }

  public List method886(float arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //      4: invokeinterface  #98 // java.util.List.size:()I, count 1
        //      9: ldc  #11 // -324014452
        //     11: ldc  #12 // -324014450
        //     13: ixor
        //     14: if_icmplt  24 (offset +10)
        //     17: aload_0
        //     18: getfield  #65 // dev.angelvisuals.a.bz$a.af:Z
        //     21: ifeq  29 (offset +8)
        //     24: aload_0
        //     25: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //     28: areturn
        //     29: aload_0
        //     30: getfield  #68 // dev.angelvisuals.a.bz$a.cS:F
        //     33: fload_1
        //     34: fcmpl
        //     35: ifne  55 (offset +20)
        //     38: aload_0
        //     39: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //     42: invokeinterface  #96 // java.util.List.isEmpty:()Z, count 1
        //     47: ifne  55 (offset +8)
        //     50: aload_0
        //     51: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //     54: areturn
        //     55: aload_0
        //     56: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //     59: invokeinterface  #98 // java.util.List.size:()I, count 1
        //     64: aload_0
        //     65: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //     68: invokeinterface  #98 // java.util.List.size:()I, count 1
        //     73: if_icmpeq  94 (offset +21)
        //     76: aload_0
        //     77: new  #48 // java.util.ArrayList
        //     80: dup
        //     81: aload_0
        //     82: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //     85: invokespecial  #85 // java.util.ArrayList.<init>:(Ljava/util/Collection;)V
        //     88: putfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //     91: goto  143 (offset +52)
        //     94: ldc  #15 // 36663458
        //     96: ldc  #15 // 36663458
        //     98: ixor
        //     99: istore_2
        //    100: iload_2
        //    101: aload_0
        //    102: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //    105: invokeinterface  #98 // java.util.List.size:()I, count 1
        //    110: if_icmpge  143 (offset +33)
        //    113: aload_0
        //    114: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //    117: iload_2
        //    118: aload_0
        //    119: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //    122: iload_2
        //    123: invokeinterface  #95 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    128: checkcast  #51 // net.minecraft.class_243
        //    131: invokeinterface  #97 // java.util.List.set:(ILjava/lang/Object;)Ljava/lang/Object;, count 3
        //    136: pop
        //    137: iinc  2, 1
        //    140: goto  100 (offset -40)
        //    143: aload_0
        //    144: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //    147: invokeinterface  #98 // java.util.List.size:()I, count 1
        //    152: ldc  #3 // -1685801234
        //    154: ldc  #4 // -1685801233
        //    156: ixor
        //    157: isub
        //    158: istore_2
        //    159: aload_0
        //    160: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //    163: iload_2
        //    164: invokeinterface  #95 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    169: checkcast  #51 // net.minecraft.class_243
        //    172: astore_3
        //    173: aload_0
        //    174: getfield  #62 // dev.angelvisuals.a.bz$a.B:Ljava/util/List;
        //    177: iload_2
        //    178: ldc  #17 // 60662485
        //    180: ldc  #16 // 60662484
        //    182: ixor
        //    183: isub
        //    184: invokeinterface  #95 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    189: checkcast  #51 // net.minecraft.class_243
        //    192: astore  4
        //    194: aload_0
        //    195: getfield  #67 // dev.angelvisuals.a.bz$a.cR:F
        //    198: aload_0
        //    199: getfield  #66 // dev.angelvisuals.a.bz$a.cQ:F
        //    202: aload_0
        //    203: getfield  #67 // dev.angelvisuals.a.bz$a.cR:F
        //    206: fsub
        //    207: fload_1
        //    208: fmul
        //    209: fadd
        //    210: fstore  5
        //    212: aload  4
        //    214: getfield  #74 // net.minecraft.class_243.field_1352:D
        //    217: aload_3
        //    218: getfield  #74 // net.minecraft.class_243.field_1352:D
        //    221: aload  4
        //    223: getfield  #74 // net.minecraft.class_243.field_1352:D
        //    226: dsub
        //    227: fload  5
        //    229: f2d
        //    230: dmul
        //    231: dadd
        //    232: dstore  6
        //    234: aload  4
        //    236: getfield  #73 // net.minecraft.class_243.field_1351:D
        //    239: aload_3
        //    240: getfield  #73 // net.minecraft.class_243.field_1351:D
        //    243: aload  4
        //    245: getfield  #73 // net.minecraft.class_243.field_1351:D
        //    248: dsub
        //    249: fload  5
        //    251: f2d
        //    252: dmul
        //    253: dadd
        //    254: dstore  8
        //    256: aload  4
        //    258: getfield  #72 // net.minecraft.class_243.field_1350:D
        //    261: aload_3
        //    262: getfield  #72 // net.minecraft.class_243.field_1350:D
        //    265: aload  4
        //    267: getfield  #72 // net.minecraft.class_243.field_1350:D
        //    270: dsub
        //    271: fload  5
        //    273: f2d
        //    274: dmul
        //    275: dadd
        //    276: dstore  10
        //    278: aload_0
        //    279: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //    282: iload_2
        //    283: new  #51 // net.minecraft.class_243
        //    286: dup
        //    287: dload  6
        //    289: dload  8
        //    291: dload  10
        //    293: invokespecial  #89 // net.minecraft.class_243.<init>:(DDD)V
        //    296: invokeinterface  #97 // java.util.List.set:(ILjava/lang/Object;)Ljava/lang/Object;, count 3
        //    301: pop
        //    302: aload_0
        //    303: fload_1
        //    304: putfield  #68 // dev.angelvisuals.a.bz$a.cS:F
        //    307: aload_0
        //    308: getfield  #63 // dev.angelvisuals.a.bz$a.C:Ljava/util/List;
        //    311: areturn
    }

  public float aZ() {
        return class_3532.method_15363(field507.method13(), 0.0f, 1.0f);
    }

  public boolean af() {
        return !af ? 1548276525 ^ 1548276525 : field507.method13() > 0.009999999776482582f ? 1548276525 ^ 1548276525 : -1736628820 ^ -1736628819;
    }

  private static int lQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lS(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}