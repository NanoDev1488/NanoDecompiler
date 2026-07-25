// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.AbstractSignatureTypeMapper
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeType;

public abstract class AbstractSignatureTypeMapper implements SignatureTypeMapper {

  public AbstractSignatureTypeMapper() { // было: <init>
        super();
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return null;
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return null;
    }

}