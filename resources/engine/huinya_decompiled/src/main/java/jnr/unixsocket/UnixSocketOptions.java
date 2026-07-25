// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketOptions
package jnr.unixsocket;

import java.net.SocketOption;
import jnr.unixsocket.Credentials;
import jnr.unixsocket.UnixSocketOptions_GenericOption;

public final class UnixSocketOptions {

    // ---- поля ----
  public static final SocketOption SO_SNDBUF;
  public static final SocketOption SO_SNDTIMEO;
  public static final SocketOption SO_RCVBUF;
  public static final SocketOption SO_RCVTIMEO;
  public static final SocketOption SO_KEEPALIVE;
  public static final SocketOption SO_PEERCRED;
  public static final SocketOption SO_PASSCRED;

    static {
        SO_SNDBUF = new UnixSocketOptions_GenericOption("SO_SNDBUF", Integer.class);
        SO_SNDTIMEO = new UnixSocketOptions_GenericOption("SO_SNDTIMEO", Integer.class);
        SO_RCVBUF = new UnixSocketOptions_GenericOption("SO_RCVBUF", Integer.class);
        SO_RCVTIMEO = new UnixSocketOptions_GenericOption("SO_RCVTIMEO", Integer.class);
        SO_KEEPALIVE = new UnixSocketOptions_GenericOption("SO_KEEPALIVE", Boolean.class);
        SO_PEERCRED = new UnixSocketOptions_GenericOption("SO_PEERCRED", Credentials.class);
        SO_PASSCRED = new UnixSocketOptions_GenericOption("SO_PASSCRED", Boolean.class);
    }

  public UnixSocketOptions() { // было: <init>
        super();
    }

}