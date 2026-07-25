// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeFileSelectorProvider
package jnr.enxio.channels;

import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import jnr.enxio.channels.NativeFileSelectorProvider_SingletonHolder;
import jnr.enxio.channels.PollSelector;

public final class NativeFileSelectorProvider extends SelectorProvider {

  public NativeFileSelectorProvider() { // было: <init>
        super();
    }

  public static final SelectorProvider getInstance() {
        return NativeFileSelectorProvider_SingletonHolder.INSTANCE;
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
        return new PollSelector(this);
    }

  public ServerSocketChannel openServerSocketChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  public SocketChannel openSocketChannel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}