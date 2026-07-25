// исходный (обфусцированный) внутренний класс: jnr.posix.NativeTimes
package jnr.posix;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_clock_t;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.LibC;
import jnr.posix.NativePOSIX;
import jnr.posix.NativeTimes_Layout;
import jnr.posix.Times;

public final class NativeTimes implements Times {

    // ---- поля ----
  private static final NativeTimes_Layout layout;
  final Pointer memory;

    static {
        layout = new NativeTimes_Layout(Runtime.getSystemRuntime());
    }

  static NativeTimes times(BaseNativePOSIX arg0) {
        NativeTimes __stk1;
        NativeTimes var1 = new NativeTimes(arg0);
        __stk1 = arg0.libc().times(var1) != -1L ? var1 : null;
        return ((NativeTimes) __stk1);
    }

   NativeTimes(NativePOSIX arg0) { // было: <init>
        super();
        memory = Memory.allocate(arg0.getRuntime(), layout.size());
    }

  public long utime() {
        return layout.tms_utime.get(memory);
    }

  public long stime() {
        return layout.tms_stime.get(memory);
    }

  public long cutime() {
        return layout.tms_cutime.get(memory);
    }

  public long cstime() {
        return layout.tms_cstime.get(memory);
    }

}