// исходный (обфусцированный) внутренний класс: jnr.unixsocket.SockAddrUnix.BSDSockAddrUnix
package jnr.unixsocket;

import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_UTF8String;
import jnr.ffi.Struct_Unsigned8;
import jnr.unixsocket.SockAddrUnix;

final class SockAddrUnix_BSDSockAddrUnix extends SockAddrUnix {

    // ---- поля ----
  public final Struct_Unsigned8 sun_len;
  public final Struct_Unsigned8 sun_family;
  public final Struct_UTF8String sun_addr;

   SockAddrUnix_BSDSockAddrUnix() { // было: <init>
        super();
        sun_len = new Struct_Unsigned8(this);
        sun_family = new Struct_Unsigned8(this);
        sun_addr = new Struct_UTF8String(this, 108);
    }

  public void setPath(String arg0) {
        super.setPath(arg0);
        sun_len.set(Integer.valueOf(arg0.length()));
    }

  protected Struct_UTF8String getPathField() {
        return sun_addr;
    }

  protected Struct_NumberField getFamilyField() {
        return sun_family;
    }

}