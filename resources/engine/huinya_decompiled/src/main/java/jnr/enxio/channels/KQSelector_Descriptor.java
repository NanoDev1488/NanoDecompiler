// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector.Descriptor
package jnr.enxio.channels;

import java.util.HashSet;
import java.util.Set;

class KQSelector_Descriptor {

    // ---- поля ----
  private final int fd;
  private final Set keys;
  private boolean write;
  private boolean read;

  public KQSelector_Descriptor(int arg0) { // было: <init>
        super();
        keys = new HashSet();
        write = false;
        read = false;
        fd = arg0;
    }

  static Set access$000(KQSelector_Descriptor arg0) {
        return arg0.keys;
    }

  static boolean access$100(KQSelector_Descriptor arg0) {
        return arg0.read;
    }

  static boolean access$102(KQSelector_Descriptor arg0, boolean arg1) {
        arg0.read = arg1;
        return arg1;
    }

  static boolean access$200(KQSelector_Descriptor arg0) {
        return arg0.write;
    }

  static boolean access$202(KQSelector_Descriptor arg0, boolean arg1) {
        arg0.write = arg1;
        return arg1;
    }

  static int access$300(KQSelector_Descriptor arg0) {
        return arg0.fd;
    }

}