// исходный (обфусцированный) внутренний класс: jnr.posix.Crypt
package jnr.posix;

import jnr.ffi.Pointer;

public interface Crypt {

  public abstract CharSequence crypt(CharSequence arg0, CharSequence arg1);

  public abstract Pointer crypt(byte[] arg0, byte[] arg1);

}