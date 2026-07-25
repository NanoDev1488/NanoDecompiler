// исходный (обфусцированный) внутренний класс: jnr.posix.OpenBSDPasswd.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed64;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.ffi.StructLayout_Unsigned32;
import jnr.posix.OpenBSDPasswd_Anon1;

final class OpenBSDPasswd_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_UTF8StringRef pw_name;
  public final StructLayout_UTF8StringRef pw_passwd;
  public final StructLayout_Unsigned32 pw_uid;
  public final StructLayout_Unsigned32 pw_gid;
  public final StructLayout_Signed64 pw_change;
  public final StructLayout_UTF8StringRef pw_class;
  public final StructLayout_UTF8StringRef pw_gecos;
  public final StructLayout_UTF8StringRef pw_dir;
  public final StructLayout_UTF8StringRef pw_shell;
  public final StructLayout_Signed64 pw_expire;

  private OpenBSDPasswd_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        pw_name = new StructLayout_UTF8StringRef(this);
        pw_passwd = new StructLayout_UTF8StringRef(this);
        pw_uid = new StructLayout_Unsigned32(this);
        pw_gid = new StructLayout_Unsigned32(this);
        pw_change = new StructLayout_Signed64(this);
        pw_class = new StructLayout_UTF8StringRef(this);
        pw_gecos = new StructLayout_UTF8StringRef(this);
        pw_dir = new StructLayout_UTF8StringRef(this);
        pw_shell = new StructLayout_UTF8StringRef(this);
        pw_expire = new StructLayout_Signed64(this);
    }

   OpenBSDPasswd_Layout(Runtime arg0, OpenBSDPasswd_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}