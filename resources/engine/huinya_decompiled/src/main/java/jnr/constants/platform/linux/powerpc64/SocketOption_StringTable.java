// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.SocketOption.StringTable
package jnr.constants.platform.linux.powerpc64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.powerpc64.SocketOption;

final class SocketOption_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   SocketOption_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(SocketOption.class);
        var0.put(SocketOption.SO_DEBUG, "SO_DEBUG");
        var0.put(SocketOption.SO_ACCEPTCONN, "SO_ACCEPTCONN");
        var0.put(SocketOption.SO_REUSEADDR, "SO_REUSEADDR");
        var0.put(SocketOption.SO_KEEPALIVE, "SO_KEEPALIVE");
        var0.put(SocketOption.SO_DONTROUTE, "SO_DONTROUTE");
        var0.put(SocketOption.SO_BROADCAST, "SO_BROADCAST");
        var0.put(SocketOption.SO_LINGER, "SO_LINGER");
        var0.put(SocketOption.SO_OOBINLINE, "SO_OOBINLINE");
        var0.put(SocketOption.SO_REUSEPORT, "SO_REUSEPORT");
        var0.put(SocketOption.SO_TIMESTAMP, "SO_TIMESTAMP");
        var0.put(SocketOption.SO_SNDBUF, "SO_SNDBUF");
        var0.put(SocketOption.SO_RCVBUF, "SO_RCVBUF");
        var0.put(SocketOption.SO_SNDLOWAT, "SO_SNDLOWAT");
        var0.put(SocketOption.SO_RCVLOWAT, "SO_RCVLOWAT");
        var0.put(SocketOption.SO_SNDTIMEO, "SO_SNDTIMEO");
        var0.put(SocketOption.SO_RCVTIMEO, "SO_RCVTIMEO");
        var0.put(SocketOption.SO_ERROR, "SO_ERROR");
        var0.put(SocketOption.SO_TYPE, "SO_TYPE");
        var0.put(SocketOption.SO_ATTACH_FILTER, "SO_ATTACH_FILTER");
        var0.put(SocketOption.SO_BINDTODEVICE, "SO_BINDTODEVICE");
        var0.put(SocketOption.SO_DETACH_FILTER, "SO_DETACH_FILTER");
        var0.put(SocketOption.SO_NO_CHECK, "SO_NO_CHECK");
        var0.put(SocketOption.SO_PASSCRED, "SO_PASSCRED");
        var0.put(SocketOption.SO_PEERCRED, "SO_PEERCRED");
        var0.put(SocketOption.SO_PEERNAME, "SO_PEERNAME");
        var0.put(SocketOption.SO_PRIORITY, "SO_PRIORITY");
        var0.put(SocketOption.SO_SECURITY_AUTHENTICATION, "SO_SECURITY_AUTHENTICATION");
        var0.put(SocketOption.SO_SECURITY_ENCRYPTION_NETWORK, "SO_SECURITY_ENCRYPTION_NETWORK");
        var0.put(SocketOption.SO_SECURITY_ENCRYPTION_TRANSPORT, "SO_SECURITY_ENCRYPTION_TRANSPORT");
        return var0;
    }

}