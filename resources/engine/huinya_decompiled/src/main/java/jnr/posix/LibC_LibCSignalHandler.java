// исходный (обфусцированный) внутренний класс: jnr.posix.LibC.LibCSignalHandler
package jnr.posix;

import jnr.ffi.annotations.Delegate;

public interface LibC_LibCSignalHandler {

    @Delegate
  public abstract void signal(int arg0);

}