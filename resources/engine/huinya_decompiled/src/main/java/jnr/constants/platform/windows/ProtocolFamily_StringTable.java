// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.ProtocolFamily.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.ProtocolFamily;

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
        var0.put(ProtocolFamily.PF_UNIX, "PF_UNIX");
        var0.put(ProtocolFamily.PF_INET, "PF_INET");
        var0.put(ProtocolFamily.PF_IMPLINK, "PF_IMPLINK");
        var0.put(ProtocolFamily.PF_PUP, "PF_PUP");
        var0.put(ProtocolFamily.PF_CHAOS, "PF_CHAOS");
        var0.put(ProtocolFamily.PF_NS, "PF_NS");
        var0.put(ProtocolFamily.PF_ISO, "PF_ISO");
        var0.put(ProtocolFamily.PF_OSI, "PF_OSI");
        var0.put(ProtocolFamily.PF_ECMA, "PF_ECMA");
        var0.put(ProtocolFamily.PF_DATAKIT, "PF_DATAKIT");
        var0.put(ProtocolFamily.PF_CCITT, "PF_CCITT");
        var0.put(ProtocolFamily.PF_SNA, "PF_SNA");
        var0.put(ProtocolFamily.PF_DECnet, "PF_DECnet");
        var0.put(ProtocolFamily.PF_DLI, "PF_DLI");
        var0.put(ProtocolFamily.PF_LAT, "PF_LAT");
        var0.put(ProtocolFamily.PF_HYLINK, "PF_HYLINK");
        var0.put(ProtocolFamily.PF_APPLETALK, "PF_APPLETALK");
        var0.put(ProtocolFamily.PF_IPX, "PF_IPX");
        var0.put(ProtocolFamily.PF_INET6, "PF_INET6");
        var0.put(ProtocolFamily.PF_ATM, "PF_ATM");
        var0.put(ProtocolFamily.PF_MAX, "PF_MAX");
        return var0;
    }

}