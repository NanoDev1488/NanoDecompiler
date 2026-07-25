// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Unsigned32Converter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_NumberDataConverter;

final class DefaultInvokerFactory_Unsigned32Converter extends DefaultInvokerFactory_NumberDataConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_NumberDataConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_Unsigned32Converter();
    }

   DefaultInvokerFactory_Unsigned32Converter() { // было: <init>
        super();
    }

  public Number fromNative(Number arg0, FromNativeContext arg1) {
        long __stk1;
        long var3 = ((long) arg0.intValue());
        __stk1 = var3 >= 0L ? var3 : (var3 & 2147483647L) + 2147483648L;
        return Long.valueOf(__stk1);
    }

  public Number toNative(Number arg0, ToNativeContext arg1) {
        return Long.valueOf(arg0.longValue() & 4294967295L);
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Number) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}