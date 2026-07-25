// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.AddressFamily.StringTable
package jnr.constants.platform.linux.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.aarch64.AddressFamily;

final class AddressFamily_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   AddressFamily_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(AddressFamily.class);
        var0.put(AddressFamily.AF_UNSPEC, "AF_UNSPEC");
        var0.put(AddressFamily.AF_LOCAL, "AF_LOCAL");
        var0.put(AddressFamily.AF_UNIX, "AF_UNIX");
        var0.put(AddressFamily.AF_INET, "AF_INET");
        var0.put(AddressFamily.AF_SNA, "AF_SNA");
        var0.put(AddressFamily.AF_DECnet, "AF_DECnet");
        var0.put(AddressFamily.AF_APPLETALK, "AF_APPLETALK");
        var0.put(AddressFamily.AF_ROUTE, "AF_ROUTE");
        var0.put(AddressFamily.AF_IPX, "AF_IPX");
        var0.put(AddressFamily.AF_ISDN, "AF_ISDN");
        var0.put(AddressFamily.AF_INET6, "AF_INET6");
        var0.put(AddressFamily.AF_AX25, "AF_AX25");
        var0.put(AddressFamily.AF_KEY, "AF_KEY");
        var0.put(AddressFamily.AF_NETLINK, "AF_NETLINK");
        var0.put(AddressFamily.AF_RDS, "AF_RDS");
        var0.put(AddressFamily.AF_PPPOX, "AF_PPPOX");
        var0.put(AddressFamily.AF_LLC, "AF_LLC");
        var0.put(AddressFamily.AF_IB, "AF_IB");
        var0.put(AddressFamily.AF_MPLS, "AF_MPLS");
        var0.put(AddressFamily.AF_CAN, "AF_CAN");
        var0.put(AddressFamily.AF_TIPC, "AF_TIPC");
        var0.put(AddressFamily.AF_BLUETOOTH, "AF_BLUETOOTH");
        var0.put(AddressFamily.AF_ALG, "AF_ALG");
        var0.put(AddressFamily.AF_VSOCK, "AF_VSOCK");
        var0.put(AddressFamily.AF_KCM, "AF_KCM");
        var0.put(AddressFamily.AF_XDP, "AF_XDP");
        var0.put(AddressFamily.AF_MAX, "AF_MAX");
        return var0;
    }

}