// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketChannel.DefaultOptionsHolder
package jnr.unixsocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jnr.unixsocket.UnixSocketOptions;

class UnixSocketChannel_DefaultOptionsHolder {

    // ---- поля ----
  static final Set defaultOptions;

    static {
        defaultOptions = defaultOptions();
    }

  private UnixSocketChannel_DefaultOptionsHolder() { // было: <init>
        super();
    }

  private static Set defaultOptions() {
        HashSet var0 = new HashSet(5);
        var0.add(UnixSocketOptions.SO_SNDBUF);
        var0.add(UnixSocketOptions.SO_SNDTIMEO);
        var0.add(UnixSocketOptions.SO_RCVBUF);
        var0.add(UnixSocketOptions.SO_RCVTIMEO);
        var0.add(UnixSocketOptions.SO_PEERCRED);
        var0.add(UnixSocketOptions.SO_KEEPALIVE);
        var0.add(UnixSocketOptions.SO_PASSCRED);
        return Collections.unmodifiableSet(var0);
    }

}