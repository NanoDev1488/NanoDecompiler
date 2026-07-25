// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxMsgHdr.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_size_t;
import jnr.ffi.StructLayout_socklen_t;

public class LinuxMsgHdr_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Pointer msg_name;
  public final StructLayout_socklen_t msg_namelen;
  public final StructLayout_Pointer msg_iov;
  public final StructLayout_size_t msg_iovlen;
  public final StructLayout_Pointer msg_control;
  public final StructLayout_size_t msg_controllen;
  public final StructLayout_Signed32 msg_flags;

  protected LinuxMsgHdr_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        msg_name = new StructLayout_Pointer(this);
        msg_namelen = new StructLayout_socklen_t(this);
        msg_iov = new StructLayout_Pointer(this);
        msg_iovlen = new StructLayout_size_t(this);
        msg_control = new StructLayout_Pointer(this);
        msg_controllen = new StructLayout_size_t(this);
        msg_flags = new StructLayout_Signed32(this);
    }

}