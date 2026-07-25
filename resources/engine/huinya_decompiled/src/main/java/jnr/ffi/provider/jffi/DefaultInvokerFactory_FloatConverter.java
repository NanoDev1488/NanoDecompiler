// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.FloatConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_NumberDataConverter;

final class DefaultInvokerFactory_FloatConverter extends DefaultInvokerFactory_NumberDataConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_NumberDataConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_FloatConverter();
    }

   DefaultInvokerFactory_FloatConverter() { // было: <init>
        super();
    }

  public Number fromNative(Number arg0, FromNativeContext arg1) {
        return Float.valueOf(arg0.floatValue());
    }

  public Number toNative(Number arg0, ToNativeContext arg1) {
        return Float.valueOf(arg0.floatValue());
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Number) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}