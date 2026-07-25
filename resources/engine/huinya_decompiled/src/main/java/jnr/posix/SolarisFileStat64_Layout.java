// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisFileStat64.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Signed8;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_UnsignedLong;

final class SolarisFileStat64_Layout extends StructLayout {

    // ---- поля ----
  public static final int _ST_FSTYPSZ = 16;
  public final StructLayout_UnsignedLong st_dev;
  public final StructLayout_Signed64 st_ino;
  public final StructLayout_Signed32 st_mode;
  public final StructLayout_Signed32 st_nlink;
  public final StructLayout_Signed32 st_uid;
  public final StructLayout_Signed32 st_gid;
  public final StructLayout_UnsignedLong st_rdev;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_SignedLong st_atim_sec;
  public final StructLayout_SignedLong st_atim_nsec;
  public final StructLayout_SignedLong st_mtim_sec;
  public final StructLayout_SignedLong st_mtim_nsec;
  public final StructLayout_SignedLong st_ctim_sec;
  public final StructLayout_SignedLong st_ctim_nsec;
  public final StructLayout_Signed32 st_blksize;
  public final StructLayout_Signed64 st_blocks;
  public final StructLayout_Signed8[] st_fstype;

   SolarisFileStat64_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_UnsignedLong(this);
        st_ino = new StructLayout_Signed64(this);
        st_mode = new StructLayout_Signed32(this);
        st_nlink = new StructLayout_Signed32(this);
        st_uid = new StructLayout_Signed32(this);
        st_gid = new StructLayout_Signed32(this);
        st_rdev = new StructLayout_UnsignedLong(this);
        st_size = new StructLayout_Signed64(this);
        st_atim_sec = new StructLayout_SignedLong(this);
        st_atim_nsec = new StructLayout_SignedLong(this);
        st_mtim_sec = new StructLayout_SignedLong(this);
        st_mtim_nsec = new StructLayout_SignedLong(this);
        st_ctim_sec = new StructLayout_SignedLong(this);
        st_ctim_nsec = new StructLayout_SignedLong(this);
        st_blksize = new StructLayout_Signed32(this);
        st_blocks = new StructLayout_Signed64(this);
        st_fstype = ((StructLayout_Signed8[]) array(new StructLayout_Signed8[16]));
    }

}