// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.InterfaceInfo.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.InterfaceInfo;

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
        var0.put(InterfaceInfo.IFF_BROADCAST, "IFF_BROADCAST");
        var0.put(InterfaceInfo.IFF_LOOPBACK, "IFF_LOOPBACK");
        var0.put(InterfaceInfo.IFF_MULTICAST, "IFF_MULTICAST");
        var0.put(InterfaceInfo.IFF_UP, "IFF_UP");
        return var0;
    }

}