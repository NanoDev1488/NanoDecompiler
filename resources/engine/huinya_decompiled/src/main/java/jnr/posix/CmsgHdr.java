// исходный (обфусцированный) внутренний класс: jnr.posix.CmsgHdr
package jnr.posix;

import java.nio.ByteBuffer;

public interface CmsgHdr {

  public abstract void setLevel(int arg0);

  public abstract int getLevel();

  public abstract void setType(int arg0);

  public abstract int getType();

  public abstract void setData(ByteBuffer arg0);

  public abstract ByteBuffer getData();

  public abstract int getLen();

}