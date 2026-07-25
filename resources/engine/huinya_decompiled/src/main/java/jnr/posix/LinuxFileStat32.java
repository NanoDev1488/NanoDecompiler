// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStat32
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.posix.BaseFileStat;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.LinuxFileStat32_Layout;
import jnr.posix.NanosecondFileStat;

public final class LinuxFileStat32 extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final LinuxFileStat32_Layout layout;

    static {
        layout = new LinuxFileStat32_Layout(Runtime.getSystemRuntime(), null);
    }

  public LinuxFileStat32() { // было: <init>
        this(null);
    }

  public LinuxFileStat32(BaseNativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return ((long) layout.st_atim_sec.get(memory));
    }

  public long aTimeNanoSecs() {
        return ((long) layout.st_atim_nsec.get(memory));
    }

  public long blocks() {
        return ((long) layout.st_blocks.get(memory));
    }

  public long blockSize() {
        return ((long) layout.st_blksize.get(memory));
    }

  public long ctime() {
        return ((long) layout.st_ctim_sec.get(memory));
    }

  public long cTimeNanoSecs() {
        return ((long) layout.st_ctim_nsec.get(memory));
    }

  public long dev() {
        return layout.st_dev.get(memory);
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
        return ((long) layout.st_mtim_sec.get(memory));
    }

  public long mTimeNanoSecs() {
        return ((long) layout.st_mtim_nsec.get(memory));
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
        return layout.st_uid.get(memory);
    }

}