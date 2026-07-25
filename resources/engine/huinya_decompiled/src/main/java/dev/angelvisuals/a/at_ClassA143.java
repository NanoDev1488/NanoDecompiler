// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.at.a
package dev.angelvisuals.a;

import net.minecraft.class_10185;
import net.minecraft.class_1657;
import net.minecraft.class_744;

public class at_ClassA143 extends class_744 {

    // ---- поля ----
  public boolean field734; // было: Q
  public float bw;
  public float bx;
  public class_10185 field735; // было: a
  public static final double field736 = 0.121; // было: q
  private static final String ly = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String lz = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String lA = "// flow obfuscation: ENABLED";
  private static final String lB = "// number obfuscation: ENABLED (XOR masking)";
  private static final String lC = "// number obfuscation: ENABLED (XOR masking)";
  private static final int gG = -810724917;
  private static final int gH = 2095569528;
  private static final int gI = -1974826703;
  private static final byte[] bq;

    static {
        bq = "?VG3h\\>Bv;keNM?JdKBz:h:7o}eGtEa/>wa4lb}Q]pixqWfncXdyvzj5AUKjHeN5Gsp)!6B2AZQ^CSbB]}*/;edRm/x~.+xw9T\"$ZU$2w(9*2\\\"Px1&F/A0zTEnJp*O)(5Pec}(:og-4<Af9SXAcn!z_`uSw]rI\"<`vT54n6T7mFSg1S?=rXO\\wxGKP<CY(|*W3AQ0eh}dg6[xry7~-QE7aI8RLuA,<rSt|q$'Xec{j*?5dpGRil+4}T]R)I]-Jw".getBytes("ISO-8859-1");
    }

  public at_ClassA143(class_10185 arg0) { // было: <init>
        super();
        field734 = 405616912 ^ 405616912;
        field735 = arg0;
    }

  public void ac() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //      4: invokevirtual  #56 // net.minecraft.class_10185.comp_3159:()Z
        //      7: aload_0
        //      8: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     11: invokevirtual  #57 // net.minecraft.class_10185.comp_3160:()Z
        //     14: if_icmpeq  40 (offset +26)
        //     17: aload_0
        //     18: aload_0
        //     19: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     22: invokevirtual  #56 // net.minecraft.class_10185.comp_3159:()Z
        //     25: ifeq  32 (offset +7)
        //     28: fconst_1
        //     29: goto  34 (offset +5)
        //     32: ldc  #15 // -1.0f
        //     34: putfield  #45 // dev.angelvisuals.a.at$a.bw:F
        //     37: goto  45 (offset +8)
        //     40: aload_0
        //     41: fconst_0
        //     42: putfield  #45 // dev.angelvisuals.a.at$a.bw:F
        //     45: aload_0
        //     46: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     49: invokevirtual  #58 // net.minecraft.class_10185.comp_3161:()Z
        //     52: aload_0
        //     53: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     56: invokevirtual  #59 // net.minecraft.class_10185.comp_3162:()Z
        //     59: if_icmpne  70 (offset +11)
        //     62: aload_0
        //     63: fconst_0
        //     64: putfield  #46 // dev.angelvisuals.a.at$a.bx:F
        //     67: goto  90 (offset +23)
        //     70: aload_0
        //     71: aload_0
        //     72: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     75: invokevirtual  #58 // net.minecraft.class_10185.comp_3161:()Z
        //     78: ifeq  85 (offset +7)
        //     81: fconst_1
        //     82: goto  87 (offset +5)
        //     85: ldc  #15 // -1.0f
        //     87: putfield  #46 // dev.angelvisuals.a.at$a.bx:F
        //     90: aload_0
        //     91: getfield  #43 // dev.angelvisuals.a.at$a.a:Lnet/minecraft/class_10185;
        //     94: invokevirtual  #61 // net.minecraft.class_10185.comp_3164:()Z
        //     97: ifeq  122 (offset +25)
        //    100: aload_0
        //    101: dup
        //    102: getfield  #46 // dev.angelvisuals.a.at$a.bx:F
        //    105: ldc  #16 // 0.30000001192092896f
        //    107: fmul
        //    108: putfield  #46 // dev.angelvisuals.a.at$a.bx:F
        //    111: aload_0
        //    112: dup
        //    113: getfield  #45 // dev.angelvisuals.a.at$a.bw:F
        //    116: ldc  #16 // 0.30000001192092896f
        //    118: fmul
        //    119: putfield  #45 // dev.angelvisuals.a.at$a.bw:F
        //    122: return
    }

  public String toString() {
        boolean var1 = field735.comp_3159();
        return "SimulatedPlayerInput(forwards={" + var1 + "}, backwards={" + field735.comp_3160() + "}, left={" + field735.comp_3161() + "}, right={" + field735.comp_3162() + "}, jumping={" + field735.comp_3163() + "}, sprinting=" + field735.comp_3165() + ", slowDown=" + field735.comp_3164() + ")";
    }

  public static at_ClassA143 method1287(class_10185 arg0) { // было: a
        return new at_ClassA143(arg0);
    }

  public static at_ClassA143 method1288(class_1657 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #63 // net.minecraft.class_1657.method_19538:()Lnet/minecraft/class_243;
        //      4: new  #33 // net.minecraft.class_243
        //      7: dup
        //      8: aload_0
        //      9: getfield  #48 // net.minecraft.class_1657.field_6014:D
        //     12: aload_0
        //     13: getfield  #49 // net.minecraft.class_1657.field_6036:D
        //     16: aload_0
        //     17: getfield  #47 // net.minecraft.class_1657.field_5969:D
        //     20: invokespecial  #67 // net.minecraft.class_243.<init>:(DDD)V
        //     23: invokevirtual  #68 // net.minecraft.class_243.method_1020:(Lnet/minecraft/class_243;)Lnet/minecraft/class_243;
        //     26: astore_1
        //     27: aload_1
        //     28: invokevirtual  #69 // net.minecraft.class_243.method_37268:()D
        //     31: dstore_2
        //     32: new  #31 // net.minecraft.class_10185
        //     35: dup
        //     36: ldc  #12 // 1962653085
        //     38: ldc  #12 // 1962653085
        //     40: ixor
        //     41: ldc  #6 // -726074424
        //     43: ldc  #6 // -726074424
        //     45: ixor
        //     46: ldc  #2 // -1972547212
        //     48: ldc  #2 // -1972547212
        //     50: ixor
        //     51: ldc  #7 // -437296389
        //     53: ldc  #7 // -437296389
        //     55: ixor
        //     56: aload_0
        //     57: invokevirtual  #64 // net.minecraft.class_1657.method_24828:()Z
        //     60: ifne  71 (offset +11)
        //     63: ldc  #4 // -1861388695
        //     65: ldc  #3 // -1861388696
        //     67: ixor
        //     68: goto  76 (offset +8)
        //     71: ldc  #11 // 1151996533
        //     73: ldc  #11 // 1151996533
        //     75: ixor
        //     76: aload_0
        //     77: invokevirtual  #66 // net.minecraft.class_1657.method_5715:()Z
        //     80: dload_2
        //     81: ldc2_w  #38 // 0.014641d
        //     84: dcmpl
        //     85: iflt  96 (offset +11)
        //     88: ldc  #9 // 392440211
        //     90: ldc  #8 // 392440210
        //     92: ixor
        //     93: goto  101 (offset +8)
        //     96: ldc  #13 // 2030983698
        //     98: ldc  #13 // 2030983698
        //    100: ixor
        //    101: invokespecial  #55 // net.minecraft.class_10185.<init>:(ZZZZZZZ)V
        //    104: astore  4
        //    106: dload_2
        //    107: ldc2_w  #36 // 0.0025000000000000005d
        //    110: dcmpl
        //    111: ifle  140 (offset +29)
        //    114: aload_1
        //    115: aload_0
        //    116: invokevirtual  #65 // net.minecraft.class_1657.method_36454:()F
        //    119: invokestatic  #52 // dev.angelvisuals.a.dh.a:(Lnet/minecraft/class_243;F)D
        //    122: dstore  5
        //    124: dload  5
        //    126: invokestatic  #70 // net.minecraft.class_3532.method_15338:(D)D
        //    129: dstore  7
        //    131: aload  4
        //    133: dload  7
        //    135: invokestatic  #51 // dev.angelvisuals.a.dh.a:(Lnet/minecraft/class_10185;D)Lnet/minecraft/class_10185;
        //    138: astore  4
        //    140: new  #25 // dev.angelvisuals.a.at$a
        //    143: dup
        //    144: aload  4
        //    146: invokespecial  #50 // dev.angelvisuals.a.at$a.<init>:(Lnet/minecraft/class_10185;)V
        //    149: areturn
    }

  private static int fW(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int fY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}