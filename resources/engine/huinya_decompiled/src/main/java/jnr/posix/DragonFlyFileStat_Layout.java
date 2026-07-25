// исходный (обфусцированный) внутренний класс: jnr.posix.DragonFlyFileStat.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_Unsigned16;
import jnr.posix.DragonFlyFileStat_Anon1;
import jnr.posix.DragonFlyFileStat_Layout_dev_t;
import jnr.posix.DragonFlyFileStat_Layout_time_t;

final class DragonFlyFileStat_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Signed64 st_ino;
  public final StructLayout_Signed32 st_nlink;
  public final DragonFlyFileStat_Layout_dev_t st_dev;
  public final StructLayout_Unsigned16 st_mode;
  public final StructLayout_Unsigned16 st_padding1;
  public final StructLayout_Signed32 st_uid;
  public final StructLayout_Signed32 st_gid;
  public final DragonFlyFileStat_Layout_dev_t st_rdev;
  public final DragonFlyFileStat_Layout_time_t st_atim;
  public final DragonFlyFileStat_Layout_time_t st_atimnsec;
  public final DragonFlyFileStat_Layout_time_t st_mtim;
  public final DragonFlyFileStat_Layout_time_t st_mtimnsec;
  public final DragonFlyFileStat_Layout_time_t st_ctim;
  public final DragonFlyFileStat_Layout_time_t st_ctimnsec;
  public final StructLayout_Signed32 st_size;
  public final StructLayout_Signed32 st_blocks;
  public final StructLayout_Signed32 st_blksize;
  public final StructLayout_Signed32 st_flags;
  public final StructLayout_Signed32 st_gen;
  public final StructLayout_Signed32 st_lspare;
  public final StructLayout_Signed64 st_qspare1;
  public final StructLayout_Signed64 st_qspare2;

  private DragonFlyFileStat_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        st_ino = new StructLayout_Signed64(this);
        st_nlink = new StructLayout_Signed32(this);
        st_dev = new DragonFlyFileStat_Layout_dev_t(this);
        st_mode = new StructLayout_Unsigned16(this);
        st_padding1 = new StructLayout_Unsigned16(this);
        st_uid = new StructLayout_Signed32(this);
        st_gid = new StructLayout_Signed32(this);
        st_rdev = new DragonFlyFileStat_Layout_dev_t(this);
        st_atim = new DragonFlyFileStat_Layout_time_t(this);
        st_atimnsec = new DragonFlyFileStat_Layout_time_t(this);
        st_mtim = new DragonFlyFileStat_Layout_time_t(this);
        st_mtimnsec = new DragonFlyFileStat_Layout_time_t(this);
        st_ctim = new DragonFlyFileStat_Layout_time_t(this);
        st_ctimnsec = new DragonFlyFileStat_Layout_time_t(this);
        st_size = new StructLayout_Signed32(this);
        st_blocks = new StructLayout_Signed32(this);
        st_blksize = new StructLayout_Signed32(this);
        st_flags = new StructLayout_Signed32(this);
        st_gen = new StructLayout_Signed32(this);
        st_lspare = new StructLayout_Signed32(this);
        st_qspare1 = new StructLayout_Signed64(this);
        st_qspare2 = new StructLayout_Signed64(this);
    }

   DragonFlyFileStat_Layout(Runtime arg0, DragonFlyFileStat_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}