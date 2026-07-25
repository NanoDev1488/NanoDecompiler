// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.DefaultSignatureType
package jnr.ffi.mapper;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.MethodParameterContext;
import jnr.ffi.mapper.MethodResultContext;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.util.Annotations;

public final class DefaultSignatureType implements SignatureType {

    // ---- поля ----
  private final Class declaredClass;
  private final Collection annotations;
  private final Type genericType;

  public DefaultSignatureType(Class arg0, Collection arg1, Type arg2) { // было: <init>
        super();
        declaredClass = arg0;
        annotations = Annotations.sortedAnnotationCollection(arg1);
        genericType = arg2;
    }

  public Class getDeclaredType() {
        return declaredClass;
    }

  public Collection getAnnotations() {
        return annotations;
    }

  public Type getGenericType() {
        return genericType;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    DefaultSignatureType var2 = ((DefaultSignatureType) arg0);
                    return declaredClass != var2.declaredClass ? 0 : !genericType.equals(var2.genericType) ? 0 : annotations.equals(var2.annotations);
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = declaredClass.hashCode();
        var1 = 31 * var1 + annotations.hashCode();
        if (genericType != null) {
            var1 = 31 * var1 + genericType.hashCode();
        }
        return var1;
    }

  public static DefaultSignatureType create(Class arg0, FromNativeContext arg1) {
        Class __stk1;
        __stk1 = arg0.isPrimitive() ? arg0 : !(arg1 instanceof MethodResultContext) ? arg0 : (((MethodResultContext) arg1)).getMethod().getGenericReturnType();
        Type var2 = __stk1;
        return new DefaultSignatureType(arg0, arg1.getAnnotations(), ((Type) var2));
    }

  public static DefaultSignatureType create(Class arg0, ToNativeContext arg1) {
        Class var2 = arg0;
        if (!arg0.isPrimitive()) {
            if (arg1 instanceof MethodParameterContext) {
                MethodParameterContext var3 = ((MethodParameterContext) arg1);
                var2 = var3.getMethod().getGenericParameterTypes()[var3.getParameterIndex()];
            }
        }
        return new DefaultSignatureType(arg0, arg1.getAnnotations(), var2);
    }

}