// исходный (обфусцированный) внутренний класс: jnr.posix.NativeTimes.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_clock_t;

final class NativeTimes_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_clock_t tms_utime;
  public final StructLayout_clock_t tms_stime;
  public final StructLayout_clock_t tms_cutime;
  public final StructLayout_clock_t tms_cstime;

   NativeTimes_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        tms_utime = new StructLayout_clock_t(this);
        tms_stime = new StructLayout_clock_t(this);
        tms_cutime = new StructLayout_clock_t(this);
        tms_cstime = new StructLayout_clock_t(this);
    }

}