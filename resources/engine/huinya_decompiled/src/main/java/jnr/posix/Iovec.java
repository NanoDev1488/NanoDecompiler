// исходный (обфусцированный) внутренний класс: jnr.posix.Iovec
package jnr.posix;

import java.nio.ByteBuffer;

public interface Iovec {

  public abstract ByteBuffer get();

  public abstract void set(ByteBuffer arg0);

}