// исходный (обфусцированный) внутренний класс: jnr.posix.AixPasswd
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.ffi.StructLayout_gid_t;
import jnr.ffi.StructLayout_uid_t;
import jnr.posix.AixPasswd_Layout;
import jnr.posix.NativePasswd;
import jnr.posix.Passwd;

public class AixPasswd extends NativePasswd implements Passwd {

    // ---- поля ----
  private static final AixPasswd_Layout layout;

    static {
        layout = new AixPasswd_Layout(Runtime.getSystemRuntime(), null);
    }

   AixPasswd(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public String getAccessClass() {
        return "unknown";
    }

  public String getGECOS() {
        return layout.pw_gecos.get(memory);
    }

  public long getGID() {
        return layout.pw_gid.get(memory);
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
        return layout.pw_uid.get(memory);
    }

  public int getExpire() {
        return 2147483647;
    }

}