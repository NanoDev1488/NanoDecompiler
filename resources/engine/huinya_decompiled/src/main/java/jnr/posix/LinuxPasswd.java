// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPasswd
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.posix.LinuxPasswd_Layout;
import jnr.posix.NativePasswd;
import jnr.posix.Passwd;

public final class LinuxPasswd extends NativePasswd implements Passwd {

    // ---- поля ----
  private static final LinuxPasswd_Layout layout;

    static {
        layout = new LinuxPasswd_Layout(Runtime.getSystemRuntime(), null);
    }

   LinuxPasswd(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public String getAccessClass() {
        return "";
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

  public String getPassword() {
        return layout.pw_passwd.get(memory);
    }

  public String getShell() {
        return layout.pw_shell.get(memory);
    }

  public long getUID() {
        return ((long) layout.pw_uid.get(memory));
    }

  public int getPasswdChangeTime() {
        return 0;
    }

  public int getExpire() {
        return 2147483647;
    }

}