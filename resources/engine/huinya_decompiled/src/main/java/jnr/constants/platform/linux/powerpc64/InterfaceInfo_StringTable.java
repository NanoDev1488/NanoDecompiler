// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.InterfaceInfo.StringTable
package jnr.constants.platform.linux.powerpc64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.powerpc64.InterfaceInfo;

final class InterfaceInfo_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   InterfaceInfo_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(InterfaceInfo.class);
        var0.put(InterfaceInfo.IFF_ALLMULTI, "IFF_ALLMULTI");
        var0.put(InterfaceInfo.IFF_AUTOMEDIA, "IFF_AUTOMEDIA");
        var0.put(InterfaceInfo.IFF_BROADCAST, "IFF_BROADCAST");
        var0.put(InterfaceInfo.IFF_DEBUG, "IFF_DEBUG");
        var0.put(InterfaceInfo.IFF_DYNAMIC, "IFF_DYNAMIC");
        var0.put(InterfaceInfo.IFF_LOOPBACK, "IFF_LOOPBACK");
        var0.put(InterfaceInfo.IFF_MASTER, "IFF_MASTER");
        var0.put(InterfaceInfo.IFF_MULTICAST, "IFF_MULTICAST");
        var0.put(InterfaceInfo.IFF_NOARP, "IFF_NOARP");
        var0.put(InterfaceInfo.IFF_NOTRAILERS, "IFF_NOTRAILERS");
        var0.put(InterfaceInfo.IFF_POINTOPOINT, "IFF_POINTOPOINT");
        var0.put(InterfaceInfo.IFF_PORTSEL, "IFF_PORTSEL");
        var0.put(InterfaceInfo.IFF_PROMISC, "IFF_PROMISC");
        var0.put(InterfaceInfo.IFF_RUNNING, "IFF_RUNNING");
        var0.put(InterfaceInfo.IFF_SLAVE, "IFF_SLAVE");
        var0.put(InterfaceInfo.IFF_UP, "IFF_UP");
        return var0;
    }

}