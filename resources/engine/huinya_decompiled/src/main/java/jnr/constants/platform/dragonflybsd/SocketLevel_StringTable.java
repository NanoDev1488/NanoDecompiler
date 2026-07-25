// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.SocketLevel.StringTable
package jnr.constants.platform.dragonflybsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.dragonflybsd.SocketLevel;

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
        return var0;
    }

}