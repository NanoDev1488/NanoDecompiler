// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.InternalMessageException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.NonFatalException;

public class InternalMessageException extends DBusExecutionException implements NonFatalException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public InternalMessageException(String arg0) { // было: <init>
        super(arg0);
    }

}