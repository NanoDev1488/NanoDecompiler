// исходный (обфусцированный) внутренний класс: jnr.posix.FreeBSDFileStat12.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.posix.FreeBSDFileStat12_Anon1;
import jnr.posix.FreeBSDFileStat12_Layout_dev_t;
import jnr.posix.FreeBSDFileStat12_Layout_time_t;

final class FreeBSDFileStat12_Layout extends StructLayout {

    // ---- поля ----
  public final FreeBSDFileStat12_Layout_dev_t st_dev;
  public final StructLayout_Signed64 st_ino;
  public final StructLayout_Signed32 st_nlink_upper;
  public final StructLayout_Signed32 st_nlink;
  public final StructLayout_Signed16 st_mode;
  public final StructLayout_Signed16 st_padding0;
  public final StructLayout_Signed32 st_uid;
  public final StructLayout_Signed32 st_gid;
  public final StructLayout_Signed32 st_padding1;
  public final FreeBSDFileStat12_Layout_dev_t st_rdev;
  public final FreeBSDFileStat12_Layout_time_t st_atime;
  public final StructLayout_SignedLong st_atimensec;
  public final FreeBSDFileStat12_Layout_time_t st_mtime;
  public final StructLayout_SignedLong st_mtimensec;
  public final FreeBSDFileStat12_Layout_time_t st_ctime;
  public final StructLayout_SignedLong st_ctimensec;
  public final FreeBSDFileStat12_Layout_time_t st_birthtime;
  public final StructLayout_SignedLong st_birthtimensec;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed64 st_blocks;
  public final StructLayout_Signed32 st_blksize;
  public final StructLayout_Signed32 st_flags;
  public final StructLayout_Signed64 st_gen;
  public final StructLayout_Signed64 st_qspare0;

  private FreeBSDFileStat12_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new FreeBSDFileStat12_Layout_dev_t(this);
        st_ino = new StructLayout_Signed64(this);
        st_nlink_upper = new StructLayout_Signed32(this);
        st_nlink = new StructLayout_Signed32(this);
        st_mode = new StructLayout_Signed16(this);
        st_padding0 = new StructLayout_Signed16(this);
        st_uid = new StructLayout_Signed32(this);
        st_gid = new StructLayout_Signed32(this);
        st_padding1 = new StructLayout_Signed32(this);
        st_rdev = new FreeBSDFileStat12_Layout_dev_t(this);
        st_atime = new FreeBSDFileStat12_Layout_time_t(this);
        st_atimensec = new StructLayout_SignedLong(this);
        st_mtime = new FreeBSDFileStat12_Layout_time_t(this);
        st_mtimensec = new StructLayout_SignedLong(this);
        st_ctime = new FreeBSDFileStat12_Layout_time_t(this);
        st_ctimensec = new StructLayout_SignedLong(this);
        st_birthtime = new FreeBSDFileStat12_Layout_time_t(this);
        st_birthtimensec = new StructLayout_SignedLong(this);
        st_size = new StructLayout_Signed64(this);
        st_blocks = new StructLayout_Signed64(this);
        st_blksize = new StructLayout_Signed32(this);
        st_flags = new StructLayout_Signed32(this);
        st_gen = new StructLayout_Signed64(this);
        st_qspare0 = new StructLayout_Signed64(this);
    }

   FreeBSDFileStat12_Layout(Runtime arg0, FreeBSDFileStat12_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}