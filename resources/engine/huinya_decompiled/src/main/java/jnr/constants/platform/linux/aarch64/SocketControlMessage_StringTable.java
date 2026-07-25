// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.SocketControlMessage.StringTable
package jnr.constants.platform.linux.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.aarch64.SocketControlMessage;

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
        var0.put(SocketControlMessage.SCM_TIMESTAMPNS, "SCM_TIMESTAMPNS");
        var0.put(SocketControlMessage.SCM_TIMESTAMPING, "SCM_TIMESTAMPING");
        var0.put(SocketControlMessage.SCM_CREDENTIALS, "SCM_CREDENTIALS");
        var0.put(SocketControlMessage.SCM_WIFI_STATUS, "SCM_WIFI_STATUS");
        return var0;
    }

}