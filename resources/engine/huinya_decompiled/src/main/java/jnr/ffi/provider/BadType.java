// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.BadType
package jnr.ffi.provider;

import jnr.ffi.NativeType;
import jnr.ffi.Type;

public final class BadType extends Type {

    // ---- поля ----
  private final String typeName;

  public BadType(String arg0) { // было: <init>
        super();
        typeName = arg0;
    }

  public final int alignment() {
        throw new RuntimeException(new StringBuilder().append("invalid type: ").append(typeName).toString());
    }

  public final int size() {
        throw new RuntimeException(new StringBuilder().append("invalid type: ").append(typeName).toString());
    }

  public NativeType getNativeType() {
        throw new RuntimeException(new StringBuilder().append("invalid type: ").append(typeName).toString());
    }

}