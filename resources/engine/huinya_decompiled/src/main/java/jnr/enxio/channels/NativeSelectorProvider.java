// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeSelectorProvider
package jnr.enxio.channels;

import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import jnr.enxio.channels.KQSelector;
import jnr.enxio.channels.NativeSelectorProvider_SingletonHolder;
import jnr.enxio.channels.PollSelector;
import jnr.ffi.Platform;

public final class NativeSelectorProvider extends SelectorProvider {

  public NativeSelectorProvider() { // было: <init>
        super();
    }

  public static final SelectorProvider getInstance() {
        return NativeSelectorProvider_SingletonHolder.INSTANCE;
    }

  public DatagramChannel openDatagramChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  public DatagramChannel openDatagramChannel(ProtocolFamily arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  public Pipe openPipe() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  public AbstractSelector openSelector() {
        return !Platform.getNativePlatform().isBSD() ? new PollSelector(this) : new KQSelector(this);
    }

  public ServerSocketChannel openServerSocketChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  public SocketChannel openSocketChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}