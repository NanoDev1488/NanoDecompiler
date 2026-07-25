// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.IntegerResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AbstractNumberResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

final class DefaultInvokerFactory_IntegerResultConverter extends DefaultInvokerFactory_AbstractNumberResultConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_ResultConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_IntegerResultConverter();
    }

   DefaultInvokerFactory_IntegerResultConverter() { // было: <init>
        super();
    }

  public Integer fromNative(Number arg0, FromNativeContext arg1) {
        return Integer.valueOf(arg0.intValue());
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}