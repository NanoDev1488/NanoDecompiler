// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.LongResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AbstractNumberResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

final class DefaultInvokerFactory_LongResultConverter extends DefaultInvokerFactory_AbstractNumberResultConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_ResultConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_LongResultConverter();
    }

   DefaultInvokerFactory_LongResultConverter() { // было: <init>
        super();
    }

  public Long fromNative(Number arg0, FromNativeContext arg1) {
        return Long.valueOf(arg0.longValue());
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}