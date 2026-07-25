// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.ProtocolFamily.StringTable
package jnr.constants.platform.linux.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.aarch64.ProtocolFamily;

final class ProtocolFamily_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   ProtocolFamily_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(ProtocolFamily.class);
        var0.put(ProtocolFamily.PF_UNSPEC, "PF_UNSPEC");
        var0.put(ProtocolFamily.PF_LOCAL, "PF_LOCAL");
        var0.put(ProtocolFamily.PF_UNIX, "PF_UNIX");
        var0.put(ProtocolFamily.PF_INET, "PF_INET");
        var0.put(ProtocolFamily.PF_SNA, "PF_SNA");
        var0.put(ProtocolFamily.PF_DECnet, "PF_DECnet");
        var0.put(ProtocolFamily.PF_APPLETALK, "PF_APPLETALK");
        var0.put(ProtocolFamily.PF_ROUTE, "PF_ROUTE");
        var0.put(ProtocolFamily.PF_IPX, "PF_IPX");
        var0.put(ProtocolFamily.PF_ISDN, "PF_ISDN");
        var0.put(ProtocolFamily.PF_KEY, "PF_KEY");
        var0.put(ProtocolFamily.PF_INET6, "PF_INET6");
        var0.put(ProtocolFamily.PF_NETLINK, "PF_NETLINK");
        var0.put(ProtocolFamily.PF_RDS, "PF_RDS");
        var0.put(ProtocolFamily.PF_PPPOX, "PF_PPPOX");
        var0.put(ProtocolFamily.PF_LLC, "PF_LLC");
        var0.put(ProtocolFamily.PF_IB, "PF_IB");
        var0.put(ProtocolFamily.PF_MPLS, "PF_MPLS");
        var0.put(ProtocolFamily.PF_CAN, "PF_CAN");
        var0.put(ProtocolFamily.PF_TIPC, "PF_TIPC");
        var0.put(ProtocolFamily.PF_BLUETOOTH, "PF_BLUETOOTH");
        var0.put(ProtocolFamily.PF_ALG, "PF_ALG");
        var0.put(ProtocolFamily.PF_VSOCK, "PF_VSOCK");
        var0.put(ProtocolFamily.PF_KCM, "PF_KCM");
        var0.put(ProtocolFamily.PF_XDP, "PF_XDP");
        var0.put(ProtocolFamily.PF_MAX, "PF_MAX");
        return var0;
    }

}