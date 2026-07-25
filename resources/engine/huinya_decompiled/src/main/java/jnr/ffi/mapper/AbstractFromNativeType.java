// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.AbstractFromNativeType
package jnr.ffi.mapper;

import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.FromNativeType;

public abstract class AbstractFromNativeType implements FromNativeType {

    // ---- поля ----
  private final FromNativeConverter converter;

   AbstractFromNativeType(FromNativeConverter arg0) { // было: <init>
        super();
        converter = arg0;
    }

  public FromNativeConverter getFromNativeConverter() {
        return converter;
    }

}