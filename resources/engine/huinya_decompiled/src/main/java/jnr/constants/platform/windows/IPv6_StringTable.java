// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.IPv6.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.IPv6;

final class IPv6_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   IPv6_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(IPv6.class);
        var0.put(IPv6.IPV6_JOIN_GROUP, "IPV6_JOIN_GROUP");
        var0.put(IPv6.IPV6_LEAVE_GROUP, "IPV6_LEAVE_GROUP");
        var0.put(IPv6.IPV6_MULTICAST_HOPS, "IPV6_MULTICAST_HOPS");
        var0.put(IPv6.IPV6_MULTICAST_IF, "IPV6_MULTICAST_IF");
        var0.put(IPv6.IPV6_MULTICAST_LOOP, "IPV6_MULTICAST_LOOP");
        var0.put(IPv6.IPV6_UNICAST_HOPS, "IPV6_UNICAST_HOPS");
        var0.put(IPv6.IPV6_V6ONLY, "IPV6_V6ONLY");
        var0.put(IPv6.IPV6_CHECKSUM, "IPV6_CHECKSUM");
        var0.put(IPv6.IPV6_DONTFRAG, "IPV6_DONTFRAG");
        var0.put(IPv6.IPV6_HOPLIMIT, "IPV6_HOPLIMIT");
        var0.put(IPv6.IPV6_HOPOPTS, "IPV6_HOPOPTS");
        var0.put(IPv6.IPV6_PKTINFO, "IPV6_PKTINFO");
        var0.put(IPv6.IPV6_RECVRTHDR, "IPV6_RECVRTHDR");
        var0.put(IPv6.IPV6_RECVTCLASS, "IPV6_RECVTCLASS");
        var0.put(IPv6.IPV6_RTHDR, "IPV6_RTHDR");
        var0.put(IPv6.IPV6_TCLASS, "IPV6_TCLASS");
        return var0;
    }

}