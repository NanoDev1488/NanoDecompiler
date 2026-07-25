// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.LoadedLibrary
package jnr.ffi.provider;

import jnr.ffi.Runtime;

public interface LoadedLibrary {

  public abstract Runtime getRuntime();

}