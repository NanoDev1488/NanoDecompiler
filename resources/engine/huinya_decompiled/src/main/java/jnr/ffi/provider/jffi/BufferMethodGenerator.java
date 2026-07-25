// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BufferMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.HeapInvocationBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.BaseMethodGenerator;
import jnr.ffi.provider.jffi.BufferMethodGenerator_InvokeOp;
import jnr.ffi.provider.jffi.BufferMethodGenerator_MarshalOp;
import jnr.ffi.provider.jffi.LocalVariableAllocator;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.ToNativeOp;

final class BufferMethodGenerator extends BaseMethodGenerator {

    // ---- поля ----
  static final Map marshalOps;
  static final Map invokeOps;

    static {
        EnumMap var0 = new EnumMap(NativeType.class);
        EnumMap var1 = new EnumMap(NativeType.class);
        var0.put(NativeType.SCHAR, new BufferMethodGenerator_MarshalOp("Byte", Integer.TYPE, null));
        var0.put(NativeType.UCHAR, new BufferMethodGenerator_MarshalOp("Byte", Integer.TYPE, null));
        var0.put(NativeType.SSHORT, new BufferMethodGenerator_MarshalOp("Short", Integer.TYPE, null));
        var0.put(NativeType.USHORT, new BufferMethodGenerator_MarshalOp("Short", Integer.TYPE, null));
        var0.put(NativeType.SINT, new BufferMethodGenerator_MarshalOp("Int", Integer.TYPE, null));
        var0.put(NativeType.UINT, new BufferMethodGenerator_MarshalOp("Int", Integer.TYPE, null));
        var0.put(NativeType.SLONGLONG, new BufferMethodGenerator_MarshalOp("Long", Long.TYPE, null));
        var0.put(NativeType.ULONGLONG, new BufferMethodGenerator_MarshalOp("Long", Long.TYPE, null));
        var0.put(NativeType.FLOAT, new BufferMethodGenerator_MarshalOp("Float", Float.TYPE, null));
        var0.put(NativeType.DOUBLE, new BufferMethodGenerator_MarshalOp("Double", Double.TYPE, null));
        var0.put(NativeType.ADDRESS, new BufferMethodGenerator_MarshalOp("Address", Long.TYPE, null));
        if (NumberUtil.sizeof(NativeType.SLONG) != 4) {
            var0.put(NativeType.SLONG, new BufferMethodGenerator_MarshalOp("Long", Long.TYPE, null));
            var0.put(NativeType.ULONG, new BufferMethodGenerator_MarshalOp("Long", Long.TYPE, null));
        } else {
            var0.put(NativeType.SLONG, new BufferMethodGenerator_MarshalOp("Int", Integer.TYPE, null));
            var0.put(NativeType.ULONG, new BufferMethodGenerator_MarshalOp("Int", Integer.TYPE, null));
        }
        var1.put(NativeType.SCHAR, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.UCHAR, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.SSHORT, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.USHORT, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.SINT, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.UINT, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.VOID, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        var1.put(NativeType.SLONGLONG, new BufferMethodGenerator_InvokeOp("Long", Long.TYPE, null));
        var1.put(NativeType.ULONGLONG, new BufferMethodGenerator_InvokeOp("Long", Long.TYPE, null));
        var1.put(NativeType.FLOAT, new BufferMethodGenerator_InvokeOp("Float", Float.TYPE, null));
        var1.put(NativeType.DOUBLE, new BufferMethodGenerator_InvokeOp("Double", Double.TYPE, null));
        var1.put(NativeType.ADDRESS, new BufferMethodGenerator_InvokeOp("Address", Long.TYPE, null));
        if (NumberUtil.sizeof(NativeType.SLONG) != 4) {
            var1.put(NativeType.SLONG, new BufferMethodGenerator_InvokeOp("Long", Long.TYPE, null));
            var1.put(NativeType.ULONG, new BufferMethodGenerator_InvokeOp("Long", Long.TYPE, null));
        } else {
            var1.put(NativeType.SLONG, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
            var1.put(NativeType.ULONG, new BufferMethodGenerator_InvokeOp("Int", Integer.TYPE, null));
        }
        marshalOps = Collections.unmodifiableMap(var0);
        invokeOps = Collections.unmodifiableMap(var1);
    }

   BufferMethodGenerator() { // было: <init>
        super();
    }

   void generate(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, CallContext arg3, ResultType arg4, ParameterType[] arg5, boolean arg6) {
        generateBufferInvocation(arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        return true;
    }

  private static void emitPrimitiveOp(SkinnyMethodAdapter arg0, ParameterType arg1, ToNativeOp arg2) {
        BufferMethodGenerator_MarshalOp var3 = ((BufferMethodGenerator_MarshalOp) marshalOps.get(arg1.getNativeType()));
        if (var3 != null) {
            arg2.emitPrimitive(arg0, var3.primitiveClass, arg1.getNativeType());
            arg0.invokevirtual(HeapInvocationBuffer.class, var3.methodName, Void.TYPE, new Class[]{var3.primitiveClass});
            return;
        } else {
            throw new IllegalArgumentException(new StringBuilder().append("unsupported parameter type ").append(arg1).toString());
        }
    }

  static boolean isSessionRequired(ParameterType arg0) {
        return false;
    }

  static boolean isSessionRequired(ParameterType[] arg0) {
        ParameterType[] var1 = arg0;
        int var2 = var1.length;
        int var3 = 0;
        while (true) {
            if (var3 >= var2) {
                return false;
            }
            Object var4 = var1[var3];
            if (isSessionRequired(((ParameterType) var4))) {
                break;
            }
            ++var3;
            continue;
        }
        return true;
    }

   void generateBufferInvocation(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, CallContext arg3, ResultType arg4, ParameterType[] arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  6
        //      2: invokestatic  #100 // jnr.ffi.provider.jffi.BufferMethodGenerator.isSessionRequired:([Ljnr/ffi/provider/ParameterType;)Z
        //      5: istore  7
        //      7: aload_3
        //      8: ldc  #30 // jnr.ffi.provider.InvocationSession
        //     10: invokevirtual  #107 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //     13: astore  8
        //     15: iload  7
        //     17: ifeq  54 (offset +37)
        //     20: aload_2
        //     21: ldc  #30 // jnr.ffi.provider.InvocationSession
        //     23: invokestatic  #106 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     26: invokevirtual  #118 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.newobj:(Ljava/lang/String;)V
        //     29: aload_2
        //     30: invokevirtual  #113 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.dup:()V
        //     33: aload_2
        //     34: ldc  #30 // jnr.ffi.provider.InvocationSession
        //     36: ldc  #1 // '<init>'
        //     38: getstatic  #54 // java.lang.Void.TYPE:Ljava/lang/Class;
        //     41: iconst_0
        //     42: anewarray  #17 // java.lang.Class
        //     45: invokevirtual  #115 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokespecial:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     48: aload_2
        //     49: aload  8
        //     51: invokevirtual  #112 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.astore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //     54: aload_2
        //     55: iconst_0
        //     56: invokevirtual  #110 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //     59: aload_2
        //     60: aload_1
        //     61: invokevirtual  #91 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //     64: aload_1
        //     65: aload  4
        //     67: invokevirtual  #90 // jnr.ffi.provider.jffi.AsmBuilder.getCallContextFieldName:(Lcom/kenai/jffi/CallContext;)Ljava/lang/String;
        //     70: ldc  #13 // com.kenai.jffi.CallContext
        //     72: invokestatic  #105 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //     75: invokevirtual  #114 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //     78: aload_2
        //     79: ldc  #35 // jnr.ffi.provider.jffi.AsmRuntime
        //     81: ldc  #9 // 'newHeapInvocationBuffer'
        //     83: ldc  #14 // com.kenai.jffi.HeapInvocationBuffer
        //     85: iconst_1
        //     86: anewarray  #17 // java.lang.Class
        //     89: dup
        //     90: iconst_0
        //     91: ldc  #13 // com.kenai.jffi.CallContext
        //     93: aastore
        //     94: invokevirtual  #116 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //     97: aload  6
        //     99: invokestatic  #93 // jnr.ffi.provider.jffi.AsmUtil.getParameterVariables:([Ljnr/ffi/provider/ParameterType;)[Ljnr/ffi/provider/jffi/LocalVariable;
        //    102: astore  9
        //    104: aload  6
        //    106: arraylength
        //    107: anewarray  #44 // jnr.ffi.provider.jffi.LocalVariable
        //    110: astore  10
        //    112: aload  6
        //    114: arraylength
        //    115: anewarray  #44 // jnr.ffi.provider.jffi.LocalVariable
        //    118: astore  11
        //    120: iconst_0
        //    121: istore  12
        //    123: iload  12
        //    125: aload  6
        //    127: arraylength
        //    128: if_icmpge  355 (offset +227)
        //    131: aload_2
        //    132: invokevirtual  #113 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.dup:()V
        //    135: aload  6
        //    137: iload  12
        //    139: aaload
        //    140: invokestatic  #99 // jnr.ffi.provider.jffi.BufferMethodGenerator.isSessionRequired:(Ljnr/ffi/provider/ParameterType;)Z
        //    143: ifeq  152 (offset +9)
        //    146: aload_2
        //    147: aload  8
        //    149: invokevirtual  #111 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    152: aload  10
        //    154: iload  12
        //    156: aload_1
        //    157: aload_2
        //    158: aload_3
        //    159: aload  9
        //    161: iload  12
        //    163: aaload
        //    164: aload  6
        //    166: iload  12
        //    168: aaload
        //    169: invokestatic  #101 // jnr.ffi.provider.jffi.BufferMethodGenerator.loadAndConvertParameter:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/LocalVariableAllocator;Ljnr/ffi/provider/jffi/LocalVariable;Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    172: aastore
        //    173: aload  6
        //    175: iload  12
        //    177: aaload
        //    178: invokevirtual  #83 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //    181: astore  13
        //    183: aload  6
        //    185: iload  12
        //    187: aaload
        //    188: invokestatic  #121 // jnr.ffi.provider.jffi.ToNativeOp.get:(Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/ToNativeOp;
        //    191: astore  14
        //    193: aload  14
        //    195: ifnull  220 (offset +25)
        //    198: aload  14
        //    200: invokevirtual  #122 // jnr.ffi.provider.jffi.ToNativeOp.isPrimitive:()Z
        //    203: ifeq  220 (offset +17)
        //    206: aload_2
        //    207: aload  6
        //    209: iload  12
        //    211: aaload
        //    212: aload  14
        //    214: invokestatic  #97 // jnr.ffi.provider.jffi.BufferMethodGenerator.emitPrimitiveOp:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ParameterType;Ljnr/ffi/provider/jffi/ToNativeOp;)V
        //    217: goto  349 (offset +132)
        //    220: aload  13
        //    222: invokestatic  #89 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.hasPointerParameterStrategy:(Ljava/lang/Class;)Z
        //    225: ifeq  318 (offset +93)
        //    228: aload_2
        //    229: aload  13
        //    231: invokestatic  #88 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitParameterStrategyLookup:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)Ljava/lang/Class;
        //    234: pop
        //    235: aload_2
        //    236: aload  11
        //    238: iload  12
        //    240: aload_3
        //    241: ldc  #47 // jnr.ffi.provider.jffi.PointerParameterStrategy
        //    243: invokevirtual  #107 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    246: dup_x2
        //    247: aastore
        //    248: invokevirtual  #112 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.astore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    251: aload_2
        //    252: aload  10
        //    254: iload  12
        //    256: aaload
        //    257: invokevirtual  #111 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    260: aload_2
        //    261: aload  11
        //    263: iload  12
        //    265: aaload
        //    266: invokevirtual  #111 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    269: aload_2
        //    270: aload  6
        //    272: iload  12
        //    274: aaload
        //    275: invokevirtual  #82 // jnr.ffi.provider.ParameterType.annotations:()Ljava/util/Collection;
        //    278: invokestatic  #92 // jnr.ffi.provider.jffi.AsmUtil.getNativeArrayFlags:(Ljava/util/Collection;)I
        //    281: invokevirtual  #119 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.pushInt:(I)V
        //    284: aload_2
        //    285: ldc  #14 // com.kenai.jffi.HeapInvocationBuffer
        //    287: ldc  #10 // 'putObject'
        //    289: getstatic  #54 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    292: iconst_3
        //    293: anewarray  #17 // java.lang.Class
        //    296: dup
        //    297: iconst_0
        //    298: ldc  #23 // java.lang.Object
        //    300: aastore
        //    301: dup
        //    302: iconst_1
        //    303: ldc  #16 // com.kenai.jffi.ObjectParameterStrategy
        //    305: aastore
        //    306: dup
        //    307: iconst_2
        //    308: getstatic  #52 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    311: aastore
        //    312: invokevirtual  #117 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    315: goto  349 (offset +34)
        //    318: new  #20 // java.lang.IllegalArgumentException
        //    321: dup
        //    322: new  #24 // java.lang.StringBuilder
        //    325: dup
        //    326: invokespecial  #76 // java.lang.StringBuilder.<init>:()V
        //    329: ldc  #11 // 'unsupported parameter type '
        //    331: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    334: aload  6
        //    336: iload  12
        //    338: aaload
        //    339: invokevirtual  #77 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    342: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    345: invokespecial  #75 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    348: athrow
        //    349: iinc  12, 1
        //    352: goto  123 (offset -229)
        //    355: getstatic  #69 // jnr.ffi.provider.jffi.BufferMethodGenerator.invokeOps:Ljava/util/Map;
        //    358: aload  5
        //    360: invokevirtual  #87 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    363: invokeinterface  #123 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    368: checkcast  #40 // jnr.ffi.provider.jffi.BufferMethodGenerator$InvokeOp
        //    371: astore  12
        //    373: aload  12
        //    375: ifnonnull  409 (offset +34)
        //    378: new  #20 // java.lang.IllegalArgumentException
        //    381: dup
        //    382: new  #24 // java.lang.StringBuilder
        //    385: dup
        //    386: invokespecial  #76 // java.lang.StringBuilder.<init>:()V
        //    389: ldc  #12 // 'unsupported return type '
        //    391: invokevirtual  #78 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    394: aload  5
        //    396: invokevirtual  #86 // jnr.ffi.provider.ResultType.getDeclaredType:()Ljava/lang/Class;
        //    399: invokevirtual  #77 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    402: invokevirtual  #79 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    405: invokespecial  #75 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    408: athrow
        //    409: aload_2
        //    410: ldc  #15 // com.kenai.jffi.Invoker
        //    412: aload  12
        //    414: getfield  #71 // jnr.ffi.provider.jffi.BufferMethodGenerator$InvokeOp.methodName:Ljava/lang/String;
        //    417: aload  12
        //    419: getfield  #72 // jnr.ffi.provider.jffi.BufferMethodGenerator$InvokeOp.primitiveClass:Ljava/lang/Class;
        //    422: iconst_3
        //    423: anewarray  #17 // java.lang.Class
        //    426: dup
        //    427: iconst_0
        //    428: ldc  #13 // com.kenai.jffi.CallContext
        //    430: aastore
        //    431: dup
        //    432: iconst_1
        //    433: getstatic  #53 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    436: aastore
        //    437: dup
        //    438: iconst_2
        //    439: ldc  #14 // com.kenai.jffi.HeapInvocationBuffer
        //    441: aastore
        //    442: invokevirtual  #117 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    445: aload_2
        //    446: aload  12
        //    448: getfield  #72 // jnr.ffi.provider.jffi.BufferMethodGenerator$InvokeOp.primitiveClass:Ljava/lang/Class;
        //    451: aload  5
        //    453: invokevirtual  #85 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    456: invokestatic  #94 // jnr.ffi.provider.jffi.AsmUtil.unboxedReturnType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    459: aload  5
        //    461: invokevirtual  #87 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    464: invokestatic  #108 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    467: aload_1
        //    468: aload_2
        //    469: aload  5
        //    471: aload  6
        //    473: aload  9
        //    475: aload  10
        //    477: iload  7
        //    479: ifeq  496 (offset +17)
        //    482: new  #39 // jnr.ffi.provider.jffi.BufferMethodGenerator$1
        //    485: dup
        //    486: aload_0
        //    487: aload_2
        //    488: aload  8
        //    490: invokespecial  #102 // jnr.ffi.provider.jffi.BufferMethodGenerator$1.<init>:(Ljnr/ffi/provider/jffi/BufferMethodGenerator;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    493: goto  497 (offset +4)
        //    496: aconst_null
        //    497: invokestatic  #96 // jnr.ffi.provider.jffi.BufferMethodGenerator.emitEpilogue:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;[Ljnr/ffi/provider/jffi/LocalVariable;[Ljnr/ffi/provider/jffi/LocalVariable;Ljava/lang/Runnable;)V
        //    500: return
    }

}