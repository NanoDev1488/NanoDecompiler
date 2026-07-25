// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.Shutdown.StringTable
package jnr.constants.platform.freebsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.Shutdown;

final class Shutdown_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Shutdown_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Shutdown.class);
        var0.put(Shutdown.SHUT_RD, "SHUT_RD");
        var0.put(Shutdown.SHUT_WR, "SHUT_WR");
        var0.put(Shutdown.SHUT_RDWR, "SHUT_RDWR");
        return var0;
    }

}