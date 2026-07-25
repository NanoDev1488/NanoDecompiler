// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ac.c
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.ac;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.GeneratedSerializer.DefaultImpls;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Metadata
public final class ac_ClassA172 implements GeneratedSerializer {

    // ---- поля ----
    @NotNull
  public static final ac_ClassA172 field998; // было: a
  private static final PluginGeneratedSerialDescriptor field999; // было: a
  private static final String GA = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final String GB = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String GC = "// number obfuscation: ENABLED (XOR masking)";
  private static final String GD = "// good luck with the next 9999 classes";
  private static final String GE = "// flow obfuscation: ENABLED";
  private static final int tn = 1008555773;
  private static final int to = -1974375113;
  private static final int tp = -47496671;
  private static final byte[] fe;

    static {
        fe = "_/m[:M|,2T~!<oq(F]o$3u*3Eh4-7QBu9\\nNZue0[1Kmp>@S8)~sIE?!7E%&{{uNZ.zI={j'7sfPBmZAWQwcn);LIu\"Dn>H=N>Mt #kCb:BS]nL=_80kO+8>aL9<@?R;iyIl=\\y)g9fd]#A%7\"{t e%;=/{{ \"'\\[4K8fL==L/q<KcdSpw'.1j,O!JDEc3P~Cz:&qA5wg==Y&C~njqxe1W 5?I`]T },~~^nP[-K>/m^h*#p%j!`@'9A`1-]ltN{".getBytes("ISO-8859-1");
        field998 = new ac_ClassA172();
        PluginGeneratedSerialDescriptor var0 = new PluginGeneratedSerialDescriptor(Decryptor.method1945(XorDecoder.method1946("G|h-I§d\u0013YJG^Æ\u0008;AF&\u001b±L\u001b^ªf��ES2mR'eÀ\u0008\u001b\u0019\u0008\u000emXBWÁl\u0001vM\u0015{Ëk>]¼\u000c", -1046236548 ^ -45048565)), ((GeneratedSerializer) field998), -334296402 ^ -334296408);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("$,Ïk\u001b\u0006¸`\u0015%âl7UÉ5# »6%\u0003µ9", -674224655 ^ -749190782)), -2073326827 ^ -2073326827);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("Î2ý¤ý\rÔ¢ó\u0003Û8Á¿Ä\"ëä0ú", -1859514560 ^ 1451716331)), -1769353236 ^ -1769353236);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("¬¯ÃÌ«Æ÷²Õ÷äþÅÚ·ÉâÉ¹Í½", 2091627712 ^ -65067000)), -717871962 ^ -717871962);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("`£\u007f\u0005(þ\u0015<d &\n\u0005óx\u000e\u0018Ú\u0005R:ÑqV", 1067796632 ^ 1424611529)), -1565976147 ^ -1565976147);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("øØ¹Úµ××ÞöÈËí¢ïÜÍÈ", 369227171 ^ -470725075)), -221608640 ^ -221608640);
        var0.addElement(Decryptor.method1945(XorDecoder.method1946("`÷.mø¯0?ö´\u0011jÄ¢Q]àÞ\r}Å¦C", -666374632 ^ -1495512041)), -2043826114 ^ -2043826114);
        field999 = var0;
    }

  private ac_ClassA172() { // было: <init>
        super();
    }

    @NotNull
  public KSerializer[] typeParametersSerializers() {
        return DefaultImpls.typeParametersSerializers(this);
    }

    @NotNull
  public SerialDescriptor method1893() { // было: a
        return ((SerialDescriptor) field999);
    }

    @NotNull
  public KSerializer[] childSerializers() {
        KSerializer[] var1 = new KSerializer[-1587407326 ^ -1587407324];
        var1[-1530614741 ^ -1530614741] = StringSerializer.INSTANCE;
        var1[-1692574752 ^ -1692574751] = StringSerializer.INSTANCE;
        var1[-1943262933 ^ -1943262935] = ByteArraySerializer.INSTANCE;
        var1[2097167543 ^ 2097167540] = LongSerializer.INSTANCE;
        var1[618968170 ^ 618968174] = LongSerializer.INSTANCE;
        var1[-1972991211 ^ -1972991216] = BooleanSerializer.INSTANCE;
        return var1;
    }

    @NotNull
  public ac method1894(@NotNull Decoder arg0) { // было: a
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ldc  #111 // 'sû¨Ð\x7f\x8e©ÖbÊ\x81ëWÅ\x9dÙVÄ\x9f«\x0eÖÄ¥'
        //      3: ldc  #20 // -1503287758
        //      5: ldc  #74 // 1050412808
        //      7: ixor
        //      8: invokestatic  #152 // com.joiner.runtime.XorDecoder.d:(Ljava/lang/String;I)Ljava/lang/String;
        //     11: invokestatic  #151 // com.joiner.runtime.Decryptor.d:(Ljava/lang/String;)Ljava/lang/String;
        //     14: invokestatic  #161 // kotlin.jvm.internal.Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
        //     17: aload_0
        //     18: invokevirtual  #156 // dev.angelvisuals.a.ac$c.a:()Lkotlinx/serialization/descriptors/SerialDescriptor;
        //     21: astore_2
        //     22: ldc  #76 // 1141724194
        //     24: ldc  #77 // 1141724195
        //     26: ixor
        //     27: istore_3
        //     28: ldc  #91 // 1883305465
        //     30: ldc  #91 // 1883305465
        //     32: ixor
        //     33: istore  5
        //     35: aconst_null
        //     36: astore  6
        //     38: aconst_null
        //     39: astore  7
        //     41: aconst_null
        //     42: astore  8
        //     44: ldc2_w  #140 // -5419672592605512229L
        //     47: ldc2_w  #140 // -5419672592605512229L
        //     50: lxor
        //     51: lstore  9
        //     53: ldc2_w  #142 // 2082142452577248347L
        //     56: ldc2_w  #142 // 2082142452577248347L
        //     59: lxor
        //     60: lstore  11
        //     62: ldc  #78 // 1152739765
        //     64: ldc  #78 // 1152739765
        //     66: ixor
        //     67: istore  13
        //     69: aload_1
        //     70: aload_2
        //     71: invokeinterface  #174 // kotlinx.serialization.encoding.Decoder.beginStructure:(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/CompositeDecoder;, count 2
        //     76: astore  14
        //     78: aload  14
        //     80: invokeinterface  #169 // kotlinx.serialization.encoding.CompositeDecoder.decodeSequentially:()Z, count 1
        //     85: ifeq  252 (offset +167)
        //     88: aload  14
        //     90: aload_2
        //     91: ldc  #45 // -281977605
        //     93: ldc  #45 // -281977605
        //     95: ixor
        //     96: invokeinterface  #171 // kotlinx.serialization.encoding.CompositeDecoder.decodeStringElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/String;, count 3
        //    101: astore  6
        //    103: iload  5
        //    105: ldc  #73 // 1037521123
        //    107: ldc  #72 // 1037521122
        //    109: ixor
        //    110: ior
        //    111: istore  5
        //    113: aload  14
        //    115: aload_2
        //    116: ldc  #10 // -1891360562
        //    118: ldc  #11 // -1891360561
        //    120: ixor
        //    121: invokeinterface  #171 // kotlinx.serialization.encoding.CompositeDecoder.decodeStringElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/String;, count 3
        //    126: astore  7
        //    128: iload  5
        //    130: ldc  #92 // 1923883833
        //    132: ldc  #93 // 1923883835
        //    134: ixor
        //    135: ior
        //    136: istore  5
        //    138: aload  14
        //    140: aload_2
        //    141: ldc  #79 // 1197856013
        //    143: ldc  #80 // 1197856015
        //    145: ixor
        //    146: getstatic  #148 // kotlinx.serialization.internal.ByteArraySerializer.INSTANCE:Lkotlinx/serialization/internal/ByteArraySerializer;
        //    149: checkcast  #125 // kotlinx.serialization.DeserializationStrategy
        //    152: aload  8
        //    154: invokeinterface  #170 // kotlinx.serialization.encoding.CompositeDecoder.decodeSerializableElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;, count 5
        //    159: checkcast  #117 // [B
        //    162: astore  8
        //    164: iload  5
        //    166: ldc  #60 // 180154519
        //    168: ldc  #59 // 180154515
        //    170: ixor
        //    171: ior
        //    172: istore  5
        //    174: aload  14
        //    176: aload_2
        //    177: ldc  #90 // 1735197347
        //    179: ldc  #89 // 1735197344
        //    181: ixor
        //    182: invokeinterface  #168 // kotlinx.serialization.encoding.CompositeDecoder.decodeLongElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)J, count 3
        //    187: lstore  9
        //    189: iload  5
        //    191: ldc  #88 // 1726425903
        //    193: ldc  #87 // 1726425895
        //    195: ixor
        //    196: ior
        //    197: istore  5
        //    199: aload  14
        //    201: aload_2
        //    202: ldc  #86 // 1578553630
        //    204: ldc  #85 // 1578553626
        //    206: ixor
        //    207: invokeinterface  #168 // kotlinx.serialization.encoding.CompositeDecoder.decodeLongElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)J, count 3
        //    212: lstore  11
        //    214: iload  5
        //    216: ldc  #68 // 528232479
        //    218: ldc  #67 // 528232463
        //    220: ixor
        //    221: ior
        //    222: istore  5
        //    224: aload  14
        //    226: aload_2
        //    227: ldc  #66 // 498319820
        //    229: ldc  #65 // 498319817
        //    231: ixor
        //    232: invokeinterface  #166 // kotlinx.serialization.encoding.CompositeDecoder.decodeBooleanElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z, count 3
        //    237: istore  13
        //    239: iload  5
        //    241: ldc  #22 // -1252991786
        //    243: ldc  #23 // -1252991754
        //    245: ixor
        //    246: ior
        //    247: istore  5
        //    249: goto  510 (offset +261)
        //    252: iload_3
        //    253: ifeq  510 (offset +257)
        //    256: aload  14
        //    258: aload_2
        //    259: invokeinterface  #167 // kotlinx.serialization.encoding.CompositeDecoder.decodeElementIndex:(Lkotlinx/serialization/descriptors/SerialDescriptor;)I, count 2
        //    264: istore  4
        //    266: iload  4
        //    268: tableswitch  default->500, -1->312, 0->321, 1->349, 2->377, 3->416, 4->444, 5->472
        //    312: ldc  #96 // 1999297466
        //    314: ldc  #96 // 1999297466
        //    316: ixor
        //    317: istore_3
        //    318: goto  252 (offset -66)
        //    321: aload  14
        //    323: aload_2
        //    324: ldc  #38 // -492374576
        //    326: ldc  #38 // -492374576
        //    328: ixor
        //    329: invokeinterface  #171 // kotlinx.serialization.encoding.CompositeDecoder.decodeStringElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/String;, count 3
        //    334: astore  6
        //    336: iload  5
        //    338: ldc  #81 // 1346037722
        //    340: ldc  #82 // 1346037723
        //    342: ixor
        //    343: ior
        //    344: istore  5
        //    346: goto  252 (offset -94)
        //    349: aload  14
        //    351: aload_2
        //    352: ldc  #2 // -2073227618
        //    354: ldc  #3 // -2073227617
        //    356: ixor
        //    357: invokeinterface  #171 // kotlinx.serialization.encoding.CompositeDecoder.decodeStringElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/String;, count 3
        //    362: astore  7
        //    364: iload  5
        //    366: ldc  #95 // 1932629167
        //    368: ldc  #94 // 1932629165
        //    370: ixor
        //    371: ior
        //    372: istore  5
        //    374: goto  252 (offset -122)
        //    377: aload  14
        //    379: aload_2
        //    380: ldc  #58 // 151199887
        //    382: ldc  #57 // 151199885
        //    384: ixor
        //    385: getstatic  #148 // kotlinx.serialization.internal.ByteArraySerializer.INSTANCE:Lkotlinx/serialization/internal/ByteArraySerializer;
        //    388: checkcast  #125 // kotlinx.serialization.DeserializationStrategy
        //    391: aload  8
        //    393: invokeinterface  #170 // kotlinx.serialization.encoding.CompositeDecoder.decodeSerializableElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;, count 5
        //    398: checkcast  #117 // [B
        //    401: astore  8
        //    403: iload  5
        //    405: ldc  #62 // 308931367
        //    407: ldc  #61 // 308931363
        //    409: ixor
        //    410: ior
        //    411: istore  5
        //    413: goto  252 (offset -161)
        //    416: aload  14
        //    418: aload_2
        //    419: ldc  #31 // -721766681
        //    421: ldc  #30 // -721766684
        //    423: ixor
        //    424: invokeinterface  #168 // kotlinx.serialization.encoding.CompositeDecoder.decodeLongElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)J, count 3
        //    429: lstore  9
        //    431: iload  5
        //    433: ldc  #26 // -1206453616
        //    435: ldc  #27 // -1206453608
        //    437: ixor
        //    438: ior
        //    439: istore  5
        //    441: goto  252 (offset -189)
        //    444: aload  14
        //    446: aload_2
        //    447: ldc  #25 // -1227421778
        //    449: ldc  #24 // -1227421782
        //    451: ixor
        //    452: invokeinterface  #168 // kotlinx.serialization.encoding.CompositeDecoder.decodeLongElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)J, count 3
        //    457: lstore  11
        //    459: iload  5
        //    461: ldc  #36 // -656926693
        //    463: ldc  #35 // -656926709
        //    465: ixor
        //    466: ior
        //    467: istore  5
        //    469: goto  252 (offset -217)
        //    472: aload  14
        //    474: aload_2
        //    475: ldc  #40 // -489085713
        //    477: ldc  #39 // -489085718
        //    479: ixor
        //    480: invokeinterface  #166 // kotlinx.serialization.encoding.CompositeDecoder.decodeBooleanElement:(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z, count 3
        //    485: istore  13
        //    487: iload  5
        //    489: ldc  #49 // -149222109
        //    491: ldc  #48 // -149222141
        //    493: ixor
        //    494: ior
        //    495: istore  5
        //    497: goto  252 (offset -245)
        //    500: new  #127 // kotlinx.serialization.UnknownFieldException
        //    503: dup
        //    504: iload  4
        //    506: invokespecial  #162 // kotlinx.serialization.UnknownFieldException.<init>:(I)V
        //    509: athrow
        //    510: aload  14
        //    512: aload_2
        //    513: invokeinterface  #172 // kotlinx.serialization.encoding.CompositeDecoder.endStructure:(Lkotlinx/serialization/descriptors/SerialDescriptor;)V, count 2
        //    518: new  #120 // dev.angelvisuals.a.ac
        //    521: dup
        //    522: iload  5
        //    524: aload  6
        //    526: aload  7
        //    528: aload  8
        //    530: lload  9
        //    532: lload  11
        //    534: iload  13
        //    536: aconst_null
        //    537: invokespecial  #153 // dev.angelvisuals.a.ac.<init>:(ILjava/lang/String;Ljava/lang/String;[BJJZLkotlinx/serialization/internal/SerializationConstructorMarker;)V
        //    540: areturn
    }

  public void method1895(@NotNull Encoder arg0, @NotNull ac arg1) { // было: a
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("|Ép´ös¤ÌQ¹ \u000cÝÑl½ÿ", -619053879 ^ 429461233)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("Ù\u001fIdØ0Ie(xgä\u0019Z\\Ú0N) \u0015 ", -191210130 ^ -374199104)));
        SerialDescriptor var3 = method1893();
        CompositeEncoder var4 = arg0.beginStructure(var3);
        ac.method1915(arg1, var4, var3);
        var4.endStructure(var3);
    }

  public Object method1896(Decoder arg0) { // было: a
        return method1894(arg0);
    }

  public void method1897(Encoder arg0, Object arg1) { // было: a
        method1895(arg0, ((ac) arg1));
    }

  private static int rk(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int rl(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int rm(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}