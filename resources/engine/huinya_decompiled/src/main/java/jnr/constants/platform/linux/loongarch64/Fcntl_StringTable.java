// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.Fcntl.StringTable
package jnr.constants.platform.linux.loongarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.loongarch64.Fcntl;

final class Fcntl_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Fcntl_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Fcntl.class);
        var0.put(Fcntl.FAPPEND, "FAPPEND");
        var0.put(Fcntl.FASYNC, "FASYNC");
        var0.put(Fcntl.FFSYNC, "FFSYNC");
        var0.put(Fcntl.FNONBLOCK, "FNONBLOCK");
        var0.put(Fcntl.FNDELAY, "FNDELAY");
        var0.put(Fcntl.F_DUPFD, "F_DUPFD");
        var0.put(Fcntl.F_GETFD, "F_GETFD");
        var0.put(Fcntl.F_SETFD, "F_SETFD");
        var0.put(Fcntl.F_GETFL, "F_GETFL");
        var0.put(Fcntl.F_SETFL, "F_SETFL");
        var0.put(Fcntl.F_GETOWN, "F_GETOWN");
        var0.put(Fcntl.F_SETOWN, "F_SETOWN");
        var0.put(Fcntl.F_GETLK, "F_GETLK");
        var0.put(Fcntl.F_SETLK, "F_SETLK");
        var0.put(Fcntl.F_SETLKW, "F_SETLKW");
        var0.put(Fcntl.F_RDLCK, "F_RDLCK");
        var0.put(Fcntl.F_UNLCK, "F_UNLCK");
        var0.put(Fcntl.F_WRLCK, "F_WRLCK");
        var0.put(Fcntl.F_GETPIPE_SZ, "F_GETPIPE_SZ");
        var0.put(Fcntl.F_SETPIPE_SZ, "F_SETPIPE_SZ");
        return var0;
    }

}