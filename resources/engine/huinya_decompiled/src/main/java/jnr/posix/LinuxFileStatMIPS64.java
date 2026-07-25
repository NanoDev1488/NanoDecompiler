// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStatMIPS64
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;
import jnr.posix.BaseFileStat;
import jnr.posix.LinuxFileStatMIPS64_Layout;
import jnr.posix.LinuxPOSIX;
import jnr.posix.NanosecondFileStat;

public final class LinuxFileStatMIPS64 extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final LinuxFileStatMIPS64_Layout layout;

    static {
        layout = new LinuxFileStatMIPS64_Layout(Runtime.getSystemRuntime());
    }

  public LinuxFileStatMIPS64(LinuxPOSIX arg0) { // было: <init>
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