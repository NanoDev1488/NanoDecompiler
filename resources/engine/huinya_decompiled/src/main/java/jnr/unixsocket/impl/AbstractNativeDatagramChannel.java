// исходный (обфусцированный) внутренний класс: jnr.unixsocket.impl.AbstractNativeDatagramChannel
package jnr.unixsocket.impl;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.spi.SelectorProvider;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;
import jnr.unixsocket.impl.Common;

public abstract class AbstractNativeDatagramChannel extends DatagramChannel implements ByteChannel, NativeSelectableChannel {

    // ---- поля ----
  private final Common common;

  public AbstractNativeDatagramChannel(int arg0) { // было: <init>
        this(NativeSelectorProvider.getInstance(), arg0);
    }

   AbstractNativeDatagramChannel(SelectorProvider arg0, int arg1) { // было: <init>
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

}