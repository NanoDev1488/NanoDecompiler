// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.IMessageReader
package org.freedesktop.dbus.spi.message;

import java.io.Closeable;
import org.freedesktop.dbus.messages.Message;

public interface IMessageReader extends Closeable {

  public abstract boolean isClosed();

  public abstract Message readMessage();

}