// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.IP.StringTable
package jnr.constants.platform.linux.loongarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.loongarch64.IP;

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
        var0.put(IP.IP_RETOPTS, "IP_RETOPTS");
        var0.put(IP.IP_MINTTL, "IP_MINTTL");
        var0.put(IP.IP_RECVTTL, "IP_RECVTTL");
        var0.put(IP.IP_MULTICAST_IF, "IP_MULTICAST_IF");
        var0.put(IP.IP_MULTICAST_TTL, "IP_MULTICAST_TTL");
        var0.put(IP.IP_MULTICAST_LOOP, "IP_MULTICAST_LOOP");
        var0.put(IP.IP_ADD_MEMBERSHIP, "IP_ADD_MEMBERSHIP");
        var0.put(IP.IP_DROP_MEMBERSHIP, "IP_DROP_MEMBERSHIP");
        var0.put(IP.IP_DEFAULT_MULTICAST_TTL, "IP_DEFAULT_MULTICAST_TTL");
        var0.put(IP.IP_DEFAULT_MULTICAST_LOOP, "IP_DEFAULT_MULTICAST_LOOP");
        var0.put(IP.IP_MAX_MEMBERSHIPS, "IP_MAX_MEMBERSHIPS");
        var0.put(IP.IP_ROUTER_ALERT, "IP_ROUTER_ALERT");
        var0.put(IP.IP_PKTINFO, "IP_PKTINFO");
        var0.put(IP.IP_PKTOPTIONS, "IP_PKTOPTIONS");
        var0.put(IP.IP_MTU_DISCOVER, "IP_MTU_DISCOVER");
        var0.put(IP.IP_RECVERR, "IP_RECVERR");
        var0.put(IP.IP_RECVTOS, "IP_RECVTOS");
        var0.put(IP.IP_MTU, "IP_MTU");
        var0.put(IP.IP_FREEBIND, "IP_FREEBIND");
        var0.put(IP.IP_IPSEC_POLICY, "IP_IPSEC_POLICY");
        var0.put(IP.IP_XFRM_POLICY, "IP_XFRM_POLICY");
        var0.put(IP.IP_PASSSEC, "IP_PASSSEC");
        var0.put(IP.IP_TRANSPARENT, "IP_TRANSPARENT");
        var0.put(IP.IP_PMTUDISC_DONT, "IP_PMTUDISC_DONT");
        var0.put(IP.IP_PMTUDISC_WANT, "IP_PMTUDISC_WANT");
        var0.put(IP.IP_PMTUDISC_DO, "IP_PMTUDISC_DO");
        var0.put(IP.IP_UNBLOCK_SOURCE, "IP_UNBLOCK_SOURCE");
        var0.put(IP.IP_BLOCK_SOURCE, "IP_BLOCK_SOURCE");
        var0.put(IP.IP_ADD_SOURCE_MEMBERSHIP, "IP_ADD_SOURCE_MEMBERSHIP");
        var0.put(IP.IP_DROP_SOURCE_MEMBERSHIP, "IP_DROP_SOURCE_MEMBERSHIP");
        var0.put(IP.IP_MSFILTER, "IP_MSFILTER");
        return var0;
    }

}