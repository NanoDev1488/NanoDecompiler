// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.mips64el.SocketLevel.StringTable
package jnr.constants.platform.linux.mips64el;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.mips64el.SocketLevel;

final class SocketLevel_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   SocketLevel_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(SocketLevel.class);
        var0.put(SocketLevel.SOL_SOCKET, "SOL_SOCKET");
        var0.put(SocketLevel.SOL_IP, "SOL_IP");
        var0.put(SocketLevel.SOL_TCP, "SOL_TCP");
        var0.put(SocketLevel.SOL_UDP, "SOL_UDP");
        var0.put(SocketLevel.SOL_IPV6, "SOL_IPV6");
        return var0;
    }

}