// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.FastIntMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Invoker;
import com.kenai.jffi.Platform;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.Util;

final class FastIntMethodGenerator extends AbstractFastNumericMethodGenerator {

    // ---- поля ----
  private static final boolean ENABLED;
  private static final int MAX_FASTINT_PARAMETERS;
  private static final String[] signatures;
  private static final String[] methodNames;

    static {
        ENABLED = Util.getBooleanProperty("jnr.ffi.fast-int.enabled", true);
        MAX_FASTINT_PARAMETERS = getMaximumFastIntParameters();
        methodNames = new String[]{"invokeI0", "invokeI1", "invokeI2", "invokeI3", "invokeI4", "invokeI5", "invokeI6"};
        signatures = new String[MAX_FASTINT_PARAMETERS + 1];
        int var0 = 0;
        while (var0 <= MAX_FASTINT_PARAMETERS) {
            StringBuilder var1 = new StringBuilder();
            var1.append('(').append(CodegenUtils.ci(CallContext.class)).append(CodegenUtils.ci(Long.TYPE));
            int var2 = 0;
            while (var2 < var0) {
                var1.append('I');
                ++var2;
                continue;
            }
            signatures[var0] = var1.append(")I").toString();
            ++var0;
            continue;
        }
    }

   FastIntMethodGenerator() { // было: <init>
        super();
    }

   String getInvokerMethodName(ResultType arg0, ParameterType[] arg1, boolean arg2) {
        int var4 = arg1.length;
        if (var4 > MAX_FASTINT_PARAMETERS) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(var4).toString());
        } else {
            if (var4 > methodNames.length) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(var4).toString());
            } else {
                return ((String) methodNames[var4]);
            }
        }
    }

   String getInvokerSignature(int arg0, Class arg1) {
        if (arg0 > MAX_FASTINT_PARAMETERS) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(arg0).toString());
        } else {
            if (arg0 > signatures.length) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(arg0).toString());
            } else {
                return ((String) signatures[arg0]);
            }
        }
    }

  final Class getInvokerType() {
        return Integer.TYPE;
    }

  public boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: arraylength
        //      2: istore  4
        //      4: getstatic  #43 // jnr.ffi.provider.jffi.FastIntMethodGenerator.ENABLED:Z
        //      7: ifne  12 (offset +5)
        //     10: iconst_0
        //     11: ireturn
        //     12: aload_3
        //     13: getstatic  #40 // jnr.ffi.CallingConvention.DEFAULT:Ljnr/ffi/CallingConvention;
        //     16: invokevirtual  #61 // jnr.ffi.CallingConvention.equals:(Ljava/lang/Object;)Z
        //     19: ifeq  30 (offset +11)
        //     22: iload  4
        //     24: getstatic  #44 // jnr.ffi.provider.jffi.FastIntMethodGenerator.MAX_FASTINT_PARAMETERS:I
        //     27: if_icmple  32 (offset +5)
        //     30: iconst_0
        //     31: ireturn
        //     32: invokestatic  #50 // com.kenai.jffi.Platform.getPlatform:()Lcom/kenai/jffi/Platform;
        //     35: astore  5
        //     37: aload  5
        //     39: invokevirtual  #49 // com.kenai.jffi.Platform.getOS:()Lcom/kenai/jffi/Platform$OS;
        //     42: getstatic  #37 // com.kenai.jffi.Platform$OS.WINDOWS:Lcom/kenai/jffi/Platform$OS;
        //     45: invokevirtual  #52 // com.kenai.jffi.Platform$OS.equals:(Ljava/lang/Object;)Z
        //     48: ifeq  53 (offset +5)
        //     51: iconst_0
        //     52: ireturn
        //     53: aload  5
        //     55: invokevirtual  #48 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     58: getstatic  #35 // com.kenai.jffi.Platform$CPU.I386:Lcom/kenai/jffi/Platform$CPU;
        //     61: invokevirtual  #51 // com.kenai.jffi.Platform$CPU.equals:(Ljava/lang/Object;)Z
        //     64: ifne  83 (offset +19)
        //     67: aload  5
        //     69: invokevirtual  #48 // com.kenai.jffi.Platform.getCPU:()Lcom/kenai/jffi/Platform$CPU;
        //     72: getstatic  #36 // com.kenai.jffi.Platform$CPU.X86_64:Lcom/kenai/jffi/Platform$CPU;
        //     75: invokevirtual  #51 // com.kenai.jffi.Platform$CPU.equals:(Ljava/lang/Object;)Z
        //     78: ifne  83 (offset +5)
        //     81: iconst_0
        //     82: ireturn
        //     83: aload_2
        //     84: astore  6
        //     86: aload  6
        //     88: arraylength
        //     89: istore  7
        //     91: iconst_0
        //     92: istore  8
        //     94: iload  8
        //     96: iload  7
        //     98: if_icmpge  126 (offset +28)
        //    101: aload  6
        //    103: iload  8
        //    105: aaload
        //    106: astore  9
        //    108: aload  5
        //    110: aload  9
        //    112: invokestatic  #70 // jnr.ffi.provider.jffi.FastIntMethodGenerator.isFastIntParameter:(Lcom/kenai/jffi/Platform;Ljnr/ffi/provider/ParameterType;)Z
        //    115: ifne  120 (offset +5)
        //    118: iconst_0
        //    119: ireturn
        //    120: iinc  8, 1
        //    123: goto  94 (offset -29)
        //    126: aload  5
        //    128: aload_1
        //    129: invokestatic  #71 // jnr.ffi.provider.jffi.FastIntMethodGenerator.isFastIntResult:(Lcom/kenai/jffi/Platform;Ljnr/ffi/provider/ResultType;)Z
        //    132: ireturn
    }

  static int getMaximumFastIntParameters() {
        int __stk2;
        try {
            Invoker.class.getDeclaredMethod("invokeI6", new Class[]{CallContext.class, Long.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
            __stk2 = 6;
        } catch (Throwable var0) {
            return 0;
        }
    }

  static boolean isFastIntType(Platform arg0, SigType arg1) {
        switch (arg1.getNativeType()) {
            case SCHAR:
            case UCHAR:
            case SSHORT:
            case USHORT:
            case SINT:
            case UINT:
            case SLONG:
            case ULONG:
                return NumberUtil.sizeof(arg1.getNativeType()) <= 4;
            default:
                return false;
        }
    }

  private static boolean isSupportedPointerParameterType(Class arg0) {
        return Pointer.class.isAssignableFrom(arg0);
    }

  static boolean isFastIntResult(Platform arg0, ResultType arg1) {
        return isFastIntType(arg0, arg1) ? 1 : arg1.getNativeType() == NativeType.VOID ? 1 : arg1.getNativeType() != NativeType.ADDRESS ? 0 : NumberUtil.sizeof(arg1) == 4;
    }

  static boolean isFastIntParameter(Platform arg0, ParameterType arg1) {
        return isFastIntType(arg0, arg1) ? 1 : arg1.getNativeType() != NativeType.ADDRESS ? 0 : NumberUtil.sizeof(arg1) != 4 ? 0 : isSupportedPointerParameterType(arg1.effectiveJavaType());
    }

}