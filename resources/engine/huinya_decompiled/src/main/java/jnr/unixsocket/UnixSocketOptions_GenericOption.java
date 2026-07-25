// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketOptions.GenericOption
package jnr.unixsocket;

import java.net.SocketOption;

class UnixSocketOptions_GenericOption implements SocketOption {

    // ---- поля ----
  private final String name;
  private final Class type;

   UnixSocketOptions_GenericOption(String arg0, Class arg1) { // было: <init>
        super();
        name = arg0;
        type = arg1;
    }

  public String name() {
        return name;
    }

  public Class type() {
        return type;
    }

  public String toString() {
        return name;
    }

}