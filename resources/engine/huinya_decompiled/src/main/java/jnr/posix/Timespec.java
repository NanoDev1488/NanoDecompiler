// исходный (обфусцированный) внутренний класс: jnr.posix.Timespec
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class Timespec extends Struct {

  public Timespec(Runtime arg0) { // было: <init>
        super(arg0);
    }

  public abstract void setTime(long[] arg0);

  public abstract void sec(long arg0);

  public abstract void nsec(long arg0);

  public abstract long sec();

  public abstract long nsec();

}