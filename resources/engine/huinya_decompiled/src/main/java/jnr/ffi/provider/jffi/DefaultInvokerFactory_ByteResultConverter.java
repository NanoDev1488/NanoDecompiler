// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.ByteResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AbstractNumberResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

final class DefaultInvokerFactory_ByteResultConverter extends DefaultInvokerFactory_AbstractNumberResultConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_ResultConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_ByteResultConverter();
    }

   DefaultInvokerFactory_ByteResultConverter() { // было: <init>
        super();
    }

  public Byte fromNative(Number arg0, FromNativeContext arg1) {
        return Byte.valueOf(arg0.byteValue());
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}