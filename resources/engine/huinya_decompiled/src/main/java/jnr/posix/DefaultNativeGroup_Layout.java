// исходный (обфусцированный) внутренний класс: jnr.posix.DefaultNativeGroup.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_Signed32;
import jnr.ffi.StructLayout_UTF8StringRef;

final class DefaultNativeGroup_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_UTF8StringRef gr_name;
  public final StructLayout_UTF8StringRef gr_passwd;
  public final StructLayout_Signed32 gr_gid;
  public final StructLayout_Pointer gr_mem;

  public DefaultNativeGroup_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        gr_name = new StructLayout_UTF8StringRef(this);
        gr_passwd = new StructLayout_UTF8StringRef(this);
        gr_gid = new StructLayout_Signed32(this);
        gr_mem = new StructLayout_Pointer(this);
    }

}