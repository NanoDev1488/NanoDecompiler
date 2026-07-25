// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.posix.BaseFileStat;
import jnr.posix.MacOSFileStat_Layout;
import jnr.posix.MacOSFileStat_Layout_time_t;
import jnr.posix.MacOSPOSIX;
import jnr.posix.NanosecondFileStat;

public final class MacOSFileStat extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final MacOSFileStat_Layout layout;

    static {
        layout = new MacOSFileStat_Layout(Runtime.getSystemRuntime());
    }

  public MacOSFileStat(MacOSPOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atime.get(memory);
    }

  public long blocks() {
        return layout.st_blocks.get(memory);
    }

  public long blockSize() {
        return ((long) layout.st_blksize.get(memory));
    }

  public long ctime() {
        return layout.st_ctime.get(memory);
    }

  public long dev() {
        return ((long) layout.st_dev.get(memory));
    }

  public int gid() {
        return layout.st_gid.get(memory);
    }

  public long ino() {
        return ((long) layout.st_ino.get(memory));
    }

  public int mode() {
        return layout.st_mode.get(memory) & 65535;
    }

  public long mtime() {
        return layout.st_mtime.get(memory);
    }

  public int nlink() {
        return layout.st_nlink.get(memory);
    }

  public long rdev() {
        return ((long) layout.st_rdev.get(memory));
    }

  public long st_size() {
        return layout.st_size.get(memory);
    }

  public int uid() {
        return layout.st_uid.get(memory);
    }

  public long aTimeNanoSecs() {
        return layout.st_atimensec.get(memory);
    }

  public long cTimeNanoSecs() {
        return layout.st_ctimensec.get(memory);
    }

  public long mTimeNanoSecs() {
        return layout.st_mtimensec.get(memory);
    }

}