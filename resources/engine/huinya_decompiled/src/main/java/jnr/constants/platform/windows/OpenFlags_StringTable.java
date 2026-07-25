// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.OpenFlags.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.OpenFlags;

final class OpenFlags_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   OpenFlags_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(OpenFlags.class);
        var0.put(OpenFlags.O_RDONLY, "O_RDONLY");
        var0.put(OpenFlags.O_WRONLY, "O_WRONLY");
        var0.put(OpenFlags.O_RDWR, "O_RDWR");
        var0.put(OpenFlags.O_ACCMODE, "O_ACCMODE");
        var0.put(OpenFlags.O_APPEND, "O_APPEND");
        var0.put(OpenFlags.O_CREAT, "O_CREAT");
        var0.put(OpenFlags.O_TRUNC, "O_TRUNC");
        var0.put(OpenFlags.O_EXCL, "O_EXCL");
        var0.put(OpenFlags.O_BINARY, "O_BINARY");
        return var0;
    }

}