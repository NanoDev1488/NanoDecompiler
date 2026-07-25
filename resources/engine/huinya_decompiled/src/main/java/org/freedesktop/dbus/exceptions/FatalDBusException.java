// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.FatalDBusException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.FatalException;

public class FatalDBusException extends DBusException implements FatalException {

    // ---- поля ----
  private static final long serialVersionUID = -3461692622913793488L;

  public FatalDBusException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public FatalDBusException(Throwable arg0) { // было: <init>
        super(arg0);
    }

  public FatalDBusException(String arg0) { // было: <init>
        super(arg0);
    }

}