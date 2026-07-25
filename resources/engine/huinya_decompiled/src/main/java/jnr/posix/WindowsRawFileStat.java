// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsRawFileStat
package jnr.posix;

import jnr.posix.AbstractJavaFileStat;
import jnr.posix.FileStat;
import jnr.posix.NanosecondFileStat;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;
import jnr.posix.util.WindowsHelpers;
import jnr.posix.windows.CommonFileInformation;

public class WindowsRawFileStat extends AbstractJavaFileStat implements NanosecondFileStat {

    // ---- поля ----
  private int st_atime;
  private long st_atimensec;
  private long st_mtimensec;
  private long st_ctimensec;
  private int st_rdev;
  private int st_dev;
  private int st_nlink;
  private int st_mode;
  private long st_size;
  private int st_ctime;
  private int st_mtime;

  public WindowsRawFileStat(POSIX arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
    }

  public void setup(String arg0, CommonFileInformation arg1) {
        st_mode = arg1.getMode(arg0);
        setup(arg1);
        if (WindowsHelpers.isDriveLetterPath(arg0)) {
            int var3 = Character.toUpperCase(arg0.charAt(0)) - 65;
            st_rdev = var3;
            st_dev = var3;
        }
    }

  public void setup(CommonFileInformation arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #43 // jnr.posix.windows.CommonFileInformation.getLastAccessTimeNanoseconds:()J
        //      4: lstore_2
        //      5: aload_0
        //      6: lload_2
        //      7: ldc2_w  #13 // 1000000000L
        //     10: lrem
        //     11: putfield  #16 // jnr.posix.WindowsRawFileStat.st_atimensec:J
        //     14: aload_0
        //     15: lload_2
        //     16: ldc2_w  #13 // 1000000000L
        //     19: ldiv
        //     20: l2i
        //     21: putfield  #15 // jnr.posix.WindowsRawFileStat.st_atime:I
        //     24: aload_1
        //     25: invokevirtual  #44 // jnr.posix.windows.CommonFileInformation.getLastWriteTimeNanoseconds:()J
        //     28: lstore  4
        //     30: aload_0
        //     31: lload  4
        //     33: ldc2_w  #13 // 1000000000L
        //     36: lrem
        //     37: putfield  #22 // jnr.posix.WindowsRawFileStat.st_mtimensec:J
        //     40: aload_0
        //     41: lload  4
        //     43: ldc2_w  #13 // 1000000000L
        //     46: ldiv
        //     47: l2i
        //     48: putfield  #21 // jnr.posix.WindowsRawFileStat.st_mtime:I
        //     51: aload_1
        //     52: invokevirtual  #41 // jnr.posix.windows.CommonFileInformation.getCreationTimeNanoseconds:()J
        //     55: lstore  6
        //     57: aload_0
        //     58: lload  6
        //     60: ldc2_w  #13 // 1000000000L
        //     63: lrem
        //     64: putfield  #18 // jnr.posix.WindowsRawFileStat.st_ctimensec:J
        //     67: aload_0
        //     68: lload  6
        //     70: ldc2_w  #13 // 1000000000L
        //     73: ldiv
        //     74: l2i
        //     75: putfield  #17 // jnr.posix.WindowsRawFileStat.st_ctime:I
        //     78: aload_0
        //     79: aload_0
        //     80: invokevirtual  #33 // jnr.posix.WindowsRawFileStat.isDirectory:()Z
        //     83: ifeq  90 (offset +7)
        //     86: lconst_0
        //     87: goto  94 (offset +7)
        //     90: aload_1
        //     91: invokevirtual  #42 // jnr.posix.windows.CommonFileInformation.getFileSize:()J
        //     94: putfield  #25 // jnr.posix.WindowsRawFileStat.st_size:J
        //     97: aload_0
        //     98: iconst_1
        //     99: putfield  #23 // jnr.posix.WindowsRawFileStat.st_nlink:I
        //    102: aload_0
        //    103: dup
        //    104: getfield  #20 // jnr.posix.WindowsRawFileStat.st_mode:I
        //    107: bipush  -19
        //    109: iand
        //    110: putfield  #20 // jnr.posix.WindowsRawFileStat.st_mode:I
        //    113: return
    }

  public int mode() {
        return st_mode;
    }

  public long mtime() {
        return ((long) st_mtime);
    }

  public long atime() {
        return ((long) st_atime);
    }

  public long aTimeNanoSecs() {
        return st_atimensec;
    }

  public long cTimeNanoSecs() {
        return st_ctimensec;
    }

  public long mTimeNanoSecs() {
        return st_mtimensec;
    }

  public long dev() {
        return ((long) st_dev);
    }

  public int nlink() {
        return st_nlink;
    }

  public long rdev() {
        return ((long) st_rdev);
    }

  public long st_size() {
        return st_size;
    }

  public long ctime() {
        return ((long) st_ctime);
    }

  public boolean isDirectory() {
        return (mode() & 61440) == 16384;
    }

  public boolean isEmpty() {
        return st_size() == 0L;
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

}