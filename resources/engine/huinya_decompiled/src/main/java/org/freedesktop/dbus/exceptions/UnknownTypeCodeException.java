// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.UnknownTypeCodeException
package org.freedesktop.dbus.exceptions;

import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.NonFatalException;

public class UnknownTypeCodeException extends DBusException implements NonFatalException {

    // ---- поля ----
  private static final long serialVersionUID = -4688075573912580455L;

  public UnknownTypeCodeException(byte arg0) { // было: <init>
        super("Not a valid D-Bus type code: " + arg0);
    }

}