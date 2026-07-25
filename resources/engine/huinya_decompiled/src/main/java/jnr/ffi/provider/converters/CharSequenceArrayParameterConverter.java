// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.CharSequenceArrayParameterConverter
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_Cacheable;
import jnr.ffi.mapper.ToNativeConverter_NoContext;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.CharSequenceArrayParameterConverter_Out;
import jnr.ffi.provider.converters.CharSequenceArrayParameterConverter_StringArray;

@ToNativeConverter_NoContext
@ToNativeConverter_Cacheable
public class CharSequenceArrayParameterConverter implements ToNativeConverter {

    // ---- поля ----
  private final Runtime runtime;
  private final int parameterFlags;

  public static ToNativeConverter getInstance(ToNativeContext arg0) {
        int var1 = ParameterFlags.parse(arg0.getAnnotations());
        return ParameterFlags.isOut(var1) ? new CharSequenceArrayParameterConverter_Out(arg0.getRuntime(), var1) : new CharSequenceArrayParameterConverter(arg0.getRuntime(), var1);
    }

   CharSequenceArrayParameterConverter(Runtime arg0, int arg1) { // было: <init>
        super();
        runtime = arg0;
        parameterFlags = arg1;
    }

  public Pointer toNative(CharSequence[] arg0, ToNativeContext arg1) {
        CharSequenceArrayParameterConverter_StringArray var3;
        if (arg0 != null) {
            var3 = CharSequenceArrayParameterConverter_StringArray.allocate(runtime, arg0.length + 1);
            if (ParameterFlags.isIn(parameterFlags)) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    var3.put(var4, ((CharSequence) arg0[var4]));
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
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((CharSequence[]) arg0), arg1);
    }

}