// исходный (обфусцированный) внутренний класс: jnr.posix.AixFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;
import jnr.posix.AixFileStat_Layout;
import jnr.posix.BaseFileStat;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;

public final class AixFileStat extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final AixFileStat_Layout layout;

    static {
        layout = new AixFileStat_Layout(Runtime.getSystemRuntime(), null);
    }

  public AixFileStat(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atime.get(memory);
    }

  public long blocks() {
        return layout.st_blocks.get(memory);
    }

  public long blockSize() {
        return layout.st_blksize.get(memory);
    }

  public long ctime() {
        return layout.st_ctime.get(memory);
    }

  public long dev() {
        return layout.st_dev.get(memory);
    }

  public int gid() {
        return ((int) layout.st_gid.get(memory));
    }

  public long ino() {
        return layout.st_ino.get(memory);
    }

  public int mode() {
        return ((int) layout.st_mode.get(memory)) & 65535;
    }

  public long mtime() {
        return layout.st_mtime.get(memory);
    }

  public int nlink() {
        return layout.st_nlink.get(memory);
    }

  public long rdev() {
        return layout.st_rdev.get(memory);
    }

  public long st_size() {
        return layout.st_size.get(memory);
    }

  public int uid() {
        return ((int) layout.st_uid.get(memory));
    }

  public long aTimeNanoSecs() {
        return ((long) layout.st_atime_n.get(memory));
    }

  public long cTimeNanoSecs() {
        return ((long) layout.st_ctime_n.get(memory));
    }

  public long mTimeNanoSecs() {
        return ((long) layout.st_mtime_n.get(memory));
    }

}