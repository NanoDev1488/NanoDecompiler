// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.IntByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class IntByReference extends AbstractNumberReference {

  public IntByReference() { // было: <init>
        super(Integer.valueOf(0));
    }

  public IntByReference(Integer arg0) { // было: <init>
        super(((Integer) checkNull(arg0)));
    }

  public IntByReference(int arg0) { // было: <init>
        super(Integer.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putInt(arg2, (((Integer) value)).intValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Integer.valueOf(arg1.getInt(arg2));
    }

  public int nativeSize(Runtime arg0) {
        return 4;
    }

}