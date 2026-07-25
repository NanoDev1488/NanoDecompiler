// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.AbstractReference
package jnr.ffi.byref;

import jnr.ffi.byref.ByReference;

public abstract class AbstractReference implements ByReference {

    // ---- поля ----
   Object value;

  protected AbstractReference(Object arg0) { // было: <init>
        super();
        value = arg0;
    }

  protected static Object checkNull(Object arg0) {
        if (arg0 != null) {
            return arg0;
        } else {
            throw new NullPointerException("reference value cannot be null");
        }
    }

  public Object getValue() {
        return value;
    }

}