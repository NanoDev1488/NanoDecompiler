// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.ISocketProvider
package org.freedesktop.dbus.spi.message;

import java.io.FileDescriptor;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.freedesktop.dbus.spi.message.IMessageWriter;

public interface ISocketProvider {

  public abstract IMessageReader createReader(SocketChannel arg0);

  public abstract IMessageWriter createWriter(SocketChannel arg0);

  public abstract void setFileDescriptorSupport(boolean arg0);

  public abstract boolean isFileDescriptorPassingSupported();

  public Optional getFileDescriptorValue(FileDescriptor arg0) {
        return Optional.empty();
    }

  public Optional createFileDescriptor(int arg0) {
        return Optional.empty();
    }

}