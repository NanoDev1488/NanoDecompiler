// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ClosureFromNativeConverter
package jnr.ffi.provider.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Invoker;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.CallingConvention;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.StdCall;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.MethodResultContext;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AsmBuilder;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.AsmLibraryLoader;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.BaseMethodGenerator;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter_AbstractClosurePointer;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter_ProxyConverter;
import jnr.ffi.provider.jffi.ClosureUtil;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.InvokerUtil;
import jnr.ffi.provider.jffi.LocalVariableAllocator;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public abstract class ClosureFromNativeConverter implements FromNativeConverter {

    // ---- поля ----
  private static final AtomicLong nextClassID;

    static {
        nextClassID = new AtomicLong(0L);
    }

  public ClosureFromNativeConverter() { // было: <init>
        super();
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public static FromNativeConverter getInstance(Runtime arg0, SignatureType arg1, AsmClassLoader arg2, SignatureTypeMapper arg3) {
        return newClosureConverter(arg0, arg2, arg1.getDeclaredType(), arg3);
    }

  private static FromNativeConverter newClosureConverter(Runtime arg0, AsmClassLoader arg1, Class arg2, SignatureTypeMapper arg3) {
        ClassWriter __stk5;
        ClosureFromNativeConverter_ProxyConverter __stk9;
        ClassWriter var4 = new ClassWriter(2);
        __stk5 = !AsmLibraryLoader.DEBUG ? var4 : AsmUtil.newCheckClassAdapter(var4);
        ClassVisitor var5 = __stk5;
        String var6 = new StringBuilder().append(CodegenUtils.method1942(arg2)).append("$jnr$fromNativeConverter$").append(nextClassID.getAndIncrement()).toString();
        AsmBuilder var7 = new AsmBuilder(arg0, var6, ((ClassVisitor) var5), arg1);
        var5.visit(52, 17, var6, null, CodegenUtils.method1942(ClosureFromNativeConverter_AbstractClosurePointer.class), new String[]{CodegenUtils.method1942(arg2)});
        var5.visitAnnotation(CodegenUtils.ci(FromNativeConverter_NoContext.class), true);
        generateInvocation(arg0, var7, arg2, arg3);
        Class[] __obj7 = new Class[3];
        __obj7[0] = Runtime.class;
        __obj7[1] = Long.TYPE;
        __obj7[2] = Object[].class;
        SkinnyMethodAdapter var8 = new SkinnyMethodAdapter(((ClassVisitor) var5), 1, "<init>", CodegenUtils.sig(Void.TYPE, __obj7), null, null);
        var8.start();
        var8.aload(0);
        var8.aload(1);
        var8.lload(2);
        Class[] __obj8 = new Class[2];
        __obj8[0] = Runtime.class;
        __obj8[1] = Long.TYPE;
        var8.invokespecial(CodegenUtils.method1942(ClosureFromNativeConverter_AbstractClosurePointer.class), "<init>", CodegenUtils.sig(Void.TYPE, __obj8));
        var7.emitFieldInitialization(var8, 4);
        var8.voidreturn();
        var8.visitMaxs(10, 10);
        var8.visitEnd();
        Class var9 = loadClass(arg1, var6, var4);
        try {
            __stk9 = new ClosureFromNativeConverter_ProxyConverter(arg0, var9.getConstructor(new Class[]{Runtime.class, Long.TYPE, Object[].class}), var7.getObjectFieldValues());
        } catch (Throwable var10) {
            throw new RuntimeException(var10);
        }
    }

  private static Class loadClass(AsmClassLoader arg0, String arg1, ClassWriter arg2) {
        Class __stk1;
        try {
            byte[] var3 = arg2.toByteArray();
            if (!AsmLibraryLoader.DEBUG) {
                __stk1 = arg0.defineClass(arg1.replace("/", "."), var3);
            } else {
                ClassVisitor var4 = AsmUtil.newTraceClassVisitor(new PrintWriter(System.err));
                new ClassReader(var3).accept(var4, 0);
                __stk1 = arg0.defineClass(arg1.replace("/", "."), var3);
            }
        } catch (Throwable e1) {
            Throwable var3 = e1;
            throw new RuntimeException(var3);
        }
    }

  private static void generateInvocation(Runtime arg0, AsmBuilder arg1, Class arg2, SignatureTypeMapper arg3) {
        FromNativeConverter __stk2;
        CallingConvention __stk3;
        Method var4 = ClosureUtil.getDelegateMethod(arg2);
        MethodResultContext var5 = new MethodResultContext(arg0, var4);
        DefaultSignatureType var6 = DefaultSignatureType.create(var4.getReturnType(), var5);
        FromNativeType var7 = arg3.getFromNativeType(var6, var5);
        __stk2 = var7 == null ? null : var7.getFromNativeConverter();
        FromNativeConverter var8 = __stk2;
        ResultType var9 = InvokerUtil.getResultType(arg0, var4.getReturnType(), var5.getAnnotations(), ((FromNativeConverter) var8), var5);
        ParameterType[] var10 = InvokerUtil.getParameterTypes(arg0, arg3, var4);
        __stk3 = !arg2.isAnnotationPresent(StdCall.class) ? CallingConvention.DEFAULT : CallingConvention.STDCALL;
        CallingConvention var11 = __stk3;
        CallContext var12 = InvokerUtil.getCallContext(var9, var10, ((CallingConvention) var11), true);
        LocalVariableAllocator var13 = new LocalVariableAllocator(var10);
        Class[] var14 = new Class[var10.length];
        int var15 = 0;
        while (var15 < var10.length) {
            var14[var15] = var10[var15].getDeclaredType();
            ++var15;
            continue;
        }
        var15 = new SkinnyMethodAdapter(arg1.getClassVisitor(), 17, var4.getName(), CodegenUtils.sig(var9.getDeclaredType(), var14), null, null);
        var15.start();
        var15.getstatic(CodegenUtils.method1942(ClosureFromNativeConverter_AbstractClosurePointer.class), "ffi", CodegenUtils.ci(Invoker.class));
        var15.aload(0);
        var15.getfield(arg1.getClassNamePath(), arg1.getCallContextFieldName(var12), CodegenUtils.ci(CallContext.class));
        var15.aload(0);
        var15.getfield(CodegenUtils.method1942(ClosureFromNativeConverter_AbstractClosurePointer.class), "functionAddress", CodegenUtils.ci(Long.TYPE));
        BaseMethodGenerator[] var16 = new BaseMethodGenerator[]{new FastIntMethodGenerator(), new FastLongMethodGenerator(), new FastNumericMethodGenerator(), new BufferMethodGenerator()};
        BaseMethodGenerator[] var17 = var16;
        int var18 = var17.length;
        int var19 = 0;
        while (var19 < var18) {
            Object var20 = var17[var19];
            if (var20.isSupported(var9, var10, ((CallingConvention) var11))) {
                var20.generate(arg1, var15, var13, var12, var9, var10, false);
            }
            ++var19;
            continue;
        }
        var15.visitMaxs(100, 10 + var13.getSpaceUsed());
        var15.visitEnd();
    }

}