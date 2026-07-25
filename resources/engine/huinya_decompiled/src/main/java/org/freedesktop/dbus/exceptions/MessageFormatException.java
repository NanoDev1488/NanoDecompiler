// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.MessageFormatException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.NonFatalException;

public class MessageFormatException extends DBusException implements NonFatalException {

    // ---- поля ----
  private static final long serialVersionUID = -4806500517504320924L;

  public MessageFormatException(String arg0) { // было: <init>
        super(arg0);
    }

}