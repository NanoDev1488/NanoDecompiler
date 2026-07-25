// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.SocketClosedException
package org.freedesktop.dbus.exceptions;

import java.io.IOException;

public class SocketClosedException extends IOException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public SocketClosedException() { // было: <init>
        super();
    }

  public SocketClosedException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public SocketClosedException(String arg0) { // было: <init>
        super(arg0);
    }

  public SocketClosedException(Throwable arg0) { // было: <init>
        super(arg0);
    }

}