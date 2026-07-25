// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.AbstractBufferMemoryIO
package jnr.ffi.provider;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractMemoryIO;
import jnr.ffi.util.BufferUtil;

public abstract class AbstractBufferMemoryIO extends AbstractMemoryIO {

    // ---- поля ----
  protected final ByteBuffer buffer;

  public AbstractBufferMemoryIO(Runtime arg0, ByteBuffer arg1, long arg2) { // было: <init>
        super(arg0, arg2, arg1.isDirect());
        buffer = arg1;
    }

  public long size() {
        return ((long) buffer.remaining());
    }

  public final ByteBuffer getByteBuffer() {
        return buffer;
    }

  public int arrayLength() {
        return getByteBuffer().remaining();
    }

  public int arrayOffset() {
        return getByteBuffer().arrayOffset();
    }

  public Object array() {
        return getByteBuffer().array();
    }

  public boolean hasArray() {
        return getByteBuffer().hasArray();
    }

  public byte getByte(long arg0) {
        return buffer.get(((int) arg0));
    }

  public short getShort(long arg0) {
        return buffer.getShort(((int) arg0));
    }

  public int getInt(long arg0) {
        return buffer.getInt(((int) arg0));
    }

  public long getLongLong(long arg0) {
        return buffer.getLong(((int) arg0));
    }

  public float getFloat(long arg0) {
        return buffer.getFloat(((int) arg0));
    }

  public double getDouble(long arg0) {
        return buffer.getDouble(((int) arg0));
    }

  public void putByte(long arg0, byte arg1) {
        buffer.put(((int) arg0), arg1);
    }

  public void putShort(long arg0, short arg1) {
        buffer.putShort(((int) arg0), arg1);
    }

  public void putInt(long arg0, int arg1) {
        buffer.putInt(((int) arg0), arg1);
    }

  public void putLongLong(long arg0, long arg1) {
        buffer.putLong(((int) arg0), arg1);
    }

  public void putFloat(long arg0, float arg1) {
        buffer.putFloat(((int) arg0), arg1);
    }

  public void putDouble(long arg0, double arg1) {
        buffer.putDouble(((int) arg0), arg1);
    }

  public String getString(long arg0, int arg1) {
        return BufferUtil.getString(BufferUtil.slice(buffer, ((int) arg0)), Charset.defaultCharset());
    }

  public void putString(long arg0, String arg1) {
        BufferUtil.putString(BufferUtil.slice(buffer, ((int) arg0)), Charset.defaultCharset(), arg1);
    }

  public void get(long arg0, byte[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3).get(arg1, arg2, arg3);
    }

  public void get(long arg0, short[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 16 / 8).asShortBuffer().get(arg1, arg2, arg3);
    }

  public void get(long arg0, int[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 32 / 8).asIntBuffer().get(arg1, arg2, arg3);
    }

  public void get(long arg0, long[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 64 / 8).asLongBuffer().get(arg1, arg2, arg3);
    }

  public void get(long arg0, float[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 32 / 8).asFloatBuffer().get(arg1, arg2, arg3);
    }

  public void get(long arg0, double[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 64 / 8).asDoubleBuffer().get(arg1, arg2, arg3);
    }

  public void put(long arg0, byte[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3).put(arg1, arg2, arg3);
    }

  public void put(long arg0, short[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 16 / 8).asShortBuffer().put(arg1, arg2, arg3);
    }

  public void put(long arg0, int[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 32 / 8).asIntBuffer().put(arg1, arg2, arg3);
    }

  public void put(long arg0, long[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 64 / 8).asLongBuffer().put(arg1, arg2, arg3);
    }

  public void put(long arg0, float[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 32 / 8).asFloatBuffer().put(arg1, arg2, arg3);
    }

  public void put(long arg0, double[] arg1, int arg2, int arg3) {
        BufferUtil.slice(buffer, ((int) arg0), arg3 * 64 / 8).asDoubleBuffer().put(arg1, arg2, arg3);
    }

  public String getString(long arg0) {
        return BufferUtil.getString(BufferUtil.slice(buffer, ((int) arg0)), Charset.defaultCharset());
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        return BufferUtil.getString(BufferUtil.slice(buffer, ((int) arg0), arg1), arg2);
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        BufferUtil.putString(BufferUtil.slice(buffer, ((int) arg0), arg2), arg3, arg1);
    }

  public int indexOf(long arg0, byte arg1, int arg2) {
        while (true) {
            if (arg0 <= -1L) {
                return -1;
            }
            if (buffer.get(((int) arg0)) == arg1) {
                break;
            }
            arg0 = arg0 + 1L;
            continue;
        }
        return ((int) arg0);
    }

  public void setMemory(long arg0, long arg1, byte arg2) {
        int var6 = 0;
        while (((long) var6) < arg1) {
            buffer.put(((int) arg0) + var6, arg2);
            ++var6;
            continue;
        }
    }

}