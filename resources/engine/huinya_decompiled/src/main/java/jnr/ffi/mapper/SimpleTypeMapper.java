// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.SimpleTypeMapper
package jnr.ffi.mapper;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;

final class SimpleTypeMapper implements TypeMapper {

    // ---- поля ----
  private final Map toNativeConverters;
  private final Map fromNativeConverters;

  public SimpleTypeMapper(Map arg0, Map arg1) { // было: <init>
        super();
        toNativeConverters = Collections.unmodifiableMap(new IdentityHashMap(arg0));
        fromNativeConverters = Collections.unmodifiableMap(new IdentityHashMap(arg1));
    }

  public FromNativeConverter getFromNativeConverter(Class arg0) {
        return ((FromNativeConverter) fromNativeConverters.get(arg0));
    }

  public ToNativeConverter getToNativeConverter(Class arg0) {
        return ((ToNativeConverter) toNativeConverters.get(arg0));
    }

}