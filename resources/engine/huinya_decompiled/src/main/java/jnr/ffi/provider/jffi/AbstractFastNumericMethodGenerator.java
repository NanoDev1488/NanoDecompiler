// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.ObjectParameterInfo;
import com.kenai.jffi.ObjectParameterStrategy;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import jnr.ffi.Pointer;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.BaseMethodGenerator;
import jnr.ffi.provider.jffi.BufferParameterStrategy;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LocalVariable;
import jnr.ffi.provider.jffi.LocalVariableAllocator;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.ParameterStrategy;
import jnr.ffi.provider.jffi.PointerParameterStrategy;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;

abstract class AbstractFastNumericMethodGenerator extends BaseMethodGenerator {

    // ---- поля ----
  static final Map STRATEGY_ADDRESS_METHODS;
  static final Map STRATEGY_PARAMETER_TYPES;

    static {
        HashMap var0 = new HashMap();
        addStrategyParameterType(var0, BufferParameterStrategy.class, Buffer.class);
        addStrategyParameterType(var0, PointerParameterStrategy.class, Pointer.class);
        STRATEGY_ADDRESS_METHODS = Collections.unmodifiableMap(var0);
        LinkedHashMap var1 = new LinkedHashMap();
        var1.put(Pointer.class, PointerParameterStrategy.class);
        Class[] var2 = new Class[]{ByteBuffer.class, CharBuffer.class, ShortBuffer.class, IntBuffer.class, LongBuffer.class, FloatBuffer.class, DoubleBuffer.class, Buffer.class};
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1.put(var5, BufferParameterStrategy.class);
            ++var4;
            continue;
        }
        Class[] __obj2 = new Class[8];
        __obj2[0] = byte[].class;
        __obj2[1] = short[].class;
        __obj2[2] = char[].class;
        __obj2[3] = int[].class;
        __obj2[4] = long[].class;
        __obj2[5] = float[].class;
        __obj2[6] = double[].class;
        __obj2[7] = boolean[].class;
        var2 = __obj2;
        var3 = var2.length;
        var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            var1.put(var5, ParameterStrategy.class);
            ++var4;
            continue;
        }
        STRATEGY_PARAMETER_TYPES = Collections.unmodifiableMap(var1);
    }

   AbstractFastNumericMethodGenerator() { // было: <init>
        super();
    }

  public void generate(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, CallContext arg3, ResultType arg4, ParameterType[] arg5, boolean arg6) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #114 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getInvokerType:()Ljava/lang/Class;
        //      4: astore  8
        //      6: aload_3
        //      7: getstatic  #73 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //     10: invokevirtual  #127 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //     13: astore  9
        //     15: aload  6
        //     17: invokestatic  #122 // jnr.ffi.provider.jffi.AsmUtil.getParameterVariables:([Ljnr/ffi/provider/ParameterType;)[Ljnr/ffi/provider/jffi/LocalVariable;
        //     20: astore  10
        //     22: aload  6
        //     24: arraylength
        //     25: anewarray  #62 // jnr.ffi.provider.jffi.LocalVariable
        //     28: astore  11
        //     30: iconst_0
        //     31: istore  12
        //     33: iconst_0
        //     34: istore  13
        //     36: iload  13
        //     38: aload  6
        //     40: arraylength
        //     41: if_icmpge  189 (offset +148)
        //     44: aload  11
        //     46: iload  13
        //     48: aload_1
        //     49: aload_2
        //     50: aload_3
        //     51: aload  10
        //     53: iload  13
        //     55: aaload
        //     56: aload  6
        //     58: iload  13
        //     60: aaload
        //     61: invokestatic  #118 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.loadAndConvertParameter:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/LocalVariableAllocator;Ljnr/ffi/provider/jffi/LocalVariable;Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/LocalVariable;
        //     64: aastore
        //     65: aload  6
        //     67: iload  13
        //     69: aaload
        //     70: invokevirtual  #103 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //     73: astore  14
        //     75: aload  6
        //     77: iload  13
        //     79: aaload
        //     80: invokestatic  #150 // jnr.ffi.provider.jffi.ToNativeOp.get:(Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/ToNativeOp;
        //     83: astore  15
        //     85: aload  15
        //     87: ifnull  119 (offset +32)
        //     90: aload  15
        //     92: invokevirtual  #151 // jnr.ffi.provider.jffi.ToNativeOp.isPrimitive:()Z
        //     95: ifeq  119 (offset +24)
        //     98: aload  15
        //    100: aload_2
        //    101: aload_0
        //    102: invokevirtual  #114 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getInvokerType:()Ljava/lang/Class;
        //    105: aload  6
        //    107: iload  13
        //    109: aaload
        //    110: invokevirtual  #105 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    113: invokevirtual  #149 // jnr.ffi.provider.jffi.ToNativeOp.emitPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    116: goto  183 (offset +67)
        //    119: aload  14
        //    121: invokestatic  #117 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.hasPointerParameterStrategy:(Ljava/lang/Class;)Z
        //    124: ifeq  149 (offset +25)
        //    127: aload_2
        //    128: aload  14
        //    130: aload  8
        //    132: aload  11
        //    134: iload  13
        //    136: aaload
        //    137: aload  9
        //    139: iload  12
        //    141: invokestatic  #109 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitDirectCheck:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/provider/jffi/LocalVariable;Ljnr/ffi/provider/jffi/LocalVariable;I)I
        //    144: istore  12
        //    146: goto  183 (offset +37)
        //    149: new  #27 // java.lang.IllegalArgumentException
        //    152: dup
        //    153: new  #33 // java.lang.StringBuilder
        //    156: dup
        //    157: invokespecial  #86 // java.lang.StringBuilder.<init>:()V
        //    160: ldc  #9 // 'unsupported parameter type '
        //    162: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    165: aload  6
        //    167: iload  13
        //    169: aaload
        //    170: invokevirtual  #104 // jnr.ffi.provider.ParameterType.getDeclaredType:()Ljava/lang/Class;
        //    173: invokevirtual  #89 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    176: invokevirtual  #91 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    179: invokespecial  #84 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    182: athrow
        //    183: iinc  13, 1
        //    186: goto  36 (offset -150)
        //    189: new  #69 // org.objectweb.asm.Label
        //    192: dup
        //    193: invokespecial  #152 // org.objectweb.asm.Label.<init>:()V
        //    196: astore  13
        //    198: new  #69 // org.objectweb.asm.Label
        //    201: dup
        //    202: invokespecial  #152 // org.objectweb.asm.Label.<init>:()V
        //    205: astore  14
        //    207: iload  12
        //    209: ifle  224 (offset +15)
        //    212: aload_2
        //    213: aload  9
        //    215: invokevirtual  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    218: aload_2
        //    219: aload  13
        //    221: invokevirtual  #138 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.ifne:(Lorg/objectweb/asm/Label;)V
        //    224: aload_2
        //    225: ldc  #20 // com.kenai.jffi.Invoker
        //    227: invokestatic  #126 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    230: aload_0
        //    231: aload  5
        //    233: aload  6
        //    235: iload  7
        //    237: invokevirtual  #112 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getInvokerMethodName:(Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Z)Ljava/lang/String;
        //    240: aload_0
        //    241: aload  6
        //    243: arraylength
        //    244: aload  8
        //    246: invokevirtual  #113 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getInvokerSignature:(ILjava/lang/Class;)Ljava/lang/String;
        //    249: invokevirtual  #145 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    252: iload  12
        //    254: ifle  263 (offset +9)
        //    257: aload_2
        //    258: aload  14
        //    260: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    263: aload  5
        //    265: invokevirtual  #106 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    268: astore  15
        //    270: aload  8
        //    272: astore  16
        //    274: ldc  #26 // java.lang.Float
        //    276: aload  15
        //    278: if_acmpeq  289 (offset +11)
        //    281: getstatic  #72 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    284: aload  15
        //    286: if_acmpne  327 (offset +41)
        //    289: aload_2
        //    290: aload  8
        //    292: getstatic  #73 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    295: invokestatic  #129 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    298: aload_2
        //    299: ldc  #26 // java.lang.Float
        //    301: ldc  #3 // 'intBitsToFloat'
        //    303: getstatic  #72 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    306: iconst_1
        //    307: anewarray  #24 // java.lang.Class
        //    310: dup
        //    311: iconst_0
        //    312: getstatic  #73 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    315: aastore
        //    316: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    319: getstatic  #72 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    322: astore  16
        //    324: goto  377 (offset +53)
        //    327: ldc  #25 // java.lang.Double
        //    329: aload  15
        //    331: if_acmpeq  342 (offset +11)
        //    334: getstatic  #71 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    337: aload  15
        //    339: if_acmpne  377 (offset +38)
        //    342: aload_2
        //    343: aload  8
        //    345: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    348: invokestatic  #130 // jnr.ffi.provider.jffi.NumberUtil.widen:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    351: aload_2
        //    352: ldc  #25 // java.lang.Double
        //    354: ldc  #6 // 'longBitsToDouble'
        //    356: getstatic  #71 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    359: iconst_1
        //    360: anewarray  #24 // java.lang.Class
        //    363: dup
        //    364: iconst_0
        //    365: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    368: aastore
        //    369: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    372: getstatic  #71 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    375: astore  16
        //    377: aload  15
        //    379: invokestatic  #123 // jnr.ffi.provider.jffi.AsmUtil.unboxedReturnType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    382: astore  17
        //    384: aload_2
        //    385: aload  16
        //    387: aload  17
        //    389: aload  5
        //    391: invokevirtual  #107 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    394: invokestatic  #128 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    397: aload_1
        //    398: aload_2
        //    399: aload  5
        //    401: aload  6
        //    403: aload  10
        //    405: aload  11
        //    407: aconst_null
        //    408: invokestatic  #110 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitEpilogue:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;[Ljnr/ffi/provider/jffi/LocalVariable;[Ljnr/ffi/provider/jffi/LocalVariable;Ljava/lang/Runnable;)V
        //    411: iload  12
        //    413: ifle  696 (offset +283)
        //    416: aload_2
        //    417: aload  13
        //    419: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    422: getstatic  #73 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    425: aload  8
        //    427: if_acmpne  517 (offset +90)
        //    430: aload  6
        //    432: arraylength
        //    433: anewarray  #62 // jnr.ffi.provider.jffi.LocalVariable
        //    436: astore  18
        //    438: aload  6
        //    440: arraylength
        //    441: iconst_1
        //    442: isub
        //    443: istore  19
        //    445: iload  19
        //    447: ifle  477 (offset +30)
        //    450: aload  18
        //    452: iload  19
        //    454: aload_3
        //    455: getstatic  #73 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    458: invokevirtual  #127 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    461: aastore
        //    462: aload_2
        //    463: aload  18
        //    465: iload  19
        //    467: aaload
        //    468: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.istore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    471: iinc  19, -1
        //    474: goto  445 (offset -29)
        //    477: aload  6
        //    479: arraylength
        //    480: ifle  487 (offset +7)
        //    483: aload_2
        //    484: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.i2l:()V
        //    487: iconst_1
        //    488: istore  19
        //    490: iload  19
        //    492: aload  6
        //    494: arraylength
        //    495: if_icmpge  517 (offset +22)
        //    498: aload_2
        //    499: aload  18
        //    501: iload  19
        //    503: aaload
        //    504: invokevirtual  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    507: aload_2
        //    508: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.i2l:()V
        //    511: iinc  19, 1
        //    514: goto  490 (offset -24)
        //    517: aload_2
        //    518: aload  9
        //    520: invokevirtual  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    523: aload  6
        //    525: arraylength
        //    526: anewarray  #62 // jnr.ffi.provider.jffi.LocalVariable
        //    529: astore  18
        //    531: iconst_0
        //    532: istore  19
        //    534: iload  19
        //    536: aload  6
        //    538: arraylength
        //    539: if_icmpge  658 (offset +119)
        //    542: aload  6
        //    544: iload  19
        //    546: aaload
        //    547: invokevirtual  #103 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //    550: astore  20
        //    552: aload  20
        //    554: invokestatic  #117 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.hasPointerParameterStrategy:(Ljava/lang/Class;)Z
        //    557: ifeq  652 (offset +95)
        //    560: aload_2
        //    561: aload  11
        //    563: iload  19
        //    565: aaload
        //    566: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    569: aload_2
        //    570: aload  20
        //    572: invokestatic  #111 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitParameterStrategyLookup:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)Ljava/lang/Class;
        //    575: pop
        //    576: aload_2
        //    577: aload  18
        //    579: iload  19
        //    581: aload_3
        //    582: ldc  #65 // jnr.ffi.provider.jffi.ParameterStrategy
        //    584: invokevirtual  #127 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    587: dup_x2
        //    588: aastore
        //    589: invokevirtual  #133 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.astore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    592: aload_2
        //    593: aload  11
        //    595: iload  19
        //    597: aaload
        //    598: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    601: aload_2
        //    602: aload  18
        //    604: iload  19
        //    606: aaload
        //    607: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    610: aload_2
        //    611: iconst_0
        //    612: invokevirtual  #131 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    615: iload  19
        //    617: aload  6
        //    619: iload  19
        //    621: aaload
        //    622: invokevirtual  #102 // jnr.ffi.provider.ParameterType.annotations:()Ljava/util/Collection;
        //    625: invokestatic  #121 // jnr.ffi.provider.jffi.AsmUtil.getNativeArrayFlags:(Ljava/util/Collection;)I
        //    628: invokestatic  #77 // com.kenai.jffi.ObjectParameterInfo.create:(II)Lcom/kenai/jffi/ObjectParameterInfo;
        //    631: astore  21
        //    633: aload_2
        //    634: aload_1
        //    635: invokevirtual  #119 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    638: aload_1
        //    639: aload  21
        //    641: invokevirtual  #120 // jnr.ffi.provider.jffi.AsmBuilder.getObjectParameterInfoName:(Lcom/kenai/jffi/ObjectParameterInfo;)Ljava/lang/String;
        //    644: ldc  #21 // com.kenai.jffi.ObjectParameterInfo
        //    646: invokestatic  #125 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    649: invokevirtual  #134 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    652: iinc  19, 1
        //    655: goto  534 (offset -121)
        //    658: aload_2
        //    659: ldc  #20 // com.kenai.jffi.Invoker
        //    661: invokestatic  #126 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    664: aload  6
        //    666: arraylength
        //    667: invokestatic  #115 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getObjectParameterMethodName:(I)Ljava/lang/String;
        //    670: aload  6
        //    672: arraylength
        //    673: iload  12
        //    675: invokestatic  #116 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getObjectParameterMethodSignature:(II)Ljava/lang/String;
        //    678: invokevirtual  #145 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    681: aload_2
        //    682: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    685: aload  8
        //    687: invokestatic  #129 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    690: aload_2
        //    691: aload  14
        //    693: invokevirtual  #135 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.go_to:(Lorg/objectweb/asm/Label;)V
        //    696: return
    }

  private static void addStrategyParameterType(Map arg0, Class arg1, Class arg2) {
        try {
            Method var3 = arg1.getDeclaredMethod("address", new Class[]{arg2});
            if (Modifier.isPublic(var3.getModifiers())) {
                if (Modifier.isPublic(var3.getDeclaringClass().getModifiers())) {
                    arg0.put(arg1, var3);
                }
            }
            return;
        } catch (NoSuchMethodException e1) {
            Throwable var3 = e1;
        }
    }

  static boolean hasPointerParameterStrategy(Class arg0) {
        Iterator var1 = STRATEGY_PARAMETER_TYPES.keySet().iterator();
        while (true) {
            if (!var1.hasNext()) {
                return false;
            }
            Class var2 = ((Class) var1.next());
            if (var2.isAssignableFrom(arg0)) {
                break;
            }
            continue;
        }
        return true;
    }

  static Class emitParameterStrategyLookup(SkinnyMethodAdapter arg0, Class arg1) {
        Iterator var2 = STRATEGY_PARAMETER_TYPES.entrySet().iterator();
        Entry var3;
        while (true) {
            if (!var2.hasNext()) {
                throw new RuntimeException(new StringBuilder().append("no conversion strategy for: ").append(arg1).toString());
            }
            var3 = ((Entry) var2.next());
            if ((((Class) var3.getKey())).isAssignableFrom(arg1)) {
                break;
            }
            continue;
        }
        arg0.invokestatic(AsmRuntime.class, "pointerParameterStrategy", ((Class) var3.getValue()), new Class[]{((Class) var3.getKey())});
        return ((Class) var3.getValue());
    }

  static void emitParameterStrategyAddress(SkinnyMethodAdapter arg0, Class arg1, Class arg2, LocalVariable arg3, LocalVariable arg4) {
        arg0.aload(arg3);
        arg0.aload(arg4);
        Method var5 = ((Method) STRATEGY_ADDRESS_METHODS.get(arg2));
        if (var5 == null) {
            arg0.invokevirtual(PointerParameterStrategy.class, "address", Long.TYPE, new Class[]{Object.class});
        } else {
            arg0.invokevirtual(arg2, var5.getName(), var5.getReturnType(), var5.getParameterTypes());
        }
        NumberUtil.narrow(arg0, Long.TYPE, arg1);
    }

  static int emitDirectCheck(SkinnyMethodAdapter arg0, Class arg1, Class arg2, LocalVariable arg3, LocalVariable arg4, int arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload  5
        //      2: iconst_1
        //      3: if_icmpge  16 (offset +13)
        //      6: aload_0
        //      7: invokevirtual  #137 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iconst_0:()V
        //     10: aload_0
        //     11: aload  4
        //     13: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.istore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //     16: new  #69 // org.objectweb.asm.Label
        //     19: dup
        //     20: invokespecial  #152 // org.objectweb.asm.Label.<init>:()V
        //     23: astore  6
        //     25: new  #69 // org.objectweb.asm.Label
        //     28: dup
        //     29: invokespecial  #152 // org.objectweb.asm.Label.<init>:()V
        //     32: astore  7
        //     34: aload_0
        //     35: aload  7
        //     37: invokevirtual  #139 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.ifnull:(Lorg/objectweb/asm/Label;)V
        //     40: ldc  #52 // jnr.ffi.Pointer
        //     42: aload_1
        //     43: invokevirtual  #82 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     46: ifeq  106 (offset +60)
        //     49: aload_0
        //     50: aload_3
        //     51: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //     54: aload_0
        //     55: ldc  #52 // jnr.ffi.Pointer
        //     57: ldc  #2 // 'address'
        //     59: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //     62: iconst_0
        //     63: anewarray  #24 // java.lang.Class
        //     66: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     69: aload_0
        //     70: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //     73: aload_2
        //     74: invokestatic  #129 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //     77: aload_0
        //     78: aload_3
        //     79: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //     82: aload_0
        //     83: ldc  #52 // jnr.ffi.Pointer
        //     85: ldc  #5 // 'isDirect'
        //     87: getstatic  #70 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //     90: iconst_0
        //     91: anewarray  #24 // java.lang.Class
        //     94: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     97: aload_0
        //     98: aload  6
        //    100: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iftrue:(Lorg/objectweb/asm/Label;)V
        //    103: goto  242 (offset +139)
        //    106: ldc  #37 // java.nio.Buffer
        //    108: aload_1
        //    109: invokevirtual  #82 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    112: ifeq  177 (offset +65)
        //    115: aload_0
        //    116: aload_3
        //    117: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    120: aload_0
        //    121: ldc  #60 // jnr.ffi.provider.jffi.BufferParameterStrategy
        //    123: ldc  #2 // 'address'
        //    125: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    128: iconst_1
        //    129: anewarray  #24 // java.lang.Class
        //    132: dup
        //    133: iconst_0
        //    134: ldc  #37 // java.nio.Buffer
        //    136: aastore
        //    137: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    140: aload_0
        //    141: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    144: aload_2
        //    145: invokestatic  #129 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    148: aload_0
        //    149: aload_3
        //    150: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    153: aload_0
        //    154: ldc  #37 // java.nio.Buffer
        //    156: ldc  #5 // 'isDirect'
        //    158: getstatic  #70 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //    161: iconst_0
        //    162: anewarray  #24 // java.lang.Class
        //    165: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    168: aload_0
        //    169: aload  6
        //    171: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iftrue:(Lorg/objectweb/asm/Label;)V
        //    174: goto  242 (offset +68)
        //    177: aload_1
        //    178: invokevirtual  #81 // java.lang.Class.isArray:()Z
        //    181: ifeq  215 (offset +34)
        //    184: aload_1
        //    185: invokevirtual  #78 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    188: invokevirtual  #83 // java.lang.Class.isPrimitive:()Z
        //    191: ifeq  215 (offset +24)
        //    194: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    197: aload_2
        //    198: if_acmpne  208 (offset +10)
        //    201: aload_0
        //    202: invokevirtual  #148 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lconst_0:()V
        //    205: goto  242 (offset +37)
        //    208: aload_0
        //    209: invokevirtual  #137 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iconst_0:()V
        //    212: goto  242 (offset +30)
        //    215: new  #34 // java.lang.UnsupportedOperationException
        //    218: dup
        //    219: new  #33 // java.lang.StringBuilder
        //    222: dup
        //    223: invokespecial  #86 // java.lang.StringBuilder.<init>:()V
        //    226: ldc  #10 // 'unsupported parameter type: '
        //    228: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    231: aload_1
        //    232: invokevirtual  #89 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    235: invokevirtual  #91 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    238: invokespecial  #92 // java.lang.UnsupportedOperationException.<init>:(Ljava/lang/String;)V
        //    241: athrow
        //    242: aload_0
        //    243: aload  4
        //    245: iconst_1
        //    246: invokevirtual  #141 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iinc:(Ljnr/ffi/provider/jffi/LocalVariable;I)V
        //    249: aload_0
        //    250: aload  6
        //    252: invokevirtual  #135 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.go_to:(Lorg/objectweb/asm/Label;)V
        //    255: aload_0
        //    256: aload  7
        //    258: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    261: getstatic  #74 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    264: aload_2
        //    265: if_acmpne  275 (offset +10)
        //    268: aload_0
        //    269: invokevirtual  #148 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lconst_0:()V
        //    272: goto  279 (offset +7)
        //    275: aload_0
        //    276: invokevirtual  #137 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iconst_0:()V
        //    279: aload_0
        //    280: aload  6
        //    282: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    285: iinc  5, 1
        //    288: iload  5
        //    290: ireturn
    }

  static String getObjectParameterMethodName(int arg0) {
        return new StringBuilder().append("invokeN").append(arg0).toString();
    }

  static String getObjectParameterMethodSignature(int arg0, int arg1) {
        StringBuilder var2 = new StringBuilder();
        var2.append('(').append(CodegenUtils.ci(CallContext.class)).append(CodegenUtils.ci(Long.TYPE));
        int var3 = 0;
        while (var3 < arg0) {
            var2.append('J');
            ++var3;
            continue;
        }
        var2.append('I');
        var3 = 0;
        while (var3 < arg1) {
            var2.append(CodegenUtils.ci(Object.class));
            var2.append(CodegenUtils.ci(ObjectParameterStrategy.class));
            var2.append(CodegenUtils.ci(ObjectParameterInfo.class));
            ++var3;
            continue;
        }
        var2.append(")J");
        return var2.toString();
    }

  abstract String getInvokerMethodName(ResultType arg0, ParameterType[] arg1, boolean arg2);

  abstract String getInvokerSignature(int arg0, Class arg1);

  abstract Class getInvokerType();

}