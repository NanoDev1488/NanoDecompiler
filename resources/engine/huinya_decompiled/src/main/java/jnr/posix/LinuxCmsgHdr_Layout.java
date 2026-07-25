// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxCmsgHdr.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_size_t;

public class LinuxCmsgHdr_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_size_t cmsg_len;
  public final StructLayout_Signed32 cmsg_level;
  public final StructLayout_Signed32 cmsg_type;

  protected LinuxCmsgHdr_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        cmsg_len = new StructLayout_size_t(this);
        cmsg_level = new StructLayout_Signed32(this);
        cmsg_type = new StructLayout_Signed32(this);
    }

}