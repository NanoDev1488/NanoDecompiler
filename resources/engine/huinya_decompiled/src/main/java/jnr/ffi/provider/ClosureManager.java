// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.ClosureManager
package jnr.ffi.provider;

import jnr.ffi.Pointer;

public interface ClosureManager {

  public abstract Object newClosure(Class arg0, Object arg1);

  public abstract Pointer getClosurePointer(Class arg0, Object arg1);

}