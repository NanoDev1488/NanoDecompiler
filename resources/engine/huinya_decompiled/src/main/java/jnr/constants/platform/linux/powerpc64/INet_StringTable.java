// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.INet.StringTable
package jnr.constants.platform.linux.powerpc64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.powerpc64.INet;

final class INet_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   INet_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(INet.class);
        var0.put(INet.INET_ADDRSTRLEN, "INET_ADDRSTRLEN");
        return var0;
    }

}