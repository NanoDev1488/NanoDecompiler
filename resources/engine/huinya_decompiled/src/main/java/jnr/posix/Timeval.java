// исходный (обфусцированный) внутренний класс: jnr.posix.Timeval
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class Timeval extends Struct {

  public Timeval(Runtime arg0) { // было: <init>
        super(arg0);
    }

  public abstract void setTime(long[] arg0);

  public abstract void sec(long arg0);

  public abstract void usec(long arg0);

  public abstract long sec();

  public abstract long usec();

}