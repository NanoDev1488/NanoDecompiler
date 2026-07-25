// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisPasswd
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.posix.NativePasswd;
import jnr.posix.Passwd;
import jnr.posix.SolarisPasswd_Layout;

public class SolarisPasswd extends NativePasswd implements Passwd {

    // ---- поля ----
  private static final SolarisPasswd_Layout layout;

    static {
        layout = new SolarisPasswd_Layout(Runtime.getSystemRuntime(), null);
    }

  public SolarisPasswd(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public String getAccessClass() {
        return "unknown";
    }

  public String getGECOS() {
        return layout.pw_gecos.get(memory);
    }

  public long getGID() {
        return ((long) layout.pw_gid.get(memory));
    }

  public String getHome() {
        return layout.pw_dir.get(memory);
    }

  public String getLoginName() {
        return layout.pw_name.get(memory);
    }

  public int getPasswdChangeTime() {
        return 0;
    }

  public String getPassword() {
        return layout.pw_passwd.get(memory);
    }

  public String getShell() {
        return layout.pw_shell.get(memory);
    }

  public long getUID() {
        return ((long) layout.pw_uid.get(memory));
    }

  public int getExpire() {
        return 2147483647;
    }

}