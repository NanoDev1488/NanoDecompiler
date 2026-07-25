// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.AbstractNumberResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;

abstract class DefaultInvokerFactory_AbstractNumberResultConverter implements DefaultInvokerFactory_ResultConverter {

   DefaultInvokerFactory_AbstractNumberResultConverter() { // было: <init>
        super();
    }

  public final Class nativeType() {
        return Number.class;
    }

}