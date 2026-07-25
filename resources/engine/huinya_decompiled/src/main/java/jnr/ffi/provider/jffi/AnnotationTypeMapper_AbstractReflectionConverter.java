// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.AnnotationTypeMapper.AbstractReflectionConverter
package jnr.ffi.provider.jffi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jnr.ffi.provider.jffi.AnnotationTypeMapper;

public abstract class AnnotationTypeMapper_AbstractReflectionConverter {

    // ---- поля ----
  protected final Method method;
  protected final Class nativeType;
  final AnnotationTypeMapper this$0;

  public AnnotationTypeMapper_AbstractReflectionConverter(AnnotationTypeMapper arg0, Method arg1, Class arg2) { // было: <init>
        super();
        this$0 = arg0;
        method = arg1;
        nativeType = arg2;
    }

  protected final Object invoke(Object arg0, Object arg1) {
        Object __stk2;
        try {
            __stk2 = method.invoke(method.getDeclaringClass(), new Object[]{arg0, arg1});
        } catch (IllegalAccessException var3) {
            throw new RuntimeException(var3);
        } catch (InvocationTargetException e2) {
            Throwable var3 = e2;
            throw new RuntimeException(var3);
        }
    }

  public final Class nativeType() {
        return nativeType;
    }

}