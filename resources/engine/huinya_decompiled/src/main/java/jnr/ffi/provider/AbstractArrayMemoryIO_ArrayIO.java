// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO.ArrayIO
package jnr.ffi.provider;

import java.nio.ByteOrder;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractArrayMemoryIO_BE32ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_BE64ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_LE32ArrayIO;
import jnr.ffi.provider.AbstractArrayMemoryIO_LE64ArrayIO;

public abstract class AbstractArrayMemoryIO_ArrayIO {

  protected AbstractArrayMemoryIO_ArrayIO() { // было: <init>
        super();
    }

  public static AbstractArrayMemoryIO_ArrayIO getArrayIO(Runtime arg0) {
        if (!arg0.byteOrder().equals(ByteOrder.BIG_ENDIAN)) {
            return arg0.addressSize() != 8 ? AbstractArrayMemoryIO_LE32ArrayIO.INSTANCE : AbstractArrayMemoryIO_LE64ArrayIO.INSTANCE;
        } else {
            return arg0.addressSize() != 8 ? AbstractArrayMemoryIO_BE32ArrayIO.INSTANCE : AbstractArrayMemoryIO_BE64ArrayIO.INSTANCE;
        }
    }

  public abstract short getInt16(byte[] arg0, int arg1);

  public abstract int getInt32(byte[] arg0, int arg1);

  public abstract long getInt64(byte[] arg0, int arg1);

  public abstract long getAddress(byte[] arg0, int arg1);

  public abstract void putInt16(byte[] arg0, int arg1, int arg2);

  public abstract void putInt32(byte[] arg0, int arg1, int arg2);

  public abstract void putInt64(byte[] arg0, int arg1, long arg2);

  public abstract void putAddress(byte[] arg0, int arg1, long arg2);

  public final float getFloat32(byte[] arg0, int arg1) {
        return Float.intBitsToFloat(getInt32(arg0, arg1));
    }

  public final void putFloat32(byte[] arg0, int arg1, float arg2) {
        putInt32(arg0, arg1, Float.floatToRawIntBits(arg2));
    }

  public final double getFloat64(byte[] arg0, int arg1) {
        return Double.longBitsToDouble(getInt64(arg0, arg1));
    }

  public final void putFloat64(byte[] arg0, int arg1, double arg2) {
        putInt64(arg0, arg1, Double.doubleToRawLongBits(arg2));
    }

}