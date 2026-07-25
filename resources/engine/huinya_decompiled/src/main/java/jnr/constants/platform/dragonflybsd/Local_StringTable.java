// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.Local.StringTable
package jnr.constants.platform.dragonflybsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.dragonflybsd.Local;

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
        return var0;
    }

}