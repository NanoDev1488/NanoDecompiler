// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeServerSocketChannel
package jnr.enxio.channels;

import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;

public class NativeServerSocketChannel extends AbstractSelectableChannel implements NativeSelectableChannel {

    // ---- поля ----
  private final int fd;
  private final int validOps;

  public NativeServerSocketChannel(int arg0) { // было: <init>
        this(NativeSelectorProvider.getInstance(), arg0, 17);
    }

  public NativeServerSocketChannel(SelectorProvider arg0, int arg1, int arg2) { // было: <init>
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

}