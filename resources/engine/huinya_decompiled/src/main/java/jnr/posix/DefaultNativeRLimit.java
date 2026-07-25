// исходный (обфусцированный) внутренний класс: jnr.posix.DefaultNativeRLimit
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct_UnsignedLong;
import jnr.posix.RLimit;

public class DefaultNativeRLimit extends RLimit {

    // ---- поля ----
  public final Struct_UnsignedLong rlim_cur;
  public final Struct_UnsignedLong rlim_max;

  protected DefaultNativeRLimit(Runtime arg0) { // было: <init>
        super(arg0);
        rlim_cur = new Struct_UnsignedLong(this);
        rlim_max = new Struct_UnsignedLong(this);
    }

  public void init(long arg0, long arg1) {
        rlim_cur.set(arg0);
        rlim_max.set(arg1);
    }

  public long rlimCur() {
        return rlim_cur.get();
    }

  public long rlimMax() {
        return rlim_max.get();
    }

}