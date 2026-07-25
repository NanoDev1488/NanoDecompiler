// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StructArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.StructArrayParameterConverter;

public final class StructArrayParameterConverter_Out extends StructArrayParameterConverter implements ToNativeConverter_PostInvocation {

    // ---- поля ----
  private final Constructor constructor;

   StructArrayParameterConverter_Out(Runtime arg0, Class arg1, int arg2) { // было: <init>
        super(arg0, arg2);
        try {
            Constructor var4 = arg1.getConstructor(new Class[]{Runtime.class});
        } catch (NoSuchMethodException var5) {
            throw new RuntimeException(new StringBuilder().append(arg1.getName()).append(" has no constructor that accepts jnr.ffi.Runtime").toString());
        } catch (Throwable e2) {
            Throwable var5 = e2;
            throw new RuntimeException(var5);
        }
    }

  public void postInvoke(Struct[] arg0, Pointer arg1, ToNativeContext arg2) {
        if (arg0 == null) {
            return;
        }
        if (arg1 == null) {
            return;
        }
        try {
            int var4 = 0;
            int var5 = 0;
            while (var5 < arg0.length) {
                arg0[var5] = ((Struct) constructor.newInstance(new Object[]{runtime}));
                int var6 = StructArrayParameterConverter.access$000(Struct.size(((Struct) arg0[var5])), Struct.alignment(((Struct) arg0[var5])));
                var4 = StructArrayParameterConverter.access$000(var4, Struct.alignment(((Struct) arg0[var5])));
                arg0[var5].useMemory(arg1.slice(((long) var4), ((long) var6)));
                var4 = var4 + var6;
                ++var5;
                continue;
            }
        } catch (InstantiationException e1) {
            Throwable var4 = e1;
            throw new RuntimeException(var4);
        } catch (IllegalAccessException e2) {
            Throwable var4 = e2;
            throw new RuntimeException(var4);
        } catch (InvocationTargetException e3) {
            Throwable var4 = e3;
            throw new RuntimeException(var4);
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Struct[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Struct[]) arg0), ((Pointer) arg1), arg2);
    }

}