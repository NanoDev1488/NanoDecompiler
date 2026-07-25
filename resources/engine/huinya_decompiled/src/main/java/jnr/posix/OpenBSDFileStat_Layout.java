// исходный (обфусцированный) внутренний класс: jnr.posix.OpenBSDFileStat.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;
import jnr.posix.OpenBSDFileStat_Anon1;
import jnr.posix.OpenBSDFileStat_Layout_dev_t;
import jnr.posix.OpenBSDFileStat_Layout_time_t;

final class OpenBSDFileStat_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Unsigned32 st_mode;
  public final OpenBSDFileStat_Layout_dev_t st_dev;
  public final StructLayout_Unsigned64 st_ino;
  public final StructLayout_Unsigned32 st_nlink;
  public final StructLayout_Unsigned32 st_uid;
  public final StructLayout_Unsigned32 st_gid;
  public final OpenBSDFileStat_Layout_dev_t st_rdev;
  public final OpenBSDFileStat_Layout_time_t st_atime;
  public final StructLayout_SignedLong st_atimensec;
  public final OpenBSDFileStat_Layout_time_t st_mtime;
  public final StructLayout_SignedLong st_mtimensec;
  public final OpenBSDFileStat_Layout_time_t st_ctime;
  public final StructLayout_SignedLong st_ctimensec;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed64 st_blocks;
  public final StructLayout_Unsigned32 st_blksize;
  public final StructLayout_Unsigned32 st_flags;
  public final StructLayout_Unsigned32 st_gen;
  public final OpenBSDFileStat_Layout_time_t st_birthtime;
  public final StructLayout_SignedLong st_birthtimensec;

  private OpenBSDFileStat_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_mode = new StructLayout_Unsigned32(this);
        st_dev = new OpenBSDFileStat_Layout_dev_t(this);
        st_ino = new StructLayout_Unsigned64(this);
        st_nlink = new StructLayout_Unsigned32(this);
        st_uid = new StructLayout_Unsigned32(this);
        st_gid = new StructLayout_Unsigned32(this);
        st_rdev = new OpenBSDFileStat_Layout_dev_t(this);
        st_atime = new OpenBSDFileStat_Layout_time_t(this);
        st_atimensec = new StructLayout_SignedLong(this);
        st_mtime = new OpenBSDFileStat_Layout_time_t(this);
        st_mtimensec = new StructLayout_SignedLong(this);
        st_ctime = new OpenBSDFileStat_Layout_time_t(this);
        st_ctimensec = new StructLayout_SignedLong(this);
        st_size = new StructLayout_Signed64(this);
        st_blocks = new StructLayout_Signed64(this);
        st_blksize = new StructLayout_Unsigned32(this);
        st_flags = new StructLayout_Unsigned32(this);
        st_gen = new StructLayout_Unsigned32(this);
        st_birthtime = new OpenBSDFileStat_Layout_time_t(this);
        st_birthtimensec = new StructLayout_SignedLong(this);
    }

   OpenBSDFileStat_Layout(Runtime arg0, OpenBSDFileStat_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}