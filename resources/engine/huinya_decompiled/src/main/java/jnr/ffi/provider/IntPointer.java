// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.IntPointer
package jnr.ffi.provider;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InAccessibleMemoryIO;

public final class IntPointer extends InAccessibleMemoryIO {

  public IntPointer(Runtime arg0, long arg1) { // было: <init>
        super(arg0, arg1, true);
    }

  public IntPointer(Runtime arg0, int arg1) { // было: <init>
        super(arg0, ((long) arg1) & 4294967295L, true);
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

}