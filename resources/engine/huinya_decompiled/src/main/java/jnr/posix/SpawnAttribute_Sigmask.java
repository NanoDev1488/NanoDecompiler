// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnAttribute.Sigmask
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnAttribute;

final class SpawnAttribute_Sigmask extends SpawnAttribute {

    // ---- поля ----
  final long sigmask;

  public SpawnAttribute_Sigmask(long arg0) { // было: <init>
        super();
        sigmask = arg0;
    }

  final boolean set(POSIX arg0, Pointer arg1) {
        throw new RuntimeException("sigmask not yet supported");
    }

  public String toString() {
        return new StringBuilder().append("SpawnAttribute::Sigmask(mask = ").append(Long.toHexString(sigmask)).append(")").toString();
    }

}