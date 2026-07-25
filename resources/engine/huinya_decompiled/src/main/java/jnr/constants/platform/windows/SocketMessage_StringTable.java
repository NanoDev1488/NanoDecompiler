// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.SocketMessage.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.SocketMessage;

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
        var0.put(SocketMessage.MSG_OOB, "MSG_OOB");
        var0.put(SocketMessage.MSG_PEEK, "MSG_PEEK");
        var0.put(SocketMessage.MSG_DONTROUTE, "MSG_DONTROUTE");
        var0.put(SocketMessage.MSG_WAITALL, "MSG_WAITALL");
        return var0;
    }

}