// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.ResultConverter
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;

interface DefaultInvokerFactory_ResultConverter extends FromNativeConverter {

  public abstract Object fromNative(Object arg0, FromNativeContext arg1);

}