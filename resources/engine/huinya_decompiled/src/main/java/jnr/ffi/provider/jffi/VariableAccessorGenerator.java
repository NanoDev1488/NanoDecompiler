// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.VariableAccessorGenerator
package jnr.ffi.provider.jffi;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Variable;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.NativeLibraryLoader;
import jnr.ffi.provider.jffi.SimpleNativeContext;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.VariableAccessorGenerator_PointerOp;

public class VariableAccessorGenerator {

    // ---- поля ----
  private final AtomicLong nextClassID;
  private final Runtime runtime;
  static final Map pointerOperations;
  private static final VariableAccessorGenerator_PointerOp POINTER_OP_POINTER;

    static {
        EnumMap var0 = new EnumMap(NativeType.class);
        op(var0, NativeType.SCHAR, "Byte", Byte.TYPE);
        op(var0, NativeType.UCHAR, "Byte", Byte.TYPE);
        op(var0, NativeType.SSHORT, "Short", Short.TYPE);
        op(var0, NativeType.USHORT, "Short", Short.TYPE);
        op(var0, NativeType.SINT, "Int", Integer.TYPE);
        op(var0, NativeType.UINT, "Int", Integer.TYPE);
        op(var0, NativeType.SLONG, "Long", Long.TYPE);
        op(var0, NativeType.ULONG, "Long", Long.TYPE);
        op(var0, NativeType.SLONGLONG, "LongLong", Long.TYPE);
        op(var0, NativeType.ULONGLONG, "LongLong", Long.TYPE);
        op(var0, NativeType.FLOAT, "Float", Float.TYPE);
        op(var0, NativeType.DOUBLE, "Double", Double.TYPE);
        op(var0, NativeType.ADDRESS, "Address", Long.TYPE);
        pointerOperations = Collections.unmodifiableMap(var0);
        POINTER_OP_POINTER = new VariableAccessorGenerator_PointerOp("Pointer", Pointer.class, null);
    }

  public VariableAccessorGenerator(Runtime arg0) { // было: <init>
        super();
        nextClassID = new AtomicLong(0L);
        runtime = arg0;
    }

  public void generate(AsmBuilder arg0, Class arg1, String arg2, long arg3, Class arg4, Collection arg5, SignatureTypeMapper arg6, AsmClassLoader arg7) {
        FromNativeConverter __stk1;
        ToNativeConverter __stk2;
        if (NativeLibraryLoader.ASM_ENABLED) {
            SimpleNativeContext var10 = new SimpleNativeContext(arg0.getRuntime(), arg5);
            DefaultSignatureType var11 = DefaultSignatureType.create(arg4, var10);
            FromNativeType var12 = arg6.getFromNativeType(var11, var10);
            __stk1 = var12 == null ? null : var12.getFromNativeConverter();
            FromNativeConverter var13 = __stk1;
            ToNativeType var14 = arg6.getToNativeType(var11, var10);
            __stk2 = var14 == null ? null : var14.getToNativeConverter();
            ToNativeConverter var15 = __stk2;
            Variable var16 = buildVariableAccessor(arg0.getRuntime(), arg3, arg1, arg4, arg5, ((ToNativeConverter) var15), ((FromNativeConverter) var13), arg7);
            SkinnyMethodAdapter var17 = new SkinnyMethodAdapter(arg0.getClassVisitor(), 17, arg2, CodegenUtils.sig(Variable.class, new Class[0]), null, null);
            var17.start();
            var17.aload(0);
            var17.getfield(arg0.getClassNamePath(), arg0.getVariableName(var16), CodegenUtils.ci(Variable.class));
            var17.areturn();
            var17.visitMaxs(10, 10);
            var17.visitEnd();
            return;
        } else {
            throw new UnsupportedOperationException("asm bytecode generation not supported");
        }
    }

   Variable buildVariableAccessor(Runtime arg0, long arg1, Class arg2, Class arg3, Collection arg4, ToNativeConverter arg5, FromNativeConverter arg6, AsmClassLoader arg7) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #92 // jnr.ffi.provider.jffi.AsmLibraryLoader.DEBUG:Z
        //      3: ifeq  20 (offset +17)
        //      6: aload  6
        //      8: ldc  #60 // jnr.ffi.provider.jffi.NoTrace
        //     10: invokestatic  #140 // jnr.ffi.provider.jffi.InvokerUtil.hasAnnotation:(Ljava/util/Collection;Ljava/lang/Class;)Z
        //     13: ifne  20 (offset +7)
        //     16: iconst_1
        //     17: goto  21 (offset +4)
        //     20: iconst_0
        //     21: istore  10
        //     23: new  #70 // org.objectweb.asm.ClassWriter
        //     26: dup
        //     27: iconst_2
        //     28: invokespecial  #166 // org.objectweb.asm.ClassWriter.<init>:(I)V
        //     31: astore  11
        //     33: iload  10
        //     35: ifeq  46 (offset +11)
        //     38: aload  11
        //     40: invokestatic  #134 // jnr.ffi.provider.jffi.AsmUtil.newCheckClassAdapter:(Lorg/objectweb/asm/ClassVisitor;)Lorg/objectweb/asm/ClassVisitor;
        //     43: goto  48 (offset +5)
        //     46: aload  11
        //     48: astore  12
        //     50: new  #52 // jnr.ffi.provider.jffi.AsmBuilder
        //     53: dup
        //     54: aload_1
        //     55: new  #31 // java.lang.StringBuilder
        //     58: dup
        //     59: invokespecial  #106 // java.lang.StringBuilder.<init>:()V
        //     62: aload  4
        //     64: invokestatic  #137 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     67: invokevirtual  #109 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     70: ldc  #1 // '$VariableAccessor$$'
        //     72: invokevirtual  #109 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     75: aload_0
        //     76: getfield  #95 // jnr.ffi.provider.jffi.VariableAccessorGenerator.nextClassID:Ljava/util/concurrent/atomic/AtomicLong;
        //     79: invokevirtual  #116 // java.util.concurrent.atomic.AtomicLong.getAndIncrement:()J
        //     82: invokevirtual  #107 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //     85: invokevirtual  #110 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     88: aload  12
        //     90: aload  9
        //     92: invokespecial  #123 // jnr.ffi.provider.jffi.AsmBuilder.<init>:(Ljnr/ffi/Runtime;Ljava/lang/String;Lorg/objectweb/asm/ClassVisitor;Ljnr/ffi/provider/jffi/AsmClassLoader;)V
        //     95: astore  13
        //     97: aload  12
        //     99: bipush  52
        //    101: bipush  17
        //    103: aload  13
        //    105: invokevirtual  #125 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    108: aconst_null
        //    109: ldc  #27 // java.lang.Object
        //    111: invokestatic  #137 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    114: iconst_1
        //    115: anewarray  #30 // java.lang.String
        //    118: dup
        //    119: iconst_0
        //    120: ldc  #44 // jnr.ffi.Variable
        //    122: invokestatic  #137 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    125: aastore
        //    126: invokevirtual  #164 // org.objectweb.asm.ClassVisitor.visit:(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    129: new  #62 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    132: dup
        //    133: aload  13
        //    135: invokevirtual  #126 // jnr.ffi.provider.jffi.AsmBuilder.getClassVisitor:()Lorg/objectweb/asm/ClassVisitor;
        //    138: bipush  17
        //    140: ldc  #17 // 'set'
        //    142: getstatic  #78 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    145: iconst_1
        //    146: anewarray  #21 // java.lang.Class
        //    149: dup
        //    150: iconst_0
        //    151: ldc  #27 // java.lang.Object
        //    153: aastore
        //    154: invokestatic  #138 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    157: aconst_null
        //    158: aconst_null
        //    159: invokespecial  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    162: astore  14
        //    164: aload  7
        //    166: ifnull  179 (offset +13)
        //    169: aload  7
        //    171: invokeinterface  #173 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //    176: goto  181 (offset +5)
        //    179: aload  5
        //    181: astore  15
        //    183: aload_1
        //    184: aload  15
        //    186: aload  6
        //    188: invokestatic  #156 // jnr.ffi.provider.jffi.Types.getType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/Type;
        //    191: invokevirtual  #117 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //    194: astore  16
        //    196: new  #51 // jnr.ffi.provider.ToNativeType
        //    199: dup
        //    200: aload  5
        //    202: aload  16
        //    204: aload  6
        //    206: aload  7
        //    208: aconst_null
        //    209: invokespecial  #120 // jnr.ffi.provider.ToNativeType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)V
        //    212: astore  17
        //    214: new  #50 // jnr.ffi.provider.FromNativeType
        //    217: dup
        //    218: aload  5
        //    220: aload  16
        //    222: aload  6
        //    224: aload  8
        //    226: aconst_null
        //    227: invokespecial  #119 // jnr.ffi.provider.FromNativeType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/FromNativeConverter;Ljnr/ffi/mapper/FromNativeContext;)V
        //    230: astore  18
        //    232: getstatic  #96 // jnr.ffi.provider.jffi.VariableAccessorGenerator.pointerOperations:Ljava/util/Map;
        //    235: aload  16
        //    237: invokeinterface  #168 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    242: checkcast  #67 // jnr.ffi.provider.jffi.VariableAccessorGenerator$PointerOp
        //    245: astore  19
        //    247: aload  19
        //    249: ifnonnull  280 (offset +31)
        //    252: new  #24 // java.lang.IllegalArgumentException
        //    255: dup
        //    256: new  #31 // java.lang.StringBuilder
        //    259: dup
        //    260: invokespecial  #106 // java.lang.StringBuilder.<init>:()V
        //    263: ldc  #16 // 'global variable type not supported: '
        //    265: invokevirtual  #109 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    268: aload  5
        //    270: invokevirtual  #108 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    273: invokevirtual  #110 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    276: invokespecial  #102 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    279: athrow
        //    280: aload  14
        //    282: invokevirtual  #149 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //    285: aload  14
        //    287: iconst_0
        //    288: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    291: aload_1
        //    292: lload_2
        //    293: invokestatic  #139 // jnr.ffi.provider.jffi.DirectMemoryIO.wrap:(Ljnr/ffi/Runtime;J)Ljnr/ffi/Pointer;
        //    296: astore  20
        //    298: aload  14
        //    300: aload  13
        //    302: invokevirtual  #125 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    305: aload  13
        //    307: aload  20
        //    309: ldc  #42 // jnr.ffi.Pointer
        //    311: invokevirtual  #127 // jnr.ffi.provider.jffi.AsmBuilder.getObjectFieldName:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/String;
        //    314: ldc  #42 // jnr.ffi.Pointer
        //    316: invokestatic  #136 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    319: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    322: aload  14
        //    324: invokevirtual  #148 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lconst_0:()V
        //    327: aload  14
        //    329: iconst_1
        //    330: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    333: aload  14
        //    335: aload  5
        //    337: invokevirtual  #145 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/Class;)V
        //    340: aload  13
        //    342: aload  14
        //    344: aload  17
        //    346: invokestatic  #133 // jnr.ffi.provider.jffi.AsmUtil.emitToNativeConversion:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ToNativeType;)V
        //    349: aload  17
        //    351: invokestatic  #154 // jnr.ffi.provider.jffi.ToNativeOp.get:(Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/ToNativeOp;
        //    354: astore  21
        //    356: aload  21
        //    358: ifnull  389 (offset +31)
        //    361: aload  21
        //    363: invokevirtual  #155 // jnr.ffi.provider.jffi.ToNativeOp.isPrimitive:()Z
        //    366: ifeq  389 (offset +23)
        //    369: aload  21
        //    371: aload  14
        //    373: aload  19
        //    375: getfield  #98 // jnr.ffi.provider.jffi.VariableAccessorGenerator$PointerOp.nativeIntClass:Ljava/lang/Class;
        //    378: aload  17
        //    380: invokevirtual  #122 // jnr.ffi.provider.ToNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    383: invokevirtual  #153 // jnr.ffi.provider.jffi.ToNativeOp.emitPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    386: goto  438 (offset +52)
        //    389: ldc  #42 // jnr.ffi.Pointer
        //    391: aload  17
        //    393: invokevirtual  #121 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    396: invokevirtual  #101 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    399: ifeq  410 (offset +11)
        //    402: getstatic  #94 // jnr.ffi.provider.jffi.VariableAccessorGenerator.POINTER_OP_POINTER:Ljnr/ffi/provider/jffi/VariableAccessorGenerator$PointerOp;
        //    405: astore  19
        //    407: goto  438 (offset +31)
        //    410: new  #24 // java.lang.IllegalArgumentException
        //    413: dup
        //    414: new  #31 // java.lang.StringBuilder
        //    417: dup
        //    418: invokespecial  #106 // java.lang.StringBuilder.<init>:()V
        //    421: ldc  #16 // 'global variable type not supported: '
        //    423: invokevirtual  #109 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    426: aload  5
        //    428: invokevirtual  #108 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    431: invokevirtual  #110 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    434: invokespecial  #102 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    437: athrow
        //    438: aload  19
        //    440: aload  14
        //    442: invokevirtual  #161 // jnr.ffi.provider.jffi.VariableAccessorGenerator$PointerOp.put:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;)V
        //    445: aload  14
        //    447: invokevirtual  #152 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.voidreturn:()V
        //    450: aload  14
        //    452: bipush  10
        //    454: bipush  10
        //    456: invokevirtual  #151 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    459: aload  14
        //    461: invokevirtual  #150 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    464: new  #62 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    467: dup
        //    468: aload  13
        //    470: invokevirtual  #126 // jnr.ffi.provider.jffi.AsmBuilder.getClassVisitor:()Lorg/objectweb/asm/ClassVisitor;
        //    473: bipush  17
        //    475: ldc  #15 // 'get'
        //    477: ldc  #27 // java.lang.Object
        //    479: iconst_0
        //    480: anewarray  #21 // java.lang.Class
        //    483: invokestatic  #138 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    486: aconst_null
        //    487: aconst_null
        //    488: invokespecial  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    491: astore  22
        //    493: aload  22
        //    495: invokevirtual  #149 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //    498: aload  22
        //    500: iconst_0
        //    501: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    504: aload  22
        //    506: aload  13
        //    508: invokevirtual  #125 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    511: aload  13
        //    513: aload  20
        //    515: ldc  #42 // jnr.ffi.Pointer
        //    517: invokevirtual  #127 // jnr.ffi.provider.jffi.AsmBuilder.getObjectFieldName:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/String;
        //    520: ldc  #42 // jnr.ffi.Pointer
        //    522: invokestatic  #136 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    525: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    528: aload  22
        //    530: invokevirtual  #148 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lconst_0:()V
        //    533: aload  19
        //    535: aload  22
        //    537: invokevirtual  #160 // jnr.ffi.provider.jffi.VariableAccessorGenerator$PointerOp.get:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;)V
        //    540: aload  13
        //    542: aload  22
        //    544: aload  18
        //    546: aload  19
        //    548: getfield  #98 // jnr.ffi.provider.jffi.VariableAccessorGenerator$PointerOp.nativeIntClass:Ljava/lang/Class;
        //    551: invokestatic  #132 // jnr.ffi.provider.jffi.AsmUtil.emitFromNativeConversion:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/FromNativeType;Ljava/lang/Class;)V
        //    554: aload  22
        //    556: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.areturn:()V
        //    559: aload  22
        //    561: bipush  10
        //    563: bipush  10
        //    565: invokevirtual  #151 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    568: aload  22
        //    570: invokevirtual  #150 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    573: new  #62 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    576: dup
        //    577: aload  12
        //    579: iconst_1
        //    580: ldc  #4 // '<init>'
        //    582: getstatic  #78 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    585: iconst_1
        //    586: anewarray  #21 // java.lang.Class
        //    589: dup
        //    590: iconst_0
        //    591: ldc  #18 // [Ljava.lang.Object;
        //    593: aastore
        //    594: invokestatic  #138 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    597: aconst_null
        //    598: aconst_null
        //    599: invokespecial  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    602: astore  23
        //    604: aload  23
        //    606: invokevirtual  #149 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //    609: aload  23
        //    611: iconst_0
        //    612: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    615: aload  23
        //    617: ldc  #27 // java.lang.Object
        //    619: invokestatic  #137 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    622: ldc  #4 // '<init>'
        //    624: getstatic  #78 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    627: iconst_0
        //    628: anewarray  #21 // java.lang.Class
        //    631: invokestatic  #138 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    634: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokespecial:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    637: aload  13
        //    639: aload  23
        //    641: iconst_1
        //    642: invokevirtual  #124 // jnr.ffi.provider.jffi.AsmBuilder.emitFieldInitialization:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;I)V
        //    645: aload  23
        //    647: invokevirtual  #152 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.voidreturn:()V
        //    650: aload  23
        //    652: bipush  10
        //    654: bipush  10
        //    656: invokevirtual  #151 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    659: aload  23
        //    661: invokevirtual  #150 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    664: aload  12
        //    666: invokevirtual  #165 // org.objectweb.asm.ClassVisitor.visitEnd:()V
        //    669: aload  11
        //    671: invokevirtual  #167 // org.objectweb.asm.ClassWriter.toByteArray:()[B
        //    674: astore  24
        //    676: iload  10
        //    678: ifeq  711 (offset +33)
        //    681: new  #19 // java.io.PrintWriter
        //    684: dup
        //    685: getstatic  #77 // java.lang.System.err:Ljava/io/PrintStream;
        //    688: invokespecial  #99 // java.io.PrintWriter.<init>:(Ljava/io/OutputStream;)V
        //    691: invokestatic  #135 // jnr.ffi.provider.jffi.AsmUtil.newTraceClassVisitor:(Ljava/io/PrintWriter;)Lorg/objectweb/asm/ClassVisitor;
        //    694: astore  25
        //    696: new  #68 // org.objectweb.asm.ClassReader
        //    699: dup
        //    700: aload  24
        //    702: invokespecial  #162 // org.objectweb.asm.ClassReader.<init>:([B)V
        //    705: aload  25
        //    707: iconst_0
        //    708: invokevirtual  #163 // org.objectweb.asm.ClassReader.accept:(Lorg/objectweb/asm/ClassVisitor;I)V
        //    711: aload  9
        //    713: aload  13
        //    715: invokevirtual  #125 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    718: ldc  #3 // '/'
        //    720: ldc  #2 // '.'
        //    722: invokevirtual  #105 // java.lang.String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    725: aload  24
        //    727: invokevirtual  #131 // jnr.ffi.provider.jffi.AsmClassLoader.defineClass:(Ljava/lang/String;[B)Ljava/lang/Class;
        //    730: astore  25
        //    732: aload  25
        //    734: iconst_1
        //    735: anewarray  #21 // java.lang.Class
        //    738: dup
        //    739: iconst_0
        //    740: ldc  #18 // [Ljava.lang.Object;
        //    742: aastore
        //    743: invokevirtual  #100 // java.lang.Class.getDeclaredConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
        //    746: astore  26
        //    748: aload  26
        //    750: iconst_1
        //    751: anewarray  #27 // java.lang.Object
        //    754: dup
        //    755: iconst_0
        //    756: aload  13
        //    758: invokevirtual  #128 // jnr.ffi.provider.jffi.AsmBuilder.getObjectFieldValues:()[Ljava/lang/Object;
        //    761: aastore
        //    762: invokevirtual  #112 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    765: checkcast  #44 // jnr.ffi.Variable
        //    768: areturn
        //    769: astore  24
        //    771: new  #28 // java.lang.RuntimeException
        //    774: dup
        //    775: aload  24
        //    777: invokespecial  #104 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    780: athrow
        //       Exception table:
        //         from 669 to 768 target 769 type java.lang.Throwable
    }

  private static void op(Map arg0, NativeType arg1, String arg2, Class arg3) {
        arg0.put(arg1, new VariableAccessorGenerator_PointerOp(arg2, arg3, null));
    }

}