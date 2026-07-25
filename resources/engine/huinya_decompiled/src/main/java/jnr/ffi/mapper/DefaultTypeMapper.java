// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.DefaultTypeMapper
package jnr.ffi.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.TypeMapper;

public final class DefaultTypeMapper implements TypeMapper {

    // ---- поля ----
  private final Map toNativeConverters;
  private final Map fromNativeConverters;

  public DefaultTypeMapper() { // было: <init>
        super();
        toNativeConverters = new LinkedHashMap();
        fromNativeConverters = new LinkedHashMap();
    }

  public final void put(Class arg0, DataConverter arg1) {
        toNativeConverters.put(arg0, arg1);
        fromNativeConverters.put(arg0, arg1);
    }

  public final void put(Class arg0, ToNativeConverter arg1) {
        toNativeConverters.put(arg0, arg1);
    }

  public final void put(Class arg0, FromNativeConverter arg1) {
        fromNativeConverters.put(arg0, arg1);
    }

  public FromNativeConverter getFromNativeConverter(Class arg0) {
        return ((FromNativeConverter) fromNativeConverters.get(arg0));
    }

  public ToNativeConverter getToNativeConverter(Class arg0) {
        return ((ToNativeConverter) toNativeConverters.get(arg0));
    }

}