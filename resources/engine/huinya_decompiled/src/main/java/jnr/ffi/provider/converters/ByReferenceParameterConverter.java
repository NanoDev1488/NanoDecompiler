// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.ByReferenceParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.byref.ByReference;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.ByReferenceParameterConverter_Anon1;
import jnr.ffi.provider.converters.ByReferenceParameterConverter_Out;

@ToNativeConverter_Cacheable
public class ByReferenceParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private static final ToNativeConverter IN;
  private static final ToNativeConverter OUT;
  private static final ToNativeConverter INOUT;
  private final int parameterFlags;

    static {
        IN = new ByReferenceParameterConverter(2);
        OUT = new ByReferenceParameterConverter_Out(1);
        INOUT = new ByReferenceParameterConverter_Out(3);
    }

  private ByReferenceParameterConverter(int arg0) { // было: <init>
        super();
        parameterFlags = arg0;
    }

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return !ParameterFlags.isOut(var1) ? IN : !ParameterFlags.isIn(var1) ? OUT : INOUT;
    }

  public Pointer toNative(ByReference arg0, ToNativeContext arg1) {
        if (arg0 != null) {
            Pointer var3 = Memory.allocate(arg1.getRuntime(), arg0.nativeSize(arg1.getRuntime()));
            if (ParameterFlags.isIn(parameterFlags)) {
                arg0.toNative(arg1.getRuntime(), var3, 0L);
            }
            return var3;
        } else {
            return null;
        }
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((ByReference) arg0), arg1);
    }

   ByReferenceParameterConverter(int arg0, ByReferenceParameterConverter_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}