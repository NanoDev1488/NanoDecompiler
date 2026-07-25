// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.NativeLongConverter
package jnr.ffi.provider.converters;

import jnr.ffi.NativeLong;
import jnr.ffi.mapper.AbstractDataConverter;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter_Cacheable;
import jnr.ffi.mapper.FromNativeConverter_NoContext;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;

@ToNativeConverter_NoContext
@FromNativeConverter_NoContext
@ToNativeConverter_Cacheable
@FromNativeConverter_Cacheable
public final class NativeLongConverter extends AbstractDataConverter {

    // ---- поля ----
  private static final DataConverter INSTANCE;

    static {
        INSTANCE = new NativeLongConverter();
    }

  public NativeLongConverter() { // было: <init>
        super();
    }

  public static DataConverter getInstance() {
        return INSTANCE;
    }

  public Class nativeType() {
        return Long.class;
    }

  public Long toNative(NativeLong arg0, ToNativeContext arg1) {
        return Long.valueOf(arg0.longValue());
    }

  public NativeLong fromNative(Long arg0, FromNativeContext arg1) {
        return NativeLong.valueOf(arg0.longValue());
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((NativeLong) arg0), arg1);
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return fromNative(((Long) arg0), arg1);
    }

}