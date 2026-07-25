// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.Locale.StringTable
package jnr.constants.platform.freebsd.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.aarch64.Locale;

final class Locale_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Locale_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Locale.class);
        var0.put(Locale.LC_CTYPE, "LC_CTYPE");
        var0.put(Locale.LC_NUMERIC, "LC_NUMERIC");
        var0.put(Locale.LC_TIME, "LC_TIME");
        var0.put(Locale.LC_COLLATE, "LC_COLLATE");
        var0.put(Locale.LC_MONETARY, "LC_MONETARY");
        var0.put(Locale.LC_MESSAGES, "LC_MESSAGES");
        var0.put(Locale.LC_ALL, "LC_ALL");
        return var0;
    }

}