// исходный (обфусцированный) внутренний класс: jnr.posix.NanosecondFileStat
package jnr.posix;

import jnr.posix.FileStat;

public interface NanosecondFileStat extends FileStat {

  public abstract long aTimeNanoSecs();

  public abstract long cTimeNanoSecs();

  public abstract long mTimeNanoSecs();

}