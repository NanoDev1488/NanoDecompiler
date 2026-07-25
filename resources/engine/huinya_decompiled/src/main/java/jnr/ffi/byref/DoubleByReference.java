// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.DoubleByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class DoubleByReference extends AbstractNumberReference {

    // ---- поля ----
  private static final Double DEFAULT;

    static {
        DEFAULT = Double.valueOf(0.0);
    }

  public DoubleByReference() { // было: <init>
        super(DEFAULT);
    }

  public DoubleByReference(Double arg0) { // было: <init>
        super(((Double) checkNull(arg0)));
    }

  public DoubleByReference(double arg0) { // было: <init>
        super(Double.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putDouble(arg2, (((Double) value)).doubleValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Double.valueOf(arg1.getDouble(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return 8;
    }

}