// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmLibraryLoader
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmBuilder_ObjectField;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LibraryLoader;
import jnr.ffi.provider.jffi.NativeLibrary;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.ClassVisitor;

public class AsmLibraryLoader extends LibraryLoader {

    // ---- поля ----
  public static final boolean DEBUG;
  private static final AtomicLong nextClassID;
  private static final AtomicLong uniqueId;
  private static final ThreadLocal classLoader;
  private final NativeRuntime runtime;

    static {
        DEBUG = Boolean.getBoolean("jnr.ffi.compile.dump");
        nextClassID = new AtomicLong(0L);
        uniqueId = new AtomicLong(0L);
        classLoader = new ThreadLocal();
    }

  public AsmLibraryLoader() { // было: <init>
        super();
        runtime = NativeRuntime.getInstance();
    }

   Object loadLibrary(NativeLibrary arg0, Class arg1, Map arg2, boolean arg3) {
        AsmClassLoader var5 = ((AsmClassLoader) classLoader.get());
        if (var5 == null) {
            classLoader.set(new AsmClassLoader(arg1.getClassLoader()));
        }
        try {
            Object var6 = generateInterfaceImpl(arg0, arg1, arg2, ((AsmClassLoader) classLoader.get()));
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var7 = e2;
                }
            } catch (Throwable var7) {
            }
        }
    }

  private Object generateInterfaceImpl(NativeLibrary arg0, Class arg1, Map arg2, AsmClassLoader arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #109 // jnr.ffi.provider.jffi.AsmLibraryLoader.DEBUG:Z
        //      3: ifeq  19 (offset +16)
        //      6: aload_2
        //      7: ldc  #84 // jnr.ffi.provider.jffi.NoTrace
        //      9: invokevirtual  #120 // java.lang.Class.isAnnotationPresent:(Ljava/lang/Class;)Z
        //     12: ifne  19 (offset +7)
        //     15: iconst_1
        //     16: goto  20 (offset +4)
        //     19: iconst_0
        //     20: istore  5
        //     22: new  #95 // org.objectweb.asm.ClassWriter
        //     25: dup
        //     26: iconst_2
        //     27: invokespecial  #237 // org.objectweb.asm.ClassWriter.<init>:(I)V
        //     30: astore  6
        //     32: iload  5
        //     34: ifeq  45 (offset +11)
        //     37: aload  6
        //     39: invokestatic  #175 // jnr.ffi.provider.jffi.AsmUtil.newCheckClassAdapter:(Lorg/objectweb/asm/ClassVisitor;)Lorg/objectweb/asm/ClassVisitor;
        //     42: goto  47 (offset +5)
        //     45: aload  6
        //     47: astore  7
        //     49: new  #66 // jnr.ffi.provider.jffi.AsmBuilder
        //     52: dup
        //     53: aload_0
        //     54: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //     57: new  #36 // java.lang.StringBuilder
        //     60: dup
        //     61: invokespecial  #126 // java.lang.StringBuilder.<init>:()V
        //     64: aload_2
        //     65: invokestatic  #179 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //     68: invokevirtual  #129 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     71: ldc  #1 // '$jnr$ffi$'
        //     73: invokevirtual  #129 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     76: getstatic  #111 // jnr.ffi.provider.jffi.AsmLibraryLoader.nextClassID:Ljava/util/concurrent/atomic/AtomicLong;
        //     79: invokevirtual  #144 // java.util.concurrent.atomic.AtomicLong.getAndIncrement:()J
        //     82: invokevirtual  #127 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //     85: invokevirtual  #130 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     88: aload  7
        //     90: aload  4
        //     92: invokespecial  #160 // jnr.ffi.provider.jffi.AsmBuilder.<init>:(Ljnr/ffi/Runtime;Ljava/lang/String;Lorg/objectweb/asm/ClassVisitor;Ljnr/ffi/provider/jffi/AsmClassLoader;)V
        //     95: astore  8
        //     97: aload  7
        //     99: bipush  52
        //    101: bipush  17
        //    103: aload  8
        //    105: invokevirtual  #162 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    108: aconst_null
        //    109: ldc  #65 // jnr.ffi.provider.jffi.AbstractAsmLibraryInterface
        //    111: invokestatic  #179 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    114: iconst_1
        //    115: anewarray  #35 // java.lang.String
        //    118: dup
        //    119: iconst_0
        //    120: aload_2
        //    121: invokestatic  #179 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    124: aastore
        //    125: invokevirtual  #234 // org.objectweb.asm.ClassVisitor.visit:(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //    128: aload_3
        //    129: getstatic  #107 // jnr.ffi.LibraryOption.FunctionMapper:Ljnr/ffi/LibraryOption;
        //    132: invokeinterface  #243 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //    137: ifeq  155 (offset +18)
        //    140: aload_3
        //    141: getstatic  #107 // jnr.ffi.LibraryOption.FunctionMapper:Ljnr/ffi/LibraryOption;
        //    144: invokeinterface  #244 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    149: checkcast  #56 // jnr.ffi.mapper.FunctionMapper
        //    152: goto  158 (offset +6)
        //    155: invokestatic  #149 // jnr.ffi.provider.IdentityFunctionMapper.getInstance:()Ljnr/ffi/mapper/FunctionMapper;
        //    158: astore  9
        //    160: aload_3
        //    161: invokestatic  #171 // jnr.ffi.provider.jffi.AsmLibraryLoader.getSignatureTypeMapper:(Ljava/util/Map;)Ljnr/ffi/mapper/SignatureTypeMapper;
        //    164: astore  10
        //    166: aload  4
        //    168: aload  10
        //    170: invokestatic  #172 // jnr.ffi.provider.jffi.AsmLibraryLoader.newClosureTypeMapper:(Ljnr/ffi/provider/jffi/AsmClassLoader;Ljnr/ffi/mapper/SignatureTypeMapper;)Ljnr/ffi/mapper/CompositeTypeMapper;
        //    173: astore  11
        //    175: aload_0
        //    176: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    179: aload  4
        //    181: aload  10
        //    183: aload  11
        //    185: invokestatic  #173 // jnr.ffi.provider.jffi.AsmLibraryLoader.newCompositeTypeMapper:(Ljnr/ffi/Runtime;Ljnr/ffi/provider/jffi/AsmClassLoader;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/mapper/CompositeTypeMapper;)Ljnr/ffi/mapper/CompositeTypeMapper;
        //    188: astore  10
        //    190: aload_2
        //    191: aload_3
        //    192: invokestatic  #187 // jnr.ffi.provider.jffi.InvokerUtil.getCallingConvention:(Ljava/lang/Class;Ljava/util/Map;)Ljnr/ffi/CallingConvention;
        //    195: astore  12
        //    197: aload_0
        //    198: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    201: invokestatic  #226 // jnr.ffi.provider.jffi.StubCompiler.newCompiler:(Ljnr/ffi/Runtime;)Ljnr/ffi/provider/jffi/StubCompiler;
        //    204: astore  13
        //    206: iconst_5
        //    207: anewarray  #80 // jnr.ffi.provider.jffi.MethodGenerator
        //    210: dup
        //    211: iconst_0
        //    212: aload_2
        //    213: ldc  #85 // jnr.ffi.provider.jffi.NoX86
        //    215: invokevirtual  #120 // java.lang.Class.isAnnotationPresent:(Ljava/lang/Class;)Z
        //    218: ifne  233 (offset +15)
        //    221: new  #91 // jnr.ffi.provider.jffi.X86MethodGenerator
        //    224: dup
        //    225: aload  13
        //    227: invokespecial  #230 // jnr.ffi.provider.jffi.X86MethodGenerator.<init>:(Ljnr/ffi/provider/jffi/StubCompiler;)V
        //    230: goto  240 (offset +10)
        //    233: new  #86 // jnr.ffi.provider.jffi.NotImplMethodGenerator
        //    236: dup
        //    237: invokespecial  #194 // jnr.ffi.provider.jffi.NotImplMethodGenerator.<init>:()V
        //    240: aastore
        //    241: dup
        //    242: iconst_1
        //    243: new  #75 // jnr.ffi.provider.jffi.FastIntMethodGenerator
        //    246: dup
        //    247: invokespecial  #183 // jnr.ffi.provider.jffi.FastIntMethodGenerator.<init>:()V
        //    250: aastore
        //    251: dup
        //    252: iconst_2
        //    253: new  #76 // jnr.ffi.provider.jffi.FastLongMethodGenerator
        //    256: dup
        //    257: invokespecial  #184 // jnr.ffi.provider.jffi.FastLongMethodGenerator.<init>:()V
        //    260: aastore
        //    261: dup
        //    262: iconst_3
        //    263: new  #77 // jnr.ffi.provider.jffi.FastNumericMethodGenerator
        //    266: dup
        //    267: invokespecial  #185 // jnr.ffi.provider.jffi.FastNumericMethodGenerator.<init>:()V
        //    270: aastore
        //    271: dup
        //    272: iconst_4
        //    273: new  #72 // jnr.ffi.provider.jffi.BufferMethodGenerator
        //    276: dup
        //    277: invokespecial  #177 // jnr.ffi.provider.jffi.BufferMethodGenerator.<init>:()V
        //    280: aastore
        //    281: astore  14
        //    283: new  #74 // jnr.ffi.provider.jffi.DefaultInvokerFactory
        //    286: dup
        //    287: aload_0
        //    288: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    291: aload_1
        //    292: aload  10
        //    294: aload  9
        //    296: aload  12
        //    298: aload_3
        //    299: aload_2
        //    300: ldc  #52 // jnr.ffi.annotations.Synchronized
        //    302: invokevirtual  #120 // java.lang.Class.isAnnotationPresent:(Ljava/lang/Class;)Z
        //    305: invokespecial  #181 // jnr.ffi.provider.jffi.DefaultInvokerFactory.<init>:(Ljnr/ffi/Runtime;Ljnr/ffi/provider/jffi/NativeLibrary;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/mapper/FunctionMapper;Ljnr/ffi/CallingConvention;Ljava/util/Map;Z)V
        //    308: astore  15
        //    310: new  #61 // jnr.ffi.provider.InterfaceScanner
        //    313: dup
        //    314: aload_2
        //    315: aload  10
        //    317: aload  12
        //    319: invokespecial  #150 // jnr.ffi.provider.InterfaceScanner.<init>:(Ljava/lang/Class;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/CallingConvention;)V
        //    322: astore  16
        //    324: aload  16
        //    326: invokevirtual  #151 // jnr.ffi.provider.InterfaceScanner.functions:()Ljava/util/Collection;
        //    329: invokeinterface  #240 // java.util.Collection.iterator:()Ljava/util/Iterator;, count 1
        //    334: astore  17
        //    336: aload  17
        //    338: invokeinterface  #241 // java.util.Iterator.hasNext:()Z, count 1
        //    343: ifeq  726 (offset +383)
        //    346: aload  17
        //    348: invokeinterface  #242 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    353: checkcast  #63 // jnr.ffi.provider.NativeFunction
        //    356: astore  18
        //    358: aload  18
        //    360: invokevirtual  #155 // jnr.ffi.provider.NativeFunction.getMethod:()Ljava/lang/reflect/Method;
        //    363: astore  19
        //    365: aload  19
        //    367: invokevirtual  #142 // java.lang.reflect.Method.isVarArgs:()Z
        //    370: ifne  383 (offset +13)
        //    373: aload  19
        //    375: ldc  #53 // jnr.ffi.annotations.Variadic
        //    377: invokevirtual  #141 // java.lang.reflect.Method.isAnnotationPresent:(Ljava/lang/Class;)Z
        //    380: ifeq  412 (offset +32)
        //    383: aload  8
        //    385: aload  15
        //    387: aload  19
        //    389: invokevirtual  #182 // jnr.ffi.provider.jffi.DefaultInvokerFactory.createInvoker:(Ljava/lang/reflect/Method;)Ljnr/ffi/provider/Invoker;
        //    392: ldc  #62 // jnr.ffi.provider.Invoker
        //    394: invokevirtual  #164 // jnr.ffi.provider.jffi.AsmBuilder.getObjectField:(Ljava/lang/Object;Ljava/lang/Class;)Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;
        //    397: astore  20
        //    399: aload_0
        //    400: aload  8
        //    402: aload  19
        //    404: aload  20
        //    406: invokespecial  #170 // jnr.ffi.provider.jffi.AsmLibraryLoader.generateVarargsInvocation:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljava/lang/reflect/Method;Ljnr/ffi/provider/jffi/AsmBuilder$ObjectField;)V
        //    409: goto  336 (offset -73)
        //    412: aload  9
        //    414: aload  18
        //    416: invokevirtual  #158 // jnr.ffi.provider.NativeFunction.name:()Ljava/lang/String;
        //    419: new  #81 // jnr.ffi.provider.jffi.NativeFunctionMapperContext
        //    422: dup
        //    423: aload_1
        //    424: aload  18
        //    426: invokevirtual  #153 // jnr.ffi.provider.NativeFunction.annotations:()Ljava/util/Collection;
        //    429: invokespecial  #191 // jnr.ffi.provider.jffi.NativeFunctionMapperContext.<init>:(Ljnr/ffi/provider/jffi/NativeLibrary;Ljava/util/Collection;)V
        //    432: invokeinterface  #246 // jnr.ffi.mapper.FunctionMapper.mapFunctionName:(Ljava/lang/String;Ljnr/ffi/mapper/FunctionMapper$Context;)Ljava/lang/String;, count 3
        //    437: astore  20
        //    439: aload_1
        //    440: aload  20
        //    442: invokevirtual  #192 // jnr.ffi.provider.jffi.NativeLibrary.findSymbolAddress:(Ljava/lang/String;)J
        //    445: lstore  21
        //    447: new  #58 // jnr.ffi.mapper.MethodResultContext
        //    450: dup
        //    451: aload_0
        //    452: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    455: aload  19
        //    457: invokespecial  #148 // jnr.ffi.mapper.MethodResultContext.<init>:(Ljnr/ffi/Runtime;Ljava/lang/reflect/Method;)V
        //    460: astore  23
        //    462: aload  19
        //    464: invokevirtual  #140 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    467: aload  23
        //    469: invokestatic  #147 // jnr.ffi.mapper.DefaultSignatureType.create:(Ljava/lang/Class;Ljnr/ffi/mapper/FromNativeContext;)Ljnr/ffi/mapper/DefaultSignatureType;
        //    472: astore  24
        //    474: aload_0
        //    475: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    478: aload  19
        //    480: invokevirtual  #140 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    483: aload  23
        //    485: invokeinterface  #245 // jnr.ffi.mapper.FromNativeContext.getAnnotations:()Ljava/util/Collection;, count 1
        //    490: aload  10
        //    492: aload  24
        //    494: aload  23
        //    496: invokeinterface  #247 // jnr.ffi.mapper.SignatureTypeMapper.getFromNativeType:(Ljnr/ffi/mapper/SignatureType;Ljnr/ffi/mapper/FromNativeContext;)Ljnr/ffi/mapper/FromNativeType;, count 3
        //    501: aload  23
        //    503: invokestatic  #189 // jnr.ffi.provider.jffi.InvokerUtil.getResultType:(Ljnr/ffi/Runtime;Ljava/lang/Class;Ljava/util/Collection;Ljnr/ffi/mapper/FromNativeType;Ljnr/ffi/mapper/FromNativeContext;)Ljnr/ffi/provider/ResultType;
        //    506: astore  25
        //    508: aload_0
        //    509: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    512: aload  10
        //    514: aload  19
        //    516: invokestatic  #188 // jnr.ffi.provider.jffi.InvokerUtil.getParameterTypes:(Ljnr/ffi/Runtime;Ljnr/ffi/mapper/SignatureTypeMapper;Ljava/lang/reflect/Method;)[Ljnr/ffi/provider/ParameterType;
        //    519: astore  26
        //    521: aload_3
        //    522: aload  18
        //    524: invokevirtual  #157 // jnr.ffi.provider.NativeFunction.hasSaveError:()Z
        //    527: aload  18
        //    529: invokevirtual  #156 // jnr.ffi.provider.NativeFunction.hasIgnoreError:()Z
        //    532: invokestatic  #146 // jnr.ffi.LibraryLoader.saveError:(Ljava/util/Map;ZZ)Z
        //    535: istore  27
        //    537: new  #20 // com.kenai.jffi.Function
        //    540: dup
        //    541: lload  21
        //    543: aload  25
        //    545: aload  26
        //    547: aload  18
        //    549: invokevirtual  #154 // jnr.ffi.provider.NativeFunction.convention:()Ljnr/ffi/CallingConvention;
        //    552: iload  27
        //    554: invokestatic  #186 // jnr.ffi.provider.jffi.InvokerUtil.getCallContext:(Ljnr/ffi/provider/SigType;[Ljnr/ffi/provider/SigType;Ljnr/ffi/CallingConvention;Z)Lcom/kenai/jffi/CallContext;
        //    557: invokespecial  #114 // com.kenai.jffi.Function.<init>:(JLcom/kenai/jffi/CallContext;)V
        //    560: astore  28
        //    562: aload  14
        //    564: astore  29
        //    566: aload  29
        //    568: arraylength
        //    569: istore  30
        //    571: iconst_0
        //    572: istore  31
        //    574: iload  31
        //    576: iload  30
        //    578: if_icmpge  646 (offset +68)
        //    581: aload  29
        //    583: iload  31
        //    585: aaload
        //    586: astore  32
        //    588: aload  32
        //    590: aload  25
        //    592: aload  26
        //    594: aload  18
        //    596: invokevirtual  #154 // jnr.ffi.provider.NativeFunction.convention:()Ljnr/ffi/CallingConvention;
        //    599: invokeinterface  #249 // jnr.ffi.provider.jffi.MethodGenerator.isSupported:(Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Ljnr/ffi/CallingConvention;)Z, count 4
        //    604: ifeq  640 (offset +36)
        //    607: aload  32
        //    609: aload  8
        //    611: aload  19
        //    613: invokevirtual  #138 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    616: aload  28
        //    618: aload  25
        //    620: aload  26
        //    622: iload  27
        //    624: ifne  631 (offset +7)
        //    627: iconst_1
        //    628: goto  632 (offset +4)
        //    631: iconst_0
        //    632: invokeinterface  #248 // jnr.ffi.provider.jffi.MethodGenerator.generate:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljava/lang/String;Lcom/kenai/jffi/Function;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Z)V, count 7
        //    637: goto  646 (offset +9)
        //    640: iinc  31, 1
        //    643: goto  574 (offset -69)
        //    646: goto  723 (offset +77)
        //    649: astore  21
        //    651: new  #36 // java.lang.StringBuilder
        //    654: dup
        //    655: invokespecial  #126 // java.lang.StringBuilder.<init>:()V
        //    658: ldc  #9 // 'error_'
        //    660: invokevirtual  #129 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    663: getstatic  #113 // jnr.ffi.provider.jffi.AsmLibraryLoader.uniqueId:Ljava/util/concurrent/atomic/AtomicLong;
        //    666: invokevirtual  #145 // java.util.concurrent.atomic.AtomicLong.incrementAndGet:()J
        //    669: invokevirtual  #127 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    672: invokevirtual  #130 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    675: astore  22
        //    677: aload  7
        //    679: bipush  26
        //    681: aload  22
        //    683: ldc  #35 // java.lang.String
        //    685: invokestatic  #178 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    688: aconst_null
        //    689: aload  21
        //    691: invokevirtual  #227 // jnr.ffi.provider.jffi.SymbolNotFoundError.getMessage:()Ljava/lang/String;
        //    694: invokevirtual  #236 // org.objectweb.asm.ClassVisitor.visitField:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/objectweb/asm/FieldVisitor;
        //    697: pop
        //    698: aload_0
        //    699: aload  7
        //    701: aload  8
        //    703: invokevirtual  #162 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    706: aload  22
        //    708: aload  20
        //    710: aload  19
        //    712: invokevirtual  #140 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    715: aload  19
        //    717: invokevirtual  #139 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    720: invokespecial  #168 // jnr.ffi.provider.jffi.AsmLibraryLoader.generateFunctionNotFound:(Lorg/objectweb/asm/ClassVisitor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    723: goto  336 (offset -387)
        //    726: new  #90 // jnr.ffi.provider.jffi.VariableAccessorGenerator
        //    729: dup
        //    730: aload_0
        //    731: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //    734: invokespecial  #228 // jnr.ffi.provider.jffi.VariableAccessorGenerator.<init>:(Ljnr/ffi/Runtime;)V
        //    737: astore  17
        //    739: aload  16
        //    741: invokevirtual  #152 // jnr.ffi.provider.InterfaceScanner.variables:()Ljava/util/Collection;
        //    744: invokeinterface  #240 // java.util.Collection.iterator:()Ljava/util/Iterator;, count 1
        //    749: astore  18
        //    751: aload  18
        //    753: invokeinterface  #241 // java.util.Iterator.hasNext:()Z, count 1
        //    758: ifeq  964 (offset +206)
        //    761: aload  18
        //    763: invokeinterface  #242 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    768: checkcast  #64 // jnr.ffi.provider.NativeVariable
        //    771: astore  19
        //    773: aload  19
        //    775: invokevirtual  #159 // jnr.ffi.provider.NativeVariable.getMethod:()Ljava/lang/reflect/Method;
        //    778: astore  20
        //    780: aload  20
        //    782: invokevirtual  #137 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    785: checkcast  #44 // java.lang.reflect.ParameterizedType
        //    788: invokeinterface  #239 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    793: iconst_0
        //    794: aaload
        //    795: astore  21
        //    797: aload  21
        //    799: instanceof  #26 // java.lang.Class
        //    802: ifne  833 (offset +31)
        //    805: new  #29 // java.lang.IllegalArgumentException
        //    808: dup
        //    809: new  #36 // java.lang.StringBuilder
        //    812: dup
        //    813: invokespecial  #126 // java.lang.StringBuilder.<init>:()V
        //    816: ldc  #17 // 'unsupported variable class: '
        //    818: invokevirtual  #129 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    821: aload  21
        //    823: invokevirtual  #128 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    826: invokevirtual  #130 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    829: invokespecial  #122 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    832: athrow
        //    833: aload  9
        //    835: aload  20
        //    837: invokevirtual  #138 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    840: aconst_null
        //    841: invokeinterface  #246 // jnr.ffi.mapper.FunctionMapper.mapFunctionName:(Ljava/lang/String;Ljnr/ffi/mapper/FunctionMapper$Context;)Ljava/lang/String;, count 3
        //    846: astore  22
        //    848: aload  17
        //    850: aload  8
        //    852: aload_2
        //    853: aload  20
        //    855: invokevirtual  #138 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    858: aload_1
        //    859: aload  22
        //    861: invokevirtual  #192 // jnr.ffi.provider.jffi.NativeLibrary.findSymbolAddress:(Ljava/lang/String;)J
        //    864: aload  21
        //    866: checkcast  #26 // java.lang.Class
        //    869: aload  20
        //    871: invokevirtual  #136 // java.lang.reflect.Method.getAnnotations:()[Ljava/lang/annotation/Annotation;
        //    874: invokestatic  #231 // jnr.ffi.util.Annotations.sortedAnnotationCollection:([Ljava/lang/annotation/Annotation;)Ljava/util/Collection;
        //    877: aload  10
        //    879: aload  4
        //    881: invokevirtual  #229 // jnr.ffi.provider.jffi.VariableAccessorGenerator.generate:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljava/lang/Class;Ljava/lang/String;JLjava/lang/Class;Ljava/util/Collection;Ljnr/ffi/mapper/SignatureTypeMapper;Ljnr/ffi/provider/jffi/AsmClassLoader;)V
        //    884: goto  961 (offset +77)
        //    887: astore  23
        //    889: new  #36 // java.lang.StringBuilder
        //    892: dup
        //    893: invokespecial  #126 // java.lang.StringBuilder.<init>:()V
        //    896: ldc  #9 // 'error_'
        //    898: invokevirtual  #129 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    901: getstatic  #113 // jnr.ffi.provider.jffi.AsmLibraryLoader.uniqueId:Ljava/util/concurrent/atomic/AtomicLong;
        //    904: invokevirtual  #145 // java.util.concurrent.atomic.AtomicLong.incrementAndGet:()J
        //    907: invokevirtual  #127 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    910: invokevirtual  #130 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    913: astore  24
        //    915: aload  7
        //    917: bipush  26
        //    919: aload  24
        //    921: ldc  #35 // java.lang.String
        //    923: invokestatic  #178 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    926: aconst_null
        //    927: aload  23
        //    929: invokevirtual  #227 // jnr.ffi.provider.jffi.SymbolNotFoundError.getMessage:()Ljava/lang/String;
        //    932: invokevirtual  #236 // org.objectweb.asm.ClassVisitor.visitField:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/objectweb/asm/FieldVisitor;
        //    935: pop
        //    936: aload_0
        //    937: aload  7
        //    939: aload  8
        //    941: invokevirtual  #162 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    944: aload  24
        //    946: aload  22
        //    948: aload  20
        //    950: invokevirtual  #140 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    953: aload  20
        //    955: invokevirtual  #139 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    958: invokespecial  #168 // jnr.ffi.provider.jffi.AsmLibraryLoader.generateFunctionNotFound:(Lorg/objectweb/asm/ClassVisitor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    961: goto  751 (offset -210)
        //    964: new  #87 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //    967: dup
        //    968: aload  7
        //    970: iconst_1
        //    971: ldc  #4 // '<init>'
        //    973: getstatic  #106 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    976: iconst_3
        //    977: anewarray  #26 // java.lang.Class
        //    980: dup
        //    981: iconst_0
        //    982: ldc  #51 // jnr.ffi.Runtime
        //    984: aastore
        //    985: dup
        //    986: iconst_1
        //    987: ldc  #82 // jnr.ffi.provider.jffi.NativeLibrary
        //    989: aastore
        //    990: dup
        //    991: iconst_2
        //    992: ldc  #19 // [Ljava.lang.Object;
        //    994: aastore
        //    995: invokestatic  #180 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    998: aconst_null
        //    999: aconst_null
        //   1000: invokespecial  #195 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //   1003: astore  18
        //   1005: aload  18
        //   1007: invokevirtual  #221 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //   1010: aload  18
        //   1012: iconst_0
        //   1013: invokevirtual  #197 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //   1016: aload  18
        //   1018: iconst_1
        //   1019: invokevirtual  #197 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //   1022: aload  18
        //   1024: iconst_2
        //   1025: invokevirtual  #197 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //   1028: aload  18
        //   1030: ldc  #65 // jnr.ffi.provider.jffi.AbstractAsmLibraryInterface
        //   1032: invokestatic  #179 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //   1035: ldc  #4 // '<init>'
        //   1037: getstatic  #106 // java.lang.Void.TYPE:Ljava/lang/Class;
        //   1040: iconst_2
        //   1041: anewarray  #26 // java.lang.Class
        //   1044: dup
        //   1045: iconst_0
        //   1046: ldc  #51 // jnr.ffi.Runtime
        //   1048: aastore
        //   1049: dup
        //   1050: iconst_1
        //   1051: ldc  #82 // jnr.ffi.provider.jffi.NativeLibrary
        //   1053: aastore
        //   1054: invokestatic  #180 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //   1057: invokevirtual  #214 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokespecial:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   1060: aload  8
        //   1062: aload  18
        //   1064: iconst_3
        //   1065: invokevirtual  #161 // jnr.ffi.provider.jffi.AsmBuilder.emitFieldInitialization:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;I)V
        //   1068: aload  18
        //   1070: invokevirtual  #224 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.voidreturn:()V
        //   1073: aload  18
        //   1075: bipush  10
        //   1077: bipush  10
        //   1079: invokevirtual  #223 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //   1082: aload  18
        //   1084: invokevirtual  #222 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //   1087: aload  7
        //   1089: invokevirtual  #235 // org.objectweb.asm.ClassVisitor.visitEnd:()V
        //   1092: aload  6
        //   1094: invokevirtual  #238 // org.objectweb.asm.ClassWriter.toByteArray:()[B
        //   1097: astore  19
        //   1099: iload  5
        //   1101: ifeq  1134 (offset +33)
        //   1104: new  #22 // java.io.PrintWriter
        //   1107: dup
        //   1108: getstatic  #104 // java.lang.System.err:Ljava/io/PrintStream;
        //   1111: invokespecial  #116 // java.io.PrintWriter.<init>:(Ljava/io/OutputStream;)V
        //   1114: invokestatic  #176 // jnr.ffi.provider.jffi.AsmUtil.newTraceClassVisitor:(Ljava/io/PrintWriter;)Lorg/objectweb/asm/ClassVisitor;
        //   1117: astore  20
        //   1119: new  #93 // org.objectweb.asm.ClassReader
        //   1122: dup
        //   1123: aload  19
        //   1125: invokespecial  #232 // org.objectweb.asm.ClassReader.<init>:([B)V
        //   1128: aload  20
        //   1130: iconst_0
        //   1131: invokevirtual  #233 // org.objectweb.asm.ClassReader.accept:(Lorg/objectweb/asm/ClassVisitor;I)V
        //   1134: aload  4
        //   1136: aload  8
        //   1138: invokevirtual  #162 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //   1141: ldc  #3 // '/'
        //   1143: ldc  #2 // '.'
        //   1145: invokevirtual  #125 // java.lang.String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   1148: aload  19
        //   1150: invokevirtual  #167 // jnr.ffi.provider.jffi.AsmClassLoader.defineClass:(Ljava/lang/String;[B)Ljava/lang/Class;
        //   1153: astore  20
        //   1155: aload  20
        //   1157: iconst_3
        //   1158: anewarray  #26 // java.lang.Class
        //   1161: dup
        //   1162: iconst_0
        //   1163: ldc  #51 // jnr.ffi.Runtime
        //   1165: aastore
        //   1166: dup
        //   1167: iconst_1
        //   1168: ldc  #82 // jnr.ffi.provider.jffi.NativeLibrary
        //   1170: aastore
        //   1171: dup
        //   1172: iconst_2
        //   1173: ldc  #19 // [Ljava.lang.Object;
        //   1175: aastore
        //   1176: invokevirtual  #119 // java.lang.Class.getDeclaredConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
        //   1179: astore  21
        //   1181: aload  21
        //   1183: iconst_3
        //   1184: anewarray  #32 // java.lang.Object
        //   1187: dup
        //   1188: iconst_0
        //   1189: aload_0
        //   1190: getfield  #112 // jnr.ffi.provider.jffi.AsmLibraryLoader.runtime:Ljnr/ffi/provider/jffi/NativeRuntime;
        //   1193: aastore
        //   1194: dup
        //   1195: iconst_1
        //   1196: aload_1
        //   1197: aastore
        //   1198: dup
        //   1199: iconst_2
        //   1200: aload  8
        //   1202: invokevirtual  #165 // jnr.ffi.provider.jffi.AsmBuilder.getObjectFieldValues:()[Ljava/lang/Object;
        //   1205: aastore
        //   1206: invokevirtual  #135 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //   1209: astore  22
        //   1211: getstatic  #104 // java.lang.System.err:Ljava/io/PrintStream;
        //   1214: invokevirtual  #115 // java.io.PrintStream.flush:()V
        //   1217: getstatic  #105 // java.lang.System.out:Ljava/io/PrintStream;
        //   1220: invokevirtual  #115 // java.io.PrintStream.flush:()V
        //   1223: aload  13
        //   1225: aload  20
        //   1227: invokevirtual  #225 // jnr.ffi.provider.jffi.StubCompiler.attach:(Ljava/lang/Class;)V
        //   1230: aload  22
        //   1232: areturn
        //   1233: astore  19
        //   1235: new  #33 // java.lang.RuntimeException
        //   1238: dup
        //   1239: aload  19
        //   1241: invokespecial  #124 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   1244: athrow
        //       Exception table:
        //         from 439 to 646 target 649 type jnr.ffi.provider.jffi.SymbolNotFoundError
        //         from 848 to 884 target 887 type jnr.ffi.provider.jffi.SymbolNotFoundError
        //         from 1092 to 1232 target 1233 type java.lang.Throwable
    }

  private void generateFunctionNotFound(ClassVisitor arg0, String arg1, String arg2, String arg3, Class arg4, Class[] arg5) {
        SkinnyMethodAdapter var7 = new SkinnyMethodAdapter(arg0, 17, arg3, CodegenUtils.sig(arg4, arg5), null, null);
        var7.start();
        var7.getstatic(arg1, arg2, CodegenUtils.ci(String.class));
        var7.invokestatic(AsmRuntime.class, "newUnsatisifiedLinkError", UnsatisfiedLinkError.class, new Class[]{String.class});
        var7.athrow();
        var7.visitMaxs(10, 10);
        var7.visitEnd();
    }

  private void generateVarargsInvocation(AsmBuilder arg0, Method arg1, AsmBuilder_ObjectField arg2) {
        Class[] var4 = arg1.getParameterTypes();
        SkinnyMethodAdapter var5 = new SkinnyMethodAdapter(arg0.getClassVisitor(), 17, arg1.getName(), CodegenUtils.sig(arg1.getReturnType(), var4), null, null);
        var5.start();
        var5.aload(0);
        var5.getfield(arg0.getClassNamePath(), arg2.name, CodegenUtils.ci(Invoker.class));
        var5.aload(0);
        var5.pushInt(var4.length);
        var5.anewarray(CodegenUtils.method1942(Object.class));
        int var6 = 1;
        int var7 = 0;
        while (var7 < var4.length) {
            var5.dup();
            var5.pushInt(var7);
            if (!var4[var7].equals(Long.TYPE)) {
                if (!var4[var7].equals(Double.TYPE)) {
                    if (!var4[var7].equals(Integer.TYPE)) {
                        if (!var4[var7].equals(Float.TYPE)) {
                            if (!var4[var7].equals(Short.TYPE)) {
                                if (!var4[var7].equals(Character.TYPE)) {
                                    if (!var4[var7].equals(Byte.TYPE)) {
                                        if (!var4[var7].equals(Character.TYPE)) {
                                            var5.aload(var6);
                                        } else {
                                            var5.iload(var6);
                                            var5.i2b();
                                            var5.invokestatic(Boolean.class, "valueOf", Boolean.class, new Class[]{Boolean.TYPE});
                                        }
                                    } else {
                                        var5.iload(var6);
                                        var5.i2b();
                                        var5.invokestatic(Byte.class, "valueOf", Byte.class, new Class[]{Byte.TYPE});
                                    }
                                } else {
                                    var5.iload(var6);
                                    var5.i2c();
                                    var5.invokestatic(Character.class, "valueOf", Character.class, new Class[]{Character.TYPE});
                                }
                            } else {
                                var5.iload(var6);
                                var5.i2s();
                                var5.invokestatic(Short.class, "valueOf", Short.class, new Class[]{Short.TYPE});
                            }
                        } else {
                            var5.fload(var6);
                            var5.invokestatic(Float.class, "valueOf", Float.class, new Class[]{Float.TYPE});
                        }
                    } else {
                        var5.iload(var6);
                        var5.invokestatic(Integer.class, "valueOf", Integer.class, new Class[]{Integer.TYPE});
                    }
                } else {
                    var5.dload(var6);
                    var5.invokestatic(Double.class, "valueOf", Double.class, new Class[]{Double.TYPE});
                    ++var6;
                }
            } else {
                var5.lload(var6);
                var5.invokestatic(Long.class, "valueOf", Long.class, new Class[]{Long.TYPE});
                ++var6;
            }
            var5.aastore();
            ++var6;
            ++var7;
            continue;
        }
        var5.invokeinterface(Invoker.class, "invoke", Object.class, new Class[]{Object.class, Object[].class});
        var7 = arg1.getReturnType();
        if (!var7.equals(Long.TYPE)) {
            if (!var7.equals(Double.TYPE)) {
                if (!var7.equals(Integer.TYPE)) {
                    if (!var7.equals(Float.TYPE)) {
                        if (!var7.equals(Short.TYPE)) {
                            if (!var7.equals(Character.TYPE)) {
                                if (!var7.equals(Byte.TYPE)) {
                                    if (!var7.equals(Boolean.TYPE)) {
                                        if (!Void.TYPE.isAssignableFrom(arg1.getReturnType())) {
                                            var5.checkcast(arg1.getReturnType());
                                            var5.areturn();
                                        } else {
                                            var5.voidreturn();
                                        }
                                    } else {
                                        var5.checkcast(Boolean.class);
                                        var5.invokevirtual(Boolean.class, "booleanValue", Boolean.TYPE, new Class[0]);
                                        var5.ireturn();
                                    }
                                } else {
                                    var5.checkcast(Byte.class);
                                    var5.invokevirtual(Byte.class, "byteValue", Byte.TYPE, new Class[0]);
                                    var5.ireturn();
                                }
                            } else {
                                var5.checkcast(Character.class);
                                var5.invokevirtual(Character.class, "charValue", Character.TYPE, new Class[0]);
                                var5.ireturn();
                            }
                        } else {
                            var5.checkcast(Short.class);
                            var5.invokevirtual(Short.class, "shortValue", Short.TYPE, new Class[0]);
                            var5.ireturn();
                        }
                    } else {
                        var5.checkcast(Float.class);
                        var5.invokevirtual(Float.class, "floatValue", Float.TYPE, new Class[0]);
                        var5.freturn();
                    }
                } else {
                    var5.checkcast(Integer.class);
                    var5.invokevirtual(Integer.class, "intValue", Integer.TYPE, new Class[0]);
                    var5.ireturn();
                }
            } else {
                var5.checkcast(Double.class);
                var5.invokevirtual(Double.class, "doubleValue", Double.TYPE, new Class[0]);
                var5.dreturn();
            }
        } else {
            var5.checkcast(Long.class);
            var5.invokevirtual(Long.class, "longValue", Long.TYPE, new Class[0]);
            var5.lreturn();
        }
        var5.visitMaxs(100, AsmUtil.calculateLocalVariableSpace(var4) + 1);
        var5.visitEnd();
    }

}