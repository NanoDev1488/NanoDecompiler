// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStatMIPS64.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.ffi.StructLayout_Unsigned64;

public final class LinuxFileStatMIPS64_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Unsigned64 st_dev;
  public final StructLayout_Unsigned32 __pad01;
  public final StructLayout_Unsigned32 __pad02;
  public final StructLayout_Unsigned32 __pad03;
  public final StructLayout_Unsigned64 st_ino;
  public final StructLayout_Unsigned64 st_mode;
  public final StructLayout_Unsigned32 st_nlink;
  public final StructLayout_Unsigned32 st_uid;
  public final StructLayout_Unsigned32 st_gid;
  public final StructLayout_Unsigned64 st_rdev;
  public final StructLayout_Unsigned32 __pad11;
  public final StructLayout_Unsigned32 __pad12;
  public final StructLayout_Unsigned32 __pad13;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Unsigned64 st_atime;
  public final StructLayout_Unsigned64 st_atimensec;
  public final StructLayout_Unsigned64 st_mtime;
  public final StructLayout_Unsigned64 st_mtimensec;
  public final StructLayout_Unsigned64 st_ctime;
  public final StructLayout_Unsigned64 st_ctimensec;
  public final StructLayout_Unsigned64 st_blksize;
  public final StructLayout_Unsigned32 __pad20;
  public final StructLayout_Unsigned64 st_blocks;

  public LinuxFileStatMIPS64_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_Unsigned64(this);
        __pad01 = new StructLayout_Unsigned32(this);
        __pad02 = new StructLayout_Unsigned32(this);
        __pad03 = new StructLayout_Unsigned32(this);
        st_ino = new StructLayout_Unsigned64(this);
        st_mode = new StructLayout_Unsigned64(this);
        st_nlink = new StructLayout_Unsigned32(this);
        st_uid = new StructLayout_Unsigned32(this);
        st_gid = new StructLayout_Unsigned32(this);
        st_rdev = new StructLayout_Unsigned64(this);
        __pad11 = new StructLayout_Unsigned32(this);
        __pad12 = new StructLayout_Unsigned32(this);
        __pad13 = new StructLayout_Unsigned32(this);
        st_size = new StructLayout_Signed64(this);
        st_atime = new StructLayout_Unsigned64(this);
        st_atimensec = new StructLayout_Unsigned64(this);
        st_mtime = new StructLayout_Unsigned64(this);
        st_mtimensec = new StructLayout_Unsigned64(this);
        st_ctime = new StructLayout_Unsigned64(this);
        st_ctimensec = new StructLayout_Unsigned64(this);
        st_blksize = new StructLayout_Unsigned64(this);
        __pad20 = new StructLayout_Unsigned32(this);
        st_blocks = new StructLayout_Unsigned64(this);
    }

}