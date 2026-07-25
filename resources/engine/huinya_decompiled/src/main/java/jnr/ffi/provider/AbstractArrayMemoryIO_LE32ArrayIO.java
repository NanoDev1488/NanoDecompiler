// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO.LE32ArrayIO
package jnr.ffi.provider;

import jnr.ffi.provider.AbstractArrayMemoryIO_ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_LittleEndianArrayIO;

final class AbstractArrayMemoryIO_LE32ArrayIO extends AbstractArrayMemoryIO_LittleEndianArrayIO {

    // ---- поля ----
  public static final AbstractArrayMemoryIO_ArrayIO INSTANCE;

    static {
        INSTANCE = new AbstractArrayMemoryIO_LE32ArrayIO();
    }

  private AbstractArrayMemoryIO_LE32ArrayIO() { // было: <init>
        super(null);
    }

  public final long getAddress(byte[] arg0, int arg1) {
        return ((long) getInt32(arg0, arg1)) & 4294967295L;
    }

  public final void putAddress(byte[] arg0, int arg1, long arg2) {
        putInt32(arg0, arg1, ((int) arg2));
    }

}