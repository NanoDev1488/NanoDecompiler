// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.InvalidObjectPathException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;

public class InvalidObjectPathException extends DBusException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public InvalidObjectPathException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public InvalidObjectPathException(String arg0) { // было: <init>
        super("Invalid object path: " + arg0);
    }

  public InvalidObjectPathException(Throwable arg0) { // было: <init>
        super(arg0);
    }

  public InvalidObjectPathException() { // было: <init>
        super(((String) null));
    }

}