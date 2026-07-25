// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.X86MethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.MethodGenerator;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.StubCompiler;
import jnr.ffi.provider.jffi.Util;

class X86MethodGenerator implements MethodGenerator {

    // ---- поля ----
  private static final boolean ENABLED;
  private final AtomicLong nextMethodID;
  private final StubCompiler compiler;

    static {
        ENABLED = Util.getBooleanProperty("jnr.ffi.x86asm.enabled", true);
    }

   X86MethodGenerator(StubCompiler arg0) { // было: <init>
        super();
        nextMethodID = new AtomicLong(0L);
        compiler = arg0;
    }

  public boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #65 // jnr.ffi.provider.jffi.X86MethodGenerator.ENABLED:Z
        //      3: ifne  8 (offset +5)
        //      6: iconst_0
        //      7: ireturn
        //      8: invokestatic  #72 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //     11: astore  4
        //     13: aload  4
        //     15: invokevirtual  #71 // com.kenai.jffi.Platform.getOS:()Lcom/kenai/jffi/Platform$OS;
        //     18: getstatic  #56 // com.kenai.jffi.Platform$OS.WINDOWS:Lcom/kenai/jffi/Platform$OS;
        //     21: invokevirtual  #74 // com.kenai.jffi.Platform$OS.equals:(Ljava/lang/Object;)Z
        //     24: ifeq  29 (offset +5)
        //     27: iconst_0
        //     28: ireturn
        //     29: aload  4
        //     31: invokevirtual  #70 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     34: getstatic  #54 // com.kenai.jffi.Platform$CPU.I386:Lcom/kenai/jffi/Platform$CPU;
        //     37: invokevirtual  #73 // com.kenai.jffi.Platform$CPU.equals:(Ljava/lang/Object;)Z
        //     40: ifne  73 (offset +33)
        //     43: aload  4
        //     45: invokevirtual  #70 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     48: getstatic  #55 // com.kenai.jffi.Platform$CPU.X86_64:Lcom/kenai/jffi/Platform$CPU;
        //     51: invokevirtual  #73 // com.kenai.jffi.Platform$CPU.equals:(Ljava/lang/Object;)Z
        //     54: ifne  73 (offset +19)
        //     57: aload  4
        //     59: invokevirtual  #70 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     62: getstatic  #53 // com.kenai.jffi.Platform$CPU.AARCH64:Lcom/kenai/jffi/Platform$CPU;
        //     65: invokevirtual  #73 // com.kenai.jffi.Platform$CPU.equals:(Ljava/lang/Object;)Z
        //     68: ifne  73 (offset +5)
        //     71: iconst_0
        //     72: ireturn
        //     73: aload_3
        //     74: getstatic  #63 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     77: invokevirtual  #87 // jnr.ffi.CallingConvention.equals:(Ljava/lang/Object;)Z
        //     80: ifne  85 (offset +5)
        //     83: iconst_0
        //     84: ireturn
        //     85: iconst_0
        //     86: istore  5
        //     88: iconst_0
        //     89: istore  6
        //     91: iload  6
        //     93: aload_2
        //     94: arraylength
        //     95: if_icmpge  129 (offset +34)
        //     98: aload_2
        //     99: iload  6
        //    101: aaload
        //    102: invokestatic  #157 // jnr.ffi.provider.jffi.X86MethodGenerator.isSupportedParameter:(Ljnr/ffi/provider/ParameterType;)Z
        //    105: ifne  110 (offset +5)
        //    108: iconst_0
        //    109: ireturn
        //    110: aload_2
        //    111: iload  6
        //    113: aaload
        //    114: invokestatic  #156 // jnr.ffi.provider.jffi.X86MethodGenerator.isSupportedObjectParameterType:(Ljnr/ffi/provider/ParameterType;)Z
        //    117: ifeq  123 (offset +6)
        //    120: iinc  5, 1
        //    123: iinc  6, 1
        //    126: goto  91 (offset -35)
        //    129: iload  5
        //    131: ifle  148 (offset +17)
        //    134: aload_2
        //    135: arraylength
        //    136: iconst_4
        //    137: if_icmpgt  146 (offset +9)
        //    140: iload  5
        //    142: iconst_3
        //    143: if_icmple  148 (offset +5)
        //    146: iconst_0
        //    147: ireturn
        //    148: aload_1
        //    149: invokestatic  #158 // jnr.ffi.provider.jffi.X86MethodGenerator.isSupportedResult:(Ljnr/ffi/provider/ResultType;)Z
        //    152: ifeq  172 (offset +20)
        //    155: aload_0
        //    156: getfield  #66 // jnr.ffi.provider.jffi.X86MethodGenerator.compiler:Ljnr/ffi/provider/jffi/StubCompiler;
        //    159: aload_1
        //    160: aload_2
        //    161: aload_3
        //    162: invokevirtual  #148 // jnr.ffi.provider.jffi.StubCompiler.canCompile:(Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Ljnr/ffi/CallingConvention;)Z
        //    165: ifeq  172 (offset +7)
        //    168: iconst_1
        //    169: goto  173 (offset +4)
        //    172: iconst_0
        //    173: ireturn
    }

  public void generate(AsmBuilder arg0, String arg1, Function arg2, ResultType arg3, ParameterType[] arg4, boolean arg5) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  5
        //      2: arraylength
        //      3: anewarray  #18 // java.lang.Class
        //      6: astore  7
        //      8: iconst_0
        //      9: istore  8
        //     11: iconst_0
        //     12: istore  9
        //     14: iload  9
        //     16: aload  5
        //     18: arraylength
        //     19: if_icmpge  109 (offset +90)
        //     22: iload  8
        //     24: aload  5
        //     26: iload  9
        //     28: aaload
        //     29: invokevirtual  #93 // jnr.ffi.provider.ParameterType.getToNativeConverter:()Ljnr/ffi/mapper/ToNativeConverter;
        //     32: ifnonnull  49 (offset +17)
        //     35: aload  5
        //     37: iload  9
        //     39: aaload
        //     40: invokevirtual  #90 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //     43: invokevirtual  #76 // java.lang.Class.isPrimitive:()Z
        //     46: ifne  53 (offset +7)
        //     49: iconst_1
        //     50: goto  54 (offset +4)
        //     53: iconst_0
        //     54: ior
        //     55: istore  8
        //     57: aload  5
        //     59: iload  9
        //     61: aaload
        //     62: invokevirtual  #90 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //     65: invokevirtual  #76 // java.lang.Class.isPrimitive:()Z
        //     68: ifne  90 (offset +22)
        //     71: aload  7
        //     73: iload  9
        //     75: aload  5
        //     77: iload  9
        //     79: aaload
        //     80: invokevirtual  #92 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //     83: invokestatic  #155 // jnr.ffi.provider.jffi.X86MethodGenerator.getNativeClass:(Ljnr/ffi/NativeType;)Ljava/lang/Class;
        //     86: aastore
        //     87: goto  103 (offset +16)
        //     90: aload  7
        //     92: iload  9
        //     94: aload  5
        //     96: iload  9
        //     98: aaload
        //     99: invokevirtual  #90 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //    102: aastore
        //    103: iinc  9, 1
        //    106: goto  14 (offset -92)
        //    109: iload  8
        //    111: aload  4
        //    113: invokevirtual  #96 // jnr.ffi.provider.ResultType.getFromNativeConverter:()Ljnr/ffi/mapper/FromNativeConverter;
        //    116: ifnonnull  144 (offset +28)
        //    119: aload  4
        //    121: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    124: invokevirtual  #76 // java.lang.Class.isPrimitive:()Z
        //    127: ifeq  144 (offset +17)
        //    130: getstatic  #57 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //    133: aload  4
        //    135: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    138: invokevirtual  #79 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    141: ifeq  148 (offset +7)
        //    144: iconst_1
        //    145: goto  149 (offset +4)
        //    148: iconst_0
        //    149: ior
        //    150: istore  8
        //    152: aload  4
        //    154: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    157: invokevirtual  #76 // java.lang.Class.isPrimitive:()Z
        //    160: ifeq  187 (offset +27)
        //    163: getstatic  #57 // java.lang.Boolean.TYPE:Ljava/lang/Class;
        //    166: aload  4
        //    168: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    171: invokevirtual  #79 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    174: ifne  187 (offset +13)
        //    177: aload  4
        //    179: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    182: astore  9
        //    184: goto  197 (offset +13)
        //    187: aload  4
        //    189: invokevirtual  #97 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    192: invokestatic  #155 // jnr.ffi.provider.jffi.X86MethodGenerator.getNativeClass:(Ljnr/ffi/NativeType;)Ljava/lang/Class;
        //    195: astore  9
        //    197: new  #25 // java.lang.StringBuilder
        //    200: dup
        //    201: invokespecial  #80 // java.lang.StringBuilder.<init>:()V
        //    204: aload_2
        //    205: invokevirtual  #83 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    208: iload  8
        //    210: ifeq  241 (offset +31)
        //    213: new  #25 // java.lang.StringBuilder
        //    216: dup
        //    217: invokespecial  #80 // java.lang.StringBuilder.<init>:()V
        //    220: ldc  #2 // '$jni$'
        //    222: invokevirtual  #83 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    225: aload_0
        //    226: getfield  #67 // jnr.ffi.provider.jffi.X86MethodGenerator.nextMethodID:Ljava/util/concurrent/atomic/AtomicLong;
        //    229: invokevirtual  #86 // java.util.concurrent.atomic.AtomicLong.incrementAndGet:()J
        //    232: invokevirtual  #81 // java.lang.StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    235: invokevirtual  #84 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    238: goto  243 (offset +5)
        //    241: ldc  #1 // ''
        //    243: invokevirtual  #83 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    246: invokevirtual  #84 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    249: astore  10
        //    251: aload_1
        //    252: invokevirtual  #106 // jnr.ffi.provider.jffi.AsmBuilder.getClassVisitor:()Lorg/objectweb/asm/ClassVisitor;
        //    255: sipush  273
        //    258: iload  8
        //    260: ifeq  268 (offset +8)
        //    263: bipush  8
        //    265: goto  269 (offset +4)
        //    268: iconst_0
        //    269: ior
        //    270: aload  10
        //    272: aload  9
        //    274: aload  7
        //    276: invokestatic  #117 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    279: aconst_null
        //    280: aconst_null
        //    281: invokevirtual  #160 // org.objectweb.asm.ClassVisitor.visitMethod:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/objectweb/asm/MethodVisitor;
        //    284: pop
        //    285: aload_0
        //    286: getfield  #66 // jnr.ffi.provider.jffi.X86MethodGenerator.compiler:Ljnr/ffi/provider/jffi/StubCompiler;
        //    289: aload_3
        //    290: aload  10
        //    292: aload  4
        //    294: aload  5
        //    296: aload  9
        //    298: aload  7
        //    300: getstatic  #63 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //    303: iload  6
        //    305: ifne  312 (offset +7)
        //    308: iconst_1
        //    309: goto  313 (offset +4)
        //    312: iconst_0
        //    313: invokevirtual  #149 // jnr.ffi.provider.jffi.StubCompiler.compile:(Lcom/kenai/jffi/Function;Ljava/lang/String;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Ljava/lang/Class;[Ljava/lang/Class;Ljnr/ffi/CallingConvention;Z)V
        //    316: iload  8
        //    318: ifeq  337 (offset +19)
        //    321: aload_1
        //    322: aload_2
        //    323: aload_3
        //    324: aload  4
        //    326: aload  5
        //    328: aload  10
        //    330: aload  9
        //    332: aload  7
        //    334: invokestatic  #154 // jnr.ffi.provider.jffi.X86MethodGenerator.generateWrapper:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljava/lang/String;Lcom/kenai/jffi/Function;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    337: return
    }

  private static void generateWrapper(AsmBuilder arg0, String arg1, Function arg2, ResultType arg3, ParameterType[] arg4, String arg5, Class arg6, Class[] arg7) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  4
        //      2: arraylength
        //      3: anewarray  #18 // java.lang.Class
        //      6: astore  8
        //      8: iconst_0
        //      9: istore  9
        //     11: iload  9
        //     13: aload  4
        //     15: arraylength
        //     16: if_icmpge  38 (offset +22)
        //     19: aload  8
        //     21: iload  9
        //     23: aload  4
        //     25: iload  9
        //     27: aaload
        //     28: invokevirtual  #91 // jnr.ffi.provider.ParameterType.getDeclaredType:()Ljava/lang/Class;
        //     31: aastore
        //     32: iinc  9, 1
        //     35: goto  11 (offset -24)
        //     38: new  #45 // jnr.ffi.provider.jffi.SkinnyMethodAdapter
        //     41: dup
        //     42: aload_0
        //     43: invokevirtual  #106 // jnr.ffi.provider.jffi.AsmBuilder.getClassVisitor:()Lorg/objectweb/asm/ClassVisitor;
        //     46: bipush  17
        //     48: aload_1
        //     49: aload_3
        //     50: invokevirtual  #95 // jnr.ffi.provider.ResultType.getDeclaredType:()Ljava/lang/Class;
        //     53: aload  8
        //     55: invokestatic  #117 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //     58: aconst_null
        //     59: aconst_null
        //     60: invokespecial  #125 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.<init>:(Lorg/objectweb/asm/ClassVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V
        //     63: astore  9
        //     65: aload  9
        //     67: aload  9
        //     69: invokevirtual  #129 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getMethodVisitor:()Lorg/objectweb/asm/MethodVisitor;
        //     72: invokestatic  #111 // jnr.ffi.provider.jffi.AsmUtil.newTraceMethodVisitor:(Lorg/objectweb/asm/MethodVisitor;)Lorg/objectweb/asm/MethodVisitor;
        //     75: invokevirtual  #143 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.setMethodVisitor:(Lorg/objectweb/asm/MethodVisitor;)V
        //     78: aload  9
        //     80: invokevirtual  #144 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.start:()V
        //     83: new  #41 // jnr.ffi.provider.jffi.LocalVariableAllocator
        //     86: dup
        //     87: aload  4
        //     89: invokespecial  #118 // jnr.ffi.provider.jffi.LocalVariableAllocator.<init>:([Ljnr/ffi/provider/SigType;)V
        //     92: astore  10
        //     94: aload  10
        //     96: getstatic  #60 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //     99: invokevirtual  #119 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    102: astore  11
        //    104: aload  4
        //    106: invokestatic  #110 // jnr.ffi.provider.jffi.AsmUtil.getParameterVariables:([Ljnr/ffi/provider/ParameterType;)[Ljnr/ffi/provider/jffi/LocalVariable;
        //    109: astore  12
        //    111: aload  4
        //    113: arraylength
        //    114: anewarray  #40 // jnr.ffi.provider.jffi.LocalVariable
        //    117: astore  13
        //    119: iconst_0
        //    120: istore  14
        //    122: iconst_0
        //    123: istore  15
        //    125: iload  15
        //    127: aload  4
        //    129: arraylength
        //    130: if_icmpge  289 (offset +159)
        //    133: aload  4
        //    135: iload  15
        //    137: aaload
        //    138: invokevirtual  #90 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //    141: astore  16
        //    143: aload  7
        //    145: iload  15
        //    147: aaload
        //    148: astore  17
        //    150: aload  13
        //    152: iload  15
        //    154: aload_0
        //    155: aload  9
        //    157: aload  10
        //    159: aload  12
        //    161: iload  15
        //    163: aaload
        //    164: aload  4
        //    166: iload  15
        //    168: aaload
        //    169: invokestatic  #114 // jnr.ffi.provider.jffi.BaseMethodGenerator.loadAndConvertParameter:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/jffi/LocalVariableAllocator;Ljnr/ffi/provider/jffi/LocalVariable;Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    172: aastore
        //    173: aload  4
        //    175: iload  15
        //    177: aaload
        //    178: invokestatic  #151 // jnr.ffi.provider.jffi.ToNativeOp.get:(Ljnr/ffi/provider/ToNativeType;)Ljnr/ffi/provider/jffi/ToNativeOp;
        //    181: astore  18
        //    183: aload  18
        //    185: ifnull  216 (offset +31)
        //    188: aload  18
        //    190: invokevirtual  #152 // jnr.ffi.provider.jffi.ToNativeOp.isPrimitive:()Z
        //    193: ifeq  216 (offset +23)
        //    196: aload  18
        //    198: aload  9
        //    200: aload  17
        //    202: aload  4
        //    204: iload  15
        //    206: aaload
        //    207: invokevirtual  #92 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    210: invokevirtual  #150 // jnr.ffi.provider.jffi.ToNativeOp.emitPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    213: goto  283 (offset +70)
        //    216: aload  16
        //    218: invokestatic  #103 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.hasPointerParameterStrategy:(Ljava/lang/Class;)Z
        //    221: ifeq  247 (offset +26)
        //    224: aload  9
        //    226: aload  16
        //    228: aload  17
        //    230: aload  13
        //    232: iload  15
        //    234: aaload
        //    235: aload  11
        //    237: iload  14
        //    239: invokestatic  #99 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitDirectCheck:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/provider/jffi/LocalVariable;Ljnr/ffi/provider/jffi/LocalVariable;I)I
        //    242: istore  14
        //    244: goto  283 (offset +39)
        //    247: aload  16
        //    249: invokevirtual  #76 // java.lang.Class.isPrimitive:()Z
        //    252: ifne  283 (offset +31)
        //    255: new  #21 // java.lang.IllegalArgumentException
        //    258: dup
        //    259: new  #25 // java.lang.StringBuilder
        //    262: dup
        //    263: invokespecial  #80 // java.lang.StringBuilder.<init>:()V
        //    266: ldc  #10 // 'unsupported type '
        //    268: invokevirtual  #83 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    271: aload  16
        //    273: invokevirtual  #82 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    276: invokevirtual  #84 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    279: invokespecial  #77 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    282: athrow
        //    283: iinc  15, 1
        //    286: goto  125 (offset -161)
        //    289: new  #52 // org.objectweb.asm.Label
        //    292: dup
        //    293: invokespecial  #161 // org.objectweb.asm.Label.<init>:()V
        //    296: astore  15
        //    298: new  #52 // org.objectweb.asm.Label
        //    301: dup
        //    302: invokespecial  #161 // org.objectweb.asm.Label.<init>:()V
        //    305: astore  16
        //    307: iload  14
        //    309: ifle  326 (offset +17)
        //    312: aload  9
        //    314: aload  11
        //    316: invokevirtual  #135 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    319: aload  9
        //    321: aload  15
        //    323: invokevirtual  #134 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.ifne:(Lorg/objectweb/asm/Label;)V
        //    326: aload  9
        //    328: aload_0
        //    329: invokevirtual  #105 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    332: aload  5
        //    334: aload  6
        //    336: aload  7
        //    338: invokestatic  #117 // jnr.ffi.provider.jffi.CodegenUtils.sig:(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/String;
        //    341: invokevirtual  #137 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    344: aload_3
        //    345: invokevirtual  #94 // jnr.ffi.provider.ResultType.effectiveJavaType:()Ljava/lang/Class;
        //    348: invokestatic  #112 // jnr.ffi.provider.jffi.AsmUtil.unboxedReturnType:(Ljava/lang/Class;)Ljava/lang/Class;
        //    351: astore  17
        //    353: aload  9
        //    355: aload  6
        //    357: aload  17
        //    359: invokestatic  #121 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    362: iload  14
        //    364: ifle  374 (offset +10)
        //    367: aload  9
        //    369: aload  16
        //    371: invokevirtual  #139 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    374: aload_0
        //    375: aload  9
        //    377: aload_3
        //    378: aload  4
        //    380: aload  12
        //    382: aload  13
        //    384: aconst_null
        //    385: invokestatic  #113 // jnr.ffi.provider.jffi.BaseMethodGenerator.emitEpilogue:(Ljnr/ffi/provider/jffi/AsmBuilder;Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljnr/ffi/provider/ResultType;[Ljnr/ffi/provider/ParameterType;[Ljnr/ffi/provider/jffi/LocalVariable;[Ljnr/ffi/provider/jffi/LocalVariable;Ljava/lang/Runnable;)V
        //    388: iload  14
        //    390: ifle  907 (offset +517)
        //    393: aload  9
        //    395: aload  15
        //    397: invokevirtual  #139 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.label:(Lorg/objectweb/asm/Label;)V
        //    400: aload  4
        //    402: arraylength
        //    403: anewarray  #40 // jnr.ffi.provider.jffi.LocalVariable
        //    406: astore  18
        //    408: aload  4
        //    410: arraylength
        //    411: iconst_1
        //    412: isub
        //    413: istore  19
        //    415: iload  19
        //    417: iflt  547 (offset +130)
        //    420: aload  18
        //    422: iload  19
        //    424: aload  10
        //    426: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    429: invokevirtual  #119 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    432: aastore
        //    433: getstatic  #59 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    436: aload  7
        //    438: iload  19
        //    440: aaload
        //    441: if_acmpne  474 (offset +33)
        //    444: aload  9
        //    446: ldc  #20 // java.lang.Float
        //    448: ldc  #5 // 'floatToRawIntBits'
        //    450: getstatic  #60 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    453: iconst_1
        //    454: anewarray  #18 // java.lang.Class
        //    457: dup
        //    458: iconst_0
        //    459: getstatic  #59 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    462: aastore
        //    463: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    466: aload  9
        //    468: invokevirtual  #133 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.i2l:()V
        //    471: goto  531 (offset +60)
        //    474: getstatic  #58 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    477: aload  7
        //    479: iload  19
        //    481: aaload
        //    482: if_acmpne  510 (offset +28)
        //    485: aload  9
        //    487: ldc  #19 // java.lang.Double
        //    489: ldc  #3 // 'doubleToRawLongBits'
        //    491: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    494: iconst_1
        //    495: anewarray  #18 // java.lang.Class
        //    498: dup
        //    499: iconst_0
        //    500: getstatic  #58 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    503: aastore
        //    504: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    507: goto  531 (offset +24)
        //    510: aload  9
        //    512: aload  7
        //    514: iload  19
        //    516: aaload
        //    517: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    520: aload  4
        //    522: iload  19
        //    524: aaload
        //    525: invokevirtual  #92 // jnr.ffi.provider.ParameterType.getNativeType:()Ljnr/ffi/NativeType;
        //    528: invokestatic  #122 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    531: aload  9
        //    533: aload  18
        //    535: iload  19
        //    537: aaload
        //    538: invokevirtual  #141 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lstore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    541: iinc  19, -1
        //    544: goto  415 (offset -129)
        //    547: aload  9
        //    549: ldc  #34 // jnr.ffi.provider.jffi.AbstractAsmLibraryInterface
        //    551: invokestatic  #116 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    554: ldc  #4 // 'ffi'
        //    556: ldc  #12 // com.kenai.jffi.Invoker
        //    558: invokestatic  #115 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    561: invokevirtual  #131 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getstatic:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    564: aload  9
        //    566: iconst_0
        //    567: invokevirtual  #126 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    570: aload  9
        //    572: aload_0
        //    573: invokevirtual  #105 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    576: aload_0
        //    577: aload_2
        //    578: invokevirtual  #104 // jnr.ffi.provider.jffi.AsmBuilder.getCallContextFieldName:(Lcom/kenai/jffi/Function;)Ljava/lang/String;
        //    581: ldc  #11 // com.kenai.jffi.CallContext
        //    583: invokestatic  #115 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    586: invokevirtual  #130 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    589: aload  9
        //    591: iconst_0
        //    592: invokevirtual  #126 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    595: aload  9
        //    597: aload_0
        //    598: invokevirtual  #105 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    601: aload_0
        //    602: aload_2
        //    603: invokevirtual  #107 // jnr.ffi.provider.jffi.AsmBuilder.getFunctionAddressFieldName:(Lcom/kenai/jffi/Function;)Ljava/lang/String;
        //    606: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    609: invokestatic  #115 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    612: invokevirtual  #130 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    615: aload  9
        //    617: aload  18
        //    619: invokevirtual  #140 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.lload:([Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    622: aload  9
        //    624: aload  11
        //    626: invokevirtual  #135 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.iload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    629: iconst_0
        //    630: istore  19
        //    632: iload  19
        //    634: aload  4
        //    636: arraylength
        //    637: if_icmpge  772 (offset +135)
        //    640: aload  4
        //    642: arraylength
        //    643: anewarray  #40 // jnr.ffi.provider.jffi.LocalVariable
        //    646: astore  20
        //    648: aload  4
        //    650: iload  19
        //    652: aaload
        //    653: invokevirtual  #90 // jnr.ffi.provider.ParameterType.effectiveJavaType:()Ljava/lang/Class;
        //    656: astore  21
        //    658: aload  21
        //    660: invokestatic  #103 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.hasPointerParameterStrategy:(Ljava/lang/Class;)Z
        //    663: ifeq  766 (offset +103)
        //    666: aload  9
        //    668: aload  13
        //    670: iload  19
        //    672: aaload
        //    673: invokevirtual  #127 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    676: aload  9
        //    678: aload  21
        //    680: invokestatic  #100 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.emitParameterStrategyLookup:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;)Ljava/lang/Class;
        //    683: pop
        //    684: aload  9
        //    686: aload  20
        //    688: iload  19
        //    690: aload  10
        //    692: ldc  #44 // jnr.ffi.provider.jffi.ParameterStrategy
        //    694: invokevirtual  #119 // jnr.ffi.provider.jffi.LocalVariableAllocator.allocate:(Ljava/lang/Class;)Ljnr/ffi/provider/jffi/LocalVariable;
        //    697: dup_x2
        //    698: aastore
        //    699: invokevirtual  #128 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.astore:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    702: aload  9
        //    704: aload  13
        //    706: iload  19
        //    708: aaload
        //    709: invokevirtual  #127 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    712: aload  9
        //    714: aload  20
        //    716: iload  19
        //    718: aaload
        //    719: invokevirtual  #127 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(Ljnr/ffi/provider/jffi/LocalVariable;)V
        //    722: iload  19
        //    724: aload  4
        //    726: iload  19
        //    728: aaload
        //    729: invokevirtual  #89 // jnr.ffi.provider.ParameterType.annotations:()Ljava/util/Collection;
        //    732: invokestatic  #109 // jnr.ffi.provider.jffi.AsmUtil.getNativeArrayFlags:(Ljava/util/Collection;)I
        //    735: invokestatic  #69 // com.kenai.jffi.ObjectParameterInfo.create:(II)Lcom/kenai/jffi/ObjectParameterInfo;
        //    738: astore  22
        //    740: aload  9
        //    742: iconst_0
        //    743: invokevirtual  #126 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.aload:(I)V
        //    746: aload  9
        //    748: aload_0
        //    749: invokevirtual  #105 // jnr.ffi.provider.jffi.AsmBuilder.getClassNamePath:()Ljava/lang/String;
        //    752: aload_0
        //    753: aload  22
        //    755: invokevirtual  #108 // jnr.ffi.provider.jffi.AsmBuilder.getObjectParameterInfoName:(Lcom/kenai/jffi/ObjectParameterInfo;)Ljava/lang/String;
        //    758: ldc  #13 // com.kenai.jffi.ObjectParameterInfo
        //    760: invokestatic  #115 // jnr.ffi.provider.jffi.CodegenUtils.ci:(Ljava/lang/Class;)Ljava/lang/String;
        //    763: invokevirtual  #130 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.getfield:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    766: iinc  19, 1
        //    769: goto  632 (offset -137)
        //    772: aload  9
        //    774: ldc  #12 // com.kenai.jffi.Invoker
        //    776: invokestatic  #116 // jnr.ffi.provider.jffi.CodegenUtils.p:(Ljava/lang/Class;)Ljava/lang/String;
        //    779: aload  4
        //    781: arraylength
        //    782: invokestatic  #101 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getObjectParameterMethodName:(I)Ljava/lang/String;
        //    785: aload  4
        //    787: arraylength
        //    788: iload  14
        //    790: invokestatic  #102 // jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator.getObjectParameterMethodSignature:(II)Ljava/lang/String;
        //    793: invokevirtual  #138 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokevirtual:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    796: getstatic  #59 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    799: aload  6
        //    801: if_acmpne  840 (offset +39)
        //    804: aload  9
        //    806: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    809: getstatic  #60 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    812: invokestatic  #123 // jnr.ffi.provider.jffi.NumberUtil.narrow:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;)V
        //    815: aload  9
        //    817: ldc  #20 // java.lang.Float
        //    819: ldc  #6 // 'intBitsToFloat'
        //    821: getstatic  #59 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    824: iconst_1
        //    825: anewarray  #18 // java.lang.Class
        //    828: dup
        //    829: iconst_0
        //    830: getstatic  #60 // java.lang.Integer.TYPE:Ljava/lang/Class;
        //    833: aastore
        //    834: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    837: goto  886 (offset +49)
        //    840: getstatic  #58 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    843: aload  6
        //    845: if_acmpne  873 (offset +28)
        //    848: aload  9
        //    850: ldc  #19 // java.lang.Double
        //    852: ldc  #8 // 'longBitsToDouble'
        //    854: getstatic  #58 // java.lang.Double.TYPE:Ljava/lang/Class;
        //    857: iconst_1
        //    858: anewarray  #18 // java.lang.Class
        //    861: dup
        //    862: iconst_0
        //    863: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    866: aastore
        //    867: invokevirtual  #136 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.invokestatic:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;)V
        //    870: goto  886 (offset +16)
        //    873: getstatic  #62 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    876: aload  6
        //    878: if_acmpne  886 (offset +8)
        //    881: aload  9
        //    883: invokevirtual  #142 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.pop2:()V
        //    886: aload  9
        //    888: getstatic  #61 // java.lang.Long.TYPE:Ljava/lang/Class;
        //    891: aload  17
        //    893: aload_3
        //    894: invokevirtual  #97 // jnr.ffi.provider.ResultType.getNativeType:()Ljnr/ffi/NativeType;
        //    897: invokestatic  #122 // jnr.ffi.provider.jffi.NumberUtil.convertPrimitive:(Ljnr/ffi/provider/jffi/SkinnyMethodAdapter;Ljava/lang/Class;Ljava/lang/Class;Ljnr/ffi/NativeType;)V
        //    900: aload  9
        //    902: aload  16
        //    904: invokevirtual  #132 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.go_to:(Lorg/objectweb/asm/Label;)V
        //    907: aload  9
        //    909: bipush  100
        //    911: aload  10
        //    913: invokevirtual  #120 // jnr.ffi.provider.jffi.LocalVariableAllocator.getSpaceUsed:()I
        //    916: invokevirtual  #146 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitMaxs:(II)V
        //    919: aload  9
        //    921: invokevirtual  #145 // jnr.ffi.provider.jffi.SkinnyMethodAdapter.visitEnd:()V
        //    924: return
    }

   void attach(Class arg0) {
        compiler.attach(arg0);
    }

  private static boolean isSupportedObjectParameterType(ParameterType arg0) {
        return Pointer.class.isAssignableFrom(arg0.effectiveJavaType());
    }

  private static boolean isSupportedType(SigType arg0) {
        switch (arg0.getNativeType()) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
            case SLONGLONG:
            case ULONGLONG:
            case FLOAT:
            case DOUBLE:
                return true;
            default:
                return false;
        }
    }

  static boolean isSupportedResult(ResultType arg0) {
        return isSupportedType(arg0) ? 1 : Void.TYPE == arg0.effectiveJavaType() ? 1 : arg0.getNativeType() == NativeType.ADDRESS;
    }

  static boolean isSupportedParameter(ParameterType arg0) {
        return isSupportedType(arg0) ? 1 : isSupportedObjectParameterType(arg0);
    }

  static Class getNativeClass(NativeType arg0) {
        switch (arg0) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
            case SLONGLONG:
            case ULONGLONG:
            case ADDRESS:
                return NumberUtil.sizeof(arg0) > 4 ? Long.TYPE : Integer.TYPE;
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