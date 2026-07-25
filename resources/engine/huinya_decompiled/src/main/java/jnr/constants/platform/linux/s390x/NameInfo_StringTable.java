// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.NameInfo.StringTable
package jnr.constants.platform.linux.s390x;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.s390x.NameInfo;

final class NameInfo_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   NameInfo_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(NameInfo.class);
        var0.put(NameInfo.NI_MAXHOST, "NI_MAXHOST");
        var0.put(NameInfo.NI_MAXSERV, "NI_MAXSERV");
        var0.put(NameInfo.NI_NOFQDN, "NI_NOFQDN");
        var0.put(NameInfo.NI_NUMERICHOST, "NI_NUMERICHOST");
        var0.put(NameInfo.NI_NAMEREQD, "NI_NAMEREQD");
        var0.put(NameInfo.NI_NUMERICSERV, "NI_NUMERICSERV");
        var0.put(NameInfo.NI_DGRAM, "NI_DGRAM");
        return var0;
    }

}