// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.AddressResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.Address;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_AbstractNumberResultConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

final class DefaultInvokerFactory_AddressResultConverter extends DefaultInvokerFactory_AbstractNumberResultConverter {

    // ---- поля ----
  static final DefaultInvokerFactory_ResultConverter INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_AddressResultConverter();
    }

   DefaultInvokerFactory_AddressResultConverter() { // было: <init>
        super();
    }

  public Address fromNative(Number arg0, FromNativeContext arg1) {
        return Address.valueOf(arg0.longValue());
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Number) arg0), arg1);
    }

}