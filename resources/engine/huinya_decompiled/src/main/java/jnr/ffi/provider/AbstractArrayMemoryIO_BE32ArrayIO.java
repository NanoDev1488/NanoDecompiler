// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO.BE32ArrayIO
package jnr.ffi.provider;

import jnr.ffi.provider.AbstractArrayMemoryIO_ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_BigEndianArrayIO;

final class AbstractArrayMemoryIO_BE32ArrayIO extends AbstractArrayMemoryIO_BigEndianArrayIO {

    // ---- поля ----
  public static final AbstractArrayMemoryIO_ArrayIO INSTANCE;

    static {
        INSTANCE = new AbstractArrayMemoryIO_BE32ArrayIO();
    }

  private AbstractArrayMemoryIO_BE32ArrayIO() { // было: <init>
        super(null);
    }

  public final long getAddress(byte[] arg0, int arg1) {
        return ((long) getInt32(arg0, arg1)) & 4294967295L;
    }

  public final void putAddress(byte[] arg0, int arg1, long arg2) {
        putInt32(arg0, arg1, ((int) arg2));
    }

}