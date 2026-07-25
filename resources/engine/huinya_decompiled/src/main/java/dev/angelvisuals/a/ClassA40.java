// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.Y
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ci;
import dev.angelvisuals.a.df;
import dev.angelvisuals.a.dp;
import lombok.Generated;
import net.minecraft.class_10149;
import net.minecraft.class_10156;
import net.minecraft.class_2561;
import net.minecraft.class_290;
import org.joml.Matrix4f;

public final class ClassA40 {

    // ---- поля ----
  public static final class_10156 field192; // было: a
  private static final String en = "// number obfuscation: ENABLED (XOR masking)";
  private static final String eo = "// nice try. closed source for a reason.";
  private static final String ep = "// Joiner sees you";
  private static final String eq = "// good luck with the next 9999 classes";
  private static final String er = "// number obfuscation: ENABLED (XOR masking)";
  private static final int cu = 762861514;
  private static final int cv = -1150147839;
  private static final int cw = 740131211;
  private static final byte[] field193; // было: Z

    static {
        field193 = "Kra$Y~?!ILZA;7e[JoQ`x__6b7{*+f?3Q7`7]#H/\"+<'&Ph=DOy\"HMN*<R@3gMUH#/Sg|3Wf1rrO,s/\\]=KK|ca`|_(s6c&k>X14GbqHTp1tc;F\"#tp]Cj.%l!#\"S`wh9!27{XE_)]O:lV>ez8d_B@z<oz[[}:v$.zBgI.T^lrE>jK9Jhxy#a\"m?BX20B&.mXaeF~u>gC&|(\\g4oKoj~U.*A4\\koA3Q[PvbjPm#vr(Ca8V4-FV8z9EAX;DJ0Ew.:".getBytes("ISO-8859-1");
        field192 = new class_10156(df.method402(Decryptor.method1945(XorDecoder.method1946("°²'È··&ØÈE·°&£â) ½\"", 882424691 ^ 723016330))), class_290.field_1575, class_10149.field_53930);
    }

  public static void method391(ci arg0, String arg1, float arg2, int arg3, Matrix4f arg4, float arg5, float arg6, float arg7) { // было: a
        method392(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1406522142 ^ -1406522142, 0.0f, 1.0f, 0.0f);
    }

  public static void method392(ci arg0, String arg1, float arg2, int arg3, Matrix4f arg4, float arg5, float arg6, float arg7, boolean arg8, float arg9, float arg10, float arg11) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #116 // 0.05000000074505806f
        //      2: fstore  12
        //      4: ldc  #118 // 0.5f
        //      6: fstore  13
        //      8: fconst_0
        //      9: fstore  14
        //     11: invokestatic  #203 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     14: invokestatic  #200 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //     17: invokestatic  #202 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //     20: ldc  #95 // 1568282540
        //     22: ldc  #95 // 1568282540
        //     24: ixor
        //     25: aload_0
        //     26: invokevirtual  #220 // dev.angelvisuals.a.ci.ao:()I
        //     29: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //     32: getstatic  #193 // dev.angelvisuals.a.Y.a:Lnet/minecraft/class_10156;
        //     35: invokestatic  #205 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     38: astore  15
        //     40: aload  15
        //     42: ldc  #137 // 'C\x81\x89\x9fd\x9fÏ\xadmÜ£ùf\x8b\x9c¯#Ñ\x8b¨S\x8eÇö'
        //     44: ldc  #115 // 2119674260
        //     46: ldc  #24 // -1246936960
        //     48: ixor
        //     49: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     52: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     55: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //     58: aload_0
        //     59: invokevirtual  #221 // dev.angelvisuals.a.ci.b:()Ldev/angelvisuals/a/an$e;
        //     62: invokevirtual  #214 // dev.angelvisuals.a.an$e.bi:()F
        //     65: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //     68: aload  15
        //     70: ldc  #140 // 'Iïñ)*¢ô\x178íâ\nbîå\x0euÍï\x1f"Ø\x9ef'
        //     72: ldc  #42 // -610130910
        //     74: ldc  #1 // -2147373775
        //     76: ixor
        //     77: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     80: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     83: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //     86: fload  12
        //     88: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //     91: aload  15
        //     93: ldc  #141 // 'J\x1d´®<+\x96\x8ds[¤Ö1%©¸hX¬Ä99úÒ'
        //     95: ldc  #15 // -1559857243
        //     97: ldc  #92 // 1287724972
        //     99: ixor
        //    100: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    103: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    106: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    109: ldc  #119 // 0.5699999928474426f
        //    111: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    114: aload  15
        //    116: ldc  #134 // '6r3V,w,]\x0fT/]\x10ewu"\x011n+a{%'
        //    118: ldc  #89 // 1186185150
        //    120: ldc  #99 // 1593151453
        //    122: ixor
        //    123: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    126: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    129: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    132: iload  8
        //    134: ifeq  145 (offset +11)
        //    137: ldc  #3 // -2081941278
        //    139: ldc  #4 // -2081941277
        //    141: ixor
        //    142: goto  150 (offset +8)
        //    145: ldc  #90 // 1224641262
        //    147: ldc  #90 // 1224641262
        //    149: ixor
        //    150: invokevirtual  #229 // net.minecraft.class_284.method_35649:(I)V
        //    153: aload  15
        //    155: ldc  #158 // 'Õ\\).\x97d<\x1d«`84\x8cZ\x1e{\x8dGb\x04\x88Lfp'
        //    157: ldc  #7 // -1949446601
        //    159: ldc  #36 // -963189291
        //    161: ixor
        //    162: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    165: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    168: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    171: fload  9
        //    173: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    176: aload  15
        //    178: ldc  #164 // 'ú\x15j\x1fù6D*ù\x1f��3í\x1e\x06+Û2@+Û\x1a\x0eA'
        //    180: ldc  #8 // -1939655834
        //    182: ldc  #49 // -263165742
        //    184: ixor
        //    185: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    188: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    191: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    194: fload  10
        //    196: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    199: aload  15
        //    201: ldc  #150 // '\x8f\x8eaõð\x9eBéåöiÉÕ¬RÂÓ²cë\x8e°\x15\x8c'
        //    203: ldc  #63 // 398920163
        //    205: ldc  #17 // -1494233508
        //    207: ixor
        //    208: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    211: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    214: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    217: fload  11
        //    219: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    222: aload  15
        //    224: ldc  #126 // '\x17-Þ \n\x1eú>(\x0cÂ\x1c\x145¸\x1a\x1b\x06ß8\x076±v'
        //    226: ldc  #25 // -1203848316
        //    228: ldc  #50 // -206381831
        //    230: ixor
        //    231: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    234: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    237: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    240: fload  5
        //    242: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    245: invokestatic  #232 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //    248: getstatic  #197 // net.minecraft.class_293$class_5596.field_27382:Lnet/minecraft/class_293$class_5596;
        //    251: getstatic  #196 // net.minecraft.class_290.field_1575:Lnet/minecraft/class_293;
        //    254: invokevirtual  #233 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    257: astore  16
        //    259: aload_0
        //    260: aload  4
        //    262: aload  16
        //    264: aload_1
        //    265: fload_2
        //    266: fload  12
        //    268: ldc  #118 // 0.5f
        //    270: fmul
        //    271: fload_2
        //    272: fmul
        //    273: fload  14
        //    275: fload  5
        //    277: ldc  #121 // 0.75f
        //    279: fsub
        //    280: fload  6
        //    282: fload_2
        //    283: ldc  #120 // 0.699999988079071f
        //    285: fmul
        //    286: fadd
        //    287: fload  7
        //    289: iload_3
        //    290: invokevirtual  #218 // dev.angelvisuals.a.ci.a:(Lorg/joml/Matrix4f;Lnet/minecraft/class_4588;Ljava/lang/String;FFFFFFI)V
        //    293: aload  16
        //    295: invokevirtual  #231 // net.minecraft.class_287.method_60794:()Lnet/minecraft/class_9801;
        //    298: astore  17
        //    300: aload  17
        //    302: ifnull  310 (offset +8)
        //    305: aload  17
        //    307: invokestatic  #230 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    310: ldc  #45 // -399674617
        //    312: ldc  #45 // -399674617
        //    314: ixor
        //    315: ldc  #27 // -1180182135
        //    317: ldc  #27 // -1180182135
        //    319: ixor
        //    320: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //    323: invokestatic  #204 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    326: invokestatic  #201 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    329: return
    }

  public static void method393(ci arg0, String arg1, float arg2, int arg3, Matrix4f arg4, float arg5, float arg6, float arg7, boolean arg8, float arg9, float arg10) { // было: a
        float var11 = arg0.method381(arg1, arg2) * 2.0f;
        method392(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, var11);
    }

  public static void method394(ci arg0, class_2561 arg1, float arg2, Matrix4f arg3, float arg4, float arg5, float arg6) { // было: a
        method396(arg0, arg1, arg2, arg3, arg4, arg5, arg6, -2004518640 ^ -2004518640, 0.0f, 1.0f, 0.0f);
    }

  public static void method395(ci arg0, class_2561 arg1, float arg2, Matrix4f arg3, float arg4, float arg5, float arg6, int arg7) { // было: a
        method397(arg0, arg1, arg2, arg3, arg4, arg5, arg6, 624532239 ^ 624532239, 0.0f, 1.0f, 0.0f, arg7);
    }

  public static void method396(ci arg0, class_2561 arg1, float arg2, Matrix4f arg3, float arg4, float arg5, float arg6, boolean arg7, float arg8, float arg9, float arg10) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #116 // 0.05000000074505806f
        //      2: fstore  11
        //      4: ldc  #118 // 0.5f
        //      6: fstore  12
        //      8: fconst_0
        //      9: fstore  13
        //     11: aload_1
        //     12: getstatic  #194 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //     15: invokevirtual  #215 // dev.angelvisuals.a.bp.E:()I
        //     18: invokestatic  #211 // dev.angelvisuals.a.aC.a:(Lnet/minecraft/class_2561;I)Ljava/util/List;
        //     21: astore  14
        //     23: fload  4
        //     25: fstore  15
        //     27: invokestatic  #203 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     30: invokestatic  #200 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //     33: invokestatic  #202 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //     36: ldc  #38 // -874210627
        //     38: ldc  #38 // -874210627
        //     40: ixor
        //     41: aload_0
        //     42: invokevirtual  #220 // dev.angelvisuals.a.ci.ao:()I
        //     45: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //     48: getstatic  #193 // dev.angelvisuals.a.Y.a:Lnet/minecraft/class_10156;
        //     51: invokestatic  #205 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     54: astore  16
        //     56: aload  16
        //     58: ldc  #148 // 'tW\x86OSIÀ}Z\n¬)Q]\x93\x7f\x14\x07\x84xdXÈ&'
        //     60: ldc  #57 // 167467962
        //     62: ldc  #60 // 302933145
        //     64: ixor
        //     65: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     68: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     71: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //     74: aload_0
        //     75: invokevirtual  #221 // dev.angelvisuals.a.ci.b:()Ldev/angelvisuals/a/an$e;
        //     78: invokevirtual  #214 // dev.angelvisuals.a.an$e.bi:()F
        //     81: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //     84: aload  16
        //     86: ldc  #147 // 'rõ5\x01\x11¸0?\x03÷&"Yô!&N×+7\x19ÂZN'
        //     88: ldc  #64 // 432453982
        //     90: ldc  #108 // 1788947062
        //     92: ixor
        //     93: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     96: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     99: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    102: fload  11
        //    104: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    107: aload  16
        //    109: ldc  #143 // 'K~l+=HN\x08r8|S0Fq=i;tA8Z"W'
        //    111: ldc  #74 // 709540840
        //    113: ldc  #86 // 1079357664
        //    115: ixor
        //    116: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    119: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    122: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    125: ldc  #119 // 0.5699999928474426f
        //    127: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    130: aload  16
        //    132: ldc  #157 // 'Ïp\x14\x8dÕu\x0b\x86öV\x08\x86égP®Û\x03\x16µÒc\\þ'
        //    134: ldc  #20 // -1409513853
        //    136: ldc  #105 // 1755165721
        //    138: ixor
        //    139: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    142: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    145: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    148: iload  7
        //    150: ifeq  161 (offset +11)
        //    153: ldc  #112 // 2044268914
        //    155: ldc  #113 // 2044268915
        //    157: ixor
        //    158: goto  166 (offset +8)
        //    161: ldc  #14 // -1636407473
        //    163: ldc  #14 // -1636407473
        //    165: ixor
        //    166: invokevirtual  #229 // net.minecraft.class_284.method_35649:(I)V
        //    169: aload  16
        //    171: ldc  #154 // '¢\x8a_}à²JNÜ¶Ngû\x8ch(ú\x91\x14Wÿ\x9a\x10#'
        //    173: ldc  #31 // -1024008346
        //    175: ldc  #43 // -589618445
        //    177: ixor
        //    178: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    181: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    184: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    187: fload  8
        //    189: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    192: aload  16
        //    194: ldc  #123 // "\x06\x1eý\x9c\x05=Ó©\x05\x14\x97°\x11\x15\x91¨'9×¨'\x11\x99Â"
        //    196: ldc  #103 // 1701934780
        //    198: ldc  #12 // -1697306892
        //    200: ixor
        //    201: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    204: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    207: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    210: fload  9
        //    212: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    215: aload  16
        //    217: ldc  #161 // 'íö\x11i\x92æ2u\x87\x8e\x19U·Ô"^±Ê\x13wìÈe\x10'
        //    219: ldc  #51 // -118961609
        //    221: ldc  #39 // -709855766
        //    223: ixor
        //    224: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    227: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    230: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    233: fload  10
        //    235: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    238: aload  16
        //    240: ldc  #138 // 'H±gTU\x82CJw\x90{hK©\x01nD\x9afLXª\x08\x02'
        //    242: ldc  #56 // 163854321
        //    244: ldc  #83 // 921816275
        //    246: ixor
        //    247: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    250: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    253: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    256: fload  4
        //    258: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    261: invokestatic  #232 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //    264: getstatic  #197 // net.minecraft.class_293$class_5596.field_27382:Lnet/minecraft/class_293$class_5596;
        //    267: getstatic  #196 // net.minecraft.class_290.field_1575:Lnet/minecraft/class_293;
        //    270: invokevirtual  #233 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    273: astore  17
        //    275: aload  14
        //    277: invokeinterface  #237 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    282: astore  19
        //    284: aload  19
        //    286: invokeinterface  #235 // java.util.Iterator.hasNext:()Z, count 1
        //    291: ifeq  368 (offset +77)
        //    294: aload  19
        //    296: invokeinterface  #236 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    301: checkcast  #171 // dev.angelvisuals.a.aC$a
        //    304: astore  18
        //    306: aload_0
        //    307: aload_3
        //    308: aload  17
        //    310: aload  18
        //    312: invokevirtual  #212 // dev.angelvisuals.a.aC$a.af:()Ljava/lang/String;
        //    315: fload_2
        //    316: fload  11
        //    318: ldc  #118 // 0.5f
        //    320: fmul
        //    321: fload_2
        //    322: fmul
        //    323: fload  13
        //    325: ldc  #117 // 0.30000001192092896f
        //    327: fsub
        //    328: fload  15
        //    330: ldc  #121 // 0.75f
        //    332: fsub
        //    333: fload  5
        //    335: fload_2
        //    336: ldc  #120 // 0.699999988079071f
        //    338: fmul
        //    339: fadd
        //    340: fload  6
        //    342: aload  18
        //    344: invokevirtual  #213 // dev.angelvisuals.a.aC$a.i:()I
        //    347: invokevirtual  #218 // dev.angelvisuals.a.ci.a:(Lorg/joml/Matrix4f;Lnet/minecraft/class_4588;Ljava/lang/String;FFFFFFI)V
        //    350: fload  15
        //    352: aload_0
        //    353: aload  18
        //    355: invokevirtual  #212 // dev.angelvisuals.a.aC$a.af:()Ljava/lang/String;
        //    358: fload_2
        //    359: invokevirtual  #216 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    362: fadd
        //    363: fstore  15
        //    365: goto  284 (offset -81)
        //    368: aload  17
        //    370: invokevirtual  #231 // net.minecraft.class_287.method_60794:()Lnet/minecraft/class_9801;
        //    373: astore  19
        //    375: aload  19
        //    377: ifnull  385 (offset +8)
        //    380: aload  19
        //    382: invokestatic  #230 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    385: ldc  #58 // 227220934
        //    387: ldc  #58 // 227220934
        //    389: ixor
        //    390: ldc  #34 // -971033033
        //    392: ldc  #34 // -971033033
        //    394: ixor
        //    395: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //    398: invokestatic  #204 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    401: invokestatic  #201 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    404: return
    }

  public static void method397(ci arg0, class_2561 arg1, float arg2, Matrix4f arg3, float arg4, float arg5, float arg6, boolean arg7, float arg8, float arg9, float arg10, int arg11) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: ldc  #116 // 0.05000000074505806f
        //      2: fstore  12
        //      4: ldc  #118 // 0.5f
        //      6: fstore  13
        //      8: fconst_0
        //      9: fstore  14
        //     11: aload_1
        //     12: getstatic  #194 // dev.angelvisuals.a.bp.c:Ldev/angelvisuals/a/bp;
        //     15: invokevirtual  #215 // dev.angelvisuals.a.bp.E:()I
        //     18: invokestatic  #211 // dev.angelvisuals.a.aC.a:(Lnet/minecraft/class_2561;I)Ljava/util/List;
        //     21: astore  15
        //     23: fload  4
        //     25: fstore  16
        //     27: invokestatic  #203 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     30: invokestatic  #200 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //     33: invokestatic  #202 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //     36: ldc  #19 // -1410706444
        //     38: ldc  #19 // -1410706444
        //     40: ixor
        //     41: aload_0
        //     42: invokevirtual  #220 // dev.angelvisuals.a.ci.ao:()I
        //     45: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //     48: getstatic  #193 // dev.angelvisuals.a.Y.a:Lnet/minecraft/class_10156;
        //     51: invokestatic  #205 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     54: astore  17
        //     56: aload  17
        //     58: ldc  #128 // '\x1a¿Y\x1d=¡\x1f/4âs{?µL-zï[*\n°\x17t'
        //     60: ldc  #94 // 1566933601
        //     62: ldc  #61 // 340743468
        //     64: ixor
        //     65: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     68: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     71: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //     74: aload_0
        //     75: invokevirtual  #221 // dev.angelvisuals.a.ci.b:()Ldev/angelvisuals/a/an$e;
        //     78: invokevirtual  #214 // dev.angelvisuals.a.an$e.bi:()F
        //     81: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //     84: aload  17
        //     86: ldc  #149 // '\x83\x06}\x12àKx,ò\x04n1¨\x07i5¿$c$è1\x12]'
        //     88: ldc  #101 // 1666200545
        //     90: ldc  #53 // 58676024
        //     92: ixor
        //     93: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     96: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     99: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    102: fload  12
        //    104: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    107: aload  17
        //    109: ldc  #165 // 'úI\\V\x8c\x7f~uÃ\x0fL.\x81qA@Ø\x0cD<\x89m\x12*'
        //    111: ldc  #26 // -1193695279
        //    113: ldc  #22 // -1342792856
        //    115: ixor
        //    116: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    119: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    122: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    125: ldc  #119 // 0.5699999928474426f
        //    127: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    130: aload  17
        //    132: ldc  #127 // '\x19\x1e°\x9b\x03\x1b¯\x90 8¬\x90?\tô¸\rm²£\x04\røè'
        //    134: ldc  #104 // 1736283133
        //    136: ldc  #23 // -1296511055
        //    138: ixor
        //    139: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    142: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    145: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    148: iload  7
        //    150: ifeq  161 (offset +11)
        //    153: ldc  #66 // 476835622
        //    155: ldc  #67 // 476835623
        //    157: ixor
        //    158: goto  166 (offset +8)
        //    161: ldc  #91 // 1227049515
        //    163: ldc  #91 // 1227049515
        //    165: ixor
        //    166: invokevirtual  #229 // net.minecraft.class_284.method_35649:(I)V
        //    169: aload  17
        //    171: ldc  #125 // '\x16Òø��Têí3hîé\x1aOÔÏUNÉ³*KÂ·^'
        //    173: ldc  #100 // 1596613286
        //    175: ldc  #84 // 1017169799
        //    177: ixor
        //    178: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    181: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    184: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    187: fload  8
        //    189: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    192: aload  17
        //    194: ldc  #144 // 'oW§\x80lt\x89µl]Í¬x\\Ë´Np\x8d´NXÃÞ'
        //    196: ldc  #55 // 150378636
        //    198: ldc  #47 // -351764051
        //    200: ixor
        //    201: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    204: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    207: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    210: fload  9
        //    212: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    215: aload  17
        //    217: ldc  #151 // '\x93u~½ìe]¡ù\rv\x81ÉWM\x8aÏI|£\x92K\nÄ'
        //    219: ldc  #54 // 123429259
        //    221: ldc  #52 // -26452696
        //    223: ixor
        //    224: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    227: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    230: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    233: fload  10
        //    235: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    238: aload  17
        //    240: ldc  #156 // '¹bÓR¤Q÷L\x86CÏnºzµhµIÒJ©y¼\x04'
        //    242: ldc  #72 // 604089526
        //    244: ldc  #68 // 494961765
        //    246: ixor
        //    247: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    250: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    253: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    256: fload  4
        //    258: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    261: invokestatic  #232 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //    264: getstatic  #197 // net.minecraft.class_293$class_5596.field_27382:Lnet/minecraft/class_293$class_5596;
        //    267: getstatic  #196 // net.minecraft.class_290.field_1575:Lnet/minecraft/class_293;
        //    270: invokevirtual  #233 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    273: astore  18
        //    275: aload  15
        //    277: invokeinterface  #237 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    282: astore  20
        //    284: aload  20
        //    286: invokeinterface  #235 // java.util.Iterator.hasNext:()Z, count 1
        //    291: ifeq  407 (offset +116)
        //    294: aload  20
        //    296: invokeinterface  #236 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    301: checkcast  #171 // dev.angelvisuals.a.aC$a
        //    304: astore  19
        //    306: aload  19
        //    308: invokevirtual  #213 // dev.angelvisuals.a.aC$a.i:()I
        //    311: istore  21
        //    313: iload  11
        //    315: ldc  #98 // 1591008740
        //    317: ldc  #97 // 1591008539
        //    319: ixor
        //    320: if_icmpeq  348 (offset +28)
        //    323: iload  21
        //    325: ldc  #9 // -1857481058
        //    327: ldc  #10 // -1850283679
        //    329: ixor
        //    330: iand
        //    331: iload  11
        //    333: ldc  #41 // -612462688
        //    335: ldc  #40 // -612462753
        //    337: ixor
        //    338: iand
        //    339: ldc  #87 // 1166843567
        //    341: ldc  #88 // 1166843575
        //    343: ixor
        //    344: ishl
        //    345: ior
        //    346: istore  21
        //    348: aload_0
        //    349: aload_3
        //    350: aload  18
        //    352: aload  19
        //    354: invokevirtual  #212 // dev.angelvisuals.a.aC$a.af:()Ljava/lang/String;
        //    357: fload_2
        //    358: fload  12
        //    360: ldc  #118 // 0.5f
        //    362: fmul
        //    363: fload_2
        //    364: fmul
        //    365: fload  14
        //    367: ldc  #117 // 0.30000001192092896f
        //    369: fsub
        //    370: fload  16
        //    372: ldc  #121 // 0.75f
        //    374: fsub
        //    375: fload  5
        //    377: fload_2
        //    378: ldc  #120 // 0.699999988079071f
        //    380: fmul
        //    381: fadd
        //    382: fload  6
        //    384: iload  21
        //    386: invokevirtual  #218 // dev.angelvisuals.a.ci.a:(Lorg/joml/Matrix4f;Lnet/minecraft/class_4588;Ljava/lang/String;FFFFFFI)V
        //    389: fload  16
        //    391: aload_0
        //    392: aload  19
        //    394: invokevirtual  #212 // dev.angelvisuals.a.aC$a.af:()Ljava/lang/String;
        //    397: fload_2
        //    398: invokevirtual  #216 // dev.angelvisuals.a.ci.a:(Ljava/lang/String;F)F
        //    401: fadd
        //    402: fstore  16
        //    404: goto  284 (offset -120)
        //    407: aload  18
        //    409: invokevirtual  #231 // net.minecraft.class_287.method_60794:()Lnet/minecraft/class_9801;
        //    412: astore  20
        //    414: aload  20
        //    416: ifnull  424 (offset +8)
        //    419: aload  20
        //    421: invokestatic  #230 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    424: ldc  #85 // 1074771847
        //    426: ldc  #85 // 1074771847
        //    428: ixor
        //    429: ldc  #102 // 1667171327
        //    431: ldc  #102 // 1667171327
        //    433: ixor
        //    434: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //    437: invokestatic  #204 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    440: invokestatic  #201 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    443: return
    }

  public static void method398(ci arg0, class_2561 arg1, float arg2, Matrix4f arg3, float arg4, float arg5, float arg6, boolean arg7, float arg8, float arg9) { // было: a
        float var10 = arg0.method382(arg1, arg2) * 2.0f;
        method396(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, var10);
    }

  public static void method399(ci arg0, String arg1, float arg2, dp arg3, Matrix4f arg4, float arg5, float arg6, float arg7) { // было: a
        method400(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -384176839 ^ -384176839, 0.0f, 1.0f, 0.0f);
    }

  public static void method400(ci arg0, String arg1, float arg2, dp arg3, Matrix4f arg4, float arg5, float arg6, float arg7, boolean arg8, float arg9, float arg10, float arg11) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ldc  #124 // '\x06\xad\x18\x1a\x0cü/\x147´\x19\x10,´7+4\x99\x04\x12\t©pf'
        //      3: ldc  #106 // 1765474597
        //      5: ldc  #79 // 846647136
        //      7: ixor
        //      8: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     11: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     14: ldc  #160 // 'åêÅ½Êå÷\x94ÌÿÔ¿ÙÕÄ\x88øÚöË÷ó³Ç'
        //     16: ldc  #11 // -1847713507
        //     18: ldc  #109 // 1800442785
        //     20: ixor
        //     21: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     24: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     27: invokevirtual  #225 // java.lang.String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //     30: ldc  #129 // '\x1bÕ��E!ù\x08Q��\x87 Y\x13Ò\x08|\x04òD \x1aÀM('
        //     32: ldc  #65 // 467844100
        //     34: ldc  #59 // 244452207
        //     36: ixor
        //     37: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     40: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     43: ldc  #153 // '\x98Å¸ë½Æ½Ó£ç»è¿Ò\x94\x88\x8eÎ\x8bð«ÖÓ\x83'
        //     45: ldc  #70 // 546887152
        //     47: ldc  #13 // -1636417000
        //     49: ixor
        //     50: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     53: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     56: invokevirtual  #225 // java.lang.String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //     59: astore_1
        //     60: ldc  #116 // 0.05000000074505806f
        //     62: fstore  12
        //     64: ldc  #118 // 0.5f
        //     66: fstore  13
        //     68: fconst_0
        //     69: fstore  14
        //     71: invokestatic  #203 // com.mojang.blaze3d.systems.RenderSystem.enableBlend:()V
        //     74: invokestatic  #200 // com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc:()V
        //     77: invokestatic  #202 // com.mojang.blaze3d.systems.RenderSystem.disableCull:()V
        //     80: ldc  #2 // -2082183502
        //     82: ldc  #2 // -2082183502
        //     84: ixor
        //     85: aload_0
        //     86: invokevirtual  #220 // dev.angelvisuals.a.ci.ao:()I
        //     89: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //     92: getstatic  #193 // dev.angelvisuals.a.Y.a:Lnet/minecraft/class_10156;
        //     95: invokestatic  #205 // com.mojang.blaze3d.systems.RenderSystem.setShader:(Lnet/minecraft/class_10156;)Lnet/minecraft/class_5944;
        //     98: astore  15
        //    100: aload  15
        //    102: ldc  #145 // 'oÌ\x1b\x80HÒ]²A\x911æJÆ\x0e°\x0f\x9c\x19·\x7fÃUé'
        //    104: ldc  #30 // -1074296388
        //    106: ldc  #110 // 1805593988
        //    108: ixor
        //    109: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    112: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    115: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    118: aload_0
        //    119: invokevirtual  #221 // dev.angelvisuals.a.ci.b:()Ldev/angelvisuals/a/an$e;
        //    122: invokevirtual  #214 // dev.angelvisuals.a.an$e.bi:()F
        //    125: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    128: aload  15
        //    130: ldc  #163 // 'õ"ª¦\x96o¯\x98\x84 ¹\x85Þ#¾\x81É��´\x90\x9e\x15Åé'
        //    132: ldc  #96 // 1574364475
        //    134: ldc  #6 // -1993432684
        //    136: ixor
        //    137: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    140: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    143: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    146: fload  12
        //    148: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    151: aload  15
        //    153: ldc  #146 // 'p\x0f\x16T\x0694wII\x06,\x0b7\x0bBRJ\x0e>\x03+X('
        //    155: ldc  #71 // 593312630
        //    157: ldc  #82 // 909656389
        //    159: ixor
        //    160: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    163: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    166: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    169: ldc  #119 // 0.5699999928474426f
        //    171: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    174: aload  15
        //    176: ldc  #136 // '<\x8f\x16m&\x8a\tf\x05©\nf\x1a\x98RN(ü\x14U!\x9c^\x1e'
        //    178: ldc  #93 // 1518467231
        //    180: ldc  #114 // 2044868086
        //    182: ixor
        //    183: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    186: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    189: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    192: iload  8
        //    194: ifeq  205 (offset +11)
        //    197: ldc  #32 // -998938716
        //    199: ldc  #33 // -998938715
        //    201: ixor
        //    202: goto  210 (offset +8)
        //    205: ldc  #69 // 535358419
        //    207: ldc  #69 // 535358419
        //    209: ixor
        //    210: invokevirtual  #229 // net.minecraft.class_284.method_35649:(I)V
        //    213: aload  15
        //    215: ldc  #159 // '×o·Ô\x95W¢ç©S¦Î\x8ei\x80\x81\x8ftüþ\x8a\x7fø\x8a'
        //    217: ldc  #44 // -569848379
        //    219: ldc  #107 // 1775097125
        //    221: ixor
        //    222: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    225: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    228: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    231: fload  9
        //    233: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    236: aload  15
        //    238: ldc  #162 // 'óæ \x97ðÅ\x0e¢ðìJ»äíL£ÒÁ\n£ÒéDÉ'
        //    240: ldc  #78 // 842180657
        //    242: ldc  #35 // -968163188
        //    244: ixor
        //    245: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    248: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    251: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    254: fload  10
        //    256: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    259: aload  15
        //    261: ldc  #122 // '\x01\r3$~\x1d\x108ku;\x18[/��\x13]11:��3G]'
        //    263: ldc  #37 // -917927147
        //    265: ldc  #18 // -1456222428
        //    267: ixor
        //    268: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    271: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    274: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    277: fload  11
        //    279: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    282: aload  15
        //    284: ldc  #135 // '9{KÁ$Hoß\x06ZWý:c-û5PJÙ)`$\x97'
        //    286: ldc  #29 // -1121758329
        //    288: ldc  #62 // 389702356
        //    290: ixor
        //    291: invokestatic  #199 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //    294: invokestatic  #198 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //    297: invokevirtual  #234 // net.minecraft.class_5944.method_34582:(Ljava/lang/String;)Lnet/minecraft/class_284;
        //    300: fload  5
        //    302: invokevirtual  #228 // net.minecraft.class_284.method_1251:(F)V
        //    305: invokestatic  #232 // net.minecraft.class_289.method_1348:()Lnet/minecraft/class_289;
        //    308: getstatic  #197 // net.minecraft.class_293$class_5596.field_27382:Lnet/minecraft/class_293$class_5596;
        //    311: getstatic  #196 // net.minecraft.class_290.field_1575:Lnet/minecraft/class_293;
        //    314: invokevirtual  #233 // net.minecraft.class_289.method_60827:(Lnet/minecraft/class_293$class_5596;Lnet/minecraft/class_293;)Lnet/minecraft/class_287;
        //    317: astore  16
        //    319: aload_0
        //    320: aload  4
        //    322: aload  16
        //    324: aload_1
        //    325: fload_2
        //    326: fload  12
        //    328: ldc  #118 // 0.5f
        //    330: fmul
        //    331: fload_2
        //    332: fmul
        //    333: fload  14
        //    335: fload  5
        //    337: ldc  #121 // 0.75f
        //    339: fsub
        //    340: fload  6
        //    342: fload_2
        //    343: ldc  #120 // 0.699999988079071f
        //    345: fmul
        //    346: fadd
        //    347: fload  7
        //    349: aload_3
        //    350: invokevirtual  #219 // dev.angelvisuals.a.ci.a:(Lorg/joml/Matrix4f;Lnet/minecraft/class_4588;Ljava/lang/String;FFFFFFLdev/angelvisuals/a/dp;)V
        //    353: aload  16
        //    355: invokevirtual  #231 // net.minecraft.class_287.method_60794:()Lnet/minecraft/class_9801;
        //    358: astore  17
        //    360: aload  17
        //    362: ifnull  370 (offset +8)
        //    365: aload  17
        //    367: invokestatic  #230 // net.minecraft.class_286.method_43433:(Lnet/minecraft/class_9801;)V
        //    370: ldc  #48 // -320623190
        //    372: ldc  #48 // -320623190
        //    374: ixor
        //    375: ldc  #16 // -1497277610
        //    377: ldc  #16 // -1497277610
        //    379: ixor
        //    380: invokestatic  #206 // com.mojang.blaze3d.systems.RenderSystem.setShaderTexture:(II)V
        //    383: invokestatic  #204 // com.mojang.blaze3d.systems.RenderSystem.enableCull:()V
        //    386: invokestatic  #201 // com.mojang.blaze3d.systems.RenderSystem.disableBlend:()V
        //    389: return
    }

  public static void method401(ci arg0, String arg1, float arg2, dp arg3, Matrix4f arg4, float arg5, float arg6, float arg7, boolean arg8, float arg9, float arg10) { // было: a
        float var11 = arg0.method381(arg1, arg2) * 2.0f;
        method400(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, var11);
    }

    @Generated
  private ClassA40() { // было: <init>
        super();
        throw new UnsupportedOperationException(Decryptor.method1945(XorDecoder.method1946("°sb\u000bª\u0019u7ylp¶D}\u0008Ã}].Â\u0006a;ÃHJ\u0016j_\u0019J\u001e\u000eÆ\u0003\u007foKF*\u0001f7ªti\u0012£Fh\r`ljÁZNj©YN-«z\u0013\u000eÂVJt¸BolÁFQ\u0018Às\u001ab", 1817093015 ^ 862556516)));
    }

  private static int bX(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bY(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int bZ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}