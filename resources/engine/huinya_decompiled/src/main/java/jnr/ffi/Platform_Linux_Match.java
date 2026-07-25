// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.Linux.Match
package jnr.ffi;

import jnr.ffi.Platform_Anon1;
import jnr.ffi.Platform_Linux;

class Platform_Linux_Match implements Comparable {

    // ---- поля ----
   String path;
   int[] version;
   boolean isCustom;

  private Platform_Linux_Match() { // было: <init>
        super();
    }

  public int compareTo(Platform_Linux_Match arg0) {
        return Platform_Linux.access$300(arg0.version, version);
    }

  public int compareTo(Object arg0) {
        return compareTo(((Platform_Linux_Match) arg0));
    }

   Platform_Linux_Match(Platform_Anon1 arg0) { // было: <init>
        this();
    }

}