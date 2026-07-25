// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.converters.ByReferenceParameterConverter.Out
package jnr.ffi.provider.converters;

import jnr.ffi.Pointer;
import jnr.ffi.byref.ByReference;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.converters.ByReferenceParameterConverter;

public final class ByReferenceParameterConverter_Out extends ByReferenceParameterConverter implements ToNativeConverter_PostInvocation {

  public ByReferenceParameterConverter_Out(int arg0) { // было: <init>
        super(arg0, null);
    }

  public void postInvoke(ByReference arg0, Pointer arg1, ToNativeContext arg2) {
        if (arg0 != null) {
            if (arg1 != null) {
                arg0.fromNative(arg2.getRuntime(), arg1, 0L);
            }
        }
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return super.toNative(((ByReference) arg0), arg1);
    }

  public void postInvoke(Object arg0, Object arg1, ToNativeContext arg2) {
        postInvoke(((ByReference) arg0), ((Pointer) arg1), arg2);
    }

}