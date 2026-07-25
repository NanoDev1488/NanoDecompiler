// исходный (обфусцированный) внутренний класс: jnr.ffi.Type
package jnr.ffi;

import jnr.ffi.NativeType;

public abstract class Type {

  public Type() { // было: <init>
        super();
    }

  public abstract int size();

  public abstract int alignment();

  public abstract NativeType getNativeType();

}