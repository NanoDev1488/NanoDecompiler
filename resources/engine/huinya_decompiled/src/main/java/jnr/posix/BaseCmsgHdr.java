// исходный (обфусцированный) внутренний класс: jnr.posix.BaseCmsgHdr
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.ffi.Pointer;
import jnr.posix.CmsgHdr;
import jnr.posix.NativePOSIX;
import jnr.posix.SocketMacros;

abstract class BaseCmsgHdr implements CmsgHdr {

    // ---- поля ----
  protected final NativePOSIX posix;
  final Pointer memory;

  protected BaseCmsgHdr(NativePOSIX arg0, Pointer arg1) { // было: <init>
        super();
        posix = arg0;
        memory = arg1;
    }

  protected BaseCmsgHdr(NativePOSIX arg0, Pointer arg1, int arg2) { // было: <init>
        super();
        posix = arg0;
        memory = arg1;
        setLen(arg2);
    }

  public void setData(ByteBuffer arg0) {
        byte[] var2 = new byte[arg0.capacity() - arg0.position()];
        arg0.get(var2);
        posix.socketMacros().CMSG_DATA(memory).put(0L, var2, 0, var2.length);
    }

  public ByteBuffer getData() {
        int var1 = getLen() - posix.socketMacros().CMSG_LEN(0);
        if (var1 != 0) {
            byte[] var2 = new byte[var1];
            posix.socketMacros().CMSG_DATA(memory).get(0L, var2, 0, var2.length);
            ByteBuffer var3 = ByteBuffer.allocate(var2.length);
            var3.put(var2);
            var3.flip();
            return var3;
        } else {
            return null;
        }
    }

  abstract void setLen(int arg0);

}