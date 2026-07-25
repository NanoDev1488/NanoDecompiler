// исходный (обфусцированный) внутренний класс: jnr.a64asm.CodeBuffer
package jnr.a64asm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class CodeBuffer {

    // ---- поля ----
  private ByteBuffer buf;

  public CodeBuffer() { // было: <init>
        super();
        buf = ByteBuffer.allocate(128).order(ByteOrder.LITTLE_ENDIAN);
    }

  public final void ensureSpace() {
        if (buf.remaining() < 16) {
            grow();
        }
    }

  public void grow() {
        int var1 = buf.capacity() * 2;
        ByteBuffer var2 = ByteBuffer.allocate(var1).order(ByteOrder.BIG_ENDIAN);
        buf.flip();
        var2.put(buf);
        buf = var2;
    }

  final void copyTo(ByteBuffer arg0) {
        ByteBuffer var2 = buf.duplicate();
        var2.flip();
        arg0.put(var2);
    }

  public final int offset() {
        return buf.position();
    }

  public int capacity() {
        return buf.capacity();
    }

  public final void emitByte(byte arg0) {
        buf.put(arg0);
    }

  public final void emitWord(short arg0) {
        buf.putShort(arg0);
    }

  public final void emitDWord(int arg0) {
        buf.putInt(arg0);
    }

  public final void emitQWord(long arg0) {
        buf.putLong(arg0);
    }

  public final void emitData(ByteBuffer arg0, int arg1) {
        ByteBuffer var3 = arg0.duplicate();
        if (var3.remaining() > arg1) {
            var3.limit(var3.position() + arg1);
        }
        buf.put(var3);
    }

  public final byte getByteAt(int arg0) {
        return buf.get(arg0);
    }

  public final short getWordAt(int arg0) {
        return buf.getShort(arg0);
    }

  public final int getDWordAt(int arg0) {
        return buf.getInt(arg0);
    }

  public final long getQWordAt(int arg0) {
        return buf.getLong(arg0);
    }

  public final void setByteAt(int arg0, byte arg1) {
        buf.put(arg0, arg1);
    }

  public final void setWordAt(int arg0, short arg1) {
        buf.putShort(arg0, arg1);
    }

  public final void setDWordAt(int arg0, int arg1) {
        buf.putInt(arg0, arg1);
    }

  public final void setQWordAt(int arg0, long arg1) {
        buf.putLong(arg0, arg1);
    }

}