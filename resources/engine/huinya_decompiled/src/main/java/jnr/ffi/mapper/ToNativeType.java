// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeType
package jnr.ffi.mapper;

import jnr.ffi.mapper.ToNativeConverter;

public interface ToNativeType {

  public abstract ToNativeConverter getToNativeConverter();

}