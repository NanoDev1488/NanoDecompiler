// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.Sock.StringTable
package jnr.constants.platform.dragonflybsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.dragonflybsd.Sock;

final class Sock_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Sock_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Sock.class);
        var0.put(Sock.SOCK_STREAM, "SOCK_STREAM");
        var0.put(Sock.SOCK_DGRAM, "SOCK_DGRAM");
        var0.put(Sock.SOCK_RAW, "SOCK_RAW");
        var0.put(Sock.SOCK_RDM, "SOCK_RDM");
        var0.put(Sock.SOCK_SEQPACKET, "SOCK_SEQPACKET");
        var0.put(Sock.SOCK_NONBLOCK, "SOCK_NONBLOCK");
        var0.put(Sock.SOCK_CLOEXEC, "SOCK_CLOEXEC");
        var0.put(Sock.SOCK_MAXADDRLEN, "SOCK_MAXADDRLEN");
        return var0;
    }

}