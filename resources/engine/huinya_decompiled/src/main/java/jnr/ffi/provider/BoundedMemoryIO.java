// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.BoundedMemoryIO
package jnr.ffi.provider;

import java.nio.charset.Charset;
import jnr.ffi.Address;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractMemoryIO;
import jnr.ffi.provider.DelegatingMemoryIO;

public final class BoundedMemoryIO extends AbstractMemoryIO implements DelegatingMemoryIO {

    // ---- поля ----
  private final long base;
  private final long size;
  private final Pointer io;

  public BoundedMemoryIO(Pointer arg0, long arg1, long arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokevirtual  #40 // jnr.ffi.Pointer.getRuntime:()Ljnr/ffi/Runtime;
        //      5: aload_1
        //      6: invokevirtual  #22 // jnr.ffi.Pointer.address:()J
        //      9: lconst_0
        //     10: lcmp
        //     11: ifeq  23 (offset +12)
        //     14: aload_1
        //     15: invokevirtual  #22 // jnr.ffi.Pointer.address:()J
        //     18: lload_2
        //     19: ladd
        //     20: goto  24 (offset +4)
        //     23: lconst_0
        //     24: aload_1
        //     25: invokevirtual  #45 // jnr.ffi.Pointer.isDirect:()Z
        //     28: invokespecial  #66 // jnr.ffi.provider.AbstractMemoryIO.<init>:(Ljnr/ffi/Runtime;JZ)V
        //     31: aload_0
        //     32: aload_1
        //     33: putfield  #16 // jnr.ffi.provider.BoundedMemoryIO.io:Ljnr/ffi/Pointer;
        //     36: aload_0
        //     37: lload_2
        //     38: putfield  #15 // jnr.ffi.provider.BoundedMemoryIO.base:J
        //     41: aload_0
        //     42: lload  4
        //     44: putfield  #17 // jnr.ffi.provider.BoundedMemoryIO.size:J
        //     47: return
    }

  public long size() {
        return size;
    }

  public final boolean hasArray() {
        return io.hasArray();
    }

  public final Object array() {
        return io.array();
    }

  public final int arrayOffset() {
        return io.arrayOffset() + ((int) base);
    }

  public final int arrayLength() {
        return ((int) size);
    }

  public void checkBounds(long arg0, long arg1) {
        checkBounds(size, arg0, arg1);
        getDelegatedMemoryIO().checkBounds(base + arg0, arg1);
    }

  public Pointer getDelegatedMemoryIO() {
        return io;
    }

  public int hashCode() {
        return getDelegatedMemoryIO().hashCode();
    }

  public boolean equals(Object arg0) {
        return !(arg0 instanceof BoundedMemoryIO) ? io.equals(arg0) : !io.equals((((BoundedMemoryIO) arg0)).io) ? io.equals(arg0) : (((BoundedMemoryIO) arg0)).base != base ? io.equals(arg0) : (((BoundedMemoryIO) arg0)).size == size ? 1 : io.equals(arg0);
    }

  public byte getByte(long arg0) {
        checkBounds(size, arg0, 1L);
        return io.getByte(base + arg0);
    }

  public short getShort(long arg0) {
        checkBounds(size, arg0, 2L);
        return io.getShort(base + arg0);
    }

  public int getInt(long arg0) {
        checkBounds(size, arg0, 4L);
        return io.getInt(base + arg0);
    }

  public long getLongLong(long arg0) {
        checkBounds(size, arg0, 8L);
        return io.getLongLong(base + arg0);
    }

  public float getFloat(long arg0) {
        checkBounds(size, arg0, 4L);
        return io.getFloat(base + arg0);
    }

  public double getDouble(long arg0) {
        checkBounds(size, arg0, 8L);
        return io.getDouble(base + arg0);
    }

  public Pointer getPointer(long arg0) {
        checkBounds(size, arg0, ((long) getRuntime().addressSize()));
        return io.getPointer(base + arg0);
    }

  public Pointer getPointer(long arg0, long arg1) {
        checkBounds(size, base + arg0, ((long) getRuntime().addressSize()));
        return io.getPointer(base + arg0, arg1);
    }

  public void putByte(long arg0, byte arg1) {
        checkBounds(size, arg0, 1L);
        io.putByte(base + arg0, arg1);
    }

  public void putShort(long arg0, short arg1) {
        checkBounds(size, arg0, 2L);
        io.putShort(base + arg0, arg1);
    }

  public void putInt(long arg0, int arg1) {
        checkBounds(size, arg0, 4L);
        io.putInt(base + arg0, arg1);
    }

  public void putLongLong(long arg0, long arg1) {
        checkBounds(size, arg0, 8L);
        io.putLongLong(base + arg0, arg1);
    }

  public void putFloat(long arg0, float arg1) {
        checkBounds(size, arg0, 4L);
        io.putFloat(base + arg0, arg1);
    }

  public void putDouble(long arg0, double arg1) {
        checkBounds(size, arg0, 8L);
        io.putDouble(base + arg0, arg1);
    }

  public void putPointer(long arg0, Pointer arg1) {
        checkBounds(size, arg0, ((long) getRuntime().addressSize()));
        io.putPointer(base + arg0, arg1);
    }

  public void get(long arg0, byte[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) arg3));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, byte[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) arg3));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, short[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 2)));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, short[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 2)));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, int[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 4)));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, int[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 4)));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, long[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 8)));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, long[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 8)));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, float[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 4)));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, float[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 4)));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public void get(long arg0, double[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 8)));
        io.get(base + arg0, arg1, arg2, arg3);
    }

  public void put(long arg0, double[] arg1, int arg2, int arg3) {
        checkBounds(size, arg0, ((long) Math.multiplyExact(arg3, 8)));
        io.put(base + arg0, arg1, arg2, arg3);
    }

  public long getAddress(long arg0) {
        checkBounds(size, arg0, ((long) getRuntime().addressSize()));
        return io.getAddress(base + arg0);
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        checkBounds(size, arg0, ((long) arg1));
        return io.getString(base + arg0, arg1, arg2);
    }

  public String getString(long arg0) {
        return io.getString(base + arg0, ((int) size), Charset.defaultCharset());
    }

  public void putAddress(long arg0, long arg1) {
        checkBounds(size, arg0, ((long) getRuntime().addressSize()));
        io.putAddress(base + arg0, arg1);
    }

  public void putAddress(long arg0, Address arg1) {
        checkBounds(size, arg0, ((long) getRuntime().addressSize()));
        io.putAddress(base + arg0, arg1);
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        checkBounds(size, arg0, ((long) arg2));
        io.putString(base + arg0, arg1, arg2, arg3);
    }

  public int indexOf(long arg0, byte arg1) {
        return io.indexOf(base + arg0, arg1, ((int) size));
    }

  public int indexOf(long arg0, byte arg1, int arg2) {
        checkBounds(size, arg0, ((long) arg2));
        return io.indexOf(base + arg0, arg1, arg2);
    }

  public void setMemory(long arg0, long arg1, byte arg2) {
        checkBounds(size, base + arg0, arg1);
        io.setMemory(base + arg0, arg1, arg2);
    }

  public void transferFrom(long arg0, Pointer arg1, long arg2, long arg3) {
        checkBounds(size, base + arg0, arg3);
        getDelegatedMemoryIO().transferFrom(arg0, arg1, arg2, arg3);
    }

  public void transferTo(long arg0, Pointer arg1, long arg2, long arg3) {
        checkBounds(size, base + arg0, arg3);
        getDelegatedMemoryIO().transferTo(arg0, arg1, arg2, arg3);
    }

}