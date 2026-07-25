// исходный (обфусцированный) внутренний класс: jnr.unixsocket.Credentials
package jnr.unixsocket;

import jnr.constants.platform.SocketLevel;
import jnr.constants.platform.SocketOption;
import jnr.ffi.Struct_gid_t;
import jnr.ffi.Struct_pid_t;
import jnr.ffi.Struct_uid_t;
import jnr.unixsocket.Native;
import jnr.unixsocket.Ucred;

public final class Credentials {

    // ---- поля ----
  private final Ucred ucred;

   Credentials(Ucred arg0) { // было: <init>
        super();
        ucred = arg0;
    }

  public int getPid() {
        return ucred.getPidField().intValue();
    }

  public int getUid() {
        return ucred.getUidField().intValue();
    }

  public int getGid() {
        return ucred.getGidField().intValue();
    }

  public String toString() {
        return String.format("[uid=%d gid=%d pid=%d]", new Object[]{Integer.valueOf(getUid()), Integer.valueOf(getGid()), Integer.valueOf(getPid())});
    }

  static Credentials getCredentials(int arg0) {
        Ucred var1 = new Ucred();
        int var2 = Native.getsockopt(arg0, SocketLevel.SOL_SOCKET, SocketOption.SO_PEERCRED, var1);
        if (var2 == 0) {
            return new Credentials(var1);
        } else {
            throw new UnsupportedOperationException(Native.getLastErrorString());
        }
    }

}