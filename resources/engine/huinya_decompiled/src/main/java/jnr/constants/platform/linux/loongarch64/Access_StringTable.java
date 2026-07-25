// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.Access.StringTable
package jnr.constants.platform.linux.loongarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.loongarch64.Access;

final class Access_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Access_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Access.class);
        var0.put(Access.F_OK, "F_OK");
        var0.put(Access.X_OK, "X_OK");
        var0.put(Access.W_OK, "W_OK");
        var0.put(Access.R_OK, "R_OK");
        return var0;
    }

}