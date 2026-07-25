// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX.PointerConverter
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.FromNativeConverter;

public abstract class BaseNativePOSIX_PointerConverter implements FromNativeConverter {

  public BaseNativePOSIX_PointerConverter() { // было: <init>
        super();
    }

  public Class nativeType() {
        return Pointer.class;
    }

}