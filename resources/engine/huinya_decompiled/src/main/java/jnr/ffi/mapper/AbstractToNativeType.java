// исходный (обфусцированный) внутренний класс: jnr.ffi.mapper.AbstractToNativeType
package jnr.ffi.mapper;

import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeType;

public abstract class AbstractToNativeType implements ToNativeType {

    // ---- поля ----
  private final ToNativeConverter converter;

   AbstractToNativeType(ToNativeConverter arg0) { // было: <init>
        super();
        converter = arg0;
    }

  public ToNativeConverter getToNativeConverter() {
        return converter;
    }

}