// исходный (обфусцированный) внутренний класс: jnr.posix.AixLibC
package jnr.posix;

import jnr.posix.AixFileStat;
import jnr.posix.UnixLibC;

public interface AixLibC extends UnixLibC {

  public abstract int stat64x(CharSequence arg0, AixFileStat arg1);

  public abstract int fstat64x(int arg0, AixFileStat arg1);

  public abstract int lstat64x(CharSequence arg0, AixFileStat arg1);

}