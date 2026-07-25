// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.VariadicInvoker
package jnr.ffi.provider.jffi;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jnr.ffi.CallingConvention;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Meta;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;
import jnr.ffi.util.AnnotationProxy;

class DefaultInvokerFactory_VariadicInvoker implements Invoker {

    // ---- поля ----
  private final Runtime runtime;
  private final DefaultInvokerFactory_FunctionInvoker functionInvoker;
  private final SignatureTypeMapper typeMapper;
  private final ParameterType[] fixedParameterTypes;
  private final long functionAddress;
  private final SigType resultType;
  private final boolean requiresErrno;
  private final CallingConvention callingConvention;

   DefaultInvokerFactory_VariadicInvoker(Runtime arg0, DefaultInvokerFactory_FunctionInvoker arg1, SignatureTypeMapper arg2, ParameterType[] arg3, long arg4, SigType arg5, boolean arg6, CallingConvention arg7) { // было: <init>
        super();
        runtime = arg0;
        functionInvoker = arg1;
        typeMapper = arg2;
        fixedParameterTypes = arg3;
        functionAddress = arg4;
        resultType = arg5;
        requiresErrno = arg6;
        callingConvention = arg7;
    }

  public final Object invoke(Object arg0, Object[] arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: aload_2
        //      2: arraylength
        //      3: iconst_1
        //      4: isub
        //      5: aaload
        //      6: checkcast  #2 // [Ljava.lang.Object;
        //      9: astore_3
        //     10: aload_0
        //     11: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //     14: arraylength
        //     15: aload_3
        //     16: arraylength
        //     17: iadd
        //     18: anewarray  #24 // jnr.ffi.provider.ParameterType
        //     21: astore  4
        //     23: aload_0
        //     24: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //     27: iconst_0
        //     28: aload  4
        //     30: iconst_0
        //     31: aload_0
        //     32: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //     35: arraylength
        //     36: iconst_1
        //     37: isub
        //     38: invokestatic  #52 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     41: aload_3
        //     42: arraylength
        //     43: iconst_1
        //     44: iadd
        //     45: anewarray  #6 // java.lang.Object
        //     48: astore  5
        //     50: iconst_0
        //     51: istore  6
        //     53: new  #10 // java.util.ArrayList
        //     56: dup
        //     57: invokespecial  #53 // java.util.ArrayList.<init>:()V
        //     60: astore  7
        //     62: aload_3
        //     63: astore  8
        //     65: aload  8
        //     67: arraylength
        //     68: istore  9
        //     70: iconst_0
        //     71: istore  10
        //     73: iload  10
        //     75: iload  9
        //     77: if_icmpge  307 (offset +230)
        //     80: aload  8
        //     82: iload  10
        //     84: aaload
        //     85: astore  11
        //     87: aload  11
        //     89: instanceof  #5 // java.lang.Class
        //     92: ifeq  124 (offset +32)
        //     95: ldc  #9 // java.lang.annotation.Annotation
        //     97: aload  11
        //     99: checkcast  #5 // java.lang.Class
        //    102: invokevirtual  #47 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    105: ifeq  124 (offset +19)
        //    108: aload  7
        //    110: aload  11
        //    112: checkcast  #5 // java.lang.Class
        //    115: invokeinterface  #70 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    120: pop
        //    121: goto  301 (offset +180)
        //    124: aconst_null
        //    125: astore  13
        //    127: aload  7
        //    129: invokestatic  #61 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.getAnnotations:(Ljava/util/Collection;)Ljava/util/Collection;
        //    132: astore  14
        //    134: aload  7
        //    136: invokeinterface  #71 // java.util.List.clear:()V, count 1
        //    141: new  #30 // jnr.ffi.provider.jffi.SimpleNativeContext
        //    144: dup
        //    145: aload_0
        //    146: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    149: aload  14
        //    151: invokespecial  #63 // jnr.ffi.provider.jffi.SimpleNativeContext.<init>:(Ljnr/ffi/Runtime;Ljava/util/Collection;)V
        //    154: astore  15
        //    156: aload  11
        //    158: ifnull  234 (offset +76)
        //    161: aload_0
        //    162: getfield  #40 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.typeMapper:Ljnr/ffi/mapper/SignatureTypeMapper;
        //    165: aload  11
        //    167: invokevirtual  #50 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    170: aload  15
        //    172: invokestatic  #56 // jnr.ffi.mapper.DefaultSignatureType.create:(Ljava/lang/Class;Ljnr/ffi/mapper/ToNativeContext;)Ljnr/ffi/mapper/DefaultSignatureType;
        //    175: aload  15
        //    177: invokeinterface  #72 // jnr.ffi.mapper.SignatureTypeMapper.getToNativeType:(Ljnr/ffi/mapper/SignatureType;Ljnr/ffi/mapper/ToNativeContext;)Ljnr/ffi/mapper/ToNativeType;, count 3
        //    182: astore  16
        //    184: aload  16
        //    186: ifnonnull  193 (offset +7)
        //    189: aconst_null
        //    190: goto  200 (offset +10)
        //    193: aload  16
        //    195: invokeinterface  #74 // jnr.ffi.mapper.ToNativeType.getToNativeConverter:()Ljnr/ffi/mapper/ToNativeConverter;, count 1
        //    200: astore  13
        //    202: aload  13
        //    204: ifnonnull  215 (offset +11)
        //    207: aload  11
        //    209: invokevirtual  #50 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    212: goto  222 (offset +10)
        //    215: aload  13
        //    217: invokeinterface  #73 // jnr.ffi.mapper.ToNativeConverter.nativeType:()Ljava/lang/Class;, count 1
        //    222: astore  12
        //    224: aload  5
        //    226: iload  6
        //    228: aload  11
        //    230: aastore
        //    231: goto  245 (offset +14)
        //    234: ldc  #15 // jnr.ffi.Pointer
        //    236: astore  12
        //    238: aload  5
        //    240: iload  6
        //    242: aload  11
        //    244: aastore
        //    245: aload  4
        //    247: aload_0
        //    248: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //    251: arraylength
        //    252: iload  6
        //    254: iadd
        //    255: iconst_1
        //    256: isub
        //    257: new  #24 // jnr.ffi.provider.ParameterType
        //    260: dup
        //    261: aload  12
        //    263: aload_0
        //    264: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    267: aload  12
        //    269: aload  14
        //    271: invokestatic  #64 // jnr.ffi.provider.jffi.Types.getType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/Type;
        //    274: invokevirtual  #55 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //    277: aload  14
        //    279: aload  13
        //    281: new  #30 // jnr.ffi.provider.jffi.SimpleNativeContext
        //    284: dup
        //    285: aload_0
        //    286: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    289: aload  14
        //    291: invokespecial  #63 // jnr.ffi.provider.jffi.SimpleNativeContext.<init>:(Ljnr/ffi/Runtime;Ljava/util/Collection;)V
        //    294: invokespecial  #59 // jnr.ffi.provider.ParameterType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)V
        //    297: aastore
        //    298: iinc  6, 1
        //    301: iinc  10, 1
        //    304: goto  73 (offset -231)
        //    307: aload  4
        //    309: aload_0
        //    310: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //    313: arraylength
        //    314: iload  6
        //    316: iadd
        //    317: iconst_1
        //    318: isub
        //    319: new  #24 // jnr.ffi.provider.ParameterType
        //    322: dup
        //    323: ldc  #15 // jnr.ffi.Pointer
        //    325: aload_0
        //    326: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    329: ldc  #15 // jnr.ffi.Pointer
        //    331: invokestatic  #54 // java.util.Collections.emptyList:()Ljava/util/List;
        //    334: invokestatic  #64 // jnr.ffi.provider.jffi.Types.getType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;)Ljnr/ffi/Type;
        //    337: invokevirtual  #55 // jnr.ffi.Type.getNativeType:()Ljnr/ffi/NativeType;
        //    340: invokestatic  #54 // java.util.Collections.emptyList:()Ljava/util/List;
        //    343: aconst_null
        //    344: new  #30 // jnr.ffi.provider.jffi.SimpleNativeContext
        //    347: dup
        //    348: aload_0
        //    349: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    352: invokestatic  #54 // java.util.Collections.emptyList:()Ljava/util/List;
        //    355: invokespecial  #63 // jnr.ffi.provider.jffi.SimpleNativeContext.<init>:(Ljnr/ffi/Runtime;Ljava/util/Collection;)V
        //    358: invokespecial  #59 // jnr.ffi.provider.ParameterType.<init>:(Ljava/lang/Class;Ljnr/ffi/NativeType;Ljava/util/Collection;Ljnr/ffi/mapper/ToNativeConverter;Ljnr/ffi/mapper/ToNativeContext;)V
        //    361: aastore
        //    362: aload  5
        //    364: iload  6
        //    366: aconst_null
        //    367: aastore
        //    368: iinc  6, 1
        //    371: aload_0
        //    372: getfield  #34 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.fixedParameterTypes:[Ljnr/ffi/provider/ParameterType;
        //    375: arraylength
        //    376: iconst_1
        //    377: isub
        //    378: istore  8
        //    380: iload  6
        //    382: iload  8
        //    384: iadd
        //    385: istore  9
        //    387: new  #3 // com.kenai.jffi.Function
        //    390: dup
        //    391: aload_0
        //    392: getfield  #35 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.functionAddress:J
        //    395: aload_0
        //    396: getfield  #38 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.resultType:Ljnr/ffi/provider/SigType;
        //    399: iload  8
        //    401: aload  4
        //    403: iload  9
        //    405: aload_0
        //    406: getfield  #33 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.callingConvention:Ljnr/ffi/CallingConvention;
        //    409: aload_0
        //    410: getfield  #37 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.requiresErrno:Z
        //    413: invokestatic  #62 // jnr.ffi.provider.jffi.InvokerUtil.getCallContext:(Ljnr/ffi/provider/SigType;I[Ljnr/ffi/provider/SigType;ILjnr/ffi/CallingConvention;Z)Lcom/kenai/jffi/CallContext;
        //    416: invokespecial  #41 // com.kenai.jffi.Function.<init>:(JLcom/kenai/jffi/CallContext;)V
        //    419: astore  10
        //    421: new  #4 // com.kenai.jffi.HeapInvocationBuffer
        //    424: dup
        //    425: aload  10
        //    427: invokevirtual  #42 // com.kenai.jffi.Function.getCallContext:()Lcom/kenai/jffi/CallContext;
        //    430: invokespecial  #43 // com.kenai.jffi.HeapInvocationBuffer.<init>:(Lcom/kenai/jffi/CallContext;)V
        //    433: astore  11
        //    435: new  #22 // jnr.ffi.provider.InvocationSession
        //    438: dup
        //    439: invokespecial  #57 // jnr.ffi.provider.InvocationSession.<init>:()V
        //    442: astore  12
        //    444: aload_2
        //    445: ifnull  487 (offset +42)
        //    448: iconst_0
        //    449: istore  13
        //    451: iload  13
        //    453: aload_2
        //    454: arraylength
        //    455: iconst_1
        //    456: isub
        //    457: if_icmpge  487 (offset +30)
        //    460: aload  4
        //    462: iload  13
        //    464: aaload
        //    465: invokestatic  #60 // jnr.ffi.provider.jffi.DefaultInvokerFactory.getMarshaller:(Ljnr/ffi/provider/ParameterType;)Ljnr/ffi/provider/jffi/DefaultInvokerFactory$Marshaller;
        //    468: aload  12
        //    470: aload  11
        //    472: aload_2
        //    473: iload  13
        //    475: aaload
        //    476: invokeinterface  #76 // jnr.ffi.provider.jffi.DefaultInvokerFactory$Marshaller.marshal:(Ljnr/ffi/provider/InvocationSession;Lcom/kenai/jffi/HeapInvocationBuffer;Ljava/lang/Object;)V, count 4
        //    481: iinc  13, 1
        //    484: goto  451 (offset -33)
        //    487: iconst_0
        //    488: istore  13
        //    490: iload  13
        //    492: iload  6
        //    494: if_icmpge  528 (offset +34)
        //    497: aload  4
        //    499: iload  13
        //    501: iload  8
        //    503: iadd
        //    504: aaload
        //    505: invokestatic  #60 // jnr.ffi.provider.jffi.DefaultInvokerFactory.getMarshaller:(Ljnr/ffi/provider/ParameterType;)Ljnr/ffi/provider/jffi/DefaultInvokerFactory$Marshaller;
        //    508: aload  12
        //    510: aload  11
        //    512: aload  5
        //    514: iload  13
        //    516: aaload
        //    517: invokeinterface  #76 // jnr.ffi.provider.jffi.DefaultInvokerFactory$Marshaller.marshal:(Ljnr/ffi/provider/InvocationSession;Lcom/kenai/jffi/HeapInvocationBuffer;Ljava/lang/Object;)V, count 4
        //    522: iinc  13, 1
        //    525: goto  490 (offset -35)
        //    528: aload_0
        //    529: getfield  #36 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.functionInvoker:Ljnr/ffi/provider/jffi/DefaultInvokerFactory$FunctionInvoker;
        //    532: aload_0
        //    533: getfield  #39 // jnr.ffi.provider.jffi.DefaultInvokerFactory$VariadicInvoker.runtime:Ljnr/ffi/Runtime;
        //    536: aload  10
        //    538: aload  11
        //    540: invokeinterface  #75 // jnr.ffi.provider.jffi.DefaultInvokerFactory$FunctionInvoker.invoke:(Ljnr/ffi/Runtime;Lcom/kenai/jffi/Function;Lcom/kenai/jffi/HeapInvocationBuffer;)Ljava/lang/Object;, count 4
        //    545: astore  13
        //    547: aload  12
        //    549: invokevirtual  #58 // jnr.ffi.provider.InvocationSession.finish:()V
        //    552: aload  13
        //    554: areturn
        //    555: astore  17
        //    557: aload  12
        //    559: invokevirtual  #58 // jnr.ffi.provider.InvocationSession.finish:()V
        //    562: aload  17
        //    564: athrow
        //       Exception table:
        //         from 444 to 547 target 555 type any
        //         from 555 to 557 target 555 type any
    }

  private static Collection getAnnotations(Collection arg0) {
        ArrayList var1 = new ArrayList();
        Iterator var2 = arg0.iterator();
        while (var2.hasNext()) {
            Class var3 = ((Class) var2.next());
            if (var3.getAnnotation(Meta.class) == null) {
                var1.add(AnnotationProxy.newProxy(var3));
            } else {
                Annotation[] var4 = var3.getAnnotations();
                int var5 = var4.length;
                int var6 = 0;
                while (var6 < var5) {
                    Object var7 = var4[var6];
                    if (!var7.annotationType().getName().startsWith("java")) {
                        if (!Meta.class.equals(var7.annotationType())) {
                            var1.add(var7);
                        }
                    }
                    ++var6;
                    continue;
                }
            }
            continue;
        }
        return var1;
    }

}