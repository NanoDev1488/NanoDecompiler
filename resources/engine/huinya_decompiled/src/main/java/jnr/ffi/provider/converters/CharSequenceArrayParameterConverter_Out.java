// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.CharSequenceArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.CharSequenceArrayParameterConverter;
import jnr.ffi.provider.converters.CharSequenceArrayParameterConverter_StringArray;

public final class CharSequenceArrayParameterConverter_Out extends CharSequenceArrayParameterConverter implements ToNativeConverter_PostInvocation {

   CharSequenceArrayParameterConverter_Out(Runtime arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public void postInvoke(CharSequence[] arg0, Pointer arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                CharSequenceArrayParameterConverter_StringArray var4 = ((CharSequenceArrayParameterConverter_StringArray) arg1);
                int var5 = 0;
                while (var5 < arg0.length) {
                    arg0[var5] = var4.get(var5);
                    ++var5;
                    continue;
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((CharSequence[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((CharSequence[]) arg0), ((Pointer) arg1), arg2);
    }

}