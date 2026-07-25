// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.Local.StringTable
package jnr.constants.platform.freebsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.Local;

final class Local_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Local_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Local.class);
        var0.put(Local.LOCAL_PEERCRED, "LOCAL_PEERCRED");
        var0.put(Local.LOCAL_CREDS, "LOCAL_CREDS");
        var0.put(Local.LOCAL_CONNWAIT, "LOCAL_CONNWAIT");
        return var0;
    }

}