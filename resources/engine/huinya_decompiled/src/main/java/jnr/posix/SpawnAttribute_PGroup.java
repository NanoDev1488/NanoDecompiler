// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnAttribute.PGroup
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnAttribute;
import jnr.posix.UnixLibC;

final class SpawnAttribute_PGroup extends SpawnAttribute {

    // ---- поля ----
  final long pgroup;

  public SpawnAttribute_PGroup(long arg0) { // было: <init>
        super();
        pgroup = arg0;
    }

  final boolean set(POSIX arg0, Pointer arg1) {
        return (((UnixLibC) arg0.libc())).posix_spawnattr_setpgroup(arg1, pgroup) == 0;
    }

  public String toString() {
        return new StringBuilder().append("SpawnAttribute::PGroup(pgroup = ").append(pgroup).append(")").toString();
    }

}