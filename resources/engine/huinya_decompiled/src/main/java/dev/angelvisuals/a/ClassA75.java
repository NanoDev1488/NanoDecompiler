// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.s
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA65_ClassA66;
import dev.angelvisuals.a.ClassA67_ClassA68;
import dev.angelvisuals.a.ClassA69_ClassA70;
import dev.angelvisuals.a.ClassA71_ClassA72;
import dev.angelvisuals.a.ClassA73_ClassA74;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ar;
import dev.angelvisuals.a.ay;
import dev.angelvisuals.a.ay_ClassA82;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.cK;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;

public class ClassA75 extends ay {

    // ---- поля ----
  private final ClassA2 field266; // было: B
  private final List field267; // было: y
  private static final String wg = "// number obfuscation: ENABLED (XOR masking)";
  private static final String wh = "// this jar protected by JoinerObfuscator";
  private static final String wi = "// good luck with the next 9999 classes";
  private static final String wj = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String wk = "// stop. seriously. go play minecraft instead";
  private static final int na = -1436936597;
  private static final int nb = -684710232;
  private static final int nc = 1987076231;
  private static final byte[] dj;

    static {
        dj = "fm6!5\\K_WAYUK2qJEkR~EB4C~>?~ }rxCL'P,xL3w*zb=d]'66[*}?CDAJBFdUIY}.+;d/Fmy#iQGt @$?VQxI'J*_TT9,gsxV=ckUH{E;(yi*5Wbf]aBJ2c\"O2'Ce,nq%az@H,D<Sg[Ky0UEqb52mDI9Xy5O#hXH2fo6.^&wRM3m&(nx\\KeyG.Fk ,l{XJ#9I/ f2;^U*D=H4N2~0aW[a+SI9:]1_izAsMY1c%ant$g^>+<W-3(f>S@7z~ed<AU".getBytes("ISO-8859-1");
    }

  public ClassA75(String arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, ay_ClassA82 arg7) { // было: <init>
        super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        field266 = new ClassA2(-757669321228927765L ^ -757669321228927965L, aH.field21);
        field267 = new ArrayList();
    }

  public void method527(cK arg0, boolean arg1) { // было: c
        field267.addLast(new ClassA69_ClassA70(arg0, arg1));
    }

  public void method528(String arg0, class_2561 arg1) { // было: c
        field267.addLast(new ClassA71_ClassA72(arg0, arg1));
    }

  public void method529(String arg0, boolean arg1) { // было: b
        field267.addLast(new ClassA73_ClassA74(arg0, arg1));
    }

  public void method530(String arg0, String arg1) { // было: d
        field267.addLast(new ClassA67_ClassA68(arg0, arg1));
    }

  public void method531(ap arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #137 // dev.angelvisuals.a.s.y:Ljava/util/List;
        //      4: invokeinterface  #190 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //      9: astore_2
        //     10: aload_2
        //     11: invokeinterface  #185 // java.util.Iterator.hasNext:()Z, count 1
        //     16: ifeq  125 (offset +109)
        //     19: aload_2
        //     20: invokeinterface  #186 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     25: checkcast  #96 // dev.angelvisuals.a.s$a
        //     28: astore_3
        //     29: aload_3
        //     30: getfield  #139 // dev.angelvisuals.a.s$a.e:Z
        //     33: ifne  73 (offset +40)
        //     36: invokestatic  #182 // java.lang.System.currentTimeMillis:()J
        //     39: aload_3
        //     40: getfield  #140 // dev.angelvisuals.a.s$a.g:J
        //     43: lsub
        //     44: ldc2_w  #119 // 4796449087355719634L
        //     47: ldc2_w  #117 // 4796449087355717634L
        //     50: lxor
        //     51: lcmp
        //     52: ifle  73 (offset +21)
        //     55: aload_3
        //     56: ldc  #33 // 441080880
        //     58: ldc  #34 // 441080881
        //     60: ixor
        //     61: putfield  #139 // dev.angelvisuals.a.s$a.e:Z
        //     64: aload_3
        //     65: getfield  #138 // dev.angelvisuals.a.s$a.c:Ldev/angelvisuals/a/k;
        //     68: fconst_0
        //     69: invokevirtual  #167 // dev.angelvisuals.a.k.a:(F)F
        //     72: pop
        //     73: aload_3
        //     74: getfield  #139 // dev.angelvisuals.a.s$a.e:Z
        //     77: ifeq  102 (offset +25)
        //     80: aload_3
        //     81: getfield  #138 // dev.angelvisuals.a.s$a.c:Ldev/angelvisuals/a/k;
        //     84: invokevirtual  #169 // dev.angelvisuals.a.k.e:()F
        //     87: ldc  #48 // 0.009999999776482582f
        //     89: fcmpg
        //     90: ifge  102 (offset +12)
        //     93: aload_2
        //     94: invokeinterface  #187 // java.util.Iterator.remove:()V, count 1
        //     99: goto  122 (offset +23)
        //    102: aload_3
        //    103: getfield  #138 // dev.angelvisuals.a.s$a.c:Ldev/angelvisuals/a/k;
        //    106: aload_3
        //    107: getfield  #139 // dev.angelvisuals.a.s$a.e:Z
        //    110: ifeq  117 (offset +7)
        //    113: fconst_0
        //    114: goto  118 (offset +4)
        //    117: fconst_1
        //    118: invokevirtual  #167 // dev.angelvisuals.a.k.a:(F)F
        //    121: pop
        //    122: goto  10 (offset -112)
        //    125: aload_0
        //    126: getfield  #132 // dev.angelvisuals.a.s.B:Ldev/angelvisuals/a/k;
        //    129: getstatic  #136 // dev.angelvisuals.a.s.mc:Lnet/minecraft/class_310;
        //    132: getfield  #146 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //    135: instanceof  #112 // net.minecraft.class_408
        //    138: ifne  153 (offset +15)
        //    141: aload_0
        //    142: getfield  #137 // dev.angelvisuals.a.s.y:Ljava/util/List;
        //    145: invokeinterface  #189 // java.util.List.isEmpty:()Z, count 1
        //    150: ifne  161 (offset +11)
        //    153: ldc  #29 // 260923680
        //    155: ldc  #30 // 260923681
        //    157: ixor
        //    158: goto  166 (offset +8)
        //    161: ldc  #35 // 451296606
        //    163: ldc  #35 // 451296606
        //    165: ixor
        //    166: invokevirtual  #168 // dev.angelvisuals.a.k.a:(Z)V
        //    169: aload_0
        //    170: getfield  #132 // dev.angelvisuals.a.s.B:Ldev/angelvisuals/a/k;
        //    173: invokevirtual  #169 // dev.angelvisuals.a.k.e:()F
        //    176: ldc  #48 // 0.009999999776482582f
        //    178: fcmpg
        //    179: ifge  183 (offset +4)
        //    182: return
        //    183: invokestatic  #150 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    186: invokevirtual  #151 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //    189: invokevirtual  #163 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //    192: astore_3
        //    193: getstatic  #129 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    196: ldc  #52 // 6.75f
        //    198: invokevirtual  #164 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    201: astore  4
        //    203: ldc  #55 // 18.0f
        //    205: fstore  5
        //    207: aload_0
        //    208: invokevirtual  #171 // dev.angelvisuals.a.s.N:()F
        //    211: fstore  6
        //    213: fconst_0
        //    214: fstore  7
        //    216: aload_0
        //    217: invokevirtual  #172 // dev.angelvisuals.a.s.a:()Ldev/angelvisuals/a/ay$b;
        //    220: astore  8
        //    222: aload  8
        //    224: getstatic  #126 // dev.angelvisuals.a.ay$b.h:Ldev/angelvisuals/a/ay$b;
        //    227: if_acmpeq  246 (offset +19)
        //    230: aload  8
        //    232: getstatic  #127 // dev.angelvisuals.a.ay$b.i:Ldev/angelvisuals/a/ay$b;
        //    235: if_acmpeq  246 (offset +11)
        //    238: aload  8
        //    240: getstatic  #128 // dev.angelvisuals.a.ay$b.j:Ldev/angelvisuals/a/ay$b;
        //    243: if_acmpne  254 (offset +11)
        //    246: ldc  #31 // 320803046
        //    248: ldc  #32 // 320803047
        //    250: ixor
        //    251: goto  259 (offset +8)
        //    254: ldc  #28 // 104905292
        //    256: ldc  #28 // 104905292
        //    258: ixor
        //    259: istore  9
        //    261: aload_0
        //    262: getfield  #137 // dev.angelvisuals.a.s.y:Ljava/util/List;
        //    265: invokestatic  #147 // com.google.common.collect.Lists.reverse:(Ljava/util/List;)Ljava/util/List;
        //    268: invokeinterface  #190 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    273: astore  10
        //    275: aload  10
        //    277: invokeinterface  #185 // java.util.Iterator.hasNext:()Z, count 1
        //    282: ifeq  381 (offset +99)
        //    285: aload  10
        //    287: invokeinterface  #186 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    292: checkcast  #96 // dev.angelvisuals.a.s$a
        //    295: astore  11
        //    297: aload  11
        //    299: getfield  #138 // dev.angelvisuals.a.s$a.c:Ldev/angelvisuals/a/k;
        //    302: invokevirtual  #169 // dev.angelvisuals.a.k.e:()F
        //    305: fstore  12
        //    307: fload  12
        //    309: ldc  #47 // 0.0010000000474974513f
        //    311: fcmpl
        //    312: ifle  378 (offset +66)
        //    315: aload_0
        //    316: aload_1
        //    317: aload_0
        //    318: invokevirtual  #170 // dev.angelvisuals.a.s.M:()F
        //    321: fload  6
        //    323: aload  4
        //    325: aload_3
        //    326: fload  5
        //    328: aload  11
        //    330: invokevirtual  #173 // dev.angelvisuals.a.s.a:(Ldev/angelvisuals/a/ap;FFLdev/angelvisuals/a/ar;Ldev/angelvisuals/a/bl;FLdev/angelvisuals/a/s$a;)F
        //    333: fstore  13
        //    335: fload  7
        //    337: fload  13
        //    339: invokestatic  #179 // java.lang.Math.max:(FF)F
        //    342: fstore  7
        //    344: iload  9
        //    346: ifeq  365 (offset +19)
        //    349: fload  6
        //    351: fload  5
        //    353: ldc  #50 // 4.0f
        //    355: fadd
        //    356: fload  12
        //    358: fmul
        //    359: fsub
        //    360: fstore  6
        //    362: goto  378 (offset +16)
        //    365: fload  6
        //    367: fload  5
        //    369: ldc  #50 // 4.0f
        //    371: fadd
        //    372: fload  12
        //    374: fmul
        //    375: fadd
        //    376: fstore  6
        //    378: goto  275 (offset -103)
        //    381: aload_0
        //    382: fload  7
        //    384: fconst_0
        //    385: fcmpl
        //    386: ifle  394 (offset +8)
        //    389: fload  7
        //    391: goto  396 (offset +5)
        //    394: ldc  #56 // 100.0f
        //    396: putfield  #133 // dev.angelvisuals.a.s.aM:F
        //    399: aload_0
        //    400: fload  6
        //    402: aload_0
        //    403: invokevirtual  #171 // dev.angelvisuals.a.s.N:()F
        //    406: fsub
        //    407: invokestatic  #178 // java.lang.Math.abs:(F)F
        //    410: putfield  #134 // dev.angelvisuals.a.s.aN:F
        //    413: return
    }

  private float method532(ap arg0, float arg1, float arg2, ar arg3, bl arg4, float arg5, ClassA65_ClassA66 arg6) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  7
        //      2: getfield  #138 // dev.angelvisuals.a.s$a.c:Ldev/angelvisuals/a/k;
        //      5: invokevirtual  #169 // dev.angelvisuals.a.k.e:()F
        //      8: aload_0
        //      9: getfield  #132 // dev.angelvisuals.a.s.B:Ldev/angelvisuals/a/k;
        //     12: invokevirtual  #169 // dev.angelvisuals.a.k.e:()F
        //     15: fmul
        //     16: fstore  8
        //     18: fload  8
        //     20: ldc  #47 // 0.0010000000474974513f
        //     22: fcmpg
        //     23: ifge  28 (offset +5)
        //     26: fconst_0
        //     27: freturn
        //     28: ldc  #71 // '\x84\x92>sÁ¢z8\x85\x8d\x05?\x92±\x06\rÇ\x93\x03\x07\x90©rv'
        //     30: ldc  #3 // -1891005051
        //     32: ldc  #16 // -1006209678
        //     34: ixor
        //     35: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     38: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     41: astore  9
        //     43: ldc  #70 // "g\x04«#c5°:l5\x8f\x1f��'\x99\x03d\x1a\x8a\x062\x12áR"
        //     45: ldc  #13 // -1041418668
        //     47: ldc  #8 // -1372494592
        //     49: ixor
        //     50: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     53: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     56: astore  10
        //     58: ldc  #76 // 'ÿ\x0b\x9bêû:\x80óô:¿Ö\x98(©Êü\x15ºÏª\x1dÑ\x9b'
        //     60: ldc  #45 // 1873502190
        //     62: ldc  #20 // -918093022
        //     64: ixor
        //     65: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     68: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     71: astore  11
        //     73: getstatic  #131 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //     76: astore  12
        //     78: aload  5
        //     80: invokevirtual  #159 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //     83: ldc  #57 // 255.0f
        //     85: fload  8
        //     87: fmul
        //     88: f2i
        //     89: invokevirtual  #161 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //     92: astore  13
        //     94: aload  7
        //     96: instanceof  #100 // dev.angelvisuals.a.s$e
        //     99: ifeq  274 (offset +175)
        //    102: aload  7
        //    104: checkcast  #100 // dev.angelvisuals.a.s$e
        //    107: astore  14
        //    109: aload  14
        //    111: getfield  #144 // dev.angelvisuals.a.s$e.aq:Z
        //    114: ifeq  133 (offset +19)
        //    117: ldc  #60 // '*ìqz<\x8d7GXÝbD\x07ÒU&\x13ÝlP\x1fõ8/'
        //    119: ldc  #24 // -281643706
        //    121: ldc  #27 // -46939857
        //    123: ixor
        //    124: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    127: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    130: goto  146 (offset +16)
        //    133: ldc  #72 // '\x90(êä\x82\x0b\x80¸\x890\x92ú\x94>®Ë¤6³õ£\x16ä²'
        //    135: ldc  #36 // 755738548
        //    137: ldc  #6 // -1563238318
        //    139: ixor
        //    140: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    143: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    146: astore  9
        //    148: aload  14
        //    150: getfield  #145 // dev.angelvisuals.a.s$e.e:Ldev/angelvisuals/a/cK;
        //    153: invokevirtual  #162 // dev.angelvisuals.a.cK.getName:()Ljava/lang/String;
        //    156: invokedynamic  #192 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    161: astore  10
        //    163: aload  14
        //    165: getfield  #144 // dev.angelvisuals.a.s$e.aq:Z
        //    168: ifeq  187 (offset +19)
        //    171: ldc  #74 // 'Ð\x03\x06FÙ\x1d\x05ZÄ\x7f6kÜ\x1f/sÚ\n%zä.z\x02'
        //    173: ldc  #42 // 1427680617
        //    175: ldc  #44 // 1784669396
        //    177: ixor
        //    178: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    181: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    184: goto  200 (offset +16)
        //    187: ldc  #68 // 'Tkw_Ufhu[kf\x1b=yB\x03~\x1a0ewfk\x08AdFTCb?G!qe\\y\x1eT^Cdh\r'
        //    189: ldc  #2 // -2003740778
        //    191: ldc  #9 // -1198096508
        //    193: ixor
        //    194: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    197: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    200: astore  11
        //    202: aload  14
        //    204: getfield  #144 // dev.angelvisuals.a.s$e.aq:Z
        //    207: ifeq  241 (offset +34)
        //    210: new  #90 // dev.angelvisuals.a.bp
        //    213: dup
        //    214: ldc  #5 // -1876554061
        //    216: ldc  #4 // -1876554093
        //    218: ixor
        //    219: ldc  #39 // 973908335
        //    221: ldc  #40 // 973908368
        //    223: ixor
        //    224: ldc  #18 // -990168847
        //    226: ldc  #17 // -990168879
        //    228: ixor
        //    229: ldc  #57 // 255.0f
        //    231: fload  8
        //    233: fmul
        //    234: f2i
        //    235: invokespecial  #160 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    238: goto  269 (offset +31)
        //    241: new  #90 // dev.angelvisuals.a.bp
        //    244: dup
        //    245: ldc  #10 // -1123526547
        //    247: ldc  #11 // -1123526510
        //    249: ixor
        //    250: ldc  #14 // -1021538663
        //    252: ldc  #15 // -1021538631
        //    254: ixor
        //    255: ldc  #37 // 832103814
        //    257: ldc  #38 // 832103846
        //    259: ixor
        //    260: ldc  #57 // 255.0f
        //    262: fload  8
        //    264: fmul
        //    265: f2i
        //    266: invokespecial  #160 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    269: astore  12
        //    271: goto  443 (offset +172)
        //    274: aload  7
        //    276: instanceof  #99 // dev.angelvisuals.a.s$d
        //    279: ifeq  333 (offset +54)
        //    282: aload  7
        //    284: checkcast  #99 // dev.angelvisuals.a.s$d
        //    287: astore  15
        //    289: ldc  #66 // '7\x1fm*r/)a6��Vf!<UTt\x1eP^#$!/'
        //    291: ldc  #43 // 1609835833
        //    293: ldc  #41 // 1307070589
        //    295: ixor
        //    296: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    299: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    302: astore  9
        //    304: aload  15
        //    306: getfield  #143 // dev.angelvisuals.a.s$d.c:Lnet/minecraft/class_2561;
        //    309: invokeinterface  #191 // net.minecraft.class_2561.getString:()Ljava/lang/String;, count 1
        //    314: astore  10
        //    316: getstatic  #131 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    319: ldc  #57 // 255.0f
        //    321: fload  8
        //    323: fmul
        //    324: f2i
        //    325: invokevirtual  #161 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    328: astore  12
        //    330: goto  443 (offset +113)
        //    333: aload  7
        //    335: instanceof  #98 // dev.angelvisuals.a.s$c
        //    338: ifeq  392 (offset +54)
        //    341: aload  7
        //    343: checkcast  #98 // dev.angelvisuals.a.s$c
        //    346: astore  16
        //    348: ldc  #73 // 'Â«M\x19\x87\x9b\tRÃ´vUÔ\x88ug\x81ªpmÖ\x90\x01\x1c'
        //    350: ldc  #21 // -722800884
        //    352: ldc  #26 // -170512707
        //    354: ixor
        //    355: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    358: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    361: astore  9
        //    363: aload  16
        //    365: getfield  #142 // dev.angelvisuals.a.s$c.ub:Ljava/lang/String;
        //    368: invokedynamic  #193 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    373: astore  10
        //    375: getstatic  #131 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    378: ldc  #57 // 255.0f
        //    380: fload  8
        //    382: fmul
        //    383: f2i
        //    384: invokevirtual  #161 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    387: astore  12
        //    389: goto  443 (offset +54)
        //    392: aload  7
        //    394: instanceof  #97 // dev.angelvisuals.a.s$b
        //    397: ifeq  443 (offset +46)
        //    400: aload  7
        //    402: checkcast  #97 // dev.angelvisuals.a.s$b
        //    405: astore  17
        //    407: ldc  #75 // '÷\x83 \x05²³dNö\x9c\x1bIá\xa0\x18{´\x82\x1dqã¸l��'
        //    409: ldc  #1 // -2106859766
        //    411: ldc  #12 // -1086703986
        //    413: ixor
        //    414: invokestatic  #149 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    417: invokestatic  #148 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    420: astore  9
        //    422: aload  17
        //    424: getfield  #141 // dev.angelvisuals.a.s$b.cs:Ljava/lang/String;
        //    427: astore  10
        //    429: getstatic  #131 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    432: ldc  #57 // 255.0f
        //    434: fload  8
        //    436: fmul
        //    437: f2i
        //    438: invokevirtual  #161 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    441: astore  12
        //    443: ldc  #54 // 13.0f
        //    445: fstore  14
        //    447: getstatic  #130 // dev.angelvisuals.a.bc.j:Ldev/angelvisuals/a/ci;
        //    450: aload  9
        //    452: fload  14
        //    454: invokevirtual  #165 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    457: fstore  15
        //    459: aload  4
        //    461: aload  10
        //    463: invokevirtual  #156 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    466: fstore  16
        //    468: aload  4
        //    470: aload  11
        //    472: invokevirtual  #156 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    475: fstore  17
        //    477: ldc  #50 // 4.0f
        //    479: fstore  18
        //    481: ldc  #51 // 6.0f
        //    483: fstore  19
        //    485: fload  19
        //    487: fconst_2
        //    488: fmul
        //    489: fload  15
        //    491: fadd
        //    492: fload  18
        //    494: fadd
        //    495: fload  16
        //    497: fadd
        //    498: aload  11
        //    500: invokevirtual  #181 // java.lang.String.isEmpty:()Z
        //    503: ifeq  510 (offset +7)
        //    506: fconst_0
        //    507: goto  515 (offset +8)
        //    510: fload  18
        //    512: fload  17
        //    514: fadd
        //    515: fadd
        //    516: fstore  20
        //    518: fload_2
        //    519: fstore  21
        //    521: aload_0
        //    522: invokevirtual  #172 // dev.angelvisuals.a.s.a:()Ldev/angelvisuals/a/ay$b;
        //    525: astore  22
        //    527: aload  22
        //    529: getstatic  #123 // dev.angelvisuals.a.ay$b.d:Ldev/angelvisuals/a/ay$b;
        //    532: if_acmpeq  551 (offset +19)
        //    535: aload  22
        //    537: getstatic  #125 // dev.angelvisuals.a.ay$b.g:Ldev/angelvisuals/a/ay$b;
        //    540: if_acmpeq  551 (offset +11)
        //    543: aload  22
        //    545: getstatic  #128 // dev.angelvisuals.a.ay$b.j:Ldev/angelvisuals/a/ay$b;
        //    548: if_acmpne  560 (offset +12)
        //    551: fload_2
        //    552: fload  20
        //    554: fsub
        //    555: fstore  21
        //    557: goto  592 (offset +35)
        //    560: aload  22
        //    562: getstatic  #122 // dev.angelvisuals.a.ay$b.c:Ldev/angelvisuals/a/ay$b;
        //    565: if_acmpeq  584 (offset +19)
        //    568: aload  22
        //    570: getstatic  #124 // dev.angelvisuals.a.ay$b.f:Ldev/angelvisuals/a/ay$b;
        //    573: if_acmpeq  584 (offset +11)
        //    576: aload  22
        //    578: getstatic  #127 // dev.angelvisuals.a.ay$b.i:Ldev/angelvisuals/a/ay$b;
        //    581: if_acmpne  592 (offset +11)
        //    584: fload_2
        //    585: fload  20
        //    587: fconst_2
        //    588: fdiv
        //    589: fsub
        //    590: fstore  21
        //    592: new  #90 // dev.angelvisuals.a.bp
        //    595: dup
        //    596: ldc  #23 // -614314230
        //    598: ldc  #23 // -614314230
        //    600: ixor
        //    601: ldc  #25 // -276017232
        //    603: ldc  #25 // -276017232
        //    605: ixor
        //    606: ldc  #19 // -956392490
        //    608: ldc  #19 // -956392490
        //    610: ixor
        //    611: ldc  #57 // 255.0f
        //    613: fload  8
        //    615: fmul
        //    616: f2i
        //    617: invokespecial  #160 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    620: astore  23
        //    622: aload_1
        //    623: invokevirtual  #155 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    626: fload  21
        //    628: fload_3
        //    629: fload  20
        //    631: fload  6
        //    633: ldc  #49 // 2.5f
        //    635: invokestatic  #153 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    638: aload  23
        //    640: invokestatic  #152 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    643: fload  21
        //    645: fload  19
        //    647: fadd
        //    648: fstore  24
        //    650: aload_1
        //    651: getstatic  #130 // dev.angelvisuals.a.bc.j:Ldev/angelvisuals/a/ci;
        //    654: fload  14
        //    656: invokevirtual  #164 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    659: aload  9
        //    661: fload  24
        //    663: fload_3
        //    664: fload  6
        //    666: ldc  #53 // 10.0f
        //    668: fsub
        //    669: fconst_2
        //    670: fdiv
        //    671: fadd
        //    672: aload  13
        //    674: invokevirtual  #154 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    677: fload  24
        //    679: fload  15
        //    681: fload  18
        //    683: fadd
        //    684: fadd
        //    685: fstore  24
        //    687: aload_1
        //    688: aload  4
        //    690: aload  10
        //    692: fload  24
        //    694: fload_3
        //    695: fload  6
        //    697: aload  4
        //    699: invokevirtual  #157 // dev.angelvisuals.a.ar.z:()F
        //    702: fsub
        //    703: fconst_2
        //    704: fdiv
        //    705: fadd
        //    706: getstatic  #131 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    709: ldc  #57 // 255.0f
        //    711: fload  8
        //    713: fmul
        //    714: f2i
        //    715: invokevirtual  #161 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    718: invokevirtual  #154 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    721: aload  11
        //    723: invokevirtual  #181 // java.lang.String.isEmpty:()Z
        //    726: ifne  763 (offset +37)
        //    729: fload  24
        //    731: fload  16
        //    733: fload  18
        //    735: fadd
        //    736: fadd
        //    737: fstore  24
        //    739: aload_1
        //    740: aload  4
        //    742: aload  11
        //    744: fload  24
        //    746: fload_3
        //    747: fload  6
        //    749: aload  4
        //    751: invokevirtual  #157 // dev.angelvisuals.a.ar.z:()F
        //    754: fsub
        //    755: fconst_2
        //    756: fdiv
        //    757: fadd
        //    758: aload  12
        //    760: invokevirtual  #154 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    763: fload  20
        //    765: freturn
    }

  private static int ly(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lz(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int lA(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}