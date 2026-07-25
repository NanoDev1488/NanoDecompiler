// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.SocketControlMessage.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.SocketControlMessage;

final class SocketControlMessage_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   SocketControlMessage_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(SocketControlMessage.class);
        var0.put(SocketControlMessage.SCM_RIGHTS, "SCM_RIGHTS");
        var0.put(SocketControlMessage.SCM_TIMESTAMP, "SCM_TIMESTAMP");
        var0.put(SocketControlMessage.SCM_CREDS, "SCM_CREDS");
        return var0;
    }

}