// исходный (обфусцированный) внутренний класс: jnr.posix.AixPasswd.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.ffi.StructLayout_gid_t;
import jnr.ffi.StructLayout_uid_t;
import jnr.posix.AixPasswd_Anon1;

final class AixPasswd_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_UTF8StringRef pw_name;
  public final StructLayout_UTF8StringRef pw_passwd;
  public final StructLayout_uid_t pw_uid;
  public final StructLayout_gid_t pw_gid;
  public final StructLayout_UTF8StringRef pw_gecos;
  public final StructLayout_UTF8StringRef pw_dir;
  public final StructLayout_UTF8StringRef pw_shell;

  private AixPasswd_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        pw_name = new StructLayout_UTF8StringRef(this);
        pw_passwd = new StructLayout_UTF8StringRef(this);
        pw_uid = new StructLayout_uid_t(this);
        pw_gid = new StructLayout_gid_t(this);
        pw_gecos = new StructLayout_UTF8StringRef(this);
        pw_dir = new StructLayout_UTF8StringRef(this);
        pw_shell = new StructLayout_UTF8StringRef(this);
    }

   AixPasswd_Layout(Runtime arg0, AixPasswd_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}