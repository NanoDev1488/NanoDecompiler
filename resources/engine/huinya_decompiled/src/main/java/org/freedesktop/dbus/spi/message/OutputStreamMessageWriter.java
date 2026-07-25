// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.OutputStreamMessageWriter
package org.freedesktop.dbus.spi.message;

import java.nio.channels.SocketChannel;
import java.util.List;
import org.freedesktop.dbus.spi.message.AbstractOutputStreamMessageWriter;
import org.freedesktop.dbus.spi.message.DefaultSocketProvider;

public class OutputStreamMessageWriter extends AbstractOutputStreamMessageWriter {

  public OutputStreamMessageWriter(SocketChannel arg0) { // было: <init>
        super(arg0, DefaultSocketProvider.INSTANCE);
    }

  protected void writeFileDescriptors(SocketChannel arg0, List arg1) {
        // (пустое тело)
    }

}