// исходный (обфусцированный) внутренний класс: jnr.posix.DefaultNativeTimespec
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct_SignedLong;
import jnr.posix.Timespec;

public final class DefaultNativeTimespec extends Timespec {

    // ---- поля ----
  public final Struct_SignedLong tv_sec;
  public final Struct_SignedLong tv_nsec;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !DefaultNativeTimespec.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

  public DefaultNativeTimespec(Runtime arg0) { // было: <init>
        super(arg0);
        tv_sec = new Struct_SignedLong(this);
        tv_nsec = new Struct_SignedLong(this);
    }

  public void setTime(long[] arg0) {
        if ($assertionsDisabled) {
            tv_sec.set(arg0[0]);
            tv_nsec.set(arg0[1]);
            return;
        } else {
            if (arg0.length == 2) {
                tv_sec.set(arg0[0]);
                tv_nsec.set(arg0[1]);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public void sec(long arg0) {
        tv_sec.set(arg0);
    }

  public void nsec(long arg0) {
        tv_nsec.set(arg0);
    }

  public long sec() {
        return tv_sec.get();
    }

  public long nsec() {
        return tv_nsec.get();
    }

}