// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.PosixFadvise.StringTable
package jnr.constants.platform.linux.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.aarch64.PosixFadvise;

final class PosixFadvise_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   PosixFadvise_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(PosixFadvise.class);
        var0.put(PosixFadvise.POSIX_FADV_NORMAL, "POSIX_FADV_NORMAL");
        var0.put(PosixFadvise.POSIX_FADV_SEQUENTIAL, "POSIX_FADV_SEQUENTIAL");
        var0.put(PosixFadvise.POSIX_FADV_RANDOM, "POSIX_FADV_RANDOM");
        var0.put(PosixFadvise.POSIX_FADV_NOREUSE, "POSIX_FADV_NOREUSE");
        var0.put(PosixFadvise.POSIX_FADV_WILLNEED, "POSIX_FADV_WILLNEED");
        var0.put(PosixFadvise.POSIX_FADV_DONTNEED, "POSIX_FADV_DONTNEED");
        return var0;
    }

}