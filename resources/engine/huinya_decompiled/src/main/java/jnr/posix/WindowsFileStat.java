// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsFileStat
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.posix.BaseFileStat;
import jnr.posix.NativePOSIX;
import jnr.posix.WindowsFileStat_Layout;

public class WindowsFileStat extends BaseFileStat {

    // ---- поля ----
  private static final WindowsFileStat_Layout layout;

    static {
        layout = new WindowsFileStat_Layout(Runtime.getSystemRuntime(), null);
    }

  public WindowsFileStat(NativePOSIX arg0) { // было: <init>
        super(arg0, layout);
    }

  public long atime() {
        return layout.st_atime.get(memory);
    }

  public long blockSize() {
        return 512L;
    }

  public long blocks() {
        return (layout.st_size.get(memory) + 512L - 1L) / 512L;
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
        return layout.st_mode.get(memory) & -19 & 65535;
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

  public boolean groupMember(int arg0) {
        return true;
    }

  public boolean isExecutable() {
        if (!isOwned()) {
            if (!isGroupOwned()) {
                if ((mode() & 1) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 8) != 0;
            }
        } else {
            return (mode() & 64) != 0;
        }
    }

  public boolean isExecutableReal() {
        if (!isROwned()) {
            if (!groupMember(gid())) {
                if ((mode() & 1) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 8) != 0;
            }
        } else {
            return (mode() & 64) != 0;
        }
    }

  public boolean isOwned() {
        return true;
    }

  public boolean isROwned() {
        return true;
    }

  public boolean isReadable() {
        if (!isOwned()) {
            if (!isGroupOwned()) {
                if ((mode() & 4) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 32) != 0;
            }
        } else {
            return (mode() & 256) != 0;
        }
    }

  public boolean isReadableReal() {
        if (!isROwned()) {
            if (!groupMember(gid())) {
                if ((mode() & 4) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 32) != 0;
            }
        } else {
            return (mode() & 256) != 0;
        }
    }

  public boolean isWritable() {
        if (!isOwned()) {
            if (!isGroupOwned()) {
                if ((mode() & 2) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 16) != 0;
            }
        } else {
            return (mode() & 128) != 0;
        }
    }

  public boolean isWritableReal() {
        if (!isROwned()) {
            if (!groupMember(gid())) {
                if ((mode() & 2) == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return (mode() & 16) != 0;
            }
        } else {
            return (mode() & 128) != 0;
        }
    }

  public String toString() {
        return new StringBuilder().append("st_dev: ").append(layout.st_dev.get(memory)).append(", st_mode: ").append(Integer.toOctalString(mode())).append(", layout.st_nlink: ").append(layout.st_nlink.get(memory)).append(", layout.st_rdev: ").append(layout.st_rdev.get(memory)).append(", layout.st_size: ").append(layout.st_size.get(memory)).append(", layout.st_uid: ").append(layout.st_uid.get(memory)).append(", layout.st_gid: ").append(layout.st_gid.get(memory)).append(", layout.st_atime: ").append(layout.st_atime.get(memory)).append(", layout.st_ctime: ").append(layout.st_ctime.get(memory)).append(", layout.st_mtime: ").append(layout.st_mtime.get(memory)).append(", layout.st_ino: ").append(layout.st_ino.get(memory)).toString();
    }

}