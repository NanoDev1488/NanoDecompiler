// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO.BE64ArrayIO
package jnr.ffi.provider;

import jnr.ffi.provider.AbstractArrayMemoryIO_ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_BigEndianArrayIO;

final class AbstractArrayMemoryIO_BE64ArrayIO extends AbstractArrayMemoryIO_BigEndianArrayIO {

    // ---- поля ----
  public static final AbstractArrayMemoryIO_ArrayIO INSTANCE;

    static {
        INSTANCE = new AbstractArrayMemoryIO_BE64ArrayIO();
    }

  private AbstractArrayMemoryIO_BE64ArrayIO() { // было: <init>
        super(null);
    }

  public final long getAddress(byte[] arg0, int arg1) {
        return getInt64(arg0, arg1);
    }

  public final void putAddress(byte[] arg0, int arg1, long arg2) {
        putInt64(arg0, arg1, arg2);
    }

}