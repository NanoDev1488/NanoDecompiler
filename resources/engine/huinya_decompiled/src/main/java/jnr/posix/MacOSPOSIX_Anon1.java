// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSPOSIX$1
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.MacOSPasswd;

class MacOSPOSIX_Anon1 extends BaseNativePOSIX_PointerConverter {

   MacOSPOSIX_Anon1() { // было: <init>
        super();
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return arg0 == null ? null : new MacOSPasswd(((Pointer) arg0));
    }

}