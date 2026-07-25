// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX$3
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.posix.BaseFileStat;
import jnr.posix.FileStat;

class BaseNativePOSIX_Anon3 implements ToNativeConverter {

   BaseNativePOSIX_Anon3() { // было: <init>
        super();
    }

  public Pointer toNative(FileStat arg0, ToNativeContext arg1) {
        if (!(arg0 instanceof BaseFileStat)) {
            if (!(arg0 instanceof Struct)) {
                if (arg0 != null) {
                    throw new IllegalArgumentException(new StringBuilder().append("instance of ").append(arg0.getClass()).append(" is not a struct").toString());
                } else {
                    return null;
                }
            } else {
                return Struct.getMemory(((Struct) arg0));
            }
        } else {
            return (((BaseFileStat) arg0)).memory;
        }
    }

  public Class nativeType() {
        return Pointer.class;
    }

  public Object toNative(Object arg0, ToNativeContext arg1) {
        return toNative(((FileStat) arg0), arg1);
    }

}