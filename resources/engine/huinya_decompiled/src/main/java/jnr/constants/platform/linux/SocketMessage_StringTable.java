// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.SocketMessage.StringTable
package jnr.constants.platform.linux;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.SocketMessage;

final class SocketMessage_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   SocketMessage_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(SocketMessage.class);
        var0.put(SocketMessage.MSG_DONTWAIT, "MSG_DONTWAIT");
        var0.put(SocketMessage.MSG_OOB, "MSG_OOB");
        var0.put(SocketMessage.MSG_PEEK, "MSG_PEEK");
        var0.put(SocketMessage.MSG_DONTROUTE, "MSG_DONTROUTE");
        var0.put(SocketMessage.MSG_EOR, "MSG_EOR");
        var0.put(SocketMessage.MSG_TRUNC, "MSG_TRUNC");
        var0.put(SocketMessage.MSG_CTRUNC, "MSG_CTRUNC");
        var0.put(SocketMessage.MSG_WAITALL, "MSG_WAITALL");
        var0.put(SocketMessage.MSG_PROXY, "MSG_PROXY");
        var0.put(SocketMessage.MSG_FIN, "MSG_FIN");
        var0.put(SocketMessage.MSG_SYN, "MSG_SYN");
        var0.put(SocketMessage.MSG_CONFIRM, "MSG_CONFIRM");
        var0.put(SocketMessage.MSG_RST, "MSG_RST");
        var0.put(SocketMessage.MSG_ERRQUEUE, "MSG_ERRQUEUE");
        var0.put(SocketMessage.MSG_NOSIGNAL, "MSG_NOSIGNAL");
        var0.put(SocketMessage.MSG_MORE, "MSG_MORE");
        var0.put(SocketMessage.MSG_FASTOPEN, "MSG_FASTOPEN");
        return var0;
    }

}