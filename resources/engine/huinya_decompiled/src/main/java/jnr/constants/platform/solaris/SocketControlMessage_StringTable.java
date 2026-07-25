// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.SocketControlMessage.StringTable
package jnr.constants.platform.solaris;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.solaris.SocketControlMessage;

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
        var0.put(SocketControlMessage.SCM_UCRED, "SCM_UCRED");
        return var0;
    }

}