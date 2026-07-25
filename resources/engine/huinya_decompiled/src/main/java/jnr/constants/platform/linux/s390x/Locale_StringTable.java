// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.Locale.StringTable
package jnr.constants.platform.linux.s390x;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.s390x.Locale;

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
        var0.put(Locale.LC_PAPER, "LC_PAPER");
        var0.put(Locale.LC_NAME, "LC_NAME");
        var0.put(Locale.LC_ADDRESS, "LC_ADDRESS");
        var0.put(Locale.LC_TELEPHONE, "LC_TELEPHONE");
        var0.put(Locale.LC_MEASUREMENT, "LC_MEASUREMENT");
        var0.put(Locale.LC_IDENTIFICATION, "LC_IDENTIFICATION");
        return var0;
    }

}