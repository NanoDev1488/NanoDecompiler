// исходный (обфусцированный) внутренний класс: jnr.posix.AixFileStat.Layout
package jnr.posix;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Padding;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned16;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;
import jnr.posix.AixFileStat_Anon1;

final class AixFileStat_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Unsigned64 st_dev;
  public final StructLayout_Signed64 st_ino;
  public final StructLayout_Unsigned32 st_mode;
  public final StructLayout_Signed16 st_nlink;
  public final StructLayout_Unsigned16 st_flag;
  public final StructLayout_Unsigned32 st_uid;
  public final StructLayout_Unsigned32 st_gid;
  public final StructLayout_Unsigned64 st_rdev;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed64 st_atime;
  public final StructLayout_Signed32 st_atime_n;
  public final StructLayout_Signed32 st_pad1;
  public final StructLayout_Signed64 st_mtime;
  public final StructLayout_Signed32 st_mtime_n;
  public final StructLayout_Signed32 st_pad2;
  public final StructLayout_Signed64 st_ctime;
  public final StructLayout_Signed32 st_ctime_n;
  public final StructLayout_Signed32 st_pad3;
  public final StructLayout_Unsigned64 st_blksize;
  public final StructLayout_Unsigned64 st_blocks;
  public final StructLayout_Signed32 st_vfstype;
  public final StructLayout_Unsigned32 st_vfs;
  public final StructLayout_Unsigned32 st_type;
  public final StructLayout_Unsigned32 st_gen;
  public final StructLayout_Padding st_reserved;

  private AixFileStat_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_Unsigned64(this);
        st_ino = new StructLayout_Signed64(this);
        st_mode = new StructLayout_Unsigned32(this);
        st_nlink = new StructLayout_Signed16(this);
        st_flag = new StructLayout_Unsigned16(this);
        st_uid = new StructLayout_Unsigned32(this);
        st_gid = new StructLayout_Unsigned32(this);
        st_rdev = new StructLayout_Unsigned64(this);
        st_size = new StructLayout_Signed64(this);
        st_atime = new StructLayout_Signed64(this);
        st_atime_n = new StructLayout_Signed32(this);
        st_pad1 = new StructLayout_Signed32(this);
        st_mtime = new StructLayout_Signed64(this);
        st_mtime_n = new StructLayout_Signed32(this);
        st_pad2 = new StructLayout_Signed32(this);
        st_ctime = new StructLayout_Signed64(this);
        st_ctime_n = new StructLayout_Signed32(this);
        st_pad3 = new StructLayout_Signed32(this);
        st_blksize = new StructLayout_Unsigned64(this);
        st_blocks = new StructLayout_Unsigned64(this);
        st_vfstype = new StructLayout_Signed32(this);
        st_vfs = new StructLayout_Unsigned32(this);
        st_type = new StructLayout_Unsigned32(this);
        st_gen = new StructLayout_Unsigned32(this);
        st_reserved = new StructLayout_Padding(this, NativeType.UINT, 11);
    }

   AixFileStat_Layout(Runtime arg0, AixFileStat_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}