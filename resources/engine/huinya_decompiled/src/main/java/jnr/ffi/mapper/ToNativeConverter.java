// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeConverter
package jnr.ffi.mapper;

import jnr.ffi.mapper.ToNativeContext;

public interface ToNativeConverter {

  public abstract Object toNative(Object arg0, ToNativeContext arg1);

  public abstract Class nativeType();

}