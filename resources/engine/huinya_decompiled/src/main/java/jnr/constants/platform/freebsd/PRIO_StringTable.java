// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.PRIO.StringTable
package jnr.constants.platform.freebsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.PRIO;

final class PRIO_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   PRIO_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(PRIO.class);
        var0.put(PRIO.PRIO_MIN, "PRIO_MIN");
        var0.put(PRIO.PRIO_PROCESS, "PRIO_PROCESS");
        var0.put(PRIO.PRIO_PGRP, "PRIO_PGRP");
        var0.put(PRIO.PRIO_USER, "PRIO_USER");
        var0.put(PRIO.PRIO_MAX, "PRIO_MAX");
        return var0;
    }

}