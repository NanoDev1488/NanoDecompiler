// исходный (обфусцированный) внутренний класс: jnr.posix.FreeBSDFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.posix.BaseFileStat;
import jnr.posix.FreeBSDFileStat_Layout;
import jnr.posix.FreeBSDFileStat_Layout_dev_t;
import jnr.posix.FreeBSDFileStat_Layout_time_t;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;

public final class FreeBSDFileStat extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final FreeBSDFileStat_Layout layout;

    static {
        layout = new FreeBSDFileStat_Layout(Runtime.getSystemRuntime(), null);
    }

  public FreeBSDFileStat(NativePOSIX arg0) { // было: <init>
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