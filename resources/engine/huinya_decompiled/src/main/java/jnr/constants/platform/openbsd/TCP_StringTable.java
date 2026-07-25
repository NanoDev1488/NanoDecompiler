// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.TCP.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.TCP;

final class TCP_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   TCP_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(TCP.class);
        var0.put(TCP.TCP_MAX_SACK, "TCP_MAX_SACK");
        var0.put(TCP.TCP_MSS, "TCP_MSS");
        var0.put(TCP.TCP_MAXWIN, "TCP_MAXWIN");
        var0.put(TCP.TCP_MAX_WINSHIFT, "TCP_MAX_WINSHIFT");
        var0.put(TCP.TCP_MAXBURST, "TCP_MAXBURST");
        var0.put(TCP.TCP_NODELAY, "TCP_NODELAY");
        var0.put(TCP.TCP_MAXSEG, "TCP_MAXSEG");
        var0.put(TCP.TCP_NOPUSH, "TCP_NOPUSH");
        var0.put(TCP.TCP_MD5SIG, "TCP_MD5SIG");
        return var0;
    }

}