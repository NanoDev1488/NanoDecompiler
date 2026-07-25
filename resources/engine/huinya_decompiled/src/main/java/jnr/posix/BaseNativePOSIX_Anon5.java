// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX$5
package jnr.posix;

import jnr.constants.Constant;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;

class BaseNativePOSIX_Anon5 implements ToNativeConverter {

   BaseNativePOSIX_Anon5() { // было: <init>
        super();
    }

  public Integer toNative(Constant arg0, ToNativeContext arg1) {
        return Integer.valueOf(arg0.intValue());
    }

  public Class nativeType() {
        return Integer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((Constant) arg0), arg1);
    }

}