// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsPOSIX$1
package jnr.posix;

import jnr.ffi.mapper.FromNativeContext;
import jnr.posix.BaseNativePOSIX_PointerConverter;

class WindowsPOSIX_Anon1 extends BaseNativePOSIX_PointerConverter {

   WindowsPOSIX_Anon1() { // было: <init>
        super();
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        throw new RuntimeException("no support for native passwd");
    }

}