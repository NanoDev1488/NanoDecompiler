// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.TypeMapper.Builder
package jnr.ffi.mapper;

import java.util.HashMap;
import java.util.Map;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.SimpleTypeMapper;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;

public final class TypeMapper_Builder {

    // ---- поля ----
  private final Map toNativeConverterMap;
  private final Map fromNativeConverterMap;

  public TypeMapper_Builder() { // было: <init>
        super();
        toNativeConverterMap = new HashMap();
        fromNativeConverterMap = new HashMap();
    }

  public TypeMapper_Builder map(Class arg0, ToNativeConverter arg1) {
        toNativeConverterMap.put(arg0, arg1);
        return this;
    }

  public TypeMapper_Builder map(Class arg0, FromNativeConverter arg1) {
        fromNativeConverterMap.put(arg0, arg1);
        return this;
    }

  public TypeMapper_Builder map(Class arg0, DataConverter arg1) {
        toNativeConverterMap.put(arg0, arg1);
        fromNativeConverterMap.put(arg0, arg1);
        return this;
    }

  public TypeMapper build() {
        return new SimpleTypeMapper(toNativeConverterMap, fromNativeConverterMap);
    }

}