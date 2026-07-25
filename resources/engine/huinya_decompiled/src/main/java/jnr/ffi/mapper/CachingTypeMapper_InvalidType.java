// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.CachingTypeMapper.InvalidType
package jnr.ffi.mapper;

import jnr.ffi.mapper.CachingTypeMapper_Anon1;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;

final class CachingTypeMapper_InvalidType implements FromNativeType, ToNativeType {

  private CachingTypeMapper_InvalidType() { // было: <init>
        super();
    }

  public FromNativeConverter getFromNativeConverter() {
        return null;
    }

  public ToNativeConverter getToNativeConverter() {
        return null;
    }

   CachingTypeMapper_InvalidType(CachingTypeMapper_Anon1 arg0) { // было: <init>
        this();
    }

}