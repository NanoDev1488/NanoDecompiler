// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsFileStat.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed16;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.posix.WindowsFileStat_Anon1;

final class WindowsFileStat_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Signed32 st_dev;
  public final StructLayout_Signed16 st_ino;
  public final StructLayout_Signed16 st_mode;
  public final StructLayout_Signed16 st_nlink;
  public final StructLayout_Signed16 st_uid;
  public final StructLayout_Signed16 st_gid;
  public final StructLayout_Signed32 st_rdev;
  public final StructLayout_Signed64 st_size;
  public final StructLayout_Signed64 st_atime;
  public final StructLayout_Signed64 st_mtime;
  public final StructLayout_Signed64 st_ctime;

  private WindowsFileStat_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_dev = new StructLayout_Signed32(this);
        st_ino = new StructLayout_Signed16(this);
        st_mode = new StructLayout_Signed16(this);
        st_nlink = new StructLayout_Signed16(this);
        st_uid = new StructLayout_Signed16(this);
        st_gid = new StructLayout_Signed16(this);
        st_rdev = new StructLayout_Signed32(this);
        st_size = new StructLayout_Signed64(this);
        st_atime = new StructLayout_Signed64(this);
        st_mtime = new StructLayout_Signed64(this);
        st_ctime = new StructLayout_Signed64(this);
    }

   WindowsFileStat_Layout(Runtime arg0, WindowsFileStat_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}