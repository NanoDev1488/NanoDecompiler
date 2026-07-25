// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.SocketMessage.StringTable
package jnr.constants.platform.freebsd.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.aarch64.SocketMessage;

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
        var0.put(SocketMessage.MSG_NOSIGNAL, "MSG_NOSIGNAL");
        var0.put(SocketMessage.MSG_EOF, "MSG_EOF");
        var0.put(SocketMessage.MSG_COMPAT, "MSG_COMPAT");
        return var0;
    }

}