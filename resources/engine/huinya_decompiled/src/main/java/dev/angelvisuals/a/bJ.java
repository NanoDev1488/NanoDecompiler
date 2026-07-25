// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bj
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.ak;
import dev.angelvisuals.a.bx;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;

public class bj implements cF {

    // ---- поля ----
  private cK field233; // было: c
  private boolean field234; // было: R
  private long field235; // было: p
  private final ClassA2 field236; // было: l
  private static final String mh = "// good luck with the next 9999 classes";
  private static final String mi = "Protected by t.me/JoinerClient";
  private static final String mj = "// number obfuscation: ENABLED (XOR masking)";
  private static final String mk = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String ml = "// class hierarchy hashing: ENABLED";
  private static final int hd = 77879476;
  private static final int he = -1249202760;
  private static final int hf = -1887531015;
  private static final byte[] bx;

    static {
        bx = "#`,LHR_Rv]FRYi<W/&~8t)~VG]ESt8x{I2_ZCg?geJ~c4g(SWS^|S[[,_JW_:Y-w})ed-;'fqQ(&wI2MDz5x;`a4h=a#*Ho,?H*6\\?qZysuXOxx@Py},yd+bHt{uQIF5i#M|'05VgWksq&$#W1P]|=,++2sAj;UEZ`MY<%e|%N,!u8=p4D3jUpQqsw<{<r[,'C@9GX&/XOM?PblCL[_4]=Wh;_?D8]Ws$53:'VM}fu02{}90Oo'!<DwBb2sR*=wr".getBytes("ISO-8859-1");
    }

  public bj() { // было: <init>
        super();
        field236 = new ClassA2(1814672313674250484L ^ 1814672313674250300L, aH.field21);
        EventManager.register(this);
    }

    @EventTarget
  public void method500(ak arg0) { // было: b
        field233 = arg0.method266();
        field234 = arg0.method267();
        field235 = System.currentTimeMillis();
        field236.method7(0.0f);
        field236.method6(1.0f);
    }

    @EventTarget
  public void method501(bx arg0) { // было: c
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #89 // dev.angelvisuals.a.bj.c:Ldev/angelvisuals/a/cK;
        //      4: ifnonnull  8 (offset +4)
        //      7: return
        //      8: invokestatic  #122 // java.lang.System.currentTimeMillis:()J
        //     11: aload_0
        //     12: getfield  #92 // dev.angelvisuals.a.bj.p:J
        //     15: lsub
        //     16: lstore_2
        //     17: lload_2
        //     18: ldc2_w  #76 // -1141940552964056874L
        //     21: ldc2_w  #78 // -1141940552964055962L
        //     24: lxor
        //     25: lcmp
        //     26: ifle  38 (offset +12)
        //     29: aload_0
        //     30: getfield  #90 // dev.angelvisuals.a.bj.l:Ldev/angelvisuals/a/k;
        //     33: fconst_0
        //     34: invokevirtual  #117 // dev.angelvisuals.a.k.a:(F)F
        //     37: pop
        //     38: aload_0
        //     39: getfield  #90 // dev.angelvisuals.a.bj.l:Ldev/angelvisuals/a/k;
        //     42: invokevirtual  #119 // dev.angelvisuals.a.k.e:()F
        //     45: ldc  #24 // 0.009999999776482582f
        //     47: fcmpg
        //     48: ifge  52 (offset +4)
        //     51: return
        //     52: aload_1
        //     53: invokevirtual  #111 // dev.angelvisuals.a.bx.b:()Ldev/angelvisuals/a/ap;
        //     56: astore  4
        //     58: getstatic  #91 // dev.angelvisuals.a.bj.mc:Lnet/minecraft/class_310;
        //     61: invokevirtual  #126 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     64: invokevirtual  #124 // net.minecraft.class_1041.method_4486:()I
        //     67: i2f
        //     68: fstore  5
        //     70: getstatic  #91 // dev.angelvisuals.a.bj.mc:Lnet/minecraft/class_310;
        //     73: invokevirtual  #126 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     76: invokevirtual  #125 // net.minecraft.class_1041.method_4502:()I
        //     79: i2f
        //     80: fstore  6
        //     82: aload_0
        //     83: getfield  #87 // dev.angelvisuals.a.bj.R:Z
        //     86: ifeq  105 (offset +19)
        //     89: ldc  #46 // 'X\x18¸Ä[i\x9bæG:½Êu\x07ßË-��\x81ÊDiÛò^{Ù´K��¢±l\x05ÝÓW4©ÇD>\x9d¼'
        //     91: ldc  #7 // -537859179
        //     93: ldc  #20 // 1578810248
        //     95: ixor
        //     96: invokestatic  #96 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     99: invokestatic  #95 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    102: goto  118 (offset +16)
        //    105: ldc  #47 // '`ß\x89«XÊ\x83Êwæ¹\x9fuü\xad¡iõ\xa0\x9a;Ú\x9f¸|ñ\x96\x91HÖýÆRÛø\x9f]ôþ\x96R\x88¡Ï'
        //    107: ldc  #8 // -537530006
        //    109: ldc  #17 // 758858106
        //    111: ixor
        //    112: invokestatic  #96 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    115: invokestatic  #95 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    118: astore  7
        //    120: aload_0
        //    121: getfield  #89 // dev.angelvisuals.a.bj.c:Ldev/angelvisuals/a/cK;
        //    124: invokevirtual  #112 // dev.angelvisuals.a.cK.getName:()Ljava/lang/String;
        //    127: aload  7
        //    129: invokedynamic  #127 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    134: astore  8
        //    136: getstatic  #85 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    139: aload  8
        //    141: ldc  #28 // 7.5f
        //    143: invokevirtual  #115 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    146: fstore  9
        //    148: ldc  #29 // 9.0f
        //    150: fstore  10
        //    152: aload_0
        //    153: getfield  #87 // dev.angelvisuals.a.bj.R:Z
        //    156: ifeq  175 (offset +19)
        //    159: ldc  #48 // 'õ]ãÐ\xa0s´Ô\x84[½Æ\x8ds\x8bþúX¨ë\xa0Tæ¯'
        //    161: ldc  #1 // -2055671183
        //    163: ldc  #12 // 396617661
        //    165: ixor
        //    166: invokestatic  #96 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    169: invokestatic  #95 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    172: goto  188 (offset +16)
        //    175: ldc  #45 // 'R°Ldb\x80pkx¾Y\x0bd\x8dp3_\x80_6\x1d\x8d#a'
        //    177: ldc  #19 // 1338620637
        //    179: ldc  #11 // 332878070
        //    181: ixor
        //    182: invokestatic  #96 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    185: invokestatic  #95 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    188: astore  11
        //    190: getstatic  #86 // dev.angelvisuals.a.bc.j:Ldev/angelvisuals/a/ci;
        //    193: aload  11
        //    195: fload  10
        //    197: invokevirtual  #115 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    200: fstore  12
        //    202: ldc  #33 // 18.0f
        //    204: fstore  13
        //    206: ldc  #32 // 17.0f
        //    208: fstore  14
        //    210: fload  14
        //    212: fload  9
        //    214: fadd
        //    215: ldc  #30 // 12.0f
        //    217: fadd
        //    218: fstore  15
        //    220: fload  5
        //    222: fload  15
        //    224: fsub
        //    225: fconst_2
        //    226: fdiv
        //    227: fstore  16
        //    229: fload  6
        //    231: fload  13
        //    233: fsub
        //    234: fconst_2
        //    235: fdiv
        //    236: ldc  #34 // 40.0f
        //    238: fadd
        //    239: fstore  17
        //    241: aload_0
        //    242: getfield  #90 // dev.angelvisuals.a.bj.l:Ldev/angelvisuals/a/k;
        //    245: invokevirtual  #119 // dev.angelvisuals.a.k.e:()F
        //    248: fstore  18
        //    250: ldc  #36 // 255.0f
        //    252: fload  18
        //    254: fmul
        //    255: f2i
        //    256: istore  19
        //    258: invokestatic  #97 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    261: invokevirtual  #98 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //    264: invokevirtual  #113 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //    267: invokevirtual  #108 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //    270: iload  19
        //    272: invokevirtual  #110 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    275: astore  20
        //    277: aload  4
        //    279: invokevirtual  #107 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    282: fload  16
        //    284: fload  17
        //    286: fload  15
        //    288: fload  13
        //    290: ldc  #31 // 15.0f
        //    292: ldc  #26 // 5.0f
        //    294: invokestatic  #101 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    297: getstatic  #93 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    300: iload  19
        //    302: invokevirtual  #110 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    305: invokestatic  #100 // dev.angelvisuals.a.aE.f:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    308: aload  4
        //    310: invokevirtual  #107 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    313: fload  16
        //    315: fload  17
        //    317: fload  14
        //    319: fload  13
        //    321: ldc  #26 // 5.0f
        //    323: ldc  #26 // 5.0f
        //    325: invokestatic  #102 // dev.angelvisuals.a.aY.c:(FF)Ldev/angelvisuals/a/aY;
        //    328: new  #61 // dev.angelvisuals.a.bp
        //    331: dup
        //    332: ldc  #3 // -1638769547
        //    334: ldc  #3 // -1638769547
        //    336: ixor
        //    337: ldc  #13 // 463040664
        //    339: ldc  #13 // 463040664
        //    341: ixor
        //    342: ldc  #18 // 1115055213
        //    344: ldc  #18 // 1115055213
        //    346: ixor
        //    347: iload  19
        //    349: invokespecial  #109 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    352: invokestatic  #99 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    355: aload  4
        //    357: invokevirtual  #107 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    360: fload  16
        //    362: fload  14
        //    364: fadd
        //    365: fload  17
        //    367: fload  15
        //    369: fload  14
        //    371: fsub
        //    372: fload  13
        //    374: ldc  #26 // 5.0f
        //    376: ldc  #26 // 5.0f
        //    378: invokestatic  #103 // dev.angelvisuals.a.aY.d:(FF)Ldev/angelvisuals/a/aY;
        //    381: new  #61 // dev.angelvisuals.a.bp
        //    384: dup
        //    385: ldc  #16 // 688060409
        //    387: ldc  #16 // 688060409
        //    389: ixor
        //    390: ldc  #23 // 1954453675
        //    392: ldc  #23 // 1954453675
        //    394: ixor
        //    395: ldc  #10 // 174098136
        //    397: ldc  #10 // 174098136
        //    399: ixor
        //    400: ldc  #35 // 125.0f
        //    402: fload  18
        //    404: fmul
        //    405: f2i
        //    406: invokespecial  #109 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    409: invokestatic  #99 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    412: fload  16
        //    414: fload  14
        //    416: fload  12
        //    418: fsub
        //    419: fconst_2
        //    420: fdiv
        //    421: fadd
        //    422: fstore  21
        //    424: fload  17
        //    426: fload  13
        //    428: ldc  #27 // 7.0f
        //    430: fsub
        //    431: fconst_2
        //    432: fdiv
        //    433: fadd
        //    434: fstore  22
        //    436: aload  4
        //    438: getstatic  #86 // dev.angelvisuals.a.bc.j:Ldev/angelvisuals/a/ci;
        //    441: fload  10
        //    443: invokevirtual  #114 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    446: aload  11
        //    448: fload  21
        //    450: fload  22
        //    452: ldc  #25 // 0.5f
        //    454: fadd
        //    455: aload_0
        //    456: getfield  #87 // dev.angelvisuals.a.bj.R:Z
        //    459: ifeq  467 (offset +8)
        //    462: aload  20
        //    464: goto  491 (offset +27)
        //    467: new  #61 // dev.angelvisuals.a.bp
        //    470: dup
        //    471: ldc  #22 // 1761857702
        //    473: ldc  #21 // 1761857625
        //    475: ixor
        //    476: ldc  #5 // -703577061
        //    478: ldc  #6 // -703577001
        //    480: ixor
        //    481: ldc  #14 // 646536885
        //    483: ldc  #15 // 646536953
        //    485: ixor
        //    486: iload  19
        //    488: invokespecial  #109 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    491: invokevirtual  #106 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    494: fload  16
        //    496: fload  14
        //    498: fadd
        //    499: ldc  #26 // 5.0f
        //    501: fadd
        //    502: fstore  23
        //    504: fload  17
        //    506: fload  13
        //    508: ldc  #28 // 7.5f
        //    510: fsub
        //    511: fconst_2
        //    512: fdiv
        //    513: fadd
        //    514: fstore  24
        //    516: aload  4
        //    518: getstatic  #85 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    521: ldc  #28 // 7.5f
        //    523: invokevirtual  #114 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    526: aload  8
        //    528: fload  23
        //    530: fload  24
        //    532: getstatic  #93 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    535: iload  19
        //    537: invokevirtual  #110 // dev.angelvisuals.a.bp.b:(I)Ldev/angelvisuals/a/bp;
        //    540: invokevirtual  #106 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    543: return
    }

  private static int gr(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gs(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int gt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}