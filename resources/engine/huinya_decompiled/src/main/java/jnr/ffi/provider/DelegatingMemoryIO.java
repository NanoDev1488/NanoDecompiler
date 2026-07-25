// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.DelegatingMemoryIO
package jnr.ffi.provider;

import jnr.ffi.Pointer;

public interface DelegatingMemoryIO {

  public abstract Pointer getDelegatedMemoryIO();

}