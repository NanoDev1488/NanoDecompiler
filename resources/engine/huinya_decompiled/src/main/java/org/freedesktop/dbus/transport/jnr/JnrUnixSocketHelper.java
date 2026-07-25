// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.transport.jnr.JnrUnixSocketHelper
package org.freedesktop.dbus.transport.jnr;

import java.nio.channels.SocketChannel;
import jnr.unixsocket.Credentials;
import jnr.unixsocket.UnixSocketOptions;

public final class JnrUnixSocketHelper {

  private JnrUnixSocketHelper() { // было: <init>
        super();
    }

  public static int getUid(SocketChannel arg0) {
        if (arg0 != null) {
            Credentials var1 = ((Credentials) arg0.getOption(UnixSocketOptions.SO_PEERCRED));
            return var1.getUid();
        } else {
            return -1;
        }
    }

}