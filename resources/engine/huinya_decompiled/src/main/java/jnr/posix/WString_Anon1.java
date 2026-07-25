// исходный (обфусцированный) внутренний класс: jnr.posix.WString$1
package jnr.posix;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.posix.WString;

class WString_Anon1 implements ToNativeConverter {

   WString_Anon1() { // было: <init>
        super();
    }

  public Pointer toNative(WString arg0, ToNativeContext arg1) {
        if (arg0 != null) {
            Pointer var3 = Memory.allocateDirect(WString.runtime, WString.access$000(arg0).length + 1, true);
            var3.put(0L, WString.access$000(arg0), 0, WString.access$000(arg0).length);
            return var3;
        } else {
            return null;
        }
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((WString) arg0), arg1);
    }

}