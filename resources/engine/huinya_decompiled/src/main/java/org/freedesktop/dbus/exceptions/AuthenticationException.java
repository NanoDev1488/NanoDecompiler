// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.exceptions.AuthenticationException
package org.freedesktop.dbus.exceptions;

import java.io.IOException;

public class AuthenticationException extends IOException {

    // ---- поля ----
  private static final long serialVersionUID = 1L;

  public AuthenticationException(String arg0, Throwable arg1) { // было: <init>
        super(arg0, arg1);
    }

  public AuthenticationException(String arg0) { // было: <init>
        super(arg0);
    }

}