// исходный (обфусцированный) внутренний класс: com.kenai.jffi.MemoryIO.CheckedNativeImpl
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.MemoryIO;
import com.kenai.jffi.MemoryIO_Anon1;

final class MemoryIO_CheckedNativeImpl extends MemoryIO {

  private MemoryIO_CheckedNativeImpl() { // было: <init>
        super();
    }

  public final byte getByte(long arg0) {
        return Foreign.getByteChecked(arg0);
    }

  public final short getShort(long arg0) {
        return Foreign.getShortChecked(arg0);
    }

  public final int getInt(long arg0) {
        return Foreign.getIntChecked(arg0);
    }

  public final long getLong(long arg0) {
        return Foreign.getLongChecked(arg0);
    }

  public final float getFloat(long arg0) {
        return Foreign.getFloatChecked(arg0);
    }

  public final double getDouble(long arg0) {
        return Foreign.getDoubleChecked(arg0);
    }

  public final void putByte(long arg0, byte arg1) {
        Foreign.putByteChecked(arg0, arg1);
    }

  public final void putShort(long arg0, short arg1) {
        Foreign.putShortChecked(arg0, arg1);
    }

  public final void putInt(long arg0, int arg1) {
        Foreign.putIntChecked(arg0, arg1);
    }

  public final void putLong(long arg0, long arg1) {
        Foreign.putLongChecked(arg0, arg1);
    }

  public final void putFloat(long arg0, float arg1) {
        Foreign.putFloatChecked(arg0, arg1);
    }

  public final void putDouble(long arg0, double arg1) {
        Foreign.putDoubleChecked(arg0, arg1);
    }

  public final void setMemory(long arg0, long arg1, byte arg2) {
        Foreign.setMemoryChecked(arg0, arg1, arg2);
    }

  public final void _copyMemory(long arg0, long arg1, long arg2) {
        Foreign.copyMemoryChecked(arg0, arg1, arg2);
    }

  public final long getAddress(long arg0) {
        return Foreign.getAddressChecked(arg0) & ADDRESS_MASK;
    }

  public final void putAddress(long arg0, long arg1) {
        Foreign.putAddressChecked(arg0, arg1);
    }

  public final void memcpy(long arg0, long arg1, long arg2) {
        Foreign.memcpyChecked(arg0, arg1, arg2);
    }

  public final void memmove(long arg0, long arg1, long arg2) {
        Foreign.memmoveChecked(arg0, arg1, arg2);
    }

  public final long memchr(long arg0, int arg1, long arg2) {
        return Foreign.memchrChecked(arg0, arg1, arg2);
    }

  public final void putByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.putByteArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.getByteArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putCharArray(long arg0, char[] arg1, int arg2, int arg3) {
        Foreign.putCharArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getCharArray(long arg0, char[] arg1, int arg2, int arg3) {
        Foreign.getCharArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putShortArray(long arg0, short[] arg1, int arg2, int arg3) {
        Foreign.putShortArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getShortArray(long arg0, short[] arg1, int arg2, int arg3) {
        Foreign.getShortArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putIntArray(long arg0, int[] arg1, int arg2, int arg3) {
        Foreign.putIntArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getIntArray(long arg0, int[] arg1, int arg2, int arg3) {
        Foreign.getIntArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putLongArray(long arg0, long[] arg1, int arg2, int arg3) {
        Foreign.putLongArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getLongArray(long arg0, long[] arg1, int arg2, int arg3) {
        Foreign.getLongArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putFloatArray(long arg0, float[] arg1, int arg2, int arg3) {
        Foreign.putFloatArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getFloatArray(long arg0, float[] arg1, int arg2, int arg3) {
        Foreign.getFloatArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void putDoubleArray(long arg0, double[] arg1, int arg2, int arg3) {
        Foreign.putDoubleArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final void getDoubleArray(long arg0, double[] arg1, int arg2, int arg3) {
        Foreign.getDoubleArrayChecked(arg0, arg1, arg2, arg3);
    }

  public final long getStringLength(long arg0) {
        return Foreign.strlenChecked(arg0);
    }

  public final byte[] getZeroTerminatedByteArray(long arg0) {
        return Foreign.getZeroTerminatedByteArrayChecked(arg0);
    }

  public final byte[] getZeroTerminatedByteArray(long arg0, int arg1) {
        return Foreign.getZeroTerminatedByteArrayChecked(arg0, arg1);
    }

  public final void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        Foreign.putZeroTerminatedByteArrayChecked(arg0, arg1, arg2, arg3);
    }

   MemoryIO_CheckedNativeImpl(MemoryIO_Anon1 arg0) { // было: <init>
        this();
    }

}