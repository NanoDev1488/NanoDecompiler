// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSPasswd.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_SignedLong;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.posix.MacOSPasswd_Anon1;

final class MacOSPasswd_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_UTF8StringRef pw_name;
  public final StructLayout_UTF8StringRef pw_passwd;
  public final StructLayout_Signed32 pw_uid;
  public final StructLayout_Signed32 pw_gid;
  public final StructLayout_SignedLong pw_change;
  public final StructLayout_UTF8StringRef pw_class;
  public final StructLayout_UTF8StringRef pw_gecos;
  public final StructLayout_UTF8StringRef pw_dir;
  public final StructLayout_UTF8StringRef pw_shell;
  public final StructLayout_SignedLong pw_expire;

  private MacOSPasswd_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        pw_name = new StructLayout_UTF8StringRef(this);
        pw_passwd = new StructLayout_UTF8StringRef(this);
        pw_uid = new StructLayout_Signed32(this);
        pw_gid = new StructLayout_Signed32(this);
        pw_change = new StructLayout_SignedLong(this);
        pw_class = new StructLayout_UTF8StringRef(this);
        pw_gecos = new StructLayout_UTF8StringRef(this);
        pw_dir = new StructLayout_UTF8StringRef(this);
        pw_shell = new StructLayout_UTF8StringRef(this);
        pw_expire = new StructLayout_SignedLong(this);
    }

   MacOSPasswd_Layout(Runtime arg0, MacOSPasswd_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}