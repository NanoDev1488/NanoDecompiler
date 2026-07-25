// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StructByReferenceToNativeConverter
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public final class StructByReferenceToNativeConverter implements ToNativeConverter {

    // ---- поля ----
  private final int flags;

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        return new StructByReferenceToNativeConverter(ParameterFlags.parse(arg0.getAnnotations()));
    }

   StructByReferenceToNativeConverter(int arg0) { // было: <init>
        super();
        flags = arg0;
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Pointer toNative(Struct arg0, ToNativeContext arg1) {
        return arg0 == null ? null : Struct.getMemory(arg0, flags);
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Struct) arg0), arg1);
    }

}