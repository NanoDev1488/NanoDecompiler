// исходный (обфусцированный) внутренний класс: jnr.posix.BaseIovec.Layout
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_size_t;

public class BaseIovec_Layout extends StructLayout {

    // ---- поля ----
  public final StructLayout_Pointer iov_base;
  public final StructLayout_size_t iov_len;

  protected BaseIovec_Layout(Runtime arg0) { // было: <init>
        super(arg0);
        iov_base = new StructLayout_Pointer(this);
        iov_len = new StructLayout_size_t(this);
    }

}