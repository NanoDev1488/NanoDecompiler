// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.INAddr.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.INAddr;

final class INAddr_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   INAddr_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(INAddr.class);
        var0.put(INAddr.INADDR_ANY, "INADDR_ANY");
        var0.put(INAddr.INADDR_BROADCAST, "INADDR_BROADCAST");
        var0.put(INAddr.INADDR_NONE, "INADDR_NONE");
        var0.put(INAddr.INADDR_LOOPBACK, "INADDR_LOOPBACK");
        var0.put(INAddr.INADDR_UNSPEC_GROUP, "INADDR_UNSPEC_GROUP");
        var0.put(INAddr.INADDR_ALLHOSTS_GROUP, "INADDR_ALLHOSTS_GROUP");
        var0.put(INAddr.INADDR_ALLRTRS_GROUP, "INADDR_ALLRTRS_GROUP");
        var0.put(INAddr.INADDR_MAX_LOCAL_GROUP, "INADDR_MAX_LOCAL_GROUP");
        return var0;
    }

}