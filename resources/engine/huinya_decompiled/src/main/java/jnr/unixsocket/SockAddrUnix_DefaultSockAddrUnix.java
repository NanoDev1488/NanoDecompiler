// исходный (обфусцированный) внутренний класс: jnr.unixsocket.SockAddrUnix.DefaultSockAddrUnix
package jnr.unixsocket;

import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_UTF8String;
import jnr.ffi.Struct_Unsigned16;
import jnr.unixsocket.SockAddrUnix;

final class SockAddrUnix_DefaultSockAddrUnix extends SockAddrUnix {

    // ---- поля ----
  public final Struct_Unsigned16 sun_family;
  public final Struct_UTF8String sun_addr;

   SockAddrUnix_DefaultSockAddrUnix() { // было: <init>
        super();
        sun_family = new Struct_Unsigned16(this);
        sun_addr = new Struct_UTF8String(this, 108);
    }

  protected Struct_UTF8String getPathField() {
        return sun_addr;
    }

  protected Struct_NumberField getFamilyField() {
        return sun_family;
    }

}