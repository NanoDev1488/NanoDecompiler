// исходный (обфусцированный) внутренний класс: jnr.posix.JavaPOSIX.FakePasswd
package jnr.posix;

import jnr.posix.JavaPOSIX_LoginInfo;
import jnr.posix.Passwd;

final class JavaPOSIX_FakePasswd implements Passwd {

  private JavaPOSIX_FakePasswd() { // было: <init>
        super();
    }

  public String getLoginName() {
        return JavaPOSIX_LoginInfo.USERNAME;
    }

  public String getPassword() {
        return "";
    }

  public long getUID() {
        return ((long) JavaPOSIX_LoginInfo.UID);
    }

  public long getGID() {
        return ((long) JavaPOSIX_LoginInfo.GID);
    }

  public int getPasswdChangeTime() {
        return 0;
    }

  public String getAccessClass() {
        return "";
    }

  public String getGECOS() {
        return getLoginName();
    }

  public String getHome() {
        return "/";
    }

  public String getShell() {
        return "/bin/sh";
    }

  public int getExpire() {
        return -1;
    }

}