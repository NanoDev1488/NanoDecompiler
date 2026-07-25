// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixDatagramChannel
package jnr.unixsocket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.channels.NetworkChannel;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;
import jnr.unixsocket.BindHandler;
import jnr.unixsocket.Common;
import jnr.unixsocket.Native;
import jnr.unixsocket.UnixDatagramChannel_DefaultOptionsHolder;
import jnr.unixsocket.UnixDatagramChannel_State;
import jnr.unixsocket.UnixDatagramSocket;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.impl.AbstractNativeDatagramChannel;

public class UnixDatagramChannel extends AbstractNativeDatagramChannel {

    // ---- поля ----
  private UnixDatagramChannel_State state;
  private UnixSocketAddress remoteAddress;
  private UnixSocketAddress localAddress;
  private final ReadWriteLock stateLock;
  private final BindHandler bindHandler;

  public static final UnixDatagramChannel open() {
        return new UnixDatagramChannel();
    }

  public static final UnixDatagramChannel open(ProtocolFamily arg0, int arg1) {
        return new UnixDatagramChannel(arg0, arg1);
    }

  public static final UnixDatagramChannel[] pair() {
        int[] var0 = new int[]{-1, -1};
        Native.socketpair(ProtocolFamily.PF_UNIX, Sock.SOCK_DGRAM, 0, var0);
        UnixDatagramChannel[] __obj2 = new UnixDatagramChannel[2];
        __obj2[0] = new UnixDatagramChannel(var0[0], UnixDatagramChannel_State.CONNECTED, true);
        __obj2[1] = new UnixDatagramChannel(var0[1], UnixDatagramChannel_State.CONNECTED, true);
        return __obj2;
    }

  private UnixDatagramChannel() { // было: <init>
        this(Native.socket(ProtocolFamily.PF_UNIX, Sock.SOCK_DGRAM, 0));
    }

   UnixDatagramChannel(ProtocolFamily arg0, int arg1) { // было: <init>
        this(Native.socket(arg0, Sock.SOCK_DGRAM, arg1));
    }

   UnixDatagramChannel(int arg0) { // было: <init>
        this(arg0, UnixDatagramChannel_State.IDLE, false);
    }

   UnixDatagramChannel(int arg0, UnixDatagramChannel_State arg1, boolean arg2) { // было: <init>
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

   UnixDatagramChannel(int arg0, UnixSocketAddress arg1) { // было: <init>
        this(arg0);
        connect(arg1);
    }

  public UnixDatagramChannel bind(SocketAddress arg0) {
        localAddress = bindHandler.bind(getFD(), arg0);
        return this;
    }

  public UnixDatagramChannel connect(UnixSocketAddress arg0) {
        stateLock.writeLock().lock();
        remoteAddress = arg0;
        state = UnixDatagramChannel_State.CONNECTED;
        stateLock.writeLock().unlock();
        return this;
    }

  public UnixDatagramChannel disconnect() {
        stateLock.writeLock().lock();
        remoteAddress = null;
        state = UnixDatagramChannel_State.IDLE;
        stateLock.writeLock().unlock();
        return this;
    }

   boolean isBound() {
        return bindHandler.isBound();
    }

  public boolean isConnected() {
        boolean __stk1;
        stateLock.readLock().lock();
        __stk1 = state == UnixDatagramChannel_State.CONNECTED;
        int var1 = __stk1;
        stateLock.readLock().unlock();
        return ((Boolean) var1);
    }

  public final UnixSocketAddress getRemoteSocketAddress() {
        UnixSocketAddress __stk1;
        if (isConnected()) {
            if (remoteAddress == null) {
                remoteAddress = Common.getpeername(getFD());
                __stk1 = Common.getpeername(getFD());
            } else {
                __stk1 = remoteAddress;
            }
            return __stk1;
        } else {
            return null;
        }
    }

  public final UnixSocketAddress getLocalSocketAddress() {
        UnixSocketAddress __stk1;
        if (localAddress == null) {
            localAddress = Common.getsockname(getFD());
            __stk1 = Common.getsockname(getFD());
        } else {
            __stk1 = localAddress;
        }
        return __stk1;
    }

  public UnixSocketAddress receive(ByteBuffer arg0) {
        UnixSocketAddress var2 = new UnixSocketAddress();
        int var3 = Native.recvfrom(getFD(), arg0, var2.getStruct());
        if (var3 >= 0) {
            return var2;
        } else {
            throw new IOException(Native.getLastErrorString());
        }
    }

  public int send(ByteBuffer arg0, SocketAddress arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_3
        //      2: aconst_null
        //      3: aload_2
        //      4: if_acmpne  32 (offset +28)
        //      7: aload_0
        //      8: invokevirtual  #73 // jnr.unixsocket.UnixDatagramChannel.isConnected:()Z
        //     11: ifeq  22 (offset +11)
        //     14: aload_0
        //     15: getfield  #35 // jnr.unixsocket.UnixDatagramChannel.remoteAddress:Ljnr/unixsocket/UnixSocketAddress;
        //     18: astore_3
        //     19: goto  52 (offset +33)
        //     22: new  #8 // java.lang.IllegalArgumentException
        //     25: dup
        //     26: ldc  #4 // 'Destination address cannot be null on unconnected datagram sockets'
        //     28: invokespecial  #42 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     31: athrow
        //     32: aload_2
        //     33: instanceof  #29 // jnr.unixsocket.UnixSocketAddress
        //     36: ifne  47 (offset +11)
        //     39: new  #14 // java.nio.channels.UnsupportedAddressTypeException
        //     42: dup
        //     43: invokespecial  #50 // java.nio.channels.UnsupportedAddressTypeException.<init>:()V
        //     46: athrow
        //     47: aload_2
        //     48: checkcast  #29 // jnr.unixsocket.UnixSocketAddress
        //     51: astore_3
        //     52: aconst_null
        //     53: aload_3
        //     54: if_acmpne  61 (offset +7)
        //     57: aconst_null
        //     58: goto  65 (offset +7)
        //     61: aload_3
        //     62: invokevirtual  #80 // jnr.unixsocket.UnixSocketAddress.getStruct:()Ljnr/unixsocket/SockAddrUnix;
        //     65: astore  4
        //     67: aconst_null
        //     68: aload  4
        //     70: if_acmpne  77 (offset +7)
        //     73: iconst_0
        //     74: goto  82 (offset +8)
        //     77: aload  4
        //     79: invokevirtual  #64 // jnr.unixsocket.SockAddrUnix.length:()I
        //     82: istore  5
        //     84: aload_0
        //     85: invokevirtual  #72 // jnr.unixsocket.UnixDatagramChannel.getFD:()I
        //     88: aload_1
        //     89: aload  4
        //     91: iload  5
        //     93: invokestatic  #61 // jnr.unixsocket.Native.sendto:(ILjava/nio/ByteBuffer;Ljnr/unixsocket/SockAddrUnix;I)I
        //     96: istore  6
        //     98: iload  6
        //    100: ifge  114 (offset +14)
        //    103: new  #7 // java.io.IOException
        //    106: dup
        //    107: invokestatic  #59 // jnr.unixsocket.Native.getLastErrorString:()Ljava/lang/String;
        //    110: invokespecial  #41 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //    113: athrow
        //    114: iload  6
        //    116: ireturn
    }

  public DatagramChannel connect(SocketAddress arg0) {
        if (!(arg0 instanceof UnixSocketAddress)) {
            throw new UnsupportedAddressTypeException();
        } else {
            return connect(((UnixSocketAddress) arg0));
        }
    }

  public UnixDatagramSocket socket() {
        UnixDatagramSocket __stk1;
        try {
            __stk1 = new UnixDatagramSocket(this);
        } catch (SocketException var1) {
            throw new NullPointerException("Could not create UnixDatagramSocket");
        }
    }

  public long write(ByteBuffer[] arg0, int arg1, int arg2) {
        if (state != UnixDatagramChannel_State.CONNECTED) {
            if (state != UnixDatagramChannel_State.IDLE) {
                throw new ClosedChannelException();
            } else {
                return 0L;
            }
        } else {
            return super.write(arg0, arg1, arg2);
        }
    }

  public int read(ByteBuffer arg0) {
        if (state != UnixDatagramChannel_State.CONNECTED) {
            if (state != UnixDatagramChannel_State.IDLE) {
                throw new ClosedChannelException();
            } else {
                return 0;
            }
        } else {
            return super.read(arg0);
        }
    }

  public int write(ByteBuffer arg0) {
        if (state != UnixDatagramChannel_State.CONNECTED) {
            if (state != UnixDatagramChannel_State.IDLE) {
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
        return UnixDatagramChannel_DefaultOptionsHolder.defaultOptions;
    }

  public Object getOption(SocketOption arg0) {
        if (supportedOptions().contains(arg0)) {
            return Common.getSocketOption(getFD(), arg0);
        } else {
            throw new UnsupportedOperationException(new StringBuilder().append("'").append(arg0).append("' not supported").toString());
        }
    }

  public DatagramChannel setOption(SocketOption arg0, Object arg1) {
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

  public MembershipKey join(InetAddress arg0, NetworkInterface arg1) {
        throw new UnsupportedOperationException("join is not supported");
    }

  public MembershipKey join(InetAddress arg0, NetworkInterface arg1, InetAddress arg2) {
        throw new UnsupportedOperationException("join is not supported");
    }

  public SocketAddress receive(ByteBuffer arg0) {
        return receive(arg0);
    }

  public DatagramChannel disconnect() {
        return disconnect();
    }

  public DatagramSocket socket() {
        return socket();
    }

  public DatagramChannel bind(SocketAddress arg0) {
        return bind(arg0);
    }

  public NetworkChannel setOption(SocketOption arg0, Object arg1) {
        return setOption(arg0, arg1);
    }

  public NetworkChannel bind(SocketAddress arg0) {
        return bind(arg0);
    }

}