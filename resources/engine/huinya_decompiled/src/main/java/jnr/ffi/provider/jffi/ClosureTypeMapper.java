// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ClosureTypeMapper
package jnr.ffi.provider.jffi;

import jnr.ffi.Struct;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeTypes;
import jnr.ffi.mapper.SignatureType;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeTypes;
import jnr.ffi.provider.converters.EnumConverter;
import jnr.ffi.provider.converters.StringResultConverter;
import jnr.ffi.provider.converters.StructByReferenceToNativeConverter;

final class ClosureTypeMapper implements SignatureTypeMapper {

   ClosureTypeMapper() { // было: <init>
        super();
    }

  private FromNativeConverter getFromNativeConverter(SignatureType arg0, FromNativeContext arg1) {
        if (!Enum.class.isAssignableFrom(arg0.getDeclaredType())) {
            if (!CharSequence.class.isAssignableFrom(arg0.getDeclaredType())) {
                return null;
            } else {
                return StringResultConverter.getInstance(arg1);
            }
        } else {
            return EnumConverter.getInstance(arg0.getDeclaredType().asSubclass(Enum.class));
        }
    }

  private ToNativeConverter getToNativeConverter(SignatureType arg0, ToNativeContext arg1) {
        if (!Enum.class.isAssignableFrom(arg0.getDeclaredType())) {
            if (!Struct.class.isAssignableFrom(arg0.getDeclaredType())) {
                return null;
            } else {
                return StructByReferenceToNativeConverter.getInstance(arg1);
            }
        } else {
            return EnumConverter.getInstance(arg0.getDeclaredType().asSubclass(Enum.class));
        }
    }

  public FromNativeType getFromNativeType(SignatureType arg0, FromNativeContext arg1) {
        return FromNativeTypes.create(getFromNativeConverter(arg0, arg1));
    }

  public ToNativeType getToNativeType(SignatureType arg0, ToNativeContext arg1) {
        return ToNativeTypes.create(getToNativeConverter(arg0, arg1));
    }

}