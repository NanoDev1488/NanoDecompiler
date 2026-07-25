// исходный (обфусцированный) внутренний класс: jnr.posix.MsgHdr
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.posix.CmsgHdr;

public interface MsgHdr {

  public abstract void setName(String arg0);

  public abstract String getName();

  public abstract void setIov(ByteBuffer[] arg0);

  public abstract ByteBuffer[] getIov();

  public abstract void setFlags(int arg0);

  public abstract int getFlags();

  public abstract CmsgHdr allocateControl(int arg0);

  public abstract CmsgHdr[] allocateControls(int[] arg0);

  public abstract CmsgHdr[] getControls();

  public abstract int getControlLen();

}