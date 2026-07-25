// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.SignatureTypeMapper
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeType;

public interface SignatureTypeMapper {

  public abstract FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1);

  public abstract ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1);

}