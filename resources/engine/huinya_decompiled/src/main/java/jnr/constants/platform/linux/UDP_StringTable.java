// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.UDP.StringTable
package jnr.constants.platform.linux;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.UDP;

final class UDP_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   UDP_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(UDP.class);
        var0.put(UDP.UDP_CORK, "UDP_CORK");
        return var0;
    }

}