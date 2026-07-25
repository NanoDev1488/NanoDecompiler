// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketChannel
package jnr.unixsocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NetworkChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jnr.constants.platform.Errno;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;
import jnr.ffi.LastError;
import jnr.ffi.Runtime;
import jnr.unixsocket.BindHandler;
import jnr.unixsocket.Common;
import jnr.unixsocket.Native;
import jnr.unixsocket.SockAddrUnix;
import jnr.unixsocket.UnixSocket;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel_DefaultOptionsHolder;
import jnr.unixsocket.UnixSocketChannel_State;
import jnr.unixsocket.impl.AbstractNativeSocketChannel;

public class UnixSocketChannel extends AbstractNativeSocketChannel {

    // ---- поля ----
  private UnixSocketChannel_State state;
  private UnixSocketAddress remoteAddress;
  private UnixSocketAddress localAddress;
  private final ReadWriteLock stateLock;
  private final BindHandler bindHandler;

  public static final UnixSocketChannel open() {
        return new UnixSocketChannel();
    }

  public static final UnixSocketChannel open(UnixSocketAddress arg0) {
        UnixSocketChannel var1 = new UnixSocketChannel();
        try {
            var1.connect(arg0);
        } catch (IOException var2) {
            var1.close();
            throw var2;
        }
    }

  public static final UnixSocketChannel create() {
        return new UnixSocketChannel();
    }

  public static final UnixSocketChannel[] pair() {
        int[] var0 = new int[]{-1, -1};
        Native.socketpair(ProtocolFamily.PF_UNIX, Sock.SOCK_STREAM, 0, var0);
        UnixSocketChannel[] __obj2 = new UnixSocketChannel[2];
        __obj2[0] = new UnixSocketChannel(var0[0], UnixSocketChannel_State.CONNECTED, true);
        __obj2[1] = new UnixSocketChannel(var0[1], UnixSocketChannel_State.CONNECTED, true);
        return __obj2;
    }

  public static final UnixSocketChannel fromFD(int arg0) {
        return new UnixSocketChannel(arg0);
    }

   UnixSocketChannel() { // было: <init>
        this(Native.socket(ProtocolFamily.PF_UNIX, Sock.SOCK_STREAM, 0));
    }

   UnixSocketChannel(int arg0) { // было: <init>
        this(arg0, UnixSocketChannel_State.CONNECTED, false);
    }

   UnixSocketChannel(int arg0, UnixSocketChannel_State arg1, boolean arg2) { // было: <init>
        super(arg0);
        remoteAddress = null;
        localAddress = null;
        stateLock = new ReentrantReadWriteLock();
        stateLock.writeLock().lock();
        try {
            state = arg1;
            bindHandler = new BindHandler(arg2);
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var4 = e2;
                }
            } catch (Throwable var4) {
            }
        }
    }

  private boolean doConnect(SockAddrUnix arg0) {
        if (Native.connect(getFD(), arg0, arg0.length()) == 0) {
            return true;
        }
        Errno var2 = Errno.valueOf(((long) LastError.getLastError(Runtime.getSystemRuntime())));
        switch (var2) {
            case EAGAIN:
            case EWOULDBLOCK:
                return false;
            default:
                throw new IOException(var2.toString());
        }
    }

  public boolean connect(UnixSocketAddress arg0) {
        remoteAddress = arg0;
        if (doConnect(remoteAddress.getStruct())) {
            stateLock.writeLock().lock();
            state = UnixSocketChannel_State.CONNECTED;
            stateLock.writeLock().unlock();
            return true;
        } else {
            stateLock.writeLock().lock();
            state = UnixSocketChannel_State.CONNECTING;
            stateLock.writeLock().unlock();
            return false;
        }
    }

   boolean isBound() {
        return bindHandler.isBound();
    }

  public boolean isConnected() {
        boolean __stk1;
        stateLock.readLock().lock();
        __stk1 = state == UnixSocketChannel_State.CONNECTED;
        int var1 = __stk1;
        stateLock.readLock().unlock();
        return ((Boolean) var1);
    }

  private boolean isIdle() {
        boolean __stk1;
        stateLock.readLock().lock();
        __stk1 = state == UnixSocketChannel_State.IDLE;
        int var1 = __stk1;
        stateLock.readLock().unlock();
        return ((Boolean) var1);
    }

  public boolean isConnectionPending() {
        boolean __stk1;
        stateLock.readLock().lock();
        __stk1 = state == UnixSocketChannel_State.CONNECTING;
        int var1 = __stk1;
        stateLock.readLock().unlock();
        return ((Boolean) var1);
    }

  public boolean finishConnect() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #38 // jnr.unixsocket.UnixSocketChannel.stateLock:Ljava/util/concurrent/locks/ReadWriteLock;
        //      4: invokeinterface  #96 // java.util.concurrent.locks.ReadWriteLock.writeLock:()Ljava/util/concurrent/locks/Lock;, count 1
        //      9: invokeinterface  #93 // java.util.concurrent.locks.Lock.lock:()V, count 1
        //     14: getstatic  #40 // jnr.unixsocket.UnixSocketChannel$1.$SwitchMap$jnr$unixsocket$UnixSocketChannel$State:[I
        //     17: aload_0
        //     18: getfield  #37 // jnr.unixsocket.UnixSocketChannel.state:Ljnr/unixsocket/UnixSocketChannel$State;
        //     21: invokevirtual  #87 // jnr.unixsocket.UnixSocketChannel$State.ordinal:()I
        //     24: iaload
        //     25: lookupswitch  default->127, 1->52, 2->70
        //     52: iconst_1
        //     53: istore_1
        //     54: aload_0
        //     55: getfield  #38 // jnr.unixsocket.UnixSocketChannel.stateLock:Ljava/util/concurrent/locks/ReadWriteLock;
        //     58: invokeinterface  #96 // java.util.concurrent.locks.ReadWriteLock.writeLock:()Ljava/util/concurrent/locks/Lock;, count 1
        //     63: invokeinterface  #94 // java.util.concurrent.locks.Lock.unlock:()V, count 1
        //     68: iload_1
        //     69: ireturn
        //     70: aload_0
        //     71: aload_0
        //     72: getfield  #36 // jnr.unixsocket.UnixSocketChannel.remoteAddress:Ljnr/unixsocket/UnixSocketAddress;
        //     75: invokevirtual  #73 // jnr.unixsocket.UnixSocketAddress.getStruct:()Ljnr/unixsocket/SockAddrUnix;
        //     78: invokespecial  #80 // jnr.unixsocket.UnixSocketChannel.doConnect:(Ljnr/unixsocket/SockAddrUnix;)Z
        //     81: ifne  102 (offset +21)
        //     84: iconst_0
        //     85: istore_1
        //     86: aload_0
        //     87: getfield  #38 // jnr.unixsocket.UnixSocketChannel.stateLock:Ljava/util/concurrent/locks/ReadWriteLock;
        //     90: invokeinterface  #96 // java.util.concurrent.locks.ReadWriteLock.writeLock:()Ljava/util/concurrent/locks/Lock;, count 1
        //     95: invokeinterface  #94 // java.util.concurrent.locks.Lock.unlock:()V, count 1
        //    100: iload_1
        //    101: ireturn
        //    102: aload_0
        //    103: getstatic  #42 // jnr.unixsocket.UnixSocketChannel$State.CONNECTED:Ljnr/unixsocket/UnixSocketChannel$State;
        //    106: putfield  #37 // jnr.unixsocket.UnixSocketChannel.state:Ljnr/unixsocket/UnixSocketChannel$State;
        //    109: iconst_1
        //    110: istore_1
        //    111: aload_0
        //    112: getfield  #38 // jnr.unixsocket.UnixSocketChannel.stateLock:Ljava/util/concurrent/locks/ReadWriteLock;
        //    115: invokeinterface  #96 // java.util.concurrent.locks.ReadWriteLock.writeLock:()Ljava/util/concurrent/locks/Lock;, count 1
        //    120: invokeinterface  #94 // java.util.concurrent.locks.Lock.unlock:()V, count 1
        //    125: iload_1
        //    126: ireturn
        //    127: new  #7 // java.lang.IllegalStateException
        //    130: dup
        //    131: ldc  #4 // 'socket is not waiting for connect to complete'
        //    133: invokespecial  #47 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //    136: athrow
        //    137: astore_2
        //    138: aload_0
        //    139: getfield  #38 // jnr.unixsocket.UnixSocketChannel.stateLock:Ljava/util/concurrent/locks/ReadWriteLock;
        //    142: invokeinterface  #96 // java.util.concurrent.locks.ReadWriteLock.writeLock:()Ljava/util/concurrent/locks/Lock;, count 1
        //    147: invokeinterface  #94 // java.util.concurrent.locks.Lock.unlock:()V, count 1
        //    152: aload_2
        //    153: athrow
        //       Exception table:
        //         from 14 to 54 target 137 type any
        //         from 70 to 86 target 137 type any
        //         from 102 to 111 target 137 type any
        //         from 127 to 138 target 137 type any
    }

  public final UnixSocketAddress getRemoteSocketAddress() {
        if (isConnected()) {
            if (remoteAddress == null) {
                remoteAddress = Common.getpeername(getFD());
                return remoteAddress;
            } else {
                return remoteAddress;
            }
        } else {
            return null;
        }
    }

  public final UnixSocketAddress getLocalSocketAddress() {
        if (localAddress == null) {
            localAddress = Common.getsockname(getFD());
            return localAddress;
        } else {
            return localAddress;
        }
    }

  public boolean connect(SocketAddress arg0) {
        if (!(arg0 instanceof UnixSocketAddress)) {
            throw new UnsupportedAddressTypeException();
        } else {
            return connect(((UnixSocketAddress) arg0));
        }
    }

  public UnixSocket socket() {
        return new UnixSocket(this);
    }

  public long write(ByteBuffer[] arg0, int arg1, int arg2) {
        if (!isConnected()) {
            if (!isIdle()) {
                throw new ClosedChannelException();
            } else {
                return 0L;
            }
        } else {
            return super.write(arg0, arg1, arg2);
        }
    }

  public int read(ByteBuffer arg0) {
        if (!isConnected()) {
            if (!isIdle()) {
                throw new ClosedChannelException();
            } else {
                return 0;
            }
        } else {
            return super.read(arg0);
        }
    }

  public int write(ByteBuffer arg0) {
        if (!isConnected()) {
            if (!isIdle()) {
                throw new ClosedChannelException();
            } else {
                return 0;
            }
        } else {
            return super.write(arg0);
        }
    }

  public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

  public SocketAddress getLocalAddress() {
        return localAddress;
    }

  public final Set supportedOptions() {
        return UnixSocketChannel_DefaultOptionsHolder.defaultOptions;
    }

  public Object getOption(SocketOption arg0) {
        if (supportedOptions().contains(arg0)) {
            return Common.getSocketOption(getFD(), arg0);
        } else {
            throw new UnsupportedOperationException(new StringBuilder().append("'").append(arg0).append("' not supported").toString());
        }
    }

  public SocketChannel setOption(SocketOption arg0, Object arg1) {
        if (arg0 != null) {
            if (supportedOptions().contains(arg0)) {
                Common.setSocketOption(getFD(), arg0, arg1);
                return this;
            } else {
                throw new UnsupportedOperationException(new StringBuilder().append("'").append(arg0).append("' not supported").toString());
            }
        } else {
            throw new IllegalArgumentException("name may not be null");
        }
    }

  public synchronized UnixSocketChannel bind(SocketAddress arg0) {
        localAddress = bindHandler.bind(getFD(), arg0);
        return this;
    }

  public Socket socket() {
        return socket();
    }

  public SocketChannel bind(SocketAddress arg0) {
        return bind(arg0);
    }

  public NetworkChannel setOption(SocketOption arg0, Object arg1) {
        return setOption(arg0, arg1);
    }

  public NetworkChannel bind(SocketAddress arg0) {
        return bind(arg0);
    }

}