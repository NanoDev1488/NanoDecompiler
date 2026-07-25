// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractArrayMemoryIO
package jnr.ffi.provider;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractArrayMemoryIO_ArrayIO;
import jnr.ffi.provider.AbstractMemoryIO;
import jnr.ffi.util.BufferUtil;

public abstract class AbstractArrayMemoryIO extends AbstractMemoryIO {

    // ---- поля ----
  private final AbstractArrayMemoryIO_ArrayIO io;
  protected final byte[] buffer;
  protected final int offset;
  protected final int length;

  protected AbstractArrayMemoryIO(Runtime arg0, byte[] arg1, int arg2, int arg3) { // было: <init>
        super(arg0, 0L, false);
        io = AbstractArrayMemoryIO_ArrayIO.getArrayIO(arg0);
        buffer = arg1;
        offset = arg2;
        length = arg3;
    }

  protected AbstractArrayMemoryIO(Runtime arg0, byte[] arg1) { // было: <init>
        this(arg0, arg1, 0, arg1.length);
    }

  protected AbstractArrayMemoryIO(Runtime arg0, int arg1) { // было: <init>
        this(arg0, new byte[arg1], 0, arg1);
    }

  protected final AbstractArrayMemoryIO_ArrayIO getArrayIO() {
        return io;
    }

  public final byte[] array() {
        return buffer;
    }

  public final int offset() {
        return offset;
    }

  public final int length() {
        return length;
    }

  public final int arrayLength() {
        return length;
    }

  public final int arrayOffset() {
        return offset;
    }

  public final boolean hasArray() {
        return true;
    }

  public final long size() {
        return ((long) length);
    }

  protected final int index(long arg0) {
        return offset + ((int) arg0);
    }

  protected final int remaining(long arg0) {
        return length - ((int) arg0);
    }

  public final boolean isNull() {
        return false;
    }

  public String getString(long arg0) {
        return BufferUtil.getString(ByteBuffer.wrap(buffer, index(arg0), length - ((int) arg0)), Charset.defaultCharset());
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        return BufferUtil.getString(ByteBuffer.wrap(buffer, index(arg0), Math.min(length - ((int) arg0), arg1)), arg2);
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        ByteBuffer var6 = arg3.encode(arg1);
        int var7 = Math.min(arg2 - 1, Math.min(var6.remaining(), remaining(arg0)));
        var6.get(buffer, index(arg0), var7);
        buffer[index(arg0) + var7] = 0;
    }

  public void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        System.arraycopy(arg1, arg2, buffer, index(arg0), length - ((int) arg0));
        buffer[index(arg0) + arg3] = 0;
    }

  public final byte getByte(long arg0) {
        return ((byte) (buffer[index(arg0)] & 255));
    }

  public final short getShort(long arg0) {
        return io.getInt16(buffer, index(arg0));
    }

  public final int getInt(long arg0) {
        return io.getInt32(buffer, index(arg0));
    }

  public final long getLongLong(long arg0) {
        return io.getInt64(buffer, index(arg0));
    }

  public final long getAddress(long arg0) {
        return io.getAddress(buffer, index(arg0));
    }

  public final float getFloat(long arg0) {
        return io.getFloat32(buffer, index(arg0));
    }

  public final double getDouble(long arg0) {
        return io.getFloat64(buffer, index(arg0));
    }

  public final void putByte(long arg0, byte arg1) {
        buffer[index(arg0)] = arg1;
    }

  public final void putShort(long arg0, short arg1) {
        io.putInt16(buffer, index(arg0), arg1);
    }

  public final void putInt(long arg0, int arg1) {
        io.putInt32(buffer, index(arg0), arg1);
    }

  public final void putLongLong(long arg0, long arg1) {
        io.putInt64(buffer, index(arg0), arg1);
    }

  public final void putAddress(long arg0, long arg1) {
        io.putAddress(buffer, index(arg0), arg1);
    }

  public final void putFloat(long arg0, float arg1) {
        io.putFloat32(buffer, index(arg0), arg1);
    }

  public final void putDouble(long arg0, double arg1) {
        io.putFloat64(buffer, index(arg0), arg1);
    }

  public final void get(long arg0, byte[] arg1, int arg2, int arg3) {
        System.arraycopy(buffer, index(arg0), arg1, arg2, arg3);
    }

  public final void put(long arg0, byte[] arg1, int arg2, int arg3) {
        System.arraycopy(arg1, arg2, buffer, index(arg0), arg3);
    }

  public final void get(long arg0, short[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = io.getInt16(buffer, var6 + (var7 << 1));
            ++var7;
            continue;
        }
    }

  public final void put(long arg0, short[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            io.putInt16(buffer, var6 + (var7 << 1), arg1[arg2 + var7]);
            ++var7;
            continue;
        }
    }

  public final void get(long arg0, int[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = io.getInt32(buffer, var6 + (var7 << 2));
            ++var7;
            continue;
        }
    }

  public final void put(long arg0, int[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            io.putInt32(buffer, var6 + (var7 << 2), arg1[arg2 + var7]);
            ++var7;
            continue;
        }
    }

  public final void get(long arg0, long[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = io.getInt64(buffer, var6 + (var7 << 3));
            ++var7;
            continue;
        }
    }

  public final void put(long arg0, long[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            io.putInt64(buffer, var6 + (var7 << 3), arg1[arg2 + var7]);
            ++var7;
            continue;
        }
    }

  public final void get(long arg0, float[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = io.getFloat32(buffer, var6 + (var7 << 2));
            ++var7;
            continue;
        }
    }

  public final void put(long arg0, float[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            io.putFloat32(buffer, var6 + (var7 << 2), arg1[arg2 + var7]);
            ++var7;
            continue;
        }
    }

  public final void get(long arg0, double[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            arg1[arg2 + var7] = io.getFloat64(buffer, var6 + (var7 << 3));
            ++var7;
            continue;
        }
    }

  public final void put(long arg0, double[] arg1, int arg2, int arg3) {
        int var6 = index(arg0);
        int var7 = 0;
        while (var7 < arg3) {
            io.putFloat64(buffer, var6 + (var7 << 3), arg1[arg2 + var7]);
            ++var7;
            continue;
        }
    }

  public final int indexOf(long arg0, byte arg1) {
        int var4 = index(arg0);
        int var5 = 0;
        while (true) {
            if (var5 >= length) {
                return -1;
            }
            if (buffer[var4 + var5] == arg1) {
                break;
            }
            ++var5;
            continue;
        }
        return var5;
    }

  public final int indexOf(long arg0, byte arg1, int arg2) {
        int var5 = index(arg0);
        int var6 = 0;
        while (true) {
            if (var6 >= Math.min(length, arg2)) {
                return -1;
            }
            if (buffer[var5 + var6] == arg1) {
                break;
            }
            ++var6;
            continue;
        }
        return var6;
    }

  public final void setMemory(long arg0, long arg1, byte arg2) {
        Arrays.fill(buffer, index(arg0), ((int) arg1), arg2);
    }

  public final void clear() {
        Arrays.fill(buffer, offset, length, 0);
    }

  public Object array() {
        return array();
    }

}