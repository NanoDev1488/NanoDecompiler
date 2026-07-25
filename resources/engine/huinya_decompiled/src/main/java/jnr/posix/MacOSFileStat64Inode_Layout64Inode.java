// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSFileStat64Inode.Layout64Inode
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.posix.MacOSFileStat64Inode_Layout64Inode_time_t;

public class MacOSFileStat64Inode_Layout64Inode extends StructLayout {

    // ---- поля ----
  public final StructLayout_Signed32 st_dev;
  public final StructLayout_Signed16 st_mode;
  public final StructLayout_Signed16 st_nlink;
  public final StructLayout_Signed64 st_ino;
  public final StructLayout_Signed32 st_uid;
  public final StructLayout_Signed32 st_gid;
  public final StructLayout_Signed32 st_rdev;
  public final MacOSFileStat64Inode_Layout64Inode_time_t st_atime;
  public final StructLayout_SignedLong st_atimensec;
  public final MacOSFileStat64Inode_Layout64Inode_time_t st_mtime;
  public final StructLayout_SignedLong st_mtimensec;
  public final MacOSFileStat64Inode_Layout64Inode_time_t st_ctime;
  public final StructLayout_SignedLong st_ctimensec;
  public final MacOSFileStat64Inode_Layout64Inode_time_t st_birthtime;
  public final StructLayout_SignedLong st_birthtimensec;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed64 st_blocks;
  public final StructLayout_Signed32 st_blksize;
  public final StructLayout_Signed32 st_flags;
  public final StructLayout_Signed32 st_gen;
  public final StructLayout_Signed32 st_lspare;
  public final StructLayout_Signed64 st_qspare0;
  public final StructLayout_Signed64 st_qspare1;

  public MacOSFileStat64Inode_Layout64Inode(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_Signed32(this);
        st_mode = new StructLayout_Signed16(this);
        st_nlink = new StructLayout_Signed16(this);
        st_ino = new StructLayout_Signed64(this);
        st_uid = new StructLayout_Signed32(this);
        st_gid = new StructLayout_Signed32(this);
        st_rdev = new StructLayout_Signed32(this);
        st_atime = new MacOSFileStat64Inode_Layout64Inode_time_t(this);
        st_atimensec = new StructLayout_SignedLong(this);
        st_mtime = new MacOSFileStat64Inode_Layout64Inode_time_t(this);
        st_mtimensec = new StructLayout_SignedLong(this);
        st_ctime = new MacOSFileStat64Inode_Layout64Inode_time_t(this);
        st_ctimensec = new StructLayout_SignedLong(this);
        st_birthtime = new MacOSFileStat64Inode_Layout64Inode_time_t(this);
        st_birthtimensec = new StructLayout_SignedLong(this);
        st_size = new StructLayout_Signed64(this);
        st_blocks = new StructLayout_Signed64(this);
        st_blksize = new StructLayout_Signed32(this);
        st_flags = new StructLayout_Signed32(this);
        st_gen = new StructLayout_Signed32(this);
        st_lspare = new StructLayout_Signed32(this);
        st_qspare0 = new StructLayout_Signed64(this);
        st_qspare1 = new StructLayout_Signed64(this);
    }

}