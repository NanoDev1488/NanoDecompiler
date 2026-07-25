// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixServerSocket
package jnr.unixsocket;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.UnsupportedAddressTypeException;
import jnr.unixsocket.Common;
import jnr.unixsocket.Native;
import jnr.unixsocket.UnixServerSocketChannel;
import jnr.unixsocket.UnixSocket;
import jnr.unixsocket.UnixSocketAddress;

public class UnixServerSocket {

    // ---- поля ----
  final UnixServerSocketChannel channel;
  final int fd;
  volatile UnixSocketAddress localAddress;

  public UnixServerSocket() { // было: <init>
        super();
        channel = new UnixServerSocketChannel(this);
        fd = channel.getFD();
    }

   UnixServerSocket(UnixServerSocketChannel arg0) { // было: <init>
        super();
        channel = arg0;
        fd = arg0.getFD();
    }

  public UnixSocket accept() {
        return new UnixSocket(channel.accept());
    }

  public void bind(SocketAddress arg0) {
        bind(arg0, 128);
    }

  public void bind(SocketAddress arg0, int arg1) {
        if (null == arg0) {
            localAddress = Common.bind(fd, ((UnixSocketAddress) arg0));
            if (Native.listen(fd, arg1) >= 0) {
                return;
            } else {
                throw new IOException(Native.getLastErrorString());
            }
        } else {
            if (arg0 instanceof UnixSocketAddress) {
                localAddress = Common.bind(fd, ((UnixSocketAddress) arg0));
                if (Native.listen(fd, arg1) >= 0) {
                    return;
                } else {
                    throw new IOException(Native.getLastErrorString());
                }
            } else {
                throw new UnsupportedAddressTypeException();
            }
        }
    }

}