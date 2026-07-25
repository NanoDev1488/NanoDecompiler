// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.WaitFlags.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.WaitFlags;

final class WaitFlags_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   WaitFlags_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(WaitFlags.class);
        var0.put(WaitFlags.WNOHANG, "WNOHANG");
        var0.put(WaitFlags.WUNTRACED, "WUNTRACED");
        var0.put(WaitFlags.WCONTINUED, "WCONTINUED");
        return var0;
    }

}