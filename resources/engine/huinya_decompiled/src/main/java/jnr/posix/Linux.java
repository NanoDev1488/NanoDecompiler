// исходный (обфусцированный) внутренний класс: jnr.posix.Linux
package jnr.posix;

import jnr.constants.platform.PosixFadvise;
import jnr.posix.POSIX;

public interface Linux extends POSIX {

  public abstract int ioprio_get(int arg0, int arg1);

  public abstract int ioprio_set(int arg0, int arg1, int arg2);

  public abstract int posix_fadvise(int arg0, long arg1, long arg2, PosixFadvise arg3);

}