// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.DefaultObjectReferenceManager.ObjectReference
package jnr.ffi.provider;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;

final class DefaultObjectReferenceManager_ObjectReference extends InAccessibleMemoryIO {

    // ---- поля ----
  private final Object referent;

  public DefaultObjectReferenceManager_ObjectReference(Runtime arg0, long arg1, Object arg2) { // было: <init>
        super(arg0, arg1, true);
        referent = arg2;
    }

  public long size() {
        return 0L;
    }

  public int hashCode() {
        return ((int) address());
    }

  public boolean equals(Object arg0) {
        return !(arg0 instanceof Pointer) ? 0 : (((Pointer) arg0)).address() == address();
    }

  static Object access$000(DefaultObjectReferenceManager_ObjectReference arg0) {
        return arg0.referent;
    }

}