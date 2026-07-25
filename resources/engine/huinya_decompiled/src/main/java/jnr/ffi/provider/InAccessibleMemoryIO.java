// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InAccessibleMemoryIO
package jnr.ffi.provider;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractMemoryIO;

public abstract class InAccessibleMemoryIO extends AbstractMemoryIO {

    // ---- поля ----
  private static final String msg = "attempted access to inaccessible memory";

  protected InAccessibleMemoryIO(Runtime arg0, long arg1, boolean arg2) { // было: <init>
        super(arg0, arg1, arg2);
    }

  protected RuntimeException error() {
        return new IndexOutOfBoundsException("attempted access to inaccessible memory");
    }

  public boolean hasArray() {
        return false;
    }

  public Object array() {
        return null;
    }

  public int arrayOffset() {
        return 0;
    }

  public int arrayLength() {
        return 0;
    }

  public final byte getByte(long arg0) {
        throw error();
    }

  public final short getShort(long arg0) {
        throw error();
    }

  public final int getInt(long arg0) {
        throw error();
    }

  public final long getLong(long arg0) {
        throw error();
    }

  public final long getLongLong(long arg0) {
        throw error();
    }

  public final float getFloat(long arg0) {
        throw error();
    }

  public final double getDouble(long arg0) {
        throw error();
    }

  public final void putByte(long arg0, byte arg1) {
        throw error();
    }

  public final void putShort(long arg0, short arg1) {
        throw error();
    }

  public final void putInt(long arg0, int arg1) {
        throw error();
    }

  public final void putLong(long arg0, long arg1) {
        throw error();
    }

  public final void putLongLong(long arg0, long arg1) {
        throw error();
    }

  public final void putFloat(long arg0, float arg1) {
        throw error();
    }

  public final void putDouble(long arg0, double arg1) {
        throw error();
    }

  public final void get(long arg0, byte[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, byte[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void get(long arg0, short[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, short[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void get(long arg0, int[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, int[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void get(long arg0, long[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, long[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void get(long arg0, float[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, float[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void get(long arg0, double[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final void put(long arg0, double[] arg1, int arg2, int arg3) {
        throw error();
    }

  public final Pointer getPointer(long arg0, long arg1) {
        throw error();
    }

  public final Pointer getPointer(long arg0) {
        throw error();
    }

  public final void putPointer(long arg0, Pointer arg1) {
        throw error();
    }

  public String getString(long arg0) {
        throw error();
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        throw error();
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        throw error();
    }

  public final int indexOf(long arg0, byte arg1, int arg2) {
        throw error();
    }

  public final void setMemory(long arg0, long arg1, byte arg2) {
        throw error();
    }

}