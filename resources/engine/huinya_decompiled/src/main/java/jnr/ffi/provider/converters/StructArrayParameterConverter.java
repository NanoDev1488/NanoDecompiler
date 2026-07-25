// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.StructArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.DelegatingMemoryIO;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.StructArrayParameterConverter_Out;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class StructArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  protected final Runtime runtime;
  protected final int parameterFlags;

  public static ToNativeConverter getInstance(ToNativeContext arg0, Class arg1) {
        int var2 = ParameterFlags.parse(arg0.getAnnotations());
        return ParameterFlags.isOut(var2) ? new StructArrayParameterConverter_Out(arg0.getRuntime(), arg1.asSubclass(Struct.class), var2) : new StructArrayParameterConverter(arg0.getRuntime(), var2);
    }

   StructArrayParameterConverter(Runtime arg0, int arg1) { // было: <init>
        super();
        runtime = arg0;
        parameterFlags = arg1;
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Pointer toNative(Struct[] arg0, ToNativeContext arg1) {
        if (arg0 != null) {
            Pointer var3 = Struct.getMemory(((Struct) arg0[0]), parameterFlags);
            if (var3 instanceof DelegatingMemoryIO) {
                return (((DelegatingMemoryIO) var3)).getDelegatedMemoryIO();
            } else {
                throw new RuntimeException("Struct array must be backed by contiguous array");
            }
        } else {
            return null;
        }
    }

  private static int align(int arg0, int arg1) {
        return arg0 + arg1 - 1 & (arg1 - 1 ^ -1);
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Struct[]) arg0), arg1);
    }

  static int access$000(int arg0, int arg1) {
        return align(arg0, arg1);
    }

}