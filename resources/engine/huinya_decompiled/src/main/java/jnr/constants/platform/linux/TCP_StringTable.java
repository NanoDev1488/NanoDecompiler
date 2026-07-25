// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.TCP.StringTable
package jnr.constants.platform.linux;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.TCP;

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
        var0.put(TCP.TCP_MAXWIN, "TCP_MAXWIN");
        var0.put(TCP.TCP_MAX_WINSHIFT, "TCP_MAX_WINSHIFT");
        var0.put(TCP.TCP_NODELAY, "TCP_NODELAY");
        var0.put(TCP.TCP_MAXSEG, "TCP_MAXSEG");
        var0.put(TCP.TCP_CORK, "TCP_CORK");
        var0.put(TCP.TCP_DEFER_ACCEPT, "TCP_DEFER_ACCEPT");
        var0.put(TCP.TCP_INFO, "TCP_INFO");
        var0.put(TCP.TCP_KEEPCNT, "TCP_KEEPCNT");
        var0.put(TCP.TCP_KEEPIDLE, "TCP_KEEPIDLE");
        var0.put(TCP.TCP_KEEPINTVL, "TCP_KEEPINTVL");
        var0.put(TCP.TCP_LINGER2, "TCP_LINGER2");
        var0.put(TCP.TCP_MD5SIG, "TCP_MD5SIG");
        var0.put(TCP.TCP_QUICKACK, "TCP_QUICKACK");
        var0.put(TCP.TCP_SYNCNT, "TCP_SYNCNT");
        var0.put(TCP.TCP_WINDOW_CLAMP, "TCP_WINDOW_CLAMP");
        var0.put(TCP.TCP_FASTOPEN, "TCP_FASTOPEN");
        var0.put(TCP.TCP_CONGESTION, "TCP_CONGESTION");
        var0.put(TCP.TCP_COOKIE_TRANSACTIONS, "TCP_COOKIE_TRANSACTIONS");
        var0.put(TCP.TCP_QUEUE_SEQ, "TCP_QUEUE_SEQ");
        var0.put(TCP.TCP_REPAIR, "TCP_REPAIR");
        var0.put(TCP.TCP_REPAIR_OPTIONS, "TCP_REPAIR_OPTIONS");
        var0.put(TCP.TCP_REPAIR_QUEUE, "TCP_REPAIR_QUEUE");
        var0.put(TCP.TCP_THIN_DUPACK, "TCP_THIN_DUPACK");
        var0.put(TCP.TCP_THIN_LINEAR_TIMEOUTS, "TCP_THIN_LINEAR_TIMEOUTS");
        var0.put(TCP.TCP_TIMESTAMP, "TCP_TIMESTAMP");
        var0.put(TCP.TCP_USER_TIMEOUT, "TCP_USER_TIMEOUT");
        return var0;
    }

}