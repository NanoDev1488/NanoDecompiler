// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixDatagramChannel.DefaultOptionsHolder
package jnr.unixsocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jnr.unixsocket.UnixSocketOptions;

class UnixDatagramChannel_DefaultOptionsHolder {

    // ---- поля ----
  static final Set defaultOptions;

    static {
        defaultOptions = defaultOptions();
    }

  private UnixDatagramChannel_DefaultOptionsHolder() { // было: <init>
        super();
    }

  private static Set defaultOptions() {
        HashSet var0 = new HashSet(5);
        var0.add(UnixSocketOptions.SO_SNDBUF);
        var0.add(UnixSocketOptions.SO_SNDTIMEO);
        var0.add(UnixSocketOptions.SO_RCVBUF);
        var0.add(UnixSocketOptions.SO_RCVTIMEO);
        var0.add(UnixSocketOptions.SO_PEERCRED);
        return Collections.unmodifiableSet(var0);
    }

}