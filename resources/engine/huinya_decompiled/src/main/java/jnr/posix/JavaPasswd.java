// исходный (обфусцированный) внутренний класс: jnr.posix.JavaPasswd
package jnr.posix;

import jnr.posix.JavaPOSIX_LoginInfo;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;

final class JavaPasswd implements Passwd {

    // ---- поля ----
  private final POSIXHandler handler;

  public JavaPasswd(POSIXHandler arg0) { // было: <init>
        super();
        handler = arg0;
    }

  public String getAccessClass() {
        handler.unimplementedError("passwd.pw_access unimplemented");
        return null;
    }

  public String getGECOS() {
        return getLoginName();
    }

  public long getGID() {
        return ((long) JavaPOSIX_LoginInfo.GID);
    }

  public String getHome() {
        return System.getProperty("user.home");
    }

  public String getLoginName() {
        return System.getProperty("user.name");
    }

  public int getPasswdChangeTime() {
        handler.unimplementedError("passwd.pw_change unimplemented");
        return 0;
    }

  public String getPassword() {
        handler.unimplementedError("passwd.pw_passwd unimplemented");
        return null;
    }

  public String getShell() {
        handler.unimplementedError("passwd.pw_env unimplemented");
        return null;
    }

  public long getUID() {
        return ((long) JavaPOSIX_LoginInfo.UID);
    }

  public int getExpire() {
        handler.unimplementedError("passwd.expire unimplemented");
        return -1;
    }

}