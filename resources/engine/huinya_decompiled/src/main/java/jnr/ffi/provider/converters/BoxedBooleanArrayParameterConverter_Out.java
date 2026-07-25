// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.BoxedBooleanArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.BoxedBooleanArrayParameterConverter;

public final class BoxedBooleanArrayParameterConverter_Out extends BoxedBooleanArrayParameterConverter implements ToNativeConverter_PostInvocation {

   BoxedBooleanArrayParameterConverter_Out(int arg0) { // было: <init>
        super(arg0);
    }

  public void postInvoke(Boolean[] arg0, boolean[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                int var4 = 0;
                while (var4 < arg0.length) {
                    arg0[var4] = Boolean.valueOf(arg1[var4]);
                    ++var4;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Boolean[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Boolean[]) arg0), ((boolean[]) arg1), arg2);
    }

}