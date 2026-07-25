// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixDatagramSocket
package jnr.unixsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import jnr.unixsocket.Credentials;
import jnr.unixsocket.UnixDatagramChannel;
import jnr.unixsocket.UnixSocketOptions;

public class UnixDatagramSocket extends DatagramSocket {

    // ---- поля ----
  private final UnixDatagramChannel chan;
  private final AtomicBoolean closed;

   UnixDatagramSocket(UnixDatagramChannel arg0) { // было: <init>
        super();
        closed = new AtomicBoolean(false);
        chan = arg0;
    }

  public UnixDatagramSocket() { // было: <init>
        super();
        closed = new AtomicBoolean(false);
        chan = null;
    }

  public void bind(SocketAddress arg0) {
        if (null == chan) {
            return;
        }
        if (isClosed()) {
            throw new SocketException("Socket is closed");
        }
        if (isBound()) {
            throw new SocketException("already bound");
        }
        try {
            chan.bind(arg0);
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
        }
    }

  public synchronized void disconnect() {
        if (!isClosed()) {
            if (null != chan) {
                try {
                    chan.disconnect();
                } catch (IOException var1) {
                    ignore();
                }
            }
        } else {
            return;
        }
    }

  public synchronized void close() {
        if (null != chan) {
            if (closed.compareAndSet(false, true)) {
                try {
                    chan.close();
                } catch (IOException var1) {
                    ignore();
                }
            }
        }
    }

  public void connect(SocketAddress arg0) {
        try {
            chan.connect(arg0);
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
        }
    }

  public void connect(InetAddress arg0, int arg1) {
        throw new UnsupportedOperationException("connect(InetAddress, int) is not supported");
    }

  public DatagramChannel getChannel() {
        return chan;
    }

  public InetAddress getInetAddress() {
        return null;
    }

  public SocketAddress getLocalSocketAddress() {
        if (!isClosed()) {
            if (null != chan) {
                return chan.getLocalSocketAddress();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

  public SocketAddress getRemoteSocketAddress() {
        if (isConnected()) {
            return chan.getRemoteSocketAddress();
        } else {
            return null;
        }
    }

  public boolean isBound() {
        if (null != chan) {
            return chan.isBound();
        } else {
            return false;
        }
    }

  public boolean isClosed() {
        if (null != chan) {
            return closed.get();
        } else {
            return false;
        }
    }

  public boolean isConnected() {
        if (null != chan) {
            return chan.isConnected();
        } else {
            return false;
        }
    }

  public final Credentials getCredentials() {
        Credentials __stk1;
        if (!chan.isConnected()) {
            return null;
        }
        try {
            __stk1 = ((Credentials) chan.getOption(UnixSocketOptions.SO_PEERCRED));
        } catch (IOException var1) {
            throw ((SocketException) new SocketException().initCause(var1));
        }
    }

  public int getReceiveBufferSize() {
        int __stk1;
        try {
            __stk1 = (((Integer) chan.getOption(UnixSocketOptions.SO_RCVBUF))).intValue();
        } catch (IOException var1) {
            throw ((SocketException) new SocketException().initCause(var1));
        }
    }

  public int getSendBufferSize() {
        int __stk1;
        try {
            __stk1 = (((Integer) chan.getOption(UnixSocketOptions.SO_SNDBUF))).intValue();
        } catch (IOException var1) {
            throw ((SocketException) new SocketException().initCause(var1));
        }
    }

  public int getSoTimeout() {
        int __stk1;
        try {
            __stk1 = (((Integer) chan.getOption(UnixSocketOptions.SO_RCVTIMEO))).intValue();
        } catch (IOException var1) {
            throw ((SocketException) new SocketException().initCause(var1));
        }
    }

  public void setReceiveBufferSize(int arg0) {
        try {
            chan.setOption(UnixSocketOptions.SO_RCVBUF, Integer.valueOf(arg0));
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
        }
    }

  public void setSendBufferSize(int arg0) {
        try {
            chan.setOption(UnixSocketOptions.SO_SNDBUF, Integer.valueOf(arg0));
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
        }
    }

  public void setSoTimeout(int arg0) {
        try {
            chan.setOption(UnixSocketOptions.SO_RCVTIMEO, Integer.valueOf(arg0));
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
        }
    }

  public void send(DatagramPacket arg0) {
        throw new UnsupportedOperationException("sending DatagramPackets is not supported");
    }

  public synchronized void receive(DatagramPacket arg0) {
        throw new UnsupportedOperationException("receiving DatagramPackets is not supported");
    }

  private void ignore() {
        // (пустое тело)
    }

}