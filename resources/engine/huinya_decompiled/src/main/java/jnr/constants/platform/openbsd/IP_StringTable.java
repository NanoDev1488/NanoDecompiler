// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.IP.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.IP;

final class IP_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   IP_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(IP.class);
        var0.put(IP.IP_OPTIONS, "IP_OPTIONS");
        var0.put(IP.IP_HDRINCL, "IP_HDRINCL");
        var0.put(IP.IP_TOS, "IP_TOS");
        var0.put(IP.IP_TTL, "IP_TTL");
        var0.put(IP.IP_RECVOPTS, "IP_RECVOPTS");
        var0.put(IP.IP_RECVRETOPTS, "IP_RECVRETOPTS");
        var0.put(IP.IP_RECVDSTADDR, "IP_RECVDSTADDR");
        var0.put(IP.IP_RETOPTS, "IP_RETOPTS");
        var0.put(IP.IP_MINTTL, "IP_MINTTL");
        var0.put(IP.IP_SENDSRCADDR, "IP_SENDSRCADDR");
        var0.put(IP.IP_RECVTTL, "IP_RECVTTL");
        var0.put(IP.IP_RECVIF, "IP_RECVIF");
        var0.put(IP.IP_PORTRANGE, "IP_PORTRANGE");
        var0.put(IP.IP_MULTICAST_IF, "IP_MULTICAST_IF");
        var0.put(IP.IP_MULTICAST_TTL, "IP_MULTICAST_TTL");
        var0.put(IP.IP_MULTICAST_LOOP, "IP_MULTICAST_LOOP");
        var0.put(IP.IP_ADD_MEMBERSHIP, "IP_ADD_MEMBERSHIP");
        var0.put(IP.IP_DROP_MEMBERSHIP, "IP_DROP_MEMBERSHIP");
        var0.put(IP.IP_DEFAULT_MULTICAST_TTL, "IP_DEFAULT_MULTICAST_TTL");
        var0.put(IP.IP_DEFAULT_MULTICAST_LOOP, "IP_DEFAULT_MULTICAST_LOOP");
        var0.put(IP.IP_MAX_MEMBERSHIPS, "IP_MAX_MEMBERSHIPS");
        return var0;
    }

}