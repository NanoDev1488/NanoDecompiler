// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.InvalidBusNameException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;

public class InvalidBusNameException extends DBusException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public InvalidBusNameException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public InvalidBusNameException(String arg0) { // было: <init>
        super("Invalid bus name: " + arg0);
    }

  public InvalidBusNameException(Throwable arg0) { // было: <init>
        super(arg0);
    }

  public InvalidBusNameException() { // было: <init>
        super(((String) null));
    }

}