// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.FloatByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class FloatByReference extends AbstractNumberReference {

    // ---- поля ----
  private static final Float DEFAULT;

    static {
        DEFAULT = Float.valueOf(0.0f);
    }

  public FloatByReference() { // было: <init>
        super(DEFAULT);
    }

  public FloatByReference(Float arg0) { // было: <init>
        super(((Float) checkNull(arg0)));
    }

  public FloatByReference(float arg0) { // было: <init>
        super(Float.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putFloat(arg2, (((Float) value)).floatValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Float.valueOf(arg1.getFloat(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return 4;
    }

}