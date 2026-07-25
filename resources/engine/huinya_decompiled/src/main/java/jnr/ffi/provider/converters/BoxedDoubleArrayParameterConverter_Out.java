// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedDoubleArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.BoxedDoubleArrayParameterConverter;

public final class BoxedDoubleArrayParameterConverter_Out extends BoxedDoubleArrayParameterConverter implements ToNativeConverter_PostInvocation {

   BoxedDoubleArrayParameterConverter_Out(int arg0) { // было: <init>
        super(arg0);
    }

  public void postInvoke(Double[] arg0, double[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    arg0[var4] = Double.valueOf(arg1[var4]);
                    ++var4;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Double[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Double[]) arg0), ((double[]) arg1), arg2);
    }

}