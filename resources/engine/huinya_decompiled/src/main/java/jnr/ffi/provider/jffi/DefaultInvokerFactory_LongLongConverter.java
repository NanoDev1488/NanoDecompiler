// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.LongLongConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_NumberDataConverter;

final class DefaultInvokerFactory_LongLongConverter extends DefaultInvokerFactory_NumberDataConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_NumberDataConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_LongLongConverter();
    }

   DefaultInvokerFactory_LongLongConverter() { // было: <init>
        super();
    }

  public Number fromNative(Number arg0, FromNativeContext arg1) {
        return Long.valueOf(arg0.longValue());
    }

  public Number toNative(Number arg0, ToNativeContext arg1) {
        return Long.valueOf(arg0.longValue());
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Number) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}