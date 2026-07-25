// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisFileStat64
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_UnsignedLong;
import jnr.posix.BaseFileStat;
import jnr.posix.NanosecondFileStat;
import jnr.posix.NativePOSIX;
import jnr.posix.SolarisFileStat64_Layout;

public class SolarisFileStat64 extends BaseFileStat implements NanosecondFileStat {

    // ---- поля ----
  private static final SolarisFileStat64_Layout layout;

    static {
        layout = new SolarisFileStat64_Layout(Runtime.getSystemRuntime());
    }

  public SolarisFileStat64() { // было: <init>
        this(null);
    }

  public SolarisFileStat64(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atim_sec.get(memory);
    }

  public long blocks() {
        return layout.st_blocks.get(memory);
    }

  public long blockSize() {
        return ((long) layout.st_blksize.get(memory));
    }

  public long ctime() {
        return layout.st_ctim_sec.get(memory);
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
        return layout.st_mtim_sec.get(memory);
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

  public long aTimeNanoSecs() {
        return layout.st_atim_nsec.get(memory);
    }

  public long cTimeNanoSecs() {
        return layout.st_ctim_nsec.get(memory);
    }

  public long mTimeNanoSecs() {
        return layout.st_mtim_nsec.get(memory);
    }

}