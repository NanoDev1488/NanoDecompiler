// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.Pathconf.StringTable
package jnr.constants.platform.linux;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.Pathconf;

final class Pathconf_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Pathconf_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Pathconf.class);
        var0.put(Pathconf._PC_FILESIZEBITS, "_PC_FILESIZEBITS");
        var0.put(Pathconf._PC_LINK_MAX, "_PC_LINK_MAX");
        var0.put(Pathconf._PC_MAX_CANON, "_PC_MAX_CANON");
        var0.put(Pathconf._PC_MAX_INPUT, "_PC_MAX_INPUT");
        var0.put(Pathconf._PC_NAME_MAX, "_PC_NAME_MAX");
        var0.put(Pathconf._PC_PATH_MAX, "_PC_PATH_MAX");
        var0.put(Pathconf._PC_PIPE_BUF, "_PC_PIPE_BUF");
        var0.put(Pathconf._PC_2_SYMLINKS, "_PC_2_SYMLINKS");
        var0.put(Pathconf._PC_ALLOC_SIZE_MIN, "_PC_ALLOC_SIZE_MIN");
        var0.put(Pathconf._PC_REC_INCR_XFER_SIZE, "_PC_REC_INCR_XFER_SIZE");
        var0.put(Pathconf._PC_REC_MAX_XFER_SIZE, "_PC_REC_MAX_XFER_SIZE");
        var0.put(Pathconf._PC_REC_MIN_XFER_SIZE, "_PC_REC_MIN_XFER_SIZE");
        var0.put(Pathconf._PC_REC_XFER_ALIGN, "_PC_REC_XFER_ALIGN");
        var0.put(Pathconf._PC_SYMLINK_MAX, "_PC_SYMLINK_MAX");
        var0.put(Pathconf._PC_CHOWN_RESTRICTED, "_PC_CHOWN_RESTRICTED");
        var0.put(Pathconf._PC_NO_TRUNC, "_PC_NO_TRUNC");
        var0.put(Pathconf._PC_VDISABLE, "_PC_VDISABLE");
        var0.put(Pathconf._PC_ASYNC_IO, "_PC_ASYNC_IO");
        var0.put(Pathconf._PC_PRIO_IO, "_PC_PRIO_IO");
        var0.put(Pathconf._PC_SYNC_IO, "_PC_SYNC_IO");
        return var0;
    }

}