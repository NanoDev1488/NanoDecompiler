// исходный (обфусцированный) внутренний класс: jnr.posix.FreeBSDCmsgHdr
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_socklen_t;
import jnr.posix.BaseCmsgHdr;
import jnr.posix.FreeBSDCmsgHdr_Layout;
import jnr.posix.NativePOSIX;

class FreeBSDCmsgHdr extends BaseCmsgHdr {

    // ---- поля ----
  public static final FreeBSDCmsgHdr_Layout layout;

    static {
        layout = new FreeBSDCmsgHdr_Layout(Runtime.getSystemRuntime());
    }

  public FreeBSDCmsgHdr(NativePOSIX arg0, Pointer arg1) { // было: <init>
        super(arg0, arg1);
    }

  public FreeBSDCmsgHdr(NativePOSIX arg0, Pointer arg1, int arg2) { // было: <init>
        super(arg0, arg1, arg2);
    }

  public void setLevel(int arg0) {
        layout.cmsg_level.set(memory, arg0);
    }

  public int getLevel() {
        return layout.cmsg_level.get(memory);
    }

  public void setType(int arg0) {
        layout.cmsg_type.set(memory, arg0);
    }

  public int getType() {
        return layout.cmsg_type.get(memory);
    }

  public int getLen() {
        return ((int) layout.cmsg_len.get(memory));
    }

   void setLen(int arg0) {
        layout.cmsg_len.set(memory, ((long) arg0));
    }

  public String toString(String arg0) {
        StringBuffer var2 = new StringBuffer();
        var2.append(arg0).append("cmsg {\n");
        var2.append(arg0).append("  cmsg_len=").append(layout.cmsg_len.get(memory)).append("\n");
        var2.append(arg0).append("  cmsg_level=").append(layout.cmsg_level.get(memory)).append("\n");
        var2.append(arg0).append("  cmsg_type=").append(layout.cmsg_type.get(memory)).append("\n");
        var2.append(arg0).append("  cmsg_data=").append(getData()).append("\n");
        var2.append(arg0).append("}");
        return var2.toString();
    }

  public String toString() {
        return toString("");
    }

}