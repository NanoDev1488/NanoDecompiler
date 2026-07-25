// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.MessageTypeException
package org.freedesktop.dbus.exceptions;

import java.io.IOException;
import org.freedesktop.dbus.interfaces.NonFatalException;

public class MessageTypeException extends IOException implements NonFatalException {

    // ---- поля ----
  private static final long serialVersionUID = 935695242304001622L;

  public MessageTypeException(String arg0) { // было: <init>
        super(arg0);
    }

}