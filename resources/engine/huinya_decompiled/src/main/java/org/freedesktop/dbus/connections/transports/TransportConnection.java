// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.transports.TransportConnection
package org.freedesktop.dbus.connections.transports;

import java.io.Closeable;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicLong;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.freedesktop.dbus.spi.message.IMessageWriter;
import org.freedesktop.dbus.spi.message.ISocketProvider;

public class TransportConnection implements Closeable {

    // ---- поля ----
  private static final AtomicLong TRANSPORT_ID_GENERATOR;
  private final long id;
  private final SocketChannel channel;
  private final IMessageWriter writer;
  private final IMessageReader reader;
  private final ISocketProvider socketProviderImpl;
  private final MessageFactory messageFactory;

    static {
        TRANSPORT_ID_GENERATOR = new AtomicLong(0L);
    }

  public TransportConnection(MessageFactory arg0, SocketChannel arg1, ISocketProvider arg2, IMessageWriter arg3, IMessageReader arg4) { // было: <init>
        super();
        id = TRANSPORT_ID_GENERATOR.incrementAndGet();
        messageFactory = arg0;
        channel = arg1;
        socketProviderImpl = arg2;
        writer = arg3;
        reader = arg4;
    }

  public SocketChannel getChannel() {
        return channel;
    }

  public IMessageWriter getWriter() {
        return writer;
    }

  public IMessageReader getReader() {
        return reader;
    }

  public ISocketProvider getSocketProviderImpl() {
        return socketProviderImpl;
    }

  public long getId() {
        return id;
    }

  public MessageFactory getMessageFactory() {
        return messageFactory;
    }

  public String toString() {
        return getClass().getSimpleName() + " [id=" + id + ", channel=" + String.valueOf(channel) + ", writer=" + String.valueOf(writer) + ", reader=" + String.valueOf(reader) + "]";
    }

  public void close() {
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
        if (channel != null) {
            channel.close();
        }
    }

}