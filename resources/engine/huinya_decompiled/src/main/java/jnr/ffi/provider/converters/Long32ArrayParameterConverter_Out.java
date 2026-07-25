// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.Long32ArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.Long32ArrayParameterConverter;

public final class Long32ArrayParameterConverter_Out extends Long32ArrayParameterConverter implements ToNativeConverter_PostInvocation {

   Long32ArrayParameterConverter_Out(int arg0) { // было: <init>
        super(arg0, null);
    }

  public void postInvoke(long[] arg0, int[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    arg0[var4] = ((long) arg1[var4]);
                    ++var4;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((long[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((long[]) arg0), ((int[]) arg1), arg2);
    }

}