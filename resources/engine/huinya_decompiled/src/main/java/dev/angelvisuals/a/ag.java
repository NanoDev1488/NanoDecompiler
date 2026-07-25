// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aG
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.AngelVisuals;
import dev.angelvisuals.a.aG_ClassA53;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ay;
import dev.angelvisuals.a.ay_ClassA82;
import dev.angelvisuals.a.ay_ClassA83;
import dev.angelvisuals.a.bl;
import dev.angelvisuals.a.ch;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_10209;
import net.minecraft.class_1799;
import net.minecraft.class_1814;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_327;
import net.minecraft.class_3532;
import net.minecraft.class_3695;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_636;
import net.minecraft.class_9334;
import net.minecraft.class_9779;
import net.minecraft.class_9848;
import ru.nexusguard.protection.annotations.Native;

public class aG extends ay {

    // ---- поля ----
   List field241; // было: L
  private static final String Cr = "// every class watermarked, every string encrypted, every number xored";
  private static final String Cs = "Protected by t.me/JoinerClient";
  private static final String Ct = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String Cu = "// number obfuscation: ENABLED (XOR masking)";
  private static final String Cv = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int qM = -529654926;
  private static final int qN = 23768087;
  private static final int qO = -1385490554;
  private static final byte[] er;

    static {
        er = "G<rXQ{s\"/!]@*,T3R[ hwfuM6(g5rKH}E5vnXz):%-_hQ9&uv7M0zhpcg)AeM+EZi\"YCi\\?d<7Fg!PJ>mqB!k>O6dEv9Sr`%cTg9p9~w*%:OpDODPVfRR%2ecf8g+%?7Z!:)'I6),zWOUOC;Xp@;\"n,zUeotW;3Y,IPS fjXj,HS@{P.RwO.m5B#Bf^V~mXEv6T@/7BllkBVeDS6|[W{2f?]@0q1a 0yxv\\m_vsB#{^1DxCT]I=\\l`k4G g/gu$`".getBytes("ISO-8859-1");
    }

  public aG(String arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, ay_ClassA82 arg7) { // было: <init>
        super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        field241 = new ArrayList();
        float var9 = 24.0f;
        aM = var9 * 9.0f;
        aN = var9;
        int var10 = -720910084 ^ -720910084;
        while (var10 < (1309216541 ^ 1309216532)) {
            field241.add(new aG_ClassA53(this, this, var10));
            ++var10;
            continue;
        }
    }

    @Native
  public void method506(ap arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokevirtual  #180 // dev.angelvisuals.a.ap.method_51421:()I
        //      5: i2f
        //      6: aload_0
        //      7: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //     10: fsub
        //     11: fconst_2
        //     12: fdiv
        //     13: putfield  #142 // dev.angelvisuals.a.aG.aK:F
        //     16: aload_0
        //     17: invokevirtual  #169 // dev.angelvisuals.a.aG.M:()F
        //     20: fstore_2
        //     21: aload_0
        //     22: invokevirtual  #170 // dev.angelvisuals.a.aG.N:()F
        //     25: fstore_3
        //     26: invokestatic  #164 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //     29: invokevirtual  #165 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //     32: invokevirtual  #194 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //     35: astore  4
        //     37: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //     40: getfield  #153 // net.minecraft.class_310.field_1761:Lnet/minecraft/class_636;
        //     43: invokevirtual  #219 // net.minecraft.class_636.method_2914:()Z
        //     46: ifeq  656 (offset +610)
        //     49: aload_0
        //     50: aload_1
        //     51: fload_3
        //     52: ldc  #82 // 35.0f
        //     54: fsub
        //     55: invokevirtual  #172 // dev.angelvisuals.a.aG.b:(Ldev/angelvisuals/a/ap;F)V
        //     58: aload_0
        //     59: aload_1
        //     60: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //     63: invokevirtual  #209 // net.minecraft.class_310.method_61966:()Lnet/minecraft/class_9779;
        //     66: fload_3
        //     67: ldc  #82 // 35.0f
        //     69: fsub
        //     70: ldc  #74 // 9.0f
        //     72: fsub
        //     73: invokevirtual  #171 // dev.angelvisuals.a.aG.a:(Ldev/angelvisuals/a/ap;Lnet/minecraft/class_9779;F)V
        //     76: getstatic  #148 // dev.angelvisuals.a.bc.c:Ldev/angelvisuals/a/ci;
        //     79: ldc  #73 // 7.0f
        //     81: invokevirtual  #195 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //     84: astore  5
        //     86: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //     89: getfield  #152 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     92: getfield  #160 // net.minecraft.class_746.field_7520:I
        //     95: istore  6
        //     97: aload_1
        //     98: aload  5
        //    100: iload  6
        //    102: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //    105: fload_2
        //    106: aload_0
        //    107: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    110: fconst_2
        //    111: fdiv
        //    112: fadd
        //    113: aload  5
        //    115: iload  6
        //    117: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //    120: invokevirtual  #186 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    123: fconst_2
        //    124: fdiv
        //    125: fsub
        //    126: fload_3
        //    127: ldc  #78 // 15.0f
        //    129: fsub
        //    130: aload  5
        //    132: invokevirtual  #187 // dev.angelvisuals.a.ar.z:()F
        //    135: fconst_2
        //    136: fdiv
        //    137: fadd
        //    138: getstatic  #150 // dev.angelvisuals.a.bp.e:Ldev/angelvisuals/a/bp;
        //    141: invokevirtual  #178 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    144: aload_1
        //    145: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    148: aload_0
        //    149: getfield  #142 // dev.angelvisuals.a.aG.aK:F
        //    152: aload_0
        //    153: getfield  #143 // dev.angelvisuals.a.aG.aL:F
        //    156: aload_0
        //    157: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    160: aload_0
        //    161: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    164: ldc  #80 // 21.0f
        //    166: ldc  #72 // 4.0f
        //    168: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    171: getstatic  #149 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    174: invokestatic  #167 // dev.angelvisuals.a.aE.e:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    177: aload_1
        //    178: fload_2
        //    179: fload_3
        //    180: aload_0
        //    181: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    184: ldc  #81 // 24.0f
        //    186: ldc  #72 // 4.0f
        //    188: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    191: aload  4
        //    193: invokevirtual  #190 // dev.angelvisuals.a.bl.d:()Ldev/angelvisuals/a/bp;
        //    196: invokevirtual  #177 // dev.angelvisuals.a.ap.a:(FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    199: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    202: getfield  #152 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    205: invokevirtual  #220 // net.minecraft.class_746.method_6079:()Lnet/minecraft/class_1799;
        //    208: astore  7
        //    210: aload  7
        //    212: invokevirtual  #205 // net.minecraft.class_1799.method_7960:()Z
        //    215: ifne  529 (offset +314)
        //    218: fload_2
        //    219: aload_0
        //    220: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    223: fsub
        //    224: ldc  #77 // 12.0f
        //    226: fsub
        //    227: fstore  8
        //    229: aload_1
        //    230: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    233: fload  8
        //    235: fload_3
        //    236: aload_0
        //    237: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    240: aload_0
        //    241: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    244: ldc  #80 // 21.0f
        //    246: ldc  #72 // 4.0f
        //    248: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    251: getstatic  #149 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    254: invokestatic  #167 // dev.angelvisuals.a.aE.e:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    257: aload_1
        //    258: fload  8
        //    260: fload_3
        //    261: aload_0
        //    262: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    265: aload_0
        //    266: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    269: ldc  #72 // 4.0f
        //    271: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    274: aload  4
        //    276: invokevirtual  #190 // dev.angelvisuals.a.bl.d:()Ldev/angelvisuals/a/bp;
        //    279: invokevirtual  #177 // dev.angelvisuals.a.ap.a:(FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    282: aload_1
        //    283: fload  8
        //    285: fload_3
        //    286: aload_0
        //    287: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    290: aload_0
        //    291: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    294: ldc  #67 // 0.10000000149011612f
        //    296: ldc  #72 // 4.0f
        //    298: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    301: aload  4
        //    303: invokevirtual  #191 // dev.angelvisuals.a.bl.g:()Ldev/angelvisuals/a/bp;
        //    306: invokevirtual  #179 // dev.angelvisuals.a.ap.b:(FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    309: aload_1
        //    310: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    313: fload_2
        //    314: aload_0
        //    315: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    318: fsub
        //    319: ldc  #77 // 12.0f
        //    321: fsub
        //    322: fload_3
        //    323: aload_0
        //    324: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    327: aload_0
        //    328: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    331: ldc  #67 // 0.10000000149011612f
        //    333: ldc  #78 // 15.0f
        //    335: aload  4
        //    337: invokevirtual  #189 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //    340: ldc  #72 // 4.0f
        //    342: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    345: invokestatic  #166 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFFFLdev/angelvisuals/a/bp;Ldev/angelvisuals/a/aY;)V
        //    348: aload_1
        //    349: invokevirtual  #185 // dev.angelvisuals.a.ap.z:()V
        //    352: aload_1
        //    353: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    356: fload  8
        //    358: f2d
        //    359: ldc2_w  #139 // 5.6d
        //    362: dadd
        //    363: fload_3
        //    364: f2d
        //    365: ldc2_w  #139 // 5.6d
        //    368: dadd
        //    369: dconst_1
        //    370: invokevirtual  #213 // net.minecraft.class_4587.method_22904:(DDD)V
        //    373: aload_1
        //    374: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    377: ldc  #70 // 0.800000011920929f
        //    379: ldc  #70 // 0.800000011920929f
        //    381: ldc  #70 // 0.800000011920929f
        //    383: invokevirtual  #214 // net.minecraft.class_4587.method_22905:(FFF)V
        //    386: aload_1
        //    387: aload  7
        //    389: ldc  #7 // -1656574641
        //    391: ldc  #7 // -1656574641
        //    393: ixor
        //    394: ldc  #20 // -951696810
        //    396: ldc  #20 // -951696810
        //    398: ixor
        //    399: invokevirtual  #181 // dev.angelvisuals.a.ap.method_51427:(Lnet/minecraft/class_1799;II)V
        //    402: aload_1
        //    403: checkcast  #112 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor
        //    406: aload  7
        //    408: ldc  #45 // 521331644
        //    410: ldc  #45 // 521331644
        //    412: ixor
        //    413: ldc  #42 // 362975320
        //    415: ldc  #42 // 362975320
        //    417: ixor
        //    418: invokeinterface  #223 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor.callDrawItemBar:(Lnet/minecraft/class_1799;II)V, count 4
        //    423: aload_1
        //    424: checkcast  #112 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor
        //    427: aload  7
        //    429: ldc  #37 // 247777906
        //    431: ldc  #37 // 247777906
        //    433: ixor
        //    434: ldc  #16 // -1197337055
        //    436: ldc  #16 // -1197337055
        //    438: ixor
        //    439: invokeinterface  #222 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor.callDrawCooldownProgress:(Lnet/minecraft/class_1799;II)V, count 4
        //    444: aload_1
        //    445: invokevirtual  #176 // dev.angelvisuals.a.ap.A:()V
        //    448: aload  7
        //    450: invokevirtual  #204 // net.minecraft.class_1799.method_7947:()I
        //    453: ldc  #10 // -1549747334
        //    455: ldc  #11 // -1549747333
        //    457: ixor
        //    458: if_icmple  529 (offset +71)
        //    461: aload  7
        //    463: invokevirtual  #204 // net.minecraft.class_1799.method_7947:()I
        //    466: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //    469: invokedynamic  #234 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    474: astore  9
        //    476: aload  5
        //    478: aload  9
        //    480: invokevirtual  #186 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    483: fstore  10
        //    485: fload  8
        //    487: ldc  #81 // 24.0f
        //    489: fadd
        //    490: fload  10
        //    492: fsub
        //    493: fconst_1
        //    494: fsub
        //    495: fstore  11
        //    497: fload_3
        //    498: ldc  #81 // 24.0f
        //    500: fadd
        //    501: aload  5
        //    503: invokevirtual  #187 // dev.angelvisuals.a.ar.z:()F
        //    506: fsub
        //    507: ldc  #71 // 3.0f
        //    509: fsub
        //    510: fstore  12
        //    512: aload_1
        //    513: aload  5
        //    515: aload  9
        //    517: fload  11
        //    519: fload  12
        //    521: aload  4
        //    523: invokevirtual  #192 // dev.angelvisuals.a.bl.h:()Ldev/angelvisuals/a/bp;
        //    526: invokevirtual  #178 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    529: fload_2
        //    530: fstore  8
        //    532: aload_0
        //    533: getfield  #141 // dev.angelvisuals.a.aG.L:Ljava/util/List;
        //    536: invokeinterface  #228 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    541: astore  9
        //    543: aload  9
        //    545: invokeinterface  #225 // java.util.Iterator.hasNext:()Z, count 1
        //    550: ifeq  588 (offset +38)
        //    553: aload  9
        //    555: invokeinterface  #226 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    560: checkcast  #100 // dev.angelvisuals.a.aG$a
        //    563: astore  10
        //    565: aload  10
        //    567: aload_1
        //    568: fload  8
        //    570: fload_3
        //    571: aload  4
        //    573: invokevirtual  #174 // dev.angelvisuals.a.aG$a.a:(Ldev/angelvisuals/a/ap;FFLdev/angelvisuals/a/bl;)V
        //    576: fload  8
        //    578: aload_0
        //    579: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    582: fadd
        //    583: fstore  8
        //    585: goto  543 (offset -42)
        //    588: aload_1
        //    589: aload_0
        //    590: getfield  #142 // dev.angelvisuals.a.aG.aK:F
        //    593: aload_0
        //    594: getfield  #143 // dev.angelvisuals.a.aG.aL:F
        //    597: aload_0
        //    598: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    601: ldc  #81 // 24.0f
        //    603: ldc  #67 // 0.10000000149011612f
        //    605: ldc  #72 // 4.0f
        //    607: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    610: aload  4
        //    612: invokevirtual  #191 // dev.angelvisuals.a.bl.g:()Ldev/angelvisuals/a/bp;
        //    615: invokevirtual  #179 // dev.angelvisuals.a.ap.b:(FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    618: aload_1
        //    619: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    622: aload_0
        //    623: getfield  #142 // dev.angelvisuals.a.aG.aK:F
        //    626: aload_0
        //    627: getfield  #143 // dev.angelvisuals.a.aG.aL:F
        //    630: aload_0
        //    631: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    634: ldc  #81 // 24.0f
        //    636: ldc  #67 // 0.10000000149011612f
        //    638: ldc  #78 // 15.0f
        //    640: aload  4
        //    642: invokevirtual  #189 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //    645: ldc  #72 // 4.0f
        //    647: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    650: invokestatic  #166 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFFFLdev/angelvisuals/a/bp;Ldev/angelvisuals/a/aY;)V
        //    653: goto  1340 (offset +687)
        //    656: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    659: getfield  #153 // net.minecraft.class_310.field_1761:Lnet/minecraft/class_636;
        //    662: invokevirtual  #218 // net.minecraft.class_636.method_2908:()Z
        //    665: ifeq  1340 (offset +675)
        //    668: aload_1
        //    669: invokevirtual  #185 // dev.angelvisuals.a.ap.z:()V
        //    672: aload_1
        //    673: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    676: aload_1
        //    677: invokevirtual  #180 // dev.angelvisuals.a.ap.method_51421:()I
        //    680: ldc  #22 // -824691245
        //    682: ldc  #21 // -824691247
        //    684: ixor
        //    685: idiv
        //    686: ldc  #24 // -759595443
        //    688: ldc  #23 // -759595498
        //    690: ixor
        //    691: isub
        //    692: ineg
        //    693: i2f
        //    694: aload_1
        //    695: invokevirtual  #182 // dev.angelvisuals.a.ap.method_51443:()I
        //    698: ldc  #49 // 858395275
        //    700: ldc  #50 // 858395308
        //    702: ixor
        //    703: isub
        //    704: ineg
        //    705: i2f
        //    706: fconst_0
        //    707: invokevirtual  #216 // net.minecraft.class_4587.method_46416:(FFF)V
        //    710: aload_1
        //    711: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    714: fconst_1
        //    715: fconst_1
        //    716: fconst_1
        //    717: invokevirtual  #214 // net.minecraft.class_4587.method_22905:(FFF)V
        //    720: aload_1
        //    721: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    724: fload_2
        //    725: fconst_0
        //    726: fconst_0
        //    727: invokevirtual  #216 // net.minecraft.class_4587.method_46416:(FFF)V
        //    730: aload_1
        //    731: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    734: fconst_0
        //    735: fload_3
        //    736: ldc  #78 // 15.0f
        //    738: fsub
        //    739: fconst_0
        //    740: invokevirtual  #216 // net.minecraft.class_4587.method_46416:(FFF)V
        //    743: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    746: getfield  #153 // net.minecraft.class_310.field_1761:Lnet/minecraft/class_636;
        //    749: invokevirtual  #219 // net.minecraft.class_636.method_2914:()Z
        //    752: ifne  770 (offset +18)
        //    755: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    758: getfield  #151 // net.minecraft.class_310.field_1705:Lnet/minecraft/class_329;
        //    761: checkcast  #113 // dev.angelvisuals.utility.mixin.accessors.InGameHudAccessor
        //    764: aload_1
        //    765: invokeinterface  #224 // dev.angelvisuals.utility.mixin.accessors.InGameHudAccessor.invokeRenderStatusBars:(Lnet/minecraft/class_332;)V, count 2
        //    770: aload_1
        //    771: invokevirtual  #176 // dev.angelvisuals.a.ap.A:()V
        //    774: aload_0
        //    775: aload_1
        //    776: fload_3
        //    777: ldc  #82 // 35.0f
        //    779: fsub
        //    780: invokevirtual  #172 // dev.angelvisuals.a.aG.b:(Ldev/angelvisuals/a/ap;F)V
        //    783: aload_0
        //    784: aload_1
        //    785: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    788: invokevirtual  #209 // net.minecraft.class_310.method_61966:()Lnet/minecraft/class_9779;
        //    791: fload_3
        //    792: ldc  #82 // 35.0f
        //    794: fsub
        //    795: ldc  #74 // 9.0f
        //    797: fsub
        //    798: invokevirtual  #171 // dev.angelvisuals.a.aG.a:(Ldev/angelvisuals/a/ap;Lnet/minecraft/class_9779;F)V
        //    801: getstatic  #148 // dev.angelvisuals.a.bc.c:Ldev/angelvisuals/a/ci;
        //    804: ldc  #73 // 7.0f
        //    806: invokevirtual  #195 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    809: astore  5
        //    811: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    814: getfield  #152 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    817: getfield  #160 // net.minecraft.class_746.field_7520:I
        //    820: istore  6
        //    822: aload_1
        //    823: aload  5
        //    825: iload  6
        //    827: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //    830: fload_2
        //    831: aload_0
        //    832: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    835: fconst_2
        //    836: fdiv
        //    837: fadd
        //    838: aload  5
        //    840: iload  6
        //    842: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //    845: invokevirtual  #186 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //    848: fconst_2
        //    849: fdiv
        //    850: fsub
        //    851: fload_3
        //    852: ldc  #78 // 15.0f
        //    854: fsub
        //    855: aload  5
        //    857: invokevirtual  #187 // dev.angelvisuals.a.ar.z:()F
        //    860: fconst_2
        //    861: fdiv
        //    862: fadd
        //    863: getstatic  #150 // dev.angelvisuals.a.bp.e:Ldev/angelvisuals/a/bp;
        //    866: invokevirtual  #178 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //    869: aload_1
        //    870: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    873: aload_0
        //    874: getfield  #142 // dev.angelvisuals.a.aG.aK:F
        //    877: aload_0
        //    878: getfield  #143 // dev.angelvisuals.a.aG.aL:F
        //    881: aload_0
        //    882: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    885: aload_0
        //    886: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    889: ldc  #80 // 21.0f
        //    891: ldc  #72 // 4.0f
        //    893: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    896: getstatic  #149 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //    899: invokestatic  #167 // dev.angelvisuals.a.aE.e:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    902: aload_1
        //    903: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    906: fload_2
        //    907: fload_3
        //    908: aload_0
        //    909: getfield  #144 // dev.angelvisuals.a.aG.aM:F
        //    912: ldc  #81 // 24.0f
        //    914: ldc  #76 // 11.0f
        //    916: ldc  #72 // 4.0f
        //    918: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //    921: new  #109 // dev.angelvisuals.a.bp
        //    924: dup
        //    925: ldc  #5 // -1688064073
        //    927: ldc  #6 // -1688064025
        //    929: ixor
        //    930: ldc  #52 // 1041763012
        //    932: ldc  #51 // 1041762964
        //    934: ixor
        //    935: ldc  #9 // -1587359624
        //    937: ldc  #8 // -1587359704
        //    939: ixor
        //    940: ldc  #43 // 497749788
        //    942: ldc  #44 // 497749987
        //    944: ixor
        //    945: invokespecial  #193 // dev.angelvisuals.a.bp.<init>:(IIII)V
        //    948: invokestatic  #168 // dev.angelvisuals.a.aE.f:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //    951: getstatic  #147 // dev.angelvisuals.a.aG.mc:Lnet/minecraft/class_310;
        //    954: getfield  #152 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //    957: invokevirtual  #220 // net.minecraft.class_746.method_6079:()Lnet/minecraft/class_1799;
        //    960: astore  7
        //    962: aload  7
        //    964: invokevirtual  #205 // net.minecraft.class_1799.method_7960:()Z
        //    967: ifne  1281 (offset +314)
        //    970: fload_2
        //    971: aload_0
        //    972: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    975: fsub
        //    976: ldc  #77 // 12.0f
        //    978: fsub
        //    979: fstore  8
        //    981: aload_1
        //    982: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //    985: fload  8
        //    987: fload_3
        //    988: aload_0
        //    989: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    992: aload_0
        //    993: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //    996: ldc  #80 // 21.0f
        //    998: ldc  #72 // 4.0f
        //   1000: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //   1003: getstatic  #149 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //   1006: invokestatic  #167 // dev.angelvisuals.a.aE.e:(Lnet/minecraft/class_4587;FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //   1009: aload_1
        //   1010: fload  8
        //   1012: fload_3
        //   1013: aload_0
        //   1014: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1017: aload_0
        //   1018: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1021: ldc  #72 // 4.0f
        //   1023: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //   1026: aload  4
        //   1028: invokevirtual  #190 // dev.angelvisuals.a.bl.d:()Ldev/angelvisuals/a/bp;
        //   1031: invokevirtual  #177 // dev.angelvisuals.a.ap.a:(FFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //   1034: aload_1
        //   1035: fload  8
        //   1037: fload_3
        //   1038: aload_0
        //   1039: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1042: aload_0
        //   1043: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1046: ldc  #67 // 0.10000000149011612f
        //   1048: ldc  #72 // 4.0f
        //   1050: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //   1053: aload  4
        //   1055: invokevirtual  #191 // dev.angelvisuals.a.bl.g:()Ldev/angelvisuals/a/bp;
        //   1058: invokevirtual  #179 // dev.angelvisuals.a.ap.b:(FFFFFLdev/angelvisuals/a/aY;Ldev/angelvisuals/a/bp;)V
        //   1061: aload_1
        //   1062: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //   1065: fload_2
        //   1066: aload_0
        //   1067: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1070: fsub
        //   1071: ldc  #77 // 12.0f
        //   1073: fsub
        //   1074: fload_3
        //   1075: aload_0
        //   1076: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1079: aload_0
        //   1080: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1083: ldc  #67 // 0.10000000149011612f
        //   1085: ldc  #78 // 15.0f
        //   1087: aload  4
        //   1089: invokevirtual  #189 // dev.angelvisuals.a.bl.b:()Ldev/angelvisuals/a/bp;
        //   1092: ldc  #72 // 4.0f
        //   1094: invokestatic  #175 // dev.angelvisuals.a.aY.a:(F)Ldev/angelvisuals/a/aY;
        //   1097: invokestatic  #166 // dev.angelvisuals.a.aE.a:(Lnet/minecraft/class_4587;FFFFFFLdev/angelvisuals/a/bp;Ldev/angelvisuals/a/aY;)V
        //   1100: aload_1
        //   1101: invokevirtual  #185 // dev.angelvisuals.a.ap.z:()V
        //   1104: aload_1
        //   1105: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //   1108: fload  8
        //   1110: f2d
        //   1111: ldc2_w  #139 // 5.6d
        //   1114: dadd
        //   1115: fload_3
        //   1116: f2d
        //   1117: ldc2_w  #139 // 5.6d
        //   1120: dadd
        //   1121: dconst_1
        //   1122: invokevirtual  #213 // net.minecraft.class_4587.method_22904:(DDD)V
        //   1125: aload_1
        //   1126: invokevirtual  #183 // dev.angelvisuals.a.ap.method_51448:()Lnet/minecraft/class_4587;
        //   1129: ldc  #70 // 0.800000011920929f
        //   1131: ldc  #70 // 0.800000011920929f
        //   1133: ldc  #70 // 0.800000011920929f
        //   1135: invokevirtual  #214 // net.minecraft.class_4587.method_22905:(FFF)V
        //   1138: aload_1
        //   1139: aload  7
        //   1141: ldc  #2 // -1861138618
        //   1143: ldc  #2 // -1861138618
        //   1145: ixor
        //   1146: ldc  #47 // 708661768
        //   1148: ldc  #47 // 708661768
        //   1150: ixor
        //   1151: invokevirtual  #181 // dev.angelvisuals.a.ap.method_51427:(Lnet/minecraft/class_1799;II)V
        //   1154: aload_1
        //   1155: checkcast  #112 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor
        //   1158: aload  7
        //   1160: ldc  #17 // -1168273527
        //   1162: ldc  #17 // -1168273527
        //   1164: ixor
        //   1165: ldc  #56 // 1244217430
        //   1167: ldc  #56 // 1244217430
        //   1169: ixor
        //   1170: invokeinterface  #223 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor.callDrawItemBar:(Lnet/minecraft/class_1799;II)V, count 4
        //   1175: aload_1
        //   1176: checkcast  #112 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor
        //   1179: aload  7
        //   1181: ldc  #38 // 255924155
        //   1183: ldc  #38 // 255924155
        //   1185: ixor
        //   1186: ldc  #48 // 744092125
        //   1188: ldc  #48 // 744092125
        //   1190: ixor
        //   1191: invokeinterface  #222 // dev.angelvisuals.utility.mixin.accessors.DrawContextAccessor.callDrawCooldownProgress:(Lnet/minecraft/class_1799;II)V, count 4
        //   1196: aload_1
        //   1197: invokevirtual  #176 // dev.angelvisuals.a.ap.A:()V
        //   1200: aload  7
        //   1202: invokevirtual  #204 // net.minecraft.class_1799.method_7947:()I
        //   1205: ldc  #3 // -1752914196
        //   1207: ldc  #4 // -1752914195
        //   1209: ixor
        //   1210: if_icmple  1281 (offset +71)
        //   1213: aload  7
        //   1215: invokevirtual  #204 // net.minecraft.class_1799.method_7947:()I
        //   1218: invokestatic  #198 // java.lang.String.valueOf:(I)Ljava/lang/String;
        //   1221: invokedynamic  #234 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //   1226: astore  9
        //   1228: aload  5
        //   1230: aload  9
        //   1232: invokevirtual  #186 // dev.angelvisuals.a.ar.a:(Ljava/lang/String;)F
        //   1235: fstore  10
        //   1237: fload  8
        //   1239: ldc  #81 // 24.0f
        //   1241: fadd
        //   1242: fload  10
        //   1244: fsub
        //   1245: fconst_1
        //   1246: fsub
        //   1247: fstore  11
        //   1249: fload_3
        //   1250: ldc  #81 // 24.0f
        //   1252: fadd
        //   1253: aload  5
        //   1255: invokevirtual  #187 // dev.angelvisuals.a.ar.z:()F
        //   1258: fsub
        //   1259: ldc  #71 // 3.0f
        //   1261: fsub
        //   1262: fstore  12
        //   1264: aload_1
        //   1265: aload  5
        //   1267: aload  9
        //   1269: fload  11
        //   1271: fload  12
        //   1273: aload  4
        //   1275: invokevirtual  #192 // dev.angelvisuals.a.bl.h:()Ldev/angelvisuals/a/bp;
        //   1278: invokevirtual  #178 // dev.angelvisuals.a.ap.a:(Ldev/angelvisuals/a/ar;Ljava/lang/String;FFLdev/angelvisuals/a/bp;)V
        //   1281: fload_2
        //   1282: fstore  8
        //   1284: aload_0
        //   1285: getfield  #141 // dev.angelvisuals.a.aG.L:Ljava/util/List;
        //   1288: invokeinterface  #228 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //   1293: astore  9
        //   1295: aload  9
        //   1297: invokeinterface  #225 // java.util.Iterator.hasNext:()Z, count 1
        //   1302: ifeq  1340 (offset +38)
        //   1305: aload  9
        //   1307: invokeinterface  #226 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //   1312: checkcast  #100 // dev.angelvisuals.a.aG$a
        //   1315: astore  10
        //   1317: aload  10
        //   1319: aload_1
        //   1320: fload  8
        //   1322: fload_3
        //   1323: aload  4
        //   1325: invokevirtual  #174 // dev.angelvisuals.a.aG$a.a:(Ldev/angelvisuals/a/ap;FFLdev/angelvisuals/a/bl;)V
        //   1328: fload  8
        //   1330: aload_0
        //   1331: getfield  #145 // dev.angelvisuals.a.aG.aN:F
        //   1334: fadd
        //   1335: fstore  8
        //   1337: goto  1295 (offset -42)
        //   1340: return
    }

  private void method507(ap arg0, float arg1) { // было: b
        class_10209.method_64146().method_15396(Decryptor.method1945(XorDecoder.method1946("r¹\u0012\u0003»J\u000e¾0ø\\â4Ní6¡~ÿMºu Oü`Û4ùGÞK|ï?oå\u000bô", 274507110 ^ -652202416)));
        if (mc.field_1705.field_2040 > 0) {
            if (!mc.field_1705.field_2031.method_7960()) {
                class_5250 var3 = mc.field_1705.field_2031.method_7964().method_27661().method_27692(mc.field_1705.field_2031.method_7932().method_58413());
                if (mc.field_1705.field_2031.method_57826(class_9334.field_49631)) {
                    var3.method_10866().method_10978(Boolean.valueOf(107774150 ^ 107774151));
                }
                int var4 = mc.field_1772.method_27525(var3);
                int var5 = (arg0.method_51421() - var4) / (-598210753 ^ -598210755);
                int var6 = ((int) arg1);
                if (!mc.field_1761.method_2908()) {
                    var6 = var6 + 14;
                } else {
                    if (mc.field_1761.method_2914()) {
                        var6 = var6 + 14;
                    }
                }
                int var7 = ((int) (((float) mc.field_1705.field_2040) * 256.0f / 10.0f));
                if (var7 > (-1111859975 ^ -1111860218)) {
                    var7 = 1528624855 ^ 1528624680;
                }
                if (var7 > 0) {
                    arg0.method_51448().method_22903();
                    arg0.method_51448().method_46416(((float) var5), ((float) var6), 0.0f);
                    bl var8 = AngelVisuals.getInstance().getThemeManager().method481();
                    arg0.method_60649(mc.field_1772, var3, 1232632082 ^ 1232632082, -1895621822 ^ -1895621822, var4, class_9848.method_61330(var7, 555492127 ^ -555492128));
                    arg0.method_51448().method_22909();
                }
            }
        }
        class_10209.method_64146().method_15407();
    }

  public final void method508(ap arg0, class_9779 arg1, float arg2) { // было: a
        class_327 var4 = mc.field_1772;
        if (mc.field_1705.field_2018 != null) {
            if (mc.field_1705.field_2041 > 0) {
                class_10209.method_64146().method_15396(Decryptor.method1945(XorDecoder.method1946("3Kº��JÓ^\u0007°©97°<9¯í*@ß", -1200866808 ^ 1511084502)));
                float var5 = ((float) mc.field_1705.field_2041) - arg1.method_60637(-1494813589 ^ -1494813589);
                int var6 = ((int) (var5 * 255.0f / 20.0f));
                if (var6 > (1326164568 ^ 1326164647)) {
                    var6 = 1044758385 ^ 1044758414;
                }
                if (var6 > (311642560 ^ 311642568)) {
                    arg0.method_51448().method_22903();
                    arg0.method_51448().method_46416(((float) (arg0.method_51421() / (-582769137 ^ -582769139))), arg2, 0.0f);
                    int var7;
                    if (!mc.field_1705.field_2038) {
                        var7 = class_9848.method_61330(var6, 1265699646 ^ -1265699647);
                    } else {
                        var7 = class_3532.method_60599(var5 / 50.0f, 0.699999988079071f, 0.6000000238418579f, var6);
                    }
                    int var8 = var4.method_27525(mc.field_1705.field_2018);
                    arg0.method_51448().method_46416(((float) -var8) / 2.0f, -4.0f, 0.0f);
                    arg0.method_60649(var4, mc.field_1705.field_2018, -633972730 ^ -633972730, 1411817206 ^ 1411817206, var8, var7);
                    arg0.method_51448().method_22909();
                }
                class_10209.method_64146().method_15407();
            }
        }
    }

  protected void method509(ap arg0, ay_ClassA83 arg1) { // было: b
        // (пустое тело)
    }

  private static int oX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int oZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}