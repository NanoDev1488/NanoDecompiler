// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector.FreeBSD12EventLayout
package jnr.enxio.channels;

import jnr.enxio.channels.KQSelector_Anon1;
import jnr.enxio.channels.KQSelector_EventLayout;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_int64_t;
import jnr.ffi.StructLayout_u_int64_t;

class KQSelector_FreeBSD12EventLayout extends KQSelector_EventLayout {

    // ---- поля ----
  public final StructLayout_int64_t data;
  public final StructLayout_Pointer udata;
  public final StructLayout_u_int64_t[] ext;

  private KQSelector_FreeBSD12EventLayout(Runtime arg0) { // было: <init>
        super(arg0, null);
        data = new StructLayout_int64_t(this);
        udata = new StructLayout_Pointer(this);
        ext = ((StructLayout_u_int64_t[]) array(new StructLayout_u_int64_t[4]));
    }

   KQSelector_FreeBSD12EventLayout(Runtime arg0, KQSelector_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}