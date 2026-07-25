// исходный (обфусцированный) внутренний класс: jnr.posix.OpenBSDTimeval
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct_Signed64;
import jnr.ffi.Struct_SignedLong;
import jnr.posix.Timeval;

public final class OpenBSDTimeval extends Timeval {

    // ---- поля ----
  public final Struct_Signed64 tv_sec;
  public final Struct_SignedLong tv_usec;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !OpenBSDTimeval.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

  public OpenBSDTimeval(Runtime arg0) { // было: <init>
        super(arg0);
        tv_sec = new Struct_Signed64(this);
        tv_usec = new Struct_SignedLong(this);
    }

  public void setTime(long[] arg0) {
        if ($assertionsDisabled) {
            tv_sec.set(arg0[0]);
            tv_usec.set(arg0[1]);
            return;
        } else {
            if (arg0.length == 2) {
                tv_sec.set(arg0[0]);
                tv_usec.set(arg0[1]);
                return;
            } else {
                throw new AssertionError();
            }
        }
    }

  public void sec(long arg0) {
        tv_sec.set(arg0);
    }

  public void usec(long arg0) {
        tv_usec.set(arg0);
    }

  public long sec() {
        return tv_sec.get();
    }

  public long usec() {
        return tv_usec.get();
    }

}