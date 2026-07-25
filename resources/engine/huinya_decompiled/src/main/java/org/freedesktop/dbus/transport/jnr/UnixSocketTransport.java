// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.transport.jnr.UnixSocketTransport
package org.freedesktop.dbus.transport.jnr;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import jnr.posix.util.Platform;
import jnr.unixsocket.UnixServerSocket;
import jnr.unixsocket.UnixServerSocketChannel;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;
import jnr.unixsocket.UnixSocketOptions;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.AbstractUnixTransport;
import org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress;
import org.freedesktop.dbus.transport.jnr.JnrUnixSocketHelper;
import org.freedesktop.dbus.utils.Util;

public class UnixSocketTransport extends AbstractUnixTransport {

    // ---- поля ----
  private final UnixSocketAddress unixSocketAddress;
  private UnixSocketChannel socket;
  private UnixServerSocketChannel serverSocket;

   UnixSocketTransport(JnrUnixBusAddress arg0, TransportConfig arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: aload_2
        //      3: invokespecial  #54 // org.freedesktop.dbus.connections.transports.AbstractUnixTransport.<init>:(Lorg/freedesktop/dbus/connections/BusAddress;Lorg/freedesktop/dbus/connections/config/TransportConfig;)V
        //      6: aload_1
        //      7: invokevirtual  #59 // org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress.isAbstract:()Z
        //     10: ifeq  36 (offset +26)
        //     13: aload_0
        //     14: new  #17 // jnr.unixsocket.UnixSocketAddress
        //     17: dup
        //     18: aload_1
        //     19: invokevirtual  #56 // org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress.getAbstract:()Ljava/lang/String;
        //     22: invokedynamic  #66 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     27: invokespecial  #45 // jnr.unixsocket.UnixSocketAddress.<init>:(Ljava/lang/String;)V
        //     30: putfield  #32 // org.freedesktop.dbus.transport.jnr.UnixSocketTransport.unixSocketAddress:Ljnr/unixsocket/UnixSocketAddress;
        //     33: goto  71 (offset +38)
        //     36: aload_1
        //     37: invokevirtual  #58 // org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress.hasPath:()Z
        //     40: ifeq  61 (offset +21)
        //     43: aload_0
        //     44: new  #17 // jnr.unixsocket.UnixSocketAddress
        //     47: dup
        //     48: aload_1
        //     49: invokevirtual  #57 // org.freedesktop.dbus.transport.jnr.JnrUnixBusAddress.getPath:()Ljava/lang/String;
        //     52: invokespecial  #45 // jnr.unixsocket.UnixSocketAddress.<init>:(Ljava/lang/String;)V
        //     55: putfield  #32 // org.freedesktop.dbus.transport.jnr.UnixSocketTransport.unixSocketAddress:Ljnr/unixsocket/UnixSocketAddress;
        //     58: goto  71 (offset +13)
        //     61: new  #23 // org.freedesktop.dbus.exceptions.TransportConfigurationException
        //     64: dup
        //     65: ldc  #5 // "Unix socket url has to specify 'path' or 'abstract'"
        //     67: invokespecial  #55 // org.freedesktop.dbus.exceptions.TransportConfigurationException.<init>:(Ljava/lang/String;)V
        //     70: athrow
        //     71: aload_0
        //     72: invokevirtual  #62 // org.freedesktop.dbus.transport.jnr.UnixSocketTransport.getSaslConfig:()Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //     75: iconst_1
        //     76: invokevirtual  #53 // org.freedesktop.dbus.connections.config.SaslConfig.setAuthMode:(I)V
        //     79: return
    }

  protected boolean hasFileDescriptorSupport() {
        return true;
    }

  protected boolean isBound() {
        return serverSocket == null ? 0 : serverSocket.isOpen();
    }

  public void bindImpl() {
        if (getAddress().isListeningSocket()) {
            if (!isBound()) {
                serverSocket = UnixServerSocketChannel.open();
                serverSocket.configureBlocking(true);
                serverSocket.socket().bind(unixSocketAddress);
            }
            return;
        } else {
            throw new IOException("Cannot listen on a client connection (use connectImpl() instead)");
        }
    }

  public SocketChannel acceptImpl() {
        socket = serverSocket.accept();
        socket.configureBlocking(true);
        if (!Util.isMacOs()) {
            if (!Platform.IS_FREEBSD) {
                socket.setOption(UnixSocketOptions.SO_PASSCRED, Boolean.valueOf(true));
            }
        }
        return socket;
    }

  public SocketChannel connectImpl() {
        if (!getAddress().isListeningSocket()) {
            socket = UnixSocketChannel.open(unixSocketAddress);
            socket.configureBlocking(true);
            if (!Util.isMacOs()) {
                if (!Platform.IS_FREEBSD) {
                    socket.setOption(UnixSocketOptions.SO_PASSCRED, Boolean.valueOf(true));
                }
            }
            return socket;
        } else {
            throw new IOException("Connect connect to a listening socket (use listenImpl() instead)");
        }
    }

  protected void closeTransport() {
        if (socket != null) {
            if (socket.isOpen()) {
                socket.close();
            }
        }
        if (serverSocket != null) {
            if (serverSocket.isOpen()) {
                serverSocket.close();
                String var1 = unixSocketAddress.humanReadablePath();
                if (var1 != null) {
                    if (!var1.startsWith("@")) {
                        Files.deleteIfExists(Path.of(var1, new String[0]));
                    }
                }
            }
        }
    }

  public int getUid(SocketChannel arg0) {
        return JnrUnixSocketHelper.getUid(arg0);
    }

}