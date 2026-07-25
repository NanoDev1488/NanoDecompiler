// исходный (обфусцированный) внутренний класс: jnr.posix.RLimit
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class RLimit extends Struct {

  protected RLimit(Runtime arg0) { // было: <init>
        super(arg0);
    }

  public abstract void init(long arg0, long arg1);

  public abstract long rlimCur();

  public abstract long rlimMax();

}