// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.transports.AbstractTransport
package org.freedesktop.dbus.connections.transports;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.SASL;
import org.freedesktop.dbus.connections.SASL_SaslMode;
import org.freedesktop.dbus.connections.config.SaslConfig;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.transports.TransportConnection;
import org.freedesktop.dbus.exceptions.AuthenticationException;
import org.freedesktop.dbus.exceptions.InvalidBusAddressException;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.freedesktop.dbus.spi.message.IMessageWriter;
import org.freedesktop.dbus.spi.message.ISocketProvider;
import org.freedesktop.dbus.utils.IThrowingSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTransport implements Closeable {

    // ---- поля ----
  private static final AtomicLong TRANSPORT_ID_GENERATOR;
  private final ServiceLoader spiLoader;
  private final Logger logger;
  private final BusAddress address;
  private TransportConnection transportConnection;
  private boolean fileDescriptorSupported;
  private final long transportId;
  private final TransportConfig config;
  private final MessageFactory messageFactory;

    static {
        TRANSPORT_ID_GENERATOR = new AtomicLong(0L);
    }

  protected AbstractTransport(BusAddress arg0, TransportConfig arg1) { // было: <init>
        super();
        spiLoader = ServiceLoader.load(ISocketProvider.class, AbstractTransport.class.getClassLoader());
        logger = LoggerFactory.getLogger(getClass());
        transportId = TRANSPORT_ID_GENERATOR.incrementAndGet();
        address = ((BusAddress) Objects.requireNonNull(arg0, "BusAddress required"));
        config = ((TransportConfig) Objects.requireNonNull(arg1, "Config required"));
        if (!arg0.isListeningSocket()) {
            config.getSaslConfig().setMode(SASL_SaslMode.CLIENT);
        } else {
            config.getSaslConfig().setMode(SASL_SaslMode.SERVER);
        }
        config.getSaslConfig().setGuid(address.getGuid());
        config.getSaslConfig().setFileDescriptorSupport(hasFileDescriptorSupport());
        messageFactory = new MessageFactory(config.getEndianess());
    }

  public void writeMessage(Message arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #61 // org.freedesktop.dbus.connections.transports.AbstractTransport.fileDescriptorSupported:Z
        //      4: ifne  26 (offset +22)
        //      7: bipush  104
        //      9: aload_1
        //     10: invokevirtual  #121 // org.freedesktop.dbus.messages.Message.getType:()B
        //     13: if_icmpne  26 (offset +13)
        //     16: new  #23 // java.lang.IllegalArgumentException
        //     19: dup
        //     20: ldc  #11 // 'File descriptors are not supported!'
        //     22: invokespecial  #70 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     25: athrow
        //     26: aload_0
        //     27: getfield  #65 // org.freedesktop.dbus.connections.transports.AbstractTransport.transportConnection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     30: invokevirtual  #118 // org.freedesktop.dbus.connections.transports.TransportConnection.getWriter:()Lorg/freedesktop/dbus/spi/message/IMessageWriter;
        //     33: ifnull  67 (offset +34)
        //     36: aload_0
        //     37: getfield  #65 // org.freedesktop.dbus.connections.transports.AbstractTransport.transportConnection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     40: invokevirtual  #118 // org.freedesktop.dbus.connections.transports.TransportConnection.getWriter:()Lorg/freedesktop/dbus/spi/message/IMessageWriter;
        //     43: invokeinterface  #131 // org.freedesktop.dbus.spi.message.IMessageWriter.isClosed:()Z, count 1
        //     48: ifne  67 (offset +19)
        //     51: aload_0
        //     52: getfield  #65 // org.freedesktop.dbus.connections.transports.AbstractTransport.transportConnection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     55: invokevirtual  #118 // org.freedesktop.dbus.connections.transports.TransportConnection.getWriter:()Lorg/freedesktop/dbus/spi/message/IMessageWriter;
        //     58: aload_1
        //     59: invokeinterface  #132 // org.freedesktop.dbus.spi.message.IMessageWriter.writeMessage:(Lorg/freedesktop/dbus/messages/Message;)V, count 2
        //     64: goto  77 (offset +13)
        //     67: new  #21 // java.io.IOException
        //     70: dup
        //     71: ldc  #15 // 'OutputWriter already closed or null'
        //     73: invokespecial  #67 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //     76: athrow
        //     77: return
    }

  public Message readMessage() {
        if (transportConnection.getReader() == null) {
            throw new IOException("InputReader already closed or null");
        } else {
            if (transportConnection.getReader().isClosed()) {
                throw new IOException("InputReader already closed or null");
            } else {
                return transportConnection.getReader().readMessage();
            }
        }
    }

  public synchronized boolean isConnected() {
        return transportConnection == null ? 0 : transportConnection.getWriter() == null ? 0 : transportConnection.getWriter().isClosed() ? 0 : transportConnection.getReader() == null ? 0 : !transportConnection.getReader().isClosed();
    }

  protected abstract boolean hasFileDescriptorSupport();

  protected abstract SocketChannel connectImpl();

  protected abstract SocketChannel acceptImpl();

  protected abstract void bindImpl();

  protected abstract void closeTransport();

  protected abstract boolean isBound();

  public final SocketChannel connect() {
        if (!getAddress().isListeningSocket()) {
            transportConnection = internalConnect(() -> connectImpl());
            return transportConnection.getChannel();
        } else {
            throw new InvalidBusAddressException("Cannot connect when using listening address (try use listen() instead)");
        }
    }

  public final boolean isListening() {
        return getAddress().isListeningSocket();
    }

  public final TransportConnection listen() {
        if (getAddress().isListeningSocket()) {
            if (!isBound()) {
                bindImpl();
                runCallback(config.getAfterBindCallback());
            }
            transportConnection = internalConnect(() -> acceptImpl());
            return transportConnection;
        } else {
            throw new InvalidBusAddressException("Cannot listen on client connection address (try use connect() instead)");
        }
    }

  private TransportConnection internalConnect(IThrowingSupplier arg0) {
        runCallback(config.getPreConnectCallback());
        SocketChannel var2 = ((SocketChannel) arg0.get());
        authenticate(var2);
        return createInputOutput(var2);
    }

  public void setPreConnectCallback(Consumer arg0) {
        config.setPreConnectCallback(arg0);
    }

  private void authenticate(SocketChannel arg0) {
        SASL var2 = new SASL(config.getSaslConfig());
        try {
            if (!var2.auth(arg0, this)) {
                throw new AuthenticationException("Failed to authenticate");
            }
        } catch (IOException var3) {
            arg0.close();
            throw var3;
        }
    }

  private TransportConnection createInputOutput(SocketChannel arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_2
        //      2: aconst_null
        //      3: astore_3
        //      4: aconst_null
        //      5: astore  4
        //      7: aload_0
        //      8: getfield  #64 // org.freedesktop.dbus.connections.transports.AbstractTransport.spiLoader:Ljava/util/ServiceLoader;
        //     11: invokevirtual  #83 // java.util.ServiceLoader.iterator:()Ljava/util/Iterator;
        //     14: astore  5
        //     16: aload  5
        //     18: invokeinterface  #126 // java.util.Iterator.hasNext:()Z, count 1
        //     23: ifeq  126 (offset +103)
        //     26: aload  5
        //     28: invokeinterface  #127 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     33: checkcast  #50 // org.freedesktop.dbus.spi.message.ISocketProvider
        //     36: astore  6
        //     38: aload_0
        //     39: getfield  #62 // org.freedesktop.dbus.connections.transports.AbstractTransport.logger:Lorg/slf4j/Logger;
        //     42: ldc  #12 // 'Found ISocketProvider {}'
        //     44: aload  6
        //     46: invokeinterface  #138 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     51: aload  6
        //     53: aload_0
        //     54: invokevirtual  #108 // org.freedesktop.dbus.connections.transports.AbstractTransport.hasFileDescriptorSupport:()Z
        //     57: ifeq  71 (offset +14)
        //     60: aload_0
        //     61: getfield  #61 // org.freedesktop.dbus.connections.transports.AbstractTransport.fileDescriptorSupported:Z
        //     64: ifeq  71 (offset +7)
        //     67: iconst_1
        //     68: goto  72 (offset +4)
        //     71: iconst_0
        //     72: invokeinterface  #135 // org.freedesktop.dbus.spi.message.ISocketProvider.setFileDescriptorSupport:(Z)V, count 2
        //     77: aload  6
        //     79: aload_1
        //     80: invokeinterface  #133 // org.freedesktop.dbus.spi.message.ISocketProvider.createReader:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/spi/message/IMessageReader;, count 2
        //     85: astore_2
        //     86: aload  6
        //     88: aload_1
        //     89: invokeinterface  #134 // org.freedesktop.dbus.spi.message.ISocketProvider.createWriter:(Ljava/nio/channels/SocketChannel;)Lorg/freedesktop/dbus/spi/message/IMessageWriter;, count 2
        //     94: astore_3
        //     95: aload_2
        //     96: ifnull  123 (offset +27)
        //     99: aload_3
        //    100: ifnull  123 (offset +23)
        //    103: aload_0
        //    104: getfield  #62 // org.freedesktop.dbus.connections.transports.AbstractTransport.logger:Lorg/slf4j/Logger;
        //    107: ldc  #16 // 'Using ISocketProvider {}'
        //    109: aload  6
        //    111: invokeinterface  #138 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    116: aload  6
        //    118: astore  4
        //    120: goto  126 (offset +6)
        //    123: goto  16 (offset -107)
        //    126: goto  162 (offset +36)
        //    129: astore  5
        //    131: aload_0
        //    132: getfield  #62 // org.freedesktop.dbus.connections.transports.AbstractTransport.logger:Lorg/slf4j/Logger;
        //    135: ldc  #8 // 'Could not initialize service provider'
        //    137: aload  5
        //    139: invokeinterface  #139 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    144: goto  162 (offset +18)
        //    147: astore  5
        //    149: aload_0
        //    150: getfield  #62 // org.freedesktop.dbus.connections.transports.AbstractTransport.logger:Lorg/slf4j/Logger;
        //    153: ldc  #7 // 'Could not initialize alternative message reader/writer'
        //    155: aload  5
        //    157: invokeinterface  #139 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    162: aload_2
        //    163: ifnull  170 (offset +7)
        //    166: aload_3
        //    167: ifnonnull  204 (offset +37)
        //    170: aload_0
        //    171: getfield  #62 // org.freedesktop.dbus.connections.transports.AbstractTransport.logger:Lorg/slf4j/Logger;
        //    174: ldc  #14 // 'No alternative ISocketProvider found, using built-in implementation'
        //    176: invokeinterface  #137 // org.slf4j.Logger.debug:(Ljava/lang/String;)V, count 2
        //    181: new  #51 // org.freedesktop.dbus.spi.message.InputStreamMessageReader
        //    184: dup
        //    185: aload_1
        //    186: invokespecial  #123 // org.freedesktop.dbus.spi.message.InputStreamMessageReader.<init>:(Ljava/nio/channels/SocketChannel;)V
        //    189: astore_2
        //    190: new  #52 // org.freedesktop.dbus.spi.message.OutputStreamMessageWriter
        //    193: dup
        //    194: aload_1
        //    195: invokespecial  #124 // org.freedesktop.dbus.spi.message.OutputStreamMessageWriter.<init>:(Ljava/nio/channels/SocketChannel;)V
        //    198: astore_3
        //    199: aload_0
        //    200: iconst_0
        //    201: putfield  #61 // org.freedesktop.dbus.connections.transports.AbstractTransport.fileDescriptorSupported:Z
        //    204: new  #43 // org.freedesktop.dbus.connections.transports.TransportConnection
        //    207: dup
        //    208: aload_0
        //    209: getfield  #63 // org.freedesktop.dbus.connections.transports.AbstractTransport.messageFactory:Lorg/freedesktop/dbus/messages/MessageFactory;
        //    212: aload_1
        //    213: aload  4
        //    215: aload_3
        //    216: aload_2
        //    217: invokespecial  #113 // org.freedesktop.dbus.connections.transports.TransportConnection.<init>:(Lorg/freedesktop/dbus/messages/MessageFactory;Ljava/nio/channels/SocketChannel;Lorg/freedesktop/dbus/spi/message/ISocketProvider;Lorg/freedesktop/dbus/spi/message/IMessageWriter;Lorg/freedesktop/dbus/spi/message/IMessageReader;)V
        //    220: areturn
        //       Exception table:
        //         from 7 to 126 target 129 type java.util.ServiceConfigurationError
        //         from 7 to 126 target 147 type java.io.IOException
    }

  private void runCallback(Consumer arg0) {
        Optional.ofNullable(arg0).ifPresent(lp0 -> lambda$runCallback$0(((Consumer) lp0)));
    }

  protected BusAddress getAddress() {
        return address;
    }

  protected Logger getLogger() {
        return logger;
    }

  protected SaslConfig getSaslConfig() {
        return config.getSaslConfig();
    }

  public TransportConnection getTransportConnection() {
        return transportConnection;
    }

  public MessageFactory getMessageFactory() {
        return messageFactory;
    }

  public TransportConfig getTransportConfig() {
        return config;
    }

  public boolean isFileDescriptorSupported() {
        return fileDescriptorSupported;
    }

  public String toString() {
        StringBuilder var1 = new StringBuilder(getClass().getSimpleName());
        var1.append(" [id=").append(transportId).append(", ");
        if (transportConnection != null) {
            var1.append("connectionId=").append(transportConnection.getId()).append(", ");
        }
        var1.append("address=").append(address).append("]");
        return var1.toString();
    }

  public final void close() {
        if (transportConnection != null) {
            transportConnection.close();
            transportConnection = null;
        }
        getLogger().debug("Disconnecting Transport: {}", this);
        closeTransport();
    }

  private void lambda$runCallback$0(Consumer arg0) {
        arg0.accept(this);
    }

}