// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.BoxedLong64ArrayParameterConverter;

public final class BoxedLong64ArrayParameterConverter_Out extends BoxedLong64ArrayParameterConverter implements ToNativeConverter_PostInvocation {

   BoxedLong64ArrayParameterConverter_Out(int arg0) { // было: <init>
        super(arg0);
    }

  public void postInvoke(Long[] arg0, long[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    arg0[var4] = Long.valueOf(arg1[var4]);
                    ++var4;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Long[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Long[]) arg0), ((long[]) arg1), arg2);
    }

}