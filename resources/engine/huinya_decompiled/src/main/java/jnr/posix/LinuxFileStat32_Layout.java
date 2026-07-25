// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxFileStat32.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.posix.LinuxFileStat32_Anon1;

final class LinuxFileStat32_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Signed64 st_dev;
  public final StructLayout_Signed16 __pad1;
  public final StructLayout_Signed32 st_ino;
  public final StructLayout_Signed32 st_mode;
  public final StructLayout_Signed32 st_nlink;
  public final StructLayout_Signed32 st_uid;
  public final StructLayout_Signed32 st_gid;
  public final StructLayout_Signed64 st_rdev;
  public final StructLayout_Signed16 __pad2;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed32 st_blksize;
  public final StructLayout_Signed32 st_blocks;
  public final StructLayout_Signed32 __unused4;
  public final StructLayout_Signed32 st_atim_sec;
  public final StructLayout_Signed32 st_atim_nsec;
  public final StructLayout_Signed32 st_mtim_sec;
  public final StructLayout_Signed32 st_mtim_nsec;
  public final StructLayout_Signed32 st_ctim_sec;
  public final StructLayout_Signed32 st_ctim_nsec;
  public final StructLayout_Signed64 __unused5;

  private LinuxFileStat32_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_Signed64(this);
        __pad1 = new StructLayout_Signed16(this);
        st_ino = new StructLayout_Signed32(this);
        st_mode = new StructLayout_Signed32(this);
        st_nlink = new StructLayout_Signed32(this);
        st_uid = new StructLayout_Signed32(this);
        st_gid = new StructLayout_Signed32(this);
        st_rdev = new StructLayout_Signed64(this);
        __pad2 = new StructLayout_Signed16(this);
        st_size = new StructLayout_Signed64(this);
        st_blksize = new StructLayout_Signed32(this);
        st_blocks = new StructLayout_Signed32(this);
        __unused4 = new StructLayout_Signed32(this);
        st_atim_sec = new StructLayout_Signed32(this);
        st_atim_nsec = new StructLayout_Signed32(this);
        st_mtim_sec = new StructLayout_Signed32(this);
        st_mtim_nsec = new StructLayout_Signed32(this);
        st_ctim_sec = new StructLayout_Signed32(this);
        st_ctim_nsec = new StructLayout_Signed32(this);
        __unused5 = new StructLayout_Signed64(this);
    }

   LinuxFileStat32_Layout(Runtime arg0, LinuxFileStat32_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}