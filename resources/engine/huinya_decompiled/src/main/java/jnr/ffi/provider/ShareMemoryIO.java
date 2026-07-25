// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.ShareMemoryIO
package jnr.ffi.provider;

import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.provider.AbstractMemoryIO;
import jnr.ffi.provider.DelegatingMemoryIO;

public class ShareMemoryIO extends AbstractMemoryIO implements DelegatingMemoryIO {

    // ---- поля ----
  private final Pointer ptr;
  private final long base;

  public ShareMemoryIO(Pointer arg0, long arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokevirtual  #25 // jnr.ffi.Pointer.getRuntime:()Ljnr/ffi/Runtime;
        //      5: aload_1
        //      6: invokevirtual  #7 // jnr.ffi.Pointer.address:()J
        //      9: lconst_0
        //     10: lcmp
        //     11: ifeq  23 (offset +12)
        //     14: aload_1
        //     15: invokevirtual  #7 // jnr.ffi.Pointer.address:()J
        //     18: lload_2
        //     19: ladd
        //     20: goto  24 (offset +4)
        //     23: lconst_0
        //     24: aload_1
        //     25: invokevirtual  #31 // jnr.ffi.Pointer.isDirect:()Z
        //     28: invokespecial  #49 // jnr.ffi.provider.AbstractMemoryIO.<init>:(Ljnr/ffi/Runtime;JZ)V
        //     31: aload_0
        //     32: aload_1
        //     33: putfield  #6 // jnr.ffi.provider.ShareMemoryIO.ptr:Ljnr/ffi/Pointer;
        //     36: aload_0
        //     37: lload_2
        //     38: putfield  #5 // jnr.ffi.provider.ShareMemoryIO.base:J
        //     41: return
    }

  public long size() {
        return ptr.size() - base;
    }

  public final boolean hasArray() {
        return ptr.hasArray();
    }

  public final Object array() {
        return ptr.array();
    }

  public final int arrayOffset() {
        return ptr.arrayOffset() + ((int) base);
    }

  public final int arrayLength() {
        return ptr.arrayLength() - ((int) base);
    }

  public final Pointer getDelegatedMemoryIO() {
        return ptr;
    }

  public byte getByte(long arg0) {
        return ptr.getByte(base + arg0);
    }

  public short getShort(long arg0) {
        return ptr.getShort(base + arg0);
    }

  public int getInt(long arg0) {
        return ptr.getInt(base + arg0);
    }

  public long getLong(long arg0) {
        return ptr.getLong(base + arg0);
    }

  public long getLongLong(long arg0) {
        return ptr.getLongLong(base + arg0);
    }

  public float getFloat(long arg0) {
        return ptr.getFloat(base + arg0);
    }

  public double getDouble(long arg0) {
        return ptr.getDouble(base + arg0);
    }

  public Pointer getPointer(long arg0) {
        return ptr.getPointer(base + arg0);
    }

  public Pointer getPointer(long arg0, long arg1) {
        return ptr.getPointer(base + arg0, arg1);
    }

  public String getString(long arg0) {
        return ptr.getString(base + arg0);
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        return ptr.getString(base + arg0, arg1, arg2);
    }

  public void putByte(long arg0, byte arg1) {
        ptr.putByte(base + arg0, arg1);
    }

  public void putShort(long arg0, short arg1) {
        ptr.putShort(base + arg0, arg1);
    }

  public void putInt(long arg0, int arg1) {
        ptr.putInt(base + arg0, arg1);
    }

  public void putLong(long arg0, long arg1) {
        ptr.putLong(base + arg0, arg1);
    }

  public void putLongLong(long arg0, long arg1) {
        ptr.putLongLong(base + arg0, arg1);
    }

  public void putFloat(long arg0, float arg1) {
        ptr.putFloat(base + arg0, arg1);
    }

  public void putDouble(long arg0, double arg1) {
        ptr.putDouble(base + arg0, arg1);
    }

  public void putPointer(long arg0, Pointer arg1) {
        ptr.putPointer(base + arg0, arg1);
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        ptr.putString(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, byte[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, byte[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, short[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, short[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, int[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, int[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, long[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, long[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, float[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, float[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, double[] arg1, int arg2, int arg3) {
        ptr.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, double[] arg1, int arg2, int arg3) {
        ptr.put(base + arg0, arg1, arg2, arg3);
    }

  public int indexOf(long arg0, byte arg1, int arg2) {
        return ptr.indexOf(base + arg0, arg1, arg2);
    }

  public void setMemory(long arg0, long arg1, byte arg2) {
        ptr.setMemory(base + arg0, arg1, arg2);
    }

}