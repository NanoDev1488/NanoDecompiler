// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO.BigEndianArrayIO
package jnr.ffi.provider;

import jnr.ffi.provider.AbstractArrayMemoryIO_Anon1;
import jnr.ffi.provider.AbstractArrayMemoryIO_ArrayIO;

abstract class AbstractArrayMemoryIO_BigEndianArrayIO extends AbstractArrayMemoryIO_ArrayIO {

  private AbstractArrayMemoryIO_BigEndianArrayIO() { // было: <init>
        super();
    }

  public short getInt16(byte[] arg0, int arg1) {
        return ((short) ((arg0[arg1 + 0] & 255) << 8 | arg0[arg1 + 1] & 255));
    }

  public int getInt32(byte[] arg0, int arg1) {
        return (arg0[arg1 + 0] & 255) << 24 | (arg0[arg1 + 1] & 255) << 16 | (arg0[arg1 + 2] & 255) << 8 | (arg0[arg1 + 3] & 255) << 0;
    }

  public long getInt64(byte[] arg0, int arg1) {
        return (((long) arg0[arg1 + 0]) & 255L) << 56 | (((long) arg0[arg1 + 1]) & 255L) << 48 | (((long) arg0[arg1 + 2]) & 255L) << 40 | (((long) arg0[arg1 + 3]) & 255L) << 32 | (((long) arg0[arg1 + 4]) & 255L) << 24 | (((long) arg0[arg1 + 5]) & 255L) << 16 | (((long) arg0[arg1 + 6]) & 255L) << 8 | (((long) arg0[arg1 + 7]) & 255L) << 0;
    }

  public final void putInt16(byte[] arg0, int arg1, int arg2) {
        arg0[arg1 + 0] = ((byte) (arg2 >> 8));
        arg0[arg1 + 1] = ((byte) (arg2 >> 0));
    }

  public final void putInt32(byte[] arg0, int arg1, int arg2) {
        arg0[arg1 + 0] = ((byte) (arg2 >> 24));
        arg0[arg1 + 1] = ((byte) (arg2 >> 16));
        arg0[arg1 + 2] = ((byte) (arg2 >> 8));
        arg0[arg1 + 3] = ((byte) (arg2 >> 0));
    }

  public final void putInt64(byte[] arg0, int arg1, long arg2) {
        arg0[arg1 + 0] = ((byte) ((int) (arg2 >> 56)));
        arg0[arg1 + 1] = ((byte) ((int) (arg2 >> 48)));
        arg0[arg1 + 2] = ((byte) ((int) (arg2 >> 40)));
        arg0[arg1 + 3] = ((byte) ((int) (arg2 >> 32)));
        arg0[arg1 + 4] = ((byte) ((int) (arg2 >> 24)));
        arg0[arg1 + 5] = ((byte) ((int) (arg2 >> 16)));
        arg0[arg1 + 6] = ((byte) ((int) (arg2 >> 8)));
        arg0[arg1 + 7] = ((byte) ((int) (arg2 >> 0)));
    }

   AbstractArrayMemoryIO_BigEndianArrayIO(AbstractArrayMemoryIO_Anon1 arg0) { // было: <init>
        this();
    }

}