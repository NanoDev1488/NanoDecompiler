// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.NativeLongByReference
package jnr.ffi.byref;

import jnr.ffi.NativeLong;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class NativeLongByReference extends AbstractNumberReference {

  public NativeLongByReference() { // было: <init>
        super(NativeLong.valueOf(0));
    }

  public NativeLongByReference(NativeLong arg0) { // было: <init>
        super(((NativeLong) checkNull(arg0)));
    }

  public NativeLongByReference(long arg0) { // было: <init>
        super(NativeLong.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putNativeLong(arg2, (((NativeLong) value)).longValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = NativeLong.valueOf(arg1.getNativeLong(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return arg0.longSize();
    }

}