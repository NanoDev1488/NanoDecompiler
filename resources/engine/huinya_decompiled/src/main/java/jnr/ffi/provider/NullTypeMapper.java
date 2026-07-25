// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.NullTypeMapper
package jnr.ffi.provider;

import jnr.ffi.mapper.AbstractSignatureTypeMapper;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;
import jnr.ffi.provider.FromNativeType;
import jnr.ffi.provider.ToNativeType;

public class NullTypeMapper extends AbstractSignatureTypeMapper implements SignatureTypeMapper, TypeMapper {

  public NullTypeMapper() { // было: <init>
        super();
    }

  public FromNativeConverter getFromNativeConverter(Class arg0) {
        return null;
    }

  public ToNativeConverter getToNativeConverter(Class arg0) {
        return null;
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return null;
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return null;
    }

  public jnr.ffi.mapper.ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return getToNativeType(arg0, arg1);
    }

  public jnr.ffi.mapper.FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return getFromNativeType(arg0, arg1);
    }

}