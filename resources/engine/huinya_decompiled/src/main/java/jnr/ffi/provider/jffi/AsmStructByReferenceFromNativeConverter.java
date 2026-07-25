// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AsmStructByReferenceFromNativeConverter
package jnr.ffi.provider.jffi;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.provider.jffi.AsmClassLoader;
import jnr.ffi.provider.jffi.AsmLibraryLoader;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.CodegenUtils;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;

@FromNativeConverter_NoContext
@FromNativeConverter_Cacheable
public abstract class AsmStructByReferenceFromNativeConverter implements FromNativeConverter {

    // ---- поля ----
  private final Runtime runtime;
  private final int flags;
  static final Map converterClasses;
  private static final AtomicLong nextClassID;

    static {
        converterClasses = new ConcurrentHashMap();
        nextClassID = new AtomicLong(0L);
    }

  protected AsmStructByReferenceFromNativeConverter(Runtime arg0, int arg1) { // было: <init>
        super();
        runtime = arg0;
        flags = arg1;
    }

  public final Class nativeType() {
        return Pointer.class;
    }

  protected final Runtime getRuntime() {
        return runtime;
    }

  static AsmStructByReferenceFromNativeConverter newStructByReferenceConverter(Runtime arg0, Class arg1, int arg2, AsmClassLoader arg3) {
        AsmStructByReferenceFromNativeConverter __stk3;
        try {
            Object[] __obj2 = new Object[2];
            __obj2[0] = arg0;
            __obj2[1] = Integer.valueOf(arg2);
            __stk3 = ((AsmStructByReferenceFromNativeConverter) newStructByReferenceClass(arg1, arg3).getConstructor(new Class[]{Runtime.class, Integer.TYPE}).newInstance(__obj2));
        } catch (NoSuchMethodException var4) {
            throw new RuntimeException(var4);
        } catch (IllegalAccessException e2) {
            Throwable var4 = e2;
            throw new RuntimeException(var4);
        } catch (InstantiationException e3) {
            Throwable var4 = e3;
            throw new RuntimeException(var4);
        } catch (InvocationTargetException e4) {
            Throwable var4 = e4;
            throw new RuntimeException(var4);
        }
    }

  static Class newStructByReferenceClass(Class arg0, AsmClassLoader arg1) {
        Constructor __stk9;
        Class __stk17;
        try {
            Constructor var2 = arg0.asSubclass(Struct.class).getConstructor(new Class[]{Runtime.class});
            if (!Modifier.isPublic(var2.getModifiers())) {
                throw new RuntimeException(new StringBuilder().append(arg0.getName()).append(" constructor is not public").toString());
            }
            var2 = new ClassWriter(2);
            __stk9 = !AsmLibraryLoader.DEBUG ? var2 : AsmUtil.newCheckClassAdapter(var2);
            ClassVisitor var3 = __stk9;
            String var4 = new StringBuilder().append(CodegenUtils.method1942(arg0)).append("$$jnr$$StructByReferenceFromNativeConverter$$").append(nextClassID.getAndIncrement()).toString();
            var3.visit(49, 17, var4, null, CodegenUtils.method1942(AsmStructByReferenceFromNativeConverter.class), new String[0]);
            var3.visitAnnotation(CodegenUtils.ci(FromNativeConverter_NoContext.class), true);
            Class[] __obj10 = new Class[2];
            __obj10[0] = Runtime.class;
            __obj10[1] = Integer.TYPE;
            SkinnyMethodAdapter var5 = new SkinnyMethodAdapter(((ClassVisitor) var3), 1, "<init>", CodegenUtils.sig(Void.TYPE, __obj10), null, null);
            var5.start();
            var5.aload(0);
            var5.aload(1);
            var5.iload(2);
            Class[] __obj11 = new Class[2];
            __obj11[0] = Runtime.class;
            __obj11[1] = Integer.TYPE;
            var5.invokespecial(CodegenUtils.method1942(AsmStructByReferenceFromNativeConverter.class), "<init>", CodegenUtils.sig(Void.TYPE, __obj11));
            var5.voidreturn();
            var5.visitMaxs(10, 10);
            var5.visitEnd();
            Class[] __obj12 = new Class[2];
            __obj12[0] = Pointer.class;
            __obj12[1] = FromNativeContext.class;
            SkinnyMethodAdapter var6 = new SkinnyMethodAdapter(((ClassVisitor) var3), 17, "fromNative", CodegenUtils.sig(arg0, __obj12), null, null);
            var6.start();
            Label var7 = new Label();
            var6.aload(1);
            var6.ifnull(var7);
            var6.newobj(CodegenUtils.method1942(arg0));
            var6.dup();
            var6.aload(0);
            var6.invokevirtual(CodegenUtils.method1942(AsmStructByReferenceFromNativeConverter.class), "getRuntime", CodegenUtils.sig(Runtime.class, new Class[0]));
            Class[] __obj13 = new Class[1];
            __obj13[0] = Runtime.class;
            var6.invokespecial(arg0, "<init>", Void.TYPE, __obj13);
            var6.dup();
            var6.aload(1);
            Class[] __obj14 = new Class[1];
            __obj14[0] = Pointer.class;
            var6.invokevirtual(arg0, "useMemory", Void.TYPE, __obj14);
            var6.areturn();
            var6.label(var7);
            var6.aconst_null();
            var6.areturn();
            var6.visitAnnotation(CodegenUtils.ci(FromNativeConverter_NoContext.class), true);
            var6.visitMaxs(10, 10);
            var6.visitEnd();
            Class[] __obj15 = new Class[2];
            __obj15[0] = Object.class;
            __obj15[1] = FromNativeContext.class;
            var6 = new SkinnyMethodAdapter(((ClassVisitor) var3), 17, "fromNative", CodegenUtils.sig(Object.class, __obj15), null, null);
            var6.start();
            var6.aload(0);
            var6.aload(1);
            var6.checkcast(Pointer.class);
            var6.aload(2);
            Class[] __obj16 = new Class[2];
            __obj16[0] = Pointer.class;
            __obj16[1] = FromNativeContext.class;
            var6.invokevirtual(var4, "fromNative", CodegenUtils.sig(arg0, __obj16));
            var6.areturn();
            var6.visitAnnotation(CodegenUtils.ci(FromNativeConverter_NoContext.class), true);
            var6.visitMaxs(10, 10);
            var6.visitEnd();
            var3.visitEnd();
            try {
                byte[] var8 = var2.toByteArray();
                if (!AsmLibraryLoader.DEBUG) {
                    __stk17 = arg1.defineClass(var4.replace("/", "."), var8);
                } else {
                    ClassVisitor var9 = AsmUtil.newTraceClassVisitor(new PrintWriter(System.err));
                    new ClassReader(var8).accept(var9, 0);
                    __stk17 = arg1.defineClass(var4.replace("/", "."), var8);
                }
            } catch (Throwable e1) {
                Throwable var8 = e1;
                throw new RuntimeException(var8);
            }
        } catch (NoSuchMethodException e2) {
            Throwable var2 = e2;
            throw new RuntimeException(new StringBuilder().append("struct subclass ").append(arg0.getName()).append(" has no constructor that takes a ").append(Runtime.class.getName()).toString(), var2);
        }
    }

}