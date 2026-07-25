// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.IPv6.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.IPv6;

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
        var0.put(IPv6.IPV6_DSTOPTS, "IPV6_DSTOPTS");
        var0.put(IPv6.IPV6_HOPLIMIT, "IPV6_HOPLIMIT");
        var0.put(IPv6.IPV6_HOPOPTS, "IPV6_HOPOPTS");
        var0.put(IPv6.IPV6_NEXTHOP, "IPV6_NEXTHOP");
        var0.put(IPv6.IPV6_PATHMTU, "IPV6_PATHMTU");
        var0.put(IPv6.IPV6_PKTINFO, "IPV6_PKTINFO");
        var0.put(IPv6.IPV6_RECVDSTOPTS, "IPV6_RECVDSTOPTS");
        var0.put(IPv6.IPV6_RECVHOPLIMIT, "IPV6_RECVHOPLIMIT");
        var0.put(IPv6.IPV6_RECVHOPOPTS, "IPV6_RECVHOPOPTS");
        var0.put(IPv6.IPV6_RECVPKTINFO, "IPV6_RECVPKTINFO");
        var0.put(IPv6.IPV6_RECVRTHDR, "IPV6_RECVRTHDR");
        var0.put(IPv6.IPV6_RECVTCLASS, "IPV6_RECVTCLASS");
        var0.put(IPv6.IPV6_RTHDR, "IPV6_RTHDR");
        var0.put(IPv6.IPV6_RTHDRDSTOPTS, "IPV6_RTHDRDSTOPTS");
        var0.put(IPv6.IPV6_RTHDR_TYPE_0, "IPV6_RTHDR_TYPE_0");
        var0.put(IPv6.IPV6_RECVPATHMTU, "IPV6_RECVPATHMTU");
        var0.put(IPv6.IPV6_TCLASS, "IPV6_TCLASS");
        var0.put(IPv6.IPV6_USE_MIN_MTU, "IPV6_USE_MIN_MTU");
        return var0;
    }

}