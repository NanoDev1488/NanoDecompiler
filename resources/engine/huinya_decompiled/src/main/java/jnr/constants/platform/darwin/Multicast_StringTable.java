// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Multicast.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.Multicast;

final class Multicast_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Multicast_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Multicast.class);
        var0.put(Multicast.MCAST_JOIN_GROUP, "MCAST_JOIN_GROUP");
        var0.put(Multicast.MCAST_BLOCK_SOURCE, "MCAST_BLOCK_SOURCE");
        var0.put(Multicast.MCAST_UNBLOCK_SOURCE, "MCAST_UNBLOCK_SOURCE");
        var0.put(Multicast.MCAST_LEAVE_GROUP, "MCAST_LEAVE_GROUP");
        var0.put(Multicast.MCAST_JOIN_SOURCE_GROUP, "MCAST_JOIN_SOURCE_GROUP");
        var0.put(Multicast.MCAST_LEAVE_SOURCE_GROUP, "MCAST_LEAVE_SOURCE_GROUP");
        var0.put(Multicast.MCAST_EXCLUDE, "MCAST_EXCLUDE");
        var0.put(Multicast.MCAST_INCLUDE, "MCAST_INCLUDE");
        return var0;
    }

}