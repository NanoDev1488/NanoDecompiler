// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.d
package dev.angelvisuals.a;

import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bN;
import dev.angelvisuals.a.cA;
import java.util.List;
import net.minecraft.class_1041;

public class ClassA128 {

    // ---- поля ----
  private final bN field615; // было: a
  private bA field616; // было: a
  private float field617; // было: b
  private static final String field618 = "// Joiner sees you"; // было: p
  private static final String field619 = "// you are reading machine-generated garbage"; // было: q
  private static final String field620 = "// string encryption: ENABLED (AES-128/ECB + XOR)"; // было: r
  private static final String field621 = "// every class watermarked, every string encrypted, every number xored"; // было: s
  private static final String field622 = "// reverse-engineering this jar is a waste of time, friend"; // было: t
  private static final int field623 = -960821632; // было: j
  private static final int field624 = -685203740; // было: k
  private static final int field625 = 126927841; // было: l
  private static final byte[] field626; // было: d

    static {
        field626 = "7#uhU:T[j>Er8eHo),6YT`C@v:S|=8AC\"%\\5Kl#}M9-Mr\\u*S~=6f$IfucVx$CVF2H?X>C@>R0HF4}k0PPZiDx\"9Pf7.sX}C3B3YzIu_peGoAgSV<g 'qzBV-,@.\"JFpCjxQ|l\"Z(At-L}_)t[lZ(r.n.$y_N|F/$$1gb2g3#cjb)DrSSXz{$}-x\"r9V%(I-0cV>FI8G8kf>}x:I0wlA+`GrCx6Nrd>@qq}@;wM}=m5B###Ot;e&-v4.8tYLnKR ".getBytes("ISO-8859-1");
    }

  public ClassA128(bN arg0) { // было: <init>
        super();
        field615 = arg0;
    }

  public boolean method1000(double arg0, double arg1, int arg2, class_1041 arg3) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  6
        //      2: ifnonnull  11 (offset +9)
        //      5: ldc  #176 // 1382986394
        //      7: ldc  #176 // 1382986394
        //      9: ixor
        //     10: ireturn
        //     11: aload_0
        //     12: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     15: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //     18: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //     21: if_acmpne  30 (offset +9)
        //     24: invokestatic  #358 // dev.angelvisuals.a.cG.a:()[Ldev/angelvisuals/a/cG;
        //     27: goto  52 (offset +25)
        //     30: ldc  #179 // 1468660295
        //     32: ldc  #178 // 1468660294
        //     34: ixor
        //     35: anewarray  #257 // dev.angelvisuals.a.cG
        //     38: dup
        //     39: ldc  #171 // 1327006571
        //     41: ldc  #171 // 1327006571
        //     43: ixor
        //     44: aload_0
        //     45: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     48: invokevirtual  #344 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cG;
        //     51: aastore
        //     52: astore  7
        //     54: aload_0
        //     55: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     58: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //     61: fstore  8
        //     63: aload_0
        //     64: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     67: invokevirtual  #342 // dev.angelvisuals.a.bN.ap:()F
        //     70: aload_0
        //     71: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     74: invokevirtual  #343 // dev.angelvisuals.a.bN.aq:()F
        //     77: fadd
        //     78: ldc  #219 // 20.0f
        //     80: fsub
        //     81: fstore  9
        //     83: iload  5
        //     85: ifne  212 (offset +127)
        //     88: dload_3
        //     89: fload  9
        //     91: f2d
        //     92: dcmpl
        //     93: iflt  212 (offset +119)
        //     96: dload_3
        //     97: fload  9
        //     99: ldc  #216 // 15.0f
        //    101: fadd
        //    102: f2d
        //    103: dcmpg
        //    104: ifgt  212 (offset +108)
        //    107: dload_1
        //    108: fload  8
        //    110: f2d
        //    111: dcmpl
        //    112: iflt  139 (offset +27)
        //    115: dload_1
        //    116: fload  8
        //    118: ldc  #224 // 34.0f
        //    120: fadd
        //    121: f2d
        //    122: dcmpg
        //    123: ifgt  139 (offset +16)
        //    126: aload_0
        //    127: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    130: getstatic  #298 // dev.angelvisuals.a.bN$a.b:Ldev/angelvisuals/a/bN$a;
        //    133: invokevirtual  #333 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/bN$a;)V
        //    136: goto  206 (offset +70)
        //    139: dload_1
        //    140: fload  8
        //    142: ldc  #225 // 38.0f
        //    144: fadd
        //    145: f2d
        //    146: dcmpl
        //    147: iflt  174 (offset +27)
        //    150: dload_1
        //    151: fload  8
        //    153: ldc  #227 // 86.0f
        //    155: fadd
        //    156: f2d
        //    157: dcmpg
        //    158: ifgt  174 (offset +16)
        //    161: aload_0
        //    162: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    165: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    168: invokevirtual  #333 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/bN$a;)V
        //    171: goto  206 (offset +35)
        //    174: dload_1
        //    175: fload  8
        //    177: ldc  #228 // 90.0f
        //    179: fadd
        //    180: f2d
        //    181: dcmpl
        //    182: iflt  206 (offset +24)
        //    185: dload_1
        //    186: fload  8
        //    188: ldc  #232 // 124.0f
        //    190: fadd
        //    191: f2d
        //    192: dcmpg
        //    193: ifgt  206 (offset +13)
        //    196: aload_0
        //    197: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    200: getstatic  #300 // dev.angelvisuals.a.bN$a.d:Ldev/angelvisuals/a/bN$a;
        //    203: invokevirtual  #333 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/bN$a;)V
        //    206: ldc  #17 // -1915783808
        //    208: ldc  #18 // -1915783807
        //    210: ixor
        //    211: ireturn
        //    212: aload_0
        //    213: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    216: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    219: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    222: if_acmpeq  337 (offset +115)
        //    225: iload  5
        //    227: ifne  337 (offset +110)
        //    230: aload_0
        //    231: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    234: invokevirtual  #342 // dev.angelvisuals.a.bN.ap:()F
        //    237: aload_0
        //    238: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    241: invokevirtual  #343 // dev.angelvisuals.a.bN.aq:()F
        //    244: fadd
        //    245: ldc  #226 // 42.0f
        //    247: fadd
        //    248: fstore  10
        //    250: invokestatic  #358 // dev.angelvisuals.a.cG.a:()[Ldev/angelvisuals/a/cG;
        //    253: astore  11
        //    255: aload  11
        //    257: arraylength
        //    258: istore  12
        //    260: ldc  #12 // -1974135947
        //    262: ldc  #12 // -1974135947
        //    264: ixor
        //    265: istore  13
        //    267: iload  13
        //    269: iload  12
        //    271: if_icmpge  337 (offset +66)
        //    274: aload  11
        //    276: iload  13
        //    278: aaload
        //    279: astore  14
        //    281: dload_1
        //    282: dload_3
        //    283: aload_0
        //    284: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    287: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //    290: ldc  #211 // 7.0f
        //    292: fadd
        //    293: f2d
        //    294: fload  10
        //    296: f2d
        //    297: ldc2_w  #294 // 102.0d
        //    300: ldc2_w  #284 // 25.0d
        //    303: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    306: ifeq  324 (offset +18)
        //    309: aload_0
        //    310: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    313: aload  14
        //    315: invokevirtual  #337 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cG;)V
        //    318: ldc  #60 // -1071614694
        //    320: ldc  #61 // -1071614693
        //    322: ixor
        //    323: ireturn
        //    324: fload  10
        //    326: ldc  #223 // 30.0f
        //    328: fadd
        //    329: fstore  10
        //    331: iinc  13, 1
        //    334: goto  267 (offset -67)
        //    337: aload_0
        //    338: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    341: invokevirtual  #345 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cK;
        //    344: ifnull  383 (offset +39)
        //    347: iload  5
        //    349: ldc  #89 // -515002438
        //    351: ldc  #88 // -515002440
        //    353: ixor
        //    354: if_icmplt  383 (offset +29)
        //    357: aload_0
        //    358: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    361: invokevirtual  #345 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cK;
        //    364: iload  5
        //    366: invokevirtual  #361 // dev.angelvisuals.a.cK.v:(I)V
        //    369: aload_0
        //    370: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    373: aconst_null
        //    374: invokevirtual  #349 // dev.angelvisuals.a.bN.c:(Ldev/angelvisuals/a/cK;)V
        //    377: ldc  #65 // -1036197388
        //    379: ldc  #66 // -1036197387
        //    381: ixor
        //    382: ireturn
        //    383: aload_0
        //    384: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    387: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    390: ifnull  482 (offset +92)
        //    393: iload  5
        //    395: ldc  #32 // -1596823329
        //    397: ldc  #31 // -1596823331
        //    399: ixor
        //    400: if_icmplt  482 (offset +82)
        //    403: aload_0
        //    404: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    407: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    410: astore  12
        //    412: aload  12
        //    414: instanceof  #251 // dev.angelvisuals.a.ai
        //    417: ifeq  437 (offset +20)
        //    420: aload  12
        //    422: checkcast  #251 // dev.angelvisuals.a.ai
        //    425: astore  10
        //    427: aload  10
        //    429: iload  5
        //    431: invokevirtual  #322 // dev.angelvisuals.a.ai.d:(I)V
        //    434: goto  468 (offset +34)
        //    437: aload_0
        //    438: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    441: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    444: astore  12
        //    446: aload  12
        //    448: instanceof  #248 // dev.angelvisuals.a.aM
        //    451: ifeq  468 (offset +17)
        //    454: aload  12
        //    456: checkcast  #248 // dev.angelvisuals.a.aM
        //    459: astore  11
        //    461: aload  11
        //    463: iload  5
        //    465: invokevirtual  #318 // dev.angelvisuals.a.aM.f:(I)V
        //    468: aload_0
        //    469: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    472: aconst_null
        //    473: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    476: ldc  #1 // -2099463630
        //    478: ldc  #2 // -2099463629
        //    480: ixor
        //    481: ireturn
        //    482: aload_0
        //    483: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    486: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //    489: aload  7
        //    491: arraylength
        //    492: ldc  #231 // 120.0f
        //    494: invokestatic  #311 // dev.angelvisuals.a.L.a:(FIF)F
        //    497: fstore  10
        //    499: aload_0
        //    500: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    503: invokevirtual  #342 // dev.angelvisuals.a.bN.ap:()F
        //    506: aload_0
        //    507: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    510: invokevirtual  #343 // dev.angelvisuals.a.bN.aq:()F
        //    513: fadd
        //    514: invokestatic  #316 // dev.angelvisuals.a.L.e:(F)F
        //    517: fstore  11
        //    519: iload  5
        //    521: ifne  591 (offset +70)
        //    524: dload_1
        //    525: dload_3
        //    526: fload  10
        //    528: f2d
        //    529: fload  11
        //    531: f2d
        //    532: ldc2_w  #296 // 120.0d
        //    535: ldc2_w  #282 // 22.0d
        //    538: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    541: ifeq  591 (offset +50)
        //    544: aload_0
        //    545: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    548: ldc  #16 // -1928100793
        //    550: ldc  #15 // -1928100794
        //    552: ixor
        //    553: invokevirtual  #351 // dev.angelvisuals.a.bN.i:(Z)V
        //    556: aload_0
        //    557: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    560: ldc  #42 // -1363879814
        //    562: ldc  #42 // -1363879814
        //    564: ixor
        //    565: invokevirtual  #352 // dev.angelvisuals.a.bN.j:(Z)V
        //    568: aload_0
        //    569: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    572: aload_0
        //    573: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    576: invokevirtual  #327 // dev.angelvisuals.a.bN.P:()Ljava/lang/String;
        //    579: invokevirtual  #371 // java.lang.String.length:()I
        //    582: invokevirtual  #353 // dev.angelvisuals.a.bN.n:(I)V
        //    585: ldc  #120 // 81253745
        //    587: ldc  #119 // 81253744
        //    589: ixor
        //    590: ireturn
        //    591: iload  5
        //    593: ifne  618 (offset +25)
        //    596: aload_0
        //    597: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    600: invokevirtual  #324 // dev.angelvisuals.a.bN.K:()Z
        //    603: ifeq  618 (offset +15)
        //    606: aload_0
        //    607: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    610: ldc  #53 // -1170144041
        //    612: ldc  #53 // -1170144041
        //    614: ixor
        //    615: invokevirtual  #351 // dev.angelvisuals.a.bN.i:(Z)V
        //    618: ldc  #133 // 409002994
        //    620: ldc  #133 // 409002994
        //    622: ixor
        //    623: istore  12
        //    625: iload  12
        //    627: aload  7
        //    629: arraylength
        //    630: if_icmpge  1037 (offset +407)
        //    633: aload  7
        //    635: iload  12
        //    637: aaload
        //    638: astore  13
        //    640: aload_0
        //    641: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    644: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    647: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    650: if_acmpne  668 (offset +18)
        //    653: aload_0
        //    654: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    657: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //    660: iload  12
        //    662: invokestatic  #310 // dev.angelvisuals.a.L.a:(FI)F
        //    665: goto  678 (offset +13)
        //    668: aload_0
        //    669: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    672: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //    675: ldc  #234 // 126.0f
        //    677: fadd
        //    678: fstore  14
        //    680: aload_0
        //    681: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    684: invokevirtual  #342 // dev.angelvisuals.a.bN.ap:()F
        //    687: aload_0
        //    688: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    691: invokevirtual  #343 // dev.angelvisuals.a.bN.aq:()F
        //    694: fadd
        //    695: fstore  15
        //    697: aload_0
        //    698: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    701: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    704: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    707: if_acmpne  715 (offset +8)
        //    710: ldc  #218 // 18.0f
        //    712: goto  717 (offset +5)
        //    715: ldc  #221 // 25.0f
        //    717: fstore  16
        //    719: fload  15
        //    721: fload  16
        //    723: fadd
        //    724: fstore  17
        //    726: ldc  #235 // 260.0f
        //    728: fload  16
        //    730: fsub
        //    731: ldc  #208 // 3.0f
        //    733: fsub
        //    734: fstore  18
        //    736: aload_0
        //    737: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    740: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    743: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    746: if_acmpne  754 (offset +8)
        //    749: ldc  #230 // 105.0f
        //    751: goto  756 (offset +5)
        //    754: ldc  #233 // 125.0f
        //    756: fstore  19
        //    758: dload_1
        //    759: dload_3
        //    760: fload  14
        //    762: f2d
        //    763: fload  17
        //    765: f2d
        //    766: fload  19
        //    768: f2d
        //    769: fload  18
        //    771: f2d
        //    772: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    775: ifne  781 (offset +6)
        //    778: goto  1031 (offset +253)
        //    781: fload  17
        //    783: fconst_2
        //    784: fadd
        //    785: aload_0
        //    786: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    789: aload  13
        //    791: invokevirtual  #335 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cG;)F
        //    794: fadd
        //    795: fstore  20
        //    797: aload_0
        //    798: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    801: aload  13
        //    803: invokevirtual  #336 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cG;)Ljava/util/List;
        //    806: invokeinterface  #379 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    811: astore  21
        //    813: aload  21
        //    815: invokeinterface  #376 // java.util.Iterator.hasNext:()Z, count 1
        //    820: ifeq  1031 (offset +211)
        //    823: aload  21
        //    825: invokeinterface  #377 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    830: checkcast  #258 // dev.angelvisuals.a.cK
        //    833: astore  22
        //    835: aload_0
        //    836: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    839: aload  22
        //    841: invokevirtual  #346 // dev.angelvisuals.a.bN.b:(Ldev/angelvisuals/a/cK;)F
        //    844: fstore  23
        //    846: aload  22
        //    848: fload  23
        //    850: invokestatic  #315 // dev.angelvisuals.a.L.a:(Ldev/angelvisuals/a/cK;F)F
        //    853: fstore  24
        //    855: dload_1
        //    856: dload_3
        //    857: fload  14
        //    859: ldc  #207 // 2.5f
        //    861: fadd
        //    862: f2d
        //    863: fload  20
        //    865: f2d
        //    866: ldc2_w  #292 // 100.0d
        //    869: ldc2_w  #280 // 19.0d
        //    872: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    875: ifeq  969 (offset +94)
        //    878: iload  5
        //    880: ifne  894 (offset +14)
        //    883: aload  22
        //    885: invokevirtual  #359 // dev.angelvisuals.a.cK.aD:()V
        //    888: ldc  #122 // 205127580
        //    890: ldc  #123 // 205127581
        //    892: ixor
        //    893: ireturn
        //    894: iload  5
        //    896: ldc  #100 // -269050674
        //    898: ldc  #101 // -269050673
        //    900: ixor
        //    901: if_icmpne  938 (offset +37)
        //    904: aload  22
        //    906: invokestatic  #314 // dev.angelvisuals.a.L.a:(Ldev/angelvisuals/a/cK;)Z
        //    909: ifeq  932 (offset +23)
        //    912: aload_0
        //    913: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    916: aload  22
        //    918: invokevirtual  #347 // dev.angelvisuals.a.bN.b:(Ldev/angelvisuals/a/cK;)V
        //    921: aload_0
        //    922: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    925: aload  13
        //    927: fload  18
        //    929: invokevirtual  #339 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cG;F)V
        //    932: ldc  #169 // 1178117061
        //    934: ldc  #168 // 1178117060
        //    936: ixor
        //    937: ireturn
        //    938: iload  5
        //    940: ldc  #27 // -1677962742
        //    942: ldc  #26 // -1677962744
        //    944: ixor
        //    945: if_icmpne  963 (offset +18)
        //    948: aload_0
        //    949: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    952: aload  22
        //    954: invokevirtual  #349 // dev.angelvisuals.a.bN.c:(Ldev/angelvisuals/a/cK;)V
        //    957: ldc  #188 // 1671597443
        //    959: ldc  #187 // 1671597442
        //    961: ixor
        //    962: ireturn
        //    963: ldc  #103 // -188413565
        //    965: ldc  #102 // -188413566
        //    967: ixor
        //    968: ireturn
        //    969: aload_0
        //    970: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    973: aload  22
        //    975: invokevirtual  #348 // dev.angelvisuals.a.bN.b:(Ldev/angelvisuals/a/cK;)Z
        //    978: ifeq  1018 (offset +40)
        //    981: fload  23
        //    983: ldc  #206 // 0.10000000149011612f
        //    985: fcmpl
        //    986: ifle  1018 (offset +32)
        //    989: aload_0
        //    990: dload_1
        //    991: dload_3
        //    992: iload  5
        //    994: fload  14
        //    996: ldc  #207 // 2.5f
        //    998: fadd
        //    999: fload  20
        //   1001: aload  22
        //   1003: invokevirtual  #360 // dev.angelvisuals.a.cK.t:()Ljava/util/List;
        //   1006: invokevirtual  #364 // dev.angelvisuals.a.d.a:(DDIFFLjava/util/List;)Z
        //   1009: ifeq  1018 (offset +9)
        //   1012: ldc  #51 // -1178029248
        //   1014: ldc  #52 // -1178029247
        //   1016: ixor
        //   1017: ireturn
        //   1018: fload  20
        //   1020: ldc  #209 // 4.0f
        //   1022: fload  24
        //   1024: fadd
        //   1025: fadd
        //   1026: fstore  20
        //   1028: goto  813 (offset -215)
        //   1031: iinc  12, 1
        //   1034: goto  625 (offset -409)
        //   1037: ldc  #186 // 1633232067
        //   1039: ldc  #186 // 1633232067
        //   1041: ixor
        //   1042: ireturn
    }

  public boolean method1001(char arg0, int arg1) { // было: a
        if (field615.method1070() == null) {
            if (field615.method1064()) {
                if (!Character.isISOControl(arg0)) {
                    String var3 = field615.method1066();
                    if (var3.length() < (-908096483 ^ -908096507)) {
                        int var4 = Math.max(817906064 ^ 817906064, Math.min(field615.method1068(), var3.length()));
                        field615.method1067(var3.substring(1351036690 ^ 1351036690, var4) + arg0 + var3.substring(var4));
                        field615.method1069(var4 + (1552182151 ^ 1552182150));
                        return -117724049 ^ -117724050;
                    } else {
                        return 757340928 ^ 757340929;
                    }
                } else {
                    return 1545117858 ^ 1545117858;
                }
            } else {
                return 363259374 ^ 363259374;
            }
        } else {
            if (!Character.isISOControl(arg0)) {
                cA var3 = field615.method1070();
                String var4 = var3.method736();
                if (var4.length() < var3.ad()) {
                    int var5 = Math.max(4842610 ^ 4842610, Math.min(field615.method1072(), var4.length()));
                    var3.method733(var4.substring(-1095312364 ^ -1095312364, var5) + arg0 + var4.substring(var5));
                    field615.method1073(var5 + (903483535 ^ 903483534));
                    return 681557651 ^ 681557650;
                } else {
                    return 1678855449 ^ 1678855448;
                }
            } else {
                return 1238983192 ^ 1238983192;
            }
        }
    }

  public boolean method1002(int arg0) { // было: a
        if (arg0 != 0) {
            return -1787841657 ^ -1787841657;
        } else {
            if (field616 == null) {
                return -1787841657 ^ -1787841657;
            } else {
                field615.method1077(null);
                field616 = null;
                return 1339016075 ^ 1339016074;
            }
        }
    }

  public boolean method1003(double arg0, double arg1, int arg2) { // было: a
        if (arg2 != 0) {
            return 655292725 ^ 655292725;
        } else {
            if (field616 != null) {
                field616.af(field615.method1047(field616, field617, arg0));
                return -668435720 ^ -668435719;
            } else {
                return 655292725 ^ 655292725;
            }
        }
    }

  public boolean method1004(double arg0, double arg1, double arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //      4: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //      7: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //     10: if_acmpne  19 (offset +9)
        //     13: invokestatic  #358 // dev.angelvisuals.a.cG.a:()[Ldev/angelvisuals/a/cG;
        //     16: goto  41 (offset +25)
        //     19: ldc  #198 // 1907539646
        //     21: ldc  #199 // 1907539647
        //     23: ixor
        //     24: anewarray  #257 // dev.angelvisuals.a.cG
        //     27: dup
        //     28: ldc  #110 // -99198612
        //     30: ldc  #110 // -99198612
        //     32: ixor
        //     33: aload_0
        //     34: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     37: invokevirtual  #344 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cG;
        //     40: aastore
        //     41: astore  7
        //     43: ldc  #182 // 1527939271
        //     45: ldc  #182 // 1527939271
        //     47: ixor
        //     48: istore  8
        //     50: iload  8
        //     52: aload  7
        //     54: arraylength
        //     55: if_icmpge  239 (offset +184)
        //     58: aload  7
        //     60: iload  8
        //     62: aaload
        //     63: astore  9
        //     65: aload_0
        //     66: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     69: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //     72: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //     75: if_acmpne  93 (offset +18)
        //     78: aload_0
        //     79: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     82: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //     85: iload  8
        //     87: invokestatic  #310 // dev.angelvisuals.a.L.a:(FI)F
        //     90: goto  103 (offset +13)
        //     93: aload_0
        //     94: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     97: invokevirtual  #341 // dev.angelvisuals.a.bN.ao:()F
        //    100: ldc  #234 // 126.0f
        //    102: fadd
        //    103: fstore  10
        //    105: aload_0
        //    106: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    109: invokevirtual  #342 // dev.angelvisuals.a.bN.ap:()F
        //    112: aload_0
        //    113: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    116: invokevirtual  #343 // dev.angelvisuals.a.bN.aq:()F
        //    119: fadd
        //    120: fstore  11
        //    122: fload  11
        //    124: aload_0
        //    125: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    128: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    131: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    134: if_acmpne  142 (offset +8)
        //    137: ldc  #218 // 18.0f
        //    139: goto  144 (offset +5)
        //    142: ldc  #221 // 25.0f
        //    144: fadd
        //    145: fstore  12
        //    147: ldc  #235 // 260.0f
        //    149: aload_0
        //    150: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    153: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    156: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    159: if_acmpne  167 (offset +8)
        //    162: ldc  #219 // 20.0f
        //    164: goto  169 (offset +5)
        //    167: ldc  #222 // 28.0f
        //    169: fsub
        //    170: fstore  13
        //    172: aload_0
        //    173: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    176: invokevirtual  #328 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/bN$a;
        //    179: getstatic  #299 // dev.angelvisuals.a.bN$a.c:Ldev/angelvisuals/a/bN$a;
        //    182: if_acmpne  190 (offset +8)
        //    185: ldc  #230 // 105.0f
        //    187: goto  192 (offset +5)
        //    190: ldc  #233 // 125.0f
        //    192: fstore  14
        //    194: dload_1
        //    195: dload_3
        //    196: fload  10
        //    198: f2d
        //    199: fload  12
        //    201: f2d
        //    202: fload  14
        //    204: f2d
        //    205: fload  13
        //    207: f2d
        //    208: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    211: ifeq  233 (offset +22)
        //    214: aload_0
        //    215: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    218: aload  9
        //    220: dload  5
        //    222: fload  13
        //    224: invokevirtual  #338 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cG;DF)V
        //    227: ldc  #95 // -395580347
        //    229: ldc  #94 // -395580348
        //    231: ixor
        //    232: ireturn
        //    233: iinc  8, 1
        //    236: goto  50 (offset -186)
        //    239: ldc  #56 // -1113341360
        //    241: ldc  #56 // -1113341360
        //    243: ixor
        //    244: ireturn
    }

  public boolean method1005(int arg0, int arg1) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //      4: invokevirtual  #329 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/cA;
        //      7: ifnull  291 (offset +284)
        //     10: aload_0
        //     11: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     14: invokevirtual  #329 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/cA;
        //     17: astore_3
        //     18: aload_3
        //     19: invokevirtual  #355 // dev.angelvisuals.a.cA.V:()Ljava/lang/String;
        //     22: astore  4
        //     24: ldc  #37 // -1556376720
        //     26: ldc  #37 // -1556376720
        //     28: ixor
        //     29: aload_0
        //     30: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     33: invokevirtual  #326 // dev.angelvisuals.a.bN.P:()I
        //     36: aload  4
        //     38: invokevirtual  #371 // java.lang.String.length:()I
        //     41: invokestatic  #368 // java.lang.Math.min:(II)I
        //     44: invokestatic  #367 // java.lang.Math.max:(II)I
        //     47: istore  5
        //     49: iload_1
        //     50: ldc  #76 // -701329360
        //     52: ldc  #77 // -701329104
        //     54: ixor
        //     55: if_icmpeq  67 (offset +12)
        //     58: iload_1
        //     59: ldc  #54 // -1168018416
        //     61: ldc  #55 // -1168018159
        //     63: ixor
        //     64: if_icmpne  81 (offset +17)
        //     67: aload_0
        //     68: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //     71: aconst_null
        //     72: invokevirtual  #334 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cA;)V
        //     75: ldc  #126 // 308384886
        //     77: ldc  #127 // 308384887
        //     79: ixor
        //     80: ireturn
        //     81: iload_1
        //     82: ldc  #131 // 394223313
        //     84: ldc  #132 // 394223570
        //     86: ixor
        //     87: if_icmpne  150 (offset +63)
        //     90: iload  5
        //     92: ifle  144 (offset +52)
        //     95: aload_3
        //     96: aload  4
        //     98: ldc  #203 // 2121016737
        //    100: ldc  #203 // 2121016737
        //    102: ixor
        //    103: iload  5
        //    105: ldc  #43 // -1335869144
        //    107: ldc  #44 // -1335869143
        //    109: ixor
        //    110: isub
        //    111: invokevirtual  #373 // java.lang.String.substring:(II)Ljava/lang/String;
        //    114: aload  4
        //    116: iload  5
        //    118: invokevirtual  #372 // java.lang.String.substring:(I)Ljava/lang/String;
        //    121: invokedynamic  #385 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    126: invokevirtual  #357 // dev.angelvisuals.a.cA.i:(Ljava/lang/String;)V
        //    129: aload_0
        //    130: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    133: iload  5
        //    135: ldc  #39 // -1488288107
        //    137: ldc  #38 // -1488288108
        //    139: ixor
        //    140: isub
        //    141: invokevirtual  #354 // dev.angelvisuals.a.bN.o:(I)V
        //    144: ldc  #163 // 1073614047
        //    146: ldc  #162 // 1073614046
        //    148: ixor
        //    149: ireturn
        //    150: iload_1
        //    151: ldc  #114 // -15472460
        //    153: ldc  #115 // -15472207
        //    155: ixor
        //    156: if_icmpne  209 (offset +53)
        //    159: iload  5
        //    161: aload  4
        //    163: invokevirtual  #371 // java.lang.String.length:()I
        //    166: if_icmpge  203 (offset +37)
        //    169: aload_3
        //    170: aload  4
        //    172: ldc  #86 // -537594497
        //    174: ldc  #86 // -537594497
        //    176: ixor
        //    177: iload  5
        //    179: invokevirtual  #373 // java.lang.String.substring:(II)Ljava/lang/String;
        //    182: aload  4
        //    184: iload  5
        //    186: ldc  #35 // -1563396476
        //    188: ldc  #36 // -1563396475
        //    190: ixor
        //    191: iadd
        //    192: invokevirtual  #372 // java.lang.String.substring:(I)Ljava/lang/String;
        //    195: invokedynamic  #385 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    200: invokevirtual  #357 // dev.angelvisuals.a.cA.i:(Ljava/lang/String;)V
        //    203: ldc  #68 // -1034534473
        //    205: ldc  #67 // -1034534474
        //    207: ixor
        //    208: ireturn
        //    209: iload_1
        //    210: ldc  #25 // -1778905634
        //    212: ldc  #24 // -1778905895
        //    214: ixor
        //    215: if_icmpne  247 (offset +32)
        //    218: aload_0
        //    219: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    222: ldc  #177 // 1412624533
        //    224: ldc  #177 // 1412624533
        //    226: ixor
        //    227: iload  5
        //    229: ldc  #193 // 1796935995
        //    231: ldc  #192 // 1796935994
        //    233: ixor
        //    234: isub
        //    235: invokestatic  #367 // java.lang.Math.max:(II)I
        //    238: invokevirtual  #354 // dev.angelvisuals.a.bN.o:(I)V
        //    241: ldc  #74 // -704622764
        //    243: ldc  #75 // -704622763
        //    245: ixor
        //    246: ireturn
        //    247: iload_1
        //    248: ldc  #3 // -2079893472
        //    250: ldc  #4 // -2079893210
        //    252: ixor
        //    253: if_icmpne  285 (offset +32)
        //    256: aload_0
        //    257: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    260: aload  4
        //    262: invokevirtual  #371 // java.lang.String.length:()I
        //    265: iload  5
        //    267: ldc  #146 // 690924830
        //    269: ldc  #147 // 690924831
        //    271: ixor
        //    272: iadd
        //    273: invokestatic  #368 // java.lang.Math.min:(II)I
        //    276: invokevirtual  #354 // dev.angelvisuals.a.bN.o:(I)V
        //    279: ldc  #200 // 1962093190
        //    281: ldc  #201 // 1962093191
        //    283: ixor
        //    284: ireturn
        //    285: ldc  #28 // -1629811248
        //    287: ldc  #29 // -1629811247
        //    289: ixor
        //    290: ireturn
        //    291: aload_0
        //    292: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    295: invokevirtual  #324 // dev.angelvisuals.a.bN.K:()Z
        //    298: ifeq  579 (offset +281)
        //    301: aload_0
        //    302: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    305: invokevirtual  #327 // dev.angelvisuals.a.bN.P:()Ljava/lang/String;
        //    308: astore_3
        //    309: ldc  #137 // 482771241
        //    311: ldc  #137 // 482771241
        //    313: ixor
        //    314: aload_0
        //    315: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    318: invokevirtual  #325 // dev.angelvisuals.a.bN.O:()I
        //    321: aload_3
        //    322: invokevirtual  #371 // java.lang.String.length:()I
        //    325: invokestatic  #368 // java.lang.Math.min:(II)I
        //    328: invokestatic  #367 // java.lang.Math.max:(II)I
        //    331: istore  4
        //    333: iload_1
        //    334: ldc  #109 // -102159387
        //    336: ldc  #108 // -102159643
        //    338: ixor
        //    339: if_icmpeq  351 (offset +12)
        //    342: iload_1
        //    343: ldc  #112 // -76857539
        //    345: ldc  #111 // -76857796
        //    347: ixor
        //    348: if_icmpne  369 (offset +21)
        //    351: aload_0
        //    352: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    355: ldc  #134 // 441633248
        //    357: ldc  #134 // 441633248
        //    359: ixor
        //    360: invokevirtual  #351 // dev.angelvisuals.a.bN.i:(Z)V
        //    363: ldc  #141 // 653777200
        //    365: ldc  #142 // 653777201
        //    367: ixor
        //    368: ireturn
        //    369: iload_1
        //    370: ldc  #13 // -1945206546
        //    372: ldc  #14 // -1945206291
        //    374: ixor
        //    375: if_icmpne  439 (offset +64)
        //    378: iload  4
        //    380: ifle  433 (offset +53)
        //    383: aload_0
        //    384: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    387: aload_3
        //    388: ldc  #154 // 895905394
        //    390: ldc  #154 // 895905394
        //    392: ixor
        //    393: iload  4
        //    395: ldc  #205 // 2144011933
        //    397: ldc  #204 // 2144011932
        //    399: ixor
        //    400: isub
        //    401: invokevirtual  #373 // java.lang.String.substring:(II)Ljava/lang/String;
        //    404: aload_3
        //    405: iload  4
        //    407: invokevirtual  #372 // java.lang.String.substring:(I)Ljava/lang/String;
        //    410: invokedynamic  #385 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    415: invokevirtual  #350 // dev.angelvisuals.a.bN.e:(Ljava/lang/String;)V
        //    418: aload_0
        //    419: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    422: iload  4
        //    424: ldc  #6 // -2039943235
        //    426: ldc  #5 // -2039943236
        //    428: ixor
        //    429: isub
        //    430: invokevirtual  #353 // dev.angelvisuals.a.bN.n:(I)V
        //    433: ldc  #105 // -160955431
        //    435: ldc  #104 // -160955432
        //    437: ixor
        //    438: ireturn
        //    439: iload_1
        //    440: ldc  #57 // -1100967390
        //    442: ldc  #58 // -1100967129
        //    444: ixor
        //    445: if_icmpne  498 (offset +53)
        //    448: iload  4
        //    450: aload_3
        //    451: invokevirtual  #371 // java.lang.String.length:()I
        //    454: if_icmpge  492 (offset +38)
        //    457: aload_0
        //    458: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    461: aload_3
        //    462: ldc  #174 // 1346115466
        //    464: ldc  #174 // 1346115466
        //    466: ixor
        //    467: iload  4
        //    469: invokevirtual  #373 // java.lang.String.substring:(II)Ljava/lang/String;
        //    472: aload_3
        //    473: iload  4
        //    475: ldc  #96 // -300107480
        //    477: ldc  #97 // -300107479
        //    479: ixor
        //    480: iadd
        //    481: invokevirtual  #372 // java.lang.String.substring:(I)Ljava/lang/String;
        //    484: invokedynamic  #385 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    489: invokevirtual  #350 // dev.angelvisuals.a.bN.e:(Ljava/lang/String;)V
        //    492: ldc  #64 // -1039888499
        //    494: ldc  #63 // -1039888500
        //    496: ixor
        //    497: ireturn
        //    498: iload_1
        //    499: ldc  #164 // 1122971146
        //    501: ldc  #165 // 1122971405
        //    503: ixor
        //    504: if_icmpne  536 (offset +32)
        //    507: aload_0
        //    508: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    511: ldc  #152 // 813815512
        //    513: ldc  #152 // 813815512
        //    515: ixor
        //    516: iload  4
        //    518: ldc  #11 // -1975500109
        //    520: ldc  #10 // -1975500110
        //    522: ixor
        //    523: isub
        //    524: invokestatic  #367 // java.lang.Math.max:(II)I
        //    527: invokevirtual  #353 // dev.angelvisuals.a.bN.n:(I)V
        //    530: ldc  #45 // -1323304174
        //    532: ldc  #46 // -1323304173
        //    534: ixor
        //    535: ireturn
        //    536: iload_1
        //    537: ldc  #196 // 1889295512
        //    539: ldc  #197 // 1889295774
        //    541: ixor
        //    542: if_icmpne  573 (offset +31)
        //    545: aload_0
        //    546: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    549: aload_3
        //    550: invokevirtual  #371 // java.lang.String.length:()I
        //    553: iload  4
        //    555: ldc  #19 // -1914111004
        //    557: ldc  #20 // -1914111003
        //    559: ixor
        //    560: iadd
        //    561: invokestatic  #368 // java.lang.Math.min:(II)I
        //    564: invokevirtual  #353 // dev.angelvisuals.a.bN.n:(I)V
        //    567: ldc  #167 // 1170415771
        //    569: ldc  #166 // 1170415770
        //    571: ixor
        //    572: ireturn
        //    573: ldc  #118 // 66893763
        //    575: ldc  #117 // 66893762
        //    577: ixor
        //    578: ireturn
        //    579: aload_0
        //    580: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    583: invokevirtual  #345 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cK;
        //    586: ifnull  678 (offset +92)
        //    589: iload_1
        //    590: ldc  #181 // 1496977267
        //    592: ldc  #180 // 1496977011
        //    594: ixor
        //    595: if_icmpne  609 (offset +14)
        //    598: aload_0
        //    599: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    602: aconst_null
        //    603: invokevirtual  #349 // dev.angelvisuals.a.bN.c:(Ldev/angelvisuals/a/cK;)V
        //    606: goto  672 (offset +66)
        //    609: iload_1
        //    610: ldc  #158 // 962385666
        //    612: ldc  #157 // 962385415
        //    614: ixor
        //    615: if_icmpeq  627 (offset +12)
        //    618: iload_1
        //    619: ldc  #129 // 390859425
        //    621: ldc  #130 // 390859682
        //    623: ixor
        //    624: if_icmpne  653 (offset +29)
        //    627: aload_0
        //    628: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    631: invokevirtual  #345 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cK;
        //    634: ldc  #159 // 1049553762
        //    636: ldc  #62 // -1049553763
        //    638: ixor
        //    639: invokevirtual  #361 // dev.angelvisuals.a.cK.v:(I)V
        //    642: aload_0
        //    643: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    646: aconst_null
        //    647: invokevirtual  #349 // dev.angelvisuals.a.bN.c:(Ldev/angelvisuals/a/cK;)V
        //    650: goto  672 (offset +22)
        //    653: aload_0
        //    654: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    657: invokevirtual  #345 // dev.angelvisuals.a.bN.b:()Ldev/angelvisuals/a/cK;
        //    660: iload_1
        //    661: invokevirtual  #361 // dev.angelvisuals.a.cK.v:(I)V
        //    664: aload_0
        //    665: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    668: aconst_null
        //    669: invokevirtual  #349 // dev.angelvisuals.a.bN.c:(Ldev/angelvisuals/a/cK;)V
        //    672: ldc  #149 // 752689623
        //    674: ldc  #148 // 752689622
        //    676: ixor
        //    677: ireturn
        //    678: aload_0
        //    679: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    682: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    685: ifnull  888 (offset +203)
        //    688: aload_0
        //    689: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    692: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    695: astore  5
        //    697: aload  5
        //    699: instanceof  #251 // dev.angelvisuals.a.ai
        //    702: ifeq  785 (offset +83)
        //    705: aload  5
        //    707: checkcast  #251 // dev.angelvisuals.a.ai
        //    710: astore_3
        //    711: iload_1
        //    712: ldc  #92 // -479236473
        //    714: ldc  #93 // -479236217
        //    716: ixor
        //    717: if_icmpne  731 (offset +14)
        //    720: aload_0
        //    721: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    724: aconst_null
        //    725: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    728: goto  882 (offset +154)
        //    731: iload_1
        //    732: ldc  #84 // -539664188
        //    734: ldc  #85 // -539663935
        //    736: ixor
        //    737: if_icmpeq  749 (offset +12)
        //    740: iload_1
        //    741: ldc  #82 // -652491783
        //    743: ldc  #81 // -652492038
        //    745: ixor
        //    746: if_icmpne  769 (offset +23)
        //    749: aload_3
        //    750: ldc  #9 // -2023045782
        //    752: ldc  #202 // 2023045781
        //    754: ixor
        //    755: invokevirtual  #322 // dev.angelvisuals.a.ai.d:(I)V
        //    758: aload_0
        //    759: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    762: aconst_null
        //    763: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    766: goto  882 (offset +116)
        //    769: aload_3
        //    770: iload_1
        //    771: invokevirtual  #322 // dev.angelvisuals.a.ai.d:(I)V
        //    774: aload_0
        //    775: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    778: aconst_null
        //    779: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    782: goto  882 (offset +100)
        //    785: aload_0
        //    786: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    789: invokevirtual  #330 // dev.angelvisuals.a.bN.a:()Ldev/angelvisuals/a/q;
        //    792: astore  5
        //    794: aload  5
        //    796: instanceof  #248 // dev.angelvisuals.a.aM
        //    799: ifeq  882 (offset +83)
        //    802: aload  5
        //    804: checkcast  #248 // dev.angelvisuals.a.aM
        //    807: astore  4
        //    809: iload_1
        //    810: ldc  #161 // 1049779696
        //    812: ldc  #160 // 1049779440
        //    814: ixor
        //    815: if_icmpne  829 (offset +14)
        //    818: aload_0
        //    819: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    822: aconst_null
        //    823: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    826: goto  882 (offset +56)
        //    829: iload_1
        //    830: ldc  #125 // 298090262
        //    832: ldc  #124 // 298090003
        //    834: ixor
        //    835: if_icmpeq  847 (offset +12)
        //    838: iload_1
        //    839: ldc  #98 // -297172896
        //    841: ldc  #99 // -297172637
        //    843: ixor
        //    844: if_icmpne  868 (offset +24)
        //    847: aload  4
        //    849: ldc  #83 // -602888474
        //    851: ldc  #140 // 602888473
        //    853: ixor
        //    854: invokevirtual  #318 // dev.angelvisuals.a.aM.f:(I)V
        //    857: aload_0
        //    858: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    861: aconst_null
        //    862: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    865: goto  882 (offset +17)
        //    868: aload  4
        //    870: iload_1
        //    871: invokevirtual  #318 // dev.angelvisuals.a.aM.f:(I)V
        //    874: aload_0
        //    875: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    878: aconst_null
        //    879: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    882: ldc  #22 // -1787380600
        //    884: ldc  #23 // -1787380599
        //    886: ixor
        //    887: ireturn
        //    888: ldc  #113 // -49669104
        //    890: ldc  #113 // -49669104
        //    892: ixor
        //    893: ireturn
    }

  private boolean method1006(double arg0, double arg1, int arg2, float arg3, float arg4, List arg5) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #217 // 17.5f
        //      2: fstore  9
        //      4: ldc  #207 // 2.5f
        //      6: fstore  10
        //      8: aload  8
        //     10: invokeinterface  #381 // java.util.List.stream:()Ljava/util/stream/Stream;, count 1
        //     15: invokedynamic  #386 // invokedynamic test:()Ljava/util/function/Predicate;
        //     20: invokeinterface  #382 // java.util.stream.Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;, count 2
        //     25: invokeinterface  #383 // java.util.stream.Stream.toList:()Ljava/util/List;, count 1
        //     30: astore  11
        //     32: ldc  #191 // 1724911808
        //     34: ldc  #191 // 1724911808
        //     36: ixor
        //     37: istore  12
        //     39: iload  12
        //     41: aload  11
        //     43: invokeinterface  #380 // java.util.List.size:()I, count 1
        //     48: if_icmpge  845 (offset +797)
        //     51: aload  11
        //     53: iload  12
        //     55: invokeinterface  #378 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //     60: checkcast  #262 // dev.angelvisuals.a.q
        //     63: astore  13
        //     65: fload  7
        //     67: fload  9
        //     69: fadd
        //     70: ldc  #208 // 3.0f
        //     72: fadd
        //     73: fstore  14
        //     75: fconst_0
        //     76: fstore  15
        //     78: aload  13
        //     80: instanceof  #248 // dev.angelvisuals.a.aM
        //     83: ifeq  191 (offset +108)
        //     86: aload  13
        //     88: checkcast  #248 // dev.angelvisuals.a.aM
        //     91: astore  16
        //     93: iload  5
        //     95: ifne  134 (offset +39)
        //     98: dload_1
        //     99: dload_3
        //    100: fload  6
        //    102: ldc  #211 // 7.0f
        //    104: fadd
        //    105: f2d
        //    106: fload  14
        //    108: fconst_2
        //    109: fsub
        //    110: f2d
        //    111: ldc2_w  #288 // 91.0d
        //    114: ldc2_w  #278 // 12.0d
        //    117: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    120: ifeq  134 (offset +14)
        //    123: aload  16
        //    125: invokevirtual  #317 // dev.angelvisuals.a.aM.R:()V
        //    128: ldc  #48 // -1315007561
        //    130: ldc  #47 // -1315007562
        //    132: ixor
        //    133: ireturn
        //    134: iload  5
        //    136: ldc  #40 // -1376835043
        //    138: ldc  #41 // -1376835041
        //    140: ixor
        //    141: if_icmpne  184 (offset +43)
        //    144: dload_1
        //    145: dload_3
        //    146: fload  6
        //    148: ldc  #211 // 7.0f
        //    150: fadd
        //    151: f2d
        //    152: fload  14
        //    154: fconst_2
        //    155: fsub
        //    156: f2d
        //    157: ldc2_w  #288 // 91.0d
        //    160: ldc2_w  #278 // 12.0d
        //    163: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    166: ifeq  184 (offset +18)
        //    169: aload_0
        //    170: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    173: aload  16
        //    175: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    178: ldc  #135 // 453589504
        //    180: ldc  #136 // 453589505
        //    182: ixor
        //    183: ireturn
        //    184: ldc  #215 // 12.0f
        //    186: fstore  15
        //    188: goto  807 (offset +619)
        //    191: aload  13
        //    193: instanceof  #252 // dev.angelvisuals.a.bA
        //    196: ifeq  294 (offset +98)
        //    199: aload  13
        //    201: checkcast  #252 // dev.angelvisuals.a.bA
        //    204: astore  17
        //    206: iload  5
        //    208: ifne  287 (offset +79)
        //    211: dload_1
        //    212: dload_3
        //    213: fload  6
        //    215: ldc  #211 // 7.0f
        //    217: fadd
        //    218: f2d
        //    219: fload  14
        //    221: ldc  #213 // 9.0f
        //    223: fadd
        //    224: f2d
        //    225: ldc2_w  #286 // 88.0d
        //    228: ldc2_w  #274 // 6.0d
        //    231: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    234: ifeq  287 (offset +53)
        //    237: aload  17
        //    239: aload_0
        //    240: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    243: aload  17
        //    245: fload  6
        //    247: ldc  #211 // 7.0f
        //    249: fadd
        //    250: dload_1
        //    251: invokevirtual  #332 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/bA;FD)F
        //    254: invokevirtual  #323 // dev.angelvisuals.a.bA.af:(F)V
        //    257: aload_0
        //    258: aload  17
        //    260: putfield  #302 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bA;
        //    263: aload_0
        //    264: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    267: aload  17
        //    269: invokevirtual  #331 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/bA;)V
        //    272: aload_0
        //    273: fload  6
        //    275: ldc  #211 // 7.0f
        //    277: fadd
        //    278: putfield  #304 // dev.angelvisuals.a.d.b:F
        //    281: ldc  #7 // -2034182220
        //    283: ldc  #8 // -2034182219
        //    285: ixor
        //    286: ireturn
        //    287: ldc  #220 // 22.0f
        //    289: fstore  15
        //    291: goto  807 (offset +516)
        //    294: aload  13
        //    296: instanceof  #249 // dev.angelvisuals.a.aZ
        //    299: ifeq  475 (offset +176)
        //    302: aload  13
        //    304: checkcast  #249 // dev.angelvisuals.a.aZ
        //    307: astore  18
        //    309: fload  6
        //    311: ldc  #211 // 7.0f
        //    313: fadd
        //    314: fstore  22
        //    316: fload  14
        //    318: ldc  #215 // 12.0f
        //    320: fadd
        //    321: fstore  23
        //    323: ldc  #214 // 11.0f
        //    325: fstore  24
        //    327: aload  18
        //    329: invokevirtual  #320 // dev.angelvisuals.a.aZ.j:()Ljava/util/List;
        //    332: invokeinterface  #379 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    337: astore  25
        //    339: aload  25
        //    341: invokeinterface  #376 // java.util.Iterator.hasNext:()Z, count 1
        //    346: ifeq  465 (offset +119)
        //    349: aload  25
        //    351: invokeinterface  #377 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    356: checkcast  #250 // dev.angelvisuals.a.aZ$a
        //    359: astore  26
        //    361: getstatic  #301 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    364: aload  26
        //    366: invokevirtual  #321 // dev.angelvisuals.a.aZ$a.W:()Ljava/lang/String;
        //    369: ldc  #210 // 6.0f
        //    371: invokevirtual  #362 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    374: fstore  27
        //    376: fload  27
        //    378: ldc  #212 // 8.0f
        //    380: fadd
        //    381: fstore  28
        //    383: fload  22
        //    385: fload  28
        //    387: fadd
        //    388: fload  6
        //    390: ldc  #229 // 98.0f
        //    392: fadd
        //    393: fcmpl
        //    394: ifle  414 (offset +20)
        //    397: fload  6
        //    399: ldc  #211 // 7.0f
        //    401: fadd
        //    402: fstore  22
        //    404: fload  23
        //    406: fload  24
        //    408: ldc  #208 // 3.0f
        //    410: fadd
        //    411: fadd
        //    412: fstore  23
        //    414: iload  5
        //    416: ifne  452 (offset +36)
        //    419: dload_1
        //    420: dload_3
        //    421: fload  22
        //    423: f2d
        //    424: fload  23
        //    426: f2d
        //    427: fload  28
        //    429: f2d
        //    430: fload  24
        //    432: f2d
        //    433: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    436: ifeq  452 (offset +16)
        //    439: aload  18
        //    441: aload  26
        //    443: invokevirtual  #319 // dev.angelvisuals.a.aZ.a:(Ldev/angelvisuals/a/aZ$a;)V
        //    446: ldc  #90 // -511213502
        //    448: ldc  #91 // -511213501
        //    450: ixor
        //    451: ireturn
        //    452: fload  22
        //    454: fload  28
        //    456: ldc  #208 // 3.0f
        //    458: fadd
        //    459: fadd
        //    460: fstore  22
        //    462: goto  339 (offset -123)
        //    465: aload  18
        //    467: invokestatic  #313 // dev.angelvisuals.a.L.a:(Ldev/angelvisuals/a/aZ;)F
        //    470: fstore  15
        //    472: goto  807 (offset +335)
        //    475: aload  13
        //    477: instanceof  #245 // dev.angelvisuals.a.I
        //    480: ifeq  675 (offset +195)
        //    483: aload  13
        //    485: checkcast  #245 // dev.angelvisuals.a.I
        //    488: astore  19
        //    490: fload  6
        //    492: ldc  #211 // 7.0f
        //    494: fadd
        //    495: fstore  22
        //    497: fload  14
        //    499: ldc  #215 // 12.0f
        //    501: fadd
        //    502: fstore  23
        //    504: ldc  #214 // 11.0f
        //    506: fstore  24
        //    508: aload  19
        //    510: invokevirtual  #306 // dev.angelvisuals.a.I.d:()Ljava/util/List;
        //    513: invokeinterface  #379 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    518: astore  25
        //    520: aload  25
        //    522: invokeinterface  #376 // java.util.Iterator.hasNext:()Z, count 1
        //    527: ifeq  665 (offset +138)
        //    530: aload  25
        //    532: invokeinterface  #377 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    537: checkcast  #246 // dev.angelvisuals.a.I$a
        //    540: astore  26
        //    542: getstatic  #301 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    545: aload  26
        //    547: invokevirtual  #307 // dev.angelvisuals.a.I$a.ab:()Ljava/lang/String;
        //    550: ldc  #210 // 6.0f
        //    552: invokevirtual  #362 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    555: fstore  27
        //    557: fload  27
        //    559: ldc  #212 // 8.0f
        //    561: fadd
        //    562: fstore  28
        //    564: fload  22
        //    566: fload  28
        //    568: fadd
        //    569: fload  6
        //    571: ldc  #229 // 98.0f
        //    573: fadd
        //    574: fcmpl
        //    575: ifle  595 (offset +20)
        //    578: fload  6
        //    580: ldc  #211 // 7.0f
        //    582: fadd
        //    583: fstore  22
        //    585: fload  23
        //    587: fload  24
        //    589: ldc  #208 // 3.0f
        //    591: fadd
        //    592: fadd
        //    593: fstore  23
        //    595: iload  5
        //    597: ifne  652 (offset +55)
        //    600: dload_1
        //    601: dload_3
        //    602: fload  22
        //    604: f2d
        //    605: fload  23
        //    607: f2d
        //    608: fload  28
        //    610: f2d
        //    611: fload  24
        //    613: f2d
        //    614: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    617: ifeq  652 (offset +35)
        //    620: aload  26
        //    622: aload  26
        //    624: invokevirtual  #308 // dev.angelvisuals.a.I$a.aj:()Z
        //    627: ifne  638 (offset +11)
        //    630: ldc  #49 // -1256805626
        //    632: ldc  #50 // -1256805625
        //    634: ixor
        //    635: goto  643 (offset +8)
        //    638: ldc  #30 // -1629147472
        //    640: ldc  #30 // -1629147472
        //    642: ixor
        //    643: invokevirtual  #309 // dev.angelvisuals.a.I$a.r:(Z)V
        //    646: ldc  #138 // 588765256
        //    648: ldc  #139 // 588765257
        //    650: ixor
        //    651: ireturn
        //    652: fload  22
        //    654: fload  28
        //    656: ldc  #208 // 3.0f
        //    658: fadd
        //    659: fadd
        //    660: fstore  22
        //    662: goto  520 (offset -142)
        //    665: aload  19
        //    667: invokestatic  #312 // dev.angelvisuals.a.L.a:(Ldev/angelvisuals/a/I;)F
        //    670: fstore  15
        //    672: goto  807 (offset +135)
        //    675: aload  13
        //    677: instanceof  #251 // dev.angelvisuals.a.ai
        //    680: ifeq  742 (offset +62)
        //    683: aload  13
        //    685: checkcast  #251 // dev.angelvisuals.a.ai
        //    688: astore  20
        //    690: iload  5
        //    692: ifne  735 (offset +43)
        //    695: dload_1
        //    696: dload_3
        //    697: fload  6
        //    699: ldc  #211 // 7.0f
        //    701: fadd
        //    702: f2d
        //    703: fload  14
        //    705: fconst_2
        //    706: fsub
        //    707: f2d
        //    708: ldc2_w  #290 // 98.0d
        //    711: ldc2_w  #278 // 12.0d
        //    714: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    717: ifeq  735 (offset +18)
        //    720: aload_0
        //    721: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    724: aload  20
        //    726: invokevirtual  #340 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/q;)V
        //    729: ldc  #33 // -1591729428
        //    731: ldc  #34 // -1591729427
        //    733: ixor
        //    734: ireturn
        //    735: ldc  #215 // 12.0f
        //    737: fstore  15
        //    739: goto  807 (offset +68)
        //    742: aload  13
        //    744: instanceof  #256 // dev.angelvisuals.a.cA
        //    747: ifeq  807 (offset +60)
        //    750: aload  13
        //    752: checkcast  #256 // dev.angelvisuals.a.cA
        //    755: astore  21
        //    757: iload  5
        //    759: ifne  803 (offset +44)
        //    762: dload_1
        //    763: dload_3
        //    764: fload  6
        //    766: ldc  #211 // 7.0f
        //    768: fadd
        //    769: f2d
        //    770: fload  14
        //    772: ldc  #213 // 9.0f
        //    774: fadd
        //    775: f2d
        //    776: ldc2_w  #286 // 88.0d
        //    779: ldc2_w  #276 // 10.0d
        //    782: invokestatic  #363 // dev.angelvisuals.a.cs.c:(DDDDDD)Z
        //    785: ifeq  803 (offset +18)
        //    788: aload_0
        //    789: getfield  #303 // dev.angelvisuals.a.d.a:Ldev/angelvisuals/a/bN;
        //    792: aload  21
        //    794: invokevirtual  #334 // dev.angelvisuals.a.bN.a:(Ldev/angelvisuals/a/cA;)V
        //    797: ldc  #73 // -869542301
        //    799: ldc  #72 // -869542302
        //    801: ixor
        //    802: ireturn
        //    803: ldc  #220 // 22.0f
        //    805: fstore  15
        //    807: fload  9
        //    809: fload  15
        //    811: fadd
        //    812: fstore  9
        //    814: iload  12
        //    816: aload  11
        //    818: invokeinterface  #380 // java.util.List.size:()I, count 1
        //    823: ldc  #194 // 1806624350
        //    825: ldc  #195 // 1806624351
        //    827: ixor
        //    828: isub
        //    829: if_icmpge  839 (offset +10)
        //    832: fload  9
        //    834: fload  10
        //    836: fadd
        //    837: fstore  9
        //    839: iinc  12, 1
        //    842: goto  39 (offset -803)
        //    845: ldc  #87 // -525214777
        //    847: ldc  #87 // -525214777
        //    849: ixor
        //    850: ireturn
    }

  private static int method1007(int arg0, int arg1) { // было: j
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1008(int arg0, int arg1) { // было: k
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method1009(int arg0, int arg1) { // было: l
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}