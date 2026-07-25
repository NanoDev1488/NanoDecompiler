// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ClosureFromNativeConverter.ProxyConverter
package jnr.ffi.provider.jffi;

import java.lang.reflect.Constructor;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.ClosureFromNativeConverter;

public final class ClosureFromNativeConverter_ProxyConverter extends ClosureFromNativeConverter {

    // ---- поля ----
  private final Runtime runtime;
  private final Constructor closureConstructor;
  private final Object[] initFields;

  public ClosureFromNativeConverter_ProxyConverter(Runtime arg0, Constructor arg1, Object[] arg2) { // было: <init>
        super();
        runtime = arg0;
        closureConstructor = arg1;
        initFields = ((Object[]) arg2.clone());
    }

  public Object fromNative(Pointer arg0, FromNativeContext arg1) {
        Object __stk2;
        try {
            __stk2 = closureConstructor.newInstance(new Object[]{runtime, Long.valueOf(arg0.address()), initFields});
        } catch (Throwable var3) {
            throw new RuntimeException(var3);
        }
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Pointer) arg0), arg1);
    }

}