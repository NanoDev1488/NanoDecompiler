// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.TCP.StringTable
package jnr.constants.platform.freebsd.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.aarch64.TCP;

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
        var0.put(TCP.TCP_MINMSS, "TCP_MINMSS");
        var0.put(TCP.TCP_MAXWIN, "TCP_MAXWIN");
        var0.put(TCP.TCP_MAX_WINSHIFT, "TCP_MAX_WINSHIFT");
        var0.put(TCP.TCP_MAXBURST, "TCP_MAXBURST");
        var0.put(TCP.TCP_MAXHLEN, "TCP_MAXHLEN");
        var0.put(TCP.TCP_MAXOLEN, "TCP_MAXOLEN");
        var0.put(TCP.TCP_NODELAY, "TCP_NODELAY");
        var0.put(TCP.TCP_MAXSEG, "TCP_MAXSEG");
        var0.put(TCP.TCP_NOPUSH, "TCP_NOPUSH");
        var0.put(TCP.TCP_NOOPT, "TCP_NOOPT");
        var0.put(TCP.TCP_INFO, "TCP_INFO");
        var0.put(TCP.TCP_KEEPCNT, "TCP_KEEPCNT");
        var0.put(TCP.TCP_KEEPIDLE, "TCP_KEEPIDLE");
        var0.put(TCP.TCP_KEEPINTVL, "TCP_KEEPINTVL");
        var0.put(TCP.TCP_MD5SIG, "TCP_MD5SIG");
        var0.put(TCP.TCP_FASTOPEN, "TCP_FASTOPEN");
        var0.put(TCP.TCP_CONGESTION, "TCP_CONGESTION");
        return var0;
    }

}