// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.bt
package dev.angelvisuals.a;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.angelvisuals.a.ap;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bc;
import dev.angelvisuals.a.bp;
import dev.angelvisuals.a.cF;
import dev.angelvisuals.a.cK;
import dev.angelvisuals.a.ci;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_465;
import net.minecraft.class_9288;

@bI(name = "ShulkerPreview", a = "RENDER", I = "Показывает содержимое шалкера при наведении + CTRL")
public final class bt extends cK implements cF {

    // ---- поля ----
  public static final bt field564; // было: a
  private static final int hV = 18;
  private static final int hW = 7;
  private static final int hX = 3;
  private static final int hY = 9;
  private static final int hZ = 14;
  private static final int ia = -7631989;
  private Field field565; // было: a
  private Field field566; // было: b
  private static final String nt = "Protected by t.me/JoinerClient";
  private static final String nu = "// === DO NOT TOUCH ===";
  private static final String nv = "// number obfuscation: ENABLED (XOR masking)";
  private static final String nw = "Protected by t.me/JoinerClient";
  private static final String nx = "// === DO NOT TOUCH ===";
  private static final int ib = 84422300;
  private static final int ic = 1549200599;
  private static final int ie = 151607714;
  private static final byte[] bJ;

    static {
        bJ = "|bV66.#/<5E!XR*;,_#s0fY\\cx!dCV6hQw;j3^=\\yQ2,oy L{4H5Tj\\@ @9aG9n,6(_&Y6{87l bvam?Zl:Vh)w=f)J%C4Sc6~21Ew7Wxb<,;\\)8Gk_{Wd]7*z.ivd|'en/ t:EF8EU YJ#w|YV;N\\sQ|t;i!Ntb]?uG`#ucLk|m$j6'fs@O-&\\@&\\}yZ^0`^lM5qNV(ei%[`B=$OAU7?0y'}Fe'/XC7]y]?-e0Hzmo]]N]+UI$==>YJYT] u*7P".getBytes("ISO-8859-1");
        field564 = new bt();
    }

  private bt() { // было: <init>
        super();
        ag();
    }

  private void ag() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc_w  #281 // net.minecraft.class_465
        //      3: invokevirtual  #351 // java.lang.Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //      6: astore_1
        //      7: aload_1
        //      8: arraylength
        //      9: istore_2
        //     10: ldc  #111 // 369303568
        //     12: ldc  #111 // 369303568
        //     14: ixor
        //     15: istore_3
        //     16: iload_3
        //     17: iload_2
        //     18: if_icmpge  243 (offset +225)
        //     21: aload_1
        //     22: iload_3
        //     23: aaload
        //     24: astore  4
        //     26: aload  4
        //     28: invokevirtual  #360 // java.lang.reflect.Field.getType:()Ljava/lang/Class;
        //     31: getstatic  #301 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //     34: if_acmpne  237 (offset +203)
        //     37: aload  4
        //     39: ldc  #126 // 583290247
        //     41: ldc  #125 // 583290246
        //     43: ixor
        //     44: invokevirtual  #361 // java.lang.reflect.Field.setAccessible:(Z)V
        //     47: aload  4
        //     49: invokevirtual  #359 // java.lang.reflect.Field.getName:()Ljava/lang/String;
        //     52: astore  5
        //     54: aload  5
        //     56: ldc  #238 // '@ùÉyS¨ÔlY¢§nhð¢Xrðë\x7f,¾¬\x0b'
        //     58: ldc  #170 // 1348254127
        //     60: ldc  #196 // 1724739767
        //     62: ixor
        //     63: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     66: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     69: invokevirtual  #355 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     72: ifne  138 (offset +66)
        //     75: aload  5
        //     77: ldc  #233 // '\x023GÅ@DmÔXin\x9dX;\x7føZ\x7fjÃ\\Z;\x8f'
        //     79: ldc  #35 // -1314791560
        //     81: ldc  #95 // 61330511
        //     83: ixor
        //     84: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     87: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     90: invokevirtual  #355 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     93: ifne  138 (offset +45)
        //     96: aload  5
        //     98: ldc  #242 // '®ô>G\x89ô\x08aªÌ]\x7fèáE\t¨Æ\x04~µâS\x1b'
        //    100: ldc  #13 // -1852255848
        //    102: ldc  #45 // -1208585633
        //    104: ixor
        //    105: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    108: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    111: invokevirtual  #354 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //    114: ifne  138 (offset +24)
        //    117: aload  5
        //    119: ldc  #235 // '\x17Ç°\x8f7ß\x97»\x1eÏ©\x8e!³§\x95\x14¿Ô¯LÇØÊ'
        //    121: ldc  #86 // -7471989
        //    123: ldc  #97 // 141064958
        //    125: ixor
        //    126: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    129: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    132: invokevirtual  #354 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //    135: ifeq  147 (offset +12)
        //    138: aload_0
        //    139: aload  4
        //    141: putfield  #297 // dev.angelvisuals.a.bt.a:Ljava/lang/reflect/Field;
        //    144: goto  237 (offset +93)
        //    147: aload  5
        //    149: ldc  #234 // "\x10Î\x81n'\x8a\x93S\x11\x81¦asó½e+Î\x8a\x0b'Þø\x1d"
        //    151: ldc  #12 // -1866388210
        //    153: ldc  #34 // -1341877173
        //    155: ixor
        //    156: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    159: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    162: invokevirtual  #355 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    165: ifne  231 (offset +66)
        //    168: aload  5
        //    170: ldc  #243 // 'Î\x9b¿çÂ¼ÄùÜ¾½ùð¦æèË¿êãÏª°\x90'
        //    172: ldc  #69 // -450248959
        //    174: ldc  #162 // 1218736775
        //    176: ixor
        //    177: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    180: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    183: invokevirtual  #355 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    186: ifne  231 (offset +45)
        //    189: aload  5
        //    191: ldc  #244 // 'Ýâ\x8a8ñûÌ[Çòª=¤ô\x9cAþöºa¢àÂ1'
        //    193: ldc  #56 // -752853966
        //    195: ldc  #65 // -538968671
        //    197: ixor
        //    198: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    201: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    204: invokevirtual  #354 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //    207: ifne  231 (offset +24)
        //    210: aload  5
        //    212: ldc  #245 // 'æñ!8\x86Ç\x0f=\x85Ï\x1d7ü÷ \\ÀÃbK\x81ñk3'
        //    214: ldc  #187 // 1592401732
        //    216: ldc  #171 // 1354533361
        //    218: ixor
        //    219: invokestatic  #328 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    222: invokestatic  #327 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    225: invokevirtual  #354 // java.lang.String.contains:(Ljava/lang/CharSequence;)Z
        //    228: ifeq  237 (offset +9)
        //    231: aload_0
        //    232: aload  4
        //    234: putfield  #298 // dev.angelvisuals.a.bt.b:Ljava/lang/reflect/Field;
        //    237: iinc  3, 1
        //    240: goto  16 (offset -224)
        //    243: goto  247 (offset +4)
        //    246: astore_1
        //    247: return
        //       Exception table:
        //         from 0 to 243 target 246 type java.lang.Exception
    }

  private int method945(class_465 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #297 // dev.angelvisuals.a.bt.a:Ljava/lang/reflect/Field;
        //      4: ifnull  16 (offset +12)
        //      7: aload_0
        //      8: getfield  #297 // dev.angelvisuals.a.bt.a:Ljava/lang/reflect/Field;
        //     11: aload_1
        //     12: invokevirtual  #358 // java.lang.reflect.Field.getInt:(Ljava/lang/Object;)I
        //     15: ireturn
        //     16: goto  20 (offset +4)
        //     19: astore_2
        //     20: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     23: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     26: invokevirtual  #365 // net.minecraft.class_1041.method_4486:()I
        //     29: ldc  #197 // 1726850858
        //     31: ldc  #198 // 1726850970
        //     33: ixor
        //     34: isub
        //     35: ldc  #80 // -343105953
        //     37: ldc  #79 // -343105955
        //     39: ixor
        //     40: idiv
        //     41: ireturn
        //       Exception table:
        //         from 0 to 15 target 19 type java.lang.Exception
    }

  private int method946(class_465 arg0) { // было: b
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #298 // dev.angelvisuals.a.bt.b:Ljava/lang/reflect/Field;
        //      4: ifnull  16 (offset +12)
        //      7: aload_0
        //      8: getfield  #298 // dev.angelvisuals.a.bt.b:Ljava/lang/reflect/Field;
        //     11: aload_1
        //     12: invokevirtual  #358 // java.lang.reflect.Field.getInt:(Ljava/lang/Object;)I
        //     15: ireturn
        //     16: goto  20 (offset +4)
        //     19: astore_2
        //     20: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     23: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     26: invokevirtual  #367 // net.minecraft.class_1041.method_4502:()I
        //     29: ldc  #24 // -1618020371
        //     31: ldc  #23 // -1618020533
        //     33: ixor
        //     34: isub
        //     35: ldc  #115 // 412996915
        //     37: ldc  #114 // 412996913
        //     39: ixor
        //     40: idiv
        //     41: ireturn
        //       Exception table:
        //         from 0 to 15 target 19 type java.lang.Exception
    }

  public void method947(class_332 arg0, int arg1, int arg2) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #343 // dev.angelvisuals.a.bt.ah:()Z
        //      4: ifne  8 (offset +4)
        //      7: return
        //      8: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     11: ifnull  32 (offset +21)
        //     14: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     17: getfield  #322 // net.minecraft.class_310.field_1724:Lnet/minecraft/class_746;
        //     20: ifnull  32 (offset +12)
        //     23: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     26: getfield  #324 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //     29: ifnonnull  33 (offset +4)
        //     32: return
        //     33: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     36: getfield  #324 // net.minecraft.class_310.field_1755:Lnet/minecraft/class_437;
        //     39: astore  5
        //     41: aload  5
        //     43: instanceof  #281 // net.minecraft.class_465
        //     46: ifeq  59 (offset +13)
        //     49: aload  5
        //     51: checkcast  #281 // net.minecraft.class_465
        //     54: astore  4
        //     56: goto  60 (offset +4)
        //     59: return
        //     60: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     63: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     66: invokevirtual  #366 // net.minecraft.class_1041.method_4490:()J
        //     69: lstore  5
        //     71: lload  5
        //     73: ldc  #17 // -1758091654
        //     75: ldc  #18 // -1758091473
        //     77: ixor
        //     78: invokestatic  #389 // org.lwjgl.glfw.GLFW.glfwGetKey:(JI)I
        //     81: ldc  #57 // -737005200
        //     83: ldc  #58 // -737005199
        //     85: ixor
        //     86: if_icmpne  97 (offset +11)
        //     89: ldc  #200 // 1841950936
        //     91: ldc  #201 // 1841950937
        //     93: ixor
        //     94: goto  102 (offset +8)
        //     97: ldc  #84 // -167358321
        //     99: ldc  #84 // -167358321
        //    101: ixor
        //    102: istore  7
        //    104: iload  7
        //    106: ifne  110 (offset +4)
        //    109: return
        //    110: aload_0
        //    111: aload  4
        //    113: invokevirtual  #341 // dev.angelvisuals.a.bt.a:(Lnet/minecraft/class_465;)Lnet/minecraft/class_1735;
        //    116: astore  8
        //    118: aload  8
        //    120: ifnonnull  124 (offset +4)
        //    123: return
        //    124: aload  8
        //    126: invokevirtual  #369 // net.minecraft.class_1735.method_7677:()Lnet/minecraft/class_1799;
        //    129: astore  9
        //    131: aload_0
        //    132: aload  9
        //    134: invokevirtual  #344 // dev.angelvisuals.a.bt.b:(Lnet/minecraft/class_1799;)Z
        //    137: ifne  141 (offset +4)
        //    140: return
        //    141: aload  9
        //    143: getstatic  #326 // net.minecraft.class_9334.field_49622:Lnet/minecraft/class_9331;
        //    146: invokevirtual  #370 // net.minecraft.class_1799.method_57824:(Lnet/minecraft/class_9331;)Ljava/lang/Object;
        //    149: checkcast  #282 // net.minecraft.class_9288
        //    152: astore  10
        //    154: aload  10
        //    156: ifnonnull  160 (offset +4)
        //    159: return
        //    160: aload_0
        //    161: aload_1
        //    162: aload  9
        //    164: aload  10
        //    166: iload_2
        //    167: i2f
        //    168: iload_3
        //    169: i2f
        //    170: invokevirtual  #339 // dev.angelvisuals.a.bt.a:(Lnet/minecraft/class_332;Lnet/minecraft/class_1799;Lnet/minecraft/class_9288;FF)V
        //    173: return
    }

  private class_1735 method948(class_465 arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #387 // net.minecraft.class_465.method_17577:()Lnet/minecraft/class_1703;
        //      4: astore_2
        //      5: aload_2
        //      6: ifnull  16 (offset +10)
        //      9: aload_2
        //     10: getfield  #302 // net.minecraft.class_1703.field_7761:Lnet/minecraft/class_2371;
        //     13: ifnonnull  18 (offset +5)
        //     16: aconst_null
        //     17: areturn
        //     18: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     21: getfield  #323 // net.minecraft.class_310.field_1729:Lnet/minecraft/class_312;
        //     24: invokevirtual  #376 // net.minecraft.class_312.method_1603:()D
        //     27: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     30: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     33: invokevirtual  #365 // net.minecraft.class_1041.method_4486:()I
        //     36: i2d
        //     37: dmul
        //     38: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     41: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     44: invokevirtual  #364 // net.minecraft.class_1041.method_4480:()I
        //     47: i2d
        //     48: ddiv
        //     49: dstore_3
        //     50: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     53: getfield  #323 // net.minecraft.class_310.field_1729:Lnet/minecraft/class_312;
        //     56: invokevirtual  #377 // net.minecraft.class_312.method_1604:()D
        //     59: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     62: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     65: invokevirtual  #367 // net.minecraft.class_1041.method_4502:()I
        //     68: i2d
        //     69: dmul
        //     70: getstatic  #300 // dev.angelvisuals.a.bt.mc:Lnet/minecraft/class_310;
        //     73: invokevirtual  #375 // net.minecraft.class_310.method_22683:()Lnet/minecraft/class_1041;
        //     76: invokevirtual  #368 // net.minecraft.class_1041.method_4507:()I
        //     79: i2d
        //     80: ddiv
        //     81: dstore  5
        //     83: aload_0
        //     84: aload_1
        //     85: invokevirtual  #340 // dev.angelvisuals.a.bt.a:(Lnet/minecraft/class_465;)I
        //     88: istore  7
        //     90: aload_0
        //     91: aload_1
        //     92: invokevirtual  #345 // dev.angelvisuals.a.bt.b:(Lnet/minecraft/class_465;)I
        //     95: istore  8
        //     97: aload_2
        //     98: getfield  #302 // net.minecraft.class_1703.field_7761:Lnet/minecraft/class_2371;
        //    101: invokevirtual  #374 // net.minecraft.class_2371.iterator:()Ljava/util/Iterator;
        //    104: astore  9
        //    106: aload  9
        //    108: invokeinterface  #390 // java.util.Iterator.hasNext:()Z, count 1
        //    113: ifeq  200 (offset +87)
        //    116: aload  9
        //    118: invokeinterface  #391 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    123: checkcast  #272 // net.minecraft.class_1735
        //    126: astore  10
        //    128: iload  7
        //    130: aload  10
        //    132: getfield  #304 // net.minecraft.class_1735.field_7873:I
        //    135: iadd
        //    136: istore  11
        //    138: iload  8
        //    140: aload  10
        //    142: getfield  #303 // net.minecraft.class_1735.field_7872:I
        //    145: iadd
        //    146: istore  12
        //    148: dload_3
        //    149: iload  11
        //    151: i2d
        //    152: dcmpl
        //    153: iflt  197 (offset +44)
        //    156: dload_3
        //    157: iload  11
        //    159: ldc  #32 // -1347509912
        //    161: ldc  #33 // -1347509896
        //    163: ixor
        //    164: iadd
        //    165: i2d
        //    166: dcmpg
        //    167: ifge  197 (offset +30)
        //    170: dload  5
        //    172: iload  12
        //    174: i2d
        //    175: dcmpl
        //    176: iflt  197 (offset +21)
        //    179: dload  5
        //    181: iload  12
        //    183: ldc  #185 // 1590945829
        //    185: ldc  #186 // 1590945845
        //    187: ixor
        //    188: iadd
        //    189: i2d
        //    190: dcmpg
        //    191: ifge  197 (offset +6)
        //    194: aload  10
        //    196: areturn
        //    197: goto  106 (offset -91)
        //    200: goto  204 (offset +4)
        //    203: astore_2
        //    204: aconst_null
        //    205: areturn
        //       Exception table:
        //         from 0 to 17 target 203 type java.lang.Exception
        //         from 18 to 196 target 203 type java.lang.Exception
        //         from 197 to 200 target 203 type java.lang.Exception
    }

  private boolean method949(class_1799 arg0) { // было: b
        if (arg0 == null) {
            return -1474910187 ^ -1474910187;
        } else {
            if (!arg0.method_7960()) {
                return arg0.method_7909() == class_1802.field_8545 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8722 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8380 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8050 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8829 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8271 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8548 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8520 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8627 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8451 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8213 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8816 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8350 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8584 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8461 ? -1242739244 ^ -1242739243 : arg0.method_7909() == class_1802.field_8676 ? -1242739244 ^ -1242739243 : arg0.method_7909() != class_1802.field_8268 ? 1961282220 ^ 1961282220 : -1242739244 ^ -1242739243;
            } else {
                return -1474910187 ^ -1474910187;
            }
        }
    }

  private int method950(class_1799 arg0) { // было: a
        if (arg0.method_7909() != class_1802.field_8545) {
            if (arg0.method_7909() != class_1802.field_8722) {
                if (arg0.method_7909() != class_1802.field_8380) {
                    if (arg0.method_7909() != class_1802.field_8050) {
                        if (arg0.method_7909() != class_1802.field_8829) {
                            if (arg0.method_7909() != class_1802.field_8271) {
                                if (arg0.method_7909() != class_1802.field_8548) {
                                    if (arg0.method_7909() != class_1802.field_8520) {
                                        if (arg0.method_7909() != class_1802.field_8627) {
                                            if (arg0.method_7909() != class_1802.field_8451) {
                                                if (arg0.method_7909() != class_1802.field_8213) {
                                                    if (arg0.method_7909() != class_1802.field_8816) {
                                                        if (arg0.method_7909() != class_1802.field_8350) {
                                                            if (arg0.method_7909() != class_1802.field_8584) {
                                                                if (arg0.method_7909() != class_1802.field_8461) {
                                                                    if (arg0.method_7909() != class_1802.field_8676) {
                                                                        if (arg0.method_7909() != class_1802.field_8268) {
                                                                            return 285856634 ^ -292045113;
                                                                        } else {
                                                                            return 1892856641 ^ -1882214816;
                                                                        }
                                                                    } else {
                                                                        return 1220732831 ^ -1217214023;
                                                                    }
                                                                } else {
                                                                    return 1876645091 ^ -1870321419;
                                                                }
                                                            } else {
                                                                return 1283069927 ^ -1275509803;
                                                            }
                                                        } else {
                                                            return -1890484459 ^ 1886206911;
                                                        }
                                                    } else {
                                                        return 2047456156 ^ -2055175900;
                                                    }
                                                } else {
                                                    return 968137697 ^ -962459779;
                                                }
                                            } else {
                                                return -421518509 ^ 427671236;
                                            }
                                        } else {
                                            return 1565057887 ^ -1576031219;
                                        }
                                    } else {
                                        return -1028161231 ^ 1027869339;
                                    }
                                } else {
                                    return 1306082449 ^ -1302725746;
                                }
                            } else {
                                return -1625271380 ^ 1625197457;
                            }
                        } else {
                            return -1627660966 ^ 1640066688;
                        }
                    } else {
                        return 1795499093 ^ -1799197976;
                    }
                } else {
                    return 1489149157 ^ -1489298184;
                }
            } else {
                return 1091037287 ^ -1091037288;
            }
        } else {
            return -1910382661 ^ 1908388358;
        }
    }

  private void method951(class_332 arg0, class_1799 arg1, class_9288 arg2, float arg3, float arg4) { // было: a
        int __stk1;
        class_4587 var6 = arg0.method_51448();
        int var7 = arg0.method_51421();
        int var8 = arg0.method_51443();
        float var9 = 162.0f;
        float var10 = 54.0f;
        float var11 = var9 + 14.0f;
        float var12 = var10 + 14.0f + 14.0f;
        float var13 = arg3 + 12.0f;
        float var14 = arg4 - 12.0f;
        if (var13 + var11 > ((float) var7)) {
            var13 = arg3 - var11 - 4.0f;
        }
        if (var14 + var12 > ((float) var8)) {
            var14 = ((float) var8) - var12 - 4.0f;
        }
        if (var14 < 4.0f) {
            var14 = 4.0f;
        }
        if (var13 < 4.0f) {
            var13 = 4.0f;
        }
        int var15 = method950(arg1);
        int var16 = -2143606969 ^ -2143606881;
        int var17 = var16 << (1715323538 ^ 1715323530) | var15 & (1102573881 ^ 1095241414);
        int var18 = method952(var15, 0.6000000238418579f);
        int var19 = method953(var15, 1.2999999523162842f);
        var6.method_22903();
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        var6.method_46416(0.0f, 0.0f, 500.0f);
        arg0.method_25294(((int) var13), ((int) var14), ((int) (var13 + var11)), ((int) (var14 + var12)), var17);
        arg0.method_25294(((int) var13), ((int) var14), ((int) (var13 + var11)), ((int) (var14 + 2.0f)), var19);
        arg0.method_25294(((int) var13), ((int) (var14 + var12 - 2.0f)), ((int) (var13 + var11)), ((int) (var14 + var12)), var18);
        arg0.method_25294(((int) var13), ((int) var14), ((int) (var13 + 2.0f)), ((int) (var14 + var12)), var19);
        arg0.method_25294(((int) (var13 + var11 - 2.0f)), ((int) var14), ((int) (var13 + var11)), ((int) (var14 + var12)), var18);
        String var20 = arg1.method_7964().getString();
        int var21 = ((int) (var13 + 7.0f));
        int var22 = ((int) (var14 + 7.0f - 1.0f));
        __stk1 = !method954(var15) ? 417809676 ^ -402825450 : -716288703 ^ 716288702;
        int var23 = __stk1;
        ap.method1637(arg0).method1638(bc.field171.method383(12.0f), var20, ((float) var21), ((float) var22), new bp(((Integer) var23)));
        float var24 = var13 + 7.0f;
        float var25 = var14 + 7.0f + 14.0f - 2.0f;
        int var26 = method952(var15, 0.5f);
        arg0.method_25294(((int) (var24 - 1.0f)), ((int) (var25 - 1.0f)), ((int) (var24 + var9 + 1.0f)), ((int) (var25 + var10 + 1.0f)), var26);
        ArrayList var27 = new ArrayList();
        Objects.requireNonNull(var27);
        arg2.method_57489().forEach(lp0 -> var27.add(lp0));
        int var28 = -458737115 ^ -458737115;
        while (var28 < (990670193 ^ 990670186)) {
            int var29 = var28 / (865658885 ^ 865658892);
            int var30 = var28 % (367314588 ^ 367314581);
            int var31 = ((int) (var24 + ((float) (var30 * (-176034034 ^ -176034020)))));
            int var32 = ((int) (var25 + ((float) (var29 * (1683362089 ^ 1683362107)))));
            arg0.method_25294(var31, var32, var31 + (-1216237960 ^ -1216237974) - (1869889520 ^ 1869889522), var32 + (694576630 ^ 694576612) - (-1280220750 ^ -1280220752), 567079950 ^ -565739643);
            arg0.method_25294(var31, var32, var31 + (-1761320487 ^ -1761320501) - (624736514 ^ 624736512), var32 + (563731994 ^ 563731995), 1882165481 ^ -1887779396);
            arg0.method_25294(var31, var32, var31 + (-723177217 ^ -723177218), var32 + (1092732751 ^ 1092732765) - (873313863 ^ 873313861), -2065129859 ^ 2076035880);
            arg0.method_25294(var31, var32 + (1650294092 ^ 1650294110) - (1918657795 ^ 1918657792), var31 + (241817636 ^ 241817654) - (693310686 ^ 693310684), var32 + (-1024010832 ^ -1024010846) - (181498332 ^ 181498334), -345540436 ^ 345540435);
            arg0.method_25294(var31 + (-383799681 ^ -383799699) - (1006651043 ^ 1006651040), var32, var31 + (1869085464 ^ 1869085450) - (1319751852 ^ 1319751854), var32 + (1283991974 ^ 1283991988) - (1377917374 ^ 1377917372), 886322666 ^ -886322667);
            if (var28 < var27.size()) {
                class_1799 var33 = ((class_1799) var27.get(var28));
                if (!var33.method_7960()) {
                    arg0.method_51427(var33, var31, var32);
                    arg0.method_51431(mc.field_1772, var33, var31, var32);
                }
            }
            ++var28;
            continue;
        }
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        var6.method_22909();
    }

  private int method952(int arg0, float arg1) { // было: c
        int var3 = arg0 >> (-1550946175 ^ -1550946151) & (230118957 ^ 230119122);
        int var4 = ((int) (((float) (arg0 >> (1417283210 ^ 1417283226) & (-385939890 ^ -385939791))) * arg1));
        int var5 = ((int) (((float) (arg0 >> (1545796980 ^ 1545796988) & (927594635 ^ 927594612))) * arg1));
        int var6 = ((int) (((float) (arg0 & (-1419539227 ^ -1419539430))) * arg1));
        return var3 << (-1752052081 ^ -1752052073) | Math.min(-465409257 ^ -465409048, var4) << (374011975 ^ 374011991) | Math.min(1504797818 ^ 1504797829, var5) << (1163978658 ^ 1163978666) | Math.min(2071215892 ^ 2071216107, var6);
    }

  private int method953(int arg0, float arg1) { // было: d
        int var3 = arg0 >> (1176494102 ^ 1176494094) & (-779040260 ^ -779040509);
        int var4 = ((int) Math.min(255.0f, ((float) (arg0 >> (988475468 ^ 988475484) & (456916826 ^ 456916901))) * arg1));
        int var5 = ((int) Math.min(255.0f, ((float) (arg0 >> (431856138 ^ 431856130) & (-1927776081 ^ -1927776176))) * arg1));
        int var6 = ((int) Math.min(255.0f, ((float) (arg0 & (39695023 ^ 39694928))) * arg1));
        return var3 << (-1152075005 ^ -1152074981) | var4 << (880393898 ^ 880393914) | var5 << (1568781479 ^ 1568781487) | var6;
    }

  private boolean method954(int arg0) { // было: c
        int var2 = arg0 >> (200506193 ^ 200506177) & (-713245260 ^ -713245365);
        int var3 = arg0 >> (1062007381 ^ 1062007389) & (-434197023 ^ -434197218);
        int var4 = arg0 & (2136121566 ^ 2136121377);
        double var5 = (0.299 * ((double) var2) + 0.587 * ((double) var3) + 0.114 * ((double) var4)) / 255.0;
        return var5 >= 0.5 ? 27203188 ^ 27203188 : 2015541886 ^ 2015541887;
    }

  private static int hb(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hc(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hd(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}