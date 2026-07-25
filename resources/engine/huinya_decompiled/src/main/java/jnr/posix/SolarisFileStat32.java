// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisFileStat32
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.posix.BaseFileStat;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;
import jnr.posix.SolarisFileStat32_Layout;

public class SolarisFileStat32 extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final SolarisFileStat32_Layout layout;

    static {
        layout = new SolarisFileStat32_Layout(Runtime.getSystemRuntime());
    }

  public SolarisFileStat32(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return ((long) layout.st_atim_sec.get(memory));
    }

  public long blocks() {
        return layout.st_blocks.get(memory);
    }

  public long blockSize() {
        return ((long) layout.st_blksize.get(memory));
    }

  public long ctime() {
        return ((long) layout.st_ctim_sec.get(memory));
    }

  public long dev() {
        return ((long) layout.st_dev.get(memory));
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
        return ((long) layout.st_mtim_sec.get(memory));
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
        return ((long) layout.st_atim_nsec.get(memory));
    }

  public long cTimeNanoSecs() {
        return ((long) layout.st_ctim_nsec.get(memory));
    }

  public long mTimeNanoSecs() {
        return ((long) layout.st_mtim_nsec.get(memory));
    }

}