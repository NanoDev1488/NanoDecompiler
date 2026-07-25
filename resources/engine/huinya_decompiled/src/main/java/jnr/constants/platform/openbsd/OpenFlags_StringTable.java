// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.OpenFlags.StringTable
package jnr.constants.platform.openbsd;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.openbsd.OpenFlags;

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
        var0.put(OpenFlags.O_NONBLOCK, "O_NONBLOCK");
        var0.put(OpenFlags.O_APPEND, "O_APPEND");
        var0.put(OpenFlags.O_SYNC, "O_SYNC");
        var0.put(OpenFlags.O_SHLOCK, "O_SHLOCK");
        var0.put(OpenFlags.O_EXLOCK, "O_EXLOCK");
        var0.put(OpenFlags.O_ASYNC, "O_ASYNC");
        var0.put(OpenFlags.O_FSYNC, "O_FSYNC");
        var0.put(OpenFlags.O_NOFOLLOW, "O_NOFOLLOW");
        var0.put(OpenFlags.O_CREAT, "O_CREAT");
        var0.put(OpenFlags.O_TRUNC, "O_TRUNC");
        var0.put(OpenFlags.O_EXCL, "O_EXCL");
        var0.put(OpenFlags.O_DIRECTORY, "O_DIRECTORY");
        var0.put(OpenFlags.O_NOCTTY, "O_NOCTTY");
        var0.put(OpenFlags.O_CLOEXEC, "O_CLOEXEC");
        return var0;
    }

}