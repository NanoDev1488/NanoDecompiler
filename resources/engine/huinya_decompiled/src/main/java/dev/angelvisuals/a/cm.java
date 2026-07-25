// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cM
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ClassA146;
import dev.angelvisuals.a.aI;
import dev.angelvisuals.a.ad;
import dev.angelvisuals.a.cB;
import dev.angelvisuals.a.do;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_7439;
import ru.nexusguard.protection.annotations.Native;

public class cM implements ClassA146 {

    // ---- поля ----
  private final cB field214; // было: a
  private boolean ai;
  private int nP;
  private static final String xs = "// number obfuscation: ENABLED (XOR masking)";
  private static final String xt = "// === DO NOT TOUCH ===";
  private static final String xu = "// stop. seriously. go play minecraft instead";
  private static final String xv = "// flow obfuscation: ENABLED";
  private static final String xw = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int nQ = 1328192591;
  private static final int nR = -1328349252;
  private static final int nS = -202798665;
  private static final byte[] dv;

    static {
        dv = "ja&!R6~1tP;{48:|HR`fO~vt0io(2EVs.^?3^Jzr[aS?Oc=k|QjkOCqkwB//{zjK`~o&qnXk 5U(AURG&qeC91a}o0^Ots=mhU'@Xg[(7E3qp:Tk\"x5*aRc,jU>LD]_M`G*9KD:xsCd}p3};@D^})Fxy~;s!$J] 238Z'S&fI<]$a)}3%c8]TH'PjyNnI$PY0]\"3F=~L`nTF]N`9p;3F_7<U}_ChW4~`,:X4'&GmD(7e^`\\yT]wNM{dMb#aY.gky".getBytes("ISO-8859-1");
    }

  public cM() { // было: <init>
        super();
        field214 = new cB();
        try {
            EventManager.register(this);
        } catch (Exception var1) {
        }
    }

    @EventTarget
    @Native
  public void method430(aI arg0) { // было: c
        if (nP != 0) {
            class_2596 var2 = arg0.method335();
            if (var2 instanceof class_7439) {
                class_7439 var3 = ((class_7439) var2);
                if (arg0.am()) {
                    String var4 = var3.comp_763().getString().toLowerCase();
                    if (!var4.contains(Decryptor.method1945(XorDecoder.method1946("%\u001fi©\r\u0005+\u0003\u0016��¸\u00199\u0003È$({°Y(\u007fÃ", -826256647 ^ 813833624)))) {
                        if (var4.contains(Decryptor.method1945(XorDecoder.method1946("$Tèö\t'®åQsôó\u0015!Öî\u0004fÜ¤+]øÍ\u0011& Õ$[ðç+X© (\\­¦4@ú©", -1181837021 ^ 756517189)))) {
                            ad var5 = ad.method425();
                            String var6 = String.valueOf(class_124.field_1061);
                            var5.method429(Decryptor.method1945(XorDecoder.method1946("\u001d\u001f\u0015£~Kw®{e\u000c´`q\u0013¬*e\" #_}Ä", -1669808259 ^ 1698207538)), class_2561.method_43470(" На данную анархию " + var6 + "нельзя" + String.valueOf(class_124.field_1070) + " зайти"));
                            nP = 1177711914 ^ 1177711914;
                        }
                    }
                }
            }
        }
    }

    @EventTarget
    @Native
  public void method431(do arg0) { // было: f
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //      4: ifeq  486 (offset +482)
        //      7: invokestatic  #143 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     10: invokevirtual  #144 // dev.angelvisuals.AngelVisuals.getServerHandler:()Ldev/angelvisuals/a/i;
        //     13: astore_2
        //     14: aload_2
        //     15: invokevirtual  #153 // dev.angelvisuals.a.i.g:()Z
        //     18: ifne  33 (offset +15)
        //     21: aload_0
        //     22: ldc  #47 // 381807673
        //     24: ldc  #47 // 381807673
        //     26: ixor
        //     27: putfield  #133 // dev.angelvisuals.a.cM.nP:I
        //     30: goto  486 (offset +456)
        //     33: aload_2
        //     34: invokevirtual  #152 // dev.angelvisuals.a.i.b:()I
        //     37: istore_3
        //     38: aload_0
        //     39: getfield  #130 // dev.angelvisuals.a.cM.ai:Z
        //     42: ifeq  94 (offset +52)
        //     45: iload_3
        //     46: ldc  #42 // 7491904
        //     48: ldc  #41 // -7491905
        //     50: ixor
        //     51: if_icmpne  66 (offset +15)
        //     54: aload_0
        //     55: ldc  #61 // 936824273
        //     57: ldc  #61 // 936824273
        //     59: ixor
        //     60: putfield  #130 // dev.angelvisuals.a.cM.ai:Z
        //     63: goto  486 (offset +423)
        //     66: getstatic  #132 // dev.angelvisuals.a.cM.mc:Lnet/minecraft/class_310;
        //     69: getfield  #137 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     72: getfield  #139 // net.minecraft.class_746.field_3944:Lnet/minecraft/class_634;
        //     75: ldc  #85 // '!)©_{\x19\x96p*\x1c½@{.õpwJô|O\x08ñ\x0e'
        //     77: ldc  #3 // -1804358099
        //     79: ldc  #12 // -1480601291
        //     81: ixor
        //     82: invokestatic  #142 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     85: invokestatic  #141 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     88: invokevirtual  #164 // net.minecraft.class_634.method_45730:(Ljava/lang/String;)V
        //     91: goto  486 (offset +395)
        //     94: iload_3
        //     95: aload_0
        //     96: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //     99: if_icmpne  114 (offset +15)
        //    102: aload_0
        //    103: ldc  #32 // -451476547
        //    105: ldc  #32 // -451476547
        //    107: ixor
        //    108: putfield  #133 // dev.angelvisuals.a.cM.nP:I
        //    111: goto  486 (offset +375)
        //    114: getstatic  #132 // dev.angelvisuals.a.cM.mc:Lnet/minecraft/class_310;
        //    117: getfield  #138 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //    120: astore  4
        //    122: aload  4
        //    124: instanceof  #121 // net.minecraft.class_476
        //    127: ifeq  444 (offset +317)
        //    130: aload  4
        //    132: checkcast  #121 // net.minecraft.class_476
        //    135: astore  5
        //    137: aload  5
        //    139: invokevirtual  #163 // net.minecraft.class_476.method_25440:()Lnet/minecraft/class_2561;
        //    142: invokeinterface  #167 // net.minecraft.class_2561.getString:()Ljava/lang/String;, count 1
        //    147: ldc  #96 // 'ã<;N°N\x16r\x80 \x08$\x8crLR¯ \x1b]\x92{<:ÿV7F\x8a@?=\x80\x7f\x1bi¸p\t:\x80F\x0bd¾[NsºD\x1b;»[*l½a/mûq/9'
        //    149: ldc  #37 // -184714955
        //    151: ldc  #40 // -8360195
        //    153: ixor
        //    154: invokestatic  #142 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    157: invokestatic  #141 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    160: invokevirtual  #156 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    163: ifeq  444 (offset +281)
        //    166: aload  5
        //    168: invokevirtual  #162 // net.minecraft.class_476.method_17577:()Lnet/minecraft/class_1703;
        //    171: checkcast  #117 // net.minecraft.class_1707
        //    174: invokevirtual  #161 // net.minecraft.class_1707.method_7629:()Lnet/minecraft/class_1263;
        //    177: invokeinterface  #166 // net.minecraft.class_1263.method_5439:()I, count 1
        //    182: ldc  #16 // -1314112419
        //    184: ldc  #15 // -1314112425
        //    186: ixor
        //    187: if_icmpge  198 (offset +11)
        //    190: ldc  #30 // -539070019
        //    192: ldc  #29 // -539070020
        //    194: ixor
        //    195: goto  203 (offset +8)
        //    198: ldc  #53 // 536693013
        //    200: ldc  #53 // 536693013
        //    202: ixor
        //    203: istore  6
        //    205: aload_0
        //    206: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //    209: ldc  #21 // -836175490
        //    211: ldc  #20 // -836175503
        //    213: ixor
        //    214: if_icmpge  251 (offset +37)
        //    217: ldc  #77 // 1681570879
        //    219: ldc  #76 // 1681570877
        //    221: ixor
        //    222: newarray  int
        //    224: dup
        //    225: ldc  #23 // -798579702
        //    227: ldc  #23 // -798579702
        //    229: ixor
        //    230: ldc  #35 // -322888196
        //    232: ldc  #35 // -322888196
        //    234: ixor
        //    235: iastore
        //    236: dup
        //    237: ldc  #9 // -1532399943
        //    239: ldc  #8 // -1532399944
        //    241: ixor
        //    242: ldc  #70 // 1205809668
        //    244: ldc  #70 // 1205809668
        //    246: ixor
        //    247: iastore
        //    248: goto  374 (offset +126)
        //    251: aload_0
        //    252: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //    255: ldc  #54 // 574588053
        //    257: ldc  #55 // 574588084
        //    259: ixor
        //    260: if_icmpge  297 (offset +37)
        //    263: ldc  #65 // 1009572937
        //    265: ldc  #66 // 1009572939
        //    267: ixor
        //    268: newarray  int
        //    270: dup
        //    271: ldc  #33 // -433595546
        //    273: ldc  #33 // -433595546
        //    275: ixor
        //    276: ldc  #72 // 1397385904
        //    278: ldc  #73 // 1397385905
        //    280: ixor
        //    281: iastore
        //    282: dup
        //    283: ldc  #50 // 431718910
        //    285: ldc  #51 // 431718911
        //    287: ixor
        //    288: ldc  #67 // 1061302914
        //    290: ldc  #68 // 1061302924
        //    292: ixor
        //    293: iastore
        //    294: goto  374 (offset +80)
        //    297: aload_0
        //    298: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //    301: ldc  #46 // 207067960
        //    303: ldc  #45 // 207067912
        //    305: ixor
        //    306: if_icmpge  343 (offset +37)
        //    309: ldc  #75 // 1621482618
        //    311: ldc  #74 // 1621482616
        //    313: ixor
        //    314: newarray  int
        //    316: dup
        //    317: ldc  #34 // -363847554
        //    319: ldc  #34 // -363847554
        //    321: ixor
        //    322: ldc  #10 // -1504430644
        //    324: ldc  #11 // -1504430642
        //    326: ixor
        //    327: iastore
        //    328: dup
        //    329: ldc  #63 // 956786535
        //    331: ldc  #62 // 956786534
        //    333: ixor
        //    334: ldc  #25 // -747159914
        //    336: ldc  #26 // -747159882
        //    338: ixor
        //    339: iastore
        //    340: goto  374 (offset +34)
        //    343: ldc  #80 // 2146625021
        //    345: ldc  #81 // 2146625023
        //    347: ixor
        //    348: newarray  int
        //    350: dup
        //    351: ldc  #52 // 449854647
        //    353: ldc  #52 // 449854647
        //    355: ixor
        //    356: ldc  #28 // -739600373
        //    358: ldc  #27 // -739600376
        //    360: ixor
        //    361: iastore
        //    362: dup
        //    363: ldc  #6 // -1676595931
        //    365: ldc  #5 // -1676595932
        //    367: ixor
        //    368: ldc  #17 // -1264611705
        //    370: ldc  #18 // -1264611672
        //    372: ixor
        //    373: iastore
        //    374: astore  7
        //    376: iload  6
        //    378: ifeq  408 (offset +30)
        //    381: aload  7
        //    383: ldc  #24 // -766453793
        //    385: ldc  #24 // -766453793
        //    387: ixor
        //    388: iaload
        //    389: ldc  #38 // -96971269
        //    391: ldc  #38 // -96971269
        //    393: ixor
        //    394: getstatic  #136 // net.minecraft.class_1713.field_7790:Lnet/minecraft/class_1713;
        //    397: ldc  #79 // 2005813833
        //    399: ldc  #79 // 2005813833
        //    401: ixor
        //    402: invokestatic  #151 // dev.angelvisuals.a.dL.a:(IILnet/minecraft/class_1713;Z)V
        //    405: goto  443 (offset +38)
        //    408: ldc  #1 // -1976962995
        //    410: ldc  #2 // -1976962980
        //    412: ixor
        //    413: aload_0
        //    414: getfield  #133 // dev.angelvisuals.a.cM.nP:I
        //    417: iadd
        //    418: aload  7
        //    420: ldc  #48 // 393706330
        //    422: ldc  #49 // 393706331
        //    424: ixor
        //    425: iaload
        //    426: isub
        //    427: ldc  #39 // -31209516
        //    429: ldc  #39 // -31209516
        //    431: ixor
        //    432: getstatic  #136 // net.minecraft.class_1713.field_7790:Lnet/minecraft/class_1713;
        //    435: ldc  #64 // 959943984
        //    437: ldc  #64 // 959943984
        //    439: ixor
        //    440: invokestatic  #151 // dev.angelvisuals.a.dL.a:(IILnet/minecraft/class_1713;Z)V
        //    443: return
        //    444: aload_0
        //    445: getfield  #129 // dev.angelvisuals.a.cM.a:Ldev/angelvisuals/a/cB;
        //    448: ldc2_w  #125 // 8484834895829314659L
        //    451: ldc2_w  #127 // 8484834895829314967L
        //    454: lxor
        //    455: invokevirtual  #150 // dev.angelvisuals.a.cB.b:(J)Z
        //    458: ifeq  486 (offset +28)
        //    461: getstatic  #132 // dev.angelvisuals.a.cM.mc:Lnet/minecraft/class_310;
        //    464: getfield  #137 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    467: getfield  #139 // net.minecraft.class_746.field_3944:Lnet/minecraft/class_634;
        //    470: ldc  #97 // 'ú\x99ß4õ\x9eì\x1eþ\x9fÆ ÞÛÃ\x1a\x8c®\x9d\x1cÍ\x9f\x94D'
        //    472: ldc  #4 // -1686793908
        //    474: ldc  #31 // -488867339
        //    476: ixor
        //    477: invokestatic  #142 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    480: invokestatic  #141 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    483: invokevirtual  #164 // net.minecraft.class_634.method_45730:(Ljava/lang/String;)V
        //    486: return
    }

    @Native
  public void method432(int arg0) { // было: w
        if (arg0 <= 0) {
            ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("CÏ!çbð*åzá\u0001Ìwá2È<Ñ\u0012ýMÉ}´", -1350613146 ^ 641716072)), class_2561.method_43470(" Не верный " + String.valueOf(class_124.field_1061) + "лайт"));
        } else {
            if (arg0 >= (185460251 ^ 185460315)) {
                ad.method425().method429(Decryptor.method1945(XorDecoder.method1946("CÏ!çbð*åzá\u0001Ìwá2È<Ñ\u0012ýMÉ}´", -1350613146 ^ 641716072)), class_2561.method_43470(" Не верный " + String.valueOf(class_124.field_1061) + "лайт"));
            } else {
                nP = arg0;
                ai = 698888009 ^ 698888008;
            }
        }
    }

  private static int mi(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int mk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}