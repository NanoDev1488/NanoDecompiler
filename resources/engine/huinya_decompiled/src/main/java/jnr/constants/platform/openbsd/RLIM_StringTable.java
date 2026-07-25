// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.RLIM.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.RLIM;

final class RLIM_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   RLIM_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(RLIM.class);
        var0.put(RLIM.RLIM_NLIMITS, "RLIM_NLIMITS");
        var0.put(RLIM.RLIM_INFINITY, "RLIM_INFINITY");
        var0.put(RLIM.RLIM_SAVED_MAX, "RLIM_SAVED_MAX");
        var0.put(RLIM.RLIM_SAVED_CUR, "RLIM_SAVED_CUR");
        return var0;
    }

}