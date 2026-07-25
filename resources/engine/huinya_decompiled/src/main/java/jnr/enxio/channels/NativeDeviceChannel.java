// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeDeviceChannel
package jnr.enxio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeFileSelectorProvider;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;

public class NativeDeviceChannel extends AbstractSelectableChannel implements ByteChannel, NativeSelectableChannel {

    // ---- поля ----
  private final int fd;
  private final int validOps;
  private final boolean isFile;

  public NativeDeviceChannel(int arg0) { // было: <init>
        this(arg0, false);
    }

  public NativeDeviceChannel(int arg0, boolean arg1) { // было: <init>
        this(selectorProvider(arg1), arg0, 5, arg1);
    }

  public NativeDeviceChannel(SelectorProvider arg0, int arg1, int arg2, boolean arg3) { // было: <init>
        super(arg0);
        fd = arg1;
        validOps = arg2;
        isFile = arg3;
    }

  private static SelectorProvider selectorProvider(boolean arg0) {
        return !arg0 ? NativeSelectorProvider.getInstance() : NativeFileSelectorProvider.getInstance();
    }

  protected void implCloseSelectableChannel() {
        int var1 = Native.close(fd);
        if (var1 >= 0) {
            return;
        } else {
            throw new IOException(Native.getLastErrorString());
        }
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
        } else {
            throw new IOException(Native.getLastErrorString());
        }
    }

}