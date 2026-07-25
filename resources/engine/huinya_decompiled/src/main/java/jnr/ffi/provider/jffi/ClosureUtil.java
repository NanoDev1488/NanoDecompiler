// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ClosureUtil
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.mapper.DefaultSignatureType;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.ToNativeType;
import jnr.ffi.provider.jffi.SimpleNativeContext;
import jnr.ffi.provider.jffi.Types;
import jnr.ffi.util.Annotations;

final class ClosureUtil {

  private ClosureUtil() { // было: <init>
        super();
    }

  static ToNativeType getResultType(Runtime arg0, Method arg1, SignatureTypeMapper arg2) {
        ToNativeConverter __stk1;
        Class __stk2;
        Collection var3 = Annotations.sortedAnnotationCollection(arg1.getAnnotations());
        SimpleNativeContext var4 = new SimpleNativeContext(arg0, var3);
        DefaultSignatureType var5 = DefaultSignatureType.create(arg1.getReturnType(), var4);
        jnr.ffi.mapper.ToNativeType var6 = arg2.getToNativeType(var5, var4);
        __stk1 = var6 == null ? null : var6.getToNativeConverter();
        ToNativeConverter var7 = __stk1;
        __stk2 = var7 == null ? arg1.getReturnType() : var7.nativeType();
        Class var8 = __stk2;
        NativeType var9 = Types.getType(arg0, ((Class) var8), var3).getNativeType();
        return new ToNativeType(arg1.getReturnType(), var9, var3, ((ToNativeConverter) var7), var4);
    }

  static FromNativeType getParameterType(Runtime arg0, Method arg1, int arg2, SignatureTypeMapper arg3) {
        FromNativeConverter __stk1;
        Object __stk2;
        Collection var4 = Annotations.sortedAnnotationCollection(((Annotation[]) arg1.getParameterAnnotations()[arg2]));
        Object var5 = arg1.getParameterTypes()[arg2];
        SimpleNativeContext var6 = new SimpleNativeContext(arg0, var4);
        DefaultSignatureType var7 = new DefaultSignatureType(((Class) var5), var6.getAnnotations(), ((Type) arg1.getGenericParameterTypes()[arg2]));
        jnr.ffi.mapper.FromNativeType var8 = arg3.getFromNativeType(var7, var6);
        __stk1 = var8 == null ? null : var8.getFromNativeConverter();
        FromNativeConverter var9 = __stk1;
        __stk2 = var9 == null ? var5 : var9.nativeType();
        Class var10 = __stk2;
        NativeType var11 = Types.getType(arg0, ((Class) var10), var4).getNativeType();
        return new FromNativeType(((Class) var5), var11, var4, ((FromNativeConverter) var9), var6);
    }

  static Method getDelegateMethod(Class arg0) {
        Object var1 = null;
        Method[] var2 = arg0.getMethods();
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            if (!var5.isAnnotationPresent(Delegate.class)) {
                ++var4;
                continue;
            } else {
                if (!Modifier.isPublic(var5.getModifiers())) {
                    ++var4;
                    continue;
                } else {
                    if (Modifier.isStatic(var5.getModifiers())) {
                        ++var4;
                        continue;
                    } else {
                        var1 = var5;
                        break;
                    }
                }
            }
        }
        if (var1 != null) {
            return ((Method) var1);
        } else {
            throw new NoSuchMethodError(new StringBuilder().append("no public non-static delegate method defined in ").append(arg0.getName()).toString());
        }
    }

}