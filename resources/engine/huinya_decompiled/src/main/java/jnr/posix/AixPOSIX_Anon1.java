// исходный (обфусцированный) внутренний класс: jnr.posix.AixPOSIX$1
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.posix.AixPasswd;
import jnr.posix.BaseNativePOSIX_PointerConverter;

class AixPOSIX_Anon1 extends BaseNativePOSIX_PointerConverter {

   AixPOSIX_Anon1() { // было: <init>
        super();
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return arg0 == null ? null : new AixPasswd(((Pointer) arg0));
    }

}