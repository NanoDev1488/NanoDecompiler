// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Fcntl.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.Fcntl;

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
        var0.put(Fcntl.FREAD, "FREAD");
        var0.put(Fcntl.FWRITE, "FWRITE");
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
        var0.put(Fcntl.F_CHKCLEAN, "F_CHKCLEAN");
        var0.put(Fcntl.F_PREALLOCATE, "F_PREALLOCATE");
        var0.put(Fcntl.F_SETSIZE, "F_SETSIZE");
        var0.put(Fcntl.F_RDADVISE, "F_RDADVISE");
        var0.put(Fcntl.F_RDAHEAD, "F_RDAHEAD");
        var0.put(Fcntl.F_NOCACHE, "F_NOCACHE");
        var0.put(Fcntl.F_LOG2PHYS, "F_LOG2PHYS");
        var0.put(Fcntl.F_GETPATH, "F_GETPATH");
        var0.put(Fcntl.F_FULLFSYNC, "F_FULLFSYNC");
        var0.put(Fcntl.F_PATHPKG_CHECK, "F_PATHPKG_CHECK");
        var0.put(Fcntl.F_FREEZE_FS, "F_FREEZE_FS");
        var0.put(Fcntl.F_THAW_FS, "F_THAW_FS");
        var0.put(Fcntl.F_GLOBAL_NOCACHE, "F_GLOBAL_NOCACHE");
        var0.put(Fcntl.F_ADDSIGS, "F_ADDSIGS");
        var0.put(Fcntl.F_RDLCK, "F_RDLCK");
        var0.put(Fcntl.F_UNLCK, "F_UNLCK");
        var0.put(Fcntl.F_WRLCK, "F_WRLCK");
        var0.put(Fcntl.F_ALLOCATECONTIG, "F_ALLOCATECONTIG");
        var0.put(Fcntl.F_ALLOCATEALL, "F_ALLOCATEALL");
        return var0;
    }

}