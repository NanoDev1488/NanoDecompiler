// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.SocketOption.StringTable
package jnr.constants.platform.linux;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.SocketOption;

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
        var0.put(SocketOption.SO_SNDBUFFORCE, "SO_SNDBUFFORCE");
        var0.put(SocketOption.SO_RCVBUFFORCE, "SO_RCVBUFFORCE");
        var0.put(SocketOption.SO_GET_FILTER, "SO_GET_FILTER");
        var0.put(SocketOption.SO_TIMESTAMPNS, "SO_TIMESTAMPNS");
        var0.put(SocketOption.SO_PEERSEC, "SO_PEERSEC");
        var0.put(SocketOption.SO_PASSSEC, "SO_PASSSEC");
        var0.put(SocketOption.SO_MARK, "SO_MARK");
        var0.put(SocketOption.SO_TIMESTAMPING, "SO_TIMESTAMPING");
        var0.put(SocketOption.SO_PROTOCOL, "SO_PROTOCOL");
        var0.put(SocketOption.SO_DOMAIN, "SO_DOMAIN");
        var0.put(SocketOption.SO_RXQ_OVFL, "SO_RXQ_OVFL");
        var0.put(SocketOption.SO_WIFI_STATUS, "SO_WIFI_STATUS");
        var0.put(SocketOption.SO_PEEK_OFF, "SO_PEEK_OFF");
        var0.put(SocketOption.SO_NOFCS, "SO_NOFCS");
        var0.put(SocketOption.SO_LOCK_FILTER, "SO_LOCK_FILTER");
        var0.put(SocketOption.SO_SELECT_ERR_QUEUE, "SO_SELECT_ERR_QUEUE");
        var0.put(SocketOption.SO_BUSY_POLL, "SO_BUSY_POLL");
        var0.put(SocketOption.SO_MAX_PACING_RATE, "SO_MAX_PACING_RATE");
        var0.put(SocketOption.SO_BPF_EXTENSIONS, "SO_BPF_EXTENSIONS");
        var0.put(SocketOption.SO_SECURITY_AUTHENTICATION, "SO_SECURITY_AUTHENTICATION");
        var0.put(SocketOption.SO_SECURITY_ENCRYPTION_NETWORK, "SO_SECURITY_ENCRYPTION_NETWORK");
        var0.put(SocketOption.SO_SECURITY_ENCRYPTION_TRANSPORT, "SO_SECURITY_ENCRYPTION_TRANSPORT");
        return var0;
    }

}