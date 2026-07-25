// исходный (обфусцированный) внутренний класс: jnr.posix.BaseIovec
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_size_t;
import jnr.posix.BaseIovec_Layout;
import jnr.posix.Iovec;
import jnr.posix.NativePOSIX;

public class BaseIovec implements Iovec {

    // ---- поля ----
  public static final BaseIovec_Layout layout;
  private final NativePOSIX posix;
  protected final Pointer memory;

    static {
        layout = new BaseIovec_Layout(Runtime.getSystemRuntime());
    }

  public String toString(String arg0) {
        StringBuffer var2 = new StringBuffer();
        var2.append(arg0).append("iovec {\n");
        var2.append(arg0).append("  iov_base=").append(layout.iov_base.get(memory)).append(",\n");
        var2.append(arg0).append("  iov_len=").append(layout.iov_len.get(memory)).append(",\n");
        var2.append(arg0).append("}");
        return var2.toString();
    }

  protected BaseIovec(NativePOSIX arg0) { // было: <init>
        super();
        posix = arg0;
        memory = Memory.allocate(arg0.getRuntime(), layout.size());
    }

   BaseIovec(NativePOSIX arg0, Pointer arg1) { // было: <init>
        super();
        posix = arg0;
        memory = arg1;
    }

  public ByteBuffer get() {
        int var1 = getLen();
        byte[] var2 = new byte[var1];
        layout.iov_base.get(memory).get(0L, var2, 0, var1);
        return ByteBuffer.wrap(var2);
    }

  public void set(ByteBuffer arg0) {
        int var2 = arg0.remaining();
        layout.iov_base.set(memory, Pointer.wrap(posix.getRuntime(), arg0));
        setLen(var2);
    }

  protected void setLen(int arg0) {
        layout.iov_len.set(memory, ((long) arg0));
    }

  protected int getLen() {
        return ((int) layout.iov_len.get(memory));
    }

}