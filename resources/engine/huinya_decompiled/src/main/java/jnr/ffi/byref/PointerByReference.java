// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.PointerByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractReference;

public final class PointerByReference extends AbstractReference {

  public PointerByReference() { // было: <init>
        super(null);
    }

  public PointerByReference(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public final void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putPointer(arg2, ((Pointer) value));
    }

  public final void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = arg1.getPointer(arg2);
    }

  public final int nativeSize(Runtime arg0) {
        return arg0.addressSize();
    }

}