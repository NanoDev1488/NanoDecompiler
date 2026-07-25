// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.INet6.StringTable
package jnr.constants.platform.linux.s390x;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.s390x.INet6;

final class INet6_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   INet6_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(INet6.class);
        var0.put(INet6.INET6_ADDRSTRLEN, "INET6_ADDRSTRLEN");
        return var0;
    }

}