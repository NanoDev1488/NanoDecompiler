// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.SignatureTypeMapperAdapter
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeTypes;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeTypes;
import jnr.ffi.mapper.TypeMapper;

public class SignatureTypeMapperAdapter implements SignatureTypeMapper {

    // ---- поля ----
  private final TypeMapper typeMapper;

  public SignatureTypeMapperAdapter(TypeMapper arg0) { // было: <init>
        super();
        typeMapper = arg0;
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return FromNativeTypes.create(typeMapper.getFromNativeConverter(arg0.getDeclaredType()));
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return ToNativeTypes.create(typeMapper.getToNativeConverter(arg0.getDeclaredType()));
    }

}