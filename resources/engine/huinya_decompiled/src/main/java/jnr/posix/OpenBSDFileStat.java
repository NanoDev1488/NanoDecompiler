// исходный (обфусцированный) внутренний класс: jnr.posix.OpenBSDFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;
import jnr.posix.BaseFileStat;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;
import jnr.posix.OpenBSDFileStat_Layout;
import jnr.posix.OpenBSDFileStat_Layout_dev_t;
import jnr.posix.OpenBSDFileStat_Layout_time_t;

public final class OpenBSDFileStat extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final OpenBSDFileStat_Layout layout;

    static {
        layout = new OpenBSDFileStat_Layout(Runtime.getSystemRuntime(), null);
    }

  public OpenBSDFileStat(NativePOSIX arg0) { // было: <init>
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
        return ((long) layout.st_dev.get(memory));
    }

  public int gid() {
        return ((int) layout.st_gid.get(memory));
    }

  public long ino() {
        return layout.st_ino.get(memory);
    }

  public int mode() {
        return ((int) (layout.st_mode.get(memory) & 65535L));
    }

  public long mtime() {
        return layout.st_mtime.get(memory);
    }

  public int nlink() {
        return ((int) layout.st_nlink.get(memory));
    }

  public long rdev() {
        return ((long) layout.st_rdev.get(memory));
    }

  public long st_size() {
        return layout.st_size.get(memory);
    }

  public int uid() {
        return ((int) layout.st_uid.get(memory));
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