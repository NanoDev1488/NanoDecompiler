// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.r
package dev.angelvisuals.a;

import dev.angelvisuals.a.ClassA2;
import dev.angelvisuals.a.ClassA56_ClassA57;
import dev.angelvisuals.a.ClassA58_ClassA59;
import dev.angelvisuals.a.ClassA60_ClassA61;
import dev.angelvisuals.a.ClassA62_ClassA63;
import dev.angelvisuals.a.aH;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.ay;
import dev.angelvisuals.a.ay_ClassA82;
import dev.angelvisuals.a.cK;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;

public class ClassA64 extends ay {

    // ---- поля ----
  private final ClassA2 field255; // было: b
  private final List field256; // было: c
  private static final String aJ = "// class hierarchy hashing: ENABLED";
  private static final String aK = "// number obfuscation: ENABLED (XOR masking)";
  private static final String aL = "// class hierarchy hashing: ENABLED";
  private static final String aM = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String aN = "// you are reading machine-generated garbage";
  private static final int ag = 1362514138;
  private static final int ah = 447205114;
  private static final int ai = 2052150629;
  private static final byte[] field257; // было: r

    static {
        field257 = ":5jhl[Yz,p:to|[I0Z0C4'?<LjL$PpV)W2/t.G$c Ht>&rg+v\\Khx\"?G7-8!N:>iR%M8e@(J?On)bX7pISp67C|~J8F*eR2'<01-ymUY,;c whcQa>=:1Mc:]1-Z-6ESU<JgPJyDjFWjLA7o&oD$_`+Q^V\"Rr@Z[y8$.DgqkN'|YD0F;$ht''4LY~+f8Ki(Z]i8tm8ZMU\"`>]F7O=j?\\5 %>d;OVZv1G|C\"^'kxmhXqDb:0x|J&Y3m3R~)tzP)Pm".getBytes("ISO-8859-1");
    }

  public ClassA64(String arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, ay_ClassA82 arg7) { // было: <init>
        super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
        field255 = new ClassA2(-1234240791845220081L ^ -1234240791845219897L, aH.field21);
        field256 = new ArrayList();
    }

  public void method521(cK arg0, boolean arg1) { // было: a
        field256.addLast(new ClassA58_ClassA59(arg0, arg1));
    }

  public void method522(String arg0, class_2561 arg1) { // было: a
        field256.addLast(new ClassA60_ClassA61(arg0, arg1));
    }

  public void method523(String arg0, boolean arg1) { // было: a
        field256.addLast(new ClassA62_ClassA63(arg0, arg1));
    }

  public void method524(String arg0, String arg1) { // было: a
        field256.addLast(new ClassA56_ClassA57(arg0, arg1));
    }

  public void method525(ap arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #57 // dev.angelvisuals.a.r.c:Ljava/util/List;
        //      4: invokeinterface  #90 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //      9: astore_2
        //     10: aload_2
        //     11: invokeinterface  #85 // java.util.Iterator.hasNext:()Z, count 1
        //     16: ifeq  125 (offset +109)
        //     19: aload_2
        //     20: invokeinterface  #86 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     25: checkcast  #36 // dev.angelvisuals.a.r$d
        //     28: astore_3
        //     29: aload_3
        //     30: getfield  #60 // dev.angelvisuals.a.r$d.O:Z
        //     33: ifne  73 (offset +40)
        //     36: invokestatic  #80 // java.lang.System.currentTimeMillis:()J
        //     39: aload_3
        //     40: getfield  #62 // dev.angelvisuals.a.r$d.o:J
        //     43: lsub
        //     44: ldc2_w  #52 // 804843788171255229L
        //     47: ldc2_w  #50 // 804843788171254381L
        //     50: lxor
        //     51: lcmp
        //     52: ifle  73 (offset +21)
        //     55: aload_3
        //     56: ldc  #5 // -397570949
        //     58: ldc  #4 // -397570950
        //     60: ixor
        //     61: putfield  #60 // dev.angelvisuals.a.r$d.O:Z
        //     64: aload_3
        //     65: getfield  #61 // dev.angelvisuals.a.r$d.j:Ldev/angelvisuals/a/k;
        //     68: fconst_0
        //     69: invokevirtual  #71 // dev.angelvisuals.a.k.a:(F)F
        //     72: pop
        //     73: aload_3
        //     74: getfield  #60 // dev.angelvisuals.a.r$d.O:Z
        //     77: ifeq  102 (offset +25)
        //     80: aload_3
        //     81: getfield  #61 // dev.angelvisuals.a.r$d.j:Ldev/angelvisuals/a/k;
        //     84: invokevirtual  #73 // dev.angelvisuals.a.k.e:()F
        //     87: ldc  #10 // 0.009999999776482582f
        //     89: fcmpg
        //     90: ifge  102 (offset +12)
        //     93: aload_2
        //     94: invokeinterface  #87 // java.util.Iterator.remove:()V, count 1
        //     99: goto  122 (offset +23)
        //    102: aload_3
        //    103: getfield  #61 // dev.angelvisuals.a.r$d.j:Ldev/angelvisuals/a/k;
        //    106: aload_3
        //    107: getfield  #60 // dev.angelvisuals.a.r$d.O:Z
        //    110: ifeq  117 (offset +7)
        //    113: fconst_0
        //    114: goto  118 (offset +4)
        //    117: fconst_1
        //    118: invokevirtual  #71 // dev.angelvisuals.a.k.a:(F)F
        //    121: pop
        //    122: goto  10 (offset -112)
        //    125: aload_0
        //    126: getfield  #56 // dev.angelvisuals.a.r.b:Ldev/angelvisuals/a/k;
        //    129: getstatic  #58 // dev.angelvisuals.a.r.mc:Lnet/minecraft/class_310;
        //    132: getfield  #63 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //    135: instanceof  #45 // net.minecraft.class_408
        //    138: ifne  153 (offset +15)
        //    141: aload_0
        //    142: getfield  #57 // dev.angelvisuals.a.r.c:Ljava/util/List;
        //    145: invokeinterface  #89 // java.util.List.isEmpty:()Z, count 1
        //    150: ifne  161 (offset +11)
        //    153: ldc  #1 // -1992423136
        //    155: ldc  #2 // -1992423135
        //    157: ixor
        //    158: goto  166 (offset +8)
        //    161: ldc  #3 // -824548628
        //    163: ldc  #3 // -824548628
        //    165: ixor
        //    166: invokevirtual  #72 // dev.angelvisuals.a.k.a:(Z)V
        //    169: invokestatic  #65 // dev.angelvisuals.AngelVisuals.getInstance:()Ldev/angelvisuals/AngelVisuals;
        //    172: invokevirtual  #66 // dev.angelvisuals.AngelVisuals.getThemeManager:()Ldev/angelvisuals/a/ch;
        //    175: invokevirtual  #68 // dev.angelvisuals.a.ch.a:()Ldev/angelvisuals/a/bl;
        //    178: astore_3
        //    179: getstatic  #55 // dev.angelvisuals.a.bc.d:Ldev/angelvisuals/a/ci;
        //    182: ldc  #12 // 6.75f
        //    184: invokevirtual  #69 // dev.angelvisuals.a.ci.a:(F)Ldev/angelvisuals/a/ar;
        //    187: astore  4
        //    189: ldc  #16 // 18.0f
        //    191: fstore  5
        //    193: getstatic  #58 // dev.angelvisuals.a.r.mc:Lnet/minecraft/class_310;
        //    196: invokevirtual  #84 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //    199: invokevirtual  #83 // net.minecraft.class_1041.method_4502:()I
        //    202: i2f
        //    203: fconst_2
        //    204: fdiv
        //    205: ldc  #15 // 16.0f
        //    207: fadd
        //    208: fstore  6
        //    210: aload_0
        //    211: getfield  #57 // dev.angelvisuals.a.r.c:Ljava/util/List;
        //    214: invokestatic  #64 // com.google.common.collect.Lists.reverse:(Ljava/util/List;)Ljava/util/List;
        //    217: invokeinterface  #90 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    222: astore  7
        //    224: aload  7
        //    226: invokeinterface  #85 // java.util.Iterator.hasNext:()Z, count 1
        //    231: ifeq  316 (offset +85)
        //    234: aload  7
        //    236: invokeinterface  #86 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    241: checkcast  #36 // dev.angelvisuals.a.r$d
        //    244: astore  8
        //    246: aload  8
        //    248: getfield  #61 // dev.angelvisuals.a.r$d.j:Ldev/angelvisuals/a/k;
        //    251: invokevirtual  #73 // dev.angelvisuals.a.k.e:()F
        //    254: fstore  9
        //    256: fload  9
        //    258: ldc  #9 // 0.0010000000474974513f
        //    260: fcmpl
        //    261: ifle  313 (offset +52)
        //    264: fload  6
        //    266: ldc  #14 // 10.5f
        //    268: fload  9
        //    270: fmul
        //    271: fadd
        //    272: fstore  6
        //    274: aload  8
        //    276: aload_1
        //    277: getstatic  #58 // dev.angelvisuals.a.r.mc:Lnet/minecraft/class_310;
        //    280: invokevirtual  #84 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //    283: invokevirtual  #82 // net.minecraft.class_1041.method_4486:()I
        //    286: i2f
        //    287: fconst_2
        //    288: fdiv
        //    289: fload  6
        //    291: ldc  #11 // 4.0f
        //    293: fsub
        //    294: aload  4
        //    296: aload_3
        //    297: fload  5
        //    299: aload_0
        //    300: invokevirtual  #77 // dev.angelvisuals.a.r$d.a:(Ldev/angelvisuals/a/ap;FFLdev/angelvisuals/a/ar;Ldev/angelvisuals/a/bl;FLdev/angelvisuals/a/r;)V
        //    303: fload  6
        //    305: ldc  #13 // 8.0f
        //    307: fload  9
        //    309: fmul
        //    310: fadd
        //    311: fstore  6
        //    313: goto  224 (offset -89)
        //    316: return
    }

  private static int method526(int arg0, int arg1) { // было: Z
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aa(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ab(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}