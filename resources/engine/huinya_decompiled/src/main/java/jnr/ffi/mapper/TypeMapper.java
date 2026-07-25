// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.TypeMapper
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeConverter;

public interface TypeMapper {

  public abstract FromNativeConverter getFromNativeConverter(Class arg0);

  public abstract ToNativeConverter getToNativeConverter(Class arg0);

}