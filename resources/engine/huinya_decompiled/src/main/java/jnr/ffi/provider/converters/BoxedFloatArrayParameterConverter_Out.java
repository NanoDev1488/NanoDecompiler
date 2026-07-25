// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedFloatArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.BoxedFloatArrayParameterConverter;

public final class BoxedFloatArrayParameterConverter_Out extends BoxedFloatArrayParameterConverter implements ToNativeConverter_PostInvocation {

   BoxedFloatArrayParameterConverter_Out(int arg0) { // было: <init>
        super(arg0);
    }

  public void postInvoke(Float[] arg0, float[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    arg0[var4] = Float.valueOf(arg1[var4]);
                    ++var4;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Float[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Float[]) arg0), ((float[]) arg1), arg2);
    }

}