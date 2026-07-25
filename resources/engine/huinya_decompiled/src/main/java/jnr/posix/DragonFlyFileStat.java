// исходный (обфусцированный) внутренний класс: jnr.posix.DragonFlyFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned16;
import jnr.posix.BaseFileStat;
import jnr.posix.DragonFlyFileStat_Layout;
import jnr.posix.DragonFlyFileStat_Layout_dev_t;
import jnr.posix.DragonFlyFileStat_Layout_time_t;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;

public final class DragonFlyFileStat extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final DragonFlyFileStat_Layout layout;

    static {
        layout = new DragonFlyFileStat_Layout(Runtime.getSystemRuntime(), null);
    }

  public DragonFlyFileStat(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atim.get(memory);
    }

  public long blocks() {
        return ((long) layout.st_blocks.get(memory));
    }

  public long blockSize() {
        return ((long) layout.st_blksize.get(memory));
    }

  public long ctime() {
        return layout.st_ctim.get(memory);
    }

  public long dev() {
        return layout.st_dev.get(memory);
    }

  public int gid() {
        return layout.st_gid.get(memory);
    }

  public long ino() {
        return layout.st_ino.get(memory);
    }

  public int mode() {
        return layout.st_mode.get(memory) & 65535;
    }

  public long mtime() {
        return layout.st_mtim.get(memory);
    }

  public int nlink() {
        return layout.st_nlink.get(memory);
    }

  public long rdev() {
        return layout.st_rdev.get(memory);
    }

  public long st_size() {
        return ((long) layout.st_size.get(memory));
    }

  public int uid() {
        return layout.st_uid.get(memory);
    }

  public long aTimeNanoSecs() {
        return layout.st_atimnsec.get(memory);
    }

  public long cTimeNanoSecs() {
        return layout.st_ctimnsec.get(memory);
    }

  public long mTimeNanoSecs() {
        return layout.st_mtimnsec.get(memory);
    }

}