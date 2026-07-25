// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.FastLongMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Function;
import com.kenai.jffi.Invoker;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_CPU;
import com.kenai.jffi.Platform_OS;
import jnr.ffi.CallingConvention;
import jnr.ffi.NativeType;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.SigType;
import jnr.ffi.provider.jffi.AbstractFastNumericMethodGenerator;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.FastIntMethodGenerator;
import jnr.ffi.provider.jffi.LocalVariableAllocator;
import jnr.ffi.provider.jffi.NumberUtil;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.Util;

public class FastLongMethodGenerator extends AbstractFastNumericMethodGenerator {

    // ---- поля ----
  private static final boolean ENABLED;
  private static final int MAX_PARAMETERS;
  private static final String[] signatures;
  private static final String[] methodNames;

    static {
        ENABLED = Util.getBooleanProperty("jnr.ffi.fast-long.enabled", true);
        MAX_PARAMETERS = getMaximumFastLongParameters();
        methodNames = new String[]{"invokeL0", "invokeL1", "invokeL2", "invokeL3", "invokeL4", "invokeL5", "invokeL6"};
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

  public FastLongMethodGenerator() { // было: <init>
        super();
    }

   String getInvokerMethodName(ResultType arg0, ParameterType[] arg1, boolean arg2) {
        int var4 = arg1.length;
        if (var4 > MAX_PARAMETERS) {
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
        if (arg0 > MAX_PARAMETERS) {
            throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(arg0).toString());
        } else {
            if (arg0 > signatures.length) {
                throw new IllegalArgumentException(new StringBuilder().append("invalid fast-int parameter count: ").append(arg0).toString());
            } else {
                return ((String) signatures[arg0]);
            }
        }
    }

   Class getInvokerType() {
        return Long.TYPE;
    }

  public boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        int var4 = arg1.length;
        if (!ENABLED) {
            return false;
        }
        if (arg2 != CallingConvention.DEFAULT) {
            return false;
        }
        if (var4 > MAX_PARAMETERS) {
            return false;
        }
        Platform var5 = Platform.getPlatform();
        if (var5.getCPU() != Platform_CPU.X86_64) {
            return false;
        }
        if (!var5.getOS().equals(Platform_OS.WINDOWS)) {
            ParameterType[] var6 = arg1;
            int var7 = var6.length;
            int var8 = 0;
        } else {
            return false;
        }
        while (true) {
            if (var8 >= var7) {
                return isFastLongResult(var5, arg0);
            }
            Object var9 = var6[var8];
            if (!isFastLongParameter(var5, ((ParameterType) var9))) {
                break;
            }
            ++var8;
            continue;
        }
        return false;
    }

  static int getMaximumFastLongParameters() {
        int __stk2;
        try {
            Invoker.class.getDeclaredMethod("invokeL6", new Class[]{CallContext.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE});
            __stk2 = 6;
        } catch (Throwable var0) {
            return 0;
        }
    }

  private static boolean isFastLongType(Platform arg0, SigType arg1) {
        return FastIntMethodGenerator.isFastIntType(arg0, arg1) ? 1 : arg1.getNativeType() != NativeType.ADDRESS ? arg1.getNativeType() == NativeType.SLONG ? 1 : arg1.getNativeType() == NativeType.ULONG ? 1 : arg1.getNativeType() == NativeType.SLONGLONG ? 1 : arg1.getNativeType() == NativeType.ULONGLONG : NumberUtil.sizeof(NativeType.ADDRESS) == 8 ? 1 : arg1.getNativeType() == NativeType.SLONG ? 1 : arg1.getNativeType() == NativeType.ULONG ? 1 : arg1.getNativeType() == NativeType.SLONGLONG ? 1 : arg1.getNativeType() == NativeType.ULONGLONG;
    }

  static boolean isFastLongResult(Platform arg0, ResultType arg1) {
        return isFastLongType(arg0, arg1) ? 1 : arg1.getNativeType() == NativeType.VOID ? 1 : arg1.getNativeType() != NativeType.ADDRESS ? 0 : NumberUtil.sizeof(NativeType.ADDRESS) == 8;
    }

  static boolean isFastLongParameter(Platform arg0, ParameterType arg1) {
        return isFastLongType(arg0, arg1);
    }

  public void generate(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, CallContext arg3, ResultType arg4, ParameterType[] arg5, boolean arg6) {
        super.generate(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public void generate(AsmBuilder arg0, String arg1, Function arg2, ResultType arg3, ParameterType[] arg4, boolean arg5) {
        super.generate(arg0, arg1, arg2, arg3, arg4, arg5);
    }

}