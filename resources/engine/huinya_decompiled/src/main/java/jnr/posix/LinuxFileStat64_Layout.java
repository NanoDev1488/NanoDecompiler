// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStat64.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_blkcnt_t;
import jnr.ffi.StructLayout_blksize_t;
import jnr.ffi.StructLayout_dev_t;
import jnr.ffi.StructLayout_gid_t;
import jnr.ffi.StructLayout_ino_t;
import jnr.ffi.StructLayout_mode_t;
import jnr.ffi.StructLayout_nlink_t;
import jnr.ffi.StructLayout_off_t;
import jnr.ffi.StructLayout_time_t;
import jnr.ffi.StructLayout_uid_t;

public final class LinuxFileStat64_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_dev_t st_dev;
  public final StructLayout_ino_t st_ino;
  public final StructLayout_nlink_t st_nlink;
  public final StructLayout_mode_t st_mode;
  public final StructLayout_uid_t st_uid;
  public final StructLayout_gid_t st_gid;
  public final StructLayout_dev_t st_rdev;
  public final StructLayout_off_t st_size;
  public final StructLayout_blksize_t st_blksize;
  public final StructLayout_blkcnt_t st_blocks;
  public final StructLayout_time_t st_atime;
  public final StructLayout_SignedLong st_atimensec;
  public final StructLayout_time_t st_mtime;
  public final StructLayout_SignedLong st_mtimensec;
  public final StructLayout_time_t st_ctime;
  public final StructLayout_SignedLong st_ctimensec;
  public final StructLayout_Signed64 __unused4;
  public final StructLayout_Signed64 __unused5;
  public final StructLayout_Signed64 __unused6;

  public LinuxFileStat64_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_dev_t(this);
        st_ino = new StructLayout_ino_t(this);
        st_nlink = new StructLayout_nlink_t(this);
        st_mode = new StructLayout_mode_t(this);
        st_uid = new StructLayout_uid_t(this);
        st_gid = new StructLayout_gid_t(this);
        st_rdev = new StructLayout_dev_t(this);
        st_size = new StructLayout_off_t(this);
        st_blksize = new StructLayout_blksize_t(this);
        st_blocks = new StructLayout_blkcnt_t(this);
        st_atime = new StructLayout_time_t(this);
        st_atimensec = new StructLayout_SignedLong(this);
        st_mtime = new StructLayout_time_t(this);
        st_mtimensec = new StructLayout_SignedLong(this);
        st_ctime = new StructLayout_time_t(this);
        st_ctimensec = new StructLayout_SignedLong(this);
        __unused4 = new StructLayout_Signed64(this);
        __unused5 = new StructLayout_Signed64(this);
        __unused6 = new StructLayout_Signed64(this);
    }

}