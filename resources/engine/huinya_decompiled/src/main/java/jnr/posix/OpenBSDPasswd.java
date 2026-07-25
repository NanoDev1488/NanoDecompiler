// исходный (обфусцированный) внутренний класс: jnr.posix.OpenBSDPasswd
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.posix.NativePasswd;
import jnr.posix.OpenBSDPasswd_Layout;
import jnr.posix.Passwd;

public class OpenBSDPasswd extends NativePasswd implements Passwd {

    // ---- поля ----
  private static final OpenBSDPasswd_Layout layout;

    static {
        layout = new OpenBSDPasswd_Layout(Runtime.getSystemRuntime(), null);
    }

   OpenBSDPasswd(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public String getAccessClass() {
        return layout.pw_class.get(memory);
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
        return layout.pw_change.intValue(memory);
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
        return layout.pw_expire.intValue(memory);
    }

}