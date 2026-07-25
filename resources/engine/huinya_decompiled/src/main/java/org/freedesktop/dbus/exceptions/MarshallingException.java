// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.MarshallingException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.NonFatalException;

public class MarshallingException extends DBusException implements NonFatalException {

    // ---- поля ----
  private static final long serialVersionUID = 3065477360622428063L;

  public MarshallingException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public MarshallingException(String arg0) { // было: <init>
        super(arg0);
    }

}