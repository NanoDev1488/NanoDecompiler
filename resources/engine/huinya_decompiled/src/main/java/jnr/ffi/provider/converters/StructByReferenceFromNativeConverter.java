// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StructByReferenceFromNativeConverter
package jnr.ffi.provider.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;

public class StructByReferenceFromNativeConverter implements FromNativeConverter {

    // ---- поля ----
  private final Constructor constructor;

  public static FromNativeConverter getInstance(Class arg0, FromNativeContext arg1) {
        StructByReferenceFromNativeConverter __stk2;
        try {
            __stk2 = new StructByReferenceFromNativeConverter(arg0.getConstructor(new Class[]{Runtime.class}));
        } catch (NoSuchMethodException var2) {
            throw new RuntimeException(new StringBuilder().append(arg0.getName()).append(" has no constructor that accepts jnr.ffi.Runtime").toString());
        } catch (Throwable e2) {
            Throwable var2 = e2;
            throw new RuntimeException(var2);
        }
    }

   StructByReferenceFromNativeConverter(Constructor arg0) { // было: <init>
        super();
        constructor = arg0;
    }

  public Struct fromNative(Pointer arg0, FromNativeContext arg1) {
        Struct __stk2;
        try {
            Struct var3 = ((Struct) constructor.newInstance(new Object[]{arg1.getRuntime()}));
            var3.useMemory(arg0);
            __stk2 = var3;
        } catch (InstantiationException e1) {
            Throwable var3 = e1;
            throw new RuntimeException(var3);
        } catch (IllegalAccessException e2) {
            Throwable var3 = e2;
            throw new RuntimeException(var3);
        } catch (InvocationTargetException e3) {
            Throwable var3 = e3;
            throw new RuntimeException(var3);
        }
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Pointer) arg0), arg1);
    }

}