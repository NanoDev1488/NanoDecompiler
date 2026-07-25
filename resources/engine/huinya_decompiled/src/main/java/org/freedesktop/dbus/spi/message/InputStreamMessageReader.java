// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.InputStreamMessageReader
package org.freedesktop.dbus.spi.message;

import java.nio.channels.SocketChannel;
import java.util.List;
import org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader;
import org.freedesktop.dbus.spi.message.DefaultSocketProvider;

public class InputStreamMessageReader extends AbstractInputStreamMessageReader {

  public InputStreamMessageReader(SocketChannel arg0) { // было: <init>
        super(arg0, DefaultSocketProvider.INSTANCE);
    }

  protected List readFileDescriptors(SocketChannel arg0) {
        return null;
    }

}