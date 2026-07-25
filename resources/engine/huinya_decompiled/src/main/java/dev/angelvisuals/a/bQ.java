// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bq
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA139;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ar;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.bq_ClassA160;
import dev.angelvisuals.a.cF;
import lombok.Generated;
import net.minecraft.class_3532;
import net.minecraft.class_5611;

public class bq implements cF {

    // ---- поля ----
  private String pU;
  private boolean field888; // было: W
  private boolean field889; // было: X
  private int jt;
  private float ch;
  private ar field890; // было: a
  private class_5611 field891; // было: a
  private String pV;
  private float ci;
  private long field892; // было: v
  private int ju;
  private bq_ClassA160 field893; // было: f
  private float cj;
   ClassA2 field894; // было: w
  private static final String pW = "// this jar protected by JoinerObfuscator";
  private static final String pX = "// you are reading machine-generated garbage";
  private static final String pY = "// stop. seriously. go play minecraft instead";
  private static final String pZ = "// good luck with the next 9999 classes";
  private static final String qa = "// every class watermarked, every string encrypted, every number xored";
  private static final int jv = 1659710327;
  private static final int jw = -2133000199;
  private static final int jx = -1129153715;
  private static final byte[] ce;

    static {
        ce = "/\\j^^eMj%Q>TU@;*$C1`U-v7I&G=: N6O@ 0r,VDXQ:xrqm'iqqz?*P)A`-Cs$xA0$cpz+h[ +]b(3zD52Njew\\EYC',}l.VA(]+)q{h4MbP]2@Wlyc~.U\"u>!0/!768y#_K%jHmz,?9*e(g'Pk1Qg^<o s'EC:$+hFC$BYQr3vc;)oOZ/%(B#;IHRzkzKvbcY{25)f|d3A&K|\\V}YJDPRyHe#: $O5l*&s<9@WErCtEF7q<N(]\\7H%}6.*.MYiY".getBytes("ISO-8859-1");
    }

  public bq(class_5611 arg0, ar arg1, String arg2, float arg3) { // было: <init>
        super();
        pU = Decryptor.method1945(XorDecoder.method1946("XL\\¸US¸·p?ª¡l[²i\rÙ=", 630388205 ^ 628501382));
        field892 = System.currentTimeMillis();
        ju = -720332271 ^ -1427151378;
        field893 = bq_ClassA160.field882;
        cj = 0.0f;
        field894 = new ClassA2(-8046452367838853854L ^ -8046452367838853966L, 0.20000000298023224f, aH.field19);
        field890 = arg1;
        pV = arg2;
        ci = arg3;
        field891 = arg0;
    }

  public void method1562(ap arg0, float arg1, float arg2, bp arg3, bp arg4) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: new  #212 // net.minecraft.class_5611
        //      4: dup
        //      5: fload_2
        //      6: fload_3
        //      7: invokespecial  #303 // net.minecraft.class_5611.<init>:(FF)V
        //     10: putfield  #229 // dev.angelvisuals.a.bq.a:Lnet/minecraft/class_5611;
        //     13: aload_0
        //     14: aload_0
        //     15: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //     18: ldc  #142 // 1410800396
        //     20: ldc  #142 // 1410800396
        //     22: ixor
        //     23: aload_0
        //     24: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //     27: invokevirtual  #287 // java.lang.String.length:()I
        //     30: invokestatic  #301 // net.minecraft.class_3532.method_15340:(III)I
        //     33: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //     36: aload_0
        //     37: fload_2
        //     38: putfield  #231 // dev.angelvisuals.a.bq.ch:F
        //     41: aload_0
        //     42: invokevirtual  #252 // dev.angelvisuals.a.bq.M:()Z
        //     45: istore  6
        //     47: fconst_0
        //     48: fstore  7
        //     50: iload  6
        //     52: ifne  84 (offset +32)
        //     55: aload_0
        //     56: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //     59: ldc  #2 // -2109779211
        //     61: ldc  #2 // -2109779211
        //     63: ixor
        //     64: aload_0
        //     65: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //     68: invokevirtual  #288 // java.lang.String.substring:(II)Ljava/lang/String;
        //     71: astore  8
        //     73: aload_0
        //     74: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //     77: aload  8
        //     79: invokevirtual  #249 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //     82: fstore  7
        //     84: aload_0
        //     85: getfield  #232 // dev.angelvisuals.a.bq.ci:F
        //     88: fstore  8
        //     90: ldc  #23 // -1573416872
        //     92: ldc  #23 // -1573416872
        //     94: ixor
        //     95: istore  9
        //     97: aload_0
        //     98: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    101: aload_0
        //    102: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    105: iload  9
        //    107: aload_0
        //    108: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    111: invokevirtual  #288 // java.lang.String.substring:(II)Ljava/lang/String;
        //    114: invokevirtual  #249 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    117: fload  8
        //    119: fcmpl
        //    120: ifle  129 (offset +9)
        //    123: iinc  9, 1
        //    126: goto  97 (offset -29)
        //    129: aload_0
        //    130: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    133: istore  10
        //    135: iload  10
        //    137: aload_0
        //    138: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    141: invokevirtual  #287 // java.lang.String.length:()I
        //    144: if_icmpge  177 (offset +33)
        //    147: aload_0
        //    148: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    151: aload_0
        //    152: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    155: iload  9
        //    157: iload  10
        //    159: invokevirtual  #288 // java.lang.String.substring:(II)Ljava/lang/String;
        //    162: invokevirtual  #249 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    165: fload  8
        //    167: fcmpg
        //    168: ifge  177 (offset +9)
        //    171: iinc  10, 1
        //    174: goto  135 (offset -39)
        //    177: aload_0
        //    178: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    181: iload  9
        //    183: iload  10
        //    185: invokevirtual  #288 // java.lang.String.substring:(II)Ljava/lang/String;
        //    188: astore  11
        //    190: iload  6
        //    192: ifeq  214 (offset +22)
        //    195: aload_1
        //    196: aload_0
        //    197: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    200: aload_0
        //    201: getfield  #239 // dev.angelvisuals.a.bq.pV:Ljava/lang/String;
        //    204: fload_2
        //    205: fload_3
        //    206: aload  5
        //    208: invokevirtual  #248 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    211: goto  228 (offset +17)
        //    214: aload_1
        //    215: aload_0
        //    216: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    219: aload  11
        //    221: fload_2
        //    222: fload_3
        //    223: aload  4
        //    225: invokevirtual  #248 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    228: aload_0
        //    229: getfield  #226 // dev.angelvisuals.a.bq.W:Z
        //    232: ifeq  354 (offset +122)
        //    235: invokestatic  #295 // java.lang.System.currentTimeMillis:()J
        //    238: aload_0
        //    239: getfield  #240 // dev.angelvisuals.a.bq.v:J
        //    242: lsub
        //    243: ldc2_w  #219 // -8035883750154547490L
        //    246: ldc2_w  #217 // -8035883750154547690L
        //    249: lxor
        //    250: lcmp
        //    251: ifle  354 (offset +103)
        //    254: aload_0
        //    255: getfield  #231 // dev.angelvisuals.a.bq.ch:F
        //    258: fload  7
        //    260: fadd
        //    261: aload_0
        //    262: getfield  #233 // dev.angelvisuals.a.bq.cj:F
        //    265: fsub
        //    266: fstore  12
        //    268: aload_0
        //    269: getfield  #241 // dev.angelvisuals.a.bq.w:Ldev/angelvisuals/a/k;
        //    272: ldc2_w  #221 // -7200022694912430846L
        //    275: ldc2_w  #223 // -7200022694912430600L
        //    278: lxor
        //    279: invokevirtual  #274 // dev.angelvisuals.a.k.a:(J)V
        //    282: aload_1
        //    283: fload  12
        //    285: fload_3
        //    286: fconst_1
        //    287: fsub
        //    288: fconst_1
        //    289: aload_0
        //    290: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    293: invokevirtual  #250 // dev.angelvisuals.a.ar.z:()F
        //    296: fconst_2
        //    297: fadd
        //    298: aload  4
        //    300: aload_0
        //    301: getfield  #241 // dev.angelvisuals.a.bq.w:Ldev/angelvisuals/a/k;
        //    304: aload_0
        //    305: getfield  #241 // dev.angelvisuals.a.bq.w:Ldev/angelvisuals/a/k;
        //    308: invokevirtual  #275 // dev.angelvisuals.a.k.e:()F
        //    311: ldc  #171 // 0.20000000298023224f
        //    313: fcmpl
        //    314: ifne  321 (offset +7)
        //    317: fconst_1
        //    318: goto  345 (offset +27)
        //    321: aload_0
        //    322: getfield  #241 // dev.angelvisuals.a.bq.w:Ldev/angelvisuals/a/k;
        //    325: invokevirtual  #275 // dev.angelvisuals.a.k.e:()F
        //    328: fconst_1
        //    329: fcmpl
        //    330: ifne  338 (offset +8)
        //    333: ldc  #171 // 0.20000000298023224f
        //    335: goto  345 (offset +10)
        //    338: aload_0
        //    339: getfield  #241 // dev.angelvisuals.a.bq.w:Ldev/angelvisuals/a/k;
        //    342: invokevirtual  #276 // dev.angelvisuals.a.k.g:()F
        //    345: invokevirtual  #273 // dev.angelvisuals.a.k.a:(F)F
        //    348: invokevirtual  #251 // dev.angelvisuals.a.bp.c:(F)Ldev/angelvisuals/a/bp;
        //    351: invokevirtual  #247 // dev.angelvisuals.a.ap.a:(FFFFLdev/angelvisuals/a/bp;)V
        //    354: aload_0
        //    355: getfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    358: ifeq  398 (offset +40)
        //    361: aload_1
        //    362: fload_2
        //    363: fconst_1
        //    364: fsub
        //    365: fload_3
        //    366: fconst_1
        //    367: fsub
        //    368: aload_0
        //    369: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    372: aload  11
        //    374: invokevirtual  #249 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    377: fconst_2
        //    378: fadd
        //    379: aload_0
        //    380: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //    383: invokevirtual  #250 // dev.angelvisuals.a.ar.z:()F
        //    386: fconst_2
        //    387: fadd
        //    388: aload  5
        //    390: ldc  #172 // 0.5f
        //    392: invokevirtual  #251 // dev.angelvisuals.a.bp.c:(F)Ldev/angelvisuals/a/bp;
        //    395: invokevirtual  #247 // dev.angelvisuals.a.ap.a:(FFFFLdev/angelvisuals/a/bp;)V
        //    398: return
    }

  public void method1563(double arg0, double arg1, ClassA139 arg2) { // было: c
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #261 // dev.angelvisuals.a.bq.a:()Lnet/minecraft/class_5611;
        //      4: astore  6
        //      6: aload_0
        //      7: aload  5
        //      9: invokevirtual  #246 // dev.angelvisuals.a.D.c:()I
        //     12: ifne  60 (offset +48)
        //     15: dload_1
        //     16: dload_3
        //     17: aload  6
        //     19: invokevirtual  #304 // net.minecraft.class_5611.method_32118:()F
        //     22: f2d
        //     23: aload  6
        //     25: invokevirtual  #305 // net.minecraft.class_5611.method_32119:()F
        //     28: fconst_1
        //     29: fsub
        //     30: f2d
        //     31: aload_0
        //     32: getfield  #232 // dev.angelvisuals.a.bq.ci:F
        //     35: f2d
        //     36: aload_0
        //     37: getfield  #228 // dev.angelvisuals.a.bq.a:Ldev/angelvisuals/a/ar;
        //     40: invokevirtual  #250 // dev.angelvisuals.a.ar.z:()F
        //     43: fconst_2
        //     44: fadd
        //     45: f2d
        //     46: invokestatic  #271 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //     49: ifeq  60 (offset +11)
        //     52: ldc  #101 // -113922705
        //     54: ldc  #100 // -113922706
        //     56: ixor
        //     57: goto  65 (offset +8)
        //     60: ldc  #89 // -258496318
        //     62: ldc  #89 // -258496318
        //     64: ixor
        //     65: putfield  #226 // dev.angelvisuals.a.bq.W:Z
        //     68: aload_0
        //     69: getfield  #226 // dev.angelvisuals.a.bq.W:Z
        //     72: ifeq  84 (offset +12)
        //     75: aload_0
        //     76: ldc  #135 // 1137165609
        //     78: ldc  #135 // 1137165609
        //     80: ixor
        //     81: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //     84: return
    }

  public boolean method1564(int arg0, int arg1, int arg2) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #226 // dev.angelvisuals.a.bq.W:Z
        //      4: ifne  13 (offset +9)
        //      7: ldc  #162 // 2009405823
        //      9: ldc  #162 // 2009405823
        //     11: ixor
        //     12: ireturn
        //     13: aload_0
        //     14: invokestatic  #295 // java.lang.System.currentTimeMillis:()J
        //     17: putfield  #240 // dev.angelvisuals.a.bq.v:J
        //     20: aload_0
        //     21: aload_0
        //     22: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //     25: ldc  #33 // -1350216900
        //     27: ldc  #33 // -1350216900
        //     29: ixor
        //     30: aload_0
        //     31: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //     34: invokevirtual  #287 // java.lang.String.length:()I
        //     37: invokestatic  #301 // net.minecraft.class_3532.method_15340:(III)I
        //     40: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //     43: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //     46: invokevirtual  #300 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     49: invokevirtual  #297 // net.minecraft.class_1041.method_4490:()J
        //     52: ldc  #117 // 447575816
        //     54: ldc  #116 // 447575645
        //     56: ixor
        //     57: invokestatic  #302 // net.minecraft.class_3675.method_15987:(JI)Z
        //     60: ifeq  232 (offset +172)
        //     63: iload_1
        //     64: ldc  #150 // 1520178251
        //     66: ldc  #149 // 1520178205
        //     68: ixor
        //     69: if_icmpne  161 (offset +92)
        //     72: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //     75: getfield  #243 // net.minecraft.class_310.field_1774:Lnet/minecraft/class_309;
        //     78: invokevirtual  #299 // net.minecraft.class_309.method_1460:()Ljava/lang/String;
        //     81: astore  4
        //     83: aload_0
        //     84: getfield  #227 // dev.angelvisuals.a.bq.X:Z
        //     87: ifeq  125 (offset +38)
        //     90: aload_0
        //     91: ldc  #183 // '[v\x04\x89_G\x1f\x90PG µ<U6©Xh%¬\x0e`Nø'
        //     93: ldc  #45 // -1015947951
        //     95: ldc  #110 // 100858937
        //     97: ixor
        //     98: invokestatic  #245 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    101: invokestatic  #244 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    104: putfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    107: aload_0
        //    108: ldc  #97 // -221457843
        //    110: ldc  #97 // -221457843
        //    112: ixor
        //    113: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    116: aload_0
        //    117: ldc  #139 // 1322542361
        //    119: ldc  #139 // 1322542361
        //    121: ixor
        //    122: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    125: aload_0
        //    126: aload  4
        //    128: aload_0
        //    129: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    132: invokevirtual  #265 // dev.angelvisuals.a.bq.b:(Ljava/lang/String;I)V
        //    135: aload_0
        //    136: dup
        //    137: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    140: aload  4
        //    142: invokevirtual  #287 // java.lang.String.length:()I
        //    145: iadd
        //    146: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    149: aload_0
        //    150: ldc  #10 // -1797313838
        //    152: ldc  #10 // -1797313838
        //    154: ixor
        //    155: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    158: goto  614 (offset +456)
        //    161: iload_1
        //    162: ldc  #16 // -1733365912
        //    164: ldc  #15 // -1733365975
        //    166: ixor
        //    167: if_icmpne  193 (offset +26)
        //    170: aload_0
        //    171: ldc  #90 // -257579810
        //    173: ldc  #91 // -257579809
        //    175: ixor
        //    176: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    179: aload_0
        //    180: aload_0
        //    181: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    184: invokevirtual  #287 // java.lang.String.length:()I
        //    187: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    190: goto  614 (offset +424)
        //    193: iload_1
        //    194: ldc  #41 // -1098577730
        //    196: ldc  #42 // -1098577667
        //    198: ixor
        //    199: if_icmpne  614 (offset +415)
        //    202: aload_0
        //    203: getfield  #226 // dev.angelvisuals.a.bq.W:Z
        //    206: ifeq  614 (offset +408)
        //    209: aload_0
        //    210: getfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    213: ifeq  614 (offset +401)
        //    216: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //    219: getfield  #243 // net.minecraft.class_310.field_1774:Lnet/minecraft/class_309;
        //    222: aload_0
        //    223: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    226: invokevirtual  #298 // net.minecraft.class_309.method_1455:(Ljava/lang/String;)V
        //    229: goto  614 (offset +385)
        //    232: iload_1
        //    233: ldc  #166 // 2027168993
        //    235: ldc  #167 // 2027169252
        //    237: ixor
        //    238: if_icmpne  277 (offset +39)
        //    241: aload_0
        //    242: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    245: invokevirtual  #286 // java.lang.String.isEmpty:()Z
        //    248: ifne  277 (offset +29)
        //    251: aload_0
        //    252: aload_0
        //    253: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    256: ldc  #57 // -896435941
        //    258: ldc  #56 // -896435942
        //    260: ixor
        //    261: iadd
        //    262: invokevirtual  #269 // dev.angelvisuals.a.bq.p:(I)V
        //    265: aload_0
        //    266: ldc  #170 // 2082083682
        //    268: ldc  #170 // 2082083682
        //    270: ixor
        //    271: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    274: goto  614 (offset +340)
        //    277: iload_1
        //    278: ldc  #9 // -1854268641
        //    280: ldc  #8 // -1854268900
        //    282: ixor
        //    283: if_icmpne  425 (offset +142)
        //    286: aload_0
        //    287: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    290: invokevirtual  #286 // java.lang.String.isEmpty:()Z
        //    293: ifne  425 (offset +132)
        //    296: aload_0
        //    297: getfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    300: ifeq  341 (offset +41)
        //    303: aload_0
        //    304: ldc  #180 // 'L\x91\x1fÿH\xa0\x04æG\xa0;Ã+²-ßO\x8f>Ú\x19\x87U\x8e'
        //    306: ldc  #38 // -1136635832
        //    308: ldc  #112 // 254318135
        //    310: ixor
        //    311: invokestatic  #245 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    314: invokestatic  #244 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    317: putfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    320: aload_0
        //    321: ldc  #131 // 976509427
        //    323: ldc  #131 // 976509427
        //    325: ixor
        //    326: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    329: aload_0
        //    330: ldc  #76 // -549388487
        //    332: ldc  #76 // -549388487
        //    334: ixor
        //    335: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    338: goto  614 (offset +276)
        //    341: aload_0
        //    342: aload_0
        //    343: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    346: invokevirtual  #269 // dev.angelvisuals.a.bq.p:(I)V
        //    349: aload_0
        //    350: dup
        //    351: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    354: ldc  #105 // 31580680
        //    356: ldc  #106 // 31580681
        //    358: ixor
        //    359: isub
        //    360: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    363: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //    366: invokevirtual  #300 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //    369: invokevirtual  #297 // net.minecraft.class_1041.method_4490:()J
        //    372: ldc  #58 // -884583925
        //    374: ldc  #59 // -884583586
        //    376: ixor
        //    377: invokestatic  #302 // net.minecraft.class_3675.method_15987:(JI)Z
        //    380: ifeq  614 (offset +234)
        //    383: aload_0
        //    384: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    387: invokevirtual  #286 // java.lang.String.isEmpty:()Z
        //    390: ifne  614 (offset +224)
        //    393: aload_0
        //    394: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    397: ifle  614 (offset +217)
        //    400: aload_0
        //    401: aload_0
        //    402: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    405: invokevirtual  #269 // dev.angelvisuals.a.bq.p:(I)V
        //    408: aload_0
        //    409: dup
        //    410: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    413: ldc  #27 // -1544620210
        //    415: ldc  #28 // -1544620209
        //    417: ixor
        //    418: isub
        //    419: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    422: goto  383 (offset -39)
        //    425: iload_1
        //    426: ldc  #4 // -1937435449
        //    428: ldc  #5 // -1937435199
        //    430: ixor
        //    431: if_icmpne  491 (offset +60)
        //    434: aload_0
        //    435: dup
        //    436: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    439: ldc  #140 // 1328843912
        //    441: ldc  #141 // 1328843913
        //    443: ixor
        //    444: iadd
        //    445: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    448: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //    451: invokevirtual  #300 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //    454: invokevirtual  #297 // net.minecraft.class_1041.method_4490:()J
        //    457: ldc  #103 // -83521259
        //    459: ldc  #102 // -83521472
        //    461: ixor
        //    462: invokestatic  #302 // net.minecraft.class_3675.method_15987:(JI)Z
        //    465: ifeq  479 (offset +14)
        //    468: aload_0
        //    469: aload_0
        //    470: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    473: invokevirtual  #287 // java.lang.String.length:()I
        //    476: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    479: aload_0
        //    480: ldc  #32 // -1368097203
        //    482: ldc  #32 // -1368097203
        //    484: ixor
        //    485: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    488: goto  614 (offset +126)
        //    491: iload_1
        //    492: ldc  #92 // -251810154
        //    494: ldc  #93 // -251809903
        //    496: ixor
        //    497: if_icmpne  555 (offset +58)
        //    500: aload_0
        //    501: dup
        //    502: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    505: ldc  #95 // -246263925
        //    507: ldc  #94 // -246263926
        //    509: ixor
        //    510: isub
        //    511: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    514: getstatic  #237 // dev.angelvisuals.a.bq.mc:Lnet/minecraft/class_310;
        //    517: invokevirtual  #300 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //    520: invokevirtual  #297 // net.minecraft.class_1041.method_4490:()J
        //    523: ldc  #113 // 257496237
        //    525: ldc  #114 // 257496568
        //    527: ixor
        //    528: invokestatic  #302 // net.minecraft.class_3675.method_15987:(JI)Z
        //    531: ifeq  543 (offset +12)
        //    534: aload_0
        //    535: ldc  #22 // -1690186077
        //    537: ldc  #22 // -1690186077
        //    539: ixor
        //    540: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    543: aload_0
        //    544: ldc  #152 // 1658839043
        //    546: ldc  #152 // 1658839043
        //    548: ixor
        //    549: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    552: goto  614 (offset +62)
        //    555: iload_1
        //    556: ldc  #165 // 2017959769
        //    558: ldc  #164 // 2017959508
        //    560: ixor
        //    561: if_icmpne  587 (offset +26)
        //    564: aload_0
        //    565: aload_0
        //    566: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    569: invokevirtual  #287 // java.lang.String.length:()I
        //    572: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    575: aload_0
        //    576: ldc  #161 // 1959315709
        //    578: ldc  #161 // 1959315709
        //    580: ixor
        //    581: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    584: goto  614 (offset +30)
        //    587: iload_1
        //    588: ldc  #169 // 2073461749
        //    590: ldc  #168 // 2073461497
        //    592: ixor
        //    593: if_icmpne  614 (offset +21)
        //    596: aload_0
        //    597: ldc  #96 // -227378957
        //    599: ldc  #96 // -227378957
        //    601: ixor
        //    602: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    605: aload_0
        //    606: ldc  #123 // 809819134
        //    608: ldc  #123 // 809819134
        //    610: ixor
        //    611: putfield  #227 // dev.angelvisuals.a.bq.X:Z
        //    614: aload_0
        //    615: aload_0
        //    616: getfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    619: ldc  #111 // 153934978
        //    621: ldc  #111 // 153934978
        //    623: ixor
        //    624: aload_0
        //    625: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    628: invokevirtual  #287 // java.lang.String.length:()I
        //    631: invokestatic  #301 // net.minecraft.class_3532.method_15340:(III)I
        //    634: putfield  #235 // dev.angelvisuals.a.bq.jt:I
        //    637: ldc  #19 // -1719293323
        //    639: ldc  #18 // -1719293324
        //    641: ixor
        //    642: ireturn
    }

  public boolean method1565(char arg0, int arg1) { // было: c
        if (field888) {
            field892 = System.currentTimeMillis();
            jt = class_3532.method_15340(jt, -2009448158 ^ -2009448158, pU.length());
            if (field889) {
                pU = Decryptor.method1945(XorDecoder.method1946("Ór^×°iGØ°Vb´¢@~ÐS{8/", -1537503489 ^ -1235331041));
                jt = 1482688400 ^ 1482688400;
                field889 = -5371730 ^ -5371730;
            }
            method1566(Character.toString(arg0), jt);
            jt = jt + (-480253742 ^ -480253741);
            jt = class_3532.method_15340(jt, 1840763825 ^ 1840763825, pU.length());
            return -511514573 ^ -511514574;
        } else {
            return -1722284346 ^ -1722284346;
        }
    }

  private void method1566(String arg0, int arg1) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #202 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #291 // java.lang.StringBuilder.<init>:()V
        //      7: astore_3
        //      8: aload_1
        //      9: invokevirtual  #289 // java.lang.String.toCharArray:()[C
        //     12: astore  4
        //     14: aload  4
        //     16: arraylength
        //     17: istore  5
        //     19: ldc  #160 // 1890642219
        //     21: ldc  #160 // 1890642219
        //     23: ixor
        //     24: istore  6
        //     26: iload  6
        //     28: iload  5
        //     30: if_icmpge  65 (offset +35)
        //     33: aload  4
        //     35: iload  6
        //     37: caload
        //     38: istore  7
        //     40: aload_0
        //     41: getfield  #234 // dev.angelvisuals.a.bq.f:Ldev/angelvisuals/a/bq$a;
        //     44: iload  7
        //     46: invokevirtual  #270 // dev.angelvisuals.a.bq$a.a:(C)Z
        //     49: ifeq  59 (offset +10)
        //     52: aload_3
        //     53: iload  7
        //     55: invokevirtual  #292 // java.lang.StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //     58: pop
        //     59: iinc  6, 1
        //     62: goto  26 (offset -36)
        //     65: aload_3
        //     66: invokevirtual  #294 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     69: astore  6
        //     71: aload_0
        //     72: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //     75: invokevirtual  #287 // java.lang.String.length:()I
        //     78: aload  6
        //     80: invokevirtual  #287 // java.lang.String.length:()I
        //     83: iadd
        //     84: aload_0
        //     85: getfield  #236 // dev.angelvisuals.a.bq.ju:I
        //     88: if_icmple  133 (offset +45)
        //     91: aload_0
        //     92: getfield  #236 // dev.angelvisuals.a.bq.ju:I
        //     95: aload_0
        //     96: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //     99: invokevirtual  #287 // java.lang.String.length:()I
        //    102: isub
        //    103: istore  5
        //    105: iload  5
        //    107: ifgt  111 (offset +4)
        //    110: return
        //    111: aload  6
        //    113: ldc  #30 // -1510131505
        //    115: ldc  #30 // -1510131505
        //    117: ixor
        //    118: iload  5
        //    120: aload  6
        //    122: invokevirtual  #287 // java.lang.String.length:()I
        //    125: invokestatic  #280 // java.lang.Math.min:(II)I
        //    128: invokevirtual  #288 // java.lang.String.substring:(II)Ljava/lang/String;
        //    131: astore  6
        //    133: new  #202 // java.lang.StringBuilder
        //    136: dup
        //    137: invokespecial  #291 // java.lang.StringBuilder.<init>:()V
        //    140: astore  7
        //    142: ldc  #85 // -393507459
        //    144: ldc  #85 // -393507459
        //    146: ixor
        //    147: istore  8
        //    149: ldc  #65 // -810292483
        //    151: ldc  #65 // -810292483
        //    153: ixor
        //    154: istore  9
        //    156: iload  9
        //    158: aload_0
        //    159: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    162: invokevirtual  #287 // java.lang.String.length:()I
        //    165: if_icmpge  210 (offset +45)
        //    168: iload  9
        //    170: iload_2
        //    171: if_icmpne  189 (offset +18)
        //    174: ldc  #108 // 92830421
        //    176: ldc  #107 // 92830420
        //    178: ixor
        //    179: istore  8
        //    181: aload  7
        //    183: aload  6
        //    185: invokevirtual  #293 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    188: pop
        //    189: aload  7
        //    191: aload_0
        //    192: getfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    195: iload  9
        //    197: invokevirtual  #284 // java.lang.String.charAt:(I)C
        //    200: invokevirtual  #292 // java.lang.StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    203: pop
        //    204: iinc  9, 1
        //    207: goto  156 (offset -51)
        //    210: iload  8
        //    212: ifne  223 (offset +11)
        //    215: aload  7
        //    217: aload  6
        //    219: invokevirtual  #293 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    222: pop
        //    223: aload_0
        //    224: aload  7
        //    226: invokevirtual  #294 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    229: putfield  #238 // dev.angelvisuals.a.bq.pU:Ljava/lang/String;
        //    232: return
    }

  private void method1567(int arg0) { // было: p
        StringBuilder var2 = new StringBuilder();
        int var3 = 591534321 ^ 591534321;
        while (var3 < pU.length()) {
            if (var3 != arg0 - (-865976678 ^ -865976677)) {
                var2.append(pU.charAt(var3));
            }
            ++var3;
            continue;
        }
        pU = var2.toString();
    }

  public boolean method1568() { // было: M
        return pU.isEmpty();
    }

    @Generated
  public String method1569() { // было: Q
        return pU;
    }

    @Generated
  public boolean method1570() { // было: N
        return field888;
    }

    @Generated
  public boolean method1571() { // было: O
        return field889;
    }

    @Generated
  public int method1572() { // было: Q
        return jt;
    }

    @Generated
  public float as() {
        return ch;
    }

    @Generated
  public ar method1573() { // было: a
        return field890;
    }

    @Generated
  public class_5611 method1574() { // было: a
        return field891;
    }

    @Generated
  public String method1575() { // было: R
        return pV;
    }

    @Generated
  public float at() {
        return ci;
    }

    @Generated
  public long method1576() { // было: k
        return field892;
    }

    @Generated
  public int method1577() { // было: R
        return ju;
    }

    @Generated
  public bq_ClassA160 method1578() { // было: a
        return field893;
    }

    @Generated
  public float au() {
        return cj;
    }

    @Generated
  public ClassA2 method1579() { // было: g
        return field894;
    }

    @Generated
  public void method1580(String arg0) { // было: f
        pU = arg0;
    }

    @Generated
  public void method1581(boolean arg0) { // было: k
        field888 = arg0;
    }

    @Generated
  public void method1582(boolean arg0) { // было: l
        field889 = arg0;
    }

    @Generated
  public void method1583(int arg0) { // было: q
        jt = arg0;
    }

    @Generated
  public void method1584(float arg0) { // было: B
        ch = arg0;
    }

    @Generated
  public void method1585(ar arg0) { // было: a
        field890 = arg0;
    }

    @Generated
  public void method1586(class_5611 arg0) { // было: a
        field891 = arg0;
    }

    @Generated
  public void method1587(String arg0) { // было: g
        pV = arg0;
    }

    @Generated
  public void method1588(float arg0) { // было: C
        ci = arg0;
    }

    @Generated
  public void method1589(long arg0) { // было: d
        field892 = arg0;
    }

    @Generated
  public void method1590(int arg0) { // было: r
        ju = arg0;
    }

    @Generated
  public void method1591(bq_ClassA160 arg0) { // было: a
        field893 = arg0;
    }

    @Generated
  public void method1592(float arg0) { // было: D
        cj = arg0;
    }

    @Generated
  public void method1593(ClassA2 arg0) { // было: c
        field894 = arg0;
    }

    @Generated
  public boolean equals(Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: if_acmpne  11 (offset +9)
        //      5: ldc  #12 // -1797044763
        //      7: ldc  #11 // -1797044764
        //      9: ixor
        //     10: ireturn
        //     11: aload_1
        //     12: instanceof  #192 // dev.angelvisuals.a.bq
        //     15: ifne  24 (offset +9)
        //     18: ldc  #88 // -321323689
        //     20: ldc  #88 // -321323689
        //     22: ixor
        //     23: ireturn
        //     24: aload_1
        //     25: checkcast  #192 // dev.angelvisuals.a.bq
        //     28: astore_2
        //     29: aload_2
        //     30: aload_0
        //     31: invokevirtual  #268 // dev.angelvisuals.a.bq.o:(Ljava/lang/Object;)Z
        //     34: ifne  43 (offset +9)
        //     37: ldc  #163 // 2016914920
        //     39: ldc  #163 // 2016914920
        //     41: ixor
        //     42: ireturn
        //     43: aload_0
        //     44: invokevirtual  #253 // dev.angelvisuals.a.bq.N:()Z
        //     47: aload_2
        //     48: invokevirtual  #253 // dev.angelvisuals.a.bq.N:()Z
        //     51: if_icmpeq  60 (offset +9)
        //     54: ldc  #151 // 1630032273
        //     56: ldc  #151 // 1630032273
        //     58: ixor
        //     59: ireturn
        //     60: aload_0
        //     61: invokevirtual  #254 // dev.angelvisuals.a.bq.O:()Z
        //     64: aload_2
        //     65: invokevirtual  #254 // dev.angelvisuals.a.bq.O:()Z
        //     68: if_icmpeq  77 (offset +9)
        //     71: ldc  #134 // 1102275071
        //     73: ldc  #134 // 1102275071
        //     75: ixor
        //     76: ireturn
        //     77: aload_0
        //     78: invokevirtual  #255 // dev.angelvisuals.a.bq.Q:()I
        //     81: aload_2
        //     82: invokevirtual  #255 // dev.angelvisuals.a.bq.Q:()I
        //     85: if_icmpeq  94 (offset +9)
        //     88: ldc  #34 // -1259278659
        //     90: ldc  #34 // -1259278659
        //     92: ixor
        //     93: ireturn
        //     94: aload_0
        //     95: invokevirtual  #262 // dev.angelvisuals.a.bq.as:()F
        //     98: aload_2
        //     99: invokevirtual  #262 // dev.angelvisuals.a.bq.as:()F
        //    102: invokestatic  #278 // java.lang.Float.compare:(FF)I
        //    105: ifeq  114 (offset +9)
        //    108: ldc  #122 // 771066669
        //    110: ldc  #122 // 771066669
        //    112: ixor
        //    113: ireturn
        //    114: aload_0
        //    115: invokevirtual  #263 // dev.angelvisuals.a.bq.at:()F
        //    118: aload_2
        //    119: invokevirtual  #263 // dev.angelvisuals.a.bq.at:()F
        //    122: invokestatic  #278 // java.lang.Float.compare:(FF)I
        //    125: ifeq  134 (offset +9)
        //    128: ldc  #115 // 296050493
        //    130: ldc  #115 // 296050493
        //    132: ixor
        //    133: ireturn
        //    134: aload_0
        //    135: invokevirtual  #267 // dev.angelvisuals.a.bq.k:()J
        //    138: aload_2
        //    139: invokevirtual  #267 // dev.angelvisuals.a.bq.k:()J
        //    142: lcmp
        //    143: ifeq  152 (offset +9)
        //    146: ldc  #156 // 1824383138
        //    148: ldc  #156 // 1824383138
        //    150: ixor
        //    151: ireturn
        //    152: aload_0
        //    153: invokevirtual  #257 // dev.angelvisuals.a.bq.R:()I
        //    156: aload_2
        //    157: invokevirtual  #257 // dev.angelvisuals.a.bq.R:()I
        //    160: if_icmpeq  169 (offset +9)
        //    163: ldc  #62 // -852742926
        //    165: ldc  #62 // -852742926
        //    167: ixor
        //    168: ireturn
        //    169: aload_0
        //    170: invokevirtual  #264 // dev.angelvisuals.a.bq.au:()F
        //    173: aload_2
        //    174: invokevirtual  #264 // dev.angelvisuals.a.bq.au:()F
        //    177: invokestatic  #278 // java.lang.Float.compare:(FF)I
        //    180: ifeq  189 (offset +9)
        //    183: ldc  #43 // -1096576974
        //    185: ldc  #43 // -1096576974
        //    187: ixor
        //    188: ireturn
        //    189: aload_0
        //    190: invokevirtual  #256 // dev.angelvisuals.a.bq.Q:()Ljava/lang/String;
        //    193: astore_3
        //    194: aload_2
        //    195: invokevirtual  #256 // dev.angelvisuals.a.bq.Q:()Ljava/lang/String;
        //    198: astore  4
        //    200: aload_3
        //    201: ifnonnull  212 (offset +11)
        //    204: aload  4
        //    206: ifnonnull  224 (offset +18)
        //    209: goto  230 (offset +21)
        //    212: aload_3
        //    213: aload  4
        //    215: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    218: ifeq  224 (offset +6)
        //    221: goto  230 (offset +9)
        //    224: ldc  #40 // -1108077064
        //    226: ldc  #40 // -1108077064
        //    228: ixor
        //    229: ireturn
        //    230: aload_0
        //    231: invokevirtual  #259 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/ar;
        //    234: astore_3
        //    235: aload_2
        //    236: invokevirtual  #259 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/ar;
        //    239: astore  4
        //    241: aload_3
        //    242: ifnonnull  256 (offset +14)
        //    245: aload  4
        //    247: ifnull  271 (offset +24)
        //    250: ldc  #145 // 1450925483
        //    252: ldc  #145 // 1450925483
        //    254: ixor
        //    255: ireturn
        //    256: aload_3
        //    257: aload  4
        //    259: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    262: ifne  271 (offset +9)
        //    265: ldc  #109 // 94972804
        //    267: ldc  #109 // 94972804
        //    269: ixor
        //    270: ireturn
        //    271: aload_0
        //    272: invokevirtual  #261 // dev.angelvisuals.a.bq.a:()Lnet/minecraft/class_5611;
        //    275: astore  5
        //    277: aload_2
        //    278: invokevirtual  #261 // dev.angelvisuals.a.bq.a:()Lnet/minecraft/class_5611;
        //    281: astore  6
        //    283: aload  5
        //    285: ifnonnull  296 (offset +11)
        //    288: aload  6
        //    290: ifnonnull  309 (offset +19)
        //    293: goto  315 (offset +22)
        //    296: aload  5
        //    298: aload  6
        //    300: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    303: ifeq  309 (offset +6)
        //    306: goto  315 (offset +9)
        //    309: ldc  #138 // 1285547223
        //    311: ldc  #138 // 1285547223
        //    313: ixor
        //    314: ireturn
        //    315: aload_0
        //    316: invokevirtual  #258 // dev.angelvisuals.a.bq.R:()Ljava/lang/String;
        //    319: astore  5
        //    321: aload_2
        //    322: invokevirtual  #258 // dev.angelvisuals.a.bq.R:()Ljava/lang/String;
        //    325: astore  6
        //    327: aload  5
        //    329: ifnonnull  343 (offset +14)
        //    332: aload  6
        //    334: ifnull  359 (offset +25)
        //    337: ldc  #121 // 711913208
        //    339: ldc  #121 // 711913208
        //    341: ixor
        //    342: ireturn
        //    343: aload  5
        //    345: aload  6
        //    347: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    350: ifne  359 (offset +9)
        //    353: ldc  #44 // -1087077604
        //    355: ldc  #44 // -1087077604
        //    357: ixor
        //    358: ireturn
        //    359: aload_0
        //    360: invokevirtual  #260 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/bq$a;
        //    363: astore  7
        //    365: aload_2
        //    366: invokevirtual  #260 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/bq$a;
        //    369: astore  8
        //    371: aload  7
        //    373: ifnonnull  384 (offset +11)
        //    376: aload  8
        //    378: ifnonnull  397 (offset +19)
        //    381: goto  403 (offset +22)
        //    384: aload  7
        //    386: aload  8
        //    388: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    391: ifeq  397 (offset +6)
        //    394: goto  403 (offset +9)
        //    397: ldc  #24 // -1560985173
        //    399: ldc  #24 // -1560985173
        //    401: ixor
        //    402: ireturn
        //    403: aload_0
        //    404: invokevirtual  #266 // dev.angelvisuals.a.bq.g:()Ldev/angelvisuals/a/k;
        //    407: astore  7
        //    409: aload_2
        //    410: invokevirtual  #266 // dev.angelvisuals.a.bq.g:()Ldev/angelvisuals/a/k;
        //    413: astore  8
        //    415: aload  7
        //    417: ifnonnull  431 (offset +14)
        //    420: aload  8
        //    422: ifnull  447 (offset +25)
        //    425: ldc  #128 // 883149089
        //    427: ldc  #128 // 883149089
        //    429: ixor
        //    430: ireturn
        //    431: aload  7
        //    433: aload  8
        //    435: invokevirtual  #282 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    438: ifne  447 (offset +9)
        //    441: ldc  #73 // -571976202
        //    443: ldc  #73 // -571976202
        //    445: ixor
        //    446: ireturn
        //    447: ldc  #36 // -1145552408
        //    449: ldc  #37 // -1145552407
        //    451: ixor
        //    452: ireturn
    }

    @Generated
  protected boolean method1594(Object arg0) { // было: o
        return arg0 instanceof bq;
    }

    @Generated
  public int hashCode() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #80 // -501132151
        //      2: ldc  #79 // -501132152
        //      4: ixor
        //      5: istore_1
        //      6: ldc  #54 // -902222804
        //      8: ldc  #55 // -902222803
        //     10: ixor
        //     11: istore_2
        //     12: iload_2
        //     13: ldc  #49 // -968153301
        //     15: ldc  #48 // -968153328
        //     17: ixor
        //     18: imul
        //     19: aload_0
        //     20: invokevirtual  #253 // dev.angelvisuals.a.bq.N:()Z
        //     23: ifeq  34 (offset +11)
        //     26: ldc  #47 // -990100386
        //     28: ldc  #46 // -990100463
        //     30: ixor
        //     31: goto  39 (offset +8)
        //     34: ldc  #137 // 1224761808
        //     36: ldc  #136 // 1224761777
        //     38: ixor
        //     39: iadd
        //     40: istore_2
        //     41: iload_2
        //     42: ldc  #71 // -595706279
        //     44: ldc  #72 // -595706270
        //     46: ixor
        //     47: imul
        //     48: aload_0
        //     49: invokevirtual  #254 // dev.angelvisuals.a.bq.O:()Z
        //     52: ifeq  63 (offset +11)
        //     55: ldc  #143 // 1428858559
        //     57: ldc  #144 // 1428858608
        //     59: ixor
        //     60: goto  68 (offset +8)
        //     63: ldc  #124 // 864676755
        //     65: ldc  #125 // 864676850
        //     67: ixor
        //     68: iadd
        //     69: istore_2
        //     70: iload_2
        //     71: ldc  #147 // 1516613078
        //     73: ldc  #148 // 1516613101
        //     75: ixor
        //     76: imul
        //     77: aload_0
        //     78: invokevirtual  #255 // dev.angelvisuals.a.bq.Q:()I
        //     81: iadd
        //     82: istore_2
        //     83: iload_2
        //     84: ldc  #154 // 1733522142
        //     86: ldc  #155 // 1733522149
        //     88: ixor
        //     89: imul
        //     90: aload_0
        //     91: invokevirtual  #262 // dev.angelvisuals.a.bq.as:()F
        //     94: invokestatic  #279 // java.lang.Float.floatToIntBits:(F)I
        //     97: iadd
        //     98: istore_2
        //     99: iload_2
        //    100: ldc  #126 // 882333975
        //    102: ldc  #127 // 882333996
        //    104: ixor
        //    105: imul
        //    106: aload_0
        //    107: invokevirtual  #263 // dev.angelvisuals.a.bq.at:()F
        //    110: invokestatic  #279 // java.lang.Float.floatToIntBits:(F)I
        //    113: iadd
        //    114: istore_2
        //    115: aload_0
        //    116: invokevirtual  #267 // dev.angelvisuals.a.bq.k:()J
        //    119: lstore_3
        //    120: iload_2
        //    121: ldc  #67 // -756321054
        //    123: ldc  #66 // -756321063
        //    125: ixor
        //    126: imul
        //    127: lload_3
        //    128: ldc  #50 // -951018469
        //    130: ldc  #51 // -951018437
        //    132: ixor
        //    133: lushr
        //    134: lload_3
        //    135: lxor
        //    136: l2i
        //    137: iadd
        //    138: istore_2
        //    139: iload_2
        //    140: ldc  #14 // -1780243350
        //    142: ldc  #13 // -1780243375
        //    144: ixor
        //    145: imul
        //    146: aload_0
        //    147: invokevirtual  #257 // dev.angelvisuals.a.bq.R:()I
        //    150: iadd
        //    151: istore_2
        //    152: iload_2
        //    153: ldc  #20 // -1711253292
        //    155: ldc  #21 // -1711253265
        //    157: ixor
        //    158: imul
        //    159: aload_0
        //    160: invokevirtual  #264 // dev.angelvisuals.a.bq.au:()F
        //    163: invokestatic  #279 // java.lang.Float.floatToIntBits:(F)I
        //    166: iadd
        //    167: istore_2
        //    168: aload_0
        //    169: invokevirtual  #256 // dev.angelvisuals.a.bq.Q:()Ljava/lang/String;
        //    172: astore  5
        //    174: iload_2
        //    175: ldc  #7 // -1871574813
        //    177: ldc  #6 // -1871574824
        //    179: ixor
        //    180: imul
        //    181: aload  5
        //    183: ifnonnull  194 (offset +11)
        //    186: ldc  #53 // -934252238
        //    188: ldc  #52 // -934252263
        //    190: ixor
        //    191: goto  199 (offset +8)
        //    194: aload  5
        //    196: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    199: iadd
        //    200: istore_2
        //    201: aload_0
        //    202: invokevirtual  #259 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/ar;
        //    205: astore  6
        //    207: iload_2
        //    208: ldc  #158 // 1849512092
        //    210: ldc  #159 // 1849512103
        //    212: ixor
        //    213: imul
        //    214: aload  6
        //    216: ifnonnull  227 (offset +11)
        //    219: ldc  #75 // -549513230
        //    221: ldc  #74 // -549513255
        //    223: ixor
        //    224: goto  232 (offset +8)
        //    227: aload  6
        //    229: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    232: iadd
        //    233: istore_2
        //    234: aload_0
        //    235: invokevirtual  #261 // dev.angelvisuals.a.bq.a:()Lnet/minecraft/class_5611;
        //    238: astore  7
        //    240: iload_2
        //    241: ldc  #84 // -454631449
        //    243: ldc  #83 // -454631460
        //    245: ixor
        //    246: imul
        //    247: aload  7
        //    249: ifnonnull  260 (offset +11)
        //    252: ldc  #133 // 1045071401
        //    254: ldc  #132 // 1045071362
        //    256: ixor
        //    257: goto  265 (offset +8)
        //    260: aload  7
        //    262: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    265: iadd
        //    266: istore_2
        //    267: aload_0
        //    268: invokevirtual  #258 // dev.angelvisuals.a.bq.R:()Ljava/lang/String;
        //    271: astore  8
        //    273: iload_2
        //    274: ldc  #64 // -844160840
        //    276: ldc  #63 // -844160893
        //    278: ixor
        //    279: imul
        //    280: aload  8
        //    282: ifnonnull  293 (offset +11)
        //    285: ldc  #68 // -748312817
        //    287: ldc  #69 // -748312796
        //    289: ixor
        //    290: goto  298 (offset +8)
        //    293: aload  8
        //    295: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    298: iadd
        //    299: istore_2
        //    300: aload_0
        //    301: invokevirtual  #260 // dev.angelvisuals.a.bq.a:()Ldev/angelvisuals/a/bq$a;
        //    304: astore  9
        //    306: iload_2
        //    307: ldc  #129 // 927382469
        //    309: ldc  #130 // 927382526
        //    311: ixor
        //    312: imul
        //    313: aload  9
        //    315: ifnonnull  326 (offset +11)
        //    318: ldc  #25 // -1552830180
        //    320: ldc  #26 // -1552830153
        //    322: ixor
        //    323: goto  331 (offset +8)
        //    326: aload  9
        //    328: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    331: iadd
        //    332: istore_2
        //    333: aload_0
        //    334: invokevirtual  #266 // dev.angelvisuals.a.bq.g:()Ldev/angelvisuals/a/k;
        //    337: astore  10
        //    339: iload_2
        //    340: ldc  #87 // -368497238
        //    342: ldc  #86 // -368497263
        //    344: ixor
        //    345: imul
        //    346: aload  10
        //    348: ifnonnull  359 (offset +11)
        //    351: ldc  #99 // -205131988
        //    353: ldc  #98 // -205132025
        //    355: ixor
        //    356: goto  364 (offset +8)
        //    359: aload  10
        //    361: invokevirtual  #283 // java.lang.Object.hashCode:()I
        //    364: iadd
        //    365: istore_2
        //    366: iload_2
        //    367: ireturn
    }

    @Generated
  public String toString() {
        String var1 = method1569();
        return "TextBox(text=" + var1 + ", selected=" + method1570() + ", selectAll=" + method1571() + ", cursor=" + method1572() + ", posX=" + as() + ", font=" + String.valueOf(method1573()) + ", position=" + String.valueOf(method1574()) + ", emptyText=" + method1575() + ", width=" + at() + ", lastInputTime=" + method1576() + ", maxLength=" + method1577() + ", charFilter=" + String.valueOf(method1578()) + ", scrollOffset=" + au() + ", animation=" + String.valueOf(method1579()) + ")";
    }

  private static int ij(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ik(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int il(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}