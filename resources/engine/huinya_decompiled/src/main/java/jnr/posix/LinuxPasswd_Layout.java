// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPasswd.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_UTF8StringRef;
import jnr.posix.LinuxPasswd_Anon1;

final class LinuxPasswd_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_UTF8StringRef pw_name;
  public final StructLayout_UTF8StringRef pw_passwd;
  public final StructLayout_Signed32 pw_uid;
  public final StructLayout_Signed32 pw_gid;
  public final StructLayout_UTF8StringRef pw_gecos;
  public final StructLayout_UTF8StringRef pw_dir;
  public final StructLayout_UTF8StringRef pw_shell;

  private LinuxPasswd_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        pw_name = new StructLayout_UTF8StringRef(this);
        pw_passwd = new StructLayout_UTF8StringRef(this);
        pw_uid = new StructLayout_Signed32(this);
        pw_gid = new StructLayout_Signed32(this);
        pw_gecos = new StructLayout_UTF8StringRef(this);
        pw_dir = new StructLayout_UTF8StringRef(this);
        pw_shell = new StructLayout_UTF8StringRef(this);
    }

   LinuxPasswd_Layout(Runtime arg0, LinuxPasswd_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}