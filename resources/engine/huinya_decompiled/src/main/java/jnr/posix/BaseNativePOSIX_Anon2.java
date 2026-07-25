// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX$2
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeContext;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.DefaultNativeGroup;

class BaseNativePOSIX_Anon2 extends BaseNativePOSIX_PointerConverter {

   BaseNativePOSIX_Anon2() { // было: <init>
        super();
    }

  public Object fromNative(Object arg0, FromNativeContext arg1) {
        return arg0 == null ? null : new DefaultNativeGroup(((Pointer) arg0));
    }

}