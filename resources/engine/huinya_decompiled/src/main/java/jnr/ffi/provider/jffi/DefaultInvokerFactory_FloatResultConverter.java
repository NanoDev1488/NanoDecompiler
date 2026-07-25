// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.FloatResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AbstractNumberResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

final class DefaultInvokerFactory_FloatResultConverter extends DefaultInvokerFactory_AbstractNumberResultConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_ResultConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_FloatResultConverter();
    }

   DefaultInvokerFactory_FloatResultConverter() { // было: <init>
        super();
    }

  public Float fromNative(Number arg0, FromNativeContext arg1) {
        return Float.valueOf(arg0.floatValue());
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}