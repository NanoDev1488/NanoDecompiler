// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.NumberDataConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.DataConverter;

abstract class DefaultInvokerFactory_NumberDataConverter implements DataConverter {

   DefaultInvokerFactory_NumberDataConverter() { // было: <init>
        super();
    }

  public final Class nativeType() {
        return Number.class;
    }

}