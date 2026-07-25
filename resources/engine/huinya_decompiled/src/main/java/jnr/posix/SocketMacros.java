// исходный (обфусцированный) внутренний класс: jnr.posix.SocketMacros
package jnr.posix;

import jnr.ffi.Pointer;

public interface SocketMacros {

  public abstract int CMSG_SPACE(int arg0);

  public abstract int CMSG_LEN(int arg0);

  public abstract Pointer CMSG_DATA(Pointer arg0);

}