// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector.LegacyEventLayout
package jnr.enxio.channels;

import jnr.enxio.channels.KQSelector_Anon1;
import jnr.enxio.channels.KQSelector_EventLayout;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Pointer;
import jnr.ffi.StructLayout_intptr_t;

class KQSelector_LegacyEventLayout extends KQSelector_EventLayout {

    // ---- поля ----
  public final StructLayout_intptr_t data;
  public final StructLayout_Pointer udata;

  private KQSelector_LegacyEventLayout(Runtime arg0) { // было: <init>
        super(arg0, null);
        data = new StructLayout_intptr_t(this);
        udata = new StructLayout_Pointer(this);
    }

   KQSelector_LegacyEventLayout(Runtime arg0, KQSelector_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}