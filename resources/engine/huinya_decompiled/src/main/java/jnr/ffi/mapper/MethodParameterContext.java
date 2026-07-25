// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.MethodParameterContext
package jnr.ffi.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.util.Annotations;

public final class MethodParameterContext implements ToNativeContext {

    // ---- поля ----
  private final Runtime runtime;
  private final Method method;
  private final int parameterIndex;
  private Collection annotations;
  private Annotation[] annotationArray;

  public MethodParameterContext(Runtime arg0, Method arg1, int arg2) { // было: <init>
        super();
        runtime = arg0;
        method = arg1;
        parameterIndex = arg2;
    }

  public MethodParameterContext(Runtime arg0, Method arg1, int arg2, Annotation[] arg3) { // было: <init>
        super();
        runtime = arg0;
        method = arg1;
        parameterIndex = arg2;
        annotationArray = ((Annotation[]) arg3.clone());
    }

  public MethodParameterContext(Runtime arg0, Method arg1, int arg2, Collection arg3) { // было: <init>
        super();
        runtime = arg0;
        method = arg1;
        parameterIndex = arg2;
        annotations = Annotations.sortedAnnotationCollection(arg3);
    }

  public Method getMethod() {
        return method;
    }

  public int getParameterIndex() {
        return parameterIndex;
    }

  public Collection getAnnotations() {
        return annotations == null ? buildAnnotationCollection() : annotations;
    }

  public Runtime getRuntime() {
        return runtime;
    }

  private Collection buildAnnotationCollection() {
        if (annotationArray == null) {
            annotationArray = ((Annotation[]) method.getParameterAnnotations()[parameterIndex]);
            annotations = Annotations.sortedAnnotationCollection(((Annotation[]) method.getParameterAnnotations()[parameterIndex]));
            return Annotations.sortedAnnotationCollection(((Annotation[]) method.getParameterAnnotations()[parameterIndex]));
        } else {
            annotations = Annotations.sortedAnnotationCollection(annotationArray);
            return Annotations.sortedAnnotationCollection(annotationArray);
        }
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    MethodParameterContext var2 = ((MethodParameterContext) arg0);
                    return parameterIndex != var2.parameterIndex ? 0 : !method.equals(var2.method) ? 0 : getAnnotations().equals(var2.getAnnotations());
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = method.hashCode();
        var1 = 31 * var1 + parameterIndex;
        var1 = 31 * var1 + getAnnotations().hashCode();
        return var1;
    }

}