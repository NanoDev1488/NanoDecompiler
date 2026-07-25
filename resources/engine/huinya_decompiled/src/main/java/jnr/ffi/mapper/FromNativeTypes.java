// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.FromNativeTypes
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.FromNativeType;
import jnr.ffi.mapper.FromNativeTypes_Cacheable;
import jnr.ffi.mapper.FromNativeTypes_UnCacheable;

public final class FromNativeTypes {

  public FromNativeTypes() { // было: <init>
        super();
    }

  public static FromNativeType create(FromNativeConverter arg0) {
        if (arg0 != null) {
            return !arg0.getClass().isAnnotationPresent(FromNativeConverter_Cacheable.class) ? new FromNativeTypes_UnCacheable(arg0) : new FromNativeTypes_Cacheable(arg0);
        } else {
            return null;
        }
    }

}