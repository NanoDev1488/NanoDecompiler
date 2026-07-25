// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Unsigned8Converter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_NumberDataConverter;

final class DefaultInvokerFactory_Unsigned8Converter extends DefaultInvokerFactory_NumberDataConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_NumberDataConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_Unsigned8Converter();
    }

   DefaultInvokerFactory_Unsigned8Converter() { // было: <init>
        super();
    }

  public Number fromNative(Number arg0, FromNativeContext arg1) {
        byte __stk1;
        byte var3 = arg0.byteValue();
        __stk1 = var3 >= 0 ? var3 : (var3 & 127) + 128;
        return Integer.valueOf(__stk1);
    }

  public Number toNative(Number arg0, ToNativeContext arg1) {
        return Integer.valueOf(arg0.intValue() & 65535);
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Number) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}