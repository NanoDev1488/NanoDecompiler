// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.ShortByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class ShortByReference extends AbstractNumberReference {

  public ShortByReference() { // было: <init>
        super(Short.valueOf(0));
    }

  public ShortByReference(Short arg0) { // было: <init>
        super(((Short) checkNull(arg0)));
    }

  public ShortByReference(short arg0) { // было: <init>
        super(Short.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putShort(arg2, (((Short) value)).shortValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Short.valueOf(arg1.getShort(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return 2;
    }

}