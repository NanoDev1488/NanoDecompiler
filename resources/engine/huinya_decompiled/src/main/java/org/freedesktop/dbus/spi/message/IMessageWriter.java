// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.IMessageWriter
package org.freedesktop.dbus.spi.message;

import java.io.Closeable;
import org.freedesktop.dbus.messages.Message;

public interface IMessageWriter extends Closeable {

  public abstract void writeMessage(Message arg0);

  public abstract boolean isClosed();

}