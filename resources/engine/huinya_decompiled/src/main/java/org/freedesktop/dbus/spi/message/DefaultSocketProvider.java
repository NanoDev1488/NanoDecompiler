// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.DefaultSocketProvider
package org.freedesktop.dbus.spi.message;

import java.nio.channels.SocketChannel;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.freedesktop.dbus.spi.message.IMessageWriter;
import org.freedesktop.dbus.spi.message.ISocketProvider;
import org.freedesktop.dbus.spi.message.InputStreamMessageReader;
import org.freedesktop.dbus.spi.message.OutputStreamMessageWriter;

final class DefaultSocketProvider implements ISocketProvider {

    // ---- поля ----
  static final ISocketProvider INSTANCE;

    static {
        INSTANCE = new DefaultSocketProvider();
    }

  private DefaultSocketProvider() { // было: <init>
        super();
    }

  public IMessageReader createReader(SocketChannel arg0) {
        return new InputStreamMessageReader(arg0);
    }

  public IMessageWriter createWriter(SocketChannel arg0) {
        return new OutputStreamMessageWriter(arg0);
    }

  public void setFileDescriptorSupport(boolean arg0) {
        // (пустое тело)
    }

  public boolean isFileDescriptorPassingSupported() {
        return false;
    }

}