// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisPOSIX.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_int16_t;
import jnr.ffi.StructLayout_int32_t;
import jnr.ffi.StructLayout_off_t;
import jnr.ffi.StructLayout_pid_t;

public class SolarisPOSIX_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_int16_t l_type;
  public final StructLayout_int16_t l_whence;
  public final StructLayout_off_t l_start;
  public final StructLayout_off_t l_len;
  public final StructLayout_int32_t l_sysid;
  public final StructLayout_pid_t l_pid;
  public final StructLayout_int32_t[] l_pad;

  protected SolarisPOSIX_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        l_type = new StructLayout_int16_t(this);
        l_whence = new StructLayout_int16_t(this);
        l_start = new StructLayout_off_t(this);
        l_len = new StructLayout_off_t(this);
        l_sysid = new StructLayout_int32_t(this);
        l_pid = new StructLayout_pid_t(this);
        l_pad = new StructLayout_int32_t[4];
    }

}