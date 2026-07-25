// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ac
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ac_ClassA172;
import dev.angelvisuals.a.ac_ClassA173;
import dev.angelvisuals.a.ac_ClassA174;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata
public final class ac {

    // ---- поля ----
    @NotNull
  public static final ac_ClassA174 field1001; // было: a
    @NotNull
  private final String oR;
    @NotNull
  private final String oS;
    @NotNull
  private final byte[] bW;
  private final long field1002; // было: r
  private final long field1003; // было: s
  private final boolean field1004; // было: T
    @NotNull
  private final Lazy field1005; // было: a
  private static final String oT = "// you are reading machine-generated garbage";
  private static final String oU = "// === DO NOT TOUCH ===";
  private static final String oV = "// every class watermarked, every string encrypted, every number xored";
  private static final String oW = "// nice try. closed source for a reason.";
  private static final String oX = "// class hierarchy hashing: ENABLED";
  private static final int iS = 619165982;
  private static final int iT = 121237110;
  private static final int iU = -247148342;
  private static final byte[] bX;

    static {
        bX = "0-i2GYffSV@d:k8`Ln]8<c[wR]D=>?23.w17n)5SK\"I HXMZ:* _kq (_.:ORub<%yL2o,5EshdigGn!oFEUc;#ns\")be]5$|^M)1u9r&F.ij4=RO1cU9P}nQ%idxxm@U#T0T9q36aQW%XS6|^@2$e1A{nVj!~BZEH0>pv=t&rbrF_r:x0XZR|>7fRk2t)t?~zNAm(SV`n-qnCjP+1\\ SwCH_&./L*7+]C7~7u#%ox9pZ+tAewo :ydfaex{ x{%".getBytes("ISO-8859-1");
        field1001 = new ac_ClassA174(null);
    }

  public ac(@NotNull String arg0, @NotNull String arg1, @NotNull byte[] arg2, long arg3, long arg4, boolean arg5) { // было: <init>
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("NþT_qÔ#T\u007f÷yX]R\u0001Iò \u0002OÑ.\r", 162015641 ^ 968597376)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("OWn¼p~h²~6WEku_AK¥M:0", 1131674529 ^ 1316284235)));
        Intrinsics.checkNotNullParameter(arg2, Decryptor.method1945(XorDecoder.method1946("Ä\u0014ýÜð\u0010øçÚnÉô~ä§õrÜÙÑv·­", -640771825 ^ 1229242031)));
        super();
        oR = arg0;
        oS = arg1;
        bW = arg2;
        field1002 = arg3;
        field1003 = arg4;
        field1004 = arg5;
        field1005 = LazyKt.lazy(((Function0) new ac_ClassA173(this)));
    }

    @NotNull
  public final String method1900() { // было: J
        return oR;
    }

    @NotNull
  public final String method1901() { // было: K
        return oS;
    }

    @NotNull
  public final byte[] method1902() { // было: a
        return bW;
    }

  public final long method1903() { // было: g
        return field1002;
    }

  public final long method1904() { // было: h
        return field1003;
    }

  public final boolean method1905() { // было: G
        return field1004;
    }

    @Nullable
  public final BufferedImage method1906() { // было: b
        Lazy var1 = field1005;
        return ((BufferedImage) var1.getValue());
    }

  public boolean equals(@Nullable Object arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: if_acmpne  11 (offset +9)
        //      5: ldc  #51 // 829645426
        //      7: ldc  #52 // 829645427
        //      9: ixor
        //     10: ireturn
        //     11: aload_0
        //     12: invokevirtual  #129 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     15: aload_1
        //     16: dup
        //     17: ifnull  26 (offset +9)
        //     20: invokevirtual  #129 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     23: goto  28 (offset +5)
        //     26: pop
        //     27: aconst_null
        //     28: invokestatic  #136 // kotlin.jvm.internal.Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //     31: ifne  40 (offset +9)
        //     34: ldc  #23 // -567787591
        //     36: ldc  #23 // -567787591
        //     38: ixor
        //     39: ireturn
        //     40: aload_1
        //     41: ldc  #78 // '3a\x9bÓ\x04]¬§\x18\x06\x90ä\x1a\x7f½á7z´á8n\x83¹hS\x8dó\x08\\²ëkB¶âjz·¤\x08l±¹\x1ey¥«\x16\x1e¹¦?\x19\x84ó\x14\x06\x97÷\x0exÄÄ,@\x99Õ\x1eY\x8dù\x06G\x96Ö\x16_ÆÁ\x16`Ìè\x1b@\x87¡5p¸¤nM\x9eÂ*s\x8dë3q¿ô2S´¯'
        //     43: ldc  #45 // 730272171
        //     45: ldc  #16 // -1183696649
        //     47: ixor
        //     48: invokestatic  #120 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     51: invokestatic  #119 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     54: invokestatic  #137 // kotlin.jvm.internal.Intrinsics.checkNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
        //     57: aload_1
        //     58: checkcast  #89 // dev.angelvisuals.a.ac
        //     61: pop
        //     62: aload_0
        //     63: getfield  #113 // dev.angelvisuals.a.ac.oR:Ljava/lang/String;
        //     66: aload_1
        //     67: checkcast  #89 // dev.angelvisuals.a.ac
        //     70: getfield  #113 // dev.angelvisuals.a.ac.oR:Ljava/lang/String;
        //     73: invokestatic  #136 // kotlin.jvm.internal.Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //     76: ifne  85 (offset +9)
        //     79: ldc  #8 // -1672422831
        //     81: ldc  #8 // -1672422831
        //     83: ixor
        //     84: ireturn
        //     85: aload_0
        //     86: getfield  #114 // dev.angelvisuals.a.ac.oS:Ljava/lang/String;
        //     89: aload_1
        //     90: checkcast  #89 // dev.angelvisuals.a.ac
        //     93: getfield  #114 // dev.angelvisuals.a.ac.oS:Ljava/lang/String;
        //     96: invokestatic  #136 // kotlin.jvm.internal.Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //     99: ifne  108 (offset +9)
        //    102: ldc  #1 // -2116579975
        //    104: ldc  #1 // -2116579975
        //    106: ixor
        //    107: ireturn
        //    108: aload_0
        //    109: getfield  #111 // dev.angelvisuals.a.ac.bW:[B
        //    112: aload_1
        //    113: checkcast  #89 // dev.angelvisuals.a.ac
        //    116: getfield  #111 // dev.angelvisuals.a.ac.bW:[B
        //    119: invokestatic  #133 // java.util.Arrays.equals:([B[B)Z
        //    122: ifne  131 (offset +9)
        //    125: ldc  #46 // 760629531
        //    127: ldc  #46 // 760629531
        //    129: ixor
        //    130: ireturn
        //    131: aload_0
        //    132: getfield  #115 // dev.angelvisuals.a.ac.r:J
        //    135: aload_1
        //    136: checkcast  #89 // dev.angelvisuals.a.ac
        //    139: getfield  #115 // dev.angelvisuals.a.ac.r:J
        //    142: lcmp
        //    143: ifeq  152 (offset +9)
        //    146: ldc  #17 // -1099784169
        //    148: ldc  #17 // -1099784169
        //    150: ixor
        //    151: ireturn
        //    152: aload_0
        //    153: getfield  #116 // dev.angelvisuals.a.ac.s:J
        //    156: aload_1
        //    157: checkcast  #89 // dev.angelvisuals.a.ac
        //    160: getfield  #116 // dev.angelvisuals.a.ac.s:J
        //    163: lcmp
        //    164: ifeq  173 (offset +9)
        //    167: ldc  #41 // 242843256
        //    169: ldc  #41 // 242843256
        //    171: ixor
        //    172: ireturn
        //    173: aload_0
        //    174: getfield  #108 // dev.angelvisuals.a.ac.T:Z
        //    177: aload_1
        //    178: checkcast  #89 // dev.angelvisuals.a.ac
        //    181: getfield  #108 // dev.angelvisuals.a.ac.T:Z
        //    184: if_icmpeq  193 (offset +9)
        //    187: ldc  #60 // 1258162065
        //    189: ldc  #60 // 1258162065
        //    191: ixor
        //    192: ireturn
        //    193: ldc  #21 // -1025367791
        //    195: ldc  #20 // -1025367792
        //    197: ixor
        //    198: ireturn
    }

  public int hashCode() {
        int var1 = oR.hashCode();
        var1 = (-1798251082 ^ -1798251095) * var1 + oS.hashCode();
        var1 = (1305477277 ^ 1305477250) * var1 + Arrays.hashCode(bW);
        var1 = (-1406047502 ^ -1406047507) * var1 + Long.hashCode(field1002);
        var1 = (-1695617802 ^ -1695617815) * var1 + Long.hashCode(field1003);
        var1 = (-171988068 ^ -171988093) * var1 + Boolean.hashCode(field1004);
        return var1;
    }

    @NotNull
  public String toString() {
        return "MediaInfo(title='" + oR + "', artist='" + oS + "', position=" + field1002 + ", duration=" + field1003 + ", playing=" + field1004 + ")";
    }

    @NotNull
  public final String method1907() { // было: L
        return oR;
    }

    @NotNull
  public final String method1908() { // было: M
        return oS;
    }

    @NotNull
  public final byte[] method1909() { // было: b
        return bW;
    }

  public final long method1910() { // было: i
        return field1002;
    }

  public final long method1911() { // было: j
        return field1003;
    }

  public final boolean method1912() { // было: H
        return field1004;
    }

    @NotNull
  public final ac method1913(@NotNull String arg0, @NotNull String arg1, @NotNull byte[] arg2, long arg3, long arg4, boolean arg5) { // было: a
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("FGãßymÔwNÎØU>åAKGh", 1571114761 ^ -318685160)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("8)éç\u000b\u0016Àá\u0005\u0018Þ-#Õü29ÿÂ\u0012+¹", 867749861 ^ -1224624712)));
        Intrinsics.checkNotNullParameter(arg2, Decryptor.method1945(XorDecoder.method1946("5Ç\u001e\u0001Ã%+½®6g­e\u0004¡»\u001b ¥Ðo", 1351053800 ^ 40535481)));
        return new ac(arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public static ac method1914(ac arg0, String arg1, String arg2, byte[] arg3, long arg4, long arg5, boolean arg6, int arg7, Object arg8) { // было: a
        if ((arg7 & (75983969 ^ 75983968)) != 0) {
            arg1 = arg0.oR;
        }
        if ((arg7 & (1547409329 ^ 1547409331)) != 0) {
            arg2 = arg0.oS;
        }
        if ((arg7 & (821185090 ^ 821185094)) != 0) {
            arg3 = arg0.bW;
        }
        if ((arg7 & (-1438497143 ^ -1438497151)) != 0) {
            arg4 = arg0.field1002;
        }
        if ((arg7 & (778455339 ^ 778455355)) != 0) {
            arg5 = arg0.field1003;
        }
        if ((arg7 & (2003095874 ^ 2003095906)) != 0) {
            arg6 = arg0.field1004;
        }
        return arg0.method1913(arg1, arg2, arg3, arg4, arg5, arg6);
    }

    @JvmStatic
  public static final void method1915(ac arg0, CompositeEncoder arg1, SerialDescriptor arg2) { // было: a
        arg1.encodeStringElement(arg2, 923483788 ^ 923483788, arg0.oR);
        arg1.encodeStringElement(arg2, -1332471668 ^ -1332471667, arg0.oS);
        arg1.encodeSerializableElement(arg2, 1237602274 ^ 1237602272, ((SerializationStrategy) ByteArraySerializer.INSTANCE), arg0.bW);
        arg1.encodeLongElement(arg2, 724837322 ^ 724837321, arg0.field1002);
        arg1.encodeLongElement(arg2, -1695830315 ^ -1695830319, arg0.field1003);
        arg1.encodeBooleanElement(arg2, 230253080 ^ 230253085, arg0.field1004);
    }

    @Deprecated
  public ac(int arg0, String arg1, String arg2, byte[] arg3, long arg4, long arg5, boolean arg6, SerializationConstructorMarker arg7) { // было: <init>
        if ((225601152 ^ 225601215) != ((1770027859 ^ 1770027884) & arg0)) {
            PluginExceptionsKt.throwMissingFieldException(arg0, -1063123046 ^ -1063123035, ac_ClassA172.field998.method1893());
        }
        super();
        oR = arg1;
        oS = arg2;
        bW = arg3;
        field1002 = arg4;
        field1003 = arg5;
        field1004 = arg6;
        field1005 = LazyKt.lazy(((Function0) new ac_ClassA173(this)));
    }

  private static int hO(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hP(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int hQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}