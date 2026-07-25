// исходный (обфусцированный) внутренний класс: com.kenai.jffi.UnsafeMemoryIO
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public abstract class UnsafeMemoryIO extends MemoryIO {

    // ---- поля ----
  protected static Unsafe unsafe;

    static {
        unsafe = ((Unsafe) Unsafe.class.cast(getUnsafe()));
    }

  public UnsafeMemoryIO() { // было: <init>
        super();
    }

  private static Object getUnsafe() {
        Object __stk1;
        try {
            Class var0 = Class.forName("sun.misc.Unsafe");
            Field var1 = var0.getDeclaredField("theUnsafe");
            var1.setAccessible(true);
            __stk1 = var1.get(var0);
        } catch (Exception e1) {
            Throwable var0 = e1;
            throw new RuntimeException(var0);
        }
    }

  public final byte getByte(long arg0) {
        return unsafe.getByte(arg0);
    }

  public final short getShort(long arg0) {
        return unsafe.getShort(arg0);
    }

  public final int getInt(long arg0) {
        return unsafe.getInt(arg0);
    }

  public final long getLong(long arg0) {
        return unsafe.getLong(arg0);
    }

  public final float getFloat(long arg0) {
        return unsafe.getFloat(arg0);
    }

  public final double getDouble(long arg0) {
        return unsafe.getDouble(arg0);
    }

  public final void putByte(long arg0, byte arg1) {
        unsafe.putByte(arg0, arg1);
    }

  public final void putShort(long arg0, short arg1) {
        unsafe.putShort(arg0, arg1);
    }

  public final void putInt(long arg0, int arg1) {
        unsafe.putInt(arg0, arg1);
    }

  public final void putLong(long arg0, long arg1) {
        unsafe.putLong(arg0, arg1);
    }

  public final void putFloat(long arg0, float arg1) {
        unsafe.putFloat(arg0, arg1);
    }

  public final void putDouble(long arg0, double arg1) {
        unsafe.putDouble(arg0, arg1);
    }

  public final void _copyMemory(long arg0, long arg1, long arg2) {
        unsafe.copyMemory(arg0, arg1, arg2);
    }

  public final void setMemory(long arg0, long arg1, byte arg2) {
        unsafe.setMemory(arg0, arg1, arg2);
    }

  public final void memcpy(long arg0, long arg1, long arg2) {
        Foreign.memcpy(arg0, arg1, arg2);
    }

  public final void memmove(long arg0, long arg1, long arg2) {
        Foreign.memmove(arg0, arg1, arg2);
    }

  public final long memchr(long arg0, int arg1, long arg2) {
        return Foreign.memchr(arg0, arg1, arg2);
    }

  public final void putByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.putByteArray(arg0, arg1, arg2, arg3);
    }

  public final void getByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.getByteArray(arg0, arg1, arg2, arg3);
    }

  public final void putCharArray(long arg0, char[] arg1, int arg2, int arg3) {
        Foreign.putCharArray(arg0, arg1, arg2, arg3);
    }

  public final void getCharArray(long arg0, char[] arg1, int arg2, int arg3) {
        Foreign.getCharArray(arg0, arg1, arg2, arg3);
    }

  public final void putShortArray(long arg0, short[] arg1, int arg2, int arg3) {
        Foreign.putShortArray(arg0, arg1, arg2, arg3);
    }

  public final void getShortArray(long arg0, short[] arg1, int arg2, int arg3) {
        Foreign.getShortArray(arg0, arg1, arg2, arg3);
    }

  public final void putIntArray(long arg0, int[] arg1, int arg2, int arg3) {
        Foreign.putIntArray(arg0, arg1, arg2, arg3);
    }

  public final void getIntArray(long arg0, int[] arg1, int arg2, int arg3) {
        Foreign.getIntArray(arg0, arg1, arg2, arg3);
    }

  public final void putLongArray(long arg0, long[] arg1, int arg2, int arg3) {
        Foreign.putLongArray(arg0, arg1, arg2, arg3);
    }

  public final void getLongArray(long arg0, long[] arg1, int arg2, int arg3) {
        Foreign.getLongArray(arg0, arg1, arg2, arg3);
    }

  public final void putFloatArray(long arg0, float[] arg1, int arg2, int arg3) {
        Foreign.putFloatArray(arg0, arg1, arg2, arg3);
    }

  public final void getFloatArray(long arg0, float[] arg1, int arg2, int arg3) {
        Foreign.getFloatArray(arg0, arg1, arg2, arg3);
    }

  public final void putDoubleArray(long arg0, double[] arg1, int arg2, int arg3) {
        Foreign.putDoubleArray(arg0, arg1, arg2, arg3);
    }

  public final void getDoubleArray(long arg0, double[] arg1, int arg2, int arg3) {
        Foreign.getDoubleArray(arg0, arg1, arg2, arg3);
    }

  public final long getStringLength(long arg0) {
        return Foreign.strlen(arg0);
    }

  public final byte[] getZeroTerminatedByteArray(long arg0) {
        return Foreign.getZeroTerminatedByteArray(arg0);
    }

  public final byte[] getZeroTerminatedByteArray(long arg0, int arg1) {
        return Foreign.getZeroTerminatedByteArray(arg0, arg1);
    }

  public final void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.putZeroTerminatedByteArray(arg0, arg1, arg2, arg3);
    }

}