// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.BaseMethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Function;
import com.kenai.jffi.Invoker;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.ToNativeType;
import jnr.ffi.provider.jffi.AbstractAsmLibraryInterface;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmBuilder_ObjectField;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.BaseMethodGenerator_Anon1;
import jnr.ffi.provider.jffi.BaseMethodGenerator_Anon2;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.LocalVariable;
import jnr.ffi.provider.jffi.LocalVariableAllocator;
import jnr.ffi.provider.jffi.MethodGenerator;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;

abstract class BaseMethodGenerator implements MethodGenerator {

   BaseMethodGenerator() { // было: <init>
        super();
    }

  public void generate(AsmBuilder arg0, String arg1, Function arg2, ResultType arg3, ParameterType[] arg4, boolean arg5) {
        Class[] var7 = new Class[arg4.length];
        int var8 = 0;
        while (var8 < arg4.length) {
            var7[var8] = arg4[var8].getDeclaredType();
            ++var8;
            continue;
        }
        var8 = new SkinnyMethodAdapter(arg0.getClassVisitor(), 17, arg1, CodegenUtils.sig(arg3.getDeclaredType(), var7), null, null);
        var8.start();
        var8.getstatic(CodegenUtils.method1942(AbstractAsmLibraryInterface.class), "ffi", CodegenUtils.ci(Invoker.class));
        var8.aload(0);
        var8.getfield(arg0.getClassNamePath(), arg0.getCallContextFieldName(arg2.getCallContext()), CodegenUtils.ci(CallContext.class));
        var8.aload(0);
        var8.getfield(arg0.getClassNamePath(), arg0.getFunctionAddressFieldName(arg2), CodegenUtils.ci(Long.TYPE));
        LocalVariableAllocator var9 = new LocalVariableAllocator(arg4);
        generate(arg0, var8, var9, arg2.getCallContext(), arg3, arg4, arg5);
        var8.visitMaxs(100, var9.getSpaceUsed());
        var8.visitEnd();
    }

  abstract void generate(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, CallContext arg3, ResultType arg4, ParameterType[] arg5, boolean arg6);

  static LocalVariable loadAndConvertParameter(AsmBuilder arg0, SkinnyMethodAdapter arg1, LocalVariableAllocator arg2, LocalVariable arg3, ToNativeType arg4) {
        AsmUtil.load(arg1, arg4.getDeclaredType(), arg3);
        AsmUtil.emitToNativeConversion(arg0, arg1, arg4);
        if (arg4.getToNativeConverter() == null) {
            return arg3;
        } else {
            LocalVariable var5 = arg2.allocate(arg4.getToNativeConverter().nativeType());
            arg1.astore(var5);
            arg1.aload(var5);
            return var5;
        }
    }

  static boolean isPostInvokeRequired(ParameterType[] arg0) {
        ParameterType[] var1 = arg0;
        int var2 = var1.length;
        int var3 = 0;
        while (true) {
            if (var3 >= var2) {
                return false;
            }
            Object var4 = var1[var3];
            if (var4.getToNativeConverter() instanceof ToNativeConverter_PostInvocation) {
                break;
            }
            ++var3;
            continue;
        }
        return true;
    }

  static void emitEpilogue(AsmBuilder arg0, SkinnyMethodAdapter arg1, ResultType arg2, ParameterType[] arg3, LocalVariable[] arg4, LocalVariable[] arg5, Runnable arg6) {
        Class var7 = AsmUtil.unboxedReturnType(arg2.effectiveJavaType());
        if (isPostInvokeRequired(arg3)) {
            AsmUtil.tryfinally(arg1, new BaseMethodGenerator_Anon1(arg0, arg1, arg2, var7), new BaseMethodGenerator_Anon2(arg0, arg1, arg3, arg4, arg5, arg6));
        } else {
            if (arg6 == null) {
                AsmUtil.emitFromNativeConversion(arg0, arg1, arg2, var7);
            } else {
                AsmUtil.tryfinally(arg1, new BaseMethodGenerator_Anon1(arg0, arg1, arg2, var7), new BaseMethodGenerator_Anon2(arg0, arg1, arg3, arg4, arg5, arg6));
            }
        }
        AsmUtil.emitReturnOp(arg1, arg2.getDeclaredType());
    }

  static void emitPostInvoke(AsmBuilder arg0, SkinnyMethodAdapter arg1, ParameterType[] arg2, LocalVariable[] arg3, LocalVariable[] arg4) {
        int var5 = 0;
        while (var5 < arg4.length) {
            if (arg4[var5] != null) {
                if (arg2[var5].getToNativeConverter() instanceof ToNativeConverter_PostInvocation) {
                    arg1.aload(0);
                    AsmBuilder_ObjectField var6 = arg0.getToNativeConverterField(arg2[var5].getToNativeConverter());
                    arg1.getfield(arg0.getClassNamePath(), var6.name, CodegenUtils.ci(var6.klass));
                    if (!ToNativeConverter_PostInvocation.class.isAssignableFrom(var6.klass)) {
                        arg1.checkcast(ToNativeConverter_PostInvocation.class);
                    }
                    arg1.aload(((LocalVariable) arg3[var5]));
                    arg1.aload(((LocalVariable) arg4[var5]));
                    if (arg2[var5].getToNativeContext() == null) {
                        arg1.aconst_null();
                    } else {
                        AsmUtil.getfield(arg1, arg0, arg0.getToNativeContextField(arg2[var5].getToNativeContext()));
                    }
                    arg1.invokestatic(AsmRuntime.class, "postInvoke", Void.TYPE, new Class[]{ToNativeConverter_PostInvocation.class, Object.class, Object.class, ToNativeContext.class});
                }
            }
            ++var5;
            continue;
        }
    }

}