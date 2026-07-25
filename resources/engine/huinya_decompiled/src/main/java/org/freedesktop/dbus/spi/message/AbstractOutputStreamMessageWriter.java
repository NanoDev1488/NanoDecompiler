// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter
package org.freedesktop.dbus.spi.message;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Objects;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.spi.message.IMessageWriter;
import org.freedesktop.dbus.spi.message.ISocketProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractOutputStreamMessageWriter implements IMessageWriter {

    // ---- поля ----
  private final Logger logger;
  private final SocketChannel outputChannel;
  private final ISocketProvider socketProviderImpl;

  protected AbstractOutputStreamMessageWriter(SocketChannel arg0, ISocketProvider arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        outputChannel = ((SocketChannel) Objects.requireNonNull(arg0, "SocketChannel required"));
        socketProviderImpl = ((ISocketProvider) Objects.requireNonNull(arg1, "ISocketProvider implementation required"));
    }

  public final void writeMessage(Message arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #27 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.logger:Lorg/slf4j/Logger;
        //      4: ldc  #3 // '<= {}'
        //      6: aload_1
        //      7: invokeinterface  #47 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     12: aconst_null
        //     13: aload_1
        //     14: if_acmpne  18 (offset +4)
        //     17: return
        //     18: aconst_null
        //     19: aload_1
        //     20: invokevirtual  #41 // org.freedesktop.dbus.messages.Message.getWireData:()[[B
        //     23: if_acmpne  39 (offset +16)
        //     26: aload_0
        //     27: getfield  #27 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.logger:Lorg/slf4j/Logger;
        //     30: ldc  #8 // 'Message {} wire-data was null!'
        //     32: aload_1
        //     33: invokeinterface  #50 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     38: return
        //     39: aload_0
        //     40: getfield  #29 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.socketProviderImpl:Lorg/freedesktop/dbus/spi/message/ISocketProvider;
        //     43: invokeinterface  #45 // org.freedesktop.dbus.spi.message.ISocketProvider.isFileDescriptorPassingSupported:()Z, count 1
        //     48: ifeq  63 (offset +15)
        //     51: aload_0
        //     52: aload_0
        //     53: getfield  #28 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.outputChannel:Ljava/nio/channels/SocketChannel;
        //     56: aload_1
        //     57: invokevirtual  #40 // org.freedesktop.dbus.messages.Message.getFiledescriptors:()Ljava/util/List;
        //     60: invokevirtual  #42 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.writeFileDescriptors:(Ljava/nio/channels/SocketChannel;Ljava/util/List;)V
        //     63: aload_1
        //     64: invokevirtual  #41 // org.freedesktop.dbus.messages.Message.getWireData:()[[B
        //     67: astore_2
        //     68: aload_2
        //     69: arraylength
        //     70: istore_3
        //     71: iconst_0
        //     72: istore  4
        //     74: iload  4
        //     76: iload_3
        //     77: if_icmpge  153 (offset +76)
        //     80: aload_2
        //     81: iload  4
        //     83: aaload
        //     84: astore  5
        //     86: aload_0
        //     87: getfield  #27 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.logger:Lorg/slf4j/Logger;
        //     90: invokeinterface  #48 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //     95: ifeq  125 (offset +30)
        //     98: aload_0
        //     99: getfield  #27 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.logger:Lorg/slf4j/Logger;
        //    102: ldc  #10 // '{}'
        //    104: aconst_null
        //    105: aload  5
        //    107: if_acmpne  115 (offset +8)
        //    110: ldc  #2 // '(buffer was null)'
        //    112: goto  120 (offset +8)
        //    115: aload  5
        //    117: invokestatic  #43 // org.freedesktop.dbus.utils.Hexdump.format:([B)Ljava/lang/String;
        //    120: invokeinterface  #49 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    125: aconst_null
        //    126: aload  5
        //    128: if_acmpne  134 (offset +6)
        //    131: goto  153 (offset +22)
        //    134: aload_0
        //    135: getfield  #28 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.outputChannel:Ljava/nio/channels/SocketChannel;
        //    138: aload  5
        //    140: invokestatic  #35 // java.nio.ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //    143: invokevirtual  #38 // java.nio.channels.SocketChannel.write:(Ljava/nio/ByteBuffer;)I
        //    146: pop
        //    147: iinc  4, 1
        //    150: goto  74 (offset -76)
        //    153: aload_0
        //    154: getfield  #27 // org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter.logger:Lorg/slf4j/Logger;
        //    157: ldc  #7 // 'Message sent: {}'
        //    159: aload_1
        //    160: invokeinterface  #49 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    165: return
    }

  protected abstract void writeFileDescriptors(SocketChannel arg0, List arg1);

  protected Logger getLogger() {
        return logger;
    }

  protected ISocketProvider getSocketProviderImpl() {
        return socketProviderImpl;
    }

  public void close() {
        logger.debug("Closing Message Writer");
        if (outputChannel.isOpen()) {
            outputChannel.close();
            logger.debug("Message Writer closed");
        }
    }

  public boolean isClosed() {
        return !outputChannel.isOpen();
    }

  public String toString() {
        return getClass().getSimpleName() + " [outputChannel=" + String.valueOf(outputChannel) + ", socketProviderImpl=" + String.valueOf(socketProviderImpl) + "]";
    }

}