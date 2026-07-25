// исходный (обфусцированный) внутренний класс: jnr.posix.BaseFileStat
package jnr.posix;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.posix.FileStat;
import jnr.posix.NativePOSIX;
import jnr.posix.POSIX;

public abstract class BaseFileStat implements FileStat {

    // ---- поля ----
  protected final POSIX posix;
  protected final Pointer memory;

  protected BaseFileStat(NativePOSIX arg0, StructLayout arg1) { // было: <init>
        super();
        posix = arg0;
        memory = Memory.allocate(arg0.getRuntime(), arg1.size());
    }

  public String ftype() {
        if (!isFile()) {
            if (!isDirectory()) {
                if (!isCharDev()) {
                    if (!isBlockDev()) {
                        if (!isFifo()) {
                            if (!isSymlink()) {
                                if (!isSocket()) {
                                    return "unknown";
                                } else {
                                    return "socket";
                                }
                            } else {
                                return "link";
                            }
                        } else {
                            return "fifo";
                        }
                    } else {
                        return "blockSpecial";
                    }
                } else {
                    return "characterSpecial";
                }
            } else {
                return "directory";
            }
        } else {
            return "file";
        }
    }

  public boolean groupMember(int arg0) {
        if (posix.getgid() == arg0) {
            return true;
        } else {
            if (posix.getegid() != arg0) {
                return false;
            } else {
                return true;
            }
        }
    }

  public boolean isBlockDev() {
        return (mode() & 61440) == 24576;
    }

  public boolean isCharDev() {
        return (mode() & 61440) == 8192;
    }

  public boolean isDirectory() {
        return (mode() & 61440) == 16384;
    }

  public boolean isEmpty() {
        return st_size() == 0L;
    }

  public boolean isExecutable() {
        if (posix.geteuid() != 0) {
            if (!isOwned()) {
                if (!isGroupOwned()) {
                    return (mode() & 1) != 0;
                } else {
                    return (mode() & 8) != 0;
                }
            } else {
                return (mode() & 64) != 0;
            }
        } else {
            return (mode() & 73) != 0;
        }
    }

  public boolean isExecutableReal() {
        if (posix.getuid() != 0) {
            if (!isROwned()) {
                if (!groupMember(gid())) {
                    return (mode() & 1) != 0;
                } else {
                    return (mode() & 8) != 0;
                }
            } else {
                return (mode() & 64) != 0;
            }
        } else {
            return (mode() & 73) != 0;
        }
    }

  public boolean isFile() {
        return (mode() & 61440) == 32768;
    }

  public boolean isFifo() {
        return (mode() & 61440) == 4096;
    }

  public boolean isGroupOwned() {
        return groupMember(gid());
    }

  public boolean isIdentical(FileStat arg0) {
        return dev() != arg0.dev() ? 0 : ino() == arg0.ino();
    }

  public boolean isNamedPipe() {
        return (mode() & 4096) != 0;
    }

  public boolean isOwned() {
        return posix.geteuid() == uid();
    }

  public boolean isROwned() {
        return posix.getuid() == uid();
    }

  public boolean isReadable() {
        if (posix.geteuid() != 0) {
            if (!isOwned()) {
                if (!isGroupOwned()) {
                    return (mode() & 4) != 0;
                } else {
                    return (mode() & 32) != 0;
                }
            } else {
                return (mode() & 256) != 0;
            }
        } else {
            return true;
        }
    }

  public boolean isReadableReal() {
        if (posix.getuid() != 0) {
            if (!isROwned()) {
                if (!groupMember(gid())) {
                    return (mode() & 4) != 0;
                } else {
                    return (mode() & 32) != 0;
                }
            } else {
                return (mode() & 256) != 0;
            }
        } else {
            return true;
        }
    }

  public boolean isSetgid() {
        return (mode() & 1024) != 0;
    }

  public boolean isSetuid() {
        return (mode() & 2048) != 0;
    }

  public boolean isSocket() {
        return (mode() & 61440) == 49152;
    }

  public boolean isSticky() {
        return (mode() & 512) != 0;
    }

  public boolean isSymlink() {
        return (mode() & 61440) == 40960;
    }

  public boolean isWritable() {
        if (posix.geteuid() != 0) {
            if (!isOwned()) {
                if (!isGroupOwned()) {
                    return (mode() & 2) != 0;
                } else {
                    return (mode() & 16) != 0;
                }
            } else {
                return (mode() & 128) != 0;
            }
        } else {
            return true;
        }
    }

  public boolean isWritableReal() {
        if (posix.getuid() != 0) {
            if (!isROwned()) {
                if (!groupMember(gid())) {
                    return (mode() & 2) != 0;
                } else {
                    return (mode() & 16) != 0;
                }
            } else {
                return (mode() & 128) != 0;
            }
        } else {
            return true;
        }
    }

  public int major(long arg0) {
        return ((int) (arg0 >> 24)) & 255;
    }

  public int minor(long arg0) {
        return ((int) (arg0 & 16777215L));
    }

}