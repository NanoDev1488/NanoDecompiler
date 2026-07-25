// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.Pointer32ArrayParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.MemoryManager;
import jnr.ffi.provider.ParameterFlags;
import jnr.ffi.provider.converters.Pointer32ArrayParameterConverter;

public final class Pointer32ArrayParameterConverter_Out extends Pointer32ArrayParameterConverter implements ToNativeConverter_PostInvocation {

  public Pointer32ArrayParameterConverter_Out(Runtime arg0, int arg1) { // было: <init>
        super(arg0, arg1);
    }

  public void postInvoke(Pointer[] arg0, int[] arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                if (ParameterFlags.isOut(parameterFlags)) {
                    MemoryManager var4 = runtime.getMemoryManager();
                    int var5 = 0;
                    while (var5 < arg0.length) {
                        arg0[var5] = var4.newPointer(((long) arg1[var5]));
                        ++var5;
                        continue;
                    }
                }
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((Pointer[]) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((Pointer[]) arg0), ((int[]) arg1), arg2);
    }

}