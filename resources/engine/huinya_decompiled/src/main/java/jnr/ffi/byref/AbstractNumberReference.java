// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.AbstractNumberReference
package jnr.ffi.byref;

import jnr.ffi.byref.ByReference;

public abstract class AbstractNumberReference extends Number implements ByReference {

    // ---- поля ----
   Number value;

  protected AbstractNumberReference(Number arg0) { // было: <init>
        super();
        value = arg0;
    }

  protected static Number checkNull(Number arg0) {
        if (arg0 != null) {
            return arg0;
        } else {
            throw new NullPointerException("reference value cannot be null");
        }
    }

  public Number getValue() {
        return value;
    }

  public final byte byteValue() {
        return value.byteValue();
    }

  public final short shortValue() {
        return ((short) value.byteValue());
    }

  public final int intValue() {
        return value.intValue();
    }

  public final long longValue() {
        return value.longValue();
    }

  public final float floatValue() {
        return value.floatValue();
    }

  public final double doubleValue() {
        return value.doubleValue();
    }

  public Object getValue() {
        return getValue();
    }

}