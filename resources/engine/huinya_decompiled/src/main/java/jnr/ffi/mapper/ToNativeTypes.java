// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.ToNativeTypes
package jnr.ffi.mapper;

import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeType;
import jnr.ffi.mapper.ToNativeTypes_Cacheable;
import jnr.ffi.mapper.ToNativeTypes_UnCacheable;

public final class ToNativeTypes {

  public ToNativeTypes() { // было: <init>
        super();
    }

  public static ToNativeType create(ToNativeConverter arg0) {
        if (arg0 != null) {
            return !arg0.getClass().isAnnotationPresent(ToNativeConverter_Cacheable.class) ? new ToNativeTypes_UnCacheable(arg0) : new ToNativeTypes_Cacheable(arg0);
        } else {
            return null;
        }
    }

}