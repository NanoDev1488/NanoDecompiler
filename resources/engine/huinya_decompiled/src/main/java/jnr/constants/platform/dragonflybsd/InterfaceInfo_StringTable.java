// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.InterfaceInfo.StringTable
package jnr.constants.platform.dragonflybsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.dragonflybsd.InterfaceInfo;

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
        var0.put(InterfaceInfo.IFF_ALTPHYS, "IFF_ALTPHYS");
        var0.put(InterfaceInfo.IFF_BROADCAST, "IFF_BROADCAST");
        var0.put(InterfaceInfo.IFF_DEBUG, "IFF_DEBUG");
        var0.put(InterfaceInfo.IFF_LINK0, "IFF_LINK0");
        var0.put(InterfaceInfo.IFF_LINK1, "IFF_LINK1");
        var0.put(InterfaceInfo.IFF_LINK2, "IFF_LINK2");
        var0.put(InterfaceInfo.IFF_LOOPBACK, "IFF_LOOPBACK");
        var0.put(InterfaceInfo.IFF_MONITOR, "IFF_MONITOR");
        var0.put(InterfaceInfo.IFF_MULTICAST, "IFF_MULTICAST");
        var0.put(InterfaceInfo.IFF_NOARP, "IFF_NOARP");
        var0.put(InterfaceInfo.IFF_OACTIVE, "IFF_OACTIVE");
        var0.put(InterfaceInfo.IFF_POINTOPOINT, "IFF_POINTOPOINT");
        var0.put(InterfaceInfo.IFF_PPROMISC, "IFF_PPROMISC");
        var0.put(InterfaceInfo.IFF_PROMISC, "IFF_PROMISC");
        var0.put(InterfaceInfo.IFF_RUNNING, "IFF_RUNNING");
        var0.put(InterfaceInfo.IFF_SIMPLEX, "IFF_SIMPLEX");
        var0.put(InterfaceInfo.IFF_SMART, "IFF_SMART");
        var0.put(InterfaceInfo.IFF_STATICARP, "IFF_STATICARP");
        var0.put(InterfaceInfo.IFF_UP, "IFF_UP");
        var0.put(InterfaceInfo.IFF_CANTCHANGE, "IFF_CANTCHANGE");
        return var0;
    }

}