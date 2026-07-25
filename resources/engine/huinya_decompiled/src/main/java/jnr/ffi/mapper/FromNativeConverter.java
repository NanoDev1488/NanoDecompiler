// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FromNativeConverter
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeContext;

public interface FromNativeConverter {

  public abstract Object fromNative(Object arg0, FromNativeContext arg1);

  public abstract Class nativeType();

}