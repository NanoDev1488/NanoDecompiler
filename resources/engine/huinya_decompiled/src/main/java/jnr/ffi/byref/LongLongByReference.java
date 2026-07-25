// исходный (обфусцированный) внутренний класс: jnr.ffi.byref.LongLongByReference
package jnr.ffi.byref;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.byref.AbstractNumberReference;

public final class LongLongByReference extends AbstractNumberReference {

  public LongLongByReference() { // было: <init>
        super(Long.valueOf(0L));
    }

  public LongLongByReference(Long arg0) { // было: <init>
        super(((Long) checkNull(arg0)));
    }

  public LongLongByReference(long arg0) { // было: <init>
        super(Long.valueOf(arg0));
    }

  public void toNative(Runtime arg0, Pointer arg1, long arg2) {
        arg1.putLongLong(arg2, (((Long) value)).longValue());
    }

  public void fromNative(Runtime arg0, Pointer arg1, long arg2) {
        value = Long.valueOf(arg1.getLongLong(arg2));
    }

  public final int nativeSize(Runtime arg0) {
        return 8;
    }

}