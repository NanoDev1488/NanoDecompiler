// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector.EventLayout
package jnr.enxio.channels;

import jnr.enxio.channels.KQSelector_Anon1;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_int16_t;
import jnr.ffi.StructLayout_u_int16_t;
import jnr.ffi.StructLayout_u_int32_t;
import jnr.ffi.StructLayout_uintptr_t;

abstract class KQSelector_EventLayout extends StructLayout {

    // ---- поля ----
  public final StructLayout_uintptr_t ident;
  public final StructLayout_int16_t filter;
  public final StructLayout_u_int16_t flags;
  public final StructLayout_u_int32_t fflags;

  private KQSelector_EventLayout(Runtime arg0) { // было: <init>
        super(arg0);
        ident = new StructLayout_uintptr_t(this);
        filter = new StructLayout_int16_t(this);
        flags = new StructLayout_u_int16_t(this);
        fflags = new StructLayout_u_int32_t(this);
    }

   KQSelector_EventLayout(Runtime arg0, KQSelector_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}