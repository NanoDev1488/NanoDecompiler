// исходный (обфусцированный) внутренний класс: jnr.posix.FileStat
package jnr.posix;

public interface FileStat {

    // ---- поля ----
  public static final int S_IFIFO = 4096;
  public static final int S_IFCHR = 8192;
  public static final int S_IFDIR = 16384;
  public static final int S_IFBLK = 24576;
  public static final int S_IFREG = 32768;
  public static final int S_IFLNK = 40960;
  public static final int S_IFSOCK = 49152;
  public static final int S_IFMT = 61440;
  public static final int S_ISUID = 2048;
  public static final int S_ISGID = 1024;
  public static final int S_ISVTX = 512;
  public static final int S_IRUSR = 256;
  public static final int S_IWUSR = 128;
  public static final int S_IXUSR = 64;
  public static final int S_IRGRP = 32;
  public static final int S_IWGRP = 16;
  public static final int S_IXGRP = 8;
  public static final int S_IROTH = 4;
  public static final int S_IWOTH = 2;
  public static final int S_IXOTH = 1;
  public static final int ALL_READ = 292;
  public static final int ALL_WRITE = 146;
  public static final int S_IXUGO = 73;

  public abstract long atime();

  public abstract long blocks();

  public abstract long blockSize();

  public abstract long ctime();

  public abstract long dev();

  public abstract String ftype();

  public abstract int gid();

  public abstract boolean groupMember(int arg0);

  public abstract long ino();

  public abstract boolean isBlockDev();

  public abstract boolean isCharDev();

  public abstract boolean isDirectory();

  public abstract boolean isEmpty();

  public abstract boolean isExecutable();

  public abstract boolean isExecutableReal();

  public abstract boolean isFifo();

  public abstract boolean isFile();

  public abstract boolean isGroupOwned();

  public abstract boolean isIdentical(FileStat arg0);

  public abstract boolean isNamedPipe();

  public abstract boolean isOwned();

  public abstract boolean isROwned();

  public abstract boolean isReadable();

  public abstract boolean isReadableReal();

  public abstract boolean isWritable();

  public abstract boolean isWritableReal();

  public abstract boolean isSetgid();

  public abstract boolean isSetuid();

  public abstract boolean isSocket();

  public abstract boolean isSticky();

  public abstract boolean isSymlink();

  public abstract int major(long arg0);

  public abstract int minor(long arg0);

  public abstract int mode();

  public abstract long mtime();

  public abstract int nlink();

  public abstract long rdev();

  public abstract long st_size();

  public abstract int uid();

}