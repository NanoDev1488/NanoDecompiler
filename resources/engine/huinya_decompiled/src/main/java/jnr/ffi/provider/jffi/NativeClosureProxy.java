// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureProxy
package jnr.ffi.provider.jffi;

import java.lang.ref.Reference;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.ToNativeType;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.NativeClosureProxy_Factory;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.NumberUtil;

public abstract class NativeClosureProxy {

    // ---- поля ----
  protected final Runtime runtime;
  volatile Reference closureReference;
  public static final boolean DEBUG;
  private static final AtomicLong nextClassID;

    static {
        DEBUG = Boolean.getBoolean("jnr.ffi.compile.dump");
        nextClassID = new AtomicLong(0L);
    }

  protected NativeClosureProxy(NativeRuntime arg0) { // было: <init>
        super();
        runtime = arg0;
    }

  protected Object getCallable() {
        Object __stk1;
        __stk1 = closureReference == null ? null : closureReference.get();
        Object var1 = __stk1;
        if (var1 == null) {
            throw new NullPointerException("callable is null");
        } else {
            return var1;
        }
    }

  static NativeClosureProxy_Factory newProxyFactory(Runtime arg0, Method arg1, ToNativeType arg2, FromNativeType[] arg3, AsmClassLoader arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #28 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #87 // java.lang.StringBuilder.<init>:()V
        //      7: ldc  #47 // jnr.ffi.provider.jffi.NativeClosureProxy
        //      9: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     12: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     15: ldc  #1 // '$$impl$$'
        //     17: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     20: getstatic  #70 // jnr.ffi.provider.jffi.NativeClosureProxy.nextClassID:Ljava/util/concurrent/atomic/AtomicLong;
        //     23: invokevirtual  #100 // java.util.concurrent.atomic.AtomicLong.getAndIncrement:()J
        //     26: invokevirtual  #88 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //     29: invokevirtual  #91 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     32: astore  5
        //     34: new  #55 // org.objectweb.asm.ClassWriter
        //     37: dup
        //     38: iconst_2
        //     39: invokespecial  #158 // org.objectweb.asm.ClassWriter.<init>:(I)V
        //     42: astore  6
        //     44: getstatic  #68 // jnr.ffi.provider.jffi.NativeClosureProxy.DEBUG:Z
        //     47: ifeq  58 (offset +11)
        //     50: aload  6
        //     52: invokestatic  #119 // jnr.ffi.provider.jffi.AsmUtil.newCheckClassAdapter:(Lorg/objectweb/asm/ClassVisitor;)Lorg/objectweb/asm/ClassVisitor;
        //     55: goto  60 (offset +5)
        //     58: aload  6
        //     60: astore  7
        //     62: new  #40 // jnr.ffi.provider.jffi.AsmBuilder
        //     65: dup
        //     66: aload_0
        //     67: aload  5
        //     69: aload  7
        //     71: aload  4
        //     73: invokespecial  #108 // jnr.ffi.provider.jffi.AsmBuilder.<init>:(Ljnr/ffi/Runtime;Ljava/lang/String;Lorg/objectweb/asm/ClassVisitor;Ljnr/ffi/provider/jffi/AsmClassLoader;)V
        //     76: astore  8
        //     78: aload  7
        //     80: bipush  52
        //     82: bipush  17
        //     84: aload  5
        //     86: aconst_null
        //     87: ldc  #47 // jnr.ffi.provider.jffi.NativeClosureProxy
        //     89: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     92: iconst_0
        //     93: anewarray  #27 // java.lang.String
        //     96: invokevirtual  #155 // org.objectweb.asm.ClassVisitor.visit:(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //     99: aload_3
        //    100: arraylength
        //    101: anewarray  #14 // java.lang.Class
        //    104: astore  9
        //    106: iconst_0
        //    107: istore  10
        //    109: iload  10
        //    111: aload_3
        //    112: arraylength
        //    113: if_icmpge  137 (offset +24)
        //    116: aload  9
        //    118: iload  10
        //    120: aload_3
        //    121: iload  10
        //    123: aaload
        //    124: invokevirtual  #104 // jnr.ffi.provider.FromNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    127: invokestatic  #132 // jnr.ffi.provider.jffi.NativeClosureProxy.getNativeClass:(Ljnr/ffi/NativeType;)Ljava/lang/Class;
        //    130: aastore
        //    131: iinc  10, 1
        //    134: goto  109 (offset -25)
        //    137: aload_2
        //    138: invokevirtual  #107 // jnr.ffi.provider.ToNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    141: invokestatic  #132 // jnr.ffi.provider.jffi.NativeClosureProxy.getNativeClass:(Ljnr/ffi/NativeType;)Ljava/lang/Class;
        //    144: astore  10
        //    146: new  #52 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    149: dup
        //    150: aload  7
        //    152: bipush  17
        //    154: ldc  #5 // 'invoke'
        //    156: aload  10
        //    158: aload  9
        //    160: invokestatic  #129 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    163: aconst_null
        //    164: aconst_null
        //    165: invokespecial  #138 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    168: astore  11
        //    170: aload  11
        //    172: invokevirtual  #149 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //    175: aload  11
        //    177: iconst_0
        //    178: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    181: aload  11
        //    183: ldc  #47 // jnr.ffi.provider.jffi.NativeClosureProxy
        //    185: ldc  #4 // 'getCallable'
        //    187: ldc  #24 // java.lang.Object
        //    189: iconst_0
        //    190: anewarray  #14 // java.lang.Class
        //    193: invokevirtual  #145 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    196: aload  11
        //    198: aload_1
        //    199: invokevirtual  #95 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    202: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    205: invokevirtual  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/String;)V
        //    208: aload  9
        //    210: invokestatic  #117 // jnr.ffi.provider.jffi.AsmUtil.getParameterVariables:([Ljava/lang/Class;)[Ljnr/ffi/provider/jffi/LocalVariable;
        //    213: astore  12
        //    215: new  #45 // jnr.ffi.provider.jffi.LocalVariableAllocator
        //    218: dup
        //    219: aload  9
        //    221: invokespecial  #130 // jnr.ffi.provider.jffi.LocalVariableAllocator.<init>:([Ljava/lang/Class;)V
        //    224: astore  13
        //    226: iconst_0
        //    227: istore  14
        //    229: iload  14
        //    231: aload_3
        //    232: arraylength
        //    233: if_icmpge  355 (offset +122)
        //    236: aload_3
        //    237: iload  14
        //    239: aaload
        //    240: astore  15
        //    242: aload  15
        //    244: invokevirtual  #102 // jnr.ffi.provider.FromNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    247: astore  16
        //    249: aload  16
        //    251: invokestatic  #133 // jnr.ffi.provider.jffi.NativeClosureProxy.isParameterTypeSupported:(Ljava/lang/Class;)Z
        //    254: ifne  290 (offset +36)
        //    257: new  #18 // java.lang.IllegalArgumentException
        //    260: dup
        //    261: new  #28 // java.lang.StringBuilder
        //    264: dup
        //    265: invokespecial  #87 // java.lang.StringBuilder.<init>:()V
        //    268: ldc  #7 // 'unsupported closure parameter type '
        //    270: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    273: aload_3
        //    274: iload  14
        //    276: aaload
        //    277: invokevirtual  #103 // jnr.ffi.provider.FromNativeType.getDeclaredType:()Ljava/lang/Class;
        //    280: invokevirtual  #89 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    283: invokevirtual  #91 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    286: invokespecial  #83 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    289: athrow
        //    290: aload  11
        //    292: aload  9
        //    294: iload  14
        //    296: aaload
        //    297: aload  12
        //    299: iload  14
        //    301: aaload
        //    302: invokestatic  #118 // jnr.ffi.provider.jffi.AsmUtil.load:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    305: aload  16
        //    307: invokevirtual  #81 // java.lang.Class.isPrimitive:()Z
        //    310: ifne  332 (offset +22)
        //    313: aload  8
        //    315: aload  11
        //    317: aload_3
        //    318: iload  14
        //    320: aaload
        //    321: aload  9
        //    323: iload  14
        //    325: aaload
        //    326: invokestatic  #114 // jnr.ffi.provider.jffi.AsmUtil.emitFromNativeConversion:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/FromNativeType;Ljava/lang/Class;)V
        //    329: goto  349 (offset +20)
        //    332: aload  11
        //    334: aload  9
        //    336: iload  14
        //    338: aaload
        //    339: aload  16
        //    341: aload  15
        //    343: invokevirtual  #104 // jnr.ffi.provider.FromNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    346: invokestatic  #136 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    349: iinc  14, 1
        //    352: goto  229 (offset -123)
        //    355: aload_1
        //    356: invokevirtual  #95 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    359: invokevirtual  #80 // java.lang.Class.isInterface:()Z
        //    362: ifeq  395 (offset +33)
        //    365: aload  11
        //    367: aload_1
        //    368: invokevirtual  #95 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    371: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    374: aload_1
        //    375: invokevirtual  #96 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    378: aload_1
        //    379: invokevirtual  #98 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    382: aload_1
        //    383: invokevirtual  #97 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    386: invokestatic  #129 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    389: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokeinterface:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    392: goto  422 (offset +30)
        //    395: aload  11
        //    397: aload_1
        //    398: invokevirtual  #95 // java.lang.reflect.Method.getDeclaringClass:()Ljava/lang/Class;
        //    401: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    404: aload_1
        //    405: invokevirtual  #96 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    408: aload_1
        //    409: invokevirtual  #98 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    412: aload_1
        //    413: invokevirtual  #97 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    416: invokestatic  #129 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    419: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    422: aload_2
        //    423: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    426: invokestatic  #134 // jnr.ffi.provider.jffi.NativeClosureProxy.isReturnTypeSupported:(Ljava/lang/Class;)Z
        //    429: ifne  462 (offset +33)
        //    432: new  #18 // java.lang.IllegalArgumentException
        //    435: dup
        //    436: new  #28 // java.lang.StringBuilder
        //    439: dup
        //    440: invokespecial  #87 // java.lang.StringBuilder.<init>:()V
        //    443: ldc  #8 // 'unsupported closure return type '
        //    445: invokevirtual  #90 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    448: aload_2
        //    449: invokevirtual  #106 // jnr.ffi.provider.ToNativeType.getDeclaredType:()Ljava/lang/Class;
        //    452: invokevirtual  #89 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    455: invokevirtual  #91 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    458: invokespecial  #83 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    461: athrow
        //    462: aload  8
        //    464: aload  11
        //    466: aload_2
        //    467: invokestatic  #116 // jnr.ffi.provider.jffi.AsmUtil.emitToNativeConversion:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ToNativeType;)V
        //    470: aload_2
        //    471: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    474: invokevirtual  #81 // java.lang.Class.isPrimitive:()Z
        //    477: ifne  551 (offset +74)
        //    480: ldc  #23 // java.lang.Number
        //    482: aload_2
        //    483: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    486: invokevirtual  #79 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    489: ifeq  510 (offset +21)
        //    492: aload  11
        //    494: aload_2
        //    495: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    498: aload  10
        //    500: aload_2
        //    501: invokevirtual  #107 // jnr.ffi.provider.ToNativeType.getNativeType:()Ljnr/ffi/NativeType;
        //    504: invokestatic  #123 // jnr.ffi.provider.jffi.AsmUtil.unboxNumber:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    507: goto  551 (offset +44)
        //    510: ldc  #12 // java.lang.Boolean
        //    512: aload_2
        //    513: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    516: invokevirtual  #79 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    519: ifeq  532 (offset +13)
        //    522: aload  11
        //    524: aload  10
        //    526: invokestatic  #121 // jnr.ffi.provider.jffi.AsmUtil.unboxBoolean:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)V
        //    529: goto  551 (offset +22)
        //    532: ldc  #37 // jnr.ffi.Pointer
        //    534: aload_2
        //    535: invokevirtual  #105 // jnr.ffi.provider.ToNativeType.effectiveJavaType:()Ljava/lang/Class;
        //    538: invokevirtual  #79 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    541: ifeq  551 (offset +10)
        //    544: aload  11
        //    546: aload  10
        //    548: invokestatic  #124 // jnr.ffi.provider.jffi.AsmUtil.unboxPointer:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)V
        //    551: aload  11
        //    553: aload  10
        //    555: invokestatic  #115 // jnr.ffi.provider.jffi.AsmUtil.emitReturnOp:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)V
        //    558: aload  11
        //    560: bipush  10
        //    562: bipush  10
        //    564: aload  13
        //    566: invokevirtual  #131 // jnr.ffi.provider.jffi.LocalVariableAllocator.getSpaceUsed:()I
        //    569: iadd
        //    570: invokevirtual  #151 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    573: aload  11
        //    575: invokevirtual  #150 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    578: new  #52 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    581: dup
        //    582: aload  7
        //    584: iconst_1
        //    585: ldc  #2 // '<init>'
        //    587: getstatic  #64 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    590: iconst_2
        //    591: anewarray  #14 // java.lang.Class
        //    594: dup
        //    595: iconst_0
        //    596: ldc  #50 // jnr.ffi.provider.jffi.NativeRuntime
        //    598: aastore
        //    599: dup
        //    600: iconst_1
        //    601: ldc  #10 // [Ljava.lang.Object;
        //    603: aastore
        //    604: invokestatic  #129 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    607: aconst_null
        //    608: aconst_null
        //    609: invokespecial  #138 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    612: astore  14
        //    614: aload  14
        //    616: invokevirtual  #149 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //    619: aload  14
        //    621: iconst_0
        //    622: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    625: aload  14
        //    627: iconst_1
        //    628: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    631: aload  14
        //    633: ldc  #47 // jnr.ffi.provider.jffi.NativeClosureProxy
        //    635: invokestatic  #128 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    638: ldc  #2 // '<init>'
        //    640: getstatic  #64 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    643: iconst_1
        //    644: anewarray  #14 // java.lang.Class
        //    647: dup
        //    648: iconst_0
        //    649: ldc  #50 // jnr.ffi.provider.jffi.NativeRuntime
        //    651: aastore
        //    652: invokestatic  #129 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    655: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokespecial:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    658: aload  8
        //    660: invokevirtual  #112 // jnr.ffi.provider.jffi.AsmBuilder.getObjectFieldArray:()[Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;
        //    663: astore  15
        //    665: aload  15
        //    667: arraylength
        //    668: anewarray  #24 // java.lang.Object
        //    671: astore  16
        //    673: iconst_0
        //    674: istore  17
        //    676: iload  17
        //    678: aload  16
        //    680: arraylength
        //    681: if_icmpge  851 (offset +170)
        //    684: aload  16
        //    686: iload  17
        //    688: aload  15
        //    690: iload  17
        //    692: aaload
        //    693: getfield  #67 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.value:Ljava/lang/Object;
        //    696: aastore
        //    697: aload  15
        //    699: iload  17
        //    701: aaload
        //    702: getfield  #66 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.name:Ljava/lang/String;
        //    705: astore  18
        //    707: aload  8
        //    709: invokevirtual  #111 // jnr.ffi.provider.jffi.AsmBuilder.getClassVisitor:()Lorg/objectweb/asm/ClassVisitor;
        //    712: bipush  18
        //    714: aload  18
        //    716: aload  15
        //    718: iload  17
        //    720: aaload
        //    721: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    724: invokestatic  #127 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    727: aconst_null
        //    728: aconst_null
        //    729: invokevirtual  #157 // org.objectweb.asm.ClassVisitor.visitField:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/objectweb/asm/FieldVisitor;
        //    732: pop
        //    733: aload  14
        //    735: iconst_0
        //    736: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    739: aload  14
        //    741: iconst_2
        //    742: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    745: aload  14
        //    747: iload  17
        //    749: invokevirtual  #147 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.pushInt:(I)V
        //    752: aload  14
        //    754: invokevirtual  #139 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aaload:()V
        //    757: aload  15
        //    759: iload  17
        //    761: aaload
        //    762: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    765: invokevirtual  #81 // java.lang.Class.isPrimitive:()Z
        //    768: ifeq  809 (offset +41)
        //    771: aload  15
        //    773: iload  17
        //    775: aaload
        //    776: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    779: invokestatic  #125 // jnr.ffi.provider.jffi.AsmUtil.unboxedType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    782: astore  19
        //    784: aload  14
        //    786: aload  19
        //    788: invokevirtual  #141 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/Class;)V
        //    791: aload  14
        //    793: aload  19
        //    795: aload  15
        //    797: iload  17
        //    799: aaload
        //    800: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    803: invokestatic  #122 // jnr.ffi.provider.jffi.AsmUtil.unboxNumber:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    806: goto  822 (offset +16)
        //    809: aload  14
        //    811: aload  15
        //    813: iload  17
        //    815: aaload
        //    816: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    819: invokevirtual  #141 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.checkcast:(Ljava/lang/Class;)V
        //    822: aload  14
        //    824: aload  8
        //    826: invokevirtual  #110 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    829: aload  18
        //    831: aload  15
        //    833: iload  17
        //    835: aaload
        //    836: getfield  #65 // jnr.ffi.provider.jffi.AsmBuilder$ObjectField.klass:Ljava/lang/Class;
        //    839: invokestatic  #127 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    842: invokevirtual  #148 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.putfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    845: iinc  17, 1
        //    848: goto  676 (offset -172)
        //    851: aload  14
        //    853: invokevirtual  #152 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.voidreturn:()V
        //    856: aload  14
        //    858: bipush  10
        //    860: bipush  10
        //    862: invokevirtual  #151 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    865: aload  14
        //    867: invokevirtual  #150 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    870: aload  7
        //    872: invokevirtual  #156 // org.objectweb.asm.ClassVisitor.visitEnd:()V
        //    875: aload  6
        //    877: invokevirtual  #159 // org.objectweb.asm.ClassWriter.toByteArray:()[B
        //    880: astore  17
        //    882: getstatic  #68 // jnr.ffi.provider.jffi.NativeClosureProxy.DEBUG:Z
        //    885: ifeq  918 (offset +33)
        //    888: new  #11 // java.io.PrintWriter
        //    891: dup
        //    892: getstatic  #63 // java.lang.System.err:Ljava/io/PrintStream;
        //    895: invokespecial  #73 // java.io.PrintWriter.<init>:(Ljava/io/OutputStream;)V
        //    898: invokestatic  #120 // jnr.ffi.provider.jffi.AsmUtil.newTraceClassVisitor:(Ljava/io/PrintWriter;)Lorg/objectweb/asm/ClassVisitor;
        //    901: astore  18
        //    903: new  #53 // org.objectweb.asm.ClassReader
        //    906: dup
        //    907: aload  17
        //    909: invokespecial  #153 // org.objectweb.asm.ClassReader.<init>:([B)V
        //    912: aload  18
        //    914: iconst_0
        //    915: invokevirtual  #154 // org.objectweb.asm.ClassReader.accept:(Lorg/objectweb/asm/ClassVisitor;I)V
        //    918: ldc  #46 // jnr.ffi.provider.jffi.NativeClosureFactory
        //    920: invokevirtual  #75 // java.lang.Class.getClassLoader:()Ljava/lang/ClassLoader;
        //    923: astore  18
        //    925: aload  18
        //    927: ifnonnull  938 (offset +11)
        //    930: invokestatic  #92 // java.lang.Thread.currentThread:()Ljava/lang/Thread;
        //    933: invokevirtual  #93 // java.lang.Thread.getContextClassLoader:()Ljava/lang/ClassLoader;
        //    936: astore  18
        //    938: aload  18
        //    940: ifnonnull  948 (offset +8)
        //    943: invokestatic  #82 // java.lang.ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //    946: astore  18
        //    948: aload  8
        //    950: invokevirtual  #109 // jnr.ffi.provider.jffi.AsmBuilder.getClassLoader:()Ljnr/ffi/provider/jffi/AsmClassLoader;
        //    953: aload  5
        //    955: invokestatic  #126 // jnr.ffi.provider.jffi.CodegenUtils.c:(Ljava/lang/String;)Ljava/lang/String;
        //    958: aload  17
        //    960: invokevirtual  #113 // jnr.ffi.provider.jffi.AsmClassLoader.defineClass:(Ljava/lang/String;[B)Ljava/lang/Class;
        //    963: astore  19
        //    965: aconst_null
        //    966: astore  20
        //    968: aload  19
        //    970: iconst_2
        //    971: anewarray  #14 // java.lang.Class
        //    974: dup
        //    975: iconst_0
        //    976: ldc  #50 // jnr.ffi.provider.jffi.NativeRuntime
        //    978: aastore
        //    979: dup
        //    980: iconst_1
        //    981: ldc  #10 // [Ljava.lang.Object;
        //    983: aastore
        //    984: invokevirtual  #76 // java.lang.Class.getConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
        //    987: astore  20
        //    989: goto  1003 (offset +14)
        //    992: astore  21
        //    994: aload  19
        //    996: invokevirtual  #77 // java.lang.Class.getConstructors:()[Ljava/lang/reflect/Constructor;
        //    999: iconst_0
        //   1000: aaload
        //   1001: astore  20
        //   1003: new  #49 // jnr.ffi.provider.jffi.NativeClosureProxy$Factory
        //   1006: dup
        //   1007: aload_0
        //   1008: aload  20
        //   1010: aload  19
        //   1012: ldc  #5 // 'invoke'
        //   1014: aload  9
        //   1016: invokevirtual  #78 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   1019: aload  16
        //   1021: invokespecial  #135 // jnr.ffi.provider.jffi.NativeClosureProxy$Factory.<init>:(Ljnr/ffi/Runtime;Ljava/lang/reflect/Constructor;Ljava/lang/reflect/Method;[Ljava/lang/Object;)V
        //   1024: areturn
        //   1025: astore  17
        //   1027: new  #25 // java.lang.RuntimeException
        //   1030: dup
        //   1031: aload  17
        //   1033: invokespecial  #86 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   1036: athrow
        //       Exception table:
        //         from 968 to 989 target 992 type java.lang.NoSuchMethodException
        //         from 875 to 1024 target 1025 type java.lang.Throwable
    }

  private static boolean isReturnTypeSupported(Class arg0) {
        return arg0.isPrimitive() ? 1 : Boolean.TYPE == arg0 ? 1 : Boolean.class == arg0 ? 1 : Byte.class == arg0 ? 1 : Short.class == arg0 ? 1 : Integer.class == arg0 ? 1 : Long.class == arg0 ? 1 : Float.class == arg0 ? 1 : Double.class == arg0 ? 1 : Pointer.class == arg0;
    }

  private static boolean isParameterTypeSupported(Class arg0) {
        return arg0.isPrimitive() ? 1 : Boolean.TYPE == arg0 ? 1 : Boolean.class == arg0 ? 1 : Byte.class == arg0 ? 1 : Short.class == arg0 ? 1 : Integer.class == arg0 ? 1 : Long.class == arg0 ? 1 : Float.class == arg0 ? 1 : Double.class == arg0 ? 1 : Pointer.class == arg0;
    }

  static Class getNativeClass(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
            case UCHAR:
                return Byte.TYPE;
            case SSHORT:
            case USHORT:
                return Short.TYPE;
            case SINT:
            case UINT:
                return Integer.TYPE;
            case SLONG:
            case ULONG:
            case ADDRESS:
                return NumberUtil.sizeof(arg0) > 4 ? Long.TYPE : Integer.TYPE;
            case SLONGLONG:
            case ULONGLONG:
                return Long.TYPE;
            case FLOAT:
                return Float.TYPE;
            case DOUBLE:
                return Double.TYPE;
            case VOID:
                return Void.TYPE;
            default:
                throw new IllegalArgumentException(new StringBuilder().append("unsupported native type: ").append(arg0).toString());
        }
    }

}