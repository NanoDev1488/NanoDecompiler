// исходный (обфусцированный) внутренний класс: jnr.unixsocket.impl.AbstractNativeSocketChannel
package jnr.unixsocket.impl;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import jnr.constants.platform.Errno;
import jnr.constants.platform.Shutdown;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeException;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;
import jnr.unixsocket.impl.Common;

public abstract class AbstractNativeSocketChannel extends SocketChannel implements ByteChannel, NativeSelectableChannel {

    // ---- поля ----
  private final Common common;
  private static final int SHUT_RD;
  private static final int SHUT_WR;

    static {
        SHUT_RD = Shutdown.SHUT_RD.intValue();
        SHUT_WR = Shutdown.SHUT_WR.intValue();
    }

  public AbstractNativeSocketChannel(int arg0) { // было: <init>
        this(NativeSelectorProvider.getInstance(), arg0);
    }

   AbstractNativeSocketChannel(SelectorProvider arg0, int arg1) { // было: <init>
        super(arg0);
        common = new Common(arg1);
    }

  public void setFD(int arg0) {
        common.setFD(arg0);
    }

  public final int getFD() {
        return common.getFD();
    }

  protected void implCloseSelectableChannel() {
        if (isConnected()) {
            shutdownInput();
            shutdownOutput();
        }
        Native.close(common.getFD());
    }

  protected void implConfigureBlocking(boolean arg0) {
        Native.setBlocking(common.getFD(), arg0);
    }

  public int read(ByteBuffer arg0) {
        return common.read(arg0);
    }

  public long read(ByteBuffer[] arg0, int arg1, int arg2) {
        return common.read(arg0, arg1, arg2);
    }

  public int write(ByteBuffer arg0) {
        return common.write(arg0);
    }

  public long write(ByteBuffer[] arg0, int arg1, int arg2) {
        return common.write(arg0, arg1, arg2);
    }

  public SocketChannel shutdownInput() {
        int var1 = Native.shutdown(common.getFD(), SHUT_RD);
        if (var1 >= 0) {
            return this;
        } else {
            if (Native.getLastError() == Errno.ENOTCONN) {
                return this;
            } else {
                throw new NativeException(Native.getLastErrorString(), Native.getLastError());
            }
        }
    }

  public SocketChannel shutdownOutput() {
        int var1 = Native.shutdown(common.getFD(), SHUT_WR);
        if (var1 >= 0) {
            return this;
        } else {
            if (Native.getLastError() == Errno.ENOTCONN) {
                return this;
            } else {
                throw new NativeException(Native.getLastErrorString(), Native.getLastError());
            }
        }
    }

}