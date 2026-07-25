// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.TCP.StringTable
package jnr.constants.platform.solaris;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.solaris.TCP;

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
        var0.put(TCP.TCP_MSS, "TCP_MSS");
        var0.put(TCP.TCP_NODELAY, "TCP_NODELAY");
        var0.put(TCP.TCP_MAXSEG, "TCP_MAXSEG");
        var0.put(TCP.TCP_KEEPALIVE, "TCP_KEEPALIVE");
        var0.put(TCP.TCP_CORK, "TCP_CORK");
        var0.put(TCP.TCP_INFO, "TCP_INFO");
        var0.put(TCP.TCP_KEEPCNT, "TCP_KEEPCNT");
        var0.put(TCP.TCP_KEEPIDLE, "TCP_KEEPIDLE");
        var0.put(TCP.TCP_KEEPINTVL, "TCP_KEEPINTVL");
        var0.put(TCP.TCP_LINGER2, "TCP_LINGER2");
        var0.put(TCP.TCP_MD5SIG, "TCP_MD5SIG");
        var0.put(TCP.TCP_CONGESTION, "TCP_CONGESTION");
        return var0;
    }

}