// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.EnumConverter
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.util.EnumMapper;

@ToNativeConverter_NoContext
@FromNativeConverter_NoContext
@ToNativeConverter_Cacheable
@FromNativeConverter_Cacheable
public final class EnumConverter implements DataConverter {

    // ---- поля ----
  private final EnumMapper mapper;

  public static EnumConverter getInstance(Class arg0) {
        return new EnumConverter(arg0);
    }

  private EnumConverter(Class arg0) { // было: <init>
        super();
        mapper = EnumMapper.getInstance(arg0);
    }

  public Enum fromNative(Integer arg0, FromNativeContext arg1) {
        return mapper.valueOf(arg0.intValue());
    }

  public Integer toNative(Enum arg0, ToNativeContext arg1) {
        return mapper.integerValue(arg0);
    }

  public Class nativeType() {
        return Integer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Enum) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Integer) arg0), arg1);
    }

}