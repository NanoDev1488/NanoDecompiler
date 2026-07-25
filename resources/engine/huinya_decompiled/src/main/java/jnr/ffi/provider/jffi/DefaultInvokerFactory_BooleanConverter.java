// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.BooleanConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;

final class DefaultInvokerFactory_BooleanConverter implements DataConverter {

    // ---- поля ----
  static final DataConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_BooleanConverter();
    }

   DefaultInvokerFactory_BooleanConverter() { // было: <init>
        super();
    }

  public Boolean fromNative(Number arg0, FromNativeContext arg1) {
        boolean __stk1;
        __stk1 = (arg0.intValue() & 1) != 0;
        return Boolean.valueOf(__stk1);
    }

  public Number toNative(Boolean arg0, ToNativeContext arg1) {
        boolean __stk1;
        __stk1 = arg0.booleanValue();
        return Integer.valueOf(__stk1);
    }

  public Class nativeType() {
        return Number.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Boolean) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}