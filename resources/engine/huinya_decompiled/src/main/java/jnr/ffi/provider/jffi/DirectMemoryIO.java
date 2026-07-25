// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DirectMemoryIO
package jnr.ffi.provider.jffi;

import com.kenai.jffi.MemoryIO;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.AbstractMemoryIO;
import jnr.ffi.provider.DelegatingMemoryIO;
import jnr.ffi.provider.jffi.MemoryUtil;

class DirectMemoryIO extends AbstractMemoryIO {

    // ---- поля ----
  static final MemoryIO IO;

    static {
        IO = MemoryIO.getInstance();
    }

   DirectMemoryIO(Runtime arg0, long arg1) { // было: <init>
        super(arg0, arg1, true);
    }

   DirectMemoryIO(Runtime arg0, int arg1) { // было: <init>
        super(arg0, ((long) arg1) & 4294967295L, true);
    }

  public long size() {
        return 9223372036854775807L;
    }

  public boolean hasArray() {
        return false;
    }

  public Object array() {
        throw new UnsupportedOperationException("no array");
    }

  public int arrayOffset() {
        throw new UnsupportedOperationException("no array");
    }

  public int arrayLength() {
        throw new UnsupportedOperationException("no array");
    }

  public int hashCode() {
        return ((int) (address() << 32 ^ address()));
    }

  public boolean equals(Object arg0) {
        return !(arg0 instanceof Pointer) ? 0 : (((Pointer) arg0)).address() != address() ? 0 : (((Pointer) arg0)).getRuntime().isCompatible(getRuntime());
    }

  public final byte getByte(long arg0) {
        return IO.getByte(address() + arg0);
    }

  public final short getShort(long arg0) {
        return IO.getShort(address() + arg0);
    }

  public final int getInt(long arg0) {
        return IO.getInt(address() + arg0);
    }

  public final long getLongLong(long arg0) {
        return IO.getLong(address() + arg0);
    }

  public final float getFloat(long arg0) {
        return IO.getFloat(address() + arg0);
    }

  public final double getDouble(long arg0) {
        return IO.getDouble(address() + arg0);
    }

  public final void putByte(long arg0, byte arg1) {
        IO.putByte(address() + arg0, arg1);
    }

  public final void putShort(long arg0, short arg1) {
        IO.putShort(address() + arg0, arg1);
    }

  public final void putInt(long arg0, int arg1) {
        IO.putInt(address() + arg0, arg1);
    }

  public final void putLongLong(long arg0, long arg1) {
        IO.putLong(address() + arg0, arg1);
    }

  public final void putFloat(long arg0, float arg1) {
        IO.putFloat(address() + arg0, arg1);
    }

  public final void putDouble(long arg0, double arg1) {
        IO.putDouble(address() + arg0, arg1);
    }

  public final void get(long arg0, byte[] arg1, int arg2, int arg3) {
        IO.getByteArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, byte[] arg1, int arg2, int arg3) {
        IO.putByteArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void get(long arg0, short[] arg1, int arg2, int arg3) {
        IO.getShortArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, short[] arg1, int arg2, int arg3) {
        IO.putShortArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void get(long arg0, int[] arg1, int arg2, int arg3) {
        IO.getIntArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, int[] arg1, int arg2, int arg3) {
        IO.putIntArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void get(long arg0, long[] arg1, int arg2, int arg3) {
        IO.getLongArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, long[] arg1, int arg2, int arg3) {
        IO.putLongArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void get(long arg0, float[] arg1, int arg2, int arg3) {
        IO.getFloatArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, float[] arg1, int arg2, int arg3) {
        IO.putFloatArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void get(long arg0, double[] arg1, int arg2, int arg3) {
        IO.getDoubleArray(address() + arg0, arg1, arg2, arg3);
    }

  public final void put(long arg0, double[] arg1, int arg2, int arg3) {
        IO.putDoubleArray(address() + arg0, arg1, arg2, arg3);
    }

  public Pointer getPointer(long arg0) {
        return MemoryUtil.newPointer(getRuntime(), IO.getAddress(address() + arg0));
    }

  public Pointer getPointer(long arg0, long arg1) {
        return MemoryUtil.newPointer(getRuntime(), IO.getAddress(address() + arg0), arg1);
    }

  public void putPointer(long arg0, Pointer arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #18 // jnr.ffi.provider.jffi.DirectMemoryIO.IO:Lcom/kenai/jffi/MemoryIO;
        //      3: aload_0
        //      4: invokevirtual  #70 // jnr.ffi.provider.jffi.DirectMemoryIO.address:()J
        //      7: lload_1
        //      8: ladd
        //      9: aload_3
        //     10: ifnull  20 (offset +10)
        //     13: aload_3
        //     14: invokevirtual  #63 // jnr.ffi.Pointer.address:()J
        //     17: goto  21 (offset +4)
        //     20: lconst_0
        //     21: invokevirtual  #37 // com.kenai.jffi.MemoryIO.putAddress:(JJ)V
        //     24: return
    }

  public String getString(long arg0) {
        return Charset.defaultCharset().decode(ByteBuffer.wrap(IO.getZeroTerminatedByteArray(address() + arg0))).toString();
    }

  public String getString(long arg0, int arg1, Charset arg2) {
        byte[] var5 = IO.getZeroTerminatedByteArray(address() + arg0, arg1);
        return arg2.decode(ByteBuffer.wrap(var5)).toString();
    }

  public void putString(long arg0, String arg1, int arg2, Charset arg3) {
        ByteBuffer var6 = arg3.encode(arg1);
        int var7 = Math.min(arg2, var6.remaining());
        IO.putZeroTerminatedByteArray(address() + arg0, var6.array(), var6.arrayOffset() + var6.position(), var7);
    }

  public void putZeroTerminatedByteArray(long arg0, byte[] arg1, int arg2, int arg3) {
        IO.putZeroTerminatedByteArray(address() + arg0, arg1, arg2, arg3);
    }

  public int indexOf(long arg0, byte arg1, int arg2) {
        return ((int) IO.indexOf(address() + arg0, arg1, arg2));
    }

  public final void setMemory(long arg0, long arg1, byte arg2) {
        IO.setMemory(address() + arg0, arg1, arg2);
    }

  public void transferTo(long arg0, Pointer arg1, long arg2, long arg3) {
        Pointer __stk1;
        __stk1 = !(arg1 instanceof DelegatingMemoryIO) ? arg1 : (((DelegatingMemoryIO) arg1)).getDelegatedMemoryIO();
        Pointer var8 = __stk1;
        if (!(var8 instanceof DirectMemoryIO)) {
            super.transferTo(arg0, arg1, arg2, arg3);
        } else {
            arg1.checkBounds(arg2, arg3);
            memcpy(this, arg0, ((DirectMemoryIO) var8), arg2, arg3);
        }
    }

  public void transferFrom(long arg0, Pointer arg1, long arg2, long arg3) {
        Pointer __stk1;
        __stk1 = !(arg1 instanceof DelegatingMemoryIO) ? arg1 : (((DelegatingMemoryIO) arg1)).getDelegatedMemoryIO();
        Pointer var8 = __stk1;
        if (!(var8 instanceof DirectMemoryIO)) {
            super.transferFrom(arg0, arg1, arg2, arg3);
        } else {
            arg1.checkBounds(arg2, arg3);
            memcpy(((DirectMemoryIO) var8), arg2, this, arg0, arg3);
        }
    }

  private static void memcpy(DirectMemoryIO arg0, long arg1, DirectMemoryIO arg2, long arg3, long arg4) {
        IO.memcpy(arg2.address() + arg3, arg0.address() + arg1, arg4);
    }

}