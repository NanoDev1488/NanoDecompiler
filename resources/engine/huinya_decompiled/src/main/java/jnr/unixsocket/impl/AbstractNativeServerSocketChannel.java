// исходный (обфусцированный) внутренний класс: jnr.unixsocket.impl.AbstractNativeServerSocketChannel
package jnr.unixsocket.impl;

import java.nio.channels.spi.SelectorProvider;
import jnr.constants.platform.Shutdown;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeServerSocketChannel;

public abstract class AbstractNativeServerSocketChannel extends NativeServerSocketChannel {

    // ---- поля ----
  private static final int SHUT_RD;

    static {
        SHUT_RD = Shutdown.SHUT_RD.intValue();
    }

  public AbstractNativeServerSocketChannel(int arg0) { // было: <init>
        super(arg0);
    }

  public AbstractNativeServerSocketChannel(SelectorProvider arg0, int arg1, int arg2) { // было: <init>
        super(arg0, arg1, arg2);
    }

  protected void implCloseSelectableChannel() {
        Native.shutdown(getFD(), SHUT_RD);
        Native.close(getFD());
    }

}