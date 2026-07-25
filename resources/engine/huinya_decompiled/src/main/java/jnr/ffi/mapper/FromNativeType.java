// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FromNativeType
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeConverter;

public interface FromNativeType {

  public abstract FromNativeConverter getFromNativeConverter();

}