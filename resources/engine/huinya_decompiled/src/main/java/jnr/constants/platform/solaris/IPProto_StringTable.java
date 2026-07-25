// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.IPProto.StringTable
package jnr.constants.platform.solaris;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.solaris.IPProto;

final class IPProto_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   IPProto_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(IPProto.class);
        var0.put(IPProto.IPPROTO_IP, "IPPROTO_IP");
        var0.put(IPProto.IPPROTO_HOPOPTS, "IPPROTO_HOPOPTS");
        var0.put(IPProto.IPPROTO_ICMP, "IPPROTO_ICMP");
        var0.put(IPProto.IPPROTO_IGMP, "IPPROTO_IGMP");
        var0.put(IPProto.IPPROTO_TCP, "IPPROTO_TCP");
        var0.put(IPProto.IPPROTO_EGP, "IPPROTO_EGP");
        var0.put(IPProto.IPPROTO_PUP, "IPPROTO_PUP");
        var0.put(IPProto.IPPROTO_UDP, "IPPROTO_UDP");
        var0.put(IPProto.IPPROTO_IDP, "IPPROTO_IDP");
        var0.put(IPProto.IPPROTO_IPV6, "IPPROTO_IPV6");
        var0.put(IPProto.IPPROTO_ROUTING, "IPPROTO_ROUTING");
        var0.put(IPProto.IPPROTO_FRAGMENT, "IPPROTO_FRAGMENT");
        var0.put(IPProto.IPPROTO_RSVP, "IPPROTO_RSVP");
        var0.put(IPProto.IPPROTO_ESP, "IPPROTO_ESP");
        var0.put(IPProto.IPPROTO_AH, "IPPROTO_AH");
        var0.put(IPProto.IPPROTO_ICMPV6, "IPPROTO_ICMPV6");
        var0.put(IPProto.IPPROTO_NONE, "IPPROTO_NONE");
        var0.put(IPProto.IPPROTO_DSTOPTS, "IPPROTO_DSTOPTS");
        var0.put(IPProto.IPPROTO_ENCAP, "IPPROTO_ENCAP");
        var0.put(IPProto.IPPROTO_PIM, "IPPROTO_PIM");
        var0.put(IPProto.IPPROTO_SCTP, "IPPROTO_SCTP");
        var0.put(IPProto.IPPROTO_RAW, "IPPROTO_RAW");
        var0.put(IPProto.IPPROTO_MAX, "IPPROTO_MAX");
        return var0;
    }

}