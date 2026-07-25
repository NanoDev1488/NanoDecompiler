// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocket
package jnr.unixsocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import jnr.unixsocket.Credentials;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;
import jnr.unixsocket.UnixSocketOptions;
import jnr.unixsocket.UnixSocket_UnselectableByteChannel;

public class UnixSocket extends Socket {

    // ---- поля ----
  private UnixSocketChannel chan;
  private AtomicBoolean closed;
  private AtomicBoolean indown;
  private AtomicBoolean outdown;
  private InputStream in;
  private OutputStream out;

  public UnixSocket(UnixSocketChannel arg0) { // было: <init>
        super();
        closed = new AtomicBoolean(false);
        indown = new AtomicBoolean(false);
        outdown = new AtomicBoolean(false);
        chan = arg0;
        in = Channels.newInputStream(new UnixSocket_UnselectableByteChannel(arg0));
        out = Channels.newOutputStream(new UnixSocket_UnselectableByteChannel(arg0));
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

  public void close() {
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
        connect(arg0, 0);
    }

  public void connect(SocketAddress arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: instanceof  #20 // jnr.unixsocket.UnixSocketAddress
        //      4: ifeq  22 (offset +18)
        //      7: aload_0
        //      8: getfield  #23 // jnr.unixsocket.UnixSocket.chan:Ljnr/unixsocket/UnixSocketChannel;
        //     11: aload_1
        //     12: checkcast  #20 // jnr.unixsocket.UnixSocketAddress
        //     15: invokevirtual  #61 // jnr.unixsocket.UnixSocketChannel.connect:(Ljnr/unixsocket/UnixSocketAddress;)Z
        //     18: pop
        //     19: goto  67 (offset +48)
        //     22: new  #9 // java.lang.IllegalArgumentException
        //     25: dup
        //     26: new  #12 // java.lang.StringBuilder
        //     29: dup
        //     30: invokespecial  #41 // java.lang.StringBuilder.<init>:()V
        //     33: ldc  #4 // 'address of type '
        //     35: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     38: aload_1
        //     39: invokevirtual  #40 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     42: invokevirtual  #42 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     45: ldc  #1 // ' are not supported. Use '
        //     47: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     50: ldc  #20 // jnr.unixsocket.UnixSocketAddress
        //     52: invokevirtual  #42 // java.lang.StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //     55: ldc  #2 // ' instead'
        //     57: invokevirtual  #43 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     60: invokevirtual  #44 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //     63: invokespecial  #37 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     66: athrow
        //     67: return
    }

  public SocketChannel getChannel() {
        return chan;
    }

  public InetAddress getInetAddress() {
        return null;
    }

  public InputStream getInputStream() {
        if (!chan.isConnected()) {
            throw new IOException("not connected");
        } else {
            return in;
        }
    }

  public SocketAddress getLocalSocketAddress() {
        return chan.getLocalSocketAddress();
    }

  public OutputStream getOutputStream() {
        if (!chan.isConnected()) {
            throw new IOException("not connected");
        } else {
            return out;
        }
    }

  public SocketAddress getRemoteSocketAddress() {
        UnixSocketAddress var1 = chan.getRemoteSocketAddress();
        if (var1 == null) {
            return null;
        } else {
            return var1;
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
        return closed.get();
    }

  public boolean isConnected() {
        return chan.isConnected();
    }

  public boolean isInputShutdown() {
        return indown.get();
    }

  public boolean isOutputShutdown() {
        return outdown.get();
    }

  public void shutdownInput() {
        if (indown.compareAndSet(false, true)) {
            chan.shutdownInput();
        }
    }

  public void shutdownOutput() {
        if (outdown.compareAndSet(false, true)) {
            chan.shutdownOutput();
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

  public boolean getKeepAlive() {
        boolean __stk1;
        try {
            __stk1 = (((Boolean) chan.getOption(UnixSocketOptions.SO_KEEPALIVE))).booleanValue();
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

  public void setKeepAlive(boolean arg0) {
        try {
            chan.setOption(UnixSocketOptions.SO_KEEPALIVE, Boolean.valueOf(arg0));
        } catch (IOException var2) {
            throw ((SocketException) new SocketException().initCause(var2));
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

  private void ignore() {
        // (пустое тело)
    }

}