// исходный (обфусцированный) внутренний класс: jnr.posix.FreeBSDSocketMacros
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.posix.FreeBSDCmsgHdr;
import jnr.posix.FreeBSDCmsgHdr_Layout;
import jnr.posix.SocketMacros;

public class FreeBSDSocketMacros implements SocketMacros {

    // ---- поля ----
  public static final FreeBSDSocketMacros INSTANCE;

    static {
        INSTANCE = new FreeBSDSocketMacros();
    }

  public FreeBSDSocketMacros() { // было: <init>
        super();
    }

  public int CMSG_ALIGN(int arg0) {
        int var2 = Runtime.getSystemRuntime().findType(TypeAlias.size_t).size();
        return arg0 + var2 - 1 & (var2 - 1 ^ -1);
    }

  public int CMSG_SPACE(int arg0) {
        return CMSG_ALIGN(FreeBSDCmsgHdr.layout.size()) + CMSG_ALIGN(arg0);
    }

  public int CMSG_LEN(int arg0) {
        return CMSG_ALIGN(FreeBSDCmsgHdr.layout.size()) + arg0;
    }

  public Pointer CMSG_DATA(Pointer arg0) {
        return arg0.slice(((long) CMSG_ALIGN(FreeBSDCmsgHdr.layout.size())));
    }

}