// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.MessageProtocolVersionException
package org.freedesktop.dbus.exceptions;

import java.io.IOException;
import org.freedesktop.dbus.interfaces.FatalException;

public class MessageProtocolVersionException extends IOException implements FatalException {

    // ---- поля ----
  private static final long serialVersionUID = 3107039118803575407L;

  public MessageProtocolVersionException(String arg0) { // было: <init>
        super(arg0);
    }

}