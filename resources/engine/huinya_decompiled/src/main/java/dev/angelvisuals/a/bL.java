// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bl
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cQ;
import java.awt.Color;
import lombok.Generated;
import net.minecraft.class_4587;

public class bl {

    // ---- поля ----
  private ClassA2 field223; // было: r
  private ClassA2 field224; // было: s
  private float bz;
  private float bA;
  private float bB;
  private float bC;
  private String mr;
  private int hj;
  private int hk;
  private int hl;
  private int hm;
  private static final String ms = "// reverse-engineering this jar is a waste of time, friend";
  private static final String mt = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String mu = "// this jar protected by JoinerObfuscator";
  private static final String mv = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String mw = "// nice try. closed source for a reason.";
  private static final int hn = -2067963949;
  private static final int ho = -552047608;
  private static final int hp = -1606337552;
  private static final byte[] bz;

    static {
        bz = "m0[_gDX%ZV?`TEP]H25n2jmim^PJ k%9FsC&%\"h8d%BgP<\\]*GF&gtkDdc<H_ga$W(UutF+5MQ9L?@K=R<:*1z8'Jl )S?&thdxl@}j K]lZvto1#HfR$1G`BrgE?r1xhc\"$:gN ;WJ\\?('v|(K/ Zj:tY=Q;Mmp5n}'_fI=&7J>{jqN<N'bA\\=ea)P\"pyB%Eivi~hb7G0'!cPKsU}[@^\\ZLWv+k!EaV}fPZ8_*!*R0M0a^5*Y&EUpx`4[%ty/KX".getBytes("ISO-8859-1");
    }

  public bl(String arg0, int arg1, int arg2) { // было: <init>
        super();
        field223 = new ClassA2(3063798951881794846L ^ 3063798951881795044L, aH.field21);
        field224 = new ClassA2(2726618555119207671L ^ 2726618555119207437L, aH.field21);
        mr = arg0;
        hj = method460(arg1);
        hk = method460(arg2);
        hl = hj;
        hm = hk;
    }

  public void method448(int arg0, int arg1) { // было: a
        hl = arg0;
        hm = arg1;
        field223 = new ClassA2(-1790971964695561685L ^ -1790971964695561519L, aH.field21);
    }

  public bp method449() { // было: b
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method450() { // было: c
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method451() { // было: d
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method452() { // было: e
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method453() { // было: f
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method454() { // было: g
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method455() { // было: h
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method456() { // было: i
        float var1 = field223.method13();
        return cQ.method1716(new bp(hl), new bp(hj), var1).method1693(0.10000000149011612f);
    }

  public bp method457() { // было: j
        float var1 = field223.method13();
        return cQ.method1716(new bp(hm), new bp(hk), var1).method1693(0.10000000149011612f);
    }

  public bp method458() { // было: k
        return new bp(-2141246566 ^ -2141246534, -958703444 ^ -958703533, -1832774394 ^ -1832774362, -1068358871 ^ -1068358698);
    }

  public void method459(class_4587 arg0, double arg1) { // было: a
        // (пустое тело)
    }

  private int method460(int arg0) { // было: c
        float[] var2 = Color.RGBtoHSB(arg0 >> (2069804975 ^ 2069804991) & (623635117 ^ 623635026), arg0 >> (699721667 ^ 699721675) & (-909186118 ^ -909186235), arg0 & (84127043 ^ 84127164), ((float[]) null));
        var2[-68651304 ^ -68651303] = Math.min(1.0f, var2[-966550182 ^ -966550181] * 1.7999999523162842f);
        var2[693114592 ^ 693114594] = Math.min(1.0f, var2[1617451659 ^ 1617451657] * 1.100000023841858f);
        return Color.HSBtoRGB(var2[-137822656 ^ -137822656], var2[-1763991005 ^ -1763991006], var2[890160807 ^ 890160805]);
    }

    @Generated
  public ClassA2 method461() { // было: e
        return field223;
    }

    @Generated
  public ClassA2 method462() { // было: f
        return field224;
    }

    @Generated
  public float ag() {
        return bz;
    }

    @Generated
  public float ah() {
        return bA;
    }

    @Generated
  public String method463() { // было: F
        return mr;
    }

    @Generated
  public int method464() { // было: A
        return hj;
    }

    @Generated
  public int method465() { // было: B
        return hk;
    }

    @Generated
  public int method466() { // было: C
        return hl;
    }

    @Generated
  public int method467() { // было: D
        return hm;
    }

    @Generated
  public void method468(ClassA2 arg0) { // было: a
        field223 = arg0;
    }

    @Generated
  public void method469(ClassA2 arg0) { // было: b
        field224 = arg0;
    }

    @Generated
  public void method470(float arg0) { // было: w
        bz = arg0;
    }

    @Generated
  public void method471(float arg0) { // было: x
        bB = arg0;
    }

    @Generated
  public void method472(float arg0) { // было: y
        bC = arg0;
    }

    @Generated
  public float ai() {
        return bB;
    }

    @Generated
  public float aj() {
        return bC;
    }

    @Generated
  public void method473(float arg0) { // было: z
        bA = arg0;
    }

    @Generated
  public void method474(String arg0) { // было: c
        mr = arg0;
    }

    @Generated
  public void method475(int arg0) { // было: i
        hj = arg0;
    }

    @Generated
  public void method476(int arg0) { // было: j
        hk = arg0;
    }

    @Generated
  public void method477(int arg0) { // было: k
        hl = arg0;
    }

    @Generated
  public void method478(int arg0) { // было: l
        hm = arg0;
    }

    @Generated
  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: if_acmpne  11 (offset +9)
        //      5: ldc  #22 // -1091001155
        //      7: ldc  #21 // -1091001156
        //      9: ixor
        //     10: ireturn
        //     11: aload_1
        //     12: instanceof  #93 // dev.angelvisuals.a.bl
        //     15: ifne  24 (offset +9)
        //     18: ldc  #73 // 1681181392
        //     20: ldc  #73 // 1681181392
        //     22: ixor
        //     23: ireturn
        //     24: aload_1
        //     25: checkcast  #93 // dev.angelvisuals.a.bl
        //     28: astore_2
        //     29: aload_2
        //     30: aload_0
        //     31: invokevirtual  #140 // dev.angelvisuals.a.bl.l:(Ljava/lang/Object;)Z
        //     34: ifne  43 (offset +9)
        //     37: ldc  #35 // -648287651
        //     39: ldc  #35 // -648287651
        //     41: ixor
        //     42: ireturn
        //     43: aload_0
        //     44: invokevirtual  #135 // dev.angelvisuals.a.bl.ag:()F
        //     47: aload_2
        //     48: invokevirtual  #135 // dev.angelvisuals.a.bl.ag:()F
        //     51: invokestatic  #149 // java.lang.Float.compare:(FF)I
        //     54: ifeq  63 (offset +9)
        //     57: ldc  #77 // 1885649853
        //     59: ldc  #77 // 1885649853
        //     61: ixor
        //     62: ireturn
        //     63: aload_0
        //     64: invokevirtual  #136 // dev.angelvisuals.a.bl.ah:()F
        //     67: aload_2
        //     68: invokevirtual  #136 // dev.angelvisuals.a.bl.ah:()F
        //     71: invokestatic  #149 // java.lang.Float.compare:(FF)I
        //     74: ifeq  83 (offset +9)
        //     77: ldc  #3 // -2126995105
        //     79: ldc  #3 // -2126995105
        //     81: ixor
        //     82: ireturn
        //     83: aload_0
        //     84: invokevirtual  #130 // dev.angelvisuals.a.bl.A:()I
        //     87: aload_2
        //     88: invokevirtual  #130 // dev.angelvisuals.a.bl.A:()I
        //     91: if_icmpeq  100 (offset +9)
        //     94: ldc  #68 // 1465481569
        //     96: ldc  #68 // 1465481569
        //     98: ixor
        //     99: ireturn
        //    100: aload_0
        //    101: invokevirtual  #131 // dev.angelvisuals.a.bl.B:()I
        //    104: aload_2
        //    105: invokevirtual  #131 // dev.angelvisuals.a.bl.B:()I
        //    108: if_icmpeq  117 (offset +9)
        //    111: ldc  #6 // -2095646703
        //    113: ldc  #6 // -2095646703
        //    115: ixor
        //    116: ireturn
        //    117: aload_0
        //    118: invokevirtual  #132 // dev.angelvisuals.a.bl.C:()I
        //    121: aload_2
        //    122: invokevirtual  #132 // dev.angelvisuals.a.bl.C:()I
        //    125: if_icmpeq  134 (offset +9)
        //    128: ldc  #54 // 353717208
        //    130: ldc  #54 // 353717208
        //    132: ixor
        //    133: ireturn
        //    134: aload_0
        //    135: invokevirtual  #133 // dev.angelvisuals.a.bl.D:()I
        //    138: aload_2
        //    139: invokevirtual  #133 // dev.angelvisuals.a.bl.D:()I
        //    142: if_icmpeq  151 (offset +9)
        //    145: ldc  #76 // 1880309730
        //    147: ldc  #76 // 1880309730
        //    149: ixor
        //    150: ireturn
        //    151: aload_0
        //    152: invokevirtual  #138 // dev.angelvisuals.a.bl.e:()Ldev/angelvisuals/a/k;
        //    155: astore_3
        //    156: aload_2
        //    157: invokevirtual  #138 // dev.angelvisuals.a.bl.e:()Ldev/angelvisuals/a/k;
        //    160: astore  4
        //    162: aload_3
        //    163: ifnonnull  177 (offset +14)
        //    166: aload  4
        //    168: ifnull  192 (offset +24)
        //    171: ldc  #40 // -468228029
        //    173: ldc  #40 // -468228029
        //    175: ixor
        //    176: ireturn
        //    177: aload_3
        //    178: aload  4
        //    180: invokevirtual  #153 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    183: ifne  192 (offset +9)
        //    186: ldc  #39 // -542184937
        //    188: ldc  #39 // -542184937
        //    190: ixor
        //    191: ireturn
        //    192: aload_0
        //    193: invokevirtual  #139 // dev.angelvisuals.a.bl.f:()Ldev/angelvisuals/a/k;
        //    196: astore  5
        //    198: aload_2
        //    199: invokevirtual  #139 // dev.angelvisuals.a.bl.f:()Ldev/angelvisuals/a/k;
        //    202: astore  6
        //    204: aload  5
        //    206: ifnonnull  217 (offset +11)
        //    209: aload  6
        //    211: ifnonnull  230 (offset +19)
        //    214: goto  236 (offset +22)
        //    217: aload  5
        //    219: aload  6
        //    221: invokevirtual  #153 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    224: ifeq  230 (offset +6)
        //    227: goto  236 (offset +9)
        //    230: ldc  #19 // -1357134687
        //    232: ldc  #19 // -1357134687
        //    234: ixor
        //    235: ireturn
        //    236: aload_0
        //    237: invokevirtual  #134 // dev.angelvisuals.a.bl.F:()Ljava/lang/String;
        //    240: astore  5
        //    242: aload_2
        //    243: invokevirtual  #134 // dev.angelvisuals.a.bl.F:()Ljava/lang/String;
        //    246: astore  6
        //    248: aload  5
        //    250: ifnonnull  264 (offset +14)
        //    253: aload  6
        //    255: ifnull  280 (offset +25)
        //    258: ldc  #20 // -1227669165
        //    260: ldc  #20 // -1227669165
        //    262: ixor
        //    263: ireturn
        //    264: aload  5
        //    266: aload  6
        //    268: invokevirtual  #153 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    271: ifne  280 (offset +9)
        //    274: ldc  #65 // 856404065
        //    276: ldc  #65 // 856404065
        //    278: ixor
        //    279: ireturn
        //    280: ldc  #55 // 360376442
        //    282: ldc  #56 // 360376443
        //    284: ixor
        //    285: ireturn
    }

    @Generated
  protected boolean method479(Object arg0) { // было: l
        return arg0 instanceof bl;
    }

    @Generated
  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #5 // -2097282165
        //      2: ldc  #4 // -2097282166
        //      4: ixor
        //      5: istore_1
        //      6: ldc  #8 // -1990725942
        //      8: ldc  #9 // -1990725941
        //     10: ixor
        //     11: istore_2
        //     12: iload_2
        //     13: ldc  #74 // 1843268760
        //     15: ldc  #75 // 1843268771
        //     17: ixor
        //     18: imul
        //     19: aload_0
        //     20: invokevirtual  #135 // dev.angelvisuals.a.bl.ag:()F
        //     23: invokestatic  #150 // java.lang.Float.floatToIntBits:(F)I
        //     26: iadd
        //     27: istore_2
        //     28: iload_2
        //     29: ldc  #70 // 1539736124
        //     31: ldc  #69 // 1539736071
        //     33: ixor
        //     34: imul
        //     35: aload_0
        //     36: invokevirtual  #136 // dev.angelvisuals.a.bl.ah:()F
        //     39: invokestatic  #150 // java.lang.Float.floatToIntBits:(F)I
        //     42: iadd
        //     43: istore_2
        //     44: iload_2
        //     45: ldc  #64 // 826951152
        //     47: ldc  #63 // 826951115
        //     49: ixor
        //     50: imul
        //     51: aload_0
        //     52: invokevirtual  #130 // dev.angelvisuals.a.bl.A:()I
        //     55: iadd
        //     56: istore_2
        //     57: iload_2
        //     58: ldc  #50 // 87704336
        //     60: ldc  #51 // 87704363
        //     62: ixor
        //     63: imul
        //     64: aload_0
        //     65: invokevirtual  #131 // dev.angelvisuals.a.bl.B:()I
        //     68: iadd
        //     69: istore_2
        //     70: iload_2
        //     71: ldc  #11 // -1988406353
        //     73: ldc  #10 // -1988406380
        //     75: ixor
        //     76: imul
        //     77: aload_0
        //     78: invokevirtual  #132 // dev.angelvisuals.a.bl.C:()I
        //     81: iadd
        //     82: istore_2
        //     83: iload_2
        //     84: ldc  #45 // -97464543
        //     86: ldc  #44 // -97464550
        //     88: ixor
        //     89: imul
        //     90: aload_0
        //     91: invokevirtual  #133 // dev.angelvisuals.a.bl.D:()I
        //     94: iadd
        //     95: istore_2
        //     96: aload_0
        //     97: invokevirtual  #138 // dev.angelvisuals.a.bl.e:()Ldev/angelvisuals/a/k;
        //    100: astore_3
        //    101: iload_2
        //    102: ldc  #36 // -556567406
        //    104: ldc  #37 // -556567383
        //    106: ixor
        //    107: imul
        //    108: aload_3
        //    109: ifnonnull  120 (offset +11)
        //    112: ldc  #28 // -961643485
        //    114: ldc  #27 // -961643512
        //    116: ixor
        //    117: goto  124 (offset +7)
        //    120: aload_3
        //    121: invokevirtual  #154 // java.lang.Object.hashCode:()I
        //    124: iadd
        //    125: istore_2
        //    126: aload_0
        //    127: invokevirtual  #139 // dev.angelvisuals.a.bl.f:()Ldev/angelvisuals/a/k;
        //    130: astore  4
        //    132: iload_2
        //    133: ldc  #13 // -1957992578
        //    135: ldc  #12 // -1957992635
        //    137: ixor
        //    138: imul
        //    139: aload  4
        //    141: ifnonnull  152 (offset +11)
        //    144: ldc  #52 // 341470614
        //    146: ldc  #53 // 341470653
        //    148: ixor
        //    149: goto  157 (offset +8)
        //    152: aload  4
        //    154: invokevirtual  #154 // java.lang.Object.hashCode:()I
        //    157: iadd
        //    158: istore_2
        //    159: aload_0
        //    160: invokevirtual  #134 // dev.angelvisuals.a.bl.F:()Ljava/lang/String;
        //    163: astore  5
        //    165: iload_2
        //    166: ldc  #42 // -122719808
        //    168: ldc  #43 // -122719749
        //    170: ixor
        //    171: imul
        //    172: aload  5
        //    174: ifnonnull  185 (offset +11)
        //    177: ldc  #33 // -700889656
        //    179: ldc  #34 // -700889629
        //    181: ixor
        //    182: goto  190 (offset +8)
        //    185: aload  5
        //    187: invokevirtual  #154 // java.lang.Object.hashCode:()I
        //    190: iadd
        //    191: istore_2
        //    192: iload_2
        //    193: ireturn
    }

    @Generated
  public String toString() {
        String var1 = String.valueOf(method461());
        return "Theme(animation=" + var1 + ", checkAnimation=" + String.valueOf(method462()) + ", x=" + ag() + ", y=" + ah() + ", name=" + method463() + ", color1=" + method464() + ", color2=" + method465() + ", fromColor1=" + method466() + ", fromColor2=" + method467() + ")";
    }

  private static int gx(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gy(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}