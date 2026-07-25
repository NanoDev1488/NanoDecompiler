// исходный (обфусцированный) внутренний класс: jnr.posix.Flock
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class Flock extends Struct {

  public Flock(Runtime arg0) { // было: <init>
        super(arg0);
    }

  public abstract void type(short arg0);

  public abstract void whence(short arg0);

  public abstract void start(long arg0);

  public abstract void len(long arg0);

  public abstract void pid(int arg0);

  public abstract short type();

  public abstract short whence();

  public abstract long start();

  public abstract long len();

  public abstract int pid();

}