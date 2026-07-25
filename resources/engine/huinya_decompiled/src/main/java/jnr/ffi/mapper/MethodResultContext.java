// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.MethodResultContext
package jnr.ffi.mapper;

import java.lang.reflect.Method;
import java.util.Collection;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.util.Annotations;

public class MethodResultContext implements FromNativeContext {

    // ---- поля ----
  private final Runtime runtime;
  private final Method method;
  private Collection annotations;

  public MethodResultContext(Runtime arg0, Method arg1) { // было: <init>
        super();
        runtime = arg0;
        method = arg1;
    }

  public Method getMethod() {
        return method;
    }

  public Collection getAnnotations() {
        Collection __stk1;
        if (annotations == null) {
            annotations = Annotations.sortedAnnotationCollection(method.getAnnotations());
            __stk1 = Annotations.sortedAnnotationCollection(method.getAnnotations());
        } else {
            __stk1 = annotations;
        }
        return __stk1;
    }

  public Runtime getRuntime() {
        return runtime;
    }

}