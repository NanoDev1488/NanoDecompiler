// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStatLOONGARCH64
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_blkcnt_t;
import jnr.ffi.StructLayout_blksize_t;
import jnr.ffi.StructLayout_dev_t;
import jnr.ffi.StructLayout_gid_t;
import jnr.ffi.StructLayout_ino_t;
import jnr.ffi.StructLayout_mode_t;
import jnr.ffi.StructLayout_nlink_t;
import jnr.ffi.StructLayout_off_t;
import jnr.ffi.StructLayout_time_t;
import jnr.ffi.StructLayout_uid_t;
import jnr.posix.BaseFileStat;
import jnr.posix.LinuxFileStatLOONGARCH64_Layout;
import jnr.posix.LinuxPOSIX;
import jnr.posix.NanosecondFileStat;

public final class LinuxFileStatLOONGARCH64 extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final LinuxFileStatLOONGARCH64_Layout layout;

    static {
        layout = new LinuxFileStatLOONGARCH64_Layout(Runtime.getSystemRuntime());
    }

  public LinuxFileStatLOONGARCH64(LinuxPOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atime.get(memory);
    }

  public long aTimeNanoSecs() {
        return layout.st_atimensec.get(memory);
    }

  public long blockSize() {
        return layout.st_blksize.get(memory);
    }

  public long blocks() {
        return layout.st_blocks.get(memory);
    }

  public long ctime() {
        return layout.st_ctime.get(memory);
    }

  public long cTimeNanoSecs() {
        return layout.st_ctimensec.get(memory);
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
        return ((int) layout.st_mode.get(memory));
    }

  public long mtime() {
        return layout.st_mtime.get(memory);
    }

  public long mTimeNanoSecs() {
        return layout.st_mtimensec.get(memory);
    }

  public int nlink() {
        return ((int) layout.st_nlink.get(memory));
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

}