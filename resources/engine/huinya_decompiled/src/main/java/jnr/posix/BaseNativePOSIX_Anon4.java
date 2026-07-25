// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX$4
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.posix.NativeTimes;

class BaseNativePOSIX_Anon4 implements ToNativeConverter {

   BaseNativePOSIX_Anon4() { // было: <init>
        super();
    }

  public Pointer toNative(NativeTimes arg0, ToNativeContext arg1) {
        return arg0.memory;
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((NativeTimes) arg0), arg1);
    }

}