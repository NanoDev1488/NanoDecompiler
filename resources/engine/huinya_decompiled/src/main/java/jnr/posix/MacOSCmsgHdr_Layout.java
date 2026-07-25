// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSCmsgHdr.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_Unsigned32;

public class MacOSCmsgHdr_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Unsigned32 cmsg_len;
  public final StructLayout_Signed32 cmsg_level;
  public final StructLayout_Signed32 cmsg_type;

  protected MacOSCmsgHdr_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        cmsg_len = new StructLayout_Unsigned32(this);
        cmsg_level = new StructLayout_Signed32(this);
        cmsg_type = new StructLayout_Signed32(this);
    }

}