// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeSocketChannel
package jnr.enxio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;
import jnr.constants.platform.Shutdown;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;

public class NativeSocketChannel extends AbstractSelectableChannel implements ByteChannel, NativeSelectableChannel {

    // ---- поля ----
  private final int fd;
  private final int validOps;
  private static final int SHUT_RD;
  private static final int SHUT_WR;

    static {
        SHUT_RD = Shutdown.SHUT_RD.intValue();
        SHUT_WR = Shutdown.SHUT_WR.intValue();
    }

  public NativeSocketChannel(int arg0) { // было: <init>
        this(NativeSelectorProvider.getInstance(), arg0, 5);
    }

  public NativeSocketChannel(int arg0, int arg1) { // было: <init>
        this(NativeSelectorProvider.getInstance(), arg0, arg1);
    }

   NativeSocketChannel(SelectorProvider arg0, int arg1, int arg2) { // было: <init>
        super(arg0);
        fd = arg1;
        validOps = arg2;
    }

  protected void implCloseSelectableChannel() {
        Native.close(fd);
    }

  protected void implConfigureBlocking(boolean arg0) {
        Native.setBlocking(fd, arg0);
    }

  public final int validOps() {
        return validOps;
    }

  public final int getFD() {
        return fd;
    }

  public int read(ByteBuffer arg0) {
        int var2 = Native.read(fd, arg0);
        switch (var2) {
            case 0:
                return -1;
            case -1:
                switch (Native.getLastError()) {
                    case EAGAIN:
                    case EWOULDBLOCK:
                        return 0;
                    default:
                        throw new IOException(Native.getLastErrorString());
                }
            default:
                return var2;
        }
    }

  public int write(ByteBuffer arg0) {
        int var2 = Native.write(fd, arg0);
        if (var2 >= 0) {
            return var2;
        }
        switch (Native.getLastError()) {
            case EAGAIN:
            case EWOULDBLOCK:
                return 0;
            default:
                throw new IOException(Native.getLastErrorString());
        }
    }

  public void shutdownInput() {
        int var1 = Native.shutdown(fd, SHUT_RD);
        if (var1 >= 0) {
            return;
        } else {
            throw new IOException(Native.getLastErrorString());
        }
    }

  public void shutdownOutput() {
        int var1 = Native.shutdown(fd, SHUT_WR);
        if (var1 >= 0) {
            return;
        } else {
            throw new IOException(Native.getLastErrorString());
        }
    }

}