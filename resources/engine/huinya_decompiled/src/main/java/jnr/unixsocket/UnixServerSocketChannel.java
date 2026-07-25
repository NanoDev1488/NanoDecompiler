// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixServerSocketChannel
package jnr.unixsocket;

import java.nio.channels.spi.SelectorProvider;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;
import jnr.unixsocket.Native;
import jnr.unixsocket.UnixServerSocket;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;
import jnr.unixsocket.impl.AbstractNativeServerSocketChannel;

public class UnixServerSocketChannel extends AbstractNativeServerSocketChannel {

    // ---- поля ----
  private final UnixServerSocket socket;

   UnixServerSocketChannel(UnixServerSocket arg0) { // было: <init>
        super(Native.socket(ProtocolFamily.PF_UNIX, Sock.SOCK_STREAM, 0));
        socket = new UnixServerSocket(this);
    }

   UnixServerSocketChannel(SelectorProvider arg0, int arg1) { // было: <init>
        super(arg0, arg1, 17);
        socket = new UnixServerSocket(this);
    }

  public static UnixServerSocketChannel open() {
        return new UnixServerSocket().channel;
    }

  public UnixSocketChannel accept() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #16 // jnr.unixsocket.UnixSocketAddress
        //      3: dup
        //      4: invokespecial  #48 // jnr.unixsocket.UnixSocketAddress.<init>:()V
        //      7: astore_1
        //      8: aload_1
        //      9: invokevirtual  #49 // jnr.unixsocket.UnixSocketAddress.getStruct:()Ljnr/unixsocket/SockAddrUnix;
        //     12: astore_2
        //     13: aload_2
        //     14: invokevirtual  #40 // jnr.unixsocket.SockAddrUnix.getMaximumLength:()I
        //     17: istore_3
        //     18: new  #10 // jnr.ffi.byref.IntByReference
        //     21: dup
        //     22: iload_3
        //     23: invokespecial  #33 // jnr.ffi.byref.IntByReference.<init>:(I)V
        //     26: astore  4
        //     28: iconst_m1
        //     29: istore  5
        //     31: aload_0
        //     32: invokevirtual  #44 // jnr.unixsocket.UnixServerSocketChannel.begin:()V
        //     35: aload_0
        //     36: invokevirtual  #46 // jnr.unixsocket.UnixServerSocketChannel.getFD:()I
        //     39: aload_2
        //     40: aload  4
        //     42: invokestatic  #35 // jnr.unixsocket.Native.accept:(ILjnr/unixsocket/SockAddrUnix;Ljnr/ffi/byref/IntByReference;)I
        //     45: istore  5
        //     47: aload_0
        //     48: iload  5
        //     50: iflt  57 (offset +7)
        //     53: iconst_1
        //     54: goto  58 (offset +4)
        //     57: iconst_0
        //     58: invokevirtual  #45 // jnr.unixsocket.UnixServerSocketChannel.end:(Z)V
        //     61: goto  83 (offset +22)
        //     64: astore  6
        //     66: aload_0
        //     67: iload  5
        //     69: iflt  76 (offset +7)
        //     72: iconst_1
        //     73: goto  77 (offset +4)
        //     76: iconst_0
        //     77: invokevirtual  #45 // jnr.unixsocket.UnixServerSocketChannel.end:(Z)V
        //     80: aload  6
        //     82: athrow
        //     83: iload  5
        //     85: ifge  179 (offset +94)
        //     88: aload_0
        //     89: invokevirtual  #47 // jnr.unixsocket.UnixServerSocketChannel.isBlocking:()Z
        //     92: ifeq  177 (offset +85)
        //     95: getstatic  #24 // jnr.unixsocket.UnixServerSocketChannel$1.$SwitchMap$jnr$constants$platform$Errno:[I
        //     98: invokestatic  #36 // jnr.unixsocket.Native.getLastError:()Ljnr/constants/platform/Errno;
        //    101: invokevirtual  #32 // jnr.constants.platform.Errno.ordinal:()I
        //    104: iaload
        //    105: lookupswitch  default->148, 1->132, 2->140
        //    132: new  #5 // java.nio.channels.ClosedChannelException
        //    135: dup
        //    136: invokespecial  #30 // java.nio.channels.ClosedChannelException.<init>:()V
        //    139: athrow
        //    140: new  #6 // java.nio.channels.NotYetBoundException
        //    143: dup
        //    144: invokespecial  #31 // java.nio.channels.NotYetBoundException.<init>:()V
        //    147: athrow
        //    148: new  #2 // java.io.IOException
        //    151: dup
        //    152: new  #4 // java.lang.StringBuilder
        //    155: dup
        //    156: invokespecial  #27 // java.lang.StringBuilder.<init>:()V
        //    159: ldc  #1 // 'accept failed: '
        //    161: invokevirtual  #28 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    164: invokestatic  #37 // jnr.unixsocket.Native.getLastErrorString:()Ljava/lang/String;
        //    167: invokevirtual  #28 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    170: invokevirtual  #29 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    173: invokespecial  #25 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //    176: athrow
        //    177: aconst_null
        //    178: areturn
        //    179: aload_2
        //    180: aload  4
        //    182: invokevirtual  #34 // jnr.ffi.byref.IntByReference.getValue:()Ljava/lang/Number;
        //    185: checkcast  #3 // java.lang.Integer
        //    188: invokevirtual  #26 // java.lang.Integer.intValue:()I
        //    191: invokevirtual  #41 // jnr.unixsocket.SockAddrUnix.updatePath:(I)V
        //    194: iload  5
        //    196: iconst_1
        //    197: invokestatic  #38 // jnr.unixsocket.Native.setBlocking:(IZ)V
        //    200: new  #17 // jnr.unixsocket.UnixSocketChannel
        //    203: dup
        //    204: iload  5
        //    206: invokespecial  #50 // jnr.unixsocket.UnixSocketChannel.<init>:(I)V
        //    209: areturn
        //       Exception table:
        //         from 35 to 47 target 64 type any
        //         from 64 to 66 target 64 type any
    }

  public final UnixServerSocket socket() {
        return socket;
    }

  public final UnixSocketAddress getRemoteSocketAddress() {
        return null;
    }

  public final UnixSocketAddress getLocalSocketAddress() {
        return socket.localAddress;
    }

}