// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnAttribute.SetFlags
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnAttribute;
import jnr.posix.UnixLibC;

final class SpawnAttribute_SetFlags extends SpawnAttribute {

    // ---- поля ----
  final short flags;

  public SpawnAttribute_SetFlags(short arg0) { // было: <init>
        super();
        flags = arg0;
    }

  final boolean set(POSIX arg0, Pointer arg1) {
        return (((UnixLibC) arg0.libc())).posix_spawnattr_setflags(arg1, flags) == 0;
    }

  public String toString() {
        return new StringBuilder().append("SpawnAttribute::SetFlags(flags = ").append(Integer.toHexString(flags)).append(")").toString();
    }

}