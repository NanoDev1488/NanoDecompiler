// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.Long32ArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.Long32ArrayParameterConverter_Anon1;
import jnr.ffi.provider.converters.Long32ArrayParameterConverter_Out;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class Long32ArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private static final Long32ArrayParameterConverter IN;
  private static final Long32ArrayParameterConverter OUT;
  private static final Long32ArrayParameterConverter INOUT;
  private final int parameterFlags;

    static {
        IN = new Long32ArrayParameterConverter(2);
        OUT = new Long32ArrayParameterConverter_Out(1);
        INOUT = new Long32ArrayParameterConverter_Out(3);
    }

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return !ParameterFlags.isOut(var1) ? IN : !ParameterFlags.isIn(var1) ? OUT : INOUT;
    }

  private Long32ArrayParameterConverter(int arg0) { // было: <init>
        super();
        parameterFlags = arg0;
    }

  public int[] toNative(long[] arg0, ToNativeContext arg1) {
        int[] var3;
        if (arg0 != null) {
            var3 = new int[arg0.length];
            if (ParameterFlags.isIn(parameterFlags)) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    var3[var4] = ((int) arg0[var4]);
                    ++var4;
                    continue;
                }
            }
        } else {
            return null;
        }
        return var3;
    }

  public Class nativeType() {
        return int[].class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((long[]) arg0), arg1);
    }

   Long32ArrayParameterConverter(int arg0, Long32ArrayParameterConverter_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}