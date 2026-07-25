// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSSocketMacros
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.MacOSCmsgHdr;
import jnr.posix.MacOSCmsgHdr_Layout;
import jnr.posix.SocketMacros;

public class MacOSSocketMacros implements SocketMacros {

    // ---- поля ----
  public static final SocketMacros INSTANCE;

    static {
        INSTANCE = new MacOSSocketMacros();
    }

  public MacOSSocketMacros() { // было: <init>
        super();
    }

  public int __DARWIN_ALIGN32(int arg0) {
        return arg0 + 3 & -4;
    }

  public int CMSG_SPACE(int arg0) {
        return __DARWIN_ALIGN32(MacOSCmsgHdr.layout.size()) + __DARWIN_ALIGN32(arg0);
    }

  public int CMSG_LEN(int arg0) {
        return __DARWIN_ALIGN32(MacOSCmsgHdr.layout.size()) + arg0;
    }

  public Pointer CMSG_DATA(Pointer arg0) {
        return arg0.slice(((long) __DARWIN_ALIGN32(MacOSCmsgHdr.layout.size())));
    }

}