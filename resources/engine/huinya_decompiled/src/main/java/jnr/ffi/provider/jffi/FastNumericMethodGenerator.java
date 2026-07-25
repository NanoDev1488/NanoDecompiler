// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.FastNumericMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Invoker;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Type;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.FastIntMethodGenerator;
import jnr.ffi.provider.jffi.Util;

class FastNumericMethodGenerator extends AbstractFastNumericMethodGenerator {

    // ---- поля ----
  private static final boolean ENABLED;
  private static final int MAX_PARAMETERS;
  private static final String[] signatures;
  private static final String[] methodNames;

    static {
        ENABLED = Util.getBooleanProperty("jnr.ffi.fast-numeric.enabled", true);
        MAX_PARAMETERS = getMaximumParameters();
        methodNames = new String[]{"invokeN0", "invokeN1", "invokeN2", "invokeN3", "invokeN4", "invokeN5", "invokeN6"};
        signatures = new String[MAX_PARAMETERS + 1];
        int var0 = 0;
        while (var0 <= MAX_PARAMETERS) {
            StringBuilder var1 = new StringBuilder();
            var1.append('(').append(CodegenUtils.ci(CallContext.class)).append(CodegenUtils.ci(Long.TYPE));
            int var2 = 0;
            while (var2 < var0) {
                var1.append('J');
                ++var2;
                continue;
            }
            signatures[var0] = var1.append(")J").toString();
            ++var0;
            continue;
        }
    }

   FastNumericMethodGenerator() { // было: <init>
        super();
    }

  public boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: arraylength
        //      2: istore  4
        //      4: getstatic  #61 // jnr.ffi.provider.jffi.FastNumericMethodGenerator.ENABLED:Z
        //      7: ifne  12 (offset +5)
        //     10: iconst_0
        //     11: ireturn
        //     12: aload_3
        //     13: getstatic  #52 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     16: if_acmpne  27 (offset +11)
        //     19: iload  4
        //     21: getstatic  #62 // jnr.ffi.provider.jffi.FastNumericMethodGenerator.MAX_PARAMETERS:I
        //     24: if_icmple  29 (offset +5)
        //     27: iconst_0
        //     28: ireturn
        //     29: invokestatic  #67 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //     32: astore  5
        //     34: aload  5
        //     36: invokevirtual  #65 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     39: getstatic  #47 // com.kenai.jffi.Platform$CPU.I386:Lcom/kenai/jffi/Platform$CPU;
        //     42: if_acmpeq  58 (offset +16)
        //     45: aload  5
        //     47: invokevirtual  #65 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     50: getstatic  #48 // com.kenai.jffi.Platform$CPU.X86_64:Lcom/kenai/jffi/Platform$CPU;
        //     53: if_acmpeq  58 (offset +5)
        //     56: iconst_0
        //     57: ireturn
        //     58: aload  5
        //     60: invokevirtual  #66 // com.kenai.jffi.Platform.getOS:()Lcom/kenai/jffi/Platform$OS;
        //     63: getstatic  #49 // com.kenai.jffi.Platform$OS.WINDOWS:Lcom/kenai/jffi/Platform$OS;
        //     66: invokevirtual  #68 // com.kenai.jffi.Platform$OS.equals:(Ljava/lang/Object;)Z
        //     69: ifeq  74 (offset +5)
        //     72: iconst_0
        //     73: ireturn
        //     74: aload_2
        //     75: astore  6
        //     77: aload  6
        //     79: arraylength
        //     80: istore  7
        //     82: iconst_0
        //     83: istore  8
        //     85: iload  8
        //     87: iload  7
        //     89: if_icmpge  117 (offset +28)
        //     92: aload  6
        //     94: iload  8
        //     96: aaload
        //     97: astore  9
        //     99: aload  5
        //    101: aload  9
        //    103: invokestatic  #86 // jnr.ffi.provider.jffi.FastNumericMethodGenerator.isFastNumericParameter:(Lcom/kenai/jffi/Platform;Ljnr/ffi/provider/ParameterType;)Z
        //    106: ifne  111 (offset +5)
        //    109: iconst_0
        //    110: ireturn
        //    111: iinc  8, 1
        //    114: goto  85 (offset -29)
        //    117: aload  5
        //    119: aload_1
        //    120: invokestatic  #87 // jnr.ffi.provider.jffi.FastNumericMethodGenerator.isFastNumericResult:(Lcom/kenai/jffi/Platform;Ljnr/ffi/provider/ResultType;)Z
        //    123: ireturn
    }

   String getInvokerMethodName(ResultType arg0, ParameterType[] arg1, boolean arg2) {
        int var4 = arg1.length;
        if (var4 > MAX_PARAMETERS) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid fast-numeric parameter count: ").append(var4).toString());
        } else {
            if (var4 > methodNames.length) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid fast-numeric parameter count: ").append(var4).toString());
            } else {
                return ((String) methodNames[var4]);
            }
        }
    }

   String getInvokerSignature(int arg0, Class arg1) {
        if (arg0 > MAX_PARAMETERS) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid fast-numeric parameter count: ").append(arg0).toString());
        } else {
            if (arg0 > signatures.length) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid fast-numeric parameter count: ").append(arg0).toString());
            } else {
                return ((String) signatures[arg0]);
            }
        }
    }

   Class getInvokerType() {
        return Long.TYPE;
    }

  private static boolean isNumericType(Platform arg0, SigType arg1) {
        return FastIntMethodGenerator.isFastIntType(arg0, arg1) ? 1 : arg1.getNativeType() == NativeType.SLONG ? 1 : arg1.getNativeType() == NativeType.ULONG ? 1 : arg1.getNativeType() == NativeType.SLONGLONG ? 1 : arg1.getNativeType() == NativeType.ULONGLONG ? 1 : arg1.getNativeType() == NativeType.FLOAT ? 1 : arg1.getNativeType() == NativeType.DOUBLE;
    }

  static boolean isFastNumericResult(Platform arg0, ResultType arg1) {
        return isNumericType(arg0, arg1) ? 1 : NativeType.VOID == arg1.getNativeType() ? 1 : NativeType.ADDRESS == arg1.getNativeType();
    }

  static boolean isFastNumericParameter(Platform arg0, ParameterType arg1) {
        return isNumericType(arg0, arg1) ? 1 : arg1.getNativeType() != NativeType.ADDRESS ? 0 : isSupportedPointerParameterType(arg1.effectiveJavaType());
    }

  private static boolean isSupportedPointerParameterType(Class arg0) {
        return Pointer.class.isAssignableFrom(arg0) ? 1 : ByteBuffer.class.isAssignableFrom(arg0) ? 1 : ShortBuffer.class.isAssignableFrom(arg0) ? 1 : IntBuffer.class.isAssignableFrom(arg0) ? 1 : !LongBuffer.class.isAssignableFrom(arg0) ? FloatBuffer.class.isAssignableFrom(arg0) ? 1 : DoubleBuffer.class.isAssignableFrom(arg0) ? 1 : byte[].class == arg0 ? 1 : short[].class == arg0 ? 1 : int[].class == arg0 ? 1 : long[].class != arg0 ? float[].class == arg0 ? 1 : double[].class == arg0 ? 1 : boolean[].class == arg0 : Type.SLONG.size() == 8 ? 1 : float[].class == arg0 ? 1 : double[].class == arg0 ? 1 : boolean[].class == arg0 : Type.SLONG.size() == 8 ? 1 : FloatBuffer.class.isAssignableFrom(arg0) ? 1 : DoubleBuffer.class.isAssignableFrom(arg0) ? 1 : byte[].class == arg0 ? 1 : short[].class == arg0 ? 1 : int[].class == arg0 ? 1 : long[].class != arg0 ? float[].class == arg0 ? 1 : double[].class == arg0 ? 1 : boolean[].class == arg0 : Type.SLONG.size() == 8 ? 1 : float[].class == arg0 ? 1 : double[].class == arg0 ? 1 : boolean[].class == arg0;
    }

  static int getMaximumParameters() {
        int __stk2;
        try {
            Invoker.class.getDeclaredMethod("invokeN6", new Class[]{CallContext.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE});
            __stk2 = 6;
        } catch (Throwable var0) {
            return 0;
        }
    }

}