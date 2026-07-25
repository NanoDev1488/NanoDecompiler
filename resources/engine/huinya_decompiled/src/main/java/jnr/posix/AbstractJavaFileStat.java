// исходный (обфусцированный) внутренний класс: jnr.posix.AbstractJavaFileStat
package jnr.posix;

import jnr.posix.FileStat;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;

public abstract class AbstractJavaFileStat implements FileStat {

    // ---- поля ----
  protected final POSIXHandler handler;
  protected final POSIX posix;

  public AbstractJavaFileStat(POSIX arg0, POSIXHandler arg1) { // было: <init>
        super();
        handler = arg1;
        posix = arg0;
    }

  public boolean isBlockDev() {
        handler.unimplementedError("block device detection");
        return false;
    }

  public boolean isCharDev() {
        return false;
    }

  public boolean isFifo() {
        handler.unimplementedError("fifo file detection");
        return false;
    }

  public boolean isNamedPipe() {
        handler.unimplementedError("piped file detection");
        return false;
    }

  public boolean isSetgid() {
        handler.unimplementedError("setgid detection");
        return false;
    }

  public boolean isSetuid() {
        handler.unimplementedError("setuid detection");
        return false;
    }

  public boolean isSocket() {
        handler.unimplementedError("socket file type detection");
        return false;
    }

  public boolean isSticky() {
        handler.unimplementedError("sticky bit detection");
        return false;
    }

  public int major(long arg0) {
        handler.unimplementedError("major device");
        return -1;
    }

  public int minor(long arg0) {
        handler.unimplementedError("minor device");
        return -1;
    }

  public int nlink() {
        handler.unimplementedError("stat.nlink");
        return -1;
    }

  public long rdev() {
        handler.unimplementedError("stat.rdev");
        return -1L;
    }

  public int uid() {
        return -1;
    }

  public long blocks() {
        handler.unimplementedError("stat.st_blocks");
        return -1L;
    }

  public long blockSize() {
        return 4096L;
    }

  public long dev() {
        handler.unimplementedError("stat.st_dev");
        return -1L;
    }

  public String ftype() {
        if (!isFile()) {
            if (!isDirectory()) {
                return "unknown";
            } else {
                return "directory";
            }
        } else {
            return "file";
        }
    }

  public int gid() {
        handler.unimplementedError("stat.st_gid");
        return -1;
    }

  public boolean groupMember(int arg0) {
        return posix.getgid() == arg0 ? 1 : posix.getegid() == arg0;
    }

  public long ino() {
        return 0L;
    }

}